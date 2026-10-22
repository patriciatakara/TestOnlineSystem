<!-- GENERAL
This is the start point of the web application. The application's homepage where users have the first contact.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Home</title>
</head>

<body>
<div class="entirePageIndex"> 

<!-- Menu bar -->
<ul>
	<li><a href="${pageContext.request.contextPath}/index.jsp" class="active">Home</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp">Student Login</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Forms/RegistrationForm.jsp">Registration</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Forms/FacultyLoginForm.jsp">Faculty</a></li>
	<li ><a href="${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp">ADMIN</a></li>
</ul>

<h1 class="titleIndex">OnlineTest</h1>

<h2 class="textIndex"> OnlineTest System seeks to provide you with the best experience while conducting examinations. 
To utilize all the services we provide, please register first. 
</h2> 
 
</div>

<div class="bottom-bar">
    <p class="email">Support: testonlinespp@edu.com</p>
    <p class="year">© 2023</p>
</div>
</body>
</html>