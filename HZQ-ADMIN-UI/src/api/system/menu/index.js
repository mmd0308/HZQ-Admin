import request from '@/utils/request'

export function selectMenuList(query) {
  return request({
    url: '/hzq/system/menu/list',
    method: 'get',
    params: query
  })
}

export function selectMenuTree() {
  return request({
    url: '/hzq/system/menu/tree',
    method: 'get'
  })
}

export function addMenu(menu) {
  return request({
    url: '/hzq/system/menu/add',
    method: 'post',
    data: menu
  })
}

export function editMenu(menuId) {
  return request({
    url: '/hzq/system/menu/edit/' + menuId,
    method: 'get'
  })
}

export function deleteMenuByIds(menuIds) {
  return request({
    url: '/hzq/system/menu/remove',
    method: 'delete',
    params: {menuIds}
  })
}

export function editSaveMenu(menu) {
  return request({
    url: '/hzq/system/menu/edit',
    method: 'put',
    data: menu
  })
}

export function checkPermission(menu) {
  return request({
    url: '/hzq/system/menu/checkPermission',
    method: 'post',
    data: menu
  })
}
