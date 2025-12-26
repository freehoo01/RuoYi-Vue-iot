package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 固件升级任务实体类
 * 
 * @author ruoyi
 */
public class IotFirmwareUpgradeTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 任务名称 */
    private String taskName;

    /** 固件ID */
    private Long firmwareId;

    /** 固件版本 */
    private String firmwareVersion;

    /** 目标设备列表（多个设备用逗号分隔） */
    private String targetDevices;

    /** 目标分组ID */
    private Long targetGroupId;

    /** 升级状态（0-未开始，1-升级中，2-升级完成，3-升级失败） */
    private String status;

    /** 升级进度（百分比） */
    private Integer progress;

    /** 成功升级设备数量 */
    private Integer successCount;

    /** 失败升级设备数量 */
    private Integer failCount;

    /** 总设备数量 */
    private Integer totalCount;

    /** 开始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

    /** 获取任务ID */
    public Long getTaskId()
    {
        return taskId;
    }

    /** 设置任务ID */
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    /** 获取任务名称 */
    public String getTaskName()
    {
        return taskName;
    }

    /** 设置任务名称 */
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

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

    /** 获取目标设备列表 */
    public String getTargetDevices()
    {
        return targetDevices;
    }

    /** 设置目标设备列表 */
    public void setTargetDevices(String targetDevices)
    {
        this.targetDevices = targetDevices;
    }

    /** 获取目标分组ID */
    public Long getTargetGroupId()
    {
        return targetGroupId;
    }

    /** 设置目标分组ID */
    public void setTargetGroupId(Long targetGroupId)
    {
        this.targetGroupId = targetGroupId;
    }

    /** 获取升级状态 */
    public String getStatus()
    {
        return status;
    }

    /** 设置升级状态 */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /** 获取升级进度 */
    public Integer getProgress()
    {
        return progress;
    }

    /** 设置升级进度 */
    public void setProgress(Integer progress)
    {
        this.progress = progress;
    }

    /** 获取成功升级设备数量 */
    public Integer getSuccessCount()
    {
        return successCount;
    }

    /** 设置成功升级设备数量 */
    public void setSuccessCount(Integer successCount)
    {
        this.successCount = successCount;
    }

    /** 获取失败升级设备数量 */
    public Integer getFailCount()
    {
        return failCount;
    }

    /** 设置失败升级设备数量 */
    public void setFailCount(Integer failCount)
    {
        this.failCount = failCount;
    }

    /** 获取总设备数量 */
    public Integer getTotalCount()
    {
        return totalCount;
    }

    /** 设置总设备数量 */
    public void setTotalCount(Integer totalCount)
    {
        this.totalCount = totalCount;
    }

    /** 获取开始时间 */
    public String getStartTime()
    {
        return startTime;
    }

    /** 设置开始时间 */
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    /** 获取结束时间 */
    public String getEndTime()
    {
        return endTime;
    }

    /** 设置结束时间 */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
}