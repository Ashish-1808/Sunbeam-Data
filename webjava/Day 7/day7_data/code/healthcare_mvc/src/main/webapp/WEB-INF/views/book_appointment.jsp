<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="appointments" method="post">

		<table style="background-color: lightgrey; margin: auto">
		<%--generate doc id dynamically select option list --%>
			<tr>
				<td>Doctor ID</td>
				<td><input type="number" name="doc_id" required /></td>
			</tr>
			<tr>
				<td>Appointment Date Time</td>
				<td><input type="datetime-local" name="appointmentDateTime"
					required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Book Appointment" /></td>
			</tr>
		</table>
	</form>
</body>
</html>