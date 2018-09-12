-----------------------------------------------------
-- USING DDL TO CREATE A TABLE
-----------------------------------------------------
-- Need a semicolon at the end of a compelete query
/*

THIS IS A 
MULTILINE
COMMENT
*/

/*
CREATE TABLE DEPARTMENT(
    DEPT_ID NUMBER(5) PRIMARY KEY
    );
*/

CREATE TABLE DEPARTMENT(
    DEPT_ID NUMBER(5) CONSTRAINT PK_DEPT PRIMARY KEY, 
    DEPT_NAME VARCHAR2(50), --ORACLE CLAIMS THAT VARCHAR 2 IS RECOMMENDED
    MONTHLY_BUDGET NUMBER(7,2)
    );
        
CREATE TABLE EMPLOYEE(
    EMP_ID NUMBER(5) CONSTRAINT PK_EMP PRIMARY KEY,
    EMP_NAME VARCHAR2(50), 
    BIRTHDAY DATE, 
    MONTHLY_SALARY NUMBER(7,2) NOT NULL,
    HIRE_DATE DATE, 
    POSITION VARCHAR2(20), --POSITION IN HER EXAMPLE
    MANAGER_ID NUMBER(5),
    DEPT_ID NUMBER(5) CONSTRAINT FK_EMP_DPT REFERENCES DEPARTMENT

);

INSERT INTO DEPARTMENT VALUES (1,'MARKETING',5000);
INSERT INTO DEPARTMENT VALUES  (2,'ACCOUNTING',4000);
INSERT INTO DEPARTMENT VALUES  (3,'INFORMATION TECHNOLOGY',4500);
INSERT INTO DEPARTMENT VALUES  (4,'HUMAN RESOURCES',3500);
INSERT INTO DEPARTMENT VALUES  (5,'LEGAL',2000);
INSERT INTO DEPARTMENT VALUES  (6,'CUSTOMER SERVICE',3000);
--INSERT INTO DEPARTMENT (DEPT_ID, DEPT_NAME) VALUES (7,'SALES')
-- Another way of inserting into the table without having to specify all of the values explictly

--When attempting to drop a table reference by anotther we are unable to do so
--DROP TABLE DEPARTMENT;

ALTER TABLE EMPLOYEE
DROP CONSTRAINT FK_EMP_DPT;

DROP TABLE DEPARTMENT;

ALTER TABLE EMPLOYEE
ADD CONSTRAINT FK_EMP_DPT
FOREIGN KEY (DEPT_ID) REFERENCES DEPARTMENT (DEPT_ID) ON DELETE CASCADE;
---ANOTHER Option is ON DELETE SET NULL


INSERT INTO EMPLOYEE VALUES (1,'JOHN SMITH', DATE '1989-01-05', 2000, DATE '2015-03-08','MK_REP',NULL,1);
INSERT INTO EMPLOYEE VALUES (2, 'JAMES BOSH', DATE '1990-08-13', 3200, DATE '2013-02-20', 'AC_ACCOUNT',NULL, 2);
INSERT INTO EMPLOYEE VALUES (3, 'LISA JACKSON', DATE '1988-10-15', 3800, DATE '2012-03-08', 'IT_PROF', NULL, 3);
INSERT INTO EMPLOYEE VALUES (4, 'ANGELA DEAN', DATE '1982-12-07', 2000, DATE '2018-07-20', 'IT_PROF', 3, 1);
INSERT INTO EMPLOYEE VALUES (5, 'NIGEL OAKS', DATE '1990-07-28', 2200, DATE '2017-04-12', 'MK_REP', 1, 1);
INSERT INTO EMPLOYEE VALUES (6, 'JAMES BOND', DATE '1992-11-13', 2800, DATE '2017-10-13', 'MK_REP', 1, 1);
--WE CANNOT INSERT OUT EMPLOYEE INTO THE EMPLOYEE TABLE WITHOUT FIRST DEFIFNING THE DEPARTMENTS


