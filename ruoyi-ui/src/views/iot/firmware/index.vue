<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="固件名称" prop="firmwareName">
        <el-input
          v-model="queryParams.firmwareName"
          placeholder="请输入固件名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="固件版本" prop="firmwareVersion">
        <el-input
          v-model="queryParams.firmwareVersion"
          placeholder="请输入固件版本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-input
          v-model="queryParams.deviceType"
          placeholder="请输入设备类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择发布状态" clearable>
          <el-option label="未发布" value="0" />
          <el-option label="已发布" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['iot:firmware:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['iot:firmware:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['iot:firmware:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-upload2"
          size="mini"
          @click="handleUpload"
          v-hasPermi="['iot:firmware:upload']"
        >上传固件</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="firmwareList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="固件ID" prop="firmwareId" width="80" align="center" />
      <el-table-column label="固件名称" prop="firmwareName" align="center" />
      <el-table-column label="固件版本" prop="firmwareVersion" align="center" />
      <el-table-column label="设备类型" prop="deviceType" align="center" />
      <el-table-column label="文件大小" prop="fileSize" align="center">
        <template slot-scope="scope">
          {{ formatFileSize(scope.row.fileSize) }}
        </template>
      </el-table-column>
      <el-table-column label="发布状态" prop="status" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '0' ? 'warning' : 'success'">
            {{ scope.row.status === '0' ? '未发布' : '已发布' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" prop="publishTime" width="180" align="center" />
      <el-table-column label="创建时间" prop="createTime" width="180" align="center" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:firmware:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row.firmwareId)"
            v-hasPermi="['iot:firmware:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="handlePublish(scope.row)"
            v-if="scope.row.status === '0'"
            v-hasPermi="['iot:firmware:publish']"
          >发布</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-upload2"
            @click="handleCreateUpgradeTask(scope.row)"
            v-hasPermi="['iot:firmware:upgrade']"
          >创建升级任务</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      :current-page.sync="currentPage"
      :page-sizes="pageSizes"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!-- 固件升级任务列表 -->
    <el-divider>固件升级任务</el-divider>
    
    <el-form :model="taskQueryParams" ref="taskQueryForm" size="small" :inline="true" label-width="68px" class="mb10">
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="taskQueryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleTaskQuery"
        />
      </el-form-item>
      <el-form-item label="固件版本" prop="firmwareVersion">
        <el-input
          v-model="taskQueryParams.firmwareVersion"
          placeholder="请输入固件版本"
          clearable
          @keyup.enter.native="handleTaskQuery"
        />
      </el-form-item>
      <el-form-item label="升级状态" prop="status">
        <el-select v-model="taskQueryParams.status" placeholder="请选择升级状态" clearable>
          <el-option label="未开始" value="0" />
          <el-option label="升级中" value="1" />
          <el-option label="升级完成" value="2" />
          <el-option label="升级失败" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleTaskQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetTaskQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="taskLoading" :data="upgradeTaskList" @selection-change="handleTaskSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" prop="taskId" width="80" align="center" />
      <el-table-column label="任务名称" prop="taskName" align="center" />
      <el-table-column label="固件版本" prop="firmwareVersion" align="center" />
      <el-table-column label="升级状态" prop="status" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '0' ? 'warning' : scope.row.status === '1' ? 'primary' : scope.row.status === '2' ? 'success' : 'danger'">
            {{ scope.row.status === '0' ? '未开始' : scope.row.status === '1' ? '升级中' : scope.row.status === '2' ? '升级完成' : '升级失败' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="升级进度" prop="progress" align="center">
        <template slot-scope="scope">
          <el-progress :percentage="scope.row.progress || 0" :stroke-width="8" />
        </template>
      </el-table-column>
      <el-table-column label="设备数量" prop="totalCount" align="center">
        <template slot-scope="scope">
          {{ scope.row.successCount || 0 }}/{{ scope.row.totalCount || 0 }}
        </template>
      </el-table-column>
      <el-table-column label="开始时间" prop="startTime" width="180" align="center" />
      <el-table-column label="结束时间" prop="endTime" width="180" align="center" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-play"
            @click="handleStartUpgradeTask(scope.row.taskId)"
            v-if="scope.row.status === '0'"
            v-hasPermi="['iot:firmware-upgrade-task:start']"
          >开始</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-close"
            @click="handleCancelUpgradeTask(scope.row.taskId)"
            v-if="scope.row.status === '1'"
            v-hasPermi="['iot:firmware-upgrade-task:cancel']"
          >取消</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDeleteUpgradeTask(scope.row.taskId)"
            v-hasPermi="['iot:firmware-upgrade-task:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-show="taskTotal>0"
      :total="taskTotal"
      :page.sync="taskQueryParams.pageNum"
      :limit.sync="taskQueryParams.pageSize"
      :current-page.sync="taskCurrentPage"
      :page-sizes="pageSizes"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleTaskSizeChange"
      @current-change="handleTaskCurrentChange"
    />

    <!-- 添加或修改固件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="固件名称" prop="firmwareName">
          <el-input v-model="form.firmwareName" placeholder="请输入固件名称" />
        </el-form-item>
        <el-form-item label="固件版本" prop="firmwareVersion">
          <el-input v-model="form.firmwareVersion" placeholder="请输入固件版本" />
        </el-form-item>
        <el-form-item label="设备类型" prop="deviceType">
          <el-input v-model="form.deviceType" placeholder="请输入设备类型" />
        </el-form-item>
        <el-form-item label="固件描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入固件描述" />
        </el-form-item>
        <el-form-item label="固件文件" prop="firmwarePath" v-if="!form.firmwareId">
          <el-upload
            ref="upload"
            :auto-upload="false"
            :on-success="handleUploadSuccess"
            :on-error="handleUploadError"
            :file-list="fileList"
            :action="uploadUrl"
            :headers="headers"
            name="file"
            :multiple="false"
            :limit="1"
            accept=".bin,.hex,.elf"
            drag
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传bin/hex/elf文件，且不超过50MB</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="固件状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择固件状态">
            <el-option label="未发布" value="0" />
            <el-option label="已发布" value="1" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>

    <!-- 固件升级任务对话框 -->
    <el-dialog :title="'创建升级任务 - ' + form.firmwareVersion" :visible.sync="taskOpen" width="600px" append-to-body>
      <el-form ref="taskForm" :model="taskForm" :rules="taskRules" label-width="80px">
        <el-form-item label="任务名称" prop="taskName">
          <el-input v-model="taskForm.taskName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="目标设备" prop="targetDevices">
          <el-select v-model="taskForm.targetDevices" multiple placeholder="请选择目标设备">
            <el-option
              v-for="device in deviceList"
              :key="device.deviceCode"
              :label="device.deviceName"
              :value="device.deviceCode"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="目标分组" prop="targetGroupId">
          <el-select v-model="taskForm.targetGroupId" placeholder="请选择目标分组">
            <el-option
              v-for="group in deviceGroups"
              :key="group.groupId"
              :label="group.groupName"
              :value="group.groupId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="任务描述" prop="remark">
          <el-input v-model="taskForm.remark" type="textarea" placeholder="请输入任务描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="taskOpen = false">取消</el-button>
        <el-button type="primary" @click="submitTaskForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFirmware, getFirmware, delFirmware, addFirmware, updateFirmware, exportFirmware } from '@/api/iot/firmware'
import { listFirmwareUpgradeTask, addFirmwareUpgradeTask, delFirmwareUpgradeTask, startUpgradeTask, cancelUpgradeTask } from '@/api/iot/firmware'
import { listDevice } from '@/api/iot/device'

export default {
  name: 'Firmware',
  components: {
    RightToolbar: () => import('@/components/RightToolbar')
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 固件升级任务遮罩层
      taskLoading: true,
      // 选中数组
      ids: [],
      // 任务选中数组
      taskIds: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 任务非单个禁用
      taskSingle: true,
      // 任务非多个禁用
      taskMultiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 任务总条数
      taskTotal: 0,
      // 固件列表
      firmwareList: [],
      // 固件升级任务列表
      upgradeTaskList: [],
      // 设备列表
      deviceList: [],
      // 设备分组列表
      deviceGroups: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 是否显示任务弹出层
      taskOpen: false,
      // 上传文件列表
      fileList: [],
      // 上传路径
      uploadUrl: process.env.VUE_APP_BASE_API + '/iot/firmware/upload',
      // 分页参数
      currentPage: 1,
      taskCurrentPage: 1,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        firmwareName: null,
        firmwareVersion: null,
        deviceType: null,
        status: null
      },
      // 任务查询参数
      taskQueryParams: {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        firmwareVersion: null,
        status: null
      },
      // 表单参数
      form: {},
      // 任务表单参数
      taskForm: {},
      // 表单校验
      rules: {
        firmwareName: [
          { required: true, message: '固件名称不能为空', trigger: 'blur' }
        ],
        firmwareVersion: [
          { required: true, message: '固件版本不能为空', trigger: 'blur' }
        ],
        deviceType: [
          { required: true, message: '设备类型不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '固件状态不能为空', trigger: 'change' }
        ]
      },
      // 任务表单校验
      taskRules: {
        taskName: [
          { required: true, message: '任务名称不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getTaskList()
    this.getDeviceList()
    this.getDeviceGroups()
  },
  methods: {
    /** 查询固件列表 */
    getList() {
      this.loading = true
      listFirmware(this.queryParams).then(response => {
        this.firmwareList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 查询固件升级任务列表 */
    getTaskList() {
      this.taskLoading = true
      listFirmwareUpgradeTask(this.taskQueryParams).then(response => {
        this.upgradeTaskList = response.rows
        this.taskTotal = response.total
        this.taskLoading = false
      })
    },
    /** 查询设备列表 */
    getDeviceList() {
      listDevice({ pageNum: 1, pageSize: 100 }).then(response => {
        this.deviceList = response.rows
      })
    },
    /** 获取设备分组列表 */
    getDeviceGroups() {
      // 这里需要调用设备分组的API，暂时模拟数据
      this.deviceGroups = [
        { groupId: 1, groupName: '默认分组' },
        { groupId: 2, groupName: '测试分组' }
      ]
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.$refs.queryForm.resetFields()
      this.handleQuery()
    },
    /** 任务搜索按钮操作 */
    handleTaskQuery() {
      this.taskQueryParams.pageNum = 1
      this.getTaskList()
    },
    /** 任务重置按钮操作 */
    resetTaskQuery() {
      this.$refs.taskQueryForm.resetFields()
      this.handleTaskQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.firmwareId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 任务多选框选中数据
    handleTaskSelectionChange(selection) {
      this.taskIds = selection.map(item => item.taskId)
      this.taskSingle = selection.length !== 1
      this.taskMultiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加固件'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const firmwareId = row.firmwareId || this.ids
      getFirmware(firmwareId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改固件'
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const firmwareIds = row.firmwareId || this.ids
      this.$confirm('是否确认删除固件编号为' + firmwareIds + '的数据项？', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return delFirmware(firmwareIds)
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {
        // 取消删除操作，不执行任何操作
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('iot/firmware/export', this.queryParams, `firmware_${new Date().getTime()}.xlsx`)
    },
    /** 上传固件操作 */
    handleUpload() {
      // 实现固件上传逻辑
      this.$message.info('固件上传功能待实现')
    },
    /** 发布固件操作 */
    handlePublish(row) {
      // 实现固件发布逻辑
      this.$message.info('固件发布功能待实现')
    },
    /** 创建升级任务操作 */
    handleCreateUpgradeTask(row) {
      this.form = { ...row }
      this.taskOpen = true
      this.taskForm = {
        taskName: '',
        targetDevices: [],
        targetGroupId: null,
        remark: ''
      }
    },
    /** 开始升级任务操作 */
    handleStartUpgradeTask(taskId) {
      startUpgradeTask(taskId).then(() => {
        this.getTaskList()
        this.$message.success('升级任务已开始')
      })
    },
    /** 取消升级任务操作 */
    handleCancelUpgradeTask(taskId) {
      cancelUpgradeTask(taskId).then(() => {
        this.getTaskList()
        this.$message.success('升级任务已取消')
      })
    },
    /** 删除升级任务操作 */
    handleDeleteUpgradeTask(taskId) {
      delFirmwareUpgradeTask(taskId).then(() => {
        this.getTaskList()
        this.$message.success('升级任务已删除')
      })
    },
    /** 提交表单 */
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.firmwareId != null) {
            updateFirmware(this.form).then(response => {
              this.$message.success('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addFirmware(this.form).then(response => {
              this.$message.success('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 提交升级任务表单 */
    submitTaskForm() {
      this.$refs.taskForm.validate((valid) => {
        if (valid) {
          const taskData = {
            ...this.taskForm,
            firmwareId: this.form.firmwareId,
            firmwareVersion: this.form.firmwareVersion
          }
          addFirmwareUpgradeTask(taskData).then(response => {
            this.$message.success('升级任务创建成功')
            this.taskOpen = false
            this.getTaskList()
          })
        }
      })
    },
    /** 重置表单 */
    reset() {
      this.form = {
        firmwareId: null,
        firmwareName: null,
        firmwareVersion: null,
        deviceType: null,
        description: null,
        firmwarePath: null,
        status: '0',
        fileSize: null,
        publishTime: null
      }
      this.$refs.form.resetFields()
    },
    /** 格式化文件大小 */
    formatFileSize(bytes) {
      if (bytes === 0) return '0 B'
      const k = 1024
      const sizes = ['B', 'KB', 'MB', 'GB', 'TB']
      const i = Math.floor(Math.log(bytes) / Math.log(k))
      return (bytes / Math.pow(k, i)).toFixed(2) + ' ' + sizes[i]
    },
    /** 分页大小改变 */
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      this.getList()
    },
    /** 当前页码改变 */
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      this.getList()
    },
    /** 任务分页大小改变 */
    handleTaskSizeChange(val) {
      this.taskQueryParams.pageSize = val
      this.getTaskList()
    },
    /** 任务当前页码改变 */
    handleTaskCurrentChange(val) {
      this.taskQueryParams.pageNum = val
      this.getTaskList()
    },
    /** 取消按钮 */
    cancel() {
      this.open = false
      this.taskOpen = false
      this.reset()
    },
    /** 处理上传成功 */
    handleUploadSuccess(response) {
      this.form.firmwarePath = response.data.filePath
      this.form.fileSize = response.data.fileSize
      this.$message.success('文件上传成功')
    },
    /** 处理上传错误 */
    handleUploadError() {
      this.$message.error('文件上传失败')
    }
  }
}