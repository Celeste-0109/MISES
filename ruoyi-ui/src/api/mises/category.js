import request from '@/utils/request'

// 查询商品分类（支持树形结构）列表
export function listCategory(query) {
  return request({
    url: '/mises/category/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类（支持树形结构）详细
export function getCategory(categoryId) {
  return request({
    url: '/mises/category/' + categoryId,
    method: 'get'
  })
}

// 新增商品分类（支持树形结构）
export function addCategory(data) {
  return request({
    url: '/mises/category',
    method: 'post',
    data: data
  })
}

// 修改商品分类（支持树形结构）
export function updateCategory(data) {
  return request({
    url: '/mises/category',
    method: 'put',
    data: data
  })
}

// 删除商品分类（支持树形结构）
export function delCategory(categoryId) {
  return request({
    url: '/mises/category/' + categoryId,
    method: 'delete'
  })
}
