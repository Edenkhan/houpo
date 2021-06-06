<template>
  <div>
    <van-loading v-if="show" style="position: absolute;top: 30%;left: 50%; transform: translate(-50%, -50%);z-index: 1100"  color="#1989fa" type="spinner">
      加载中...
    </van-loading>
    <div v-else>
      <div class="main_div" v-if="successShow">
        <img src="../../../public/img/onlyThirty/bg.png" class="bg">
        <img v-if="showData.name && showData.name.length > 0" src="../../../public/img/onlyThirty/has_person.png" class="has-person">
        <img v-else src="../../../public/img/onlyThirty/selfdom.png" class="has-person">
        <div class="user_div" v-if="showData.name && showData.name.length > 0">
          <div>
            <div class="user_detail_div">
                <img :src="user.avatar">
            </div>
            <p>{{ user.nikeName }}</p>
          </div>
          <div class="center-relation">
            <img src="../../../public/img/onlyThirty/zhu.png" class="relation-top-img">
            <img src="../../../public/img/onlyThirty/jian.png" class="relation-bottom-img">
          </div>
          <div>
            <div class="user_detail_div">
              <img :src="showData.img">
            </div>
            <p>{{ showData.name }}</p>
          </div>
        </div>
        <div class="user_div" v-else>
          <div style="margin: 0 auto;">
            <div class="user_detail_div">
                <img :src="user.avatar">
            </div>
            <p>{{ user.nikeName }}</p>
          </div>
        </div>

        <div class="content_div">
            <ul>
              <li v-for="showText in showData.data" :key="showText">{{ showText }}</li>
            </ul>
          <img src="../../../public/img/onlyThirty/two_star.png" class="two_star">
          <img src="../../../public/img/onlyThirty/bottom-star.png" class="bottom-star">
        </div>

        <div class="qrcode-div">

          <img class="qrCode" :src="user.qrCode" />
        </div>
        <div class="btn" @click="toAssess()"></div>
      </div>
      <div v-else>
        <p style="font-size: .3rem;color: #999;margin-top: 1rem">测评未完成</p>
        <van-button type="info" @click="toAssess()">继续测评</van-button>
      </div>
    <!--分享-->
      <div v-if="explainShow" class="explain" @click="explainShow = false">
        <img style="width: 100%" src="../../assets/img/assess/explain.png"/>
      </div>
    </div>
  </div>
</template>

<script>
import {Dialog, Toast} from "vant";
import {assessResultJson,share,shareSuccess} from "../../api/index";
import wx from "weixin-js-sdk";

