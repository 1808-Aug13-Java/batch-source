--2.1 SELECT
--Task – Select all records from the Employee table.
SELECT *
FROM MYEMPLOYEE;
--Task – Select all records from the Employee table where last name is King.
SELECT LASTNAME
FROM MYEMPLOYEE
WHERE LASTNAME = 'King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.// 
SELECT FIRSTNAME,REPORTSTO
FROM MYEMPLOYEE 
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO = 'Null';
--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM ALBUM
ORDER BY TITLE DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME 
FROM CUSTOMER
ORDER BY FIRSTNAME ASC;
--2.3 INSERT INTO
--Task – Insert two new records into Genre table 
INSERT INTO GENRE VALUES (26,'TRAP MUSIC')
INSERT INTO GENRE VALUES (27,'HOUSE MUSIC')
--Task – Insert two new records into Employee table
INSERT INTO MYEmployee VALUES (21, 'Mongo', 'Muanza', 'General Manager', TO_DATE('1962-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-8-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Jasper Ave NW', 'Edmonton', 'AB', 'Canada', 'T5K 2N1', '+1 (780) 428-9482', '+1 (780) 428-3457', 'andrew@chinookcorp.com');
INSERT INTO MYEmployee VALUES (22, 'Jeremie', 'Bolobiongo', 'Sales Manager', 1, TO_DATE('1958-12-8 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-5-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), '825 8 Ave SW', 'Calgary', 'AB', 'Canada', 'T2P 2T3', '+1 (403) 262-3443', '+1 (403) 262-3322', 'nancy@chinookcorp.com');
--Task – Insert two new records into Customer table 
INSERT INTO Customer VALUES (60, 'Phil', 'Diezu', 'Embraer - Empresa Brasileira de Aeronáutica S.A.', 'Av. Brigadeiro Faria Lima, 2170', 'São José dos Campos', 'SP', 'Brazil', '12227-000', '+55 (12) 3923-5555', '+55 (12) 3923-5566', 'luisg@embraer.com.br', 3);
INSERT INTO Customer VALUES (61, 'Ralph', 'Ghanaza', 'Theodor-Heuss-Straße 34', 'Stuttgart', 'Germany', '70174', '+49 0711 2842222', 'leonekohler@surfeu.de', 5);
--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';
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
--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM MYEMPLOYEE
WHERE HIREDATE <= DATE '2004-03-01' AND  HIREDATE >= DATE '2003-06-01';
--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
UPDATE INVOICE
SET
WHERE CUSTOMERID IN(
SELECT CUSTOMERID
FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');
DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--3.1 System Defined Functions
--Task – Create a function that returns the current time.
CREATE OR REPLACE FUNCTION RETURN_CURRENT_TIME
RETURN TIMESTAMP
IS
BEGIN
    RETURN TIME_STAMP;
END;
--Task – create a function that returns the length of name in MEDIATYPE table		
SET SERVEROUT ON;
CREATE OR REPLACE FUNCTION LENGTH_OF_NAME
RETURN NUMBER
IS
BEGIN
    RETURN length(str);
END;


--3.2 System Defined Aggregate Functions

--Task – Create a function that returns the average total of all invoices 
CREATE OR REPLACE FUNCTION AVG_INVOICES
RETURN NUMBER
IS
    WHOLE_DIV_UNITS NUMBER;
BEGIN
    SELECT AVG TOTAL INTO WHOLE_DIV_UNITS FROM INVOICE;
    RETURN WHOLE_DIV_UNITS;
END;
--Task – Create a function that returns the most expensive track
SELECT UNITPRICE 
FROM TRACK
ORDER BY UNITPRICE ASC;
    
--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION AVG_INVL_PRICE
RETURN NUMBER
IS
    AVG_PRICE NUMBER;
BEGIN
    SELECT AVG UNITPRICE INTO AVG_PRICE FROM INVOICELINE; 
    RETURN AVG_PRICE ;
END;
--3.4 User Defined Table Valued Functions

--Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION 
RETURN

--4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE FIRST_LAST(S OUT VARCHAR2, T OUT VARCHAR2)
IS
BEGIN
SELECT FIRSTNAME INTO S FROM CHINOOK.EMPLOYEE;
SELECT LASTNAME INTO T FROM CHINOOK.EMPLOYEE;
END;
--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE 
(
  NEW_EMPLOYEEID IN NUMBER,
  NEW_LASTNAME IN VARCHAR2,
  NEW_FIRSTNAME IN VARCHAR2,
  NEW_TITLE IN VARCHAR2,
  NEW_REPORTSTO IN NUMBER,
  NEW_BIRTHDATE IN DATE,
  NEW_HIREDATE IN DATE,
  NEW_ADDRESS IN VARCHAR2,
  NEW_CITY IN VARCHAR2,
  NEW_STATE IN VARCHAR2,
  NEW_COUNTRY VARCHAR2,
  NEW_POSTALCODE VARCHAR2,
  NEW_PHONE VARCHAR2,
  NEW_FAX VARCHAR2,
  NEW_EMAIL VARCHAR2
)
IS
BEGIN
  UPDATE CHINOOK.EMPLOYEE
  SET LASTNAME = COALESCE(NEW_LASTNAME, LASTNAME),
      FIRSTNAME = COALESCE(NEW_FIRSTNAME, FIRSTNAME),
      TITLE = NEW_TITLE,
      REPORTSTO = NEW_REPORTSTO,
      BIRTHDATE = NEW_BIRTHDATE,
      HIREDATE = NEW_HIREDATE,
      ADDRESS = NEW_ADDRESS,
      CITY = NEW_CITY,
      STATE = NEW_STATE,
      COUNTRY = NEW_COUNTRY,
      POSTALCODE = NEW_POSTALCODE,
      PHONE = NEW_PHONE,
      FAX = NEW_FAX,
      EMAIL = NEW_EMAIL
    WHERE EMPLOYEEID = NEW_EMPLOYEEID;
END;
/
--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE FETCH_MYMANAGER(

--4.3 Stored Procedure Output Parameters

--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE FETCH_CUS (CUS OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN CUS FOR
    SELECT (FIRSTNAME, LASTNAME, COMPANY) FROM CUSTOMER;
END;
--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
ALTER TABLE INVOICELINE 
    DROP 
    CONSTRAINT 
    FK_INVO_INLINEVO_ID;
    CREATE OR REPLACE PROCEDURE REMOVER(FIRED_ID IN INVOICE.INVOICEID%TYPE)
IS
BEGIN
   DELETE FROM INVOICE
   WHERE INVOICEID = FIRED_ID;
END
  COMMIT;
--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE ENTER_CUS
NEW_CUSID IN NUMBER(10)
NEW_CUS_FIRST IN VARCHAR2 (60)
NEW_CUS_LAST IN VARCHAR2  (60)
NEW_CUS_COMPANY IN VARCHAR2 (60)
NEW_CUS_ADDY IN VARCHAR2 (120)
NEW_CUS_CITY IN VARCHAR2 (80)
NEW_CUS_STATE IN VARCHAR2 (2)
NEW_CUS_COUNTRY IN VARCHAR2(60)
NEW_CUS_POST IN NUMBER (5)
NEW_CUS_TELE  IN VARCHAR2 (20)
NEW_CUS_FAX IN VARCHAR2(20)
NEW_CUS_EMAIL IN VARCHAR2(80)
NEW_CUS_SUP_REP_ID IN NUMBER(8)

--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER DELAY_INSERT_TRIGGER
AFTER INSERT ON MYEMPLOYEE
FOR EACH ROW
DECLARE INSERT_PASSWORD NUMBER(6);
--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER RENEW
    AFTER UPDATE
    ON ALBUM
    FOR EACH ROW
    DECLARE INSERT_PASSWORD NUMBER(6)
--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER REMOVER
AFTER DELETE
    ON CUSTOMER
    FOR EACH ROW
    DECLARE
    INSERT_PASSWORD NUMBER(6);
--7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT
    (FIRSTNAME, LASTNAME)AS FULL_NAME,
    INVOICEID AS ID_INV
FROM CUSTOMER E
JOIN INVOICE D
ON E.CUSTOMERID = D.CUSTOMERID

--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT CUSTOMERID,
    (FIRSTNAME, LASTNAME), 
    INVOICEID,
    TOTAL
FROM CUSTOMER E
FULL JOIN INVOICE D
ON E.CUSTOMERID = D.CUSTOMERID;
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT 
    NAME AS ARTIST_NAME, 
    TITLE AS SONG_TITLE
    FROM ARTIST H
    RIGHT JOIN ALBUM U
ON H.ARTISTID = U.ARTISTID;
--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
    FROM ARTIST
    CROSS JOIN ALBUM
ORDER BY ARTIST.NAME ASC;
--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT 
    (FIRSTNAME, LASTNAME)AS MANAGER_NAME
    FROM MYEMPLOYEE K
    WHERE MANAGER_ID NOT 'Null',
    (FIRSTNAME, E.LASTNAME)AS EMPLOYEE_NAME
    FROM MYEMPLOYEE F
LEFT JOIN REPORTSTO
ON K.MYEMPLOYEE = F.;
    






