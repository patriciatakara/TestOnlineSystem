package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.EditService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for updating a user's information stored on the database.
 * The updateUser method from the EditService class, passes the user's name, ID number, email address, and password to process 
 * the editing, and update the user on the database. 
 */
@WebServlet(name = "AdminEditUserProcess", urlPatterns = { "/AdminEditUserProcess" })
public class AdminEditUserProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("tableName");
		String userid = request.getParameter("userid");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RegisterBean user = new RegisterBean(first, last, email, password);
	
		EditService editService = new EditService();
		int rowsUpdated;
		
		try {
			rowsUpdated = editService.updateUser(user, tableName, userid);
			
			if(rowsUpdated > 0) {  
				response.sendRedirect(request.getContextPath() + "/AdminUpdateUsers");  
		    } else {
		    	HttpSession session = request.getSession();
		    	session.setAttribute("userNotAdded","Yes"); 
		    	session.setAttribute("user", user);
		    	session.setAttribute("tableName",tableName); 
		    	session.setAttribute("userid",userid); 
		    	response.sendRedirect(request.getContextPath() + "/jsp/Admin/EditUserForm.jsp"); }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	