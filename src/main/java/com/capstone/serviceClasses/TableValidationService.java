package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.capstone.model.DatabaseConnection;

/**
 * Validates whether a table exists in the database.
 * Other service classes can call TableValidationService before running queries or updates to ensure the requested 
 * table is available.
 */
public class TableValidationService {

  /**
   * Validates whether the specified table exists in the database. (INCLIUDES ALL TABLES)
   * @param tableName the name of the table to validate
   * @throws SQLException if the table name is null, empty, or does not exist
   */
  public static void validateAnyTable(String table) throws SQLException {

    if (table == null || table.trim().isEmpty() || !table.matches("[A-Za-z][A-Za-z0-9_]*")) {
      throw new SQLException("Table name cannot be null or empty.");
    }

    try (Connection connection = DatabaseConnection.getConnection()) {

      // Retrieve metadata for checking tables in the current database.
      DatabaseMetaData metadata = connection.getMetaData();

      // Normalize the requested table name to uppercase for the metadata lookup.
      try (ResultSet tables = metadata.getTables(connection.getCatalog(), connection.getSchema(), table.toUpperCase(), new String[]{"TABLE"})) {
        
        if (!tables.next()) {
          throw new SQLException("Table does not exist: " + table);
        }
      }
    } catch (ClassNotFoundException e) {
      throw new SQLException("Unable to load the database driver.", e);
    }
  }
  

	/**
	 * Checks whether the supplied table name is one of the two permitted user tables: STUDENTS or FACULTY.
	 * Adding users can be stored only in one of the two tables.
	 * @param table the user table name to validate
	 * @throws SQLException if the table name is null or unsupported
	 */
	public static void validateUsersTables(String table) throws SQLException {
		if (table == null || (!"STUDENTS".equalsIgnoreCase(table) && !"FACULTY".equalsIgnoreCase(table))) {
		throw new SQLException("Invalid user table: " + table);
		}
	}


  /**  
  * Checks whether the supplied table name is an exam table
  */
  public static void validateExamTables(String table) throws SQLException{
    
    // Check if table exist
    validateAnyTable(table);
    
    Set<String> requiredColumns = Set.of("QID", "QUESTION", "OP1", "OP2","OP3", "OP4", "CORRECTANS");

    if (!hasRequiredColumns(table, requiredColumns)) {
      throw new SQLException("Invalid exam table: " + table);
    }
  }


  /**
  *  Checks whether the supplied table name is a grade table
  */
  public static void validateGradeTables(String table) throws SQLException{
    
    // Check if table exist
    validateAnyTable(table);
    
    if (!table.toUpperCase().endsWith("_GRADE")) {
      throw new SQLException("Invalid grade table: " + table);
    }

    Set<String> requiredColumns = Set.of("USERID", "GRADE", "EXAM", "LETTER", "SUBMISSION");

    if (!hasRequiredColumns(table, requiredColumns)) {
      throw new SQLException("Invalid grade table: " + table);
    }
  }



  /**
   * Checks whether a table contains every column in the supplied required-column set.
   *
   * Column names are read from JDBC metadata and normalized to uppercase before
   * comparison. Required column names should therefore also be supplied in
   * uppercase. The table may contain additional columns; this method checks only
   * that all required columns are present.
   *
   * @param tableName the table whose columns are to be inspected
   * @param requiredColumns the column names that must be present, in uppercase
   * @return {@code true} if all required columns are present; otherwise {@code false}
   * @throws SQLException if a database access error occurs or the driver cannot be loaded
   */
  private static boolean hasRequiredColumns(String tableName, Set<String> requiredColumns) throws SQLException {

    // Collect the table's column names so they can be compared with the required set.
    Set<String> actualColumns = new HashSet<>();

    try (Connection connection = DatabaseConnection.getConnection()) {
      
      // Use metadata from this connection to inspect the table in its current
      // catalog and schema.
      DatabaseMetaData metadata = connection.getMetaData();
      
      // Retrieve all columns for the specified table.
      try (ResultSet columns = metadata.getColumns(connection.getCatalog(), connection.getSchema(), tableName.toUpperCase(), null)) {

        // Normalize metadata names to uppercase so they match the required
        // column names, which are expected to use the same convention.
        while (columns.next()) {
          actualColumns.add(columns.getString("COLUMN_NAME").toUpperCase());
        }
      }
    } catch (ClassNotFoundException e) {
      throw new SQLException("Unable to load the database driver.", e);
    }

    // Extra table columns are allowed; return true only if none of the required columns are missing.
    return actualColumns.containsAll(requiredColumns);
  }

}