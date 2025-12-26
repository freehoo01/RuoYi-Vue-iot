package com.ruoyi.iot.service;

import java.util.List;
import com.ruoyi.iot.domain.IotDeviceGroup;

/**
 * 设备分组Service接口
 * 
 * @author ruoyi
 */
public interface IIotDeviceGroupService
{
    /**
     * 查询设备分组
     * 
     * @param groupId 设备分组ID
     * @return 设备分组
     */
    public IotDeviceGroup selectIotDeviceGroupById(Long groupId);

    /**
     * 查询设备分组列表
     * 
     * @param iotDeviceGroup 设备分组
     * @return 设备分组集合
     */
    public List<IotDeviceGroup> selectIotDeviceGroupList(IotDeviceGroup iotDeviceGroup);

    /**
     * 新增设备分组
     * 
     * @param iotDeviceGroup 设备分组
     * @return 结果
     */
    public int insertIotDeviceGroup(IotDeviceGroup iotDeviceGroup);

    /**
     * 修改设备分组
     * 
     * @param iotDeviceGroup 设备分组
     * @return 结果
     */
    public int updateIotDeviceGroup(IotDeviceGroup iotDeviceGroup);

    /**
     * 批量删除设备分组
     * 
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotDeviceGroupByIds(Long[] groupIds);

    /**
     * 删除设备分组信息
     * 
     * @param groupId 设备分组ID
     * @return 结果
     */
    public int deleteIotDeviceGroupById(Long groupId);
}