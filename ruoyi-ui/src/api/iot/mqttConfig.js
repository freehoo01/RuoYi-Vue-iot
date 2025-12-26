import request from '@/utils/request'

// 查询MQTT配置列表
export function listMqttConfig(query) {
  return request({
    url: '/iot/mqtt-config/list',
    method: 'get',
    params: query
  })
}

// 查询MQTT配置详细
export function getMqttConfig(configId) {
  return request({
    url: '/iot/mqtt-config/' + configId,
    method: 'get'
  })
}

// 新增MQTT配置
export function addMqttConfig(data) {
  return request({
    url: '/iot/mqtt-config',
    method: 'post',
    data: data
  })
}

// 修改MQTT配置
export function updateMqttConfig(data) {
  return request({
    url: '/iot/mqtt-config',
    method: 'put',
    data: data
  })
}

// 删除MQTT配置
export function delMqttConfig(configIds) {
  return request({
    url: '/iot/mqtt-config/' + configIds,
    method: 'delete'
  })
}

// 导出MQTT配置
export function exportMqttConfig(query) {
  return request({
    url: '/iot/mqtt-config/export',
    method: 'post',
    params: query
  })
}