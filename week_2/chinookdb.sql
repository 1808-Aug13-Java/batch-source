--Task – Select all records from the Employee table.
SELECT * FROM EMPLOYEE;

--Task – Select all records from the Employee table where last name is King.
SELECT *
FROM EMPLOYEE
WHERE LASTNAME = 'King';

--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.// 
SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew'
AND REPORTSTO IS NULL;

--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM ALBUM
ORDER BY TITLE;

--Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME
FROM CUSTOMER
ORDER BY CITY ASC;

--Task – Insert two new records into Genre table 
INSERT INTO GENRE VALUES (26, 'Death Metal');
INSERT INTO GENRE VALUES (27, 'Math Rock');

--Task – Insert two new records into Employee table
--INSERT INTO EMPLOYEE VALUES (9, 'Smith', 'John', 'IT Staff', 6, DATE 

INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES (9, 'Smith', 'John', 'IT Staff', 6, TO_DATE('1993-11-10 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-8-13 00:00:00','yyyy-mm-dd hh24:mi:ss'), '1999 Joachim Ave', 'Mobile', 'AL', 'USA', '36608', '+1 (251) 499-1333', '+1 (251) 938-2176', 'john@chinookcorp.com');
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES (10, 'Cohen', 'George', 'Sales Support Agent', 2, TO_DATE('1990-10-11 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2011-1-11 00:00:00','yyyy-mm-dd hh24:mi:ss'), '2222 North St', 'Atlanta', 'GA', 'USA', '30301', '+1 (678) 633-1478', '+1 (678) 938-1093', 'george@chinookcorp.com');

-- Task – Insert two new records into Customer table
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) VALUES (60, 'Robert', 'Mandela', 'Uber', '123 Francisco Rd', 'San Francisco', 'SF', 'USA', '12345', '+1 (251) 666-5555', '+1 (251) 666-5566', 'robertmandela@uber.com', 3);
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) VALUES (61, 'Jacob', 'Zen', 'Apple Inc.', '321 Cupertino St', 'Cupertino', 'CA', 'USA', '12543', '+1 (393) 690-4343', '+1 (393) 698-6655', 'jzen@apple.com', 4);

-- Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron'
AND LASTNAME = 'Mitchell';

-- Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

-- Task – Select all invoices with a billing address like “T%”
SELECT *
FROM INVOICE
WHERE BILLINGADDRESS
LIKE 'T%';

-- Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM INVOICE
WHERE TOTAL
BETWEEN 15
AND 50;

-- Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM EMPLOYEE
WHERE HIREDATE
BETWEEN TO_DATE('2003-06-01','YYYY-MM-DD')
AND TO_DATE('2004-03-01','YYYY-MM-DD');

-- Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them)

ALTER TABLE INVOICE
DROP CONSTRAINT FK_InvoiceCustomerId;

DELETE FROM CUSTOMER
WHERE FIRSTNAME='Robert'
AND LASTNAME='Walter';

--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION GET_DATETIME
RETURN CHAR
IS
BEGIN
    RETURN TO_CHAR(SYSDATE, 'MM-DD-YYYY HH24:MI:SS');
END;
/

SET SERVEROUTPUT ON
BEGIN
    DBMS_OUTPUT.PUT_LINE(GET_DATETIME);
END;
/

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table


--Task – create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION LENGTH_OF_NAME(GET_NAME VARCHAR2)
RETURN VARCHAR2
IS
BEGIN
    RETURN LENGTH(GET_NAME);
END;
/

SELECT NAME, LENGTH_OF_NAME(NAME)
FROM MEDIATYPE;

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION AVG_ALL_INVOICES
RETURN NUMBER
IS
    AVERAGE_TOTAL NUMBER;
BEGIN
    SELECT AVG(TOTAL)
    INTO AVERAGE_TOTAL
    FROM INVOICE;
    RETURN AVERAGE_TOTAL;
END;
/

BEGIN
DBMS_OUTPUT.PUT_LINE(AVG_ALL_INVOICES);
END;
/

--Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK
RETURN NUMBER
IS TOP_PRICE NUMBER;
BEGIN
    SELECT MAX(UNITPRICE)
    INTO TOP_PRICE
    FROM TRACK;
    RETURN TOP_PRICE;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE(MOST_EXPENSIVE_TRACK());
END;
/
-- 3.3 User Defined Scalar Functions
-- Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION INVOICELINE_AVG
RETURN NUMBER
IS AVG_UNITPRICE NUMBER(5, 2);
BEGIN
    SELECT AVG(UNITPRICE)
    INTO AVG_UNITPRICE
    FROM INVOICELINE;
    RETURN AVG_UNITPRICE;
    
END;
/



-- 3.4 User Defined Table Valued Functions
-- Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION AFTER_68
RETURN SYS_REFCURSOR
IS
    AGE SYS_REFCURSOR;
BEGIN
    OPEN AGE FOR
    SELECT * FROM EMPLOYEE WHERE BIRTHDATE > DATE '1968-12-30';
    
    RETURN AGE;
END;
/








--4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.

--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE SELECT_FNLN(FIRSTNAME_LASTNAME OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN FIRSTNAME_LASTNAME FOR
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_EMP_INFO(
    EMP_ID EMPLOYEE.EMPLOYEEID%TYPE,
    EMP_LASTNAME EMPLOYEE.LASTNAME%TYPE,
    EMP_FIRSTNAME EMPLOYEE.FIRSTNAME%TYPE,
    EMP_TITLE EMPLOYEE.TITLE%TYPE,
    EMP_REPORTSTO EMPLOYEE.REPORTSTO%TYPE,
    EMP_BIRTHDAY EMPLOYEE.BIRTHDATE%TYPE,
    EMP_HIREDATE EMPLOYEE.HIREDATE%TYPE,
    EMP_ADDRESS EMPLOYEE.ADDRESS%TYPE,
    EMP_CITY EMPLOYEE.CITY%TYPE,
    EMP_STATE EMPLOYEE.STATE%TYPE,
    EMP_COUNTRY EMPLOYEE.COUNTRY%TYPE,
    EMP_POSTALCODE EMPLOYEE.POSTALCODE%TYPE,
    EMP_PHONE EMPLOYEE.PHONE%TYPE,
    EMP_FAX EMPLOYEE.FAX%TYPE,
    EMP_EMAIL EMPLOYEE.EMAIL%TYPE)
    
    IS
    BEGIN
        UPDATE EMPLOYEE
        SET
            LASTNAME = EMP_LASTNAME,
            FIRSTNAME = EMP_FIRSTNAME,
            TITLE = EMP_TITLE,
            REPORTSTO = EMP_REPORTSTO,
            BIRTHDATE = EMP_BIRTHDAY,
            HIREDATE = EMP_HIREDATE,
            ADDRESS = EMP_ADDRESS,
            CITY = EMP_CITY,
            STATE = EMP_STATE,
            COUNTRY = EMP_COUNTRY,
            POSTALCODE = EMP_POSTALCODE,
            PHONE = EMP_PHONE,
            FAX = EMP_FAX,
            EMAIL = EMP_EMAIL
        WHERE EMPLOYEEID = EMP_ID;
    END;
    /


--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE GET_MGMT(EMP_ID IN EMPLOYEE.EMPLOYEEID%TYPE, MGMT OUT SYS_REFCURSOR)
IS MGMT_ID NUMBER;
BEGIN
    OPEN MGMT FOR
    SELECT *
    FROM EMPLOYEE
    WHERE EMPLOYEEID = 
        (SELECT REPORTSTO FROM EMPLOYEE WHERE EMPLOYEEID = EMP_ID);
END;
/

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE NAME_AND_COMP(CUST_ID IN CUSTOMER.CUSTOMERID%TYPE, NAMECO OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN NAMECO FOR
    SELECT FIRSTNAME, LASTNAME, COMPANY
    FROM CUSTOMER
    WHERE CUSTOMERID=CUST_ID;
END;
/


--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE DELETE_INVOICE(INV_ID IN INVOICE.INVOICEID%TYPE)
IS
BEGIN
    DELETE FROM INVOICELINE INV_LINE
    WHERE INV_LINE.INVOICEID = INV_ID;
    DELETE FROM INVOICE INV
    WHERE INV.INVOICEID = INV_ID;
END;
/




--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE CREATE_CUSTOMER(
    CUST_ID IN CUSTOMER.CUSTOMERID%TYPE,
    FN IN CUSTOMER.FIRSTNAME%TYPE,
    LN IN CUSTOMER.LASTNAME%TYPE,
    CUST_COMP IN CUSTOMER.COMPANY%TYPE,
    ADDR IN CUSTOMER.ADDRESS%TYPE,
    CIT IN CUSTOMER.CITY%TYPE,
    ST IN CUSTOMER.STATE%TYPE,
    CUST_COUNTRY IN CUSTOMER.COUNTRY%TYPE,
    PC IN CUSTOMER.POSTALCODE%TYPE,
    PHONENO IN CUSTOMER.PHONE%TYPE,
    FAXNO IN CUSTOMER.FAX%TYPE,
    EMAIL_ADDR IN CUSTOMER.EMAIL%TYPE,
    SUPPORTREP IN CUSTOMER.SUPPORTREPID%TYPE)
IS
BEGIN
    INSERT INTO CUSTOMER(
        CUSTOMERID,
        FIRSTNAME,
        LASTNAME,
        COMPANY,
        ADDRESS,
        CITY,
        STATE,
        COUNTRY,
        POSTALCODE,
        PHONE,
        FAX,
        EMAIL,
        SUPPORTREPID
    )
    VALUES(
        CUST_ID,
        FN,
        LN,
        CUST_COMP,
        ADDR,
        CIT,
        ST,
        CUST_COUNTRY,
        PC,
        PHONENO,
        FAXNO,
        EMAIL_ADDR,
        SUPPORTREP
    );
END;
/

    



-- 6.0 Triggers
-- In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
-- 6.1 AFTER/FOR
-- Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER TR_AFTER_EMPLOYEE_INSERT
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('You successfully inserted a new record into the EMPLOYEE table!');
END;
/

INSERT INTO EMPLOYEE VALUES (11, 'Last', 'First', 'IT Staff', 6, TO_DATE('1989-12-17 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2015-1-19 00:00:00','yyyy-mm-dd hh24:mi:ss'), '8675 Threeohnine Blvd', 'Baton Rouge', 'LA', 'USA', '45590', '+1 (455) 401-3131', '+1 (456) 402-4242', 'firstnamelastname@chinookcorp.com');
commit;
-- Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER TR_AFTER_ALBUM_UPDATE
AFTER UPDATE ON ALBUM
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('You successfully updated a record in the ALBUM table');
END;
/

-- Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER TR_AFTER_CUSTOMER_DELETE
AFTER DELETE ON CUSTOMER
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('You successfully deleted a row in the CUSTOMER table.');
END;
/


-- 7.1
-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CUSTOMER.FIRSTNAME, INVOICE.INVOICEID
FROM CUSTOMER
INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID=INVOICE.CUSTOMERID;

/* 
7.2 OUTER
Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/

SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL
FROM CUSTOMER
FULL OUTER JOIN INVOICE
ON CUSTOMER.CUSTOMERID=INVOICE.CUSTOMERID
ORDER BY CUSTOMERID ASC;

--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT ALBUM.TITLE, ARTIST.NAME
FROM ALBUM
RIGHT OUTER JOIN ARTIST
ON ALBUM.ARTISTID=ARTIST.ARTISTID
ORDER BY NAME DESC;


--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM ALBUM
CROSS JOIN ARTIST
ORDER BY ARTIST.NAME ASC;


-- 7.5
-- Task – Perform a self-join on the employee table, joining on the reportsto column.

SELECT E1.REPORTSTO, E2.EMPLOYEEID
FROM EMPLOYEE E1, EMPLOYEE E2
WHERE E1.EMPLOYEEID = E2.EMPLOYEEID;
