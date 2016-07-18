package com.cmcc.web.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

//javabean随着session存储到硬盘中和取出到内存中
public class MyBean implements HttpSessionActivationListener, Serializable {    //javabean要想存到硬盘中取必须实现Serializable接口

    @Override
    public void sessionDidActivate(HttpSessionEvent arg0) {
        System.out.println("javabean随着session从硬盘中回到内存了！");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent arg0) {
        System.out.println("javabean随着session到硬盘中去了！");
    }

}
