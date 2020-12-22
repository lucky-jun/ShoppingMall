package com.j.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.j.pojo.MessegeException;

public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exc) {
		Logger logger = Logger.getLogger(ExceptionResolver.class);
		System.out.println("系统有异常跳转到这里进行处理");
		ModelAndView modelAndView = new ModelAndView();
		if(exc instanceof MessegeException) {
			MessegeException msg = (MessegeException) exc;
			//写入日志
			logger.error(msg);
			modelAndView.addObject("error",msg.getMsg());
		}
		//跳转视图
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
