<template>
  <div class="but-container">
    <el-card style="margin:10px 0px;">
      <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入" />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addButton">新增</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="editButton(null)" v-if="selectSize === 1">修改</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="editButton(null)" disabled v-else>修改</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteButton(null)" v-if="selectSize != 0">删除</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="deleteButton(null)" disabled v-else>删除</el-button>
        <el-button size="small" type="warning" icon="el-icon-download" @click="deleteButton">导出</el-button>
        <el-table
          :data="tableData"
          ref="userTable"
          fit
          highlight-current-row
          style="width: 100%"
          @selection-change="changeCheckBox">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="butName" label="按钮名称" />
          <el-table-column prop="permission" label="权限标示" />
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
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column prop="updateTime" label="更新时间" />
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success" icon="el-icon-edit" @click="editButton(scope.row.userId)"/>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="deleteButton(scope.row.userId)"/>
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
import { selectButtonList, deleteButtonByIds } from '@/api/system/button/index'
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
        menuId: ''
      },
      total: null,
      tableData: [],
      selectSize: 0
    }
  },
  methods: {
    page(menuId) {
      this.tableData = []
      this.query.menuId = menuId
      selectButtonList(this.query).then(reponse => {
        this.tableData = reponse.data
        this.total = reponse.total
      })
    },
    resetList() {
      this.tableData = []
    },
    addButton() {
      this.$refs.form.addButton()
    },
    editButton(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId)[0]
      }
      this.$refs.form.editButton(userId)
    },
    deleteButton(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId).join()
      }
      deleteButtonByIds(userId).then(() => {
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
