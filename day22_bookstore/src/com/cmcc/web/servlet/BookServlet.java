package com.cmcc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.Book;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;
import com.cmcc.utils.WebUtils;

public class BookServlet extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String method = request.getParameter("method");
	    if("forAll".equals(method)) {
	        forAll(request, response);
	    }
	    
	    if("add".equals(method)) {
            add(request, response);
        }
	    
	    if("list".equals(method)) {
	        list(request, response);
	    }
	
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        List list = service.getAllBook();
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        Book book = WebUtils.upload(request, this.getServletContext().getRealPath("/images"));
	        service.addBook(book);
	        request.setAttribute("message", "添加成功！");
        } catch (Exception e) {
            request.setAttribute("message", "添加失败！");
        }
	    request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void forAll(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        List categories = service.getAllCategory();
	        request.setAttribute("categories", categories);
            request.getRequestDispatcher("/manager/addbook.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
