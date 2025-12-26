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
import com.ruoyi.iot.domain.IotMessage;
import com.ruoyi.iot.service.IIotMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消息推送记录Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/iot/message")
public class IotMessageController extends BaseController
{
    @Autowired
    private IIotMessageService iotMessageService;

    /**
     * 获取消息推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('iot:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(IotMessage iotMessage)
    {
        startPage();
        List<IotMessage> list = iotMessageService.selectIotMessageList(iotMessage);
        return getDataTable(list);
    }

    /**
     * 导出消息推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('iot:message:export')")
    @Log(title = "消息推送记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IotMessage iotMessage)
    {
        List<IotMessage> list = iotMessageService.selectIotMessageList(iotMessage);
        ExcelUtil<IotMessage> util = new ExcelUtil<IotMessage>(IotMessage.class);
        util.exportExcel(response, list, "消息推送记录数据");
    }

    /**
     * 获取消息推送记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('iot:message:query')")
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId)
    {
        return success(iotMessageService.selectIotMessageById(messageId));
    }

    /**
     * 新增消息推送记录
     */
    @PreAuthorize("@ss.hasPermi('iot:message:add')")
    @Log(title = "消息推送记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IotMessage iotMessage)
    {
        return toAjax(iotMessageService.insertIotMessage(iotMessage));
    }

    /**
     * 修改消息推送记录
     */
    @PreAuthorize("@ss.hasPermi('iot:message:edit')")
    @Log(title = "消息推送记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IotMessage iotMessage)
    {
        return toAjax(iotMessageService.updateIotMessage(iotMessage));
    }

    /**
     * 删除消息推送记录
     */
    @PreAuthorize("@ss.hasPermi('iot:message:remove')")
    @Log(title = "消息推送记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds)
    {
        return toAjax(iotMessageService.deleteIotMessageByIds(messageIds));
    }

    /**
     * 推送消息给所有设备
     */
    @PreAuthorize("@ss.hasPermi('iot:message:push')")
    @Log(title = "消息推送", businessType = BusinessType.INSERT)
    @PostMapping("/push/all")
    public AjaxResult pushToAll(@RequestBody String messageContent)
    {
        return toAjax(iotMessageService.pushMessageToAll(messageContent));
    }

    /**
     * 推送消息给指定设备
     */
    @PreAuthorize("@ss.hasPermi('iot:message:push')")
    @Log(title = "消息推送", businessType = BusinessType.INSERT)
    @PostMapping("/push/devices")
    public AjaxResult pushToDevices(@RequestBody PushMessageRequest request)
    {
        return toAjax(iotMessageService.pushMessageToDevices(request.getDeviceCodes(), request.getMessageContent()));
    }

    /**
     * 推送消息给指定分组
     */
    @PreAuthorize("@ss.hasPermi('iot:message:push')")
    @Log(title = "消息推送", businessType = BusinessType.INSERT)
    @PostMapping("/push/group")
    public AjaxResult pushToGroup(@RequestBody PushGroupMessageRequest request)
    {
        return toAjax(iotMessageService.pushMessageToGroup(request.getGroupId(), request.getMessageContent()));
    }

    /**
     * 推送消息请求类
     */
    static class PushMessageRequest {
        private String[] deviceCodes;
        private String messageContent;

        public String[] getDeviceCodes() {
            return deviceCodes;
        }

        public void setDeviceCodes(String[] deviceCodes) {
            this.deviceCodes = deviceCodes;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }
    }

    /**
     * 推送分组消息请求类
     */
    static class PushGroupMessageRequest {
        private Long groupId;
        private String messageContent;

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }
    }
}