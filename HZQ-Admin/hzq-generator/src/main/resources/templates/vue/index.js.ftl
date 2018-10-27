import request from '@/utils/request'

export function select${className}List(query) {
  return request({
    url: '/hzq/${moudleName}/${classNameLower}/list',
    method: 'get',
    params: query
  })
}

export function add${className}(${classNameLower}) {
  return request({
    url: '/hzq/${moudleName}/${classNameLower}/add',
    method: 'post',
    data: ${classNameLower}
  })
}

export function edit${className}(${classNameLower}Id) {
  return request({
    url: '/hzq/${moudleName}/${classNameLower}/edit/' + ${classNameLower}Id,
    method: 'get'
  })
}

export function delete${className}ByIds(${classNameLower}Ids) {
  return request({
    url: '/hzq/${moudleName}/${classNameLower}/remove',
    method: 'delete',
    params: {${classNameLower}Ids}
  })
}

export function editSave${className}(${classNameLower}) {
  return request({
    url: '/hzq/${moudleName}/${classNameLower}/edit',
    method: 'put',
    data: ${classNameLower}
  })
}
