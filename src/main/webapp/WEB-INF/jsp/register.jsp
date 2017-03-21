<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/login">BACK</a></li>
				</ul>
			</div>
		</div>
	</nav>
    <div class="container">
     <legend align = "center"> Register </legend>
        <form:form action = ""  method = "post" class = "form-signin" align = "center" commandName = "registeruser">
         <div class = "form-group ${status.error ? 'has-error' : ''}">
           <label>Email     </label>
           <input type = "text" name = "email" placeholder = "enter email">
           <form:errors path="email"></form:errors><br><br>
         </div>
         <div class = "form-group ${status.error ? 'has-error' : ''}">
           <label>Firstname </label>
           <input type = "type" name = "firstname" placeholder = "enter firstname" >
           <form:errors path="firstname"></form:errors><br><br>
         </div> 
         <div class = "form-group ${status.error ? 'has-error' : ''}">
           <label>Lastname  </label>
           <input type = "text" name = "lastname" placeholder = "enter lastname">
           <form:errors path="lastname"></form:errors><br><br>
         </div>
          <div class = "form-group ${status.error ? 'has-error' : ''}">
           <label>Designation  </label>
           <input type = "text" name = "designation" placeholder = "enter designation">
           <form:errors path="designation"></form:errors><br><br>
         </div>
         <div class = "form-group ${status.error ? 'has-error' : ''}">
           <label>Password  </label>
           <input type = "password" name = "password" placeholder = "enter password">
           <form:errors path="password"></form:errors><br><br>
         </div>
         <div class = "form-group ${status.error ? 'has-error' : ''}">
           <label>Confirm Password</label>
           <input type = "password" name = "confirmpassword" placeholder = "enter password again">
           <form:errors path="confirmpassword"></form:errors><br><br>
         </div>
           <button class="btn btn-success" type = "submit" name = "registerbutton" value = "submit" onClick ="registration()"> REGISTER </button>
       </form:form>
    </div>
</body>
</html>