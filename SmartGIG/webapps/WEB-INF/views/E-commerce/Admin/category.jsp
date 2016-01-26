<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smart GIG | Site</title>
</head>
<body>
    <div id="advantages">
         <div class="container">
             <div class="same-height-row">
             	<c:if test="${fn:length(categoryList) eq 0}">
             	 	<div class="col-sm-12">
                      <div class="box same-height clickable">
                          <div class="icon"><i class="fa fa-heart"></i></div>
							<strong><h3><p class=".text-center">No Categories yet.</p></strong></h3>
                      </div>
                    </div>
		         </c:if>
		         <c:if test="${fn:length(categoryList) gt 0}">
			         <c:forEach items="${categoryList}" var="cl">
	                 	<div class="col-sm-4">
	                      <div class="box same-height clickable">
	                          <div class="icon"><i class="fa fa-heart"></i></div>
								<h3>
									<a href="<%=request.getContextPath()%>/admin/category/main/view?catId=${cl.catId}">
										<c:out value="${cl.categoryName}"/>
									</a>
									<p class="text-right">
										<a href="#" class="btn btn-primary navbar-btn">
											<i class="fa fa-trash-o"></i>
										</a>
									</p>
								</h3>
	                      </div>
	                  	</div>
	                </c:forEach>
		        </c:if>
             </div>
             <!-- /.row -->
         </div>
         <!-- /.container -->
     </div>
    <!-- /#advantages -->
<!-- *** DYNAMIC LOADING OF CATEGORIES END *** -->
</body>
</html>