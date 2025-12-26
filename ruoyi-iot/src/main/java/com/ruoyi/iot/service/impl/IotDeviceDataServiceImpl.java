package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotDeviceDataMapper;
import com.ruoyi.iot.domain.IotDeviceData;
import com.ruoyi.iot.service.IIotDeviceDataService;

/**
 * 设备实时数据Service实现类
 * 
 * @author ruoyi
 */
@Service
public class IotDeviceDataServiceImpl implements IIotDeviceDataService
{
    @Autowired
    private IotDeviceDataMapper iotDeviceDataMapper;

    /**
     * 查询设备实时数据
     * 
     * @param dataId 设备实时数据ID
     * @return 设备实时数据
     */
    @Override
    public IotDeviceData selectIotDeviceDataById(Long dataId)
    {
        return iotDeviceDataMapper.selectIotDeviceDataById(dataId);
    }

    /**
     * 查询设备实时数据列表
     * 
     * @param iotDeviceData 设备实时数据
     * @return 设备实时数据集合
     */
    @Override
    public List<IotDeviceData> selectIotDeviceDataList(IotDeviceData iotDeviceData)
    {
        return iotDeviceDataMapper.selectIotDeviceDataList(iotDeviceData);
    }

    /**
     * 新增设备实时数据
     * 
     * @param iotDeviceData 设备实时数据
     * @return 结果
     */
    @Override
    public int insertIotDeviceData(IotDeviceData iotDeviceData)
    {
        return iotDeviceDataMapper.insertIotDeviceData(iotDeviceData);
    }

    /**
     * 修改设备实时数据
     * 
     * @param iotDeviceData 设备实时数据
     * @return 结果
     */
    @Override
    public int updateIotDeviceData(IotDeviceData iotDeviceData)
    {
        return iotDeviceDataMapper.updateIotDeviceData(iotDeviceData);
    }

    /**
     * 批量删除设备实时数据
     * 
     * @param dataIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotDeviceDataByIds(Long[] dataIds)
    {
        return iotDeviceDataMapper.deleteIotDeviceDataByIds(dataIds);
    }

    /**
     * 删除设备实时数据信息
     * 
     * @param dataId 设备实时数据ID
     * @return 结果
     */
    @Override
    public int deleteIotDeviceDataById(Long dataId)
    {
        return iotDeviceDataMapper.deleteIotDeviceDataById(dataId);
    }

    /**
     * 查询设备最新数据
     * 
     * @param deviceCode 设备唯一标识
     * @return 设备最新数据
     */
    @Override
    public List<IotDeviceData> getLatestDeviceData(String deviceCode)
    {
        return iotDeviceDataMapper.selectLatestDeviceData(deviceCode);
    }

    /**
     * 查询设备历史数据
     * 
     * @param deviceCode 设备唯一标识
     * @param dataType 数据类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 设备历史数据集合
     */
    @Override
    public List<IotDeviceData> getDeviceHistoryData(String deviceCode, String dataType, String startTime, String endTime)
    {
        return iotDeviceDataMapper.selectDeviceHistoryData(deviceCode, dataType, startTime, endTime);
    }
}