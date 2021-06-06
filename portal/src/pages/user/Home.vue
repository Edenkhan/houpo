<template>
  <div class="home">
    <div class="bg-home">
      <div class="info" @click="to">
        <div class="avatar" v-if="logined">
          <img :src="user.avatar" width="100%" style="border-radius: 50%" />
        </div>
        <div class="avatar un" v-else></div>
        <div class="user-message" v-if="logined" style="margin-left: -1.9em">
          <!--          <span>默认微信昵称，可更改</span>-->
          <!--          <span>199****6415</span>-->
          <span>{{ user.nikeName }}</span>
          <span>{{ user.phoneNumber }}</span>
        </div>
        <div class="user-message untext" v-else>
          <span>请登录</span>
        </div>
        <div class="arrow"></div>
      </div>
    </div>

    <van-cell-group>
      <van-cell title="测评报告" :icon="icons.report" @click="toResult">
        <span v-if="user.flag & 4">{{ckcp}}</span>
        <span v-else-if="user.flag & 2">测评中</span>
        <span v-else>{{gcp}}</span>
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #right-icon>
          <van-icon :name="icons.arrow" style="line-height: inherit;" />
        </template>
      </van-cell>
      <van-cell title="绑定手机号" icon="phone-o"  v-if="this.user.phoneNumber ">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <span>已绑定</span>
        <template #right-icon>
          <van-icon :name="icons.arrow" style="line-height: inherit;" />
        </template>
      </van-cell>
      <van-cell title="绑定手机号" v-else icon="phone-o" @click="showBindPhone = true">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <span>去绑定</span>
        <template #right-icon>
          <van-icon :name="icons.arrow" style="line-height: inherit;" />
        </template>
      </van-cell>

      <van-cell title="报名管理" :icon="icons.manger" @click="toManger">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #right-icon>
          <van-icon :name="icons.arrow" style="line-height: inherit;" />
        </template>
      </van-cell>
      <van-cell title="联系我们" :icon="icons.concat" @click="tel">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #right-icon>
          <van-icon :name="icons.arrow" style="line-height: inherit;" />
        </template>
      </van-cell>
      <van-cell title="关于我们" :icon="icons.about" @click="toAbout">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #right-icon>
          <van-icon :name="icons.arrow" style="line-height: inherit;" />
        </template>
      </van-cell>
    </van-cell-group>

    <van-popup v-model="showBindPhone" class="bindPhoneForm">
      <label>绑定手机号</label>

      <van-field
        v-model="bindPhoneForm.phone"
        center
        clearable
        left-icon="phone-o"
        :error-message="errorPhone.code"
        placeholder="请输入绑定手机号"
        @blur="checkPhone"
        class="input_vant"
      >

      </van-field>
      <van-field
        v-model="bindPhoneForm.verificationCode"
        center
        clearable
        :left-icon="icons.safe"
        placeholder="请输入短信验证码"
        :error-message="error.code"
        @blur="checkCode"
        class="input_vant"
      >
        <van-button
          slot="button"
          size="small"
          type="info"
          @click="sendCode"
          :disabled="codeSending"
        >{{btn_text}}</van-button>
      </van-field>
      <div style="display: flex">
        <van-button block type="default" native-type="submit" @click="bindPhoneForm.verificationCode = '';bindPhoneForm.phone = '';showBindPhone = false">
          取消
        </van-button>
        <van-button block type="default" :loading="bindLoading" @click="bindPhone" native-type="submit" style="color:#1f94f7">
          提交
        </van-button>
      </div>
    </van-popup>


    <!--<div class="loginout">
      <van-button block v-if="logined" @click="outline">退出登录</van-button>
    </div>-->
    <tabbar :curpage="msg"></tabbar>
  </div>
</template>

<script>
import tabbar from "../../components/Tabbar";
import { Dialog, Toast } from "vant";
import { logout, profile, checkUser,send,bind } from "../../api/user";
import { maskPhoneNumber } from "../../utils/mask";
import { assessResult, createAssess } from "../../api/index";
import {navigateToPage} from "../../utils/navigate";

