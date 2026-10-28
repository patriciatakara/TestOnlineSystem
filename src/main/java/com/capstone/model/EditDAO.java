
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.capstone.bean.NewExamBean;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for editing users, exam questions, and grades.
 * This class retrieves existing records and executes UPDATE statements.
 * It works with EditService, which handles the business logic. 
 */
public class EditDAO {

  /**
   * Opens a database connection.
   */
  private Connection getConnection() throws SQLException {
    try {
      return DatabaseConnection.getConnection();
    } catch (ClassNotFoundException e) {
      throw new SQLException("Unable to load the database driver.", e);
    }
  }

  /**
   * Retrieves the current information for a user.
   * @param tableName the database table containing the user.
   * @param userId the ID identifying the user.
   * @return a RegisterBean containing the user's current information.
   * @throws SQLException if the table is invalid, a database operation fails, or the user cannot be found.
   */
  public RegisterBean getUser(String tableName, String userId) throws SQLException {

    // Validates the table name before using it in the SQL query.
    // This helps prevent invalid table names from being used.
    TableValidationService.validateUsersTables(tableName);

    // Chooses the correct ID column based on the table name.
    // If the table is FACULTY, use FACULTYID. Otherwise, use USERID.
    String idColumn = "FACULTY".equalsIgnoreCase(tableName) ? "FACULTYID" : "USERID";

    // Builds a SELECT query to retrieve the user's first name,
    // last name, email, and password.
    // The table name and ID column are inserted into the SQL string.
    // The ? is a placeholder for the user's ID.
    String sql = "SELECT FIRSTNAME, LASTNAME, EMAIL, PASSWORD FROM " + tableName + " WHERE " + idColumn + " = ?";

    // Opens a database connection and prepares the SQL statement.
    try (Connection connection = getConnection();
   
    PreparedStatement statement = connection.prepareStatement(sql)) {
     
      // Replaces the first ? placeholder with the supplied user ID.
      // setString() passes the ID as a string value.
      statement.setString(1, userId);

      // Executes the SELECT query.
      // executeQuery() returns the matching rows in a ResultSet.
      // The ResultSet is also closed automatically when the block ends.
      //try-with-resources automatically closes both resources when finished.
      try (ResultSet resultSet = statement.executeQuery()) {
        if (!resultSet.next()) {
          throw new SQLException("User not found: " + userId);
        }
        
        // Creates a RegisterBean to hold the retrieved user data.
        RegisterBean user = new RegisterBean();
        
        // Reads data from the current database row and stores it in the bean.
        user.setFirst(resultSet.getString("FIRSTNAME"));
        user.setLast(resultSet.getString("LASTNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        
        // Returns the bean containing the user's current information to the calling method, usually EditService.
        return user;
      }
    }
  }


  /**
   * Updates a user's information.
   * @param tableName the database table containing the user.
   * @param userId the ID identifying the user.
   * @param user a RegisterBean containing the final values to save.
   * @return the number of rows updated.
   * @throws SQLException if a database operation fails.
   */
  public int updateUser(String tableName, String userId, RegisterBean user) throws SQLException {

    // Checks that the supplied table is an allowed user table.
    TableValidationService.validateUsersTables(tableName);

    // Selects the appropriate ID column for the user table.
    // FACULTY uses FACULTYID; other validated user tables use USERID.
    String idColumn = "FACULTY".equalsIgnoreCase(tableName) ? "FACULTYID" : "USERID";

    // Builds an UPDATE query to change the user's first name, last name, email, and password.
    // The final WHERE condition identifies which user to update.
    // The ? placeholders are filled in using PreparedStatement.
    String sql = "UPDATE " + tableName + " SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, PASSWORD = ?  WHERE " + idColumn + " = ?";

    // Opens the database connection and prepares the UPDATE statement.
    try (Connection connection = getConnection();

    // Set data to each ?
    PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, user.getFirst());
      statement.setString(2, user.getLast());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getPassword());
      statement.setString(5, userId);   // This ensures that the query targets the specified user.

      // Executes the UPDATE statement.
      // Returns the number of rows affected by the update.
      return statement.executeUpdate();
    }
  }


