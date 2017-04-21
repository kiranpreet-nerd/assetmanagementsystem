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

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Create New Asset</h3>
					</div>
					<br>
					<form:form action="/asset" method="post" class="form-group"
						align="center" commandName = "asset">
						<div class = "form-group" >
						    <label class="col-sm-4 control-label" for = "company" > Company </label>
						    <select class = "form-control" name = "company">
						        <option> MacBook Pro </option>
						        <option> Dell </option>
						    </select>
						</div><br>
						<div class = "form-group" >
					           <label class="col-sm-4 control-label">Asset Tag </label>
					           <input type="text" name="tag" value = ""  class="form-control" >
					     </div> <br>
					     <div class = "form-group" >
						    <label class="col-sm-4 control-label" for = "model" > Model </label>
						    <select class = "form-control" id = "sell" name = "model">
						        <option> model 1 </option>
						        <option> model 2 </option>
						    </select>
						</div>&nbsp;<button type = "submit" value = "submit" name = "newmodelbutton" class="btn btn-success"> New </button><br>
						<div class = "form-group" >
						    <label class="col-sm-4 control-label" for = "status" > Status </label>
						    <select class = "form-control" id = "sell" name = "status">
						        <option> Ready To Deploy </option>
						        <option> Pending </option>
						        <option> Lost/Stolen  </option>
						        <option> Broken - can't be repaired </option>
						        <option>Out For Repair </option>
						    </select>
						</div>&nbsp; <button type = "submit" value = "submit" name = "newstatusbutton" class="btn btn-success"> New </button><br>
						<div class = "form-group" >
					           <label class="col-sm-4 control-label">Serial Number </label>
					           <input type="text" name="serialnumber" value = ""  class="form-control" >
					     </div> <br>
					     <div class = "form-group" >
					           <label class="col-sm-4 control-label input-group date" data-provide="datepicker" data-date-format="yyyy-MM-dd"> Purchase Date </label>
					           <input type="text" name="purchasedate" value = ""  class="form-control" >
					     </div> <br>
					     <div class = "form-group" >
						    <label class="col-sm-4 control-label" for = "supplier"> Supplier </label>
						    <select class = "form-control" id = "sell" name = "supplier">
						        <option> Supplier 1 </option>
						        <option> Supplier 2 </option>
						        <option> Supplier 3 </option>
						    </select>
						</div>&nbsp; <button type = "submit" value = "submit" name = "newsupplierbutton" class="btn btn-success"> New </button><br>
						<div class = "form-group" >
					           <label class="col-sm-4 control-label">Order Number </label>
					           <input type="number" name="ordernumber" value = ""  class="form-control" >
					     </div> <br>
					     <div class = "form-group" >
					           <label class="col-sm-4 control-label">Purchase cost(Per Asset Cost) </label>
					           <input type="number" name="purchasecost" value = ""  class="form-control" >
					     </div> <br>

 					     <div class = "form-group" >
					           <label class="col-sm-4 control-label">Warranty </label>
					           <input type="number" name="warranty" value = "" placeholder = "enter value in months" class="form-control" >
					     </div><br>
					     <div class = "form-group" >
					           <label class="col-sm-4 control-label">Quantity </label>
					           <input type="number" name="quantity" value = ""  class="form-control" >
					     </div><br>
					     <div class = "form-group" >
					           <label class="col-sm-4 control-label">Total Cost </label>
					           <input type="number" readonly = readonly name="totalcost" value = ""  class="form-control" >
					     </div><br>
					     <button type = "submit" value = "submit" name = "saveassetbutton" class="btn btn-success"> SAVE </button><


					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
