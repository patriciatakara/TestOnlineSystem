package com.capstone.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for deleting users, questions, grades, and exams.
 */
public class DeleteDAO {

  /**
   * Deletes a student or faculty member from the database.
   */
  public void deleteUser(String tableName, String id) throws SQLException, ClassNotFoundException {

    // Validate the user table name before using it in the DELETE query.
    TableValidationService.validateUsersTables(tableName);

    //Determine which ID column belongs to the selected user table.
    String columnId;
    if ("STUDENTS".equalsIgnoreCase(tableName)) {
        columnId = "USERID";
    } else {
        columnId = "FACULTYID";
    }

    // Build the SQL query using the validated table and ID column.
    String sql = "DELETE FROM " + tableName + " WHERE " + columnId + " = ?";

    // Open the connection and prepare the query.
    try (Connection connection = DatabaseConnection.getConnection();

    PreparedStatement statement = connection.prepareStatement(sql)) {

      // Set the ID of the user to delete.
      statement.setString(1, id);
      // Execute the DELETE query.
      statement.executeUpdate();
    }
  }


  /**
   * Deletes a question from the specified exam table.
   */
  public void deleteQuestion(String tableName, String id) throws SQLException, ClassNotFoundException {

    // Validate the exam table name before including it in SQL.
    TableValidationService.validateExamTables(tableName);

    // Build the SQL query to delete the question by its QID.
    String sql = "DELETE FROM " + tableName + " WHERE QID = ?";

    // Open the connection and prepare the query.
    try (Connection connection = DatabaseConnection.getConnection();
  
    PreparedStatement statement = connection.prepareStatement(sql)) {

      // Set the ID of the question to delete.
      statement.setString(1, id);
      // Execute the DELETE query.
      statement.executeUpdate();
    }
  }


  /**
   * Deletes a student's grade from the specified grade table.
   */
  public void deleteGrade(String tableName, String id) throws SQLException, ClassNotFoundException {

  // Validate the exam table name before including it in SQL.
    TableValidationService.validateGradeTables(tableName);

    // Build the SQL query to delete the grade by student ID.
    String sql = "DELETE FROM " + tableName + " WHERE USERID = ?";

    // Open the connection and prepare the query.
    try (Connection connection = DatabaseConnection.getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {

      // Set the student's ID.
      statement.setString(1, id);
      // Execute the DELETE query.
      statement.executeUpdate();
    }
  }


  /**
   * Deletes an exam table by calling the SP_DELETETABLE stored procedure.
   *
   * @return true if the stored procedure reports success; otherwise false.
   */
  public boolean deleteExam(String tableName) throws SQLException, ClassNotFoundException {

    // Validate the exam name before passing it to the stored procedure.
    // Validate the exam table name before including it in SQL.
    TableValidationService.validateExamTables(tableName);

    // Open the connection and prepare the stored procedure call.
    try (Connection connection = DatabaseConnection.getConnection();
    
    CallableStatement statement = connection.prepareCall("{ call SP_DELETETABLE(?, ?) }")) {

      // Pass the exam table name to the stored procedure.
      statement.setString(1, tableName);

      // Register the second parameter as the output value.
      statement.registerOutParameter(2, Types.NUMERIC);

      // Execute the stored procedure.
      statement.execute();

      // Return true if the procedure reports a result of 1.
      return statement.getInt(2) == 1;
    }
  }

}