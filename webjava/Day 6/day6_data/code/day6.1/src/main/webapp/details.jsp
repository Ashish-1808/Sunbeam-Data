<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>${param.mesg}</h5>
	<h6>User Details - ${sessionScope.user_details}</h6>
	<%
	 String encUrl=response.encodeURL("logout.jsp");
	%>
	<h5>
		<a href="<%= encUrl %>">Log Out</a>
	</h5>
</body>
</html>