<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	 <%
     String email=request.getParameter("email");
     request.getSession().setAttribute("email", email);
     String firstname=request.getParameter("firstname");
     request.getSession().setAttribute("firstname", firstname);
     String lastname=request.getParameter("lastname");
     request.getSession().setAttribute("lastname", lastname);
     String designation=request.getParameter("designation");
     request.getSession().setAttribute("designation", designation);
     String role=request.getParameter("role");
     request.getSession().setAttribute("role", role);
     
     %>
	
<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<c:url value="/css/userdesign.css" var="jstlCss" />
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/users">BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-3">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">Edit Values</h3>
					</div>
				</div>
					<br>
					 <% out.println("hi"); %>
					<form:form action = "/updateuser"  method = "post" class = "form-group" align = "center" commandName="user" >
					<input type = "hidden" name = "${user.email}"/>
					           <div class = "form-group" >
					           <label class="col-sm-4 control-label">First Name </label> 
					           <input type="text" name="firstname" value = "${user.firstname}" class="form-control" > 
					           <form:errors path="firstname"/>
					           </div> <br>
					           <div class = "form-group" >
					           <label class="col-sm-4 control-label">Last Name </label> 
					           <input type="text" name="lastname"  value = "${user.lastname}" class="form-control" > 
					           <form:errors path="lastname" />
					           </div> <br>
					            <div class = "form-group">
					           <label class="col-sm-4 control-label">Email </label> 
					           <input type="text" name="email" readonly = "readonly" value = "${user.email}" class="form-control" > 
					           </div> <br>
					           <div class = "form-group">
					           <label class="col-sm-4 control-label" >Designation </label> 
					           <input type="text" name="designation" value = "${user.designation}" class="form-control" > 
					           <form:errors path="designation" />
					           </div> <br>
					           <div class = "form-group" >
					           <label class="col-sm-4 control-label" >Role </label> 
					           <div class=" col-sm-3 checkbox checkbox-primary">
					           <form:errors path="role"  />
					           <input id="checkbox" type="checkbox" name = "${user.role}" value = " ROLE_SUPER"> Super Admin <br>
					           </div><br>
                               <div class=" col-sm-3 checkbox checkbox-primary">
					           <input id="checkbox" type="checkbox" name = "${user.role}" value = "ROLE_ADMIN"> Admin <br>
					           </div><br>
					           <div class="col-sm-1 checkbox checkbox-primary">
					           <input id="checkbox" type="checkbox" name = "${user.role}" value = "ROLE_USER"> Employee <br>
					           </div>
					           </div><br> 
					            <button type = "submit" value = "submit" name = "editbutton" class="btn btn-success" onClick="updateUser()"> Update </button>
					        
					</form:form>
					</div>
				</div>
			</div>

</body>
</html>