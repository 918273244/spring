package com.cmcc.demo4;

public class ManWaiter implements Waiter {

    @Override
    public void serve() {
        System.out.println("ManWaiter 开始为您服务！");
    }

    @Override
    public void cash() {
        System.out.println("ManWaiter 正在收钱！");
    }

}
