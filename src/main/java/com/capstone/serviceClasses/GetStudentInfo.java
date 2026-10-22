package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class GetStudentInfo {
	
	/**
	 * This method gets the first and last name of a student with the given ID.
	 * @param userid is the student's ID.
	 * @return a string containing the first and last name of a student.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public String getName(int userid)throws ClassNotFoundException, SQLException  {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String first, last;
		String fullName = "";
			
		try {
			connection = DatabaseConnection.getConnection();
			String query = "select FIRSTNAME, LASTNAME from STUDENTS where USERID=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, userid); 
			resultSet= statement.executeQuery();
			    
		    if (resultSet.next()) {
		    	first =  resultSet.getString("FIRSTNAME");
		    	last = resultSet.getString("LASTNAME");
		    	fullName = first + " " + last; 
		    }
		} catch (SQLException | ClassNotFoundException e) {
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
	return fullName;
	}
	
	
	/**
	 * This method gets the student's ID with a given email.
	 * @param email is the student's email.
	 * @return an integer containing the student's ID
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public int getStudentId(String email) throws ClassNotFoundException, SQLException { 
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int userId = 0;
	
		try {
			connection = DatabaseConnection.getConnection();
			String query = "select USERID from STUDENTS where email=?";
			statement = connection.prepareStatement(query);
		    statement.setString(1, email.toUpperCase()); 
		    resultSet= statement.executeQuery();
		    
	        if (resultSet.next()) {
	            userId =  resultSet.getInt("USERID");
	        }
	    } catch (SQLException | ClassNotFoundException e) {
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
		return userId;
	}	   

}