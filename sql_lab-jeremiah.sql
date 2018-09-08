
--Task – Select all records from the Employee table. ----------------------------------------------
SELECT * 
FROM "CHINOOK".EMPLOYEE;


--Task – Select all records from the Employee table where last name is King. ----------------------------------------------

SELECT *
FROM "CHINOOK"."EMPLOYEE" empl WHERE 
empl.LASTNAME='King';
	
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL. ----------------------------------------------

select *
from "CHINOOK"."EMPLOYEE" empl WHERE 
(empl.FIRSTNAME='Andrew') and (empl.reportsto is null);

 -- Task – Select all albums in Album table and sort result set in descending order by title.----------------------------------------------

select TITLE
from "CHINOOK".ALBUM ALB
ORDER BY ALB.TITLE DESC;
 
 -- Task – Select first name from Customer and sort result set in ascending order by city--------------------------------------------------
 CREATE OR REPLACE VIEW CUST_BY_CITY_ASC AS
 select *
from "CHINOOK".CUSTOMER CUST
ORDER BY CUST.CITY ASC;

SELECT FIRSTNAME
FROM CUST_BY_CITY_ASC;

 -- Task – Insert two new records into Genre table -------------------------------------------------------------------------------------
-- CREATE TRIGGER WHICH USES THIS SEQUENCE FOR INSERTING GENRE_ID
CREATE SEQUENCE SQ_GENRE_ID
START WITH 26
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_GENRE_ID
BEFORE INSERT ON "CHINOOK".GENRE
FOR EACH ROW
BEGIN
    SELECT SQ_GENRE_ID.NEXTVAL INTO :NEW.GENREID FROM DUAL;
END;
/
 
INSERT INTO "CHINOOK".GENRE (NAME) VALUES ('VAPORWAVE');
INSERT INTO "CHINOOK".GENRE (NAME) VALUES ('LO FI HIP HOP');
 
 
 --Task – Insert two new records into Employee table-----------------------------------------------------------------------
CREATE SEQUENCE SQ_CHINOOK_EMPLOYEE_ID
START WITH 8
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_CHINOOK_EMPLOYEE_ID
BEFORE INSERT ON "CHINOOK".EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_CHINOOK_EMPLOYEE_ID.NEXTVAL INTO :NEW.EMPLOYEEID FROM DUAL;
END;
/


CREATE OR REPLACE TRIGGER NEW_EMPLOYEE_HIREDATE 
BEFORE INSERT 
ON "CHINOOK".EMPLOYEE 
FOR EACH ROW
DECLARE TODAY DATE;
BEGIN
    TODAY := SYSDATE;
    :NEW.HIREDATE := TODAY;
END;
/


insert into "CHINOOK".EMPLOYEE (LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, ADDRESS, CITY, "STATE", COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) 
values ('McNair', 'Jonis', 'Pharmacist', 2, TO_DATE('2/24/1986', 'mm/dd/yyyy'), '3 Dahle Road', 'Lansing', 'Michigan', 'United States', '48912', 
'+1 (517) 732-9516', '+1 (202) 760-5090', 'jmcnair0@washingtonpost.com');
 
insert into "CHINOOK".EMPLOYEE (LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, ADDRESS, 
 CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) 
 values ('Kernley', 'Edna', 'Analog Circuit Design manager', 2, 
 TO_DATE('7/18/1987', 'MM/DD/YYYY'), '05 Kropf Avenue', 'Tampa', 'Florida', 
 'United States', '33625', '+1 (813) 372-3419', '+1 (847) 825-9338', 'ekernley2@reverbnation.com');
 
 
 --Task – Insert two new records into Customer table  ----------------------------------------------------------------------------------

CREATE SEQUENCE SQ_CUSTOMER_ID
START WITH 59
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_CUSTOMER_ID
BEFORE INSERT ON "CHINOOK".CUSTOMER
FOR EACH ROW
BEGIN
    SELECT SQ_CUSTOMER_ID.NEXTVAL INTO :NEW.CUSTOMERID FROM DUAL;
END;
/

