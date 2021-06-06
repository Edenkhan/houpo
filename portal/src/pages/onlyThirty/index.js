import Vue from "vue";
import Page from "./index.vue"

import {Button,Loading,Divider } from "vant";

Vue.use(Button);
Vue.use(Loading);
Vue.use(Divider);
new Vue({
  render: h => h(Page),
}).$mount('#app');
