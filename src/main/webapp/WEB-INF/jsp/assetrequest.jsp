<%@page
	import="java.text.SimpleDateFormat, java.util.Calendar, java.text.DateFormat"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<spring:url value="/css/main.css" var="springCss" />
<link href="${springCss}" rel="stylesheet" />


<script type="text/javascript">
	     function configureDropDownLists(ddl1,ddl2) {
	    	 
	    	 switch(ddl1.value) {
	    	 case 'asset':
	    		 ddl2.options.length = 0;
	    		 var myMap = {<c:forEach items="${listmodelname}" var="item"> ${item.id}: '${item.model}' ${not loop.last ? ',' : ''} </c:forEach>};  
 	    		 // Object.keys(myMap).length
 	    		 for(key in myMap){		 
 	    			 createOption(ddl2, myMap[key], myMap[key]);
 	    		  }
	    		 break;
	    	 case 'accessory':
	    		 ddl2.options.length = 0;
	    		 var myMap1 = { <c:forEach items="${listmodelaccessory}" var="item1"> ${item1.id}: '${item1.model}' ${not loop.last ? ',' : ''} </c:forEach>}; 
 	    		 // Object.keys(myMap).length
	    		 for(key in myMap1){		 
	    			 createOption(ddl2, myMap1[key], myMap1[key]);
	    		  }
	    		 break;
	    	 case 'consumable':
	    		 ddl2.options.length = 0;
	    		 var myMap2 = { <c:forEach items="${listmodelconsumable}" var="item2"> ${item2.id}: '${item2.model}' ${not loop.last ? ',' : ''} </c:forEach>};  
	    		 // Object.keys(myMap).length
	    		 for(key in myMap2){		 
	    			 createOption(ddl2, myMap2[key], myMap2[key]);
	    		 }
	    		 break;
	    		 default:
	    			 ddl2.options.length = 0;
	    		 break;
	    	 }
	     }
	 
	     function createOption(ddl, text, value) {
	    	 var opt = document.createElement('option');
	    	 opt.value = value;
	    	 opt.text = text;
	    	 ddl.options.add(opt);
	     }
	 
	 
	 </script>
<script>
	      function validate() {
	    	  if (document.form.email.value == "") {
	 		             alert("email required");
	 		             document.form.email.focus();
	 		             return false;
	 		         }
	    	  if (document.form.assettype.value == "") {
	 		             alert("asset type required");
	 		             document.form.assettype.focus();
	 		             return false;
	 		         }
	    	  if (document.form.assetname.value == "") {
	 		             alert("asset name required");
	 		             document.form.assetname.focus();
	 		             return false;
	 		         }
	    	  if (document.form.quantity.value == "") {
	 		             alert("quantity required");
	 		             document.form.quantity.focus();
	 		             return false;
	 		         }
	    	  if (document.form.reason.value == "") {
	 		             alert("reason required");
	 		             document.form.reason.focus();
	 		             return false;
	 		         }
	    	  if (document.form.requestdate.value == "") {
	 		             alert("request date required");
	 		             document.form.requestdate.focus();
	 		             return false;
	 		         }
	    	  var quantity = document.getElementById('quantity').value;
	    	  if(quantity <= 0) {
	    		  alert("quantity must be greater than zero");
	    		  document.form.quantity.focus();
	    		  return false;
	    	  }
	      }
	 
	 </script>
	 
	 
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<c:url value="/css/userdesign.css" var="jstlCss" />
</head>
<body>
	<%
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             Calendar cal = Calendar.getInstance();           
         %>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				    <li><a href="/statusassetslist?email=${email}">Assets Request List</a></li>
				    <li><a href= "/changePassword?email=${email}"> CHANGE PASSWORD</a>
					<li><a href="/logout">LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">REQUEST ASSET</h3>
					</div>
					<br>
					<form:form name="form" action="/assetrequest" method="post"
						class="form-group" align="center" commandName="assetrequest"
						onsubmit="return validate();">

						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label">Email </label> <input
								type="text" readonly="readonly" name="email" value="${email}"
								class="form-control">
							<div>
								<form:errors path="assetname"></form:errors>
							</div>
						</div>
						<br>

						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label" for="type"> Type </label> <select
								class="form-control" name="assettype" id="ddl"
								onChange="configureDropDownLists(this,document.getElementById('ddl2'))">
								<option value="">None</option>
								<option value="asset">Asset</option>
								<option value="accessory">Accessory</option>
								<option value="consumable">Consumable</option>
							</select>
							<div>
								<form:errors path="assettype"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label"> Name </label> <select
								class="form-control" name="assetname" id="ddl2"></select>
							<div>
								<form:errors path="assetname"></form:errors>
							</div>
						</div>
						<br>

						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label">Number of assets
								needed </label> <input type="number" name="quantity" id="quantity"
								value="" class="form-control">
							<div>
								<form:errors path="quantity"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label">Reason </label>
							<textarea class="form-control" name="reason" rows="5"></textarea>
							<div>
								<form:errors path="reason"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label">Date of request </label> <input
								type="text" name="requestdate" readonly=readonly
								value="<%=    dateFormat.format(cal.getTime())%>"
								class="form-control">
							<div>
								<form:errors path="requestdate"></form:errors>
							</div>
						</div>
						<br>
						<button type="submit" value="submit" name="requestbutton"
							class="btn btn-success">REQUEST</button>


					</form:form>
				</div>

			</div>
			<div style='float: left'>
				<form:form action="/assetrequest" class="form-group"
					commandName="assetrequest">
					<table class="table table-striped" style="width: 40%">
					    <tr>
							<th>Type</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Actions</th>
						</tr>
						<c:forEach items="${listAssetsRequest}" var="listAssetsRequest">
							<tr>
								<td>${listAssetsRequest.assettype}</td>
								<td>${listAssetsRequest.assetname}</td>
								<td>${listAssetsRequest.quantity}</td>
								<td><a
									href="/deleterequestedassets?id=${listAssetsRequest.id}"><span
										class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
						</c:forEach>

					</table>
				</form:form>
			</div>

		</div>
	</div>
	<script type="text/javascript">
   var modelAttributeValue = '${typeRegistered}';
   if(modelAttributeValue != ""){
   alert(modelAttributeValue);
   }
</script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>