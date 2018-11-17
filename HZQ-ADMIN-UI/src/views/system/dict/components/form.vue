<template>
  <el-card>
    <div style="padding-bottom:15px;">
      <el-button v-if="status === 'init'" size="small" type="primary" icon="el-icon-plus" @click="toAddDict">新增</el-button>
      <el-button v-if="status === 'init'" size="small" type="success" icon="el-icon-edit" @click="editDict" >修改</el-button>
      <el-button v-if="status === 'init'" size="small" type="danger" icon="el-icon-delete" @click="deleteDict" >删除</el-button>
      <el-button v-if="status === 'add'" type="primary" size="small" @click="addSave()">保存</el-button>
      <el-button v-if="status === 'edit'" type="primary" size="small" @click="editSave()">更新</el-button>
      <el-button v-if="status != 'init'" type="info" size="small" @click="refreshTree">取消</el-button>
    </div>
    <el-form
      v-loading="dictFormLoading"
      :model="dictForm"
      :rules="rules"
      :ref="dictFormRef"
      element-loading-text="拼命加载中..."
      element-loading-spinner="el-icon-loading"
      label-width="100px"
      class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="字典名称" prop="dictName">
            <el-input v-if="status === 'init'" v-model="dictForm.dictName" disabled />
            <el-input v-else v-model="dictForm.dictName" placeholder="请输入字典名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序" prop="dictSort">
            <el-input-number v-if="status === 'init'" v-model="dictForm.dictSort" disabled />
            <el-input-number v-else v-model="dictForm.dictSort" placeholder="请输入排序" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="是否启用" prop="enabled">
            <el-switch
              v-if="status === 'init'"
              v-model="dictForm.enabled"
              disabled
              active-text="启用"
              active-color="#1ab394"
              active-value="Y"
              inactive-value="N"
              inactive-color="#FFA500"
              inactive-text="禁用"/>
            <el-switch
              v-else
              v-model="dictForm.enabled"
              active-text="启用"
              active-color="#1ab394"
              active-value="Y"
              inactive-value="N"
              inactive-color="#FFA500"
              inactive-text="禁用"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="字典编码" prop="dictCode">
            <el-input v-if="status === 'init'" v-model="dictForm.dictCode" disabled />
            <el-input v-else v-model="dictForm.dictCode" placeholder="请输入字典编码" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="备注" prop="remark">
        <el-input v-if="status === 'init'" v-model="dictForm.remark" disabled type="textarea" />
        <el-input v-else v-model="dictForm.remark" type="textarea" placeholder="请输入备注信息" />
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { addDict, editSaveDict, deleteDictByIds, checkCode } from '@/api/system/dict/index'
import { Message } from 'element-ui'
export default {
  name: 'FormDialog',
  data() {
    const validateCode = (dict, value, callback) => {
      if (value === '') {
        callback(new Error('请输入数据字典编码'))
      } else {
        checkCode(this.dictForm).then(response => {
          if (!response.data) {
            callback(new Error('数据字典编码重复'))
          }
          callback()
        })
      }
    }
    return {
      status: 'init',
      dictFormRef: 'dictFormRef',
      dictForm: {
        dictId: '',
        dictName: '',
        dictCode: '',
        dictSort: '',
        remark: '',
        parentId: '',
        createBy: '',
        createTime: '',
        updateBy: '',
        updateTime: ''
      },
      rules: {
        dictName: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        dictCode: [
          { validator: validateCode, required: true, trigger: 'blur' }
        ]
      },
      dictFormLoading: false
    }
  },
  methods: {
    seeDict(data) {
      this.status = 'init'
      this.dictFormLoading = true
      this.dictForm = JSON.parse(JSON.stringify(data))
      this.dictFormLoading = false
    },
    addSave() {
      this.$refs[this.dictFormRef].validate((valid) => {
        if (valid) {
          addDict(this.dictForm).then(() => {
            this.status = 'init'
            this.refreshTree()
          })
        } else {
          return false
        }
      })
    },
    toAddDict() {
      this.status = 'add'
      const parentId = this.dictForm.dictId
      this.resetForm()
      this.dictForm.parentId = parentId
    },
    editDict(dictId) {
      this.status = 'edit'
    },
    editSave() {
      this.$refs[this.dictFormRef].validate((valid) => {
        if (valid) {
          editSaveDict(this.dictForm).then(() => {
            this.status = 'init'
            if (this.dictForm.parentId === '0') {
              this.refreshTree()
            } else {
              this.$parent.$parent.$refs.treeData.remove(this.dictForm.dictId)
              this.$parent.$parent.$refs.treeData.append(this.dictForm, this.dictForm.parentId)
            }
          })
        } else {
          return false
        }
      })
    },
    deleteDict() { // 删除菜单
      if (this.dictForm.parentId === '0') {
        Message({
          message: '不能删除最高级字典',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
      this.$confirm('此操作将永久删除[' + this.dictForm.dictName + ']字典?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deleteDictByIds(this.dictForm.dictId).then(response => {
          this.status = 'init'
          this.refreshTree()
        })
      })
    },
    refreshTree() { // 刷新
      this.$emit('refreshTree')
      this.status = 'init'
    },
    resetForm() {
      this.$refs[this.dictFormRef].resetFields()
    }
  }
}
</script>
