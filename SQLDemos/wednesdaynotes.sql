-------------------------------------
-- USING DDL TO CREATE OUR TABLES
-------------------------------------
CREATE TABLE DEPARTMENT(
    DEPT_ID NUMBER(5) CONSTRAINT PK_DEPT PRIMARY KEY,
    DEPT_NAME VARCHAR2(50),
    MONTHLY_BUDGET NUMBER(7,2)
);

-- We can't first insert to our employee table with a department without first defining the department
-- INSERT INTO EMPLOYEE VALUES (1, 'JOHN SMITH', DATE '1989-01-05', 2000, DATE '2015-03-08', 'MK_REP',NULL,1);

-- INSERTING RECORDS INTO OUR DEPARTMENT TABLE
INSERT INTO DEPARTMENT VALUES (1, 'MARKETING', 5000);
INSERT INTO DEPARTMENT VALUES (2, 'ACCOUNTING', 4000);
INSERT INTO DEPARTMENT VALUES (3, 'INFORMATION TECHNOLOGY', 4500);
INSERT INTO DEPARTMENT VALUES (4, 'HUMAN RESOURCED', 3500);
INSERT INTO DEPARTMENT VALUES (5, 'LEGAL', 2000);
INSERT INTO DEPARTMENT VALUES (6, 'CUSTOMER SERVICE', 3000);
-- INSERT INTO DEPARTMENT (DEPT_ID, DEPT_NAME) VALUES (7, 'SALES'); 

CREATE TABLE EMPLOYEE (
    EMP_ID NUMBER(5) CONSTRAINT PK_EMP PRIMARY KEY,
    EMP_NAME VARCHAR2(50),
    BIRTHDAY DATE,
    MONTHLY_SALARY NUMBER(7,2),
    HIRE_DATE DATE,
    POSITION VARCHAR2(20),
    MANAGER_ID NUMBER(5),
    DEPT_ID NUMBER(5) CONSTRAINT FK_EMP_DPT REFERENCES DEPARTMENT
);

-- INSERT SOME EMPLOYEES INTO OUR EMPLOYEE TABLE (DML)
INSERT INTO EMPLOYEE VALUES (1, 'JOHN SMITH', DATE '1989-01-05', 2000, DATE '2015-03-08', 'MK_REP',NULL,1);
INSERT INTO EMPLOYEE VALUES (2, 'JAMES BOSH', DATE '1990-08-13', 3200, DATE '2013-02-20', 'AC_ACCOUNT',NULL,2);
INSERT INTO EMPLOYEE VALUES (3, 'LISA JACKSON', DATE '1988-10-15', 3800, DATE '2012-03-08', 'IT_PROF',NULL,3);
INSERT INTO EMPLOYEE VALUES (4, 'ANGELA DEAN', DATE '1982-12-07', 2000, DATE '2017-04-12', 'IT_PROF',3,3);
INSERT INTO EMPLOYEE VALUES (5, 'NIGEL OAKS', DATE '1990-07-28', 2200, DATE '2018-07-20', 'MK_REP',1,1);
INSERT INTO EMPLOYEE VALUES (6, 'JAMES BOND', DATE '1992-11-13', 2800, DATE '2017-10-03', 'MK_REP',1,1);
INSERT INTO EMPLOYEE VALUES (7, 'JILLIAN KYND', DATE '1980-10-15', 2840.00, DATE '2015-05-11', 'AC_ACCOUNT', 2, 2);
INSERT INTO EMPLOYEE VALUES (8, 'TIM KIBBEL', DATE '1980-05-20', 2240.00, DATE '2014-07-28', 'MK_REP', 1, 1);
INSERT INTO EMPLOYEE VALUES (9, 'ETHELIN COMINI', DATE '1982-06-16', 3380.00, DATE '2017-10-02', 'IT_DEV', 3, 3);
INSERT INTO EMPLOYEE VALUES (10, 'JASE HANDLEY', DATE '1975-10-08', 1870.00, DATE '2017-08-17', 'LG_LAW',11, 5);
INSERT INTO EMPLOYEE VALUES (11, 'ARIEL PAVIS', DATE '1981-09-23', 4500.00, DATE '2015-07-12', 'LG_LAW', NULL, 5);
INSERT INTO EMPLOYEE VALUES (12, 'MELISSA ITZKOVSKY', DATE '1983-03-03', 3870.00, DATE '2014-09-15', 'LG_LAW',11, 5);
INSERT INTO EMPLOYEE VALUES (13, 'MALIA FILISOV', DATE '1976-07-11', 4620.00, DATE '2014-11-09', 'CS_REP', NULL, 6);
INSERT INTO EMPLOYEE VALUES (14, 'BRENDAN LOUISET', DATE '1979-01-21', 3760.00, DATE '2018-03-28', 'CS_REP',13, 6);
INSERT INTO EMPLOYEE VALUES (15, 'MILT KLIEMANN', DATE '1983-02-25', 3820.00, DATE '2016-05-01', 'AC_ACCOUNT', 2, 2);
INSERT INTO EMPLOYEE VALUES (16, 'LUCILLE HUNE', DATE '1994-01-04', 2300.00, DATE '2016-04-17', 'MK_REP',1, 1);
INSERT INTO EMPLOYEE VALUES (17, 'PETA POLTZOLD', DATE '1990-09-24', 2500.00, DATE '2015-07-10', 'IT_DEV',3, 3);
INSERT INTO EMPLOYEE VALUES (18, 'LYDIA POVER', DATE '1991-10-01', 2800.00, DATE '2016-08-03', 'IT_DEV', 17, 3);
INSERT INTO EMPLOYEE VALUES (19, 'RON WINTERTON', DATE '1977-09-27', 2500.00, DATE '2018-02-23', 'LG_LAW', 11, 5);
INSERT INTO EMPLOYEE VALUES (20, 'NITIN CHESTNUT', DATE '1995-01-18', 2800.00, DATE '2014-10-25', 'CS_REP', 13, 6);
COMMIT;

