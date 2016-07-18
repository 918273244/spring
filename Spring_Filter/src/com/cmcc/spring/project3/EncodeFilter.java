package com.cmcc.spring.project3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {
    
    public void init(FilterConfig fConfig) throws ServletException {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	    request.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(req.getMethod().equals("POST")) {
		    /*
		     * 解决post方法的乱码问题
		     */
		    chain.doFilter(request, response);
		} else if(req.getMethod().equals("GET")) {
		    /*
		     * 解决get方法的乱码问题：通过掉包request
		     * 1、写一个request的装饰类
		     * 2、在放行时，使用我们自己的request
		     */
		    
		    chain.doFilter(new EncodeRequest(req), response);
		}
	}
	
	private class EncodeRequest extends HttpServletRequestWrapper{
	    
	    private HttpServletRequest request;
	    
        public EncodeRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            String value = request.getParameter(name);
            try {
                value = new String(value.getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return value;
        }
	}
}
