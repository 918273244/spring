package com.cmcc.spring.project2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 1、获取用户名
         * 2、判断用户名中是否包含ldd
         *    3、如果包含，就是管理员
         *    4、如果不包含，就是普通会员
         * 5、要把登陆的用户名称保存到session中
         * 6、转发到index.jsp
         */
        
        String username = request.getParameter("username");
        if(username.contains("ldd")) {
            request.getSession().setAttribute("admin", username);
        } else {
            request.getSession().setAttribute("username", username);
        }
        
        //仅仅是游客登陆
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
