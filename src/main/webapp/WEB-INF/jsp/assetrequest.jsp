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
	    	 var Asset = ['laptop','monitor'];
	    	 var Accessory = ['mouse', 'keyboard'];
	    	 var Consumable = ['printerink','paper'];
	    	 
	    	 switch(ddl1.value) {
	    	 case 'Asset':
	    		 ddl2.options.length = 0;
	    		 for(i=0; i < Asset.length; i++) {
	    			 createOption(ddl2, Asset[i], Asset[i]);
	    		 }
	    		 break;
	    	 case 'Accessory':
	    		 ddl2.options.length = 0;
	    		 for(i=0; i < Accessory.length; i++) {
	    			 createOption(ddl2, Accessory[i], Accessory[i]);
	    		 }
	    		 break;
	    	 case 'Consumable':
	    		 ddl2.options.length = 0;
	    		 for(i=0; i < Consumable.length; i++) {
	    			 createOption(ddl2, Consumable[i], Consumable[i]);
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
	<!--  <script>
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
	      }
	 
	 </script>
	  -->
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
					<form:form name = "form" action="/assetrequest" method="post" class="form-group"
						align="center" commandName="assetrequest">
						
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
							<label class="col-sm-4 control-label" for="type"> Type </label>
							<select class="form-control" name="assettype" id="ddl"
								onChange="configureDropDownLists(this,document.getElementById('ddl2'))">
								<option value="">None</option>
								<option value="Asset">Asset</option>
								<option value="Accessory">Accessory</option>
								<option value="Consumable">Consumable</option>
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
								needed </label> <input type="number" name="quantity" value=""
								class="form-control">
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
				<form:form action="/assetrequest" class="form-group" commandName = "assetrequest">
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
								<td> <a href ="/deleterequestedassets?id=${listAssetsRequest.id}"><span
								class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
						</c:forEach>

					</table>
				</form:form>
			</div>

		</div>
	</div>

</body>
</html>