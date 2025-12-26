package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotDeviceMapper;
import com.ruoyi.iot.domain.IotDevice;
import com.ruoyi.iot.service.IIotDeviceService;

/**
 * 设备Service实现类
 * 
 * @author ruoyi
 */
@Service
public class IotDeviceServiceImpl implements IIotDeviceService
{
    @Autowired
    private IotDeviceMapper iotDeviceMapper;

    /**
     * 查询设备
     * 
     * @param deviceId 设备ID
     * @return 设备
     */
    @Override
    public IotDevice selectIotDeviceById(Long deviceId)
    {
        return iotDeviceMapper.selectIotDeviceById(deviceId);
    }

    /**
     * 根据设备唯一标识查询设备
     * 
     * @param deviceCode 设备唯一标识
     * @return 设备
     */
    @Override
    public IotDevice selectIotDeviceByCode(String deviceCode)
    {
        return iotDeviceMapper.selectIotDeviceByCode(deviceCode);
    }

    /**
     * 查询设备列表
     * 
     * @param iotDevice 设备
     * @return 设备集合
     */
    @Override
    public List<IotDevice> selectIotDeviceList(IotDevice iotDevice)
    {
        return iotDeviceMapper.selectIotDeviceList(iotDevice);
    }

    /**
     * 新增设备
     * 
     * @param iotDevice 设备
     * @return 结果
     */
    @Override
    public int insertIotDevice(IotDevice iotDevice)
    {
        return iotDeviceMapper.insertIotDevice(iotDevice);
    }

    /**
     * 修改设备
     * 
     * @param iotDevice 设备
     * @return 结果
     */
    @Override
    public int updateIotDevice(IotDevice iotDevice)
    {
        return iotDeviceMapper.updateIotDevice(iotDevice);
    }

    /**
     * 批量删除设备
     * 
     * @param deviceIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotDeviceByIds(Long[] deviceIds)
    {
        return iotDeviceMapper.deleteIotDeviceByIds(deviceIds);
    }

    /**
     * 删除设备信息
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    @Override
    public int deleteIotDeviceById(Long deviceId)
    {
        return iotDeviceMapper.deleteIotDeviceById(deviceId);
    }

    /**
     * 更新设备状态
     * 
     * @param deviceCode 设备唯一标识
     * @param status 状态
     * @return 结果
     */
    @Override
    public int updateDeviceStatus(String deviceCode, String status)
    {
        return iotDeviceMapper.updateDeviceStatus(deviceCode, status);
    }

    /**
     * 设备自动注册
     * 
     * @param deviceCode 设备唯一标识
     * @param deviceName 设备别名
     * @param deviceType 设备类型
     * @param firmwareVersion 固件版本
     * @param deviceIp 设备IP
     * @return 结果
     */
    @Override
    public int autoRegisterDevice(String deviceCode, String deviceName, String deviceType, String firmwareVersion, String deviceIp)
    {
        // 先查询设备是否已存在
        IotDevice existingDevice = selectIotDeviceByCode(deviceCode);
        if (existingDevice != null)
        {
            // 设备已存在，更新状态和信息
            existingDevice.setStatus("1"); // 在线状态
            existingDevice.setDeviceName(deviceName);
            existingDevice.setDeviceType(deviceType);
            existingDevice.setFirmwareVersion(firmwareVersion);
            existingDevice.setDeviceIp(deviceIp);
            return updateIotDevice(existingDevice);
        }
        else
        {
            // 设备不存在，新增设备
            IotDevice newDevice = new IotDevice();
            newDevice.setDeviceCode(deviceCode);
            newDevice.setDeviceName(deviceName);
            newDevice.setDeviceType(deviceType);
            newDevice.setFirmwareVersion(firmwareVersion);
            newDevice.setDeviceIp(deviceIp);
            newDevice.setStatus("1"); // 在线状态
            return insertIotDevice(newDevice);
        }
    }
}