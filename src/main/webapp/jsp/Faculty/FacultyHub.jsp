<!-- FACULTY PAGE
This is the main page for faculty members after the login. This page gives access to sensitive data and permission to make limited 
alterations. 
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>FacultyHub</title>
</head>

<body class="entirePageFacultyHub">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
   	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/FacultyHub.jsp" class="active">FacultyHub</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/ManageExams.jsp">Manage Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/ViewExams">View Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/StudentsGrades">Students Report</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>

<h1 style="color:white; text-align:center; padding:70px; font-size:50px ">OnlineTest</h1>

<!-- Welcome message to the instructor -->
<div class="centerJustify" style="padding:5px">
<h2>Welcome, <span style="font-style:italic; font-size:20px">${email}</span></h2>
</div>


<!-- To prevent the user to go back to the previous page after logging out of the system. -->
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.

if (session.getAttribute("email")==null) {
response.sendRedirect("${pageContext.request.contextPath}/jsp/Forms/FacultyLoginForm.jsp");
}
%>  

<div class="bottom-bar">
    <p class="email">Support: testonlinespp@edu.com</p>
    <p class="year">© 2023</p>
</div>
</body>
</html>