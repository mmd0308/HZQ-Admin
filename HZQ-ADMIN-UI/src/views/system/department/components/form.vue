<template>
  <el-card>
    <div style="padding-bottom:15px;">
      <el-button v-if="status === 'init'" size="small" type="primary" icon="el-icon-plus" @click="toAddDepartment">新增</el-button>
      <el-button v-if="status === 'init'" size="small" type="success" icon="el-icon-edit" @click="editDepartment" >修改</el-button>
      <el-button v-if="status === 'init'" size="small" type="danger" icon="el-icon-delete" @click="deleteDepartment" >删除</el-button>
      <el-button v-if="status === 'add'" type="primary" size="small" @click="addSave()">保存</el-button>
      <el-button v-if="status === 'edit'" type="primary" size="small" @click="editSave()">更新</el-button>
      <el-button v-if="status != 'init'" type="info" size="small" @click="refreshTree">取消</el-button>
    </div>
    <el-form 
      v-loading="departmentFormLoading"
      element-loading-text="拼命加载中..."
      element-loading-spinner="el-icon-loading" 
      :model="departmentForm" :rules="rules" :ref="departmentFormRef" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="部门名称" prop="departName">
            <el-input v-if="status === 'init'" disabled v-model="departmentForm.departName" />
            <el-input v-else v-model="departmentForm.departName" placeholder="请输入部门名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序" prop="departSort">
            <el-input v-if="status === 'init'" disabled v-model="departmentForm.departSort" />
            <el-input v-else v-model="departmentForm.departSort" placeholder="请输入排序" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="部门负责人" prop="leader">
            <el-select v-if="status === 'init'" disabled v-model="departmentForm.leader" style="width:100%">
              <el-option
                v-for="item in userList"
                :key="item.userId"
                :label="item.userName"
                :value="item.userId">
              </el-option>
            </el-select>
            <el-select v-else v-model="departmentForm.leader" placeholder="请选择部门负责人" style="width:100%">
              <el-option
                v-for="item in userList"
                :key="item.userId"
                :label="item.userName"
                :value="item.userId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="部门电话" prop="phone">
            <el-input v-if="status === 'init'" disabled v-model="departmentForm.phone" />
            <el-input v-else v-model="departmentForm.phone" placeholder="请输入部门电话" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="是否启用" prop="enabled">
        <el-switch
          v-if="status === 'init'"
          disabled 
          v-model="departmentForm.enabled"
          active-text="启用"
          active-color="#1ab394"
          active-value="Y"
          inactive-value="N"
          inactive-color="#FFA500"
          inactive-text="禁用"/>
        <el-switch
          v-else
          v-model="departmentForm.enabled"
          active-text="启用"
          active-color="#1ab394"
          active-value="Y"
          inactive-value="N"
          inactive-color="#FFA500"
          inactive-text="禁用"/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-if="status === 'init'" disabled type="textarea" v-model="departmentForm.remark" />
        <el-input v-else type="textarea" v-model="departmentForm.remark" placeholder="请输入备注信息" />
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { addDepartment, editDepartment, editSaveDepartment, deleteDepartmentByIds } from '@/api/system/department/index'
import { selectUserListAll } from '@/api/system/user/index'
export default {
  name: 'FormDialog',
  data() {
    return {
      status: 'init',
      departmentFormRef: 'departmentFormRef',
      userList: [],
      departmentForm: {
        departId: '',
        departName: '',
        departSort: '',
        leader: '',
        phone: '',
        enabled: '',
        delFlag: '',
        createBy: '',
        createTime: '',
        updateBy: '',
        updateTime: '',
        parentId: '',
        remark: ''
      },
      rules: {
        departName: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        departSort: [
          { required: true, message: '请输入排序', trigger: 'blur' }
        ],
        phone: [
          {  required: true, message: '请输入部门电话', trigger: 'blur' }
        ],
        leader: [
          {  required: true, message: '请选择部门负责人', trigger: 'blur' }
        ]
      },
      departmentFormLoading: false
    }
  },
  created(){
    this.getUserAll()
  },
  methods: {
    getUserAll() {
      selectUserListAll().then(response => {
        this.userList = response.data
      })
    },
    seeDepartment(data) {
      this.status = 'init'
      this.departmentFormLoading = true
      this.departmentForm = JSON.parse(JSON.stringify(data))
      this.departmentFormLoading = false
    },
    addSave() {
      this.$refs[this.departmentFormRef].validate((valid) => {
        if (valid) {
          addDepartment(this.departmentForm).then(() => {
            this.status = 'init'
            this.refreshTree()
          })
        } else {
          return false
        }
      });
    },
    toAddDepartment() {
      this.status = 'add'
      const parentId = this.departmentForm.departId
      this.resetForm()
      this.departmentForm.parentId = parentId
    },
    editDepartment(departmentId) {
      this.status = 'edit'
    },
    editSave() {
      this.$refs[this.departmentFormRef].validate((valid) => {
        if (valid) {
          editSaveDepartment(this.departmentForm).then(() => {
            this.status = 'init'
            if (this.departmentForm.parentId == '0') {
              this.refreshTree()
            } else {
              this.$parent.$parent.$refs.treeData.remove(this.departmentForm.departId)
              this.$parent.$parent.$refs.treeData.append(this.departmentForm,this.departmentForm.parentId)
            }
          })
        } else {
          return false
        }
      })
    },
    deleteDepartment() { // 删除菜单
      this.$confirm('此操作将永久删除[' + this.departmentForm.departName + ']部门?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deleteDepartmentByIds(this.departmentForm.departId).then(response => {
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
      this.$refs[this.departmentFormRef].resetFields()
    }
  }
}
</script>
