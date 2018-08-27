
-- Because we are using the chinook database,
-- need to set view to chinook and not our database
GRANT connect to chinook;
GRANT resource to chinook;
GRANT create session TO chinook;
GRANT create table TO chinook;
GRANT create view TO chinook;


conn chinook/p4ssw0rd
ROLLBACK;
SAVEPOINT BEFORE_CHINOOK;
-- ROLLBACK TO BEFORE_CHINOOK;


DELETE FROM CHINOOK.EMPLOYEE WHERE EMPLOYEEID IN (9,10);
DELETE FROM CHINOOK.CUSTOMER WHERE CUSTOMERID IN (60,61);

-- 2.1 SELECT

-- A. Task – Select all records from the Employee table.
SELECT * FROM CHINOOK.EMPLOYEE;

-- B. Task – Select all records from the Employee table where last name is King.
SELECT * FROM CHINOOK.EMPLOYEE WHERE LASTNAME='King';

-- C. Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL
SELECT * FROM CHINOOK.EMPLOYEE WHERE FIRSTNAME='Andrew' AND REPORTSTO=NULL;


-- 2.2 ORDER BY

-- A. Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM CHINOOK.ALBUM ORDER BY TITLE DESC;

-- B. Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CHINOOK.CUSTOMER ORDER BY CITY;

-- 2.3 INSERT INTO

-- A. Task – Insert two new records into Genre table 
INSERT INTO CHINOOK.GENRE VALUES (26, 'IMPERIAL ERA');
INSERT INTO CHINOOK.GENRE VALUES (27, 'REPUBLIC ERA');

-- B. Task – Insert two new records into Employee table
INSERT INTO CHINOOK.EMPLOYEE VALUES
(
    9, 'Thomas', 'D.', 'PR Manager', NULL, DATE '1992-08-23', DATE '2014-03-03', '543 Stewart St.', 
    'Lethbridge', 'AB' , 'Canada', 'T8I 6T8', '+1 (403) 444-444', '+1 (403) 444-4445', 'dthomas@chinookcorp.com'
);
INSERT INTO CHINOOK.EMPLOYEE VALUES
(
    10, 'Cosgrove', 'M.', 'PR Intern', 9, DATE '1993-05-14', DATE '2018-06-24', '5864 Queens RD.', 
    'Lethbridge', 'AB' , 'Canada', 'T8I 6T8', '+1 (403) 444-5554', '+1 (403) 444-5555', 'mcos@chinookcorp.com'
);

-- C. Task – Insert two new records into Customer table 
INSERT INTO CHINOOK.CUSTOMER VALUES
(
    60, 'Will', 'Turner', NULL, '234 Orange Cat Dr.', 'Seattle', 'WA', 'USA', '55555', '+1 (800) 555-5555', 
    '+1 (800) 555-5554', 'wturner@customer.com', 3
);

INSERT INTO CHINOOK.CUSTOMER VALUES
(
    61, 'Davy', 'Jones', NULL, '684 Flying Dutchman Rd.', 'Los Angelos', 'CA', 'USA', '44444', '+1 (804) 555-5555', 
    '+1 (804) 555-5554', 'djones@customer.com', 3
);


-- 2.4 UPDATE

-- A. Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CHINOOK.CUSTOMER SET FIRSTNAME='Robert' 
WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';
UPDATE CHINOOK.CUSTOMER SET LASTNAME='Walter' 
WHERE FIRSTNAME='Aaron' AND LASTNAME='Mitchell';

-- B. Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE CHINOOK.ARTIST SET NAME='CCR' WHERE NAME='Creedence Clearwater Revival';


-- 2.5 LIKE

-- A. Task – Select all invoices with a billing address like “T%”
SELECT * FROM CHINOOK.INVOICE WHERE BILLINGADDRESS LIKE 'T%';

-- 2.6 BETWEEN

-- A. Task – Select all invoices that have a total between 15 and 50
SELECT * FROM CHINOOK.INVOICE WHERE TOTAL > 15 AND TOTAL < 50;

-- B. Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM CHINOOK.EMPLOYEE WHERE HIREDATE > DATE '2003-06-01' AND HIREDATE < DATE '2004-03-1';

-- 2.7 DELETE

-- A. Task – Delete a record in Customer table where the name is Robert Walter (There may be
--  constraints that rely on this, find out how to resolve them). 
UPDATE CHINOOK.CUSTOMER SET SUPPORTREPID=NULL 
    WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';
