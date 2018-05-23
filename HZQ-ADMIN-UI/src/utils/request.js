import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken, setToken } from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  // baseURL: process.env.BASE_API, // api的base_url
  timeout: 15000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  if (store.getters.token) {
    config.headers['ACCESS-TOKEN'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    console.log('发送数据请求携带的token:' + getToken())
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    console.log('请求返回信息：' + res)
    if (response.status === 200) { // 请求成功
      if (res === '') { // 表示请求被拦截
        MessageBox.confirm('会话超时，请重新登录，或者取消继续留在该页面', '确定退出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
            location.href = '/login'
          })
        })
        // return Promise.reject('error')
      } else if (res.status === 308001) { // 登录失败  登录名或者密码错误
        // router.push({path: '/error/401'})
        Message({
          message: res.data,
          type: 'error',
          duration: 5 * 1000
        })
      } else if (res.status === 308100) { // 更新token
        setToken(res.token)
        console.log('更新token' + res.token)
        return res
      }
    }
    // 请求状态不是200 操作错误信息
    if (response.status !== 200 || res.status !== 200) {
      Message({
        message: res.data,
        type: 'error',
        duration: 5 * 1000
      })
    } else {
      return response.data
    }
  },
  error => {
    // 如果拦截器拦截，就返回500错误信息 存在bug 稍后处理
    console.log('responseErr:' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
