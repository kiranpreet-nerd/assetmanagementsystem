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

<!-- Bootstrap Core CSS -->
<link
	href="/webjars/startbootstrap-sb-admin-2/1.0.2/css/bootstrap.min.css"
	rel="stylesheet" />

<!-- MetisMenu CSS -->
<link
	href="/webjars/startbootstrap-sb-admin-2/1.0.2/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet" />

<!-- Custom CSS -->
<link href="/webjars/startbootstrap-sb-admin-2/1.0.2/css/sb-admin-2.css"
	rel="stylesheet" />

<!-- Custom Fonts -->
<link
	href="/webjars/startbootstrap-sb-admin-2/1.0.2/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
	
	<script>
	     function validate() {
	    	 if(document.form.email.value == "" && document.form.password.value == "") {
	    		 alert("Email and password required");
	    		 document.form.email.focus();
	    		 return false;
	        }
	    	 if (document.form.email.value == "") {
	             alert("email required");
	             document.form.email.focus();
	             return false;
	         }
	         if (document.form.password.value == "") {
	             alert("password required");
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
					<li><a href="<c:url value="/register"/>">SIGN UP</a>
				</ul>
			</div>
		</div>
	</nav>
	<div class="alert alert-info" <c:if test = "${param.message != null}" > <c:out value = "${param.message[0]}"/></c:if> >message</div>
	<br></br>
	<div class = "alert alert-info" <c:if test = "${param.error != null}"/>
  <c:out value = "${session[SPRING_SECURITY_LAST_EXCEPTION]}"/>>error</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					   
					${loginError}
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
							value="submit"  align="center">LOGIN</button>
						<br>
						<br>
						<a href="<c:url value="/forgotpassword"/>">Forgot Password?</a>
						<br>
						<br>
						<br>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/sb-admin-2.js"></script>
</body>
</html>