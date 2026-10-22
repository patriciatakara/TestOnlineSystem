package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.GetStudentInfo;
import com.capstone.serviceClasses.TakeTestService;
import com.capstone.serviceClasses.VerifyExamDone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for starting the examination process. It will first check if the student has taken the exam 
 * before proceeding to the exam. If the student has already taken the exam, he will be informed and will not be able to proceed.
 * This servlet will use the verify method from the verifyExamDone class to check if the student already has a grade in the exam he
 * selected to take.
 * The questionCount method from the TakeTestService class is used to get the number of questions the exam selected has.
 * The duration method from the TakeTestService class is used to calculate the duration of the quiz based on the number of questions.
 * If the student has not taken the test yet, he will be redirected to the exam page(ExamFormat.jsp) and the timer will start
 * counting down.
 */
@WebServlet(name = "TakeTest", urlPatterns = { "/TakeTest" })
public class TakeTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String examName = request.getParameter("examName");
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		ArrayList<NewExamBean> test = new ArrayList<>();
		VerifyExamDone verifyExamDone = new VerifyExamDone();
		TakeTestService takeTestService = new TakeTestService();
		@SuppressWarnings("unused")
		Boolean verify;
		int questionCount = 0;
		int quizDurationSeconds = 0;
		
		// Creates an object to call the method to retriever the userID using the email.
		GetStudentInfo user= new GetStudentInfo();
		int userId;
		
		try {
			userId = user.getStudentId(email);
				
			if (verify = verifyExamDone.Verify(userId, examName)){
			
				try {
					test = takeTestService.getExam(examName);
					questionCount = takeTestService.questionsCount(test);
					quizDurationSeconds=  takeTestService.duration(questionCount);
					 
					request.setAttribute("test", test); 
					request.setAttribute("exam", examName);
					request.setAttribute("questionCount", questionCount);
					request.setAttribute("timeLeft", quizDurationSeconds);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Students/ExamFormat.jsp");
					dispatcher.forward(request, response);
						    	
				}catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
							
			}else {
				HttpSession session1 = request.getSession(); 
				session1.setAttribute("getAlert1","Yes");
				response.sendRedirect(request.getContextPath() + "/dropDownExam");
			} 

		}catch(NullPointerException e){
			response.sendRedirect(request.getContextPath() + "/jsp/Forms/StudentLoginForm.jsp");
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
	}
}