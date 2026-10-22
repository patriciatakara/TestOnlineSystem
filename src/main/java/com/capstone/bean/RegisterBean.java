package com.capstone.bean;
/**
 * @author Patricia Takara
 */
public class RegisterBean {
	
	protected int userId;
	protected String first;
	protected String last;
	protected String email;
	protected String password;
	
	/*
	 * Default constructor
	 */
	public RegisterBean() {
	}
	
	/**
	 * This constructor builds a new user with a specified id and name.
	 * @param userid is the student's ID.
	 * @param first is the student's first name.
	 * @param last is the student's last name.
	 */
	public RegisterBean(int userid, String first, String last) {
	}
	
	/**
	 * This constructor builds a new user with a specified name, email, and password.
	 * @param first is the student's first name.
	 * @param last is the student's last name.
	 * @param email is the student's email that will be used as the username for login.
	 * @param password is the student's password used for login.
	 */
	public RegisterBean(String first, String last, String email, String password) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFirst() {
	return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}