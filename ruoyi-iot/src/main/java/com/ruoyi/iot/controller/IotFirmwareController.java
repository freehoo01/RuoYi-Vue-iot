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
import com.ruoyi.iot.domain.IotFirmware;
import com.ruoyi.iot.service.IIotFirmwareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 固件信息Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/firmware")
public class IotFirmwareController extends BaseController
{
    @Autowired
    private IIotFirmwareService iotFirmwareService;

    /**
     * 获取固件信息列表
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:list')")
    @GetMapping("/list")
    public TableDataInfo list(IotFirmware iotFirmware)
    {
        startPage();
        List<IotFirmware> list = iotFirmwareService.selectIotFirmwareList(iotFirmware);
        return getDataTable(list);
    }

    /**
     * 导出固件信息列表
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:export')")
    @Log(title = "固件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IotFirmware iotFirmware)
    {
        List<IotFirmware> list = iotFirmwareService.selectIotFirmwareList(iotFirmware);
        ExcelUtil<IotFirmware> util = new ExcelUtil<IotFirmware>(IotFirmware.class);
        util.exportExcel(response, list, "固件信息数据");
    }

    /**
     * 获取固件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:query')")
    @GetMapping(value = "/{firmwareId}")
    public AjaxResult getInfo(@PathVariable("firmwareId") Long firmwareId)
    {
        return success(iotFirmwareService.selectIotFirmwareById(firmwareId));
    }

    /**
     * 新增固件信息
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:add')")
    @Log(title = "固件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IotFirmware iotFirmware)
    {
        return toAjax(iotFirmwareService.insertIotFirmware(iotFirmware));
    }

    /**
     * 修改固件信息
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:edit')")
    @Log(title = "固件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IotFirmware iotFirmware)
    {
        return toAjax(iotFirmwareService.updateIotFirmware(iotFirmware));
    }

    /**
     * 删除固件信息
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:remove')")
    @Log(title = "固件信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{firmwareIds}")
    public AjaxResult remove(@PathVariable Long[] firmwareIds)
    {
        return toAjax(iotFirmwareService.deleteIotFirmwareByIds(firmwareIds));
    }

    /**
     * 根据设备类型查询最新固件
     */
    @PreAuthorize("@ss.hasPermi('iot:firmware:latest')")
    @GetMapping("/latest/{deviceType}")
    public AjaxResult getLatestFirmware(@PathVariable("deviceType") String deviceType)
    {
        return success(iotFirmwareService.getLatestFirmwareByDeviceType(deviceType));
    }
}