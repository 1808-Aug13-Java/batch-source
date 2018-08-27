-- we can create a comment like this
/*

This file is all DDL.

Run script runs the whole thing
Run statement runs the statement
Can highlight and run selected statements

-- can also declare DEPT_ID completely different from the table creation
-- don't need CONSTRAINT PK_DEPT but we just want to give it a name pk_dept

-- varchar and varchar2 are the same, but varchar2 is reccommended because
-- Oracle claims that at some point varchar will become obsolete

-- so the highest budget we can have is 9999999.99
*/

----------------------------------
--USING DDL TO CREATE OUR TABLES
----------------------------------
CREATE TABLE DEPARTMENT (
    DEPT_ID NUMBER(5) CONSTRAINT PK_DEPT PRIMARY KEY, 
    DEPT_NAME VARCHAR2(50),
    MONTHLY_BUDGET NUMBER(7,2)
);

CREATE TABLE EMPLOYEE (
    EMP_ID NUMBER(5) CONSTRAINT PK_EMP PRIMARY KEY,
    EXP_NAME VARCHAR2(50),
    BIRTHDAY DATE,
    MONTHLY_SALARY NUMBER(7,5) NOT NULL,
    HIRE_DATE DATE,
    POSITION VARCHAR2(20),
    MANAGER_ID NUMBER(5),
    DEPT_ID NUMBER(5) CONSTRAINT FK_EMP_DPT REFERENCES DEPARTMENT
);

-- We can't first insert to our employee table with a department without first defining the department
--INSERT INTO EMPLOYEE VALUES (1, 'JOHN SMITH', DATE '1989-01-05', 2000, DATE '2015-03-08', 'MK_REP', NULL, 1);

-- INSERTING RECORDS INTO OUR DEPARTMENT TABLE
INSERT INTO DEPARTMENT VALUES(1, 'MARKETING', 5000);
INSERT INTO DEPARTMENT VALUES(2, 'ACCOUNTING', 4000);
INSERT INTO DEPARTMENT VALUES(3, 'INFORMATION TECHNOLOGY', 4500);
INSERT INTO DEPARTMENT VALUES(4, 'HUMAN RESOURCES', 3500);
INSERT INTO DEPARTMENT VALUES(5, 'LEGAL', 2000);
INSERT INTO DEPARTMENT VALUES(6, 'CUSTOMER SERVICE', 3000);
INSERT INTO DEPARTMENT (DEPT_ID, DEPT_NAME) VALUES (7, 'SALES');



-- WHEN ATTEMPTING TO DROP A TABLE WHICH IS REFERENCED BY ANOTHER WE ARE UNABLE TO DO SO
--DROP TABLE DEPARTMENT; not allowed, use ALTER
--ALTER TABLE EMPLOYEE
--DROP CONSTRAINT FK_EMP_DPT;

--DROP TABLE DEPARTMENT;

-- RE-CREATING OUR FOREIGN KEY RELATIONSIHP
--ALTER TABLE EMPLOYEE
--ADD CONSTRAINT FK_EMP_DPT
--FOREIGN KEY (DEPT_ID) REFERENCES DEPARTMENT(DEPT_ID) ON DELETE CASCADE;
-- aonther option here is 'ON DELETE SET NULL' so references will be set null instead of deleted

