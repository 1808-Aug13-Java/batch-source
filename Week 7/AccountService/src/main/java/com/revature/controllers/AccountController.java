package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private Logger logger =Logger.getLogger(AccountController.class.getName());
	
	private List<Account> accounts= new ArrayList<>();
	
	
	public AccountController() {
		accounts.add(new Account(1,2,300.89));
		accounts.add(new Account(2,2,300.89));
		
	}
	
	
	@GetMapping
	public List<Account> getAllAccounts(){
		logger.info("AccountController.getAllAccounts()");
		return accounts;
	}
	
	
	@GetMapping(value="/{accountId}")
	public Account getAccountById(@PathVariable ("acountId")int accountId) {
			logger.info("finding account by id");
			for (Account a: accounts) {
				if(accountId== a.getAccountId()) {
					return a;
				}
			}
			return null;
	}
 

@GetMapping(value="/customers/{customerId}")
public List<Account> getAccountsByCustomerId(@PathVariable("customerId")int customerId){
	logger.info("find account by customer id");
	return accounts.stream().filter(acct -> acct.getCustomerId() == customerId).collect(Collectors.toList());
}
}
