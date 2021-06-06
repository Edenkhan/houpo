<template>
  <div class="tabbar">
    <div class="item" v-for="(item,index) in list" :key="index" @click="getIndex(index)">
      <div class="toggle-img" v-show="curpage==index?false:true">
        <img :src="item.icon" alt />
      </div>
      <div class="toggle-img" v-show="curpage==index?true:false">
        <img :src="item.active_icon" alt />
      </div>

      <span :class="curpage==index?'active':''">{{item.text}}</span>
    </div>
  </div>
</template>

<script>

export default {
  name: "",
  data() {
    return {
      list: [
        {
          name:"index",
          icon: require("../assets/tabbar/index.png"),
          active_icon: require("../assets/tabbar/index_active.png"),
          text: "首页",
          path: "/index.html"
        },
        {

          name:"activity",
          icon: require("../assets/tabbar/activity.png"),
          active_icon: require("../assets/tabbar/activity_active.png"),
          text: "活动",
          path: "/activity.html"
        },
        {

          name:"home",
          icon: require("../assets/tabbar/count.png"),
          active_icon: require("../assets/tabbar/count_active.png"),
          text: "我的",
          path: "/user/home.html"
        }
      ]
    };
  },
  props: {
    curpage:{
      default:0,
      type:Number
    }
  },
  components: {},
  methods: {
    getIndex(index) {
      this.curindex=index
      location=this.list[index].path;
    }
  }
};
</script>

<style lang="scss">

.tabbar {

  width: 7.5rem;
  height: 0.98rem;
  background-color: white;
  position: fixed;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
  .item {
    height: 0.8rem;
    width: 0.5rem;

    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    .toggle-img {
      width: 0.42rem;
      height: 0.42rem;
      display: flex;
      img {
        width: 0.42rem;
        height: 0.42rem;
      }
    }
    .active {
      color: #fe7d74;
    }

    span {
      font-size: 0.24rem;
      font-family: PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
    }
  }
}
</style>
