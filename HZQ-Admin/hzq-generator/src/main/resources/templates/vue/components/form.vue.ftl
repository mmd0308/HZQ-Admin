<template>
  <el-dialog
    :visible="dialogVisible"
    :title="title"
    @close="resetForm('${classNameLower}Form')"
    width="30%">
    <el-form :model="${classNameLower}Form" :rules="rules" :ref="${classNameLower}FormRef" label-width="100px" class="demo-ruleForm">
    <#list columns as col>
      <el-form-item label="${col.columnComment}" prop="${col.attrName}">
        <el-input v-model="${classNameLower}Form.${col.attrName}" />
      </el-form-item>
    </#list>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button v-if="status === 'add'" type="primary" size="mini" @click="addSave()">确定</el-button>
      <el-button v-if="status === 'edit'" type="primary" size="mini" @click="editSave()">确定</el-button>
      <el-button @click="resetForm()" size="mini">关闭</el-button>
    </span>
  </el-dialog>
</template>
<script>
import { add${className}, edit${className}, editSave${className} } from '@/api/system/${classNameLower}/index'
export default {
  name: 'FormDialog',
  data() {
    return {
      dialogVisible: false,
      title: '',
      status: '',
      ${classNameLower}FormRef: '${classNameLower}FormRef',
      ${classNameLower}Form: {
      <#list columns as col>
          ${col.attrName}: ''<#if  col_index != columns?size-1>,</#if>
      </#list>
      },
      rules: {
        ${classNameLower}Name: [
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
      this.$refs[this.${classNameLower}FormRef].validate((valid) => {
        if (valid) {
          add${className}(this.${classNameLower}Form).then(() => {
            this.refreshList()
            this.resetForm()
          })
        } else {
          return false
        }
      });
    },
    add${className}() {
      this.status = 'add'
      this.title = '新增用户'
      this.dialogVisible = true
    },
    edit${className}(${classNameLower}Id) {
      this.status = 'edit'
      this.title = '修改用户'
      this.dialogVisible = true
      edit${className}(${classNameLower}Id).then(response => {
        this.${classNameLower}Form = response.data
      })
    },
    editSave() {
      this.$refs[this.${classNameLower}FormRef].validate((valid) => {
        if (valid) {
          editSave${className}(this.${classNameLower}Form).then(() => {
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
      this.$refs[this.${classNameLower}FormRef].resetFields()
      this.dialogVisible = false
    }
  }
}
</script>
