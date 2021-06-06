import 'url-search-params-polyfill';
import {Icon} from "vant";
import Vue from "vue";
import Page from "./Tip.vue";

Vue.use(Icon);

Vue.config.productionTip = false;

new Vue({
  render: h => h(Page),
}).$mount('#app');
