package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.bean.RegisterBean;
import com.capstone.model.RegistrationDAO;

/**
 * Provides registration operations for students and faculty members.
 */
public class RegistrationService {
	
	private static final RegistrationDAO registrationDAO = new RegistrationDAO();

	/**
	 * This method checks if a user/student with the email provided exists in the database.
	 * @param email is the student's email.
	 * @param table is the name of the userTable it is referring to, STUDENTS or FACULTY. 
	 * @return a boolean value. True or value greater than zero if a user with the given email address exists, otherwise it is false.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database. 
	 */
	public Boolean checkExistUser(String email, String table) throws SQLException, ClassNotFoundException{
		
		// Validate the target table before attempting registration.
		// if table is invalid, it throws an exception and let the user know. The next statements are skipped.
		TableValidationService.validateUsersTables(table);

		if (email == null || email.trim().isEmpty()) {
			return false;
		}
		return registrationDAO.checkExistUser(email.trim(), table.toUpperCase());
	}


	/**
	 * Registers a new student or faculty member.
	 * @param user is the user information being added to the database.
	 * @param table is the name of the userTable it is referring to, STUDENTS or FACULTY. 
	 * @return True, if the user was added successfully; otherwise, false.
	 */
	public static boolean addUser(RegisterBean user, String table) throws SQLException, ClassNotFoundException {

		// Validate the target table before attempting registration.
		// if table is invalid, it throws an exception and let the user know. The next statements are skipped.
		TableValidationService.validateUsersTables(table);

		// Check whether the user object is null before accessing its data.
		if (user == null) {
			// Throw an exception if no user information was provided.
			throw new IllegalArgumentException("User information cannot be null.");
		}

		// Convert the table name to uppercase and call the DAO.
    // Insert the user's information into the database.
		return registrationDAO.addUser(user, table.toUpperCase());
	}
	
}