package com.yl.config;

import com.yl.interceptor.ResponseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class Interceptors extends WebMvcConfigurerAdapter {
	/**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResponseInterceptor())
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/**");
    }

}
