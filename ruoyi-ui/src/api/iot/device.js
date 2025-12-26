import request from '@/utils/request'

// 查询设备列表
export function listDevice(query) {
  return request({
    url: '/iot/device/list',
    method: 'get',
    params: query
  })
}

// 查询设备详细
export function getDevice(deviceId) {
  return request({
    url: '/iot/device/' + deviceId,
    method: 'get'
  })
}

// 新增设备
export function addDevice(data) {
  return request({
    url: '/iot/device',
    method: 'post',
    data: data
  })
}

// 修改设备
export function updateDevice(data) {
  return request({
    url: '/iot/device',
    method: 'put',
    data: data
  })
}

// 删除设备
export function delDevice(deviceIds) {
  return request({
    url: '/iot/device/' + deviceIds,
    method: 'delete'
  })
}

// 导出设备
export function exportDevice(query) {
  return request({
    url: '/iot/device/export',
    method: 'post',
    params: query
  })
}

// 查询设备最新数据
export function getLatestDeviceData(deviceCode) {
  return request({
    url: '/iot/device-data/latest/' + deviceCode,
    method: 'get'
  })
}