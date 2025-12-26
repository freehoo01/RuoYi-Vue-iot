package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotDeviceGroupMapper;
import com.ruoyi.iot.domain.IotDeviceGroup;
import com.ruoyi.iot.service.IIotDeviceGroupService;

/**
 * 设备分组Service实现类
 * 
 * @author ruoyi
 */
@Service
public class IotDeviceGroupServiceImpl implements IIotDeviceGroupService
{
    @Autowired
    private IotDeviceGroupMapper iotDeviceGroupMapper;

    /**
     * 查询设备分组
     * 
     * @param groupId 设备分组ID
     * @return 设备分组
     */
    @Override
    public IotDeviceGroup selectIotDeviceGroupById(Long groupId)
    {
        return iotDeviceGroupMapper.selectIotDeviceGroupById(groupId);
    }

    /**
     * 查询设备分组列表
     * 
     * @param iotDeviceGroup 设备分组
     * @return 设备分组集合
     */
    @Override
    public List<IotDeviceGroup> selectIotDeviceGroupList(IotDeviceGroup iotDeviceGroup)
    {
        return iotDeviceGroupMapper.selectIotDeviceGroupList(iotDeviceGroup);
    }

    /**
     * 新增设备分组
     * 
     * @param iotDeviceGroup 设备分组
     * @return 结果
     */
    @Override
    public int insertIotDeviceGroup(IotDeviceGroup iotDeviceGroup)
    {
        return iotDeviceGroupMapper.insertIotDeviceGroup(iotDeviceGroup);
    }

    /**
     * 修改设备分组
     * 
     * @param iotDeviceGroup 设备分组
     * @return 结果
     */
    @Override
    public int updateIotDeviceGroup(IotDeviceGroup iotDeviceGroup)
    {
        return iotDeviceGroupMapper.updateIotDeviceGroup(iotDeviceGroup);
    }

    /**
     * 批量删除设备分组
     * 
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotDeviceGroupByIds(Long[] groupIds)
    {
        return iotDeviceGroupMapper.deleteIotDeviceGroupByIds(groupIds);
    }

    /**
     * 删除设备分组信息
     * 
     * @param groupId 设备分组ID
     * @return 结果
     */
    @Override
    public int deleteIotDeviceGroupById(Long groupId)
    {
        return iotDeviceGroupMapper.deleteIotDeviceGroupById(groupId);
    }
}