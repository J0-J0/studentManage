package com.jojo.model;

public class Manager {

	private int id;
	private String managerName;
	private String password;

	public Manager() {
		super();
	}

	public Manager(String managerName, String password) {
		super();
		this.managerName = managerName;
		this.password = password;
	}

	public Manager(int id, String managerName, String password) {
		super();
		this.id = id;
		this.managerName = managerName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
