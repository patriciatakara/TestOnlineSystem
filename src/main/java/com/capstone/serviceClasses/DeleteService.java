package com.capstone.serviceClasses;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class DeleteService {
	
	/**
	 * This method deletes a user from the database. It then checks whether the table name is "FACULTY" or 
	 * "USER_REGISTRATION"(students) to use the proper column ID in the SQL query. 
	 * For faculty, it is "FACULTY ID", and for students is "USERID".
	 * @param tableName refers to the name of the exam/table containing students/faculty.
	 * @param id is the type of the ID. Students = USERID  faculty = FACULTYID.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public void deleteUser(String tableName, String id) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement statement = null;
		String columnId;
		
		if(tableName.equals("FACULTY")) {
			columnId = "FACULTYID";
		}else {
			columnId = "USERID";
		}
		
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "delete from " + tableName + " where " + columnId + "= ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
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
	}
		
	
	/**
	 * This method deletes a question from a specified exam. The question ID is used to specify which question is being deleted.
	 * @param tableName refers to the name of the exam in which the question is stored.
	 * @param id is the question ID.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public void deleteQuestion(String tableName, String id) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement statement = null;
	
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "delete from " + tableName + " where QID = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
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
	}		
		
	
	/**
	 * This method deletes a student's grade from a specified examination. 	
	 * @param tableName refers to the name of the exam.
	 * @param id is the student's ID. It is used to specify which student is having the grade deleted.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public void deleteGrade(String tableName, String id) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement statement = null;
	
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "delete from " + tableName + " where USERID =?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
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
	}
	
	
	/**
	 * This method deletes an whole exam from the database.
	 * @param exam is the name of the exam.
	 * @return a boolean value. True if the exam is deleted successfully. False if the exam is not deleted.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public boolean deleteExam(String exam) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		String tableName = exam;
		boolean success = false;

		try {
	        connection = DatabaseConnection.getConnection();
	        CallableStatement cs = connection.prepareCall("{  call SP_DELETETABLE(?, ?)  }");
	        cs.setString(1, tableName);
	        cs.registerOutParameter(2, Types.NUMERIC);
	        cs.execute();
	        success = cs.getInt(2) == 1;
	        cs.close();
	        
	    }catch (SQLException e) {
            e.printStackTrace();
            success = false;
         
	    }finally {
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return success;
	}
	
}