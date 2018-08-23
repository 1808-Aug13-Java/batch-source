/*

Create a table INVOICE which includes an id, date, customer id, and amount
Create a table CUSTOMER which includes relevant customer information 
Customer id in the invoice table should make a reference to the customer id in the customer table
Insert at least 50 records into your invoice table and at least 10 records into your customer table
Create a query which shows purchases that occured today
Create a query which shows each customer and the number of purchases made by each
Create a query which shows each customer and the total cost of all their purchases
Create a query which returns all purchases which took place in the last month, display them in descending order
Create a query which show the top three most expensive purchases

*/

CREATE TABLE INVOICE (
    INVOICE_ID NUMBER(5),
    INVOICE_DATE DATE,
    CUSTOMER_ID NUMBER(3),
    AMOUNT NUMBER(5)         
);

ALTER TABLE INVOICE
ADD CONSTRAINT PK_INVOICE PRIMARY KEY (INVOICE_ID);

--Create a table CUSTOMER which includes relevant customer information 
CREATE TABLE CUSTOMER (
    CUSTOMER_ID NUMBER(3) CONSTRAINT PK_CUS PRIMARY KEY,
    PRODUCT VARCHAR2(20), 
    DATE_BOUGHT DATE, 
    PAYMENT NUMBER (3)
);
--DELETE FROM CUSTOMER;
--DROP TABLE CUSTOMER;
--DELETE FROM INVOICE;
--DROP TABLE INVOICE;
--Customer id in the invoice table should make a reference to the customer id i
ALTER TABLE INVOICE
ADD CONSTRAINT FK_INV_CUS
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER;


--Insert at least 50 records into your invoice table and at least 10 records into your customer table
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (1, DATE '2017-12-05', 'Chinese Foods', 7.83);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (2, DATE '2017-12-05', 'Muffin', 8.87);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (3, DATE '2017-09-11', 'Crawfish', 4.56);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (4, DATE '2017-09-12', 'Chicken', 8.93);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (5, DATE '2018-05-05', 'Cheese', 8.66);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (6, DATE '2018-07-31', 'Vinegar', 2.35);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (7, DATE '2018-04-26', 'Cocktail', 6.88);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (8, DATE '2017-09-12', 'Table Cloth', 0.04);
insert into CUSTOMER (CUSTOMER_ID, DATE_BOUGHT, PRODUCT, PAYMENT) values (9, DATE '2018-08-14', 'Bread', 2.96);

insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (1, DATE '2018-07-14', 1, 5421);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (2, DATE '2018-07-14', 2, 9641);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (3, DATE '2018-07-14', 3, 2647);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (4, DATE '2018-07-14', 4, 7942);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (5, DATE '2018-06-14', 5, 9046);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (6, DATE '2018-05-14', 6, 3412);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (7, DATE '2018-06-14', 7, 9553);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (8, DATE '2018-05-14', 8, 5224);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (9, DATE '2018-06-14', 9, 4154);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (10, DATE '2018-03-14', 9, 8214);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (11, DATE '2018-03-14', 1, 9813);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (12, DATE '2018-02-14', 2, 4559);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (13, DATE '2018-02-14', 3, 5239);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (14, DATE '2018-01-14', 4, 7244);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (15, DATE '2018-01-14', 5, 7604);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (16, DATE '2018-08-14', 6, 3672);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (17, DATE '2018-04-14', 7, 1154);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (18, DATE '2018-04-14', 8, 2563);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (19, DATE '2018-04-14', 9, 5020);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (20, DATE '2018-08-14', 9, 8952);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (21, DATE '2018-08-14', 1, 5989);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (22, DATE '2018-08-14', 2, 2369);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (23, DATE '2018-08-14', 3, 6596);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (24, DATE '2018-08-14', 4, 7650);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (25, DATE '2018-08-14', 5, 9753);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (26, DATE '2018-08-14', 6, 1827);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (27, DATE '2018-08-14', 7, 6439);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (28, DATE '2018-08-14', 8, 1209);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (29, DATE '2018-08-14', 9, 1119);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (30, DATE '2018-08-14', 9, 5758);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (31, DATE '2018-08-14', 1, 6001);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (32, DATE '2018-08-14', 2, 9386);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (33, DATE '2018-08-14', 3, 9730);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (34, DATE '2018-08-14', 4, 1016);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (35, DATE '2018-08-14', 5, 3098);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (36, DATE '2018-08-14', 6, 3391);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (37, DATE '2018-08-14', 7, 2484);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (38, DATE '2018-08-14', 8, 1350);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (39, DATE '2018-08-14', 9, 2825);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (40, DATE '2018-08-14', 9, 4509);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (41, DATE '2018-08-14', 1, 5810);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (42, DATE '2018-08-14', 2, 569);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (43, DATE '2018-08-14', 3, 5200);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (44, DATE '2018-08-14', 4, 3797);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (45, DATE '2018-08-14', 5, 8252);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (46, DATE '2018-08-14', 6, 9537);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (47, DATE '2018-08-14', 7, 6654);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (48, DATE '2018-08-14', 8, 7786);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (49, DATE '2018-08-14', 9, 3873);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (50, DATE '2018-08-14', 1, 8820);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (51, DATE '2018-08-21', 7, 6654);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (52, DATE '2018-08-21', 8, 7786);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (53, DATE '2018-08-12', 9, 3873);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (54, DATE '2018-08-10', 1, 8820);


