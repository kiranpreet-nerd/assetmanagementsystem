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
					<li><a href="/login">BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-3">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">Register</h3>
					</div>
					<br>
					<form:form action="" method="post" commandName="userform"
						class="form-group" align="center">
						<div class="form-group " ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label ">Email<span class = "required"> * </span></label> <input
								type="text" name="email" placeholder="enter email"
								class="form-control">
							<div>
								<form:errors path="email"></form:errors>
							</div>
							<div>${emailError}</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">First Name </label> <input
								type="text" name="firstname" placeholder="enter firstname"
								class="form-control">
							<div>
								<form:errors path="firstname"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Last Name </label> <input
								type="text" name="lastname" placeholder="enter lastname"
								class="form-control">
							<div>
								<form:errors path="lastname"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Designation </label> <input
								type="text" name="designation" placeholder="enter designation"
								class="form-control">
							<div>
								<form:errors path="designation"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Role </label> <input
								type="text" readonly="readonly" value="Employee" name="role"
								class="form-control">
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Password </label> <input
								type="password" name="password" placeholder="enter password"
								class="form-control">
							<div>
								<form:errors path="password"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Confirm Password</label> <input
								type="password" name="confirm"
								placeholder="enter password again" class="form-control">
							<div>
								<form:errors path="confirm"></form:errors>
							</div>
						</div>
						<button class="btn btn-success" type="submit"
							name="registerbutton" value="submit" onClick="registration()">
							REGISTER</button>
					</form:form>
				</div>
			</div>
			</div>
			</div>
			
			
</body>
</html>