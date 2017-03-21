<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />


<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<c:url value="/css/userdesign.css" var="jstlCssfile" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				   <li><a href = "<c:url value="/register"/>" >SIGN UP</a>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<legend align="center"> LOGIN </legend>
		<form:form action="" method="post" class="form-inline" align="center" >
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<label class = "sr-only">Email </label> 
				<input class = "textbox-alignment" type="text" name="email" width = "20px"
					placeholder="enter email" >
			</div><br>
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<label class = "sr-only">Password </label> 
				<input class = "textbox-alignment" type="password"
					name="password" placeholder="enter password">
			</div><br><br>
			<button class="btn btn-success" type = "submit" name = "loginbutton" value = "submit" onclick = "validateLogin()" > LOGIN </button><br><br>
			<a href="<c:url value="/forgotpassword"/>" >Forgot Password?</a>
		
		</form:form>
	</div>
</body>
</html>