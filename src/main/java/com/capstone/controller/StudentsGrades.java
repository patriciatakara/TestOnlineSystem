package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.bean.ReportBean;
import com.capstone.serviceClasses.GetAllExamsService;
import com.capstone.serviceClasses.GetAllGradesService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Patricia Takara
 * @implSpec This servlet is designed to retrieve all the student's grades for a specified exam.
 * It first retrieves the name of all tables storing the grades of each exam and displays them on a drop-down list. The selected exam
 * is then passed to the getGrades method from the GetAllGradesServices to retrieve the grades of all students that had taken the exam.
 * The getQuestionsFromAnExam method from the GetAllExamsService class is used to get the number of questions of the selected exam.
 * The entire report, including the exam name, the number of questions, the student's information and grade are displayed to the 
 * instructor on the DisplayGrades.jsp page.
 */
@WebServlet(name = "StudentsGrades", urlPatterns = { "/StudentsGrades" })
public class StudentsGrades extends HttpServlet {
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
				
				// It gets the name of the table's grade, remove the "_grade" part from the table name to get the name of the exam
				String table2 = table.replace("_grade", "");
				
				GetAllExamsService exams = new GetAllExamsService();
				int qCount = 0;
				
				// It passes the name of the exam to get the number of questions.
				try {
					ArrayList<NewExamBean> exam = exams.getQuestionsFromAnExam(table2);
					for(int i =0; i<exam.size();i++) {
						qCount++;
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				HttpSession session = request.getSession(); 
				session.setAttribute("tableGrades", AlltablesGrades); 
				session.setAttribute("table", table2);
				request.setAttribute("qCount",qCount); 
				request.setAttribute("reports",reports);     
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DisplayGrades.jsp");
				Dispatcher.forward(request, response);
				
			}else {
				 request.setAttribute("tableGrades", AlltablesGrades);
				 RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DisplayGradesDropDown.jsp");
				 Dispatcher.forward(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
}