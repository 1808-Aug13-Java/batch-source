/*
2.1 SELECT
Task – Select all records from the Employee table.
Task – Select all records from the Employee table where last name is King.
Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.// 
2.2 ORDER BY
Task – Select all albums in Album table and sort result set in descending order by title.
Task – Select first name from Customer and sort result set in ascending order by city
2.3 INSERT INTO
Task – Insert two new records into Genre table 
Task – Insert two new records into Employee table
Task – Insert two new records into Customer table 
*/


--Task – Select all records from the Employee table.
SELECT *
FROM CHINOOK.EMPLOYEE;      --CANT ACCESS RIGHT EMPLOYEE LESS U SPECIFY CHINOOK.TABLE
--Task – Select all records from the Employee table where last name is King.
SELECT * 
FROM CHINOOK.EMPLOYEE
WHERE LASTNAME = 'King';    --HEHEH CASE MATTERS, BEFORE I DID 'KING', GOT NO RETURN
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.// 
SELECT *
FROM CHINOOK.EMPLOYEE
WHERE (FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL);  --IS NULL TO GET NULL VALUES, NOT '= NULL'

--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT * 
FROM CHINOOK.ALBUM
ORDER BY TITLE DESC;        --GROUP BY DONT WORK?, I DID GROUP BY TITLE DESC

SELECT FIRSTNAME, CITY            
FROM CHINOOK.CUSTOMER
ORDER BY CITY ASC;

