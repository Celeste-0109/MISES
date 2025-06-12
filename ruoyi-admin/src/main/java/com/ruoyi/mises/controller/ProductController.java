package com.ruoyi.mises.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mises.domain.Product;
import com.ruoyi.mises.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品基础信息Controller
 * 
 * @author AvA
 * @date 2025-03-09
 */
@RestController
@RequestMapping("/mises/product")
public class ProductController extends BaseController
{
    @Autowired
    private IProductService productService;

    /**
     * 查询商品基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('mises:product:list')")
    @GetMapping("/list")
    public TableDataInfo list( @RequestParam(required = false) Long productId,
                               @RequestParam(required = false) String productName,
                               @RequestParam(required = false) String categoryName,
                               @RequestParam(required = false) String unit,
                               @RequestParam(required = false) String specification,
                               @RequestParam(required = false) String description)
    {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.SetCategoryName(categoryName);
        product.setUnit(unit);
        product.setSpecification(specification);
        product.setDescription(description);
        startPage();
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    /**
     * 导出商品基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('mises:product:export')")
    @Log(title = "商品基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Product product)
    {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        util.exportExcel(response, list, "商品基础信息数据");
    }

    /**
     * 获取商品基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mises:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return success(productService.selectProductByProductId(productId));
    }

    /**
     * 新增商品基础信息
     */
    @PreAuthorize("@ss.hasPermi('mises:product:add')")
    @Log(title = "商品基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Product product)
    {
        return toAjax(productService.insertProduct(product));
    }

    /**
     * 修改商品基础信息
     */
    @PreAuthorize("@ss.hasPermi('mises:product:edit')")
    @Log(title = "商品基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Product product)
    {
        return toAjax(productService.updateProduct(product));
    }

    /**
     * 删除商品基础信息
     */
    @PreAuthorize("@ss.hasPermi('mises:product:remove')")
    @Log(title = "商品基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(productService.deleteProductByProductIds(productIds));
    }
}
