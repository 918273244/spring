package com.cmcc.web.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.User;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;
import com.cmcc.utils.WebUtils;

public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
            User user = WebUtils.request2Bean(request, User.class);
            user.setId(UUID.randomUUID().toString());
            
            BusinessService service = new BusinessServiceImpl();
            service.addUser(user);
            
            request.setAttribute("message", "×¢²á³É¹¦£¡");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "×¢²áÊ§°Ü£¡");
        }
	    
	    request.getRequestDispatcher("/message.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
