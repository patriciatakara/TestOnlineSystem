<!-- ADMINISTRATOR PAGE
This page displays the drop-down menu to select an exam's grade list.
 -->
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">

	<title>Update Grades DropDown</title>
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
  
  <div class="center-section clickable-div" onclick="ADM2()">
    
    UPDATE EXAMS
    
  </div>
  
  <div class="right-section active2 clickable-div" onclick="ADM3()">
    
    UPDATE GRADES
    
  </div>
</div>


<!-- Drop Down Menu -->
<div class="centerJustify" style="padding-top:50px;" >
<form action="${pageContext.request.contextPath}/AdminDropDownAllGrades" method="get">
	<select class="dropdown-menu" name="selected"  style="font-size:13px">
		<option value="" selected disabled>Select Examination Grades</option>
	<c:forEach var="table" items="${tableGrades}">
	    <option value="${table}">${table}</option>
	</c:forEach>
	</select>	
	<input type="submit" value="OK">
</form>
</div>


<!-- Image -->
<div class="centerJustify" style="padding-top:100px" >
	<img src="images/adm2.jpg" alt="Image description" width="35%">
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