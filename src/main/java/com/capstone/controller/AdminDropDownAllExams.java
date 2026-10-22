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
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.GetAllExamsService;
/** 
 * @author Patricia Takara
 * @implSpec This servlet retrieves all the exams on the database and display them in a drop-down list. It also retrieves all the content 
 * of the exam selected from the drop-down list. The getAllExams method from the GetallExamsService class is responsible for retrieving 
 * a list of all the exams and store in an ArrayList (exams). The getQuestionsFromAnExam method from the GetAllExamsService class is 
 * responsible for retrieving all the content of a specified exam, and store them on an Array list(questions).
 *  Both lists are dispatched to their respective pages.
 */
@WebServlet(name = "AdminDropDownAllExams", urlPatterns = { "/AdminDropDownAllExams" })
public class AdminDropDownAllExams extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String table = request.getParameter("selected");
		ArrayList<NewExamBean> questions = new ArrayList<>(); 

		GetAllExamsService examService = new GetAllExamsService();
		ArrayList<String> exams;
		
		try {
			// Retrieve all exam names to populate the administrator's exam list.
			exams = examService.getAllExams();
			
			// Load the selected exam's questions when an exam was chosen.
			if (table != null) {
				questions = examService.getQuestionsFromAnExam(table);
				
				HttpSession session = request.getSession(); 
				session.setAttribute("tableExams",exams); 
				session.setAttribute("table", table);
				request.setAttribute("questions",questions);     
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Admin/UpdateExams.jsp");
				Dispatcher.forward(request, response);
				
			// No exam is selected, so display the exam dropdown.
			}else {
				request.setAttribute("tableExams", exams);
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Admin/UpdateExamsDropDown.jsp");
				Dispatcher.forward(request, response);	
			}
	
		} catch (SQLException | ClassNotFoundException e) {
				 e.printStackTrace();	
		} 
	}
}