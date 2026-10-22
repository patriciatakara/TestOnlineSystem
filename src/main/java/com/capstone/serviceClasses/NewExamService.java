package com.capstone.serviceClasses;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class NewExamService {

	/**
	 * This method checks if an exam name already exists on the database. If there is a table with the same name as the examName 
	 * parameter, then it will return 1, otherwise, it will return 0.
	 * @param examName is the name of the new exam. 
	 * @return the count of tables with the same name as the examName variable value.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public int checkExamName(String examName) throws SQLException {
	
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String tableName = examName.toUpperCase();
		int count=0;

		try {
		    connection = DatabaseConnection.getConnection();
		    String sql = "select count(*) from ALL_TABLES WHERE TABLE_NAME = '" +tableName+ "'" ;
		 	statement = connection.prepareStatement(sql);
			resultSet= statement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		
		}catch (SQLException | ClassNotFoundException e) {
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
		return count;
	}

	
	/**
	 * This method creates a new exam/table on the database with the given name, by calling the stored procedure sp_newTable using 
	 * a CallableStatement. The CallableStatement sets the input parameter to the given name and executes the stored procedure. 
	 * If the stored procedure does not return any result, the method retrieves the update count, which should be 1 if the table was 
	 * created successfully. The method then closes the CallableStatement and returns the update count.
	 * @param name is the exam name entered by the instructor.
	 * @return an integer number representing if the exam/table was created successfully or not.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public int createNewTable(String name) throws SQLException, ClassNotFoundException {
		
	    Connection connection = null;
	    String examName = name;
	    int updateCount = 0;
	    
	    try {
	        connection = DatabaseConnection.getConnection();
	        CallableStatement cs = connection.prepareCall("{ call sp_newTable(?) }");
	        cs.setString(1, examName.toUpperCase());
	        boolean hasResult = cs.execute();
	        
	        if (!hasResult) {
	            updateCount = cs.getUpdateCount();
	        }
	        cs.close();
	        
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return updateCount;
	}

}