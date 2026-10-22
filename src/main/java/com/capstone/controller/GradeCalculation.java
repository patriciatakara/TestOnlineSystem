package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.capstone.serviceClasses.GetStudentInfo;
import com.capstone.serviceClasses.GradeCalculationService;
/**
 * @author Patricia Takara
 * @implSpec This servlet calculates the score of an exam, displays it to the student, and stores the result on the database.
 * The first step is to check the user's answers and compare them with the correct answers stored in the database. The score is
 * incremented every time if the answer is not null and correct.
 * A timestamp is generated, to display the date and time the examination was submitted.
 * The percentage of the grade and the lette grade are calculated.
 * The grades and timestamp are saved to the database by using the saveScore method from the GradeCalculationService.
 * The student's grade, the score in percentage format, ID number, and exam name are displayed to the student right after the exam is
 * submitted.
 */
@WebServlet(name = "GradeCalculation", urlPatterns = { "/GradeCalculation" })
public class GradeCalculation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String questionCountString = request.getParameter("questionCount");
		
		//If there the exam has no questions, a message will be displayed as under maintenance.
		if (questionCountString == null) {
			HttpSession session = request.getSession();
			session.setAttribute("maintenance", "Yes");
			response.sendRedirect(request.getContextPath() + "/dropDownExam");
			
		}else {
			int questionCountInt =  Integer.parseInt(questionCountString);
			
			GetStudentInfo getUserid = new GetStudentInfo();
			GradeCalculationService gradeService = new GradeCalculationService();
			int score = 0;
			
			/* Iterates through all the parameters submitted by the user and compares the user's answer with the correct answer for each 
			question. The score variable is incremented by one if the user's answer is not null and it matches the correct answer.  */
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
			    String paramName = parameterNames.nextElement();
			    if (paramName.endsWith("-correct")) {
			        // This is the hidden input field for the correct answer
			        String questionId = paramName.substring(0, paramName.indexOf("-correct"));
			        String correctAnswer = request.getParameter(paramName);
			        String userAnswer = request.getParameter(questionId);
			       
			        if (userAnswer != null && userAnswer.equals(correctAnswer)) {
			            score++;
			        }    
			    } 
			}
			
			
			try {
				String exam = request.getParameter("exam");
				HttpSession session = request.getSession();
				String email = (String) session.getAttribute("email");
	
				// Creates an object to call the method to retriever the userID using the email.	
				int userId = getUserid.getStudentId(email);
				
				// Generates a time stamp of when the exam was submitted
				String date = gradeService.timeStamp();
				
				// Calculate the grade in percentage format and letter grade.
				double percentage = gradeService.percentage(questionCountInt, score);
				String letterGrade = gradeService.getLetterGrade(percentage);
						
				// Invokes method to store the grade on the database.
				gradeService.saveScore(userId, score, exam, letterGrade, date);
				
				request.setAttribute("percentage", percentage); 
				request.setAttribute("date", date); 
				request.setAttribute("score", score); 
				request.setAttribute("exam", exam); 
				request.setAttribute("letterGrade", letterGrade); 
				request.setAttribute("userid", userId); 
				request.setAttribute("email", email); 
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Students/DisplayResult.jsp");
				dispatcher.forward(request, response);
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}	  