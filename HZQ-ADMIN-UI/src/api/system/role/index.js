import request from '@/utils/request'

export function selectRoleList(query) {
  return request({
    url: '/hzq/system/role/list',
    method: 'get',
    params: query
  })
}

export function addRole(role) {
  return request({
    url: '/hzq/system/role/add',
    method: 'post',
    data: role
  })
}

export function editRole(roleId) {
  return request({
    url: '/hzq/system/role/edit/' + roleId,
    method: 'get'
  })
}

export function deleteRoleByIds(roleIds) {
  return request({
    url: '/hzq/system/role/remove',
    method: 'delete',
    params: {roleIds}
  })
}

export function editSaveRole(role) {
  return request({
    url: '/hzq/system/role/edit',
    method: 'put',
    data: role
  })
}

export function selectRolesByUserId(userId) {
  return request({
    url: '/hzq/system/role/selectRolesByUserId/' + userId,
    method: 'get'
  })
}
