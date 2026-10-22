<!-- STUDENT PAGE
This page displays the student's result on an exam right after submission.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Result</title>
</head>

<body class="entirePageStudent">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
    <li><a href="${pageContext.request.contextPath}/jsp/Students/StudentHub.jsp">StudentHub</a></li>
    <li><a href="${pageContext.request.contextPath}/dropDownExam" class="active">Exams</a></li>
    <li><a href="${pageContext.request.contextPath}/GetReport">Reports</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>

<!-- Print button -->
<div class="buttonPL">
<button onclick="window.print()">Save Report</button>
</div>

<!-- Displays the student's report on the exam he just submitted. -->
<div class="resultBoard">
	<div class="boardBorder">
		<h1 style="color:black" >Exam Result</h1>
		<h4 style="color:black" >User: ${email} | ID: ${userid}</h4><br>
		<h3 style="color:black" >Exam: ${exam}</h3>
		<h3 style="color:black" >Grade: ${letterGrade}</h3>
		<h3 style="color:black" >Correct Answers: ${score} | <em>${percentage}%</em></h3>
		<h3 style="color:black" >${date}</h3>
	</div>
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

</body>
</html>