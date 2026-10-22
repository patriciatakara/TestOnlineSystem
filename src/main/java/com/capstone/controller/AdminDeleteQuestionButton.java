package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.serviceClasses.DeleteService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for deleting a question from an exam on the database. The values from the 
 * parameters, tableName and qid, are passed to the deleteQuestion method from the DeleteService class. The tableName is used to identify 
 * which exam the question will be deleted, and the qid is used to specify which question set is being deleted. 
 * After the question is deleted the administrator is redirected to the same page in case more deletions are necessary.  
 */
@WebServlet("/AdminDeleteQuestionButton")
public class AdminDeleteQuestionButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tableName = request.getParameter("table");
		String qid = request.getParameter("qid");
	
		DeleteService grade = new DeleteService();
		
		try {
			grade.deleteQuestion(tableName, qid);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/AdminDropDownAllExams?selected="+tableName);
	}
}