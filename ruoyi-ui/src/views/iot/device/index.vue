<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
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
      <el-form-item label="设备状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择设备状态" clearable>
          <el-option label="在线" value="0" />
          <el-option label="离线" value="1" />
          <el-option label="故障" value="2" />
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
          v-hasPermi="['iot:device:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple" v-hasPermi="['iot:device:remove']"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['iot:device:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-view"
          size="mini"
          @click="handleViewData"
          :disabled="multiple"
        >查看数据</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备ID" prop="deviceId" width="80" align="center" />
      <el-table-column label="设备名称" prop="deviceName" align="center" />
      <el-table-column label="设备唯一标识" prop="deviceCode" align="center" />
      <el-table-column label="设备类型" prop="deviceType" align="center" />
      <el-table-column label="设备分组" prop="groupName" align="center" />
      <el-table-column label="设备状态" prop="status" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '0' ? 'success' : scope.row.status === '1' ? 'danger' : 'warning'">
            {{ scope.row.status === '0' ? '在线' : scope.row.status === '1' ? '离线' : '故障' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" width="180" align="center" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['iot:device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row.deviceId)"
            v-hasPermi="['iot:device:remove']"
          >删除</el-button>
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

    <!-- 添加或修改设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备唯一标识" prop="deviceCode">
          <el-input v-model="form.deviceCode" placeholder="请输入设备唯一标识" />
        </el-form-item>
        <el-form-item label="设备类型" prop="deviceType">
          <el-input v-model="form.deviceType" placeholder="请输入设备类型" />
        </el-form-item>
        <el-form-item label="设备分组" prop="groupId">
          <el-select v-model="form.groupId" placeholder="请选择设备分组">
            <el-option
              v-for="group in deviceGroups"
              :key="group.groupId"
              :label="group.groupName"
              :value="group.groupId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择设备状态">
            <el-option label="在线" value="0" />
            <el-option label="离线" value="1" />
            <el-option label="故障" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>

    <!-- 设备数据对话框 -->
    <el-dialog :title="'设备数据 - ' + form.deviceName" :visible.sync="dataOpen" width="800px" append-to-body>
      <el-tabs v-model="activeName">
        <el-tab-pane label="实时数据" name="realtime">
          <el-row v-loading="realtimeLoading" :gutter="10" class="mb10">
            <el-col :span="8" v-for="(item, index) in realtimeData" :key="index">
              <el-card shadow="hover" class="data-card">
                <div class="data-item">
                  <div class="data-label">{{ item.dataType }}</div>
                  <div class="data-value">{{ item.dataValue }}{{ item.unit }}</div>
                  <div class="data-status" :class="{'status-normal': item.status === '0', 'status-error': item.status === '1'}">
                    {{ item.status === '0' ? '正常' : '异常' }}
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="历史数据" name="history">
          <el-form :model="historyQuery" ref="historyForm" size="small" :inline="true" label-width="68px" class="mb10">
            <el-form-item label="数据类型" prop="dataType">
              <el-select v-model="historyQuery.dataType" placeholder="请选择数据类型" clearable>
                <el-option
                  v-for="type in dataTypes"
                  :key="type"
                  :label="type"
                  :value="type"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker
                v-model="historyQuery.startTime"
                type="datetime"
                placeholder="选择开始时间"
                clearable
                value-format="yyyy-MM-dd HH:mm:ss"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker
                v-model="historyQuery.endTime"
                type="datetime"
                placeholder="选择结束时间"
                clearable
                value-format="yyyy-MM-dd HH:mm:ss"
              ></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleHistoryQuery">查询</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetHistoryQuery">重置</el-button>
            </el-form-item>
          </el-form>
          <el-table v-loading="historyLoading" :data="historyData">
            <el-table-column label="数据类型" prop="dataType" align="center" />
            <el-table-column label="数据值" prop="dataValue" align="center" />
            <el-table-column label="单位" prop="unit" align="center" />
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-tag :type="scope.row.status === '0' ? 'success' : 'danger'">
                  {{ scope.row.status === '0' ? '正常' : '异常' }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="采集时间" prop="collectTime" width="180" align="center" />
          </el-table>
          <el-pagination
            v-show="historyTotal>0"
            :total="historyTotal"
            :page.sync="historyQuery.pageNum"
            :limit.sync="historyQuery.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleHistorySizeChange"
            @current-change="handleHistoryCurrentChange"
          />
        </el-tab-pane>
      </el-tabs>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dataOpen = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDevice, getDevice, delDevice, addDevice, updateDevice, exportDevice, getLatestDeviceData } from '@/api/iot/device'
import { getDeviceHistoryData } from '@/api/iot/deviceData'

export default {
  name: 'Device',
  components: {
    RightToolbar: () => import('@/components/RightToolbar')
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 设备列表
      deviceList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 设备分组列表
      deviceGroups: [],
      // 实时数据
      realtimeData: [],
      // 实时数据加载
      realtimeLoading: false,
      // 历史数据
      historyData: [],
      // 历史数据加载
      historyLoading: false,
      // 历史数据总条数
      historyTotal: 0,
      // 数据类型列表
      dataTypes: [],
      // 活跃标签
      activeName: 'realtime',
      // 设备数据对话框
      dataOpen: false,
      // 分页参数
      currentPage: 1,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceName: null,
        deviceType: null,
        status: null
      },
      // 表单参数
      form: {},
      // 历史查询参数
      historyQuery: {
        pageNum: 1,
        pageSize: 10,
        deviceCode: null,
        dataType: null,
        startTime: null,
        endTime: null
      },
      // 表单校验
      rules: {
        deviceName: [
          { required: true, message: '设备名称不能为空', trigger: 'blur' }
        ],
        deviceCode: [
          { required: true, message: '设备唯一标识不能为空', trigger: 'blur' }
        ],
        deviceType: [
          { required: true, message: '设备类型不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '设备状态不能为空', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getDeviceGroups()
  },
  methods: {
    /** 查询设备列表 */
    getList() {
      this.loading = true
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows
        this.total = response.total
        this.loading = false
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加设备'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const deviceId = row.deviceId || this.ids
      getDevice(deviceId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改设备'
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deviceIds = row.deviceId || this.ids
      this.$confirm('是否确认删除设备编号为"' + deviceIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delDevice(deviceIds)
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {
        this.$message.error('取消删除')
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.$confirm('是否确认导出所有设备数据?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return exportDevice(this.queryParams)
      }).then(response => {
        this.download(response.msg)
      }).catch(() => {
        this.$message.error('取消导出')
      })
    },
    /** 查看数据按钮操作 */
    handleViewData(row) {
      const deviceId = row.deviceId || this.ids
      getDevice(deviceId).then(response => {
        this.form = response.data
        this.dataOpen = true
        this.getRealtimeData()
        this.historyQuery.deviceCode = this.form.deviceCode
        this.handleHistoryQuery()
      })
    },
    /** 获取实时数据 */
    getRealtimeData() {
      this.realtimeLoading = true
      getLatestDeviceData(this.form.deviceCode).then(response => {
        this.realtimeData = response.data
        // 提取数据类型列表
        this.dataTypes = [...new Set(this.realtimeData.map(item => item.dataType))]
        this.realtimeLoading = false
      })
    },
    /** 查询历史数据 */
    handleHistoryQuery() {
      this.historyLoading = true
      getDeviceHistoryData(this.historyQuery.deviceCode, this.historyQuery.dataType, this.historyQuery.startTime, this.historyQuery.endTime).then(response => {
        this.historyData = response.data
        this.historyTotal = response.data.length
        this.historyLoading = false
      })
    },
    /** 重置历史数据查询 */
    resetHistoryQuery() {
      this.$refs.historyForm.resetFields()
      this.handleHistoryQuery()
    },
    /** 表单重置 */
    reset() {
      this.form = {
        deviceId: null,
        deviceName: null,
        deviceCode: null,
        deviceType: null,
        groupId: null,
        status: null,
        remark: null
      }
      this.$refs.form.resetFields()
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.deviceId != null) {
            updateDevice(this.form).then(response => {
              this.$message.success('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addDevice(this.form).then(response => {
              this.$message.success('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 取消按钮 */
    cancel() {
      this.open = false
      this.reset()
    },
    /** 分页大小改变 */
    handleSizeChange(val) {
      this.pageSize = val
      this.loading = true
      this.getList()
    },
    /** 分页当前页改变 */
    handleCurrentChange(val) {
      this.currentPage = val
      this.loading = true
      this.getList()
    },
    /** 历史数据分页大小改变 */
    handleHistorySizeChange(val) {
      this.historyQuery.pageSize = val
      this.historyLoading = true
      this.handleHistoryQuery()
    },
    /** 历史数据分页当前页改变 */
    handleHistoryCurrentChange(val) {
      this.historyQuery.pageNum = val
      this.historyLoading = true
      this.handleHistoryQuery()
    }
  }
}
</script>

<style scoped>
.data-card {
  margin-bottom: 10px;
}

.data-item {
  text-align: center;
  padding: 20px 0;
}

.data-label {
  font-size: 14px;
  color: #606266;
  margin-bottom: 10px;
}

.data-value {
  font-size: 32px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 10px;
}

.data-status {
  font-size: 12px;
  padding: 2px 8px;
  border-radius: 10px;
  display: inline-block;
}

.status-normal {
  background-color: #f0f9eb;
  color: #67c23a;
}

.status-error {
  background-color: #fef0f0;
  color: #f56c6c;
}
</style>