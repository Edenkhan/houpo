
import Vue from "vue";
import Page from "./assess.vue";
import {Button,Loading,Divider } from "vant";

Vue.use(Button);
Vue.use(Loading);
Vue.use(Divider);
Vue.config.productionTip = false;

new Vue({
  render: h => h(Page),
}).$mount('#app');
