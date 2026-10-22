<!-- STUDENT PAGE
This is the main page for students after the login. This page gives access to sensitive data and grants permission to take exams.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Student Hub</title>
</head>

<body class="entirePageStudentHub" >

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
    <li><a href="${pageContext.request.contextPath}/jsp/Students/StudentHub.jsp" class="active">StudentHub</a></li>
    <li><a href="${pageContext.request.contextPath}/dropDownExam">Exams</a></li>
    <li><a href="${pageContext.request.contextPath}/GetReport">Reports</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>


<h1 class="titleIndexStudent">OnlineTest</h1>

<!-- Welcome message to the student -->
<div class="centerJustify">
	<h2 style="color:black" >Welcome, <span style="color:black; font-style:italic; font-size:22px">${email}</span></h2>
</div>

<!-- To prevent the user to go back to the previous page after logging out of the system. -->
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.

if (session.getAttribute("email")==null) {
response.sendRedirect("${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp");
}
%>  

<div class="bottom-bar">
    <p class="email">Support: testonlinespp@edu.com</p>
    <p class="year">© 2023</p>
</div>
</body>
</html>