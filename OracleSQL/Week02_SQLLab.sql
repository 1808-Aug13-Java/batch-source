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
RETURN SYS_REFCURSOR
IS
    returnCursor SYS_REFCURSOR;
BEGIN
    OPEN returnCursor FOR 
        SELECT firstName FROM CHINOOK.EMPLOYEE E WHERE E.birthdate >= DATE '1969-01-01';
    RETURN returnCursor;
END;
/* -- The folowing code is for testing the preceding statement. 
SET SERVEROUTPUT ON;
DECLARE
    -- The variable to our CURSOR pointer
    SVAR SYS_REFCURSOR;
    TEMP_NAME EMPLOYEE.EMP_NAME % TYPE;--VARCHAR2(50);
BEGIN
    -- Gets the CURSOR pointer to the dataset
    SVAR := getEmployeesAfter1968();
    
    LOOP
        FETCH SVAR INTO TEMP_NAME; -- "Active Set" is each row returned by the cursor
        -- If it can't find a Temp_ID and Temp_Name, break;
        EXIT WHEN SVAR % NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(' is current ID to ' || TEMP_NAME);
    END LOOP;
    
    -- Don't forget to close that pointer
    CLOSE SVAR;
END;
*/

-- 4.1
-- A stored procedure that selects the first an last names of all employees. The provided is an out-variable that will 
-- hold the resulting query pointer. 
CREATE OR REPLACE PROCEDURE getAllEmployeeNames(sVar OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN sVar FOR 
        SELECT E.firstname, E.lastname FROM CHINOOK.EMPLOYEE E;
END;


-- 4.2
-- Updates the personal information of an employee specified by the employee's id
CREATE OR REPLACE PROCEDURE updateEmployee(employeeIdIn CHINOOK.EMPLOYEE.employeeId % TYPE
                                            , firstNameIn CHINOOK.EMPLOYEE.firstName % TYPE
                                            , lastNameIn CHINOOK.EMPLOYEE.lastName % TYPE)
IS
BEGIN
    UPDATE CHINOOK.EMPLOYEE E
        SET E.firstName = firstNameIn, 
            E.lastName = lastNameIn
        WHERE E.employeeId = employeeIdIn;
END;

BEGIN
    updateEmployee(1, 'asdf', 'qwer');
END;

-- Returns the managers of employees
CREATE OR REPLACE FUNCTION getEmployeeManagers(employeeIdIn CHINOOK.EMPLOYEE.employeeId % TYPE)
RETURN SYS_REFCURSOR
IS
    sVar SYS_REFCURSOR;
BEGIN
    OPEN sVar FOR 
        SELECT E.firstName AS Emp_FirstName, E.lastName As Emp_LastName, 
                M.firstName AS Mng_FirstName, M.lastName AS Mng_LastName
            FROM CHINOOK.EMPLOYEE E
            JOIN CHINOOK.EMPLOYEE M 
                ON E.reportsTo = M.employeeId
            WHERE E.employeeId = employeeIdIn;
    RETURN sVar;
END;
DECLARE
    -- The variable to our CURSOR pointer
    SVAR SYS_REFCURSOR;
    TEMP_NAME CHINOOK.EMPLOYEE.firstName % TYPE;--VARCHAR2(50);
    TEMP_NAME2 CHINOOK.EMPLOYEE.lastName % TYPE;--VARCHAR2(50);
    TEMP_NAME3 CHINOOK.EMPLOYEE.firstName % TYPE;--VARCHAR2(50);
    TEMP_NAME4 CHINOOK.EMPLOYEE.lastName % TYPE;--VARCHAR2(50);
BEGIN
    -- Gets the CURSOR pointer to the dataset
    SVAR := getEmployeeManagers(3);
    
    LOOP
        FETCH SVAR INTO TEMP_NAME, TEMP_NAME2,  TEMP_NAME3,  TEMP_NAME4; -- "Active Set" is each row returned by the cursor
        -- If it can't find a Temp_ID and Temp_Name, break;
        EXIT WHEN SVAR % NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(TEMP_NAME || ' ' || TEMP_NAME2 || ' is managed by ' || TEMP_NAME3 || ' ' || TEMP_NAME4);
    END LOOP;
    
    -- Don't forget to close that pointer
    CLOSE SVAR;
END;



-- 4.3
-- Returns the name and company of a given customer, and a cursor as an OUT parameter
CREATE OR REPLACE PROCEDURE getCustomerData(customerIdIn CHINOOK.CUSTOMER.customerId % TYPE, sVar OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN sVar FOR 
        SELECT C.firstName, C.lastName, C.company
            FROM CHINOOK.CUSTOMER C
            WHERE C.customerId = customerIdIn;
END;





-- 5.0 
-- A transaction that deletes an invoice with it's child invoicelines. 
CREATE OR REPLACE PROCEDURE deleteInvoice(invoiceIdIn CHINOOK.INVOICE.invoiceId % TYPE)
IS
BEGIN
    SET TRANSACTION NAME 'Deleting Invoice';
    DELETE FROM CHINOOK.INVOICELINE IL
        WHERE IL.invoiceId = invoiceIdIn;
    DELETE FROM CHINOOK.INVOICE I
        WHERE I.invoiceId = invoiceIdIn;
    COMMIT;
END;

BEGIN 
    deleteInvoice(99999);
END;


CREATE OR REPLACE PROCEDURE insertCustomer(
                                        customerIdIn CHINOOK.CUSTOMER.customerId % TYPE, 
                                        firstNameIn CHINOOK.CUSTOMER.firstName % TYPE, 
                                        lastNameIn CHINOOK.CUSTOMER.lastName % TYPE, 
                                        companyIn CHINOOK.CUSTOMER.company % TYPE, 
                                        addressIn CHINOOK.CUSTOMER.address % TYPE, 
                                        cityIn CHINOOK.CUSTOMER.city % TYPE, 
                                        stateIn CHINOOK.CUSTOMER.state % TYPE, 
                                        countryIn CHINOOK.CUSTOMER.country % TYPE, 
                                        postalCodeIn CHINOOK.CUSTOMER.postalCode % TYPE, 
                                        phoneIn CHINOOK.CUSTOMER.phone % TYPE, 
                                        faxIn CHINOOK.CUSTOMER.fax % TYPE, 
                                        emailIn CHINOOK.CUSTOMER.email % TYPE, 
                                        supportRepIdIn CHINOOK.CUSTOMER.supportRepId % TYPE)
IS
BEGIN
    SET TRANSACTION NAME 'Insert Employee';
    INSERT INTO CHINOOK.CUSTOMER (customerId, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, supportRepId)
        VALUES (customerIdIn, firstNameIn, lastNameIn, companyIn, addressIn, cityIn, stateIn, countryIn, postalCodeIn, phoneIn, faxIn, emailIn, supportRepIdIn);
    COMMIT;
END;

BEGIN
    INSERT INTO CHINOOK.CUSTOMER VALUES (2900, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 1);
END;



-- 6.1
-- Create a trigger that fires after a new record is inserted into EMPLOYEE. Specifically, if Franklin Newmen is entered, 
-- remove all the customers with the same name to give him space. 
CREATE OR REPLACE TRIGGER TR_INSERT_EMPLOYEE
AFTER INSERT ON CHINOOK.EMPLOYEE
FOR EACH ROW
BEGIN
    IF (:NEW.firstName = 'Franklin' AND :NEW.lastName = 'Newman') THEN
        -- Can't modify the table being modified on AFTER
        DELETE FROM CHINOOK.CUSTOMER C
        WHERE C.firstName = :NEW.firstName
            AND C.lastName != :NEW.lastName;
    END IF;
END;
BEGIN
    INSERT INTO CHINOOK.CUSTOMER (customerId, firstName, lastName, email) VALUES (1009, 'Franklin', 'Hansen', 'n@a.net');
    INSERT INTO CHINOOK.EMPLOYEE (employeeId, firstName, lastName) VALUES (101, 'Franklin', 'Newman');
END;


CREATE SEQUENCE SQ_ALBUM_UPDATES
START WITH 1 -- Needs to start at one as Oracle won't let it start at zero, which is dumb. 
INCREMENT BY 1;
-- Create a trigger that does something after insertion of a record in the Album table
CREATE OR REPLACE TRIGGER TR_UPDATE_ALBUM
AFTER UPDATE ON CHINOOK.ALBUM
FOR EACH ROW
BEGIN
    -- Perform arbitrary task, like number of updates performed
    NULL;
    --SQ_ALBUM_UPDATES.nextVal;
END;

CREATE OR REPLACE TRIGGER TR_DELETE_CUSTOMER
AFTER DELETE ON CHINOOK.CUSTOMER
BEGIN
    -- Perform arbitrary task. 
    NULL;
END;


-- 7.1 Get customer names along with invoices. 
SELECT C.firstName, C.lastName, I.invoiceId FROM CHINOOK.INVOICE I
JOIN CHINOOK.CUSTOMER C ON I.customerId = C.customerId;

-- 7.2 Get all customer names along with all invoice ids with their totals.
SELECT C.firstName, C.lastName, I.invoiceId, I.total FROM CHINOOK.CUSTOMER C
FULL OUTER JOIN CHINOOK.INVOICE I ON C.customerId = I.customerId;

-- 7.3 Display all Artists along with the albums they made
SELECT AR.name AS Artist_Name, AL.title AS Album_Title FROM CHINOOK.ALBUM AL
RIGHT JOIN CHINOOK.ARTIST AR ON AL.artistId = AR.artistId;

-- 7.4 Cross All albums with All artists. Ordered by artist name If run, this will bring the server to it's knees. (For a minute)
SELECT AR.name AS Artist_Name, AL.title AS Album_Title FROM CHINOOK.ALBUM AL
CROSS JOIN CHINOOK.ARTIST AR
ORDER BY AR.name ASC;

-- 7.5 Joins all employees with their managers. 
SELECT * FROM CHINOOK.EMPLOYEE Emp
JOIN CHINOOK.EMPLOYEE Mngr ON Emp.reportsTo = Mngr.employeeId;


