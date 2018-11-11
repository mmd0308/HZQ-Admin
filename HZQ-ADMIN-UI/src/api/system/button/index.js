import request from '@/utils/request'

export function selectButtonList(query) {
  return request({
    url: '/hzq/system/button/list',
    method: 'get',
    params: query
  })
}

export function addButton(button) {
  return request({
    url: '/hzq/system/button/add',
    method: 'post',
    data: button
  })
}

export function editButton(buttonId) {
  return request({
    url: '/hzq/system/button/edit/' + buttonId,
    method: 'get'
  })
}

export function deleteButtonByIds(buttonIds) {
  return request({
    url: '/hzq/system/button/remove',
    method: 'delete',
    params: {buttonIds}
  })
}

export function editSaveButton(button) {
  return request({
    url: '/hzq/system/button/edit',
    method: 'put',
    data: button
  })
}

export function checkPermission(button) {
  return request({
    url: '/hzq/system/button/checkPermission',
    method: 'post',
    data: button
  })
}
