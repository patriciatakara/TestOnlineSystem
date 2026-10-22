package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.GetAllExamsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Patricia Takara
 * @implSpec This servlet is designed to retrieve all the contents of an exam, includig the question ID, question text, multiple choices, 
 * and the correct answer.
 * It first retrieves all the exams on the database and display them in a drop-down list. When the instructor selects an exam, all the
 * content of that exam is displayed. 
 * The getAllExams method from the GetallExamsService class is responsible for retrieving a list of all the exams and store in an
 * ArrayList (exams). The getQuestionsFromAnExam method from the GetAllExamsService class is responsible for retrieving all the
 * content of a specified exam, and store them on an Array list(questions). Both lists are dispatched to their respective pages.
 */
@WebServlet(name = "ViewExams", urlPatterns = { "/ViewExams" })
public class ViewExams extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		String table = request.getParameter("selected");
		
		ArrayList<NewExamBean> questions = new ArrayList<>(); 

		GetAllExamsService examService = new GetAllExamsService();
		ArrayList<String> exams;
		
		try {
			exams = examService.getAllExams();
			
			if (table != null) {
				questions = examService.getQuestionsFromAnExam(table);
				
				session.setAttribute("email", email); 
				session.setAttribute("tableExams",exams); 
				session.setAttribute("table", table);
				request.setAttribute("questions",questions);     
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DisplayExams.jsp");
				Dispatcher.forward(request, response);

			}else {
				session.setAttribute("email", email); 
				request.setAttribute("tableExams", exams);
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DisplayExamsDropDown.jsp");
				Dispatcher.forward(request, response);	
			}
	
		} catch (SQLException | ClassNotFoundException e) {
				 e.printStackTrace();	
		} 
	}
}	