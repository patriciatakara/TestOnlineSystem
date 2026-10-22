package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class VerifyExamDone {

	/**
	 * This method verifies if a student has already done a specified exam. 
	 * @param userid is the student's ID.
	 * @param exam is the name of the exam.
	 * @return a boolean value. True if a grade for the specified exam exists, and false otherwise.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database. 
	 */
	public boolean Verify(int userid, String exam) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String tableGrade  = exam+"_grade";
		 
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "select * from " +tableGrade+ " where USERID =? and EXAM =?";
			statement = connection.prepareStatement(sql) ;
			statement.setInt(1, userid);
			statement.setString(2, exam);
			resultSet = statement.executeQuery();
				
			if(resultSet.next()) {
				return false;
			}	
		}catch (Exception e) {
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
		return true;	
	}
}