DELETE FROM CHINOOK.CUSTOMER 
    WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';


-- 3.1 System Defined Functions

-- A. Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION GET_CURRENT_TIME
RETURN DATE
IS
RET DATE;
BEGIN
    RET := SYSDATE;
    RETURN RET;
END;
/

-- B. Task – create a function that returns the length of name in MEDIATYPE table        
CREATE OR REPLACE FUNCTION GET_NAME_LENGTH_MEDIATYPE
RETURN NUMBER
IS
RET NUMBER;
STR VARCHAR2;
CUR SYS_REFCURSOR;
BEGIN
    RET := 0;
    OPEN CUR FOR SELECT NAME FROM CHINOOK.MEDIATYPE;
    
    LOOP
        EXIT WHEN CUR%NOTFOUND;
        FETCH CUR INTO STR;
        IF RET < LENGTH(STR) THEN
            RET := LENGTH(STR);
        END IF;
    END LOOP;
END;
/

-- 3.2 System Defined Aggregate Functions

-- A. Task – Create a function that returns the average total of all invoices 
CREATE OR REPLACE FUNCTION GET_AVERAGE_TOTAL_INVOICE
RETURN NUMBER
IS
TOTAL_ NUMBER;
DIV_BY NUMBER;
BEGIN
    SELECT SUM(TOTAL) INTO TOTAL_ FROM CHINOOK.INVOICE;
    SELECT COUNT(TOTAL) INTO DIV_BY FROM CHINOOK.INVOICE;
    RETURN TOTAL_ / DIV_BY;
END;
/

-- B. Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION GET_MOST_EXPENSIVE_TRACK
RETURN SYS_REFCURSOR
IS
RET SYS_REFCURSOR;
BEGIN
    OPEN RET FOR
    SELECT * FROM CHINOOK.TRACK WHERE ROWNUM = 1 ORDER BY UNITPRICE desc;
    return ret;
END;
/

-- 3.3 User Defined Scalar Functions

-- A. Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION GET_AVERAGE_PRICE_INVOICELINE
RETURN NUMBER
IS
TOTAL_ NUMBER;
DIV_BY NUMBER;
BEGIN
    SELECT SUM(UNITPRICE) INTO TOTAL_ FROM CHINOOK.INVOICELINE;
    SELECT COUNT(UNITPRICE) INTO DIV_BY FROM CHINOOK.INVOICELINE;
    RETURN TOTAL_ / DIV_BY;
END;
/

-- 3.4 User Defined Table Valued Functions

-- A. Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION GET_AFTER_1968
RETURN SYS_REFCURSOR
IS
RET SYS_REFCURSOR;
BEGIN
    OPEN RET FOR
    SELECT * FROM CHINOOK.EMPLOYEE WHERE EXTRACT(YEAR FROM BIRTHDATE) > 1968;
    RETURN RET;
END;
/

-- 4.0 Stored Procedures

-- 4.1 Basic Stored Procedure

