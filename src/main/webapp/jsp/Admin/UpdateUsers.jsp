<!-- ADMINISTRATOR PAGE
This page displays all the information of students and faculty members along with the buttons 'edit' and 'delete'. 
Buttons to create new users are implemented according to each group of users.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Update Users</title>
</head>

<body class="entirePageAfterDropDownUser">

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
  <div class="left-section active2 clickable-div" onclick="ADM1()">
   
	UPDATE USERS

  </div>
  
 <div class="center-section clickable-div" onclick="ADM2()">
    
    UPDATE EXAMS
    
  </div> 
  
  <div class="right-section clickable-div" onclick="ADM3()">
    
    UPDATE GRADES
    
  </div>
</div>

<!-- Students section -->
<h3 class="centerJustify" style="padding-top:30px; color:black ">STUDENTS</h3>

 <!-- Add New Student button -->
 <div style="justify-content:center; align-items:center; display:flex; padding-bottom:10px">
  	<a  href="${pageContext.request.contextPath}/jsp/Admin/AddUserForm.jsp?type=STUDENTS"><button style="padding:6px 13px">Add New Student</button></a>
</div>

<!-- Displays the student's information. -->
<div style="justify-content:center; align-items:center; display:flex;">
<table border='1' style="width: 60%">
		  <tr >
		     <th class="table-data" >User ID</th>
		   	 <th class="table-data" >First Name</th>
		   	 <th class="table-data" >Last Name</th>
		   	 <th class="table-data" >Email</th>
		     <th class="table-data" >Password</th>
		     <th >Edit</th>
		     <th >Delete</th>
		  </tr>

 <c:forEach var="row" items="${resultSet}">
    <tr>
      <td class="table-data" >${row.userId}</td>
      <td>${row.first}</td>
      <td>${row.last}</td>
      <td>${row.email}</td>
      <td>${row.password}</td>
      <td> <!-- EDIT STUDENTS -->
         <form style="text-align:center; padding:4px" method='post' action='${pageContext.request.contextPath}/AdminEditUserButton'>
          <input type='hidden' name='first' value='${row.first}'/>
          <input type='hidden' name='last' value='${row.last}'/>
          <input type='hidden' name='email' value='${row.email}'/>
          <input type='hidden' name='password' value='${row.password}'/>
          <input type='hidden' name='tableName' value='STUDENTS'/>
          <input type='hidden' name='userid' value='${row.userId}'/>
          <input style="padding:3px 15px" type='submit' value='Edit'/>
        </form> 
      </td>
      <td> <!-- DELETE STUDENTS -->
       <form style="text-align:center; padding:4px" method='post' action='${pageContext.request.contextPath}/AdminDeleteUserButton'>
      	  <input type='hidden' name='tableName' value='STUDENTS'/>
          <input type='hidden' name='userid' value='${row.userId}'/>
          <input style="padding:3px 15px" type='submit' value='Delete'/>
        </form>
      </td>
    </tr>
</c:forEach>
</table>
</div>


<!-- Faculty section -->
<h3 class="centerJustify" style="padding-top:40px; color:black ">FACULTY</h3>

 <!-- Add New Instructor button -->
<div style="justify-content:center; align-items:center; display:flex; padding-bottom:10px; ">
  	<a  href="${pageContext.request.contextPath}/jsp/Admin/AddUserForm.jsp?type=FACULTY"><button style="padding:6px 13px" >Add New Instructor</button></a>
</div>

<!-- Displays the faculty members' information and the buttons 'edit and 'delete'. -->
<div style="justify-content:center; align-items:center; display:flex;">
<table border='1' style="width: 60%">
		  <tr >
		     <th class="table-data" >Faculty ID</th>
		   	 <th class="table-data" >First Name</th>
		   	 <th class="table-data" >Last Name</th>
		   	 <th class="table-data" >Email</th>
		     <th class="table-data" >Password</th>
		     <th >Edit</th>
		     <th >Delete</th>
		  </tr>

 <c:forEach var="row" items="${resultSet2}">
    <tr>
      <td class="table-data" >${row.userId}</td>
      <td>${row.first}</td>
      <td>${row.last}</td>
      <td>${row.email}</td>
      <td>${row.password}</td>
      <td> <!-- EDIT FACULTY -->
         <form style="text-align:center; padding:4px" method='post' action='${pageContext.request.contextPath}/AdminEditUserButton'>
          <input type='hidden' name='first' value='${row.first}'/>
          <input type='hidden' name='last' value='${row.last}'/>
          <input type='hidden' name='email' value='${row.email}'/>
          <input type='hidden' name='password' value='${row.password}'/>
          <input type='hidden' name='tableName' value='FACULTY'/>
          <input type='hidden' name='userid' value='${row.userId}'/>
          <input style="padding:3px 15px" type='submit' value='Edit'/>
        </form> 
      </td>
      <td> <!-- DELETE FACULTY -->
       <form style="text-align:center; padding:4px" method='post' action='${pageContext.request.contextPath}/AdminDeleteUserButton'>
      	  <input type='hidden' name='tableName' value='FACULTY'/>
          <input type='hidden' name='userid' value='${row.userId}'/>
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