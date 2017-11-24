package com.wisely.highlight_springmvc4.web.ch4_6;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wisely.highlight_springmvc4.MyMvcConfig;
import com.wisely.highlight_springmvc4.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MyMvcConfig.class })
@WebAppConfiguration("src/main/resources")//WebAppConfiguration注解在类上, 用来声明加载的ApplicationContext是一个 WebApplicationContext. 它的属性指定的是Web资源的位置,默认为src/main/webapp,本例按照我们实际的项目修改为src/main/resources.
public class TestControllerIntegrationTests {
	/*
	 * MockMvc模拟MVC对象, 通过MockMvcBuilders.webAppContextSetup(this.context).build()进行初始化
	 */
	private MockMvc mockMvc;

	@Autowired
	private DemoService demoService;//可以在测试用例中注入Spring的bean

	@Autowired
	WebApplicationContext webApplicationContext; //可注入 WebApplicationContext

	@Autowired
	MockHttpSession session; //可注入模拟的 http session, 此处未使用, 仅作演示

	@Autowired
	MockHttpServletRequest request;//可注入模拟的 http request, 此处未使用, 仅作演示

	@Before //单元测试开始前进行初始化
	public void setup() {
		/*
		 * MockMvc模拟MVC对象, 通过MockMvcBuilders.webAppContextSetup(this.context).build()进行初始化
		 */
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void testNormalController() throws Exception {
		mockMvc.perform(get("/normal"))	//模拟向 http://127.0.0.1/normal 进行 get请求
		.andExpect(status().isOk())		//预期控制  返回状态为200
		.andExpect(view().name("page"))	//预期view的名称为page
		.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp")) 		//预期页面转向的真正路径为 /WEB-INF/classes/views/page.jsp
		.andExpect(model().attribute("msg", demoService.saySomething()));	//预期model里的值是demoService.saySomething()的返回值(hello)
	}
	
	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(get("/testRest")) 	//模拟向 http://127.0.0.1/testRest 进行 get请求
		.andExpect(status().isOk())			//预期控制  返回状态为200
		.andExpect(content().contentType("text/plain;charset=UTF-8"))	//预期返回值的媒体类型为text/plain;charset=UTF-8
		.andExpect(content().string(demoService.saySomething()));		//预期返回值的内容是demoService.saySomething()的返回值(hello)
	}
	
	@Test
	public void testNewController() throws Exception {
		mockMvc.perform(get("/new"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("msg", "newww"));
	}
}
