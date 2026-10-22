<!-- FACULTY PAGE
This page displays the drop-down menu to select an exam.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<script src="javaScript.js"></script>
	<title>Display Exams DropDown</title>
</head>

<body class="entirePageMain">
		
<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
   	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/FacultyHub.jsp">FacultyHub</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/ManageExams.jsp">Manage Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/ViewExams" class="active">View Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/StudentsGrades">Students Report</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>


<h2 class="centerJustify" style="padding-top:20px; "> Exams </h2>

<!-- Drop Down Menu -->
<div class="centerJustify" style="padding-top:20px" >
<form action="${pageContext.request.contextPath}/ViewExams" method="get">
	<select class="dropdown-menu" name="selected" >
		<option value="" selected disabled>Select Exam</option>
	<c:forEach var="table" items="${tableExams}">
	    <option value="${table}">${table}</option>
	</c:forEach>
	</select>	
	<input type="submit" value="OK">
</form>
</div>

<!-- Image -->
<div class="centerJustify" style="padding-top:70px" >
	<img src="images/facexam.jpg" alt="Image description" width="35%">
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

</body>
</html>