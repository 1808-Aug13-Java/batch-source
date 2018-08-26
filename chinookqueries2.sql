SET SERVEROUTPUT ON;

-- CHINOOK.
select * from CHINOOK.EMPLOYEE;
select * from CHINOOK.Employee where LastName = 'King';
select * from CHINOOK.Employee where FirstName = 'Andrew' AND ReportsTo IS NULL;

select * from CHINOOK.Album Order by Title desc;

select FirstName from CHINOOK.Customer order by city asc;

insert into Genre values (26, 'Ska');
insert into Genre values (27, 'Screamo');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES (9, 'Tizzlebottom', 'Fitzera', 'Faerie', null, TO_DATE('1900-1-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-8-21 00:00:00','yyyy-mm-dd hh24:mi:ss'), '987 6 ST FB', 'Faeland', 'FW', 'Canada', 'F35 234', '+1 (403) 467-3352', '+1 (403) 467-8773', 'fitzera@chinookcorp.com');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email)
VALUES (10, 'Sparxira', 'Elanda', 'Faerie', 9, TO_DATE('1900-1-2 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-8-22 00:00:00','yyyy-mm-dd hh24:mi:ss'), '987 6 ST FB', 'Faeland', 'FW', 'Canada', 'F35 234', '+1 (403) 467-3353', '+1 (403) 467-8774', 'elanda@chinookcorp.com');

INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
VALUES (60, 'Arthur', 'Pendragon', 'The Roundtable LLC', 'Great Britain somewhere', 'Camelot', 'CL', 'Britain', '12548', '+55 (12) 3923-5556', '+55 (12) 3923-5567', 'pendragon@table.com.br', 3);

INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
VALUES (61, 'Lancelot', 'du Lac', 'The Roundtable LLC', 'Great Britain somewhere', 'Camelot', 'CL', 'Britain', '12548', '+55 (12) 3923-5558', '+55 (12) 3923-5569', 'dulac@table.com.br', 3);

UPDATE CHINOOK.CUSTOMER 
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
where FIRSTNAME = 'Andrew' AND LASTNAME = 'Mitchell';

UPDATE CHINOOK.ARTIST
SET NAME = 'CCR'
WHERE NAME =  'CREEDENCE CLEARWATER REVIVAL';

SELECT * 
FROM CHINOOK.INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

SELECT *
FROM CHINOOK.INVOICE
WHERE TOTAL BETWEEN 15 AND 20;

SELECT *
FROM CHINOOK.EMPLOYEE
WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

