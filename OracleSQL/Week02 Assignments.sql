-- David Calkins
-- SQL Scripts

DROP TABLE INVOICE;
DROP TABLE CUSTOMER;

CREATE TABLE INVOICE(
    IN_ID  INTEGER  CONSTRAINT PK_INVOICE  PRIMARY KEY,
    IN_DATE  DATE,
    CUSTOMER_ID  INTEGER,--  CONSTRAINT FK_INVOICE_CUSTOMER  REFERENCES CUSTOMER,
    AMOUNT  DECIMAL
);

CREATE TABLE CUSTOMER(
    CUST_ID  INTEGER  CONSTRAINT PK_CUSTOMER  PRIMARY KEY,
    CUST_NAME  varchar2(50),
    CUST_ADDRESS  varchar2(120)
);

SET TRANSACTION NAME 'Inserting Customers And Invoices';
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (1, 'Ply Gem Holdings, Inc.', '8 Hudson Point');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (2, 'ONE Gas, Inc.', '750 Kedzie Point');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (3, 'Landcadia Holdings, Inc.', '45194 Union Alley');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (4, 'Jamba, Inc.', '47525 Florence Road');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (5, 'The Cheesecake Factory Incorporated', '551 Novick Terrace');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (6, 'Brookfield Infrastructure Partners LP', '2 Granby Road');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (7, 'Gartner, Inc.', '12 Ronald Regan Drive');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (8, 'Sunesis Pharmaceuticals, Inc.', '5038 Maple Alley');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (9, 'iShares MSCI New Zealand Capped ETF', '78 Marcy Circle');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (10, 'CenterState Banks, Inc.', '5 Shelley Parkway');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_ADDRESS) values (11, 'Nintendo INC.', 'Japan');


insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (1, '21-Nov-2017', 8, 4598.2017893605);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (2, '28-Nov-2017', 4, 3486.2724251049);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (3, '15-Apr-2018', 10, 583.1883950441);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (4, '13-Sep-2017', 6, 300.709528559);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (5, '11-Sep-2017', 7, 1662.4688668825);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (6, '04-Aug-2017', 6, 2071.3647921779);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (7, '06-Apr-2018', 8, 4705.8772647758);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (8, '06-Jan-2018', 2, 1438.6590949449);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (9, '03-Aug-2018', 3, 2699.2774556873);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (10, '21-May-2018', 9, 1419.3634871652);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (11, '04-Dec-2017', 9, 3813.5218303915);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (12, '12-Jun-2018', 5, 4832.583058284);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (13, '15-Oct-2017', 2, 4479.6329988661);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (14, '19-Oct-2017', 5, 2991.651373476);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (15, '21-Jan-2018', 8, 1753.8705435438);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (16, '15-Mar-2018', 7, 46.638532779);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (17, '19-Jun-2018', 9, 1958.9570512091);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (18, '29-Jul-2018', 3, 2296.952198431);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (19, '21-Dec-2017', 5, 1109.9000455612);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (20, '03-Aug-2018', 2, 4416.2974678107);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (21, '06-Jun-2018', 8, 4978.7531149659);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (22, '09-Feb-2018', 1, 1516.6069332948);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (23, '07-Oct-2017', 9, 4707.1460957979);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (24, '09-Jul-2018', 2, 4073.70326677);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (25, '28-Oct-2017', 7, 2941.9799182961);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (26, '09-Oct-2017', 4, 1462.5901173629);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (27, '21-Aug-2017', 9, 2011.3444578109);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (28, '23-Sep-2017', 5, 3553.2069923021);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (29, '02-Mar-2018', 9, 742.1538950476);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (30, '03-May-2018', 10, 335.0311804067);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (31, '13-Aug-2017', 2, 3191.845467588);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (32, '09-Apr-2018', 3, 391.8239537295);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (33, '16-May-2018', 2, 719.3788837323);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (34, '08-Jan-2018', 5, 1622.1784385577);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (35, '06-Nov-2017', 6, 2075.3430577708);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (36, '22-Jan-2018', 8, 3896.1249049759);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (37, '17-Jul-2018', 10, 3672.3966991377);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (38, '23-Jul-2018', 1, 4644.6519919922);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (39, '01-Aug-2017', 5, 325.7626505007);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (40, '28-Nov-2017', 1, 3224.4818443976);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (41, '16-Sep-2017', 7, 2814.5334784719);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (42, '19-May-2018', 6, 308.3484540124);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (43, '31-Jan-2018', 10, 4995.3717584579);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (44, '12-Mar-2018', 7, 2997.6505902199);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (45, '17-Oct-2017', 5, 2858.4191747143);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (46, '09-Sep-2017', 4, 3520.5860448965);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (47, '14-Oct-2017', 6, 3071.5770178556);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (48, '08-Aug-2017', 6, 4918.9460407042);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (49, '21-Aug-2017', 8, 3680.1814967161);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (50, '19-Feb-2018', 1, 4355.7991050673);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (51, SYSDATE, 6, 4597.92134753459);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (52, CURRENT_DATE, 2, 4597.92134753459);
insert into INVOICE (IN_ID, IN_DATE, CUSTOMER_ID, AMOUNT) values (53, CURRENT_DATE, NULL, 2);
COMMIT;


