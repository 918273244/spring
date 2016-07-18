package com.cmcc.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/*
 * 查看当前登陆人数listener
 */
public class CountLoginUserNumListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Integer count = (Integer)context.getAttribute("count");
        if(count == null) {
            count = 1;
            context.setAttribute("count", count);
        } else {
            count++;
            context.setAttribute("count", count);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Integer count = (Integer)context.getAttribute("count");
        count--;
        context.setAttribute("count", count);
    }

}
