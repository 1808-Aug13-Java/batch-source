package com.revature.intercom;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.models.Account;

@Component
public class AccountClientFallback implements AccountClient{

	@Override
	public List<Account> getAllaccounts(int customerId) {
		
		return null;
	}
	

}
