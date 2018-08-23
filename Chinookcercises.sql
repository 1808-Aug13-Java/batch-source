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
