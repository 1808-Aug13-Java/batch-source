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

DELETE CHINOOK.INVOICE
WHERE CUSTOMERID = 
    ( SELECT CUSTOMERID
    FROM CHINOOK.CUSTOMER
    WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
    );

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


