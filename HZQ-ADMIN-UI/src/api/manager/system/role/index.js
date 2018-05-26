import request from '@/utils/request'

export function page(query) {
  return request({
    url: '/api/role/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/api/role/add',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/api/role/get/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/api/role/delete/' + id,
    method: 'delete'
  })
}

export function putObj(id, obj) {
  return request({
    url: '/api/role/put/' + id,
    method: 'put',
    data: obj
  })
}
/**
 * 获取所有的启用角色 没有分页
 */
export function getAllEnabledRole() {
  return request({
    url: '/api/role/enabled/',
    method: 'get'
  })
}
/**
 * 根据用户id,获取角色
 * @param {*用户id} id
 */
export function getRoleByUserId(id) {
  return request({
    url: '/api/role/getRoleByUserId/' + id,
    method: 'get'
  })
}

export function addRoleRes(roleId, menu) {
  return request({
    url: '/api/role/addRoleRes/' + roleId,
    data: menu,
    method: 'post'
  })
}

export function getResIdByRoleId(roleId) {
  return request({
    url: '/api/role/getResIdByRoleId/' + roleId,
    method: 'get'
  })
}

