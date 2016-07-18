package com.cmcc.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//乱码处理
public class CharacterEncodingFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        //设置编码方式为UTF-8。
        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("html/file;characterset=utf-8");
            /*
             * 不需要在对response使用相同的定义编码方式，因为response的characterEncoding属性是为了设置客户端页面的编码方式为utf-8，
             * 而这个可以通过在.jsp页面中直接显示写编码方式为utf-8
             */
        chain.doFilter(request, response);
    }

    public void destroy() {
        
    }

    public void init(FilterConfig fConfig) throws ServletException {
        
    }

}
