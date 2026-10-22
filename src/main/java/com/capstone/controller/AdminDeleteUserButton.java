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
 * @implSpec This servlet is responsible for deleting a user(student or faculty member) on the database. The values from the parameters, 
 * tableName and userid, are passed to the deleteUser method from the DeleteService class. The tableName is used to identify which list 
 * of users(students or faculty) the user will be deleted. After the user is deleted the administrator is redirected to the same page 
 * in case more deletions are necessary. 
 */
@WebServlet(name = "AdminDeleteUserButton", urlPatterns = { "/AdminDeleteUserButton" })
public class AdminDeleteUserButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("tableName");
		String userId = request.getParameter("userid");
		
		DeleteService user = new DeleteService();
		
		try {
			user.deleteUser(tableName, userId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/AdminUpdateUsers");
	} 			
}