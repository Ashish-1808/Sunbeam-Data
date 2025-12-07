<%@page import="com.sunbeam.pojos.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>From 1st page...</h5>
	<%
	long pid = Long.parseLong(request.getParameter("product_id"));
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	Product product = new Product(pid, name, price);
	//add the product details under suitable scope
	request.setAttribute("product_details", product);
	pageContext.setAttribute("test", 12345);
	%>
	<jsp:include page="test5.jsp">
		<jsp:param value="stationary" name="category" />
	</jsp:include>

</body>
</html>