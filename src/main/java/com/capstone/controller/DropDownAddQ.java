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
import com.capstone.serviceClasses.GetAllExamsService;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves all the exams on the database and displays them in a drop-down list.
 * The getAllExams method from the GetallExamsService class is responsible for retrieving a list of all the exams and store in an
 * ArrayList (exams). 
 * When the instructor selects an exam from the drop-down list, the exam name is sent to this same servlet to set it as an 
 * attribute, and dispatch it to the AddQuestionForm which will provide the fields to create a new question for the specified exam.
 */
@WebServlet(name = "DropDownAddQ", urlPatterns = { "/DropDownAddQ" })
public class DropDownAddQ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableName = request.getParameter("examName");
		GetAllExamsService examService = new GetAllExamsService();
		ArrayList<String> exams;
		
        try {
        	  exams = examService.getAllExams();
        	
        	if (tableName != null) {
        		HttpSession session = request.getSession(); 
 				session.setAttribute("exams",exams);
        	    request.setAttribute("tableName",tableName);
    			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Faculty/AddQuestionForm.jsp");
    		    dispatcher.forward(request, response); 	    
        	}else {
	            request.setAttribute("exams", exams);
	            RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DropDownAddQ.jsp");
	    	    Dispatcher.forward(request, response);
        	}
    	   
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
}