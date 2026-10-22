<!-- STUDENT PAGE
This page displays the exam in process. The number of question and a timer counting down are displayed.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
<title>Examination in Process</title>

<script>
	// Disable back button
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };
</script>

</head>


<body class="entirePageAfterDropDown" >

<!-- Timer -->
<div class="timer">
<jsp:include page="Timer.jsp"/> 
</div>

<script>
	var timeLeft =${timeLeft};
	startTimer(timeLeft);
</script>


<h1 class="centerJustify" style="padding-top:5px; color:black;">${exam}</h1>

<div class="left-right">
<h3 class="leftJustify" style="color:black">${email}</h3>
<h3 class="rightJustify" style="color:black">${questionCount} questions</h3>
</div>

<!-- The exam is displayed in a test format. Each question has radio buttons to select the student's answer option -->
<form id="examForm" action="${pageContext.request.contextPath}/GradeCalculation"  method="post">
<c:set var="questionNumber" value="0" />	
	<c:forEach var="question" items="${test}">
		<c:set var="questionNumber" value="${questionNumber + 1}" />
		<div class="quiz">
			 <input type="hidden" name="exam" value="${exam}">
			
			<div class="question">${questionNumber}) ${question.qtext}</div>
			
			
			<div class="answer">
				<input type="radio" name="${question.qid}" value="${question.opt1}" /> ${question.opt1}
			</div>
			
			<div class="answer">
				<input type="radio" name="${question.qid}" value="${question.opt2}" /> ${question.opt2}
			</div>
			
			<div class="answer">
				<input type="radio" name="${question.qid}" value="${question.opt3}" /> ${question.opt3}
			</div>
			
			<div class="answer">
				<input type="radio" name="${question.qid}" value="${question.opt4}" /> ${question.opt4}
			</div>
			
			<input type="hidden" name="${question.qid}-correct" value="${question.correctAnswer}" />
			<input type="hidden" name="questionCount" value="${questionCount}" />

		</div>
	</c:forEach>
	<button class="addButton1" type="submit"  id="submitButton">Submit</button>
</form>


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