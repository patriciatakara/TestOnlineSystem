<!-- STUDENT PAGE
This page displays the drop-down menu of exams available to students.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>DropDown Exams</title>
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

<div class="centerJustify" style="padding:25px">
	<h1>Instructions </h1>

	<h2 style="padding:25px">Choose the exame and click on Start Exam to start examination.</h2>
	
<h3 style="padding-top:10px">Please read carefully:</h3>
<div style="display:flex; justify-content:center;">
<p style="color:white; text-align:left; font-size:17px ">
1. This is a multiple choice examination. Only one answer can be submitted per each question.<br>
2. It is a timed examination. Be mindful.<br>
3. Only one attempt is allowed.<br>
4. The results will be available immediatly after the exam is submitted.
</p>
</div>
</div>

<!-- Drop Down Menu of exams -->
<div class="centerJustify" style="padding-top:20px" >
<form action="${pageContext.request.contextPath}/TakeTest" method="post">
<select name="examName" style="font-size:13px; width: 140px !important" >
<c:forEach var="examName" items="${exams}">
    <option value="${examName}">${examName}</option>
</c:forEach>
</select>
<input type="submit" value="Start Exam">
</form>
</div>

<!-- Alert message: if the student's has already taken the exam, he cannot retake it. -->
<c:if test="${sessionScope.getAlert1== 'Yes'}">
    <script type="text/javascript">
        alert("Not Allowed to Retake Exam.");
    </script>
    <c:remove var="getAlert1" scope="session" />
</c:if>

<!-- Alert message: if an exam is still in process of development. -->
<c:if test="${sessionScope.maintenance== 'Yes'}">
    <script type="text/javascript">
        alert("Exam under maintenance, try again later.");
    </script>
    <c:remove var="maintenance" scope="session" />
</c:if>


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