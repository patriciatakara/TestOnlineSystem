package com.capstone.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Patricia Takara
 * @implSpec This servlet logs out the user from the system application.
 * It invalidates the current session of the user by removing the email attribute from the session.
 * The user is redirected to the main page (index.jsp).
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");	
	}
}