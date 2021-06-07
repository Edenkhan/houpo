const ActivityList = () => import(/* webpackChunkName: "user" */ '../views/activity/ActivityList.vue');
const ActivityEdit = () => import(/* webpackChunkName: "user" */ '../views/activity/ActivityEdit.vue');

export default [
  {
    path: '/activity/list',
    component: ActivityList,
    meta: {
      title: '活动列表',
      permission: 'activity.activity.list'
    }
  },
  {
    path: '/activity/edit',
    component: ActivityEdit,
    meta: {
      title: '修改活动',
      permission: 'activity.activity.edit',
      parents: [
        {
          path: '/activity/list',
          title: '活动列表'
        }
      ]
    }
  },
  {
    path: '/activity/add',
    component: ActivityEdit,
    meta: {
      title: '添加活动',
      permission: 'activity.activity.add',
      parents: [
        {
          path: '/activity/list',
          title: '活动列表'
        }
      ]
    }
  }
]
