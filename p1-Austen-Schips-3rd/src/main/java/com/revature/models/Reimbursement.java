package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {
	private int reimbursementID;
	private double amount;
	private String description;
	private Blob reciept;
	private Timestamp dateSubmitted;
	private Timestamp dateResolved;
	private int authorID;
	private int resolverID;
	private int typeID;
	private int statusID;
	
	public Reimbursement(int reimbursementID, double amount, String description, Blob reciept, Timestamp dateSubmitted,
			Timestamp dateResolved, int authorID, int resolverID, int typeID, int statusID) {
		super();
		this.reimbursementID = reimbursementID;
		this.amount = amount;
		this.description = description;
		this.reciept = reciept;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.authorID = authorID;
		this.resolverID = resolverID;
		this.typeID = typeID;
		this.statusID = statusID;
	}
	
	public int getReimbursementID() {
		return reimbursementID;
	}
	public void setReimbursementID(int reimbursementID) {
		this.reimbursementID = reimbursementID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Blob getReciept() {
		return reciept;
	}
	public void setReciept(Blob reciept) {
		this.reciept = reciept;
	}
	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public Timestamp getDateResolved() {
		return dateResolved;
	}
	public void setDateResolved(Timestamp dateResolved) {
		this.dateResolved = dateResolved;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public int getResolverID() {
		return resolverID;
	}
	public void setResolverID(int resolverID) {
		this.resolverID = resolverID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementID=" + reimbursementID + ", amount=" + amount + ", reciept=" + reciept
				+ ", dateSubmitted=" + dateSubmitted + ", dateResolved=" + dateResolved + ", authorID=" + authorID
				+ ", resolverID=" + resolverID + ", typeID=" + typeID + ", statusID=" + statusID + "]";
	}
}