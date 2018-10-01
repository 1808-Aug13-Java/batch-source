#!/usr/bin/env bash
mvn -f DiscoveryService/ spring-boot:run & > discovery.out &
mvn -f GatewayService/ spring-boot:run & > gateway.out &
mvn -f EmployeeService/ spring-boot:run & > employee.out &
mvn -f ReimbursementService/ spring-boot:run & > reimbursement.out &

