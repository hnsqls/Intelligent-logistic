<template>
    <el-form :model="form" label-width="auto" style="max-width: 600px">
      <el-form-item label="账号">
        <el-input v-model="form.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" />
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" />
      </el-form-item>

      <el-form-item label="状态">
        <el-switch v-model="form.status" />
      </el-form-item>
      <el-form-item label="头像">
        <!-- action是选择图片提交的地址 -->
        <el-upload
            class="avatar-uploader"
            action="http://localhost:10010/upload/uploadfile"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            >
        <!-- 缩略图 -->
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <!-- 默认上传图片显示的图片 -->
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>

     
      
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script lang="ts" setup>
  import { reactive,ref} from 'vue'
import { sysuserinstance } from '../request/request'
  
  // do not use same name with ref
  const form = ref({
    username: '',
    password: '',
    nickname: '',
    email: '',
    status: true,
    photo: "",
    
  })
  
  import { sysusersaveinstance } from "../request/request.js";
  import { ElMessage, ElMessageBox } from "element-plus";
  import { Plus } from '@element-plus/icons-vue'

import type { UploadProps } from 'element-plus'
import router from '../router/router';

const imageUrl = ref('')

//上传成功的回调函数
const handleAvatarSuccess: UploadProps['onSuccess'] = (
  response,
  uploadFile
) => {
  imageUrl.value = URL.createObjectURL(uploadFile.raw!)
  // 服务器返回图片的地址
  // console.log(response.extra);
  //将服务器图片返回的地址赋值给表单photo属性
  form.value.photo =response.extra;
}
//上传图片前判断格式，是否符合要求并提示
const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {

    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}
  
 
  function onSubmit (){
    //获取表格内容
    console.log(form.value)
    //发送网络请求，像后端服务发起新建用户请求
    sysusersaveinstance(form.value).then((resp) =>{

        if(resp.data.code != 0){
            
        ElMessage({
        type: "success",
        message: "修改成功",
        
        });
        router.push("/Main/Home");


        }

    })

  }

  function reset(){
    // console.log("ccc")
    //重置表格，就是将表格赋值为空，由于双向绑定，赋值为空即可改变
    form.value = {
        username: '',
        password: '',
        nickname: '',
        email: '',
        status: true,
        photo: "",
    }
    imageUrl.value= "";
 
  }
 


  </script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;

}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
  