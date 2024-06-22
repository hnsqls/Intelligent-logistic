import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {

    hmr: {

      host: "localhost",

      //vue3 vite配置热更新不用手动刷新

       hmr: true,

     },

    open: true, // 是否自动在浏览器打开

   },
})
