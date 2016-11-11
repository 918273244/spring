package com.cmcc.demo5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 动态代理的用法
 */
public class Demo5 {

    public static void main(String[] args) {
        // 使用动态代理来调用A接口中的a()方法
        A proxyA_Instance = (A)Proxy.newProxyInstance(
                Demo5.class.getClassLoader(),
                new Class[] {A.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxyA_Instance中的InvocationHandler中的invoke()方法执行了");
                        return null;
                    }
                });
        proxyA_Instance.a();
        
        System.out.println("======================我是分割线=====================");
        
        // 使用动态代理来调用B接口中的b()、bb()方法
        B proxyB_Instance = (B) Proxy.newProxyInstance(     // newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
                Demo5.class.getClassLoader(),   // ClassLoader loader
                new Class[] {B.class},          // Class<?>[] interfaces
                new InvocationHandler() {       // InvocationHandler h
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxyB_Instance中的InvocationHandler中的invoke()方法执行了");
                        method.invoke(new BImpl(), args);   // invoke(Object, Method, Object[])方法中Object参数位置放置了上面interfaces参数中的具体实现类，那么在调用该代理类中的方法时本质上就会调用实现类中的相应方法
                        return null;
                    }
                });
        
        proxyB_Instance.b();
        proxyB_Instance.bb("hehe");
        
        /*
         * 输出为：
                proxyA_Instance中的InvocationHandler中的invoke()方法执行了
                ======================我是分割线=====================
                proxyB_Instance中的InvocationHandler中的invoke()方法执行了
                BImpl类的b()方法被调用了
                proxyB_Instance中的InvocationHandler中的invoke()方法执行了
                BImpl类的bb()方法被调用了, 形参arg为hehe
         */
    }
}

interface A {
    public void a();
}

interface B {
    public void b();
    public void bb(String arg);
}

class BImpl implements B {
    @Override
    public void b() {
        System.out.println("BImpl类的b()方法被调用了");
    }
    @Override
    public void bb(String arg) {
        System.out.println("BImpl类的bb()方法被调用了, 形参arg为" + arg);
    }
}