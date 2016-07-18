package com.cmcc.filter_Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
        System.out.println("AListener destroyed...");
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
        System.out.println("AListener run...");
    }
	
}
