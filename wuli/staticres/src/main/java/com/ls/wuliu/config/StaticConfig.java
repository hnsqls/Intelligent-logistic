package com.ls.wuliu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置静态资源的虚拟路径
 * 将e/upload目录中的资源部署到springbootweb项目。
 * 供前端访问
 */

@Configuration
public class StaticConfig implements WebMvcConfigurer {
    /**
     * 配置静态资源路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //虚拟路径
        registry.addResourceHandler("/img/**")
                //一定以/结尾，虚拟资源指向的实际路径
                .addResourceLocations("file:E:/Back-end-development/微服务/upload/");


    }
}
