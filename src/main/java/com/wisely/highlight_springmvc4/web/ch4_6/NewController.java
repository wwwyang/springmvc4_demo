package com.wisely.highlight_springmvc4.web.ch4_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisely.highlight_springmvc4.service.DemoService;

@Controller
public class NewController {

	@Autowired
	DemoService demoService;

	@RequestMapping("/new")
	public String testPage(Model model) {
		model.addAttribute("msg", "newww");
		return "page";
	}
}
