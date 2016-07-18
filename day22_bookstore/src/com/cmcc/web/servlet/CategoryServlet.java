package com.cmcc.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.Category;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;
import com.cmcc.utils.WebUtils;

public class CategoryServlet extends HttpServlet {
    
    private BusinessService service = new BusinessServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String method = request.getParameter("method");
	    if("add".equals(method)) {
	        add(request, response);
	    }
	    
	    if("getAll".equals(method)) {
	        getAll(request, response);
	    }
	    
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) {
	    
	    try {
	        List list = service.getAllCategory();
	        request.setAttribute("list", list);
            request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    try {
            Category c = WebUtils.request2Bean(request, Category.class);
            c.setId(UUID.randomUUID().toString());
            service.addCategory(c);
            request.setAttribute("message", "添加成功！");
        } catch (Exception e) {
            request.setAttribute("message", "添加成功！");
            e.printStackTrace();
        }
	    
	    request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
