/*------------------------------------------------------------------------------
2.0 SQL Queries
In this section you will be performing various queries against the Oracle Chinook database.
*/------------------------------------------------------------------------------

/*
2.1 SELECT
Task – Select all records from the Employee table.
Task – Select all records from the Employee table where last name is King.
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.// 
*/

-- Task – Select all records from the Employee table.
SELECT *
FROM chinook.EMPLOYEE;

-- Task – Select all records from the Employee table where last name is King.
SELECT *
FROM CHINOOK.EMPLOYEE
WHERE LASTNAME='King';

-- Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT *
FROM CHINOOK.EMPLOYEE
WHERE FIRSTNAME='Andrew' AND REPORTSTO IS NULL;

/*
2.2 ORDER BY
Task – Select all albums in Album table and sort result set in descending order by title.
Task – Select first name from Customer and sort result set in ascending order by city
*/

-- Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM CHINOOK.ALBUM
ORDER BY TITLE DESC;

-- Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME, CITY
FROM CHINOOK.CUSTOMER
ORDER BY CITY ASC;

/*
2.3 INSERT INTO
Task – Insert two new records into Genre table 
Task – Insert two new records into Employee table
Task – Insert two new records into Customer table 
*/

-- Task – Insert two new records into Genre table 
INSERT INTO CHINOOK.Genre (GenreId, Name) VALUES (26, 'Wizard Rock');
INSERT INTO CHINOOK.Genre (GenreId, Name) VALUES (27, 'K Pop');

-- Task – Insert two new records into Employee table
INSERT INTO CHINOOK.Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (9, 'Kholeka', 'Okuzon', 'IT Staff', 6, TO_DATE('1999-9-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2010-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), '3406 Widecrest Lane', 'Calgary', 'AB', 'Canada', 'T1Y 1P9', '+1 (403) 455-9988', '+1 (403) 455-9980', 'okuzon@chinookcorp.com');
INSERT INTO CHINOOK.Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10, 'Granger', 'Hermione', 'IT Staff', 6, TO_DATE('1997-1-8 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2008-3-5 00:00:00','yyyy-mm-dd hh24:mi:ss'), '934 10 ST NE', 'Camrose', 'AB', 'Canada', 'T4V 1R8', '+1 (403) 468-3358', '+1 (403) 468-3350', 'hermione@chinookcorp.com');

-- Task – Insert two new records into Customer table
INSERT INTO CHINOOK.Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (60, 'Millie', 'Nozuko', '74,Coral Springs Blvd', 'Cape Town', 'South Africa', '12345', '+91 0123 39883998', 'nozuko.millie@gmail.com', 3);
INSERT INTO CHINOOK.Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (61, 'Mandela', 'Zandile', '3,Tshwane Road', 'Tshwane', 'South Africa', '76549', '+91 081 22289899', 'zandile_mandela@yahoo.com', 3);

/*
2.4 UPDATE
Task – Update Aaron Mitchell in Customer table to Robert Walter
Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
*/

-- Task – Update Aaron Mitchell in Customer table to Robert Walter
-- NOTE: AARON MITCHELL CUSTOMER_ID = 32
UPDATE CHINOOK.CUSTOMER
SET LastName = 'Walter',
    FirstName = 'Robert'
WHERE LastName = 'Mitchell'
AND FirstName = 'Aaron';

-- Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE CHINOOK.ARTIST
SET NAME = 'CCR'
WHERE NAME= 'Creedence Clearwater Revival';

/*
2.5 LIKE
Task – Select all invoices with a billing address like “T%”
*/

-- Task – Select all invoices with a billing address like “T%”
SELECT *
FROM CHINOOK.INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

/*
2.6 BETWEEN
Task – Select all invoices that have a total between 15 and 50
Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
*/

-- Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM CHINOOK.INVOICE
WHERE TOTAL BETWEEN 15 AND 50;

-- Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM CHINOOK.EMPLOYEE
WHERE HIREDATE BETWEEN DATE'2003-06-01' AND DATE'2004-03-31';

/*
2.7 DELETE
Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).	
*/

-- Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).	
-- THIS HAS FOREIGN KEY CONSTRAINT. FOREIGN KEY IS: FK_CustomerSupportRepId
-- FOREGN KEY GOES FROM the child to the parent. SO, IT'S LIKE (CHILD) INVOICE -> CUSTOMER (PARENT) -> EMPLOYEE (GRANDPARENT) (??)
-- DELETE CHILD OF CHILD:
DELETE FROM INVOICELINE
WHERE INVOICEID IN (
    SELECT INVOICEID
    FROM INVOICE --PARENT
    WHERE CUSTOMERID IN (
        SELECT CUSTOMERID
        FROM CUSTOMER --PARENT
        WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
        )
    ); -- 38 rows deleted.
