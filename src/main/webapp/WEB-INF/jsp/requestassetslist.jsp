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
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-It</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/requestedassets">BACK</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<form:form action="" class="form-group">
			<table class="table table-striped">
				<tr>
					<th>Type</th>
					<th>Name</th>
					<th>Quantity</th>
					<th>Reason</th>
					<th>Request Date</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${listAssets}" var="assetrequest">
					<tr>
						<td>${assetrequest.assettype}</td>
						<td>${assetrequest.assetname}</td>
						<td>${assetrequest.quantity}</td>
						<td>${assetrequest.reason}</td>
						<td>${assetrequest.requestdate}</td>
						<td><a href="#">ASSIGN</a> &nbsp; <a href="#">CANCEL</a></td>
				</c:forEach>

			</table>
		</form:form>
	</div>

</body>
</html>