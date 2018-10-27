<template>
  <div class="app-container">
    <el-col :span="5" style="padding-right:20px;">
      <el-card>
      <el-tree :data="treeDate" :props="treeProps" @node-click="handleNodeClick"/>
      </el-card>
    </el-col>
    <el-col :span="19">
      <menu-form ref="form" @refreshList="refreshList"/>
      <button-index ref="butList" />
    </el-col>
  </div>
</template>
<script>
import { selectMenuTree, deleteMenuByIds } from '@/api/system/menu/index'
import MenuForm from './components/form'
import ButtonIndex from '@/views/system/button/index'
export default {
  components: {
    MenuForm,
    ButtonIndex
  },
  data() {
    return {
      treeProps: {
        label: 'menuName'
      },
      query: {
        pageNum: 1,
        pageSize: 10
      },
      total: null,
      treeDate: [],
      selectSize: 0
    }
  },
  created() {
    this.tree()
  },
  methods: {
    tree() {
      selectMenuTree().then(reponse => {
        this.treeDate = reponse.data
      })
    },
    addMenu() {
      this.$refs.form.addMenu()
    },
    editMenu(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId)[0]
      }
      this.$refs.form.editMenu(userId)
    },
    deleteMenu(userId) {
      if (userId == null) {
        userId = this.$refs.userTable.selection.map(item => item.userId).join()
      }
      deleteMenuByIds(userId).then(() => {
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
