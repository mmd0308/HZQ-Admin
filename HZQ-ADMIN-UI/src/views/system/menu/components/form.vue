<template>
  <el-card>
    <div style="padding-bottom:15px;">
      <el-button v-if="status === 'init'" size="small" type="primary" icon="el-icon-plus" @click="toAddMenu">新增</el-button>
      <el-button v-if="status === 'init'" size="small" type="success" icon="el-icon-edit" @click="editMenu" >修改</el-button>
      <el-button v-if="status === 'init'" size="small" type="danger" icon="el-icon-delete" @click="deleteMenu" >删除</el-button>

      <el-button v-if="status === 'add'" type="primary" size="small" @click="addSave()">保存</el-button>

      <el-button v-if="status === 'edit'" type="primary" size="small" @click="editSave()">更新</el-button>
      <el-button v-if="status != 'init'" type="info" size="small" @click="refreshTree">取消</el-button>
    </div>
    <el-form
      v-loading="menuFormLoading"
      :model="menuForm"
      :rules="rules"
      :ref="menuFormRef"
      element-loading-text="拼命加载中..."
      element-loading-spinner="el-icon-loading"
      label-width="100px"
      class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="菜单名称:" prop="menuName">
            <el-input v-if="status === 'init'" v-model="menuForm.menuName" disabled />
            <el-input v-else v-model="menuForm.menuName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="图标" prop="icon">
            <el-input v-if="status === 'init'" v-model="menuForm.icon" disabled />
            <el-input v-else v-model="menuForm.icon" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="权限标示" prop="permission">
            <el-input v-if="status === 'init'" v-model="menuForm.permission" disabled />
            <el-input v-else v-model="menuForm.permission" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序" prop="menuSort">
            <el-input-number v-if="status === 'init'" v-model="menuForm.menuSort" disabled />
            <el-input-number v-else v-model="menuForm.menuSort" type="number" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="菜单类型" prop="menuType">
            <el-radio v-if="status === 'init'" v-model="menuForm.menuType" disabled label="M">菜单</el-radio>
            <el-radio v-else v-model="menuForm.menuType" label="M">菜单</el-radio>
            <el-radio v-if="status === 'init'" v-model="menuForm.menuType" disabled label="C">目录</el-radio>
            <el-radio v-else v-model="menuForm.menuType" label="C">目录</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否启用" prop="enabled">
            <el-switch
              v-if="status === 'init'"
              v-model="menuForm.enabled"
              disabled
              active-text="启用"
              active-color="#1ab394"
              active-value="Y"
              inactive-value="N"
              inactive-color="#FFA500"
              inactive-text="禁用"/>
            <el-switch
              v-else
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
      <el-form-item label="备注" prop="remark">
        <el-input v-if="status === 'init'" v-model="menuForm.remark" disabled type="textarea" />
        <el-input v-else v-model="menuForm.remark" type="textarea" />
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { addMenu, editSaveMenu, deleteMenuByIds, checkPermission } from '@/api/system/menu/index'
export default {
  name: 'FormDialog',
  data() {
    const validatePermission = (menu, value, callback) => {
      if (value === '') {
        callback(new Error('请输入权限编码'))
      } else {
        checkPermission(this.menuForm).then(response => {
          if (!response.data) {
            callback(new Error('权限编码重复'))
          }
          callback()
        })
      }
    }
    return {
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
          { required: true, message: '请输入菜单名称', trigger: 'blur' }
        ],
        menuType: [
          { required: true, message: '请输选择菜单类型' }
        ],
        permission: [
          { validator: validatePermission, required: true, trigger: 'blur' }
        ]
      },
      menuFormLoading: false
    }
  },
  methods: {
    seeMenu(data) {
      this.status = 'init'
      this.menuFormLoading = true
      this.menuForm = JSON.parse(JSON.stringify(data))
      this.menuFormLoading = false
    },
    addSave() {
      this.$refs[this.menuFormRef].validate((valid) => {
        if (valid) {
          addMenu(this.menuForm).then(() => {
            this.status = 'init'
            this.refreshTree()
          })
        } else {
          return false
        }
      })
    },
    toAddMenu() {
      this.status = 'add'
      const parentId = this.menuForm.menuId
      this.resetForm()
      this.menuForm.parentId = parentId
    },
    editMenu(menuId) {
      this.status = 'edit'
    },
    editSave() {
      this.$refs[this.menuFormRef].validate((valid) => {
        if (valid) {
          editSaveMenu(this.menuForm).then(() => {
            this.status = 'init'
            if (this.menuForm.parentId === '0') {
              this.refreshTree()
            } else {
              this.$parent.$parent.$refs.treeData.remove(this.menuForm.menuId)
              this.$parent.$parent.$refs.treeData.append(this.menuForm, this.menuForm.parentId)
            }
          })
        } else {
          return false
        }
      })
    },
    deleteMenu() { // 删除菜单
      this.$confirm('此操作将永久删除[' + this.menuForm.menuName + ']菜单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deleteMenuByIds(this.menuForm.menuId).then(response => {
          this.status = 'init'
          this.refreshTree()
        })
      })
    },
    resetForm() {
      this.$refs[this.menuFormRef].resetFields()
    },
    refreshTree() { // 刷新
      this.$emit('refreshTree')
      this.status = 'init'
    }
  }
}
</script>
