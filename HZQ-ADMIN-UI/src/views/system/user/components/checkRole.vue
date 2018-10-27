<template>
  <el-dialog
    :visible="dialogRoleVisible"
    :title="title"
    @close="closeRoleListDialog()"
    width="40%">
    <el-card style="border:none;box-shadow:none">
      <div style="background:#fff">
        <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入" />
        <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
        <el-table
          :data="tableData"
          ref="roleTable"
          fit
          highlight-current-row
          style="width: 100%"
          @selection-change="changeCheckBox">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="roleName" label="角色名称" width="100" />
          <el-table-column prop="permCode" label="权限编码" width="150" />
          <el-table-column prop="remark" label="备注" />
        </el-table>
      </div>
      <el-pagination
        :current-page="query.pageNum"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="query.pageSize"
        :total="total"
        style="float:right; padding:10px 0px"
        layout="total, sizes, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"/>
      
    </el-card>
    <span slot="footer" class="dialog-footer" >
        <el-button type="primary" size="small" @click="addSave()">确定</el-button>
        <el-button @click="closeRoleListDialog()" size="small">关闭</el-button>
      </span>
  </el-dialog>
</template>
<script>
import { selectRoleList, deleteRoleByIds, selectRolesByUserId } from '@/api/system/role/index'

export default {
  data() {
    return {
      query: {
        pageNum: 1,
        pageSize: 10
      },
      total: null,
      tableData: [],
      selectSize: 0,
      dialogRoleVisible: false
    }
  },
  methods: {
    toCheckRoleList(userId) {
      // 获取所有的角色
      this.page() 
      this.checkedRoleList(userId)
    },
    page() {
      selectRoleList(this.query).then(reponse => {
        this.tableData = reponse.data
        this.total = reponse.total
        this.dialogRoleVisible = true
      })
    },
    checkedRoleList(userId) { // 用户已经拥有的角色
      selectRolesByUserId(userId).then(reponse => {
        reponse.data.array.forEach(element => {
          this.$refs.roleTable.toggleRowSelection(element)
        });
      })
    },
    addRole() {
      this.$refs.form.addRole()
    },
    editRole(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId)[0]
      }
      this.$refs.form.editRole(userId)
    },
    deleteRole(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId).join()
      }
      deleteRoleByIds(userId).then(() => {
        this.page()
      })
    },
    refreshList() {
      this.page()
    },
    changeCheckBox(val) {
      // 设置选中行数
      this.selectSize = this.$refs.userTable.selection.map(item => item.userId).length
    },
    handleSizeChange(val) {
      this.query.pageSize = val
      this.page()
    },
    handleCurrentChange(val) {
      this.query.pageNum = val
      this.page()
    },
    closeRoleListDialog() {
      this.dialogRoleVisible = false
    }
  }
}
</script>
