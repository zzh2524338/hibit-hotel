import request from '@/utils/request'

// 创建公司分部
export function createBuilding(data) {
  return request({
    url: '/hotel/building/create',
    method: 'post',
    data: data
  })
}

// 更新公司分部
export function updateBuilding(data) {
  return request({
    url: '/hotel/building/update',
    method: 'put',
    data: data
  })
}

// 删除公司分部
export function deleteBuilding(id) {
  return request({
    url: '/hotel/building/delete?id=' + id,
    method: 'delete'
  })
}

// 获得公司分部
export function getBuilding(id) {
  return request({
    url: '/hotel/building/get?id=' + id,
    method: 'get'
  })
}

// 获得公司分部分页
export function getBuildingPage(query) {
  return request({
    url: '/hotel/building/page',
    method: 'get',
    params: query
  })
}

// 导出公司分部 Excel
export function exportBuildingExcel(query) {
  return request({
    url: '/hotel/building/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
