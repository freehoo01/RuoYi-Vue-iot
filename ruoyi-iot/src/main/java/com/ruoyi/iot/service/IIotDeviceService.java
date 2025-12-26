package com.ruoyi.iot.service;

import java.util.List;
import com.ruoyi.iot.domain.IotDevice;

/**
 * 设备Service接口
 * 
 * @author ruoyi
 */
public interface IIotDeviceService
{
    /**
     * 查询设备
     * 
     * @param deviceId 设备ID
     * @return 设备
     */
    public IotDevice selectIotDeviceById(Long deviceId);

    /**
     * 根据设备唯一标识查询设备
     * 
     * @param deviceCode 设备唯一标识
     * @return 设备
     */
    public IotDevice selectIotDeviceByCode(String deviceCode);

    /**
     * 查询设备列表
     * 
     * @param iotDevice 设备
     * @return 设备集合
     */
    public List<IotDevice> selectIotDeviceList(IotDevice iotDevice);

    /**
     * 新增设备
     * 
     * @param iotDevice 设备
     * @return 结果
     */
    public int insertIotDevice(IotDevice iotDevice);

    /**
     * 修改设备
     * 
     * @param iotDevice 设备
     * @return 结果
     */
    public int updateIotDevice(IotDevice iotDevice);

    /**
     * 批量删除设备
     * 
     * @param deviceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotDeviceByIds(Long[] deviceIds);

    /**
     * 删除设备信息
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    public int deleteIotDeviceById(Long deviceId);

    /**
     * 更新设备状态
     * 
     * @param deviceCode 设备唯一标识
     * @param status 状态
     * @return 结果
     */
    public int updateDeviceStatus(String deviceCode, String status);

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
    public int autoRegisterDevice(String deviceCode, String deviceName, String deviceType, String firmwareVersion, String deviceIp);
}