import {Button, Form, NumberKeyboard, PasswordInput, Toast} from 'vant';
import Vue from "vue";
import 'url-search-params-polyfill';
import Page from "./Verify.vue";

Vue.use(Button);
Vue.use(PasswordInput);
Vue.use(NumberKeyboard);
Vue.use(Form);
Vue.use(Toast);

Vue.prototype.Toast = Toast;
Vue.config.productionTip = false;

new Vue({
  render: h => h(Page),
}).$mount('#app');
