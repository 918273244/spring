package com.cmcc.project8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//定时发送邮件
public class MyTimerListener implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent event)  { 
        
        //定时到几点的时间对象用Calendar，Timer已经过期了
        Calendar c = Calendar.getInstance();
        c.set(2016, 2, 8, 21, 14, 10);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            
            @Override
            public void run() {
                System.out.println("现在是" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getTime()) + ", 该发送邮件了！");
            }
        }, c.getTime());
    }

    public void contextDestroyed(ServletContextEvent event)  { 
    }
	
}
