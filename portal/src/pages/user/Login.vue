<template>
  <div class="login">
    <div class="login-bg">
      <h1>欢迎使用厚朴人才</h1>
    </div>
    <div class="login-form">
      <van-form ref="form">
        <van-field class="input" v-model="phoneNumber" name="phoneNumber" type="digit" placeholder="请输入手机号" :rules="phoneNumberRules"/>
        <van-field class="input" v-model="verifyCode" name="verifyCode" type="digit" placeholder="请输入短信验证码" :rules="verifyCodeRules">
          <template #button>
            <van-button size="small" native-type="button"  type="primary" class="passport" plain @click.stop="sendVerifyCode" :disabled="disabled">{{intervalSeconds}}</van-button>
          </template>
        </van-field>
        <div class="proto">
          <van-checkbox v-model="checked" shape="square" class="checkbox"></van-checkbox>
          <p>
            我已阅读并同意
            <span @click="showPopup">《厚朴用户许可协议》</span>
          </p>
        </div>
        <van-button round type="info" class="submit" color="linear-gradient(to right, #FF826A, #FF695F)" :loading="loading" @click="login">登录 / 注册</van-button>
      </van-form>
    </div>
  </div>
</template>

<script>
import { sendLoginVerifyCode, login } from "../../api/user";
import { Toast } from 'vant';
// import { navigateToPage } from "../../utils/navigate";


const phoneNumberRules = [
  { pattern: /^1\d{10}$/, message: "手机号格式有误" }
];


const verifyCodeRules = [
  { pattern: /\d{4}$/, message: "验证码格式有误" }
];


export default {
  data() {
    const urlParams = new URLSearchParams(window.location.search);
    return {
      code: urlParams.get("code"),
      phoneNumber: null,
      verifyCode: null,
      loading: false,
      intervalSeconds: '发送验证码',
      phoneNumberRules,
      verifyCodeRules,
      checked: false,
      disabled:false,
      showother:''
    };
  },
  components: {
    // agreement
   Toast
  },
  methods: {
    showPopup() {
      this.showother = true;
    },
    startTick() {
     this.disabled=true;
     let second = 120;
        const timer = setInterval(() => {

          if (second) {
            this.intervalSeconds = ` ${second} 秒后重新发送`;
          } else {
            clearInterval(timer);
            this.intervalSeconds = "发送验证码";
            /*Toast.clear();*/
          }
           second--;
        }, 1000);
    },

    sendVerifyCode() {
      // this.loading = true;
      sendLoginVerifyCode({ phoneNumber: this.phoneNumber })
        .then(({ message, intervalSeconds }) => {
            Toast.success("发送成功");
            if (intervalSeconds != null) {
                this.startTick();
            }
        })
        .catch(({ message, data }) => {
            Toast.fail(message);
        })
        .finally(() => {

          this.disabled = false;
        });
    },
    login() {

        if (this.checked == false){
            Toast("请勾选用户许可协议");
            return;
        }
        if(this.phoneNumber == null){
          return;
        }
        if(this.verifyCode == null){
          return;
        }
        this.loading = true;
        login({ phoneNumber: this.phoneNumber, code: this.code, verifyCode: this.verifyCode })
          .then((data) => {
            window.location.href='/index.html';
          })
          .catch(({ message }) => {
            Toast(message);
          })
          .finally(() => {
            this.loading = false;
          });
      }

  }
};
</script>

<style lang="scss">
body {
  background-color: #f2f2f2;
}
.van-field__error-message {
  position: absolute;
  top: 90%;
  z-index: 1;
}
.login {
  .login-bg {
    width: 7.5rem;
    height: 5rem;
    background: url("../../assets/login/bg_login.png") no-repeat;
    background-size: 100% 100%;
    position: relative;
    h1 {
      font-size: 0.48rem;
      font-family: PingFang SC;
      font-weight: bold;
      color: rgba(255, 255, 255, 1);
      position: absolute;
      left: 0.77rem;
      top: 0.94rem;
      margin: 0;
    }
  }
  .login-form {
    text-align: center;
    width: 6.9rem;
    height: 6.9rem;
    background: white;
    transform: translateY(-2.9rem);
    border: 0.01rem transparent solid;
    border-radius: 10px;
    margin: 0 auto;
    .input {
      //  background: red;
      overflow: visible;
      width: 5.8rem;
      height: 1rem;
      margin: 0 auto;
      margin-top: 0.6rem;
      border-bottom: 0.01rem solid #888888;
      color: #b8b8b8;
      font-size: 0.28rem;
      font-family: PingFang SC;
      font-weight: 500;
      // background: yellow  ;
      position: relative;
      .passport {
        color: #ff7a73;
        font-size: 0.24rem;
        border: none;
        position: absolute;
        right: -0.3rem;
        top: -0.08rem;
      }
      &:nth-of-type(2) {
        margin-bottom: 0.64rem;
      }
    }
    .proto {
      // height: 0.24rem;
      width: 5.74rem;
      margin: 0 auto;
      display: flex;
      align-items: center;

      .checkbox {
        .van-checkbox__icon--checked {
          i {
            color: rgb(255, 122, 114);
            background-color: transparent;
            border-color: #888888;
          }
        }
      }
      p {
        margin-left: 0.12rem;

        font-size: 0.24rem;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgba(153, 153, 153, 1);
        span {
          color: #fe7e75;
          cursor: pointer;
        }
      }
    }
.submit{
  width: 5.74rem;
  height: 1rem;
  font-size: 0.36rem;

font-family:PingFang SC;
font-weight:500;
color:rgba(255,255,255,1);


}
  }
}

// .form {
//   background: #fff;
//   border-bottom: 1px solid $body-border-color;
//   padding: 24px 16px;

//   .field-phone-number {
//     margin-bottom: 24px;
//     padding: 12px 0;
//     line-height: 12 * 2px;

//     &:after {
//       left: 0;
//     }
//   }
// }
</style>
