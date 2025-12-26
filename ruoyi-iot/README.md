# 若依框架IoT模块

## 功能概述

基于若依(RuoYi-Vue-springboot3)框架开发的独立IoT功能模块，实现MQTT通信链路搭建、ESP32-S3设备自动化接入、设备全生命周期管理及精准消息推送四大核心能力，构建轻量化、可落地的物联网后台管理体系。

## 核心功能

### 1. MQTT服务配置管理
- 支持管理人员可视化配置MQTT核心参数
- 配置项包含：MQTT服务器地址、服务端口、访问账号、登录密码
- 支持配置信息保存、编辑、缓存及有效性校验
- 提供标准化读取接口，供前端设备获取
- 支持配置版本记录与回滚

### 2. 设备自动化接入
- 设备联网后自动获取MQTT服务参数
- 基于获取的参数完成连接初始化
- 默认订阅指定双主题：
  - 上行主题：`sgjn/up`（设备向后台上报设备状态、业务数据等信息）
  - 下行主题：`sgjn/down`（设备接收后台下发的控制指令、配置更新等消息）
- 实现设备自动注册机制：设备首次连接MQTT并上线后，自动向后台提交设备信息，完成自动注册入库

### 3. 设备后台管理
- 设备基础管理：展示所有已注册设备的核心信息，支持查询、筛选、分页
- 设备分组管理：支持自定义创建/编辑/删除设备分组，支持批量/单个将设备归属至指定分组
- 设备别名配置：支持为每台设备设置个性化别名
- 设备状态监控：实时同步设备MQTT连接状态，设备离线时自动标记

### 4. 消息推送功能
- 全局推送：向所有已注册设备统一推送消息
- 指定设备推送：支持按设备唯一标识、设备别名、设备分组三种维度精准筛选目标设备
- 支持消息内容自定义输入
- 支持推送记录留存与查询

## 技术栈

- 后端：Spring Boot 3.5.8 + MyBatis + MySQL + MQTT Client
- 前端：Vue 2.6.12 + Element UI

## 模块结构

```
ruoyi-iot/
├── src/
│   ├── main/
│   │   ├── java/com/ruoyi/iot/
│   │   │   ├── controller/         # 控制器层
│   │   │   ├── domain/             # 实体类
│   │   │   ├── mapper/             # Mapper接口
│   │   │   └── service/            # 服务层
│   │   └── resources/
│   │       ├── mapper/iot/         # Mapper XML文件
│   │       └── iot_init.sql        # 数据库初始化脚本
│   └── test/                       # 测试代码
├── pom.xml                         # 模块依赖配置
└── README.md                       # 模块说明文档
```

## 快速开始

### 1. 数据库初始化

执行 `src/main/resources/iot_init.sql` 脚本创建IoT模块所需的数据库表。

### 2. 模块配置

1. 在若依框架中添加IoT模块权限菜单
2. 配置MQTT服务参数
3. 设备接入配置

### 3. 设备接入流程

1. 设备联网后，调用 `/iot/mqtt/current` 接口获取MQTT服务配置
2. 基于获取的配置连接MQTT服务器
3. 订阅主题 `sgjn/up` 和 `sgjn/down`
4. 发送设备注册信息到 `sgjn/up` 主题
5. 后台自动完成设备注册

## API接口

### MQTT配置管理
- `GET /iot/mqtt/list` - 获取MQTT配置列表
- `GET /iot/mqtt/current` - 获取当前启用的MQTT配置
- `POST /iot/mqtt` - 新增MQTT配置
- `PUT /iot/mqtt` - 修改MQTT配置
- `DELETE /iot/mqtt/{configIds}` - 删除MQTT配置

### 设备管理
- `GET /iot/device/list` - 获取设备列表
- `GET /iot/device/{deviceId}` - 获取设备详情
- `POST /iot/device` - 新增设备
- `PUT /iot/device` - 修改设备
- `DELETE /iot/device/{deviceIds}` - 删除设备
- `POST /iot/device/auto-register` - 设备自动注册
- `PUT /iot/device/status/{deviceCode}/{status}` - 更新设备状态

### 设备分组管理
- `GET /iot/group/list` - 获取设备分组列表
- `GET /iot/group/{groupId}` - 获取设备分组详情
- `POST /iot/group` - 新增设备分组
- `PUT /iot/group` - 修改设备分组
- `DELETE /iot/group/{groupIds}` - 删除设备分组

### 消息推送
- `GET /iot/message/list` - 获取消息推送记录列表
- `POST /iot/message/push/all` - 推送消息给所有设备
- `POST /iot/message/push/devices` - 推送消息给指定设备
- `POST /iot/message/push/group` - 推送消息给指定分组

## 注意事项

1. 确保MQTT服务器已正确配置并运行
2. 设备需支持MQTT协议
3. 设备需正确实现主题订阅和消息处理逻辑
4. 建议使用HTTPS协议保护API接口

## 开发说明

1. 本模块基于若依框架开发，需集成到若依框架中使用
2. 支持二次开发，可根据实际需求扩展功能
3. 遵循若依框架的代码规范和开发流程

## 许可证

MIT License
