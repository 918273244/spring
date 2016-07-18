package com.cmcc.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.util.SimpleNameVector;

import com.cmcc.domain.DbBak;
import com.cmcc.service.BusinessService;
import com.cmcc.service.impl.BusinessServiceImpl;

public class DbServlet extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String method = request.getParameter("method");
	    if("bakup".equals(method)) {
	        bakup(request, response);
	    }
	    
	    if("list".equals(method)) {
	        list(request, response);
        }
	    
	    if("restore".equals(method)) {
	        restore(request, response);
        }
	    
	    
	}

	private void restore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
            String id = request.getParameter("id");
            DbBak bak = service.findDbBak(id);
            String filename = bak.getFilename();
            
            String command = "cmd /c mysql -uroot -proot bookstore <" + filename;
            Runtime.getRuntime().exec(command);
            
            request.setAttribute("message", "恢复成功！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "恢复失败！");
        }
	    
	    request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    //列出所有备份信息
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = service.getAllBak();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/manager/listdbbak.jsp").forward(request, response);
    }

	//备份当前数据
    private void bakup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bakpath = this.getServletContext().getRealPath("/bakup");
            String filename = System.currentTimeMillis() + ".sql";
            
            //1、作备份操作
            String command = "cmd /c mysqldump -uroot -proot bookstore >" + bakpath + "\\" + filename;
            Runtime.getRuntime().exec(command);
                
            //2、把备份信息封装到一个javabean中，并把javabean保存到数据库
            DbBak bak = new DbBak();
            bak.setBaktime(new Date());
            bak.setDescription(request.getParameter("description"));
            bak.setFilename(bakpath + "\\" + filename);
            bak.setId(UUID.randomUUID().toString());
            service.addDbBak(bak);
            
            request.setAttribute("message", "备份成功！");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "备份失败！");
        }
        
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