export default {
  data() {
    return {
      cp: "",
      gcp: "去测评",
      ckcp: "查看测评结果",
      pendingPsPayOffRequests: 0,
      profileLoading: false,
      loginAuthUrl: '/user/login.html',
      user: {
        avatar: "https://img.yzcdn.cn/vant/cat.jpeg",
        nikeName: "",
        phoneNumber: ""
      },
      error: {
        code: ""
      },
      errorPhone: {
        code: ""
      },
      btn_text:'发送验证码',
      codeSending: false,
      bindLoading:false,
      bindPhoneForm:{
        phone:"",
        verificationCode:''
      },
      resubmit:false,
      showBindPhone:false,
      icons: {
        arrow: require("../../assets/home/more_icon.png"),
        report: require("../../assets/home/icon-report.png"),
        manger: require("../../assets/home/icon-manger.png"),
        concat: require("../../assets/home/icon-concat.png"),
        about: require("../../assets/home/icon-aboutUs.png"),
        safe: require("../../assets/img/safe_icon.png")
      },
      logined: false,
      msg: 2
    };
  },

  created() {
    profile()
      .then(data => {
        this.user = data.user;
        if(this.user.phoneNumber){
          this.user.phoneNumber = maskPhoneNumber(this.user.phoneNumber);
        }
        this.loginAuthUrl = data.loginAuthUrl;
        this.logined = true;
      })
      .catch(() => {
        Toast("系统繁忙，请稍后重试");
      });
  },

  methods: {
    //跳转到个人信息
    to() {
      if (this.logined) {
        window.location.href = "/user/profile.html";
      } else {
        window.location.href = "/user/login.html";
      }
    },
    //联系我们
    tel() {
      Dialog.confirm({
        title: "拨打电话？",
        message: "028-88888888",
        confirmButtonText: "拨打",
        confirmButtonColor: "#79C86A"
      })
        .then(() => {
          // on confirm
        })
        .catch(() => {
          // on cancel
        });
    },
    //退出按钮
    outline() {
      Dialog.confirm({
        title: "确定退出吗？",
        message: "退出后账户将与微信解绑，登录则需要重新验证。"
      })
        .then(() => {
          this.loginout();

          this.logined = false;
        })
        .catch(() => {
          // 无需处理取消
        });
    },
    loginout() {
      Toast.loading({
        message: "退出登录...",
        forbidClick: true
      });
      logout()
        .then(() => {
           navigateToPage(this.loginAuthUrl);
        })
        .catch(({ message }) => {
          // Toast(message);
        });
    },
    //跳转到关于我们页面
    toAbout() {
      window.location.href = "/user/about.html";
    },
    //跳转到报名管理
    toManger() {
      if(this.logined){
        window.location.href = "/user/manger.html";
      } else{
        window.location.href = "/user/login.html";
      }
      //
    },
    sendCode(){
      if(!this.checkPhone()){
        return
      }
      this.codeSending = true;
      send(this.bindPhoneForm.phone).then(res => {
        this.$toast("发送成功");
        this.ladingSecord(res.intervalSeconds);
      }).catch((res)=>{
        this.codeSending = false;
        this.$toast({message: res.message, duration: 3000});
      });
    },
    bindPhone(){
      if(!this.checkPhone()){
        return
      }
      if(!this.checkCode()){
        return
      }
      this.bindLoading = true;
      bind(this.bindPhoneForm).then(res => {
        this.$toast("绑定成功");
        location.reload();
      }).catch((res)=>{
        this.bindLoading = false;
        this.$toast({message: res.message, duration: 3000});
      });
    },
    checkPhone() {
      if (!this.required(this.bindPhoneForm.phone)) {
        this.errorPhone.code = "手机号必填";
        return false;
      }
      var regex = new RegExp("^[0-9]*$");
      if (!regex.test(this.bindPhoneForm.phone)) {
        this.errorPhone.code = "手机号必须是数字";
        return false;
      }
      if (this.bindPhoneForm.phone.length !== 11) {
        this.errorPhone.code = "手机号必须为11位";
        return false;
      }
      this.errorPhone.code = "";
      return true;
    },
    checkCode() {
      if (!this.required(this.bindPhoneForm.verificationCode)) {
        this.error.code = "验证码必填";
        return false;
      }
      var regex = new RegExp("^[0-9]*$");
      if (!regex.test(this.bindPhoneForm.verificationCode)) {
        this.error.code = "验证码必须是数字";
        return false;
      }
      this.error.code = "";
      return true;
    },
    required(val) {
      return val != undefined && val != null && val.length > 0;
    },
    ladingSecord(t) {
      const _this = this;
      _this.time = t;
      let myVar = setInterval(function() {
        if (_this.time <= 0) {
          _this.codeSending = false;
          _this.btn_text = "发送验证码";
          clearInterval(myVar);
          return;
        }
        _this.btn_text = _this.time + "秒";
        _this.time -= 1;
      }, 1000);
    },
    toResult() {
      if(this.resubmit){
        return false;
      }
      this.resubmit = true;
      if (this.logined) {
        if (this.user.flag & 1) {
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
        } else {
          this.resubmit = false;
          Dialog.confirm({
            title: "完善资料",
            message: "请先完善个人相关信息后再测评哦",
            confirmButtonText: "去完善信息"
          }).then(res => {
            window.location.href = "/user/update.html";
          });
        }
      }
    }
  },
  components: {
    tabbar,
    Toast
  }
};
</script>

