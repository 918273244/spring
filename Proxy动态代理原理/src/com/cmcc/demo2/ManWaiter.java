package com.cmcc.demo2;


public class ManWaiter implements Waiter {
    //通过增强接口来增强该接口实现类的方法
    @Override
    public void serve() {
        System.out.println("正在服务...");
    }

}
