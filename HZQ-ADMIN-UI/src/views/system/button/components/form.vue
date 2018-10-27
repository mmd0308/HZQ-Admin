<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    @close="resetForm('buttonForm')"
    width="30%">
    <el-form :model="buttonForm" :rules="rules" :ref="buttonFormRef" label-width="100px" class="demo-ruleForm">
      <el-form-item label="菜单id" prop="menuId">
        <el-input v-model="buttonForm.menuId" />
      </el-form-item>
      <el-form-item label="按钮名称" prop="butName">
        <el-input v-model="buttonForm.butName" />
      </el-form-item>
      <el-form-item label="权限标示" prop="permission">
        <el-input v-model="buttonForm.permission" />
      </el-form-item>
      <el-form-item label="是否启用" prop="enabled">
        <el-switch
            v-model="buttonForm.enabled"
            active-text="启用"
            active-color="#1ab394"
            active-value="Y"
            inactive-value="N"
            inactive-color="#FFA500"
            inactive-text="禁用"/>
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
import { addButton, editButton, editSaveButton } from '@/api/system/button/index'
export default {
  name: 'FormDialog',
  data() {
    return {
      dialogVisible: false,
      title: '',
      status: '',
      buttonFormRef: 'buttonFormRef',
      buttonForm: {
          butId: '',
          menuId: '3367c5161bee4169a67c494e7349d2ff',
          butName: '',
          permission: '',
          enabled: '',
          createId: '',
          createTime: '',
          updateId: '',
          updateTime: ''
      },
      rules: {
        buttonName: [
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
      this.$refs[this.buttonFormRef].validate((valid) => {
        if (valid) {
          addButton(this.buttonForm).then(() => {
            this.refreshList()
            this.resetForm()
          })
        } else {
          return false
        }
      });
    },
    addButton() {
      this.status = 'add'
      this.title = '新增用户'
      this.dialogVisible = true
    },
    editButton(buttonId) {
      this.status = 'edit'
      this.title = '修改用户'
      this.dialogVisible = true
      editButton(buttonId).then(response => {
        this.buttonForm = response.data
      })
    },
    editSave() {
      this.$refs[this.buttonFormRef].validate((valid) => {
        if (valid) {
          editSaveButton(this.buttonForm).then(() => {
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
      this.$refs[this.buttonFormRef].resetFields()
      this.dialogVisible = false
    }
  }
}
</script>
