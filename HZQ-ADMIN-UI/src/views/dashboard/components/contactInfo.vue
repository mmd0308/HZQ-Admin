<template>
    <el-card style="width:350px">
        <div slot="header" class="clearfix">
            <svg-icon icon-class="phone" /> <span>联系信息</span>
        </div>
        <div class="contact-item">
            <svg-icon icon-class="hzq_network" /> 官网: <a style="color:blue" href="http://www.hzqing.com">http://admin.hzqing.com</a>
        </div>
         <div class="contact-item">
            <svg-icon icon-class="qq" /> QQ群: 
        </div>
         <div class="contact-item">
           <svg-icon icon-class="weixin" style="height:1.2em;width:1.2em" /> 微信:
        </div>
         <div class="contact-item">
            <svg-icon icon-class="reg-email" /> 邮箱: mmd0308@126.com
        </div>
    </el-card>

</template>

<script>
export default {

}
</script>

<style>
.contact-item{
    padding: 8px 0px;
    color: #606266;
}

</style>
