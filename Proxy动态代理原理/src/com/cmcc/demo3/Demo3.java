package com.cmcc.demo3;

import org.junit.Test;

/*
 * 目标是让目标对象和增强都可以自由变换
 */
public class Demo3 {

    @Test
    public void test() {
        ProxyFactory factory = new ProxyFactory();
        factory.setTargetObject(new ManWaiter());
        factory.setBeforeAdvice(new BeforeAdvice() {
            
            @Override
            public void before() {
                System.out.println("你好！");
            }
        });
        factory.setAfterAdvice(new AfterAdvice() {
            
            @Override
            public void after() {
                System.out.println("再见！");
            }
        });
        
        Waiter proxyWaiter = (Waiter)factory.createProxy();
        //所有的方法都会被增强
        proxyWaiter.serve();
        proxyWaiter.cash();
    }
}

