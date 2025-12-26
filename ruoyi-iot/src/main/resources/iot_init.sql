-- MQTT服务配置表
CREATE TABLE `mqtt_config` (
  `config_id` bigint NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `server_url` varchar(255) NOT NULL COMMENT 'MQTT服务器地址',
  `port` int NOT NULL COMMENT '服务端口',
  `username` varchar(50) DEFAULT NULL COMMENT '访问账号',
  `password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `version` varchar(20) DEFAULT NULL COMMENT '配置版本',
  `status` char(1) NOT NULL COMMENT '是否启用（0停用 1启用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='MQTT服务配置表';

-- 设备分组表
CREATE TABLE `iot_device_group` (
  `group_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分组ID',
  `group_name` varchar(50) NOT NULL COMMENT '分组名称',
  `description` varchar(200) DEFAULT NULL COMMENT '分组描述',
  `group_type` varchar(20) DEFAULT NULL COMMENT '分组类型（按区域、按设备类型、按业务场景）',
  `parent_id` bigint DEFAULT NULL COMMENT '父分组ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备分组表';

-- 设备表
CREATE TABLE `iot_device` (
  `device_id` bigint NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_code` varchar(50) NOT NULL COMMENT '设备唯一标识（MAC地址/设备SN码）',
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备别名',
  `group_id` bigint DEFAULT NULL COMMENT '设备分组ID',
  `status` char(1) NOT NULL COMMENT '连接状态（0离线 1在线）',
  `last_online_time` datetime DEFAULT NULL COMMENT '最后上线时间',
  `device_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
  `firmware_version` varchar(20) DEFAULT NULL COMMENT '固件版本',
  `device_ip` varchar(50) DEFAULT NULL COMMENT '设备IP地址',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `uk_device_code` (`device_code`),
  KEY `idx_group_id` (`group_id`),
  KEY `idx_status` (`status`),
  CONSTRAINT `fk_device_group` FOREIGN KEY (`group_id`) REFERENCES `iot_device_group` (`group_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备表';

-- 消息推送记录表
CREATE TABLE `iot_message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `push_mode` char(1) NOT NULL COMMENT '推送模式（0全局推送 1指定设备推送）',
  `target_devices` varchar(1000) DEFAULT NULL COMMENT '目标设备标识（多个设备用逗号分隔）',
  `target_group_id` bigint DEFAULT NULL COMMENT '目标分组ID',
  `message_content` text NOT NULL COMMENT '消息内容',
  `status` char(1) NOT NULL COMMENT '推送状态（0未推送 1推送中 2推送成功 3推送失败）',
  `result_desc` varchar(200) DEFAULT NULL COMMENT '推送结果描述',
  `push_time` datetime DEFAULT NULL COMMENT '推送时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`message_id`),
  KEY `idx_push_mode` (`push_mode`),
  KEY `idx_status` (`status`),
  KEY `idx_push_time` (`push_time`),
  CONSTRAINT `fk_message_group` FOREIGN KEY (`target_group_id`) REFERENCES `iot_device_group` (`group_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息推送记录表';

-- 设备实时数据表
CREATE TABLE `iot_device_data` (
  `data_id` bigint NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `device_code` varchar(50) NOT NULL COMMENT '设备唯一标识',
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `data_type` varchar(50) NOT NULL COMMENT '数据类型（如温度、湿度、压力等）',
  `data_value` varchar(100) NOT NULL COMMENT '数据值',
  `unit` varchar(20) DEFAULT NULL COMMENT '数据单位',
  `status` char(1) NOT NULL COMMENT '数据状态（0-正常，1-异常）',
  `collect_time` datetime NOT NULL COMMENT '数据采集时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`data_id`),
  KEY `idx_device_code` (`device_code`),
  KEY `idx_data_type` (`data_type`),
  KEY `idx_collect_time` (`collect_time`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备实时数据表';

-- 固件信息表
CREATE TABLE `iot_firmware` (
  `firmware_id` bigint NOT NULL AUTO_INCREMENT COMMENT '固件ID',
  `firmware_version` varchar(50) NOT NULL COMMENT '固件版本',
  `firmware_name` varchar(100) NOT NULL COMMENT '固件名称',
  `description` varchar(500) DEFAULT NULL COMMENT '固件描述',
  `device_type` varchar(50) NOT NULL COMMENT '固件类型（对应设备类型）',
  `firmware_path` varchar(255) NOT NULL COMMENT '固件文件路径',
  `file_size` bigint NOT NULL COMMENT '固件文件大小',
  `md5` varchar(32) NOT NULL COMMENT '固件文件MD5',
  `status` char(1) NOT NULL COMMENT '固件状态（0-未发布，1-已发布）',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`firmware_id`),
  KEY `idx_firmware_version` (`firmware_version`),
  KEY `idx_device_type` (`device_type`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='固件信息表';

-- 固件升级任务表
CREATE TABLE `iot_firmware_upgrade_task` (
  `task_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `task_name` varchar(100) NOT NULL COMMENT '任务名称',
  `firmware_id` bigint NOT NULL COMMENT '固件ID',
  `firmware_version` varchar(50) NOT NULL COMMENT '固件版本',
  `target_devices` varchar(2000) DEFAULT NULL COMMENT '目标设备列表（多个设备用逗号分隔）',
  `target_group_id` bigint DEFAULT NULL COMMENT '目标分组ID',
  `status` char(1) NOT NULL COMMENT '升级状态（0-未开始，1-升级中，2-升级完成，3-升级失败）',
  `progress` int DEFAULT '0' COMMENT '升级进度（百分比）',
  `success_count` int DEFAULT '0' COMMENT '成功升级设备数量',
  `fail_count` int DEFAULT '0' COMMENT '失败升级设备数量',
  `total_count` int DEFAULT '0' COMMENT '总设备数量',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`task_id`),
  KEY `idx_status` (`status`),
  KEY `idx_firmware_id` (`firmware_id`),
  KEY `idx_target_group_id` (`target_group_id`),
  CONSTRAINT `fk_upgrade_firmware` FOREIGN KEY (`firmware_id`) REFERENCES `iot_firmware` (`firmware_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_upgrade_group` FOREIGN KEY (`target_group_id`) REFERENCES `iot_device_group` (`group_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='固件升级任务表';