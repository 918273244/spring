<%@page import="com.cmcc.web.listener.MyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cmcc.web.listener.User" %>
<%@ page import="com.cmcc.web.listener.MyBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前登陆人数</title>
</head>
<body>

    当前在线用户数为：${applicationScope.count }人
    
    <!-- 测试MyServletContextAttributeListener -->
    <!-- 以后可以用这种方式代替写servlet来进行测试 -->
    <%
        //application.setAttribute("name", "aaa");
        //application.setAttribute("name", "bbb");
        //application.removeAttribute("name");
        
        //session.setAttribute("name", "aaa");
        //session.setAttribute("name", "bbb");
        //session.removeAttribute("name");
        
        //request.setAttribute("name", "aaa");
        //request.setAttribute("name", "bbb");
        //request.removeAttribute("name");
        
        //session.setAttribute("user", new User());
        //session.removeAttribute("name");
        
        session.setAttribute("bean", new MyBean());
    %>
    
</body>
</html>