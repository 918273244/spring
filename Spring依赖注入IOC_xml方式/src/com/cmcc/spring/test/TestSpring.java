package com.cmcc.spring.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmcc.spring.action.UserAction;
import com.cmcc.spring.domain.HelloWorld;
import com.cmcc.spring.model.User;
import com.cmcc.spring.service.UserService;

public class TestSpring {
    
    //创建spring的工厂
    private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
    
    @Test
    public void testHelloWorld() {
        //通过工厂获取spring的对象。此处getBean()中的helloWorld就是beans.xml配置文件中的id
        HelloWorld helloWorld1 = (HelloWorld) factory.getBean("helloWorld");     //获取HelloWorld对象。就像执行了HelloWorld helloWoeld = new HelloWorld();
        helloWorld1.say();
        
        //如果在bean中没有做scope的配置，默认是singleton，即生成的对象是单例模式
        HelloWorld helloWorld2 = (HelloWorld) factory.getBean("helloWorld");     //获取HelloWorld对象。就像执行了HelloWorld helloWoeld = new HelloWorld();
        System.out.println("helloWorld1 == helloWorld2?" + (helloWorld1 == helloWorld2));
    }

    @Test
    public void testUserService() {
        UserService userService = (UserService) factory.getBean("userService");
        System.out.println("userService = " + userService);
    }
    
    @Test
    public void testUserAction() {
        UserAction userAction1 = (UserAction) factory.getBean("userAction");
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        userAction1.setUser(user);
        userAction1.add();
        
        //默认是singleton模式，所以两次添加的对象一样
        UserAction userAction2 = (UserAction) factory.getBean("userAction");
        userAction2.add();
    }
}
