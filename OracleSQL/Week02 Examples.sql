





-- Sets verbose output
SET SERVEROUTPUT ON;


-- DUAL is a dummy table that we can use to retrieve data which is not in a table
-- in a similar format to a dataset
SELECT 5 FROM DUAL;
SELECT 5 AS "Number", SQUARE(5) AS "Number Squared" FROM DUAL;



-- Creates a FUNCTION that returns the square of that number
CREATE OR REPLACE FUNCTION SQUARE(X IN NUMBER) -- The In is implicit
RETURN NUMBER
IS
BEGIN
    RETURN X * X;
END;

-- Does the same as the previous, but with a declared variable
CREATE OR REPLACE FUNCTION SQUARE(X IN NUMBER)
RETURN NUMBER
IS
    Y NUMBER;
BEGIN
    Y := X * X;
    RETURN Y;
END;


BEGIN
    DBMS_OUTPUT.PUT_LINE(SQUARE(25));
END;


-- Shows that we can use the stored function. 
SELECT MONTHLY_SALARY, SQUARE(MONTHLY_SALARY) FROM EMPLOYEE;



-- Create a function that returns the max of two numbers
CREATE OR REPLACE FUNCTION maxNum(X NUMBER, Y NUMBER)
RETURN NUMBER
IS
BEGIN
    IF X > Y THEN
        RETURN X;
    ELSE
        RETURN Y;
    END IF;
END;


-- Tests the maxNum function. 
DECLARE 
    FIRST_NUM NUMBER;
    SECOND_NUM NUMBER;
    MAX_NUM NUMBER;
BEGIN
    FIRST_NUM := 25;
    SECOND_NUM := 38;
    MAX_NUM := maxNum(FIRST_NUM, SECOND_NUM);
    DBMS_OUTPUT.PUT_LINE('MAX: ' || MAX_NUM);
END;




-- This function creates a function that applies a varying tax rate
CREATE OR REPLACE FUNCTION APPLY_TAX(PRE_TAX IN NUMBER)
RETURN NUMBER
IS
    POST_TAX NUMBER;
BEGIN
    IF PRE_TAX < 2000 THEN
        POST_TAX := (.78) * PRE_TAX;
    ELSIF PRE_TAX < 3000 THEN
        POST_TAX := (.75) * PRE_TAX;
    ELSE
        POST_TAX := (.71) * PRE_TAX;
    END IF;
    RETURN POST_TAX;
END;

SELECT MONTHLY_SALARY AS "Pre Tax", APPLY_TAX(Monthly_Salary) FROM EMPLOYEE;

-- Turns off verbose output
SET SERVEROUTPUT OFF;




-- Create a PROCEDURE which has an output parameter of a CURSOR
-- A CURSOR is a POINTER to a CONTEXT AREA, which is essentially a result set. 
CREATE OR REPLACE PROCEDURE GET_ALL_EMPLOYEES (S OUT SYS_REFCURSOR)
IS
BEGIN
    -- Note, this runs the whole query and returns a cursor to the result set. 
    -- This does not do any partial execution. 
    OPEN S FOR
    SELECT EMP_ID, EMP_NAME FROM EMPLOYEE;
END;


-- Make a script block that executes when sent to server. 
DECLARE
    -- The variable to our CURSOR pointer
    SVAR SYS_REFCURSOR;
    TEMP_ID EMPLOYEE.EMP_ID % TYPE;--NUMBER;
    TEMP_NAME EMPLOYEE.EMP_NAME % TYPE;--VARCHAR2(50);
BEGIN
    -- Gets the CURSOR pointer to the dataset
    GET_ALL_EMPLOYEES(SVAR);
    
    LOOP
        FETCH SVAR INTO TEMP_ID, TEMP_NAME; -- "Active Set" is each row returned by the cursor
        -- If it can't find a Temp_ID and Temp_Name, break;
        EXIT WHEN SVAR % NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(TEMP_ID || ' is current ID to ' || TEMP_NAME);
    END LOOP;
    
    -- Don't forget to close that pointer
    CLOSE SVAR;
END;



-- Create a PROCEDURE which increases a departments monthly budget
CREATE OR REPLACE PROCEDURE INCREASE_BUDGET(Dept IN DEPARTMENT.DEPT_ID % TYPE, Val IN DEPARTMENT.MONTHLY_BUDGET % TYPE)
IS
BEGIN
    UPDATE DEPARTMENT
    SET MONTHLY_BUDGET = MONTHLY_BUDGET + Val
    WHERE DEPT_ID = Dept;
END;

BEGIN 
    INCREASE_BUGET(1, 6000);
    INCREASE_BUGET(2, 10000);
    INCREASE_BUGET(3, 11000);
    INCREASE_BUGET(4, 1000);
    INCREASE_BUGET(5, 8000);
    INCREASE_BUGET(6, 6000);
END;

