<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    width="30%"
    @close="resetForm">
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
      <el-form-item label="选择角色" prop="roleIds">
        <el-select v-model="userForm.roleIds" multiple placeholder="请选择" style="width:100%">
          <el-option
            v-for="item in roles"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="所属部门" prop="departId">
        <el-cascader
          :options="departDatas"
          :props="departProps"
          v-model="userForm.departId"
          style="width:100%"
          expand-trigger="hover"/>
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
// 获取所有的部门
import { selectDepartmentTree } from '@/api/system/department/index'
// 获取角色
import { selectRoleListAll } from '@/api/system/role/index'
export default {
  name: 'FormDialog',
  data() {
    const phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/
    const validatePhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('号码不能为空!!'))
        return
      }
      if (!phoneReg.test(value)) {
        callback(new Error('请输入正确的手机号码'))
        return
      }
      callback()
      return
    }
    return {
      dialogVisible: false,
      title: '',
      status: '',
      userFormRef: 'userFormRef',
      departDatas: [],
      departProps: {
        label: 'departName',
        value: 'departId'
      },
      roles: [],
      userForm: {
        userName: '',
        loginName: '',
        phone: '',
        password: '',
        avatar: '',
        departId: [],
        roleIds: []
      },
      rules: {
        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        loginName: [
          { required: true, message: '请输入登陆名称', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        phone: [
          { required: true, validator: validatePhone, trigger: 'blur' }
        ],
        departId: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
        roleIds: [
          { required: true, message: '请选择角色', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    addSave() {
      this.$refs[this.userFormRef].validate((valid) => {
        if (valid) {
          this.userForm.departId = this.userForm.departId.map(item => item).join()
          addUser(this.userForm).then(() => {
            this.refreshList()
            this.resetForm()
          })
        } else {
          return false
        }
      })
    },
    getDepartments() {
      selectDepartmentTree().then(reponse => {
        this.departDatas = reponse.data
      })
    },
    getRoles() {
      selectRoleListAll().then(reponse => {
        this.roles = reponse.data
      })
    },
    addUser() {
      this.status = 'add'
      this.title = '新增用户'
      this.dialogVisible = true
      this.getDepartments()
      this.getRoles()
    },
    editUser(userId) {
      this.status = 'edit'
      this.title = '修改用户'
      this.dialogVisible = true
      this.getDepartments()
      this.getRoles()
      editUser(userId).then(response => {
        this.userForm = response.data
        this.userForm.departId = response.data.departId.split(',')
      })
    },
    editSave() {
      this.$refs[this.userFormRef].validate((valid) => {
        if (valid) {
          this.userForm.departId = this.userForm.departId.map(item => item).join()
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
      this.userForm.password = ''
    }
  }
}
</script>
