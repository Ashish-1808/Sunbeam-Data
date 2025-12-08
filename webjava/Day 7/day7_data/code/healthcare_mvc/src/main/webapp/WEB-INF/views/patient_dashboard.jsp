<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Dash board</title>
</head>
<body>
	<%-- set user details in user attribute --%>
	<c:set var="user" value="${sessionScope.user_details}" />
	<h5>Hello , ${user.firstName} ${user.lastName}</h5>
	<h5>Blood Group -${patient_details.bloodGroup} Gender -
		${patient_details.gender}</h5>
	<table style="background-color: lightgrey; margin: auto">
		<caption>Upcoming Appointments</caption>
		<tr>
			<th>Appointment ID</th>
			<th>Date Time</th>
			<th>Doctor Name</th>
			<th>Action</th>
		</tr>
		<c:forEach var="app" items="${requestScope.appointment_list}">
			<tr>
				<td>${app.appointmentId}</td>
				<td>${app.appointmentDateTime}</td>
				<td>${app.firstName}${app.lastName}</td>
				<td><a href="appointments?id=${app.appointmentId}">Cancel</a></td>
			</tr>
		</c:forEach>
	</table>
	<h5>
		<a href="appointments">Book New Appointment</a>
	</h5>
	<h5>
		<a href="logout">Log Out</a>
	</h5>
</body>
</html>