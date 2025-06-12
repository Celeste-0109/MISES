import request from '@/utils/request'

// 查询采购订单明细列表
export function listPurchase_order_detail(query) {
  return request({
    url: '/mises/purchase_order_detail/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单明细详细
export function getPurchase_order_detail(detailId) {
  return request({
    url: '/mises/purchase_order_detail/' + detailId,
    method: 'get'
  })
}

// 新增采购订单明细
export function addPurchase_order_detail(data) {
  return request({
    url: '/mises/purchase_order_detail',
    method: 'post',
    data: data
  })
}

// 修改采购订单明细
export function updatePurchase_order_detail(data) {
  return request({
    url: '/mises/purchase_order_detail',
    method: 'put',
    data: data
  })
}

// 删除采购订单明细
export function delPurchase_order_detail(detailId) {
  return request({
    url: '/mises/purchase_order_detail/' + detailId,
    method: 'delete'
  })
}
