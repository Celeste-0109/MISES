package com.ruoyi.mises.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mises.mapper.ProductMapper;
import com.ruoyi.mises.domain.Product;
import com.ruoyi.mises.service.IProductService;

/**
 * 商品基础信息Service业务层处理
 * 
 * @author AvA
 * @date 2025-03-09
 */
@Service
public class ProductServiceImpl implements IProductService 
{
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询商品基础信息
     * 
     * @param productId 商品基础信息主键
     * @return 商品基础信息
     */
    @Override
    public Product selectProductByProductId(Long productId)
    {
        return productMapper.selectProductByProductId(productId);
    }

    /**
     * 查询商品基础信息列表
     * 
     * @param product 商品基础信息
     * @return 商品基础信息
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增商品基础信息
     * 
     * @param product 商品基础信息
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {
        return productMapper.insertProduct(product);
    }

    /**
     * 修改商品基础信息
     * 
     * @param product 商品基础信息
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除商品基础信息
     * 
     * @param productIds 需要删除的商品基础信息主键
     * @return 结果
     */
    @Override
    public int deleteProductByProductIds(Long[] productIds)
    {
        return productMapper.deleteProductByProductIds(productIds);
    }

    /**
     * 删除商品基础信息信息
     * 
     * @param productId 商品基础信息主键
     * @return 结果
     */
    @Override
    public int deleteProductByProductId(Long productId)
    {
        return productMapper.deleteProductByProductId(productId);
    }
}
