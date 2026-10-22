<!-- FACULTY PAGE
This page displays the drop-down menu to select an exam's grade list and all the students' grades on that exam.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Students Report</title>
</head>

<body class="entirePageAfterDropDown">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
   	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/FacultyHub.jsp">FacultyHub</a></li>
	<li><a href="${pageContext.request.contextPath}/jsp/Faculty/ManageExams.jsp">Manage Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/ViewExams">View Exams</a></li>
	<li><a href="${pageContext.request.contextPath}/StudentsGrades" class="active">Students Report</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>

<!-- Drop Down Menu -->
<div class="centerJustify" style="padding-top:20px">
<form action="${pageContext.request.contextPath}/StudentsGrades" method="get">
	<select class="select" name="selected">
		<option value="" selected disabled>Select Examination Grades</option>
	<c:forEach var="table" items="${tableGrades}">
	    <option value="${table}">${table}</option>
	</c:forEach>
	</select>
	<input type="submit" value="OK">
</form>
</div>


<h3 class="centerJustify" style="padding-top:30px; color:black "> Examination: ${table} </h3>
<h4 class="centerJustify" style="padding-top:5px; color:black ">Number of question: ${qCount}</h4>

<!-- Displays all the students' grades on the selected exam, plus the submission date -->
<div style="justify-content:center; align-items:center; display:flex; padding-top:15px ">
    <table  border='1'>
        <tr>
            <th>Student ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Correct Answers</th>
            <th>Grade</th>
            <th>Submission Date</th>  
        </tr>
        <c:forEach var="report" items="${reports}">
                <tr>
                    <td>${report.userId}</td>
                    <td>${report.first}</td>
                    <td>${report.last}</td>
                    <td>${report.grade}</td>
                    <td>${report.letterGrade}</td>
                    <td>${report.submissionDate}</td>
                </tr>
        </c:forEach>
    </table>
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