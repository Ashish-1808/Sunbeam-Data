<%@page import="java.time.LocalDate"%>
<%@page import="com.sunbeam.pojos.User"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	HashMap<String, User> users;

	public void jspInit() {
		//create & populate map
		users = new HashMap<>();
		users.put("rama@gmail.com", new User("Rama Patil", "rama@gmail.com", "12345", LocalDate.parse("1990-10-20")));
		users.put("anish@gmail.com", new User("Anish Patel", "anish@gmail.com", "2345", LocalDate.parse("1992-10-20")));
		System.out.println("in jsp init");

	}%>
<body>
	<%
	System.out.println("in jsp service");
	String email = request.getParameter("em");
	String password = request.getParameter("pass");
	//validation logic
	User user = users.get(email);
	if (user != null) {
		if (user.getPassword().equals(password)) {
			//successful login
			session.setAttribute("user_details", user);
			
			//redirect
			String encURL=response.encodeRedirectURL("details.jsp");
			System.out.println(encURL);
			response.sendRedirect(encURL);
		} else {
	%>
	<h5>
		Invalid Password , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	} else {
	%>
	<h5>
		Invalid Email , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	%>
</body>
</html>