-------------------------------------------------
-- 2.1 SELECT
-------------------------------------------------

SELECT *
FROM CHINOOK.EMPLOYEE;

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE LASTNAME = 'King';

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

-------------------------------------------------
-- 2.2 ORDER BY
-------------------------------------------------

SELECT *
FROM CHINOOK.ALBUM
ORDER BY TITLE DESC;

SELECT *
FROM CHINOOK.CUSTOMER
ORDER BY CITY; -- ascending by default but could also add ASC

-------------------------------------------------
-- 2.3 INSERT INTO
-------------------------------------------------

INSERT INTO CHINOOK.GENRE VALUES (26, 'Symphonic Metal');
INSERT INTO CHINOOK.GENRE VALUES (27, 'Classical Dubstep');

INSERT INTO CHINOOK.EMPLOYEE VALUES (9, 'Brooks', 'Brandon', 'Chief Executive Officer', (null), DATE '1990-10-11', DATE '1991-10-11', '100 Spruce St', 'San Jose', 'CA', 'United States', 95014, '+1 (408) 555-5555', '+1 (408) 347-3542', 'bbrooks@gmail.com');
INSERT INTO CHINOOK.EMPLOYEE VALUES (10, 'Brooks', 'Alexandra', 'IT Staff', 6, DATE '1995-10-31', DATE '2017-3-11', '111 8th St', 'Troy', 'NY', 'United States', 12180, '+1 (518) 241-5555', '+1 (518) 347-3942', 'brooks.alexandra@gmail.com');

INSERT INTO CHINOOK.CUSTOMER VALUES (60, 'Pizza-Mai', 'Hart', 'Target', '214 King St', 'Hollaback', 'CA', 'United States', 95014, '+1 (214) 804-2341', '+1 (214) 347-3942', 'pizzamyheart@yahoo.com', 3);
INSERT INTO CHINOOK.CUSTOMER VALUES (61, 'Josh', 'Dun', 'Fueled By Ramen', '10 Drummer Ave', 'Boy Lane', 'CA', 'United States', 12345, '+1 (111) 485-3598', '+1 (586) 292-4592', 'joshlovestyler@gmail.com', 2);

-------------------------------------------------
-- 2.4 UPDATE
-------------------------------------------------

UPDATE CHINOOK.CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

UPDATE CHINOOK.ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

-------------------------------------------------
-- 2.5 LIKE
-------------------------------------------------

SELECT * 
FROM CHINOOK.INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

-------------------------------------------------
-- 2.6 BETWEEN
-------------------------------------------------

SELECT *
FROM CHINOOK.INVOICE
WHERE TOTAL BETWEEN 0 AND 15;

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE HIREDATE BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

-------------------------------------------------
-- 2.7 DELETE
-------------------------------------------------

ALTER TABLE CHINOOK.INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;

ALTER TABLE CHINOOK.INVOICE
ADD CONSTRAINT FK_INVOICECUSTOMERID
FOREIGN KEY (CUSTOMERID) 
REFERENCES CHINOOK.CUSTOMER (CUSTOMERID)
ON DELETE CASCADE;

ALTER TABLE CHINOOK.INVOICELINE
DROP CONSTRAINT FK_INVOICELINEINVOICEID;

ALTER TABLE CHINOOK.INVOICELINE
ADD CONSTRAINT FK_INVOICELINEINVOICEID
FOREIGN KEY (INVOICEID) 
REFERENCES CHINOOK.INVOICE (INVOICEID)
ON DELETE CASCADE;

DELETE CHINOOK.CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

-------------------------------------------------
-- 3.1 System Defined Functions
-------------------------------------------------

CREATE FUNCTION CURRENT_TIME
RETURN TIMESTAMP
IS
BEGIN
    RETURN LOCALTIMESTAMP();
END;
/

CREATE FUNCTION LENGTH_OF_NAME(MEDIA_NAME CHINOOK.MEDIATYPE.NAME%TYPE)
RETURN NUMBER
IS
BEGIN
    RETURN LENGTH(MEDIA_NAME);
END;
/

-------------------------------------------------
-- 3.2 System Defined Aggregate Functions
-------------------------------------------------

CREATE OR REPLACE FUNCTION INVOICE_AVERAGE
RETURN CHINOOK.INVOICE.TOTAL%TYPE
IS
    AVERAGE_TOTAL CHINOOK.INVOICE.TOTAL%TYPE;
BEGIN
    SELECT AVG(TOTAL)
    INTO AVERAGE_TOTAL
    FROM CHINOOK.INVOICE;
    RETURN AVERAGE_TOTAL;
END;
/

