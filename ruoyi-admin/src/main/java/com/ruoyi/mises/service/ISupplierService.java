package com.ruoyi.mises.service;

import java.util.List;
import com.ruoyi.mises.domain.Supplier;

/**
 * 供应商信息Service接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface ISupplierService 
{
    /**
     * 查询供应商信息
     * 
     * @param supplierId 供应商信息主键
     * @return 供应商信息
     */
    public Supplier selectSupplierBySupplierId(Long supplierId);

    /**
     * 查询供应商信息列表
     * 
     * @param supplier 供应商信息
     * @return 供应商信息集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商信息
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商信息
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 批量删除供应商信息
     * 
     * @param supplierIds 需要删除的供应商信息主键集合
     * @return 结果
     */
    public int deleteSupplierBySupplierIds(Long[] supplierIds);

    /**
     * 删除供应商信息信息
     * 
     * @param supplierId 供应商信息主键
     * @return 结果
     */
    public int deleteSupplierBySupplierId(Long supplierId);
}
