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
	<title>DropDown Add Question</title>
</head>

<body class="entirePageMain">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
   	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/FacultyHub.jsp">FacultyHub</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/ManageExams.jsp" class="active">Manage Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/ViewExams">View Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/StudentsGrades">Students Report</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>


<!-- Go back button -->
<div class="GoBackButton">
  	<a href="${pageContext.request.contextPath}/jsp/Faculty/ManageExams.jsp"><button >Go Back</button></a>
</div>


<h2 class="centerJustify" style="padding-top:20px; ">Choose the exam you wish to ADD questions to:</h2>

<!-- Dropdown menu -->
<div class="centerJustify" style="padding-top:20px" >
<form action="${pageContext.request.contextPath}/DropDownAddQ" method="get">
	<select class="dropdown-menu"  name="examName">
	<option value="" selected disabled>Select Exam</option>
		<c:forEach var="examName" items="${exams}">
		    <option value="${examName}">${examName}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Submit">
</form>
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