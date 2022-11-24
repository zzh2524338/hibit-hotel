<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单号" prop="uuid">
        <el-input v-model="queryParams.uuid" placeholder="请输入订单号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="预定人" prop="orderGuestName">
        <el-input v-model="queryParams.orderGuestName" placeholder="请输入预定人z电话" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="联系人" prop="contactPerson">
        <el-input v-model="queryParams.contactPerson" placeholder="请输入联系人" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="联系电话" prop="contactNumber">
        <el-input v-model="queryParams.contactNumber" placeholder="请输入联系电话" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="房间信息" prop="roomInfo">
        <el-input v-model="queryParams.roomInfo" placeholder="请输入房间信息" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="会员信息快照" prop="memberInfo">
        <el-input v-model="queryParams.memberInfo" placeholder="请输入会员信息快照" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="订单来源" prop="guestSourceId">
        <el-input v-model="queryParams.guestSourceId" placeholder="请输入订单来源" clearable
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable size="small">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="入住时间" prop="checkInTime">
        <el-date-picker clearable v-model="queryParams.checkInTime" type="date" value-format="yyyy-MM-dd"
                        placeholder="选择入住时间"/>
      </el-form-item>
      <el-form-item label="离店时间" prop="checkOutTime">
        <el-date-picker clearable v-model="queryParams.checkOutTime" type="date" value-format="yyyy-MM-dd"
                        placeholder="选择离店时间"/>
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
      <el-table-column label="订单号" align="center" prop="uuid"/>
      <el-table-column label="预定人名称" align="center" prop="orderGuestName"/>
      <el-table-column label="联系人" align="center" prop="contactPerson"/>
      <el-table-column label="联系电话" align="center" prop="contactNumber"/>
      <el-table-column label="房间信息" align="center" prop="roomInfo"/>
      <el-table-column label="会员信息快照" align="center" prop="memberInfo"/>
      <el-table-column label="订单来源" align="center" prop="guestSourceId"/>
      <el-table-column label="订单状态" align="center" prop="orderStatus"/>
      <el-table-column label="入住时间" align="center" prop="checkInTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkInTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="离店时间" align="center" prop="checkOutTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkOutTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
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
    <el-dialog :title="title" :visible.sync="open" width="600px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="预定人" prop="orderGuestName">
          <el-input v-model="form.orderGuestName" placeholder="请输入预定人姓名" size="40px"/>
        </el-form-item>

        <el-row>
          <el-col :span="10">
            <el-form-item label="联系人" prop="contactPerson">
              <el-input v-model="form.contactPerson" placeholder="联系人姓名"/>
            </el-form-item>
          </el-col>
          <el-col :span="14">
            <el-form-item label="联系电话" prop="contactNumber">
              <el-input v-model="form.contactNumber" placeholder="请输入联系电话"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="中介号" prop="intermediaryNumber">
          <el-input v-model="form.intermediaryNumber" placeholder="请输入中介号"/>
        </el-form-item>

        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>房间信息</span>
          </div>

          <el-row>
            <el-col :span="8">
              <el-form-item label="类型" prop="roomType">
                <el-select v-model="form.roomType" filterable placeholder="请选择房间类型表" clearable size="small">
                  <el-option
                    v-for="item in roomTypeList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="原价" prop="originalPrice">
                <span v-if="form.roomType" v-model="form.originalPrice">{{ getOriginalPrice }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="房间号" prop="roomNo">
                <el-input v-model="form.roomNo" placeholder="请输入房间号"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>

        <!--        <el-form-item label="房间信息" prop="roomInfo">-->
        <!--          <el-input v-model="form.roomInfo" placeholder="请输入房间信息" />-->
        <!--        </el-form-item>-->
        <el-form-item label="会员信息快照" prop="memberInfo">
          <el-input v-model="form.memberInfo" placeholder="请输入会员信息快照"/>
        </el-form-item>
        <el-form-item label="订单来源" prop="guestSourceId">
          <el-input v-model="form.guestSourceId" placeholder="请输入订单来源"/>
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注信息"/>
        </el-form-item>

        <el-form-item label="折后实际价格" prop="discountPrice">
          <el-input v-model="form.discountPrice" placeholder="请输入折后实际价格"/>
        </el-form-item>
        <el-form-item label="实际付款" prop="actuallyPaid">
          <el-input v-model="form.actuallyPaid" placeholder="请输入实际付款"/>
        </el-form-item>
        <el-form-item label="订单状态" prop="orderStatus">
          <el-radio-group v-model="form.orderStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="入住时间" prop="checkInTime">
          <el-date-picker clearable v-model="form.checkInTime" type="date" value-format="timestamp"
                          placeholder="选择入住时间"/>
        </el-form-item>
        <el-form-item label="客人信息" prop="guestInfo">
          <el-input v-model="form.guestInfo" placeholder="请输入客人信息"/>
        </el-form-item>
        <el-form-item label="离店时间" prop="checkOutTime">
          <el-date-picker clearable v-model="form.checkOutTime" type="date" value-format="timestamp"
                          placeholder="选择离店时间"/>
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
  updateOrderInfo,
  deleteOrderInfo,
  getOrderInfo,
  getOrderInfoPage,
  exportOrderInfoExcel
} from "@/api/hotel/orderInfo";
import roomInfo from "@/views/hotel/roomInfo";
import {getRoomTypeList} from "@/api/hotel/roomType";

