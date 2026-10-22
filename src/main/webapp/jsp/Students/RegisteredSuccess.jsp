<!-- STUDENT PAGE
This page displays a welcome message for students who have registered successfully.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">	
	<title>Registration Complete</title>
</head>

<body class="entirePageStudent">

<!-- Menu bar -->
<ul>
	<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp">Student Login</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Forms/RegistrationForm.jsp">Register</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Forms/FacultyLoginForm.jsp">Faculty</a></li>
</ul>

<!-- Welcom message -->
<div  style="padding:65px; flex-direction: column; text-align: center;">
	<h1>Welcome Aboard</h1>
	
<!-- Login button -->
	<div style="padding:25px">
		<h3>Now, go to login.</h3>
	<a href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp"><button style="	font-size:13px; padding: 8px 65px;">Login</button></a>
	</div>
</div>


<!-- To prevent the user to go back to the previous page after logging out of the system. -->
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%> 

</body>
</html>