package com.ruoyi.mises.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品基础信息对象 product
 * 
 * @author AvA
 * @date 2025-03-09
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品唯一标识 */
    @Excel(name = "商品唯一标识")
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 所属分类ID */
    @Excel(name = "所属分类")
    private String categoryName;

    /** 单位（如件、千克） */
    @Excel(name = "单位", readConverterExp = "如=件、千克")
    private String unit;

    /** 规格（如颜色、尺寸） */
    @Excel(name = "规格", readConverterExp = "如=颜色、尺寸")
    private String specification;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String description;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }

    public void SetCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("categoryId", getCategoryName())
            .append("unit", getUnit())
            .append("specification", getSpecification())
            .append("description", getDescription())
            .toString();
    }
}
