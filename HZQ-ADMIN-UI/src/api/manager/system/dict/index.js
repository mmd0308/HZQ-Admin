import request from '@/utils/request'

export function tree(id) {
  return request({
    url: '/api/dict/tree/' + id,
    method: 'get'
  })
}

export function page(query) {
  return request({
    url: '/api/dict/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/api/dict/add',
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

export function getDictByCode(code) {
  return request({
    url: '/api/dict/' + code + '/code',
    method: 'get'
  })
}

export function checkCode(code, id) {
  return request({
    url: '/api/dict/check/' + code + '/code' + '?id=' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/api/dict/delete/' + id,
    method: 'delete'
  })
}

export function putObj(id, obj) {
  return request({
    url: '/api/dict/put/' + id,
    method: 'put',
    data: obj
  })
}
