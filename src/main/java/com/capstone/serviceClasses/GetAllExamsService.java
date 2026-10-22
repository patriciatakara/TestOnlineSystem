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
public class GetAllExamsService {
	
	 /**
	  * This method retrieves the names of exams in the database and returns them as an ArrayList of strings called "exams."
	  * @return an ArrayList of Strings, where each String is the name of a table/exam in the database. 
	  * @throws SQLException for errors occurring while accessing the database.
	  * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	  */
	 public ArrayList<String> getAllExams() throws SQLException, ClassNotFoundException {
		 
		 Connection connection = null;
	     PreparedStatement statement = null;
	     ResultSet resultSet = null;
	     ArrayList<String> exams = new ArrayList<String>();
		 String examName;	 
		 
		 try { 
			connection = DatabaseConnection.getConnection();
	        String sql = "select TABLE_NAME from ALL_TABLES order by TABLE_NAME asc";
	        statement = connection.prepareStatement(sql);
	        resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            examName = resultSet.getString("table_name");
	            exams.add(examName);
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
		 return exams;
	 }
	 
	 
	 /**
	   * This method retrieves the names of exams on the database and returns them as an ArrayList of NewExamBean type.
	   * The return type is NewExamBean, not String like the previous method. The exams' names returned on this method are used to be
	   * displayed directly on a jsp page. 
	   * @return an NewExamBean object containing the names of each table/exam on the database. 
	   * @throws SQLException  for errors occurring while accessing the database.
	   * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	   */
	  public ArrayList<NewExamBean> getExamsName() throws SQLException, ClassNotFoundException {
			 
		  Connection connection = null;
		  PreparedStatement statement = null;
		  ResultSet resultSet = null;
		  ArrayList<NewExamBean> exams = new ArrayList<NewExamBean>(); 
			 
		  try { 
			  connection = DatabaseConnection.getConnection();
		      String sql = "select TABLE_NAME from ALL_TABLES order by TABLE_NAME asc";
		      statement = connection.prepareStatement(sql);
		      resultSet = statement.executeQuery();
		        
			  while (resultSet.next()) {
				  NewExamBean examName = new NewExamBean();
			      examName.setTableName(resultSet.getString("table_name"));
			      exams.add(examName);
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
			return exams;
		 }
	 
	 
	 /**
	  * This method retrieves the content of all the exams stored in a database. It takes an ArrayList of exam names as input.
	  * @param exams is an ArrayList containing all the exams in the database. It will be used for iteration. 
	  * @return an ArrayList of NewExamBean objects containing all the questions, multiple choices, and correct answers from all exams in the database.
	  * @throws SQLException for errors occurring while accessing the database.
	  * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	  */
	  public ArrayList <NewExamBean> getQuestionsFromAllExams(ArrayList<String> exams) throws SQLException, ClassNotFoundException {
	       
		  Connection connection = null;
	      PreparedStatement statement = null;
	      ResultSet resultSet = null;
	      ArrayList<NewExamBean> questions = new ArrayList<>();

	      try {
	         connection = DatabaseConnection.getConnection();
	         for (String exam : exams) {
	        	 String sql = "SELECT * from " + exam + " ORDER BY qid ASC";
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
	   * This method retrieves all the questions, multiple choices, and correct answers from a single exam/table in the database.
	   * @param examSelected is a String containing the name of the exam table from which to retrieve the questions.
	   * @return all the content of an exam in the form of an ArrayList of NewExam objects.
	   * @throws SQLException for errors occurring while accessing the database.
	   * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	   */
	  public ArrayList <NewExamBean> getQuestionsFromAnExam(String examSelected) throws SQLException, ClassNotFoundException {
	       
		  Connection connection = null;
	      PreparedStatement statement = null;
	      ResultSet resultSet = null;
	      ArrayList<NewExamBean> questions = new ArrayList<>();
	      String exam = examSelected;
	      							
	      try {
	         connection = DatabaseConnection.getConnection();
	         String sql = "select * from " + exam + " order by QID asc";
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
	  
}