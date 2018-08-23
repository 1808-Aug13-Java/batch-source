------------------------
-- CREATING USER DEFINED FUNCTIONS
------------------------
--HELLO WORLD

SET SERVEROUTPUT ON;


CREATE OR REPLACE FUNCTION HELLO
RETURN VARCHAR2
IS
BEGIN
    RETURN 'HELLO WORLD!!';
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE(HELLO());
END;

--  CREATE A FUNCTION WHICH TAKES A UMERIC INPUT AND RETURNS THE SQR OF THAT UMBER
CREATE OR REPLACE FUNCTION SQUARE(X NUMBER)
RETURN NUMBER
IS
BEGIN
 RETURN X * X;   
END;
/

DECLARE
    -- DECLARE/ASSIGN ANY NECCESSARY VARS TO USE IN OUR FUNCTION
    N NUMBER := 5;
BEGIN 
    DBMS_OUTPUT.PUT_LINE(SQUARE(N));
END;
/

SELECT MONTHLY_SALARY, SQUARE(MONTHLY_SALARY)
FROM EMPLOYEE;

SELECT 5 AS "NUMBER", SQUARE(5) AS NUMBER_SQUARED
FROM DUAL;
--DUAL IS A DUMMY TABE WHICH WE CAN USE TO RETRIEVE DATA WHICH IS NOT IN A TABLE
-- IN A SIMILAR FORMAT TO A DATASET

--DUAL IS A DUMMY TABE WHICH WE CAN USE TO RETRIEVE DATA WHICH IS NOT IN A TABLE
-- RECREATING WITH DECLARED VAR
CREATE OR REPLACE FUNCTION SQUARE(X NUMBER, Y NUMBER)
RETURN NUMBER
IS
Y NUMBER;
BEGIN
    Y := X*X;
    RETURN Y;   
END;
/

CREATE OR REPLACE FUNCTION FIND_MAX_NUMBER(X NUMBER, Y NUMBER)
RETURN NUMBER
IS
BEGIN
    IF X>Y THEN
    RETURN X;
    ELSE
    RETURN Y;
    END IF;
END;
/

DECLARE
    FIRST_NUM NUMBER;
    SECOND_NUM NUMBER;
    MAX_NUM NUMBER;
BEGIN 
    FIRST_NUM := 25;
    SECOND_NUM := 33;
    MAX_NUM := FIND_MAX_NUMBER(FIRST_NUM, SECOND_NUM);
    -- || CONCATS FOR US
    DBMS_OUTPUT.PUT_LINE('MAX: '||MAX_NUM);
END;

CREATE OR REPLACE FUNCTION APPLY_TAX(PRE_TAX NUMBER)
RETURN NUMBER
IS
POST_TAX NUMBER;
BEGIN
    IF PRE_TAX < 2000 THEN
    POST_TAX := (.78)*PRE_TAX;
    ELSIF PRE_TAX < 3000 THEN
    POST_TAX := (.75)*PRE_TAX;
    ELSE
    POST_TAX := (.71)*PRE_TAX;
    END IF;
    RETURN POST_TAX;
END;
/

SELECT MONTHLY_SALARY AS "PRE TAX", APPLY_TAX(MONTHLY_SALARY) AS "POST TAX"
FROM EMPLOYEE;


--------------------
-- STORED PROCEDURES
--------------------
-- BASIC FIRST HELLO WORLD PROCEDURE
CREATE OR REPLACE PROCEDURE HELLO_PROCEDURE
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END;
/

BEGIN
    HELLO_PROCEDURE();
END;

