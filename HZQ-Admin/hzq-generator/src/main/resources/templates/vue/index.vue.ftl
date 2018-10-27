<template>
  <div class="app-container">
    <el-card style="margin-bottom:10px;">
      <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入" />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-plus" @click="add${className}">新增</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="edit${className}(null)" v-if="selectSize === 1">修改</el-button>
        <el-button size="small" type="success" icon="el-icon-edit" @click="edit${className}(null)" disabled v-else>修改</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="delete${className}(null)" v-if="selectSize != 0">删除</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete" @click="delete${className}(null)" disabled v-else>删除</el-button>
        <el-button size="small" type="warning" icon="el-icon-download" @click="delete${className}">导出</el-button>
        <el-table
          :data="tableData"
          ref="userTable"
          fit
          highlight-current-row
          style="width: 100%"
          @selection-change="changeCheckBox">
          <el-table-column type="selection" width="55" />
          <#list columns as col>
          <el-table-column prop="${col.attrName}" label="${col.columnComment}" />
          </#list>
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success" icon="el-icon-edit" @click="edit${className}(scope.row.userId)"/>
              <el-button size="mini" type="danger" icon="el-icon-delete" @click="delete${className}(scope.row.userId)"/>
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
import { select${className}List, delete${className}ByIds } from '@/api/${moudleName}/${classNameLower}/index'
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
      selectSize: 0
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      select${className}List(this.query).then(reponse => {
        this.tableData = reponse.data
        this.total = reponse.total
      })
    },
    add${className}() {
      this.$refs.form.add${className}()
    },
    edit${className}(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId)[0]
      }
      this.$refs.form.edit${className}(userId)
    },
    delete${className}(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId).join()
      }
      delete${className}ByIds(userId).then(() => {
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
