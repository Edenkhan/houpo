<template>
  <div>
    <div class="banner">
      <van-swipe class="my-swipe" :autoplay="6000" indicator-color="white">
        <van-swipe-item v-for="im in pic" :key="im.index">
          <a :href="im.url">
            <img :src="im.address" alt />
          </a>
        </van-swipe-item>
      </van-swipe>
    </div>
    <div class="cp container" @click="assess">
      <img src="../assets/img/index/cp.png" alt />
    </div>
    <div class="exclusive container">
      <p class="exclusive_t">专属推荐</p>
      <div class="state"></div>
      <div class="point" v-for="item in list" :key="item.index">
        <a :href="item.src">
          <img :src="item.img" alt/>
        </a>
        <div class="info">
          <div>{{item.des}}</div>
          <a :href="item.src">立即报名</a>
        </div>
      </div>
      <br>
    </div>
    <tabbar :curpage="msg"></tabbar>
  </div>
</template>

<script>
import tabbar from "../components/Tabbar";
import { Dialog  } from "vant";
import {  profile } from "../api/user";
import { createAssess, assessResult } from "../api/index";
import {getBannerOnindex} from "../api/base";

export default {
  data() {
    return {
      show: false,
      list: [
        {
          img: "/img/index/Workplace.png",
          des: "职场百分百：提升学员职场综合胜任力",
          src: "/workplace.html"
        },
        {
          img: "/img/index/employment.png",
          des: "就业直通车：尊享合作企业独家绿色面试通道，100%保障就业",
          src: "/employment.html"
        }
      ],
      pic: [],
      msg: 0
    };
  },
  created(){
    this.fetch();
  },
  methods: {
    assess() {
      if(this.resubmit){
        return false;
      }
      this.resubmit = true;
      profile()
        .then(data => {
            if(data.user.flag & 1){
              assessResult()
                .then(data => {
                  if(data.code == 200){
                    location.href = "/onlyThirty/index.html";
                  }else if(data.code == 201){
                    window.location.href = data.url;
                  }
                })
                .catch(data => {
                  createAssess().then(res => {
                    window.location.href = res.url;
                  });
                });
            }else {
              this.resubmit = false;
              Dialog.confirm({
                title: "完善资料",
                message: "请先完善个人相关信息后再测评哦",
                confirmButtonText: "去完善信息"
              }).then(res => {
                window.location.href = "/user/update.html";
              });
            }
        })
        .catch(() => {});
    },
    fetch(){
      getBannerOnindex().then(data=>{
        this.pic=data.data;
      })
    },
  },
  components: {
    tabbar
  }
};
</script>
<style lang="scss">
.banner {
  width: 100%;
  height: 3.63rem;
  .my-swipe {
    width: 100%;
    height: 3.63rem;
    color: #fff;
    font-size: 0.2rem;
    .van-swipe-item {
      img {
        width: 100%;
        height: 100%;
      }
    }
  }
}
.cp {
  margin-top: 0.31rem;
  margin-bottom: 0.3rem;
  height: 1.66rem;
  img {
    width: 100%;
    height: 100%;
  }
}
.exclusive {
  margin-bottom: 0.98rem;
  .exclusive_t {
    font-size: 0.3rem;
    font-family: PingFang SC;
    font-weight: bold;
    color: rgba(0, 0, 0, 1);
    margin-bottom: 0.11rem;
  }
  .state {
    width: 0.68rem;
    height: 0.06rem;
    background-color: #fe6a60;
  }
  .point {
    margin-top: 0.3rem;
    font-size: 0.24rem;
    img {
      width: 100%;
      height: 100%;
    }
    .info {
      padding-left: 0.27rem;
      background-color: #fff;
      padding-bottom: .2rem;
      display: flex;
      > div {
        display: inline-block;
        margin-top: 0.31rem;
        margin-bottom: 0.34rem;
        width: 80%;
      }

      > div:nth-child(1) {
        font-size: 0.27rem;
        height: 0.47rem;
        line-height: 0.47rem;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgb(86, 40, 40);
      }

      a {
        padding: 0.1rem 0.1rem;
        width: 20%;
        text-align: center;
        height: 0.4rem;
        line-height: 0.4rem;
        background-color: #fe6a60;
        border-radius: 0.23rem;
        font-size: 0.24rem;
        margin: .25rem .1rem 0;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgba(255, 255, 255, 1) !important;
      }
    }
  }
}
</style>
