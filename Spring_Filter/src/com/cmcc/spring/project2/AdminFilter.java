package com.cmcc.spring.project2;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//相当于admin文件夹的看门人
public class AdminFilter implements Filter {

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
        } else {
            req.setAttribute("msg", "您不是管理员！");
            req.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
