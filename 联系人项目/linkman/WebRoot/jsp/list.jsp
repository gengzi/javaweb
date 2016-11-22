<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>list.jsp</title>
</head>

<body style="text-align: center;">
	<h3>联系人列表</h3>
	<table border="1" align="center" width="60%">
		<tr>
			<th>姓名</th>
			<th>电话</th>
			<th>地址</th>
			<th>邮编</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${linkmans}" var="c">
			<tr>
				<td>${c.name }</td>
				<td>${c.tel }</td>
				<td>${c.address }</td>
				<td>${c.zipcode }</td>
				<td><a
					href="<c:url value='/LinkManServlet?method=4&id=${c.id }'/>">编辑</a>
					<a href="<c:url value='/LinkManServlet?method=5&id=${c.id}'/>"
					onclick="return confirm('是否真要删除该联系人？')">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
