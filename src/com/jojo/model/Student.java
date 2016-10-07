package com.jojo.model;

public class Student {

	private int studentID;
	private String studentName;
	private String className;
	private String major;
	private String department;
	private int userID;

	public Student() {
		super();
	}

	public Student(int studentID, String studentName, String className, String major, String department, int userID) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.className = className;
		this.major = major;
		this.department = department;
		this.userID = userID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
