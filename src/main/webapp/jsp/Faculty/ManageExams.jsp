<!-- FACULTY PAGE
This page provides links to different funtionalities for managing exams such as creating new exams, adding questions,  and deleting 
questions from exams.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Manage Exams</title>
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


<!-- Layout in columns -->	
<div class="element">
	
<!-- New test box -->
<div class="backNewTest">
	<div style="flex: 1; text-align: center;">	
		<h2 style="padding:25px">Create A New Exam</h2>
		<div>
		 <a href="${pageContext.request.contextPath}/jsp/Faculty/CreateNewExam.jsp"><button class="button" >New Exam</button></a> 
		</div>
	</div>
</div>	

<!-- Add Question box -->
<div class="backAddQue">
	<div style="flex: 1; text-align: center;">	
		<h2 style="padding:25px">Add Questions</h2>
		<div>
			<a href="${pageContext.request.contextPath}/DropDownAddQ"><button class="button">Add Questions</button></a>
		</div>
	</div>
</div>	

<!-- Delete Question box -->
<div class="backDelQue">
	<div style="flex: 1; text-align: center;">	
		<h2 style="padding:25px">Delete Questions</h2>
		<div>
			<a href="${pageContext.request.contextPath}/DropDownDelete"><button class="button">Delete Questions</button></a>
		</div>
	</div>
</div>

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