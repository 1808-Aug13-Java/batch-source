--TUESDAY EXERCISE 08/21/2018
drop table invoice;
drop table customer;

--A) Create a table INVOICE which includes an id, date, customer id, and amount
CREATE TABLE INVOICE(
    INVOICE_ID NUMBER(5),
    INVOICE_DATE DATE,
    CUSTOMER_ID NUMBER(5),
    AMOUNT NUMBER (3) 
);

--INCLUDE PK
ALTER TABLE INVOICE
ADD CONSTRAINT PK_INVOICE PRIMARY KEY (INVOICE_ID);

--B) Create a table CUSTOMER which includes relevant customer information 
CREATE TABLE CUSTOMER(
    CUSTOMER_ID NUMBER(2),
    CUSTOMER_NAME VARCHAR2(25),
    STREET VARCHAR2(30),
    CITY VARCHAR2(25),
    STATE VARCHAR2(20),
    ZIPCODE NUMBER(5)
);

--INCLUDE PK
ALTER TABLE CUSTOMER
ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY (CUSTOMER_ID);

--C) Customer id in the invoice table should make a reference to the customer id in the customer table
ALTER TABLE INVOICE
ADD CONSTRAINT FK_INVOICE_ID
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER;
--SHOULD BE FOREIGN KEY IN INVOICE

--D) Insert at least 10 records into your customer table...
insert into CUSTOMER values (1, 'Asia Nuton', '69 Sommers Circle', 'Cincinnati', 'Ohio', '45999');
insert into CUSTOMER values (2, 'Dukey Foat', '85316 Blackbird Point', 'Tacoma', 'Washington', '98405');
insert into CUSTOMER values (3, 'Myrilla Dodsworth', '80143 Lakewood Gardens Pass', 'El Paso', 'Texas', '88569');
insert into CUSTOMER values (4, 'Devin Giacobilio', '96 Bultman Junction', 'Norwalk', 'Connecticut', '06854');
insert into CUSTOMER values (5, 'Carolin Catterill', '19 Rowland Point', 'Hartford', 'Connecticut', '06160');
insert into CUSTOMER values (6, 'Addison McDarmid', '21 Division Hill', 'New York City', 'New York', '10110');
insert into CUSTOMER values (7, 'Kin Ferreres', '0 Hayes Crossing', 'Bellevue', 'Washington', '98008');
insert into CUSTOMER values (8, 'Julissa Pankettman', '688 Pierstorff Avenue', 'El Paso', 'Texas', '88546');
insert into CUSTOMER values (9, 'Derrek Spikings', '5729 Merchant Hill', 'Duluth', 'Georgia', '30096');
insert into CUSTOMER values (10, 'Meta Malcher', '0733 Lien Lane', 'Hartford', 'Connecticut', '06105');

