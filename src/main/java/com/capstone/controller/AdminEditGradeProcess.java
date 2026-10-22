package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import com.capstone.serviceClasses.EditService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for updating a grade on the database or the submission date. The updateGrade method from the 
 * EditService class, passes the exam name, the student's name and ID, and his grade to process the editing, and update the grade or 
 * the submission date on the database.
 */
@WebServlet(name = "AdminEditGradeProcess", urlPatterns = { "/AdminEditGradeProcess" })
public class AdminEditGradeProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("examName");
		String id = request.getParameter("userid");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String score = request.getParameter("correctAnswers");
		String letterGrade = request.getParameter("letterGrade");
		String submissionDate = request.getParameter("submissionDate");

		//If String score is null, then it must be 0 to allow it to convert into integer.
		if (score.isEmpty()) { 
			score = "0"; 
		} 

		int userid = Integer.parseInt(id);
		int grade = Integer.parseInt(score);

		EditService editService = new EditService();
		int rowsUpdated;
		
		try {
			rowsUpdated = editService.updateGrade(tableName, userid, first, last, grade, letterGrade, submissionDate);
			
			if(rowsUpdated > 0) {  
				 response.sendRedirect(request.getContextPath() + "/AdminDropDownAllGrades?selected="+tableName);		
		    } 
	
		} catch (SQLException e) {
			 e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            "A database error occurred while updating the grade. Check the Tomcat logs.");
		}
	}
}