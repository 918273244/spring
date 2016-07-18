package com.cmcc.spring.project1;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AFilter implements Filter {

    /**
     * 创建之后马上执行，用来做初始化
     */
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("AFilter启动了...");
    }

	/**
	 * 每次过滤时都会执行
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    System.out.println("成功拦截...");
	    
	    //放行
		chain.doFilter(request, response);    
	}

	/**
	 * 销毁前执行，用来做对非内存资源进行释放
	 */
	public void destroy() {
	    System.out.println("AFilter销毁了...");
	}

}
