package com.ruoyi.mises.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商信息对象 supplier
 * 
 * @author AvA
 * @date 2025-03-09
 */
public class Supplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商唯一标识 */
    @Excel(name = "供应商唯一标识")
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String name;

    /** 所在地区 */
    @Excel(name = "所在地区")
    private String region;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }

    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("name", getName())
            .append("region", getRegion())
            .append("contactPerson", getContactPerson())
            .append("contactPhone", getContactPhone())
            .append("address", getAddress())
            .append("email", getEmail())
            .toString();
    }
}
