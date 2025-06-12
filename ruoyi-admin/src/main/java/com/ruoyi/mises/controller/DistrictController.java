package com.ruoyi.mises.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mises.domain.District;
import com.ruoyi.mises.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * districtController
 * 
 * @author AvA
 * @date 2025-03-16
 */
@RestController
@RequestMapping("/mises/district")
public class DistrictController extends BaseController
{
    @Autowired
    private IDistrictService districtService;

    @GetMapping("/getAllDistrict")
     public AjaxResult getAllDistrict(){
        return success(districtService.getAllDistrict());
     }

    /**
     * 查询district列表
     */
    @PreAuthorize("@ss.hasPermi('mises:district:list')")
    @GetMapping("/list")
    public TableDataInfo list(District district)
    {
        startPage();
        List<District> list = districtService.selectDistrictList(district);
        return getDataTable(list);
    }

    /**
     * 导出district列表
     */
    @PreAuthorize("@ss.hasPermi('mises:district:export')")
    @Log(title = "district", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, District district)
    {
        List<District> list = districtService.selectDistrictList(district);
        ExcelUtil<District> util = new ExcelUtil<District>(District.class);
        util.exportExcel(response, list, "district数据");
    }

    /**
     * 获取district详细信息
     */
    @PreAuthorize("@ss.hasPermi('mises:district:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(districtService.selectDistrictById(id));
    }

    /**
     * 新增district
     */
    @PreAuthorize("@ss.hasPermi('mises:district:add')")
    @Log(title = "district", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody District district)
    {
        return toAjax(districtService.insertDistrict(district));
    }

    /**
     * 修改district
     */
    @PreAuthorize("@ss.hasPermi('mises:district:edit')")
    @Log(title = "district", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody District district)
    {
        return toAjax(districtService.updateDistrict(district));
    }

    /**
     * 删除district
     */
    @PreAuthorize("@ss.hasPermi('mises:district:remove')")
    @Log(title = "district", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(districtService.deleteDistrictByIds(ids));
    }
}
