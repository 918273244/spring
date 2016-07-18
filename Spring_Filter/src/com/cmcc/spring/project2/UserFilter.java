package com.cmcc.spring.project2;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


//该Filter就相当于user文件夹的守门人
public class UserFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1、得到session
		 * 2、判断session中是否存在admin，如果存在就放行
		 * 3、不存在就返回login.jsp
		 */
	    HttpServletRequest req = (HttpServletRequest) request;
	    String adminName = (String) req.getSession().getAttribute("admin");
	    if(adminName != null) {
	        chain.doFilter(request, response);
	    } 
	    
	    String userName = (String) req.getSession().getAttribute("username");
	    if(userName != null) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("msg", "您什么都不是，不要瞎溜达！");
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
