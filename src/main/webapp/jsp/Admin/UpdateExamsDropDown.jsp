<!-- ADMINISTRATOR PAGE
This page displays the drop-down menu to select an exam and the button Create New Exam to create a new exam on the database.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">

	<title>Update Exams DropDown</title>
</head>

<body class="entirePageAdmin">

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
  
  <div class="center-section active2 clickable-div" onclick="ADM2()">
    
    UPDATE EXAMS
    
  </div>
  
  <div class="right-section clickable-div" onclick="ADM3()">
    
    UPDATE GRADES
    
  </div>
</div>


<div style="display: flex; flex-direction: row; align-items: center;  justify-content: center; padding-top:25px ">

 <!-- Create New Exam button-->
	 <div style="justify-content:center; align-items:center; display:flex; padding:25px 85px">
	  	<a  href="${pageContext.request.contextPath}/jsp/Admin/NewExamForm.jsp"><button style="padding:9px 20px">New Exam</button></a>
	</div>
	
<!-- Drop Down Menu -->
<div class="centerJustify" style="padding:25px 85px" >	
<form action="${pageContext.request.contextPath}/AdminDropDownAllExams" method="get">
<select class="dropdown-menu" name="selected" style="font-size:13px; width: 135px !important; " >
	<option value="" selected disabled >Update Exam</option>
<c:forEach var="table" items="${tableExams}">
    <option value="${table}">${table}</option>
</c:forEach>
</select>	
	<input type="submit" value="OK">
</form>
</div>

<!-- Delete Exam button-->
	 <div style="justify-content:center; align-items:center; display:flex; padding:25px 85px">
		<a  href="${pageContext.request.contextPath}/AdminDeleteExamButton"><button style="padding:9px 20px">Delete Exam</button></a>
	</div>
</div>


<!-- Image -->
<div class="centerJustify" style="padding-top:55px" >
	<img src="images/adm1.jpg" alt="Image description" width="35%">
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