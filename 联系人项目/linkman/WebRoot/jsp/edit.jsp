<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>edit.jsp</title>

	<!-- 日期插件，使用jquery -->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.4.2.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/jquery/jquery.datepick.css'/>" type="text/css">
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
  
  </head>
  
  <body style="text-align: center;"> 
    <h3>修改联系人信息</h3>
    <form action="<c:url value='/LinkManServlet'/>" method="post">
    	<input type="hidden" name="method" value="6">
    	<input type="hidden" name="userid" value="${linkmans.userId}">
    	<input type="hidden" name="id" value="${linkmans.id}">
    	<table border="1" width="50%" align="center">
    		<tr>
    			<td>姓名</td>
    			<td>
    				<input type="text" name="name" value="${linkmans.name }" />
    			</td>
    		</tr>
    		<tr>
    			<td>电话</td>
    			<td>
    				<input type="text" name="tel" value="${linkmans.tel }" />
    			</td>
    		</tr>
    		<tr>
    			<td>地址</td>
    			<td>
    				<input type="text"  name="address" value="${linkmans.address }" />
    			</td>
    		</tr>    	
     		<tr>
    			<td>邮编</td>
    			<td>
    				<input type="text" name="zipcode" value="${linkmans.zipcode }" />
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="更改联系人"/>		
    			</td>
    		</tr>
        </table>
    </form>
  </body>
</html>
