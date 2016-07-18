package com.cmcc.spring.project2;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class BListener implements ServletContextListener {

    /*
     * 在服务器启动时创建Map，保存到ServletContext中
     */
    public void contextInitialized(ServletContextEvent event)  { 
        //创建Map
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        //得到ServletContext
        ServletContext servletContext = event.getServletContext();
        //把map保存到ServletContext中
        servletContext.setAttribute("map", map);
    }

    public void contextDestroyed(ServletContextEvent event)  { 
    
    }
}
