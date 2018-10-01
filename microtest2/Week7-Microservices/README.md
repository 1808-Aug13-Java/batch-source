Make a microservice ecosystem which consists of four services:
Discovery Service
Gateway Service
Employee Service
Reimbursement Service

Gateway, Employee, and Reimbursement services should be registered with eureka
Use gateway service to route requests to employee and reimbursements accordingly
Use feign client to display a single employee’s reimbursement when retrieving their information
Provide fallback implementation for the reimbursement service client and use circuit breaking to improve fault tolerance

