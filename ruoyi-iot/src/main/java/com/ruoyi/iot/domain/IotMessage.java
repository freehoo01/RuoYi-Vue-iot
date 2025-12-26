package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息推送记录实体类
 * 
 * @author ruoyi
 */
public class IotMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息ID */
    private Long messageId;

    /** 推送模式：0-全局推送，1-指定设备推送 */
    private String pushMode;

    /** 目标设备标识（多个设备用逗号分隔） */
    private String targetDevices;

    /** 目标分组ID */
    private Long targetGroupId;

    /** 消息内容 */
    private String messageContent;

    /** 推送状态：0-未推送，1-推送中，2-推送成功，3-推送失败 */
    private String status;

    /** 推送结果描述 */
    private String resultDesc;

    /** 推送时间 */
    private String pushTime;

    /** 获取消息ID */
    public Long getMessageId()
    {
        return messageId;
    }

    /** 设置消息ID */
    public void setMessageId(Long messageId)
    {
        this.messageId = messageId;
    }

    /** 获取推送模式 */
    public String getPushMode()
    {
        return pushMode;
    }

    /** 设置推送模式 */
    public void setPushMode(String pushMode)
    {
        this.pushMode = pushMode;
    }

    /** 获取目标设备标识 */
    public String getTargetDevices()
    {
        return targetDevices;
    }

    /** 设置目标设备标识 */
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

    /** 获取消息内容 */
    public String getMessageContent()
    {
        return messageContent;
    }

    /** 设置消息内容 */
    public void setMessageContent(String messageContent)
    {
        this.messageContent = messageContent;
    }

    /** 获取推送状态 */
    public String getStatus()
    {
        return status;
    }

    /** 设置推送状态 */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /** 获取推送结果描述 */
    public String getResultDesc()
    {
        return resultDesc;
    }

    /** 设置推送结果描述 */
    public void setResultDesc(String resultDesc)
    {
        this.resultDesc = resultDesc;
    }

    /** 获取推送时间 */
    public String getPushTime()
    {
        return pushTime;
    }

    /** 设置推送时间 */
    public void setPushTime(String pushTime)
    {
        this.pushTime = pushTime;
    }
}