<template>
  <el-dialog
    :visible="dialogRoleVisible"
    title="角色分配"
    @close="closeRoleListDialog()"
    width="40%">
    <el-card style="border:none;box-shadow:none">
      <div style="background:#fff">
        <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入" />
        <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
        <el-table
          :data="roleTableData"
          ref="roleTable"
          fit
          highlight-current-row
          style="width: 100%">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="roleName" label="角色名称" width="100" />
          <el-table-column prop="permCode" label="权限编码" width="150" />
          <el-table-column prop="remark" label="备注" />
        </el-table>
      </div>
    </el-card>
    <span slot="footer" class="dialog-footer" >
        <el-button type="primary" size="small" @click="addSave()">确定</el-button>
        <el-button @click="closeRoleListDialog()" size="small">关闭</el-button>
      </span>
  </el-dialog>
</template>
<script>
import { selectRoleListAll, deleteRoleByIds, selectRolesByUserId } from '@/api/system/role/index'
import { addUserRole } from '@/api/system/user/index'


export default {
  data() {
    return {
      roleTableData: [],
      dialogRoleVisible: false,
      form: {
        userId: null,
        roleIds: null
      }
    }
  },
  methods: {
    toCheckRoleList(userId) {
      this.form.userId = userId
      // 获取所有的角色
      this.page() 
    },
    page() {
      selectRoleListAll().then(reponse => {
        this.roleTableData = reponse.data
        this.dialogRoleVisible = true
        this.checkedRoleList(this.form.userId)
      })
    },
    checkedRoleList(userId) { // 用户已经拥有的角色
      selectRolesByUserId(userId).then(reponse => {
        this.$refs.roleTable.toggleRowSelection(this.roleTableData)
        debugger
      })
    },
    addSave() {
      if (this.form.roleIds === null) {
        this.$message.error('请选择角色');
        return;
      }
      addUserRole(this.form).then(() => {
        this.closeRoleListDialog()
      })
    },
    closeRoleListDialog() {
      this.dialogRoleVisible = false
    }
  }
}
</script>
