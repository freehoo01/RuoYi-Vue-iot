package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备实体类
 * 
 * @author ruoyi
 */
public class IotDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private Long deviceId;

    /** 设备唯一标识（MAC地址/设备SN码） */
    private String deviceCode;

    /** 设备别名 */
    private String deviceName;

    /** 设备分组ID */
    private Long groupId;

    /** 设备分组名称 */
    private String groupName;

    /** 连接状态：0-离线，1-在线 */
    private String status;

    /** 最后上线时间 */
    private String lastOnlineTime;

    /** 设备类型 */
    private String deviceType;

    /** 固件版本 */
    private String firmwareVersion;

    /** 设备IP地址 */
    private String deviceIp;

    /** 获取设备ID */
    public Long getDeviceId()
    {
        return deviceId;
    }

    /** 设置设备ID */
    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
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

    /** 获取设备别名 */
    public String getDeviceName()
    {
        return deviceName;
    }

    /** 设置设备别名 */
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    /** 获取设备分组ID */
    public Long getGroupId()
    {
        return groupId;
    }

    /** 设置设备分组ID */
    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
    }

    /** 获取设备分组名称 */
    public String getGroupName()
    {
        return groupName;
    }

    /** 设置设备分组名称 */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /** 获取连接状态 */
    public String getStatus()
    {
        return status;
    }

    /** 设置连接状态 */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /** 获取最后上线时间 */
    public String getLastOnlineTime()
    {
        return lastOnlineTime;
    }

    /** 设置最后上线时间 */
    public void setLastOnlineTime(String lastOnlineTime)
    {
        this.lastOnlineTime = lastOnlineTime;
    }

    /** 获取设备类型 */
    public String getDeviceType()
    {
        return deviceType;
    }

    /** 设置设备类型 */
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    /** 获取固件版本 */
    public String getFirmwareVersion()
    {
        return firmwareVersion;
    }

    /** 设置固件版本 */
    public void setFirmwareVersion(String firmwareVersion)
    {
        this.firmwareVersion = firmwareVersion;
    }

    /** 获取设备IP地址 */
    public String getDeviceIp()
    {
        return deviceIp;
    }

    /** 设置设备IP地址 */
    public void setDeviceIp(String deviceIp)
    {
        this.deviceIp = deviceIp;
    }
}