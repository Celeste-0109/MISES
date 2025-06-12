package com.ruoyi.mises.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mises.domain.SalesOrderDetail;
import com.ruoyi.mises.service.ISalesOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 销售订单明细Controller
 * 
 * @author AvA
 * @date 2025-03-09
 */
@RestController
@RequestMapping("/mises/sales_order_detail")
public class SalesOrderDetailController extends BaseController
{
    @Autowired
    private ISalesOrderDetailService salesOrderDetailService;

    /**
     * 查询销售订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('mises:sales_order_detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrderDetail salesOrderDetail)
    {
        startPage();
        List<SalesOrderDetail> list = salesOrderDetailService.selectSalesOrderDetailList(salesOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出销售订单明细列表
     */
    @PreAuthorize("@ss.hasPermi('mises:sales_order_detail:export')")
    @Log(title = "销售订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SalesOrderDetail salesOrderDetail)
    {
        List<SalesOrderDetail> list = salesOrderDetailService.selectSalesOrderDetailList(salesOrderDetail);
        ExcelUtil<SalesOrderDetail> util = new ExcelUtil<SalesOrderDetail>(SalesOrderDetail.class);
        util.exportExcel(response, list, "销售订单明细数据");
    }

    /**
     * 获取销售订单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('mises:sales_order_detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(salesOrderDetailService.selectSalesOrderDetailByDetailId(detailId));
    }

    /**
     * 新增销售订单明细
     */
    @PreAuthorize("@ss.hasPermi('mises:sales_order_detail:add')")
    @Log(title = "销售订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SalesOrderDetail salesOrderDetail)
    {
        return salesOrderDetailService.insertSalesOrderDetail(salesOrderDetail);
    }

    /**
     * 修改销售订单明细
     */
    @PreAuthorize("@ss.hasPermi('mises:sales_order_detail:edit')")
    @Log(title = "销售订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SalesOrderDetail salesOrderDetail)
    {
        return salesOrderDetailService.updateSalesOrderDetail(salesOrderDetail);
    }

    /**
     * 删除销售订单明细
     */
    @PreAuthorize("@ss.hasPermi('mises:sales_order_detail:remove')")
    @Log(title = "销售订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long detailId)
    {
        return salesOrderDetailService.deleteSalesOrderDetailByDetailId(detailId);
    }
}
