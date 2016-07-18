package com.cmcc.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.Book;
import com.cmcc.domain.Cart;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;

public class BuyServlet extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String id = request.getParameter("id");
	    Book book = service.findBook(id);
	    
	    Cart cart = (Cart) request.getSession().getAttribute("cart");
	    if(cart==null) {
	        cart = new Cart();
	        request.getSession().setAttribute("cart", cart);
	    }
	    
	    cart.add(book);
	    response.sendRedirect(request.getContextPath()+"/client/listcart.jsp");
	    
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