insert into "CHINOOK".CUSTOMER (FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) 
values ('Kleinmintz', 'Chrysa', 'Physical Therapy Assistant', '8414 Portage Drive', 'Phoenix', 'Arizona', 'United States', '85083', 
'+1 (602) 855-4607', '43605', 'ckleinmintz0@engadget.com', 8); 

 --Task – Update Aaron Mitchell in Customer table to Robert Walter ---------------------------------------------------------------------------
UPDATE "CHINOOK".CUSTOMER
	SET CUSTOMER.FIRSTNAME = 'Robert', 
	CUSTOMER.LASTNAME = 'Walter'
	WHERE (CUSTOMER.FIRSTNAME = 'Aaron') AND (CUSTOMER.LASTNAME = 'Mitchell')

select * 
from "CHINOOK".CUSTOMER
where customer.firstname = 'Robert';
 
 
-- Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR” --------------------------------

UPDATE "CHINOOK".ARTIST
	SET ARTIST.NAME = 'CCR'
	WHERE ARTIST.NAME = 'Creedence Clearwater Revival';

SELECT *
FROM "CHINOOK".ARTIST
WHERE ARTIST.NAME = 'CCR';
 
-- Task Select all invoices with a billing address like “T%” -----------------------------------------------------------

SELECT *
FROM "CHINOOK".INVOICE
WHERE "CHINOOK".INVOICE.BILLINGADDRESS LIKE 'T%';

-- Task Select all invoices that have a total between 15 and 50 ----------------------------------------------------------------

SELECT *
FROM "CHINOOK".INVOICE
WHERE "CHINOOK".INVOICE.TOTAL 
BETWEEN 15 AND 50;

-- Task Select all employees hired between 1st of June 2003 and 1st of March 2004 ---------------------------------------------------------

SELECT *
FROM "CHINOOK".EMPLOYEE
WHERE "CHINOOK".EMPLOYEE.HIREDATE 
BETWEEN TO_DATE('06/01/2003', 'MM/DD/YYYY') AND TO_DATE('03/01/2004', 'MM/DD/YYYY');


-- Task Delete a record in Customer table where the name is Robert Walter 
-- (There may be constraints that rely on this, find out how to resolve them).	SQL Functions

ALTER TABLE "CHINOOK".CUSTOMER
	DROP CONSTRAINT FK_CUSTOMERSUPPORTREPID;
	
ALTER TABLE "CHINOOK".CUSTOMER
   ADD CONSTRAINT "FK_CUSTOMERSUPPORTREPID" 
   FOREIGN KEY ("SUPPORTREPID")
   REFERENCES "CHINOOK"."EMPLOYEE" ("EMPLOYEEID") 
   ON DELETE CASCADE;
   
   ALTER TABLE "CHINOOK".INVOICE
	DROP CONSTRAINT FK_INVOICECUSTOMERID;
	
ALTER TABLE "CHINOOK".INVOICE
   ADD CONSTRAINT "FK_INVOICECUSTOMERID" 
   FOREIGN KEY ("CUSTOMERID")
   REFERENCES "CHINOOK"."CUSTOMER" ("CUSTOMERID") 
   ON DELETE CASCADE;

ALTER TABLE "CHINOOK".INVOICELINE
	DROP CONSTRAINT FK_INVOICELINEINVOICEID;
	
ALTER TABLE "CHINOOK".INVOICELINE
   ADD CONSTRAINT "FK_INVOICELINEINVOICEID" 
   FOREIGN KEY ("INVOICEID")
   REFERENCES "CHINOOK"."INVOICE" ("INVOICEID") 
   ON DELETE CASCADE;
 
-- Task – Create a function that returns the current time. 
CREATE OR REPLACE FUNCTION CURR_TIME
RETURN TIMESTAMP
IS
BEGIN
    RETURN TO_TIMESTAMP(LOCALTIMESTAMP, 'DD-MON-RR HH.MI.SSXFF PM');
END;
/
 
SET SERVEROUTPUT ON;
 BEGIN
 DBMS_OUTPUT.PUT_LINE('Current time is: ' || TO_CHAR(CURR_TIME(), 'HH.MI.SS PM') );
 DBMS_OUTPUT.PUT_LINE(CURR_TIME());
 END;
 
