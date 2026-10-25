package com.capstone.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Patricia Takara
 */
public class DatabaseConnection {

	/**
	 *  This method uses the Oracle JDBC driver to connect to the database with the specified URL, username, and password. 
	 *  The URL specifies the database server name, port number, and database instance name. The username and password are used for
	 *  authentication to access the database.
	 * @return A connection object to an Oracle Database 
	 * @throws SQLException for when there is a problem with the database connection. 
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String driver = "oracle.jdbc.driver.OracleDriver";
	    String dbURL = "jdbc:oracle:thin:@//oracle:1521/FREEPDB1";
	    String username = System.getenv("DB_username"); // Get username from an environment variable.(.env)
	    String password = System.getenv("DB_password"); // Get password from an environment variable.(.env)

	    Class.forName(driver);
	    Connection connection = DriverManager.getConnection(dbURL, username, password);
	    return connection;
	}
}