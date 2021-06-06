import Vue from "vue";
import Page from "./Activity.vue";

Vue.config.productionTip = false;

new Vue({
  render: h => h(Page),
}).$mount('#app');
