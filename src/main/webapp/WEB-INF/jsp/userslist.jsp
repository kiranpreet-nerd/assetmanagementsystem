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
					<li><a href='/listOfApprovalUsers'>APPROVAL LISTS</a></li>
					<li><a href='/add'>ADD USER</a></li>
					<li><a href='/changePassword?email=${email}'>CHANGE PASSWORD</a></li>
					<li><a href='/logout'>LOGOUT</a></li>
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
				<c:forEach items="${listUsers}" var="user">
					<tr>
						<td>${user.firstname} ${user.lastname}</td>
						<td>${user.email}</td>
						<td>${user.designation}</td>
						<td>${user.role}</td>
						<td><button type = "submit" name = "deleteuserbutton" onclick = "return Val('${user.email}')" ><span
								class="glyphicon glyphicon-trash" aria-hidden="true"></span></button> 
							&nbsp; <a href="/getUser?email=${user.email}"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
					</tr>
				</c:forEach>
			</table>
  		</form:form>
	</div>
	<script type = "text/javascript">
	 function Val(email) {
		   var confirmUser = confirm("Do you want to delete this user?");
		   if(confirmUser == true) {
				  confirmUserDelete();
			  }else {
				  return false;
			  };
		function confirmUserDelete() {
		   
	   $.ajax({
	        url : "/deleteUser",
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