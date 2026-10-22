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
 * @implSpec This servlet retrieves the names of all tables storing the grades of each exam and sends them to the DeleteExam.jsp page. 
 * The names of each table containing an exam's grades are displayed in a table format for the administrator to perform the necessary 
 * deletions. 
 */
@WebServlet("/AdminDeleteExamButton")
public class AdminDeleteExamButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		GetAllExamsService examService = new GetAllExamsService();
		ArrayList<NewExamBean> exams;
		
		try {
			exams = examService.getExamsName();
				
			HttpSession session = request.getSession(); 
			session.setAttribute("tableExams",exams);      
			RequestDispatcher Dispatcher = request.getRequestDispatcher("jsp/Admin/DeleteExam.jsp");
			Dispatcher.forward(request, response);
	
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();	
		} 
	}
}