package com.ruoyi.iot.service.impl;

import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.MqttConfigMapper;
import com.ruoyi.iot.domain.MqttConfig;
import com.ruoyi.iot.service.IMqttConfigService;

/**
 * MQTT服务配置Service实现类
 * 
 * @author ruoyi
 */
@Service
public class MqttConfigServiceImpl implements IMqttConfigService
{
    @Autowired
    private MqttConfigMapper mqttConfigMapper;

    /**
     * 查询MQTT服务配置
     * 
     * @param configId MQTT服务配置ID
     * @return MQTT服务配置
     */
    @Override
    public MqttConfig selectMqttConfigById(Long configId)
    {
        return mqttConfigMapper.selectMqttConfigById(configId);
    }

    /**
     * 查询MQTT服务配置列表
     * 
     * @param mqttConfig MQTT服务配置
     * @return MQTT服务配置集合
     */
    @Override
    public List<MqttConfig> selectMqttConfigList(MqttConfig mqttConfig)
    {
        return mqttConfigMapper.selectMqttConfigList(mqttConfig);
    }

    /**
     * 新增MQTT服务配置
     * 
     * @param mqttConfig MQTT服务配置
     * @return 结果
     */
    @Override
    public int insertMqttConfig(MqttConfig mqttConfig)
    {
        return mqttConfigMapper.insertMqttConfig(mqttConfig);
    }

    /**
     * 修改MQTT服务配置
     * 
     * @param mqttConfig MQTT服务配置
     * @return 结果
     */
    @Override
    public int updateMqttConfig(MqttConfig mqttConfig)
    {
        return mqttConfigMapper.updateMqttConfig(mqttConfig);
    }

    /**
     * 批量删除MQTT服务配置
     * 
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMqttConfigByIds(Long[] configIds)
    {
        return mqttConfigMapper.deleteMqttConfigByIds(configIds);
    }

    /**
     * 删除MQTT服务配置信息
     * 
     * @param configId MQTT服务配置ID
     * @return 结果
     */
    @Override
    public int deleteMqttConfigById(Long configId)
    {
        return mqttConfigMapper.deleteMqttConfigById(configId);
    }

    /**
     * 获取当前启用的MQTT配置
     * 
     * @return MQTT服务配置
     */
    @Override
    public MqttConfig getCurrentMqttConfig()
    {
        return mqttConfigMapper.selectCurrentMqttConfig();
    }
}