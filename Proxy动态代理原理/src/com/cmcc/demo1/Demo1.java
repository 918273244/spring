package com.cmcc.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/*
 * 动态代理最简单的例子
 */
public class Demo1 {

    @Test
    public void test() {
        /*
         * 动态代理，主要就是完成三大参数的编写
         * 
         * 1、ClassLoader
         *      方法需要动态生成一个类，这个类实现了A、B接口，然后创建这个类的对象。
         *      需要生成一个类，这个类也需要加载到方法区中，谁来加载，当然是ClassLoader。
         * 2、Class[] interfaces
         *      它是要实现的接口们
         * 3、InvocationHandler
         *      它是真正的增强处理器，如果对被实现的接口中的方法进行增强，都在该对象的invoke()方法中
         *      
         * 注意：代理对象实现的所有接口中的方法，内容都是调用InvocationHandler中的invoke()方法
         */
        
        ClassLoader classLoader = this.getClass().getClassLoader();     //ClassLoader从哪里来？哪个类要创建动态代理对象就使用该类的Class来获取ClassLoader对象。形象的理解就是，朋友让带走他的的马头人把我也带走，也就是我们都能被同一个马头人带走
        Class[] interfaces = new Class[] {A.class, B.class};
        InvocationHandler h = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                System.out.println("invoke方法被调用了...");
                return null;
            }
        };
        //使用三大参数创建代理对象
        Object proxyObject = Proxy.newProxyInstance(classLoader, interfaces, h);
        
        //将代理对象强行转换为A或B类型。因为代理对象同时实现了这两个接口，因此都是他们的实现类，所以可以强转
        A a = (A)proxyObject;
        B b = (B)proxyObject;
        
        a.a();      //输出：invoke方法被调用了...
        a.aa("hello", 100);      //输出：invoke方法被调用了...
        b.b();      //输出：invoke方法被调用了...
        
    }
}

interface A {
    public void a();
    public void aa(String s, int i);
}

interface B {
    public void b();
}
