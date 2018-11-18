import request from '@/utils/request'

export function selectDepartmentList(query) {
  return request({
    url: '/hzq/system/department/list',
    method: 'get',
    params: query
  })
}

export function selectDepartmentTree() {
  return request({
    url: '/hzq/system/department/tree',
    method: 'get'
  })
}

export function addDepartment(department) {
  return request({
    url: '/hzq/system/department/add',
    method: 'post',
    data: department
  })
}

export function editDepartment(departmentId) {
  return request({
    url: '/hzq/system/department/edit/' + departmentId,
    method: 'get'
  })
}

export function deleteDepartmentByIds(departmentId) {
  return request({
    url: '/hzq/system/department/remove',
    method: 'delete',
    params: { departmentId }
  })
}

export function editSaveDepartment(department) {
  return request({
    url: '/hzq/system/department/edit',
    method: 'put',
    data: department
  })
}
