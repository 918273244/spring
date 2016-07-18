package com.cmcc.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cmcc.utils.JdbcUtils;

public class TransactionFilter implements Filter {

    public void destroy() {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            //拦截下来后：获取链接、开启事物、并把链接绑定到当前线程
//          JdbcUtils.StartTransaction();
            
            chain.doFilter(request, response);     //目标资源执行
            
            //获取当前线程上绑定的链接，提交事务，并关闭链接，释放链接与当前线程的绑定
            JdbcUtils.commitTransaction();
        } finally {
            JdbcUtils.closeConn();
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        
    }

}
