import request from '@/utils/request'

export function selectTableList(tableInfo) {
  return request({
    url: '/hzq/tools/generator/list',
    method: 'get',
    params:  tableInfo 
  })
}

export function generatorCode(tables) {
  return request({
    url: '/hzq/tools/generator/generatorCode',
    method: 'get',
    params: tables
  })
}
