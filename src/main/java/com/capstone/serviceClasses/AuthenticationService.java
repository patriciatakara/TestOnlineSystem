package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class AuthenticationService {
	
	/**
	 * This method authenticates a user by checking if the provided email and password exist in the database. 
	 * The method takes three parameters.
	 * @param email is the email of the user.
	 * @param password is the password of the user.
	 * @param table is the name of the database table to search for the user's information.
	 * @return a boolean value. If the authentication is successful it returns true, otherwise, it returns false.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public boolean authenticate(String email, String password, String table) throws Exception, SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "select * from "+ table +" where EMAIL=? and PASSWORD=?";
		    statement = connection.prepareStatement(sql);
			statement.setString(1, email.toUpperCase());
			statement.setString(2, password);
			resultSet = statement.executeQuery();
		
			if(resultSet.next()) {
				return true;
			}	
		} catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
		} catch (ClassNotFoundException e) {
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
		return false;
	}
}