export default {
  name: "index",
  data() {
    return {
      dataList: [
        {
          key: "ISTJ",
          name: "陈屿",
          img: require("../../../public/img/onlyThirty/cheng-yu.png"),
          data: ["把工作放在第一位", "值得信任，重视承诺", "对细节有很强的记忆和判断", "重视和利用符合逻辑、客观的分析", "天生喜怒不形于色，即使危机之时也显得很平静"]
        },
        {
          key: "ESFJ",
          name: "许幻山",
          img: require("../../../public/img/onlyThirty/xu-huan-shang.png"),
          data: ["友好、富有同情心", "理想化自己欣赏的人或物", "乐于别人也能对自己的事情很确信", "喜欢具有主动性和创造性", "对于批评或别人的漠视很敏感"]
        },
        {
          key: "ESTP",
          name: "陈旭",
          img: require("../../../public/img/onlyThirty/chen-xu.png"),
          data: ["活跃、随遇而安、天真率直、快乐", "信任和依赖于自己对这个世界的感受", "喜欢处理、分析与恢复原状的真实事物", "喜欢行动而不是漫谈", "擅长用非传统的方法说服他人"]
        },
        {
          key: "INFP",
          name: "王漫妮",
          img: require("../../../public/img/onlyThirty/wang-man-ni.png"),
          data: ["把内在和谐视为高于其他一切", "个人信仰坚定", "对已知事物之外的可能性很感兴趣", "往往承担太多压力", "关心内在、富有同情心、理解力"]
        },
        {
          key: "ISFP",
          name: "钟晓阳",
          img: require("../../../public/img/onlyThirty/zhong-xiao-yang.png"),
          data: ["保持着许多强烈的个人理想和自己的价值观", "与信赖的人在一起，会表现出自我的另一面", "善于观察周围的人和事", "喜欢有自有空间及照自订的行程办事", "对冲突和分歧很敏感"]
        },
        {
          key: "INTP",
          name: "顾佳",
          img: require("../../../public/img/onlyThirty/gu-jia.png"),
          data: ["有才智和条理性", "外表平静、缄默、超然，内心专心致志于分析问题", "理性的问题解决者", "能更好地组织概会和想法", "发现有创见且合理的解决方法"]
        },
        {
          key: "ENTP",
          name: "林有有",
          img: require("../../../public/img/onlyThirty/lin-you-you.png"),
          data: ["热情开放、足智多谋", "灵感高于一切，力求使想法变为现实", "认为多数的规则和章程就是用来打破的", "在生活中寻找快乐和变化", "喜欢努力理解和回应他人"]
        },
        {
          key: "ISFJ",
          name: "钟晓芹",
          img: require("../../../public/img/onlyThirty/zhong-xiao-qing.png"),
          data: ["忠诚、有奉献精神和同情心", "乐于协助别人", "具有强烈的职业道德", "平和谦虚、勤奋严肃", "喜欢事情能够清晰明确"]
        },
        {
          key: "INFJ",
          name: "",
          img: "",
          data: ["独立的、有创造性的思想家", "具有强烈的感情、坚定的原则和正直的人性", "面对怀疑仍相信自己的看法与决定", "具有本能的洞察力", "深思熟虑的决策者"]
        },
        {
          key: "ENFP",
          name: "",
          img: "",
          data: ["充满热情和新思想，乐观、自然、富有创造性和自信","主动性强并且具有想象力","喜欢和睦，避免冲突","对可能性很感兴趣","具有出色的洞察力和观察力"]
        },
        {
          key: "ENFJ",
          name: "",
          img: "",
          data: ["能很好地理解别人","理想化，通常看到别人身上的优点","精力充沛、满腔热情、富有责任感","具有平和的性格与忍耐力，长于外交","天然的领导者，具有条理性"]
        },
        {
          key: "ENTJ",
          name: "",
          img: "",
          data: ["有极强的预见能力","典型的领导者和决策人","有条理和分析能力","乐于钻研复杂的理论性问题","对于行为的未来结果更感兴趣"]
        },
        {
          key: "ESFP",
          name: "",
          img: "",
          data: ["乐意与人相处，擅长交际，天真率直，很有魅力和说服力","脾气随和、适应性强，热情友好和慷慨大方","乐于合作","现实的观察者，不依赖于理论的解释","喜欢意料不到的事情"]
        },
        {
          key: "ESTJ",
          name: "",
          img: "",
          data: ["很善于完成任务","喜欢操纵局势和促使事情发生，具有责任感、信守承诺","喜欢条理性、及时和尽可能高效率地、系统地开始达到目标","很现实、有头脑、讲求实际","直爽坦率，友善合群"]
        },
        {
          key: "ISTP",
          name: "",
          img: "",
          data: ["坦率、诚实、平等公正讲求实效","理解力强，擅长分析","有条理而直接","好奇心强，而且善于观察","重视事实，是现实主义者"]
        },
        {
          key: "INTJ",
          name: "",
          img: "",
          data: ["思维严谨、有逻辑性、足智多谋","对自己和别人都很苛求","精于理论","是优秀的战略思想家","有见解的组织者"]
        },
        {
          key: "DEFAULT",
          name: "",
          img: "",
          data: ["坦率、诚实、平等公正讲求实效","理解力强，擅长分析","有条理而直接","好奇心强，而且善于观察","重视事实，是现实主义者"]
        }
      ],
      user:{},
      mbtiList:[],
      hldArray:[],
      t12List:[],
      show:true,
      bgShow:false,
      num:0,
      showData:{},
      successShow:true,
      explainShow:false,
      shareFlag:false,

    }
  },
  created() {
    this.result();
  },
  methods: {
    result:function () {
      assessResultJson().then((data)=>{
        this.url = data.url;
        if(data.code == 200){
          this.user = data.user;
          this.mbtiList = data.mbtiList;
          this.hldArray = data.hldArray;
          this.t12List = data.t12List;
          this.show = false;
          this.bgShow = true;
          this.num = data.num;
          this.checkUser();
          this.shareParameter();
          this.getDataByCode(data.characterCode);
        }else if(data.code == 201){
          this.user = data.user;
          this.evpi();
          this.url = data.url;

        }
      }).catch((data) =>{
        Toast(data.message);
      })
    },
    shareParameter(){
      share().then((data) =>{
        this.shareUrl = data.shareUrl;
        wx.config({
          appId: data.jsapiSignature.appId, // 必填，公众号的唯一标识
          timestamp: data.jsapiSignature.timestamp, // 必填，生成签名的时间戳
          nonceStr: data.jsapiSignature.nonceStr, // 必填，生成签名的随机串
          signature: data.jsapiSignature.signature,// 必填，签名
          jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表
        });
        this.share();
      }).catch((message) =>{
        Toast(message);
      })
    },
    checkUser(){
      if(this.user.type === 1 || this.user.share){
        this.assessShow = true;
        this.shareFlag = true;
      }else{
        this.shareFlag = false;
      }
    },
    evpi(){
      if(!this.user.flag & 1){
        Dialog.confirm({
          title: "完善资料",
          message: "请先完善个人相关信息后再测评哦",
          confirmButtonText: "去完善信息"
        }).then(res => {
          window.location.href = "/user/update.html";
        });
      }else{
        this.show = false;
        this.successShow = false;
      }
    },
    getDataByCode(code){
      const showData =
        this.dataList.find((item) =>{
        return item.key == code
      })
      if(showData == null){
        this.showData = this.dataList[this.dataList.length - 1];
      }else{
        this.showData = showData;
      }
      console.log(this.showData);
    },
    toAssess(){
      if(!this.user.phoneNumber){
        Dialog.confirm({
          title: "绑定手机",
          message: "请先绑定手机后再查看测评哦",
          confirmButtonText: "去绑定手机"
        }).then(res => {
          window.location.href = "/user/home.html";
        });
        return;
      }
      if(this.successShow && !this.shareFlag){
        this.explainShow = true;
        return
      }
      location.href = this.url;
    },
    share(){
      wx.ready(() => {   //需在用户可能点击分享按钮前就先调用111
        wx.onMenuShareTimeline({
          // 分享标题
          title: '一键了解自己的职业优势、适合什么工作岗位，大家快来试试吧～',
          link: this.shareUrl+'?id='+this.user.id,
          imgUrl: window.location.protocol + "//" + window.location.hostname+'/img/share.png',
          success: () => {
            shareSuccess();
            this.shareFlag = true;
          },
          cancel: () => {
          }
        })
      })
    }
  }
}
</script>
<style>
body{
  margin: 0;
  padding: 0;
}
</style>
<style scoped lang="scss">
$base-color: #3E447E;
.main_div{
  position: relative;
  min-height: 100vh;
  background-color: #63BCFF;
  .bg{
    width: 100%;
  }
  .has-person{
    position: absolute;
    top: 1.6rem;
    width: 80%;
    left: 10%;
  }
  .user_div{
    position: absolute;
    width: 90%;
    top: 4rem;
    left: 5%;
    display: flex;
    justify-content: space-between;
    .user_detail_div{
      width: 2.2rem;
      height: 2.2rem;
      border-radius: 2.2rem;
      border: 2px solid $base-color;
      text-align: center;
      align-items: center;
      background-color: white;
      img{
        width: 2rem;
        height: 2rem;
        border-radius: 2rem;
        vertical-align: top;
        margin-top: 0.1rem;
      }
    }
    p{
      font-size: 0.28rem;
      text-align: center;
      font-weight: bold;
      margin-top: 0.1rem;
    }
    .center-relation{
      width: 2.48rem;
      text-align: center;
      line-height: 0.3rem;

      .relation-top-img{
        width: 1.17rem;
        margin-top: 0.28rem;
      }
      .relation-bottom-img{
        width: 100%;
      }
    }
  }
  .content_div{
    position: absolute;
    width: 80%;
    padding: 3% 5%;
    border: 2px solid $base-color;
    border-radius: 5px;
    top: 7rem;
    left: 5%;
    background-color: white;
    ul{
      position: relative;
      z-index: 1;
      li{
        font-size: 0.3rem;
        color: $base-color;
        padding: 0.05rem 0;
        &:before{
          content: "";
          display: inline-block;
          width: 3px;
          height: 3px;
          border-radius: 3px;
          background-color: $base-color;
          margin-right: 0.1rem;
          vertical-align: middle;
        }
      }
    }
    .two_star{
      position: absolute;
      width: 1.4rem;
      top: 0.05rem;
      right: 0.05rem;
    }
    .bottom-star{
      position: absolute;
      width: 1.4rem;
      bottom: 0.1rem;
      right: 0.05rem;
    }
  }
  .qrcode-div{
    width: 1.55rem;
    height: 1.55rem;
    background: url("../../../public/img/onlyThirty/qrcode_bg.png");
    background-size: 100% 100%;
    position: absolute;
    top: 11.5rem;
    right: 5%;

    img{
      width: 1.3rem;
      margin: 0.08rem auto 0 0.1rem;
    }
  }
}
.btn{
  background: url("../../assets/img/assess/btn.png") no-repeat;
  background-size: 100% 100%;
  width: 180px;
  height: 40px;
  margin: 10px auto;
  position: fixed;
  bottom: 10px;
  left: 50%;
  transform: translate(-50%,0);
}
.explain{
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0,0,0,.4);
  z-index: 100;
}
</style>
