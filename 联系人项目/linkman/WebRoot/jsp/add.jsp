<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>add.jsp</title>

	<!-- 日期插件，使用jquery -->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.4.2.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/jquery/jquery.datepick.css'/>" type="text/css">
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
  </head>
  
  <body style="text-align: center;">
    <h1>添加联系人</h1>
    <form action="<c:url value='/LinkManServlet'/>" method="post">
    	<input type="hidden" name="method" value="7"> 
    	<input type="hidden" name="userid" value="${userid}">
    	<table border="1" width="50%" align="center">
    		<tr>
    		<!-- 最好不要在服务端，做一些对文本框的判断 -->
    			<td>姓名</td>
    			<td>
    			<!-- onfocus //当鼠标放在客户名文本框时触发-->
    				<input  type="text" name="name" />
    	
    			</td>
    		</tr>
    		<tr>
    			<td>电话</td>
    			<td>
    				<input  type="text" name="tel"  />
    			
    			</td>
    		</tr>    	
     		<tr>
    			<td>地址</td>
    			<td>
    				<input type="text" name="address" />
    			</td>
    		</tr>
       		<tr>
    			<td>邮编</td>
    			<td>
    				<input type="text" name="zipcode" />
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="添加联系人"/>		
    			</td>
    		</tr>
        </table>
    </form>
  </body>
</html>
