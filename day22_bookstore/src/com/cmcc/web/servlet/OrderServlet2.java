package com.cmcc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.Order;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;

public class OrderServlet2 extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String method = request.getParameter("method");
	    if("getAll".equals(method)) {
	        getAll(request, response);
	    }
	    
	    if("find".equals(method)) {
	        find(request, response);
        }
	    
	    if("update".equals(method)) {
	        update(request, response);
        }
    }

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.updateOrder(id, true);
            request.setAttribute("message", "订单已置为发货，请及时发货！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "出错！");
        }
        
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("id");
        Order order = service.findOrder(id);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    boolean state = Boolean.parseBoolean(request.getParameter("state"));
	    BusinessService service = new BusinessServiceImpl();
	    List list = service.getOrderByState(state);
	    request.setAttribute("list", list);
	    request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
