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

<script>
function validate() {
	 if(document.form.email.value == "" && document.form.firstname.value == "" && document.form.lastname.value == "" && document.form.designation.value == "" && document.form.role.value == "" && document.form.password.value == "" && document.form.confirm.value == "") {
		 alert("All fields required");
		 document.form.email.focus();
		 return false;
   }
	 if (document.form.email.value == "") {
        alert("email required");
        document.form.email.focus();
        return false;
    }
	 if (document.form.firstname.value == "") {
	        alert("first name required");
	        document.form.firstname.focus();
	        return false;
	    }
	 if(!(document.form.firstname.value.match(/^[a-zA-Z]+$/))) {
    	 alert("only alphabets are allowed in firstname");
    	 document.form.firstname.focus();
    	 return false;
     }
	 if (document.form.lastname.value == "") {
	        alert("last name required");
	        document.form.lastname.focus();
	        return false;
	    }
	 if(!(document.form.lastname.value.match(/^[a-zA-Z]+$/))) {
    	 alert("only alphabets are allowed in last name");
    	 document.form.lastname.focus();
    	 return false;
     }
	 if (document.form.designation.value == "") {
	        alert("designation required");
	        document.form.designation.focus();
	        return false;
	    }
	 if (document.form.role.value == "") {
	        alert("role required");
	        document.form.role.focus();
	        return false;
	    }
	 if (document.form.password.value == "") {
        alert("password required");
        document.form.password.focus();
        return false;
	 }
	 if (document.form.confirm.value == "") {
	        alert("confirm password required");
	        document.form.confirm.focus();
	        return false;
	    }
	 if(document.form.password.value != document.form.confirm.value) {
		 alert("password must be same ");
		 //document.form.confirm.focus();
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
							<div class = "redalert">${emailError}</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">First Name </label> <input
								type="text" name="firstname" placeholder="enter firstname"
								class="form-control">
							<div>
								<form:errors path="firstname"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Last Name </label> <input
								type="text" name="lastname" placeholder="enter lastname"
								class="form-control">
							<div>
								<form:errors path="lastname"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Designation </label> <input
								type="text" name="designation" placeholder="enter designation"
								class="form-control">
							<div>
								<form:errors path="designation"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Role </label> <input
								type="text" readonly="readonly" value=" ROLE_USER" name="role"
								class="form-control">
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Password </label> <input
								type="password" name="password" placeholder="enter password"
								class="form-control">
							<div>
								<form:errors path="password"></form:errors>
							</div>
						</div>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-4 control-label">Confirm Password</label> <input
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
			
			
</body>
</html>