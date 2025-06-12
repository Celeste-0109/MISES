package com.ruoyi.mises.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mises.domain.Inventory;
import com.ruoyi.mises.domain.PurchaseOrderDetail;
import com.ruoyi.mises.mapper.InventoryMapper;
import com.ruoyi.mises.mapper.PurchaseOrderDetailMapper;
import com.ruoyi.mises.service.IProductService;
import com.ruoyi.mises.service.IPurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 采购订单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-09
 */
@Service
public class PurchaseOrderDetailServiceImpl implements IPurchaseOrderDetailService 
{
    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private IProductService productService;

    /**
     * 查询采购订单明细
     * 
     * @param detailId 采购订单明细主键
     * @return 采购订单明细
     */
    @Override
    public PurchaseOrderDetail selectPurchaseOrderDetailByDetailId(Long detailId)
    {
        return purchaseOrderDetailMapper.selectPurchaseOrderDetailByDetailId(detailId);
    }

    /**
     * 查询采购订单明细列表
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 采购订单明细
     */
    @Override
    public List<PurchaseOrderDetail> selectPurchaseOrderDetailList(PurchaseOrderDetail purchaseOrderDetail)
    {
        return purchaseOrderDetailMapper.selectPurchaseOrderDetailList(purchaseOrderDetail);
    }

    /**
     * 新增采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail)
    {
        Long productId = purchaseOrderDetail.getProductId();
        Long quantity = purchaseOrderDetail.getQuantity();
        Integer result = 0;

        try {
            if(productService.selectProductByProductId(productId) == null){
                return AjaxResult.error("当前商品不存在,请重试!");
            }

            Inventory inventory = inventoryMapper.selectInventoryByProductId(productId);
            if(inventory == null){
                inventoryMapper.addInventory(productId, quantity);
            } else {
                inventory.setQuantity(inventory.getQuantity() + purchaseOrderDetail.getQuantity());
                inventory.setLastUpdated(LocalDate.now());
                inventoryMapper.updateInventory(inventory);
            }

            result = purchaseOrderDetailMapper.insertPurchaseOrderDetail(purchaseOrderDetail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.success(result);
    }

    /**
     * 修改采购订单明细
     * 
     * @param purchaseOrderDetail 采购订单明细
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail)
    {
        Long productId = purchaseOrderDetail.getProductId();
        Long quantity = purchaseOrderDetail.getQuantity();

        int result = 0;
        try {
            if(productService.selectProductByProductId(productId) == null){
                return AjaxResult.error("当前商品不存在,请重试!");
            }

            //todo 有点小问题
            Inventory inventory = inventoryMapper.selectInventoryByProductId(productId);
            if(inventory == null){
                inventoryMapper.addInventory(productId, quantity);
            } else {
                inventory.setQuantity(inventory.getQuantity() + purchaseOrderDetail.getQuantity());
                inventory.setLastUpdated(LocalDate.now());
                inventoryMapper.updateInventory(inventory);
            }

            result = purchaseOrderDetailMapper.updatePurchaseOrderDetail(purchaseOrderDetail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.success(result);
    }

    /**
     * 批量删除采购订单明细
     * 
     * @param detailIds 需要删除的采购订单明细主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderDetailByDetailIds(Long[] detailIds)
    {
        return purchaseOrderDetailMapper.deletePurchaseOrderDetailByDetailIds(detailIds);
    }

    /**
     * 删除采购订单明细信息
     * 
     * @param detailId 采购订单明细主键
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult deletePurchaseOrderDetailByDetailId(Long detailId)
    {
        PurchaseOrderDetail purchaseOrderDetail = purchaseOrderDetailMapper.selectPurchaseOrderDetailByDetailId(detailId);
        Long productId = purchaseOrderDetail.getProductId();
        Long quantity = purchaseOrderDetail.getQuantity();

        int result = 0;
        try {
            if(productService.selectProductByProductId(productId) == null){
                return AjaxResult.error("当前商品不存在,请重试!");
            }

            Inventory inventory = inventoryMapper.selectInventoryByProductId(productId);
            if(inventory == null){
                inventoryMapper.addInventory(productId, quantity);
            } else {
                if(inventory.getQuantity() - purchaseOrderDetail.getQuantity() < 0){
                    return AjaxResult.error("当前商品库存不足,无法删除此采购单");
                }
                inventory.setQuantity(inventory.getQuantity() - purchaseOrderDetail.getQuantity());
                inventory.setLastUpdated(LocalDate.now());
                inventoryMapper.updateInventory(inventory);
            }

            result = purchaseOrderDetailMapper.deletePurchaseOrderDetailByDetailId(detailId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.success(result);
    }
}