-- Task create a function that returns the length of name in MEDIATYPE table	 ------------------------------------------------------
CREATE OR REPLACE FUNCTION GET_LENGTH(M_ID "CHINOOK".MEDIATYPE.MEDIATYPEID%TYPE)
    RETURN NUMBER
    IS
    VAL VARCHAR2(200);
BEGIN
SELECT NAME INTO VAL FROM "CHINOOK".MEDIATYPE WHERE MEDIATYPEID = M_ID;
RETURN LENGTH(TRIM(VAL));
END;
/

BEGIN
DBMS_OUTPUT.PUT_LINE(GET_LENGTH(2));
END;
 
 
 --Task – Create a function that returns the average total of all invoices --------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION AVG_INVOICES
RETURN NUMBER
IS
AVRG NUMBER(20);
BEGIN
SELECT CAST(AVG(TOTAL) AS DECIMAL(10,2)) "Average" INTO AVRG FROM "CHINOOK".INVOICE;
RETURN  AVRG;
END;
/

BEGIN
DBMS_OUTPUT.PUT_LINE('Average of invoice totals: ' ||AVG_INVOICES);
END;
 
 --Task – Create a function that returns the most expensive track\
 --I wasn't sure if by most expensive track we were meant to get just the track name, or the whole
 --row, so I got the record to be safe. we can also pull the information we need from a row
CREATE OR REPLACE FUNCTION MAX_TRACK
RETURN "CHINOOK".TRACK%ROWTYPE
IS
output_rec "CHINOOK".TRACK%ROWTYPE;
BEGIN
    SELECT * INTO output_rec FROM 
        (SELECT * FROM "CHINOOK".TRACK ORDER BY UNITPRICE DESC) 
            WHERE ROWNUM = 1;
    RETURN output_rec;
END;
/

DECLARE
  var1 "CHINOOK".TRACK%ROWTYPE;
BEGIN
  var1 := MAX_TRACK;
  DBMS_OUTPUT.PUT_LINE( var1.name );
END;
/


--Task – Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION AVG_INVOICELINE
RETURN NUMBER
IS
AVRG NUMBER(20);
BEGIN
SELECT CAST(AVG(UNITPRICE) AS DECIMAL(10,2)) "Average" INTO AVRG FROM "CHINOOK".INVOICELINE;
RETURN  AVRG;
END;
/

BEGIN
DBMS_OUTPUT.PUT_LINE('Average of invoice line totals: ' ||AVG_INVOICELINE);
END;
/*
3.4 User Defined Table Valued Functions
*/
--Task – Create a function that returns all employees who are born after 1968.

CREATE OR REPLACE PROCEDURE GET_ALL_EMPLOYEES(S OUT SYS_REFCURSOR)
IS
BEGIN
        OPEN S FOR
        SELECT EMPLOYEEID, FIRSTNAME, LASTNAME, BIRTHDATE FROM "CHINOOK".EMPLOYEE WHERE "CHINOOK".EMPLOYEE.BIRTHDATE 
		BETWEEN TO_DATE('01/01/1968', 'MM/DD/YYYY') AND SYSDATE;
END;
/

CREATE OR REPLACE TYPE string_array
    IS TABLE OF VARCHAR2(100);
    
create or replace function get_employee_bday_after68_test
return string_array
pipelined
is
SVAR SYS_REFCURSOR;
TEMP_ID    "CHINOOK".EMPLOYEE.EMPLOYEEID%TYPE; --NUMBER;
TEMP_FNAME "CHINOOK".EMPLOYEE.FIRSTNAME%TYPE; --VARCHAR2;
TEMP_LNAME "CHINOOK".EMPLOYEE.LASTNAME%TYPE; --VARCHAR2;
TEMP_DATE  "CHINOOK".EMPLOYEE.BIRTHDATE%TYPE; --DATE;
    begin
    
    OPEN SVAR FOR
        SELECT EMPLOYEEID, FIRSTNAME, LASTNAME, BIRTHDATE FROM "CHINOOK".EMPLOYEE WHERE "CHINOOK".EMPLOYEE.BIRTHDATE 
		BETWEEN TO_DATE('01/01/1968', 'MM/DD/YYYY') AND SYSDATE;

        LOOP
        FETCH SVAR INTO TEMP_ID, TEMP_FNAME, TEMP_LNAME, TEMP_DATE; -- "ACTIVE SET" IS EACH ROW RETURNED BY THE CURSOR
		--pipe row( TEMP_ID );
		pipe row( TEMP_FNAME );
		--pipe row( TEMP_LNAME );
		--pipe row( TEMP_DATE );
        EXIT WHEN SVAR%NOTFOUND;
        --DBMS_OUTPUT._PUT_LINE(TEMP_ID||' IS CURRENT ID, '||TEMP_NAME||' IS CURRENT NAME');
    END LOOP;
    CLOSE SVAR;
    return;
