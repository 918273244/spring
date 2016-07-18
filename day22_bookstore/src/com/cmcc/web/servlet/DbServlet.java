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
            
            request.setAttribute("message", "�ָ��ɹ���");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "�ָ�ʧ�ܣ�");
        }
	    
	    request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    //�г����б�����Ϣ
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List list = service.getAllBak();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/manager/listdbbak.jsp").forward(request, response);
    }

	//���ݵ�ǰ����
    private void bakup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bakpath = this.getServletContext().getRealPath("/bakup");
            String filename = System.currentTimeMillis() + ".sql";
            
            //1�������ݲ���
            String command = "cmd /c mysqldump -uroot -proot bookstore >" + bakpath + "\\" + filename;
            Runtime.getRuntime().exec(command);
                
            //2���ѱ�����Ϣ��װ��һ��javabean�У�����javabean���浽���ݿ�
            DbBak bak = new DbBak();
            bak.setBaktime(new Date());
            bak.setDescription(request.getParameter("description"));
            bak.setFilename(bakpath + "\\" + filename);
            bak.setId(UUID.randomUUID().toString());
            service.addDbBak(bak);
            
            request.setAttribute("message", "���ݳɹ���");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "����ʧ�ܣ�");
        }
        
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
