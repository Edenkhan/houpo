<template>
  <div class="container">
    <div class="info">
      <div class="content" v-for="item in list" :key="item.index">
        <p class="name">{{item.title}}</p>
        <img :src="item.img" alt class="bg" />
        <div class="des">{{item.des}}</div>
        <div class="point" v-for="re in item.tip" :key="re.index">
          <img src="../assets/img/employment/radius.png" alt />
          <span>{{re}}</span>
        </div>
      </div>
    </div>
   <div class="button container">
      <div class="sign" :class="finshed?'finshed':''" @click="check">
      {{text}}
      </div>
    </div>
      <!--<van-popup v-model="proto">
      <agreement></agreement>
    </van-popup>-->
  </div>
</template>
<script>
import { checkUser,handInEmployment,queryStatus,profile } from "../api/user";
import agreement from "../components/Agreement";
import {Dialog, Toast}  from "vant";

export default {
  data() {
    return {
      list: [
        {
          title: "一对一就业指导师",
          img: "/img/employment/1.png",
          des:
            "一对一匹配专属就业指导师进行全方位综合评估及就业指导服务，权利保障100%就业安置。",
          tip: [
            "学员综合评估",
            "职业规划",
            "简历辅导及优化",
            "面试技巧指导",
            "合格人才报告输出"
          ]
        },
        {
          title: "《职场新兵训练营》",
          img: "/img/employment/2.png",
          des: "小范围群体线下精品课程助力系统提升职场综合胜任力。",
          tip: [
            "沟通技巧",
            "职场潜规则",
            "职场升职加薪秘诀",
            "合作企业新员工培训通用课程"
          ]
        },
        {
          title: "IT技术强化营",
          img: "/img/employment/3.png",
          des:
            "针对学员个人IT专业技术的全方位训练，以实现可立即上 岗的系统性综合课程。",
          tip: [
            "Java全体系课程（共8个阶段）",
            "H5全体系课程（共12个阶段）",
            "UI全体系课程（共12个阶段）",
            "面试技巧指导"
          ]
        }
      ],
      proto:false,
      text:"立即报名",
      finshed:false
    };
  },
  methods: {
    async check(e) {
        try {
            if(this.finshed==true){
          console.log("已报名");
          return;
        }
        let status = await checkUser();
        if (status.check) {

         // this.proto=true
         let res=await handInEmployment({type:1})
            console.log(res);
            Toast("报名成功")
            this.finshed=true;
            this.text="已报名";
            console.log("cur");


        }else{
            await Dialog.confirm({
          title: "完善资料",
          message: "请先完善个人相关信息后再报名哦",
          confirmButtonText: "完善"
        });
        window.location.href = "/user/update.html";
        }

      } catch (e) {}

      e.preventDefault();
    }
  },
  components:{
    agreement,
      Dialog,
      Toast
  },
  created(){
    profile();
    queryStatus({type:1}).then(res=>{
      console.log(res);
      if(res.orderStatus==1){
        this.text="已报名";
        this.finshed=true;
      }
    })
  }
};
</script>
<style lang="scss" scope>
.info {
  padding-bottom: 1.8rem;
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
.button {
  position: fixed;
  left: 0;
  bottom: 0;
  // width: 100%;
  padding-top: 0.58rem;
  padding-left: 0.88rem;
  padding-bottom: 0.63rem;
    .finshed{
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
    font-size: 0.36rem;
    font-family: PingFang SC;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
    // }
  }
}
</style>
