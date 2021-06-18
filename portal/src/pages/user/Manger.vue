<template>
  <div class="manger">
    <Tabs
      v-model="active"
      class="tablist"
      title-active-color="#1B1B1B"
      title-inactive-color="#333333"
    >
      <Tab title="全部">
        <div class="order">
          <div class="item" v-for="(item,index) in list" :key="index">
            <div class="desc">
              <p>{{item.content.title}}</p>
              <p v-if="item.state==0?true:false" @click="handleDelete(item.id)">取消</p>
            </div>
            <div class="content">
              <div class="pic">
                <a :href="item.content.src">
                <img :src="item.content.img" alt />
                </a>
              </div>
              <div class="info">
                <p>{{item.content.desc}}</p>
                <span>{{item.date| filterDate('YYYY-MM-DD HH:mm:ss')}}</span>
                <p v-if="item.state==0?true:false">
                  ￥
                  <span>{{item.content.price}}元/人</span>
                </p>
              </div>
            </div>
            <button v-if="item.state == 0" @click="toPay(item.id)">{{states[item.state].name}}</button>
            <button v-else :class="item.state==1||item.state==3?'finsh':''">{{states[item.state].name}}</button>
          </div>
        </div>
      </Tab>
      <Tab title="待支付">
        <div class="order">

          <div class="item" v-for="(item,index) in list" :key="index" v-show="item.state==0?true:false">
            <div class="desc">
              <p>{{item.content.title}}</p>
              <p v-if="item.state==0?true:false" @click="handleDelete(item.id)">取消</p>
            </div>
            <div class="content">
              <div class="pic">
                <a :href="item.content.src">
                <img :src="item.content.img" alt />
                </a>
              </div>
              <div class="info">
                <p>{{item.content.desc}}</p>
                <span>{{item.date| filterDate('YYYY-MM-DD HH:mm:ss')}}</span>
                <p v-if="item.state==0?true:false">
                  ￥
                  <span>{{item.content.price}}元/人</span>
                </p>
              </div>
            </div>
            <button v-if="item.state == 0" @click="toPay(item.id)">{{states[item.state].name}}</button>
            <button v-else :class="item.state==1||item.state==3?'finsh':''">{{states[item.state].name}}</button>
          </div>
        </div>
      </Tab>
      <Tab title="已报名">
        <div class="order">
          <div class="item" v-for="(item,index) in list" :key="index" v-show="item.state==1?true:false">
            <div class="desc">
              <p>{{item.content.title}}</p>
              <!--<p v-if="item.state==0?true:false"><a href="/user/manager.html">取消</a></p>-->
            </div>
            <div class="content">
              <div class="pic">
                <a :href="item.content.src">
                <img :src="item.content.img" alt />
                </a>
              </div>
              <div class="info">
                <p>{{item.content.desc}}</p>
                <span>{{item.date| filterDate('YYYY-MM-DD HH:mm:ss')}}</span>
                <p v-if="item.state==0?true:false">
                  ￥
                  <span>{{item.price}}元/人</span>
                </p>
              </div>
            </div>
            <button :class="item.state==1||item.state==3?'finsh':''">{{states[item.state].name}}</button>
          </div>
        </div>
      </Tab>
    </Tabs>
  </div>
</template>

<script>
import { Tab, Tabs ,Toast} from "vant";
import { serachOrder,deleteOrder,pay,baseNotify } from "../../api/user";

