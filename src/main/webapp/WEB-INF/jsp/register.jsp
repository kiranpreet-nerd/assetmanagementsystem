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
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<c:url value="/css/userdesign.css" var="jstlCss" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
function validate() {
	 if(document.form.email.value == "" && document.form.firstname.value == "" && document.form.lastname.value == "" && document.form.designation.value == "" && document.form.role.value == "" && document.form.password.value == "" && document.form.confirm.value == "") {
		 alert("All fields required");
		 document.form.email.focus();
		 return false;
   }
	 if (document.form.email.value == "") {
        alert("Email required");
        document.form.email.focus();
        return false;
    }
	 if (document.form.firstname.value == "") {
	        alert("First name required");
	        document.form.firstname.focus();
	        return false;
	    }
	 if (document.form.lastname.value == "") {
	        alert("Last name required");
	        document.form.lastname.focus();
	        return false;
	    }
	 var numeric = /(?=.*[!@#$%^&*])/;
   	 if(numeric.test(form.designation.value)) {
   		 alert("Designation must be alpha numeric");
   		 document.form.designation.focus();
   		 return false;
   	 }
	 if (document.form.designation.value == "") {
	        alert("Designation required");
	        document.form.designation.focus();
	        return false;
	    }
	 if (document.form.role.value != "ROLE_USER" ){
	        alert("Role must be of user by default");
	        document.form.role.focus();
	        return false;
	    }
	 if (document.form.password.value == "") {
        alert("password required");
        document.form.password.focus();
        return false;
	 }
	 var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,20}/;
	 if(!re.test(form.password.value)) {
		 alert("Password must contain one number, one lowercase letter and uppercase letter,one symbol, atleast six characters and maximum 20");
		 document.form.password.focus();
		 return false;
	 }
	 if (document.form.confirm.value == "") {
	        alert("Confirm password required");
	        document.form.confirm.focus();
	        return false;
	    }
	 if(document.form.password.value != document.form.confirm.value) {
		 alert("Password must be same ");
		 //document.form.confirm.focus();
		 return false;
	 }
	 if(!(document.form.firstname.value.match(/^[a-zA-Z]+$/))) {
    	 alert("Only alphabets are allowed in firstname");
    	 document.form.firstname.focus();
    	 return false;
     }
	 if(!(document.form.lastname.value.match(/^[a-zA-Z]+$/))) {
    	 alert("Only alphabets are allowed in last name");
    	 document.form.lastname.focus();
    	 return false;
     }
}
</script>

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
		<div class="row">
			<div class="col-md-5 col-md-offset-3">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align="center">Register</h3>
					</div>
					<br>
					<form:form name = "form" action="" method="post" commandName="userform"
						class="form-group" align="center" onsubmit = "return validate();">
						<div class="form-group " ${status.error ? 'has-error' : ''}>
							<label class="col-sm-4 control-label ">Email<span class = "required"> * </span></label> <input
								type="text" name="email" placeholder="enter email"
								class="form-control">
							<div>
								<form:errors path="email"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">First Name<span class = "required"> * </span> </label> <input
								type="text" name="firstname" placeholder="enter firstname"
								class="form-control">
							<div>
								<form:errors path="firstname"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Last Name<span class = "required"> * </span> </label> <input
								type="text" name="lastname" placeholder="enter lastname"
								class="form-control">
							<div>
								<form:errors path="lastname"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Designation<span class = "required"> * </span> </label> <input
								type="text" name="designation" placeholder="enter designation"
								class="form-control">
							<div>
								<form:errors path="designation"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Role<span class = "required"> * </span></label> <input
								type="text" readonly="readonly" value=" ROLE_USER" name="role"
								class="form-control">
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Password <span class = "required"> * </span></label> <input
								type="password" name="password" placeholder="enter password"
								class="form-control">
							<div>
								<form:errors path="password"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Confirm Password <span class = "required"> * </span></label> <input
								type="password" name="confirm"
								placeholder="enter password again" class="form-control">
							<div>
								<form:errors path="confirm"></form:errors>
							</div>
						</div>
						<button class="btn btn-success" type="submit"
							name="registerbutton" value="submit" onClick="registration()">
							REGISTER</button>
					</form:form>
				</div>
			</div>
			</div>
			</div>
			<script type="text/javascript">
   var modelAttributeValue = '${emailError}';
   if(modelAttributeValue != ""){
   alert(modelAttributeValue);
   }
   var modelAttributeValue = '${confirmationlink}';
   if(modelAttributeValue != ""){
   alert(modelAttributeValue);
   }
   
</script>
			<c:if test="${SUCCESS_MESSAGE != null}">
			  <div id="status_message">${SUCCESS_MESSAGE}</div>
			</c:if>
			
</body>

</html>