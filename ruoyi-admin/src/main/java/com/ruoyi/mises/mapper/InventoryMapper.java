package com.ruoyi.mises.mapper;

import com.ruoyi.mises.domain.Inventory;
import com.ruoyi.mises.domain.InventoryListVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 实时库存Mapper接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface InventoryMapper 
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
     * 删除实时库存
     * 
     * @param productId 实时库存主键
     * @return 结果
     */
    public int deleteInventoryByProductId(Long productId);

    /**
     * 批量删除实时库存
     * 
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInventoryByProductIds(Long[] productIds);

    @Insert("insert into inventory (product_id, quantity, last_updated) value (#{productId},#{quantity},NOW())")
    void addInventory(@Param("productId") Long productId, @Param("quantity") Long quantity);

    @Select("select p.product_name as productName, quantity from inventory left join product p on inventory.product_id = p.product_id")
    List<InventoryListVO> getInventoryByCategory();
}
