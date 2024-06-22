<template>

<!-- 修改订单弹窗 -->
<el-dialog v-model="dialogFormVisible" title="修改订单信息" width="40%">
    <el-form :model="edituserinfo">
      <el-form-item label="收货人">
        <el-input autocomplete="off" v-model="edituserinfo.person" />
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input autocomplete="off" v-model="edituserinfo.tel" />
      </el-form-item>
      <el-form-item label="地址">
        <el-input autocomplete="off" v-model="edituserinfo.addr" />
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
  <!-- 创建订单弹窗 -->
  <el-dialog v-model="dialogFormVisible1" title="填写订单信息" width="40%">
    <el-form :model="edituserinfo1">
      <el-form-item label="收货人">
        <el-input autocomplete="off" v-model="edituserinfo1.person" />
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input autocomplete="off" v-model="edituserinfo1.tel" />
      </el-form-item>
      <el-form-item label="商品">
        <el-input autocomplete="off" v-model="edituserinfo1.product" />
      </el-form-item>
      <el-form-item label="地址">
        <el-input autocomplete="off" v-model="edituserinfo1.addr" />
      </el-form-item>
      <el-form-item label="状态">
        <el-switch v-model="edituserinfo1.status"></el-switch>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取消</el-button>
        <el-button type="primary" @click="editsubmit1"> 创建 </el-button>
      </div>
    </template>
  </el-dialog>

      <el-table :data="tableData" height="440" style="width: 100%">
    <el-table-column prop="id" label="订单号" width="180" />
    <el-table-column prop="person" label="收货人" width="180" />
    <el-table-column prop="tel" label="联系方式" />
    <el-table-column prop="product" label="商品" />
    <el-table-column prop="addr" label="收货地址" width="180" />
    <el-table-column prop="status" label="状态" width="180" >
      <template #default ="scope" >
        <el-switch  
        v-model="scope.row.status"
        @click="statusclick(scope.row)"></el-switch>

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
  <el-row>
    <template  >
         <el-col :span="6">
        <el-button type="primary" @click="createorder()">创建订单</el-button>
      </el-col>
    </template>
   
  </el-row>

  <el-pagination background layout="prev, pager, next" 
  @current-change="currentchange"
  :page-count="pages" />

 
</template>

<script lang="ts" setup>
// function currentchange(Number){
//     initData(Number,5);
    
//     }
import { initCustomFormatter, ref } from "vue";
import {
  orderinstance,
  orderupdateinstance,
  orderdelinstance,
  ordersaveinstance
} from "../request/request";
import { ElMessage, ElMessageBox } from "element-plus";

const tableData=ref([])
const pages=ref()
const dialogFormVisible =ref(false)
const dialogFormVisible1=ref(false)
const edituserinfo=ref({
    person:"",
    tel:"",
    addr:"",
    product:"",
    status:""
})
const edituserinfo1=ref({
    person:"",
    tel:"",
    addr:"",
    product:"",
    status:""
})
initData(1,5);

//获取分页订单数据
function initData(pagenum,pagesize){
  //访问管理员列表接口，获取所有管理员信息的组成的数组
  orderinstance(pagenum,pagesize).then((resp) => {
  //resp响应，包含响应头，响应体，状态行
  //resp.data获取响应体内容，也就是包含code,msg,data三部分的json数据
  //resp.data.data获取json数据中data的值
  // let users = resp.data.data;//分页过后所需的数据在extra.list中
//   console.log(resp.data.extra)
  let orders = resp.data.extra.list
//   console.log(orders)
  tableData.value = orders;
  pages.value = resp.data.extra.pages;
});
}

//页脚分页查询
function currentchange(Number){
initData(Number,5);
}
//点击修改订单的状态
function statusclick(data){
  // console.log(data)
  requestEdit(data);
}

//更新函数
function requestEdit(data){
  orderupdateinstance(data).then((resp) => {
    if(resp.data.code==1){

        ElMessage({
        type: "success",
        message: "修改成功",
        
      });
    } 
  });

}

//删除订单信息
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
      orderdelinstance(row.id).then((resp) => {
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
//修改订单信息，弹出修改框，获取修改信息
function handleEdit(index, row) {
  //开启提示框
  dialogFormVisible.value = true;
  //双向绑定，将数据在编辑框中显示
  edituserinfo.value = row;
}

//修改订单信息---》发送到后端服务
function editsubmit() {
  dialogFormVisible.value = false;
  //发送网络请求，访问修改用户信息的接口
  //edituserinfo永远等于点击的编辑按钮所在行的最新数据
  console.log(edituserinfo.value)
  requestEdit(edituserinfo.value)
  
}

//新建订单提示框
function createorder(){

    dialogFormVisible1.value=true;
    // edituserinfo1.value = row.value;


}
//确定创建订单信息
function editsubmit1(){
    dialogFormVisible1.value=false
    //发送新增订单请求
ordersaveinstance(edituserinfo1.value).then((resp) =>{
    console.log(edituserinfo1)

if(resp.data.code != 0){
    
ElMessage({
type: "success",
message: "修改成功",

});


}

}) 
    
}


</script>




