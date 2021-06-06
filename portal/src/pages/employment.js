import Vue from "vue";
import Page from "./Employment.vue";
import { Popup } from 'vant';
Vue.config.productionTip = false;
Vue.use(Popup)
new Vue({
    render: h => h(Page),
  }).$mount('#app');
  