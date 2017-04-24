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
<link href="${jstlCss}" rel="stylesheet" />

<script type="text/javascript">
	    $(document).ready(function() {
	    	$('#forgotpasswordbutton').click(function(){
	    		alert('enter password');
	    	});
	    });
	 
	 </script>
	 <title> FORGOT PASSWORD</title>

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/login">BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		
		<form:form action="/recoverypassword" method="post" class="form-signin" align="center" commandName = "passwordform">
			<div class="form-group">
				<label >Email </label> 
				<input type="text" name="email"
					placeholder="enter email"><br>
				<br> ${emailError} 
			</div>
			<button type = "submit" name = "forgotpasswordbutton" value = "submit" class="btn btn-success"> GET PASSWORD</button><br><br>
		</form:form>
	</div>
</body>
</html>