package com.revature.model;

public class Request {

	private int ERS_REQ_ID;
	private double ERS_AMOUNT;
	private int ERS_STATUS;
	private int ERS_EMP_ID;
	private String ERS_MAN_USERNAME;
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Request(int eRS_REQ_ID, double eRS_AMOUNT, int eRS_STATUS, int eRS_EMP_ID, String eRS_MAN_USERNAME) {
		super();
		ERS_REQ_ID = eRS_REQ_ID;
		ERS_AMOUNT = eRS_AMOUNT;
		ERS_STATUS = eRS_STATUS;
		ERS_EMP_ID = eRS_EMP_ID;
		ERS_MAN_USERNAME = eRS_MAN_USERNAME;
	}
	public int getERS_REQ_ID() {
		return ERS_REQ_ID;
	}
	public void setERS_REQ_ID(int eRS_REQ_ID) {
		ERS_REQ_ID = eRS_REQ_ID;
	}
	public double getERS_AMOUNT() {
		return ERS_AMOUNT;
	}
	public void setERS_AMOUNT(double eRS_AMOUNT) {
		ERS_AMOUNT = eRS_AMOUNT;
	}
	public int getERS_STATUS() {
		return ERS_STATUS;
	}
	public void setERS_STATUS(int eRS_STATUS) {
		ERS_STATUS = eRS_STATUS;
	}
	public int getERS_EMP_ID() {
		return ERS_EMP_ID;
	}
	public void setERS_EMP_ID(int eRS_EMP_ID) {
		ERS_EMP_ID = eRS_EMP_ID;
	}
	public String getERS_MAN_USERNAME() {
		return ERS_MAN_USERNAME;
	}
	public void setERS_MAN_USERNAME(String eRS_MAN_USERNAME) {
		ERS_MAN_USERNAME = eRS_MAN_USERNAME;
	}
	@Override
	public String toString() {
		return "Request [ERS_REQ_ID=" + ERS_REQ_ID + ", ERS_AMOUNT=" + ERS_AMOUNT + ", ERS_STATUS=" + ERS_STATUS
				+ ", ERS_EMP_ID=" + ERS_EMP_ID + ", ERS_MAN_USERNAME=" + ERS_MAN_USERNAME + "]";
	}
	
	
}
