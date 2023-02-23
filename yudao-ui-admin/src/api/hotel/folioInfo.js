import request from '@/utils/request'

// 创建房单信息
export function createFolioInfo(data) {
  return request({
    url: '/hotel/folio-info/create',
    method: 'post',
    data: data
  })
}

// 更新房单信息
export function updateFolioInfo(data) {
  return request({
    url: '/hotel/folio-info/update',
    method: 'put',
    data: data
  })
}

// 删除房单信息
export function deleteFolioInfo(id) {
  return request({
    url: '/hotel/folio-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得房单信息
export function getFolioInfo(id) {
  return request({
    url: '/hotel/folio-info/get?id=' + id,
    method: 'get'
  })
}

// 获得房单信息分页
export function getFolioInfoPage(query) {
  return request({
    url: '/hotel/folio-info/page',
    method: 'get',
    params: query
  })
}

// 导出房单信息 Excel
export function exportFolioInfoExcel(query) {
  return request({
    url: '/hotel/folio-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
