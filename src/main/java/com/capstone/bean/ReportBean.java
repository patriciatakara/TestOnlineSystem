package com.capstone.bean;
/**
 * @author Patricia Takara
 */
public class ReportBean extends RegisterBean {
	
	protected int grade;
	protected String examName;
	protected String letterGrade;
	protected String submissionDate;
	
	/**
	 * Default constructor
	 */
	public ReportBean() {
	}
	
	/**
	 * This constructor builds a new grade report containing the exam's name and the student's name, ID, and grade.
	 * @param examName is the exam name.
	 * @param userid is the student's ID.
	 * @param first is the student's first name.
	 * @param last is the student's last name.
	 * @param grade is the student's grade from the exam.
	 * @param letterGrade is the student's letter grade (A, B, C, D, E, F).
	 * @param submission is the exam's submission date.
	 */
	public ReportBean (String examName, int userid, String first, String last, int grade, String letterGrade, String submission) {
		super(userid,first, last);
		this.userId = userid;
		this.first = first;
		this.last = last;
		this.grade = grade;
		this.letterGrade = letterGrade;
		this.examName = examName;
		this.submissionDate = submission;
	}
	
	public ReportBean (String examName, int userid, String first, String last, int grade, String letterGrade) {
		super(userid,first, last);
		this.userId = userid;
		this.first = first;
		this.last = last;
		this.grade = grade;
		this.letterGrade = letterGrade;
		this.examName = examName;
	}

	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}


	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getLetterGrade() {
		return letterGrade;
	}
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}		
}