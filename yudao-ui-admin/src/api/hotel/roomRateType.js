import request from '@/utils/request'

// 创建房价类型
export function createRoomRateType(data) {
  return request({
    url: '/hotel/room-rate-type/create',
    method: 'post',
    data: data
  })
}

// 更新房价类型
export function updateRoomRateType(data) {
  return request({
    url: '/hotel/room-rate-type/update',
    method: 'put',
    data: data
  })
}

// 删除房价类型
export function deleteRoomRateType(id) {
  return request({
    url: '/hotel/room-rate-type/delete?id=' + id,
    method: 'delete'
  })
}

// 获得房价类型
export function getRoomRateType(id) {
  return request({
    url: '/hotel/room-rate-type/get?id=' + id,
    method: 'get'
  })
}

// 获得房价类型分页
export function getRoomRateTypePage(query) {
  return request({
    url: '/hotel/room-rate-type/page',
    method: 'get',
    params: query
  })
}

// 导出房价类型 Excel
export function exportRoomRateTypeExcel(query) {
  return request({
    url: '/hotel/room-rate-type/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 获得所有房间价格类型列表
export function getRoomRateTypeList(query) {
  return request({
    url: '/hotel/room-rate-type/list',
    method: 'get',
    params: query
  })
}

