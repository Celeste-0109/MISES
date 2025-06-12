package com.ruoyi.mises.mapper;

import java.util.List;
import com.ruoyi.mises.domain.SalesOrderDetail;

/**
 * 销售订单明细Mapper接口
 * 
 * @author AvA
 * @date 2025-03-09
 */
public interface SalesOrderDetailMapper 
{
    /**
     * 查询销售订单明细
     * 
     * @param detailId 销售订单明细主键
     * @return 销售订单明细
     */
    public SalesOrderDetail selectSalesOrderDetailByDetailId(Long detailId);

    /**
     * 查询销售订单明细列表
     * 
     * @param salesOrderDetail 销售订单明细
     * @return 销售订单明细集合
     */
    public List<SalesOrderDetail> selectSalesOrderDetailList(SalesOrderDetail salesOrderDetail);

    /**
     * 新增销售订单明细
     * 
     * @param salesOrderDetail 销售订单明细
     * @return 结果
     */
    public int insertSalesOrderDetail(SalesOrderDetail salesOrderDetail);

    /**
     * 修改销售订单明细
     * 
     * @param salesOrderDetail 销售订单明细
     * @return 结果
     */
    public int updateSalesOrderDetail(SalesOrderDetail salesOrderDetail);

    /**
     * 删除销售订单明细
     * 
     * @param detailId 销售订单明细主键
     * @return 结果
     */
    public int deleteSalesOrderDetailByDetailId(Long detailId);

    /**
     * 批量删除销售订单明细
     * 
     * @param detailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSalesOrderDetailByDetailIds(Long[] detailIds);
}