--2.3 INSERT INTO
--Task – Insert two new records into Genre table 
INSERT INTO CHINOOK.GENRE VALUES(26, 'Romance');
--Task – Insert two new records into Employee table
INSERT INTO CHINOOK.EMPLOYEE VALUES(9, 'Peng', 'Cindy', 'IT Staff', 6, DATE '1974-06-19', 
                                    DATE '2015-09-18' '2341 Happy Drive', 'Fremont', 
                                    'AB', 'Canada', 'T5K 2T3', '+1 (460)790-1667', 
                                    '+1 (403)776-4533', 'cindy@gmail.com';
INSERT INTO CHINOOK.EMPLOYEE VALUES(10, 'Roberts', 'Julie', 'IT Staff', 6, DATE '1974-06-19', 
                                    DATE '2015-09-18' '2341 Happy Court', 'Minnowski', 
                                    'AB', 'Canada', 'T57K 2K3', '+1 (900)860-3395', 
                                    '+1 (403)710-4533', 'jroberts@gmail.com';
--Task – Insert two new records into Customer table 
INSERT INTO CHINOOK.CUSTOMER VALUES(60, 'Matthew', 'Leon', 'Google', '35910 Plaza America Dr', 'Troy',
                                    'FL', 'USA', '92371',  '+1 (249) 1246-2345', '+1(250)234-1948',
                                    'mattleon@gmail.com', 5);
INSERT INTO CHINOOK.CUSTOMER VALUES(61, 'Matt', 'Tseng', 'E Enterprises', '832957 Horse Palace', 
                                    'Petunia City', 'CA', 'USA', '450891',  '+1 (492) 4621-4523', 
                                    '+1(502)342-4848','mtseng@gmail.com', 6);
COMMIT;

--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE CHINOOK.CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

UPDATE CHINOOK.ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';
COMMIT;
--2.5 LIKE
--Task – Select all invoices with a billing address like “T%”
SELECT *
FROM CHINOOK.INVOICE
WHERE BILLINGADDRESS LIKE 'T%';      --REMEMBER 'LIKE' COMMAND, LETS U COMPARE PATTERNS

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT *
FROM CHINOOK.INVOICE
WHERE TOTAL BETWEEN 15 AND 50;

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM CHINOOK.EMPLOYEE
WHERE HIREDATE BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

--2.7 DELETE 
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).	
ALTER TABLE CHINOOK.CUSTOMER
DROP CONSTRAINT FK_CUSTOMERSUPPORTREPID;

DELETE FROM CHINOOK.CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

ALTER TABLE CHINOOK.CUSTOMER
ADD CONSTRAINT FK_CUSTOMERSUPPORTREPID
FOREIGN KEY (SUPPORTREPID) REFERENCES CHINOOK.EMPLOYEE;




--3.1 System Defined Functions
--Task – Create a function that returns the current time.
SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION CURR_TIME
RETURN TIMESTAMP
IS
D TIMESTAMP;
BEGIN
    D := SYSDATE();      --ORACLE SQL HAS NO GETDATE
    RETURN D;
END;
/
BEGIN
    DBMS_OUTPUT.PUT_LINE(CURR_TIME());   
END;
/
--Task – create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION NAMELENGTH(NAMESTR CHINOOK.MEDIATYPE.NAME%TYPE)
RETURN NUMBER
IS
BEGIN
    RETURN LENGTH(NAMESTR);
END;
/
BEGIN
    
END;
/
--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 
CREATE OR REPLACE FUNCTION AVG_TOTAL_INVOICES
RETURN NUMBER
IS
SUM_NUM NUMBER;
COUNT_TOTAL NUMBER;
BEGIN
    SELECT SUM(TOTAL) INTO SUM_NUM
    FROM CHINOOK.INVOICE;
    
    SELECT COUNT(TOTAL) INTO COUNT_TOTAL
    FROM CHINOOK.INVOICE;
   
    RETURN SUM_NUM / COUNT_TOTAL;
END;
/
BEGIN
    DBMS_OUTPUT.PUT_LINE(AVG_TOTAL_INVOICES());
END;
/
--Task – Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK
RETURN CHINOOK.TRACK.NAME%TYPE
IS
TRACK_NAME CHINOOK.TRACK.NAME%TYPE;
MAX_COST NUMBER;
BEGIN
    SELECT MAX(UNITPRICE) INTO MAX_COST
    FROM CHINOOK.TRACK;
    
    SELECT NAME INTO TRACK_NAME
    FROM CHINOOK.TRACK
    WHERE UNITPRICE = MAX_COST AND ROWNUM <= 1;
    RETURN TRACK_NAME;
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE(MOST_EXPENSIVE_TRACK());
END;
/
--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the 
--invoiceline table
SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION AVG_PRICE_ITEMS
RETURN CHINOOK.INVOICELINE.UNITPRICE%TYPE
IS 
AVG_PRICE CHINOOK.INVOICELINE.UNITPRICE%TYPE;
BEGIN
    SELECT AVG(UNITPRICE) INTO AVG_PRICE
    FROM CHINOOK.INVOICELINE;
    RETURN AVG_PRICE;
END;
/
BEGIN
    DBMS_OUTPUT.PUT_LINE(AVG_PRICE_ITEMS());
END;
/
--SOURCES :O
--https://www.databasejournal.com/features/oracle/article.php/2222781/Returning-Rows-Through-a-Table-Function-in-Oracle.htm
--https://stackoverflow.com/questions/46993559/return-a-table-in-a-pl-sql-function
--https://docs.oracle.com/cd/B19306_01/appdev.102/b14289/dcitblfns.htm

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE TYPE EMP_BIRTH_T AS OBJECT  --THIS IS OBJECT WITH THESE VALUES
(
    EMP_ID NUMBER,                  --DON'T FORGET COMMAS
    LAST_N VARCHAR2(50),
    FIRST_N VARCHAR2(50),
    BIRTHDATE DATE
);
/
CREATE OR REPLACE TYPE EMP_BIRTH_T_TABLE AS TABLE OF EMP_BIRTH_T; --MAKES TABLE TYPE FROM OBJ
/
CREATE OR REPLACE FUNCTION EMPLOYEE_BIRTH
RETURN EMP_BIRTH_T_TABLE PIPELINED -- ALL EMPLOYEES AFTER 1968 BIRTH
AS CURSOR EMPLOYEE_CURSOR
    IS
        SELECT EMPLOYEEID, LASTNAME, FIRSTNAME, BIRTHDATE
        FROM CHINOOK.EMPLOYEE
        WHERE BIRTHDATE > DATE '1968-01-01';
BEGIN           --START CODE...JUST LOOP CURSOR ITEMS INTO YOUR TABLE
    FOR i IN EMPLOYEE_CURSOR
    LOOP
        PIPE ROW (EMP_BIRTH_T(i.employeeid, i.lastname, i.firstname, i.birthdate) );
        EXIT WHEN EMPLOYEE_CURSOR%NOTFOUND;
    END LOOP;
    RETURN;     --PIPE WILL RETURN EACH ROW AS IT'S PROCESSED
END;
/
SELECT *
FROM TABLE(EMPLOYEE_BIRTH());   --WITHOUT 'TABLE()', WILL SAY SQL COMMAND NOT PROPERLY ENDED
/




--4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating 
--various types of stored procedures that take input and output parameters.
--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE FIRST_AND_LAST
IS
FIRST_N VARCHAR2(50);
LAST_N VARCHAR2(50);
BEGIN
--   https://stackoverflow.com/questions/28387339/loop-through-a-table-in-oracle-pl-sql
    FOR o IN (SELECT FIRSTNAME, LASTNAME FROM CHINOOK.EMPLOYEE)
    LOOP
        DBMS_OUTPUT.PUT_LINE(o.firstname||', '||o.lastname);
    END LOOP;
--    SELECT FIRSTNAME, LASTNAME INTO FIRST_N, LAST_N FROM CHINOOK.EMPLOYEE FETCH FIRST ROW ONLY;
--    DBMS_OUTPUT.PUT_LINE('YOYOYO');
END;
/
BEGIN
    FIRST_AND_LAST();
END;
/
--4.2 Stored Procedure Input Parameters
--Task – 4.2 Create a stored procedure that updates the personal information of an employee.
 conn chinook/p4ssw0rd     --YOU MUST PUT THE CONNECTION ABOVE THE PROCEDURE :o
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE_NAMES
   ( EMP_ID CHINOOK.EMPLOYEE.EMPLOYEEID%TYPE, 
     FIRST_N CHINOOK.EMPLOYEE.FIRSTNAME%TYPE,
     LAST_N CHINOOK.EMPLOYEE.LASTNAME%TYPE )
IS
BEGIN
    UPDATE CHINOOK.EMPLOYEE
    SET  LASTNAME = LAST_N, FIRSTNAME = FIRST_N
    WHERE EMPLOYEEID = EMP_ID;
END;
/
conn chinook/p4ssw0rd
BEGIN
    UPDATE_EMPLOYEE_NAMES(3, 'Jane', 'Peamane');
    COMMIT;
END;
/
--Task – 4.2 Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE MANAGER_OF
    (FIRST_N CHINOOK.EMPLOYEE.FIRSTNAME%TYPE, LAST_N CHINOOK.EMPLOYEE.LASTNAME%TYPE)
IS
MANAGER_ID CHINOOK.EMPLOYEE.REPORTSTO%TYPE;
BEGIN
    --FROM GIVEN FIRST AND LAST NAME, GET MANAGER, REPORTSTO
    SELECT REPORTSTO INTO MANAGER_ID
    FROM CHINOOK.EMPLOYEE
    WHERE FIRSTNAME = FIRST_N AND LASTNAME = LAST_N;
    DBMS_OUTPUT.PUT_LINE(MANAGER_ID);
END;
/
BEGIN
    MANAGER_OF('Nancy', 'Edwards');
END;
/
--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE GET_CUSTOMER
    (C_ID      IN CHINOOK.CUSTOMER.CUSTOMERID%TYPE, 
     C_F_NAME  OUT CHINOOK.CUSTOMER.FIRSTNAME%TYPE, 
     C_COMPANY OUT CHINOOK.CUSTOMER.COMPANY%TYPE)
IS
BEGIN
    SELECT FIRSTNAME, COMPANY INTO C_F_NAME, C_COMPANY
    FROM CHINOOK.CUSTOMER
    WHERE CUSTOMERID = C_ID;
END;
/
DECLARE
C_F_NAME CHINOOK.CUSTOMER.FIRSTNAME%TYPE;
C_COMPANY CHINOOK.CUSTOMER.COMPANY%TYPE;
BEGIN
    GET_CUSTOMER(60, C_F_NAME, C_COMPANY);
    DBMS_OUTPUT.PUT_LINE(C_F_NAME||', '||C_COMPANY);
END;
/

--5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested 
--within a stored procedure.
--Task – Create a transaction that given a invoiceId will delete that invoice (There 
--may be constraints that rely on this, find out how to resolve them).
CONN chinook/p4ssw0rd
CREATE OR REPLACE PROCEDURE DELETE_INVOICE
    (INV_ID CHINOOK.INVOICE.INVOICEID%TYPE)
IS
BEGIN
    DELETE FROM CHINOOK.INVOICE
    WHERE INVOICEID = INV_ID;
    
END;
/
COMMIT;
CONN CHINOOK/p4ssw0rd
    SAVEPOINT A;
    ALTER TABLE CHINOOK.INVOICE
    DROP CONSTRAINT FK_INVOICECUSTOMERID;   
BEGIN
    SAVEPOINT B;
    DELETE_INVOICE();
END;
/   
    ROLLBACK TO B;
    SAVEPOINT C;
    ALTER TABLE CHINOOK.INVOICE 
    ADD CONSTRAINT FK_INVOICECUSTOMERID
    FOREIGN KEY (CUSTOMERID) REFERENCES CHINOOK.CUSTOMER;
    ROLLBACK TO A;
    COMMIT;
    /

--Task – Create a transaction nested within a stored procedure that inserts a new record 
--in the Customer table
conn chinook/p4ssw0rd
CREATE OR REPLACE PROCEDURE INSERT_RECORD
    (NUM_ID CHINOOK.CUSTOMER.CUSTOMERID%TYPE, 
     FIRST_N CHINOOK.CUSTOMER.FIRSTNAME%TYPE, 
     LAST_N CHINOOK.CUSTOMER.LASTNAME%TYPE,
     C_EMAIL CHINOOK.CUSTOMER.EMAIL%TYPE)
IS
BEGIN
    SAVEPOINT A;
    INSERT INTO CHINOOK.CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, EMAIL) VALUES
        (NUM_ID, FIRST_N, LAST_N, C_EMAIL );
    ROLLBACK TO A;
    COMMIT;
