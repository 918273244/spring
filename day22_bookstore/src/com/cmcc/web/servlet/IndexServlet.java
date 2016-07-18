package com.cmcc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmcc.domain.PageBean;
import com.cmcc.domain.QueryInfo;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;
import com.cmcc.utils.WebUtils;

//处理前台发来的请求。获取首页数据
public class IndexServlet extends HttpServlet {
    private BusinessService service = new BusinessServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
	    String category_id = request.getParameter("category_id");
	    if(category_id != null && !"".equals(category_id)) {
	        info.setQueryname("category_id");
	        info.setQueryvalue(category_id);
	    }
	    
	    List categories = service.getAllCategory();
	    PageBean pageBean = service.bookPageQuery(info);
	    
	    request.setAttribute("categories", categories);
	    request.setAttribute("pageBean", pageBean);
	    
	    request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
