<template>
  <div>
    <div class="bgc">
      <List
        v-model="loading"
        :finished="finished"
        finished-text="没有更多了"
        :error.sync="error"
        error-text="请求失败，点击重新加载"
        @load="onLoad"
      >
        <div class="container" v-for="item in list" :key="item.id">
          <div class="content">

            <a :href= "`article.html?id=${item.id}`">
              <img :src="item.imageUrl" style="width: 100%;height: 170px" />
              <span class="triangle" v-if="item.releaseStatus==1 && item.enrollStatus==1">已报名</span>
              <div class="info">

                <span v-if="item.releaseStatus==1">【活动】</span>
                <span v-else>【公告】</span>
                <span>{{item.title}}</span>
              </div>
            </a>
          </div>
        </div>
      </List>

      <br>
    </div>
    <tabbar :curpage="msg"></tabbar>
  </div>
</template>
<script>
import tabbar from "../../components/Tabbar";
import {activityList} from "../../api/activity";
import { Toast, List } from 'vant';

export default {
  data() {
    return {
      list: [],
      msg: 1,
      page: 1,
      finished: false,
      loading: false,
      error:false
    };
  },
  components: {
    tabbar,
    Toast,
      List
  },

  methods: {
      onLoad() {
          this.loading = true;
          this.fetch(this.page)
          this.page += 1;
      },
      fetch(page) {
          activityList({page:page}).then((data)=>{
              data.data.forEach((val, index, arr)=>{
                  this.list.push(val);
              })
              if (this.list.length==data.rows){
                  this.finished = true;
              }

          }).catch(({message})=>{
              Toast.fail(message);
              this.error = true;
          }).finally(()=>{
              this.loading=false;
          })
      }
  },

};
</script>
<style lang="scss">
.triangle{
  width: 60px;
  height: 60px;
  position: absolute;
  background: #09d;
  top: -30px;
  right: -30px;
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
  color: #fff;
  font-size: .2rem;
  display: inline-block;
  line-height: 100px;
  text-align: center;

}
.bgc {
  width: 100%;
  height: 100%;
  background-color: #f2f2f2;
  margin-bottom: 0.98rem;
  .container {
    .content {
      overflow: hidden;
      position: relative;
      margin-top: 0.3rem;
      a {
        img {
          width: 100%;
          height: 100%;
        }
        .info {
          font-size: 0.28rem;
          font-family: PingFang SC;
          font-weight: 500;
          color: rgba(0, 0, 0, 1);
        }
      }
    }
  }
}
</style>