-- A. Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE GET_EMPLOYEE_NAMES(NAMES OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN NAMES FOR
        SELECT LASTNAME, FIRSTNAME FROM CHINOOK.EMPLOYEE;
END;
/

-- 4.2 Stored Procedure Input Parameters

-- A. Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE(ID_ IN NUMBER, FN IN VARCHAR2, LN IN VARCHAR2, NT IN VARCHAR2,
    REPORTS IN NUMBER, ADDRESS_ IN VARCHAR2, CITY_ IN VARCHAR2, STATE_ IN VARCHAR2, COUNTRY_ IN VARCHAR2,
    CODE_ IN VARCHAR2, PH IN VARCHAR2, FAX_ IN VARCHAR2, EMAIL_ IN VARCHAR2)
IS
BEGIN
    UPDATE CHINOOK.EMPLOYEE SET 
        LASTNAME = LN, FIRSTNAME = FN, TITLE = NT, REPORTSTO = REPORTS,
        ADDRESS = ADDRESS_, CITY = CITY_, STATE = STATE_, COUNTRY = COUNTRY_,
        POSTALCODE = CODE_, PHONE = PH, FAX = FAX_, EMAIL = EMAIL_
        WHERE EMPLOYEEID = ID_;
END;
/

-- B. Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE GET_MANAGER_BY_EMPLOYEE(EMP IN NUMBER, MAN OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN MAN FOR 
        SELECT * FROM CHINOOK.EMPLOYEE WHERE EMPLOYEEID = 
            (SELECT REPORTSTO FROM CHINOOK.EMPLOYEE WHERE EMPLOYEEID = EMP);
END;
/

-- 4.3 Stored Procedure Output Parameters

-- A. Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE GET_NAME_COMP_OF_CUST(CUST IN NUMBER,RET OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN RET FOR
        SELECT FIRSTNAME, LASTNAME, COMPANY FROM CHINOOK.CUSTOMER WHERE CUSTOMERID = CUST;
END;
/

-- 5.0 Transactions

-- A. Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints
--    that rely on this, find out how to resolve them).

-- Note: infor on declaring variables for transactions was found at 
-- https://docs.oracle.com/cd/B19306_01/appdev.102/b14261/sqloperations.htm
DECLARE
    INVOICE_TARGET NUMBER;
BEGIN
    -- NEED TO DELETE ENTRIES FROM INVOICE LINE THAT REFERENCE THE INVOICE BEFORE THE INVOICE ITSELF
    INVOICE_TARGET := 123; -- NOTE, CHANGE THIS TO AFFECT THE ENTIRE TRANSACTION
    DELETE FROM CHINOOK.INVOICELINE WHERE INVOICEID = INVOICE_TARGET;
    DELETE FROM CHINOOK.INVOICE WHERE INVOICEID = INVOICE_TARGET;
END;
/

-- B. Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE ADD_NEW_CUSTOMER(ID_ IN NUMBER, FN IN VARCHAR2, LN IN VARCHAR2, EM_ IN VARCHAR2)
IS
BEGIN
    BEGIN
        INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) 
            VALUES (ID_, FN, LN, EM_);
    END;
    
END;
/
-- 6.0 Triggers

-- 6.1 AFTER/FOR
/*
---------------------NOTE: Not sure if I should simply use Null.
---------------------If I can, then please remove the milti-line comment markers
---------------------therwise, treat it as I didn't do number 6


-- A. Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER TR_NEW_EMPLOYEE
AFTER INSERT ON CHINOOK.EMPLOYEE FOR EACH ROW
BEGIN
    --UPDATE CHINOOK.EMPLOYEE SET HIREDATE = SYSDATE WHERE
    --    HIREDATE = NULL;
    null;
END;
/

-- B. Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER TR_NEW_ALBUM
AFTER INSERT ON CHINOOK.ALBUM
BEGIN
    -- SELECT * FROM DUAL;
    null;
END;
/

-- C. Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER TR_NEW_CUSTOMER
AFTER INSERT ON CHINOOK.CUSTOMER FOR EACH ROW
BEGIN
    --UPDATE CHINOOK.CUSTOMER SET SUPPORTREPID = -1 WHERE
    --    SUPPORTREPID = NULL;
    null;
END;
/
*/
-- 7.0 JOINS

-- 7.1 INNER
-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CONCAT(CUS.FIRSTNAME, CONCAT(', ', CUS.LASTNAME)), VOICE.INVOICEID FROM
    CHINOOK.CUSTOMER CUS JOIN CHINOOK.INVOICE VOICE ON CUS.CUSTOMERID = VOICE.CUSTOMERID;

-- 7.2 OUTER
-- Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT CUS.CUSTOMERID, CUS.FIRSTNAME, CUS.LASTNAME, VOICE.INVOICEID,
    VOICE.TOTAL FROM CHINOOK.CUSTOMER CUS FULL JOIN CHINOOK.INVOICE VOICE ON CUS.CUSTOMERID = VOICE.CUSTOMERID;

-- 7.3 RIGHT
-- Task – Create a right join that joins album and artist specifying artist name and title.
SELECT AL.TITLE , AR.NAME FROM CHINOOK.ALBUM  AL RIGHT JOIN CHINOOK.ARTIST AR
    ON AL.ARTISTID = AR.ARTISTID;

-- 7.4 CROSS
-- Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT TITLE , NAME FROM CHINOOK.ALBUM CROSS JOIN CHINOOK.ARTIST ORDER BY NAME;

-- 7.5 SELF
-- Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT CONCAT(EMP1.FIRSTNAME, CONCAT(', ', EMP1.LASTNAME)) EMPLOYEE_, 
    CONCAT(EMP2.FIRSTNAME, CONCAT(', ', EMP2.LASTNAME)) REPORTS_TO
    FROM CHINOOK.EMPLOYEE EMP1, CHINOOK.EMPLOYEE EMP2
    WHERE EMP1.REPORTSTO = EMP2.EMPLOYEEID;
