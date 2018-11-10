<template>
  <div class="app-container">
    <el-card style="margin-bottom:10px;">
      <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入用户名" v-model="query.userName" @keyup.enter.native="page"  />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search" @click="page">查找</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addUser">新增</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="editUser(null)" v-if="selectSize === 1">修改</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="editUser(null)" disabled v-else>修改</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteUser(null, null)" v-if="selectSize != 0">删除</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteUser(null, null)" disabled v-else>删除</el-button>
        <!-- <el-button size="small" type="warning" icon="el-icon-download" @click="deleteUser">导出</el-button> -->
        <el-table
          v-loading="tableload"
          element-loading-text="拼命加载中..."
          element-loading-spinner="el-icon-loading"
          :data="tableData"
          ref="userTable"
          fit
          highlight-current-row
          style="width: 100%"
          @selection-change="changeCheckBox">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="userName" label="姓名" width="180" />
          <el-table-column prop="loginName" label="登陆账号" />
          <el-table-column prop="phone" label="手机号码" />
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column prop="updateTime" label="更新时间" />
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success" icon="el-icon-edit" @click="editUser(scope.row.userId)"/>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="deleteUser(scope.row.userId, scope.row.userName)"/>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-pagination
        :current-page="query.pageNum"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="query.pageSize"
        :total="total"
        style="float:right; padding:10px 0px"
        layout="total, sizes, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"/>
    </el-card>
    <form-dialog ref="form" @refreshList="refreshList"/>
  </div>
</template>
<script>
import { selectUserList, deleteUserByIds } from '@/api/system/user/index'
import FormDialog from './components/form'

export default {
  components: {
    FormDialog
  },
  data() {
    return {
      query: {
        pageNum: 1,
        pageSize: 10,
        userName: ''
      },
      total: null,
      tableData: [],
      selectSize: 0,
      tableload: false
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      this.tableload = true
      selectUserList(this.query).then(reponse => {
        this.tableData = reponse.data
        this.total = reponse.total
        this.tableload = false
      })
    },
    addUser() {
      this.$refs.form.addUser()
    },
    editUser(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId)[0]
      }
      this.$refs.form.editUser(userId)
    },
    deleteUser(userId, userName) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId).join()
      }
      if (userName == null) {
        userName = this.$refs.userTable.selection.map(item => item.userName).join()
      }
      this.$confirm('此操作将永久删除[' + userName + ']用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deleteUserByIds(userId).then(() => {
          this.page()
        })
      })
    },
    refreshList() {  
      this.page()
    },
    changeCheckBox(val) {
      // 设置选中行数
      this.selectSize = this.$refs.userTable.selection.map(item => item.userId).length
    },
    handleSizeChange(val) {
      this.query.pageSize = val
      this.page()
    },
    handleCurrentChange(val) {
      this.query.pageNum = val
      this.page()
    }
  }
}
</script>
