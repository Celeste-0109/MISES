package com.ruoyi.mises.service.impl;

import com.ruoyi.mises.domain.Category;
import com.ruoyi.mises.mapper.CategoryMapper;
import com.ruoyi.mises.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类（支持树形结构）Service业务层处理
 * 
 * @author AvA
 * @date 2025-03-09
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询商品分类（支持树形结构）
     * 
     * @param categoryId 商品分类（支持树形结构）主键
     * @return 商品分类（支持树形结构）
     */
    @Override
    public Category selectCategoryByCategoryId(Long categoryId)
    {
        return categoryMapper.selectCategoryByCategoryId(categoryId);
    }

    /**
     * 查询商品分类（支持树形结构）列表
     * 
     * @param category 商品分类（支持树形结构）
     * @return 商品分类（支持树形结构）
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增商品分类（支持树形结构）
     * 
     * @param category 商品分类（支持树形结构）
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品分类（支持树形结构）
     * 
     * @param category 商品分类（支持树形结构）
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品分类（支持树形结构）
     * 
     * @param categoryIds 需要删除的商品分类（支持树形结构）主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCategoryIds(Long[] categoryIds)
    {
        return categoryMapper.deleteCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除商品分类（支持树形结构）信息
     * 
     * @param categoryId 商品分类（支持树形结构）主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCategoryId(Long categoryId)
    {
        return categoryMapper.deleteCategoryByCategoryId(categoryId);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }
}
