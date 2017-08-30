package org.powerstone.sample;

import java.util.List;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.AbstractModelAndViewTests;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

public class UserPagingControllerTest  extends AbstractModelAndViewTests {
	protected XmlWebApplicationContext ctx;

	protected MockHttpServletRequest request = new MockHttpServletRequest();

	protected MockHttpServletResponse response = new MockHttpServletResponse();

	protected ModelAndView mv = null;
	
	protected UserPagingController controller;

	protected String[] getConfigLocations() {
		return new String[] { "/WEB-INF/springmvc-servlet.xml" };
	}

	protected final void setUp() {
		ctx = new XmlWebApplicationContext();
		ctx.setConfigLocations(getConfigLocations());
		ctx.setServletContext(new MockServletContext(""));
		ctx.refresh();
		
		controller = (UserPagingController) ctx
		.getBean("userPagingController");
		request.setMethod("GET");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'org.powerstone.sample.UserPagingController.doHandleRequest(HttpServletRequest, HttpServletResponse)'
	 */
	public void testDoHandleRequest() throws Exception {
		request.setRequestURI("/user_query.html");
		request.addParameter("firstName","x");
		
		mv = controller.handleRequest(request, response);

		assertViewName(mv, "usersList");
		// 验证结果
		assertModelAttributeAvailable(mv,"usersList");
		assertAndReturnModelAttributeOfType(mv,"usersList", List.class);
	}

}
