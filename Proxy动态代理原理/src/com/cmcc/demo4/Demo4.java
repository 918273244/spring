package com.cmcc.demo4;

import org.junit.Test;

public class Demo4 {

    @Test
    public void test() {
        ProxyFactory factory = new ProxyFactory();    
        factory.setTargetObject(new ManWaiter());
        factory.setBeforeAdvice(new BeforeAdvice() {
            
            @Override
            public void before() {
                System.out.println("您好...");
            }
        });
        factory.setAfterAdvice(new AfterAdvice() {
            
            @Override
            public void after() {
                System.out.println("再见...");
            }
        });
        
        Waiter waiterProxy = (Waiter) factory.createProxy();
        waiterProxy.serve();
        System.out.println("============================");
        waiterProxy.cash();
        
        
    }
}