-- DELETE CHILD:
DELETE FROM INVOICE -- integrity constraint (CHINOOK.FK_INVOICELINEINVOICEID) violated - child record found
WHERE CUSTOMERID IN (
    SELECT CUSTOMERID
    FROM CUSTOMER --PARENT
    WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
    ); -- 7 rows deleted.
    
-- DELETE PARENT:
DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'; -- integrity constraint (CHINOOK.FK_INVOICECUSTOMERID) violated - child record found
-- 1 row deleted.

/*------------------------------------------------------------------------------
3.0 SQL Functions
In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
*/------------------------------------------------------------------------------

/*
3.1 System Defined Functions
Task – Create a function that returns the current time.
Task – create a function that returns the length of name in MEDIATYPE table
*/
SET SERVEROUTPUT ON;

-- Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION THE_DATE
RETURN DATE
IS
BEGIN
    RETURN CURRENT_TIMESTAMP;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE(THE_DATE());
END;
/

-- Task – create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION LENGTH_OF_NAME(X IN VARCHAR2)
RETURN NUMBER
IS
BEGIN
    RETURN LENGTH(X);
END;
/

SELECT LENGTH_OF_NAME(NAME)
FROM CHINOOK.MEDIATYPE;

/*
3.2 System Defined Aggregate Functions
Task – Create a function that returns the average total of all invoices 
Task – Create a function that returns the most expensive track
*/

SAVEPOINT S1;
-- Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION AVG_TOTAL_OF_INVOICES(TOTAL IN NUMBER)
RETURN NUMBER
IS
    FINALNUM NUMBER;
    COUNTING NUMBER;
    Y NUMBER;
BEGIN
    SELECT SUM (TOTAL) INTO FINALNUM
    FROM CHINOOK.INVOICE;
    SELECT COUNT (TOTAL) INTO COUNTING
    FROM CHINOOK.INVOICE;
    Y := (FINALNUM/COUNTING);
    RETURN Y;
END;
/

SELECT distinct AVG_TOTAL_OF_INVOICES(TOTAL)
FROM CHINOOK.INVOICE;

-- Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MOST_EXP_TRACK(COSTING IN NUMBER)
RETURN VARCHAR2
IS
    MAX_TRACK NUMBER;
    THE_NAME CHINOOK.TRACK.NAME%TYPE;
BEGIN
    SELECT NAME INTO THE_NAME
    FROM (
        SELECT *
        FROM CHINOOK.TRACK
        ORDER BY CHINOOK.TRACK.UNITPRICE DESC
        )
    WHERE ROWNUM = 1;
    RETURN THE_NAME;
END;
/
-- RESULT SHOULD BE 1.99

SELECT DISTINCT MOST_EXP_TRACK(UNITPRICE)
FROM CHINOOK.TRACK;
/

/*
3.3 User Defined Scalar Functions
Task – Create a function that returns the average price of invoiceline items in the invoiceline table
*/
CREATE OR REPLACE FUNCTION AVG_TOTAL_OF_INVOICELINE(UNITPRICE CHINOOK.INVOICELINE.UNITPRICE%TYPE)
RETURN NUMBER
IS
    FINALNUM CHINOOK.INVOICELINE.UNITPRICE%TYPE;
    COUNTING CHINOOK.INVOICELINE.UNITPRICE%TYPE;
    Y NUMBER;
