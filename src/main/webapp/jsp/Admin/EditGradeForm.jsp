	<!-- ADMINISTRATOR PAGE
This page displays a form to edit a student's grade on an exam.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Editing Grade</title>
</head>

<body class="entirePageAfterDropDown">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">	
	<li><a style="color:white; font-size:20px; padding-left:25px; padding-right:150px"href="${pageContext.request.contextPath}/jsp/Admin/AdmMainHub.jsp">Home</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>

<!-- Go back button -->
<div class="GoBackButton">
  	<a href="${pageContext.request.contextPath}/AdminDropDownAllGrades?selected=${report.examName}"><button >Go Back</button></a>
</div>


<h2 class="centerJustify" style="padding-top:15px; color:black ">Alterations Grade Form</h2>

<!-- Form to add a student's grade and exam's submission data -->
<div class="editForm">
<form id=editForm method="post" action="${pageContext.request.contextPath}/AdminEditGradeProcess">
	  <div class="formRow">
	    <label for="userid">Student ID</label> 
	    <input type="text" id="userid" name="userid" value="${report.userId}" placeholder="${report.userId}" disabled />
	    <input type="hidden" name="userid" value='${report.userId}'>
	  </div>   
	  <div class="formRow">
	   	<label for="first">First Name</label> 
	    <input type="text" id="first" name="first" value="${report.first}" placeholder="${report.first}" disabled />
	    <input type="hidden" name="first" value='${report.first}'>
	  </div>   
	  <div class="formRow">
	    <label for="last">Last Name</label> 
	    <input type="text" id="last" name="last" value="${report.last}" placeholder="${report.last}" disabled />
	    <input type="hidden" name="last" value='${report.last}'>
	  </div> 
	  <div class="formRow">
	    <label for="examName">Exam</label> 
	    <input type="text" id="examName" name="examName" value="${report.examName}" placeholder="${report.examName}" disabled />
	    <input type="hidden" name="examName" value='${report.examName}'>
	  </div> 
	  <div class="formRow">
	    <label for="correctAnswers">Correct Answers</label> 
	    <input type="number" id="correctAnswers" name="correctAnswers" value="${report.grade}" placeholder="${report.grade}"/>
	  </div>   
	  <div class="formRow">
	    <label for="letterGrade">Grade</label> 
	    <input type="text" id="letterGrade" name="letterGrade" value="${report.letterGrade}" placeholder="${report.letterGrade}"/>
	  </div> 	  
	  <div class="formRow">
	    <label for="submissionDate">Submission Date</label> 
	    <input type="text" id="submissionDate" name="submissionDate" value="${report.submissionDate}" placeholder="${report.submissionDate}"/>
	  </div> 	  
	 	<input class="buttonSaveUsers" type="submit" value="Save" />
  </form>
</div>


<!-- To prevent the user to go back to the previous page after logging out of the system -->
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.

if (session.getAttribute("email")==null) {
response.sendRedirect("${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp");
}
%> 

</body>
</html>