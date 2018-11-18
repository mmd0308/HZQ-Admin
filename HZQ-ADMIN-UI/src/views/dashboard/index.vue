<template>
  <div class="dashboard-container">
    <el-row>
      <el-col :span="7">
        <!-- 联系方式 -->
        <contact-info />
      </el-col>
      <el-col :span="9">
        <!-- 捐赠信息 -->
        <donation />
      </el-col>
      <el-col :span="8">
        <!-- 获取系统信息 -->
        <system />
      </el-col>
    </el-row>
  </div>
</template>

<script>
// 联系信息
import ContactInfo from './components/contactInfo'
// 捐赠
import Donation from './components/donation'
// 获取系统信息
import System from './components/system'
export default {
  name: 'Dashboard',
  components: {
    ContactInfo,
    Donation,
    System
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
