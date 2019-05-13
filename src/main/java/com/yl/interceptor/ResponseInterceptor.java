package com.yl.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该拦截器的作用主要是设置response的响应头,解决跨域访问报错问题
 * response.setHeader("Access-Control-Allow-Origin", "*");
 * @author jiangjc
 * 2017年5月15日上午11:02:11
 */
public class ResponseInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return super.preHandle(request, response, handler);
	}	
}

