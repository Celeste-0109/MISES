package com.ruoyi.mises.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mises.domain.SalesOrderDetail;

import java.util.List;

/**
 * 销售订单明细Service接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface ISalesOrderDetailService 
{
    /**
     * 查询销售订单明细
     * 
     * @param detailId 销售订单明细主键
     * @return 销售订单明细
     */
    public SalesOrderDetail selectSalesOrderDetailByDetailId(Long detailId);

    /**
     * 查询销售订单明细列表
     * 
     * @param salesOrderDetail 销售订单明细
     * @return 销售订单明细集合
     */
    public List<SalesOrderDetail> selectSalesOrderDetailList(SalesOrderDetail salesOrderDetail);

    /**
     * 新增销售订单明细
     * 
     * @param salesOrderDetail 销售订单明细
     * @return 结果
     */
    public AjaxResult insertSalesOrderDetail(SalesOrderDetail salesOrderDetail);

    /**
     * 修改销售订单明细
     * 
     * @param salesOrderDetail 销售订单明细
     * @return 结果
     */
    public AjaxResult updateSalesOrderDetail(SalesOrderDetail salesOrderDetail);

    /**
     * 批量删除销售订单明细
     * 
     * @param detailIds 需要删除的销售订单明细主键集合
     * @return 结果
     */
    public int deleteSalesOrderDetailByDetailIds(Long[] detailIds);

    /**
     * 删除销售订单明细信息
     * 
     * @param detailId 销售订单明细主键
     * @return 结果
     */
    public AjaxResult deleteSalesOrderDetailByDetailId(Long detailId);
}