CREATE OR REPLACE FUNCTION GET_MOST_EXPENSIVE_TRACK
RETURN CHINOOK.TRACK.NAME%TYPE
IS
    MOST_EXPENSIVE_TRACK CHINOOK.TRACK.NAME%TYPE;
BEGIN
    SELECT NAME
    INTO MOST_EXPENSIVE_TRACK
    FROM CHINOOK.TRACK,
        (SELECT MAX(UNITPRICE) AS MAX_PRICE
        FROM CHINOOK.TRACK
        ORDER BY UNITPRICE DESC)
    WHERE UNITPRICE = MAX_PRICE
        AND ROWNUM = 1;
    RETURN MOST_EXPENSIVE_TRACK;
END;
/

-------------------------------------------------
-- 3.3 User Defined Scalar Functions
-------------------------------------------------

CREATE OR REPLACE FUNCTION AVERAGE_INVOICELINE_PRICE
RETURN CHINOOK.INVOICELINE.UNITPRICE%TYPE
IS
    AVG_INVOICELINE CHINOOK.INVOICELINE.UNITPRICE%TYPE;
BEGIN
    SELECT AVG(UNITPRICE)
    INTO AVG_INVOICELINE
    FROM CHINOOK.INVOICELINE;
    RETURN AVG_INVOICELINE;
END;

-------------------------------------------------
-- 3.4 User Defined Table Valued Functions
-------------------------------------------------

CREATE OR REPLACE FUNCTION AFTER_SIXTY_EIGHT
RETURN SYS_REFCURSOR
IS
S SYS_REFCURSOR;
BIRTH_DATE DATE;
EMP_DATE CHINOOK.EMPLOYEE.LASTNAME%TYPE;
BEGIN
   OPEN S FOR SELECT CHINOOK.EMPLOYEE.BIRTHDATE, CHINOOK.EMPLOYEE.LASTNAME FROM CHINOOK.EMPLOYEE
   WHERE CHINOOK.EMPLOYEE.BIRTHDATE > DATE '1968-12-31';
   RETURN S;
END;
/

DECLARE
C SYS_REFCURSOR;
BIRTH_DATE DATE;
EMP_NAME CHINOOK.EMPLOYEE.LASTNAME%TYPE;

BEGIN
   C := AFTER_SIXTY_EIGHT();
   LOOP
       FETCH C INTO BIRTH_DATE, EMP_NAME;
       EXIT WHEN C%NOTFOUND;
       DBMS_OUTPUT.PUT_LINE(BIRTH_DATE||' '|| EMP_NAME);
   END LOOP;
   CLOSE C;
END;

-------------------------------------------------
-- 4.1 Basic Stored Procedure
-------------------------------------------------

--CREATE OR REPLACE PROCEDURE EMPLOYEE_NAMES()

-------------------------------------------------
-- 4.2 Stored Procedure Input Parameters
-------------------------------------------------

BEGIN
    DBMS_OUTPUT.PUT_LINE(GET_MOST_EXPENSIVE_TRACK());
END;


-------------------------------------------------
-- 4.3 Stored Procedure Output Parameters
-------------------------------------------------

-------------------------------------------------
-- 4.3 Stored Procedure Output Parameters
-------------------------------------------------

-------------------------------------------------
-- 5.0 TRANSACTIONS
-------------------------------------------------

-------------------------------------------------
-- 6.1 AFTER/FOR
-------------------------------------------------

-------------------------------------------------
-- 7.1 INNER
-------------------------------------------------

SELECT
    C.FIRSTNAME, 
    C.LASTNAME,
    I.INVOICEID
FROM CHINOOK.CUSTOMER C
JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;

-------------------------------------------------
-- 7.2 OUTER
-------------------------------------------------

SELECT 
    C.CUSTOMERID,
    FIRSTNAME,
    LASTNAME,
    INVOICEID,
    TOTAL
FROM CHINOOK.CUSTOMER C
FULL JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;

-------------------------------------------------
-- 7.3 RIGHT
-------------------------------------------------

SELECT 
    ART.NAME,
    A.TITLE
FROM CHINOOK.ARTIST ART
RIGHT JOIN CHINOOK.ALBUM A
ON ART.ARTISTID = A.ARTISTID;

-------------------------------------------------
-- 7.4 CROSS
-------------------------------------------------

SELECT *
FROM CHINOOK.ALBUM
CROSS JOIN CHINOOK.ARTIST
ORDER BY CHINOOK.ARTIST.NAME ASC;

-------------------------------------------------
-- 7.5 SELF
-------------------------------------------------

SELECT *
FROM CHINOOK.EMPLOYEE E1, CHINOOK.EMPLOYEE E2
WHERE E1.REPORTSTO = E2.REPORTSTO;