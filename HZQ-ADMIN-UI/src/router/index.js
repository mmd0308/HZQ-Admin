import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '../views/manager/layout/Layout'
import ShowIndexLayout from '../views/show/indexLayout/Layout'

export const constantRouterMap = [
  {
    path: '/login',
    component: () => import('@/views/manager/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    name: 'index',
    children: [{
      path: 'index',
      meta: { title: 'Weclome', icon: 'example' },
      component: () => import('@/views/manager/dashboard/index')
    }]
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: 'icon',
        name: 'Icon',
        component: () => import('@/views/manager/svg-icons/index'),
        meta: { title: '图标管理', icon: 'admin-comment' }
      }
    ]
  },
  {
    path: '',
    component: Layout,
    name: 'System',
    meta: { title: '系统管理', icon: 'admin-system' },
    children: [
      {
        path: 'sysUser',
        name: 'User',
        component: () => import('@/views/manager/system/user/index'),
        meta: { title: '用户管理', icon: 'user' },
        // code: 'MENU_ADMIN_AUTH_USER'
      },
      {
        path: 'sysRole',
        name: 'Role',
        component: () => import('@/views/manager/system/role/index'),
        meta: { title: '角色管理', icon: 'tree' },
        // code: 'MENU_ADMIN_AUTH_ROLE'
      },
      {
        path: 'sysMenu',
        name: 'Menu',
        component: () => import('@/views/manager/system/menu/index'),
        meta: { title: '资源管理', icon: 'tree' },
        // code: 'MENU_ADMIN_AUTH_MENU'
      },
      {
        path: 'sysDict',
        name: 'Dict',
        component: () => import('@/views/manager/system/dict/index'),
        meta: { title: '数据字典管理', icon: 'tree' },
        // code: 'MENU_ADMIN_AUTH_DICT'
      }
    ]
  },
  { path: '*', redirect: '/index', hidden: true }
]

export default new Router({
  mode: 'history', // 后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '',
    component: Layout,
    name: 'System',
    meta: { title: '系统管理', icon: 'admin-system' },
    children: [
      {
        path: 'sysUser',
        name: 'User',
        component: () => import('@/views/manager/system/user/index'),
        meta: { title: '用户管理', icon: 'user' },
        // code: 'MENU_ADMIN_AUTH_USER'
      },
      {
        path: 'sysRole',
        name: 'Role',
        component: () => import('@/views/manager/system/role/index'),
        meta: { title: '角色管理', icon: 'tree' },
        // code: 'MENU_ADMIN_AUTH_ROLE'
      },
      {
        path: 'sysMenu',
        name: 'Menu',
        component: () => import('@/views/manager/system/menu/index'),
        meta: { title: '资源管理', icon: 'tree' },
        // code: 'MENU_ADMIN_AUTH_MENU'
      },
      {
        path: 'sysDict',
        name: 'Dict',
        component: () => import('@/views/manager/system/dict/index'),
        meta: { title: '数据字典管理', icon: 'tree' },
        // code: 'MENU_ADMIN_AUTH_DICT'
      }
    ]
  },
  { path: '*', redirect: '/index', hidden: true }
]
