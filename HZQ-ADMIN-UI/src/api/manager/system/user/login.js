import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/api/user/login',
    method: 'post',
    params: {
      username,
      password
    }
  })
}

export function register(obj) {
  return request({
    url: '/api/user/show/register',
    method: 'post',
    data: obj
  })
}

export function getUserInfo(token) {
  return request({
    url: '/api/user/getUserinfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/user/logout',
    method: 'post'
  })
}
