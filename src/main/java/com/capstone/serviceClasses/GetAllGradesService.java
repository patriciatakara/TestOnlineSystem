package com.capstone.serviceClasses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.ReportBean;
import com.capstone.model.DatabaseConnection;
/**
 * @author Patricia Takara
 */
public class GetAllGradesService {
	
	/**
	 * This method retrieves only the names of all tables containing an exam's grades. 
	 * Each table contains the grade of all the students that took the same exam.
	 * @return an ArrayList of Strings that contains the names of all exam's grades tables retrieved from the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public ArrayList<String> getAllTablesGrades() throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> tableGrades = new ArrayList<>();
		String tableName = " ";
			 
		try {
			connection = DatabaseConnection.getConnection();
			String sql = "select * from ALL_TABLESGRADES order by TABLE_NAME asc";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
				     
			while(resultSet.next()) {
			tableName = resultSet.getString("table_name");	
			tableGrades.add(tableName);
			}   
		} catch (SQLException | ClassNotFoundException e) {
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
		return tableGrades;
	}

	
	/**
	 * This method retrieves the grades of all students that took the same exam. 
	 * @param exam is the name of the exam/table in the database.
	 * @return an ArrayList of ReportBean objects. Each object contains information about a student's performance in a specific exam.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public ArrayList<ReportBean> getGrades(String exam) throws SQLException, ClassNotFoundException {
		
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    ArrayList<ReportBean> reports = new ArrayList<>();
	    
	    try {
	        connection = DatabaseConnection.getConnection();
	        String sql = "select USERID, FIRSTNAME, LASTNAME, GRADE, LETTER, SUBMISSION from STUDENTS natural join " + exam + " ORDER BY userid ASC";
	        statement = connection.prepareStatement(sql);
	        resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	        	ReportBean reportB = new ReportBean();
	            reportB.setExamName(exam);
	            reportB.setUserId(resultSet.getInt("USERID"));
	            reportB.setFirst(resultSet.getString("FIRSTNAME"));
	            reportB.setLast(resultSet.getString("LASTNAME"));
	            reportB.setGrade(resultSet.getInt("GRADE"));
	            reportB.setLetterGrade(resultSet.getString("LETTER"));
	            reportB.setSubmissionDate(resultSet.getString("SUBMISSION"));
	            reports.add(reportB);
	        }  
	    } catch (SQLException | ClassNotFoundException e) {
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
	    return reports;
	}
	
	
	/**
	 * This method retrieves the grades of a specified student with the given ID. It fetches grades from all the exams that were taken.
	 * @param allTables is an ArrayList containing all the tables' grades from the exams. A loop will be used to fetch the grade from each
	 * exam the student took.
	 * @param id is the student ID.
	 * @return an ArrayList of ReportBean objects that contain information about the grades of a student.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
    public ArrayList<ReportBean> getGradesStudent(ArrayList<String> allTables, int id) throws SQLException, ClassNotFoundException {
    	
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<ReportBean> grades = new ArrayList<>();
        
        try {
            connection = DatabaseConnection.getConnection(); 
            for (int i = 0; i <allTables.size(); i++) {
                String table = allTables.get(i);
                 
                String sql ="select USERID, FIRSTNAME, LASTNAME, GRADE, LETTER, EXAM, SUBMISSION from STUDENTS natural join " + table + " where USERID = " + id + " order by USERID asc";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery(); 
                
                while (resultSet.next()) {
                    ReportBean grade = new ReportBean();
                    grade.setExamName(allTables.get(i));
                    grade.setUserId(resultSet.getInt("USERID"));
                    grade.setFirst(resultSet.getString("FIRSTNAME"));
                    grade.setLast(resultSet.getString("LASTNAME"));
                    grade.setGrade(resultSet.getInt("GRADE"));
                    grade.setLetterGrade(resultSet.getString("LETTER"));
                    grade.setSubmissionDate(resultSet.getString("SUBMISSION"));
                    grade.setExamName(resultSet.getString("EXAM"));
                    grades.add(grade);
                } 
            }    
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
        return grades;
    }

}
