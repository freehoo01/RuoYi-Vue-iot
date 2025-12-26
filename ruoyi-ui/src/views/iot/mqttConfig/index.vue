<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="配置名称" prop="configName">
        <el-input
          v-model="queryParams.configName"
          placeholder="请输入配置名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务地址" prop="serverUrl">
        <el-input
          v-model="queryParams.serverUrl"
          placeholder="请输入服务地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="连接状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择连接状态" clearable>
          <el-option label="已连接" value="0" />
          <el-option label="未连接" value="1" />
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
          v-hasPermi="['iot:mqtt-config:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['iot:mqtt-config:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['iot:mqtt-config:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mqttConfigList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="配置ID" prop="configId" width="80" align="center" />
      <el-table-column label="配置名称" prop="configName" align="center" />
      <el-table-column label="服务地址" prop="serverUrl" align="center" />
      <el-table-column label="端口" prop="port" width="80" align="center" />
      <el-table-column label="客户端ID" prop="clientId" align="center" />
      <el-table-column label="用户名" prop="username" align="center" />
      <el-table-column label="连接状态" prop="status" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '0' ? 'success' : 'danger'">
            {{ scope.row.status === '0' ? '已连接' : '未连接' }}</el-tag>
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
            v-hasPermi="['iot:mqtt-config:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row.configId)"
            v-hasPermi="['iot:mqtt-config:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-refresh"
            @click="handleConnect(scope.row)"
            v-if="scope.row.status === '1'"
            v-hasPermi="['iot:mqtt-config:connect']"
          >连接</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-close"
            @click="handleDisconnect(scope.row)"
            v-if="scope.row.status === '0'"
            v-hasPermi="['iot:mqtt-config:disconnect']"
          >断开</el-button>
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

    <!-- 添加或修改MQTT配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="配置名称" prop="configName">
          <el-input v-model="form.configName" placeholder="请输入配置名称" />
        </el-form-item>
        <el-form-item label="服务地址" prop="serverUrl">
          <el-input v-model="form.serverUrl" placeholder="请输入服务地址" />
        </el-form-item>
        <el-form-item label="端口" prop="port">
          <el-input v-model="form.port" type="number" placeholder="请输入端口" />
        </el-form-item>
        <el-form-item label="客户端ID" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户端ID" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item label="主题前缀" prop="topicPrefix">
          <el-input v-model="form.topicPrefix" placeholder="请输入主题前缀" />
        </el-form-item>
        <el-form-item label="连接状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择连接状态">
            <el-option label="已连接" value="0" />
            <el-option label="未连接" value="1" />
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
  </div>
</template>

<script>
import { listMqttConfig, getMqttConfig, delMqttConfig, addMqttConfig, updateMqttConfig, exportMqttConfig } from '@/api/iot/mqttConfig'

export default {
  name: 'MqttConfig',
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
      // MQTT配置列表
      mqttConfigList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 分页参数
      currentPage: 1,
      pageSizes: [10, 20, 30, 40, 50],
      pageSize: 10,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        configName: null,
        serverUrl: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        configName: [
          { required: true, message: '配置名称不能为空', trigger: 'blur' }
        ],
        serverUrl: [
          { required: true, message: '服务地址不能为空', trigger: 'blur' }
        ],
        port: [
          { required: true, message: '端口不能为空', trigger: 'blur' }
        ],
        clientId: [
          { required: true, message: '客户端ID不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询MQTT配置列表 */
    getList() {
      this.loading = true
      listMqttConfig(this.queryParams).then(response => {
        this.mqttConfigList = response.rows
        this.total = response.total
        this.loading = false
      })
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
      this.ids = selection.map(item => item.configId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加MQTT配置'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const configId = row.configId || this.ids
      getMqttConfig(configId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改MQTT配置'
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const configIds = row.configId || this.ids
      this.$confirm('是否确认删除MQTT配置编号为' + configIds + '的数据项？', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return delMqttConfig(configIds)
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {
        // 取消删除操作，不执行任何操作
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('iot/mqttConfig/export', this.queryParams, `mqtt_config_${new Date().getTime()}.xlsx`)
    },
    /** 连接MQTT操作 */
    handleConnect(row) {
      // 实现MQTT连接逻辑
      this.$message.info('MQTT连接功能待实现')
    },
    /** 断开MQTT连接操作 */
    handleDisconnect(row) {
      // 实现MQTT断开连接逻辑
      this.$message.info('MQTT断开连接功能待实现')
    },
    /** 提交表单 */
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.configId != null) {
            updateMqttConfig(this.form).then(response => {
              this.$message.success('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addMqttConfig(this.form).then(response => {
              this.$message.success('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 重置表单 */
    reset() {
      this.form = {
        configId: null,
        configName: null,
        serverUrl: null,
        port: null,
        clientId: null,
        username: null,
        password: null,
        topicPrefix: null,
        status: '1',
        remark: null
      }
      this.$refs.form.resetFields()
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
    /** 取消按钮 */
    cancel() {
      this.open = false
      this.reset()
    }
  }
}