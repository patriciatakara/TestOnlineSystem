<!-- FACULTY PAGE
This page displays a field/form to create a new exam. The instructor is expected to submit the name for the new exam to be created.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>New Exam</title>
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

<h2 class="centerJustify" style="padding:15px">Create New Exam</h2>

<h3 class="centerJustify" style="padding-top:25px" >Exam Name</h3>

<!-- Form to create a new exam -->
<div class="centerJustify">
<form method = "post"  action="${pageContext.request.contextPath}/createExam"> 		
	<input class="fieldSizeName0" type="text" name="examName"  placeholder= "Enter exam name here"> 
	<input class="signButton1" type="submit" value="Create">
</form>
</div>


<!-- Alert message: for when the exam name entered already exists on the database. -->
<c:if test="${sessionScope.getAlert1 == 'Yes'}">
    <script type="text/javascript">
        alert("Exame not created. This exam name already exists");
    </script>
    <c:remove var="getAlert1" scope="session" />
</c:if>

<!-- Alert message:for when an exam is NOT created. -->
<c:if test="${sessionScope.getAlert2 == 'Yes'}">
    <script type="text/javascript">
        alert("Exame not created. Try Again.");
    </script>
    <c:remove var="getAlert2" scope="session" />
</c:if>


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