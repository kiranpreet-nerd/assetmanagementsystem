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
					<li><a href='/logout'>LOGOUT</a></li>
					<li class = "dropdown">
                    <a href = "#" class = "dropdown-toggle" data-toggle = "dropdown"> Create New</a>
                    <span class="caret"></span>
                    <ul class = "dropdown-menu">
                       <li class = "active"><a href = "/newasset"> Asset</a></li>
                       <li class = "active"><a href = "/newaccessory"> Accessory</a></li>
                       <li class = "active"><a href = "/newconsumable"> Consumable</a></li>
                    </ul>
                    </li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<form:form action="" class="form-group" >
			<table class="table table-striped">
				<tr>
				    <th>Email</th>
				    <th>Employee name</th>
					<th>Date of Request</th>
					<th>Asset Name</th>
					<th>Asset Type</th>
					<th>Number of Assets needed</th>
					<th>Reason</th>
					<th>Actions</th>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>