--Delete
DELETE FROM INVOICELINE WHERE INVOICEID IN 
(SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID IN 
(SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'));

DELETE FROM INVOICE WHERE CUSTOMERID IN 
(SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');

DELETE FROM CHINOOK.CUSTOMER 
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

/*--------------------------------------------------
section 3
--------------------------------------------------*/
--3.1
--GET CURRENT DATE
SELECT CURRENT_TIMESTAMP FROM dual;
--GET LENGTH OF A COLUMN'S VALUE
SELECT NAME, length(name)
     FROM CHINOOK.MEDIATYPE;
     
--3.2
-- AVG OF TOTAL FROM INVOICE
SELECT TOTAL FROM CHINOOK.INVOICE;
SELECT AVG(TOTAL) FROM CHINOOK.INVOICE;

--MOST EXPENSIVE
SELECT MAX(UNITPRICE) FROM CHINOOK.TRACK;

--3.3 Create a function that returns the average price of invoiceline items in the invoiceline table
select avg(unitprice) from chinook.invoiceline;
--3.4 Task – Create a function that returns all employees who are born after 1968.
/*
Created a table object to store a table in so I could return it.
*/
--CREATE OR REPLACE TYPE EMP_ROW AS OBJECT(EMPID NUMBER(5));

--CREATE OR REPLACE TYPE EMP_TABLE AS TABLE OF EMP_ROW;

CREATE OR REPLACE FUNCTION BD_AFTER_DATE (D IN DATE)
RETURN EMP_TABLE
IS
return_TABLE EMP_TABLE;
i INTEGER;
BEGIN
    return_table := emp_table();
    i:=0;
    for cur in (select employeeid --Iterates through a selection 
                from chinook.employee e
                where e.birthdate >= d) LOOP
        i:=i+1;
        return_table.extend();
        return_table(i):=emp_row(cur.employeeid);
        END LOOP;
    return return_table;
END;
/
SELECT * FROM TABLE(BD_AFTER_DATE('01-JAN-64'));



/*--------------------------------------------------
section 4
--------------------------------------------------*/

--4.1 Task – Create a stored procedure that selects the first and last names of all the employees.

CREATE OR REPLACE PROCEDURE GET_ALL_NAMES (S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR 
    SELECT FIRSTNAME, LASTNAME FROM CHINOOK.EMPLOYEE;
END;
/

DECLARE
    SVAR SYS_REFCURSOR; -- EXPLICIT CURSOR BECAUSE WE DECLARE IT
    TEMP_FIRST CHINOOK.EMPLOYEE.FIRSTNAME%TYPE; -- MATCHES TYPES WITH THE TABLE'S DECLARED TYPES
    TEMP_LAST CHINOOK.EMPLOYEE.LASTNAME%TYPE;
BEGIN
    GET_ALL_NAMES(SVAR);
    --NOW WE SHOULD HAVE ACCESS TO OUR EMPLOYEES THROUGH SVAR
    LOOP
        FETCH SVAR INTO TEMP_FIRST, TEMP_LAST; -- "ACTIVE SET" IS EACH REOW RETURNED BY THE CURSOR
        EXIT WHEN SVAR%NOTFOUND; -- WHEN SVAR DOESN'T FIND VARIABLES FOR TEMP_ID OR TEMP_NAME, IT STOPS
        DBMS_OUTPUT.PUT_LINE(TEMP_FIRST||' '||TEMP_LAST);
    END LOOP;
    CLOSE SVAR;
END;
/

--4.2 Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_ADDRESS (I EMPLOYEE.EMPLOYEEID%TYPE, A EMPLOYEE.ADDRESS%TYPE, C EMPLOYEE.CITY%TYPE, S EMPLOYEE.STATE%TYPE)
IS
BEGIN
    UPDATE EMPLOYEE E SET ADDRESS = A WHERE E.EMPLOYEEID = I;
    UPDATE EMPLOYEE E SET CITY = C WHERE E.EMPLOYEEID = I;
    UPDATE EMPLOYEE E SET STATE = S WHERE E.EMPLOYEEID = I;
END;
/

BEGIN
    UPDATE_ADDRESS (1, 'YE OLDE TEST', 'TESTCITY', 'TC');
END;
/
SELECT * FROM EMPLOYEE;
--Task – Create a stored procedure that returns the managers of an employee.

CREATE OR REPLACE PROCEDURE GET_MANAGERS(I EMPLOYEE.EMPLOYEEID%TYPE, S OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN S FOR
    SELECT M.FIRSTNAME MANAGER, M.LASTNAME, E.FIRSTNAME EMPLOYEE, E.LASTNAME
    FROM EMPLOYEE E, EMPLOYEE M
    WHERE E.REPORTSTO = M.EMPLOYEEID;
END;
/
    
DECLARE
    SVAR SYS_REFCURSOR; -- EXPLICIT CURSOR BECAUSE WE DECLARE IT
    EMP_FIRST EMPLOYEE.FIRSTNAME%TYPE; -- MATCHES TYPES WITH THE TABLE'S DECLARED TYPES
    EMP_LAST EMPLOYEE.LASTNAME%TYPE;
    MAN_FIRST EMPLOYEE.FIRSTNAME%TYPE;
    MAN_LAST EMPLOYEE.LASTNAME%TYPE;
BEGIN
    GET_MANAGERS(1,SVAR);
    --NOW WE SHOULD HAVE ACCESS TO OUR EMPLOYEES THROUGH SVAR
    LOOP
        FETCH SVAR INTO MAN_FIRST, MAN_LAST, EMP_FIRST, EMP_LAST; -- "ACTIVE SET" IS EACH REOW RETURNED BY THE CURSOR
        EXIT WHEN SVAR%NOTFOUND; -- WHEN SVAR DOESN'T FIND VARIABLES FOR TEMP_ID OR TEMP_NAME, IT STOPS
        DBMS_OUTPUT.PUT_LINE(MAN_FIRST||' '||MAN_LAST||' MANAGES '||EMP_FIRST||' '||EMP_LAST);
    END LOOP;
    CLOSE SVAR;
END;
/
--4.3 Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE GET_NAME_COMP(C CUSTOMER.CUSTOMERID%TYPE, fname out varchar2, lname out varchar2, cname out varchar2)
IS
BEGIN
    select firstname, lastname, company into fname, lname, cname
    from customer
    where c=customerid;
END;
/
declare
fname customer.firstname%type;
lname customer.lastname%type;
cname customer.company%type;
begin
    get_name_comp(1, fname, lname, cname);
    dbms_output.put_line(fname||' '||lname||' '||cname);
end;
/


/*--------------------------------------------------
section 5
--------------------------------------------------*/

--create a transaction that given an invoiceID will delete that invoice
create or replace procedure del_invoice(i invoice.invoiceid%type)
is
begin
    set transaction name del_inv;
    savepoint before_del;
    delete from invoice where invoiceid=i;
end;
/
commit;
--create a transaction nested within a stored procedure that inserts a new record in the customer table
create or replace procedure new_customer(fn customer.firstname%type, ln customer.lastname%type, email customer.email%type)
is
begin
    set transaction name ins_cust;
    savepoint before_ins;
    insert into customer(firstname, lastname, email) values(fn,ln,email);
end;
/
commit;
/*--------------------------------------------------
section 6
--------------------------------------------------*/

--create an after insert trigger on the employee table fired after a new record is insert into the table.
create or replace trigger TR_INSERT_EMPLOYEE_COMMIT
AFTER INSERT ON EMPLOYEE
BEGIN
    COMMIT;
END;
/

--create an after update trigger on the album table that fires after a row is inserted in the table

CREATE OR REPLACE TRIGGER TR_UPDATE_ALBUM_COMMIT
AFTER UPDATE ON ALBUM
BEGIN
    COMMIT;
END;
/

--create an after delete trigger on the customer table that fires after a row is deleted from the table
CREATE OR REPLACE TRIGGER TR_DEL_CUSTOMER_COMMIT
AFTER DELETE ON CUSTOMER
BEGIN
    COMMIT;
END;
/
/*--------------------------------------------------
section 7
--------------------------------------------------*/

--7.1
SELECT 
    C.FIRSTNAME AS FIRST,
    C.LASTNAME AS LAST,
    I.INVOICEID AS INVOICEID
FROM CHINOOK.CUSTOMER C
INNER JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;

--7.2

SELECT 
    C.CUSTOMERID AS CUST_ID,
    C.FIRSTNAME AS FIRST,
    C.LASTNAME AS LAST,
    I.INVOICEID AS INVOICEID,
    I.TOTAL AS TOTAL
FROM CHINOOK.CUSTOMER C
FULL OUTER JOIN CHINOOK.INVOICE I
ON C.CUSTOMERID = I.CUSTOMERID;

--7.3

SELECT
    A.TITLE AS TITLE,
    C.NAME AS ARTIST
FROM CHINOOK.ALBUM A
RIGHT OUTER JOIN CHINOOK.ARTIST C
ON C.ARTISTID = A.ARTISTID;

--7.4

SELECT A.ALBUMID ALBUMID,
A.TITLE TITLE,
A.ARTISTID ARTISTID,
C.ARTISTID ARTISTID_0,
C.NAME NAME 
FROM CHINOOK.ALBUM A
CROSS JOIN CHINOOK.ARTIST C
ORDER BY ARTISTID ASC;

--7.5
SELECT 
    E.EMPLOYEEID EMP,
    M.EMPLOYEEID MNG
FROM CHINOOK.EMPLOYEE E
JOIN CHINOOK.EMPLOYEE M
ON M.EMPLOYEEID = E.REPORTSTO;