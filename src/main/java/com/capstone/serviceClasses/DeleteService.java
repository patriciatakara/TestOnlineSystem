package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.model.DeleteDAO;
/**
 * @author Patricia Takara
 */
public class DeleteService {
	
	  // Create an object to access deletion database operations.
    private final DeleteDAO deleteDAO = new DeleteDAO();

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
		// Delegate the deletion to the DAO.
  	deleteDAO.deleteUser(tableName, id);
	}
		
	
	/**
	 * This method deletes a question from a specified exam. The question ID is used to specify which question is being deleted.
	 * @param tableName refers to the name of the exam in which the question is stored.
	 * @param id is the question ID.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public void deleteQuestion(String tableName, String id) throws SQLException, ClassNotFoundException {
		// Delegate the deletion to the DAO.
    deleteDAO.deleteQuestion(tableName, id);
	}		
		
	
	/**
	 * This method deletes a student's grade from a specified examination. 	
	 * @param tableName refers to the name of the exam.
	 * @param id is the student's ID. It is used to specify which student is having the grade deleted.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public void deleteGrade(String tableName, String id) throws SQLException, ClassNotFoundException {
    // Delegate the deletion to the DAO.
    deleteDAO.deleteGrade(tableName, id);
	}
	
	
	/**
	 * This method deletes an whole exam from the database.
	 * @param exam is the name of the exam.
	 * @return a boolean value. True if the exam is deleted successfully. False if the exam is not deleted.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public boolean deleteExam(String exam) throws SQLException, ClassNotFoundException {
		try {
			// Delegate the deletion to the DAO and return its result.
			return deleteDAO.deleteExam(exam);
		} catch (SQLException e) {
			// Preserve the original behavior by returning false on SQL errors.
			e.printStackTrace();
			return false;
	}
	}

}