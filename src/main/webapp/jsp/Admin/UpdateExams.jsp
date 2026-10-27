<!-- ADMINISTRATOR PAGE
This page displays the drop-down menu to select an exam and the questions belonging to that exam along 
with the buttons 'edit' and 'delete'. A button to create a new exam is also implemented in this page.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Update Exams</title>
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
  
  <div class="center-section active2 clickable-div" onclick="ADM2()">
    
    UPDATE EXAMS
    
  </div>
  
  <div class="right-section clickable-div" onclick="ADM3()">
    
    UPDATE GRADES
    
  </div>
</div>

<div style="display: flex; flex-direction: row; align-items: center;  justify-content: center; padding-top:0px ">

 <!-- Create New Exam button-->
	 <div style="justify-content:center; align-items:center; display:flex; padding:25px 85px">
	  	<a  href="${pageContext.request.contextPath}/jsp/Admin/NewExamForm.jsp"><button style="padding:5px 10px"> New Exam</button></a>
	</div>
	
<!-- Drop Down Menu -->
	<div class="centerJustify" style="padding:25px 85px" >
	<form action="${pageContext.request.contextPath}/AdminDropDownAllExams" method="get">
		<select class="dropdown-menu" name="selected" style="font-size:13px; width: 150px !important;">
			<option value="" selected disabled>Update Exam</option>
		<c:forEach var="table" items="${tableExams}">
		    <option value="${table}">${table}</option>
		</c:forEach>
		</select>	
		<input type="submit" value="OK">
	</form>
	</div>

<!-- Delete Exam button-->
	 <div style="justify-content:center; align-items:center; display:flex; padding:25px 85px">
		<a  href="${pageContext.request.contextPath}/AdminDeleteExamButton"><button style="padding:5px 10px">Delete Exam</button></a>
	</div>
</div>


<!-- Displays the questions belonging to the selected exam and the buttons 'edit and 'delete'. -->
<h3 class="centerJustify" style="padding-top:5px; color:black "> ${table} </h3>
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
                <th >Edit</th>
		    	<th >Delete</th>
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
                        <td> <!-- EDIT GRADE -->
                        	<form style="text-align:center; padding:3px" method='post' action='${pageContext.request.contextPath}/AdminEditExamButton'>
			           			<input type='hidden' name='qid' value='${question.qid}'/>
			           			<input type='hidden' name='questionText' value='${question.qtext}'/>
			         		 	<input type='hidden' name='op1' value='${question.opt1}'/>
			         		 	<input type='hidden' name='op2' value='${question.opt2}'/>
			          			<input type='hidden' name='op3' value='${question.opt3}'/>
			          			<input type='hidden' name='op4' value='${question.opt4}'/>
			          			<input type='hidden' name='correctAnswer' value='${question.correctAnswer}'/>
			          			<input type='hidden' name='tableName' value='${table}'/>
			         		 	<input style="padding:3px 15px" type='submit' value='Edit'/>
			         	 	</form> 
			        	</td>
			        	<td> <!-- DELETE GRADE -->
			          		<form style="text-align:center; padding:3px" method='post' action='${pageContext.request.contextPath}/AdminDeleteQuestionButton'>
				      	   		<input type='hidden' name='table' value='${table}'/>
				          		<input type='hidden' name='qid' value='${question.qid}'/>
				          		<input style="padding:3px 15px" type='submit' value='Delete'/>
			        		</form>
      					</td>      
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
response.sendRedirect("${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp");
}
%>  

  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/javaScriptADM.js"></script>

</body>
</html>