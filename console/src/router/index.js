import Vue from 'vue'
import VueRouter from 'vue-router'
import SiderLayoutView from "../views/SiderLayoutView";
import NProgress from "nprogress";
import 'nprogress/nprogress.css';
import user from "./user";
import platform from "./platform";
import message from "./message";
import enroll from "./enroll"
import banner from "./banner";
import {everyPermissions, loadPermissions} from "../api/permission";
import activity from "./activity";

const AccessDenied = () => import(/* webpackChunkName: "platform" */ '../views/platform/AccessDenied');
const Index = () => import(/* webpackChunkName: "platform" */ '../views/platform/Index');

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: SiderLayoutView,
    children: [
      {
        path: '/',
        component: Index
      },
      {
        path: '/accessDenied',
        component: AccessDenied
      },
      ...user,
      ...platform,
      ...message,
      ...enroll,
      ...banner,
      ...activity
    ]
  },
  {
    path: '*',
    component: SiderLayoutView,
    children: [
      {
        path: '*',
        component: AccessDenied
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  NProgress.start();
  loadPermissions()
    .then(() => {
      if (to.meta.permission == null || everyPermissions(to.meta.permission)) {
        next();
      } else {
        next('/accessDenied')
      }
    });
});

router.afterEach((to) => {
  const title = typeof to.meta.title === 'function' ? to.meta.title(to) : to.meta.title;
  if (title) {
    document.title = title + ' · 直播课';
  }
  NProgress.done();
});

export default router
