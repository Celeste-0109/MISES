import request from '@/utils/request'

// 查询商品基础信息列表
export function listProduct(query) {
  return request({
    url: '/mises/product/list',
    method: 'get',
    params: query
  })
}

// 查询商品基础信息详细
export function getProduct(productId) {
  return request({
    url: '/mises/product/' + productId,
    method: 'get'
  })
}

// 新增商品基础信息
export function addProduct(data) {
  return request({
    url: '/mises/product',
    method: 'post',
    data: data
  })
}

// 修改商品基础信息
export function updateProduct(data) {
  return request({
    url: '/mises/product',
    method: 'put',
    data: data
  })
}

// 删除商品基础信息
export function delProduct(productId) {
  return request({
    url: '/mises/product/' + productId,
    method: 'delete'
  })
}
