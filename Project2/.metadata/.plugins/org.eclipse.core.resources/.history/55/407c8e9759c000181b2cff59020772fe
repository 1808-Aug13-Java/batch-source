package com.revature.salutem.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.AccountIdNotFoundException;
import com.revature.salutem.models.Account;
import com.revature.salutem.services.AccountDaoImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountDaoImpl accServ;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts(){
		return accServ.getAllAccounts();
	}
	
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Account getAccountById(@PathVariable("id") int id)  {
		Account a = accServ.getAccountById(id);
		if(a == null) {
			throw new AccountIdNotFoundException();
		}
		return a;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Account createAccount(@RequestBody Account acc) {
		return accServ.createAccount(acc);
	}
	
	@PostMapping(value="/login",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Boolean verifyLogin(@RequestBody Account acc) {
		return accServ.verifyLogin(acc);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(@RequestBody Account acc) {
		return accServ.updateAccount(acc);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteAccount(@RequestBody Account acc) {
		accServ.deleteAccount(acc);
	}
	
	@DeleteMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteAccount(@PathVariable("id") int id) {
		accServ.deleteAccountById(id);
	}	

}
