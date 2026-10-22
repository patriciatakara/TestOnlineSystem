package com.capstone.controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.capstone.serviceClasses.AuthenticationService;
/**
 * @author Patricia Takara
 * @implSpec This servlet authenticates a student user. 
 * The authenticate method from the AuthenticationService verifies the email and password parameters against the stored credentials 
 * in the database. If the student is authenticated successfully, he is redirected to the StudentHub, 
 * otherwise, an alert is displayed informing the error. 
 */
@WebServlet(name = "StudentLogin", urlPatterns = { "/StudentLogin" })
public class StudentLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String table = "STUDENTS";
		
		AuthenticationService login = new AuthenticationService();
		
		try {
			if (login.authenticate(email, password, table)) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				response.sendRedirect( request.getContextPath() + "/jsp/Students/StudentHub.jsp");
			} else {
				HttpSession session = request.getSession(); 
				session.setAttribute("getAlert", "Yes");
				response.sendRedirect(request.getContextPath() +"jsp/Forms/StudentLoginForm.jsp");
			}	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}