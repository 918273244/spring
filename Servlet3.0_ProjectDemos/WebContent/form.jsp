<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/UploadServlet" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"><br/>
    简历：<input type="file" name="resume"><br/>
    <input type="submit" value="注册"><br/>
</form>

</body>
</html>