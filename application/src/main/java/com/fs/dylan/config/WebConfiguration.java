package com.fs.dylan.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dylan
 * @Title: WebConfiguration
 * @Package com.fs.dylan.config
 * @Description:
 * @date 2023/9/25
 */
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        //registry.
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
