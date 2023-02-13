<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="房型编号" prop="roomTypeId">
        <el-input v-model="queryParams.roomTypeId" placeholder="请输入房型编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="房价类型编号" prop="roomRateTypeId">
        <el-input v-model="queryParams.roomRateTypeId" placeholder="请输入房价类型编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="现在折扣价格" prop="roomRate">
        <el-input v-model="queryParams.roomRate" placeholder="请输入现在折扣价格" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="门市价格" prop="proRate">
        <el-input v-model="queryParams.proRate" placeholder="请输入门市价格" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="生效日期" prop="accDate">
        <el-date-picker v-model="queryParams.accDate" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['hotel:room-type-rate:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['hotel:room-type-rate:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label=" 编号" align="center" prop="id" />
      <el-table-column label="房型编号" align="center" prop="roomTypeId" />
      <el-table-column label="房价类型编号" align="center" prop="roomRateTypeId" />
      <el-table-column label="现在折扣价格" align="center" prop="roomRate" />
      <el-table-column label="门市价格" align="center" prop="proRate" />
      <el-table-column label="生效日期" align="center" prop="accDate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.accDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['hotel:room-type-rate:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['hotel:room-type-rate:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="房型编号" prop="roomTypeId">
          <el-input v-model="form.roomTypeId" placeholder="请输入房型编号" />
        </el-form-item>
        <el-form-item label="房价类型编号" prop="roomRateTypeId">
          <el-input v-model="form.roomRateTypeId" placeholder="请输入房价类型编号" />
        </el-form-item>
        <el-form-item label="现在折扣价格" prop="roomRate">
          <el-input v-model="form.roomRate" placeholder="请输入现在折扣价格" />
        </el-form-item>
        <el-form-item label="门市价格" prop="proRate">
          <el-input v-model="form.proRate" placeholder="请输入门市价格" />
        </el-form-item>
        <el-form-item label="生效日期" prop="accDate">
          <el-date-picker clearable v-model="form.accDate" type="date" value-format="timestamp" placeholder="选择生效日期" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createRoomTypeRate, updateRoomTypeRate, deleteRoomTypeRate, getRoomTypeRate, getRoomTypeRatePage, exportRoomTypeRateExcel } from "@/api/hotel/roomTypeRate";

export default {
  name: "RoomTypeRate",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 房型价格列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        roomTypeId: null,
        roomRateTypeId: null,
<<<<<<< Updated upstream
        roomRate: null,
        proRate: null,
=======
>>>>>>> Stashed changes
        accDate: [],
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        roomTypeId: [{ required: true, message: "房型编号不能为空", trigger: "blur" }],
        roomRateTypeId: [{ required: true, message: "房价类型编号不能为空", trigger: "blur" }],
        roomRate: [{ required: true, message: "现在折扣价格不能为空", trigger: "blur" }],
        proRate: [{ required: true, message: "门市价格不能为空", trigger: "blur" }],
        accDate: [{ required: true, message: "生效日期不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getRoomTypeRatePage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        roomTypeId: undefined,
        roomRateTypeId: undefined,
        roomRate: undefined,
        proRate: undefined,
        accDate: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加房型价格";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getRoomTypeRate(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改房型价格";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateRoomTypeRate(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createRoomTypeRate(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除房型价格编号为"' + id + '"的数据项?').then(function() {
          return deleteRoomTypeRate(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有房型价格数据项?').then(() => {
          this.exportLoading = true;
          return exportRoomTypeRateExcel(params);
        }).then(response => {
          this.$download.excel(response, '房型价格.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
