<template>
    <div id="button">
        <div id="query">
            <el-row>
                <el-col :span="8">
                    <el-input v-model="query" style="width:96%" placeholder="请输入角色名称"></el-input>
                </el-col>
                <el-col :span="16">
                    <el-button-group>
                        <el-button type="primary" @click="queryList()">查询</el-button>
                        <el-button type="primary" @click="toCreate()">新增</el-button>
                    </el-button-group>
                </el-col>
            </el-row>
        </div>
        <el-table
        :data="list"
        style="width: 100%"
        :highlight-current-row="true"
        class="system-table"
        >
            <el-table-column align="center" label="序号" type="index" width="90"></el-table-column>
            <el-table-column
            label="按钮名称"
            sortable>
                <template scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column
            label="编码">
                <template scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.code }}</span>
                </template>
            </el-table-column>
            <el-table-column
            label="是否启用" >
                <template scope="scope">
                    <span style="margin-left: 10px">
                      <el-tag v-if="scope.row.enabled == '1'" type="success">启用</el-tag>
                      <el-tag v-else type="danger">禁用</el-tag>
                    </span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="300px">
                <template scope="scope">
                    <el-button
                        size="mini"
                        type="success"
                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-container">
            <el-pagination @size-change="handleSizeChange" 
                            @current-change="handleCurrentChange"
                            :current-page.sync="listQuery.page"
                            :page-sizes="[5.,10,15,20]" 
                            :page-size="listQuery.pageSize"
                            layout="total, sizes, prev, pager, next, jumper" 
                            :total="total">
            </el-pagination>
        </div>
        <!--编辑框-->
        <el-dialog  :visible.sync="dialogFormVisible" :before-close="handleClose"  width="25%" :title="title">
            <el-form :model="form" :inline="false" :rules="rules" ref="form" label-width="90px">
                <el-form-item label="名称" prop="name" >
                    <el-input v-model="form.name" placeholder="请输入名称" style="width:80%" ></el-input>
                </el-form-item>
                <el-form-item label="编码" prop="code">
                    <el-input  v-model="form.code" placeholder="请输入编码" style="width:80%"></el-input>
                </el-form-item>
                <el-form-item label="是否可用" prop="enabled">
                    <el-switch  active-value='1'  inactive-value='0' v-model="form.enabled"></el-switch>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel('form')">取 消</el-button>
                <el-button v-if="dialogStatus=='create'" type="primary" @click="create('form')">确 定</el-button>
                <el-button v-else type="primary" @click="update('form')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
  import { page, getObj, addObj, putObj, delObj, checkCode } from '@/api/manager/system/button/index'
  // import roleMenu from '@/views/manager/system/role/roleMenu'
  export default {
    props: ['menuId'],
    // components: {
    //   roleMenu: roleMenu
    // },
    data() {
      const validateCode = (rule, value, callback) => {
        if (this.form.code === '') {
          callback(new Error('请输入编码'))
        } else {
          checkCode(this.form.code, this.form.id).then(response => {
            if (response.data) {
              callback()
            } else {
              callback(new Error('编码重复，请重新输入'))
            }
          })
        }
      }
      return {
        list: null,
        query: '',
        total: null,
        listQuery: {
          page: 1,
          pageSize: 5,
          name: '',
          menuId: ''
        },
        title: '',
        dialogStatus: '',
        dialogFormVisible: false,
        form: this.initObj(),
        dialogMenuVisible: false,
        roleToMenuId: '',
        rules: {
          name: [
            { required: true, message: '请输入名称', trigger: 'blur' }
          ],
          code: { required: true, trigger: 'blur', validator: validateCode }
        }
      }
    },
    // created() {
    //   this.getList()
    // },
    methods: {
      initObj() {
        return {
          id: '',
          code: '',
          name: '',
          menuId: '',
          enabled: '0'
        }
      },
      resetTemp() {
        this.form = this.initObj()
      },
      queryList() {
        this.listQuery.name = this.query
        this.getList()
      },
      getList(id) {
        this.listQuery.menuId = this.menuId
        if (id !== undefined && id != null) {
          this.listQuery.menuId = id
        }
        page(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
        })
      },
      cancel(formName) {
        this.dialogFormVisible = false
        this.$refs[formName].resetFields()
      },
      handleClose(done) {
        this.cancel('form')
        done()
      },
      handleEdit(index, row) {
        getObj(row.id).then(response => {
          this.form = response.data
          this.dialogFormVisible = true
          this.dialogStatus = 'update'
          this.title = '修改按钮'
        })
      },
      update(formName) {
        const set = this.$refs
        set[formName].validate(valid => {
          if (valid) {
            putObj(this.form.id, this.form).then(() => {
              this.cancel(formName)
              this.getList()
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          } else {
            return false
          }
        })
      },
      handleDelete(index, row) {
        this.$confirm('是否刪除该记录？', '记录', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delObj(row.id).then(() => {
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
            this.getList()
          })
        })
      },
      toCreate() {
        this.resetTemp()
        this.dialogFormVisible = true
        this.dialogStatus = 'create'
        this.title = '新增按钮'
      },
      create(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.form.menuId = this.menuId
            addObj(this.form).then(() => {
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
              this.cancel(formName)
              this.getList()
            })
          } else {
            return false
          }
        })
      },
      handleSizeChange(val) {
        this.listQuery.pageSize = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getList()
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
#button{
    .el-input__inner{
        width: 80% !important;
    }
}
</style>