SELECT * FROM EMPLOYEE 
WHERE MANAGER_ID=1;

SELECT * 
FROM EMPLOYEE
WHERE EMP_NAME LIKE 'JAMES%'
ORDER BY MONTHLY_SALARY DESC;

SELECT EMP_NAME, MONTHLY_SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE MANAGER_ID=1;

SELECT COUNT(*)
FROM EMPLOYEE;

--OR
--USE AN ALIES

SELECT COUNT(*) AS TOTAL_EMPLOYEES
FROM EMPLOYEE;

--SHOW THE AVERAGE SALARY BY DEPT

SELECT DEPT_ID, ROUND (AVG( MONTHLY_SALARY)) AVG_SALARY --THIS IS ALSO ALIASING, W/O USING AS 
FROM EMPLOYEE
GROUP BY DEPT_ID
HAVING DEPT_ID<3;


SELECT * 
FROM EMPLOYEE
WHERE DEPT_ID=1 OR DEPT_ID=6;

SELECT * 
FROM EMPLOYEE
WHERE DEPT_TD IN (1,3,6);

--USING SUBCQUERIES 
SELECT *
FROM EMPLOYEE
WHERE MONTHLY_SALARY=
(SELECT MAX (MONTHLY_SALARY)
FROM EMPLOYEE
);


---

--Lets add a third table
--it should hold the locations, have some addresses, the employeeID and then 
-- refactor the employee table to include this new field

CREATE TABLE LOCATIONS(
    LOC_ID NUMBER(5),
    STREET VARCHAR2(25),
    CITY  VARCHAR2(25),
    STATE VARCHAR(2),
    ZIPCODE NUMBER(5)
);

ALTER TABLE LOCATIONS
  ADD CONSTRAINT PK_LOCATIONS PRIMARY KEY (LOC_ID);
  
ALTER TABLE EMPLOYEE
ADD LOC_ID NUMBER(5) ;
  
ALTER TABLE EMPLOYEE
ADD CONSTRAINT FK_EMP_LOC
FOREIGN KEY (LOC_ID) REFERENCES LOCATIONS; 

INSERT INTO LOCATIONS VALUES(1,'14 MAIN SREET', 'RESTON', 'VA', 20190);
INSERT INTO LOCATIONS VALUES(2,'960 TCHOUPITULOUS ST', 'NEW ORLEANS', 'LA', 70118);
INSERT INTO LOCATIONS VALUES(3,'200 LOMBARD ST', 'SAN FRANCISCO', 'CA', 94109);
COMMIT;

--ADD PRIMARY LOCATION IN EMPLOYEE AND LOCATIONS


--ALTER TABLE EMPLOYEE
--ADD LOC_ID NUMBER(5) CONSTRAINT FK_LOC_ID REFERENCES LOCATIONS;

--
--ALTER TABLE EMPLOYEE
--DROP CONSTRAINT FK_EMP_LOC;
----
--DROP TABLE LOCATIONS;



--MAKE SURE ALL EMPLOYESS HAVE A LOCATION


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
WE CAN CREATE AN ANONYMOUS CONSTAINT DURING TABLE DELACATIONS , A NAMED CONSTAINT DURING 
TABLE DECLARATION AFTER OUR TABLE HAS BEEN MADE

candidate key- any column which could be a primary key
*/





/*
DDL:DATA DEFINITION LANGUAGE
- CREATE 
- ALTER
DROP
TRUNCATE

DML
INSERT
SELECTED (DQL)
DELETE
UPDATE

TCL



*/
--inner join
SELECT
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
INNER JOIN DEPARTMENT D 
--JOIN DEPARTMENT D
ON E.DEPT_ID=D.DEPT_ID;


SELECT 
E.EMP_NAME AS NAME,
D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DEPT_ID = D.DEPT_ID;


SELECT 
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
LEFT JOIN DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID;

--RIGHT JOIN
SELECT 
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
RIGHT JOIN DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID;

