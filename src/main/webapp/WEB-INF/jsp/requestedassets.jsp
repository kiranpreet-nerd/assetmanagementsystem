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
      <li class="active"><a href="/logout">LOGOUT</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown">CREATE NEW
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#"> Asset</a></li>
          <li><a href="#"> Accessory</a></li>
          <li><a href="#"> Consumable</a></li>
        </ul>
      </li>
    </ul>
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
				<c:forEach items="${listAssets}" var="assetrequest user">
					<tr>
					    <td>${user.email}</td>
					    <td>${user.firstname} ${user.lastname}</td>
						<td>${assetrequest.requestdate}</td>
						<td>${assetrequest.assetname}</td>
						<td>${assetrequest.reason}</td>
						<td>${assetrequest.quantity}</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</div>

</body>
</html>