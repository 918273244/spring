package com.cmcc.project6;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计当前在线用户。
//根据session数目大致估算当前用户数目
//将count数目通过ServletContext返回给前端
public class MySessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event)  { 
        ServletContext servletContext = event.getSession().getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        if(count == null) {
            count = 1;
            servletContext.setAttribute("count", count);
        } else {
            count++;
            servletContext.setAttribute("count", count);
        }
    }

    public void sessionDestroyed(HttpSessionEvent event)  { 
        ServletContext servletContext = event.getSession().getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        count--;
        servletContext.setAttribute("count", count);
    }
	
}
