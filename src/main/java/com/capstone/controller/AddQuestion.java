package com.capstone.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.AddQuestionService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for adding a new question to an exam.
 * The values from the parameters passed from the AddQuestionForm.jsp are used to create a NewExamBean object that will contain the
 * exam name, new question text, multiple choice options, and a correct answer. Then, the new question set and the exam name are
 * passed to the addQuestion method from the AddQuestionService class to be added to the database.
 * If the question is successfully added to the database, a box alert will be displayed to confirm it and the instructor is redirected to
 * the same form to add another question.
 * If the question fails to be inserted into the database, a box alert is displayed to the instructor describing the error.
 */
@WebServlet(name = "AddQuestion ", urlPatterns = { "/AddQuestion" })
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableName = request.getParameter("examName").toUpperCase(); 
		String questiontxt = request.getParameter("question"); 
		String answerOp1 = request.getParameter("optionA"); 
		String answerOp2 = request.getParameter("optionB"); 
		String answerOp3 = request.getParameter("optionC"); 
		String answerOp4 = request.getParameter("optionD"); 
		String correctAns = request.getParameter("correctAnswer");
		
		NewExamBean question = new NewExamBean(tableName,questiontxt,answerOp1, answerOp2, answerOp3, answerOp4, correctAns);
			
		try {
			AddQuestionService.addQuestion(question, tableName);
					
			HttpSession session = request.getSession(); 
			session.setAttribute("getAlert1","Yes");
			session.setAttribute("tableName", tableName);
			response.sendRedirect(request.getContextPath() + "/jsp/Faculty/AddQuestionForm.jsp");
					  	  	
		} catch (Exception e) {
			e.printStackTrace();  
			
			HttpSession session = request.getSession(); 
			session.setAttribute("getAlert2","Yes");
			session.setAttribute("tableName", tableName);
			response.sendRedirect(request.getContextPath() + "/jsp/Faculty/AddQuestionForm.jsp");
		}
	}
}