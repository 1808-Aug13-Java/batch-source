--this is the script for the tuesday exercise on queries
--Create a table INVOICE which includes an id, date, customer id, and amount

--2. Create a table CUSTOMER which includes relevant customer information
--	1. Customer id
--	2. Customer name
--	3. Phone number
--
--3. Customer id in the invoice table should make a reference to the customer id in the customer table
--4. Insert at least 50 records into your invoice table and at least 10 records into your customer table


--6. Create a query which shows each customer and the number of purchases made by each
--7. Create a query which shows each customer and the total cost of all their purchases
--8. Create a query which returns all purchases which took place in the last month, display them in descending order
--Create a query which show the top three most expensive purchases





CREATE TABLE CUSTOMER (
    CUSTOMER_ID NUMBER(5) CONSTRAINT PK_CSTR PRIMARY KEY,
    CUSTOMER_FIRST_NAME VARCHAR2(10),
    CUSTOMER_LAST_NAME VARCHAR2(10),
    PHONE_NUMBER NUMBER(10)
    );

CREATE TABLE INVOICE (
    INVOICE_ID NUMBER(5) UNIQUE,
    INVOICE_DATE DATE,
    CUSTOMER_ID NUMBER(5) CONSTRAINT FK_CSTR_ID REFERENCES CUSTOMER,
    AMOUNT NUMBER(7,2)
);


insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (86842, 'Ivy', 'Rispine', '8892224050');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (16090, 'Mendy', 'O''Hearn', '2291413201');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (88722, 'Mersey', 'Fosberry', '0277955378');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (54439, 'Kevin', 'McKague', '5923851849');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (46413, 'Dasi', 'Shillam', '8901968428');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (73070, 'Wes', 'Olivo', '5947369486');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (8494, 'Juliana', 'Scoates', '5438820457');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (73775, 'Jeremy', 'Ventris', '0213324075');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (64919, 'Mauricio', 'Swoffer', '0218996500');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (58739, 'Ogdan', 'Atyeo', '4250573672');


--ALTER TABLE INVOICE
--DROP CONSTRAINT FK_CSTR_ID;
--DROP TABLE INVOICE;
--DROP TABLE CUSTOMER;

