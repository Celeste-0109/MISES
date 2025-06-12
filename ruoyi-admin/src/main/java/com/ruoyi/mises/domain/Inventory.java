package com.ruoyi.mises.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

/**
 * 实时库存对象 inventory
 * 
 * @author AvA
 * @date 2025-03-09
 */
public class Inventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联商品ID */
    @Excel(name = "关联商品ID")
    private Long productId;

    /** 当前库存量 */
    @Excel(name = "当前库存量")
    private Long quantity;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate lastUpdated;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    public void setLastUpdated(LocalDate lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public LocalDate getLastUpdated()
    {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("quantity", getQuantity())
            .append("lastUpdated", getLastUpdated())
            .toString();
    }
}
