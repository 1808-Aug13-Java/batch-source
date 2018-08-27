/*
JULIE SEALS SQL Lab

2.0 SQL Queries
In this section you will be performing various queries against the Oracle Chinook database.
*/


/*2.1 SELECT
Task – Select all records from the Employee table.
Task – Select all records from the Employee table where last name is King.
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.//
*/
SELECT * 
FROM CHINOOK.EMPLOYEE;

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE LASTNAME = 'King';

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

/*2.2 ORDER BY
Task – Select all albums in Album table and sort result set in descending order by title.
Task – Select first name from Customer and sort result set in ascending order by city
*/
SELECT * 
FROM CHINOOK.ALBUM
ORDER BY TITLE DESC;

SELECT FIRSTNAME
FROM CHINOOK.CUSTOMER
ORDER BY CITY;

/*
2.3 INSERT INTO
Task – Insert two new records into Genre table 
Task – Insert two new records into Employee table
Task – Insert two new records into Customer table 
*/
INSERT INTO CHINOOK.GENRE VALUES (26, 'Bluegrass');
INSERT INTO CHINOOK.GENRE VALUES (27, 'Math hop');

INSERT INTO CHINOOK.EMPLOYEE VALUES (9, 'Maki', 'Thomas', 'Head of Security',NULL, DATE '1980-01-02', DATE '2012-02-12', '918 Tulsa Rd', 'Calgary', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 427-8373', '+1 (780) 427-8374', 'thomasm@chinookcorp.com');
INSERT INTO CHINOOK.EMPLOYEE VALUES (10, 'Jennifer', 'Max', 'Security', 9, DATE '1989-07-31', DATE '2017-05-16', '256 Hazelnut Rd', 'Calgary', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 438-9238', '+1 (780) 427-8374', 'max@chinookcorp.com');

INSERT INTO CHINOOK.CUSTOMER VALUES (60, 'Rick', 'Peterson', NULL, '212 Delachaise St', 'New Orleans', 'LA', 'USA', '70101', '504-232-4988', NULL, 'rickpeterson@email.com', 3);
INSERT INTO CHINOOK.CUSTOMER VALUES (61, 'Helen', 'Troy', 'Nightingale Theatre Co.', 'Route 3 Box 3 Woodlawn Farms', 'Claremore', 'OK', 'USA', '75121', '585-343-5079', NULL, 'shipslaunched@email.com', 5);

/*
2.4 UPDATE
Task – Update Aaron Mitchell in Customer table to Robert Walter
Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
*/

UPDATE CHINOOK.CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE CUSTOMERID = 32;

UPDATE CHINOOK.ARTIST
SET  NAME = 'CCR'
WHERE ARTISTID = 76;

/*
2.5 LIKE
Task – Select all invoices with a billing address like “T%”
*/

SELECT *
FROM CHINOOK.INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

/*
2.6 BETWEEN
Task – Select all invoices that have a total between 15 and 50
Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
*/
SELECT *
FROM CHINOOK.INVOICE 
WHERE TOTAL BETWEEN 15 AND 50;

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE HIREDATE BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

/*
2.7 DELETE
Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).	
*/
--KEEPING IN THE LONG JOURNEY OF MISTAKES I MADE IN SOLVING THIS SO I CAN REMEMBER TO NOT MAKE THEM AGAIN
ALTER TABLE CHINOOK.CUSTOMER
DROP CONSTRAINT FK_CUSTOMERSUPPORTREPID;

ALTER TABLE CHINOOK.INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;

UPDATE CHINOOK.CUSTOMER
SET SUPPORTREPID = NULL
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
COMMIT;

--ACTUAL TASK--
DELETE FROM CHINOOK.CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--ADDING BACK IN THE CONSTRAINTS
ALTER TABLE CHINOOK.CUSTOMER
ADD CONSTRAINT FK_CUSTOMERSUPPORTREPID
    FOREIGN KEY (SUPPORTREPID) REFERENCES CHINOOK.EMPLOYEE (EMPLOYEEID);
COMMIT;

--HOW I SHOULD HAVE JUST DONE THINGS TO BEGIN WITH
ALTER TABLE CHINOOK.INVOICE 
ADD CONSTRAINT FK_CUSTOMERINVOICEID
    FOREIGN KEY (CUSTOMERID) REFERENCES CHINOOK.CUSTOMER (CUSTOMERID) ON DELETE CASCADE;

/*
HOW I ENDED UP HAVING TO FIX JUST DROPPING THE CONSTRAINT ON THE INVOICE FK
KEEPING IN FOR POSTERITY SO I WILL REMEMBER IN THE FUTURE
TO ALTER A TABLE'S FK TO EITHER SET NULL OR DELETE ON CASCADE

select CUSTOMERID from CHINOOK.INVOICE 
where 
CUSTOMERID not in (select CUSTOMERID from CHINOOK.CUSTOMER);

SELECT *
FROM CHINOOK.INVOICE
WHERE CUSTOMERID = 32;

--THERE WERE MULTIPLE RECORDS FROM INVOICE AND INVOICELINE 
--I HAD TO GO BACK AND DELETE
DELETE FROM CHINOOK.INVOICE
WHERE INVOICEID = 61;

DELETE FROM CHINOOK.INVOICELINE
WHERE INVOICEID = 61;

SELECT *
FROM CHINOOK.INVOICELINE
WHERE INVOICEID = 268; */


/*
SQL Functions
In this section you will be using the Oracle system functions, 
as well as your own functions, to perform various actions against the database
3.1 System Defined Functions
Task – Create a function that returns the current time.
Task – create a function that returns the length of name in MEDIATYPE table		
*/
CREATE OR REPLACE FUNCTION CURRENT_TIME
RETURN TIMESTAMP
IS
BEGIN
    RETURN LOCALTIMESTAMP();
END;
/
/***/
CREATE OR REPLACE FUNCTION NAME_LENGTH
RETURN CHINOOK.MEDIATYPE.NAME%TYPE
IS
    NAMELENGTH CHINOOK.mediatype.NAME%TYPE;
BEGIN
    SELECT LENGTH(NAME)
    INTO NAMELENGTH
    FROM CHINOOK.MEDIATYPE
    RETURN NAMELENGTH;
END;
/

/*
3.2 System Defined Aggregate Functions
Task – Create a function that returns the average total of all invoices 
Task – Create a function that returns the most expensive track
*/
/***/
CREATE OR REPLACE FUNCTION INV_AVG
RETURN CHINOOK.INVOICE.TOTAL%TYPE
IS
    TOTAL_AVG CHINOOK.INVOICE.TOTAL%TYPE;
BEGIN
    SELECT AVG(TOTAL)
    INTO TOTAL_AVG
    FROM CHINOOK.INVOICE;
    RETURN TOTAL_AVG;
END;
/
/***/
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE
RETURN CHINOOK.INVOICE.TOTAL%TYPE
IS
    HIGHEST CHINOOK.INVOICE.TOTAL%TYPE;
BEGIN
    SELECT MAX(TOTAL)
    INTO HIGHEST
    FROM CHINOOK.INVOICE;
    RETURN HIGHEST;
END;
/

/*
3.3 User Defined Scalar Functions
Task – Create a function that returns the average price of invoiceline items in the invoiceline table
*/
/***/
CREATE OR REPLACE FUNCTION INV_LINE_AVG
RETURN CHINOOK.INVOICELINE.UNITPRICE%TYPE
IS
    TOTAL_AVG CHINOOK.INVOICELINE.UNITPRICE%TYPE;
BEGIN
    SELECT AVG(UNITPRICE)
    INTO TOTAL_AVG
    FROM CHINOOK.INVOICELINE;
    RETURN TOTAL_AVG;
END;
/

/*
3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.
*/
/***/
CREATE OR REPLACE FUNCTION BORN_1968(BIRTHDAY IN DATE, EID IN NUMBER)
RETURN NUMBER
IS
    GENX_EMP NUMBER;
BEGIN
    IF BIRTHDAY > DATE '1968-12-31' THEN
    GENX_EMP := EID;
    END IF;
    RETURN GENX_EMP;
END;
/

/*
4.0 Stored Procedures
 In this section you will be creating and executing stored procedures. 
 You will be creating various types of stored procedures that take input and output parameters.
4.1 Basic Stored Procedure
Task – Create a stored procedure that selects the first and last names of all the employees.
*/
CREATE OR REPLACE PROCEDURE FNAME_LNAME(S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR 
    SELECT FIRSTNAME, LASTNAME 
    FROM CHINOOK.EMPLOYEE;
END;
/

/* Testing the procedure
DECLARE
    SVAR SYS_REFCURSOR;
    TEMP_FNAME CHINOOK.EMPLOYEE.FIRSTNAME%TYPE;
    TEMP_LNAME CHINOOK.EMPLOYEE.LASTNAME%TYPE; 
BEGIN 
    FNAME_LNAME(SVAR);
    LOOP
        FETCH SVAR INTO TEMP_FNAME, TEMP_LNAME;
        EXIT WHEN SVAR%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(TEMP_FNAME||' IS FIRST NAME, '||TEMP_LNAME||' IS LAST NAME');
    END LOOP;
    CLOSE SVAR;
END;
*/

/*
4.2 Stored Procedure Input Parameters
Task – Create a stored procedure that updates the personal information of an employee.
Task – Create a stored procedure that returns the managers of an employee.
*/
/***/
CREATE OR REPLACE PROCEDURE UPDATE_EMP( 
        NEW_LNAME CHINOOK.EMPLOYEE.LASTNAME%TYPE, 
        NEW_FNAME CHINOOK.EMPLOYEE.FIRSTNAME%TYPE,
        NEW_TITLE CHINOOK.EMPLOYEE.TITLE%TYPE, 
        NEW_REPORTSTO NUMBER, 
        NEW_BIRTHDATE DATE, 
        NEW_HIREDATE DATE, 
        NEW_ADDRESS CHINOOK.EMPLOYEE.ADDRESS%TYPE, 
        NEW_CITY CHINOOK.EMPLOYEE.CITY%TYPE,
        NEW_STATE CHINOOK.EMPLOYEE.STATE%TYPE, 
        NEW_COUNTRY CHINOOK.EMPLOYEE.COUNTRY%TYPE, 
        NEW_POSTALCODE CHINOOK.EMPLOYEE.POSTALCODE%TYPE, 
        NEW_PHONE CHINOOK.EMPLOYEE.PHONE%TYPE,
        NEW_FAX CHINOOK.EMPLOYEE.FAX%TYPE, 
        NEW_EMAIL CHINOOK.EMPLOYEE.EMAIL%TYPE,
        EID NUMBER)
IS
BEGIN
    UPDATE CHINOOK.EMPLOYEE
    SET LASTNAME = 
    CASE WHEN NEW_LNAME IS NULL
        THEN LASTNAME 
        ELSE NEW_LNAME
        END,
    FIRSTNAME = 
    CASE WHEN NEW_FNAME IS NULL
        THEN FIRSTNAME
        ELSE NEW_FNAME
        END,
    TITLE = 
    CASE WHEN NEW_TITLE IS NULL
        THEN TITLE
        ELSE NEW_TITLE
        END,
    REPORTSTO = 
    CASE WHEN NEW_REPORTSTO IS NULL
        THEN REPORTSTO
        ELSE NEW_REPORTSTO
        END,
    BIRTHDATE = 
    CASE WHEN NEW_BIRTHDATE IS NULL
        THEN BIRTHDATE
        ELSE NEW_BIRTHDATE
        END,
    HIREDATE = 
        CASE WHEN NEW_HIREDATE IS NULL
        THEN HIREDATE
        ELSE NEW_HIREDATE
        END,
    ADDRESS = 
    CASE WHEN NEW_ADDRESS IS NULL
        THEN ADDRESS
        ELSE NEW_ADDRESS
        END,
    CITY = 
    CASE WHEN NEW_CITY IS NULL
        THEN CITY
        ELSE NEW_CITY
        END,
    STATE = 
    CASE WHEN NEW_STATE IS NULL
        THEN STATE
        ELSE NEW_STATE
        END,
    COUNTRY = 
    CASE WHEN NEW_COUNTRY IS NULL
        THEN COUNTRY
        ELSE NEW_COUNTRY
        END,
    POSTALCODE = 
    CASE WHEN NEW_POSTALCODE IS NULL
        THEN POSTALCODE
        ELSE NEW_POSTALCODE
        END,
    PHONE = 
    CASE WHEN NEW_PHONE IS NULL
        THEN PHONE
        ELSE NEW_PHONE
        END,
    FAX = 
    CASE WHEN NEW_FAX IS NULL
        THEN FAX
        ELSE NEW_FAX
        END,
    EMAIL = 
    CASE WHEN NEW_EMAIL IS NULL
        THEN EMAIL
        ELSE NEW_EMAIL
        END
    WHERE EID = EMPLOYEEID;
END;
/

CREATE OR REPLACE PROCEDURE SHOW_MANAGER(BOSS OUT CHINOOK.EMPLOYEE.REPORTSTO%TYPE)
IS
BEGIN
    SELECT REPORTSTO INTO BOSS
    FROM CHINOOK.EMPLOYEE
    WHERE EMPLOYEEID = REPORTSTO;
END;
/

/*
4.3 Stored Procedure Output Parameters
Task – Create a stored procedure that returns the name and company of a customer.
*/

CREATE OR REPLACE PROCEDURE CUST_NAME_CO(C_LNAME OUT CHINOOK.CUSTOMER.LASTNAME%TYPE, 
                            C_FNAME OUT CHINOOK.CUSTOMER.FIRSTNAME%TYPE,
                            CO OUT CHINOOK.CUSTOMER.COMPANY%TYPE)
IS
BEGIN
    SELECT LASTNAME INTO C_LNAME
        FROM CHINOOK.CUSTOMER;
    SELECT FIRSTNAME INTO C_FNAME
    FROM CHINOOK.CUSTOMER;
    SELECT COMPANY INTO CO
    FROM CHINOOK.CUSTOMER;
END;
/


/*
5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, 
find out how to resolve them).
Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
*/
SELECT * FROM
CHINOOK.INVOICELINE;

/*
ALTER TABLE CHINOOK.INVOICELINE 
ADD CONSTRAINT FK_INVOICEID
    FOREIGN KEY (INVOICEID) REFERENCES CHINOOK.INVOICE (INVOICEID) ON DELETE CASCADE;
    */

CREATE OR REPLACE PROCEDURE DELETE_INV(INV_ID IN CHINOOK.INVOICE.INVOICEID%TYPE)
IS
BEGIN
    DELETE FROM CHINOOK.INVOICE
    WHERE INVOICEID = INV_ID;
END;
/

/*
6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.

6.1 AFTER/FOR
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
*/

SET SERVEROUTPUT ON;
CREATE OR REPLACE TRIGGER TR_AFTER_EMP_INSERT
AFTER INSERT ON CHINOOK.EMPLOYEE
FOR EACH ROW 
BEGIN
    DBMS_OUTPUT.PUT_LINE('RECORD SUCCESSFULLY INSERTED');
END;
/

CREATE OR REPLACE TRIGGER TR_AFTER_ALBUM_UPDATE
AFTER UPDATE ON CHINOOK.ALBUM
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('RECORD SUCCESSFULLY UPDATED');
END;
/

CREATE OR REPLACE TRIGGER TR_AFTER_CUSTOMER_DELETE
AFTER DELETE ON CHINOOK.CUSTOMER
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('CUSTOMER RECORD HAS BEEN DELETED');
END;
/
/*
7.0 JOINS
In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, 
right, left, cross, and self joins.
7.1 INNER
Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
*/
SELECT C.FIRSTNAME FIRST_NAME, C.LASTNAME LAST_NAME, I.INVOICEID INVOICE_NUMBER
FROM CHINOOK.CUSTOMER C, CHINOOK.INVOICE I
WHERE C.CUSTOMERID = I.CUSTOMERID;
/*
7.2 OUTER
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, 
invoiceId, and total.
*/
SELECT 
    C.CUSTOMERID CUSTOMER_ID, 
    C.FIRSTNAME FIRST_NAME, 
    C.LASTNAME LAST_NAME, 
    I.INVOICEID INVOICE_ID, 
    I.TOTAL
FROM CHINOOK.CUSTOMER C
FULL JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID
ORDER BY I.INVOICEID;

/*
7.3 RIGHT
Task – Create a right join that joins album and artist specifying artist name and title.
*/
SELECT
    ART.NAME ARTIST,
    AL.TITLE TITLE
FROM CHINOOK.ARTIST ART
RIGHT JOIN CHINOOK.ALBUM AL
ON ART.ARTISTID = AL.ARTISTID
ORDER BY ART.NAME;

/*
7.4 CROSS
Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
*/
SELECT *
FROM CHINOOK.ALBUM AL
CROSS JOIN CHINOOK.ARTIST A
ORDER BY A.NAME;
    
/*
7.5 SELF
Task – Perform a self-join on the employee table, joining on the reportsto column.
*/
--TO MAKE THE QUERY MORE MEANINGFUL, I CHOSE TO SELECT THE EMPLOYEES' NAMES TO DISPLAY
SELECT  E1.LASTNAME EMP1_LASTNAME, E1.FIRSTNAME EMP1_FIRSTNAME, E2.LASTNAME EMP2_LASTNAME, E2.FIRSTNAME EMP2_FIRSTNAME, E1.REPORTSTO MANAGER
FROM CHINOOK.EMPLOYEE E1, CHINOOK.EMPLOYEE E2
WHERE E1.LASTNAME <> E2.LASTNAME AND E1.FIRSTNAME <> E2.FIRSTNAME
AND E1.REPORTSTO = E2.REPORTSTO
ORDER BY E1.REPORTSTO;

