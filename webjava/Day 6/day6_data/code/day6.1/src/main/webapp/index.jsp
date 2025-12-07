<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5> Session ID - ${pageContext.session.id}</h5>
	<h5>
		<a href="login.jsp">User Login</a>
	</h5>
	<h5>
		<a href="test1.jsp">JSP include directive</a>
	</h5>
	<h5>
		<a href="test3.jsp">Solve this</a>
	</h5>
	<h5>
		<a href="test4.jsp?product_id=100&name=pen&price=50">JSP Actions for Request Dispatching(Server Pull)</a>
	</h5>
</body>
</html>