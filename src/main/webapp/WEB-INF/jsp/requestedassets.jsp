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
	 <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<c:url value="/css/userdesign.css" var="jstlCss" />
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-It</a>
			</div>
			
			<ul class="nav navbar-nav">
				
	           <li class="active"><a href="/assetslist">ASSETS LIST &nbsp;<span class="glyphicon glyphicon-th-list"></span></a></li>
	           <li class = "active"><a href="/asset"> ADD ASSET</a></li>
	           <li class = "active"><a href="/changePassword?email=${email}"> CHANGE PASSWORD</a></li>
	          <li class = "dropdown active" ><a class = "dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" href = "#">
									DELETE <span class="caret"></span></a>
								<ul class="dropdown-menu" >
									<li><a href="/listOfAssetModels">Asset Model</a></li>
									<li><a href="/listOfAccessoryModels">Accessory Model</a></li>
									<li><a href="/listOfConsumableModels">Consumable Model</a></li>
									<li><a href="/listOfSuppliers">Supplier</a></li>
									<li><a href="/listOfCompanies">Company</a></li>
								</ul>
								</li> 
			  <li class="active"><a href="/logout">LOGOUT</a></li>
								
			</ul>
		</div>
	</nav>
	<div class="container">
		<form:form action="" class="form-group">
			<table class="table table-striped">
				<tr>
					<th>Email</th>
					<th>User Name</th>
					<th>Actions</th>
				</tr>

				<c:forEach items="${listEmail}" var="user">
					<tr>
						<td>${user.email}</td>
						<td>${user.firstname}${user.lastname}</td>
						<td><a href="/requestedassetslist?email=${user.email}">
								<span class="glyphicon glyphicon-check"></span></a></td>
				</c:forEach>

			</table>
		</form:form>
	</div>

</body>
</html>