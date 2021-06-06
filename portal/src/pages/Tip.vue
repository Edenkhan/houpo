<template>
  <div>
    <div class="block">
      <van-icon name="warning-o" size="16"/> {{ message }}
    </div>
    <div class="copyright">联系我们：028-88888888</div>
  </div>
</template>

<script>
  export default {
    data() {
      const urlParams = new URLSearchParams(window.location.search);
      return {
        message: urlParams.get('message') || '无效访问！'
      }
    }
  }
</script>

<style lang="scss">
  body {
    margin: 0;
    font-size: 14px;
  }
  .block {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: #999999;
    padding: 32px 16px;
    background: #fff;
    border-bottom: 1px solid $body-border-color;

    .van-icon {
      margin-right: 4px;
    }
  }
</style>
