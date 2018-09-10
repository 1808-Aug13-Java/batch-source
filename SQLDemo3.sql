-------------------------------------------
-- CREATING USER DEFINED FUNCTIONS
-------------------------------------------
-- HELLO WORLD FUNCTION
SET SERVEROUTPUT ON;

-- WE CAN PRINT HELLO WORLD TO THE CONSOLE
BEGIN
   DBMS_OUTPUT.PUT_LINE('HELLO WORLD'); 
END;

-- WE CAN CREATE A FUNCTION WHICH RETURNS TO US HELLO WORLD
CREATE OR REPLACE FUNCTION HELLO
RETURN VARCHAR2
IS
BEGIN
    RETURN 'HELLO WORLD!!!!';
END;
/
-- DROP FUNCTION HELLO;

-- USING THIS FUNCTION IN A PL/SQL STATEMENT ALLOWS US TO PRINT HELLO WORLD
-- TO THE CONSOLE
BEGIN
   DBMS_OUTPUT.PUT_LINE(HELLO()); 
END;
/
-- CREATE A FUNCTION WHICH TAKES A NUMERIC INPUT AND RETURNS THE SQUARE OF THAT NUMBER
CREATE OR REPLACE FUNCTION SQUARE(X IN NUMBER)
RETURN NUMBER
IS
BEGIN 
    RETURN X*X;
END;
/

DECLARE
    -- DELCARE/ASSIGN ANY NECCESSARY VARIABLES TO USE IN OUR FUNCTION
    N NUMBER := 5;
BEGIN
    -- CALL OUR FUNCTION
    DBMS_OUTPUT.PUT_LINE(SQUARE(N));
END;
/

SELECT MONTHLY_SALARY, SQUARE(MONTHLY_SALARY)
FROM EMPLOYEE;

SELECT 5 AS "NUMBER", SQUARE(5) AS "NUMBER SQUARED"
FROM DUAL; 
-- DUAL IS A DUMMY TABLE WHICH WE CAN USE TO RETRIEVE DATA WHICH IS NOT IN A TABLE 
-- IN A SIMILAR FORMAT TO A DATASET
DROP TABLE DUAL;

-- RECREATING FUNCTION WITH A DECLARED VARIABLE
CREATE OR REPLACE FUNCTION SQUARE(X IN NUMBER)
RETURN NUMBER
IS
Y NUMBER;
BEGIN 
    Y := X*X;
    RETURN Y;
END;
/

-- CREATE A FUNCTION WHICH RETURNS THE MAX OF TWO NUMBERS
CREATE OR REPLACE FUNCTION FIND_MAX_NUMBER(X NUMBER, Y NUMBER)
RETURN NUMBER
IS
Z NUMBER;
BEGIN
    IF X>Y THEN
    -- RETURN X;
    Z := X;
    ELSE
    -- RETURN Y;
    Z := Y;
    END IF;
    RETURN Z;
END;
/

DECLARE
    FIRST_NUM NUMBER;
    SECOND_NUM NUMBER;
    MAX_NUM NUMBER;
BEGIN
    FIRST_NUM := 25;
    SECOND_NUM := 38;
    MAX_NUM := FIND_MAX_NUMBER(FIRST_NUM, SECOND_NUM);
    DBMS_OUTPUT.PUT_LINE('MAX: '||MAX_NUM);
END;
/

CREATE OR REPLACE FUNCTION APPLY_TAX(PRE_TAX IN NUMBER)
RETURN NUMBER
IS 
POST_TAX NUMBER;
BEGIN 
    IF 2000>PRE_TAX THEN
    POST_TAX:= (.78)*PRE_TAX;
    ELSIF 3000>PRE_TAX THEN
    POST_TAX:= (.75)*PRE_TAX;
    ELSE 
    POST_TAX:= (.71)*PRE_TAX;
    END IF;
    RETURN POST_TAX;
END;
/

SELECT MONTHLY_SALARY AS "PRE TAX", APPLY_TAX(MONTHLY_SALARY) AS "POST TAX"
FROM EMPLOYEE;


----------------------------------
-- CREATING STORED PROCEDURES
----------------------------------
-- BASIC FIRST HELLO WORLD PROCEDURE
CREATE OR REPLACE PROCEDURE HELLO_PROCEDURE
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END;
/
-- INVOKING HELLO WORLD PROCEDURE
BEGIN 
    HELLO_PROCEDURE();
