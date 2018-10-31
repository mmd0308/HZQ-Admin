<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    width="30%"
    @close="resetForm('userForm')">
    <el-form :model="userForm" :rules="rules" :ref="userFormRef" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名称" prop="userName">
        <el-input v-model="userForm.userName" />
      </el-form-item>
      <el-form-item label="登陆名称" prop="loginName">
        <el-input v-model="userForm.loginName" />
      </el-form-item>
      <el-form-item v-if="status === 'add'" label="密码" prop="password">
        <el-input v-model="userForm.password" />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="userForm.phone" />
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
import { addUser, editUser, editSaveUser } from '@/api/system/user/index'
export default {
  name: 'FormDialog',
  data() {
    return {
      dialogVisible: false,
      title: '',
      status: '',
      userFormRef: 'userFormRef',
      userForm: {
        userName: '',
        loginName: '',
        phone: '',
        password: '',
        avatar: ''
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        loginName: [
          { required: true, message: '请输入登陆名称', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    addSave() {
      this.$refs[this.userFormRef].validate((valid) => {
        if (valid) {
          addUser(this.userForm).then(() => {
            this.refreshList()
            this.resetForm()
          })
        } else {
          return false
        }
      })
    },
    addUser() {
      this.status = 'add'
      this.title = '新增用户'
      this.dialogVisible = true
    },
    editUser(userId) {
      this.status = 'edit'
      this.title = '修改用户'
      this.dialogVisible = true
      editUser(userId).then(response => {
        this.userForm = response.data
      })
    },
    editSave() {
      this.$refs[this.userFormRef].validate((valid) => {
        if (valid) {
          editSaveUser(this.userForm).then(() => {
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
      this.$refs[this.userFormRef].resetFields()
      this.dialogVisible = false
    }
  }
}
</script>
