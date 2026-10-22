<!-- FACULTY PAGE
This page displays a form to add a new question into an exam on the database.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Add Questions Form</title>
</head>

<body class="entirePageAfterDropDown">

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
  	<a href="${pageContext.request.contextPath}/DropDownAddQ"><button >Go Back</button></a>
</div>


<h2 class="centerJustify" style="padding-top:5px; color:black ">Add Questions to ${tableName}</h2>

<!-- DropDown menu -->
<div class="centerJustify" style="padding-top:5px; padding-bottom:20px;" >
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

<!-- Form to add questions -->
<div class="formCenter6">
<form method = "post"  action="${pageContext.request.contextPath}/AddQuestion"  onsubmit="return validateForm()">
<pre>
<label class="examFormTitles">Enter question</label>
<textarea class="fieldSizeQuestion" name="question" placeholder= "Enter the question here" ></textarea>
		
Enter choice A
<textarea class="fieldSizeAlternative" name="optionA" placeholder= "A"></textarea>
		
Enter choice B
<textarea class="fieldSizeAlternative" name="optionB" placeholder= "B"></textarea>
		
Enter choice C
<textarea class="fieldSizeAlternative" name="optionC" placeholder= "C"></textarea>
		
Enter choice D
<textarea class="fieldSizeAlternative" name="optionD" placeholder= "D"></textarea>
		
Enter correct choice
<textarea class="fieldSizeAnswer" name="correctAnswer" placeholder= "Answer"></textarea>
</pre>
	<input class="fieldSizeName" type="hidden" name="examName" value ='${tableName}'> 
	<input class="addButton1" type="submit" value="Add">
</form>
</div>


<!-- Alert message: when a question is added to the database successfully. -->
<c:if test="${sessionScope.getAlert1 == 'Yes'}">
    <script type="text/javascript">
        alert("Question added successfully!");
    </script>
    <c:remove var="getAlert1" scope="session" />
</c:if>
 
<!--Alert message: when a question is NOT added to the database -->
<c:if test="${sessionScope.getAlert2 == 'Yes'}">
    <script type="text/javascript">
        alert("Question not Added. All the fields must be completed.");
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