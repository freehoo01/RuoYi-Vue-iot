package com.ruoyi.iot.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * MQTT服务配置实体类
 * 
 * @author ruoyi
 */
public class MqttConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    private Long configId;

    /** MQTT服务器地址 */
    private String serverUrl;

    /** 服务端口 */
    private Integer port;

    /** 访问账号 */
    private String username;

    /** 登录密码 */
    private String password;

    /** 配置版本 */
    private String version;

    /** 是否启用 */
    private String status;

    /** 获取配置ID */
    public Long getConfigId()
    {
        return configId;
    }

    /** 设置配置ID */
    public void setConfigId(Long configId)
    {
        this.configId = configId;
    }

    /** 获取MQTT服务器地址 */
    public String getServerUrl()
    {
        return serverUrl;
    }

    /** 设置MQTT服务器地址 */
    public void setServerUrl(String serverUrl)
    {
        this.serverUrl = serverUrl;
    }

    /** 获取服务端口 */
    public Integer getPort()
    {
        return port;
    }

    /** 设置服务端口 */
    public void setPort(Integer port)
    {
        this.port = port;
    }

    /** 获取访问账号 */
    public String getUsername()
    {
        return username;
    }

    /** 设置访问账号 */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /** 获取登录密码 */
    public String getPassword()
    {
        return password;
    }

    /** 设置登录密码 */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /** 获取配置版本 */
    public String getVersion()
    {
        return version;
    }

    /** 设置配置版本 */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /** 获取是否启用 */
    public String getStatus()
    {
        return status;
    }

    /** 设置是否启用 */
    public void setStatus(String status)
    {
        this.status = status;
    }
}