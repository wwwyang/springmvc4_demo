package com.wisely.highlight_springmvc4;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.highlight_springmvc4.domain.DemoObj;

@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

	/**
	 * 什么都不写, 默认走/anno路径
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(produces = "text/plain;charset=UTF-8")
	public @ResponseBody String index(HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access";
	}

	/**
	 * 获取url中的可变字符串, 例如根据openid查记录
	 * 
	 * @param str
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String demoPathVar(@PathVariable String str, HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access, str: <font color='red'>" + str + "</font>";
	}

	/**
	 * 普通传参
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String passRequestParam(Long id, HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access, id:" + id;
	}

	/**
	 * 传对象
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String passObj(DemoObj obj, HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access, obj id:" + obj.getId() + " obj name:" + obj.getName();
	}

	/**
	 * 可以同时提供两个url地址,在一个方法中处理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String remove(HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access";
	}
}
