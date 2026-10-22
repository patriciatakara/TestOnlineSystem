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
 * @implSpec This servlet is responsible for deleting a question from an exam on the database. 
 * The values from the parameters, tableName and qid, are passed to the deleteQuestion method from the DeleteService class.
 * The tableName is used to identify which exam the question will be deleted, and the qid is used to specify which question 
 * set is being deleted.
 * After the question is deleted the instructor is redirected to the same page in case more deletions are necessary.  
 */
@WebServlet(name = "DeleteQuestionsButton", urlPatterns = { "/DeleteQuestionsButton" })
public class DeleteQuestionsButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String name = request.getParameter("name");
		 String id = request.getParameter("qid");
		
		 DeleteService deleteService = new DeleteService();
		 
		 try {
			deleteService.deleteQuestion(name, id);
			
			 response.sendRedirect(request.getContextPath() + "/DropDownDelete?examName="+name);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	} 		
}