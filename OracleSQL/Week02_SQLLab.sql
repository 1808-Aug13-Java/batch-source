conn chinook/p4ssw0rd;

disconnect chinook;

-- 2.1 Select
SELECT * 
FROM CHINOOK.EMPLOYEE;

SELECT * 
FROM CHINOOK.EMPLOYEE
WHERE Lastname = 'King';

SELECT *
FROM CHINOOK.EMPLOYEE 
WHERE Firstname = 'Andrew' 
    AND ReportsTo IS NULL;

-- 2.2 Order By
SELECT * 
FROM CHINOOK.ALBUM
ORDER BY Title;

SELECT C.FirstName
FROM CHINOOK.CUSTOMER C
ORDER BY City;

-- 2.3 Insert Into
INSERT INTO CHINOOK.GENRE (GENREID, NAME) VALUES (26, 'Hard Rock');
INSERT INTO CHINOOK.GENRE VALUES ((SELECT count(*) FROM CHINOOK.GENRE)+1, 'Pirate Metal');

INSERT INTO CHINOOK.EMPLOYEE
(EmployeeID, LastName, FirstName, Title, ReportsTo, Birthdate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES (
    (SELECT count(*) FROM CHINOOK.EMPLOYEE)+1, 
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
VALUES ((SELECT count(*) FROM CHINOOK.EMPLOYEE)+1, 'Low', 'Daniel', 'IT Staff', 6, DATE '1987-02-03', CURRENT_DATE, '9001 N Scout Dr', 'Lethbridge', 'AB', 'Canada', 'T1K 976', '+1 (403) 123-9876', '+1 (403) 123-9876', 'low@chinookcorp.net');

INSERT INTO CHINOOK.CUSTOMER
(CustomerID, LastName, FirstName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepID)
VALUES ((SELECT count(*) FROM CHINOOK.CUSTOMER)+1, 'Drowned', 'Ben', 'Nintendo', '9001 N IDK Pl', 'Lethbridge', 'AB', 'Canada', 'T1K 976', '+1 (403) 123-9876', '+1 (403) 123-4567', '1234@nintendo.com', 3);
INSERT INTO CHINOOK.CUSTOMER
(CustomerID, LastName, FirstName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepID)
VALUES ((SELECT count(*) FROM CHINOOK.CUSTOMER)+1, 'Uncle', 'My', 'Nintendo', '9001 S Keen Bl', 'Lethbridge', 'AB', 'Canada', 'T1K 976', '+1 (403) 123-9876', '+1 (403) 987-6543', '9876@nintendo.com', 3);


-- 2.4 Update
UPDATE CHINOOK.CUSTOMER
SET FirstName = 'Robert', LastName = 'Walter'
WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';

UPDATE CHINOOK.ARTIST
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';


-- 2.5 Like
SELECT * FROM CHINOOK.INVOICE
WHERE BillingAddress LIKE 'T%';


-- 2.6 Between
SELECT * FROM CHINOOK.INVOICE
WHERE Total >= 15 AND Total <= 50;

SELECT * FROM CHINOOK.EMPLOYEE
WHERE HireDate >= DATE '2003-06-01' AND HireDate <= DATE '2004-03-01';


-- 2.7 Delete
-- DON'T DELETE THE OTHER STUFF!!! ONLY DELETE CUSTOMER, and set Forign KEys to it to null. Saves Data. 
-- There are two ways of doing this, deleting the other rows in the other tables, or setting a forign key to null. 
-- I would assume, without any other knowledge, that 
UPDATE CHINOOK.INVOICE
SET 
WHERE CustomerID IN 
    (SELECT CustomerID FROM CHINOOK.CUSTOMER
        WHERE FirstName = 'Robert' AND LastName = 'Walter'
    );

DELETE FROM CHINOOK.CUSTOMER
WHERE FirstName = 'Robert' AND LastName = 'Walter';









