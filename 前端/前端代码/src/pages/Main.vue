<template>
    <el-row>
        <el-col :span="24">
             <!-- 顶处菜单 -->
            <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="handleSelect"
        >
            <el-menu-item index="1">智慧物流</el-menu-item>
            <el-sub-menu index="2">
            <template #title>个人中心</template>
            <el-menu-item index="2-1">个人主页</el-menu-item>
            <el-menu-item index="2-2">修改密码</el-menu-item>
            <el-menu-item index="2-3">退出登录</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="3" disabled>大屏展示</el-menu-item>
            </el-menu>
        </el-col>
    </el-row>

    <el-row>
        <el-col :span="4">
            <!-- 侧标菜单 -->
            <el-menu
                style="height: 660px"
                active-text-color="#ffd04b"
                background-color="#545c64"
                class="el-menu-vertical-demo"

                text-color="#fff"
                @open="handleOpen"
                @close="handleClose"
                :router = true
            >
            <!-- :router  是菜单中的一个属性，默认false。可以选择打开。index默认是坐标数字，开启router后，是跳转url的地址 -->
                <el-sub-menu index="1">
                <template #title>
                    <el-icon><location /></el-icon>
                    <span>用户管理</span>
                </template>
               
                    <el-menu-item index="/Main/SysuserAdd">   <el-icon><icon-menu /></el-icon>新增用户</el-menu-item>
                    <el-menu-item index="/Main/Home"> <el-icon><icon-menu /></el-icon>用户列表</el-menu-item>
                </el-sub-menu>
                <el-menu-item index="2">
                <el-icon><icon-menu /></el-icon>
                <span>商品管理</span>
                </el-menu-item>
                <el-menu-item index="/Main/Order" >
                <el-icon><document /></el-icon>
                <span>订单管理</span>
                </el-menu-item>
                <el-menu-item index="4">
                <el-icon><setting /></el-icon>
                <span>接口管理</span>
                </el-menu-item>
            </el-menu>
        </el-col>
          
        <el-col :span="20">
            <!-- 欢迎使用 -->
            <!-- 嵌入子路由 -->
            <router-view></router-view>
        </el-col>
    </el-row>

    
 
</template>

<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'


</script>

<style scoped="scoped">

</style>