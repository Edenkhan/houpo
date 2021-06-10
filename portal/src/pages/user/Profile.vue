<template>
  <div class="profile" >
    <Cell title="头像" class="alone" center>
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
      <template #right-icon>
        <Icon
          :name="avatar"
          style="line-height: inherit;"
          class="avatar"
        />
      </template>
    </Cell>
    <CellGroup>
    <Cell :title="item.title" :value="accounts[item.value]" class="cell" v-for="(item,index) in accountinfo" :key="index"/>
  </CellGroup>
    <div class="edit">
      <van-button block @click="update" is-link >修改资料</van-button>
    </div>
<!--    <Loading />-->
  </div>
</template>

<script>
import { Cell, CellGroup, Icon, Toast, Loading } from "vant";
import areaList from "../../utils/area";
import {getUser} from "../../api/user";
export default {
  name: "",
  data() {
    return {
      avatar:"",
      gender: { 0: "女",1: "男" },
      education: {0: "高中及以下", 1: "大专", 2: "本科", 3: "硕士", 4: "博士及以上"},
      accounts:{},
      areaList: areaList,
      accountinfo:
      [
        {title:"昵称",value:"nikename"},
        {title:"姓名",value:"realName"},
        {title:"年龄",value:"age"},
        {title:"性别",value:"gender"},
        {title:"所学专业",value:"major"},
        {title:"毕业院校",value:"school"},
        {title:"学历水平",value:"education"},
        {title:"期望从事职业",value:"job"},
        {title:"期望就业地区",value:"area"}
        ]
    };
  },
  created() {
        getUser().then((data)=>{
            this.accounts = data;
            this.accounts.gender = this.gender[data.gender]
            this.accounts.education = this.education[data.education]
            if (data.area != null){
              let province = this.areaList.province_list[data.area.substring(0,2)+"0000"];
              this.accounts.area = province + "-" + this.areaList.city_list[data.area];
            }

            this.avatar = data["avatar"]
        }).catch(({message})=>{
            Toast(message)
        });
        // let mark=window.location.href.split("?")[1].split("=")[1]
        //
        // let types=Boolean(mark)
        // this.logined=types


    },
  methods:{
    update(){
      window.location.href='./update.html'
    }
  },
  components: {
    Cell,
    CellGroup,
    Icon,
    areaList,
    Toast,
    Loading,
  }
};
</script>

<style   lang="scss" >

.edit {
  button {
    width: 6.9rem;
    height: 1rem;
    margin: 16px auto;
    color: #fe9791;
  }
}
.profile {
  width: inherit;
  height: 100%;

  border: 0.01rem transparent solid;
  overflow: hidden;
  .alone {
    width: 7.5rem;
    height: 1.16rem;
margin: 0.3rem 0;

    .avatar {
      img {
        width: 0.8rem;
        height: 0.8rem;
      }
    }

    .van-cell__title {
      span {
        font-size: 0.32rem;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgba(173, 172, 172, 1);
      }
    }
  }
  .cell{
    .van-cell__title{
      span{
font-size:0.28rem;
font-family:PingFang SC;
font-weight:500;
color:rgba(173,172,172,1);
      }
    }
    .van-cell__value{
      span{


font-size:0.28rem;
font-family:PingFang SC;
font-weight:500;
color:rgba(51,51,51,1);
      }
    }
  }
}
</style>
