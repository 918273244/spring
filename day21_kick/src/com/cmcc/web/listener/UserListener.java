package com.cmcc.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.cmcc.domain.User;

public class UserListener extends HttpServlet implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        Map map = (Map) event.getSession().getServletContext().getAttribute("map");
        if(map == null) {
            map = new HashMap();
            event.getSession().getServletContext().setAttribute("map", map);
        }
        
        Object obj = event.getValue();
        if(obj instanceof User) {
            User user = (User) obj;
            map.put(user.getUsername(), event.getSession());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        
    }


}
