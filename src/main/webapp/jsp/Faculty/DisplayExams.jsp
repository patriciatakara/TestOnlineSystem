<!-- FACULTY PAGE
This page displays the drop-down menu to select an exam and the questions belonging to that exam. The question ID, question text,
 multiple choices, and the correct answer are displayed to the instructor.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>View Exams</title>
</head>

<body class="entirePageAfterDropDown">
		
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


<!-- Display the questions belonging to the selected table -->
<h3 class="centerJustify" style="padding-top:30px; color:black  "> ${table} </h3>
<div style="justify-content:center; align-items:center; display:flex;">
<c:if test="${not empty table}">
    <div style="overflow-x: auto;">    
        <table border='1' >
            <tr>
                <th>Question ID</th>
                <th>Question</th>
                <th>Option 1</th>
                <th>Option 2</th>
                <th>Option 3</th>
                <th>Option 4</th>
                <th>Correct Answer</th>
            </tr>
            <c:forEach var="question" items="${questions}" varStatus="questionIndex">
                <c:if test="${question.tableName == table}">
                    <tr>
                        <td>${question.qid}</td>
                        <td>${question.qtext}</td>
                        <td>${question.opt1}</td>
                        <td>${question.opt2}</td>
                        <td>${question.opt3}</td>
                        <td>${question.opt4}</td>
                        <td>${question.correctAnswer}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>   
</c:if>
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