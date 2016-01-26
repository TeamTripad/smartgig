<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Smart GIG</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/agency.css" rel="stylesheet">
    
    <!-- MY CSS -->
	<link href="<%=request.getContextPath()%>/resources/css/smartgig.css" rel="stylesheet">
		
    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath()%>/resources/css/fonts/glyphicons-halflings-regular.ttf">
    <link href="<%=request.getContextPath()%>/resources/css/fonts/glyphicons-halflings-regular.woff">
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">
	<div id="fb-root"></div>


	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
		<!-- 
			<a class="navbar-brand page-scroll" href="view/SmartGIG/homepage.jsp"><i
				class="fa fa-gift"></i> Smart GIG</a> -->
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<!-- Header -->
	<header> <!-- S:LOG-IN & SIGN-UP -->
	<div class="container intro-text">
		<div class="container">

			<div class="row">
				<div class="col-lg-12">
					<div class="intro-message">
					<br><br><br><br>
						<h1>Smart Gift Idea Generator</h1>
						<h3>An Easy Way of Giving Gifts</h3>
						<hr class="intro-divider">
						<ul class="list-inline intro-social-buttons">
						
							
							<!--  href="<%//=fbConnection.getFBAuthUrl()%>"-->
							<li><a href="http://www.facebook.com/dialog/oauth?client_id=1647100832211574&redirect_uri=http://localhost:8089/SmartGIG/fbconnect/auth/callback&scope=email"
								class="btn btn-default btn-lg"><i
									class="fa fa-facebook fa-fw"></i> <span class="network-name">Log in with FACEBOOK</span></a>
							</li>
						</ul>
					</div>
				</div>
			</div>

		</div>

	</div>
	
	
	<!-- <div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="true" data-auto-logout-link="true"></div> -->
	<!-- E:LOG-IN & SIGN-UP --> </header>
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/classie.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/js/jqBootstrapValidation.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<%=request.getContextPath()%>/resources/js/agency.js"></script>
</body>
</html>