-- Display the department budget alongside the amount that each employee is paid. 
SELECT D.DEPT_NAME AS Department, D.MONTHLY_BUDGET AS Budget, SUM(E.MONTHLY_SALARY) AS "Budget Used"
FROM EMPLOYEE E
RIGHT JOIN DEPARTMENT D
    ON E.DEPT_ID = D.DEPT_ID
GROUP BY D.DEPT_NAME, D.MONTHLY_BUDGET;


-- Create a procedure that give a raise only if there is enough money. 
CREATE OR REPLACE PROCEDURE giveRaise(INPUT_ID EMPLOYEE.EMP_ID % TYPE, RAISE_AMOUNT EMPLOYEE.MONTHLY_SALARY % TYPE)
IS
 -- Variable declarations for Comparison
    DEPT_BUDGET DEPARTMENT.MONTHLY_BUDGET % TYPE;
    BUDGET_USED DEPARTMENT.MONTHLY_BUDGET % TYPE;
    
    -- Declare Variables to store employee information
    EMP_ID NUMBER;
    EMP_NAME varchar(50);
    MONTHLY_SALARY NUMBER;
BEGIN
    -- Get the employee in question
    
    -- Get the monthly budget of the department the employee works in
    SELECT MONTHLY_BUDGET INTO DEPT_BUDGET
    FROM DEPARTMENT 
    WHERE DEPT_ID = 
        (SELECT DEPT_ID FROM EMPLOYEE WHERE EMP_ID = INPUT_ID);
    
    -- Get the amount of money used by the department
    SELECT SUM(E.MONTHLY_SALARY) AS "Budget Used" INTO BUDGET_USED -- This is an implicit cursor
    FROM EMPLOYEE E
    WHERE DEPT_ID = 
        (SELECT DEPT_ID FROM EMPLOYEE WHERE EMP_ID = 3);
    
    IF ((BUDGET_USED + RAISE_AMOUNT) > DEPT_BUDGET) THEN
        DBMS_OUTPUT.PUT_LINE('Not Enough Funds');
    ELSE
        UPDATE EMPLOYEE
        SET MONTHLY_SALARY = MONTHLY_SALARY + RAISE_AMOUNT
        WHERE EMP_ID = INPUT_ID;
        DBMS_OUTPUT.PUT_LINE('Now Makes More');
    END IF;
END;

BEGIN
    giveRaise(3, -300);
END;

--ALTER TABLE DEPARTMENT
--RENAME COLUMN MOUNTHLY_BUDGET TO MONTHLY_BUDGET;



-------------------------------------------------------------------------------------------------
-- Working With SEQUENCES
-------------------------------------------------------------------------------------------------
CREATE SEQUENCE SQ_DEPARTMENT_PK
START WITH 7
INCREMENT BY 1;

-- Create a Trigger which uses this sequence to Generate Primary Keys
CREATE OR REPLACE TRIGGER TR_INSERT_DEPARTMENT
-- When, on what event, and On which Table to apply the trigger
BEFORE INSERT ON DEPARTMENT
FOR EACH ROW -- Operates on each that is affected
BEGIN
-- Gets the next value in the sequence (SQ_DEPARTMENT_PK.nextVal) 
-- and puts it into the new record's DEPT_ID (INTO :NEW.DEPT_ID)
SELECT SQ_DEPARTMENT_PK.nextVal INTO :NEW.DEPT_ID FROM DUAL;
END;

-- Test by inserting a new department
INSERT INTO DEPARTMENT (DEPT_NAME, MONTHLY_BUDGET) VALUES ('Reapers', 25000);


-- Creates a sequence for the Employee table. 
CREATE SEQUENCE SQ_EMPLOYEE_PK
START WITH 21
INCREMENT BY 1;

-- Create a Trigger which uses this sequence to Generate primary Keys
CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMPLOYEE_PK.nextVal INTO :NEW.EMP_ID FROM DUAL;
END;

 -- Test the sequence
INSERT INTO EMPLOYEE VALUES (0, 'JIM BRINE', DATE '1234-12-23', 2000, DATE '0123-04-05', 'MK_7', NULL, 3, 1);


-- Create a sequence for the primary keys of the locations
CREATE SEQUENCE SQ_LOCATION_PK
START WITH 11
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_LOCATION
BEFORE INSERT ON LOCATION
FOR EACH ROW
BEGIN
    SELECT SQ_LOCATION_PK.nextVal INTO :NEW.LOC_ID FROM DUAL;
END;

-- Test the insert 
INSERT INTO LOCATION VALUES (0, 555, 'Dennis Dr.', 'New York', 'NY', '11111', '');


-- Creates a trigger 
CREATE OR REPLACE TRIGGER TR_LIMIT_SALARY
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    IF (:NEW.Monthly_Salary > 25000) THEN
        RAISE_APPLICATION_ERROR(-20101, 'Salary Too High');
    END IF;
END;

-- Test it
INSERT INTO EMPLOYEE VALUES (0, 'JOHN BRINE', DATE '1234-12-23', 98765, DATE '0123-04-05', 'MK_7', NULL, 3, 1);

-- To Remove a trigger
DROP TRIGGER TR_LIMIT_SALARY;


