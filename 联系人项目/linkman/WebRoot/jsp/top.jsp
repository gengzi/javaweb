<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>top.jsp</title>
</head>

<body style="text-align: center;">

	<c:if test="${user.id == null }">
		<font color="red">请重新登陆</font>
	</c:if>

	<c:if test="${user.id != null }">
		<h1>欢迎登陆到通信录系统</h1>
		<a
			href="<c:url value='/LinkManServlet?method=3&i=1'/>"
			target="body">查看联系人</a>
		<a
			href="<c:url value='/LinkManServlet?method=3&i=2'/>"
			target="body">添加联系人</a>
		<a href="<c:url value='/LinkManServlet?method=8'/>" target="body">退出系统</a>
	</c:if>
</body>
</html>
