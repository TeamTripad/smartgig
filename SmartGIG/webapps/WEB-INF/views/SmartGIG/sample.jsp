<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample</title>

</head>
<body>
	<div class="col-md-6">
	    <div class="box">
	        <h1>Login</h1>
	
	        <p class="lead">Already our customer?</p>
	        <p class="text-muted">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies
	            mi vitae est. Mauris placerat eleifend leo.</p>
	
	        <hr>
	
	        <form action="<%=request.getContextPath()%>/site/login">
	            <div class="form-group">
	                <label for="email">Username</label>
	                <input type="text" class="form-control" name="username">
	            </div>
	            <div class="form-group">
	                <label for="password">Password</label>
	                <input type="password" class="form-control" name="password">
	            </div>
	            <div class="text-center">
	                <button type="submit" class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
	            </div>
	        </form>
	    </div>
	</div>
</body>
</html>