-- E.
SELECT * FROM INVOICE
    WHERE EXTRACT(MONTH FROM CURRENT_DATE) = EXTRACT(MONTH FROM IN_DATE)
    AND EXTRACT(DAY FROM CURRENT_DATE) = EXTRACT(DAY FROM IN_DATE)
    AND EXTRACT(YEAR FROM CURRENT_DATE) = EXTRACT(YEAR FROM IN_DATE);

-- F.
SELECT * FROM CUSTOMER
    JOIN (
        SELECT CUSTOMER_ID, COUNT(*) ORDERS FROM INVOICE GROUP BY CUSTOMER_ID
    ) ON CUSTOMER_ID = CUST_ID;

-- G.
SELECT * FROM CUSTOMER
    JOIN (
        SELECT CUSTOMER_ID, SUM(AMOUNT) TOTAL_COST FROM INVOICE GROUP BY CUSTOMER_ID
    ) ON CUSTOMER_ID = CUST_ID;


-- H. -- TEST FURTHER, Test January
/*
SELECT * FROM INVOICE
    WHERE EXTRACT(MONTH FROM CURRENT_DATE) - 1  = EXTRACT(MONTH FROM IN_DATE); -- Doesn't work for months of less than 31 days
SELECT * FROM INVOICE
    WHERE EXTRACT(MONTH FROM (CURRENT_DATE - 31))  = EXTRACT(MONTH FROM IN_DATE); -- Doesn't work for January
SELECT * FROM INVOICE
    WHERE mod(EXTRACT(MONTH FROM DATE '2018-01-01') + 11, 13) = EXTRACT(MONTH FROM IN_DATE); -- Still can't figure out how to write this
*/
SELECT * FROM INVOICE -- Add 11 months to the current date, and then extract the month to get the previous month
    WHERE EXTRACT(MONTH FROM (ADD_MONTHS(CURRENT_DATE, 11))) = EXTRACT(MONTH FROM IN_DATE); -- Works!


-- I.
SELECT * FROM 
    (SELECT * FROM INVOICE
        ORDER BY AMOUNT DESC
    )
    WHERE ROWNUM <= 3;



/*
-- WEDNESDAY -----------------------------------------------------------------
*/
-- A. Create a query which returns all of the invoices which have a listed customer, but not invoices who have no customer 
-- listed and not customers who have no invoices listed
SELECT * FROM INVOICE
WHERE CUSTOMER_ID IS NOT NULL;

-- B. Create a query which returns all of the invoices and their customer, not invoices who have no customer listed but 
-- include customers which have no invoices listed
SELECT * FROM CUSTOMER C
LEFT JOIN INVOICE I
    ON C.CUST_ID = I.CUSTOMER_ID;

-- C. Create a query which shows each record in the invoice table, along with the name of the customer
SELECT I.IN_ID, I.IN_DATE, I.CUSTOMER_ID, I.AMOUNT, C.CUST_NAME FROM INVOICE I
    LEFT JOIN CUSTOMER C
        ON I.CUSTOMER_ID = C.CUST_ID;

-- D. Create a query which shows the name of each customer and the total amount they have spent
SELECT * FROM CUSTOMER C
    LEFT JOIN 
        (SELECT I.CUSTOMER_ID, SUM(AMOUNT) TOTAL_AMOUNT FROM INVOICE I
            GROUP BY I.CUSTOMER_ID   
        ) AMOUNTS
    ON AMOUNTS.CUSTOMER_ID = C.CUST_ID;

-- E. Create a query which returns the record of the customer who made the most recent purchase
SELECT * FROM INVOICE I1
JOIN 
    (SELECT MAX(IN_DATE) Most_Recent FROM INVOICE I2)
    ON Most_Recent = I1.IN_DATE
JOIN CUSTOMER C
    ON I1.CUSTOMER_ID = C.CUST_ID;

-- F. Create a query which shows the purchaser of each invoice and the subtotal of each invoice if 6% 
-- sales tax was applied to the subtotal to get the price of each invoice
SELECT IN_ID, CUST_NAME, AMOUNT Subtotal, (AMOUNT * 1.06) TOTAL FROM INVOICE I
JOIN CUSTOMER C ON I.Customer_ID = C.Cust_ID;



