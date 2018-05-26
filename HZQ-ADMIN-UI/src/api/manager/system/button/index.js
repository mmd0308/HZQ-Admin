import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/button/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/api/button/add',
    method: 'post',
    data: obj
  })
}

export function checkCode(code, id) {
  return request({
    url: '/api/button/check/' + code + '/code' + '?id=' + id,
    method: 'get'
  })
}

export function getButtonByMenuIdOrRoleId(id, obj) {
  return request({
    url: '/api/button/getButtonByMenuIdOrRoleId/' + id,
    method: 'get',
    params: obj
  })
}

export function getObj(id) {
  return request({
    url: '/api/button/get/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/api/button/delete/' + id,
    method: 'delete'
  })
}

export function putObj(id, obj) {
  return request({
    url: '/api/button/put/' + id,
    method: 'put',
    data: obj
  })
}
