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
				<button class="btn btn-primary" data-toggle="modal" data-target="#modal_calendar_add_form">
					<i class="fa fa-plus"></i> Add Events
				</button>
			</ul>
		</div>
		<div class="container">
			<hr>
			<div id="calendar"></div>
		</div>
	</section>

	<div class="modal fade" id="modal_calendar_add_form" tabindex="-1"
		role="dialog" aria-labelledby="Login" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="Login">Add Event Form</h4>
				</div>
				<div class="modal-body">
					<form action="/SmartGIG/admin/category/sub/add" method="get">
						<div class="form-group">
							<p>Giftee:<p>
							<input type="text" class="form-control" id="email-modal"
								placeholder="giftee" name="giftee" required>
							<p>Event:</p>
							<div class="dropdown">
							<button class="btn btn-default dropdown-toggle" type="button"
								id="" data-toggle="dropdown" style="width:270px;">
								Events <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">Birthday</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">Christmas</a></li>
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">Graduation</a></li>
<!-- 								<li role="presentation" class="divider"></li> -->
								<li role="presentation"><a role="menuitem" tabindex="-1"
									href="#">Valentines</a></li>
							</ul>
							<p>Date:</p>
							<input type="date" name="eventDate" style="width:270px; border-radius:5px;">
						</div>
						</div>
						<p class="text-center">
							<button class="btn btn-primary" type="submit">
								<i class="fa fa-sign-in"></i> Submit
							</button>
						</p>
						<!-- Spring security feature; necessary when using get web service -->
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>