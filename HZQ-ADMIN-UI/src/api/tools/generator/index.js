import request from '@/utils/request'

export function selectTableList(tableInfo) {
  return request({
    url: '/hzq/tools/generator/list',
    method: 'get',
    params: { tableInfo }
  })
}
