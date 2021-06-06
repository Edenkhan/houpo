import {Button, Field, Form, Toast, Checkbox} from 'vant';
import Vue from "vue";
import 'url-search-params-polyfill';
import Page from "./Login.vue";
import { Popup } from 'vant';


Vue.use(Button);
Vue.use(Field);
Vue.use(Form);
Vue.use(Toast);
Vue.use( Checkbox);
Vue.use(Popup);

Vue.prototype.Toast = Toast;
Vue.config.productionTip = false;

new Vue({
  render: h => h(Page),
}).$mount('#app');
