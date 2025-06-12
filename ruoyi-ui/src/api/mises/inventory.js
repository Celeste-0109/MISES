import request from '@/utils/request'

// 查询实时库存列表
export function listInventory(query) {
  return request({
    url: '/mises/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询实时库存详细
export function getInventory(productId) {
  return request({
    url: '/mises/inventory/' + productId,
    method: 'get'
  })
}

// 新增实时库存
export function addInventory(data) {
  return request({
    url: '/mises/inventory',
    method: 'post',
    data: data
  })
}

// 修改实时库存
export function updateInventory(data) {
  return request({
    url: '/mises/inventory',
    method: 'put',
    data: data
  })
}

// 删除实时库存
export function delInventory(productId) {
  return request({
    url: '/mises/inventory/' + productId,
    method: 'delete'
  })
}
