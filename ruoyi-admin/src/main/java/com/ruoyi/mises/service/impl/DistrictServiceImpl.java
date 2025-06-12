package com.ruoyi.mises.service.impl;

import com.ruoyi.mises.domain.District;
import com.ruoyi.mises.mapper.DistrictMapper;
import com.ruoyi.mises.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * districtService业务层处理
 * 
 * @author AvA
 * @date 2025-03-16
 */
@Service
public class DistrictServiceImpl implements IDistrictService 
{
    @Autowired
    private DistrictMapper districtMapper;

    /**
     * 查询district
     * 
     * @param id district主键
     * @return district
     */
    @Override
    public District selectDistrictById(Long id)
    {
        return districtMapper.selectDistrictById(id);
    }

    /**
     * 查询district列表
     * 
     * @param district district
     * @return district
     */
    @Override
    public List<District> selectDistrictList(District district)
    {
        return districtMapper.selectDistrictList(district);
    }

    /**
     * 新增district
     * 
     * @param district district
     * @return 结果
     */
    @Override
    public int insertDistrict(District district)
    {
        return districtMapper.insertDistrict(district);
    }

    /**
     * 修改district
     * 
     * @param district district
     * @return 结果
     */
    @Override
    public int updateDistrict(District district)
    {
        return districtMapper.updateDistrict(district);
    }

    /**
     * 批量删除district
     * 
     * @param ids 需要删除的district主键
     * @return 结果
     */
    @Override
    public int deleteDistrictByIds(Long[] ids)
    {
        return districtMapper.deleteDistrictByIds(ids);
    }

    /**
     * 删除district信息
     * 
     * @param id district主键
     * @return 结果
     */
    @Override
    public int deleteDistrictById(Long id)
    {
        return districtMapper.deleteDistrictById(id);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.getAllDistrict();
    }
}
