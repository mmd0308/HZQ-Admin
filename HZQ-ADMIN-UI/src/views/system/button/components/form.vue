<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    @close="resetForm('buttonForm')"
    width="30%">
    <el-form :model="buttonForm" :rules="rules" :ref="buttonFormRef" label-width="100px" class="demo-ruleForm">
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
          menuId: '',
          butName: '',
          permission: '',
          enabled: ''
      },
      rules: {
        butName: [
          { required: true, message: '请输入按钮名称', trigger: 'blur' }
        ],
        permission: [
          { required: true, message: '请输入权限编码', trigger: 'blur' }
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
    toAddButton(menuId) {
      this.status = 'add'
      this.title = '新增按钮'
      this.buttonForm.menuId = menuId
      this.dialogVisible = true
    },
    editButton(buttonId) {
      this.status = 'edit'
      this.title = '修改按钮'
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
