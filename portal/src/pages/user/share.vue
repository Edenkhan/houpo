<template>
  <div class="us">
    <van-loading v-if="show" style="position: absolute;top: 30%;left: 50%; transform: translate(-50%, -50%)"  color="#1989fa" type="spinner"></van-loading>
    <div v-if="successShow">
      <div v-if="bgShow">
        <div class="box" ref="capture">
          <div class="head">
            <img src="../../assets/img/assess/head.png" style="width: 100%"/>
          </div>
          <div class="avatar">
            <div class="avatar-box">
              <img class="avatar_img" :src="user.avatar" width="100%" />
            </div>
            <div class="avatar-info">
              <p>
                <label>姓名：</label>
                <span>{{user.realName.substr(0,1)}}**</span>
              </p>
              <p>
                <label>职业优势等级：</label>
                <span v-if="num === 1">
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star3.png"/>
              </span>
                <span v-else-if="num === 2">
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star2.png"/>
              </span>
                <span v-else>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
                <img src="../../assets/img/assess/star.png"/>
              </span>
              </p>
            </div>
          </div>
          <div class="content-box">
            <label class="content-label">你的性格类型：</label>
            <ul>
              <li v-for="m in mbtiList"><span></span>{{m}}</li>
            </ul>
          </div>
          <div class="content-box">
            <label class="content-label">你的兴趣倾向：</label>
            <table border="1" cellspacing="0">
              <tr v-for="h in hldArray">
                <td>{{h.name}}</td>
                <td>{{sp(h.desc)}}</td>
              </tr>
            </table>
          </div>
          <div class="content-box">
            <label class="content-label">你的职业优势：</label>
            <table border="1" cellspacing="0">
              <tr v-for="h in t12List">
                <td style="width: 56px;">{{h.name}}型</td>
                <td style="line-height: 16px;text-align: left;">{{h.desc}}</td>
              </tr>
            </table>
          </div>
          <div class="code">
            <div class="code-box">
              <img class="qrCode" :src="user.qrCode" style="width: 100%"/>
            </div>
            <div class="text">
              <p>长按识别二维码</p>
              <p>获取你的专属职业优势测评报告</p>
            </div>
          </div>
          <span class="icon">厚朴人才荣誉出品</span>
        </div>
      </div>

      <img class="fixed-bg" v-if="fShow" :src="dataURL"/>
    </div>
    <div v-if="!successShow">
      <p style="font-size: .3rem;color: #999;margin-top: 1rem">测评未完成</p>
    </div>

  </div>
</template>

<script>
import { Toast } from "vant";
import {shareAssessResultJson} from "../../api/index";
import html2canvas from 'html2canvas';

export default {
  name: "",
  data() {const params = new URLSearchParams(window.location.search);
    return {
      id: params.get('id'),
      show:true,
      bgShow:false,
      successShow:true,
      fShow:false,
      url:null,
      num:0,
      user: {
        avatar: null,
        nikeName: null
      },

      mbtiList:[],
      hldArray:[],
      t12List:[],
      dataURL:''
    };
  },
  components: {
  },
  watch:{
    dataURL(newV, oldV) {
      if (newV && newV.length > 0) {
        this.fShow = true;
        this.show = false;
        this.bgShow = false;
      }
    }
  },
  methods:{
    sp(str){
      return str.split("<br/>")[1].replace("</span>","");
    },
    result:function () {
      shareAssessResultJson(this.id).then((data)=>{
        this.url = data.url;
        if(data.code == 200){
          this.user = data.user;
          this.mbtiList = data.mbtiList;
          this.hldArray = data.hldArray;
          this.t12List = data.t12List;
          this.show = false;
          this.bgShow = true;
          this.num = data.num

        }else if(data.code == 201){
          this.show = false;
          this.successShow = false;
        }
      }).catch((data) =>{
        Toast(data.message);
      })
    },
  },
  mounted(){
  },
  created(){
    this.result();
  }
};
</script>

<style lang="scss">
body{
  background-color:#f2f2f2 ;
}
.us {
  border: 0.01rem solid transparent;
  text-align: center;
  .btn{
    background: url("../../assets/img/assess/btn.png") no-repeat;
    background-size: 100% 100%;
    width: 180px;
    height: 40px;
    margin: 10px auto;
  }
  .loading{
    position: absolute;
    z-index: 2;
    left: 50%;
    top: 30%;
    -webkit-transform: translate(-50%,0);
    transform: translate(-50%, 0);
  }
  .fixed-bg{
    width: 100%;
  }
  .box{
    width: 100%;
    height: 100%;
    position: fixed;
    overflow: auto;
    line-height: 1;
    background: url("../../assets/img/assess/bg.png") no-repeat;
    background-size: 100% 100%;

    .avatar{
      width: 80%;
      margin: 0 auto;
      padding-bottom: 4px;
      display: flex;
      border-bottom: 1px dashed #fff;

      .avatar-box{
        width: 20%;
      }
      .avatar-info{
        width: 80%;
        padding: 6px 0 6px 10px;
        color: #fff;
        font-weight: bold;
        p{
          font-size: .3rem;
          text-align: left;
          span{
            letter-spacing: 1px;

            img{
              width: 20px;
            }
          }
        }
        p:last-child{
          padding-top: 10px;
        }
      }
    }
    .icon{
      font-size: .1rem;
      color: #fff;
      position: absolute;
      bottom: 8px;
      right: 8px;
    }

    .code{
      padding-top: 10px;
      width: 90%;
      margin: 0 auto;
      display: flex;
      padding-bottom: 10px;
      .code-box{
        width: 20%;
      }
      .text{
        padding-left: 10px;
        p{
          font-size: .3rem;
          color: #fff;
          font-weight: bold;
          padding-top: 10px;
          text-align: left;
        }
      }
    }

    .content-box{
      padding-top: 10px;
      width: 100%;
      .content-label{
        color: #f4c82a;
        font-size: .275rem;
        padding-bottom: 6px;
        padding-left: 10%;
        font-weight: bold;
        text-align: left;
        display: block;
      }
      .content-label:before{
        content: '';
        display: inline-block;
        width: 12px;
        height: 12px;
        background: #f4c82a;
        vertical-align: middle;
        transform: rotate(45deg);
        margin-right: 10px;
        margin-top: -2px;
      }
      table{
        border:solid #000;
        border-width:1px 0 0 1px;
        font-size: .20rem;
        width: 90%;
        margin: 0 auto;
        td{
          border:solid #000;
          border-width:0 1px 1px 0;
          padding:6px 4px;
          text-align: center;
          color: #fff;
        }
      }

      ul{
        li{
          color: #fff;
          font-size: 12px;
          line-height: 16px;
          padding: 4px 10%;
          text-align: left;
          position: relative;

          span{
            display: inline-block;
            position: absolute;
            width: 8px;
            height: 8px;
            background: #fff;
            left: 4%;
            top: 10px;
          }
        }

      }
    }

  }
}
</style>
