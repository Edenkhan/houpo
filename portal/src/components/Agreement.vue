<template>
  <div class="content_tipother">
    <div class="center">
      <p>请仔细阅读以下协议</p>
      <p>《用户协议及隐私保护》</p>
      <div class="text">
        <p>感谢您使用厚朴人才！</p>
        <p>在使用协议内容协议内容协议内容，协议内 容协议内容协议内容。协议内容协议内容协 议内容协议内容协议，内容协议内容，协议 内容协议内容协议内容。协议内容协议内容 协议内容，协议内容协议内容协议，内容协 议内容。协议内容协议内容协议内容，协议 内容协议内容协，议内容在使用协议内容, 协议。内容协议内容协议内容协议，内容协 议内容协议内容协议。内容协议内容协议内 容协议内容协议内容。</p>
      </div>
      <div class="agree" @click="order">同意并报名</div>
    </div>
  </div>
</template>
<script>
import { handIn } from "../api/user";
export default {
methods:{
 async order(){
   let flag=await handIn({type:1})
   console.log(flag);
   if(flag){
     window.location.href="/user/manger.html"
   }
   
    
    
  }
}
}
</script>
<style lang="scss" scope>
.content_tipother {
  width: 6.31rem;
  text-align: center;
  .center {
    width: 5.31rem;
    margin: 0.45rem auto;
    > p:nth-child(1) {
      font-size: 0.36rem;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(38, 38, 38, 1);
    }
    > p:nth-child(2) {
      padding-top: 0.41rem;
      font-size: 0.3rem;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(254, 106, 96, 1);
    }
    .text {
      overflow: auto;
      height: 4.06rem;
      margin-top: 0.33rem;
      text-align: left;
      font-size: 0.28rem;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(38, 38, 38, 1);
    }
    .agree {
      margin-top: 0.47rem;
      margin-left: 0.1rem;
      margin-bottom: 0.56rem;
      width: 5rem;
      text-align: center;
      height: 0.89rem;
      line-height: 0.89rem;
      //   background-image: url("../assets/img/workplace/button.png");
      background-color: #ff846b;
      border-radius: 0.45rem;
      background-size: contain;
      font-size: 0.3rem;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(255, 255, 255, 1);
    }
  }
}
</style>
