package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capstone.serviceClasses.DeleteService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for deleting an exam from the database. The examName is the specified name of the exam to be 
 * deleted. It is then passed to the method deleteExam from the deleteService class to process the deletion. If the exam is deleted
 * successfully, the application will display an "Exam deleted successfully" message, otherwise, it will display "Exam not deleted."
 */
@WebServlet("/AdminDeleteExamProcess")
public class AdminDeleteExamProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String examName = request.getParameter("examName");
		DeleteService deleteService = new DeleteService();
		boolean deleted = false;
		
		try {
			deleted = deleteService.deleteExam(examName);
			
			if (deleted) {
	        	HttpSession session = request.getSession();
		        session.setAttribute("getAlert1", "Yes");
	            response.sendRedirect(request.getContextPath() + "/AdminDeleteExamButton");
	        } else {
	            HttpSession session = request.getSession();
	            session.setAttribute("getAlert2", "Yes");
	            response.sendRedirect(request.getContextPath() + "/AdminDeleteExamButton");
	        }	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		 	
	}
}