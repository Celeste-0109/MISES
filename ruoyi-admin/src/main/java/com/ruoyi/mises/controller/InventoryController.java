package com.ruoyi.mises.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mises.domain.Inventory;
import com.ruoyi.mises.domain.InventoryListVO;
import com.ruoyi.mises.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 实时库存Controller
 * 
 * @author AvA
 * @date 2025-03-09
 */
@RestController
@RequestMapping("/mises/inventory")
public class InventoryController extends BaseController
{
    @Autowired
    private IInventoryService inventoryService;

    /**
     * 查询实时库存列表
     */
    @PreAuthorize("@ss.hasPermi('mises:inventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(Inventory inventory)
    {
        startPage();
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        return getDataTable(list);
    }

    /**
     * 导出实时库存列表
     */
    @PreAuthorize("@ss.hasPermi('mises:inventory:export')")
    @Log(title = "实时库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Inventory inventory)
    {
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        ExcelUtil<Inventory> util = new ExcelUtil<Inventory>(Inventory.class);
        util.exportExcel(response, list, "实时库存数据");
    }

    /**
     * 获取实时库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('mises:inventory:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return success(inventoryService.selectInventoryByProductId(productId));
    }

    /**
     * 新增实时库存
     */
    @PreAuthorize("@ss.hasPermi('mises:inventory:add')")
    @Log(title = "实时库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Inventory inventory)
    {
        return toAjax(inventoryService.insertInventory(inventory));
    }

    /**
     * 修改实时库存
     */
    @PreAuthorize("@ss.hasPermi('mises:inventory:edit')")
    @Log(title = "实时库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Inventory inventory)
    {
        return toAjax(inventoryService.updateInventory(inventory));
    }

    /**
     * 删除实时库存
     */
    @PreAuthorize("@ss.hasPermi('mises:inventory:remove')")
    @Log(title = "实时库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(inventoryService.deleteInventoryByProductIds(productIds));
    }

    // 按分类统计库存
    @GetMapping("/byCategory")
    public AjaxResult getByCategory() {
        List<InventoryListVO> data = inventoryService.getInventoryByCategory();
        return AjaxResult.success(data);
    }
}
