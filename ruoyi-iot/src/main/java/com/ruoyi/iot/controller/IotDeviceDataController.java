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
import com.ruoyi.iot.domain.IotDeviceData;
import com.ruoyi.iot.service.IIotDeviceDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备实时数据Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/device-data")
public class IotDeviceDataController extends BaseController
{
    @Autowired
    private IIotDeviceDataService iotDeviceDataService;

    /**
     * 获取设备实时数据列表
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:list')")
    @GetMapping("/list")
    public TableDataInfo list(IotDeviceData iotDeviceData)
    {
        startPage();
        List<IotDeviceData> list = iotDeviceDataService.selectIotDeviceDataList(iotDeviceData);
        return getDataTable(list);
    }

    /**
     * 导出设备实时数据列表
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:export')")
    @Log(title = "设备实时数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IotDeviceData iotDeviceData)
    {
        List<IotDeviceData> list = iotDeviceDataService.selectIotDeviceDataList(iotDeviceData);
        ExcelUtil<IotDeviceData> util = new ExcelUtil<IotDeviceData>(IotDeviceData.class);
        util.exportExcel(response, list, "设备实时数据");
    }

    /**
     * 获取设备实时数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:query')")
    @GetMapping(value = "/{dataId}")
    public AjaxResult getInfo(@PathVariable("dataId") Long dataId)
    {
        return success(iotDeviceDataService.selectIotDeviceDataById(dataId));
    }

    /**
     * 新增设备实时数据
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:add')")
    @Log(title = "设备实时数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IotDeviceData iotDeviceData)
    {
        return toAjax(iotDeviceDataService.insertIotDeviceData(iotDeviceData));
    }

    /**
     * 修改设备实时数据
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:edit')")
    @Log(title = "设备实时数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IotDeviceData iotDeviceData)
    {
        return toAjax(iotDeviceDataService.updateIotDeviceData(iotDeviceData));
    }

    /**
     * 删除设备实时数据
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:remove')")
    @Log(title = "设备实时数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dataIds}")
    public AjaxResult remove(@PathVariable Long[] dataIds)
    {
        return toAjax(iotDeviceDataService.deleteIotDeviceDataByIds(dataIds));
    }

    /**
     * 获取设备最新数据
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:latest')")
    @GetMapping("/latest/{deviceCode}")
    public AjaxResult getLatestData(@PathVariable("deviceCode") String deviceCode)
    {
        return success(iotDeviceDataService.getLatestDeviceData(deviceCode));
    }

    /**
     * 获取设备历史数据
     */
    @PreAuthorize("@ss.hasPermi('iot:device-data:history')")
    @GetMapping("/history")
    public AjaxResult getHistoryData(String deviceCode, String dataType, String startTime, String endTime)
    {
        return success(iotDeviceDataService.getDeviceHistoryData(deviceCode, dataType, startTime, endTime));
    }
}