-- CREATE A PROCEDURE WHICH HAS AN OUTPUT PARAMETER OF A CURSOR
-- A CURSOR IS A POINTER TO A CONTEXT AREA, WHICH IS ESSENTIAL A RESULT SET
CREATE OR REPLACE PROCEDURE GET_ALL_EMPLOYEES (S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR
    SELECT EMP_ID, EMP_NAME FROM EMPLOYEE; 
END;
/

DECLARE
    SVAR SYS_REFCURSOR;
    TEMP_ID EMPLOYEE.EMP_ID%TYPE;--NUMBER;
    TEMP_NAME EMPLOYEE.EMP_NAME%TYPE;--VARCHAR2(50);
BEGIN 
    GET_ALL_EMPLOYEES(SVAR);
    -- NOW WE SHOULD HAVE ACCESS TO OUR EMPLOYEES
    -- THROUGH SVAR
    LOOP
        FETCH SVAR INTO TEMP_ID, TEMP_NAME; -- "ACTIVE SET" IS EACH ROW RETURNED BY CURSOR
        EXIT WHEN SVAR%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(TEMP_ID||' IS CURRENT ID, '||TEMP_NAME||' IS CURRENT NAME');
    END LOOP;
    CLOSE SVAR;
END;


-- CREATE A PROCEDURE WHICH INCREASES A DEPARTMENTS MONTHLY BUDGET
UPDATE DEPARTMENT
SET MONTHLY_BUDGET = MONTHLY_BUDGET + 500
WHERE DEPT_ID = 3;

CREATE OR REPLACE PROCEDURE INCREASE_BUDGET(
            DEPT IN DEPARTMENT.DEPT_ID%TYPE, AMOUNT IN DEPARTMENT.MONTHLY_BUDGET%TYPE)
IS
BEGIN
    UPDATE DEPARTMENT
    SET MONTHLY_BUDGET = MONTHLY_BUDGET + AMOUNT
    WHERE DEPT_ID = DEPT;

END;
/

BEGIN
    INCREASE_BUDGET(1, 5000);
    INCREASE_BUDGET(2, 10000);
    INCREASE_BUDGET(3, 11000);
    INCREASE_BUDGET(4, 1000);
    INCREASE_BUDGET(5, 8000);
    INCREASE_BUDGET(6, 9000);
    COMMIT;
END;
/


-- DISPLAYING DEPARTMENT BUDGET AND HOW MUCH IS USED BY EACH DEPT
SELECT 
    D.DEPT_NAME DEPT, D.MONTHLY_BUDGET BUDGET, SUM(E.MONTHLY_SALARY) "BUDGET USED"
FROM EMPLOYEE E
JOIN DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID
GROUP BY D.DEPT_NAME, D.MONTHLY_BUDGET;
-- GET EVERY DEPT UNDER BUDGET
BEGIN
    INCREASE_BUDGET(1, 2000);
    INCREASE_BUDGET(5, 3000);
    COMMIT;
END;
/

-- CREATE A PROCEDURE WHICH GIVES AN EMPL A RAISE
-- ONLY IF THERE IS ENOUGH IN EMPLOYEES DEPT BUDGET
CREATE OR REPLACE PROCEDURE GIVE_RAISE(
                INPUT_ID EMPLOYEE.EMP_ID%TYPE, RAISE_AMOUNT EMPLOYEE.MONTHLY_SALARY%TYPE)
IS
    DEPT_BUDGET DEPARTMENT.MONTHLY_BUDGET%TYPE;
    BUDGET_USED DEPARTMENT.MONTHLY_BUDGET%TYPE;
    EMPLOYEE_NAME EMPLOYEE.EMP_NAME%TYPE;
    CURRENT_SALARY EMPLOYEE.MONTHLY_SALARY%TYPE;
BEGIN
    -- NOT THE INTO KEYWORD
    -- SAVE THE DEPT BUDGET OF THE INPUT EMPLOYEE
    -- TO DEPT_BUDGET
    SELECT MONTHLY_BUDGET INTO DEPT_BUDGET
    FROM DEPARTMENT
    WHERE DEPT_ID = (
        SELECT DEPT_ID
        FROM EMPLOYEE
        WHERE EMP_ID = INPUT_ID);
    
    SELECT SUM(MONTHLY_SALARY) INTO BUDGET_USED
    FROM EMPLOYEE
    WHERE DEPT_ID = (
        SELECT DEPT_ID
        FROM EMPLOYEE
        WHERE EMP_ID = INPUT_ID);
    
    
    SELECT EMP_NAME, MONTHLY_SALARY INTO EMPLOYEE_NAME, CURRENT_SALARY -- IMPLICIT CURSOR
    FROM EMPLOYEE
    WHERE EMP_ID = INPUT_ID;
    
    IF ((BUDGET_USED + RAISE_AMOUNT) > DEPT_BUDGET) THEN
        DBMS_OUTPUT.PUT_LINE('INSUFFICIENT DEPARTMENT FUNDS FOR '||EMPLOYEE_NAME);
    ELSE
        UPDATE EMPLOYEE
        SET MONTHLY_SALARY = MONTHLY_SALARY + RAISE_AMOUNT
        WHERE EMP_ID = INPUT_ID;
        DBMS_OUTPUT.PUT_LINE('RAISE SUCCESSFULLY INCREASED FOR '
                                ||EMPLOYEE_NAME||' FROM '
                                ||CURRENT_SALARY||' TO '||CURRENT_SALARY + RAISE_AMOUNT);
    END IF;
END;
/

BEGIN
    GIVE_RAISE(1, 1000);
END;
/

-------------------------------
-- WORKING WITH SEQUENCES AND TRIGGERS
-------------------------------
-- CREATING SEQUENCES FOR OUR TABLES
CREATE SEQUENCE SQ_DEPARTMENT_PK
START WITH 7
INCREMENT BY 1;

-- CREATING A TRIGGER WHICH USES THIS SEQUENCE TO GEN PK
CREATE OR REPLACE TRIGGER TR_INSERT_DEPARTMENT
BEFORE INSERT ON DEPARTMENT
FOR EACH ROW
BEGIN
    SELECT SQ_DEPARTMENT_PK.NEXTVAL INTO :NEW.DEPT_ID FROM DUAL;
END;

INSERT INTO DEPARTMENT (DEPT_NAME, MONTHLY_BUDGET) 
VALUES ('ADVERTISING', 11500);

-- CREATING SEQUENCES FOR OUR TABLES
CREATE SEQUENCE SQ_EMPLOYEE_PK
START WITH 10
INCREMENT BY 1;

-- CREATING A TRIGGER WHICH USES THIS SEQUENCE TO GEN PK
CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMPLOYEE_PK.NEXTVAL INTO :NEW.EMP_ID FROM DUAL;
END;

INSERT INTO EMPLOYEE (EMP_NAME, BIRTHDAY, MONTHLY_SALARY, HIRE_DATE, POSITION, MANAGER_ID, DEPT_ID, LOCATION_ID)
VALUES ('BOB', DATE '2000-01-01', 2000, DATE '2018-01-01', 'MK_REP', 1, 1, 1);
-- CREATING SEQUENCES FOR OUR TABLES
CREATE SEQUENCE SQ_LOCATION_PK
START WITH 3
INCREMENT BY 1;

-- CREATING A TRIGGER WHICH USES THIS SEQUENCE TO GEN PK
CREATE OR REPLACE TRIGGER TR_INSERT_LOCATION
BEFORE INSERT ON LOCATION
FOR EACH ROW
BEGIN
    SELECT SQ_LOCATION_PK.NEXTVAL INTO :NEW.LOCATION_ID FROM DUAL;
END;

INSERT INTO LOCATION (STREET, CITY, STATE, ZIPCODE)
VALUES ('100 BALLING ST.', 'ELIZABETHTOWN', 'KY', 42701);

CREATE OR REPLACE TRIGGER TR_LIMIT_SALARY
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    IF(:NEW.MONTHLY_SALARY>10000) THEN
        RAISE_APPLICATION_ERROR(-20101, 'SALARY TOO HIGH');
    END IF;
END;
/

INSERT INTO EMPLOYEE (EMP_ID, MONTHLY_SALARY) 
VALUES (21, 20000);