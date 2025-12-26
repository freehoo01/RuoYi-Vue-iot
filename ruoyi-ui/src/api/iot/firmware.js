import request from '@/utils/request'

// 查询固件信息列表
export function listFirmware(query) {
  return request({
    url: '/iot/firmware/list',
    method: 'get',
    params: query
  })
}

// 查询固件信息详细
export function getFirmware(firmwareId) {
  return request({
    url: '/iot/firmware/' + firmwareId,
    method: 'get'
  })
}

// 新增固件信息
export function addFirmware(data) {
  return request({
    url: '/iot/firmware',
    method: 'post',
    data: data
  })
}

// 修改固件信息
export function updateFirmware(data) {
  return request({
    url: '/iot/firmware',
    method: 'put',
    data: data
  })
}

// 删除固件信息
export function delFirmware(firmwareIds) {
  return request({
    url: '/iot/firmware/' + firmwareIds,
    method: 'delete'
  })
}

// 导出固件信息
export function exportFirmware(query) {
  return request({
    url: '/iot/firmware/export',
    method: 'post',
    params: query
  })
}

// 根据设备类型查询最新固件
export function getLatestFirmwareByDeviceType(deviceType) {
  return request({
    url: '/iot/firmware/latest/' + deviceType,
    method: 'get'
  })
}

// 查询固件升级任务列表
export function listFirmwareUpgradeTask(query) {
  return request({
    url: '/iot/firmware-upgrade-task/list',
    method: 'get',
    params: query
  })
}

// 查询固件升级任务详细
export function getFirmwareUpgradeTask(taskId) {
  return request({
    url: '/iot/firmware-upgrade-task/' + taskId,
    method: 'get'
  })
}

// 新增固件升级任务
export function addFirmwareUpgradeTask(data) {
  return request({
    url: '/iot/firmware-upgrade-task',
    method: 'post',
    data: data
  })
}

// 修改固件升级任务
export function updateFirmwareUpgradeTask(data) {
  return request({
    url: '/iot/firmware-upgrade-task',
    method: 'put',
    data: data
  })
}

// 删除固件升级任务
export function delFirmwareUpgradeTask(taskIds) {
  return request({
    url: '/iot/firmware-upgrade-task/' + taskIds,
    method: 'delete'
  })
}

// 开始固件升级任务
export function startUpgradeTask(taskId) {
  return request({
    url: '/iot/firmware-upgrade-task/start/' + taskId,
    method: 'post'
  })
}

// 取消固件升级任务
export function cancelUpgradeTask(taskId) {
  return request({
    url: '/iot/firmware-upgrade-task/cancel/' + taskId,
    method: 'post'
  })
}