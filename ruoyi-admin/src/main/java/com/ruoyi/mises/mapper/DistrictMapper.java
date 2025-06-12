package com.ruoyi.mises.mapper;

import com.ruoyi.mises.domain.District;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * districtMapper接口
 * 
 * @author AvA
 * @date 2025-03-16
 */
public interface DistrictMapper 
{
    /**
     * 查询district
     * 
     * @param id district主键
     * @return district
     */
    public District selectDistrictById(Long id);

    /**
     * 查询district列表
     * 
     * @param district district
     * @return district集合
     */
    public List<District> selectDistrictList(District district);

    /**
     * 新增district
     * 
     * @param district district
     * @return 结果
     */
    public int insertDistrict(District district);

    /**
     * 修改district
     * 
     * @param district district
     * @return 结果
     */
    public int updateDistrict(District district);

    /**
     * 删除district
     * 
     * @param id district主键
     * @return 结果
     */
    public int deleteDistrictById(Long id);

    /**
     * 批量删除district
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDistrictByIds(Long[] ids);

    @Select("select id, district_name as districtName from district")
    List<District> getAllDistrict();
}
