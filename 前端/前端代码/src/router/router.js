//配置路由，绑定url和组件的对应关系，实现访问路径浏览器窗口切换到对应组件的效果
//引入创建路由的组件
//Vue3和Vue2基本差不多，只不过需要将createRouter、createWebHistory从vue-router中引入，再进行使用。
import {
    createRouter, //用于创建路由
    createWebHistory  //用于设置history模式
} from "vue-router";
import Login from "../pages/Login.vue"
import Home from "../pages/Home.vue"
import Main from "../pages/Main.vue"
import Welcome from "../pages/Welcome.vue"
import SysuserAdd from "../pages/SysuserAdd.vue"
import Order from "../pages/Order.vue"
//路由规则
const routes = [
    //login为默认页面
    {
        path: "/",
        redirect: "/Login"
    }
    , {
        name: "Login",
        path: "/Login",
        component: Login
    }, {
        name: "Home",
        path: "/Home",
        component: Home
    }, {
        name: "Main",
        path: "/Main",
        component: Main,
        //子路由
        children: [
            {
                path : "/Main/Home",
                component: Home
            },
            {
                path : "/Main/Welcome",
                component: Welcome
            },
            {
                path: "/Main/SysuserAdd",
                component: SysuserAdd
            },
            {
                path: "/Main/Order",
                component: Order
            }

        ] 
    }]

//创建路由
const router = createRouter({
    history: createWebHistory(),
    //配置路由规则，数组类型，数组中每一个项就是一个路由规则，一个路由规则是一个对象，对象里通过属性绑定对应关系
    routes: routes
   
})
//router需要在main.js中进行使用，用于加载路由，使路由生效
export default router
