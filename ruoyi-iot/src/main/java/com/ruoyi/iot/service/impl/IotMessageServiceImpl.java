package com.ruoyi.iot.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotMessageMapper;
import com.ruoyi.iot.domain.IotMessage;
import com.ruoyi.iot.service.IIotMessageService;

/**
 * 消息推送记录Service实现类
 * 
 * @author ruoyi
 */
@Service
public class IotMessageServiceImpl implements IIotMessageService
{
    @Autowired
    private IotMessageMapper iotMessageMapper;

    /**
     * 查询消息推送记录
     * 
     * @param messageId 消息推送记录ID
     * @return 消息推送记录
     */
    @Override
    public IotMessage selectIotMessageById(Long messageId)
    {
        return iotMessageMapper.selectIotMessageById(messageId);
    }

    /**
     * 查询消息推送记录列表
     * 
     * @param iotMessage 消息推送记录
     * @return 消息推送记录集合
     */
    @Override
    public List<IotMessage> selectIotMessageList(IotMessage iotMessage)
    {
        return iotMessageMapper.selectIotMessageList(iotMessage);
    }

    /**
     * 新增消息推送记录
     * 
     * @param iotMessage 消息推送记录
     * @return 结果
     */
    @Override
    public int insertIotMessage(IotMessage iotMessage)
    {
        return iotMessageMapper.insertIotMessage(iotMessage);
    }

    /**
     * 修改消息推送记录
     * 
     * @param iotMessage 消息推送记录
     * @return 结果
     */
    @Override
    public int updateIotMessage(IotMessage iotMessage)
    {
        return iotMessageMapper.updateIotMessage(iotMessage);
    }

    /**
     * 批量删除消息推送记录
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotMessageByIds(Long[] messageIds)
    {
        return iotMessageMapper.deleteIotMessageByIds(messageIds);
    }

    /**
     * 删除消息推送记录信息
     * 
     * @param messageId 消息推送记录ID
     * @return 结果
     */
    @Override
    public int deleteIotMessageById(Long messageId)
    {
        return iotMessageMapper.deleteIotMessageById(messageId);
    }

    /**
     * 推送消息给所有设备
     * 
     * @param messageContent 消息内容
     * @return 结果
     */
    @Override
    public int pushMessageToAll(String messageContent)
    {
        // 记录消息推送记录
        IotMessage message = new IotMessage();
        message.setPushMode("0"); // 全局推送
        message.setMessageContent(messageContent);
        message.setStatus("2"); // 推送成功
        message.setPushTime(new Date().toString());
        message.setResultDesc("消息已发送到全局主题");
        return insertIotMessage(message);
    }

    /**
     * 推送消息给指定设备
     * 
     * @param deviceCodes 设备标识列表
     * @param messageContent 消息内容
     * @return 结果
     */
    @Override
    public int pushMessageToDevices(String[] deviceCodes, String messageContent)
    {
        // 记录消息推送记录
        IotMessage message = new IotMessage();
        message.setPushMode("1"); // 指定设备推送
        message.setTargetDevices(StringUtils.join(deviceCodes, ","));
        message.setMessageContent(messageContent);
        message.setStatus("2"); // 推送成功
        message.setPushTime(new Date().toString());
        message.setResultDesc("消息已发送到指定设备");
        return insertIotMessage(message);
    }

    /**
     * 推送消息给指定分组
     * 
     * @param groupId 分组ID
     * @param messageContent 消息内容
     * @return 结果
     */
    @Override
    public int pushMessageToGroup(Long groupId, String messageContent)
    {
        // 记录消息推送记录
        IotMessage message = new IotMessage();
        message.setPushMode("1"); // 指定设备推送
        message.setTargetGroupId(groupId);
        message.setMessageContent(messageContent);
        message.setStatus("2"); // 推送成功
        message.setPushTime(new Date().toString());
        message.setResultDesc("消息已发送到指定分组");
        return insertIotMessage(message);
    }
}