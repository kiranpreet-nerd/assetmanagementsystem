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
		<form:form action="" class="form-group" commandName = "updateasset">
			<table class="table table-striped">
				<tr>
					<th>Company</th>
					<th>Asset Type </th>
					<th>Asset Tag</th>
					<th>Model</th>
					<th>Status</th>
					<th>Serial Number</th>
					<th>Purchase Date</th>
					<th>Supplier</th>
					<th>Supplier Contact </th>
					<th>Order Number</th>
					<th>Purchase Cost</th>
					<th>Warranty</th>
					<th>Quantity</th>
					<th> Total Cost</th>
				</tr>
				<c:forEach items="${listAssets}" var="asset">
					<tr>
						<td>${asset.company}</td>
						<td>${asset.assettype}</td>
						<td>${asset.tag}</td>
						<td>${asset.model}</td>
						<td>${asset.status}</td>
						<td>${asset.serialnumber}</td>
						<td>${asset.purchasedate}</td>
						<td>${asset.supplier}</td>
						<td>${asset.suppliercontact}</td>
						<td>${asset.ordernumber}</td>
						<td>${asset.purchasecost}</td>
						<td>${asset.warranty}</td>
						<td>${asset.quantity}</td>
						<td>${asset.totalcost}</td>
						<td><a href="/deleteAsset?id=${asset.id}"> <span
								class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
							&nbsp; <a href="/getAsset?id=${asset.id}"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
				</c:forEach>

			</table>
		</form:form>
	</div>

</body>
</html>