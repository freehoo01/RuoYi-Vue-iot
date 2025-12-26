package com.ruoyi.iot.service;

import java.util.List;
import com.ruoyi.iot.domain.IotMessage;

/**
 * 消息推送记录Service接口
 * 
 * @author ruoyi
 */
public interface IIotMessageService
{
    /**
     * 查询消息推送记录
     * 
     * @param messageId 消息推送记录ID
     * @return 消息推送记录
     */
    public IotMessage selectIotMessageById(Long messageId);

    /**
     * 查询消息推送记录列表
     * 
     * @param iotMessage 消息推送记录
     * @return 消息推送记录集合
     */
    public List<IotMessage> selectIotMessageList(IotMessage iotMessage);

    /**
     * 新增消息推送记录
     * 
     * @param iotMessage 消息推送记录
     * @return 结果
     */
    public int insertIotMessage(IotMessage iotMessage);

    /**
     * 修改消息推送记录
     * 
     * @param iotMessage 消息推送记录
     * @return 结果
     */
    public int updateIotMessage(IotMessage iotMessage);

    /**
     * 批量删除消息推送记录
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotMessageByIds(Long[] messageIds);

    /**
     * 删除消息推送记录信息
     * 
     * @param messageId 消息推送记录ID
     * @return 结果
     */
    public int deleteIotMessageById(Long messageId);

    /**
     * 推送消息给所有设备
     * 
     * @param messageContent 消息内容
     * @return 结果
     */
    public int pushMessageToAll(String messageContent);

    /**
     * 推送消息给指定设备
     * 
     * @param deviceCodes 设备标识列表
     * @param messageContent 消息内容
     * @return 结果
     */
    public int pushMessageToDevices(String[] deviceCodes, String messageContent);

    /**
     * 推送消息给指定分组
     * 
     * @param groupId 分组ID
     * @param messageContent 消息内容
     * @return 结果
     */
    public int pushMessageToGroup(Long groupId, String messageContent);
}