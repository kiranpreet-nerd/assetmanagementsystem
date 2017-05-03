<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<!-- 
Bootstrap Core CSS
  <link
	href="/webjars/startbootstrap-sb-admin-2/1.0.2/css/bootstrap.min.css"
	rel="stylesheet" />

MetisMenu CSS
<link
	href="/webjars/startbootstrap-sb-admin-2/1.0.2/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet" />

Custom CSS
<link href="/webjars/startbootstrap-sb-admin-2/1.0.2/css/sb-admin-2.css"
	rel="stylesheet" />

Custom Fonts
<link
	href="/webjars/startbootstrap-sb-admin-2/1.0.2/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

Dropdown hover
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/bootstrap-dropdownhover.min.css" rel="stylesheet">

 -->

<script type="text/javascript">
 	     function configureDropDownLists(ddl1,ddl2) {
	    	
 	    	 switch(ddl1.value) {
 	    	 case 'asset':
 	    		 ddl2.options.length = 0;
 	    		 var myMap = { <c:forEach items="${listmodelname}" var="item"> ${item.id}: '${item.model}' ${not loop.last ? ',' : ''} </c:forEach>};  
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
<script type="text/javascript">
	function calculateTotalCost() {
		var purchasecost = document.getElementById('purchasecost').value;
		var quantity = document.getElementById('quantity').value;
		var result = parseInt(purchasecost) * parseInt(quantity);
		if (!isNaN(result)) {
			document.getElementById('totalcost').value = result;
		}
	}
</script>

<script>
	function validate() {
		if (document.form.model.value == "") {
			alert("model required");
			document.form.model.focus();
			return false;
		}
		if (document.form.tag.value == "") {
			alert("tag required");
			document.form.tag.focus();
			return false;
		}
		if (document.form.serialnumber.value == "") {
			alert("serial number required");
			document.form.serialnumber.focus();
			return false;
		}
		if (document.form.purchasedate.value == "") {
			alert("purchase date required");
			document.form.purchasedate.focus();
			return false;
		}
		if (document.form.suppliercontact.value == "") {
			alert("supplier contact required");
			document.form.suppliercontact.focus();
			return false;
		}
		if (document.form.suppliercontact.value.length != 10) {
			alert("length must be 10 of contact number");
			document.form.suppliercontact.focus();
			return false;
		}
		if (!(document.form.suppliercontact.value.match(/^[0-9]+$/))) {
			alert("only numbers are allowed");
			document.form.suppliercontact.focus();
			return false;
		}
		if (document.form.ordernumber.value == "") {
			alert("order number required");
			document.form.ordernumber.focus();
			return false;
		}
		if (document.form.purchasecost.value == "") {
			alert("purchase cost required");
			document.form.purchasecost.focus();
			return false;
		}
		if (document.form.warranty.value == "") {
			alert("warranty required");
			document.form.warranty.focus();
			return false;
		}
		if (document.form.quantity.value == "") {
			alert("quantity required");
			document.form.quantity.focus();
			return false;
		}
		if (document.form.totalcost.value == "") {
			alert("totalcost required");
			document.form.totalcost.focus();
			return false;
		}

	}
</script>
<script type="text/javascript">
	function serialError() {
		var modelAttributeValue = '${serialError}';
		//var serialError=  <c:out value = "${serialError}"/>;
		alert(modelAtrributeValue + "required");
	}
</script>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/requestedassets">BACK</a></li>
					<li class="active"><a href="/assetslist">ASSETS LIST</a></li>
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
					<form:form name="form" action="/asset" method="post"
						class="form-group" align="center" commandName="asset"
						onsubmit="return validate();">
						<div>
							<form:hidden path="id" />
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="company">
								Company </label>
							<form:select class="form-control" id="sell" name="company"
								path="company">
								<c:forEach var="listcompany" items="${listcompany}">
									<option value="${listcompany.company}">
										${listcompany.company}</option>
								</c:forEach>
							</form:select>
							<a href="/newcompany"> New <span
								class="glyphicon glyphicon-link"></span></a><br>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="assettype">
								Asset Type </label>
							<form:select class="form-control" id="ddl" name="assettype"
								path="assettype" value="${asset.assettype}"
								onChange="configureDropDownLists(this,document.getElementById('ddl2'))">
								<option value="">None</option>
								<option value="asset">Asset</option>
								<option value="accessory">Accessory</option>
								<option value="consumable">Consumable</option>
							</form:select>
						</div>
						<br>
						<div class="form-group" ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label">Asset Tag </label>
							<form:input type="text" name="tag" path="tag"
								value="${asset.tag}" class="form-control" />
							<div>
								<form:errors path="tag"></form:errors>
							</div>
							<div class="redalert">${tagError}</div>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="model"> Model
							</label>
							<form:select class="form-control" id="ddl2" name="model"
								path="model" value="${asset.model}">

							</form:select>
							<div class="dropdown">
								<a id="dlabel" data-toggle="dropdown" data-hover="dropdown">
									New <span class="caret"></span>
								</a>
								<ul class="dropdown-menu" aria-labelledby="dlabel">
									<li><a href="/newmodel">Asset Model</a></li>
									<li><a href="/newmodelaccessory">Accessory Model</a></li>
									<li><a href="/newmodelconsumable">Consumable Model</a></li>
								</ul>
							</div>
						</div>&nbsp;
						<div class="form-group">
							<label class="col-sm-4 control-label" for="status">
								Status </label>
							<form:select class="form-control" id="sell" name="status"
								path="status">
								<c:forEach var="liststatus" items="${liststatus}">
									<option value="${liststatus.status}">
										${liststatus.status}</option>
								</c:forEach>
							</form:select>
						</div>&nbsp; <a href="/newstatus"> New <span
							class="glyphicon glyphicon-link"></span></a>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Serial Number </label>
							<form:input type="text" name="serialnumber" path="serialnumber"
								value="${asset.serialnumber}" class="form-control"
								onkeyup="return serialError();" />
							<div>
								<form:errors path="serialnumber"></form:errors>
							</div>
						</div>
						<div class="redalert">${serialError}</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label input-group date"
								data-provide="datepicker" data-date-format="yyyy-MM-dd">
								Purchase Date </label>
							<form:input type="date" name="purchasedate" path="purchasedate"
								value="${asset.purchasedate}" class="form-control" />
							<div>
								<form:errors path="purchasedate"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="supplier">
								Supplier </label>
							<form:select class="form-control" id="sell" name="supplier"
								path="supplier">
								<c:forEach var="listsupplier" items="${listsupplier}">
									<option value="${listsupplier.supplier}">
										${listsupplier.supplier}</option>
								</c:forEach>
							</form:select>
						</div>&nbsp; <a href="/newsupplier"> New <span
							class="glyphicon glyphicon-link"></span></a>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Supplier contact
								number </label>
							<form:input type="text" name="suppliercontact"
								path="suppliercontact" value="${asset.suppliercontact}"
								class="form-control" maxlength="10" />
							<div>
								<form:errors path="suppliercontact"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Order Number </label>
							<form:input type="number" name="ordernumber" path="ordernumber"
								value="${ordernumber}" class="form-control" />
							<div>
								<form:errors path="ordernumber"></form:errors>
							</div>
							<div class="redalert">${orderError}</div>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Purchase cost(Per
								Asset Cost) </label>
							<form:input type="number" name="purchasecost" path="purchasecost"
								value="${purchasecost}" id="purchasecost" class="form-control"
								onkeyup="return calculateTotalCost();" />
							<div>
								<form:errors path="purchasecost"></form:errors>
							</div>
						</div>
						<br>

						<div class="form-group">
							<label class="col-sm-4 control-label">Warranty </label>
							<form:input type="number" name="warranty" path="warranty"
								value="${asset.warranty}" placeholder="enter value in months"
								class="form-control" />
							<div>
								<form:errors path="warranty"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Quantity </label>
							<form:input type="number" name="quantity"
								value="${asset.quantity}" id="quantity" path="quantity"
								class="form-control" onkeyup="return calculateTotalCost();" />
							<div>
								<form:errors path="quantity"></form:errors>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Total Cost </label>
							<form:input type="number" readonly="readonly" name="totalcost"
								id="totalcost" path="totalcost" value="${asset.totalcost}"
								class="form-control" />
							<div>
								<form:errors path="totalcost"></form:errors>
							</div>
						</div>
						<br>
						<button type="submit" value="submit" name="saveassetbutton"
							class="btn btn-success">SAVE</button>


					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	jQuery
	<script src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/jquery.js"></script>
	
	Bootstrap Core JavaScript
	<script
		src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/bootstrap.min.js"></script>
	
	Metis Menu Plugin JavaScript
	<script
		src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/plugins/metisMenu/metisMenu.min.js"></script>
	
	Custom Theme JavaScript
	<script src="/webjars/startbootstrap-sb-admin-2/1.0.2/js/sb-admin-2.js"></script>
	
	Bootstrap Dropdown Hover JS
	    <script src="webjars/startbootstrap-sb-admin-2/1.0.2/js/bootstrap-dropdownhover.min.js"></script> -->
</body>
</html>
