package com.ruoyi.mises.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品分类（支持树形结构）对象 category
 * 
 * @author AvA
 * @date 2025-03-09
 */
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类唯一标识 */
    @Excel(name = "分类唯一标识")
    private Long categoryId;

    /** 分类名称（如服装、食品） */
    @Excel(name = "分类名称", readConverterExp = "如=服装、食品")
    private String categoryName;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryId", getCategoryId())
            .append("categoryName", getCategoryName())
            .toString();
    }
}
