<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable size="small">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="预定人名称" prop="bookingPerson">
        <el-input v-model="queryParams.bookingPerson" placeholder="请输入预定人名称" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="联系人" prop="contactName">
        <el-input v-model="queryParams.contactName" placeholder="请输入联系人" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="联系电话" prop="contactNumber">
        <el-input v-model="queryParams.contactNumber" placeholder="请输入联系电话" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="是否担保" prop="assure">
        <el-select v-model="queryParams.assure" placeholder="请选择是否担保" clearable size="small">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="担保时间" prop="assureTime">
        <el-date-picker v-model="queryParams.assureTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss"
                        type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"
                        :default-time="['00:00:00', '23:59:59']"/>
      </el-form-item>
      <el-form-item label="入住时间" prop="arrivalTime">
        <el-date-picker clearable v-model="queryParams.arrivalTime" type="date" value-format="yyyy-MM-dd"
                        placeholder="选择入住时间"/>
      </el-form-item>
      <el-form-item label="离店时间" prop="departTime">
        <el-date-picker clearable v-model="queryParams.departTime" type="date" value-format="yyyy-MM-dd"
                        placeholder="选择离店时间"/>
      </el-form-item>
      <el-form-item label="会员信息快照" prop="memberInfo">
        <el-input v-model="queryParams.memberInfo" placeholder="请输入会员信息快照" clearable
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
                   v-hasPermi="['hotel:order-info:create']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                   :loading="exportLoading"
                   v-hasPermi="['hotel:order-info:export']">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="订单状态" align="center" prop="status"/>
      <el-table-column label="预定人名称" align="center" prop="bookingPerson"/>
      <el-table-column label="联系人" align="center" prop="contactName"/>
      <el-table-column label="联系电话" align="center" prop="contactNumber"/>
      <el-table-column label="订单来源" align="center" prop="sourceId"/>
      <el-table-column label="是否担保" align="center" prop="assure"/>
      <el-table-column label="担保时间" align="center" prop="assureTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.assureTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="房价类型" align="center" prop="roomRateTypeId"/>
      <el-table-column label="入住时间" align="center" prop="arrivalTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.arrivalTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="离店时间" align="center" prop="departTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.departTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否爆单预定" align="center" prop="ignoreRoomStatus"/>
      <el-table-column label="备注信息" align="center" prop="remark"/>
      <el-table-column label="房间信息" align="center" prop="roomInfo"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['hotel:order-info:update']">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['hotel:order-info:delete']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="预定人" prop="bookingPerson">
              <el-input v-model="form.bookingPerson" placeholder="请输入预定人"/>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-button type="primary" @click="queryMemberInfo(form.id)">查询</el-button>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="联系人" prop="contactName">
              <el-input v-model="form.contactName" placeholder="请输入联系人"/>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="联系电话" prop="contactNumber">
              <el-input v-model="form.contactNumber" placeholder="请输入联系电话"/>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="中介号" prop="agencyCode">
              <el-input v-model="form.agencyCode" placeholder="请输入中介号"/>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="房价类型" prop="roomRateTypeId">
              <el-input v-model="form.roomRateTypeId" placeholder="请输入房价类型"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="入住时间" prop="arrivalTime">
              <el-date-picker clearable editable v-model="form.arrivalTime" type="date" value-format="timestamp"
                              placeholder="选择入住时间" size="small"/>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="离店时间" prop="departTime">
              <el-date-picker clearable v-model="form.departTime" type="date" value-format="timestamp"
                              placeholder="选择离店时间" size="small"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="是否担保" prop="assure" custom-class="assure-selector">
              <el-select v-model="queryParams.assure" placeholder="是否担保" clearable size="small">
                <el-option v-for="dict in this.getDictDatas(DICT_TYPE.HOTEL_ASSURE)"
                           :key="dict.value" :label="dict.label" :value="dict.value"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="担保时间" prop="assureTime">
              <el-time-select
                v-model="form.assureTime"
                :picker-options="{start: '00:30', step: '00:30', end: '23:00'}"
                placeholder="选择担保时间" size="small">
              </el-time-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="会员卡号" prop="memberId">
              <el-input v-model="form.memberId" disabled="true"/>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="会员类型" prop="memberTypeId">
              <el-input v-model="form.memberTypeId" disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="客人信息" prop="bookingGuestInfo">
          <el-input v-model="form.bookingGuestInfo" placeholder="请输入客人信息"/>
        </el-form-item>

        <el-form-item label="订单来源" prop="sourceId">
          <el-input v-model="form.sourceId" placeholder="请输入订单来源"/>
        </el-form-item>
        <el-form-item label="订单来源(小类)" prop="sourceSubId">
          <el-input v-model="form.sourceSubId" placeholder="请输入订单来源(小类)"/>
        </el-form-item>


        <el-form-item label="客源" prop="guestsSourceId">
          <el-input v-model="form.guestsSourceId" placeholder="请输入客源"/>
        </el-form-item>
        <el-form-item label="客源(小类)" prop="guestsSubSourceId">
          <el-input v-model="form.guestsSubSourceId" placeholder="请输入客源(小类)"/>
        </el-form-item>
        <el-form-item label="订单来源" prop="guestSourceId">
          <el-input v-model="form.guestSourceId" placeholder="请输入订单来源"/>
        </el-form-item>
        <el-form-item label="是否爆单预定" prop="ignoreRoomStatus">
          <el-radio-group v-model="form.ignoreRoomStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注信息"/>
        </el-form-item>
        <el-form-item label="房间信息" prop="roomInfo">
          <el-input v-model="form.roomInfo" placeholder="请输入房间信息"/>
        </el-form-item>
        <el-form-item label="会员信息快照" prop="memberInfo">
          <el-input v-model="form.memberInfo" placeholder="请输入会员信息快照"/>
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
  createOrderInfo,
  deleteOrderInfo,
  exportOrderInfoExcel,
  getOrderInfo,
  getOrderInfoPage,
  updateOrderInfo
} from "@/api/hotel/orderInfo";

