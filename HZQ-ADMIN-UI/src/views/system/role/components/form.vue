<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    @close="resetForm('roleForm')"
    width="30%">
    <el-form :model="roleForm" :rules="rules" :ref="roleFormRef" label-width="100px" class="demo-ruleForm">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="roleForm.roleName" />
      </el-form-item>
      <el-form-item label="排序" prop="roleSort">
        <el-input v-model="roleForm.roleSort" />
      </el-form-item>
      <el-form-item label="权限编码" prop="permCode">
        <el-input v-model="roleForm.permCode" />
      </el-form-item>
      <el-form-item label="是否启用" prop="enabled">
        <el-switch
            v-model="roleForm.enabled"
            active-text="启用"
            active-color="#1ab394"
            active-value="Y"
            inactive-value="N"
            inactive-color="#FFA500"
            inactive-text="禁用"/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="roleForm.remark" />
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button v-if="status === 'add'" type="primary" size="mini" @click="addSave()">确定</el-button>
      <el-button v-if="status === 'edit'" type="primary" size="mini" @click="editSave()">确定</el-button>
      <el-button @click="resetForm()" size="mini">关闭</el-button>
    </span>
  </el-dialog>
</template>
<script>
import { addRole, editRole, editSaveRole } from '@/api/system/role/index'
export default {
  name: 'FormDialog',
  data() {
    return {
      dialogVisible: false,
      title: '',
      status: '',
      roleFormRef: 'roleFormRef',
      roleForm: {
        roleId: '',
        roleName: '',
        roleSort: 0,
        permCode: '',
        enabled: 'Y',
        remark: ''
      },
      rules: {
        roleName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        loginName: [
          { required: true, message: '请输入登陆名称', trigger: 'blur' }
        ],
        phone: [
          {  required: true, message: '请输入手机号码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    addSave() {
      this.$refs[this.roleFormRef].validate((valid) => {
        if (valid) {
          addRole(this.roleForm).then(() => {
            this.refreshList()
            this.resetForm()
          })
        } else {
          return false
        }
      });
    },
    addRole() {
      this.status = 'add'
      this.title = '新增用户'
      this.dialogVisible = true
    },
    editRole(roleId) {
      this.status = 'edit'
      this.title = '修改用户'
      this.dialogVisible = true
      editRole(roleId).then(response => {
        this.roleForm = response.data
      })
    },
    editSave() {
      this.$refs[this.roleFormRef].validate((valid) => {
        if (valid) {
          editSaveRole(this.roleForm).then(() => {
            this.resetForm()
              this.refreshList()
            })
            } else {
              return false
            }
        })
    },
    refreshList() { // 更新列表
      this.$emit('refreshList')
    },
    resetForm() {
      this.$refs[this.roleFormRef].resetFields()
      this.dialogVisible = false
    }
  }
}
</script>
