<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Smart GIG : Sample</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <!-- <link href="<%=request.getContextPath()%>/resources/css/custom.css" rel="stylesheet">-->
    <link href="<%=request.getContextPath()%>/resources/css/agency.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]><![endif]-->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    
</head>

<body id="page-top" class="index">
    <!-- Navigation -->
    <jsp:include page="nav.jsp"/>
    
   	<%pageContext.setAttribute("state", request.getAttribute("state")); %>
    <!--<c:out value="STATE : ${state}"></c:out>-->
    
    <!-- User -->
    <jsp:include page="user.jsp"/>
    
    <c:choose>
      <c:when test="${state eq 0 || state eq 1}">
	    <!-- Notifications Section -->
	    <jsp:include page="notifications.jsp"/>
	    <script>$(".navbar-default .navbar-nav>li .notifications").addClass("active");</script>
      </c:when>
      
      <c:when test="${state eq 2}">
	    <!-- CALENDAR SECTION -->
	    <jsp:include page="calendar.jsp"/>
	    <script>$(".navbar-default .navbar-nav>li .calendar").addClass("active");</script>
      </c:when>
      
      <c:when test="${state eq 3}">
	    <!-- Contacts Section -->
	    <jsp:include page="contacts.jsp"/>
	    <script>$(".navbar-default .navbar-nav>li .contacts").addClass("active");</script>
      </c:when>
      
      <c:when test="${state eq 4}">
	    <!-- EDIT CALENDAR SECTION -->
	    <jsp:include page="editCalendar.jsp"/>
	    <script>$(".navbar-default .navbar-nav>li .contacts").addClass("active");</script>
      </c:when>   
  	</c:choose>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp"/>

	<!-- modal forms -->
	<div id="top">
		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="Login">Administrator login</h4>
                    </div>
                    <div class="modal-body">
                        <form action="/SmartGIG/admin/home" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" id="email-modal" placeholder="username" name="username" required>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password-modal" placeholder="password" name="password" required>
                            </div>

                            <p class="text-center">
                                <button class="btn btn-primary" type="submit"><i class="fa fa-sign-in"></i> Log in</button>
                            </p>
                            
                            <!-- Springs security feature; add this for using post services -->
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                        <!-- <p class="text-center text-muted">Not registered yet?</p>
                        <p class="text-center text-muted"><a href="register.html"><strong>Register now</strong></a>! It is easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p> -->
                    </div>
                </div>
            </div>
        </div>
	</div>
    <!-- Portfolio Modals -->
    <!-- Use the modals below to showcase details about your portfolio projects! -->
    <jsp:include page="portfolioModals.jsp"/>

    <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/classie.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <!-- <link href="<c:url value="/resources/js/jqBootstrapValidation.js"/>" rel="stylesheet">-->
	<script href="<%=request.getContextPath()%>/resources/js/jqBootstrapValidation.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/contact_me.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/agency.js"></script>
    <!--  -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.js"></script>
    <!-- CALENDAR -->
    <script src="<%=request.getContextPath()%>/resources/js/calendar.js"></script>
    
    <script>
		/**$(document).ready(function(){
		    $("#show-notifications").click(function(){
		    	$(".navbar-default .navbar-nav>li .notifications").addClass("active");
		    	$(".navbar-default .navbar-nav>li .calendar").removeClass("active");
		    	$(".navbar-default .navbar-nav>li .contacts").removeClass("active");
		    	
		        $("#t-notifications").show();
		        $("#t-calendar").hide();
		        $("#t-contacts").hide();
		    });
		    
		    $("#show-calendar").click(function(){
		    	$(".navbar-default .navbar-nav>li .calendar").addClass("active");
		    	$(".navbar-default .navbar-nav>li .notifications").removeClass("active");
		    	$(".navbar-default .navbar-nav>li .contacts").removeClass("active");
		    	
		    	$("#t-calendar").show();
		        $("#t-notifications").hide();
		        $("#t-contacts").hide();
		    });
		    
		    $("#show-contacts").click(function(){
		    	$(".navbar-default .navbar-nav>li .contacts").addClass("active");
		    	$(".navbar-default .navbar-nav>li .notifications").removeClass("active");
		    	$(".navbar-default .navbar-nav>li .calendar").removeClass("active");
		    	
		    	$("#t-contacts").show();
		        $("#t-notifications").hide();
		        $("#t-calendar").hide();
		    });
		});**/
	</script>
	<!-- S:BY:JZAH:09-25-15 CONNECT WITH FB THINGY -->
        <!-- 
        <div id="fb-root"></div>
        <script>(function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
                js = d.createElement(s); js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.4&appId=965974406775399";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
         -->
    <!-- E:BY:JZAH:09-25-15 CONNECT WITH FB THINGY -->
    
    <!-- 
        references: 
        http://www.bootply.com/rzNQTlDlFX 
        https://usman.it/themes/charisma/calendar.html
        http://fullcalendar.io/docs/usage/
        http://fullcalendar.io/scheduler/
        
        http://www.w3schools.com/bootstrap/bootstrap_grid_system.asp
    -->
</body>

</html>