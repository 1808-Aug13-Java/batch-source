--2.0 SQL Queries
--In this section you will be performing various queries against the Oracle Chinook database.
--2.1 SELECT
--Task – Select all records from the Employee table.
SELECT *
FROM EMPLOYEE;

--Task – Select all records from the Employee table where last name is King.
SELECT *
FROM EMPLOYEE 
WHERE LASTNAME LIKE 'King';
--Task – Select all records from the Employee table where first name is Andrew and 
--REPORTSTO is NULL.// 
SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' and REPORTSTO IS NULL;

--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM ALBUM
ORDER BY TITLE DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT C.FIRSTNAME
FROM CUSTOMER C
ORDER BY CITY;

--2.3 INSERT INTO
--Task – Insert two new records into Genre table 
INSERT INTO GENRE VALUES (26,'Techno');
INSERT INTO GENRE VALUES  (27,'Trap');
COMMIT;
--Task – Insert two new records into Employee table
INSERT INTO EMPLOYEE VALUES (9,'Lannister','Tyrion', 'Recruiter',1,DATE '1984-01-01',DATE '2018-8-23','1600 Pennsylvania Ave','Washington','D.C.','USA','20500','+1 (372) 810 2937','+1 (183) 940 4758','tyrion@chinookcorp.com') ;
INSERT INTO EMPLOYEE VALUES (10,'Snow','John','Software Developer',6,DATE '1985-02-03',DATE '2018-8-23','1234 Main Street','Los Angeles', 'CA','USA','900201','+1 (183) 940 4758','+1 (372) 810 2937','john@chinookcorp.com');
--Task – Insert two new records into Customer table 
INSERT INTO CUSTOMER VALUES (60,'Gordon','Ramsey','Food.INC','4707 Mark St','Mobile','AL','USA','90201','+1 (783) 374 4380)','+1 (783) 374 4381)','ramsey@food.com',3);
INSERT INTO CUSTOMER VALUES (61,'Arnold','Lincoln','Sugar','3484 Toe Ave','Springfield','IL','USA','60201','+1 (384) 123 3948','+1 (384) 123 3949','lincold@sweet.com',4);
COMMIT;
SAVEPOINT SP1;
--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” t o “CCR”
UPDATE CUSTOMER C
SET FIRSTNAME='Robert', LASTNAME='Walter'
WHERE C.FIRSTNAME='Aaron' AND C.LASTNAME='Mitchell';

UPDATE ARTIST A
SET NAME='CCR'
WHERE A.NAME='Creedence Clearwater Revival';
COMMIT;

--2.5 LIKE
--Task – Select all invoices with a billing address like “T%”
SELECT *
FROM INVOICE 
WHERE BILLINGADDRESS LIKE 'T%';

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
--Task – Select all employees hired between 1st of June 2003 and
--1st of March 2004

SELECT *
FROM EMPLOYEE
WHERE HIREDATE BETWEEN DATE '2003-6-1' AND DATE '2004-3-1';

--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them). 
--delete robert 
ROLLBACK;

--ALTER TABLE CUSTOMER 
--DROP CONSTRAINT FK_CUSTOMERSUPPORTREPID;
--
--ALTER TABLE Customer ADD CONSTRAINT FK_CustomerSupportRepId
--    FOREIGN KEY (SupportRepId) REFERENCES Employee (EmployeeId);

ALTER TABLE INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;

ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId) ON DELETE CASCADE ;


ALTER TABLE INVOICELINE
DROP CONSTRAINT FK_InvoiceLineInvoiceId;
ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId)  ON DELETE CASCADE;

DELETE FROM CUSTOMER C
WHERE C.FIRSTNAME='Robert' AND C.LASTNAME='Walter';

--3.1 System Defined Functions
--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION GET_TIME
RETURN CHAR
IS
    BEGIN
    RETURN TO_CHAR(SYSDATE,'YYYY-MM-DD HH:MM:SS');
    END;
    /
    
--    SELECT GET_TIME
--    FROM CUSTOMER;
---Task – create a function that returns the length of name in MEDIATYPE table 
CREATE OR REPLACE FUNCTION GET_LEN (STR VARCHAR2)
RETURN NUMBER
IS
    BEGIN
    RETURN LENGTH(STR);
    END; 
    /
