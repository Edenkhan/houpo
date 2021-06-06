// window.location.replace('/user/home.html');
import Vue from "vue";
import Page from "./Index.vue";
import { Swipe, SwipeItem } from 'vant';

Vue.use(Swipe);
Vue.use(SwipeItem);

new Vue({
    render: h => h(Page),
  }).$mount('#app');
  