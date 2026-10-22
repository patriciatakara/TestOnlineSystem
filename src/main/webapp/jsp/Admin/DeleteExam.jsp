<!-- ADMINISTRATOR PAGE
This page displays all the existing exams on the database and "Delete" buttons for each exam.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<script src="javaScriptADM.js"></script>
	<title>Delete Exams</title>
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


<!-- Displays the name of all exams and a 'delete' button on each row. -->
<h2 class="centerJustify" style="padding:15px; color:black "> Delete Exams </h2>
<div style="justify-content:center; align-items:center; display:flex; ">
<table border="1" style="width: 35%; ">
  <tr>
    <th width="75%" >Exam Name</th>
    <th>Delete</th>
  </tr>
  <c:forEach var="exam" items="${tableExams}">
    <tr>
      <td width="75%" style="text-align:center" >${exam.tableName}</td>
      <td> <!-- DELETE EXAM -->
        <form style="text-align:center; padding:4px "  method="post" action="${pageContext.request.contextPath}/AdminDeleteExamProcess">
          <input type="hidden" name="examName" value="${exam.tableName}" />
          <input  style="padding:3px 15px"  type="submit" value="Delete" />
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
</div>


<!-- Alert message: when an exam is deleted. -->
<c:if test="${sessionScope.getAlert1 == 'Yes'}">
    <script type="text/javascript">
        alert("Exam deleted successfully.");
    </script>
    <c:remove var="getAlert1" scope="session" />
</c:if>

<!-- Alert message: when an exam is NOT deleted. -->
<c:if test="${sessionScope.getAlert2 == 'Yes'}">
    <script type="text/javascript">
        alert("Exam not deleted.");
    </script>
    <c:remove var="getAlert2" scope="session" />
</c:if>


<!-- To prevent the user to go back to the previous page after logging out of the system. -->
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