--Create a query which shows purchases that occured today
SELECT * 
FROM INVOICE
--WHERE INVOICE_DATE = (SELECT CURDATE() FROM INVOICE);
WHERE INVOICE_DATE = DATE '2018-08-21';

--Create a query which shows each customer and the number of purchases made by each
SELECT CUSTOMER_ID, COUNT(CUSTOMER_ID)  
FROM INVOICE
GROUP BY CUSTOMER_ID;

--Create a query which shows each customer and the total cost of all their purchases
SELECT CUSTOMER_ID, SUM(AMOUNT)
FROM INVOICE
GROUP BY CUSTOMER_ID;           --GROUP BY IS SO IMPORTANT LOL
                                --IT WONT JUST RANDOMLY DISPLAY
--Create a query which returns all purchases which took place in the last month, display them in descending order
SELECT *
FROM INVOICE
WHERE INVOICE_DATE BETWEEN DATE '2018-07-01' AND DATE '2018-07-30'
ORDER BY AMOUNT DESC;
--GROUP BY AMOUNT (DESCENDING)

SELECT *
FROM INVOICE

GROUP BY AMOUNT DESC;

--HAVING DATE_FORMAT(INVOICE_DATE, '%MM') = DATE_FORMAT(DATE '2018-07-01', '%MM');


--Create a query which show the top three most expensive purchases
--SELECT MAX(AMOUNT)
--FROM INVOICE
--WHERE ROWNUM <= 3
--ORDER BY MAX(AMOUNT) DESC;
SELECT *
FROM (
    SELECT AMOUNT
    FROM INVOICE
    ORDER BY AMOUNT DESC
)
WHERE ROWNUM <= 3;


------------------------------------------------------------------------
---Wednesday
--------------------------------------------------------------------------
/*
Create a query which returns all of the invoices which have a listed customer, but not invoices who have no customer listed and not customers who have no invoices listed
Create a query which returns all of the invoices and their customer, not invoices who have no customer listed but include customers which have no invoices listed
Create a query which shows each record in the invoice table, along with the name of the customer
Create a query which shows the name of each customer and the total amount they have spent
Create a query which returns the record of the customer who made the most recent purchase
Create a query which shows the purchaser of each invoice and the subtotal of each invoice if 6% sales tax was applied to the subtotal to get the price of each invoice
*/

--Create a query which returns all of the invoices which have a listed customer, 
--but not invoices who have no customer listed and not customers who have no invoices listed
ALTER TABLE CUSTOMER
ADD INVOICE_ID NUMBER(5);   --i DIDNT HAVE THIS ID IN MY CUSTOMERS TABLE BEFORE

ALTER TABLE CUSTOMER
ADD CONSTRAINT FK_CUS_INV
FOREIGN KEY (INVOICE_ID) REFERENCES INVOICE;

