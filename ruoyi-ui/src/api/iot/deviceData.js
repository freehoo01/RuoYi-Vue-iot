import request from '@/utils/request'

// 查询设备实时数据列表
export function listDeviceData(query) {
  return request({
    url: '/iot/device-data/list',
    method: 'get',
    params: query
  })
}

// 查询设备实时数据详细
export function getDeviceData(dataId) {
  return request({
    url: '/iot/device-data/' + dataId,
    method: 'get'
  })
}

// 新增设备实时数据
export function addDeviceData(data) {
  return request({
    url: '/iot/device-data',
    method: 'post',
    data: data
  })
}

// 修改设备实时数据
export function updateDeviceData(data) {
  return request({
    url: '/iot/device-data',
    method: 'put',
    data: data
  })
}

// 删除设备实时数据
export function delDeviceData(dataIds) {
  return request({
    url: '/iot/device-data/' + dataIds,
    method: 'delete'
  })
}

// 导出设备实时数据
export function exportDeviceData(query) {
  return request({
    url: '/iot/device-data/export',
    method: 'post',
    params: query
  })
}

// 查询设备历史数据
export function getDeviceHistoryData(deviceCode, dataType, startTime, endTime) {
  return request({
    url: '/iot/device-data/history',
    method: 'get',
    params: {
      deviceCode: deviceCode,
      dataType: dataType,
      startTime: startTime,
      endTime: endTime
    }
  })
}