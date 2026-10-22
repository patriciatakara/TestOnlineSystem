<!-- ADMINISTRATOR PAGE
This page displays a form to add a new exam into the database.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Create New Exam</title>
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
  	<a href="${pageContext.request.contextPath}/AdminDropDownAllExams"><button >Go Back</button></a>
</div>


<div class="formCenter3" style="padding-top:5px;">
<h2 style="color:black">Create New Exam</h2>
	
<h3 style="padding-top:15px; color:black;" >Exam Name</h3>
	
<!-- Form to add a new exam -->
<form method = "post"  action="${pageContext.request.contextPath}/AdminCreateExamButton"> 		
	<input class="fieldSizeName0" type="text" name="examName"  placeholder= "Enter exam name here"> 
				 
	<input class="signButton1" type="submit" value="Create">
</form>
</div>


<!-- Alert message: when the exam name already exists on the database. -->
<c:if test="${sessionScope.getAlert1 == 'Yes'}">
    <script type="text/javascript">
        alert("Exame not created. This exam name already exists");
    </script>
    <c:remove var="getAlert1" scope="session" />
</c:if>

<!-- Alert message: when the exam is not created. -->
<c:if test="${sessionScope.getAlert2 == 'Yes'}">
    <script type="text/javascript">
        alert("Exame not created. Try Again.");
    </script>
    <c:remove var="getAlert2" scope="session" />
</c:if>

<!-- Alert message: when the exam is created successfully -->
<c:if test="${sessionScope.getAlert3 == 'Yes'}">
    <script type="text/javascript">
        alert("Exame created successfully");
    </script>
    <c:remove var="getAlert3" scope="session" />
</c:if>


</body>
</html>