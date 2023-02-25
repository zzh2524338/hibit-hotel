import request from '@/utils/request'

// 创建会员信息
export function createMemberInfo(data) {
  return request({
    url: '/hotel/member-info/create',
    method: 'post',
    data: data
  })
}

// 更新会员信息
export function updateMemberInfo(data) {
  return request({
    url: '/hotel/member-info/update',
    method: 'put',
    data: data
  })
}

// 删除会员信息
export function deleteMemberInfo(id) {
  return request({
    url: '/hotel/member-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得会员信息
export function getMemberInfo(id) {
  return request({
    url: '/hotel/member-info/get?id=' + id,
    method: 'get'
  })
}

// 获得会员信息分页
export function getMemberInfoPage(query) {
  return request({
    url: '/hotel/member-info/page',
    method: 'get',
    params: query
  })
}

export function getMemberInfoPageByName(query) {
  return request({
    url: '/hotel/member-info/page/name',
    method: 'get',
    params: query
  })
}

// 导出会员信息 Excel
export function exportMemberInfoExcel(query) {
  return request({
    url: '/hotel/member-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
