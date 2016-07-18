package com.cmcc.web.listener;

import java.util.Collections;
import java.util.Iterator;
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

//定时删除session
public class SessionScanner implements HttpSessionListener, ServletContextListener {

    private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
    private Object lock = new Object();     //新建一个对象作为下面synchronized同步的公共锁
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        System.out.println(session + "被创建了");
        synchronized (lock) {       //锁旗标
            list.add(session);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println(event.getSession() + "被销毁了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
    }

    //创建一个定时器，进行倒计时。每隔5分钟，延迟0秒就清楚session
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(list, lock), 0, 5*1000);     //间隔5秒钟，延迟0秒启动对象MyTask()
    }

}

class MyTask extends TimerTask{
    private List list;
    private Object lock;
    
    public MyTask(List list, Object lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        //这种写法有错。因为it已经获取了list中的元素个数，已经是确定的了，此时如果通过list删除或者增加元素，那么it并不知道，会继续迭代，那么就有可能错失元素，因此不能这种写法
        /*
        Iterator it = list.iterator();      //
        while(it.hasNext()) {
            HttpSession session = (HttpSession) it.next();
            if(System.currentTimeMillis() - session.getLastAccessedTime() > 5*60*1000) {
                session.invalidate();
                list.remove(session);
            }
        }
        */
        synchronized (lock) {
            ListIterator it = list.listIterator();
            while(it.hasNext()) {
                HttpSession session = (HttpSession) it.next();
                if(System.currentTimeMillis() - session.getLastAccessedTime() > 5*1000) {
                    session.invalidate();
                    it.remove();    //通过iterator来删除元素，这样iterator中就会知道元素已经更改
                }
            }
        }
    }
    
}
