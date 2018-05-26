import request from '@/utils/request'

export function tree(id) {
  return request({
    url: '/api/menu/tree/' + id,
    method: 'get'
  })
}

export function getDefaultMenus(auth, code) {
  return request({
    url: '/api/menu/show/getDefaultMenus/' + auth + '/' + code,
    method: 'get'
  })
}

export function checkCode(code, id) {
  return request({
    url: '/api/menu/check/' + code + '/code' + '?id=' + id,
    method: 'get'
  })
}

export function getNextLevelCode(id, lcode) {
  return request({
    url: '/api/menu/getNextLevelCode/' + id + '/' + lcode,
    method: 'get'
  })
}

export function page(query) {
  return request({
    url: '/api/user/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/api/menu/add',
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

export function delObj(id) {
  return request({
    url: '/api/menu/delete/' + id,
    method: 'delete'
  })
}

export function putObj(id, obj) {
  return request({
    url: '/api/menu/put/' + id,
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

