package com.ruoyi.mises.service.impl;

import com.ruoyi.mises.domain.Inventory;
import com.ruoyi.mises.domain.InventoryListVO;
import com.ruoyi.mises.mapper.InventoryMapper;
import com.ruoyi.mises.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实时库存Service业务层处理
 * 
 * @author AvA
 * @date 2025-03-09
 */
@Service
public class InventoryServiceImpl implements IInventoryService 
{
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 查询实时库存
     * 
     * @param productId 实时库存主键
     * @return 实时库存
     */
    @Override
    public Inventory selectInventoryByProductId(Long productId)
    {
        return inventoryMapper.selectInventoryByProductId(productId);
    }

    /**
     * 查询实时库存列表
     * 
     * @param inventory 实时库存
     * @return 实时库存
     */
    @Override
    public List<Inventory> selectInventoryList(Inventory inventory)
    {
        return inventoryMapper.selectInventoryList(inventory);
    }

    /**
     * 新增实时库存
     * 
     * @param inventory 实时库存
     * @return 结果
     */
    @Override
    public int insertInventory(Inventory inventory)
    {
        return inventoryMapper.insertInventory(inventory);
    }

    /**
     * 修改实时库存
     * 
     * @param inventory 实时库存
     * @return 结果
     */
    @Override
    public int updateInventory(Inventory inventory)
    {
        return inventoryMapper.updateInventory(inventory);
    }

    /**
     * 批量删除实时库存
     * 
     * @param productIds 需要删除的实时库存主键
     * @return 结果
     */
    @Override
    public int deleteInventoryByProductIds(Long[] productIds)
    {
        return inventoryMapper.deleteInventoryByProductIds(productIds);
    }

    /**
     * 删除实时库存信息
     * 
     * @param productId 实时库存主键
     * @return 结果
     */
    @Override
    public int deleteInventoryByProductId(Long productId)
    {
        return inventoryMapper.deleteInventoryByProductId(productId);
    }

    @Override
    public List<InventoryListVO> getInventoryByCategory() {
        return inventoryMapper.getInventoryByCategory();
    }
}
