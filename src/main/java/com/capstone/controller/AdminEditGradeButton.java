package com.capstone.controller;
import java.io.IOException;
import com.capstone.bean.ReportBean;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves the data from the table containing the grades for an exam that is being displayed to the administrator
 * and prepares it for editing. The ReportBean object, report, contains the name of the exam, the student ID, name, the grades of a 
 * student for an exam, and the submission date. This content is sent to a jsp page to be displayed in a form that will allow the 
 * administrator to edit it.
 */
@WebServlet(name = "AdminEditGradeButton", urlPatterns = { "/AdminEditGradeButton" })
public class AdminEditGradeButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("tableName");
		String id = request.getParameter("userid");
		String firstn = request.getParameter("first");
		String lastn = request.getParameter("last");
		String score = request.getParameter("correctAnswers");
		String letterGrade = request.getParameter("letterGrade");
		String submissionDate = request.getParameter("submissionDate");
		
		int userid = Integer.parseInt(id);
		int grade = Integer.parseInt(score);
		
		ReportBean report = new ReportBean(tableName, userid, firstn, lastn, grade, letterGrade, submissionDate);
	
		request.setAttribute("report",report);
		request.setAttribute("tableName",tableName);
		request.setAttribute("userid",  userid);
		request.getRequestDispatcher("/jsp/Admin/EditGradeForm.jsp").forward(request, response);
	}
}