BEGIN
    SELECT SUM (UNITPRICE) INTO FINALNUM
    FROM CHINOOK.INVOICELINE;
    SELECT COUNT (UNITPRICE) INTO COUNTING
    FROM CHINOOK.INVOICELINE;
    Y := (FINALNUM/COUNTING);
    RETURN Y;
END;
/
--RESULT SHOULD BE 1.03955

SELECT DISTINCT AVG_TOTAL_OF_INVOICELINE(UNITPRICE)
FROM CHINOOK.INVOICELINE; 

/*
3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.
*/

-- Task – Create a function that returns all employees who are born after 1968.

-- MAY HAVE TO RUN DROP TYPE RESULT_TABLE AND DROP TYPE EMPLOYEE_OBJ BEFORE RUNNING THESE BLOCKS ONE AFTER THE OTHER IN ORDER
-- AVOID COMPILATION ISSUES

-- Create Object of your table
CREATE TYPE EMPLOYEE_OBJ AS OBJECT (
    EmployeeId NUMBER,
    LastName VARCHAR2(20),
    FirstName VARCHAR2(20),
    Title VARCHAR2(30),
    ReportsTo NUMBER,
    BirthDate DATE,
    HireDate DATE,
    Address VARCHAR2(70),
    City VARCHAR2(40),
    State VARCHAR2(40),
    Country VARCHAR2(40),
    PostalCode VARCHAR2(10),
    Phone VARCHAR2(24),
    Fax VARCHAR2(24),
    Email VARCHAR2(60)
);

--Create a type of your object 
CREATE TYPE RESULT_TABLE AS TABLE OF EMPLOYEE_OBJ;
/

--Create function that uses the type created as Return Type; 
CREATE OR REPLACE FUNCTION YOUNG_EMPLOYEES
RETURN RESULT_TABLE
PIPELINED
AS
    CURSOR MY_CURSOR
    IS
        SELECT *
        FROM CHINOOK.EMPLOYEE
        WHERE BIRTHDATE > DATE'1968-12-31';
BEGIN

   FOR i IN MY_CURSOR
   LOOP
      PIPE ROW (EMPLOYEE_OBJ (i.EmployeeId, i.LastName, i.FirstName, i.Title, 
      i.ReportsTo, i.BirthDate, i.HireDate, i.Address, i.City, i.State,
      i.Country, i.PostalCode, i.Phone, i.Fax, i.Email));
      EXIT WHEN MY_CURSOR%NOTFOUND;

   END LOOP;

   RETURN;
END;
/


/*------------------------------------------------------------------------------
4.0 Stored Procedures
In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
*/------------------------------------------------------------------------------

/*
4.1 Basic Stored Procedure
Task – Create a stored procedure that selects the first and last names of all the employees.
*/

-- Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE FIRST_LAST_PROCEDURE(S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/

DECLARE
    SVAR SYS_REFCURSOR;
    TEMP_FIRSTNAME EMPLOYEE.FIRSTNAME%TYPE;
    TEMP_LASTNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
    FIRST_LAST_PROCEDURE(SVAR);
    -- HOW WE SHOULD HAVE ACCESS TO OUR EMPLOYEES' NAMES THROUGH SVAR
    LOOP
        FETCH SVAR INTO TEMP_FIRSTNAME, TEMP_LASTNAME; -- "ACTIVE SET" IS EACH ROW RETURNED BY THE CURSOR
        EXIT WHEN SVAR%NOTFOUND; -- IF WE LOOP AROUND AND SVAR DOESNT FINT ANY NAME FOR TEMP_FIRSTNAME & TEMP_LASTNAME THEN STOP
        DBMS_OUTPUT.PUT_LINE('NAME IS: '||TEMP_FIRSTNAME||' '||TEMP_LASTNAME);
    END LOOP;
    CLOSE SVAR;
END;
/

 /*
 4.2 Stored Procedure Input Parameters
Task – Create a stored procedure that updates the personal information of an employee.
Task – Create a stored procedure that returns the managers of an employee.
*/

-- Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_PI(
	   EMP_IDENT IN EMPLOYEE.EMPLOYEEID%TYPE,
	   EMP_BIRTH IN EMPLOYEE.BIRTHDATE%TYPE,
       EMP_PHONE IN EMPLOYEE.PHONE%TYPE,
       EMP_EMAIL IN EMPLOYEE.EMAIL%TYPE)
IS
BEGIN
  UPDATE EMPLOYEE
  SET   BIRTHDATE = EMP_BIRTH,
        PHONE = EMP_PHONE,
        EMAIL = EMP_EMAIL
  WHERE EMPLOYEEID = EMP_IDENT;
END;
/

BEGIN
   UPDATE_PI(2, DATE'2000-01-01', '+1 (512) 455-5555', 'edwards@chinookcorp.com');
END;
/

SELECT * FROM EMPLOYEE; -- just to check

-- Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE GET_MANAGERS(S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR
    SELECT EMP1.EMPLOYEEID, EMP2.FIRSTNAME MANAGER_FIRSTNAME, EMP2.LASTNAME MANAGER_LASTNAME
    FROM EMPLOYEE EMP1, EMPLOYEE EMP2
    WHERE EMP1.REPORTSTO = EMP2.EMPLOYEEID;
END;
/

DECLARE
    SVAR2 SYS_REFCURSOR;
    TEMP_EMP_ID EMPLOYEE.EMPLOYEEID%TYPE;
    TEMP_EMP_FIRSTNAME EMPLOYEE.FIRSTNAME%TYPE;
    TEMP_EMP_LASTNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
    GET_MANAGERS(SVAR2);
    LOOP
        FETCH SVAR2 INTO TEMP_EMP_ID, TEMP_EMP_FIRSTNAME, TEMP_EMP_LASTNAME;
        EXIT WHEN SVAR2%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('MANAGER ' || TEMP_EMP_FIRSTNAME || ' ' ||TEMP_EMP_LASTNAME || ' MANAGES EMPLOYEE ID: ' || TEMP_EMP_ID);
    END LOOP;
    CLOSE SVAR2;
END;
/

/*
4.3 Stored Procedure Output Parameters
Task – Create a stored procedure that returns the name and company of a customer.
*/

-- Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE GET_NAME_COMPANY(S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME, COMPANY
    FROM CUSTOMER;
END;
/

DECLARE
    SVAR3 SYS_REFCURSOR;
    TEMP_FIRSTNAME CUSTOMER.FIRSTNAME%TYPE;
    TEMP_LASTNAME CUSTOMER.LASTNAME%TYPE;
    TEMP_COMPANY CUSTOMER.COMPANY%TYPE;
BEGIN
    GET_NAME_COMPANY(SVAR3);
    LOOP
        FETCH SVAR3 INTO TEMP_FIRSTNAME, TEMP_LASTNAME, TEMP_COMPANY;
        EXIT WHEN SVAR3%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('NAME:  '||TEMP_FIRSTNAME||' '||TEMP_LASTNAME||' , COMPANY:  '||TEMP_COMPANY);
    END LOOP;
    CLOSE SVAR3;
END;
/

/*
5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
*/

-- Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE DELETE_INVOICE(INV_ID IN INVOICE.INVOICEID%TYPE)
IS
BEGIN

    SAVEPOINT DELETE_INVOICE_SP1;
    -- DELETE CHILD:
    DELETE FROM INVOICELINE
    WHERE INVOICEID = INV_ID;
       
    SAVEPOINT DELETE_INVOICE_SP2; 
    -- DELETE PARENT:
    DELETE FROM INVOICE
    WHERE INVOICEID = INV_ID;
    
    COMMIT;
END;
/

BEGIN
    DELETE_INVOICE(412);
END;
/

-- Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE INSERT_CUSTOMER(CUST_ID IN CUSTOMER.CUSTOMERID%TYPE, FN IN CUSTOMER.FIRSTNAME%TYPE,
LN IN CUSTOMER.LASTNAME%TYPE, CO IN CUSTOMER.COMPANY%TYPE, AD IN CUSTOMER.ADDRESS%TYPE, CY IN CUSTOMER.CITY%TYPE, 
ST IN CUSTOMER.STATE%TYPE, CNTRY IN CUSTOMER.COUNTRY%TYPE, PC IN CUSTOMER.POSTALCODE%TYPE, PH IN CUSTOMER.PHONE%TYPE, 
FX IN CUSTOMER.FAX%TYPE, EML IN CUSTOMER.EMAIL%TYPE, SUP_REPID IN CUSTOMER.SUPPORTREPID%TYPE)
IS
BEGIN
       
    SAVEPOINT INSERT_CUSTOMER_SP1; 

    INSERT INTO CUSTOMER
    VALUES (CUST_ID, FN, LN, CO, AD, CY, ST, CNTRY, PC, PH, FX, EML, SUP_REPID);
    
    COMMIT;
END;
/

BEGIN
    INSERT_CUSTOMER(62, 'Marco', 'Alvares', 'The Process', 'Barrio Fino Lima, 2290', 
    'Itapura', 'SP', 'Brazil', '12228-000', '+55 (13) 4823-6655', '+55 (13) 3983-5666', 'marcoalv@embraer.com.br', 3);
END;
/

/*------------------------------------------------------------------------------
6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
*/------------------------------------------------------------------------------

/*
6.1 AFTER/FOR
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table
*/

-- Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
-- CREATING SEQUENCE FOR TABLE
CREATE SEQUENCE SQ_INSERT_EMPLOYEE_PK
START WITH 9
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
AFTER INSERT ON CHINOOK.EMPLOYEE
FOR EACH ROW
declare
employeeid employee.employeeid%type;
BEGIN
   SELECT SQ_INSERT_EMPLOYEE_PK.NEXTVAL INTO employeeid FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('Record successfully INSTERTED into EMPLOYEE table');
END;
/

-- Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE SEQUENCE SQ_UPDATE_ALBUM_PK
START WITH 348
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_UPDATE_ALBUM
AFTER UPDATE ON CHINOOK.ALBUM
FOR EACH ROW
declare
A_ID ALBUM.ALBUMID%TYPE;
BEGIN
    SELECT SQ_UPDATE_ALBUM_PK.NEXTVAL INTO A_ID FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('Record successfully UPDATED in ALBUM table');
END;
/

-- Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table
CREATE SEQUENCE SQ_DELETE_CUSTOMER_PK
START WITH 63
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_DELETE_CUSTOMER
AFTER UPDATE ON CHINOOK.ALBUM
FOR EACH ROW
declare
CUST_ID CUSTOMER.CUSTOMERID%TYPE;
BEGIN
    --SELECT SQ_DELETE_CUSTOMER_PK.NEXTVAL INTO CUST_ID FROM DUAL;
    DBMS_OUTPUT.PUT_LINE('Record successfully DELETED from CUSTOMER table');
END;
/

/*------------------------------------------------------------------------------
7.0 JOINS
In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
*/------------------------------------------------------------------------------

/*
7.1 INNER
Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
*/

-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT C.FIRSTNAME, C.LASTNAME, I.INVOICEID
FROM CUSTOMER C
JOIN INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;

/*
7.2 OUTER
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/

-- Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME, I.INVOICEID, I.TOTAL
FROM CUSTOMER C
FULL JOIN INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;

/*
7.3 RIGHT
Task – Create a right join that joins album and artist specifying artist name and title.
*/

-- Task – Create a right join that joins album and artist specifying artist name and title.
SELECT R.NAME AS ARTIST_NAME, A.TITLE AS ALBUM_TITLE
FROM ALBUM A
RIGHT JOIN ARTIST R
ON A.ARTISTID = R.ARTISTID;

/*
7.4 CROSS
Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
*/

-- Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT NAME, TITLE
FROM ALBUM
CROSS JOIN ARTIST
ORDER BY NAME ASC;

/*
7.5 SELF
Task – Perform a self-join on the employee table, joining on the reportsto column.
*/

-- Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT EMP1.FIRSTNAME EMPLOYEE_FIRSTNAME, EMP1.LASTNAME EMPLOYEE_LASTNAME, EMP2.FIRSTNAME MANAGER_FIRSTNAME, EMP2.LASTNAME MANAGER_LASTNAME
FROM EMPLOYEE EMP1, EMPLOYEE EMP2
WHERE EMP1.REPORTSTO = EMP2.EMPLOYEEID;