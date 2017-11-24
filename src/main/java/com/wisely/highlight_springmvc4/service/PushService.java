package com.wisely.highlight_springmvc4.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {

	private DeferredResult<String> deferredResult; // 在PushService里产生 DeferredResult 给控制器使用, 通过 @Scheduled注解的方法定时更新
													// DeferredResult

	public DeferredResult<String> getAsyncUpdate() {
		deferredResult = new DeferredResult<String>();
		return deferredResult;
	}

	/**
	 * @Scheduled 注解 实现 延时五秒, 自动刷新
	 */
	@Scheduled(fixedDelay = 1000)
	public void refresh() {
		if (deferredResult != null) {
			deferredResult.setResult(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		}
	}

}