-- INSERT SOME EMPLOYEES INTO OUR EMPLOYEE TABLE (DML)
INSERT INTO EMPLOYEE VALUES (7, 'JILLIAN KYND', DATE '1980-10-15', 2840.00, DATE '2015-05-11', 'AC_ACCOUNT', 2, 2, 1);
INSERT INTO EMPLOYEE VALUES (8, 'TIM KIBBEL', DATE '1980-05-20', 2240.00, DATE '2014-07-28', 'MK_REP', 1, 1, 1);
INSERT INTO EMPLOYEE VALUES (9, 'ETHELIN COMINI', DATE '1982-06-16', 3380.00, DATE '2017-10-02', 'IT_DEV', 3, 3, 1);
INSERT INTO EMPLOYEE VALUES (10, 'JASE HANDLEY', DATE '1975-10-08', 1870.00, DATE '2017-08-17', 'LG_LAW',11, 5, 1);
INSERT INTO EMPLOYEE VALUES (11, 'ARIEL PAVIS', DATE '1981-09-23', 4500.00, DATE '2015-07-12', 'LG_LAW', NULL, 5, 2);
INSERT INTO EMPLOYEE VALUES (12, 'MELISSA ITZKOVSKY', DATE '1983-03-03', 3870.00, DATE '2014-09-15', 'LG_LAW',11, 5, 2);
INSERT INTO EMPLOYEE VALUES (13, 'MALIA FILISOV', DATE '1976-07-11', 4620.00, DATE '2014-11-09', 'CS_REP', NULL, 6, 2);
INSERT INTO EMPLOYEE VALUES (14, 'BRENDAN LOUISET', DATE '1979-01-21', 3760.00, DATE '2018-03-28', 'CS_REP',13, 6, 2);
INSERT INTO EMPLOYEE VALUES (15, 'MILT KLIEMANN', DATE '1983-02-25', 3820.00, DATE '2016-05-01', 'AC_ACCOUNT', 2, 2, 2);
INSERT INTO EMPLOYEE VALUES (16, 'LUCILLE HUNE', DATE '1994-01-04', 2300.00, DATE '2016-04-17', 'MK_REP',1, 1, 2);
INSERT INTO EMPLOYEE VALUES (17, 'PETA POLTZOLD', DATE '1990-09-24', 2500.00, DATE '2015-07-10', 'IT_DEV',3, 3, 3);
INSERT INTO EMPLOYEE VALUES (18, 'LYDIA POVER', DATE '1991-10-01', 2800.00, DATE '2016-08-03', 'IT_DEV', 17, 3, 3);
INSERT INTO EMPLOYEE VALUES (19, 'RON WINTERTON', DATE '1977-09-27', 2500.00, DATE '2018-02-23', 'LG_LAW', 11, 5, 3);
INSERT INTO EMPLOYEE VALUES (20, 'NITIN CHESTNUT', DATE '1995-01-18', 2800.00, DATE '2014-10-25', 'CS_REP', 13, 6, 3);
COMMIT;

/*
SELECT EMP_NAME
FROM EMPLOYEE;

--DELETE FROM EMPLOYEE
--WHERE MONTHLY_SALARY > 3500;

--------------------------------------------
-- QUERYING THE DATABASE
--------------------------------------------
SELECT *
FROM EMPLOYEE
WHERE MANAGER_ID=1;

SELECT EMP_NAME, MONTHLY_SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE MANAGER_ID=1;

SELECT *
FROM EMPLOYEE
WHERE EMP_NAME LIKE 'JAMES%'
ORDER BY MONTHLY_SALARY;

SELECT COUNT(*) AS TOTAL_EMPLOYEES
FROM EMPLOYEE;

-- SHOW THE AVERAGE SALARY BY DEPARTMENT
SELECT DEPT_ID, ROUND(AVG(MONTHLY_SALARY)) AVG_SALARY
FROM EMPLOYEE
GROUP BY DEPT_ID
HAVING DEPT_ID < 3;

SELECT *
FROM EMPLOYEE
WHERE DEPT_ID = 1 OR DEPT_ID = 3 OR DEPT_ID = 6;

SELECT *
FROM EMPLOYEE
WHERE DEPT_ID IN (1,3,6);

-- USING SUBQUERIES
SELECT *
FROM EMPLOYEE
WHERE MONTHLY_SALARY =
    (SELECT MAX(MONTHLY_SALARY)
    FROM EMPLOYEE);
*/

CREATE TABLE LOCATIONS (
    LOC_ID NUMBER(5) CONSTRAINT PK_LOC PRIMARY KEY,
    LOC_NAME VARCHAR2(100) NOT NULL,
    ADDRESS NUMBER(10),
    STREET VARCHAR2(100),
    CITY VARCHAR2(100),
    STATE VARCHAR2(25) ,
    ZIP NUMBER(5)
);

INSERT INTO LOCATIONS VALUES (1, 'Revature', 11740, 'Plaza America Dr', 'Restion', 'VA', 10290);
INSERT INTO LOCATIONS VALUES (4, 'Rika LLC', 18824, 'Pendergast Ave', 'Cupertino', 'CA', 95014);
INSERT INTO LOCATIONS VALUES (7, 'RPI', 111, '8th Street', 'Troy', 'NY', 12180);
COMMIT;

ALTER TABLE EMPLOYEE
ADD LOCATION_ID NUMBER(5);

ALTER TABLE LOCATIONS
ADD CONSTRAINT FK_EMP_LOCATION
FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS(LOC_ID) ON DELETE CASCADE;

