<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connect to LinkedIn API</title>
</head>
<body>

Home for Linked In

<a href="https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=75ef6jpx9ot2q9&redirect_uri=https%3A%2F%2Fsmarthire-usjr.rhcloud.com%2FgetSMData%2FlinkedIn%2Fauth%2Fcallback&state=987654321&scope=r_basicprofile">Authorize Smarthire openshift</a><br>
<a href="https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=75ef6jpx9ot2q9&redirect_uri=http://localhost:8080/SMARTHire/getSMData/linkedIn/auth/callback&state=987654321&scope=r_basicprofile">Authorize Smarthire local host</a><br>


<table>
	<tr>
		<td>First Name: </td>
		<td>${firstName}</td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td>${lastName}</td>
	</tr>
	<tr>
		<td>User ID:</td>
		<td>${id}</td>
	</tr>
	<tr>
		<td>Headline:</td>
		<td>${headline}</td>
	</tr>
	<tr>
		<td>Industry:</td>
		<td>${industry}</td>
	</tr>
	<tr>
		<td>Num of Connections:</td>
		<td>${numConnections}</td>
	</tr>
	<tr>
		<td>Public URL: </td>
		<td>${publicProfileURL}</td>
	</tr>
</table>

</body>
</html>
