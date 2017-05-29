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
		<form:form action="" class="form-group" commandName = "companies" >
			<table class="table table-striped">
				<tr>
					<th>Company name</th>
					<th>Action</th>
				</tr>

				<c:forEach items="${listcompany}" var="listcompany">
					<tr>
						<td>${listcompany.company}</td>
						<td><button type = "submit" name = "deletecompanybutton" onclick = "return deleteCompany(${listcompany.id},'${listcompany.company}')">
								<span class="glyphicon glyphicon-trash"></span></button></td>
				</c:forEach>

			</table>
		</form:form>
	</div>
<script type = "text/javascript">

var companyError = '${companyError}';
if(companyError != "") {
	alert(companyError);
}
	 function deleteCompany(id,company) {
		   var confirmCompany = confirm("Do you want to delete this company?");
		   if(confirmCompany == true) {
				  confirmCompanyDelete();
			  }else {
				  return false;
			  };
		function confirmCompanyDelete() {
		   
	   $.ajax({
	        url : "/deleteCompany",
	        type : "GET",
	        dataType : 'text',
	        data : {
	        	"id" : id,
	        	"company" : company
	        }
	       });
		}
	   }
	</script>
</body>
</html>