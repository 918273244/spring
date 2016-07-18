package com.cmcc.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 它用来生成代理对象
 * 它需要所有的参数
 *    * 目标对象
 *    * 增强
 */
public class ProxyFactory {

    private Object targetObject;
    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;
    
    //用来生成代理
    public Object createProxy() {
        //1、给出三大参数
        ClassLoader loader = this.getClass().getClassLoader();
        Class[] interfaces = targetObject.getClass().getInterfaces();   //获取该对象所实现的所有接口
        InvocationHandler h = new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                
                //调用前置增强方法(想要随意修改前置增强方法，只需要传入不同的前置接口实现类即可)
                if(beforeAdvice != null) {
                    beforeAdvice.before();
                }
                
                //如何知道目标对象targetObject中的待增强方法呢？通过Method对象获取
                Object result = method.invoke(targetObject, args);  //执行目标对象的目标方法
                
                //后置增强方法
                if(afterAdvice != null) {
                    afterAdvice.after();
                }
                
                //返回目标对象的返回值
                return result;
            }
        };
        
        //2、得到代理对象
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
