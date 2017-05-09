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
	    	 var numeric = /(?=.*[!@#$%^&*])/;
	       	 if(numeric.test(form.supplier.value)) {
	       		 alert("It must be alpha numeric");
	       		 document.form.supplier.focus();
	       		 return false;
	       	 }
	          if (document.form.supplier.value == "") {
	             alert("supplier name required");
	             document.form.supplier.focus();
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
				<li><a href = "/asset">BACK</a>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Create New Supplier</h3>
					</div>
					<br>
					<form:form name = "form" action="/newsupplier" method="post" class="form-group"
						align="center" commandName="supplier" onsubmit = "return validate();">
						<div class="form-group">
							<label class="col-sm-4 control-label">Supplier </label> <input
								type="text" name="supplier" value="" class="form-control">
						</div>
						<br>
						<button type="submit" value="submit" name="addsupplierbutton"
							class="btn btn-success">ADD SUPPLIER</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>