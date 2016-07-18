package com.cmcc.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextAttributeListener
        implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        System.out.println("向servletContext中存了: " + name + "=" + value);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("从servletContext中删除了："+event.getName());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("servletContext中："+event.getName() + "属性被替换了");
    }

}
