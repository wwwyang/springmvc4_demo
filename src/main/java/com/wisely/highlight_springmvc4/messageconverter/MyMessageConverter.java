package com.wisely.highlight_springmvc4.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.wisely.highlight_springmvc4.domain.DemoObj;

public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

	/**
	 * 牛逼, 可以自定义媒体类型
	 */
	// constructor
	public MyMessageConverter() {
		super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));// 新建一个我们自定义的媒体类型application/x-wisely
	}

	/**
	 * 3.重写readIntenal方法,处理请求的数据. 代码表明我们处理由"-"隔开的数据, 并转成DemoObj对象.
	 */
	@Override
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");

		return new DemoObj(new Long(tempArr[0]), tempArr[1]);
	}

	/**
	 * 表明: 本HttpMessageConverter只处理 DemoObj这个类
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return DemoObj.class.isAssignableFrom(clazz);
	}

	/**
	 * 重写 writeInternal 方法, 处理如何输出数据到 response. 此例中, 我们在原本样本上加上"追加hello:"
	 */
	@Override
	protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String out = "append hello:[" + obj.getName() + "-" + obj.getId() + "]";

		outputMessage.getBody().write(out.getBytes());
	}

}
