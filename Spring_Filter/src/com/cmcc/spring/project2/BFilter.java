package com.cmcc.spring.project2;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BFilter implements Filter {
    
    private ServletContext servletContext;

    public void init(FilterConfig fConfig) throws ServletException {
        //1、得到ServletContext
        servletContext = fConfig.getServletContext();
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
         * 1、得到ServletContext
         * 2、获取其中的map
         * 3、查看map中是否存在这个ip对应的访问次数，如果存在，把次数+1再保存回去
         * 4、如果不存在这个ip，说明是第一次访问本站，设置访问次数为1
         */

        //2、获取map
        Map<String, Integer> map = (Map<String, Integer>) servletContext.getAttribute("map");
        //3、获取客户端访问的ip
        String ip = request.getRemoteAddr();
        //4、进行判断
        if(map.containsKey(ip)) {
            map.put(ip, (map.get(ip) + 1));
        } else {
            map.put(ip, 1);
        }
        
        //5、map再存储回去
        servletContext.setAttribute("map", map);
        //6、放行
        chain.doFilter(request, response);
    }

	public void destroy() {
	}

}
