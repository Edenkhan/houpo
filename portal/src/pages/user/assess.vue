<template>
  <div class="us">
    <van-loading v-if="show" style="position: absolute;top: 30%;left: 50%; transform: translate(-50%, -50%)"  color="#1989fa" type="spinner"></van-loading>
    <div v-if="!successShow">
      <p style="font-size: .3rem;color: #999;margin-top: 1rem">测评未完成</p>
      <van-button type="info" @click="toAssess()">继续测评</van-button>
    </div>
  </div>
</template>

<script>
import { Toast,Dialog } from "vant";
import { profile } from "../../api/user";
export default {
  name: "",
  data() {
    return {
      show:true,
      successShow:true,
      user: {
        avatar: null,
        nikeName: null
      },
    };
  },
  components: {
  },
  created() {
    profile().then(data => {
        this.user = data.user;
        if(!this.user.flag & 1){
          Dialog.confirm({
            title: "完善资料",
            message: "请先完善个人相关信息后再测评哦",
            confirmButtonText: "去完善信息"
          }).then(res => {
            window.location.href = "/user/update.html";
          });
        }else{
          location.href = "/onlyThirty/index.html";
        }
      })
  },
};
</script>

<style lang="scss">
body{
  background-color:#f2f2f2 ;
}
.us {
  border: 0.01rem solid transparent;
  text-align: center;
  background: #02182f;
}
</style>
