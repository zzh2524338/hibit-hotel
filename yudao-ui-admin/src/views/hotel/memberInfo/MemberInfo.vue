<template>
  <div class="member-list">
    <el-dialog
      title="请选择预订会员"
      :visible.sync="dialogVisible"
      width="30%">
      <el-table v-loading="loading" :data="list">
        <el-table-column label="姓名" align="center" prop="name"/>
        <el-table-column label="手机号" align="center" prop="mobile"/>
        <el-table-column label="证件号" align="center" prop="cardNo"/>
        <el-table-column label="会员类型" align="center" prop="memberTypeName"/>
      </el-table>

      <!-- 分页组件 -->
      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                  @pagination="getMemberList"/>

    </el-dialog>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
  </div>

</template>
<script>
import {getMemberInfoPage} from "@/api/hotel/memberInfo";

export default {
  name: "MemberInfo",
  data() {
    return {
      // 遮罩层
      loading: true,
      dialogVisible: false,

      total: null,
      // 会员列表
      list: null,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        memberName: null,
      }
    };
  },
  created() {
    this.getMemberList();
  },
  methods: {

    /** 查询会员列表 */
    getMemberList() {
      this.loading = true;

      getMemberInfoPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      })
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
      .then(_ => {
        done();
      })
      .catch(_ => {
      });
    }
  }
};
</script>
