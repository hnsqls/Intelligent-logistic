<template>
  <el-dialog v-model="dialogFormVisible" title="编辑管理员信息" width="40%">
    <el-form :model="edituserinfo">
      <el-form-item label="昵称">
        <el-input autocomplete="off" v-model="edituserinfo.nickname" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input autocomplete="off" v-model="edituserinfo.email" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input autocomplete="off" v-model="edituserinfo.password" />
      </el-form-item>
      <el-form-item label="状态">
        <el-switch v-model="edituserinfo.status"></el-switch>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editsubmit"> 修改 </el-button>
      </div>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogImgVisible" title="查看图片" width="40%">
    <el-image :src="imgurl">
        <template #error>
          <div class="image-slot">
            <el-icon><icon-picture /></el-icon>
          </div>
        </template>
      </el-image>
  </el-dialog>

  <el-form :model="searchform" label-width="auto" style="max-width: 600px">
    <el-row>
      <el-col :span="5">
        <el-input v-model="searchform.id" placeholder="请输入用户id"/>
      </el-col>
      <el-col :span="5">
        <el-input v-model="searchform.username" placeholder="请输入用户名"/>
      </el-col>
      <el-col :span="5">
        <el-input v-model="searchform.nickname" placeholder="请输入用户昵称"/> 
      </el-col>
      <el-col :span="4">      
        <el-input v-model="searchform.email" placeholder="请输入email"/>
      </el-col>

      <el-col :span="2">
        <el-button type="primary" @click="onsearch">搜索</el-button>
      </el-col>

      <el-col :span="3">
        <el-button type="info" @click="resetsearch">重置</el-button>
      </el-col>
    </el-row>

  </el-form>

  

  <el-table :data="tableData" height="440" style="width: 100%">
    <el-table-column prop="id" label="id" width="180" />
    <el-table-column prop="nickname" label="昵称" width="180" />
    <el-table-column prop="username" label="账号" />
    <el-table-column prop="email" label="邮箱" width="180" />
    <el-table-column prop="status" label="状态" width="180" >
      <template #default ="scope" >
        <el-switch  
        v-model="scope.row.status"
        @click="statusclick(scope.row)"></el-switch>

      </template>
    </el-table-column>
    <el-table-column prop="photo" label="头像" >
      <template #default="scope">
        <el-button size="small" @click="checkImg(scope.$index, scope.row)">
          查看头像
        </el-button>
        
      </template>
      
      

    </el-table-column>
    <el-table-column align="right">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
          编辑
        </el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
        >
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination background layout="prev, pager, next" 
  @current-change="currentchange"
  :page-count="pages" />
</template>

<script lang="ts" setup>
import { initCustomFormatter, ref } from "vue";
import {
  sysuserinstance,
  sysuserdelinstance,
  sysuserupdateinstance,
} from "../request/request";
import { ElMessage, ElMessageBox } from "element-plus";
import { Picture as IconPicture } from '@element-plus/icons-vue'
const tableData = ref([]);
const imgurl = ref()
const searchform = ref({username:"",id:"",nickname:"",email:""})//搜索的信息
const pages = ref([0]);//分页查询的总页数
//控制查看图片窗的显隐
const dialogImgVisible = ref(false);
//控制编辑框的显隐
const dialogFormVisible = ref(false);
//用来保存被点击了编辑按钮所在行的用户信息
const edituserinfo = ref({ nickname: "", email: "",password:'',status:'' });
//第一页数据
initData(1,5);

function initData(pagenum,pagesize){
  //访问管理员列表接口，获取所有管理员信息的组成的数组
sysuserinstance(pagenum,pagesize,searchform.value).then((resp) => {
  //resp响应，包含响应头，响应体，状态行
  //resp.data获取响应体内容，也就是包含code,msg,data三部分的json数据
  //resp.data.data获取json数据中data的值
  // let users = resp.data.data;//分页过后所需的数据在extra.list中
  let users = resp.data.extra.list;
  // console.log(users)
  // console.log(resp.data.extra.pages)
  tableData.value = users;

  pages.value = resp.data.extra.pages;
});
}


//index是删除按钮所在行在整个表格中的下标，row是按钮所在行的数据组成的对象
function handleDelete(index, row) {
  console.log(index);
  console.log(row);
  ElMessageBox.confirm("是否确定删除?", "警告", {
    confirmButtonText: "确定删除",
    cancelButtonText: "取消删除",
    type: "warning",
  })

    //点击确定删除按钮
    .then(() => {
      //发送网络请求，访问按照id删除用户的接口
      sysuserdelinstance(row.id).then((resp) => {
        if (resp.data.code == 1) {
          ElMessage({
            type: "success",
            message: "删除成功",
          });
          //删除响应式数据tableData中对应的记录，让列表刷新
          //splice是js中数据的函数，第一个参数是指定下标，1表示一个，从指定下标开始删除1一个
          tableData.value.splice(index, 1);
        } else {
          ElMessage({
            type: "info",
            message: "删除失败",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消删除",
      });
    });
}

function handleEdit(index, row) {
  //开启提示框
  dialogFormVisible.value = true;
  //双向绑定，将数据在编辑框中显示
  edituserinfo.value = row;
}

function editsubmit() {
  dialogFormVisible.value = false;
  //发送网络请求，访问修改用户信息的接口
  //edituserinfo永远等于点击的编辑按钮所在行的最新数据
  requestEdit(edituserinfo.value)
  

}
function currentchange(Number){
initData(Number,5);

}

//更新函数
function requestEdit(data){
  sysuserupdateinstance(data).then((resp) => {
    if(resp.data.code==1){

        ElMessage({
        type: "success",
        message: "修改成功",
        
      });
    } 
  });

}

//点击修改状态
function statusclick(data){
  // console.log(data)
  requestEdit(data);
}


//查看图片
function checkImg(index,row){
  
  console.log(row)
  console.log(row.photo)
  dialogImgVisible.value = true;
  imgurl.value ="http://localhost:10010/staticres/img/" +row.photo
}
//搜索信息
function onsearch(){
  //获取搜索表单的内容--》双向绑定传参即可
  
  //网络请求-----》服务器接口
  initData(1,5)

}

//重置搜索信息并查询所有
function resetsearch(){
  searchform.value={
    id:"",
    username:"",
    nickname:"",
    email:"",
  }
  initData(1,5);
}
</script>
