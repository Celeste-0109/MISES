package com.ruoyi.mises.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mises.domain.Inventory;
import com.ruoyi.mises.domain.SalesOrderDetail;
import com.ruoyi.mises.mapper.InventoryMapper;
import com.ruoyi.mises.mapper.SalesOrderDetailMapper;
import com.ruoyi.mises.service.IProductService;
import com.ruoyi.mises.service.ISalesOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 销售订单明细Service业务层处理
 *
 * @author AvA
 * @date 2025-03-09
 */
@Service
public class SalesOrderDetailServiceImpl implements ISalesOrderDetailService {
    @Autowired
    private SalesOrderDetailMapper salesOrderDetailMapper;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private IProductService productService;

    /**
     * 查询销售订单明细
     *
     * @param detailId 销售订单明细主键
     * @return 销售订单明细
     */
    @Override
    public SalesOrderDetail selectSalesOrderDetailByDetailId(Long detailId) {
        return salesOrderDetailMapper.selectSalesOrderDetailByDetailId(detailId);
    }

    /**
     * 查询销售订单明细列表
     *
     * @param salesOrderDetail 销售订单明细
     * @return 销售订单明细
     */
    @Override
    public List<SalesOrderDetail> selectSalesOrderDetailList(SalesOrderDetail salesOrderDetail) {
        return salesOrderDetailMapper.selectSalesOrderDetailList(salesOrderDetail);
    }

    /**
     * 新增销售订单明细
     *
     * @param salesOrderDetail 销售订单明细
     * @return 结果
     */
    @Override
    public AjaxResult insertSalesOrderDetail(SalesOrderDetail salesOrderDetail) {

        Long productId = salesOrderDetail.getProductId();
        Long quantity = salesOrderDetail.getQuantity();
        Integer result = 0;

        try {
            if (productService.selectProductByProductId(productId) == null) {
                return AjaxResult.error("当前商品不存在,请重试!");
            }

            Inventory inventory = inventoryMapper.selectInventoryByProductId(productId);
            if (inventory == null) {
                return AjaxResult.error("当前商品库存不足,请重试!");
            } else {
                if(inventory.getQuantity() - salesOrderDetail.getQuantity() < 0){
                    return AjaxResult.error("当前商品库存不足,无法新增此销售单");
                }
                inventory.setQuantity(inventory.getQuantity() - salesOrderDetail.getQuantity());
                inventory.setLastUpdated(LocalDate.now());
                inventoryMapper.updateInventory(inventory);
            }

            result = salesOrderDetailMapper.insertSalesOrderDetail(salesOrderDetail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.success(result);
    }

    /**
     * 修改销售订单明细
     *
     * @param salesOrderDetail 销售订单明细
     * @return 结果
     */
    @Override
    public AjaxResult updateSalesOrderDetail(SalesOrderDetail salesOrderDetail) {


        //todo

        int result = salesOrderDetailMapper.updateSalesOrderDetail(salesOrderDetail);

        return AjaxResult.success(result);
    }

    /**
     * 批量删除销售订单明细
     *
     * @param detailIds 需要删除的销售订单明细主键
     * @return 结果
     */
    @Override
    public int deleteSalesOrderDetailByDetailIds(Long[] detailIds) {
        return salesOrderDetailMapper.deleteSalesOrderDetailByDetailIds(detailIds);
    }

    /**
     * 删除销售订单明细信息
     *
     * @param detailId 销售订单明细主键
     * @return 结果
     */
    @Override
    public AjaxResult deleteSalesOrderDetailByDetailId(Long detailId) {

        SalesOrderDetail salesOrderDetail = salesOrderDetailMapper.selectSalesOrderDetailByDetailId(detailId);

        Long productId = salesOrderDetail.getProductId();
        Long quantity = salesOrderDetail.getQuantity();
        Integer result = 0;

        try {
            if (productService.selectProductByProductId(productId) == null) {
                return AjaxResult.error("当前商品不存在,请重试!");
            }

            Inventory inventory = inventoryMapper.selectInventoryByProductId(productId);
            if (inventory == null) {
                return AjaxResult.error("当前商品库存不足,请重试!");
            } else {
                inventory.setQuantity(inventory.getQuantity() + salesOrderDetail.getQuantity());
                inventory.setLastUpdated(LocalDate.now());
                inventoryMapper.updateInventory(inventory);
            }

            result = salesOrderDetailMapper.deleteSalesOrderDetailByDetailId(detailId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.success(result);
    }
}
