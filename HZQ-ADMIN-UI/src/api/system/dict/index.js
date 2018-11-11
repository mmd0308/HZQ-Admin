import request from '@/utils/request'

export function selectDictList(query) {
  return request({
    url: '/hzq/system/dict/list',
    method: 'get',
    params: query
  })
}

export function selectDictTree() {
  return request({
    url: '/hzq/system/dict/tree',
    method: 'get'
  })
}

export function addDict(dict) {
  return request({
    url: '/hzq/system/dict/add',
    method: 'post',
    data: dict
  })
}

export function editDict(dictId) {
  return request({
    url: '/hzq/system/dict/edit/' + dictId,
    method: 'get'
  })
}

export function deleteDictByIds(dictId) {
  return request({
    url: '/hzq/system/dict/remove',
    method: 'delete',
    params: {dictId}
  })
}

export function editSaveDict(dict) {
  return request({
    url: '/hzq/system/dict/edit',
    method: 'put',
    data: dict
  })
}

export function checkCode(dict) {
  return request({
    url: '/hzq/system/dict/checkCode',
    method: 'post',
    data: dict
  })
}
