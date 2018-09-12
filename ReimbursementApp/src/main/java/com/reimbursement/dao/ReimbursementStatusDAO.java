package com.reimbursement.dao;

import java.util.List;

import com.reimbursement.model.ReimbursementStatus;

public interface ReimbursementStatusDAO {

	/**
     * Accepts the id of the reimbursement status and returns a java bean containing information about that status
     *
     * @param statusId The id of the status
     * @return A java bean containing information about the reimbursement status
     */
    ReimbursementStatus getReimbursementStatus(int statusId);

    /**
     * Returns a list of java beans which contains information about the reimbursement status
     *
     * @return A list of java beans which contains information about the reimbursement status
     */
    List<ReimbursementStatus> getReimbursementStatus();
}
	

