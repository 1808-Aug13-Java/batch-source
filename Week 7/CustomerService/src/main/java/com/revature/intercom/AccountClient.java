package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Account;

@FeignClient(name="account-service", fallback=AccountClientFallback.class)
//registry is fetched from eureka so it knows what service this is
public interface AccountClient {
	//we will use feign client which has a method that we do not need to implement 
	//but will make a call to another service
	
	@GetMapping("accounts/customers/{customerId}")
	List<Account> getAllaccounts(@PathVariable("customerId") int customerId);

}
