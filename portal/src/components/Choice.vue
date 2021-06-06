<template>
  <div class="content_tip">
    <div class="top">
      <p>完善资料</p>
      <p>请先完善个人相关信息后再报名哦</p>
    </div>
    <div style="height:1px;background:rgba(210,210,210,1);"></div>
    <div class="choice">
      <div @click="cancel">取消</div>
      <div @click="complete">去完善</div>
    </div>
  </div>
</template>
<script>
export default {
  data(){
    return{

    }
  },
  methods: {
   cancel(){

   },
   complete(){}

  },
}
</script>
<style lang="scss" scope>
.content_tip {
  width: 5.18rem;
  text-align: center;
  box-sizing: border-box;
  .top {
    height: 2.25rem;
    p:nth-child(1) {
      padding-top: 0.47rem;
      font-size: 0.36rem;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(38, 38, 38, 1);
    }
    p:nth-child(2) {
      padding-top: 0.62rem;
      font-size: 0.3rem;
      font-family: PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
    }
  }
  .choice {
    > div {
      float: left;
      width: 2.57rem;
      height: 0.9rem;
      line-height: 0.9rem;
      font-size: 0.3rem;
      font-family: PingFang SC;
      font-weight: 500;
    }
    > div:nth-child(1) {
      color: rgba(38, 38, 38, 1);
      border-right: 1px solid rgb(210, 210, 210);
    }
    > div:nth-child(2) {
      color: rgba(93, 190, 74, 1);
    }
  }
}
</style>