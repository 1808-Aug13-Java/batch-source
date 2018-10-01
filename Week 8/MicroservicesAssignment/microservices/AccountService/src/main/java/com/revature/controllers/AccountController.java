package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private Logger logger = Logger.getLogger(AccountController.class.getName());
	
	private List<Account> accounts = new ArrayList<>();
	
	public AccountController() {
		accounts.add(new Account(1,1,79.32));
		accounts.add(new Account(2,1,196.97));
		accounts.add(new Account(3,1,224.78));
		accounts.add(new Account(4,2,57368.76));
		accounts.add(new Account(5,3,340.22));
		accounts.add(new Account(6,3,12.09));
		accounts.add(new Account(7,4,762.14));
		accounts.add(new Account(8,4,90.99));
		
	}
	
	@GetMapping
	public List<Account> getAllAccounts(){
		logger.info("AccountController.getAllAccounts()");
		return accounts;
	}
	
	@GetMapping(value="/{accountId}")
	public Account getAccountById(@PathVariable("accountId") int accountId) {
		logger.info("finding account by id");
		for(Account a: accounts) {
			if(accountId == a.getAccountId()) {
				return a;
			}
		}
		return null;
	}
	
	@GetMapping(value="/customers/{customerId}")
	public List<Account> getAccountsByCustomerId(@PathVariable("customerId") int customerId){
		logger.info("finding accounts by customer");
		return accounts.stream().filter(acct -> acct.getCustomerId() == customerId).collect(Collectors.toList());
	}

}