UPDATE CUSTOMER C
SET C.INVOICE_ID = 3
WHERE C.PRODUCT = 'Chicken';

UPDATE CUSTOMER C
SET C.INVOICE_ID = 10
WHERE C.PRODUCT = 'Bread';

UPDATE CUSTOMER C
SET C. INVOICE_ID = 50
WHERE C.CUSTOMER_ID = 1;

--PUTTING IN SOME NULL VALUES FOR CUSTOMER ID IN INVOICE TABLE
INSERT INTO INVOICE (INVOICE_ID, INVOICE_DATE, AMOUNT) 
    VALUES (55, DATE '2018-05-23', 123);
INSERT INTO INVOICE (INVOICE_ID, INVOICE_DATE, AMOUNT) 
    VALUES (56, DATE '2017-12-23', 321);

--Create a query which returns all of the invoices which have a listed customer, 
--but not invoices who have no customer listed and not customers who have no invoices listed

SELECT *
FROM INVOICE I
RIGHT JOIN CUSTOMER C
ON I.CUSTOMER_ID = C.CUSTOMER_ID
WHERE C.INVOICE_ID IS NOT NULL;

--Create a query which returns all of the invoices and their customer, not invoices 
--who have no customer listed but include customers which have no invoices listed
SELECT I.INVOICE_ID, I.CUSTOMER_ID, 
        I.INVOICE_DATE, I.AMOUNT, C.CUSTOMER_ID, C.INVOICE_ID AS CUSTOMER_INVOICE_ID
FROM INVOICE I
RIGHT JOIN CUSTOMER C
ON I.CUSTOMER_ID = C.CUSTOMER_ID;

--Create a query which shows each record in the invoice table, 
--along with the name of the customer
ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'MARY'
WHERE CUSTOMER_ID = 1;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'ZOEY'
WHERE CUSTOMER_ID = 2;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'JASMIN'
WHERE CUSTOMER_ID = 3;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'DAN'
WHERE CUSTOMER_ID = 4;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'IVAN'
WHERE CUSTOMER_ID = 5;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'MAY'
WHERE CUSTOMER_ID = 6;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'BEE'
WHERE CUSTOMER_ID = 7;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'LOO'
WHERE CUSTOMER_ID = 8;

ALTER TABLE CUSTOMER
ADD NAME VARCHAR2 (60);
UPDATE CUSTOMER
SET NAME = 'JOE'
WHERE CUSTOMER_ID = 9;

--Create a query which shows each record in the invoice table, 
--along with the name of the customer
SELECT I.INVOICE_ID, C.NAME, I.INVOICE_DATE, I.AMOUNT
FROM INVOICE I
LEFT JOIN CUSTOMER C
ON I.CUSTOMER_ID = C.CUSTOMER_ID;

--Create a query which shows the name of each customer and the total amount they have spent
SELECT C.NAME, SUM(I.AMOUNT)
FROM INVOICE I
LEFT JOIN CUSTOMER C
ON I.CUSTOMER_ID = C.CUSTOMER_ID
GROUP BY C.NAME;
--Create a query which returns the record of the customer who made the most recent purchase
SELECT CUSTOMER_ID, NAME
FROM(
    SELECT C.CUSTOMER_ID, C.NAME
    FROM CUSTOMER C
    RIGHT JOIN INVOICE I
    ON C.CUSTOMER_ID = I.CUSTOMER_ID
    ORDER BY I.INVOICE_DATE DESC
)
WHERE ROWNUM <= 1;

--Create a query which shows the purchaser of 
--each invoice and the subtotal of each invoice WITH 6% sales tax applied
--to the subtotal to get the price of each invoice
--SUBTOTAL * 1.06 = PRICE
--SUBTOTAL = PRICE * 100/106
SELECT C.NAME, I.INVOICE_ID, I.AMOUNT * 100 / 106
FROM INVOICE I
LEFT JOIN CUSTOMER C
ON I.CUSTOMER_ID = C.CUSTOMER_ID
ORDER BY C.NAME
;













