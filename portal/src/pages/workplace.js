import 'url-search-params-polyfill';
import Vue from "vue";
import { Popup } from 'vant';
import Page from "./Workplace.vue";

Vue.use(Popup);
Vue.config.productionTip = false;

new Vue({
    render: h => h(Page),
  }).$mount('#app');
  