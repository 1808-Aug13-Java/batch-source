package com.revature.intercom;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Reimbursement;
import java.util.List;

@FeignClient(name="reimbursement-service", fallback=ReimbursementClientFallback.class)
public interface ReimbursementClient {

  @GetMapping("reimbursements/employees/{username}")
  List<Reimbursement> getReimbursements(@PathVariable("username") String username);

}