end;
/


select * from table( pipeline_test );

/*
4.0 Stored Procedures
In this section you will be creating and executing stored procedures. 
You will be creating various types of stored procedures that take 
input and output parameters.

4.1 Basic Stored Procedure
*/

--Task – Create a stored procedure that selects the first and last names of all the employees.

CREATE OR REPLACE PROCEDURE GET_ALL_EMPLOYEES(S OUT SYS_REFCURSOR)
IS
    FNAME "CHINOOK".EMPLOYEE.FIRSTNAME%TYPE;
    LNAME "CHINOOK".EMPLOYEE.LASTNAME%TYPE;
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME FROM "CHINOOK".EMPLOYEE;
        LOOP
        FETCH S INTO FNAME, LNAME;
        EXIT WHEN S%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(FNAME||' '||LNAME);
    END LOOP;
    CLOSE S;
END;
/
SET SERVEROUTPUT ON;

DECLARE
    SVAR SYS_REFCURSOR;
BEGIN
    GET_ALL_EMPLOYEES(SVAR);
END;
/

/*
4.2 Stored Procedure Input Parameters
*/
--Task – Create a stored procedure that updates the personal information of an employee.

CREATE OR REPLACE PROCEDURE GET_ALL_EMPLOYEES(S OUT SYS_REFCURSOR)
IS
    FNAME "CHINOOK".EMPLOYEE.FIRSTNAME%TYPE;
    LNAME "CHINOOK".EMPLOYEE.LASTNAME%TYPE;
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME FROM "CHINOOK".EMPLOYEE;
        LOOP
        FETCH S INTO FNAME, LNAME;
        EXIT WHEN S%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(FNAME||' '||LNAME);
    END LOOP;
    CLOSE S;
END;
/
SET SERVEROUTPUT ON;

DECLARE
    SVAR SYS_REFCURSOR;
BEGIN
    GET_ALL_EMPLOYEES(SVAR);
END;
/

--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE GET_MANS(EMPID IN CHINOOK.EMPLOYEE.EMPLOYEEID%TYPE)
IS
LNAME "CHINOOK".EMPLOYEE.LASTNAME%TYPE;
FNAME "CHINOOK".EMPLOYEE.FIRSTNAME%TYPE;
REPSTO "CHINOOK".EMPLOYEE.REPORTSTO%TYPE;
BEGIN
    SELECT LASTNAME, FIRSTNAME, REPORTSTO INTO LNAME, FNAME, REPSTO
    FROM "CHINOOK".EMPLOYEE
    WHERE EMPLOYEEID = EMPID;
    DBMS_OUTPUT.PUT_LINE(LNAME||', '||FNAME||'. MANAGER_ID: '||REPSTO);
END;
/
BEGIN
    GET_MANAGERS(2);
END;
/

/*
4.3 Stored Procedure Output Parameters
*/
--Task – Create a stored procedure that returns the name and company of a customer.

CREATE OR REPLACE PROCEDURE GET_COMPANY (CUSTOMERID IN "CHINOOK".CUSTOMER.CUSTOMERID%TYPE)
IS
LNAME "CHINOOK".CUSTOMER.LASTNAME%TYPE;
FNAME "CHINOOK".CUSTOMER.FIRSTNAME%TYPE;
COMP "CHINOOK".CUSTOMER.COMPANY%TYPE;
OUTPUT VARCHAR2(300);
BEGIN
SELECT LASTNAME, FIRSTNAME, COMPANY INTO LNAME, FNAME, COMP FROM "CHINOOK".CUSTOMER WHERE CUSTOMERID = CUSTOMERID;
DBMS_OUTPUT.PUT_LINE(LNAME||' '||FNAME||' COMPANY: '||COMP);
END;
/
BEGIN
GET_COMPANY(3);
END;
/

