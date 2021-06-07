import {everyPermissions} from "../api/permission";

const allMenus = [
  {
    title: '用户管理',
    key: 'user',
    icon: 'user',
    subMenus: [
      {
        key: '/user/list',
        title: '用户列表',
        permission: 'user.user.list'
      }
    ]
  },
  {
    title: '消息管理',
    key: 'message',
    icon: 'message',
    subMenus: [
      {
        key: '/message/sms/list',
        title: '短信消息',
        permission: 'message.sms.list'
      },
      {
        key: '/message/smsVerification/list',
        title: '验证短信',
        permission: 'message.smsVerification.list'
      }
    ]
  },
  {
    title: '系统管理',
    key: 'platform',
    icon: 'setting',
    subMenus: [
      {
        key: '/platform/employee/list',
        title: '员工列表',
        permission: 'platform.employee.list'
      },
      {
        key: '/platform/role/list',
        title: '角色列表',
        permission: 'platform.role.list'
      },
      {
        key: '/platform/permission/list',
        title: '权限列表',
        permission: 'platform.permission.list'
      }
    ]
  },
  {
    title: '报名管理',
    key: 'enroll',
    icon: 'setting',
    subMenus: [
      {
        key: '/enroll/list',
        title: '报名列表',
        permission: 'enroll.enroll.list'
      }
    ]
  },
  {
    title: '轮播图管理',
    key: 'banner',
    icon: 'setting',
    subMenus: [
      {
        key: '/banner/list',
        title: '轮播图列表',
        permission: 'banner.banner.list'
      }
    ]
  },
  {
    title: '活动管理',
    key: 'activity',
    icon: 'setting',
    subMenus: [
      {
        key: '/activity/list',
        title: '活动列表',
        permission: 'activity.activity.list'
      }
    ]
  }
];

export function getMenus() {
  return allMenus.reduce((prev, menu) => {
    const subMenus = menu.subMenus.reduce((subPrev, subMenu) => {
      if (everyPermissions(subMenu.permission)) {
        subPrev.push(Object.assign({}, subMenu));
      }
      return subPrev;
    }, []);
    if (subMenus.length > 0) {
      prev.push(Object.assign({}, menu, {
        subMenus
      }));
    }
    return prev;
  }, []);
}
