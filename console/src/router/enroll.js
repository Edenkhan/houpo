const EnrollList = () => import(/* webpackChunkName: "user" */ '../views/enroll/EnrollList.vue');

export default [
  {
    path: '/enroll/list',
    component: EnrollList,
    meta: {
      title: '报名列表',
      permission: 'enroll.enroll.list'
    }
  }
]
