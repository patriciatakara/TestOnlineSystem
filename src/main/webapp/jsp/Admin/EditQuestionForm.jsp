<!-- ADMINISTRATOR PAGE
This page displays a form to edit a question of an exam on the database.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Editing Question</title>
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
  	<a href="${pageContext.request.contextPath}/AdminDropDownAllExams?selected=${tableName}" ><button >Go Back</button></a>
</div>


<h2 class="centerJustify" style="padding-top:0px; color:black ">Alteration Exam Form</h2>
<p class="centerJustify" >Exame: ${tableName}</p>
<p class="centerJustify" >Question: ${question.qid}</p>

<!-- Form to edit a question of an exam -->
<div class="formCenter6">
<form method = "post"  action="${pageContext.request.contextPath}/AdminEditQuestionProcess">
<pre>
<span>QUESTION</span>
 <input type="text" style="width:65vw; height:2vh" name="questionText" value ='${question.qtext} ' disabled> 
<textarea class="fieldSizeQuestion" name="questionText" placeholder="Question">${question.qtext}</textarea>
	
<span>OPTION A</span>
<input type="text" style="width:65vw; height:2vh" name="op1" value ='${question.opt1} ' disabled> 
<textarea class="fieldSizeAlternative" name="op1" placeholder="option A">${question.opt1}</textarea> 
	
<span>OPTION B</span>
 <input type="text" style="width:65vw; height:2vh" name="op2" value ='${question.opt2} ' disabled> 
<textarea class="fieldSizeAlternative" name="op2" placeholder="option B">${question.opt2}</textarea>
	
<span>OPTION C</span>
 <input type="text" style="width:65vw; height:2vh" name="op3" value ='${question.opt3} ' disabled> 
<textarea class="fieldSizeAlternative" name="op3" placeholder="option C">${question.opt3}</textarea>
	
<span>OPTION D</span>
 <input type="text" style="width:65vw; height:2vh" name="op4" value ='${question.opt4} ' disabled> 
<textarea class="fieldSizeAlternative" name="op4" placeholder="optionD">${question.opt4}</textarea>
	
<span>CORRECT ANSWER</span>
 <input type="text" style="width:65vw; height:2vh" name="correctAnswer" value ='${question.correctAnswer} ' disabled> 
<textarea class="fieldSizeAlternative" name="correctAnswer" placeholder="Answer">${question.correctAnswer}</textarea>
</pre>
	<input class="buttonSaveQuestions" type="submit" value="Save"> 			
	<input type="hidden" name="question" value ='${question}'>
	<input type="hidden" name="examination" value ='${tableName}'>
	<input type="hidden" name="qid" value ='${question.qid}'>
	<input type="hidden" name="questionText" value ='${text}'>
	<input type="hidden" name="op1" value ='${opt1}'>
	<input type="hidden" name="op2" value ='${opt2}'>
	<input type="hidden" name="op3" value ='${opt3}'>
	<input type="hidden" name="op4" value ='${opt4}'> 
	<input type="hidden" name="correctAnswer" value ='${correctAnswer}'>
</form>
</div>


<!-- To prevent the user to go back to the previous page after logging out of the system -->
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