-- INSERT MORE INFO
INSERT INTO EMPLOYEE VALUES (7, 'JILLIAN KYND', DATE '1980-10-15', 2840.00, 1, DATE '2015-05-11', 'AC_ACCOUNT', 1, 1);
INSERT INTO EMPLOYEE VALUES (8, 'TIM KIBBEL', DATE '1980-05-20', 2240.00, 2, DATE '2014-07-28', 'MK_REP', 2, 1);
INSERT INTO EMPLOYEE VALUES (9, 'ETHELIN COMINI', DATE '1982-06-16', 3380.00, 3, DATE '2017-10-02', 'IT_DEV', 3, 1);
INSERT INTO EMPLOYEE VALUES (10, 'JASE HANDLEY', DATE '1975-10-08', 1870.00, 5, DATE '2017-08-17', 'LG_LAW',11, 1);
INSERT INTO EMPLOYEE VALUES (11, 'ARIEL PAVIS', DATE '1981-09-23', 4500.00, 5, DATE '2015-07-12', 'LG_LAW', NULL, 2);
INSERT INTO EMPLOYEE VALUES (12, 'MELISSA ITZKOVSKY', DATE '1983-03-03', 3870.00, 5, DATE '2014-09-15', 'LG_LAW',11, 2);
INSERT INTO EMPLOYEE VALUES (13, 'MALIA FILISOV', DATE '1976-07-11', 4620.00, 6, DATE '2014-11-09', 'CS_REP', NULL, 2);
INSERT INTO EMPLOYEE VALUES (14, 'BRENDAN LOUISET', DATE '1979-01-21', 3760.00, 6, DATE '2018-03-28', 'CS_REP',13, 2);
INSERT INTO EMPLOYEE VALUES (15, 'MILT KLIEMANN', DATE '1983-02-25', 3820.00, 1, DATE '2016-05-01', 'AC_ACCOUNT', 1, 2);
INSERT INTO EMPLOYEE VALUES (16, 'LUCILLE HUNE', DATE '1994-01-04', 2300.00, 2, DATE '2016-04-17', 'MK_REP',2, 2);
INSERT INTO EMPLOYEE VALUES (17, 'PETA POLTZOLD', DATE '1990-09-24', 2500.00, 3, DATE '2015-07-10', 'IT_DEV',3, 3);
INSERT INTO EMPLOYEE VALUES (18, 'LYDIA POVER', DATE '1991-10-01', 2800.00, 3, DATE '2016-08-03', 'IT_DEV', NULL, 3);
INSERT INTO EMPLOYEE VALUES (19, 'RON WINTERTON', DATE '1977-09-27', 2500.00, 5, DATE '2018-02-23', 'LG_LAW', 11, 3);
INSERT INTO EMPLOYEE VALUES (20, 'NITIN CHESTNUT', DATE '1995-01-18', 2800.00, 6, DATE '2014-10-25', 'CS_REP', 13, 3);


-- ADDING ANOTHER TABLE FOR EMPLOYEE LOCATIONS
CREATE TABLE LOCATIONS(
    LOCATION_ID NUMBER(5),
    STREET VARCHAR2(25),
    CITY VARCHAR2(25),
    STATE VARCHAR2(2),
    ZIPCODE NUMBER(5)
);
-- INCLUDE PK
ALTER TABLE LOCATIONS
ADD CONSTRAINT PK_LOCATIONS PRIMARY KEY (LOCATION_ID);

-- ADD EMPLOYEE LOCATION IN EMPLOYEE TABLE
ALTER TABLE EMPLOYEE
ADD LOCATION_ID NUMBER(5);

-- ADD RELATIONSHIP BETWEEN EMPLOYEE AND LOCATION
ALTER TABLE EMPLOYEE 
ADD CONSTRAINT FK_EMP_LOCATION
FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS;

-- INSERT LOCATIONS INTO OUR LOCATION TABLE
INSERT INTO LOCATIONS VALUES (1, '14 MAIN STREET', 'RESTON', 'VA', 20190);
INSERT INTO LOCATIONS VALUES (2, '960 TCHOUPITOULAS ST', 'NEW ORLEANS', 'LA', 70118);
INSERT INTO LOCATIONS VALUES (3, '200 LOMBARD ST', 'SAN FRANCISCO', 'CA', 94109);
COMMIT;

/*
We can create an anonymous constraint during table declaration,
a named consraint during table declaration,
or a named constraint independent from the table declaration
after our table has been made

candidate key = any coluymn which uniquely identifies a row
and can possibly be a primary key
*/