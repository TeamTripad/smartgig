<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart GIG | Profile</title>
</head>
<body>
	<header>
        <div id="t-user">
	        <div class="user-img-bottom team-member">
	            <img src="<%=request.getContextPath()%>/resources/img/team/user.jpg" class="img-responsive img-circle" alt="">
	        </div>
	        <a href="" id="prof" class="page-scroll">User</a>
        </div>
        <!-- Toggle List of Interests -->
        <div class="navbar-buttons">
            <div class="navbar-collapse collapse right" id="search-not-mobile">
                <button type="button" class="btn navbar-btn btn-primary" data-toggle="collapse" data-target="#interests">
                    <span class="sr-only">Toggle search</span>
                    <i class="fa fa-angle-double-down"></i>
                </button>
            </div>
        </div>
        
        <!-- Dynamic Loading of Interests -->
        <div class="container" id="interests">
        	<p class="text-justify">
        	    <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> IntADFASDF ASerests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
                <a href="#" class="btn btn-primary navbar-btn"><i class="fa fa-thumbs-up"></i><span class="hidden-sm"> Interests</span></a>
            </p>
        </div>
    </header>
</body>
</html>