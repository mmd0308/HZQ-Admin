<template>
    <div id="menu">
      <el-row  v-loading.body="listLoading">
        <el-col :span="5">
            <el-input placeholder="输入关键字进行过滤" v-model="filterText">
            </el-input>
            <el-tree class="filter-tree" 
              style="margin-top:10px;"  
              node-key="id"
              :data="treeDate" 
              :props="defaultProps" 
              :default-expand-all="true"  
              :filter-node-method="filterNode" 
              :default-expanded-keys="[0,1]" 
              ref="tree"
              @node-click="clickTree" 
              @node-expand="expandTree">
            </el-tree>
        </el-col>
        <el-col :span="19">
             <div class="right-layout-from">
                <div class="top-button">
                      <el-button type="primary" native-type="submit"  @click="toCreate()" icon="plus">添加</el-button>
                </div>
                 <el-card class="box-card" style="margin-bottom: 10px;">
                    <el-table
                      :data="list"
                      style="width: 100%"
                      :highlight-current-row="true"
                      class="system-table"
                      >
                        <el-table-column align="center" label="序号" type="index" width="50"></el-table-column>
                        <el-table-column
                        label="名称"
                        width="100px"
                        sortable>
                            <template scope="scope">
                                <span style="margin-left: 10px">{{ scope.row.dictName }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="编码">
                            <template scope="scope">
                                <span style="margin-left: 10px">{{ scope.row.code }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                        width="100px"
                        label="是否启用" >
                            <template scope="scope">
                                <span style="margin-left: 10px">
                                  <el-tag v-if="scope.row.enabled == '1'" type="success">启用</el-tag>
                                  <el-tag v-else type="danger">禁用</el-tag>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column
                        width="150px"
                        label="类型">
                            <template scope="scope">
                                <span style="margin-left: 10px" v-for="(item,index) in types" :key="index" v-if="item.value == scope.row.dictType">{{ item.label }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                        label="备注" >
                            <template scope="scope">
                                <span style="margin-left: 10px">{{ scope.row.description }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="200px">
                          <template scope="scope">
                              <el-button
                                  size="mini"
                                  type="success"
                                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                              <el-button
                                  size="mini"
                                  type="danger"
                                  v-if="scope.row.dictType === 'BUS'"
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
                </el-card>
             </div>

             <!--编辑框-->
          <el-dialog  :visible.sync="dialogFormVisible" :before-close="handleClose"  width="25%" :title="title">
              <el-form :model="form" :inline="false" :rules="rules" ref="form" label-width="90px">
                  <el-form-item label="名称" prop="dictName" >
                      <el-input v-model="form.dictName" placeholder="请输入名称" style="width:80%" ></el-input>
                  </el-form-item>
                  <el-form-item label="父级节点" prop="parentName" >
                      <el-input v-model="form.parentName" disabled style="width:80%" ></el-input>
                  </el-form-item>
                  <el-form-item label="菜单类型" prop="dictType">
                    <el-select v-model="form.dictType" placeholder="请选择" style="width:80%" >
                      <el-option
                        v-for="item in types"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="编码" prop="code">
                      <el-input  v-model="form.code" placeholder="请输入编码" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="是否可用" prop="enabled">
                      <el-switch  active-value='1'  inactive-value='0' v-model="form.enabled"></el-switch>
                  </el-form-item>
                  <el-form-item label="描述" prop="description">
                      <el-input  v-model="form.description" placeholder="请输入描述内容" style="width:80%"></el-input>
                  </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                  <el-button @click="cancel('form')">取 消</el-button>
                  <el-button v-if="status=='create'" type="primary" @click="create('form')">确 定</el-button>
                  <el-button v-else type="primary" @click="update('form')">确 定</el-button>
              </div>
          </el-dialog>
        </el-col>
      </el-row>

    </div>
</template>

<script>
  import { tree, addObj, putObj, delObj, page, checkCode } from '@/api/manager/system/dict/index'
  import ButtonView from '@/views/manager/system/button/index'
  import { guid } from '@/utils/uuid'
  export default {
    components: {
      ButtonView
    },
    watch: {
      filterText(val) {
        this.$refs.tree.filter(val)
      }
    },
    created() {
      this.getTree()
    },
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
        form: this.initObj(),
        parent: {
          parentId: '',
          parentName: ''
        },
        filterText: '',
        defaultProps: {
          children: 'children',
          label: 'dictName'
        },
        treeDate: null,
        listLoading: false,
        dialogFormVisible: false,
        status: '',
        types: [
          {
            label: '系统字典',
            value: 'SYS'
          },
          {
            label: '业务字典',
            value: 'BUS'
          }
        ],
        title: '',
        rules: {
          dictName: [
            { required: true, message: '请输入字典名称', trigger: 'blur' }
          ],
          dictType: [
            { required: true, message: '请选择类型', trigger: 'blur' }
          ],
          code: { required: true, trigger: 'blur', validator: validateCode },
          enabled: { required: true, trigger: 'blur', message: '请选择是否启用' }
        },
        list: null,
        total: null,
        listQuery: {
          page: 1,
          pageSize: 10,
          parentId: ''
        }
      }
    },
    methods: {
      initObj() {
        return {
          id: guid(),
          dictName: '',
          parentId: '',
          dictType: '',
          code: '',
          enabled: '',
          description: '',
          parentName: ''
        }
      },
      queryList() {
        this.getList()
      },
      getList() {
        page(this.listQuery).then(response => {
          this.list = response.data.list
          this.total = response.data.total
        })
      },
      filterNode(value, data) {
        if (!value) return true
        return data.dictName.indexOf(value) !== -1
      },
      getTree() {
        tree('-1').then(response => {
          this.treeDate = response.data
          this.listQuery.parentId = response.data[0].id // 根据父级别id获取列表
          this.parent.parentId = response.data[0].id
          this.parent.parentName = response.data[0].dictName
          this.getList()
        })
      },
      clickTree(date) {
        this.parent.parentName = date.dictName
        this.parent.parentId = date.id
        this.listQuery.parentId = date.id
        this.getList()
      },
      expandTree(date) {
        console.log('expand')
      },
      toCreate() {
        this.status = 'create'
        this.dialogFormVisible = true
        this.title = '新增字典'
        this.form.parentName = this.parent.parentName
        this.form.parentId = this.parent.parentId
      },
      create(form) {
        this.$refs[form].validate(vaild => {
          if (vaild) {
            addObj(this.form).then(() => {
              this.$notify({
                title: '成功',
                message: '菜单字典成功',
                type: 'success',
                duration: 2000
              })
              if (this.form.enabled === '1') {
                this.$refs.tree.append(this.form, this.form.parentId)
              }
              this.listQuery.parentId = this.form.parentId
              this.getList()
              this.cancel()
            })
          }
        })
      },
      handleEdit(index, row) {
        this.status = 'update'
        this.title = '更新字典'
        this.form = row
        this.dialogFormVisible = true
        this.form.parentName = this.parent.parentName
      },
      update(form) {
        this.$refs[form].validate(vaild => {
          if (vaild) {
            putObj(this.form.id, this.form).then(() => {
              // this.getTree()
              // if (this.form.enabled === '1') {
              //   this.$refs.tree.append(this.form,this.form.parentId)
              // }
              this.getTree()
              this.listQuery.parentId = this.form.parentId
              this.getList()
              this.cancel()
              this.$notify({
                title: '成功',
                message: '字典更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(index, row) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delObj(row.id).then(response => {
            if (response.data) {
              if (row.enabled === '1') {
                this.$refs.tree.remove(row.id)
              }
              this.listQuery.parentId = row.parentId
              this.getList()
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: '失败',
                message: '请先删除下级字典',
                type: 'error',
                duration: 2000
              })
            }
          })
        })
      },
      resetTemp() {
        this.form = this.initObj()
      },
      cancel() {
        this.dialogFormVisible = false
        this.resetTemp()
      },
      handleClose(done) {
        this.cancel()
        done()
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
<style>
#menu{
    padding: 20px;
}
.right-layout-from{
  margin-left: 20px;
}
.top-button{
  margin-bottom: 20px;
}
</style>

