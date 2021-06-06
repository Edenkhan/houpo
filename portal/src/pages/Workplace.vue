<template>
  <div>
    <div class="container">
      <div class="con" v-for="item in list" :key="item.index">
        <div class="content part_one">
          <p class="name">{{item.title}}</p>
          <img :src="item.img" alt class="bg"/>
          <div class="des">{{item.des}}</div>
          <div class="point" v-for="re in item.tip" :key="re.index">
            <img src="../assets/img/workplace/radius.png" alt/>
            <span>{{re}}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="button">
      <div v-if="state == 0" @click="toPay(id)" class="sign">待支付</div>
      <div v-else-if="state == 1" class="sign finshed">已报名</div>
      <div @click="showPopup()" v-else class="sign">原价5999元，现在购买，尊享超值 100元/人</div>
    </div>

  </div>
</template>
<script>

  import {Dialog, Toast} from "vant";

  import {checkUser, handIn, queryStatus, profile, baseNotify, pay} from "../api/user";

  export default {
    data() {
      return {
        list: [
          {
            title: "TOP企业游学",
            img: "/img/workplace/1.png",
            des:
              "带领学生走进企业，感受工作场景与文化氛围，增强对企 业经营发展的理解。通过与企业IT精英交流，认知行业发 展现状，了解企业IT发展趋势，开拓眼界，增长专业知识。",
            tip: ["企业内部参观", "企业人才画像", "IT人才成长体系主题交流"]
          },
          {
            title: "《我们生在这个伟大的时代》-职业生涯大讲堂",
            img: "/img/workplace/2.png",
            des:
              "通过职业生涯大讲堂的组织实施，让学生全面了解行业发展情况，并以此进行复合个人实际情况的职业生涯规划。",
            tip: ["行业大牛全方位解读行业发展趋势及技术要点", "职业规划师带领梳理个人专属职业生涯规划"]
          },
          {
            title: "一对一模拟面试",
            img: "/img/workplace/3.png",
            des:
              "人力资源专家通过一对一模拟面试的方式，提供面试技巧及相关建议反馈。",
            tip: [
              "人力资源专家一对一线下模拟面试"
            ]
          },
          {
            title: "IT技术体验营",
            img: "/img/workplace/4.png",
            des: "线上核心技术专业课程学习",
            tip: ["Java核心技术要点", "H5核心技术要点", "UI核心技术要点"]
          },
          {
            title: "《一个职场人士的自我管理修养》-线下精品课",
            img: "/img/workplace/5.png",
            des: "毕业生顺利进入职场基本生存法则学习  ",
            tip: ["目标管理", "时间管理", "职业化修为"]
          }
        ],
        state: null,
        id: null

      };
    },
    components: {
      Toast
    },

    methods: {
      toPay(id) {
        pay({id}).then((data) => {
          this.payHaddle(id,data,this)
        }).catch((res) => {
          Toast({message: "支付失败，请稍后再试", duration: 1000});
        })
      },
      showPopup() {
        try {
          if (this.finshed == true) {
            return;
          }
          checkUser().then((check)=>{
            if(check.check){
              handIn({type: 0}).then((data) => {

                this.payHaddle(data.id,data,this);

              }).catch((res) => {
                Toast({message: "支付失败，请稍后再试", duration: 1000});
              });

            }else{
              Dialog.confirm({
                title: "完善资料",
                message: "请先完善个人相关信息后再报名哦",
                confirmButtonText: "完善"
              });
              window.location.href = "/user/update.html";
            }
          })

        } catch (e) {
        }
      },
      init:function () {
        profile();
        queryStatus({type: 0}).then(res => {
          this.state = res.state;
          this.id = res.id;
        })
      },
      payHaddle:function (id,data,_this) {
        WeixinJSBridge.invoke(
          'getBrandWCPayRequest',
          {
            "appId": data.result.appId,
            "timeStamp": data.result.timeStamp,
            "signType": data.result.signType,
            "nonceStr": data.result.nonceStr,
            "package": data.result.packageValue,
            "paySign": data.result.paySign
          },
          function (res) {
            if (res.err_msg === "get_brand_wcpay_request:ok") {
              baseNotify({id}).then((data) => {
                Toast({
                  message: "报名成功", duration: 3000, onClose: () => {
                    _this.init();
                  }
                });
              }).catch((data)=>{
                Toast({
                  message: data, duration: 1000, onClose: () => {
                    window.location.reload();
                  }
                });
              })
            } else if (res.err_msg === "get_brand_wcpay_request:cancel") {
              Toast({
                message: "放弃支付", duration: 1000, onClose: () => {
                  window.location.reload();
                }
              });
            } else {
              Toast({message: "支付失败，请稍后再试", duration: 1000});
            }
          }
        )
      }

    },
    created() {
      this.init();
    }
  };
</script>
<style scope lang="scss">
  .container {
    margin-bottom: 1.8rem;
    .con {
      .content {
        margin-top: 0.3rem;
        .name {
          font-size: 0.3rem;
          font-family: PingFang SC;
          font-weight: bold;
          color: rgba(0, 0, 0, 1);
          margin-bottom: 0.3rem;
        }
        .bg {
          width: 100%;
          margin-bottom: 0.32rem;
        }
        .des {
          font-size: 0.28rem;
          font-family: PingFang SC;
          font-weight: 500;
          color: rgba(0, 0, 0, 1);
          margin-bottom: 0.21rem;
        }
        .point {
          width: 6.9rem;
          height: 0.32rem;
          line-height: 0.32rem;
          font-size: 0.26rem;
          > img {
            display: inline-block;
            width: 0.11rem;
            height: 0.11rem;
            margin-right: 0.08rem;
          }
          span {
            font-family: PingFang SC;
            font-weight: 500;
            color: rgba(97, 97, 97, 1);
          }
        }
      }
    }
  }

  .button {
    position: fixed;
    bottom: 0rem;
    left: 0rem;
    padding-top: 0.63rem;
    padding-bottom: 0.63rem;
    padding-left: 0.88rem;
    width: 100%;
    height: 1rem;
    .finshed {
      background-color: #F9BAAE !important;
    }
    .sign {

      background-color: #ff846b;
      border-radius: 0.45rem;
      width: 5.74rem;
      text-align: center;
      height: 1rem;
      line-height: 1rem;
      // a {
      display: block;
      font-size: 0.25rem;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
      // }
    }
  }
</style>
