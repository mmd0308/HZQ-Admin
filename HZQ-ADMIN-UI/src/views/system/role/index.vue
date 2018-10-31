<template>
  <div class="app-container">
    <el-card style="margin-bottom:10px;">
      <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入" />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addRole">新增</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="editRole(null)" v-if="selectSize === 1">修改</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="editRole(null)" disabled v-else>修改</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteRole(null)" v-if="selectSize != 0">删除</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteRole(null)" disabled v-else>删除</el-button>
        <el-button size="small" type="warning" icon="el-icon-download" @click="deleteRole">导出</el-button>
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
          <el-table-column prop="roleName" label="角色名称" />
          <el-table-column prop="roleSort" label="顺序" />
          <el-table-column  label="是否启用">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.enabled == 'N'" type="warning" size="mini" color="#FFA500" style="color:#FFFFFF" >
                禁用
              </el-tag>
              <el-tag v-if="scope.row.enabled == 'Y'" size="mini" color="#1ab394" style="color:#FFFFFF" >
                启用
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="permCode" label="权限编码" />
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column prop="updateTime" label="更新时间" />
          <el-table-column prop="remark" label="备注" />
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success" icon="el-icon-edit" @click="editRole(scope.row.userId)"/>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="deleteRole(scope.row.userId)"/>
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
import { selectRoleList, deleteRoleByIds } from '@/api/system/role/index'
import FormDialog from './components/form'

export default {
  components: {
    FormDialog
  },
  data() {
    return {
      query: {
        pageNum: 1,
        pageSize: 10
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
      selectRoleList(this.query).then(reponse => {
        this.tableData = reponse.data
        this.total = reponse.total
        this.tableload = false
      })
    },
    addRole() {
      this.$refs.form.addRole()
    },
    editRole(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId)[0]
      }
      this.$refs.form.editRole(userId)
    },
    deleteRole(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId).join()
      }
      deleteRoleByIds(userId).then(() => {
        this.page()
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
