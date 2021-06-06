<template>
  <div>
    <van-number-keyboard
      :show="showKeyboard"
      @input="onVerifyCodeInput"
      @delete="onVerifyCodeDelete"
      @blur="showKeyboard = false"
    />

    <van-form class="form" @submit="onSubmit">
      <h2>输入验证码</h2>
      <p class="tip text-muted">
        验证码已发送至 {{ maskPhoneNumber }}，请在下方输入，5 分钟内有效。如未收到，<!--
        -->
        <template v-if="intervalSeconds > 0"><span class="interval-seconds">{{ intervalSeconds }}</span> 秒后可重新发送。
        </template><!--
        -->
        <template v-else>请点击 <a href="javascript:" @click="sendVerifyCode">重新发送</a>。</template>
      </p>
      <van-password-input
        :value="verifyCode"
        :length="4"
        :mask="false"
        info="验证码为 4 位数字"
        :error-info="errorInfo"
        :focused="showKeyboard"
        @focus="showKeyboard = true"
      />
      <van-button class="btn-login" round block type="primary" :loading="loginLoading" loading-text="正在登录 ...">登录
      </van-button>
    </van-form>
    <div class="copyright"></div>
  </div>
</template>

<script>
  import {maskPhoneNumber} from "../../utils/mask";
  import {login, sendLoginVerifyCode} from "../../api/user";
  import {navigateToPage} from "../../utils/navigate";

  export default {
    data() {
      const urlParams = new URLSearchParams(window.location.search);
      return {
        code: urlParams.get('code'),
        verifyCode: '',
        errorInfo: null,
        loginLoading: false,
        intervalSeconds: 0,
        showKeyboard: false,
        phoneNumber: urlParams.get('phoneNumber'),
        timer: null
      }
    },
    created() {
      this.startTick();

      if (process.env.NODE_ENV === 'development') {
        window.addEventListener('keydown', e => {
          if ("0123456789".indexOf(e.key) !== -1) {
            this.onVerifyCodeInput(e.key);
          } else if ("Backspace" === e.key) {
            this.onVerifyCodeDelete();
          } else if ("Enter" === e.key) {
            this.onSubmit();
          }
        });
        window.addEventListener('paste', e => {
          const paste = (event.clipboardData || window.clipboardData).getData('text');
          this.onVerifyCodeInput(paste);
        });
      }
    },

    computed: {
      maskPhoneNumber() {
        return maskPhoneNumber(this.phoneNumber);
      }
    },

    methods: {
      onSubmit() {
        if (this.verifyCode.length < 4) {
          this.showKeyboard = true;
          this.errorInfo = '请输入验证码';
          return;
        }

        this.loginLoading = true;
        login({
          phoneNumber: this.phoneNumber,
          verifyCode: this.verifyCode,
          code: this.code
        }).then(() => {
          navigateToPage('/user/home.html');
        }).catch(({message, data}) => {
          this.errorInfo = message;
          if (data != null) {
            const {verifyCodeIncorrect} = data;
            if (verifyCodeIncorrect) {
              this.verifyCode = '';
              this.showKeyboard = true;
            }
          }
        }).finally(() => {
          this.loginLoading = false;
        });
      },

      onVerifyCodeInput(key) {
        this.verifyCode = (this.verifyCode + key).slice(0, 4);
        if (this.verifyCode.length === 4) {
          this.showKeyboard = false;
        }
        this.errorInfo = null;
      },
      onVerifyCodeDelete() {
        this.verifyCode = this.verifyCode.slice(0, this.verifyCode.length - 1);
      },

      sendVerifyCode() {
        this.Toast.loading({
          message: '加载中...',
          forbidClick: true
        });
        sendLoginVerifyCode({phoneNumber: this.phoneNumber})
          .then(({message, intervalSeconds}) => {
            this.Toast(message);
            if (intervalSeconds != null) {
              this.intervalSeconds = intervalSeconds;
              this.startTick();
            }
          })
          .catch(({message, data}) => {
            this.Toast(message);
            if (data != null) {
              const {intervalSeconds} = data;
              if (intervalSeconds != null) {
                this.intervalSeconds = intervalSeconds;
                this.startTick();
              }
            }
          })
      },

      startTick() {
        if (this.timer) {
          clearInterval(this.timer);
        }
        this.timer = setInterval(() => {
          if (this.intervalSeconds === 0) {
            clearInterval(this.timer);
            return;
          }
          this.intervalSeconds--;
        }, 1000);
      }
    }
  }
</script>

<style lang="scss">
  .form {
    background: #fff;
    border-bottom: 1px solid $body-border-color;
    padding: 24px 16px;
  }

  .btn-login {
    margin-top: 24px;
  }

  .van-password-input {
    margin: 0;
  }

  .tip {
    font-size: 14px;
    line-height: 1.5;
  }

  .interval-seconds {
    color: #333;
  }
</style>
