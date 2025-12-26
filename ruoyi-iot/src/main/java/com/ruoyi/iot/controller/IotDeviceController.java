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
import com.ruoyi.iot.domain.IotDevice;
import com.ruoyi.iot.service.IIotDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/device")
public class IotDeviceController extends BaseController
{
    @Autowired
    private IIotDeviceService iotDeviceService;

    /**
     * 获取设备列表
     */
    @PreAuthorize("@ss.hasPermi('iot:device:list')")
    @GetMapping("/list")
    public TableDataInfo list(IotDevice iotDevice)
    {
        startPage();
        List<IotDevice> list = iotDeviceService.selectIotDeviceList(iotDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('iot:device:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IotDevice iotDevice)
    {
        List<IotDevice> list = iotDeviceService.selectIotDeviceList(iotDevice);
        ExcelUtil<IotDevice> util = new ExcelUtil<IotDevice>(IotDevice.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:device:query')")
    @GetMapping(value = "/{deviceId}")
    public AjaxResult getInfo(@PathVariable("deviceId") Long deviceId)
    {
        return success(iotDeviceService.selectIotDeviceById(deviceId));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('iot:device:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IotDevice iotDevice)
    {
        return toAjax(iotDeviceService.insertIotDevice(iotDevice));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('iot:device:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IotDevice iotDevice)
    {
        return toAjax(iotDeviceService.updateIotDevice(iotDevice));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('iot:device:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deviceIds}")
    public AjaxResult remove(@PathVariable Long[] deviceIds)
    {
        return toAjax(iotDeviceService.deleteIotDeviceByIds(deviceIds));
    }

    /**
     * 设备自动注册
     */
    @PostMapping("/auto-register")
    public AjaxResult autoRegister(@RequestBody IotDevice iotDevice)
    {
        return toAjax(iotDeviceService.autoRegisterDevice(
            iotDevice.getDeviceCode(),
            iotDevice.getDeviceName(),
            iotDevice.getDeviceType(),
            iotDevice.getFirmwareVersion(),
            iotDevice.getDeviceIp()
        ));
    }

    /**
     * 更新设备状态
     */
    @PutMapping("/status/{deviceCode}/{status}")
    public AjaxResult updateStatus(@PathVariable("deviceCode") String deviceCode, @PathVariable("status") String status)
    {
        return toAjax(iotDeviceService.updateDeviceStatus(deviceCode, status));
    }
}