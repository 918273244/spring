package com.cmcc.demo4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object targetObject;
    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;
    
    public Object createProxy() {
        ClassLoader loader = targetObject.getClass().getClassLoader();
        Class[] interfaces = new Class[] {Waiter.class};
        InvocationHandler h = new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                beforeAdvice.before();
                Object object = method.invoke(targetObject, args);  //对targetObejct中的所有方法进行增强
                afterAdvice.after();
                return object;
            }
        };
        
        Object proxyObject = Proxy.newProxyInstance(loader, interfaces, h);
        return proxyObject;
    }
    
    public Object getTargetObject() {
        return targetObject;
    }
    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
    public BeforeAdvice getBeforeAdvice() {
        return beforeAdvice;
    }
    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }
    public AfterAdvice getAfterAdvice() {
        return afterAdvice;
    }
    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }
    
}
