package com.wisely.highlight_springmvc4.web.ch4_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.wisely.highlight_springmvc4.service.PushService;

/**
 * 异步任务的实现是通过控制器从另外一个线程返回一个 DeferredResult, 这里的 DeferredResult 是从pushService中获得的
 * 
 * @author yangww
 *
 */
@Controller
public class AsyncController {

	@Autowired
	PushService pushService; // 定时任务, 定时更新 DeferredResult

	/**
	 * 返回给客户端DeferredResult
	 * 
	 * @return
	 */
	@RequestMapping("/defer")
	@ResponseBody
	public DeferredResult<String> deferredCall() {
		return pushService.getAsyncUpdate();
	}
}
