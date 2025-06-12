package com.ruoyi.mises.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mises.domain.Category;
import com.ruoyi.mises.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品分类（支持树形结构）Controller
 * 
 * @author AvA
 * @date 2025-03-09
 */
@RestController
@RequestMapping("/mises/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/getAllCategory")
    public AjaxResult getAllCategory(){
        return success(categoryService.getAllCategory());
    }

    /**
     * 查询商品分类（支持树形结构）列表
     */
    @PreAuthorize("@ss.hasPermi('mises:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品分类（支持树形结构）列表
     */
    @PreAuthorize("@ss.hasPermi('mises:category:export')")
    @Log(title = "商品分类（支持树形结构）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品分类（支持树形结构）数据");
    }

    /**
     * 获取商品分类（支持树形结构）详细信息
     */
    @PreAuthorize("@ss.hasPermi('mises:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(categoryService.selectCategoryByCategoryId(categoryId));
    }

    /**
     * 新增商品分类（支持树形结构）
     */
    @PreAuthorize("@ss.hasPermi('mises:category:add')")
    @Log(title = "商品分类（支持树形结构）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品分类（支持树形结构）
     */
    @PreAuthorize("@ss.hasPermi('mises:category:edit')")
    @Log(title = "商品分类（支持树形结构）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品分类（支持树形结构）
     */
    @PreAuthorize("@ss.hasPermi('mises:category:remove')")
    @Log(title = "商品分类（支持树形结构）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(categoryService.deleteCategoryByCategoryIds(categoryIds));
    }
}
