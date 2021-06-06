import { Button, Cell, CellGroup, Image, Row, Col, Skeleton,Icon} from 'vant';
import Vue from "vue";
import Page from "./Profile.vue";

Vue.use(Button);
Vue.use(Image);
Vue.use(Cell);
Vue.use(CellGroup);
Vue.use(Row);
Vue.use(Col);
Vue.use(Skeleton);
Vue.use(Icon);

Vue.config.productionTip = false;

new Vue({
  render: h => h(Page),
}).$mount('#app');