--SELF JOIN USEING IMPLICIT INNER JOIN

--SELECT [COLUMNS]
--FROM [TABLE]
--WHERE PREDICATE

CREATE VIEW MANAGERS AS
SELECT EMP1.EMP_NAME EMPLOYEE, EMP2.EMP_NAME MANAGER
FROM EMPLOYEE EMP1, EMPLOYEE EMP2
WHERE EMP1.MANAGER_ID=EMP2.EMP_ID;

--cross joins
--every employee and every dept
SELECT EMP_NAME, DEPT_NAME
FROM EMPLOYEE
CROSS JOIN DEPARTMENT;

--NATURAL JOIN
--SIMILAR AS INNER JOIN BUT DOESNT SHOW DUPLICATES
 SELECT *
 FROM EMPLOYEE
JOIN DEPARTMENT
 ON EMPLOYEE.DEPT_ID = DEPARTMENT.DEPT_ID;
 
 -- OUTER JOIN
 SELECT *
 FROM LOCATIONS
 FULL OUTER JOIN EMPLOYEE
 ON EMPLOYEE.LOC_ID = LOCATIONS.LOC_ID;
 
 --STACKING JOINS 
 SELECT 
    E.EMP_NAME NAME,
    D.DEPT_NAME DEPARTMENT,
    CONCAT (CONCAT (L.CITY,', '), L.STATE) LOCATION-- MAINTAINS INTEGRITY WHILE KEEPING IT MODULAR
    --SWEET
FROM EMPLOYEE E
INNER JOIN DEPARTMENT D
ON E.DEPT_ID=D.DEPT_ID 
INNER JOIN LOCATIONS L
ON L.LOC_ID=E.LOC_ID;



--


--set operators
SELECT *
FROM EMPLOYEE 
WHERE DEPT_ID=1
UNION
SELECT *
FROM EMPLOYEE
WHERE LOC_ID=1;
--PRINTS EMPLOYEES WHO HAVE DEPT_ID ONE AND LOC_ID 1, WITH NO DUPLICATES

SELECT *
FROM EMPLOYEE 
WHERE DEPT_ID=1
UNION ALL
SELECT *
FROM EMPLOYEE
WHERE LOC_ID=1;
--SAME AS UNION BUT WITH DUPLICATES

SELECT *
FROM EMPLOYEE 
WHERE DEPT_ID=1
INTERSECT
SELECT *
FROM EMPLOYEE
WHERE LOC_ID=1;

--MINUS
SELECT *
FROM EMPLOYEE 
WHERE DEPT_ID=1
MINUS
SELECT *
FROM EMPLOYEE
WHERE LOC_ID=1;

--USING AND IN AND EXISTS WITH SUBQUERIES
SELECT *
FROM DEPARTMENT
WHERE dept_id IN(
SELECT DEPT_ID
FROM EMPLOYEE);
--WHERE DEPT_ID= EMPLOYEE.EMP_ID);

SELECT *
FROM DEPARTMENT
WHERE EXISTS(
    SELECT *
    FROM EMPLOYEE
    WHERE EMPLOYEE.DEPT_ID =DEPARTMENT.DEPT_ID);
    --USING EXISTS LETs you add additional logic to the subquery whereas in just return all
commit; 



SELECT STREET STEET1, CITY CITY1, STATE STATE1
FROM LOCATIONS;

SAVEPOINT SP1;

UPDATE LOCATIONS
SET CITY='KANSAS CITY';

SELECT STREET STREET2, CITY CITY2, STATE STATE2
FROM LOCATIONS; 

ROLLBACK TO SP1;

UPDATE LOCATIONS
SET CITY='OKLAHOMA CITY'
WHERE LOC_ID=3;
SELECT STREET STREET3, CITY CITY3, STATE STATE3
FROM LOCATIONS; 

ROLLBACK;
COMMIT;

SELECT STREET STREET4, CITY CITY4, STATE STATE4
FROM LOCATIONS; 