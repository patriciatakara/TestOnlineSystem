package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.bean.NewExamBean;
import com.capstone.bean.RegisterBean;
import com.capstone.model.EditDAO;

/**
 * Handles business logic for editing users, exam questions, and grades.
 * The service retrieves existing information, determines which values.
 * should be updated, and delegates database operations to EditDAO.
 */
public class EditService {
	
	private final EditDAO editDAO = new EditDAO();
	
	/**
   * Checks whether a string is null, empty, or contains only whitespace.
   * @param value the string to check.
   * @return true if the string is null, empty, or contains only whitespace; otherwise, returns false.
	*/
	 private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}


	/**
	 * Returns the submitted value if it is not blank. Otherwise, it keeps the existing value.
	 * @param submitted the new value submitted by the user.
   * @param existing the current value stored in the database.
   * @return the submitted value if it is not blank; otherwise, returns the existing value.
	 */
	private String keepExistingIfBlank(String submitted, String existing) {
		return isBlank(submitted) ? existing : submitted;
	}


	/**
 	 * Updates a user's information in the database. Blank fields retain their existing values.
 	 * @param user a RegisterBean containing the submitted user information.
 	 * @param table the database table containing the user.
 	 * @param userId the ID identifying the user to update.
 	 * @return the number of database rows updated.
	 * @throws SQLException if a database operation fails.
 	 */
	public int updateUser(RegisterBean user, String table, String userId) throws SQLException {
		
		// Checks whether the RegisterBean object itself is null.
		if (user == null) {
			throw new IllegalArgumentException("User information cannot be null.");
		}

		// Retrieve the user's current information from the database.
		// The DAO performs the database query using the table and user ID.
		RegisterBean existingUser = editDAO.getUser(table, userId);
		
		// Create a bean to store the updated user information.
		// that will be sent to the DAO for updating.
		RegisterBean updatedUser = new RegisterBean();

		// Uses the submitted first name if it is not blank.
    // Otherwise, keeps the existing first name.
		String first = keepExistingIfBlank(user.getFirst(), existingUser.getFirst());
		
    // Uses the submitted last name if it is not blank.
    // Otherwise, keeps the existing last name.		
		String last = keepExistingIfBlank(user.getLast(), existingUser.getLast());
		
		// Uses the submitted email if it is not blank.
    // Otherwise, keeps the existing email.
		String email = keepExistingIfBlank(user.getEmail(), existingUser.getEmail());
		
    // Uses the submitted password if it is not blank.
    // Otherwise, keeps the existing password.
		String password = keepExistingIfBlank(user.getPassword(), existingUser.getPassword());

		// Convert the first name, last name, and email to uppercase.
		updatedUser.setFirst(first.toUpperCase());
		updatedUser.setLast(last.toUpperCase());
		updatedUser.setEmail(email.toUpperCase());

		// Stores the final password without changing its letter case.
		// Passwords are generally case-sensitive.
		updatedUser.setPassword(password);

	  //  Sends the table name, user ID, and updated information to the DAO.
    // The DAO executes the database UPDATE statement.
    // Returns the number of rows updated by that statement.
		return editDAO.updateUser(table, userId, updatedUser);
	}

	
	/**
	 * Updates a question in a specified exam table. Blank fields retain their existing values.
	 * @param table the exam table containing the question
	 * @param qid the ID identifying the question
	 * @param question a NewExamBean containing the submitted question information
	 * @return the number of database rows updated
	 * @throws SQLException if a database operation fails
	 */
	public int updateQuestion(String table, String qid, NewExamBean question) throws SQLException {
		
		// Checks whether the RegisterBean object itself is null.
		if (question == null) {
			throw new IllegalArgumentException("Question information cannot be null.");
		}

		// Retrieves the current question from the specified exam table.
    // The DAO uses the table name and question ID to find it.
		NewExamBean existingQuestion = editDAO.getQuestion(table, qid);

		// Creates a new bean to hold the final question values
    // that will be sent to the database.
		NewExamBean updatedQuestion = new NewExamBean();

		// Updates the question text if a nonblank value was submitted.
    // Otherwise, keeps the existing question text.
		updatedQuestion.setQtext(keepExistingIfBlank(question.getQtext(), existingQuestion.getQtext()));

  	// Updates the first answer option if it is not blank.
    // Otherwise, keeps the existing first option.
		updatedQuestion.setOpt1(keepExistingIfBlank(question.getOpt1(), existingQuestion.getOpt1()));

		// Updates the second answer option if it is not blank.
    // Otherwise, keeps the existing second option.
		updatedQuestion.setOpt2(keepExistingIfBlank(question.getOpt2(), existingQuestion.getOpt2()));

    // Updates the third answer option if it is not blank.
    // Otherwise, keeps the existing third option.
		updatedQuestion.setOpt3(keepExistingIfBlank(question.getOpt3(), existingQuestion.getOpt3()));

  	// Updates the fourth answer option if it is not blank.
   	// Otherwise, keeps the existing fourth option.
		updatedQuestion.setOpt4(keepExistingIfBlank(question.getOpt4(), existingQuestion.getOpt4()));

    // Updates the correct answer if it is not blank.
    // Otherwise, keeps the existing correct answer.
		updatedQuestion.setCorrectAnswer(keepExistingIfBlank(question.getCorrectAnswer(),existingQuestion.getCorrectAnswer()));

		// Sends the exam table, question ID, and final question values
    // to the DAO, which performs the database UPDATE.
    // Returns the number of rows updated.
		return editDAO.updateQuestion(table, qid, updatedQuestion);
}


	/**
	 * Updates a student's grade for a specified exam.
	 * @param table the exam grade table containing the student's grade
	 * @param userId the ID identifying the student
	 * @param first the student's first name
	 * @param last the student's last name
	 * @param grade the student's numeric grade
	 * @param letterGrade the student's letter grade
	 * @param submission the exam submission date
	 * @return the number of database rows updated
	 * @throws SQLException if a database operation fails
	 */
	public int updateGrade(String table, int userId, String first, String last, int grade, String letterGrade, String submission) throws SQLException {
		
		// Retrieves the student's existing grade record from the database.
    // The table identifies the exam grade table, and userId identifie the student whose grade should be retrieved.
		EditDAO.GradeRecord existingGrade = editDAO.getGrade(table, userId);

    // Uses the submitted letter grade if it is not blank.
    // Otherwise, keeps the existing letter grade.
		String updatedLetterGrade = keepExistingIfBlank(letterGrade, existingGrade.getLetterGrade());

		// Uses the submitted submission date if it is not blank.
  	// Otherwise, keeps the existing submission date.
		String updatedSubmission = keepExistingIfBlank(submission, existingGrade.getSubmission());

		// Grade 0 is treated as a valid grade, not as a blank field.
		// Passes the table, existing student's ID, numeric grade,
		// final letter grade, and final submission date to the DAO.
		// The DAO executes the database UPDATE statement.
		// Returns the number of rows updated.
		return editDAO.updateGrade(table, existingGrade.getUserId(), grade, updatedLetterGrade, updatedSubmission);
	}

}