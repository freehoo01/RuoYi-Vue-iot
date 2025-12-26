package com.ruoyi.iot.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.iot.domain.IotFirmwareUpgradeTask;
import com.ruoyi.iot.service.IIotFirmwareUpgradeTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 固件升级任务Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/firmware-upgrade-task")
public class IotFirmwareUpgradeTaskController extends BaseController
{
    @Autowired
    private IIotFirmwareUpgradeTaskService iotFirmwareUpgradeTaskService;

    /**
     * 获取固件升级任务列表
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:list')")
    @GetMapping("/list")
    public TableDataInfo list(IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        startPage();
        List<IotFirmwareUpgradeTask> list = iotFirmwareUpgradeTaskService.selectIotFirmwareUpgradeTaskList(iotFirmwareUpgradeTask);
        return getDataTable(list);
    }

    /**
     * 导出固件升级任务列表
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:export')")
    @Log(title = "固件升级任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        List<IotFirmwareUpgradeTask> list = iotFirmwareUpgradeTaskService.selectIotFirmwareUpgradeTaskList(iotFirmwareUpgradeTask);
        ExcelUtil<IotFirmwareUpgradeTask> util = new ExcelUtil<IotFirmwareUpgradeTask>(IotFirmwareUpgradeTask.class);
        util.exportExcel(response, list, "固件升级任务数据");
    }

    /**
     * 获取固件升级任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(iotFirmwareUpgradeTaskService.selectIotFirmwareUpgradeTaskById(taskId));
    }

    /**
     * 新增固件升级任务
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:add')")
    @Log(title = "固件升级任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        return toAjax(iotFirmwareUpgradeTaskService.insertIotFirmwareUpgradeTask(iotFirmwareUpgradeTask));
    }

    /**
     * 修改固件升级任务
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:edit')")
    @Log(title = "固件升级任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IotFirmwareUpgradeTask iotFirmwareUpgradeTask)
    {
        return toAjax(iotFirmwareUpgradeTaskService.updateIotFirmwareUpgradeTask(iotFirmwareUpgradeTask));
    }

    /**
     * 删除固件升级任务
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:remove')")
    @Log(title = "固件升级任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(iotFirmwareUpgradeTaskService.deleteIotFirmwareUpgradeTaskByIds(taskIds));
    }

    /**
     * 开始固件升级任务
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:start')")
    @Log(title = "固件升级任务", businessType = BusinessType.UPDATE)
    @PostMapping("/start/{taskId}")
    public AjaxResult startTask(@PathVariable("taskId") Long taskId)
    {
        return toAjax(iotFirmwareUpgradeTaskService.startUpgradeTask(taskId));
    }

    /**
     * 取消固件升级任务
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware-upgrade-task:cancel')")
    @Log(title = "固件升级任务", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel/{taskId}")
    public AjaxResult cancelTask(@PathVariable("taskId") Long taskId)
    {
        return toAjax(iotFirmwareUpgradeTaskService.cancelUpgradeTask(taskId));
    }
}