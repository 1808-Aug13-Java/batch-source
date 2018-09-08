/*
    Create statements for project 1
*/
CREATE TABLE MANAGER(
    MAN_ID NUMBER(6) CONSTRAINT PK_MAN_ID PRIMARY KEY,
    NAME VARCHAR2(150),
	PSWRD VARCHAR2(255)
);

CREATE TABLE REIMBURSMENT(
    REIMB_ID NUMBER(5) CONSTRAINT PK_REIM PRIMARY KEY,
    EMP_ID NUMBER(6),
    STATUS VARCHAR2(25),
    DESCRIPTION VARCHAR2(255),
    RESOLVED_BY NUMBER(6)
);

CREATE TABLE EMPLOYEE(
    EMP_ID NUMBER(6) CONSTRAINT PK_EMP PRIMARY KEY,
    FNAME VARCHAR2(150),
    LNAME VARCHAR(150),
    MAN_ID NUMBER(6),
    PSWRD VARCHAR2(255)
);

-- ADD RELATIONSHIPS BETWEEN TABLES
ALTER TABLE REIMBURSMENT 
ADD CONSTRAINT FK_EMP_REIMB_ID FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE;

ALTER TABLE EMPLOYEE 
ADD CONSTRAINT FK_MAN_ID FOREIGN KEY (MAN_ID) REFERENCES MANAGER;

--TRIGGERS FOR PRIMARY KEYS
--EMPLOYEE
CREATE SEQUENCE SQ_EMP_ID
START WITH 100
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_EMP_ID
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMP_ID.NEXTVAL INTO :NEW.EMP_ID FROM DUAL;
END;
/
 --MANAGER
CREATE SEQUENCE SQ_MAN_ID
START WITH 100
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_MAN_ID
BEFORE INSERT ON MANAGER
FOR EACH ROW
BEGIN
    SELECT SQ_MAN_ID.NEXTVAL INTO :NEW.MAN_ID FROM DUAL;
END;
/

--REIMBURSMENT
CREATE OR REPLACE SEQUENCE SQ_RIEMB_ID
START WITH 100
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_RIEMB_ID
BEFORE INSERT ON REIMBURSMENT
FOR EACH ROW
BEGIN
    SELECT SQ_RIEMB_ID.NEXTVAL INTO :NEW.REIMB_ID FROM DUAL;
END;
/


--TEST DATA: MANAGERS
insert into MANAGER (FNAME, LNAME) values ('Francesca', 'McKeggie');
insert into MANAGER (FNAME, LNAME) values ('Marchall', 'McKeaveney');
insert into MANAGER (FNAME, LNAME) values ('Arron', 'Picker');
insert into MANAGER (FNAME, LNAME) values ('Kalila', 'Waistell');
insert into MANAGER (FNAME, LNAME) values ('Cointon', 'Piperley');
insert into MANAGER (FNAME, LNAME) values ('Mollee', 'Eatherton');
insert into MANAGER (FNAME, LNAME) values ('Thornton', 'Lawland');
insert into MANAGER (FNAME, LNAME) values ('Shermy', 'Snedker');
insert into MANAGER (FNAME, LNAME) values ('Merla', 'Midlar');
insert into MANAGER (FNAME, LNAME) values ('Waylan', 'Davidek');

--TEST DATA: EMPLOYEE
insert into EMPLOYEE (FNAME, LNAME, MAN_ID, PSWRD) values ('Lindsey', 'Gilluley', 100, 'Bryum gemmiparum De Not.');
insert into EMPLOYEE (FNAME, LNAME, MAN_ID, PSWRD) values ('Cecelia', 'Woodroffe', 101, 'Vernonia pulchella Small');
insert into EMPLOYEE (FNAME, LNAME, MAN_ID, PSWRD) values ('Brandea', 'McSperron', 102, 'Echinocereus engelmannii');
insert into EMPLOYEE (FNAME, LNAME, MAN_ID, PSWRD) values ('Owen', 'Templeton', 103, 'Triplaris Loefl. ex L.');
insert into EMPLOYEE (FNAME, LNAME, MAN_ID, PSWRD) values ('Tracie', 'Runchman', 104, 'Randia parvifolia Lam.');


--TEST DATA REIMBURSMENT
insert into REIMBURSMENT (EMP_ID, STATUS, DESCRIPTION) values (100, 'pending', 'luctus rutrum nulla tellus in');
insert into REIMBURSMENT (EMP_ID, STATUS, DESCRIPTION) values (101, 'pending', 'ante ipsum primis');
insert into REIMBURSMENT (EMP_ID, STATUS, DESCRIPTION, RESOLVED_BY) values (101, 'resolved', 'nam nulla integer pede justo lacinia eget tincidunt eget tempus vel', 101);
