package com.ruoyi.mises.mapper;

import java.util.List;
import com.ruoyi.mises.domain.Product;

/**
 * 商品基础信息Mapper接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface ProductMapper 
{
    /**
     * 查询商品基础信息
     * 
     * @param productId 商品基础信息主键
     * @return 商品基础信息
     */
    public Product selectProductByProductId(Long productId);

    /**
     * 查询商品基础信息列表
     * 
     * @param product 商品基础信息
     * @return 商品基础信息集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品基础信息
     * 
     * @param product 商品基础信息
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品基础信息
     * 
     * @param product 商品基础信息
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除商品基础信息
     * 
     * @param productId 商品基础信息主键
     * @return 结果
     */
    public int deleteProductByProductId(Long productId);

    /**
     * 批量删除商品基础信息
     * 
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByProductIds(Long[] productIds);
}
