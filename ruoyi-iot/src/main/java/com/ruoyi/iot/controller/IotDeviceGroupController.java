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
import com.ruoyi.iot.domain.IotDeviceGroup;
import com.ruoyi.iot.service.IIotDeviceGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备分组Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/group")
public class IotDeviceGroupController extends BaseController
{
    @Autowired
    private IIotDeviceGroupService iotDeviceGroupService;

    /**
     * 获取设备分组列表
     */
    @PreAuthorize("@ss.hasPermi('iot:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(IotDeviceGroup iotDeviceGroup)
    {
        startPage();
        List<IotDeviceGroup> list = iotDeviceGroupService.selectIotDeviceGroupList(iotDeviceGroup);
        return getDataTable(list);
    }

    /**
     * 导出设备分组列表
     */
    @PreAuthorize("@ss.hasPermi('iot:group:export')")
    @Log(title = "设备分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IotDeviceGroup iotDeviceGroup)
    {
        List<IotDeviceGroup> list = iotDeviceGroupService.selectIotDeviceGroupList(iotDeviceGroup);
        ExcelUtil<IotDeviceGroup> util = new ExcelUtil<IotDeviceGroup>(IotDeviceGroup.class);
        util.exportExcel(response, list, "设备分组数据");
    }

    /**
     * 获取设备分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return success(iotDeviceGroupService.selectIotDeviceGroupById(groupId));
    }

    /**
     * 新增设备分组
     */
    @PreAuthorize("@ss.hasPermi('iot:group:add')")
    @Log(title = "设备分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IotDeviceGroup iotDeviceGroup)
    {
        return toAjax(iotDeviceGroupService.insertIotDeviceGroup(iotDeviceGroup));
    }

    /**
     * 修改设备分组
     */
    @PreAuthorize("@ss.hasPermi('iot:group:edit')")
    @Log(title = "设备分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IotDeviceGroup iotDeviceGroup)
    {
        return toAjax(iotDeviceGroupService.updateIotDeviceGroup(iotDeviceGroup));
    }

    /**
     * 删除设备分组
     */
    @PreAuthorize("@ss.hasPermi('iot:group:remove')")
    @Log(title = "设备分组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(iotDeviceGroupService.deleteIotDeviceGroupByIds(groupIds));
    }
}