------------------------------------------
-- WORKING WITH JOINS
------------------------------------------

-- INNER JOIN
SELECT 
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
-- INNER JOIN DEPARTMENT D
JOIN DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID;

-- IMPLICIT INNER JOIN
SELECT
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DEPT_ID = D.DEPT_ID;

-- OUTER JOIN
SELECT 
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
FULL JOIN DEPARTMENT D -- ALSO COULD USE FULL OUTER JOIN
ON E.DEPT_ID = D.DEPT_ID;

-- LEFT OUTER JOIN
SELECT 
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
LEFT JOIN DEPARTMENT D -- ALSO COULD USE LEFT FULL OUTER JOIN
ON E.DEPT_ID = D.DEPT_ID;

-- RIGHT OUTER JOIN
SELECT 
    E.EMP_NAME AS NAME,
    D.DEPT_NAME AS DEPARTMENT
FROM EMPLOYEE E
RIGHT JOIN DEPARTMENT D -- ALSO COULD USE RIGHT FULL OUTER JOIN
ON E.DEPT_ID = D.DEPT_ID;

-- SELF JOIN
SELECT
    EMP1.EMP_NAME,
    EMP2.EMP_NAME
FROM EMPLOYEE EMP1, EMPLOYEE EMP2
WHERE EMP1.MANAGER_ID = EMP2.EMP_ID;

-- CROSS JOIN WITH EVERY EMPLOYEE AND DEPARTMENT
SELECT EMP_NAME, DEPT_NAME
FROM EMPLOYEE
CROSS JOIN DEPARTMENT;

-- NATURAL JOIN
SELECT *
FROM EMPLOYEE
NATURAL JOIN DEPARTMENT;

-- OUTER JOIN WITH LOCATIONS AND EMPLOYEES
SELECT *
FROM LOCATIONS
FULL OUTER JOIN EMPLOYEE
ON EMPLOYEE.LOCATOIN_ID = LOCATIONS.LOCATION_ID;

SELECT
    E.EMP_NAME NAME,
    D.DEPT_NAME DEPARTMENT,
    CONCAT(CONCAT(L.CITY, ', ').L.STATE) LOCATION
FROM EMPLOYEE E
INNER JOIN DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID
INNER JOIN LOCATIONS
ON L.LOCATION_ID = E.LOCATION_ID;

------------------------------------
-- SET OPERATORS
------------------------------------

-- UNION
SELECT *
FROM EMPLOYEE
WHERE DEPT_ID = 1
UNION
SELECT *
FROM EMPLOYEE
WHERE LOCATION_ID = 1;

-- UNION ALL
SELECT *
FROM EMPLOYEE
WHERE DEPT_ID = 1
UNION
SELECT *
FROM EMPLOYEE
WHERE LOCATION_ID = 1;

-- INTERSECTS
SELECT *
FROM EMPLOYEE
WHERE DEPT_ID = 1
INTERSECT
SELECT *
FROM EMPLOYEE
WHERE LOCATION_ID = 1;

-- MINUS
SELECT *
FROM EMPLOYEE
WHERE DEPT_ID = 1
MINUS
SELECT *
FROM EMPLOYEE
WHERE LOCATION_ID = 1;

---------------------------------------------
-- USING IN AND EXISTS WITH SUBQUERIES
---------------------------------------------

SELECT *
FROM DEPARTMENT
WHERE DEPT_ID IN (
    SELECT DEPT_ID
    FROM EMPLOYEE
);

SELECT *
FROM DEPARTMENT
WHERE EXISTS (
    SELECT *
    FROM EMPLOYEE
    WHERE EMPLOYEE.DEPT_ID = DEPARTMENT.DEPT_ID
);

SELECT * FROM DEPARTMENT;
SELECT * FROM EMPLOYEE;

/*
EXISTS VS IN
EXISTS IS GOING TO EVALUATE TO A BOOLEAN, WHEREAS IN IS GOING TO RETURN A RESULT
EXISTS RUNS THE INNER QUERY ONCE FOR EVERY RECORD IN THE OUTER QUERY
IF (RESULTS OF THE OUTER QUERY > RESULTS OF THE INNER QUERY) IN IS MORE EFFICIENT
*/