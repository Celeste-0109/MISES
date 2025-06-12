package com.ruoyi.mises.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mises.domain.PurchaseOrderDetail;

import java.util.List;

/**
 * 采购订单明细Service接口
 * 
 * @author ruoyi
 * @date 2025-03-09
 */
public interface IPurchaseOrderDetailService 
{
    /**
     * 查询采购订单明细
     * 
     * @param detailId 采购订单明细主键
     * @return 采购订单明细
     */
    public PurchaseOrderDetail selectPurchaseOrderDetailByDetailId(Long detailId);

    /**
     * 查询采购订单明细列表
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 采购订单明细集合
     */
    public List<PurchaseOrderDetail> selectPurchaseOrderDetailList(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 新增采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    public AjaxResult insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 修改采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    public AjaxResult updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    /**
     * 批量删除采购订单明细
     * 
     * @param detailIds 需要删除的采购订单明细主键集合
     * @return 结果
     */
    public int deletePurchaseOrderDetailByDetailIds(Long[] detailIds);

    /**
     * 删除采购订单明细信息
     * 
     * @param detailId 采购订单明细主键
     * @return 结果
     */
    public AjaxResult deletePurchaseOrderDetailByDetailId(Long detailId);
}
