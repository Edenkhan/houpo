<template>
  <div class="container">
    <p class="title">{{this.activityManage.title}}</p>
    <p class="time">{{ this.activityManage.createdDate | filterDate('YYYY-MM-DD HH:mm:ss')}}</p>
    <div class="part" v-html="this.activityManage.content" style="font-size: 12px;margin-top: 10px">
      <!--<img :src="item.img" alt />
      <p>{{item.title}}</p>-->
      {{this.activityManage.content}}
    </div>
    <div class="button">
      <div v-if="this.activityManage.enrollStatus===1" >
        <div class="signo" v-if="this.entrance" >已报名</div>
        <div class="sign" v-else @click="signUp">我要报名</div>
      </div>
    </div>
  </div>
</template>
<script>
  import {getActivity, addSignUp} from "../../api/activity"
  import {Toast} from 'vant';

  export default {
    data() {
      const params = new URLSearchParams(window.location.search);
      return {
        id: params.get('id'),
        activityManage:{},
        activitySignUp:{},
        createdDate: null,
        name: null,
        entrance: false,
        enrollStatus: 0,
        content: null
      };
    },
    components: {
    },
    created() {

      getActivity(this.id).then((data) => {
        this.activityManage = data.activityManage;
        this.activitySignUp = data.activitySignUp;
        this.entrance = this.activitySignUp.state === 1;
      }).catch(({message}) => {
        Toast.fail(message);
      })
    },
    methods: {
      signUp() {
        addSignUp({id: this.id}).then((data) => {
          Toast.success("报名成功");
          this.activitySignUp.state = 1;
          this.entrance = true;
        }).catch(({message}) => {
          Toast.fail(message);
        })
      }
    }
  };
</script>
<style scope lang="scss">
  .part {
    img {
      width: 100%;
    }
  }

  .title {
    margin-top: 0.3rem;
    font-size: 0.3rem;
    font-family: PingFang SC;
    font-weight: bold;
    color: rgba(0, 0, 0, 1);
  }

  .time {
    margin-top: 0.2rem;
    font-size: 0.24rem;
    font-family: PingFang SC;
    font-weight: 500;
    color: rgba(153, 153, 153, 1);
  }

  .info {
    .part {
      margin-top: 0.3rem;

      img {
        width: 100%;
      }

      > p {
        font-size: 0.28rem;
        font-family: PingFang SC;
        font-weight: 400;
        color: rgba(0, 0, 0, 1);
      }
    }
  }

  .button {
    height: 2.26rem;
    margin: 0.48rem auto 0;

    .sign {
      width: 100%;
      text-align: center;
      height: 1rem;
      line-height: 1rem;
      background-image: url("../../assets/img/article/button.png");
      background-size: 100% 100%;
      font-size: 0.36rem;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }

    .signo {
      width: 100%;
      text-align: center;
      height: 1rem;
      line-height: 1rem;
      background-image: url("/img/article/button_one.png");
      background-size: 100% 100%;
      font-size: 0.36rem;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
  }
</style>
