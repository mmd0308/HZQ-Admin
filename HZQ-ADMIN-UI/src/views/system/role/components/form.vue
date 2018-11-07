<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    width="30%"
    @close="resetForm('roleForm')">
    <el-form :model="roleForm" :rules="roleRules" :ref="roleFormRef" label-width="100px" class="demo-ruleForm">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="roleForm.roleName" placeholder="请输入角色名称" />
      </el-form-item>
      <el-form-item label="排序" prop="roleSort">
        <el-input v-model="roleForm.roleSort" />
      </el-form-item>
      <el-form-item label="权限编码" prop="permission">
        <el-input v-model="roleForm.permission" placeholder="请输入权限编码" />
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
      <el-button size="mini" @click="resetForm()">关闭</el-button>
    </span>
  </el-dialog>
</template>
<script>
import { addRole, editRole, editSaveRole, checkPermission } from '@/api/system/role/index'
export default {
  name: 'FormDialog',
  data() {
    const validatePermission = (role, value, callback) => {
      if (value === '') {
        callback(new Error('请输入权限编码'))
      } else {
        checkPermission(this.roleForm).then(response => {
          if (!response.data) {
            debugger
            callback(new Error('权限编码重复'))
          }
          callback()
        })
      }
    };
    return {
      dialogVisible: false,
      title: '',
      status: '',
      roleFormRef: 'roleFormRef',
      roleForm: {
        roleId: '',
        roleName: '',
        roleSort: 0,
        permission: '',
        enabled: 'Y',
        remark: ''
      },
      roleRules: {
        roleName: [
          { required: true, message: '请输入用户名称', trigger:  ['blur', 'change'] }
        ],
        permission: [
          { validator: validatePermission, required: true, trigger: 'blur' }
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
      })
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
