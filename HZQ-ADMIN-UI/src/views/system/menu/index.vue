<template>
  <div class="app-container">
    <el-col :span="5" style="padding-right:20px;">
      <el-card>
        <el-tree 
          v-loading="treeLoading"
          element-loading-text="拼命加载中..."
          element-loading-spinner="el-icon-loading"
          ref="treeData"
          node-key="menuId"
          :data="treeDate"
          :props="treeProps"
          @node-click="handleNodeClick"/>
      </el-card>
    </el-col>
    <el-col :span="19">
      <menu-form ref="form" />
      <button-index ref="butList" />
    </el-col>
  </div>
</template>
<script>
import { selectMenuTree } from '@/api/system/menu/index'
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
      treeDate: [],
      treeLoading: false
    }
  },
  created() {
    this.tree()
  },
  methods: {
    tree() {
      this.treeLoading = true
      selectMenuTree().then(reponse => {
        this.treeDate = reponse.data
        // 默认展示第一菜单数据
        this.handleNodeClick(reponse.data[0])
        this.treeLoading = false
      })
    },
    handleNodeClick(data) {
      this.$refs.form.seeMenu(data)
      this.$refs.butList.getMenuId(data.menuId)
    }
  }
}
</script>
