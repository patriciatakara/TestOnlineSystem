package com.capstone.serviceClasses;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.model.GetAllExamsDAO;

/**
 * Handles service operations for retrieving exams and questions.
 */
public class GetAllExamsService {
	
	private final GetAllExamsDAO getAllExamsDAO = new GetAllExamsDAO();

	 /**
	  * This method retrieves the names of exams in the database and returns them as an ArrayList of strings called "exams." (RETURN EXAM NAMES AS STRINGS)
	  * @return an ArrayList of Strings, where each String is the name of a table/exam in the database. 
	  * @throws SQLException for errors occurring while accessing the database.
	  * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	  */
	 public ArrayList<String> getAllExams() throws SQLException, ClassNotFoundException {
		 
		 return getAllExamsDAO.getAllExams();
	 }
	 
	 
	 /**
	   * This method retrieves the names of exams on the database and returns them as an ArrayList of NewExamBean type.
	   * The return type is NewExamBean, not String like the previous method. The exams' names returned on this method * are used to be displayed directly on a jsp page.  (RETURN EXAM NAMES PACKAGED INTO BEANS)
	   * @return an NewExamBean object containing the names of each table/exam on the database. 
	   * @throws SQLException  for errors occurring while accessing the database.
	   * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	   */
	  public ArrayList<NewExamBean> getExamsName() throws SQLException, ClassNotFoundException {
			
			// Retrieve exam names before converting them into beans for the JSP.
			ArrayList<String> examNames = getAllExams();
			
			// Prepare a list to hold the exam beans for the JSP.
			ArrayList<NewExamBean> exams = new ArrayList<>();

			// Convert each exam name into a NewExamBean object.
			for (String examName : examNames) {
				NewExamBean exam = new NewExamBean();
				exam.setTableName(examName);
				exams.add(exam);
			}
			return exams;
		}
	 
	 
	//  /**
	//   * This method retrieves the content of all the exams stored in a database. It takes an ArrayList of exam names as input. (RETURN QUESTIONS FROM MULTIPLE EXAMES)
	// 	* It uses the list of exam names returned by getExamsName() to create beans for each exam. So JSP pages can access properties using the getters methods. 
	//   * @param exams is an ArrayList containing all the exams in the database. It will be used for iteration. 
	//   * @return an ArrayList of NewExamBean objects containing all the questions, multiple choices, and correct answers from all exams in the database.
	//   * @throws SQLException for errors occurring while accessing the database.
	//   * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	//   */
	//   public ArrayList <NewExamBean> getQuestionsFromAllExams(ArrayList<String> exams) throws SQLException, ClassNotFoundException {
	//       if (exams == null) {
  //   			throw new IllegalArgumentException("Exam list cannot be null.");
  //       }
  //       return getAllExamsDAO.getQuestionsFromAllExams(exams);
  //   }
	  
	  
	  /**
	   * This method retrieves all the questions, multiple choices, and correct answers from a single exam/table in the database. (RETURN QUESTIONS FROM ONE EXAM)
	   * @param examSelected is a String containing the name of the exam table from which to retrieve the questions.
	   * @return all the content of an exam in the form of an ArrayList of NewExam objects.
	   * @throws SQLException for errors occurring while accessing the database.
	   * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	   */
	  public ArrayList <NewExamBean> getQuestionsFromAnExam(String examSelected) throws SQLException, ClassNotFoundException {
	       
			return getAllExamsDAO.getQuestionsFromAnExam(examSelected);
	}

}