export default {
  name: "",
  data() {
    return {
      //头部tab当前位置索引
      active: 0,
      //已报名标志
      order: false,
      list: [],
      //原始数据
      orderlist: [
        {
          type: 0,
          title: "职场百分百",
          img: require("../../assets/img/perfect.png"),
          desc: "职场百分百：提升学员职场综合 胜任力",
          date: "2020-05-21 18:53:15",
          src:"/workplace.html",
          price: "100",
          text:""
        },
        {
          type: 1,
          title: "就业直通车",
          img: require("../../assets/img/ahead.png"),
          desc: "就业直通车：一对一深度专属就 业辅导",
          date: "2020-05-21 18:53:15",
          src: "/employment.html",
          price: "",
          text:"已报名"
        }
      ],
      states:[
        {code:0,name:'待支付'},
        {code:1,name:'已报名'},
        {code:2,name:'支付失败'},
        {code:3,name:'已取消'},
      ],
      //展示数据
      list:[]
    };
  },
  components: {
    Tab,
    Tabs,
    Toast
  },
  created() {
    this.init();
  },
  methods:{
    toPay:function(id){
      pay({id}).then((data)=>{
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
              baseNotify({id}).then((data)=>{
                Toast({
                  message: "报名成功", duration: 3000, onClose: () => {
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
      }).catch(()=>{
        Toast({message: "支付失败，请稍后再试", duration: 1000});
      })
    },
    init:function(){
      serachOrder().then(res => {
        alert(JSON.stringify(res.data))
        const stateMap = res.data.reduce((prev, current) => {
          prev[current.type] = current.state;
          return prev;
        }, {});
        res.data.forEach(item => {
          if(item.type==0){
            this.list.push({
              id:item.id,
              date:item.createdDate,
              content:this.orderlist[0],
              state:item.state
            })
          }else{
            this.list.push({
              id:item.id,
              date:item.createdDate,
              content:this.orderlist[1],
              state:1
            })
          }
        });
      });
    },
    handleDelete(id){
      deleteOrder({id}).then(() => {
        Toast("取消成功");
        this.list = [];
        this.init();
      });
    }
  }
};
</script>

<style  lang="scss">
body {
  background: #f2f2f2;
}
.manger {
  width: inherit;
  height: 100%;

  .tablist {
    .van-tabs__wrap {
      .van-tabs__nav {
        .van-tab--active {
          span {
            font-size: 0.3rem;
          }
        }
      }
    }
    .order {
      margin-top: 0.5rem;
      width: inherit;
      height: auto;
      .empty {
        font-size: 0.26rem;
        text-align: center;
        font-family: PingFang SC;
        font-weight: bold;
        color: rgba(3, 3, 3, 1);
      }
      .item {
        width: 6.9rem;
        height: 3.93rem;
        background-color: white;
        margin: 0 auto;
        margin-bottom: 0.3rem;
        position: relative;
        border: 0.01rem transparent solid;
        box-sizing: border-box;

        .desc {
          height: 0.29rem;
          width: 6.3rem;

          margin: 0 auto;
          margin-top: 0.3rem;
          display: flex;
          justify-content: space-between;
          p {
            width: 1.48rem;
            height: 0.29rem;
            font-size: 0.275rem;
            font-family: PingFang SC;
            font-weight: bold;
            color: rgba(3, 3, 3, 1);
            margin: 0;
            &:nth-of-type(2) {
              font-size: 0.26rem;
              font-family: PingFang SC;
              font-weight: 500;
              color: rgba(153, 153, 153, 1);
              text-align: right;
            }
          }
        }
        .content {
          height: 2.1rem;
          width: 6.3rem;

          margin: 0 auto;
          margin-top: 0.38rem;
          display: flex;
          justify-content: space-between;
          .pic {
            width: 2.1rem;
            height: 2.1rem;
            img {
              width: 2.1rem;
              height: 2.1rem;
            }
          }
          .info {
            width: 3.91rem;
            height: 2.1rem;

            display: flex;
            flex-direction: column;

            p {
              width: 3.91rem;

              font-size: 0.27rem;
              font-family: PingFang SC;
              font-weight: 500;
              color: rgba(0, 0, 0, 1);
              line-height: 0.36rem;
              margin: 0;

              &:nth-of-type(2) {
                font-size: 0.17rem;
                font-family: PingFang SC;
                font-weight: bold;
                color: rgba(254, 106, 96, 1);
                span {
                  font-size: 0.3rem;
                  font-family: PingFang SC;
                  font-weight: bold;
                  color: rgba(254, 106, 96, 1);
                }
              }
            }
            span {
              width: 2.45rem;
              margin: 0.25rem 0;
              font-size: 0.24rem;
              font-family: PingFang SC;
              font-weight: 500;
              color: rgba(153, 153, 153, 1);
            }
          }
        }
        .finsh {
          background-color: #feb4af;
        }
        button {
          background-color: #fe6a60;
          width: 1.21rem;
          height: 0.57rem;
          position: absolute;
          bottom: 0.29rem;
          right: 0.33rem;
          font-size: 0.24rem;
          font-family: PingFang SC;
          font-weight: 500;
          color: rgba(255, 255, 255, 1);
          border: 0;
          outline: 0;
          border-radius: 0.5rem;
          color: white;
        }
      }
    }
  }
}
</style>