SELECT GET_LEN(M.NAME)
FROM MEDIATYPE M;

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 

CREATE OR REPLACE FUNCTION GET_AVG
RETURN NUMBER
IS
    A NUMBER;
 BEGIN
   SELECT AVG(TOTAL) 
   INTO A
   FROM INVOICE;
   RETURN A;
 END;
 /
--Task – Create a function that returns the most expensive track
--
--CREATE OR REPLACE FUNCTION MONEY
--RETURN DECLARE ROW
--AS
--    YUP ROW%TYPE;
--    BEGIN
--    
--        SELECT *
--        INTO YUP
--        FROM TRACK
--        WHERE TRACK.UNITPRICE=(
--            SELECT MAX(UNITPRICE)
--            FROM TRACK);
--       RETURN YUP;     
--    END;
--    /

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items
--in the invoiceline table

CREATE OR REPLACE FUNCTION A_PRICE
RETURN NUMBER
IS
    PRC NUMBER;
BEGIN
    SELECT AVG(UNITPRICE)
    INTO PRC
    FROM INVOICELINE;
    RETURN PRC;
END;
/

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
--returns an openened cursor, idk how to use it

CREATE OR REPLACE FUNCTION OLD_PPL 
RETURN SYS_REFCURSOR
IS 
    S SYS_REFCURSOR;
    BEGIN
        OPEN S FOR 
            SELECT E.*
            FROM EMPLOYEE E
            WHERE E.BIRTHDATE > DATE '1968-12-31';
            RETURN S;
        END;
        /
        

    



--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and
--last names of all the employees.

CREATE OR REPLACE PROCEDURE EMP_NAMES (S OUT SYS_REFCURSOR)
IS
    BEGIN
        OPEN S FOR 
        SELECT FIRSTNAME, LASTNAME
        FROM EMPLOYEE;
--        SELECT E.FIRSTNAME, E.LASTNAME
--        FROM EMPLOYEE E
--        WHERE EMPLOYEEID IS NOT NULL;
    END;
/

DECLARE 
    SVAR SYS_REFCURSOR;
    TEMP_FIRST EMPLOYEE.FIRSTNAME%TYPE;--NUMBER;
    TEMP_LAST EMPLOYEE.LASTNAME%TYPE;--VARCHAR2(50);
BEGIN
    EMP_NAMES(SVAR);
     LOOP
        FETCH SVAR INTO TEMP_FIRST, TEMP_LAST; --ACTIVE SET is each row returned
        EXIT WHEN SVAR%NOTFOUND;
        --ANY manipulation to the data would be done here
         DBMS_OUTPUT.PUT_LINE(TEMP_FIRST || ' FIRSTNAME, ' ||TEMP_LAST || ' LAST NAME ');
    END LOOP;
    CLOSE SVAR;
    END;
    /
    
--    4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE PERS_INFO (NEW_ADD IN VARCHAR2, ID_NUM IN NUMBER)
IS
    BEGIN
        UPDATE EMPLOYEE E
        SET E.ADDRESS=NEW_ADD
        WHERE E.EMPLOYEEID =ID_NUM;
    END;
    /
    
--    Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE GET_MANAGERS (IN_NUM IN EMPLOYEE.EMPLOYEEID%TYPE, MNGR OUT SYS_REFCURSOR ) 
IS
    BEGIN
    OPEN MNGR FOR 
        SELECT E1.*
--        INTO MNGR
        FROM EMPLOYEE E1
        WHERE E1.EMPLOYEEID = (
                SELECT REPORTSTO 
                FROM EMPLOYEE 
                WHERE EMPLOYEEID=IN_NUM);
       
    END;
    /
    
 
--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.

CREATE OR REPLACE PROCEDURE CUST_INFO (NAME_CO OUT SYS_REFCURSOR)

IS 
    BEGIN
        OPEN NAME_CO FOR
        SELECT C.FIRSTNAME, C.LASTNAME, C.COMPANY
        FROM CUSTOMER C;
    END;
    /
    
--5.0 Transactions
--In this section you will be working with transactions.
--Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice 
--(There may be constraints that rely on this, find out how to resolve them).
COMMIT;
CREATE OR REPLACE PROCEDURE INVOICE_TRAN (IN_ID IN INVOICE.INVOICEID%TYPE) 
IS
    BEGIN
    DELETE FROM INVOICE 
    WHERE INVOICE.INVOICEID=IN_ID;
    END;
    /
    
    
