<template>
  <div class="app-container">
    <el-col :span="5" style="padding-right:20px;">
      <el-card>
        <el-tree 
          v-loading="treeLoading"
          element-loading-text="拼命加载中..."
          element-loading-spinner="el-icon-loading"
          ref="treeData"
          node-key="dictId"
          :data="treeDate"
          :props="treeProps"
          default-expand-all
          @node-click="handleNodeClick"/>
      </el-card>
    </el-col>
    <el-col :span="19">
      <form-dialog ref="form" @refreshTree="refreshTree"/>
    </el-col>
  </div>
</template>
<script>
import { selectDictTree } from '@/api/system/dict/index'
import FormDialog from './components/form'

export default {
  components: {
    FormDialog
  },
  data() {
    return {
      treeProps: {
        label: 'dictName'
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
      selectDictTree().then(reponse => {
        this.treeDate = reponse.data
        // 默认展示第一菜单数据
        this.handleNodeClick(reponse.data[0])
        this.treeLoading = false
      })
    },
    handleNodeClick(data) {
      this.$refs.form.seeDict(data)
    },
    refreshTree() {
      this.tree()
    }
  }
}
</script>
