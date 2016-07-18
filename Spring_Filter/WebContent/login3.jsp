<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <a href="<c:url value='/EncodeServlet?username=张三'/>">点我点我</a><br>

    <form action="<c:url value="/EncodeServlet"/>" method="post">
                用户名:<input type="text" name="username">
            <input type="submit" value="提交">
    </form>

</body>
</html>