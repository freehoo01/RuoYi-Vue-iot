package com.ruoyi.iot.service;

import java.util.List;
import com.ruoyi.iot.domain.IotFirmwareUpgradeTask;

/**
 * 固件升级任务Service接口
 * 
 * @author ruoyi
 */
public interface IIotFirmwareUpgradeTaskService
{
    /**
     * 查询固件升级任务
     * 
     * @param taskId 固件升级任务ID
     * @return 固件升级任务
     */
    public IotFirmwareUpgradeTask selectIotFirmwareUpgradeTaskById(Long taskId);

    /**
     * 查询固件升级任务列表
     * 
     * @param iotFirmwareUpgradeTask 固件升级任务
     * @return 固件升级任务集合
     */
    public List<IotFirmwareUpgradeTask> selectIotFirmwareUpgradeTaskList(IotFirmwareUpgradeTask iotFirmwareUpgradeTask);

    /**
     * 新增固件升级任务
     * 
     * @param iotFirmwareUpgradeTask 固件升级任务
     * @return 结果
     */
    public int insertIotFirmwareUpgradeTask(IotFirmwareUpgradeTask iotFirmwareUpgradeTask);

    /**
     * 修改固件升级任务
     * 
     * @param iotFirmwareUpgradeTask 固件升级任务
     * @return 结果
     */
    public int updateIotFirmwareUpgradeTask(IotFirmwareUpgradeTask iotFirmwareUpgradeTask);

    /**
     * 批量删除固件升级任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteIotFirmwareUpgradeTaskByIds(Long[] taskIds);

    /**
     * 删除固件升级任务信息
     * 
     * @param taskId 固件升级任务ID
     * @return 结果
     */
    public int deleteIotFirmwareUpgradeTaskById(Long taskId);

    /**
     * 开始固件升级任务
     * 
     * @param taskId 固件升级任务ID
     * @return 结果
     */
    public int startUpgradeTask(Long taskId);

    /**
     * 取消固件升级任务
     * 
     * @param taskId 固件升级任务ID
     * @return 结果
     */
    public int cancelUpgradeTask(Long taskId);
}