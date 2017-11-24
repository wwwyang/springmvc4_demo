package com.wisely.highlight_springmvc4;

import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 * @author yangww
 *
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/event-stream, charset=UTF-8");
		
		return true;
	}

	/**
	 * 后处理
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("本次请求处理时间为:" + new Long(endTime - startTime) + "ms");

		request.setAttribute("handlingTime", endTime - startTime);
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/event-stream, charset=UTF-8");
	}

}
