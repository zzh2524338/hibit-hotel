import request from '@/utils/request'

// 创建房型价格
export function createRoomTypeRate(data) {
  return request({
    url: '/hotel/room-type-rate/create',
    method: 'post',
    data: data
  })
}

// 更新房型价格
export function updateRoomTypeRate(data) {
  return request({
    url: '/hotel/room-type-rate/update',
    method: 'put',
    data: data
  })
}

// 删除房型价格
export function deleteRoomTypeRate(id) {
  return request({
    url: '/hotel/room-type-rate/delete?id=' + id,
    method: 'delete'
  })
}

// 获得房型价格
export function getRoomTypeRate(id) {
  return request({
    url: '/hotel/room-type-rate/get?id=' + id,
    method: 'get'
  })
}

// 获得房型价格分页
export function getRoomTypeRatePage(query) {
  return request({
    url: '/hotel/room-type-rate/page',
    method: 'get',
    params: query
  })
}

// 导出房型价格 Excel
export function exportRoomTypeRateExcel(query) {
  return request({
    url: '/hotel/room-type-rate/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