export default {
  name: "OrderInfo",
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
      // 订单列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        status: null,
        bookingPerson: null,
        contactName: null,
        contactNumber: null,
        assure: "false",
        assureTime: [],
        arrivalTime: null,
        departTime: [],
        memberInfo: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        uuid: [{required: true, message: "订单号不能为空", trigger: "blur"}],
        status: [{required: true, message: "订单状态不能为空", trigger: "blur"}],
        bookingPerson: [{required: true, message: "预定人名称不能为空", trigger: "blur"}],
        contactName: [{required: true, message: "联系人不能为空", trigger: "blur"}],
        contactNumber: [{required: true, message: "联系电话不能为空", trigger: "blur"}],
        sourceId: [{required: true, message: "请选择订单来源", trigger: "blur"}],
        roomRateTypeId: [{required: true, message: "房价类型不能为空", trigger: "blur"}],
        arrivalTime: [{required: true, message: "入住时间不能为空", trigger: "blur"}],
        departTime: [{required: true, message: "离店时间不能为空", trigger: "blur"}],
        guestSourceId: [{required: true, message: "订单来源不能为空", trigger: "blur"}],
        ignoreRoomStatus: [{required: true, message: "是否爆单预定不能为空", trigger: "blur"}],
        roomInfo: [{required: true, message: "房间信息不能为空", trigger: "blur"}],
        memberInfo: [{required: true, message: "会员信息快照不能为空", trigger: "blur"}],
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
      getOrderInfoPage(this.queryParams).then(response => {
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
        uuid: undefined,
        status: undefined,
        bookingPerson: undefined,
        contactName: undefined,
        contactNumber: undefined,
        agencyCode: undefined,
        bookingGuestInfo: undefined,
        folioId: undefined,
        folioType: undefined,
        sourceId: undefined,
        sourceSubId: undefined,
        assure: undefined,
        assureTime: undefined,
        roomRateTypeId: undefined,
        arrivalTime: undefined,
        departTime: undefined,
        guestsSourceId: undefined,
        guestsSubSourceId: undefined,
        guestSourceId: undefined,
        ignoreRoomStatus: undefined,
        remark: undefined,
        roomInfo: undefined,
        memberInfo: undefined,
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
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getOrderInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单";
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
          updateOrderInfo(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createOrderInfo(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /**
     * 查询顾客信息，包括会员信息
     */
    queryMemberInfo(memberId) {
      this.open = true;
      this.title = "请选择预订会员";
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除订单编号为"' + id + '"的数据项?').then(function () {
        return deleteOrderInfo(id);
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
      this.$modal.confirm('是否确认导出所有订单数据项?').then(() => {
        this.exportLoading = true;
        return exportOrderInfoExcel(params);
      }).then(response => {
        this.$download.excel(response, '订单.xls');
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
<style>
.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: 140px;
}

.assure-selector {

}
</style>
