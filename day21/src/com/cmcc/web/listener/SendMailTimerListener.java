package com.cmcc.web.listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//定时发送邮件定时器
public class SendMailTimerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Timer timer = new Timer();
        Calendar c = Calendar.getInstance();    //Date中很多方法过时了，可以用Calendar中的方法来完美代替
        c.set(2016, 1, 4, 14, 52, 50);      //定时到2016-1-4-14-34-30
        timer.schedule(new TimerTask() {    //TimerTask是接口，必须实现其中的方法
            
            @Override
            public void run() {
                System.out.println("发邮件！");
            }
        }, c.getTime());
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        
    }
}
