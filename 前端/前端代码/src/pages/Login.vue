<template>
  <div class="outer">
    <div id="login_box">
      <h2>智慧物流</h2>
      <div id="input_box">
        <input
          type="text"
          placeholder="请输入用户名"
          v-model="userinfo.username"
        />
      </div>
      <div class="input_box">
        <input
          type="password"
          placeholder="请输入密码"
          v-model="userinfo.password"
        />
      </div>
      <button @click="login">登录</button><br />
    </div>
  </div>
</template>
<script setup>
//导入响应式数据
import { ref } from "vue";
import { loginreq } from "../request/request";
import router from "../router/router";
//用于登录接口中提交给服务器的数据，默认用户名密码都为空
const userinfo = ref({ username: "", password: "" });

function login() {
  //获取用户输入的账号密码
  //userinfo是双向绑定的响应式数据，所以userinfo的值永远和输入框中值保持一致
  console.log(userinfo.value);

  //将账号密码作为数据发送到接口，进行网络请求
  
  //调用网络请求函数，then表示请求完成，resp使响应对象
  loginreq(userinfo.value).then((resp) => {
    //resp.data是响应数据，也就是json
    console.log(resp.data);
    if (resp.data.code == 0) {
      alert("error");
    } else {
      // alert("success");
      //通过路由进行跳转
      router.push("/Main/Welcome");
    }
  });
  //根据服务器返回的结果进行跳转或者给出错误提示
}
</script>
<style scoped="scoped">
.outer {
  width: 100%;
  height: 100%;
  background: url("https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg")
    no-repeat;
  background-size: 100% 100%;
}

#login_box {
  width: 20%;
  height: 400px;
  background-color: #00000060;
  margin: auto;
  position: relative;
  top: 100px;
  text-align: center;
  border-radius: 10px;
  padding: 50px 50px;
}

h2 {
  color: #ffffff90;
  margin-top: 5%;
}

#input-box {
  margin-top: 5%;
}

span {
  color: #fff;
}

input {
  border: 0;
  width: 60%;
  font-size: 15px;
  color: #fff;
  background: transparent;
  border-bottom: 2px solid #fff;
  padding: 5px 10px;
  outline: none;
  margin-top: 10px;
}

button {
  margin-top: 50px;
  width: 60%;
  height: 30px;
  border-radius: 10px;
  border: 0;
  color: #fff;
  text-align: center;
  line-height: 30px;
  font-size: 15px;
  background-image: linear-gradient(to right, #30cfd0, #330867);
}

#sign_up {
  margin-top: 45%;
  margin-left: 60%;
}

a {
  color: #b94648;
}
</style>