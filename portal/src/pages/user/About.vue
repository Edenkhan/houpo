<template>
  <div class="us">
    <h3>关于我们</h3>
    <div class="content">
      <div class="pic">
        <img src=" https://i.loli.net/2020/05/23/rDBq5Zg2hLmipXa.png" alt />
      </div>
      <h4>页面建设中，敬请期待......</h4>
      <!--<h4>成都厚朴人才科技有限公司(简称厚朴人才)</h4>
      <div class="text">
        <p>
          由四川狼码教育科技有限公司、四川睿华人力资源管
          理有限公司共同出资建立，注册资金200万，2020年5月
          1日正式挂牌运营。
        </p>
        <p>
          厚朴人才坚持“为学生、为企业、为学校负责”的理念
          ，充分挖掘IT领域毕业生职业特点、专业要求，以人力资
          源作为专业抓手，通过系统科学的测评工具全面分析学
          生优劣势，并结合企业人才画像的要求进行定制化的解
          决方案，从源头上全面提升学生立即上岗的即战力水平。
        </p>
        <p>
          在这个火热的时代，我们将秉承“责任、创新、卓越、
          共享”的核心价值观致力于大学生IT领域职业生涯全过程
          管理事业，成为IT领域大学生职业生涯管理系统专业服务
          商。
        </p>
      </div>-->
       <Button class="btn" round type="info" color="linear-gradient(to right, #FF846B, #FF695F)" @click="back">返回</Button>
    </div>
   
  </div>
</template>

<script>
import { Button } from "vant";
export default {
  name: "",
  data() {
    return {};
  },
  components: {
    Button
  },
  methods:{
    back(){
      window.location.href='/user/home.html'
    }
  }
};
</script>

<style  lang="scss">
body{
  background-color:#f2f2f2 ;
}
.us {
 
  border: 0.01rem solid transparent;
     text-align: center;    
  h3 {
      text-align: left;
    margin: 0.31rem 0 0.29rem 0.29rem;
    font-size: 0.36rem;
    font-family: PingFang SC;
    font-weight: bold;
    color: rgba(0, 0, 0, 1);
  }
  .content {
    width: 6.9rem;
    height: auto;
    // background: red;
    margin: 0 auto;
    .pic {
      width: 6.9rem;
      height: 4.36rem;
      img {
        width: 6.9rem;
        height: 4.36rem;
      }
    }
    h4 {
        text-align: left;
      font-size: 0.3rem;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(0, 0, 0, 1);
      margin: 0.3rem 0;
    }
    .text {
      p {
        text-align: justify;
        margin: 0;
        text-indent: 0.4rem;
        font-size: 0.28rem;
        font-family: PingFang SC;
        font-weight: 500;
        color: #383838;
        line-height: 0.36rem;
      }
    }
  }
  .btn{
   margin-top: 0.6rem;
width: 5.74rem;
height: 1rem;

color:white;
font-size: 0.36rem;
  }
}
</style>
