import Vue from "vue";
import Page from "./Article.vue";
import moment from "moment";

Vue.config.productionTip = false;
Vue.filter('filterDate', function (timestamp, pattern = 'YYYY-MM-DD') {
  if (timestamp == null) {
    return '';
  }
  return moment(timestamp).format(pattern);
});
new Vue({
  render: h => h(Page),
}).$mount('#app');
