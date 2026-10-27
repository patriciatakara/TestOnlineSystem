package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.model.AuthenticationDAO;

/**
 *  Handles user authentication.
 */
public class AuthenticationService {
	
  private final AuthenticationDAO authenticationDAO = new AuthenticationDAO();

	/**
	 * This method authenticates a user by checking if the provided email and password exist in the database. 
	 * The method takes three parameters.
	 * @param email is the email of the user.
	 * @param password is the password of the user.
	 * @param table is the name of the database table to search for the user's information.
	 * @return a boolean value. If the authentication is successful it returns true, otherwise, it returns false.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public boolean authenticate(String email, String password, String table) throws SQLException, ClassNotFoundException {

  	// Check that the email and password were provided.
  	if (email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
      return false;
    }

		// Calls the DAO method to authenticate the user, removing any leading or trailing spaces from the email before passing the credentials and table name.
		return authenticationDAO.authenticate(email.trim(), password, table);
    }
		
}