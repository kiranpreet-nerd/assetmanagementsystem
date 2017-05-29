<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<script>
	     function validate() {
	    	 if(document.form.email.value == "" && document.form.password.value == "") {
	    		 alert("Email and password required");
	    		 document.form.email.focus();
	    		 return false;
	        }
	    	 if (document.form.email.value == "") {
	             alert("Email required");
	             document.form.email.focus();
	             return false;
	         }
	         if (document.form.password.value == "") {
	             alert("Password required");
	             document.form.password.focus();
	             return false;
	         }
	     }
	</script>
	
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href = "/register"><span class="glyphicon glyphicon-user"></span>SIGN UP</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					   
					<div class = "redalert">${loginError}</div>
					<form:form  name = "form" action="/login" method="post" class="form-inline"
						align="center" commandName="userform" onsubmit="return validate();">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="sr-only col-sm-2 control-label">Email </label> <input
								class="form-alignment" type="text" name="email" width="20px"
								placeholder="enter email">
							<div align="left">
								<form:errors path="email"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="sr-only col-sm-2 control-label">Password </label> <input
								class="form-align" type="password" name="password"
								placeholder="enter password" width="20px">
							<div>
								<form:errors path="password"></form:errors>
							</div>
						</div>
						<br>
						<br>
						<button class="btn btn-success" type="submit" name="loginbutton"
							value="submit"  align="center" >LOGIN</button>
						<br>
						<br>
						<a href="/forgotpassword">Forgot Password<span class="glyphicon glyphicon-question-sign"></span></a>
						<br>
						<br>
						<br>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>