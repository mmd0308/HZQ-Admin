<template>
  <div class="app-container">
    <el-card style="margin-bottom:10px;">
      <el-form ref="form" :model="genRuleForm" label-width="150px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="包路径:">
              <el-input v-model="genRuleForm.packageName" @change="changPackageName" @propertychange="changPackageName" placeholder="com.hzqing.system"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="模块名称:">
              <el-input v-model="genRuleForm.moudleName" placeholder="system" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="代码生成者:">
              <el-input v-model="genRuleForm.author" placeholder="hzqing.com" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="16">
            <el-form-item label="实体类生成方案:">
              <el-radio-group v-model="genRuleForm.entityRule"  >
                <el-radio label="L" >
                  Lombok注解方式 
                  <el-tooltip class="item" effect="dark" placement="bottom">
                    <div slot="content">
                      <span style="color:#E69317"> import </span> lombok.<span style="color:#FFEF28">Data</span>;<br/>
                      <span style="color:#FFEF28"> @Data</span><br/>
                       <span style="color:#E69317"> public class </span> User {<br/>
                          &nbsp;&nbsp;&nbsp;&nbsp;private String <span style="color:#FF69B4"> userName; </span><br/>
                      }
                    </div>
                    <svg-icon icon-class="hzq-help" style="color:#c0c4cc" />
                  </el-tooltip>
                </el-radio>
                <el-radio label="C" >
                  常规生成set,get方法 
                  <el-tooltip class="item" effect="dark" placement="bottom">
                    <div slot="content">
                      <span>
                        <span style="color:#E69317"> public class </span> User {<br/>
                          &nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#E69317"> private</span> String <span style="color:#FF69B4">userName</span>;<br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;public String getUserName() {<br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#E69317"> return</span> <span style="color:#FF69B4">userName</span>;<br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#E69317"> public void</span> setUserName(String userName) {<br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#E69317"> this</span>.<span style="color:#FF69B4">userName</span> = userName;<br/>
                            &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
                        }
                      </span>
                    </div>
                    <svg-icon icon-class="hzq-help" style="color:#c0c4cc" />
                  </el-tooltip>
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="包路径">
              <el-input />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card style="margin-bottom:10px;">
      <el-input class="filter-item" size="small" style="width: 300px;" placeholder="请输入表名称" />
      <el-button class="filter-item" size="small" type="success" icon="el-icon-search">查找</el-button>
      <el-button class="filter-item" size="small" type="primary" icon="el-icon-refresh">重置</el-button>
    </el-card>
    <el-card>
      <div style="background:#fff">
        <el-button size="small" type="primary" icon="el-icon-download" @click="generatorCode(null)">批量生成</el-button>
        <el-table
          :data="tableData"
          fit
          ref="generatorTable"
          highlight-current-row
          style="width: 100%">
          <el-table-column type="selection" width="55" />
          <el-table-column prop="tableName" label="表名称" width="180" />
          <el-table-column prop="tableComment" label="表描述" width="180" />
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column prop="updateTime" label="更新时间" />
          <el-table-column label="操作" align="center" width="300" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="success"  @click="generatorCode(scope.row.tableName)" >生成代码</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-pagination
        :current-page="query.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="query.pageSize"
        :total="total"
        style="float:right; padding:10px 0px"
        layout="total, sizes, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"/>
    </el-card>
  </div>
</template> 
<script>
import { selectTableList, generatorCode } from '@/api/tools/generator/index'
export default {
  data() {
    return {
      query: {
        pageNum: 1,
        pageSize: 10
      },
      total: 0,
      tableData: [],
      genRuleForm: {
        entityRule: 'L',
        packageName: '',
        moudleName: '',
        author: ''
      }
    }
  },
  created() {
    this.page()
  },
  methods: {
    page() {
      selectTableList(this.query).then(reponse => {
        this.tableData = reponse.data
      })
    },
    generatorCode(tableName) {
      if (tableName == null) {
        tableName = this.$refs.generatorTable.selection.map(item => item.tableName);
      }
      window.location.href = process.env.BASE_API + '/hzq/tools/generator/generatorCode?tables=' + tableName + '&entityRule=' + this.genRuleForm.entityRule +
                            '&packageName=' + this.genRuleForm.packageName + '&moudleName=' + this.genRuleForm.moudleName + '&author=' + this.genRuleForm.author;
    },
    changPackageName(){
      this.genRuleForm.moudleName = this.genRuleForm.packageName.substring(this.genRuleForm.packageName.lastIndexOf(".")+1)
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
