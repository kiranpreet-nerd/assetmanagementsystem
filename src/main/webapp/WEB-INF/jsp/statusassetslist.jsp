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
				<li class="active"><a href="/assetrequest">BACK</a></li>
				
			</ul>
		</div>
	</nav>
	<div class="container">
		<form:form action="" class="form-group" commandName = "statusasset">
			<table class="table table-striped">
				<tr>
					<th>Type</th>
					<th>Name</th>
					<th>Configuration</th>
					<th>Quantity</th>
					<th>Reason</th>
					<th>Request Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${listAssets}" var="assetrequest">
					<tr>
						<td>${assetrequest.assettype}</td>
						<td>${assetrequest.assetname}</td>
						<td>${assetrequest.windows},${assetrequest.category},${assetrequest.ram},${assetrequest.harddisk}</td>
						<td>${assetrequest.quantity}</td>
						<td>${assetrequest.reason}</td>
						<td>${assetrequest.requestdate}</td>
						<td>${assetrequest.status}</td>
						<td><a href="/statusRecieveRequest?id=${assetrequest.id}&email=${user.email}"
						id ="${assetrequest.id}">RECIEVE</a> &nbsp; </td>
				</c:forEach>

			</table>
		</form:form>
	</div>

</body>
</html>