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
 * @implSpec This servlet is responsible for deleting a grade from an exam on the database. The values from the parameters, tableName 
 * and userID, are passed to the deleteGrade method from the DeleteService class. The tableName is used to identify which exam the grade 
 * will be deleted, and the userId is used to identify which student is havingthe grade deleted. After the grade is deleted the user is 
 * redirected to the same page in case more deletions are necessary.
 */
@WebServlet(name = "AdminDeleteGradeButton", urlPatterns = { "/AdminDeleteGradeButton" })
public class AdminDeleteGradeButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("tableName");
		String userId = request.getParameter("userid");
	
		DeleteService grade = new DeleteService();
		
		try {
			grade.deleteGrade(tableName, userId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/AdminDropDownAllGrades?selected="+tableName);
	} 			
}