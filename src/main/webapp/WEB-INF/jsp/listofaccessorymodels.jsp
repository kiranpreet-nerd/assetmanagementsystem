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
				<li class="active"><a href="/requestedassets">BACK</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<form:form action="" class="form-group" >
			<table class="table table-striped">
				<tr>
					<th>Model Name</th>
					<th>Action</th>
				</tr>

				<c:forEach items="${listmodelaccessory}" var="listmodelaccessory">
					<tr>
						<td>${listmodelaccessory.model}</td>
						<td><button type = "submit" name = "deleteModelbutton" onclick = "return deleteAccessoryModel(${listmodelaccessory.id},'${listmodelaccessory.model}')">
								<span class="glyphicon glyphicon-trash"></span></button></td>
				</c:forEach>

			</table>
		</form:form>
	</div>
<script type = "text/javascript">
var modelError = '${modelError}';
if(modelError != "") {
	alert(modelError);
}
	 function deleteAccessoryModel(id,model) {
		   var confirmModel = confirm("Do you want to delete this Model?");
		   if(confirmModel == true) {
				  confirmModelDelete();
			  }else {
				  return false;
			  };
		function confirmModelDelete() {
		   
	   $.ajax({
	        url : "/deleteAccessoryModel",
	        type : "GET",
	        dataType : 'text',
	        data : {
	        	"id" : id,
	        	"model" : model
	        }
	       });
		}
	   }
	</script>
</body>
</html>