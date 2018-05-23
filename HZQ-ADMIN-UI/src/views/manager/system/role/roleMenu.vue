<template>
    <div id="roleMenu" v-loading="menuTreeLoading">
      <el-row>
        <el-col :span="6">
          <el-card>
            <el-tree
                :data="menuTree"
                ref="roleMenuTree"
                show-checkbox
                node-key="id"
                :default-expand-all="true"
                @node-click="clickTree"
                :props="defaultProps">
                </el-tree>
          </el-card>
        </el-col>
        <el-col :span="18">
          <div class="role-dialog-div">
            <el-table
              ref="buttonsTable"
              :data="list"
              style="width: 100%"
              :highlight-current-row="true"
              @select="changeTableCBox"
              class="system-table"
              >
                  <el-table-column
                    type="selection"
                    width="55">
                  </el-table-column>
                  <el-table-column
                  label="按钮名称">
                      <template scope="scope">
                          <span style="margin-left: 10px">{{ scope.row.name }}</span>
                      </template>
                  </el-table-column>
                  <el-table-column
                  label="编码">
                      <template scope="scope">
                          <span style="margin-left: 10px">{{ scope.row.code }}</span>
                      </template>
                  </el-table-column>
              </el-table>
          </div>
        </el-col>
      </el-row>
        <div slot="footer" class="dialog-footer" style="text-align:center;">
            <el-button @click="cancel()">取 消</el-button>
            <el-button type="primary" @click="addRoleMenu()">确 定</el-button>
        </div>
    </div>
</template>

<script>
import { tree } from '@/api/manager/system/menu/index'
import { getButtonByMenuIdOrRoleId } from '@/api/manager/system/button/index'
import { addRoleRes, getResIdByRoleId } from '@/api/manager/system/role/index'
export default {
  props: ['roleId'],
  created() {
    this.menuTreeLoading = true
    this.getTree()
  },
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'menuName'
      },
      menuTree: null,
      checkRes: {
        menuId: '',
        roleId: '',
        buttons: ''
      },
      list: null,
      roleMenusId: null,
      roleButtonsId: null,
      menuTreeLoading: false
    }
  },
  methods: {
    getTree() {
      tree('-1').then(response => {
        this.menuTree = response.data
        this.getMenuIdByRoleId()
      })
    },
    clickTree(data) {
      this.checkRes.menuId = data.id
      this.checkRes.roleId = ''
      this.getButtonByMenuId(data.id, this.checkRes)
    },
    getButtonByMenuId(id, obj) {
      getButtonByMenuIdOrRoleId(id, obj).then(response => {
        this.list = response.data
        this.$nextTick(() => {
          this.checkButtonByRoleIdAndMenuId()
        })
      })
    },
    checkButtonByRoleIdAndMenuId() {
      this.list.forEach(element => {
        if (this.roleButtonsId.indexOf(element.id) !== -1) {
          this.$refs.buttonsTable.toggleRowSelection(element)
        }
      })
      // this.list.forEach(row => {
      //     this.$refs.buttonsTable.toggleRowSelection(row)
      // })
      // this.checkRes.roleId = this.roleId
      // this.checkRes.menuId = id
      // getButtonByMenuIdOrRoleId(id, this.checkRes).then(response => {
      //   this.chckList = response.data
      //   this.chckList.forEach(row => {
      //     console.log(row)
      //     this.$refs.buttonsTable.toggleRowSelection(row, true);
      //   })
      // })
    },
    addRoleMenu() {
      var roleId = this.roleId
      var menus = this.$refs.roleMenuTree.getCheckedNodes()
      var menuId = menus.map(menu => menu.id).join()
      this.checkRes.menuId = menuId
      this.checkRes.roleId = roleId
      // var buttons = this.$refs.buttonsTable.selection.map(button => button.id).join()
      this.checkRes.buttons = this.roleButtonsId.join()
      addRoleRes(roleId, this.checkRes).then(() => {
        this.$notify({
          title: '成功',
          message: '资源添加成功',
          type: 'success',
          duration: 2000
        })
        this.cancel()
      })
    },
    getMenuIdByRoleId() {
      getResIdByRoleId(this.roleId).then(response => {
        this.resetChecked()
        this.$refs.roleMenuTree.setCheckedKeys(response.data.menuIds)
        this.menuTreeLoading = false
        this.roleMenusId = response.data.menuIds
        this.roleButtonsId = response.data.buttonIds
      })
    },
    resetChecked() {
      this.$refs.roleMenuTree.setCheckedKeys([])
    },
    cancel() {
      // 清楚按钮选中
      this.$refs.buttonsTable.clearSelection()
      this.$emit('roleMenuCancle', 'cancel')
    },
    changeTableCBox(selection, row) {
      var buttons = selection.map(button => button.id).join()
      if (buttons.indexOf(row.id) !== -1) { // 表示选中状态
        this.roleButtonsId.push(row.id)
      } else { // 取消状态
        var buIndex = this.roleButtonsId.indexOf(row.id)
        this.roleButtonsId.splice(buIndex, 1)
      }
    }
  }
}
</script>
<style>
.role-dialog-div{
  margin: 0px 15px;
}
</style>
