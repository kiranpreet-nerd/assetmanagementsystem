<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

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
					<li><a href='/users'>BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<form:form action="" class="form-group" commandName = "user">
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Designation</th>
					<th>Role</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${listApprovalUsers}" var="user">
					<tr>
						<td>${user.firstname} ${user.lastname}</td>
						<td>${user.email}</td>
						<td>${user.designation}</td>
						<td>${user.role}</td>
						<td><a href="/approveUser?email=${user.email}"> APPROVE </a></td>
						<td> <button type = "submit" name = "rejectbutton" id = "rejectbutton" onclick = "approveUserDelete('${user.email}');">REJECT</button></td>
					</tr>
				</c:forEach>
			</table>
  		</form:form>
	</div>
	<script type = "text/javascript">
	 var modelAttributeValue = '${tokenError}';
	   if(modelAttributeValue != ""){
	   alert(modelAttributeValue);
	   }
	   
	   var modelAttributeValue = '${approveUser}';
	   if(modelAttributeValue != ""){
	   alert(modelAttributeValue);
	   
	   }
	   function approveUserDelete(email) {
		   var confirmUser = confirm("Do you want to delete this user?");
		   if(confirmUser == true) {
				  confirmUser1();
			  }else {
				  return false;
			  };
		function confirmUser1() {
		   
	   $.ajax({
	        url : "/rejectUser",
	        type : "GET",
	        dataType : 'text',
	        data : {
	        	"email" : email
	        }
	       });
		}
	   }
	</script>
</body>
</html>