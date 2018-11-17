<template>
  <div class="app-container">
    <el-card style="margin-bottom:10px;">
      <el-input v-model="query.roleName" class="filter-item" size="small" style="width: 300px;" placeholder="请输入" @keyup.enter.native="page" />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search" @click="page" >查找</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addRole">新增</el-button>
        <el-button v-if="selectSize === 1" size="small" type="success" icon="el-icon-edit" @click="editRole(null)">修改</el-button>
        <el-button v-else size="small" type="success" icon="el-icon-edit" disabled @click="editRole(null)">修改</el-button>
        <el-button v-if="selectSize != 0" size="small" type="danger" icon="el-icon-delete" @click="deleteRole(null, null)">删除</el-button>
        <el-button v-else size="small" type="danger" icon="el-icon-delete" disabled @click="deleteRole(null, null)">删除</el-button>
        <el-table
          v-loading="tableload"
          ref="roleTable"
          :data="tableData"
          element-loading-text="拼命加载中..."
          element-loading-spinner="el-icon-loading"
          fit
          highlight-current-row
          style="width: 100%"
          @selection-change="changeCheckBox">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="roleName" label="角色名称" />
          <el-table-column label="是否启用">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.enabled == 'N'" type="warning" size="mini" color="#FFA500" style="color:#FFFFFF" >
                禁用
              </el-tag>
              <el-tag v-if="scope.row.enabled == 'Y'" size="mini" color="#1ab394" style="color:#FFFFFF" >
                启用
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="permission" label="权限编码" />
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column prop="updateTime" label="更新时间" />
          <el-table-column prop="remark" label="备注" />
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success" icon="el-icon-edit" @click="editRole(scope.row.roleId)"/>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="deleteRole(scope.row.roleId, scope.row.roleName)"/>
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
        pageSize: 10,
        roleName: ''
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
    editRole(roleId) {
      if (roleId == null) {
        roleId = this.$refs.roleTable.selection.map(item => item.roleId)[0]
      }
      this.$refs.form.editRole(roleId)
    },
    deleteRole(roleId, roleName) {
      if (roleId == null) {
        roleId = this.$refs.roleTable.selection.map(item => item.roleId).join()
      }
      if (roleName == null) {
        roleName = this.$refs.roleTable.selection.map(item => item.roleName).join()
      }
      this.$confirm('此操作将永久删除[' + roleName + ']角色?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        typr: 'warning'
      }).then(() => {
        deleteRoleByIds(roleId).then(() => {
          this.page()
        })
      })
    },
    refreshList() {
      this.page()
    },
    changeCheckBox(val) {
      // 设置选中行数
      this.selectSize = this.$refs.roleTable.selection.map(item => item.roleId).length
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
