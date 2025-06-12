package com.ruoyi.mises.service;

import com.ruoyi.mises.domain.Category;

import java.util.List;

/**
 * 商品分类（支持树形结构）Service接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface ICategoryService 
{
    /**
     * 查询商品分类（支持树形结构）
     * 
     * @param categoryId 商品分类（支持树形结构）主键
     * @return 商品分类（支持树形结构）
     */
    public Category selectCategoryByCategoryId(Long categoryId);

    /**
     * 查询商品分类（支持树形结构）列表
     * 
     * @param category 商品分类（支持树形结构）
     * @return 商品分类（支持树形结构）集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品分类（支持树形结构）
     * 
     * @param category 商品分类（支持树形结构）
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品分类（支持树形结构）
     * 
     * @param category 商品分类（支持树形结构）
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除商品分类（支持树形结构）
     * 
     * @param categoryIds 需要删除的商品分类（支持树形结构）主键集合
     * @return 结果
     */
    public int deleteCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除商品分类（支持树形结构）信息
     * 
     * @param categoryId 商品分类（支持树形结构）主键
     * @return 结果
     */
    public int deleteCategoryByCategoryId(Long categoryId);

    List<Category> getAllCategory();
}
