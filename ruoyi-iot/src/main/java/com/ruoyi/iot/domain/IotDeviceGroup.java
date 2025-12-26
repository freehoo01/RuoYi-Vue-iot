package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备分组实体类
 * 
 * @author ruoyi
 */
public class IotDeviceGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分组ID */
    private Long groupId;

    /** 分组名称 */
    private String groupName;

    /** 分组描述 */
    private String description;

    /** 分组类型（按区域、按设备类型、按业务场景） */
    private String groupType;

    /** 父分组ID */
    private Long parentId;

    /** 获取分组ID */
    public Long getGroupId()
    {
        return groupId;
    }

    /** 设置分组ID */
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    /** 获取分组名称 */
    public String getGroupName()
    {
        return groupName;
    }

    /** 设置分组名称 */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /** 获取分组描述 */
    public String getDescription()
    {
        return description;
    }

    /** 设置分组描述 */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /** 获取分组类型 */
    public String getGroupType()
    {
        return groupType;
    }

    /** 设置分组类型 */
    public void setGroupType(String groupType)
    {
        this.groupType = groupType;
    }

    /** 获取父分组ID */
    public Long getParentId()
    {
        return parentId;
    }

    /** 设置父分组ID */
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }
}