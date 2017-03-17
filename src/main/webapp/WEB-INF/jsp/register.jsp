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
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>
    <div class="container">
     <legend align = "center"> Register </legend>
        <form:form action = ""  method = "post" class = "form-signin" align = "center">
         <div class = "form-group">
           <label align = "left">Email     </label>
           <input type = "text" name = "email" placeholder = "enter email"><br><br>
         </div>
         <div class = "form-group">
           <label align = "left">Firstname </label>
           <input type = "type" name = "password" placeholder = "enter firstname" ><br><br>
         </div> 
         <div class = "form-group">
           <label align = "left">Lastname  </label>
           <input type = "text" name = "email" placeholder = "enter lastname"><br><br>
         </div>
         <div class = "form-group">
           <label align = "left">Password  </label>
           <input type = "password" name = "email" placeholder = "enter password"><br><br>
         </div>
         <div class = "form-group">
           <label align = "left">Confirm Password</label>
           <input type = "password" name = "email" placeholder = "enter password again"><br><br>
         </div>
           <button class="btn btn-success" type = "submit" name = "registerbutton" value = "submit" > REGISTER </button>
       </form:form>
    </div>
</body>
</html>