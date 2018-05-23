<template>
  <div id="user">
    <el-card>
      <div id="query">
        <el-row>
          <el-col :span="8">
            <el-input v-model="query" style="width:96%" placeholder="请输入姓名或账户"></el-input>
          </el-col>
          <el-col :span="16">
            <el-button-group>
                <el-button type="primary" @click="queryList()">查询</el-button>
                <el-button type="primary"  @click="toCreate()">新增</el-button>
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
        label="账户"
        sortable>
            <template scope="scope">
                <span style="margin-left: 10px">{{ scope.row.username }}</span>
            </template>
        </el-table-column>
        <el-table-column
        label="邮箱">
            <template scope="scope">
                <span style="margin-left: 10px">{{ scope.row.email }}</span>
            </template>
        </el-table-column>
        <el-table-column
        label="备注" >
            <template scope="scope">
                <span style="margin-left: 10px">{{ scope.row.note }}</span>
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
                  type="info"
                  @click="handlePermission(scope.$index, scope.row)">角色分配</el-button>
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
                        :page-sizes="[10.,20,30,50]" 
                        :page-size="listQuery.pageSize"
                        layout="total, sizes, prev, pager, next, jumper" 
                        :total="total">
        </el-pagination>
      </div>
      <!--编辑框-->
      <el-dialog  :visible.sync="dialogFormVisible" :before-close="handleClose" width="40%" :title="title">
        <el-form :model="form" :inline="true" :rules="rules" ref="form" label-width="100px">
          <el-form-item label="帐户" prop="username" >
            <el-input v-model="form.username" placeholder="请输入姓名" ></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input  v-model="form.email" placeholder="请输入账户"  ></el-input>
          </el-form-item>
          <el-form-item  label="密码" placeholder="请输入密码" prop="password" v-if="dialogStatus=='create'">
            <el-input type="password" v-model="form.password"  ></el-input>
          </el-form-item>
          <el-form-item label="确认密码" placeholder="请输入密码" prop="password" v-if="dialogStatus=='create'">
            <el-input type="password" v-model="form.password"  ></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="note">
            <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 5}"   placeholder="请输入备注"  v-model="form.note"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="cancel('form')">取 消</el-button>
            <el-button v-if="dialogStatus=='create'" type="primary" @click="create('form')">确 定</el-button>
            <el-button v-else type="primary" @click="update('form')">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 角色分配 -->
      <el-dialog  :visible.sync="dialogRoleFormVisible">
        <el-table
          ref="roleTable"
          :data="dialogRolelist"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            label="角色名称">
            <template scope="scope">{{ scope.row.roleName }}</template>
          </el-table-column>
          <el-table-column
            label="角色编码">
            <template scope="scope">{{ scope.row.roleCode }}</template>
          </el-table-column>
          <el-table-column
            label="备注">
            <template scope="scope">{{ scope.row.note }}</template>
          </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button @click="roleCancel()">取 消</el-button>
            <el-button type="primary" @click="roleUpdate()">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>                
  </div>
</template>

<script>
  import { page, getObj, putObj, delObj, addObj, saveUserRole } from '@/api/manager/system/user/index'
  import { getAllEnabledRole, getRoleByUserId } from '@/api/manager/system/role/index'
  export default {
    data() {
      return {
        list: null,
        query: '',
        title: '',
        total: null,
        listQuery: {
          page: 1,
          pageSize: 10,
          username: '',
          email: ''
        },
        dialogStatus: '',
        dialogFormVisible: false,
        dialogRoleFormVisible: false,
        form: this.initObj(),
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 20, message: '长度在3到20个字符', trigger: 'blur' }
          ],
          email: [
            { type: 'email', message: '请输入有效的邮箱格式', trigger: 'blur' }
          ]
        },
        dialogRolelist: null,
        userRole: {
          userId: '',
          roles: []
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      initObj() {
        return {
          id: '',
          username: undefined,
          password: undefined,
          email: undefined,
          note: ''
        }
      },
      resetTemp() {
        this.form = this.initObj()
      },
      queryList() {
        this.listQuery.username = this.query
        this.listQuery.email = this.query
        this.getList()
      },
      getList() {
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
          this.title = '修改用户'
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
        this.$confirm('是否刪除该用户？', '删除用户', {
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
        this.title = '新增用户'
      },
      create(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
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
      handlePermission(index, row) {
        // 首先获取所有的权限
        getAllEnabledRole().then(response => {
          this.dialogRoleFormVisible = true
          this.dialogRolelist = response.data
          getRoleByUserId(row.id).then(response => {
            response.data.forEach(row => {
              this.$refs.roleTable.toggleRowSelection(this.dialogRolelist.find(r => r.id === row.id))
            })
            this.userRole.userId = row.id
          })
        })
      },
      handleSizeChange(val) {
        this.listQuery.pageSize = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getList()
      },
      handleSelectionChange(val) {
        this.userRole.roles = val
      },
      roleCancel() {
        this.dialogRoleFormVisible = false
        this.userRole.roles = []
        this.userRole.userId = ''
      },
      roleUpdate() {
        saveUserRole(this.userRole).then(() => {
          this.$notify({
            title: '成功',
            message: '角色分配成功',
            type: 'success',
            duration: 2000
          })
          this.roleCancel()
        })
      }
    }
  }
</script>