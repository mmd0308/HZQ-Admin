<template>
    <div id="menu">
      <el-row  v-loading.body="listLoading">
        <el-col :span="5">
            <el-input placeholder="输入关键字进行过滤" v-model="filterText">
            </el-input>
            <el-tree 
            class="filter-tree" 
            style="margin-top:10px;"  
            node-key="id"
            :data="menuTreeDate" 
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
                <div v-if="this.state == 'see'" class="top-button">
                    <el-button-group>
                    <el-button type="primary" native-type="submit"  @click="toCreate()" icon="plus">添加</el-button>
                    <el-button type="primary"  @click="toUpdate()" icon="edit">编辑</el-button>
                    <el-button type="danger"  @click="toDeleted()" icon="delete">删除</el-button>
                    </el-button-group>
                </div>
                <div  class="top-button">
                    <el-button v-if="this.state == 'add'" type="primary" native-type="submit"  @click="createMenu('form')">保存</el-button>
                    <el-button v-if="this.state == 'edit'" type="primary" native-type="submit"  @click="updateMenu('form')">保存</el-button>
                </div>
                 <el-card class="box-card" style="margin-bottom: 10px;">
                     <el-form :model="form"  ref="form" label-width="120px" :rules="rules" >
                        <el-row aria-disabled="">
                            <el-col :span="8">
                            <el-form-item  label="菜单名称" prop="menuName">
                                <el-input v-model="form.menuName" v-if="this.state == 'see'" disabled="disabled" ></el-input>
                                <el-input v-model="form.menuName" v-else ></el-input>
                            </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="父级节点" prop="parentName">
                                    <el-input v-model="form.parentName" disabled="disabled"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                            <el-form-item label="菜单编码" prop="code">
                                <el-input v-model="form.code" v-if="this.state == 'see'" disabled="disabled" ></el-input>
                                <el-input v-model="form.code" v-else></el-input>
                            </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="资源路径" prop="href">
                                    <el-input v-if="this.state == 'see'" v-model="form.href" disabled="disabled"></el-input>
                                    <el-input v-else v-model="form.href" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="前端组件" prop="component">
                                    <el-input v-if="this.state == 'see'" v-model="form.component" disabled="disabled"></el-input>
                                    <el-input v-else v-model="form.component" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="图标" prop="icon">
                                    <el-input v-if="this.state == 'see'" v-model="form.icon" disabled="disabled"></el-input>
                                    <el-input v-else v-model="form.icon" ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="8">
                              <el-form-item label="排序" prop="sort">
                                <el-input v-if="this.state == 'see'" v-model="form.sort" disabled="disabled"></el-input>
                                <el-input v-else v-model="form.sort" ></el-input>
                              </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="是否可用" prop="enabled">
                                    <el-switch active-value = 1  inactive-value = 0 v-model="form.enabled"></el-switch>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="菜单类型" prop="menuType">
                                    <el-select v-model="form.menuType" placeholder="请选择菜单类型" v-if="this.state == 'see'" disabled="disabled" >
                                        <el-option v-for="item in dicts" :key="item.id" :label="item.dictName" :value="item.id"></el-option>
                                    </el-select>
                                    <el-select v-model="form.menuType" placeholder="请选择菜单类型" v-else >
                                        <el-option v-for="item in dicts" :key="item.id" :label="item.dictName" :value="item.id"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                          <el-col :span="24">
                            <el-form-item label="机构说明" prop="note">
                              <el-input type="textarea" v-model="form.note" v-if="this.state == 'see'" disabled="disabled"></el-input>
                              <el-input type="textarea" v-model="form.note" v-else></el-input>
                            </el-form-item>
                          </el-col>
                        </el-row>
                        </el-form>
                </el-card>
                <el-card>
                  <div slot="header" class="clearfix">
                    <span>按钮管理</span>
                  </div>
                  <button-view :menuId="form.id" ref="button"></button-view>
                </el-card>
             </div>
        </el-col>
      </el-row>
    </div>
</template>

<script>
  import { tree, addObj, putObj, delObj, checkCode } from '@/api/manager/system/menu/index'
  import { getDictByCode } from '@/api/manager/system/dict/index'
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
      this.getDictByCode()
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
        state: 'see',
        form: this.initObj(),
        filterText: '',
        defaultProps: {
          children: 'children',
          label: 'menuName'
        },
        menuTreeDate: null,
        listLoading: false,
        dicts: [],
        rules: {
          code: { required: true, trigger: 'blur', validator: validateCode },
          enabled: { required: true, trigger: 'blur', message: '请选择是否启用' }
        },
        parent: {
          parentId: '',
          parentName: ''
        }
      }
    },
    methods: {
      initObj() {
        return {
          id: guid(),
          menuName: '',
          code: '',
          parentId: '',
          parentName: '',
          href: '',
          component: '',
          icon: '',
          menuType: '',
          enabled: '0',
          note: '',
          sort: null
        }
      },
      filterNode(value, data) {
        if (!value) return true
        return data.menuName.indexOf(value) !== -1
      },
      getTree() {
        tree('-1').then(response => {
          this.menuTreeDate = response.data
          this.form = response.data[0]
          this.state = 'see'
          this.$refs.button.getList(this.form.id)
        })
      },
      clickTree(date) {
        this.form = date
        this.state = 'see'
        this.$refs.button.getList(date.id)
      },
      expandTree(date) {
        console.log('expand')
      },
      toCreate() {
        this.state = 'add'
        var parentId = this.form.id
        var name = this.form.menuName
        this.resetTemp()
        this.form.parentId = parentId
        this.form.parentName = name
      },
      createMenu(form) {
        this.$refs[form].validate(vaild => {
          if (vaild) {
            addObj(this.form).then(() => {
              // this.getTree()
              this.$refs.tree.append(this.form, this.form.parentId)
              this.state = 'see'
              this.$refs.button.getList(this.form.id)

              this.$notify({
                title: '成功',
                message: '菜单新增成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      toUpdate() {
        this.state = 'edit'
      },
      updateMenu(form) {
        this.$refs[form].validate(vaild => {
          if (vaild) {
            putObj(this.form.id, this.form).then(() => {
              this.getTree()
              this.$notify({
                title: '成功',
                message: '菜单修改成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      toDeleted() {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delObj(this.form.id).then(response => {
            if (response.data) {
              // this.getTree()
              this.$refs.tree.remove(this.form.id)
              this.form = this.$refs.tree.getNode(this.form.parentId).data
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: '失败',
                message: '请先删除下级菜单',
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
      getDictByCode() {
        getDictByCode('DICT_MENU_TYPE').then(response => {
          this.dicts = response.data
        })
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
.grid-content{
    border-radius: 4px;
    min-height: 36px;
}
</style>