END;

-- CREATE A PROCEDURE WHICH HAS AN OUTPUT PARAMETER OF A CURSOR
-- A CURSOR IS A POINTER TO A CONTEXT AREA, WHICH IS ESSENTIALLY A RESULT SET
CREATE OR REPLACE PROCEDURE GET_ALL_EMPLOYEES (S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR
    SELECT EMP_ID, EMP_NAME FROM EMPLOYEE;
END;
/


DECLARE
    SVAR SYS_REFCURSOR;
    TEMP_ID EMPLOYEE.EMP_ID%TYPE; --NUMBER;
    TEMP_NAME EMPLOYEE.EMP_NAME%TYPE; --VARCHAR2(50);
BEGIN 
    GET_ALL_EMPLOYEES(SVAR);
    -- NOW WE SHOULD HAVE ACCESS TO OUR EMPLOYEES THROUGH SVAR
    LOOP
        FETCH SVAR INTO TEMP_ID, TEMP_NAME; -- "ACTIVE SET" IS EACH ROW RETURNED BY THE CURSOR
        EXIT WHEN SVAR%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(TEMP_ID||' IS CURRENT ID, '||TEMP_NAME||' IS CURRENT NAME');
    END LOOP;
    CLOSE SVAR;
END;


-- CREATE A PROCEDURE WHICH INCREASES A DEPARTMENTS MONTHLY BUDGET
/* UPDATE DEPARTMENT
SET MONTHLY_BUDGET = MONTHLY_BUDGET + 500
WHERE DEPT_ID = 3; */ -- VERY LITTLE OF OUR UPDATE STATEMENT WOULD CHANGE IF WE DID THIS MULTIPLE TIMES

CREATE OR REPLACE PROCEDURE INCREASE_BUDGET(DEPT IN DEPARTMENT.DEPT_ID%TYPE, VAL IN DEPARTMENT.MONTHLY_BUDGET%TYPE)
IS 
BEGIN
    UPDATE DEPARTMENT
    SET MONTHLY_BUDGET = MONTHLY_BUDGET + VAL
    WHERE DEPT_ID = DEPT;
END;
/

BEGIN
    INCREASE_BUDGET(1, 6000);
    INCREASE_BUDGET(2, 10000);
    INCREASE_BUDGET(3, 11000);
    INCREASE_BUDGET(4, 1000);
    INCREASE_BUDGET(5, 8000);
    INCREASE_BUDGET(6, 9000);
    COMMIT;
END;
/

-- DISPLAYING DEPARTMENT BUDGET AND HOW MUCH IS USED BY EACH DEPARTMENT 
SELECT D.DEPT_NAME DEPARTMENT, D.MONTHLY_BUDGET BUDGET, SUM(E.MONTHLY_SALARY) "BUDGET USED"
FROM EMPLOYEE E
JOIN DEPARTMENT D
ON E.DEPT_ID = D.DEPT_ID
GROUP BY D.DEPT_NAME, D.MONTHLY_BUDGET;

-- MAKE SURE EVERY DEPARTMENT IS UNDER BUDGET
BEGIN
    INCREASE_BUDGET(1, 2000);
    INCREASE_BUDGET(5, 3000);
    COMMIT;
END;
/

-- CREATE A PROCEDURE WHICH ALLOWS US TO GIVE AN EMPLOYEE A RAISE 
-- ONLY IF THERE IS ENOUGH MONEY IN THAT EMPLOYEE'S DEPARTMENT'S BUDGET

--DELCARE OUR PROCEDURE
CREATE OR REPLACE PROCEDURE GIVE_RAISE(INPUT_ID EMPLOYEE.EMP_ID%TYPE, RAISE_AMOUNT EMPLOYEE.MONTHLY_SALARY%TYPE)
IS 
-- VARIABLE DECLARATIONS FOR COMPARISON
DEPT_BUDGET DEPARTMENT.MONTHLY_BUDGET%TYPE;
BUDGET_USED DEPARTMENT.MONTHLY_BUDGET%TYPE;
-- DECLARE VARIABLES TO STORE MY EMPLOYEE INFORMATION
EMPLOYEE_NAME EMPLOYEE.EMP_NAME%TYPE;
CURRENT_SALARY EMPLOYEE.MONTHLY_SALARY%TYPE;
BEGIN
    -- SAVE THE DEPARTMENT BUDGET OF THE INPUT EMPLOYEE TO DEPT_BUDGET
    SELECT MONTHLY_BUDGET INTO DEPT_BUDGET
    FROM DEPARTMENT 
    WHERE DEPT_ID = 
        (SELECT DEPT_ID
        FROM EMPLOYEE
        WHERE EMP_ID = INPUT_ID);
    -- DBMS_OUTPUT.PUT_LINE(DEPT_BUDGET);
    
    SELECT SUM(MONTHLY_SALARY) INTO BUDGET_USED
    FROM EMPLOYEE
    WHERE DEPT_ID = 
        (SELECT DEPT_ID
        FROM EMPLOYEE
        WHERE EMP_ID = INPUT_ID);
    --DBMS_OUTPUT.PUT_LINE(BUDGET_USED);
    
    SELECT EMP_NAME, MONTHLY_SALARY INTO EMPLOYEE_NAME, CURRENT_SALARY --IMPLICIT CURSOR
    FROM EMPLOYEE
    WHERE EMP_ID = INPUT_ID;
    
    IF((BUDGET_USED+RAISE_AMOUNT)>DEPT_BUDGET) THEN
        DBMS_OUTPUT.PUT_LINE('INSUFFICIENT DEPARTMENT FUNDS. MONTHLY SALARY FOR '||EMPLOYEE_NAME||' REMAINS '||CURRENT_SALARY);
    ELSE
        -- UPDATE EMPLOYEE SALARY
        UPDATE EMPLOYEE
        SET MONTHLY_SALARY = MONTHLY_SALARY+RAISE_AMOUNT
        WHERE EMP_ID = INPUT_ID;
        DBMS_OUTPUT.PUT_LINE('RAISE SUCCESSFULLY INCREASED BY '||RAISE_AMOUNT||'. NEW MONTHLY SALARY FOR '||EMPLOYEE_NAME||' IS '||(CURRENT_SALARY+RAISE_AMOUNT));
    END IF;    
END;
/

BEGIN
  GIVE_RAISE(7,400);  
END;

-----------------------------------------
-- WORKING WITH SEQUENCES AND TRIGGERS
-----------------------------------------
-- CREATING SEQUENCES FOR OUR TABLES
CREATE SEQUENCE SQ_DEPARTMENT_PK
START WITH 7
INCREMENT BY 1;

CREATE SEQUENCE SQ_EMPLOYEE_PK
START WITH 21
INCREMENT BY 1;

CREATE SEQUENCE SQ_LOCATIONS_PK
START WITH 4
INCREMENT BY 1;

-- CREATING A TRIGGER WHICH USES THIS SEQUENCE TO GEN PK
CREATE OR REPLACE TRIGGER TR_INSERT_DEPARTMENT
BEFORE INSERT ON DEPARTMENT
FOR EACH ROW 
BEGIN
    SELECT SQ_DEPARTMENT_PK.NEXTVAL INTO :NEW.DEPT_ID FROM DUAL;
END;

INSERT INTO DEPARTMENT (DEPT_NAME, MONTHLY_BUDGET) VALUES ('ANOTHER DEPARTMENT',15000);

CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMPLOYEE_PK.NEXTVAL INTO :NEW.EMP_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_LOCATIONS
BEFORE INSERT ON LOCATIONS
FOR EACH ROW
BEGIN
    SELECT SQ_LOCATIONS_PK.NEXTVAL INTO :NEW.LOCATION_ID FROM DUAL;
END;
/
INSERT INTO LOCATIONS (STREET, CITY, STATE, ZIPCODE) VALUES ('300 BROADWAY', 'NEW YORK', 'NY', 10001);


-- CREATING A TRIGGER WHICH LIMITS SALARY VALUE
CREATE OR REPLACE TRIGGER TR_LIMIT_SALARY
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    IF (:NEW.MONTHLY_SALARY>10000) THEN
        RAISE_APPLICATION_ERROR(-20101, 'SALARY TOO HIGH');
    END IF;
END;
/

INSERT INTO EMPLOYEE (MONTHLY_SALARY) VALUES (12000);