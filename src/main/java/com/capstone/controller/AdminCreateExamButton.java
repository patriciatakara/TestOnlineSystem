package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capstone.serviceClasses.NewExamService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for creating a new exam. It will first check if the exam name entered already exist on the 
 * database. If there is an exam name with the same name the administrator entered, an error message will be displayed. 
 * If there are no exam names with the same name, the exam is created  successfully, and the system will display a message confirming it.
 */
@WebServlet("/AdminCreateExamButton")
public class AdminCreateExamButton extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newExamName = request.getParameter("examName");
		NewExamService newExamService = new NewExamService();
		HttpSession session = request.getSession();

		try {
			if (newExamName == null || newExamName.trim().isEmpty()) {
				session.setAttribute("getAlert2", "Yes");
			} else if (newExamService.checkExamName(newExamName) > 0) {
				session.setAttribute("getAlert1", "Yes");
			} else {
				int updateCount = newExamService.createNewTable(newExamName);

				if (updateCount == 0) {
					session.setAttribute("getAlert2", "Yes");
				} else {
					session.setAttribute("getAlert3", "Yes");
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException("Error creating the exam.", e);
		}

		response.sendRedirect(
		request.getContextPath() + "/jsp/Admin/NewExamForm.jsp");
		}
}