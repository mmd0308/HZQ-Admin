import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'hzq_welcome' }
    }]
  },
  {
    path: '/system',
    component: Layout,
    name: '系统管理',
    meta: { title: '系统管理', icon: 'hzq_system' },
    children: [
      {
        path: 'user',
        name: '用户管理',
        component: () => import('@/views/system/user/index'),
        meta: { title: '用户管理', icon: 'user' }

      },
      {
        path: 'role',
        name: '角色管理',
        component: () => import('@/views/system/role/index'),
        meta: { title: '角色管理', icon: 'role' }
      },
      {
        path: 'menu',
        name: '菜单管理',
        component: () => import('@/views/system/menu/index'),
        meta: { title: '菜单管理', icon: 'hzq_menu' }
      },
      {
        path: 'dict',
        name: 'Dict',
        component: () => import('@/views/system/dict/index'),
        meta: { title: '数据字典管理', icon: 'hzq_dict' }
      },
      {
        path: 'depart',
        name: 'Depart',
        component: () => import('@/views/system/department/index'),
        meta: { title: '部门管理', icon: 'hzq_depart' }
      }
    ]
  },
  {
    path: '/tools',
    component: Layout,
    name: '系统工具',
    meta: { title: '系统工具', icon: 'hzq_tools' },
    children: [
      {
        path: 'generator',
        name: '代码生成',
        component: () => import('@/views/tools/generator/index'),
        meta: { title: '代码生成', icon: 'hzq_code' }

      },
      {
        path: 'task',
        name: 'Task',
        component: () => import('@/views/tools/generator/index'),
        meta: { title: '任务管理', icon: 'hzq_task' }

      },
      {
        path: 'icon',
        name: 'Icons',
        component: () => import('@/views/tools/icons/svg/index'),
        meta: { title: '图标管理', icon: 'hzq_label' }
      }
    ]
  },
  {
    path: '/monitor',
    component: Layout,
    name: 'Monitor',
    meta: { title: '系统监控', icon: 'hzq_monitor' },
    children: [
      {
        path: 'swagger',
        name: '系统接口',
        component: () => import('@/views/monitor/swagger/index'),
        meta: { title: '系统接口', icon: 'hzq_interface' }
      },
      {
        path: 'druid',
        name: 'druid',
        component: () => import('@/views/monitor/druid/index'),
        meta: { title: 'SQL监控', icon: 'hzq_druid' }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  mode: 'history', // 后端支持可开 去除路径中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
