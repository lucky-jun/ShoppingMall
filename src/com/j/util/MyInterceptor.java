package com.j.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

public class MyInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("拦截之后");
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("拦截之前");
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		System.out.println("拦截处理");
//		String requestURI = request.getRequestURI();
//		if(!requestURI.contains("/login.do")) {
//			String SESSION = (String) request.getSession().getAttribute("USER_SESSION");
//			if(SESSION == null) {
//				response.sendRedirect(request.getContextPath()+"/login.do");
//			}
//		}
//		return true;
		
		
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers",
					"Auhthorization, Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed", "1");
		//获取URI 
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		String method = requestURI.substring(10, requestURI.length()-3);
		System.out.println(method);
		System.out.println("11111111111111111111");
		if(!method.contains("/login")){ 
			System.out.println("222222222222222222");
			String username = (String) request.getSession().getAttribute("USER_SESSION");
			String userId = request.getHeader("userId");
			// 一、判断是否跨域登录过了 （只用其中一个就够了）
			if(userId == null) {
				//response.sendRedirect(request.getContextPath()+"/login.do"); 
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("flag", false);
				response.getWriter().write(JSON.toJSONString(map));
				return false;
			}
			// 二、服务器验证登录的
			if(null==username){ 
				System.out.println(request.getContextPath());
				response.sendRedirect(request.getContextPath()+"/login.do"); 
				return false;
		     } 
		}
		return true;
	}

}
