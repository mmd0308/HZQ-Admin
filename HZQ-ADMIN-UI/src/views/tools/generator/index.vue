<template>
  <div class="app-container">
    <el-card style="margin-bottom:10px;">
      <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入表名称" />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
      <el-button class="filter-item" size="small" type="primary" icon="el-icon-refresh">重置</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-download">批量下载</el-button>
        <el-table
          :data="tableData"
          fit
          highlight-current-row
          style="width: 100%">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="date" label="表名称" width="180" />
          <el-table-column prop="name" label="表描述" width="180" />
          <el-table-column prop="address" label="创建时间" />
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success" >生成代码</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-pagination
        :current-page="currentPage4"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="100"
        :total="400"
        style="float:right; padding:10px 0px"
        layout="total, sizes, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"/>
    </el-card>
  </div>
</template>
<script>
import { selectTableList } from '@/api/tools/generator/index'

export default {
  data() {
    return {
      currentPage4: 4,
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  created() {
    this.handleSizeChange()
  },
  methods: {
    handleSizeChange(val) {
      selectTableList().then(reponse => {
        console.log(reponse)
      })
    },
    handleCurrentChange(val) {
    }
  }
}
</script>
