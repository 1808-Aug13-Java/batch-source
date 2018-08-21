CREATE TABLE INVOICE(
INV_ID NUMBER (5) CONSTRAINT PK_INV PRIMARY KEY, INV_DATE DATE, INV_AMT NUMBER(7, 2), CUST_ID NUMBER(5) CONSTRAINT FK_INV_CUST REFERENCES CUSTOMER
);

CREATE TABLE CUSTOMER(
CUST_ID NUMBER(5) CONSTRAINT PK_CUST PRIMARY KEY, CUST_NAME VARCHAR2 (50), CUST_EMAIL VARCHAR2(50)
);

insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (1, 'Kippie Skeffington', 'kskeffington0@studiopress.com');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (2, 'Roland Burfitt', 'rburfitt1@histats.com');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (3, 'Chicky Duley', 'cduley2@cargocollective.com');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (4, 'Horacio Arkell', 'harkell3@howstuffworks.com');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (5, 'Fallon Grigoryov', 'fgrigoryov4@xing.com');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (6, 'Richie Stops', 'rstops5@moonfruit.com');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (7, 'Andriette Gotter', 'agotter6@amazon.co.jp');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (8, 'Mendel Defew', 'mdefew7@ustream.tv');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (9, 'Esta Gaymer', 'egaymer8@aboutads.info');
insert into CUSTOMER (CUST_ID, CUST_NAME, CUST_EMAIL) values (10, 'Karlotte Kimblen', 'kkimblen9@tuttocitta.it');


insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (1, DATE '2018-02-22', 77, 1);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (2, DATE '2014-01-15', 62, 2);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (3, DATE '2016-09-08', 56, 3);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (4, DATE '2017-09-29', 80, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (5, DATE '2015-10-10', 26, 5);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (6, DATE '2016-03-16', 8, 6);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (7, DATE '2014-08-22', 75, 7);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (8, DATE '2013-04-08', 60, 8);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (9, DATE '2015-09-28', 83, 9);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (10, DATE '2013-06-12', 96, 10);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (11, DATE '2016-08-29', 75, 10);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (12, DATE '2018-07-19', 34, 1);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (13, DATE '2015-12-11', 39, 3);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (14,DATE  '2016-09-25', 65, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (15, DATE '2013-12-20', 57, 5);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (16, DATE '2016-05-31', 40, 6);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (17, DATE '2017-01-05', 80, 7);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (18, DATE '2015-04-30', 63, 8);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (19, DATE '2017-05-20', 43, 9);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (20, DATE '2018-06-11', 13, 1);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (21, DATE '2015-08-01', 15, 2);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (22, DATE '2017-03-19', 48, 2);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (23, DATE '2015-08-24', 58, 3);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (24, DATE '2018-04-10', 65, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (25, DATE '2018-03-28', 89, 5);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (26, DATE '2014-09-21', 41, 6);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (27, DATE '2018-01-11', 69, 7);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (28, DATE '2015-03-08', 9, 2);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (29, DATE '2014-10-23', 82, 9);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (30, DATE '2014-12-06', 38, 10);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (31, DATE '2017-04-12', 90, 1);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (32, DATE '2013-03-02', 87, 2);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (33, DATE '2015-11-27', 78, 3);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (34, DATE '2016-10-20', 33, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (35, DATE '2017-06-20', 72, 5);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (36, DATE '2014-12-30', 39, 6);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (37, DATE '2017-05-07', 84, 7);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (38, DATE '2015-12-19', 20, 8);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (39, DATE '2013-09-19', 56, 9);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (40, DATE '2016-02-22', 1, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (41, DATE '2015-04-12', 64, 1);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (42, DATE '2014-10-15', 39, 2);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (43, DATE '2014-06-24', 67, 3);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (44, DATE '2017-04-07', 54, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (45, DATE '2013-09-18', 23, 5);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (46, DATE '2017-08-16', 73, 6);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (47, DATE '2015-02-28', 7, 4);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (48, DATE '2014-09-12', 31, 8);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (49, DATE '2017-01-06', 50, 9);
insert into INVOICE (INV_ID, INV_DATE, INV_AMT, CUST_ID) values (50, DATE '2015-02-04', 47, 5);

SELECT * FROM INVOICE
WHERE INV_DATE = DATE '2018-08-21';

SELECT CUST_ID, INV_ID
FROM INVOICE
GROUP BY COUNT(INV_ID=CUST_ID);


