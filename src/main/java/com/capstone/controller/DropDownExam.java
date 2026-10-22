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


import com.capstone.serviceClasses.GetAllExamsService;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves all the exams on the database and displays them in a drop-down list.
 * The getAllExams methods from the GetAllExamsService retrieves a list of all the exams and store them in an ArrayList(exams). 
 * It then, dispatch the list to the jsp page to be displayed to the students to select their exam.
 */
@WebServlet(name = "dropDownExam", urlPatterns = { "/dropDownExam" })
public class DropDownExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GetAllExamsService examService = new GetAllExamsService();
		ArrayList<String> exams;
		
	    try {
	        exams = examService.getAllExams();
	        request.setAttribute("exams", exams);
	        RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Students/DropDownExams.jsp");
	    	Dispatcher.forward(request, response);
	            
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}