<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的通讯录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<center>
		<h1>我的通讯录</h1>
		<form action="<%=path%>/LinkManServlet" method="post">
		<input type="hidden" name="method" value="2">
		用户名：<input type="text" name="username" > <br>
		密    码： <input type="password" name="password" /><br>
		<input type="submit" value="登陆" /><a href='<c:url value='jsp/login.jsp'></c:url>' >注册</a>

		</form>
	</center>
</body>
</html>