  /**
   * Retrieves the current information for an exam question.
   * @param tableName the exam table containing the question.
   * @param qid the ID identifying the question.
   * @return a NewExamBean containing the question information.
   * @throws SQLException if a database operation fails or the question cannot be found.
   */
  public NewExamBean getQuestion(String tableName, String qid) throws SQLException {

    // Validates that the supplied table is an allowed exam table.
    TableValidationService.validateExamTables(tableName);

    // Builds a SELECT query to retrieve the question text,
    // four answer options, and correct answer.
    // QID identifies the question to retrieve.
    String sql = "SELECT QUESTION, OP1, OP2, OP3, OP4, CORRECTANS FROM " + tableName + " WHERE QID = ?";

    // Opens a connection and prepares the SELECT statement.
    try (Connection connection = getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {

      // Replaces the first ? with the question ID.
      statement.setString(1, qid);

      // Executes the query and stores the results in a ResultSet.
      try (ResultSet resultSet = statement.executeQuery()) {
        // Moves to the first matching question. If no row exists, next() returns false.
        if (!resultSet.next()) {
          // Reports that the requested question was not found.
          throw new SQLException("Question not found: " + qid);
        }
        // Creates a bean to store the retrieved question data.
        NewExamBean question = new NewExamBean();

        // Retrieves the question data and stores it in the bean.
        question.setQtext(resultSet.getString("QUESTION"));
        question.setOpt1(resultSet.getString("OP1"));
        question.setOpt2(resultSet.getString("OP2"));
        question.setOpt3(resultSet.getString("OP3"));
        question.setOpt4(resultSet.getString("OP4"));
        question.setCorrectAnswer(resultSet.getString("CORRECTANS"));
        
        // Returns the bean containing the current question data.
        return question;
      }
    }
  }


  /**
   * Updates an exam question.
   * @param tableName the exam table containing the question.
   * @param qid the ID identifying the question.
   * @param question a NewExamBean containing the values to save.
   * @return the number of rows updated.
   * @throws SQLException if a database operation fails.
   */
  public int updateQuestion(String tableName, String qid, NewExamBean question) throws SQLException {

    // Validates the exam table name before using it in the query.
    TableValidationService.validateExamTables(tableName);

    // Builds an UPDATE query to change the question text, all four answer options, and the correct answer.
    // The WHERE clause ensures only the question with the given QID is targeted.
    String sql = "UPDATE " + tableName + " SET QUESTION = ?, OP1 = ?, OP2 = ?, OP3 = ?, OP4 = ?, CORRECTANS = ? WHERE QID = ?";

    // Opens the connection and prepares the UPDATE statement.
    try (Connection connection = getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {

      // Sets question data to each ?
      statement.setString(1, question.getQtext());
      statement.setString(2, question.getOpt1());
      statement.setString(3, question.getOpt2());
      statement.setString(4, question.getOpt3());
      statement.setString(5, question.getOpt4());
      statement.setString(6, question.getCorrectAnswer());
      statement.setString(7, qid);

      // Executes the UPDATE statement and returns the number of rows affected by the query.
      return statement.executeUpdate();
    }
  }


  /**
   * Stores the existing grade information needed by EditService.
   * This nested class represents a grade record containing the student's
   * ID, letter grade, and submission timestamp.
   */
  public static class GradeRecord {
    private final int userId;
    private final String letterGrade;
    private final String submission;

    /**
     * Creates a GradeRecord using the supplied values.
     * @param userId the student's ID.
     * @param letterGrade the student's letter grade.
     * @param submission the submission timestamp.
     */
    public GradeRecord(int userId, String letterGrade, String submission) {
      this.userId = userId;
      this.letterGrade = letterGrade;
      this.submission = submission; // timestamp
    }

    // Returns the stored student ID.
    public int getUserId() {
      return userId;
    }
    // Returns the stored letter grade.
    public String getLetterGrade() {
      return letterGrade;
    }
    // Returns the stored submission timestamp.
    public String getSubmission() {
      return submission;
    }
  }


  /**
   * Retrieves the existing grade details for a student.
   * @param tableName the exam grade table.
   * @param userId the student's ID.
   * @return a GradeRecord containing the student's ID, letter grade, and submission timestamp.
   * @throws SQLException if the table is invalid, a database operation fails, or the grade record cannot be found.
   */
  public GradeRecord getGrade(String tableName, int userId) throws SQLException {

    // Validates that the supplied table is an allowed grade table.
    TableValidationService.validateGradeTables(tableName);

    // Builds a query that retrieves the student's ID, letter grade and submission timestamp.
    // STUDENTS is given the alias S.
    // The exam grade table is given the alias G.
    // JOIN combines the student record with the matching grade record.
    // The ON condition matches their USERID values.
    // TO_CHAR converts the submission timestamp into a string formatted as year-month-day hour:minute:second.
    // The WHERE condition selects the requested student.
    String sql = "SELECT S.USERID, G.LETTER, TO_CHAR(G.SUBMISSION, 'YYYY-MM-DD HH24:MI:SS') AS SUBMISSION FROM STUDENTS S JOIN " + tableName + " G ON S.USERID = G.USERID WHERE S.USERID = ?";

     // Opens the database connection and prepares the SELECT query.
    try (Connection connection = getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {
      
      // Sets the first ? placeholder to the student's numeric ID.
      statement.setInt(1, userId);

      // Executes the query and retrieves its result set.
      try (ResultSet resultSet = statement.executeQuery()) {

        // Checks whether a matching grade record exists.
        if (!resultSet.next()) {
          // Reports that no grade record was found for this student.
          throw new SQLException("Grade record not found for user ID: " + userId);
        }
                
        // Creates and returns a GradeRecord using the values retrieved from the database.
        return new GradeRecord(
          resultSet.getInt("USERID"),
          resultSet.getString("LETTER"),
          resultSet.getString("SUBMISSION"));
      }
    }
  }


  /**
   * Updates a student's numeric grade, letter grade, and submission timestamp in the specified exam grade table.
   * @param tableName the name of the exam grade table.
   * @param userId the ID of the student whose grade will be updated.
   * @param grade the student's numeric grade.
   * @param letterGrade the student's letter grade.
   * @param submission the exam submission timestamp.
   * @return the number of rows updated in the database.
   * @throws SQLException if an error occurs while accessing the database.
   */
  public int updateGrade(String tableName, int userId, int grade, String letterGrade, String submission) throws SQLException {
  
    // Validates the table name to ensure it is an allowed grade table before using it in the SQL statement.
    TableValidationService.validateGradeTables(tableName);
    
    // Creates an SQL UPDATE statement to modify the student's numeric grade, letter grade, and submission timestamp.
    // The question marks are placeholders for the values supplied below.
    // TO_TIMESTAMP converts the submission string into an Oracle TIMESTAMP using the specified date and time format.
    // WHERE USERID = ? ensures that only the specified student's record is updated.
    String sql = "UPDATE " + tableName + " SET GRADE = ?, LETTER = ?, SUBMISSION = TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') WHERE USERID = ?";
  
    // Opens a database connection and prepares the SQL statement.
    // try-with-resources automatically closes both resources when finished.
    try (Connection connection = getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {
      
      // Sets grade's data.
      statement.setInt(1, grade);
      statement.setString(2, letterGrade);
      statement.setString(3, submission);
      statement.setInt(4, userId);
      
      // Executes the UPDATE statement and returns the number of rows affected by the database operation.
      return statement.executeUpdate();
    }
  }

}