END;
/
BEGIN
    INSERT_RECORD(62, 'Jane', 'Goodall', 'jgoodall@gmail.com');
END;
/

--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML 
--statements are executed on a table.
--6.1 AFTER/FOR
--Task - 6.1:Create an after insert trigger on the employee table fired after a new 
--record is inserted into the table.
CREATE SEQUENCE SQ_GENERATE_EMPLOYEE_PK   --NO 'OR REPLACE' !!!! WONT COMPILE
START WITH 9
INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
BEFORE INSERT ON CHINOOK.EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_GENERATE_EMPLOYEE_PK.NEXTVAL INTO :NEW.EMPLOYEEID FROM DUAL;
END;
/   
INSERT INTO CHINOOK.EMPLOYEE (FIRSTNAME, LASTNAME) VALUES ('Janice', 'Berinice');

--https://www.techonthenet.com/oracle/triggers/after_update.php
--Task – 6.1:Create an after update trigger on the album table that fires after a 
--row is inserted in the table
CONN chinook/p4ssw0rd
CREATE OR REPLACE TRIGGER TR_AFTER_UPDATE_ALBUM
AFTER UPDATE ON CHINOOK.ALBUM
FOR EACH ROW
--IS
--V_USERNAME VARCHAR2(10);
BEGIN
--    SELECT USER INTO V_USERNAME FROM DUAL;  --GET THE CURRENT USER, LATER LETS US 
    -- DO THE INSERT AS THE CURRENT USER
    INSERT INTO CHINOOK.ALBUM (ALBUMID, TITLE, ARTISTID)
    VALUES(348, 'CREATEDALBUM', 'HUNTER');
