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
	 
	<%String email = request.getParameter("email"); request.getSession().setAttribute("email", email);%>
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
					<li><a href="/login">BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-3">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">Request Asset</h3>
					</div>
					<br>
					<form:form action="/assetrequest" method="post" 
						class="form-group" align="center" commandName = "assetrequest">
					<div class = "form-group" ${status.error ? 'has-error' : ''} >
					           <label class="col-sm-4 control-label">Email </label>
					           <input type="text" readonly = "readonly" name="email" value = <%=email %>  class="form-control" >
					           <div>
								<form:errors path="assetname"></form:errors>
							</div>
				   </div> <br>
					<div class = "form-group" ${status.error ? 'has-error' : ''} >
					           <label class="col-sm-4 control-label">Asset name </label>
					           <input type="text" name="assetname" value = ""  class="form-control" >
					           <div>
								<form:errors path="assetname"></form:errors>
							</div>
				   </div> <br>
				    <div class = "form-group" ${status.error ? 'has-error' : ''}>
						    <label class="col-sm-4 control-label" for = "type" > Asset type </label>
						    <select class = "form-control" name = "assettype" id = "sell">
						        <option> Asset </option>
						        <option> Accessory</option>
						        <option> Consumable </option>
						      </select>
						      <div>
								<form:errors path="assettype"></form:errors>
							</div>
					</div><br>
					<div class = "form-group" ${status.error ? 'has-error' : ''}>
					           <label class="col-sm-4 control-label">Number of assets needed </label>
					           <input type="number" name="quantity" value = ""  class="form-control" >
					           <div>
								<form:errors path="quantity"></form:errors>
							</div>
				   </div> <br>
				   <div class = "form-group" ${status.error ? 'has-error' : ''}>
					           <label class="col-sm-4 control-label">Reason </label>
					            <textarea class="form-control" name = "reason" rows="5"></textarea>
					            <div>
								<form:errors path="reason"></form:errors>
							</div>
				   </div> <br>
				   <div class = "form-group" ${status.error ? 'has-error' : ''} >
					           <label class="col-sm-4 control-label">Date of request </label>
					            <input type = "date" name="requestdate" value = "" class="form-control" >
					            <div>
								<form:errors path="requestdate"></form:errors>
							</div>
				   </div> <br>
				   <button type = "submit" value = "submit" name = "requestbutton" class="btn btn-success" > REQUEST </button>
				   
					
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>