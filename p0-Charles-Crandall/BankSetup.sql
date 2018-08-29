DROP USER banker CASCADE;
DROP TABLE ACC_TRANSACTION;
DROP TABLE CUST_ACCOUNT;
DROP TABLE CUSTOMER;

/*******************************************************************************
   Create database
********************************************************************************/


CREATE USER banker
IDENTIFIED BY Log1nNumb3r
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to banker;
GRANT resource to banker;
GRANT create session TO banker;
GRANT create table TO banker;
GRANT create view TO banker;

conn banker/Log1nNumb3r

/*******************************************************************************
   Create tables
********************************************************************************/

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

/*******************************************************************************
   Create Foreign Keys
********************************************************************************/
ALTER TABLE CUST_ACCOUNT ADD CONSTRAINT FK_ACCOUNT_CUSTID
    FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER (CUST_ID)  
    on delete cascade;
    
ALTER TABLE ACC_TRANSACTION ADD CONSTRAINT FK_FROM_ACCOUNT
    FOREIGN KEY (ACC_FROM) REFERENCES CUST_ACCOUNT (ACC_ID)  
    on delete cascade;
    

/*******************************************************************************
   Default values and sequences
********************************************************************************/
insert into CUSTOMER values (0,'DUMMY_IO_TYPE','NONE','NONE');
insert into CUST_ACCOUNT values (0,0,0,0.00);

create sequence SQ_CUSTOMER_ID
START WITH 1
INCREMENT BY 1;

create sequence SQ_ACCOUNT_ID
START WITH 1
INCREMENT BY 1;

create sequence SQ_TRANSACTION_ID
START WITH 1
INCREMENT BY 1;


/*******************************************************************************
   FINAL RESORT DO NOT USE UNDER MANY CIRCUMSTANCES!!!
********************************************************************************/
create or replace procedure THE_PURGE
is
BEGIN
    delete from CUSTOMER;
    insert into CUSTOMER values (0,'DUMMY_IO_TYPE','NONE','NONE');
    insert into CUST_ACCOUNT values (0,0,0,0.00);
    commit;
END;
/

begin THE_PURGE(); end;

commit;
exit;