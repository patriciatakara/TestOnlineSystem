<!-- STUDENT PAGE
This page displays the student's report on all the exams he submitted.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Reports</title>
</head>

<body class="entirePageStudent">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
    <li><a href="${pageContext.request.contextPath}/jsp/Students/StudentHub.jsp">StudentHub</a></li>
    <li><a href="${pageContext.request.contextPath}/dropDownExam">Exams</a></li>
    <li><a href="${pageContext.request.contextPath}/GetReport" class="active">Reports</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>


<!-- Print button -->
<div class="buttonPL">
<button onclick="window.print()">Print</button>
</div>

<!-- Display the student's report -->
<div style="height:60vh; display:flex; flex-direction: column; align-items:center; justify-content:center;" > 

<div class="boardReport" > 
  <p>Student ID: ${userid}</p> 
  <p>Name: ${fullName}</p>
  <p>${email}</p>

<div style="padding:30px; padding-top:5px; justify-content:center; align-items:center; display:flex;">
  <table border='1'>
    <tr>
      <th>Exam Name</th>
      <th>Grade</th>
      <th>Correct Answers</th>
      <th>Submission Date</th>   
    </tr>
    <c:forEach var="grades" items="${grades}">
      <tr>
        <td>${grades.examName}</td>
        <td>${grades.letterGrade}</td>
        <td>${grades.grade}</td>
        <td>${grades.submissionDate}</td>
      </tr>
    </c:forEach>
  </table>
</div>
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