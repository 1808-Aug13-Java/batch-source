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

CREATE TABLE INVOICE(
    INVOICE_ID NUMBER(5) CONSTRAINT PK_INVOICE PRIMARY KEY,
    INVOICE_DATE DATE,
    CUSTOMER_ID NUMBER(5),
    AMOUNT NUMBER (10)
);

ALTER TABLE INVOICE
ADD CONSTRAINT FK_INV_CUST
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER;

CREATE TABLE CUSTOMER(
    CUSTOMER_ID NUMBER (5),
    NAME VARCHAR2(50),
    ADDRESS VARCHAR2(50),
    CITY VARCHAR2(50),
    STATE VARCHAR2(2),
    ZIP NUMBER(5)
    );

ALTER TABLE CUSTOMER
ADD CONSTRAINT PK_CUSTOMER
PRIMARY KEY (CUSTOMER_ID);

ALTER TABLE CUSTOMER
ADD PRODUCT_BOUGHT VARCHAR(50)
ADD PRICE NUMBER (6,2);

ALTER TABLE INVOICE
ADD AMOUNT NUMBER(6,2);

ALTER TABLE CUSTOMER
DROP COLUMN ;

ALTER TABLE CUSTOMER
DROP COLUMN STATE;

INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (1, 'Frederique Cicculi', 1, 63.18);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (2, 'Shauna Janecki', 2, 38.62);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (3, 'Madelina Kundt', 3, 77.87);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (4, 'Gabbie Cleynman', 4, 15.4);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (5, 'Siouxie Heathorn', 5, 86.48);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (6, 'Allina McSwan', 6, 85.87);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (7, 'Odessa Squire', 7, 17.53);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (8, 'Dotti Toomer', 8, 25.06);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (9, 'Brigitta Pellett', 9, 54.46);
INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, PRODUCT_BOUGHT, PRICE) values (10, 'Bryon Redit', 10, 35.27);

INSERT INTO INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) VALUES (1, DATE '2018-07-20', 9, 61.43);
INSERT INTO INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (2, DATE '2018-07-20', 2, 91.56);
INSERT INTO INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (3, DATE '2018-08-04', 5, 65.23);
INSERT INTO INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (4, DATE '2018-07-27', 3, 4.09);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (5, DATE '2018-08-06', 10, 34.5);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (6, DATE '2018-07-29', 5, 35.53);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (7, DATE '2018-08-06', 7, 23.07);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (8, DATE '2018-07-13', 2, 6.12);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (9, DATE '2018-07-24', 2, 67.25);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (10, DATE '2018-08-01', 7, 47.46);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (11, DATE '2018-08-12', 10, 88.87);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (12, DATE '2018-08-04', 8, 45.09);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (13, DATE '2018-08-18', 5, 69.49);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (14, DATE '2018-08-15', 9, 54.92);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (15, DATE '2018-07-21', 8, 78.48);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (16, DATE '2018-07-11', 7, 48.62);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (17, DATE '2018-07-20', 4, 7.4);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (18, DATE '2018-08-01', 6, 92.47);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (19, DATE '2018-08-02', 7, 20.59);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (20, DATE '2018-08-16', 9, 75.31);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (21, DATE '2018-08-19', 1, 2.66);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (22, DATE '2018-08-13', 3, 98.15);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (23, DATE '2018-07-10', 2, 98.58);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (24, DATE '2018-07-25', 7, 38.49);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (25, DATE '2018-07-20', 7, 16.03);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (26, DATE '2018-07-10', 10, 22.96);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (27, DATE '2018-07-26', 7, 66.41);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (28, DATE '2018-08-18', 6, 42.27);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (29, DATE '2018-08-13', 4, 79.74);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (30, DATE '2018-08-08', 3, 52.15);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (31, DATE '2018-07-30', 3, 70.64);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (32, DATE '2018-08-19', 5, 63.77);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (33, DATE '2018-08-10', 10, 8.59);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (34, DATE '2018-07-17', 4, 96.33);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (35, DATE '2018-07-26', 9, 24.01);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (36, DATE '2018-08-13', 5, 65.93);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (37, DATE '2018-07-15', 6, 85.9);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (38, DATE '2018-07-21', 7, 58.96);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (39, DATE '2018-08-20', 3, 29.12);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (40, DATE '2018-08-09', 6, 55.1);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (41, DATE '2018-08-16', 3, 40.88);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (42, DATE '2018-07-28', 2, 51.57);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (43, DATE '2018-08-20', 9, 90.02);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (44, DATE '2018-07-27', 1, 19.22);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (45, DATE '2018-08-02', 4, 80.75);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (46, DATE '2018-08-17', 9, 18.28);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (47, DATE '2018-07-31', 2, 78.28);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (48, DATE '2018-08-06', 9, 85.15);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (49, DATE '2018-08-08', 8, 23.25);
insert into INVOICE (INVOICE_ID, INVOICE_DATE, CUSTOMER_ID, AMOUNT) values (50, DATE '2018-07-11', 8, 70.91);

