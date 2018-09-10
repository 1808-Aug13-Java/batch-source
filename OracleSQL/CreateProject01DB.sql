
-- Create a table that specifies employees
CREATE TABLE Employee(
    emp_id  NUMBER  CONSTRAINT PK_EMPLOYEE  PRIMARY KEY,
    emp_name  varchar(70),
    emp_username  varchar(100)  CONSTRAINT UNIQUE_NOT_NULL_USERNAME UNIQUE NOT NULL,
    emp_password  char(64)  CONSTRAINT NOT_NULL_PASSWORD NOT NULL
);

-- Create the table that tells which employees manage other employees
CREATE TABLE Manager(
    manager_id  NUMBER,
    emp_id  NUMBER,
    CONSTRAINT  PK_MANAGER  PRIMARY KEY (manager_id, emp_id),
    CONSTRAINT FK_MANAGER_MANAGER  FOREIGN KEY (manager_id) REFERENCES Employee(emp_id),
    CONSTRAINT FK_MANAGER_EMPLOYEE FOREIGN KEY (emp_id) REFERENCES Employee(emp_id)
);


CREATE TABLE Reimbursement_Status(
    status_number  NUMBER(4)  CONSTRAINT PK_REIMBURSEMENT_STATUS  PRIMARY KEY,
    status_string  VARCHAR(30)  NOT NULL
);


CREATE TABLE Reimbursement(
    rem_id  NUMBER  CONSTRAINT PK_REIMBURSEMENT  PRIMARY KEY,
    requester_id  NUMBER   NOT NULL,
    status  NUMBER(4)  NOT NULL, 
    amount  NUMBER  NOT NULL,
    submit_date  DATE,
    descr  VARCHAR2(254), 
    resolved_by  NUMBER,
    reason  VARCHAR2(254), 
    resolve_date  DATE,
    CONSTRAINT FK_REM_TO_EMP  FOREIGN KEY (requester_id) REFERENCES Employee(emp_id), 
    CONSTRAINT FK_REM_TO_REM_STAT  FOREIGN KEY (status) REFERENCES Reimbursement_Status(status_number), 
    CONSTRAINT FK_REM_TO_MNGR  FOREIGN KEY (resolved_by) REFERENCES Employee(emp_id)
);




-- Create a sequence for the auto generation of reimbursement keys
CREATE SEQUENCE SEQ_REM_ID 
START WITH 1
INCREMENT BY 1;

-- Create a sequence for the auto generation of employee keys
CREATE SEQUENCE SQ_EMP_ID 
START WITH 1
INCREMENT BY 1;


-- Create a trigger to apply an auto generated reimbursement key in oracle
CREATE OR REPLACE TRIGGER TR_REM_INSERT
BEFORE INSERT ON workDB.Reimbursement
FOR EACH ROW
BEGIN
    SELECT workDB.SEQ_REM_ID.nextVal INTO :NEW.rem_id FROM DUAL;
END;


-- Create a trigger to apply an auto generated employee key in oracle
CREATE OR REPLACE TRIGGER TR_EMP_INSERT
BEFORE INSERT ON workDB.Employee
FOR EACH ROW
BEGIN
    SELECT workDB.SQ_EMP_ID.nextVal INTO :NEW.emp_id FROM DUAL;
END;


insert into employee (emp_id, emp_name, emp_username, emp_password) values (1, 'Janella Sutcliffe', 'jsutcliffe0', '4a03a956885c9fcc5f34f5282de4fd70b096aa16c7366747f58f02b95f476afd');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (2, 'Ruthi Nehl', 'rnehl1', '4588c0dff8722587eceac68325bc16419fa463ff297a9375ddeb59a04eadd71d');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (3, 'Athene Abella', 'aabella2', '29a6060ad7e7f9a68a062af08e3b1256c75e8f1747e1135f0f3d4c922263c4b0');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (4, 'Josh Munro', 'jmunro3', '4d5946081a2bea6c240927eb4438933e30451a191054350d5473c460e97ff68f');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (5, 'Von Gergely', 'vgergely4', '092b81b5934a6d1affdbf6cfd655185bf884b16451ff90a1c240d61db2d4d72c');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (6, 'Liva Beament', 'lbeament5', 'a14dca904aa9b9bdbba4324d0114c917844a441be10b7cf6ed57b9bebe6f3324');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (7, 'Adrien Carnachen', 'acarnachen6', '8ccc26923b6ba8410d3b225cf04a2614f195a3fc3f101057f5b28d4520a2ba91');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (8, 'Clyde Magee', 'cmagee7', '15ef201ac4a0b8dfd5a745598c6a994bf2b157125f5961d9da95f9f754d0991c');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (9, 'Stanislas Newark', 'snewark8', '77f1df8d2ac2e1bf59f737beb053861e5c777b00f9304380ead8868d8f77fb06');
insert into employee (emp_id, emp_name, emp_username, emp_password) values (10, 'Chrissy Kavanagh', 'ckavanagh9', '12a18c0a57abc2d7076280a7c5e4452b066cc01bb9eb39fb373d4e17f0557da6');



insert into manager values (5, 1);
insert into manager values (5, 2);
insert into manager values (4, 1);
insert into manager values (4, 3);