--...Insert at least 50 records into your invoice table 
insert into INVOICE values (1, DATE '2017-08-27', 2, 696);
insert into INVOICE values (2, DATE '2018-06-18', 8, 942);
insert into INVOICE values (3, DATE '2018-08-19', 9, 556);
insert into INVOICE values (4, DATE '2017-12-08', 10, 616);
insert into INVOICE values (5, DATE '2018-05-18', 10, 728);
insert into INVOICE values (6, DATE '2018-07-04', 10, 620);
insert into INVOICE values (7, DATE '2017-12-21', 5, 251);
insert into INVOICE values (8, DATE '2017-09-17', 7, 365);
insert into INVOICE values (9, DATE '2017-08-27', 10, 855);
insert into INVOICE values (10, DATE '2018-06-24', 3, 976);
insert into INVOICE values (11, DATE '2018-04-15', 7, 732);
insert into INVOICE values (12, DATE '2017-11-05', 4, 724);
insert into INVOICE values (13, DATE '2018-07-26', 5, 517);
insert into INVOICE values (14, DATE '2017-08-21', 5, 897);
insert into INVOICE values (15, DATE '2017-10-31', 10, 811);
insert into INVOICE values (16, DATE '2018-04-09', 6, 768);
insert into INVOICE values (17, DATE '2018-06-07', 4, 542);
insert into INVOICE values (18, DATE '2017-10-01', 4, 243);
insert into INVOICE values (19, DATE '2018-04-21', 8, 687);
insert into INVOICE values (20, DATE '2017-12-06', 5, 742);
insert into INVOICE values (21, DATE '2017-10-10', 9, 782);
insert into INVOICE values (22, DATE '2018-02-08', 9, 558);
insert into INVOICE values (23, DATE '2017-10-27', 6, 840);
insert into INVOICE values (24, DATE '2018-02-02', 8, 768);
insert into INVOICE values (25, DATE '2017-11-26', 8, 877);
insert into INVOICE values (26, DATE '2018-08-22', 6, 686);
insert into INVOICE values (27, DATE '2017-10-29', 4, 299);
insert into INVOICE values (28, DATE '2018-01-23', 3, 919);
insert into INVOICE values (29, DATE '2018-03-03', 9, 255);
insert into INVOICE values (30, DATE '2018-02-05', 9, 738);
insert into INVOICE values (31, DATE '2017-11-10', 7, 261);
insert into INVOICE values (32, DATE '2018-01-25', 6, 982);
insert into INVOICE values (33, DATE '2018-02-24', 1, 396);
insert into INVOICE values (34, DATE '2017-11-13', 10, 925);
insert into INVOICE values (35, DATE '2018-02-22', 3, 681);
insert into INVOICE values (36, DATE '2018-03-16', 2, 336);
insert into INVOICE values (37, DATE '2018-06-18', 9, 874);
insert into INVOICE values (38, DATE '2018-07-21', 9, 825);
insert into INVOICE values (39, DATE '2017-10-30', 10, 850);
insert into INVOICE values (40, DATE '2018-05-01', 9, 797);
insert into INVOICE values (41, DATE '2018-02-13', 4, 951);
insert into INVOICE values (42, DATE '2017-08-29', 1, 301);
insert into INVOICE values (43, DATE '2018-03-13', 3, 785);
insert into INVOICE values (44, DATE '2017-08-31', 8, 986);
insert into INVOICE values (45, DATE '2018-03-13', 3, 873);
insert into INVOICE values (46, DATE '2018-02-14', 1, 471);
insert into INVOICE values (47, DATE '2018-03-03', 2, 767);
insert into INVOICE values (48, DATE '2018-08-21', 10, 255);
insert into INVOICE values (49, DATE '2017-09-28', 6, 323);
INSERT INTO INVOICE values (50, DATE '2018-06-22', 1, 965);

--E) Create a query which shows purchases that occured today
SELECT * 
FROM INVOICE 
WHERE INVOICE_DATE=DATE'2018-08-21';

--F) Create a query which shows each customer and the number of purchases made by each
SELECT CUSTOMER_ID, COUNT(INVOICE_ID) AS "NUM INVOICES"
FROM INVOICE
GROUP BY CUSTOMER_ID
ORDER BY CUSTOMER_ID;

--G) Create a query which shows each customer and the total cost of all their purchases
SELECT CUSTOMER_ID, SUM(AMOUNT) AS "TOTAL PURCHASES"
FROM INVOICE
GROUP BY CUSTOMER_ID
ORDER BY CUSTOMER_ID;

--H) Create a query which returns all purchases which took place in the last month, display them in descending order
SELECT INVOICE_ID, AMOUNT
FROM INVOICE
WHERE INVOICE_DATE BETWEEN DATE'2018-08-01' AND DATE'2018-08-31'
ORDER BY AMOUNT DESC;

--I) Create a query which show the top three most expensive purchases*
SELECT MAX(AMOUNT)
FROM INVOICE
--WHERE ROWNUM <= 3
ORDER BY AMOUNT DESC;
--LIMIT not supported
--ROWNUM GIVES INACCURATE DATAA
--TOP not supported
--.