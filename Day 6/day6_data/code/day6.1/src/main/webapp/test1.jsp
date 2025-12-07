<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!String message1 = "Hello....";//private instance var%>
<body>
	<%
	String message2 = "Hi there ...";//method local var - _jspService
	pageContext.setAttribute("message3", "How r u ");//page scoped attribute
	%>
	<%@ include file="test2.jsp"%>
</body>
</html>