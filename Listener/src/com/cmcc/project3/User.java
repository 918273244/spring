package com.cmcc.project3;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

//测试感知监听器，需要javabea实现HttpSessionBindingListener接口
public class User implements HttpSessionBindingListener{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent arg0) {
        System.out.println("session添加了我...");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent arg0) {
        System.out.println("我被session移除了...");
    }
    
}
