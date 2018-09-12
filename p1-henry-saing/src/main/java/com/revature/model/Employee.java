package com.revature.model;

public class Employee {
	private int ERS_EMP_ID;
	private String ERS_EMP_NAME;
	private String ERS_EMP_USERNAME;
	private String ERS_EMP_PASS;
	private String ERS_EMP_EMAIL;
	
	public Employee(int eRS_EMP_ID, String eRS_EMP_NAME, String eRS_EMP_USERNAME, String eRS_EMP_PASS,
			String eRS_EMP_EMAIL) {
		super();
		ERS_EMP_ID = eRS_EMP_ID;
		ERS_EMP_NAME = eRS_EMP_NAME;
		ERS_EMP_USERNAME = eRS_EMP_USERNAME;
		ERS_EMP_PASS = eRS_EMP_PASS;
		ERS_EMP_EMAIL = eRS_EMP_EMAIL;
	}

	public int getERS_EMP_ID() {
		return ERS_EMP_ID;
	}

	public void setERS_EMP_ID(int eRS_EMP_ID) {
		ERS_EMP_ID = eRS_EMP_ID;
	}

	public String getERS_EMP_NAME() {
		return ERS_EMP_NAME;
	}

	public void setERS_EMP_NAME(String eRS_EMP_NAME) {
		ERS_EMP_NAME = eRS_EMP_NAME;
	}

	public String getERS_EMP_USERNAME() {
		return ERS_EMP_USERNAME;
	}

	public void setERS_EMP_USERNAME(String eRS_EMP_USERNAME) {
		ERS_EMP_USERNAME = eRS_EMP_USERNAME;
	}

	public String getERS_EMP_PASS() {
		return ERS_EMP_PASS;
	}

	public void setERS_EMP_PASS(String eRS_EMP_PASS) {
		ERS_EMP_PASS = eRS_EMP_PASS;
	}

	public String getERS_EMP_EMAIL() {
		return ERS_EMP_EMAIL;
	}

	public void setERS_EMP_EMAIL(String eRS_EMP_EMAIL) {
		ERS_EMP_EMAIL = eRS_EMP_EMAIL;
	}

	@Override
	public String toString() {
		return "Employee [ERS_EMP_ID=" + ERS_EMP_ID + ", ERS_EMP_NAME=" + ERS_EMP_NAME + ", ERS_EMP_USERNAME="
				+ ERS_EMP_USERNAME + ", ERS_EMP_PASS=" + ERS_EMP_PASS + ", ERS_EMP_EMAIL=" + ERS_EMP_EMAIL + "]";
	}
	
	
}
