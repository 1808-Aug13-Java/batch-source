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

	private Logger logger = Logger.getLogger(AccountController.class.getName());
	
	private List<Account> accounts = new ArrayList<>();
	
	public AccountController() {
		accounts.add(new Account(1,1,792.90));
		accounts.add(new Account(2,2,893.61));
		accounts.add(new Account(3,3,179.51));
		accounts.add(new Account(4,1,53.34));
		accounts.add(new Account(5,3,792.90));
		accounts.add(new Account(6,2,893.61));
		accounts.add(new Account(7,2,179.51));
		accounts.add(new Account(8,2,279.34));
		accounts.add(new Account(9,1,792.90));
		accounts.add(new Account(10,2,893.61));
		accounts.add(new Account(11,3,179.51));
		accounts.add(new Account(12,4,279.34));
	}
	
	@GetMapping
	public List<Account> getAllAccounts() {
		logger.info("AccountController.getAllAccounts");
		return accounts;
	}
	
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable("id") int id) {
		logger.info("AccountController.findMessageById");
		return accounts.stream().filter(acc -> acc.getAccountId() == id).findFirst().get();
	}
	
	@GetMapping("/customers/{customerId}")
	public List<Account> getAccountsByCustomerId(@PathVariable("customerId") int id) {
		logger.info("AccountController.getAccountsByCustomerId");
		return accounts.stream().filter(acc -> acc.getCustomerId() == id).collect(Collectors.toList());
	}
	
	
	
}
