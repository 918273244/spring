package com.cmcc.project7;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//自定义一个session扫描器，定时扫描session容器
//1、将session存储到集合容器中
public class MySessionScaner implements HttpSessionListener, ServletContextListener {

    //自定义一个集合容器，用来存储session，方便管理
    //但是建议使用下面的方式来生成集合，解决同步代码块的问题，而不要使用List list = new LinkedList<>();来产生集合
    private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
    //这里还新建了一个Object对象，用来作为同步的锁lock。为了解决向list中add和remove并发的问题，而且两个代码又不在一起，因此使用相同的锁来解决该问题
    private Object lock = new Object();
    
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //实现ServletContextListener中的contextInitialized()方法，从而当servlet一启动的时候就开启计时器
        
        //通过Timer来生成计时器
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(list, lock), 0, 5*1000);     //每隔5秒扫描一下session容器
    }
    
    public void sessionCreated(HttpSessionEvent event)  {
        HttpSession session = event.getSession();
        //将session添加到容器中
        synchronized (lock) {
            list.add(session);
        }

        System.out.println("session被创建了");
    }

    public void sessionDestroyed(HttpSessionEvent event)  { 
        System.out.println("session被销毁了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}

class MyTimerTask extends TimerTask {

    //任务类需要处理session容器，但是没法直接获取，那就传进来喽
    private List list;
    //将锁旗标传递进来，从而使用相同的所lock，解决不同的代码块产生并发访问同一资源的问题
    private Object lock;
    public MyTimerTask(List list, Object lock) {
        super();
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("定时器启动了");
        
        synchronized (lock) {
            ListIterator it = list.listIterator();  //使用listIterator()方法，而不使用iterator()方法，是为了解决集合的"并发修改异常"的问题
            while(it.hasNext()) {
                HttpSession session = (HttpSession) it.next();
                if(System.currentTimeMillis() - session.getLastAccessedTime() > 5 * 1000) {
                    session.invalidate();
                    it.remove();
                }
            }
        }
    }
    
}
