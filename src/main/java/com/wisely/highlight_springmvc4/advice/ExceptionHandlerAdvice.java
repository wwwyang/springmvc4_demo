package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}

	/**
	 * 额外添加的参数
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外添加了一个变量msg的信息");
	}

	/**
	 * 过滤调的参数变量
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("name");
	}
}
