package com.cmcc.web.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

//可以查看自身对象有没有被存入session
public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent arg0) {
        System.out.println("user对象被存到session中了！");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent arg0) {
        System.out.println("user对象从session中解除了！");
    }

}
