package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备实时数据实体类
 * 
 * @author ruoyi
 */
public class IotDeviceData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据ID */
    private Long dataId;

    /** 设备唯一标识 */
    private String deviceCode;

    /** 设备名称 */
    private String deviceName;

    /** 数据类型（如温度、湿度、压力等） */
    private String dataType;

    /** 数据值 */
    private String dataValue;

    /** 数据单位 */
    private String unit;

    /** 数据状态（0-正常，1-异常） */
    private String status;

    /** 数据采集时间 */
    private String collectTime;

    /** 获取数据ID */
    public Long getDataId()
    {
        return dataId;
    }

    /** 设置数据ID */
    public void setDataId(Long dataId)
    {
        this.dataId = dataId;
    }

    /** 获取设备唯一标识 */
    public String getDeviceCode()
    {
        return deviceCode;
    }

    /** 设置设备唯一标识 */
    public void setDeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }

    /** 获取设备名称 */
    public String getDeviceName()
    {
        return deviceName;
    }

    /** 设置设备名称 */
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    /** 获取数据类型 */
    public String getDataType()
    {
        return dataType;
    }

    /** 设置数据类型 */
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    /** 获取数据值 */
    public String getDataValue()
    {
        return dataValue;
    }

    /** 设置数据值 */
    public void setDataValue(String dataValue)
    {
        this.dataValue = dataValue;
    }

    /** 获取数据单位 */
    public String getUnit()
    {
        return unit;
    }

    /** 设置数据单位 */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    /** 获取数据状态 */
    public String getStatus()
    {
        return status;
    }

    /** 设置数据状态 */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /** 获取数据采集时间 */
    public String getCollectTime()
    {
        return collectTime;
    }

    /** 设置数据采集时间 */
    public void setCollectTime(String collectTime)
    {
        this.collectTime = collectTime;
    }
}