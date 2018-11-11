<template>
    <el-card style="width:350px;color: #606266;">
        <div slot="header" class="clearfix">
            <svg-icon icon-class="hzq_system" /> <span>系统信息</span>
        </div>
        <div class="contact-item">
            <svg-icon icon-class="user" /> 用户登陆名称: {{name}}
        </div>
        <div class="contact-item">
            <svg-icon icon-class="hzq_key" /> 用户id:{{ userId }}
        </div>
        <div class="contact-item">
            <svg-icon icon-class="hzq_token" /> Token:{{ name }}
        </div>
         <div class="contact-item">
            <svg-icon icon-class="role" /> 用户拥有角色:<span v-for="role in roles" :key="role">{{ role }}</span>
        </div>
    </el-card>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      userId: ''
    }
  },
    computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>

<style>
</style>
