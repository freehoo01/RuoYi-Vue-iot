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
import com.ruoyi.iot.domain.MqttConfig;
import com.ruoyi.iot.service.IMqttConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * MQTT服务配置Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/mqtt")
public class MqttConfigController extends BaseController
{
    @Autowired
    private IMqttConfigService mqttConfigService;

    /**
     * 获取MQTT服务配置列表
     */
    @PreAuthorize("@ss.hasPermi('iot:mqtt:list')")
    @GetMapping("/list")
    public TableDataInfo list(MqttConfig mqttConfig)
    {
        startPage();
        List<MqttConfig> list = mqttConfigService.selectMqttConfigList(mqttConfig);
        return getDataTable(list);
    }

    /**
     * 导出MQTT服务配置列表
     */
    @PreAuthorize("@ss.hasPermi('iot:mqtt:export')")
    @Log(title = "MQTT服务配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MqttConfig mqttConfig)
    {
        List<MqttConfig> list = mqttConfigService.selectMqttConfigList(mqttConfig);
        ExcelUtil<MqttConfig> util = new ExcelUtil<>(MqttConfig.class);
        util.exportExcel(response, list, "MQTT服务配置数据");
    }

    /**
     * 获取MQTT服务配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:mqtt:query')")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable("configId") Long configId)
    {
        return success(mqttConfigService.selectMqttConfigById(configId));
    }

    /**
     * 获取当前启用的MQTT配置
     */
    @GetMapping("/current")
    public AjaxResult getCurrentConfig()
    {
        return success(mqttConfigService.getCurrentMqttConfig());
    }

    /**
     * 新增MQTT服务配置
     */
    @PreAuthorize("@ss.hasPermi('iot:mqtt:add')")
    @Log(title = "MQTT服务配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MqttConfig mqttConfig)
    {
        return toAjax(mqttConfigService.insertMqttConfig(mqttConfig));
    }

    /**
     * 修改MQTT服务配置
     */
    @PreAuthorize("@ss.hasPermi('iot:mqtt:edit')")
    @Log(title = "MQTT服务配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MqttConfig mqttConfig)
    {
        return toAjax(mqttConfigService.updateMqttConfig(mqttConfig));
    }

    /**
     * 删除MQTT服务配置
     */
    @PreAuthorize("@ss.hasPermi('iot:mqtt:remove')")
    @Log(title = "MQTT服务配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds)
    {
        return toAjax(mqttConfigService.deleteMqttConfigByIds(configIds));
    }
}