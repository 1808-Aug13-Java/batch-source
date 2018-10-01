#!/usr/bin/env bash
mvn -f DiscoveryService/ install 
mvn -f GatewayService/ install
mvn -f EmployeeService/ install
mvn -f ReimbursementService/ install