UPDATE INVOICE
SET CUSTOMER_ID = NULL
WHERE INVOICE_ID = 44;

--- FINISHED CREATING TABLE AND INPUTTING RECORDS

/*
A. Create a query which shows purchases that occured today
B. Create a query which shows each customer and the number of purchases made by each
C. Create a query which shows each customer and the total cost of all their purchases
D. Create a query which returns all purchases which took place in the last month, display them in descending order
E. Create a query which show the top three most expensive purchases
*/

--A.
SELECT *
FROM INVOICE
WHERE INVOICE_DATE = DATE '2018-08-21';

--B.
SELECT COUNT (CUSTOMER_ID) CUSTOMER_ID 
FROM INVOICE
JOIN CUSTOMER ON INVOICE.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID
GROUP BY CUSTOMER_ID;

--C.
SELECT C.CUSTOMER_ID, (SUM(I.AMOUNT)), C.NAME
FROM INVOICE I
INNER JOIN CUSTOMER C ON I.CUSTOMER_ID = C.CUSTOMER_ID
GROUP BY C.CUSTOMER_ID;

--D.
SELECT *
FROM INVOICE
WHERE INVOICE_DATE
BETWEEN DATE '2018-07-21' AND DATE '2018-08-21'
ORDER BY INVOICE_DATE DESC;

--E.
SELECT *
FROM INVOICE I
    (SELECT AMOUNT
    FROM INVOICE I
    ORDER BY AMOUNT ASC)
WHERE ROWNUM <=3;




------------------------
--WEDNESDAY ASSIGNMENT
------------------------
/*
A. Create a query which returns all of the invoices which have a listed customer, but not invoices who have no customer listed and not customers who have no invoices listed
B. Create a query which returns all of the invoices and their customer, not invoices who have no customer listed but include customers which have no invoices listed
C. Create a query which shows each record in the invoice table, along with the name of the customer
D. Create a query which shows the name of each customer and the total amount they have spent
E. Create a query which returns the record of the customer who made the most recent purchase
F. Create a query which shows the purchaser of each invoice and the subtotal of each invoice if 6% sales tax was applied to the subtotal to get the price of each invoice
*/

--A.
SELECT *
FROM INVOICE I
RIGHT JOIN CUSTOMER C
ON C.CUSTOMER_ID=I.CUSTOMER_ID
WHERE C.INVOICE_ID IS NOT NULL;

--B.
SELECT *
FROM INVOICE I
RIGHT JOIN CUSTOMER C
ON C.CUSTOMER_ID=I.CUSTOMER_ID;

--C.
SELECT I.INVOICE_ID, C.NAME
FROM INVOICE I
FULL JOIN CUSTOMER C
ON I.CUSTOMER_ID=C.CUSTOMER_ID;

--D.
SELECT C.CUSTOMER_ID, (SUM(I.AMOUNT)), C.NAME
FROM INVOICE I
INNER JOIN CUSTOMER C
ON I.CUSTOMER_ID = C.CUSTOMER_ID
GROUP BY C.CUSTOMER_ID;

--E.
SELECT *
FROM INVOICE I
JOIN CUSTOMER C
ON I.INVOICE_ID=C.INVOICE_ID
WHERE DATE = MAX(I.INVOICE_DATE);

--F.
SELECT I.INVOICE_ID, C.NAME, I.AMOUNT, (I.AMOUNT*.94) SUBTOTAL
FROM INVOICE I
FULL JOIN CUSTOMER C
ON I.CUSTOMER_ID=C.CUSTOMER_ID;

