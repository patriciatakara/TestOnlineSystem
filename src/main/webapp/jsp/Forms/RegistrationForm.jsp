<!-- REGISTRATION PAGE - STUDENTS
This is the registration page for students to register and have access to the application system.
 -->
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Registration</title>
</head>

<body>
	<div class="entirePageRegistration">

	<!-- Menu bar -->
	<ul>
		<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp">Student Login</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/RegistrationForm.jsp" class="active">Registration</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/FacultyLoginForm.jsp">Faculty</a></li>	
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp">ADMIN</a></li>
	</ul>

	<!-- Registration form -->
	<div class="formCenter3">
		<h2  class="centerJustify" >SING UP HERE</h2>
		
		<div class="editForm2" style="padding:45px">
		<form id=editForm2  method = "post" action="${pageContext.request.contextPath}/registration">	
			<div class="formRow">	
				<input type="text" id="first" name="first" placeholder="Enter your first name" required/>
			</div>	
			<div class="formRow"> 		
				<input type="text" id="last" name="last" placeholder="Enter your last name"  required/>	
			</div>	
			<div class="formRow">		
				<input type="email" id="email" name="email" placeholder="Enter your email" required/>		
			</div>	
			<div class="formRow">	
				<input type="password" id="password" name="password" placeholder="Enter your password" required/>
			</div>			
			<p id="passwordError" style="color: rgb(119, 28, 28); display: none;">
				Password must have:<br> 
				- At least 5 characters<br> 
				- At least 1 special character
			</p>
			<input class="signButton"  type="submit" value="Sign Up">
		</form>
		</div>
	</div>


	<!-- Alert message: in case the email entered already exists in the application's database. -->
	<c:if test="${sessionScope.getAlert == 'Yes'}">
			<script type="text/javascript">
					alert("Email already exist. Try again or go to login.");
			</script>
			<c:remove var="getAlert" scope="session" />
	</c:if>

	<!-- Alert message: in case a blank field is submitted. -->
	<c:if test="${sessionScope.nullForm == 'Yes'}">
			<script type="text/javascript">
					alert("Please, fill out all the fields.");
			</script>
			<c:remove var="nullForm" scope="session" />
	</c:if>

	<!-- Alert message: in case password does not meet requirements.
	<c:if test="${sessionScope.passwordError == 'Yes'}">
			<script type="text/javascript">
					alert("Password must have at least 5 characters and 1 special character.");
			</script>
			<c:remove var="passwordError" scope="session" />
	</c:if> -->

	</div>

	<script>
		const registrationForm = document.getElementById("editForm2");
    const passwordInput = document.getElementById("password");
    const passwordError = document.getElementById("passwordError");

    registrationForm.addEventListener("submit", function(event) {
        const password = passwordInput.value;
        const hasSpecialCharacter = /[^a-zA-Z0-9]/.test(password);

        if (password.length < 5 || !hasSpecialCharacter) {
            event.preventDefault();
            passwordError.style.display = "block";
            passwordInput.focus();
        } else {
            passwordError.style.display = "none";
        }
    });

    passwordInput.addEventListener("input", function() {
        const password = passwordInput.value;
        const hasSpecialCharacter = /[^a-zA-Z0-9]/.test(password);

        if (password.length >= 5 && hasSpecialCharacter) {
            passwordError.style.display = "none";
        }
    });
	</script>
</body>
</html>