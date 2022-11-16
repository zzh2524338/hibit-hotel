import request from '@/utils/request'

// 创建公司信息
export function createCompany(data) {
  return request({
    url: '/hotel/company/create',
    method: 'post',
    data: data
  })
}

// 更新公司信息
export function updateCompany(data) {
  return request({
    url: '/hotel/company/update',
    method: 'put',
    data: data
  })
}

// 删除公司信息
export function deleteCompany(id) {
  return request({
    url: '/hotel/company/delete?id=' + id,
    method: 'delete'
  })
}

// 获得公司信息
export function getCompany(id) {
  return request({
    url: '/hotel/company/get?id=' + id,
    method: 'get'
  })
}

// 获得公司信息分页
export function getCompanyPage(query) {
  return request({
    url: '/hotel/company/page',
    method: 'get',
    params: query
  })
}

// 导出公司信息 Excel
export function exportCompanyExcel(query) {
  return request({
    url: '/hotel/company/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
