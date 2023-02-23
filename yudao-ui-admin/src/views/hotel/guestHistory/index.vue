<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客人" prop="guestId">
        <el-input v-model="queryParams.guestId" placeholder="请输入客人" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="客人姓名" prop="guestName">
        <el-input v-model="queryParams.guestName" placeholder="请输入客人姓名" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="queryParams.phone" placeholder="请输入电话" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="证件编号" prop="cardNo">
        <el-input v-model="queryParams.cardNo" placeholder="请输入证件编号" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="会员编号" prop="memberId">
        <el-input v-model="queryParams.memberId" placeholder="请输入会员编号" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="最近一次入住" prop="latestCheckin">
        <el-date-picker clearable v-model="queryParams.latestCheckin" type="date" value-format="yyyy-MM-dd"
                        placeholder="选择最近一次入住"/>
      </el-form-item>
      <el-form-item label="入住次数" prop="checkinNum">
        <el-input v-model="queryParams.checkinNum" placeholder="请输入入住次数" clearable
                  @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['hotel:guest-history:create']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   :loading="exportLoading"
                   v-hasPermi="['hotel:guest-history:export']">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="客人姓名" align="center" prop="guestName"/>
      <el-table-column label="生日" align="center" prop="birthday"/>
      <el-table-column label="电话" align="center" prop="phone"/>
      <el-table-column label="性别" align="center" prop="gender">
        <template v-slot="scope">
          <dict-tag :type="DICT_TYPE.SYSTEM_USER_SEX" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="证件编号" align="center" prop="cardNo"/>
      <el-table-column label="地址" align="center" prop="address"/>
      <el-table-column label="会员编号" align="center" prop="memberId"/>
      <el-table-column label="最近一次入住" align="center" prop="latestCheckin" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.latestCheckin) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入住次数" align="center" prop="checkinNum"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['hotel:guest-history:update']">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['hotel:guest-history:delete']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客人姓名" prop="guestName">
          <el-input v-model="form.guestName" placeholder="请输入客人姓名"/>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker clearable v-model="form.birthday" type="date" value-format="timestamp"
                          placeholder="选择生日"/>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话"/>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择">
            <el-option v-for="dict in sexDictDatas" :key="parseInt(dict.value)" :label="dict.label"
                       :value="parseInt(dict.value)"/>
          </el-select>
        </el-form-item>
        <el-form-item label="证件类型" prop="cardType">
          <el-select v-model="form.cardType" placeholder="请选择证件类型">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.HOTEL_CARD_TYPE)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)"/>
          </el-select>
        </el-form-item>
        <el-form-item label="证件编号" prop="cardNo">
          <el-input v-model="form.cardNo" placeholder="请输入证件编号"/>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址"/>
        </el-form-item>
        <el-form-item label="会员编号" prop="memberId">
          <el-input v-model="form.memberId" placeholder="请输入会员编号"/>
        </el-form-item>
        <el-form-item label="最近一次入住" prop="latestCheckin">
          <el-date-picker clearable v-model="form.latestCheckin" type="date" value-format="timestamp"
                          placeholder="选择最近一次入住"/>
        </el-form-item>
        <el-form-item label="入住次数" prop="checkinNum">
          <el-input v-model="form.checkinNum" placeholder="请输入入住次数"/>
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
import {
  createGuestHistory,
  deleteGuestHistory,
  exportGuestHistoryExcel,
  getGuestHistory,
  getGuestHistoryPage,
  updateGuestHistory
} from "@/api/hotel/guestHistory";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";

export default {
  name: "GuestHistory",
  components: {},
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
      // 客史信息列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        guestId: null,
        guestName: null,
        phone: null,
        cardNo: null,
        memberId: null,
        memberTypeId: null,
        latestCheckin: null,
        checkinNum: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        guestId: [{required: true, message: "客人不能为空", trigger: "blur"}],
        guestName: [{required: true, message: "客人姓名不能为空", trigger: "blur"}],
        phone: [{required: true, message: "电话不能为空", trigger: "blur"}],
        gender: [{required: true, message: "性别不能为空", trigger: "blur"}],
        latestCheckin: [{required: true, message: "最近一次入住不能为空", trigger: "blur"}],
        checkinNum: [{required: true, message: "入住次数不能为空", trigger: "blur"}],
      },
      sexDictDatas: getDictDatas(DICT_TYPE.SYSTEM_USER_SEX),

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
      getGuestHistoryPage(this.queryParams).then(response => {
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
        guestId: undefined,
        guestName: undefined,
        birthday: undefined,
        phone: undefined,
        gender: undefined,
        cardNo: undefined,
        cardType: undefined,
        address: undefined,
        memberId: undefined,
        latestCheckin: undefined,
        checkinNum: undefined,
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
      this.title = "添加客史信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getGuestHistory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客史信息";
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
          updateGuestHistory(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createGuestHistory(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除客史信息编号为"' + id + '"的数据项?').then(function () {
        return deleteGuestHistory(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有客史信息数据项?').then(() => {
        this.exportLoading = true;
        return exportGuestHistoryExcel(params);
      }).then(response => {
        this.$download.excel(response, '客史信息.xls');
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
