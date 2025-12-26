package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 固件信息实体类
 * 
 * @author ruoyi
 */
public class IotFirmware extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 固件ID */
    private Long firmwareId;

    /** 固件版本 */
    private String firmwareVersion;

    /** 固件名称 */
    private String firmwareName;

    /** 固件描述 */
    private String description;

    /** 固件类型（对应设备类型） */
    private String deviceType;

    /** 固件文件路径 */
    private String firmwarePath;

    /** 固件文件大小 */
    private Long fileSize;

    /** 固件文件MD5 */
    private String md5;

    /** 固件状态（0-未发布，1-已发布） */
    private String status;

    /** 发布时间 */
    private String publishTime;

    /** 获取固件ID */
    public Long getFirmwareId()
    {
        return firmwareId;
    }

    /** 设置固件ID */
    public void setFirmwareId(Long firmwareId)
    {
        this.firmwareId = firmwareId;
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

    /** 获取固件名称 */
    public String getFirmwareName()
    {
        return firmwareName;
    }

    /** 设置固件名称 */
    public void setFirmwareName(String firmwareName)
    {
        this.firmwareName = firmwareName;
    }

    /** 获取固件描述 */
    public String getDescription()
    {
        return description;
    }

    /** 设置固件描述 */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /** 获取固件类型 */
    public String getDeviceType()
    {
        return deviceType;
    }

    /** 设置固件类型 */
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    /** 获取固件文件路径 */
    public String getFirmwarePath()
    {
        return firmwarePath;
    }

    /** 设置固件文件路径 */
    public void setFirmwarePath(String firmwarePath)
    {
        this.firmwarePath = firmwarePath;
    }

    /** 获取固件文件大小 */
    public Long getFileSize()
    {
        return fileSize;
    }

    /** 设置固件文件大小 */
    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    /** 获取固件文件MD5 */
    public String getMd5()
    {
        return md5;
    }

    /** 设置固件文件MD5 */
    public void setMd5(String md5)
    {
        this.md5 = md5;
    }

    /** 获取固件状态 */
    public String getStatus()
    {
        return status;
    }

    /** 设置固件状态 */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /** 获取发布时间 */
    public String getPublishTime()
    {
        return publishTime;
    }

    /** 设置发布时间 */
    public void setPublishTime(String publishTime)
    {
        this.publishTime = publishTime;
    }
}