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
					<form:form action = "update" class = "form-group" align = "center" modelAttribute = "updateForm" method = "post">
					           <div class = "form-group">
					           <label class="col-sm-4 control-label">Name </label> 
					           <input type="text" name="firstname" placeholder="enter Name" class="form-control" > 
					           </div> <br>
					           <div class = "form-group">
					           <label class="col-sm-4 control-label">Designation </label> 
					           <input type="text" name="designation" placeholder="enter designation" class="form-control" > 
					           </div> <br>
					           <div class = "form-group">
					           <label class="col-sm-4 control-label">Role </label> 
					           <div class=" col-sm-3 checkbox checkbox-primary">
					           <input id="checkbox" type="checkbox" > Super Admin <br>
					           </div><br>
                               <div class=" col-sm-3 checkbox checkbox-primary">
					           <input id="checkbox" type="checkbox" > Admin <br>
					           </div><br>
					           <div class="col-sm-1 checkbox checkbox-primary">
					           <input id="checkbox" type="checkbox" checked> Employee <br>
					           </div>
					           </div><br> 
					           <div>
					               <button type = "submit" value = "submit" name = "editbutton" class="btn btn-success"  > Update </button>
					           </div>
					</form:form>
					</div>
				</div>
			</div>

</body>
</html>