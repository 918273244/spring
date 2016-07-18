package com.cmcc.demo1;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.itcast.beanfactory.BeanFactory;

public class Test1 {

    @Test
    public void userTest() {
        BeanFactory factory = new BeanFactory("beans.xml");
        User user = (User) factory.getBean("user");
        System.out.println("user = " + user);
    }
    
    @Test
    public void teacherTest() {
        BeanFactory f = new BeanFactory("beans.xml");
        Teacher tea = (Teacher) f.getBean("teacher");
        System.out.println("teacher = " + tea);
        System.out.println("teacher.user = " + tea.getUser());
    }

}
