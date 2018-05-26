import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/user/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/api/user/add',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/api/user/get/' + id,
    method: 'get'
  })
}

export function getUserById(id) {
  return request({
    url: '/api/user/getUserById',
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/api/user/delete/' + id,
    method: 'delete'
  })
}

export function putObj(id, obj) {
  return request({
    url: '/api/user/put/' + id,
    method: 'put',
    data: obj
  })
}

export function saveUserRole(obj) {
  return request({
    url: '/api/user/saveUserRole',
    method: 'post',
    data: obj
  })
}

