<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Smart GIG | E-commerce</title>
	<link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

    <!-- styles -->
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/animate.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/owl.carousel.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/owl.theme.css" rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="<%=request.getContextPath()%>/resources/css/style.default.css" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="<%=request.getContextPath()%>/resources/css/custom.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/resources/js/respond.min.js"></script>

    <link rel="shortcut icon" href="favicon.png">
</head>
<body>
	<%pageContext.setAttribute("state", request.getAttribute("state")); %>
	<%pageContext.setAttribute("categoryList", request.getAttribute("categoryList")); %>
	<%pageContext.setAttribute("subCategoryList", request.getAttribute("subCategoryList")); %>
	<!-- MESSAGE -->
	<%
		pageContext.setAttribute("msg", request.getAttribute("msg"));
	    String msg = pageContext.getAttribute("msg").toString();
		if(!msg.contentEquals("none")){
	        %>
	        <script>
	        	alert(msg);
	        </script>
	        <%
	        pageContext.setAttribute("msg", "none");
	    }
	%>
	<!-- *** TOPBAR ***_________________________________________________________ -->
    <div id="top">
        <div class="container">
        	<div class="col-md-6 offer" data-animate="fadeInDown"></div>
            <div class="col-md-6" data-animate="fadeInDown">
                <ul class="menu">
                	<li>
                    	<a href="<%=request.getContextPath()%>/admin/home">Dashboard</a>
                    </li>
                    <li>
                    	<a href="#">View Site</a>
                    </li>
                    <li>
                    	<a href="#">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- ADD NEW CATEGORY FORM -->
        <div id="top">
	         <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
		         <div class="modal-dialog modal-sm">
		             <div class="modal-content">
		                 <div class="modal-header">
		                     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                     <h4 class="modal-title" id="Login">Add Category</h4>
		                 </div>
		                 <div class="modal-body">
		                     <form action="/SmartGIG/admin/category/add" method="get">
		                         <div class="form-group">
		                             <input type="text" class="form-control" id="email-modal" placeholder="category name" name="catName" required>
		                         </div>
		                         <p class="text-center">
		                             <button class="btn btn-primary" type="submit"><i class="fa fa-sign-in"></i> Submit</button>
		                         </p>
		                         <!-- Spring security feature; necessary when using get web service -->
		                         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		                     </form>
		                     <!-- <p class="text-center text-muted">Not registered yet?</p>
		                     <p class="text-center text-muted"><a href="register.html"><strong>Register now</strong></a>! It is easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p> -->
		                 </div>
		             </div>
		         </div>
	     	</div>
        </div>
    </div>

    <!-- *** TOP BAR END *** -->
    <!-- *** NAVBAR ***_________________________________________________________ -->

    <div class="navbar navbar-default yamm" role="navigation" id="navbar">
        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand home" href="<%=request.getContextPath()%>/admin/home" data-animate-hover="bounce">
                    <img src="<%=request.getContextPath()%>/resources/img/logoAdmin.png" alt="Obaju logo" class="hidden-xs">
                    <img src="<%=request.getContextPath()%>/resources/img/logo-Admin-small.png" alt="Obaju logo" class="visible-xs"><span class="sr-only">Obaju - go to homepage</span>
                </a>
                <div class="navbar-buttons">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-align-justify"></i>
                    </button>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#search">
                        <span class="sr-only">Toggle search</span>
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
            <!--/.navbar-header -->
            <!--/.nav-collapse ADD CATEGORY-->
            <div class="navbar-buttons">
                <div class="navbar-collapse collapse right" id="basket-overview">
                    <a href="#" data-toggle="modal" data-target="#login-modal" class="btn btn-primary navbar-btn"><i class="fa fa-plus-square"></i><span class="hidden-sm">Add New Category</span></a>
                </div>
            </div>
        </div>
        <!-- /.container -->
    </div>
    <!-- /#navbar -->

    <!-- *** NAVBAR END *** -->
    <div id="all">

        <div id="content">
            <c:choose>
		      <c:when test="${state eq 0 || state eq 1}">
			    <!-- *** DYNAMIC LOADING OF CATEGORIES ***_________________________________________________________ -->
            	<jsp:include page="category.jsp"/>
			  </c:when>
		      
		      <c:when test="${state eq 2}">
			    <!-- *** DYNAMIC LOADING OF SUBCATEGORIES ***_________________________________________________________ -->
			    <jsp:include page="subcategory.jsp"/>
		      </c:when>
		  	</c:choose>

            <!-- *** SMART GIG SITE ***_________________________________________________________ -->
            <jsp:include page="../site.jsp"/>
			
			<!-- *** FOOTER ***_________________________________________________________ -->
	        <jsp:include page="../footer.jsp"/>
        </div>
        <!-- /#content -->
    </div>
    <!-- /#all -->

    <!-- *** SCRIPTS TO INCLUDE ***_________________________________________________________ -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.cookie.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/waypoints.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/modernizr.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-hover-dropdown.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/front.js"></script>
</body>
</html>