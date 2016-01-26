<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart GIG</title>
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="<%=request.getContextPath()%>/site/page"><i class="fa fa-gift"></i> Smart GIG</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden" class="active">
                        <a href="#page-top"></a>
                    </li>
                    <li class="notifications">
                        <a class="page-scroll" id="show-notifications" href="<%=request.getContextPath()%>/site/home/notifications"><i class="fa fa-bell"></i>Notifications</a>
                    </li>
                    <li class="calendar">
                        <a class="page-scroll" id="show-calendar" href="<%=request.getContextPath()%>/site/home/calendar"><i class="fa fa-calendar"></i>Calendar</a>
                    </li>
                    <li class="contacts">
                        <a class="page-scroll" id="show-contacts" href="<%=request.getContextPath()%>/site/home/contacts"><i class="fa fa-users"></i>Contacts</a>
                    </li>
                    <!-- <li>
                    	<a class="page-croll" href="<%=request.getContextPath()%>/site/home/t-user"><i class="fa fa-user"></i></a>
                    </li> -->
                    <li>
                        <a class="page-scroll" href="#"><i class="fa fa-cog"></i></a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</body>
</html>