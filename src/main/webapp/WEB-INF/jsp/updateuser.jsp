<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	 <%
     String email=request.getParameter("email");
     request.getSession().setAttribute("email", email);
     String firstname=request.getParameter("firstname");
     request.getSession().setAttribute("firstname", firstname);
     String lastname=request.getParameter("lastname");
     request.getSession().setAttribute("lastname", lastname);
     String designation=request.getParameter("designation");
     request.getSession().setAttribute("designation", designation);
     String role=request.getParameter("role");
     request.getSession().setAttribute("role", role);
     
     %>
	
<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	 <script>
	      function validate() {
	    	  if (document.form.firstname.value == "") {
	  	        alert("First name required");
	  	        document.form.firstname.focus();
	  	        return false;
	  	    }
	  	 if(!(document.form.firstname.value.match(/^[a-zA-Z]+$/))) {
	      	 alert("Only alphabets are allowed in firstname");
	      	 document.form.firstname.focus();
	      	 return false;
	       }
	  	 if (document.form.lastname.value == "") {
	  	        alert("Last name required");
	  	        document.form.lastname.focus();
	  	        return false;
	  	    }
	  	 if(!(document.form.lastname.value.match(/^[a-zA-Z]+$/))) {
	      	 alert("Only alphabets are allowed in last name");
	      	 document.form.lastname.focus();
	      	 return false;
	       }
	  	var numeric = /(?=.*[!@#$%^&*])/;
	   	 if(numeric.test(form.designation.value)) {
	   		 alert("It must be alpha numeric");
	   		 document.form.designation.focus();
	   		 return false;
	   	 }
		 if (document.form.designation.value == "") {
		        alert("Designation required");
		        document.form.designation.focus();
		        return false;
		    }
		 if (document.form.role.value == "") {
		        alert("Role required");
		        document.form.role.focus();
		        return false;
		    }
	      }
	 
	 </script>
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
					<li><a href="/users">BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-3">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">Edit User</h3>
					</div>
				</div>
					<br>
					<form:form name = "form" action = "/updateuser"  method = "post" class = "form-group" align = "center" commandName="user" onsubmit = "return validate();">
					<input type = "hidden" name = "${user.email}"/>
					           <div class = "form-group" >
					           <label class="col-sm-4 control-label">First Name<span class = "required"> * </span> </label> 
					           <input type="text" name="firstname" value = "${user.firstname}" class="form-control" > 
					           <form:errors path="firstname"/>
					           </div> <br>
					           <div class = "form-group" >
					           <label class="col-sm-4 control-label">Last Name<span class = "required"> * </span></label> 
					           <input type="text" name="lastname"  value = "${user.lastname}" class="form-control" > 
					           <form:errors path="lastname" />
					           </div> <br>
					            <div class = "form-group">
					           <label class="col-sm-4 control-label">Email<span class = "required"> * </span> </label> 
					           <input type="text" name="email" readonly = "readonly" value = "${user.email}" class="form-control" > 
					           </div> <br>
					           <div class = "form-group">
					           <label class="col-sm-4 control-label" >Designation <span class = "required"> * </span></label> 
					           <input type="text" name="designation" value = "${user.designation}" class="form-control" > 
					           <form:errors path="designation" />
					           </div> <br>
					           <div class="form-group">
							       <label class="col-sm-4 control-label" for="assettype">
								Role<span class = "required"> * </span> </label>
							       <form:select class="form-control" name="role"
								path="role" >
								    <option value="">None</option>
								    <option value="ROLE_SUPER">Super Admin</option>
								    <option value="ROLE_ADMIN">Admin</option>
								    <option value="ROLE_USER">Employee</option>
							       </form:select>
						       </div>
						       <br>
					            <button type = "submit" value = "submit" name = "editbutton" class="btn btn-success" onClick="updateUser()"> Update </button>
					        
					</form:form>
					</div>
				</div>
			</div>

</body>
</html>