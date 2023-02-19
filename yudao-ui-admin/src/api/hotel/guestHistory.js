import request from '@/utils/request'

// 创建客史信息
export function createGuestHistory(data) {
  return request({
    url: '/hotel/guest-history/create',
    method: 'post',
    data: data
  })
}

// 更新客史信息
export function updateGuestHistory(data) {
  return request({
    url: '/hotel/guest-history/update',
    method: 'put',
    data: data
  })
}

// 删除客史信息
export function deleteGuestHistory(id) {
  return request({
    url: '/hotel/guest-history/delete?id=' + id,
    method: 'delete'
  })
}

// 获得客史信息
export function getGuestHistory(id) {
  return request({
    url: '/hotel/guest-history/get?id=' + id,
    method: 'get'
  })
}

// 获得客史信息分页
export function getGuestHistoryPage(query) {
  return request({
    url: '/hotel/guest-history/page',
    method: 'get',
    params: query
  })
}

// 导出客史信息 Excel
export function exportGuestHistoryExcel(query) {
  return request({
    url: '/hotel/guest-history/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