insert into INVOICE values (96558, Date '2014-01-06', 86842, 47344.32);
insert into INVOICE values (56923, Date '2011-06-04', 86842, 48821.51);
insert into INVOICE values (68485, Date '2011-06-22', 86842, 39094.06);
insert into INVOICE values (50926, Date '2017-11-14', 86842, 71440.61);
insert into INVOICE values (360, Date '2016-07-13', 86842, 54332.04);
insert into INVOICE values (38628, Date '2014-07-12', 16090, 60474.48);
insert into INVOICE values (70059, Date '2010-10-28', 16090, 35874.11);
insert into INVOICE values (49459, Date '2011-06-16', 16090, 27884.23);
insert into INVOICE values (79316, Date '2016-08-18', 16090, 29678.73);
insert into INVOICE values (38890, Date '2013-12-03', 16090, 28896.57);
insert into INVOICE values (86810, Date '2017-11-04', 88722, 32871.22);
insert into INVOICE values (59354, Date '2016-10-31', 88722, 44024.87);
insert into INVOICE values (12245, Date '2013-10-22', 88722, 64797.46);
insert into INVOICE values (88548, Date '2012-11-23', 88722, 11233.37);
insert into INVOICE values (45988, Date '2012-07-08', 88722, 99256.0);
insert into INVOICE values (4485, Date '2018-02-25', 54439, 73657.05);
insert into INVOICE values (43650, Date '2018-07-15', 54439, 43130.28);
insert into INVOICE values (52716, Date '2011-08-15', 54439, 67859.28);
insert into INVOICE values (89296, Date '2014-10-24', 54439, 11061.2);
insert into INVOICE values (78645, Date '2018-04-24', 54439, 71696.58);
insert into INVOICE values (41779, Date '2013-07-20', 46413, 13807.88);
insert into INVOICE values (34110, Date '2015-12-19', 46413, 42675.17);
insert into INVOICE values (29187, Date '2017-06-28', 46413, 62655.57);
insert into INVOICE values (33948, Date '2016-10-23', 46413, 5569.86);
insert into INVOICE values (22044, Date '2018-03-10', 46413, 42251.33);
insert into INVOICE values (51609, Date '2014-12-11', 73070, 14072.66);
insert into INVOICE values (37646, Date '2013-05-01', 73070, 672.77);
insert into INVOICE values (55874, Date '2016-09-22', 73070, 76904.69);
insert into INVOICE values (21037, Date '2018-08-07', 73070, 66314.91);
insert into INVOICE values (23235, Date '2016-08-04', 73070, 74028.46);
insert into INVOICE values (22211, Date '2012-11-10', 8494, 79426.89);
insert into INVOICE values (5311, Date '2014-05-12', 8494, 35195.1);
insert into INVOICE values (78359, Date '2012-06-26', 8494, 54944.7);
insert into INVOICE values (59879, Date '2013-06-08', 8494, 83775.86);
insert into INVOICE values (64333, Date '2015-03-31', 8494, 4526.09);
insert into INVOICE values (40336, Date '2017-05-27', 73775, 24827.78);
insert into INVOICE values (70820, Date '2018-04-15', 73775, 45833.99);
insert into INVOICE values (89447, Date '2013-07-10', 73775, 4899.14);
insert into INVOICE values (3842, Date '2016-07-11', 73775, 25180.19);
insert into INVOICE values (90045, Date '2015-10-24', 73775, 77651.24);
insert into INVOICE values (28476, Date '2014-09-03', 64919, 45748.9);
insert into INVOICE values (27938, Date '2015-12-15', 64919, 28541.45);
insert into INVOICE values (43598, Date '2016-10-12', 64919, 89061.18);
insert into INVOICE values (40104, Date '2014-11-05', 64919, 45819.66);
insert into INVOICE values (22943, Date '2014-12-13', 64919, 47156.21);
insert into INVOICE values (11716, Date '2011-04-29', 58739, 89717.99);
insert into INVOICE values (89190, Date '2011-05-22', 58739, 37143.64);
insert into INVOICE values (879, Date '2014-12-23', 58739, 55084.06);
insert into INVOICE values (17458, Date '2013-08-08', 58739, 73721.7);
insert into INVOICE values (98308, Date '2013-06-22', 58739, 5537.34);


--FIRST QUERY
SELECT COUNT(INVOICE.INVOICE_ID)AS NUMBER_OF_PURCHASES, invoice.customer_id
FROM INVOICE
group by customer_id
--GROUP BY INVOICE.CUSTOMER_ID
--HAVING COUNT (invoice.amount>0);
ORDER BY COUNT(INVOICE.INVOICE_ID);

--SECONR
SELECT INVOICE.CUSTOMER_ID, SUM(INVOICE.AMOUNT)
FROM INVOICE
group by customer_id
--GROUP BY INVOICE.CUSTOMER_ID
--HAVING COUNT (invoice.amount>0);
ORDER BY COUNT(INVOICE.INVOICE_ID);

--THIRD
Select * From INVOICE
WHERE
INVOICE_DATE >= add_months(sysdate, -6)
ORDER BY INVOICE_DATE DESC;

--FORTH
--SELECT MAX(INVOICE.AMOUNT)
--FROM INVOICE
select amount
from invoice
where ROWNUM <=3
ORDER BY AMOUNT;
COMMIT;

