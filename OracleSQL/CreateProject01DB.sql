
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

-- ACTUALLY IMPORTANT INSERTS
insert into Reimbursement_Status values(1, 'Pending');
insert into Reimbursement_Status values(2, 'Approved');
insert into Reimbursement_Status values(3, 'Denied');


-- Test Data
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


-- Test Data
insert into manager values (5, 1);
insert into manager values (5, 2);
insert into manager values (4, 1);
insert into manager values (4, 3);

-- Test Data
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (50,2,3,50,DATE '2017-10-24','ultricies ligula. Nullam enim.',4,'Vestibulum ante ipsum primis',DATE '2019-07-29');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (51,2,1,51,DATE '2019-08-25','ac arcu. Nunc mauris.',4,'justo nec ante. Maecenas',DATE '2017-12-10');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (52,10,2,52,DATE '2019-07-17','nunc id enim. Curabitur',4,'Etiam bibendum fermentum metus.',DATE '2018-06-19');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (53,2,2,53,DATE '2019-01-15','urna convallis erat, eget',4,'cursus vestibulum. Mauris magna.',DATE '2018-03-20');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (54,1,2,54,DATE '2019-09-06','sit amet massa. Quisque',5,'risus varius orci, in',DATE '2018-10-30');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (55,9,3,55,DATE '2018-11-28','dapibus quam quis diam.',4,'non, feugiat nec, diam.',DATE '2018-01-02');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (56,6,1,56,DATE '2017-09-20','in faucibus orci luctus',5,'neque. Nullam nisl. Maecenas',DATE '2018-01-18');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (57,8,2,57,DATE '2017-10-05','ac urna. Ut tincidunt',4,'amet, consectetuer adipiscing elit.',DATE '2018-04-04');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (58,3,3,58,DATE '2019-08-04','eget laoreet posuere, enim',5,'non, egestas a, dui.',DATE '2018-09-16');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (59,7,1,59,DATE '2019-07-18','orci quis lectus. Nullam',4,'at auctor ullamcorper, nisl',DATE '2019-03-20');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (60,7,2,60,DATE '2019-04-11','sit amet, faucibus ut,',5,'enim consequat purus. Maecenas',DATE '2019-06-26');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (61,2,1,61,DATE '2018-08-28','interdum. Curabitur dictum. Phasellus',4,'erat. Etiam vestibulum massa',DATE '2018-11-29');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (62,1,1,62,DATE '2018-06-18','ut dolor dapibus gravida.',4,'nascetur ridiculus mus. Proin',DATE '2019-03-01');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (63,9,1,63,DATE '2018-05-28','eget tincidunt dui augue',4,'dictum sapien. Aenean massa.',DATE '2018-01-16');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (64,8,2,64,DATE '2018-05-22','tincidunt congue turpis. In',5,'penatibus et magnis dis',DATE '2019-03-19');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (65,8,1,65,DATE '2017-09-11','dolor dapibus gravida. Aliquam',4,'ligula. Donec luctus aliquet',DATE '2019-06-28');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (66,7,3,66,DATE '2018-02-19','consectetuer adipiscing elit. Curabitur',5,'Nulla tempor augue ac',DATE '2018-11-07');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (67,7,1,67,DATE '2018-09-26','facilisis eget, ipsum. Donec',5,'dui augue eu tellus.',DATE '2018-04-28');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (68,10,1,68,DATE '2018-02-10','rutrum non, hendrerit id,',4,'Phasellus elit pede, malesuada',DATE '2018-12-11');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (69,6,1,69,DATE '2018-10-03','habitant morbi tristique senectus',5,'amet ante. Vivamus non',DATE '2018-06-14');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (70,4,1,70,DATE '2017-11-07','ac urna. Ut tincidunt',4,'porttitor scelerisque neque. Nullam',DATE '2017-09-28');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (71,6,1,71,DATE '2018-02-19','urna convallis erat, eget',5,'elit pede, malesuada vel,',DATE '2019-05-23');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (72,2,1,72,DATE '2019-01-06','felis, adipiscing fringilla, porttitor',5,'Cum sociis natoque penatibus',DATE '2018-06-29');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (73,3,3,73,DATE '2019-06-15','porttitor vulputate, posuere vulputate,',4,'elit. Curabitur sed tortor.',DATE '2018-04-11');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (74,2,1,74,DATE '2019-01-20','neque. Nullam nisl. Maecenas',4,'ligula. Nullam enim. Sed',DATE '2018-08-16');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (75,1,1,75,DATE '2018-02-21','consectetuer mauris id sapien.',4,'ut quam vel sapien',DATE '2019-03-03');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (76,7,3,76,DATE '2019-07-10','in, cursus et, eros.',5,'mauris ipsum porta elit,',DATE '2019-05-28');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (77,8,3,77,DATE '2019-03-06','turpis. In condimentum. Donec',4,'malesuada fringilla est. Mauris',DATE '2019-01-02');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (78,8,2,78,DATE '2019-06-08','et, eros. Proin ultrices.',4,'dolor. Quisque tincidunt pede',DATE '2018-10-22');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (79,4,3,79,DATE '2018-02-13','at lacus. Quisque purus',4,'id, erat. Etiam vestibulum',DATE '2018-09-12');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (80,1,3,80,DATE '2019-04-25','vel arcu. Curabitur ut',5,'velit. Cras lorem lorem,',DATE '2019-04-05');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (81,7,1,81,DATE '2018-02-10','urna et arcu imperdiet',5,'ac libero nec ligula',DATE '2018-11-15');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (82,2,3,82,DATE '2019-07-09','commodo ipsum. Suspendisse non',5,'Nullam enim. Sed nulla',DATE '2019-07-24');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (83,7,1,83,DATE '2019-04-15','malesuada vel, venenatis vel,',5,'Nulla dignissim. Maecenas ornare',DATE '2018-01-01');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (84,4,3,84,DATE '2018-12-05','viverra. Maecenas iaculis aliquet',5,'turpis. In condimentum. Donec',DATE '2019-01-05');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (85,4,3,85,DATE '2018-07-11','congue turpis. In condimentum.',5,'eu, euismod ac, fermentum',DATE '2018-03-16');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (86,4,1,86,DATE '2017-10-15','blandit at, nisi. Cum',5,'a, magna. Lorem ipsum',DATE '2018-07-21');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (87,8,2,87,DATE '2019-03-16','sapien. Nunc pulvinar arcu',5,'egestas blandit. Nam nulla',DATE '2018-07-25');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (88,3,3,88,DATE '2019-03-29','mauris ipsum porta elit,',5,'non enim. Mauris quis',DATE '2019-06-20');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (89,8,2,89,DATE '2018-06-27','morbi tristique senectus et',5,'Cras lorem lorem, luctus',DATE '2018-05-06');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (90,8,1,90,DATE '2019-06-15','fringilla est. Mauris eu',5,'congue. In scelerisque scelerisque',DATE '2019-07-19');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (91,7,1,91,DATE '2019-06-13','mi felis, adipiscing fringilla,',5,'consequat nec, mollis vitae,',DATE '2017-10-20');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (92,1,1,92,DATE '2018-02-17','non, lacinia at, iaculis',4,'eleifend, nunc risus varius',DATE '2018-03-25');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (93,5,2,93,DATE '2017-09-12','mauris sapien, cursus in,',4,'ultrices a, auctor non,',DATE '2019-08-10');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (94,4,1,94,DATE '2018-03-11','sapien. Aenean massa. Integer',4,'Duis a mi fringilla',DATE '2018-03-13');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (95,7,3,95,DATE '2019-02-15','tellus faucibus leo, in',4,'bibendum ullamcorper. Duis cursus,',DATE '2018-06-08');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (96,7,2,96,DATE '2019-07-20','rhoncus. Proin nisl sem,',5,'convallis, ante lectus convallis',DATE '2018-03-16');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (97,8,3,97,DATE '2017-11-23','auctor. Mauris vel turpis.',4,'convallis dolor. Quisque tincidunt',DATE '2018-09-18');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (98,3,2,98,DATE '2017-09-19','Nam interdum enim non',4,'velit eget laoreet posuere,',DATE '2017-11-24');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (99,4,3,99,DATE '2017-09-22','mauris sit amet lorem',5,'tellus eu augue porttitor',DATE '2018-05-08');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (100,8,1,100,DATE '2017-11-21','magna. Cras convallis convallis',5,'vel turpis. Aliquam adipiscing',DATE '2018-11-23');
INSERT INTO Reimbursement (rem_id,requester_id,status,amount,submit_date,descr,resolved_by,reason,resolve_date) VALUES (100,8,1,100,null,null,null,null,null);

