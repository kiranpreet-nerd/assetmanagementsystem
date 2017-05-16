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
 	   <script> 
 	   
 	   function temporary(serialnumber) {
 		  var temp;
 		  var quantityDynamic = { <c:forEach items="${listAssets}" var="item2"> ${item2.serialnumber}: '${item2.quantity}' ${not loop.last ? ',' : ''} </c:forEach>};
 		 var subquantity = document.getElementById("subquantity"+serialnumber).value;
 		 var quantity = document.getElementById("quantity"+serialnumber).innerHTML;
 		 
 		 
 		 for(key in quantityDynamic) {
 			 if(subquantity > quantity) {
 				 alert("invalid quantity to be subtracted");
 			 } else if(subquantity < quantity) {
 			 temp = quantity - subquantity;
 			 if(key == serialnumber) {
 				 quantityDynamic[key] = temp;
 				//alert(quantityDynamic[key]);
 				 updateAssignedData(quantityDynamic[key],key,subquantity);
 				 
 				 //alert(key);
 				 document.getElementById("subquantity"+serialnumber).value = temp;
 				 			 }
 		 }
 		 }
 		
			
 	   }
 	   
 	  function updateAssignedData(quantity,serialnumber,subquantity) {
 		 $.ajax({
		        url : "/statusAssignRequest",
		        type : "GET",
		        dataType : 'text',  
		        data : {
		        	"id" : ${asset.id},
		            "email" : '${user.email}',
		            "serialnumber" : serialnumber,
		            "quantity": subquantity
		            
		        }
		    });
		}
	   
 	   
 	   </script>
 	   <script>
 	      function Search() {
 	    	 $.ajax({
 		        url : "/Search",
 		        type : "GET",
 		        dataType : 'text',  
 		        data : {
 		        	"windows" : $("#windows").val(),
 		            "assettype" : $("#assettype").val(),
 		            "model" : $("#model").val()
 		            
 		        }
 		    });
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
		<form:form action="" name = "form" class="form-group" commandName = "asset">
		<div class="form-group">
							<label class="col-sm-4 control-label">Search Operating system</label> 
							<input type="text" name="windows" id="windows" onkeyup = "return Search();"/>
						</div>
						<br> 
		<div class="form-group">
							<label class="col-sm-4 control-label">Search Asset Type </label> 
							<input type = "text" name = "assettype" id = "assettype" />
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="model"> Model
							</label>
							<input type = "text" name = "model" id = "model" />
						</div>
			<table class="table table-striped" id = "tb1">
				<tr>
					<th>Company</th>
					<th>Asset Type </th>
					<th>Asset Tag</th>
					<th>Model</th>
					<th>Configuration</th>
					<th>Status</th>
					<th>Serial Number</th>
					<th>Purchase Date</th>
					<th>Supplier</th>
					<th>Supplier Contact </th>
					<th>Order Number</th>
					<th>Purchase Cost</th>
					<th>Warranty</th>
					<th>Quantity</th>
					<th> Total Cost</th>
					<th> Actions</th>
					<th>Specify Quantity</th>
				</tr>
				<c:forEach items="${listAssets}" var="asset" >
					<tr>
						<td>${asset.company}</td>
						<td>${asset.assettype}</td>
						<td>${asset.tag}</td>
						<td>${asset.model}</td>
						<td>${asset.windows},${asset.category},${asset.ram},${asset.harddisk} </td>
						<td>${asset.status}</td>
						<td>${asset.serialnumber}</td>
						<td>${asset.purchasedate}</td>
						<td>${asset.supplier}</td>
						<td>${asset.suppliercontact}</td>
						<td>${asset.ordernumber}</td>
						<td>${asset.purchasecost}</td>
						<td>${asset.warranty}</td>
						<td id = "quantity${asset.serialnumber}">${asset.quantity}</td>
						<td>${asset.totalcost}</td>
						<td><a href="/deleteAsset?id=${asset.id}"> <span
								class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
							&nbsp; <a href="/getAsset?id=${asset.id}"> <span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
								<td> <input type = "text" name ="subquantity" id = "subquantity${asset.serialnumber}" value = "" />&nbsp;
				 <button type = "submit" name = "assignbutton" id = "assignbutton" onclick = 'return temporary(${asset.serialnumber})'>ASSIGN</button></td>
				
				</c:forEach>
                
			</table>
		</form:form>
	</div>
</body>
</html>