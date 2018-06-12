import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
// import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // 验权

const whiteList = ['/login', '/'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) { // 如果token存在
    console.log('token 存在 ' + getToken())
    console.log('路径 ：  ' + to.path)
    if (to.path === '/login') {
      next({ path: '/index' })
    } else {
      if (store.getters.userId === '' || store.getters.userId === null) { // 如果没有角色，获取用户信息
        store.dispatch('GetUserInfo').then(res => { // 拉取用户信息
          if (res.data.menus != null) {
            store.dispatch('GenerateRoutes', res.data.menus).then(() => { // 生成可访问的路由表
              router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
              next({ ...to }) // hack方法 确保addRoutes已完成
            })
          } else {
            next()
          }
        }).catch(() => {
          store.dispatch('FedLogOut').then(() => {
            // Message.error('验证失败,请重新登录')
            console.log('验证失败,请重新登录')
            next({ path: '/login' })
          })
        })
      } else {
        next()
      }
    }
  } else { // 如果token不存在
    if (whiteList.indexOf(to.path) !== -1) { // 白名单中是否存在
      next()
    } else {
      console.log('-----permission token 不存在 不再白名单')
      next('/login') // 如果没有权限，直接返回首页面
      // NProgress.done(); // 在hash模式下 改变手动改变hash 重定向回来 不会触发afterEach 暂时hack方案 ps：history模式下无问题，可删除该行！
    }
  }
})
router.afterEach(() => {
  NProgress.done() // 结束Progress
})
