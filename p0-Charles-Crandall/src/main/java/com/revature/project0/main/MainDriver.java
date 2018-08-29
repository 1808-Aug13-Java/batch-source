package com.revature.project0.main;

import com.revature.project0.drivers.MenuDriver;

public class MainDriver {

	public static void main(String[] args) {
		MenuDriver.initialize();
	}
}

/* 
The following are the user stories that must be fulfilled for a minimal viable product (MVP). As a user on the system, I can:

	1) create a user account with a unique email and/or username
2) secure my account using a password
	3) log in/log out
	4) create a bank account associated with each user
	5) deposit money
	6) withdraw money (no negative balances!)
	7) view account balance

Your application must be able to accommodate multiple users. Keep in mind input validation (Can I deposit -$10? Can I deposit $.005?) and exception handling. Also keep in mind modularity and organization of code (How much code is in your main method? How much are you using methods to reduce your code?).

Your bank can optionally support:
 Multiple user bank accounts (checking & savings)
Ability to reset password or update account information
Transfer funds functionality between user accounts
Joint accounts (a single account with two separate users having access)
Ability to view transaction history



create table CUSTOMER(
    CUST_ID number not null,
    USERNAME varchar(20) unique not null,
    PASS varchar(20) not null,
    ADDRESS varchar(50),
    constraint PK_CUSTOMER primary key (CUST_ID)
);

create table CUST_ACCOUNT(
    ACC_ID number not null,
    CUST_ID number not null,
    ACC_TYPE number not null, --0 in/out dummy, 1 savings, 2 checking
    BALANCE number(9,2),
    constraint PK_ACCOUNT primary key (ACC_ID)
);

create table ACC_TRANSACTION(
    TRANS_ID number not null,
    TRANS_TYPE char(4) not null, --WITH, DEPT
    ACC_FROM number not null,
    ACC_TO number not null,
    AMOUNT number(9,2)
);
*/
