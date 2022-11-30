import request from '@/utils/request'

// 创建会员等级
export function createMemberLevel(data) {
  return request({
    url: '/hotel/member-level/create',
    method: 'post',
    data: data
  })
}

// 更新会员等级
export function updateMemberLevel(data) {
  return request({
    url: '/hotel/member-level/update',
    method: 'put',
    data: data
  })
}

// 删除会员等级
export function deleteMemberLevel(id) {
  return request({
    url: '/hotel/member-level/delete?id=' + id,
    method: 'delete'
  })
}

// 获得会员等级
export function getMemberLevel(id) {
  return request({
    url: '/hotel/member-level/get?id=' + id,
    method: 'get'
  })
}

// 获得会员等级分页
export function getMemberLevelPage(query) {
  return request({
    url: '/hotel/member-level/page',
    method: 'get',
    params: query
  })
}

export function getMemberLevelList() {
  return request({
    url: '/hotel/member-level/list/all',
    method: 'get',
  })
}

// 导出会员等级 Excel
export function exportMemberLevelExcel(query) {
  return request({
    url: '/hotel/member-level/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
