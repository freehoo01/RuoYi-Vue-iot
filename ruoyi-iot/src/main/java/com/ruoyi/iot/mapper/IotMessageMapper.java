package com.ruoyi.iot.mapper;

import java.util.List;
import com.ruoyi.iot.domain.IotMessage;

/**
 * 消息推送记录Mapper接口
 * 
 * @author ruoyi
 */
public interface IotMessageMapper
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
     * 删除消息推送记录
     * 
     * @param messageId 消息推送记录ID
     * @return 结果
     */
    public int deleteIotMessageById(Long messageId);

    /**
     * 批量删除消息推送记录
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotMessageByIds(Long[] messageIds);
}