END;
/
--Task – 6.1:Create an after delete trigger on the customer table that fires after 
--a row is deleted from the table.
conn chinook/p4ssw0rd
CREATE OR REPLACE TRIGGER TR_DELETE_AFTER_CUSTOMER
AFTER DELETE ON CHINOOK.CUSTOMER
FOR EACH ROW
BEGIN
    DELETE FROM CHINOOK.CUSTOMER
    WHERE CUSTOMERID = 57;
END;
/
--7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. 
--You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of 
--the customer and the invoiceId.
SELECT C.FIRSTNAME, C.LASTNAME, I.INVOICEID
FROM CHINOOK.CUSTOMER C
INNER JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;
--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the 
--CustomerId, firstname, lastname, invoiceId, and total.
SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME, I.INVOICEID, I.TOTAL
FROM CHINOOK.CUSTOMER C
FULL JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT ART.NAME, ALB.TITLE
FROM CHINOOK.ALBUM ALB
RIGHT JOIN CHINOOK.ARTIST ART
ON ART.ARTISTID = ALB.ARTISTID;
--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT ART.NAME, ALB.TITLE
FROM CHINOOK.ALBUM ALB
CROSS JOIN CHINOOK.ARTIST ART    --NEEDS NO JOIN PREDICATE. NO NEED TO SPECIFY 
ORDER BY ART.NAME ASC;          --WHAT THE TABLES NEED TO BE THE SAME ON (WHICH COL)
--7.5 SELF                       --CUZ IT WILL JUST JOIN WITH EVERYTHING
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT MAN.FIRSTNAME, MAN.LASTNAME, EMP.FIRSTNAME, EMP.LASTNAME
FROM CHINOOK.EMPLOYEE EMP, CHINOOK.EMPLOYEE MAN
WHERE EMP.REPORTSTO = MAN.EMPLOYEEID;
















