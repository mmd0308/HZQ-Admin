<template>
    <el-card style="width:450px; color: #606266;">
        <div slot="header" class="clearfix">
            <svg-icon icon-class="hzq_donation" style="height:1.2em;width:1.2em"  /> <span>捐赠</span>
        </div>
        <el-row>
            <el-col :span="12">
                <img width="200" height="200" src="@/assets/pay/weixin.png" class="image">
                <div style="padding: 14px; text-align:center">
                    <span>微信捐赠</span>
                </div>
            </el-col>
            <el-col :span="12">
                <img width="200" height="200" src="@/assets/pay/zhifubao.png" class="image">
                <div style="padding: 14px; text-align:center">
                    <span>支付宝捐赠</span>
                </div>
            </el-col>
        </el-row>
    </el-card>
</template>

<script>
export default {

}
</script>

<style>

</style>
