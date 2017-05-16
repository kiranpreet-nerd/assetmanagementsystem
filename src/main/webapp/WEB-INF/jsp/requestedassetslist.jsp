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
	 <script type="text/javascript">

    var link,color;
 function disable_link(id) { 
	 alert("#"+id);
	 $(id).click (function(e){
		 alert("*"+id);
	       e.preventDefault();
	})
  //link = document.getElementById('Link1').href;

 // document.getElementById('Link1').removeAttribute('href');
  //document.getElementById('testlink').style.color = "grey";

   } 


 function enable_link() { 

  document.getElementById('Link1').setAttribute("href",link);

   } 

</script>
	
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
		<form:form action="" class="form-group">
			<table class="table table-striped">
				<tr>
					<th>Type</th>
					<th>Name</th>
					<th>Configuration</th>
					<th>Quantity</th>
					<th>Reason</th>
					<th>Request Date</th>
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
						<td><a href="/statusCheckRequest?id=${assetrequest.id}&email=${user.email}"><span class="glyphicon glyphicon-check"></span></a> &nbsp; </td>
						<td><a href = "/requestNotAssign?id=${assetrequest.id}&email=${user.email}">REASON FOR NOT ASSIGNING</a></td>
				</c:forEach>

			</table>
		</form:form>
	</div>

</body>
</html>