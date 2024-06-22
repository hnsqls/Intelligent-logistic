//封装网络请求，方便统一管理
import axios from "axios";
//创建一个实例对象
const instance = axios.create({
    //指向网关的ip和端口
    baseURL: 'http://localhost:10010',

    //网络超时时间
    timeout: 10000,
    //设置请求头，Content-Type用于配置请求信息的格式，类型为json
    //前端传递给后端的数据都封装成json
    headers: {
        'Content-Type': 'application/json;charset=UTF-8',
    }
})

// 定义登录的网络操作,并导出，方便组件中进行引用
export const loginreq = (userinfo) => {
    return instance.post("/sysuser/login", userinfo)
}
//查询所有管理员信息--》分页
export const sysuserinstance = (pagenum,pagesize,searchform) => {
    return instance.get("/sysuser/sysuser?pageNum="+pagenum+"&pageSize="+pagesize+"&id="+searchform.id+"&username="+searchform.username+"&nickname="+searchform.nickname+"&email="+searchform.email)
}

export const sysuserdelinstance = (id) => {
    //红色报错指的是语法不严谨，不符合TS语法的规范
    return instance.delete("/sysuser/sysuser/" + id)
}
export const sysuserupdateinstance = (userinfo) => {
    //红色报错指的是语法不严谨，不符合TS语法的规范
    return instance.post("/sysuser/sysuser",userinfo)
}  

export const sysusersaveinstance = (userinfo) => {
   
    return instance.put("/sysuser/sysuser",userinfo)
}

//订单页面数据请求
export const orderinstance = (pagenum,pagesize) => {
    return instance.get("/order/order?pageNum="+pagenum+"&pageSize="+pagesize)
}
//订单更新请求
export const orderupdateinstance = (orderinfo) => {
    //红色报错指的是语法不严谨，不符合TS语法的规范
    return instance.put("/order/order",orderinfo)
}  

//订单删除请求
export const orderdelinstance = (id) => {
    //红色报错指的是语法不严谨，不符合TS语法的规范，path传参
    return instance.delete("/order/order/" + id)
}

//订单新增请求
export const ordersaveinstance = (userinfo) => {
   console.log(userinfo)
    return instance.post("/order/order",userinfo)
}