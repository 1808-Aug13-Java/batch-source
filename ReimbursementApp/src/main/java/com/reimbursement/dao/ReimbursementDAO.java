package com.reimbursement.dao;

import java.util.List;

import javax.naming.AuthenticationException;

import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;

public interface ReimbursementDAO {


	    /**
	     * Insert.
	     *
	     * @param reimbursement the reimbursement
	     */
	    void insert(Reimbursement reimbursement);

	    /**
	     * Gets reimbursements.
	     *
	     * @return the reimbursements
	     * @throws AuthenticationException the authentication exception
	     */
	    List<Reimbursement> getReimbursements() throws AuthenticationException;


	    /**
	     * Accepts a user's username and returns all the reimbursements that user submitted
	     *
	     * @param username The username of the user that submitted the reimbursements
	     * @return A list of java beans that describe the user's reimbursement
	     * @throws AuthenticationException If the username isn't found within the database,
	     *                                 an AuthenticationException is thrown
	     */
	    List<Reimbursement> getReimbursements(String username) throws AuthenticationException;


	    /**
	     * Returns a new Reimbursement java bean using the arguments provided.
	     *
	     * @param username    The user's username
	     * @param amount      The amount of money that is being asked to be reimbursed.
	     * @param description A description of what the user is being asked to be reimbursed for
	     * @param receipt     A receipt of the reimbursement
	     * @param status      The status of the reimbursement
	     * @param typeId      The id of the type of the reimbursement.
	     * @return The newly created reimbursement
	     * @throws AuthenticationException If the username can't be found in the database,
	     *                                 an authentication exception is thrown
	     */
	    Reimbursement createNewReimbursement(String username, double amount, String description, ReimbursementStatus statusId) throws AuthenticationException;

	    /**
	     * Changes the status of a user's reimbursement
	     *
	     * @param reimbursementId       The id of the reimbursement that will be changed
	     * @param reimbursementStatusId The id of the status that the user's reimbursement will be changed to
	     * @param username              The username of the user that submitted the reimbursement
	     * @throws AuthenticationException Throws an AuthenticationException if the user isn't found in the database
	     */
	    void changeStatus(int reimbursementId, int reimbursementStatusId, String username) throws AuthenticationException;
	}
	

