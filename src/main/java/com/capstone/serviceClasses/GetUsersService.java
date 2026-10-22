package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.RegisterBean;
import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class GetUsersService {
	
	/**
	 * This method retrieves information about all the students in the database.
	 * @return an ArrayList of RegisterBean type containing all the students' names, userID, email, and password.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	 public ArrayList<RegisterBean> getStudents() throws SQLException, ClassNotFoundException {
		 
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null; 
		ArrayList<RegisterBean> students = new ArrayList<>();
		
		try { 
			 connection = DatabaseConnection.getConnection();	 
			 String sql = "select * from STUDENTS order by USERID ASC";
			 statement = connection.prepareStatement(sql);			 
			 resultSet = statement.executeQuery();
			   
			 while (resultSet.next()) {
				 RegisterBean user = new RegisterBean();
				 user.setUserId(resultSet.getInt("USERID"));
				 user.setFirst(resultSet.getString("FIRSTNAME"));
				 user.setLast(resultSet.getString("LASTNAME"));
				 user.setEmail(resultSet.getString("EMAIL"));
				 user.setPassword(resultSet.getString("PASSWORD"));
				 students.add(user);
			 }
			 
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
			
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
		return students;
	 }
	 
	 
	 /**
	  * This method retrieves information about all the faculty members in the database.
	  * @return an ArrayList of type RegisterBean containing all the faculty member's ID, name, email, and password.
	  * @throws SQLException for errors occurring while accessing the database.
	  * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	  */
	 public ArrayList<RegisterBean> getFaculty() throws SQLException, ClassNotFoundException {
		 
		 Connection connection = null;
		 PreparedStatement statement = null;
		 ResultSet resultSet = null;
		 ArrayList<RegisterBean> faculty = new ArrayList<>();
		 
		 try { 
			 connection = DatabaseConnection.getConnection();	 
			 String sql = "select * from FACULTY order by FACULTYID ASC";
			 statement = connection.prepareStatement(sql);			 
			 resultSet = statement.executeQuery();
			   
			 while (resultSet.next()) {
				 RegisterBean user = new RegisterBean();
				 user.setUserId(resultSet.getInt("FACULTYID"));
				 user.setFirst(resultSet.getString("FIRSTNAME"));
				 user.setLast(resultSet.getString("LASTNAME"));
				 user.setEmail(resultSet.getString("EMAIL"));
				 user.setPassword(resultSet.getString("PASSWORD"));
				 faculty.add(user);
			 }
			 
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
			
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
		return faculty;
	 }	 	 
	 
}