--    BEGIN 
----    INVOICE_TRAN (1);
--ROLLBACK;
--    END;
--    

--Task – Create a transaction nested within a stored procedure that
--inserts a new record in the Customer table

CREATE OR REPLACE PROCEDURE CSTR_INSERT (   
    C_ID IN CUSTOMER.CUSTOMERID%TYPE,
    F_NAME IN CUSTOMER.FIRSTNAME%TYPE,
    L_NAME IN CUSTOMER.LASTNAME%TYPE,
    CO IN CUSTOMER.COMPANY%TYPE,
    ADDR IN CUSTOMER.ADDRESS%TYPE,
    CTY IN CUSTOMER.CITY%TYPE,
    ST IN CUSTOMER.STATE%TYPE,
    CNTRY IN CUSTOMER.COUNTRY%TYPE,
    PCODE IN CUSTOMER.POSTALCODE%TYPE,
    PHN IN CUSTOMER.PHONE%TYPE,
    FAX IN CUSTOMER.FAX%TYPE,
    EML IN CUSTOMER.EMAIL%TYPE,
    SREP IN CUSTOMER.SUPPORTREPID%TYPE)
IS 
    BEGIN
    INSERT INTO CUSTOMER
    VALUES (C_ID,F_NAME, L_NAME, CO, ADDR, CTY, ST, CNTRY, PCODE, PHN, FAX, EML, SREP);
    END;
    /
    
--6.0 Triggers
--In this section you will create various kinds of triggers that work when 
--certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after
--a new record is inserted into the table.

CREATE SEQUENCE SQ_EMP_IN_AFTER
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 6
CYCLE;

CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
AFTER INSERT ON EMPLOYEE
FOR EACH ROW 
    BEGIN
--        UPDATE TABLE EMPLOYEE 
--        SELECT SQ_EMP_IN_AFTER.NEXTVAL INTO :CURRENT.REPORTSTO FROM DUAL;
        DBMS_OUTPUT.PUT_LINE('Employee was inserted into the table');
    END;
    /

    
--Task – Create an after update trigger on the album table that
--fires after a row is inserted in the table

CREATE OR REPLACE TRIGGER TR_INSERT_ALBUM
AFTER UPDATE ON ALBUM
FOR EACH ROW 
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Album was inserted into the table');
    END;
    /
    
-- Task – Create an after delete trigger on the customer table that 
--fires after a row is deleted from the table.
    
    CREATE OR REPLACE TRIGGER TR_INSERT_ALBUM
AFTER DELETE ON ALBUM
FOR EACH ROW 
    BEGIN
        DBMS_OUTPUT.PUT_LINE('Album was deleted from the table');
    END;
    /
    
    
--  7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. 
--You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and
--specifies the name of the customer and the invoiceId.  
SELECT C.FIRSTNAME, C.LASTNAME, I.INVOICEID
FROM CUSTOMER C
INNER JOIN INVOICE I
ON C.CUSTOMERID=I.CUSTOMERID;

--7.2 OUTER
--Task – Create an outer join that joins the customer and
--invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME, I.INVOICEID, I.TOTAL
FROM CUSTOMER C
FULL OUTER JOIN INVOICE I
ON C.CUSTOMERID=I.CUSTOMERID;

--7.3 RIGHT
--Task – Create a right join that joins album and 
--artist specifying artist name and title.

SELECT A.NAME, R.TITLE
FROM ARTIST A
RIGHT JOIN ALBUM R
ON A.ARTISTID=R.ARTISTID;

--7.4 CROSS
--Task – Create a cross join that joins album and
--artist and sorts by artist name in ascending order.

SELECT A.NAME, R.TITLE
FROM ARTIST A
CROSS JOIN ALBUM R
ORDER BY A.NAME ASC;

--7.5 SELF
--Task – Perform a self-join on the employee table,
--joining on the reportsto column.

SELECT E.FIRSTNAME, E.LASTNAME, E1.FIRSTNAME,E1.LASTNAME
FROM EMPLOYEE E, EMPLOYEE E1
WHERE E.REPORTSTO=E1.EMPLOYEEID
ORDER BY E.REPORTSTO;