--wednesday exercises
--Create a query which returns all of the invoices which have a listed customer, but not invoices who have no customer listed and not customers who have no invoices listed
--Create a query which returns all of the invoices and their customer, not invoices who have no customer listed but include customers which have no invoices listed
--Create a query which shows each record in the invoice table, along with the name of the customer
--Create a query which shows the name of each customer and the total amount they have spent
--Create a query which returns the record of the customer who made the most recent purchase
--Create a query which shows the purchaser of each invoice and the subtotal of each invoice if 6% sales tax was applied to the subtotal to get the price of each invoice

-- insert customers that do not have any invoices
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (58738, 'Mike', 'Yo', '4129375039');
insert into CUSTOMER (CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, PHONE_NUMBER) values (58740, 'Katie', 'Bonds', '1023904856');

-- insert invoices that do not have any customers
--invoice id, date, customer id, amount
insert into INVOICE (INVOICE_ID, INVOICE_DATE,AMOUNT)values (98307, Date '2013-06-23', 2000.34);
insert into INVOICE (INVOICE_ID, INVOICE_DATE,AMOUNT)values (98306, Date '2013-06-22', 1000.34);

--1)--Create a query which returns all of the invoices which have a listed customer,
--but not invoices who have no customer listed and
--not customers who have no invoices listed

SELECT *
FROM INVOICE
WHERE EXISTS (
    SELECT *
    FROM CUSTOMER
    WHERE customer.customer_id = invoice.customer_id
    )
ORDER BY INVOICE.INVOICE_ID;
 COMMIT;
--ONLY INVOICE VALUES 98307 AND 98306 SHOULD NOT APPEAR
--2) --Create a query which returns all of the invoices and their customer,
--not invoices who have no customer listed but
--include customers which have no invoices listed

SELECT C.CUSTOMER_FIRST_NAME AS FIRSTNAME,c.customer_last_name AS LASTNAME,
    I.*
FROM CUSTOMER C
LEFT JOIN invoice I
ON c.customer_id= i.customer_id
ORDER BY i.invoice_id;
-- CUSTOMERS KATIE BANKS AND MIKE YO DO NOT HAVE INVOICES BUT STILL APPEAR,
--INVOICES W/O CUSTOMER ID VALUES DO NOT APPEAR, THERE IS NO INVOICE ID WITH A NULL CUSTOMER

--3) --Create a query which shows each record in the invoice table,
--along with the name of the customer
SELECT C.CUSTOMER_FIRST_NAME AS FIRSTNAME,c.customer_last_name AS LASTNAME,
    I.*
FROM INVOICE I
LEFT JOIN CUSTOMER C
ON i.customer_id=c.customer_id;
--ALL INVOICES APPEAR, EVEN ONES WITH NOT CUSTOMER,

--4)--Create a query which shows name of each customer and
--the total amount they have spent
--even customers who do not have purchases, they should have amount =0

SELECT C.CUSTOMER_FIRST_NAME AS FIRST, C.CUSTOMER_LAST_NAME AS LAST, SUM(I.AMOUNT)AS TOTAL
FROM CUSTOMER C
LEFT JOIN INVOICE I
ON C.CUSTOMER_ID= I.CUSTOMER_ID
GROUP BY c.customer_first_name, c.customer_last_name
ORDER BY TOTAL;

--5)--Create a query which returns the record of the customer who made the most recent purchase

SELECT *
FROM CUSTOMER
LEFT JOIN INVOICE I
ON CUSTOMER.CUSTOMER_ID =I.CUSTOMER_ID
WHERE I.INVOICE_DATE=(
SELECT MAX(INVOICE_DATE)
FROM INVOICE);

--6)--Create a query which shows the purchaser of each invoice and
--the subtotal of each invoice if
--6% sales tax was applied to the subtotal to get the price of each invoice

SELECT C.*, ROUND((I.AMOUNT/1.06),2)
FROM CUSTOMER C
LEFT JOIN INVOICE I
ON C.CUSTOMER_ID=I.CUSTOMER_ID;
COMMIT;

