<!-- ADMINISTRATOR PAGE
This is the main page for administrators after the login. This page gives access to sensitive data and permission to make any necessary
alterations.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>ADMIN MainHub</title>
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
  <div class="container">

    <div class="left-section clickable-div" onclick="ADM1()">
      
    <p class="textADMHub">UPDATE USERS</p>

    </div>
  
    <div class="center-section clickable-div" onclick="ADM2()">
      
      <p class="textADMHub">UPDATE EXAMS</p>
      
    </div>
    
    <div class="right-section clickable-div" onclick="ADM3()">
      
    <p class="textADMHub">UPDATE GRADES</p> 
      
    </div>
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

  <div class="bottom-barADM">
      <p class="email">Support: testonlinespp@edu.com</p>
      <p class="year">© 2023</p>

  </div>


  <script>
      const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/javaScriptADM.js"></script>
</body>
</html>