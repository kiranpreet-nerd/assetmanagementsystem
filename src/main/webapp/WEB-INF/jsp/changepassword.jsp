<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>


<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<c:url value="/css/userdesign.css" var="jstlCssfile" />
<link href="${jstlCss}" rel="stylesheet" />
<script>
     function validate() {
    	 if (document.form.oldpassword.value == "") {
 	        alert("Fill your old password here");
 	        document.form.oldpassword.focus();
 	        return false;
 		 }
    	 
    	 if (document.form.newpassword.value == "") {
    	        alert("Password required");
    	        document.form.newpassword.focus();
    	        return false;
    		 }
    	 var passwordpattern = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,20}/;
    	 if(!passwordpattern.test(form.newpassword.value)) {
    		 alert("Password must contain one number, one lowercase letter and uppercase letter,one symbol, atleast six characters and maximum 20");
    		 document.form.newpassword.focus();
    		 return false;
    	 }
    	 if(document.form.retypepassword.value == "") {
    		 alert("Type password again");
    		 document.form.retypepassword.focus();
    		 return false;
    	 }
    		
    		 if(document.form.newpassword.value != document.form.retypepassword.value) {
    			 alert("Password must be same ");
    			 return false;
    		 }
     }


</script>
</head>
<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Snap-IT</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/assetrequest">BACK</a></li>
					<li><a href="/users"> USERSLIST </a></li>
					<li><a href="/requestedassets"> REQUESTED USERS </a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
	<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" align = "center">Change Password</h3>
					</div>
					<br>
					<form:form name="form" action="/changePassword?email=${email}" method="post"
						class="form-group" align="center" commandName = "changepassword" onsubmit="return validate();">
						${passwordError}
						<div class="form-group">
							<label class="col-sm-4 control-label">Old Password<span class = "required"> * </span> </label>
							<input type="password" name="oldpassword"
								value=""
								class="form-control" >
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">New Password<span class = "required"> * </span> </label>
							<input type="password" name="newpassword"
								value=""
								class="form-control" >
						</div>
						<br>
						<div class="form-group">
							<label class="col-sm-4 control-label">Retype Password<span class = "required"> * </span> </label>
							<input type="password" name="retypepassword" value="" 
								class="form-control" >
						</div>
						<br>
						<button type="submit" value="submit" name="changepasswordbutton"
							class="btn btn-success">Change Password</button>
						</form:form>
	</div>
	</div>
	</div>
	</div>
	<script type="text/javascript">
   var modelAttributeValue = '${passwordError}';
   if(modelAttributeValue != ""){
   alert(modelAttributeValue);
   }
</script>

</body>
</html>