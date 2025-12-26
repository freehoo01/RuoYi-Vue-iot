package com.ruoyi.iot.mapper;

import java.util.List;
import com.ruoyi.iot.domain.IotFirmware;

/**
 * 固件信息Mapper接口
 * 
 * @author ruoyi
 */
public interface IotFirmwareMapper
{
    /**
     * 查询固件信息
     * 
     * @param firmwareId 固件信息ID
     * @return 固件信息
     */
    public IotFirmware selectIotFirmwareById(Long firmwareId);

    /**
     * 查询固件信息列表
     * 
     * @param iotFirmware 固件信息
     * @return 固件信息集合
     */
    public List<IotFirmware> selectIotFirmwareList(IotFirmware iotFirmware);

    /**
     * 新增固件信息
     * 
     * @param iotFirmware 固件信息
     * @return 结果
     */
    public int insertIotFirmware(IotFirmware iotFirmware);

    /**
     * 修改固件信息
     * 
     * @param iotFirmware 固件信息
     * @return 结果
     */
    public int updateIotFirmware(IotFirmware iotFirmware);

    /**
     * 删除固件信息
     * 
     * @param firmwareId 固件信息ID
     * @return 结果
     */
    public int deleteIotFirmwareById(Long firmwareId);

    /**
     * 批量删除固件信息
     * 
     * @param firmwareIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotFirmwareByIds(Long[] firmwareIds);

    /**
     * 根据设备类型查询最新固件
     * 
     * @param deviceType 设备类型
     * @return 固件信息
     */
    public IotFirmware selectLatestFirmwareByDeviceType(String deviceType);
}