import request from '@/utils/request'

// 创建订单
export function createOrderInfo(data) {
  return request({
    url: '/hotel/order-info/create',
    method: 'post',
    data: data
  })
}

// 更新订单
export function updateOrderInfo(data) {
  return request({
    url: '/hotel/order-info/update',
    method: 'put',
    data: data
  })
}

// 删除订单
export function deleteOrderInfo(id) {
  return request({
    url: '/hotel/order-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得订单
export function getOrderInfo(id) {
  return request({
    url: '/hotel/order-info/get?id=' + id,
    method: 'get'
  })
}

// 获得订单分页
export function getOrderInfoPage(query) {
  return request({
    url: '/hotel/order-info/page',
    method: 'get',
    params: query
  })
}

// 导出订单 Excel
export function exportOrderInfoExcel(query) {
  return request({
    url: '/hotel/order-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
