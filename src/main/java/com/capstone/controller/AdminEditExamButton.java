package com.capstone.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.bean.NewExamBean;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves the data from the exam table that is being displayed to the administrator and prepares it for editing.
 * The NewExamBean object, question, contains the question text, multiple choices, and the correct answer to a specified question  from a 
 * specified exam. This content is sent to a jsp page to be displayed in a form that will allow the administrator to edit it.
 */
@WebServlet("/AdminEditExamButton")
public class AdminEditExamButton extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qid = request.getParameter("qid");
		String tableName = request.getParameter("tableName");
		String questionText = request.getParameter("questionText");
		String op1 = request.getParameter("op1");
		String op2 = request.getParameter("op2");
		String op3 = request.getParameter("op3");
		String op4 = request.getParameter("op4");
		String correctAnswer = request.getParameter("correctAnswer");
		
		NewExamBean question = new NewExamBean(questionText,op1, op2, op3, op4, correctAnswer);
		question.setQid(qid);
		
		request.setAttribute("question",question);
		request.setAttribute("tableName",tableName);
		request.setAttribute("qid", qid);
		request.getRequestDispatcher("/jsp/Admin/EditQuestionForm.jsp").forward(request, response);
	}

}