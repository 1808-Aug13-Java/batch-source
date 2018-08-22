-- CREATE INVOICE TABLE
CREATE TABLE INVOICE (
    INV_ID NUMBER(5) PRIMARY KEY,
    INV_DATE DATE,
    CUST_ID NUMBER(5),
    AMOUNT NUMBER(10)
);

-- CREATE CUSTOMER TABLE
CREATE TABLE CUSTOMER (
    CUST_ID NUMBER(5) PRIMARY KEY,
    CUST_NAME VARCHAR2(20)
);

-- REFERENCE CUST_ID FROM INVOICE TO CUSTOMER TABLE
ALTER TABLE INVOICE
ADD CONSTRAINT FK_INV_CUST
FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER;

-- INSERTING 10 RECORDS INTO CUSTOMER
insert into CUSTOMER (CUST_ID, CUST_NAME) values (1, 'Reade Dowsett');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (2, 'Silas Gladyer');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (3, 'Garvin Riggeard');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (4, 'Kellsie Couper');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (5, 'Tuck Halburton');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (6, 'Belle Yakuntsov');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (7, 'Erinn Ibel');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (8, 'Selene Brandom');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (9, 'Bartolemo Van Merwe');
insert into CUSTOMER (CUST_ID, CUST_NAME) values (10, 'Merilyn Bastie');
COMMIT;

-- INSERTING 50 RECORDS INTO INVOICE
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (1, DATE '2018-06-08', 7, 924);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (2, DATE '2017-12-16', 1, 595);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (3, DATE '2018-07-17', 10, 418);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (4, DATE '2018-04-21', 5, 122);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (5, DATE '2018-04-09', 1, 344);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (6, DATE '2017-09-05', 7, 546);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (7, DATE '2018-05-09', 1, 122);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (8, DATE '2018-05-24', 9, 662);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (9, DATE '2018-02-19', 1, 428);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (10, DATE '2018-08-21', 10, 205);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (11, DATE '2017-12-26', 3, 251);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (12, DATE '2017-09-05', 4, 937);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (13, DATE '2018-03-21', 3, 627);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (14, DATE '2017-11-08', 3, 706);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (15, DATE '2018-07-12', 8, 681);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (16, DATE '2017-10-26', 7, 535);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (17, DATE '2018-05-30', 9, 139);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (18, DATE '2018-04-24', 10, 801);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (19, DATE '2017-09-25', 2, 697);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (20, DATE '2018-06-24', 9, 152);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (21, DATE '2017-09-16', 2, 438);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (22, DATE '2018-07-04', 5, 990);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (23, DATE '2018-08-21', 5, 612);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (24, DATE '2018-08-21', 7, 444);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (25, DATE '2018-03-20', 3, 29);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (26, DATE '2017-10-26', 8, 524);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (27, DATE '2017-09-16', 5, 924);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (28, DATE '2018-07-04', 7, 841);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (29, DATE '2017-08-23', 7, 791);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (30, DATE '2018-03-31', 8, 841);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (31, DATE '2017-10-18', 2, 232);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (32, DATE '2018-03-02', 7, 128);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (33, DATE '2018-02-23', 8, 219);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (34, DATE '2017-09-19', 2, 138);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (35, DATE '2018-06-15', 4, 414);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (36, DATE '2018-08-02', 3, 846);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (37, DATE '2017-09-19', 5, 581);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (38, DATE '2017-12-23', 7, 268);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (39, DATE '2018-08-21', 8, 155);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (40, DATE '2018-08-11', 2, 239);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (41, DATE '2017-10-08', 4, 10);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (42, DATE '2018-06-20', 5, 874);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (43, DATE '2017-11-22', 6, 563);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (44, DATE '2018-07-12', 3, 145);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (45, DATE '2017-09-02', 3, 93);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (46, DATE '2017-09-20', 2, 134);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (47, DATE '2018-05-07', 10, 124);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (48, DATE '2018-06-22', 7, 249);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (49, DATE '2018-02-18', 2, 110);
insert into INVOICE (INV_ID, INV_DATE, CUST_ID, AMOUNT) values (50, DATE '2018-08-16', 6, 971);
COMMIT;

-- CREATE A QUERY WHICH SHOWS PURCHSES THAT OCCURRED TODAY
SELECT *
FROM INVOICE
WHERE INV_DATE = DATE '2018-08-21';

-- Create a query which shows each customer and the number of purchases made by each
SELECT SUM(AMOUNT)
FROM INVOICE
GROUP BY CUST_ID
ORDER BY SUM(AMOUNT) DESC;

-- Create a query which shows each customer and the total cost of all their purchases
SELECT SUM(AMOUNT)
FROM CUSTOMER, INVOICE
WHERE CUSTOMER.CUST_ID = INVOICE.CUST_ID;

-- Create a query which shows each customer and the total cost of all their purchases
SELECT CONCAT('$', 8*SUM(AMOUNT))TOTAL_COST
FROM INVOICE
GROUP BY CUST_ID
ORDER BY SUM(AMOUNT) DESC;

-- Create a query which returns all purchases which took place in the last month, display them in descending order
SELECT TO_CHAR(INV_DATE)
FROM INVOICE
WHERE TO_CHAR(INV_DATE) LIKE '%AUG-18'
ORDER BY INV_DATE DESC;

--Create a query which show the top three most expensive purchases
SELECT *
FROM (
    SELECT CONCAT('$', 8*SUM(AMOUNT))TOTAL_COST
    FROM INVOICE
    GROUP BY CUST_ID
    ORDER BY SUM(AMOUNT) DESC)
WHERE ROWNUM <=3