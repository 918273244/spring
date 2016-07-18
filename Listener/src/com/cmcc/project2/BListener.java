package com.cmcc.project2;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

//ServletContext中的属性监听器
public class BListener implements ServletContextAttributeListener {

    //添加属性时调用
    public void attributeAdded(ServletContextAttributeEvent event)  { 
        System.out.println("您向application中添加了一个名为" + event.getName() + ", 值为" + event.getValue() + "的属性。");
    }
    
    //修改属性时调用
    //event.getValue()只能获取修改前的值，event.getServletContext().getAttribute(event.getName())可以获取修改后的值
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
        System.out.println("修改前的属性" + event.getName() + "的值为" + event.getValue() + ", 修改后的值为" + event.getServletContext().getAttribute(event.getName()));
    }

    //删除属性时调用
    public void attributeRemoved(ServletContextAttributeEvent event)  { 
        System.out.println("删除属性" + event.getName());
    }
}
