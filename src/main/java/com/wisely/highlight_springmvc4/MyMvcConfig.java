package com.wisely.highlight_springmvc4;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.wisely.highlight_springmvc4.messageconverter.MyMessageConverter;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	Log log = LogFactory.getLog(MyMvcConfig.class);

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}

	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}

	/**
	 * 可以直接在java里通过代码设置映射路径, 而不必非用注解. 但是应该只限于直接导向jsp的页面路由
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.info("===========================addViewControllers is invoking...");
		registry.addViewController("/index").setViewName("/index");
		// 文件上传
		registry.addViewController("/toUpload").setViewName("/upload");
		// 转换
		registry.addViewController("/converter").setViewName("/converter");
		// 服务器推送
		registry.addViewController("/sse").setViewName("/sse");
		// 服务器推送2
		registry.addViewController("/async").setViewName("async");
	}

	/**
	 * 通过设置, 不忽略.后面的参数
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);// xx.yy可以读为xx.yy, 如果不设置, 则.后面的都会被抛弃, 为xx
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}

	/**
	 * 重写extendMessageConverters
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}

	private MyMessageConverter converter() {
		return new MyMessageConverter();
	}

}
