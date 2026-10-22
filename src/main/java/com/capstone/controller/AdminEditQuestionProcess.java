package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.EditService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for updating a question from an exam on the database. The updateQuestion method from the 
 * EditService class, passes the question's content, exam name and the question ID to process the editing, and update the question 
 * on the database.
 */
@WebServlet("/AdminEditQuestionProcess")
public class AdminEditQuestionProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableName = request.getParameter("examination");
		String qid = request.getParameter("qid");
		String questionText = request.getParameter("questionText");
		String op1 = request.getParameter("op1");
		String op2 = request.getParameter("op2");
		String op3 = request.getParameter("op3");
		String op4 = request.getParameter("op4");
		String correctAnswer = request.getParameter("correctAnswer");
		NewExamBean question = new NewExamBean(questionText, op1, op2 ,op3, op4, correctAnswer);
		
		EditService editService = new EditService();
		int rowsUpdated;
		
		try {
			rowsUpdated = editService.updateQuestion(tableName, qid, question);
			
			if(rowsUpdated > 0) {  
				response.sendRedirect(request.getContextPath() + "/AdminDropDownAllExams?selected="+tableName);	
		    } 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
}