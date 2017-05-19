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
	          if (document.form.reason.value == "") {
	             alert("Either Give Valid reason else write no reason");
	             document.form.reason.focus();
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
					<li><a href="/requestedassets">BACK</a>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Give Reason</h3>
					</div>
					<br>
					<form:form name="form" action="/requestNotAssigned" method="post"
						class="form-group" align="center" commandName="reasonassign"
						onsubmit="return validate();">
						<div>
							<form:hidden path="id" />
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Reason For Not
								Assigning Request </label>
							<textarea class="form-control" name="reason" rows="5">
							</textarea>
						</div>
						<br>
						<button type="submit" value="submit" name="reasonbutton"
							class="btn btn-success">Go</button>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>