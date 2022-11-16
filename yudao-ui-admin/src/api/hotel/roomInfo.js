import request from '@/utils/request'

// 创建房间信息
export function createRoomInfo(data) {
  return request({
    url: '/hotel/room-info/create',
    method: 'post',
    data: data
  })
}

// 更新房间信息
export function updateRoomInfo(data) {
  return request({
    url: '/hotel/room-info/update',
    method: 'put',
    data: data
  })
}

// 删除房间信息
export function deleteRoomInfo(id) {
  return request({
    url: '/hotel/room-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得房间信息
export function getRoomInfo(id) {
  return request({
    url: '/hotel/room-info/get?id=' + id,
    method: 'get'
  })
}

// 获得房间信息分页
export function getRoomInfoPage(query) {
  return request({
    url: '/hotel/room-info/page',
    method: 'get',
    params: query
  })
}

// 导出房间信息 Excel
export function exportRoomInfoExcel(query) {
  return request({
    url: '/hotel/room-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