<style lang="scss" >
body {
  background-color: #f2f2f2;
}
.home {
  .bg-home {
    background: url("../../assets/home/bg_home.png") no-repeat;

    background-size: cover;
    width: 7.5rem;
    height: 2.52rem;
    position: relative;
    .info {
      width: 6.9rem;
      height: 2.24rem;
      background-color: white;
      position: absolute;
      bottom: -0.5rem;
      left: 0.3rem;
      display: flex;
      justify-content: space-around;
      align-items: center;
      border-radius: 0.1rem;
      .avatar {
        background: url("../../assets/home/default_avatar.png") no-repeat;
        background-size: 100% 100%;
        width: 1.2rem;
        height: 1.2rem;
      }
      .un {
        background: url("../../assets/home/unlogin_avatar.png") no-repeat;
        background-size: 100% 100%;
      }
      .user-message {
        margin-left: -0.5rem;
        height: 1.2rem;

        align-self: flex-start;
        span {
          &:nth-of-type(1) {
            font-size: 0.32rem;
            font-family: PingFang SC;
            font-weight: 500;
            color: rgba(0, 0, 0, 1);
          }
          &:nth-of-type(2) {
            display: block;

            font-size: 0.28rem;
            font-family: PingFang SC;
            font-weight: 500;
            color: rgba(173, 172, 172, 1);
          }
        }
      }
      .untext {
        width: 3.5rem;
        align-self: center;
        display: flex;
        align-items: center;

        font-size: 0.36rem;
        font-family: PingFang SC;
        font-weight: 500;
        color: #000000;
      }
      .arrow {
        background: url("../../assets/home/more_icon.png");
        background-size: 100% 100%;
        height: 0.23rem;
        width: 0.13rem;
        cursor: pointer;
      }
    }
  }

  .van-cell-group {
    width: 6.9rem;

    margin: 0 auto;
    margin-top: 1rem;
    margin-bottom: 0.29rem;
    .van-cell {
      height: 1.35rem;
      width: 6.25rem;
      margin: 0 auto;
      line-height: 1.35rem;
      padding: 0;
        span {
          margin-right: 0.18rem;
        }
        .van-cell__value {
          span {
            margin-top: 0.02rem;
          }
        }
      &:not(:last-child) {
        border-bottom: 0.01rem solid #e7e7e7;
      }

      i {
        line-height: 1.35rem;
        margin-right: 0.25rem;

        &:nth-of-type(1) {
          line-height: 1.4rem;
        }
        &:nth-of-type(2) {
          margin: 0;

          img {
            width: 0.13rem;
            height: 0.23rem;
          }
        }
        img {
          width: 0.4rem;
          height: 0.39rem;
        }
      }
    }
  }
  .loginout {
    button {
      width: 6.9rem;
      height: 1rem;
      margin: 0 auto;
      color: #fe9791;
    }
  }
  .bindPhoneForm{
    width: 80%;
    border-radius: 10px;
    background: #f5f5f5;
  }

  .bindPhoneForm label {
    color: #333;
    font-weight: normal;
    font-size: 14px;
    display: block;
    padding: 16px;
    text-align: center;
  }
  .bindPhoneForm p{
    margin: 6px 0 6px 20px;
    color: #333;
    font-size: 14px;
  }
}
</style>
