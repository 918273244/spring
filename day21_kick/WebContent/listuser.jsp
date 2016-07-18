<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
        所有登陆用户为：
        <c:forEach var="entry" items = "${applicationScope.map }">
            <c:url var="url" value="/KickServlet">
                <c:param name="username" value="${entry.key }"></c:param>
            </c:url>
            ${entry.key } <a href="${url }">踢死你 </a><br>
        </c:forEach>
</body>
</html>