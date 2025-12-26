-- 若依框架IoT模块菜单数据脚本
-- 执行此脚本将添加IoT模块的菜单和权限配置

-- 1. 物联网管理主目录
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('物联网管理', 0, 100, '/iot', 'Layout', 1, 0, 'M', '0', '0', '', 'iot', 'admin', NOW(), 'admin', NOW(), '物联网管理主目录');

-- 获取物联网管理主目录ID
SET @iot_menu_id = LAST_INSERT_ID();

-- 2. 设备管理子菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备管理', @iot_menu_id, 1, 'device', 'iot/device/index', 1, 0, 'C', '0', '0', 'iot:device:list', 'device', 'admin', NOW(), 'admin', NOW(), '设备管理菜单');

-- 设备管理权限按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备查询', @iot_menu_id, 1, '#', '', 1, 0, 'F', '0', '0', 'iot:device:query', '', 'admin', NOW(), 'admin', NOW(), '设备查询权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备新增', @iot_menu_id, 2, '#', '', 1, 0, 'F', '0', '0', 'iot:device:add', '', 'admin', NOW(), 'admin', NOW(), '设备新增权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备修改', @iot_menu_id, 3, '#', '', 1, 0, 'F', '0', '0', 'iot:device:edit', '', 'admin', NOW(), 'admin', NOW(), '设备修改权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备删除', @iot_menu_id, 4, '#', '', 1, 0, 'F', '0', '0', 'iot:device:remove', '', 'admin', NOW(), 'admin', NOW(), '设备删除权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备导出', @iot_menu_id, 5, '#', '', 1, 0, 'F', '0', '0', 'iot:device:export', '', 'admin', NOW(), 'admin', NOW(), '设备导出权限');

-- 3. 固件管理子菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件管理', @iot_menu_id, 2, 'firmware', 'iot/firmware/index', 1, 0, 'C', '0', '0', 'iot:firmware:list', 'firmware', 'admin', NOW(), 'admin', NOW(), '固件管理菜单');

-- 固件管理权限按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件查询', @iot_menu_id, 6, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:query', '', 'admin', NOW(), 'admin', NOW(), '固件查询权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件新增', @iot_menu_id, 7, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:add', '', 'admin', NOW(), 'admin', NOW(), '固件新增权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件修改', @iot_menu_id, 8, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:edit', '', 'admin', NOW(), 'admin', NOW(), '固件修改权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件删除', @iot_menu_id, 9, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:remove', '', 'admin', NOW(), 'admin', NOW(), '固件删除权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件导出', @iot_menu_id, 10, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:export', '', 'admin', NOW(), 'admin', NOW(), '固件导出权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件发布', @iot_menu_id, 11, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:publish', '', 'admin', NOW(), 'admin', NOW(), '固件发布权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('固件升级', @iot_menu_id, 12, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware:upgrade', '', 'admin', NOW(), 'admin', NOW(), '固件升级权限');

-- 固件升级任务权限按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('升级任务开始', @iot_menu_id, 13, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware-upgrade-task:start', '', 'admin', NOW(), 'admin', NOW(), '升级任务开始权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('升级任务取消', @iot_menu_id, 14, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware-upgrade-task:cancel', '', 'admin', NOW(), 'admin', NOW(), '升级任务取消权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('升级任务删除', @iot_menu_id, 15, '#', '', 1, 0, 'F', '0', '0', 'iot:firmware-upgrade-task:remove', '', 'admin', NOW(), 'admin', NOW(), '升级任务删除权限');

-- 4. MQTT配置子菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置', @iot_menu_id, 3, 'mqtt-config', 'iot/mqttConfig/index', 1, 0, 'C', '0', '0', 'iot:mqtt-config:list', 'mqtt', 'admin', NOW(), 'admin', NOW(), 'MQTT配置菜单');

-- MQTT配置权限按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置查询', @iot_menu_id, 16, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:query', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置查询权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置新增', @iot_menu_id, 17, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:add', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置新增权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置修改', @iot_menu_id, 18, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:edit', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置修改权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置删除', @iot_menu_id, 19, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:remove', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置删除权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置导出', @iot_menu_id, 20, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:export', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置导出权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置连接', @iot_menu_id, 21, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:connect', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置连接权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('MQTT配置断开', @iot_menu_id, 22, '#', '', 1, 0, 'F', '0', '0', 'iot:mqtt-config:disconnect', '', 'admin', NOW(), 'admin', NOW(), 'MQTT配置断开权限');

-- 5. 设备数据权限
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备数据查询', @iot_menu_id, 23, '#', '', 1, 0, 'F', '0', '0', 'iot:device-data:query', '', 'admin', NOW(), 'admin', NOW(), '设备数据查询权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备数据列表', @iot_menu_id, 24, '#', '', 1, 0, 'F', '0', '0', 'iot:device-data:list', '', 'admin', NOW(), 'admin', NOW(), '设备数据列表权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备数据最新', @iot_menu_id, 25, '#', '', 1, 0, 'F', '0', '0', 'iot:device-data:latest', '', 'admin', NOW(), 'admin', NOW(), '设备数据最新权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备数据历史', @iot_menu_id, 26, '#', '', 1, 0, 'F', '0', '0', 'iot:device-data:history', '', 'admin', NOW(), 'admin', NOW(), '设备数据历史权限');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('设备数据导出', @iot_menu_id, 27, '#', '', 1, 0, 'F', '0', '0', 'iot:device-data:export', '', 'admin', NOW(), 'admin', NOW(), '设备数据导出权限');

-- 6. 管理员角色授权
-- 注意：请确保admin角色ID为1，若不是请修改以下语句中的role_id值
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id
FROM sys_menu
WHERE perms LIKE 'iot:%';

-- 7. 刷新菜单缓存
-- 注意：执行此脚本后，需要重启应用或调用刷新缓存接口，才能在前端看到新添加的菜单

-- 脚本执行完成后，登录系统即可看到物联网管理菜单
-- 菜单包含：设备管理、固件管理、MQTT配置
-- 各菜单包含相应的权限按钮

-- 说明：
-- 1. menu_type字段说明：
--    M: 目录
--    C: 菜单
--    F: 按钮权限
-- 2. visible字段说明：
--    0: 显示
--    1: 隐藏
-- 3. status字段说明：
--    0: 正常
--    1: 停用
-- 4. is_frame字段说明：
--    1: 是
--    0: 否
-- 5. is_cache字段说明：
--    1: 缓存
--    0: 不缓存