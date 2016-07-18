package com.cmcc.project1;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AListener implements ServletContextListener {
    
    //Tomcat启动时该方法即启动
    public void contextInitialized(ServletContextEvent event)  { 
        System.out.println("监听器创建了...");
    }

    public void contextDestroyed(ServletContextEvent event)  { 
        System.out.println("监听器销毁了...");
    }
}
