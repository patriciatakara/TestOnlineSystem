<!-- ADMINISTRATOR PAGE
This page displays the drop-down menu to select an exam's grade list and all the students' grades on that exam. The buttons 'edit' 
and 'delete' are also implemented alongside each student's grade.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Update Grades</title>
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

<!-- Sections Menu -->
<div class="smallContainer">
  <div class="left-section clickable-div" onclick="ADM1()">
    
	UPDATE USERS

  </div>
  
  <div class="center-section clickable-div" onclick="ADM2()">
    
    UPDATE EXAMS
    
  </div>
  
  <div class="right-section active2 clickable-div" onclick="ADM3()">
    
    UPDATE GRADES
    
  </div>
</div>


<!-- Drop Down Menu -->
<div class="centerJustify" style="padding-top:50px">
<form action="${pageContext.request.contextPath}/AdminDropDownAllGrades" method="get">
	<select class="select" name="selected"  style="font-size:13px">
		<option value="" selected disabled>Select Examination Grades</option>
	<c:forEach var="table" items="${tableGrades}">
	    <option value="${table}">${table}</option>
	</c:forEach>
	</select>
	<input type="submit" value="OK">
</form>
</div>

<!-- Displays all the students' grades on the selected exam, and the buttons 'edit and 'delete'. -->
<h3 class="centerJustify" style="padding-top:30px; color:black "> Examination: ${table} </h3>
<div style="justify-content:center; align-items:center; display:flex;">
    <table  border='1' style="width: 55%">
        <tr>
            <th>Student ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Correct Answers</th>
            <th>Grade</th>
            <th>Submission Date</th> 
            <th>Edit</th> 
		    <th>Delete</th>
        </tr>
        <c:forEach var="report" items="${reports}">
                <tr>
                    <td>${report.userId}</td>
                    <td>${report.first}</td>
                    <td>${report.last}</td>
                    <td>${report.grade}</td>
                    <td>${report.letterGrade}</td>
                    <td>${report.submissionDate}</td>
                    <td> <!-- EDIT GRADE -->
			          <form style="text-align:center; padding:4px" method='post' action='${pageContext.request.contextPath}/AdminEditGradeButton'>
			           <input type='hidden' name='userid' value='${report.userId}'/>
			          <input type='hidden' name='first' value='${report.first}'/>
			          <input type='hidden' name='last' value='${report.last}'/>
			          <input type='hidden' name='correctAnswers' value='${report.grade}'/>
			          <input type='hidden' name='letterGrade' value='${report.letterGrade}'/>
			          <input type='hidden' name='tableName' value='${table}'/>
			          <input type='hidden' name='submissionDate' value='${report.submissionDate}'/>     
			          <input style="padding:3px 15px" type='submit' value='Edit'/>
			          </form> 
			        </td>
			        <td> <!-- DELETE GRADE -->
			          <form style="text-align:center; padding:4px" method='post' action='${pageContext.request.contextPath}/AdminDeleteGradeButton'>
			      	   <input type='hidden' name='tableName' value='${table}'/>
			          <input type='hidden' name='userid' value='${report.userId}'/>
			          <input style="padding:3px 15px" type='submit' value='Delete'/>
			        </form>
      				</td>
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
response.sendRedirect("${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp");
}
%>  

  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/javaScriptADM.js"></script>

</body>
</html>