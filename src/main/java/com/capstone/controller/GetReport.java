package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.ReportBean;
import com.capstone.serviceClasses.GetAllGradesService;
import com.capstone.serviceClasses.GetStudentInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Patricia Takara
 * @implSpec This servlet is designed to retrieve a student's grades for each exam they have taken.
 * The student's email is used to get the student's ID and full name.
 * The getAllTablesGrades method from the GetAllGradesService is used to fetch the names of all tables in the database from which 
 * grades can be retrieved. The getGradesStudent from the same class, GetAllGradesServices, will retrieve all the grades of any exams
 * that the student has taken. The student ID is used as a reference to find out which exams he already has been graded.
 * The complete report, including the student's full name, user ID, and grades is displayed to the student.
 */
@WebServlet(name = "GetReport", urlPatterns = { "/GetReport" })
public class GetReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
	    GetStudentInfo user = new GetStudentInfo();
	    int userId;
	    String fullName;
	    
		try {
			userId = user.getStudentId(email);
			
			ArrayList<String> tableGrades = new ArrayList<String>();
			ArrayList<ReportBean> grades= new ArrayList<>();	
			GetAllGradesService allTablesGrades = new GetAllGradesService();
			
			try {
				tableGrades = allTablesGrades.getAllTablesGrades();
				grades = allTablesGrades.getGradesStudent(tableGrades, userId);	
				fullName = user.getName(userId);
				
				request.setAttribute("fullName", fullName);
				request.setAttribute("userid",userId);
			    request.setAttribute("tableGrades", tableGrades);
				request.setAttribute("grades",grades);
				RequestDispatcher Dispatcher = request.getRequestDispatcher("/jsp/Students/Reports.jsp");
			    Dispatcher.forward(request, response); 
	  
			} catch (ClassNotFoundException | SQLException | NullPointerException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/jsp/Forms/StudentLoginForm.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}catch(NullPointerException e){
			response.sendRedirect(request.getContextPath() + "/jsp/Forms/StudentLoginForm.jsp");
		}
	}

}   