import request from '@/utils/request'

// 创建客史信息
export function createGuestInfo(data) {
  return request({
    url: '/hotel/guest-info/create',
    method: 'post',
    data: data
  })
}

// 更新客史信息
export function updateGuestInfo(data) {
  return request({
    url: '/hotel/guest-info/update',
    method: 'put',
    data: data
  })
}

// 删除客史信息
export function deleteGuestInfo(id) {
  return request({
    url: '/hotel/guest-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得客史信息
export function getGuestInfo(id) {
  return request({
    url: '/hotel/guest-info/get?id=' + id,
    method: 'get'
  })
}

// 获得客史信息分页
export function getGuestInfoPage(query) {
  return request({
    url: '/hotel/guest-info/page',
    method: 'get',
    params: query
  })
}

// 导出客史信息 Excel
export function exportGuestInfoExcel(query) {
  return request({
    url: '/hotel/guest-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
