import request from '@/utils/request'

// 查询销售订单明细列表
export function listSales_order_detail(query) {
  return request({
    url: '/mises/sales_order_detail/list',
    method: 'get',
    params: query
  })
}

// 查询销售订单明细详细
export function getSales_order_detail(detailId) {
  return request({
    url: '/mises/sales_order_detail/' + detailId,
    method: 'get'
  })
}

// 新增销售订单明细
export function addSales_order_detail(data) {
  return request({
    url: '/mises/sales_order_detail',
    method: 'post',
    data: data
  })
}

// 修改销售订单明细
export function updateSales_order_detail(data) {
  return request({
    url: '/mises/sales_order_detail',
    method: 'put',
    data: data
  })
}

// 删除销售订单明细
export function delSales_order_detail(detailId) {
  return request({
    url: '/mises/sales_order_detail/' + detailId,
    method: 'delete'
  })
}
