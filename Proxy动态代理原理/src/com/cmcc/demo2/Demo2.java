package com.cmcc.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class Demo2 {
    @Test
    public void test() {
        ClassLoader loader = this.getClass().getClassLoader();
        Class[] interfaces = new Class[] {Waiter.class};
        InvocationHandler h = new WaiterInvocationHandler(new ManWaiter());     //传递该接口的实现对象进去
        Waiter WaiterProxy = (Waiter)Proxy.newProxyInstance(loader, interfaces, h);
        
        WaiterProxy.serve();
    }
}

//通过invoke()方法对接口进行增强，从而增强该接口的实现类(实现类是通过构造函数传递对象建立起联系的)
class WaiterInvocationHandler implements InvocationHandler{
    //为了能操作待增强的对象中的方法，我们将待增强的对象保存下来并传递进来
    private Waiter waiter;
    public WaiterInvocationHandler(Waiter waiter) {
        super();
        this.waiter = waiter;
    }

    //增强ManWaiter中的serve()方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("你好...");
        waiter.serve();
        System.out.println("再见...");
        return null;
    }
    
}