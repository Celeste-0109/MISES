package com.ruoyi.mises.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mises.mapper.CustomerMapper;
import com.ruoyi.mises.domain.Customer;
import com.ruoyi.mises.service.ICustomerService;

/**
 * 客户信息Service业务层处理
 * 
 * @author AvA
 * @date 2025-03-09
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询客户信息
     * 
     * @param customerId 客户信息主键
     * @return 客户信息
     */
    @Override
    public Customer selectCustomerByCustomerId(Long customerId)
    {
        return customerMapper.selectCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户信息列表
     * 
     * @param customer 客户信息
     * @return 客户信息
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改客户信息
     * 
     * @param customer 客户信息
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户信息
     * 
     * @param customerIds 需要删除的客户信息主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByCustomerIds(Long[] customerIds)
    {
        return customerMapper.deleteCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户信息信息
     * 
     * @param customerId 客户信息主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByCustomerId(Long customerId)
    {
        return customerMapper.deleteCustomerByCustomerId(customerId);
    }
}
