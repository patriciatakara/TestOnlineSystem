package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.bean.NewExamBean;
import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class AddQuestionService {
	
	/**
	 * This method adds questions to the database. It needs the exam's name to localize which table the question will be added to.
	 * A NewExamBean object is also passed as a parameter. it contains the question text, multiple choices, and the correct answer.
	 * @param ne is the parameter for a NewExamBean object containing the contents of a question.
	 * @param tableName is the parameter for the exam's name.
	 * @throws Exception for any errors occurring during its execution.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public static void addQuestion(NewExamBean ne, String tableName) throws Exception {
		
		Connection connection = null;
		PreparedStatement statement = null;
		String qtext = ne.getQtext();
		String op1 = ne.getOpt1();
		String op2 = ne.getOpt2();
		String op3 = ne.getOpt3();
		String op4 = ne.getOpt4();
		String answer = ne.getCorrectAnswer();
		
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "insert into " + tableName + " (question, op1, op2, op3, op4, correctans) values(?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1,qtext);
			statement.setString(2, op1);
			statement.setString(3,op2);
			statement.setString(4, op3);
			statement.setString(5, op4);
			statement.setString(6, answer);
			statement.executeUpdate();
			
		  } catch (Exception e) {
		        e.printStackTrace();
		        throw e;
		        
		  } finally {
		        if (statement != null) {
		            statement.close();
		        }
		        if (connection != null) {
		            connection.close();
		        }
		  }
	}
}