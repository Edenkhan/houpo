const BannerList = () => import(/* webpackChunkName: "user" */ '../views/banner/BannerList.vue');
const BannerEdit = () => import(/* webpackChunkName: "user" */ '../views/banner/BannerEdit.vue');

export default [
  {
    path: '/banner/list',
    component: BannerList,
    meta: {
      title: '轮播图列表',
      permission: 'banner.banner.list'
    }
  },
  {
    path: '/banner/edit',
    component: BannerEdit,
    meta: {
      title: '修改轮播图',
      permission: 'banner.banner.edit',
      parents: [
        {
          path: '/banner/list',
          title: '轮播图列表'
        }
      ]
    }
  },
  {
    path: '/banner/add',
    component: BannerEdit,
    meta: {
      title: '添加轮播图',
      permission: 'banner.banner.add',
      parents: [
        {
          path: '/banner/list',
          title: '轮播图列表'
        }
      ]
    }
  }
]
