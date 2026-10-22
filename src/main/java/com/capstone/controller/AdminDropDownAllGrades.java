package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.capstone.bean.ReportBean;
import com.capstone.serviceClasses.GetAllGradesService;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves the name of all tables storing the grades of each exam and display them on a drop-down list. 
 * It also retrieves all the grades for a specified exam. The getAllTablesGrades method from the GetAllGradesService class is responsible 
 * for retrieving a list of all the tables storing the grades of each exam and store in an ArrayList (grades). 
 * The getGrades method from the GetAllGradesService class is responsible for retrieving all the student's grades for a specified exam, 
 * and store them on an Array list(reports). Both lists are dispatched to their respective pages.
 */
@WebServlet(name = "AdminDropDownAllGrades", urlPatterns = { "/AdminDropDownAllGrades" })
public class AdminDropDownAllGrades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String table = request.getParameter("selected");
		ArrayList<ReportBean> reports= new ArrayList<>(); 

		ArrayList<String> AlltablesGrades =  new ArrayList<String>();
		GetAllGradesService grades = new GetAllGradesService();
		
		try {
			AlltablesGrades = grades.getAllTablesGrades();
		
			if (table != null) {	
				reports = grades.getGrades(table);		
				
				HttpSession session = request.getSession(); 
				session.setAttribute("tableGrades", AlltablesGrades); 
				session.setAttribute("table", table);
				request.setAttribute("reports",reports);     
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Admin/UpdateGrades.jsp");
				Dispatcher.forward(request, response);
			}else {
				 request.setAttribute("tableGrades", AlltablesGrades);
				 RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Admin/UpdateGradesDropDown.jsp");
				 Dispatcher.forward(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
}