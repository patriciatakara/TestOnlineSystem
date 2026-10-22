package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.capstone.bean.NewExamBean;
import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class TakeTestService {

	/**
	 * This method retrieves a specified exam and its contents.
	 * @param exam is the name of the specified exam.
	 * @return an ArrayList of NewExamBean type containing all the questions and their IDs, multiple choices, and correct answers from an exam. 
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public ArrayList <NewExamBean> getExam(String exam) throws SQLException, ClassNotFoundException {
	       
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    ArrayList<NewExamBean> questions = new ArrayList<>();
	      
	    try {
	       connection = DatabaseConnection.getConnection();
	       String sql = "SELECT * from " + exam ;
	       statement = connection.prepareStatement(sql);
	       resultSet = statement.executeQuery();

	       while (resultSet.next()) {
	    	   NewExamBean question = new NewExamBean();
	           question.setTableName(exam);
	           question.setQid(resultSet.getString("qid"));
	           question.setQtext(resultSet.getString("question"));
	           question.setOpt1(resultSet.getString("op1"));
	           question.setOpt2(resultSet.getString("op2"));
	           question.setOpt3(resultSet.getString("op3"));
	           question.setOpt4(resultSet.getString("op4"));
	           question.setCorrectAnswer(resultSet.getString("correctans"));
	           questions.add(question);   
	           
	        }
	     }catch (SQLException e) {
	    	 e.printStackTrace(); 
	    	
	     } finally {
	    	 if (resultSet != null) {
				resultSet.close();
			 }
			 if (statement != null) {
				statement.close();
			 }
			 if (connection != null) {
				connection.close();
			 }
	     }
	     return questions;
	} 
	  
	  
	/**
	 * This method gets the number of questions of a specified exam. 
	 * @param exam is the exam name.
	 * @return an integer returning the total number of questions from an exam.
	 */
	public int questionsCount(ArrayList<NewExamBean> exam) {
		
		int questionCount = exam.size();
		return questionCount;  
	}
	
	 
	 /**
	  * This method calculates the duration of a quiz based on the number of question
	  * @param questionCount is the number of questions of an exam.
	  * @return an integer representing the duration of an exam in seconds.
	  */
	public int duration(int questionCount) {
		
		int  quizDurationSeconds =(questionCount * 60);
		return  quizDurationSeconds;  
	}	  

}