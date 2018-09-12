package com.reimbursement.dao;

import javax.naming.AuthenticationException;

import com.reimbursement.model.User;

public interface UserDAO {

	 /**
     * Accepts the user's username and retrieves a jave bean containing data about user from the database.
     *
     * @param username The user's username
     * @return A java bean containing information about the user
     * @throws AuthenticationException If the user's username isn't found in the database,
     *                                 an AuthenticationException is thrown
     */
    User getByUsername(String username) throws AuthenticationException;

    /**
     * Accepts the user's user id and retrieves a java bean containing data about user from the database.
     *
     * @param userId The user's id
     * @return A bean containing the user's information
     * @throws AuthenticationException If the user's id isn't found in the database, an
     *                                 authentication exception is thrown.
     */
    User getByUserId(int userId) throws AuthenticationException;
}
	

