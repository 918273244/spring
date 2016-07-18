package com.cmcc.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class HttpSessionAndServletRequestAttributeListener implements
        HttpSessionAttributeListener, ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent arg0) {
        System.out.println("向session中加入了东西");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent arg0) {
        System.out.println("从session中删除了东西");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent arg0) {
        System.out.println("把session中属性替换了");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        System.out.println("向request中加入了东西");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        System.out.println("从request中删除了东西");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        System.out.println("把request中属性替换了");
    }

}
