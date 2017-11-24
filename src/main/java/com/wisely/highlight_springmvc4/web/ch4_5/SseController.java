package com.wisely.highlight_springmvc4.web.ch4_5;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SseController {

	@RequestMapping(value = "/push", produces = "text/event-stream")
	public @ResponseBody String push() {
		Random r = new Random();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String utf8str = "defalutValue";
		try {
			utf8str = new String(("data: Testing 1,2,3" + r.nextInt() + "\n\n").getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException Errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrror ");
		}

		return utf8str;
	}
}