/*
5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
*/

--Task – Create a transaction that given a invoiceId will delete that invoice 
--(There may be constraints that rely on this, find out how to resolve them).
--on delete cascade added in prvious task
GRANT DELETE ON "CHINOOK".INVOICE  TO "CHINOOK";
GRANT DELETE ON "CHINOOK".INVOICELINE  TO "CHINOOK";
GRANT EXECUTE ON DELETE_INVOICE TO "CHINOOK";
CREATE OR REPLACE PROCEDURE DELETE_INVOICE (INVOICEID "CHINOOK".INVOICE.INVOICEID%TYPE)
IS
BEGIN
    DELETE FROM "CHINOOK".INVOICELINE WHERE INVOICEID = INVOICEID;
    DELETE FROM "CHINOOK".INVOICE WHERE INVOICEID = INVOICEID;
    COMMIT;
END;
/
BEGIN
DELETE_INVOICE(2);
END;
/

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
--Designed to be run after committing my triggers created for customer table in earlier section
CREATE OR REPLACE PROCEDURE INSERT_CUST (FNAME IN "CHINOOK".CUSTOMER.FIRSTNAME%TYPE,
LNAME IN "CHINOOK".CUSTOMER.LASTNAME%TYPE, EML IN "CHINOOK".CUSTOMER.EMAIL%TYPE)
IS
BEGIN
INSERT INTO "CHINOOK".CUSTOMER (FIRSTNAME,
LASTNAME, EMAIL) VALUES (FNAME, LNAME, EML);
END;
/
/*
6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
6.1 AFTER/FOR
*/
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.

CREATE OR REPLACE TRIGGER NEW_EMPLOYEE_HIREDATE 
AFTER INSERT 
ON "CHINOOK".EMPLOYEE 
FOR EACH ROW
DECLARE TODAY DATE;
BEGIN
    TODAY := SYSDATE;
    :NEW.HIREDATE := TODAY;
END;
/

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table

CREATE OR REPLACE TRIGGER NEW_EMPLOYEE_HIREDATE 
AFTER INSERT 
ON "CHINOOK".ALBUM
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('Row has been added to the Album table. ' + :NEW.TITLE);
END;
/


--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
 
 CREATE OR REPLACE TRIGGER TR_INSERT_CUSTOMER_ID
AFTER DELETE ON "CHINOOK".CUSTOMER
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('Row has been deleted. The customer is no longer in the table.');
END;
/

-- Task Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.

SELECT
    "CHINOOK".CUSTOMER.FIRSTNAME, 
    "CHINOOK".CUSTOMER.LASTNAME,
    "CHINOOK".INVOICE.INVOICEID
FROM "CHINOOK".CUSTOMER 
JOIN CHINOOK.INVOICE 
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- Task Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.

SELECT 
    "CHINOOK".CUSTOMER.CUSTOMERID,
    FIRSTNAME,
    LASTNAME,
    INVOICEID,
    TOTAL
FROM CHINOOK.CUSTOMER
FULL JOIN CHINOOK.INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- Task Create a right join that joins album and artist specifying artist name and title.

SELECT 
    ARTIST.NAME,
    ALBUM.TITLE
FROM "CHINNOK".ARTIST ART
RIGHT JOIN "CHINOOK".ALBUM 
ON ARTIST.ARTISTID = ALBUM.ARTISTID;

-- Task Create a cross join that joins album and artist and sorts by artist name in ascending order.

SELECT *
FROM "CHINOOK".ALBUM
CROSS JOIN "CHINOOK".ARTIST
ORDER BY "CHINOOK".ARTIST.NAME ASC;

-- Task Perform a self-join on the employee table, joining on the reportsto column.

SELECT *
FROM "CHINOOK".EMPLOYEE E0, "CHINOOK".EMPLOYEE E1
WHERE E0.REPORTSTO = E1.REPORTSTO;
 
 
 
 

