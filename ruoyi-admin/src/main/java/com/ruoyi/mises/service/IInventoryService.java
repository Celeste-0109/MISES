package com.ruoyi.mises.service;

import com.ruoyi.mises.domain.Inventory;
import com.ruoyi.mises.domain.InventoryListVO;

import java.util.List;

/**
 * 实时库存Service接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface IInventoryService 
{
    /**
     * 查询实时库存
     * 
     * @param productId 实时库存主键
     * @return 实时库存
     */
    public Inventory selectInventoryByProductId(Long productId);

    /**
     * 查询实时库存列表
     * 
     * @param inventory 实时库存
     * @return 实时库存集合
     */
    public List<Inventory> selectInventoryList(Inventory inventory);

    /**
     * 新增实时库存
     * 
     * @param inventory 实时库存
     * @return 结果
     */
    public int insertInventory(Inventory inventory);

    /**
     * 修改实时库存
     * 
     * @param inventory 实时库存
     * @return 结果
     */
    public int updateInventory(Inventory inventory);

    /**
     * 批量删除实时库存
     * 
     * @param productIds 需要删除的实时库存主键集合
     * @return 结果
     */
    public int deleteInventoryByProductIds(Long[] productIds);

    /**
     * 删除实时库存信息
     * 
     * @param productId 实时库存主键
     * @return 结果
     */
    public int deleteInventoryByProductId(Long productId);

    List<InventoryListVO> getInventoryByCategory();
}
