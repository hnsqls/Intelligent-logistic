<template>
    <div>
        <h1>欢迎使用智慧物流管理系统</h1>
        <h2>{{ now}}</h2>
    </div>
</template>
<script setup>
import dayjs from "dayjs";
import {ref} from "vue";
   
    const now= ref("");
   
//每1s刷新一下   
setInterval(function(){
    now.value=dayjs().format("YYYY年MM月DD日 hh:mm:ss");

}, 1000)//——会不停的调用函数

</script>
<style scoped ="scoped">

</style>
