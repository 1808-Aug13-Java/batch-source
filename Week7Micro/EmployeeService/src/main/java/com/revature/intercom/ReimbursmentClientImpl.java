package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Reimbursment;

@Component
public class ReimbursmentClientImpl implements ReimbursmentClient {

	@Override
	public List<Reimbursment> getReimbursmentsByCustomerId(int id) {
		return null;
	}

}
