import { createApp } from 'vue'

import App from './App.vue'

import router from './router/router.js'
// 引入elementui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
const app = createApp(App)
//配置路由
app.use(router)
app.use(ElementPlus, {
    locale: zhCn,
});
app.mount('#app')