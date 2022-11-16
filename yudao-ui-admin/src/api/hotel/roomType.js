import request from '@/utils/request'

// 创建房间类型
export function createRoomType(data) {
  return request({
    url: '/hotel/room-type/create',
    method: 'post',
    data: data
  })
}

// 更新房间类型
export function updateRoomType(data) {
  return request({
    url: '/hotel/room-type/update',
    method: 'put',
    data: data
  })
}

// 删除房间类型
export function deleteRoomType(id) {
  return request({
    url: '/hotel/room-type/delete?id=' + id,
    method: 'delete'
  })
}

// 获得房间类型
export function getRoomType(id) {
  return request({
    url: '/hotel/room-type/get?id=' + id,
    method: 'get'
  })
}

// 获得房间类型分页
export function getRoomTypePage(query) {
  return request({
    url: '/hotel/room-type/page',
    method: 'get',
    params: query
  })
}

// 导出房间类型 Excel
export function exportRoomTypeExcel(query) {
  return request({
    url: '/hotel/room-type/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 获得所有房间类型列表
export function getRoomTypeList(query) {
  return request({
    url: '/hotel/room-type/list',
    method: 'get',
    params: query
  })
}
