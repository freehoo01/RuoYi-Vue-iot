package com.ruoyi.iot.mapper;

import java.util.List;
import com.ruoyi.iot.domain.MqttConfig;

/**
 * MQTT服务配置Mapper接口
 * 
 * @author ruoyi
 */
public interface MqttConfigMapper
{
    /**
     * 查询MQTT服务配置
     * 
     * @param configId MQTT服务配置ID
     * @return MQTT服务配置
     */
    public MqttConfig selectMqttConfigById(Long configId);

    /**
     * 查询MQTT服务配置列表
     * 
     * @param mqttConfig MQTT服务配置
     * @return MQTT服务配置集合
     */
    public List<MqttConfig> selectMqttConfigList(MqttConfig mqttConfig);

    /**
     * 新增MQTT服务配置
     * 
     * @param mqttConfig MQTT服务配置
     * @return 结果
     */
    public int insertMqttConfig(MqttConfig mqttConfig);

    /**
     * 修改MQTT服务配置
     * 
     * @param mqttConfig MQTT服务配置
     * @return 结果
     */
    public int updateMqttConfig(MqttConfig mqttConfig);

    /**
     * 删除MQTT服务配置
     * 
     * @param configId MQTT服务配置ID
     * @return 结果
     */
    public int deleteMqttConfigById(Long configId);

    /**
     * 批量删除MQTT服务配置
     * 
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMqttConfigByIds(Long[] configIds);

    /**
     * 获取当前启用的MQTT配置
     * 
     * @return MQTT服务配置
     */
    public MqttConfig selectCurrentMqttConfig();
}