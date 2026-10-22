package com.capstone.serviceClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capstone.bean.RegisterBean;
import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class RegistrationService {
	
	/**
	 * This method checks if a user/student with the email provided exists in the database.
	 * @param email is the student's email.
	 * @param table is the name of the userTable it is referring to, STUDENTS or FACULTY. 
	 * @return a boolean value. True or value greater than zero if a user with the given email address exists, otherwise it is false.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database. 
	 */
	public Boolean checkExistUser(String email, String table) throws SQLException, ClassNotFoundException  {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String userTable = table;
		
		try {	
			connection = DatabaseConnection.getConnection();
			String sql = "select count(*) from " + userTable + " where EMAIL = ?";
			statement = connection.prepareStatement(sql);
		    statement.setString(1, email.toUpperCase());
		    resultSet =  statement.executeQuery();  
		    
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
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
		return false;
	}
	
	
	/**
	 * This method adds a new user/student to the database.
	 * @param rb is a RegisterBean object containing the user's first name, last name, email address, and password.
	 * @param table is the name of the userTable it is referring to, STUDENTS or FACULTY.
	 * @return a boolean value. True or value is greater than zero if the user is successfully added to the database.
	 *  Otherwise, it returns false.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database. 
	 */
	public static boolean addUser(RegisterBean rb, String table) throws SQLException, ClassNotFoundException  {
		
		Connection connection = null;
		PreparedStatement statement = null;
		String userTable = table;
	
		try{
			connection = DatabaseConnection.getConnection();
			String sql;
			//String sql = "insert into " + userTable + " (USERID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD) values(?,?,?,?)";


			

			if ("STUDENTS".equalsIgnoreCase(userTable)) {
    		sql = "INSERT INTO STUDENTS " + "(USERID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD) " + "VALUES (USERID_SEQ.NEXTVAL, ?, ?, ?, ?)";
			} else if ("FACULTY".equalsIgnoreCase(userTable)) {
				sql = "INSERT INTO FACULTY " + "(FACULTYID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD) " + "VALUES (FACULTID_SEQ.NEXTVAL, ?, ?, ?, ?)";
			} else {
					throw new SQLException("Invalid user table: " + userTable);
			}




			statement = connection.prepareStatement(sql);
			
			statement.setString(1, rb.getFirst().toUpperCase());
			statement.setString(2, rb.getLast().toUpperCase());
			statement.setString(3, rb.getEmail().toUpperCase());
			statement.setString(4, rb.getPassword());

			int rowsInserted = statement.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
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