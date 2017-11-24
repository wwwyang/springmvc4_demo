package com.wisely.highlight_springmvc4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/index2")
	public String hello() {
		return "index2";
	}
}
