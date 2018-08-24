conn chinook/p4ssw0rd;

disconnect chinook;

-- 2.1 Select
-- Selects all employees
SELECT * 
FROM CHINOOK.EMPLOYEE;

-- Selects all employees with the last name 'king'
SELECT * 
FROM CHINOOK.EMPLOYEE
WHERE Lastname = 'King';

-- Selects all employees with the first name 'andrew' and who don't report to anyone
SELECT *
FROM CHINOOK.EMPLOYEE 
WHERE Firstname = 'Andrew' 
    AND ReportsTo IS NULL;


-- 2.2 Order By
-- Selects all Albums and orders them by title ascending
SELECT * 
FROM CHINOOK.ALBUM
ORDER BY Title;

-- Selects the first names of customers ordered by the city they appeared in ascending
SELECT C.FirstName
FROM CHINOOK.CUSTOMER C
ORDER BY City;


-- 2.3 Insert Into
-- Inserts two records into the genre table
INSERT INTO CHINOOK.GENRE (GENREID, NAME) VALUES (26, 'Hard Rock');
INSERT INTO CHINOOK.GENRE VALUES ((SELECT max(GENREID) FROM CHINOOK.GENRE)+1, 'Pirate Metal');

-- Inserts two records into the employee table
INSERT INTO CHINOOK.EMPLOYEE
(EmployeeID, LastName, FirstName, Title, ReportsTo, Birthdate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES (
    (SELECT max(EmployeeID) FROM CHINOOK.EMPLOYEE)+1, 
    'Lee', 
    'Dennis', 
    'IT Staff', 
    6, 
    DATE '1992-07-23', 
    CURRENT_DATE, 
    '420 SE Vermont Dr',
    'Lethbridge',
    'AB',
    'Canada',
    'T1Y 512',
    '+1 (403) 987-1234',
    '+1 (403) 987-1234',
    'lee@chinookcorp.net');

INSERT INTO CHINOOK.EMPLOYEE
(EmployeeID, LastName, FirstName, Title, ReportsTo, Birthdate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES ((SELECT max(EmployeeID) FROM CHINOOK.EMPLOYEE)+1, 'Low', 'Daniel', 'IT Staff', 6, DATE '1987-02-03', CURRENT_DATE, '9001 N Scout Dr', 'Lethbridge', 'AB', 'Canada', 'T1K 976', '+1 (403) 123-9876', '+1 (403) 123-9876', 'low@chinookcorp.net');

-- Inserts two customers into the customer
INSERT INTO CHINOOK.CUSTOMER
(CustomerID, LastName, FirstName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepID)
VALUES ((SELECT max(CustomerID) FROM CHINOOK.CUSTOMER)+1, 'Drowned', 'Ben', 'Nintendo', '9001 N IDK Pl', 'Lethbridge', 'AB', 'Canada', 'T1K 976', '+1 (403) 123-9876', '+1 (403) 123-4567', '1234@nintendo.com', 3);
INSERT INTO CHINOOK.CUSTOMER
(CustomerID, LastName, FirstName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepID)
VALUES ((SELECT max(CustomerID) FROM CHINOOK.CUSTOMER)+1, 'Uncle', 'My', 'Nintendo', '9001 S Keen Bl', 'Lethbridge', 'AB', 'Canada', 'T1K 976', '+1 (403) 123-9876', '+1 (403) 987-6543', '9876@nintendo.com', 3);


-- 2.4 Update
-- Changes all the Customers nambed 'Aaron Michell' to 'Robert Walter'
UPDATE CHINOOK.CUSTOMER
SET FirstName = 'Robert', LastName = 'Walter'
WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';

-- Changes all records with the name 'Creedence Clearwater Revival' to the name 'CCR'
UPDATE CHINOOK.ARTIST
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';


-- 2.5 Like
 -- Selects all invoices where the billing address starts with T
SELECT * FROM CHINOOK.INVOICE
WHERE BillingAddress LIKE 'T%';


-- 2.6 Between
-- Selects the invoices between 15 and 50 arbitrary money units inclusive. 
SELECT * FROM CHINOOK.INVOICE
WHERE Total >= 15 AND Total <= 50;

-- Selects Employees who were hired between '2003-06-01' and '2004-03-01' inclusive
SELECT * FROM CHINOOK.EMPLOYEE
WHERE HireDate >= DATE '2003-06-01' AND HireDate <= DATE '2004-03-01';


-- 2.7 Delete
-- Since ON DELETE CASCASE isn't implemented here, we need to remove the repsective database enries with a few 
-- delete statements, one for each table. 
DELETE FROM CHINOOK.INVOICELINE IL
WHERE IL.InvoiceID IN
    (SELECT I.InvoiceID FROM CHINOOK.INVOICE I
        WHERE I.CustomerID IN
        (SELECT CustomerID FROM CHINOOK.CUSTOMER
            WHERE FirstName = 'Robert' AND LastName = 'Walter'
        )
    );
DELETE FROM CHINOOK.INVOICE I
WHERE I.CUSTOMERID IN
    (SELECT CustomerID FROM CHINOOK.CUSTOMER
        WHERE FirstName = 'Robert' AND LastName = 'Walter'
    );
DELETE FROM CHINOOK.CUSTOMER
WHERE FirstName = 'Robert' AND LastName = 'Walter';



-- 3.1 
-- A fuction that returns the current time. This is functionally equivalent to the CURRENT_TIMESTAMP function. 
CREATE OR REPLACE FUNCTION getCurrentTime
RETURN TIMESTAMP
IS
BEGIN
    RETURN CURRENT_TIMESTAMP;
END;


-- A funcion that returns the length of a string (which can be used for MEDIATYPE.Name)
CREATE OR REPLACE FUNCTION getStringLength(str String)
RETURN NUMBER
IS
BEGIN
    RETURN length(str);
END;
SET SERVEROUTPUT ON;




-- 3.2
-- A function that returns the average of all the invoices. 
CREATE OR REPLACE FUNCTION averageOfInvoices
RETURN NUMBER
IS
    average NUMBER;
BEGIN
    SELECT avg(TOTAL) INTO average FROM CHINOOK.INVOICE;
    RETURN average;
END;


-- Returns the most expensive track from all the tracks. Only returns one een if there is more than one. 
SELECT * FROM CHINOOK.TRACK 
WHERE unitPrice = 
    (SELECT max(unitPrice) FROM CHINOOK.TRACK
    WHERE ROWNUM = 1) 
    AND ROWNUM = 1;


-- 3.3 
-- Returns the average price of invoiceline items in the invoiceline table. 
CREATE OR REPLACE FUNCTION averageInvoicelineUnitPrice
RETURN NUMBER
IS
    averageNumber NUMBER;
BEGIN
    SELECT avg(unitPrice) INTO averageNumber FROM CHINOOK.INVOICELINE; 
    RETURN averageNumber;
END;


-- 3.4
-- Returns all employees who were born after 1968
CREATE OR REPLACE FUNCTION getEmployeesAfter1968
RETURN







