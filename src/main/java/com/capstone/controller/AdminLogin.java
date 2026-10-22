package com.capstone.controller;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.capstone.serviceClasses.AuthenticationService;
/**
 * @author Patricia Takara
 * @implSpec This servlet authenticates an administrator user. 
 * The authenticate method from the AuthenticationService verifies the email and password parameters against the stored credentials 
 * in the database. If the administrator is authenticated successfully, he is redirected to the AdminHub, otherwise, an alert is 
 * displayed informing the error. 
 */
@WebServlet(name = "AdminLogin", urlPatterns = { "/AdminLogin" })
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException   {
	
		String email = request.getParameter("admemail");
		String password = request.getParameter("admpassword");
		String table = "ADMINISTRATORS";
		
		AuthenticationService login = new AuthenticationService();
		
		try {
			if (login.authenticate(email, password, table)) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				response.sendRedirect(request.getContextPath() + "/jsp/Admin/AdmMainHub.jsp");
			} else {
				HttpSession session = request.getSession(); 
				session.setAttribute("getAlert", "Yes");
				response.sendRedirect(request.getContextPath() + "/jsp/Forms/AdminLoginForm.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
}