--- CREATE A FUNCTION WHICH CALCULATES THE SUBTOTAL OF A GIVEN TOTAL, IF THE TOTAL IS EQUAL TO THE SUBTOTAL WITH 6% TAX - USE IT TO RECREATE THE QUERY FROM YESTERDAY
--- CREATE A FUNCTION WHICH TAKES IN A STRING AND A NUMBER, AND CONCATINATES THAT STRING THAT NUMBER OF TIMES WITH A SPACE BETWEEN EACH AND RETURNS THE RESULT 
--    (EX. MY_FUNC('CAT',5) RETURNS 'CAT CAT CAT CAT CAT')
--- CREATE A STORED PROCEDURE WHICH CHECK ALL OF THE INVOICES IN YOUR DATABASE AND DELETES ALL ORPHAN RECORDS IN YOUR INVOICE TABLE
--- CREATE A STORED PROCEDURE WHICH ENTERS A NEW INVOICE INTO THE INVOICE TABLE - IF THE DATE IS IN THE FUTURE, IT SHOULD NOT BE ENTERED AND A MESSAGE SHOULD BE PRINTED TO THE CONSOLE
--    IN ADDITION IF THE VALUE OF THE INVOICE IS NEGATIVE, IT SHOULD NOT BE ENTERED INTO THE TABLE AND A MESSAGE SHOULD BE PRINTED TO THE CONSOLE

--- CREATE A FUNCTION WHICH CALCULATES THE SUBTOTAL OF A GIVEN TOTAL, IF THE TOTAL IS EQUAL TO THE SUBTOTAL WITH 6% TAX - USE IT TO RECREATE THE QUERY FROM YESTERDAY
CREATE OR REPLACE FUNCTION APPLY_SUBTOTAL(SUBTOTAL IN NUMBER)
RETURN NUMBER
IS
BEGIN
   RETURN ROUND((SUBTOTAL/1.06),2);
END;
/

SELECT APPLY_SUBTOTAL(I.AMOUNT)
FROM INVOICE I;

--- CREATE A FUNCTION WHICH TAKES IN A STRING AND A NUMBER, AND 
--CONCATINATES THAT STRING THAT NUMBER OF TIMES WITH A SPACE BETWEEN EACH AND
--RETURNS THE RESULT 
--    (EX. MY_FUNC('CAT',5) RETURNS 'CAT CAT CAT CAT CAT
CREATE OR REPLACE FUNCTION REPEATER(STR VARCHAR2, NMBR NUMBER)
RETURN VARCHAR2
IS
    COUNTER NUMBER;
    REPEATED VARCHAR(150);
    YUP VARCHAR(10);
    BEGIN
    REPEATED:=STR;
    COUNTER:=NMBR;
    YUP:=' ';
    LOOP
    REPEATED:= REPEATED || ' '|| STR;
--    REPEATED:= CONCAT(REPEATED+' ',STR);
    COUNTER:= COUNTER-1;
    
    DBMS_OUTPUT.PUT_LINE(REPEATED);
    EXIT WHEN COUNTER=1;
    END LOOP; 
    RETURN REPEATED;
    END;
/


SELECT REPEATER(C.CUSTOMER_FIRST_NAME,5)
FROM CUSTOMER C
WHERE C.CUSTOMER_ID=58738;

--- CREATE A STORED PROCEDURE WHICH CHECK ALL OF THE INVOICES IN YOUR DATABASE AND
--DELETES ALL ORPHAN RECORDS IN YOUR INVOICE TABLE


--- CREATE A STORED PROCEDURE WHICH ENTERS A NEW INVOICE INTO THE INVOICE TABLE - IF THE DATE IS IN THE FUTURE, IT SHOULD NOT BE ENTERED AND A MESSAGE SHOULD BE PRINTED TO THE CONSOLE
--    IN ADDITION IF THE VALUE OF THE INVOICE IS NEGATIVE, IT SHOULD NOT BE ENTERED INTO THE TABLE AND A MESSAGE SHOULD BE PRINTED TO THE CONSOLE

