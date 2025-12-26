package com.ruoyi.iot.mapper;

import java.util.List;
import com.ruoyi.iot.domain.IotDeviceData;

/**
 * 设备实时数据Mapper接口
 * 
 * @author ruoyi
 */
public interface IotDeviceDataMapper
{
    /**
     * 查询设备实时数据
     * 
     * @param dataId 设备实时数据ID
     * @return 设备实时数据
     */
    public IotDeviceData selectIotDeviceDataById(Long dataId);

    /**
     * 查询设备实时数据列表
     * 
     * @param iotDeviceData 设备实时数据
     * @return 设备实时数据集合
     */
    public List<IotDeviceData> selectIotDeviceDataList(IotDeviceData iotDeviceData);

    /**
     * 新增设备实时数据
     * 
     * @param iotDeviceData 设备实时数据
     * @return 结果
     */
    public int insertIotDeviceData(IotDeviceData iotDeviceData);

    /**
     * 修改设备实时数据
     * 
     * @param iotDeviceData 设备实时数据
     * @return 结果
     */
    public int updateIotDeviceData(IotDeviceData iotDeviceData);

    /**
     * 删除设备实时数据
     * 
     * @param dataId 设备实时数据ID
     * @return 结果
     */
    public int deleteIotDeviceDataById(Long dataId);

    /**
     * 批量删除设备实时数据
     * 
     * @param dataIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotDeviceDataByIds(Long[] dataIds);

    /**
     * 查询设备最新数据
     * 
     * @param deviceCode 设备唯一标识
     * @return 设备最新数据
     */
    public List<IotDeviceData> selectLatestDeviceData(String deviceCode);

    /**
     * 查询设备历史数据
     * 
     * @param deviceCode 设备唯一标识
     * @param dataType 数据类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 设备历史数据集合
     */
    public List<IotDeviceData> selectDeviceHistoryData(String deviceCode, String dataType, String startTime, String endTime);
}