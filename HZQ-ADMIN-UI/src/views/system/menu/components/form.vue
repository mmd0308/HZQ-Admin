<template>
  <el-card>
    <div style="padding-bottom:15px;">
      <el-button v-if="status === 'init'" size="small" type="primary" icon="el-icon-plus" @click="addMenu">新增</el-button>
      <el-button v-if="status === 'init'" size="small" type="success" icon="el-icon-edit" @click="editMenu()" >修改</el-button>
      <el-button v-if="status === 'init'" size="small" type="danger" icon="el-icon-delete" @click="deleteMenu(null)" >删除</el-button>

      <el-button v-if="status === 'add'" type="primary" size="small" @click="addSave()">保存</el-button>

      <el-button v-if="status === 'edit'" type="primary" size="small" @click="editSave()">更新</el-button>
      <el-button v-if="status != 'init'" type="info" size="small" @click="addSave()">取消</el-button>
    </div>
    <el-form :model="menuForm" :rules="rules" :ref="menuFormRef" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="菜单名称:" prop="menuName">
            <el-input v-model="menuForm.menuName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="上级id" prop="parentId">
            <el-input v-model="menuForm.parentId" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="权限标示" prop="permission">
            <el-input v-model="menuForm.permission" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序" prop="menuSort">
            <el-input v-model="menuForm.menuSort" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="菜单类型" prop="menuType">
            <el-radio v-model="menuForm.menuType" label="M">菜单</el-radio>
            <el-radio v-model="menuForm.menuType" label="C">目录</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否启用" prop="enabled">
            <el-switch
              v-model="menuForm.enabled"
              active-text="启用"
              active-color="#1ab394"
              active-value="Y"
              inactive-value="N"
              inactive-color="#FFA500"
              inactive-text="禁用"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="图标" prop="icon">
        <el-input v-model="menuForm.icon" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="menuForm.remark" type="textarea" />
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { addMenu, editMenu, editSaveMenu } from '@/api/system/menu/index'
export default {
  name: 'FormDialog',
  data() {
    return {
      dialogVisible: false,
      title: '',
      status: 'init',
      menuFormRef: 'menuFormRef',
      menuForm: {
          menuId: '',
          menuName: '',
          parentId: '',
          menuSort: '',
          menuType: '',
          enabled: '',
          icon: '',
          remark: '',
          permission: '',
          createBy: '',
          createTime: '',
          updateBy: '',
          updateTime: ''
      },
      rules: {
        menuName: [
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
      this.$refs[this.menuFormRef].validate((valid) => {
        if (valid) {
          addMenu(this.menuForm).then(() => {
            this.refreshList()
            this.resetForm()
          })
        } else {
          return false
        }
      });
    },
    addMenu() {
      this.status = 'add'
    },
    editMenu(menuId) {
      this.status = 'edit'
      // editMenu(menuId).then(response => {
      //   this.menuForm = response.data
      // })
    },
    editSave() {
      this.$refs[this.menuFormRef].validate((valid) => {
        if (valid) {
          editSaveMenu(this.menuForm).then(() => {
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
      this.$refs[this.menuFormRef].resetFields()
      this.dialogVisible = false
    }
  }
}
</script>
