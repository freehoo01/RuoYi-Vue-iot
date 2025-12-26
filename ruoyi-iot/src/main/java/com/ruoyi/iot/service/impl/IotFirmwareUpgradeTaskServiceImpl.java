package com.ruoyi.iot.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.iot.mapper.IotFirmwareUpgradeTaskMapper;
import com.ruoyi.iot.domain.IotFirmwareUpgradeTask;
import com.ruoyi.iot.service.IIotFirmwareUpgradeTaskService;

/**
 * 固件升级任务Service实现类
 * 
 * @author ruoyi
 */
@Service
public class IotFirmwareUpgradeTaskServiceImpl implements IIotFirmwareUpgradeTaskService
{
    @Autowired
    private IotFirmwareUpgradeTaskMapper iotFirmwareUpgradeTaskMapper;

    /**
     * 查询固件升级任务
     * 
     * @param taskId 固件升级任务ID
     * @return 固件升级任务
     */
    @Override
    public IotFirmwareUpgradeTask selectIotFirmwareUpgradeTaskById(Long taskId)
    {
        return iotFirmwareUpgradeTaskMapper.selectIotFirmwareUpgradeTaskById(taskId);
    }

    /**
     * 查询固件升级任务列表
     * 
     * @param iotFirmwareUpgradeTask 固件升级任务
     * @return 固件升级任务集合
     */
    @Override
    public List<IotFirmwareUpgradeTask> selectIotFirmwareUpgradeTaskList(IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        return iotFirmwareUpgradeTaskMapper.selectIotFirmwareUpgradeTaskList(iotFirmwareUpgradeTask);
    }

    /**
     * 新增固件升级任务
     * 
     * @param iotFirmwareUpgradeTask 固件升级任务
     * @return 结果
     */
    @Override
    public int insertIotFirmwareUpgradeTask(IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        return iotFirmwareUpgradeTaskMapper.insertIotFirmwareUpgradeTask(iotFirmwareUpgradeTask);
    }

    /**
     * 修改固件升级任务
     * 
     * @param iotFirmwareUpgradeTask 固件升级任务
     * @return 结果
     */
    @Override
    public int updateIotFirmwareUpgradeTask(IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        return iotFirmwareUpgradeTaskMapper.updateIotFirmwareUpgradeTask(iotFirmwareUpgradeTask);
    }

    /**
     * 批量删除固件升级任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotFirmwareUpgradeTaskByIds(Long[] taskIds)
    {
        return iotFirmwareUpgradeTaskMapper.deleteIotFirmwareUpgradeTaskByIds(taskIds);
    }

    /**
     * 删除固件升级任务信息
     * 
     * @param taskId 固件升级任务ID
     * @return 结果
     */
    @Override
    public int deleteIotFirmwareUpgradeTaskById(Long taskId)
    {
        return iotFirmwareUpgradeTaskMapper.deleteIotFirmwareUpgradeTaskById(taskId);
    }

    /**
     * 开始固件升级任务
     * 
     * @param taskId 固件升级任务ID
     * @return 结果
     */
    @Override
    public int startUpgradeTask(Long taskId)
    {
        IotFirmwareUpgradeTask task = selectIotFirmwareUpgradeTaskById(taskId);
        if (task != null) {
            task.setStatus("1"); // 升级中
            task.setStartTime(new Date().toString());
            task.setProgress(0); // 初始进度0%
            return updateIotFirmwareUpgradeTask(task);
        }
        return 0;
    }

    /**
     * 取消固件升级任务
     * 
     * @param taskId 固件升级任务ID
     * @return 结果
     */
    @Override
    public int cancelUpgradeTask(Long taskId)
    {
        IotFirmwareUpgradeTask task = selectIotFirmwareUpgradeTaskById(taskId);
        if (task != null) {
            task.setStatus("3"); // 升级失败
            task.setEndTime(new Date().toString());
            return updateIotFirmwareUpgradeTask(task);
        }
        return 0;
    }
}