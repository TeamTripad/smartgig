<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart GIG | Calendar</title>
</head>
<body>
	<section id="t-calendar">
	<div class="container">
		<ul class="nav">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					<i class="fa fa-calendar"></i>Event<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a data-toggle="modal" data-target="#myModal">Add Event</a></li>
					<li><a href="#">Delete Event</a></li>
					<li><a href="#">Edit Event</a></li>
				</ul>
			</div>
		</ul>
	</div>
	<div class="container">
		<hr>
		<div id="calendar"></div>
	</div>
	</section>


</body>
</html>