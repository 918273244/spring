package com.cmcc.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.Cart;
import com.cmcc.domain.User;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;

public class OrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User user = (User) request.getSession().getAttribute("user");
	    if(user == null) {
	        request.getSession().setAttribute("user", user);
	        request.getRequestDispatcher("/message.jsp").forward(request, response);
	        return;    //�����û��Ҫִ����
	    }
	    
	    try {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            BusinessService service = new BusinessServiceImpl();
            service.saveOrder(cart, user);
            request.setAttribute("message", "�������ɳɹ�����ȴ��ջ���");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "ʧ�ܣ�");
        }
	    
	    request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
