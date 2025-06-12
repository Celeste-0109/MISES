package com.ruoyi.mises.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mises.domain.PurchaseOrderDetail;
import com.ruoyi.mises.service.IPurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 采购订单明细Controller
 * 
 * @author ruoyi
 * @date 2025-03-09
 */
@RestController
@RequestMapping("/mises/purchase_order_detail")
public class PurchaseOrderDetailController extends BaseController
{
    @Autowired
    private IPurchaseOrderDetailService purchaseOrderDetailService;

    /**
     * 查询采购订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('mises:purchase_order_detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseOrderDetail purchaseOrderDetail)
    {
        startPage();
        List<PurchaseOrderDetail> list = purchaseOrderDetailService.selectPurchaseOrderDetailList(purchaseOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出采购订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('mises:purchase_order_detail:export')")
    @Log(title = "采购订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseOrderDetail purchaseOrderDetail)
    {
        List<PurchaseOrderDetail> list = purchaseOrderDetailService.selectPurchaseOrderDetailList(purchaseOrderDetail);
        ExcelUtil<PurchaseOrderDetail> util = new ExcelUtil<PurchaseOrderDetail>(PurchaseOrderDetail.class);
        util.exportExcel(response, list, "采购订单明细数据");
    }

    /**
     * 获取采购订单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('mises:purchase_order_detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(purchaseOrderDetailService.selectPurchaseOrderDetailByDetailId(detailId));
    }

    /**
     * 新增采购订单明细
     */
    @PreAuthorize("@ss.hasPermi('mises:purchase_order_detail:add')")
    @Log(title = "采购订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseOrderDetail purchaseOrderDetail)
    {
        return purchaseOrderDetailService.insertPurchaseOrderDetail(purchaseOrderDetail);
    }

    /**
     * 修改采购订单明细
     */
    @PreAuthorize("@ss.hasPermi('mises:purchase_order_detail:edit')")
    @Log(title = "采购订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseOrderDetail purchaseOrderDetail)
    {
        return purchaseOrderDetailService.updatePurchaseOrderDetail(purchaseOrderDetail);
    }

    /**
     * 删除采购订单明细
     */
    @PreAuthorize("@ss.hasPermi('mises:purchase_order_detail:remove')")
    @Log(title = "采购订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailId}")
    public AjaxResult remove(@PathVariable Long detailId)
    {
        return purchaseOrderDetailService.deletePurchaseOrderDetailByDetailId(detailId);
    }
}
