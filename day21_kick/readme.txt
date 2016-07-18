1、jsp文件中导入类似
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	这些标签的时候，需要先倒入jstl,jar、standard.jar两个jar包
	
2、jsp页面中的form表单无法跳转到servlet类的时候，是因为在web.xml配置文件中没有配置<servlet-mapping>