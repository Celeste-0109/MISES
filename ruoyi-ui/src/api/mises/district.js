import request from '@/utils/request'

// 查询district列表
export function listDistrict(query) {
  return request({
    url: '/mises/district/list',
    method: 'get',
    params: query
  })
}

// 查询district详细
export function getDistrict(id) {
  return request({
    url: '/mises/district/' + id,
    method: 'get'
  })
}

// 新增district
export function addDistrict(data) {
  return request({
    url: '/mises/district',
    method: 'post',
    data: data
  })
}

// 修改district
export function updateDistrict(data) {
  return request({
    url: '/mises/district',
    method: 'put',
    data: data
  })
}

// 删除district
export function delDistrict(id) {
  return request({
    url: '/mises/district/' + id,
    method: 'delete'
  })
}
