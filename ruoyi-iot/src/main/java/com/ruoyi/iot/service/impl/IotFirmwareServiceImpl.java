package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotFirmwareMapper;
import com.ruoyi.iot.domain.IotFirmware;
import com.ruoyi.iot.service.IIotFirmwareService;

/**
 * 固件信息Service实现类
 * 
 * @author ruoyi
 */
@Service
public class IotFirmwareServiceImpl implements IIotFirmwareService
{
    @Autowired
    private IotFirmwareMapper iotFirmwareMapper;

    /**
     * 查询固件信息
     * 
     * @param firmwareId 固件信息ID
     * @return 固件信息
     */
    @Override
    public IotFirmware selectIotFirmwareById(Long firmwareId)
    {
        return iotFirmwareMapper.selectIotFirmwareById(firmwareId);
    }

    /**
     * 查询固件信息列表
     * 
     * @param iotFirmware 固件信息
     * @return 固件信息集合
     */
    @Override
    public List<IotFirmware> selectIotFirmwareList(IotFirmware iotFirmware)
    {
        return iotFirmwareMapper.selectIotFirmwareList(iotFirmware);
    }

    /**
     * 新增固件信息
     * 
     * @param iotFirmware 固件信息
     * @return 结果
     */
    @Override
    public int insertIotFirmware(IotFirmware iotFirmware)
    {
        return iotFirmwareMapper.insertIotFirmware(iotFirmware);
    }

    /**
     * 修改固件信息
     * 
     * @param iotFirmware 固件信息
     * @return 结果
     */
    @Override
    public int updateIotFirmware(IotFirmware iotFirmware)
    {
        return iotFirmwareMapper.updateIotFirmware(iotFirmware);
    }

    /**
     * 批量删除固件信息
     * 
     * @param firmwareIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotFirmwareByIds(Long[] firmwareIds)
    {
        return iotFirmwareMapper.deleteIotFirmwareByIds(firmwareIds);
    }

    /**
     * 删除固件信息信息
     * 
     * @param firmwareId 固件信息ID
     * @return 结果
     */
    @Override
    public int deleteIotFirmwareById(Long firmwareId)
    {
        return iotFirmwareMapper.deleteIotFirmwareById(firmwareId);
    }

    /**
     * 根据设备类型查询最新固件
     * 
     * @param deviceType 设备类型
     * @return 固件信息
     */
    @Override
    public IotFirmware getLatestFirmwareByDeviceType(String deviceType)
    {
        return iotFirmwareMapper.selectLatestFirmwareByDeviceType(deviceType);
    }
}