export default {
  name: "OrderInfo",
  components: {},
  props: {
    originalPrice: {
      type: [Number, String],
      default: 0
    }
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
      // 订单列表
      list: [],
      // 房间类型列表
      roomTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        uuid: null,
        orderGuestName: null,
        contactPerson: null,
        contactNumber: null,
        roomInfo: null,
        memberInfo: null,
        guestSourceId: null,
        orderStatus: null,
        checkInTime: null,
        checkOutTime: null,
        roomType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderGuestName: [{required: true, message: "预定人名称不能为空", trigger: "blur"}],
        contactPerson: [{required: true, message: "联系人不能为空", trigger: "blur"}],
        contactNumber: [{required: true, message: "联系电话不能为空", trigger: "blur"}],

        roomType: [{required: true, message: "房间类型不能为空", trigger: "blur"}],
        roomInfo: [{required: true, message: "房间信息不能为空", trigger: "blur"}],
        memberInfo: [{required: true, message: "会员信息快照不能为空", trigger: "blur"}],
        guestSourceId: [{required: true, message: "订单来源不能为空", trigger: "blur"}],
        originalPrice: [{required: true, message: "原价不能为空", trigger: "blur"}],
        discountPrice: [{required: true, message: "折后实际价格不能为空", trigger: "blur"}],
        actuallyPaid: [{required: true, message: "实际付款不能为空", trigger: "blur"}],
        orderStatus: [{required: true, message: "订单状态不能为空", trigger: "blur"}],
        checkInTime: [{required: true, message: "入住时间不能为空", trigger: "blur"}],
        guestInfo: [{required: true, message: "客人信息不能为空", trigger: "blur"}],
        checkOutTime: [{required: true, message: "离店时间不能为空", trigger: "blur"}],
      }
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.getRoomTypeList();
  },
  computed: {
    getOriginalPrice() {
      return this.roomTypeList.find(roomType => roomType.id === this.form.roomType).price
    }
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
    getRoomTypeList() {
      this.loading = true;

      getRoomTypeList().then(response => {
        this.roomTypeList = response.data;
        this.loading = false;
      })
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
        orderGuestName: undefined,
        contactPerson: undefined,
        contactNumber: undefined,
        intermediaryNumber: undefined,

        // 房间信息
        roomTypeId: undefined,
        roomTyeName: undefined,
        roomNo: undefined,
        // 入住客人信息
        guestName: undefined,
        guestNumber: undefined,
        guestIdCard: undefined,
        // 身份证地址
        guestAddress: undefined,
        // 身份证签发机关
        guestIssuingAuthority: undefined,

        memberInfo: undefined,
        guestSourceId: undefined,
        remark: undefined,
        originalPrice: undefined,
        discountPrice: undefined,
        actuallyPaid: undefined,
        orderStatus: undefined,
        checkInTime: undefined,
        guestInfo: undefined,
        checkOutTime: undefined,
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
