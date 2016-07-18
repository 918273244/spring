package com.cmcc.demo3;

/*
 * 动态代理实例
 * 
 * 待增强的目标接口
 */
public interface Waiter {
    //待增强的目标方法
    public void serve();
    
    public void cash();
}
