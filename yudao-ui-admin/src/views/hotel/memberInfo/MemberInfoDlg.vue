<template>
  <div class="member-list">
    <el-dialog
      title="请选择预订会员"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-table v-loading="loading"
                :data="list"
                highlight-current-row
                @row-dblclick="selectMemInfo"
      >
        <el-table-column label="姓名" align="center" prop="name"/>
        <el-table-column label="手机号" align="center" prop="phone"/>
        <el-table-column label="证件号" align="center" prop="cardNo"/>
        <el-table-column label="会员类型" align="center" prop="levelName"/>
      </el-table>

      <!-- 分页组件 -->
      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                  @pagination="getMemberList"
      />

      <span slot="footer" class="dialog-footer">
    <el-button @click="cancelOperation">取 消</el-button>
    <el-button type="primary" @click="selectMemInfo">确 定</el-button>
  </span>
    </el-dialog>

  </div>

</template>
<script>
import { getMemberInfoPage, getMemberInfoPageByName } from '@/api/hotel/memberInfo'

export default {
  name: 'MemberInfo',
  props: {
    memberName: {
      type: String,
      required: true
    },
    dialogVisible: {
      type: Boolean,
      required: true
    }

  },

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
        memberName: null
      }
    }
  },
  mounted() {
    this.getMemberList()
  },
  methods: {

    /** 查询会员列表 */
    getMemberList() {
      this.loading = true

      getMemberInfoPageByName({
        name: this.memberName,
        pageNo: this.queryParams.pageNo,
        pageSize: this.queryParams.pageSize
      }).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },

    cancelOperation() {
      this.$emit('cancel')
    },
    selectMemInfo(row) {
      this.$emit('fillingMemInfo', row)
      this.$emit('cancel')
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {
        })
    }
  }
}
</script>
