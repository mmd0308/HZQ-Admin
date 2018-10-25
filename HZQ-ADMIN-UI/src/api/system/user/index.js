import request from '@/utils/request'

export function selectUserList(query) {
  return request({
    url: '/hzq/system/user/list',
    method: 'get',
    params: query
  })
}

export function addUser(user) {
  return request({
    url: '/hzq/system/user/add',
    method: 'post',
    data: user
  })
}

export function editUser(userId) {
  return request({
    url: '/hzq/system/user/edit/' + userId,
    method: 'get'
  })
}

export function deleteUserByIds(userIds) {
  return request({
    url: '/hzq/system/user/remove',
    method: 'delete',
    params: {userIds}
  })
}

export function editSaveUser(user) {
  return request({
    url: '/hzq/system/user/edit',
    method: 'put',
    data: user
  })
}
