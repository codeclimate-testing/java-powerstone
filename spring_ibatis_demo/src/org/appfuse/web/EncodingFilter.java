package org.appfuse.web;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	public EncodingFilter() {
	}

	public void init(FilterConfig parm1) throws javax.servlet.ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException,
			javax.servlet.ServletException {
		request.setCharacterEncoding("gb2312");

		HttpServletResponse hresponse = (HttpServletResponse) response;
		hresponse.setHeader("Pragma", "No-cache");
		hresponse.setHeader("Cache-Control", "no-cache");
		hresponse.setDateHeader("Expires", 0);

		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
