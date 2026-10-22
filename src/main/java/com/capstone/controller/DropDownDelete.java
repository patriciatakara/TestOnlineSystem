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
 * @implSpec This servlet retrieves all the exams on the database and displays them in a drop-down list. It also retrieves all the 
 * content of the exam selected from the drop-down list and displays it in a table format.
 * The getAllExams and the getQuestionsFromAnExam methods from the GetAllExamsService retrieve a list of all the exams and store them 
 * in an ArrayList(exams) and get all the content of a specified exam and stores in another ArrayList(tableExam) respectively. 
 * Both ArrayList are dispatched to their respective jsp pages to be displayed to the instructor. 
 */
@WebServlet(name = "DropDownDelete", urlPatterns = { "/DropDownDelete" })
public class DropDownDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableSelected = request.getParameter("examName");
		ArrayList<NewExamBean> tableExam = new ArrayList<>();
		
		GetAllExamsService examService = new GetAllExamsService();
		ArrayList<String> exams;
		
        try {
            exams = examService.getAllExams();
            
            if (tableSelected != null) {
            	tableExam = examService.getQuestionsFromAnExam(tableSelected);
            	
	            HttpSession session = request.getSession(); 
	        	session.setAttribute("exams",exams); 
				session.setAttribute("examName", tableSelected);
	            request.setAttribute("tableExam", tableExam);
	        	RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DeleteQuestions.jsp");
	    	    Dispatcher.forward(request, response);
            }else {
 
            	request.setAttribute("tableExams", exams);
				RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Faculty/DropDownDelete.jsp");
				Dispatcher.forward(request, response);		
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
}