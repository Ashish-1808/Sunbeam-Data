<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Included page via include directive</h5>
	<h5>Instance Var - <%= message1 %></h5>
	<h5>Local Var - <%= message2 %></h5>
	<h3>Page scoped attribute - ${pageScope.message3}</h3>
</body>
</html>