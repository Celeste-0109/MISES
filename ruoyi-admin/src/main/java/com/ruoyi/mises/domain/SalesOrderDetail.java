package com.ruoyi.mises.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售订单明细对象 sales_order_detail
 * 
 * @author AvA
 * @date 2025-03-09
 */
public class SalesOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细唯一标识 */
    @Excel(name = "明细唯一标识")
    private Long detailId;

    /** 关联销售订单ID */
    @Excel(name = "关联销售订单ID")
    private Long orderId;

    /** 关联商品ID */
    @Excel(name = "关联商品ID")
    private Long productId;

    /** 销售数量 */
    @Excel(name = "销售数量")
    private Long quantity;

    /** 销售单价（历史记录） */
    @Excel(name = "销售单价", readConverterExp = "历=史记录")
    private BigDecimal unitPrice;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

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

    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
            .toString();
    }
}
