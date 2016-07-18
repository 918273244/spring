package com.cmcc.filter_Listener;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * Servlet3.0中使用@WebFilter注解来代替在web.xml中进行Filter的配置
 */
@WebFilter("/*")
public class AFilter implements Filter {

	public void destroy() {
	    System.out.println("AFilter destroyed...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("AFilter run...");
	    chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
