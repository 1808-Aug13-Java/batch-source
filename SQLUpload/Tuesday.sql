drop table INVOICE;
drop table CUSTOMER;


create table CUSTOMER (
    CUSTOMER_ID number(5) constraint PK_CUSTOMER primary key,
    CUSTOMER_FNAME varchar2(20),
    CUSTOMER_LNAME varchar2(20),
    BIRTHDAY date,
    PHONE_NUMBER number(10),
    ADDRESS varchar2(30),
    CITY varchar2(20), 
    STATE_ABR char(2)
);

create table INVOICE (
    INVOICE_ID number(5),
    DATE_OF_INVOICE date,
    CUSTOMER_ID number(5) constraint FK_CUSTOMER references CUSTOMER,
    AMOUNT number(9,2)
);

insert into CUSTOMER values (1, 'Grissel', 'Alekhov', date '1995-04-22', 7639518777, '18 Banding Junction', 'Boston', 'MA');
insert into CUSTOMER values (2, 'Renard', 'Charville', date '1996-11-28', 6569491095, '883 Warner Terrace', 'Metairie', 'LA');
insert into CUSTOMER values (3, 'Web', 'Tansill', date '1996-02-14', 9447423707, '5 Victoria Crossing', 'Jackson', 'MS');
insert into CUSTOMER values (4, 'Gweneth', 'Udey', date '1991-06-21', 6345279051, '6211 Russell Circle', 'Chicago', 'IL');
insert into CUSTOMER values (5, 'James', 'Figgs', date '1997-02-23', 9538166187, '03630 Calypso Terrace', 'El Paso', 'TX');
insert into CUSTOMER values (6, 'Delano', 'Howgego', date '1996-03-06', 4914629633, '66737 Merry Point', 'Washington', 'DC');
insert into CUSTOMER values (7, 'Quinta', 'Geeritz', date '1994-07-19', 6533552316, '0 Portage Terrace', 'Winston Salem', 'NC');
insert into CUSTOMER values (8, 'Stern', 'Corradetti', date '1996-03-06', 3758359156, '92 Veith Crossing', 'Dallas', 'TX');
insert into CUSTOMER values (9, 'Happy', 'Woffenden', date '1990-11-17', 6611426137, '5728 3rd Trail', 'Augusta', 'GA');
insert into CUSTOMER values (10, 'Ninnette', 'Martineau', date '1999-05-24', 2401124460, '4 Raven Plaza', 'Orlando', 'FL');
insert into CUSTOMER values (11, 'Gerardo', 'Cayton', date '1995-04-14', 6362407564, '3225 Sheridan Park', 'Roanoke', 'VA');
insert into CUSTOMER values (12, 'Delly', 'Lipscombe', date '1995-09-21', 1129800047, '03265 Rowland Drive', 'Bethesda', 'MD');
insert into CUSTOMER values (13, 'Maure', 'Ferries', date '1992-05-16', 8975845211, '5963 Lake View Crossing', 'Salt Lake City', 'UT');
insert into CUSTOMER values (14, 'Shaylyn', 'Moreinis', date '1994-02-02', 4068558089, '633 Donald Alley', 'Apache Junction', 'AZ');
insert into CUSTOMER values (15, 'Rubie', 'China', date '1997-02-28', 5543963069, '551 Fuller Center', 'Knoxville', 'TN');
insert into CUSTOMER values (16, 'Midge', 'Haddrill', date '1992-05-08', 3258398125, '3 Carey Park', 'Fort Lauderdale', 'FL');
insert into CUSTOMER values (17, 'Goldarina', 'Ellgood', date '1999-09-18', 5788438862, '45 Caliangt Hill', 'El Paso', 'TX');
insert into CUSTOMER values (18, 'Walther', 'Crofthwaite', date '1999-05-17', 6242600810, '61 American Ash Drive', 'Los Angeles', 'CA');
insert into CUSTOMER values (19, 'Eda', 'Gadd', date '1995-08-28', 8343324670, '1 Northwestern Crossing', 'Lincoln', 'NE');
insert into CUSTOMER values (20, 'Arlyn', 'Plunkett', date '2000-08-06', 6548500757, '3 Drewry Trail', 'Salt Lake City', 'UT');
insert into CUSTOMER values (21, 'Magda', 'Josephy', date '1993-05-15', 9601113114, '8 Cordelia Street', 'Jacksonville', 'FL');
insert into CUSTOMER values (22, 'Leone', 'Olwen', date '2000-07-22', 7374072428, '5 Brickson Park Plaza', 'Norcross', 'GA');
insert into CUSTOMER values (23, 'Mayor', 'Derr', date '1996-10-14', 8642417366, '22312 Milwaukee Plaza', 'Columbus', 'OH');
insert into CUSTOMER values (24, 'Dawn', 'Eatttok', date '1996-12-13', 1000269387, '38 Buell Crossing', 'Pocatello', 'ID');
insert into CUSTOMER values (25, 'Darbee', 'Cranch', date '1996-10-01', 7515028557, '647 Morrow Road', 'Sacramento', 'CA');
insert into CUSTOMER values (26, 'Baudoin', 'Wedgbrow', date '1998-10-17', 3716466569, '84 Dottie Trail', 'Silver Spring', 'MD');
insert into CUSTOMER values (27, 'Tallie', 'Minster', date '1992-10-23', 9357891502, '35012 New Castle Court', 'Hamilton', 'OH');
insert into CUSTOMER values (28, 'Tracy', 'Ahlf', date '1999-11-01', 2189588208, '7513 Petterle Plaza', 'Jacksonville', 'FL');
insert into CUSTOMER values (29, 'Dilly', 'Ohrtmann', date '2000-10-31', 9633610764, '0 Kingsford Trail', 'Philadelphia', 'PA');
insert into CUSTOMER values (30, 'Andy', 'Couvert', date '1994-10-11', 8750686921, '33 Crescent Oaks Street', 'Chicago', 'IL');
insert into CUSTOMER values (31, 'Boote', 'Whysall', date '1995-07-26', 4632145476, '170 Oak Valley Circle', 'Las Vegas', 'NV');
insert into CUSTOMER values (32, 'Hayward', 'Jessop', date '1992-04-25', 9878930227, '61 Moulton Crossing', 'Washington', 'DC');
insert into CUSTOMER values (33, 'Vallie', 'Beaver', date '1997-12-06', 5359932040, '0 Texas Drive', 'Reno', 'NV');
insert into CUSTOMER values (34, 'Ibrahim', 'Hillhouse', date '1992-08-24', 7491437182, '62 Lotheville Trail', 'Fort Lauderdale', 'FL');
insert into CUSTOMER values (35, 'Kurt', 'Sidwick', date '1994-10-14', 9118167014, '569 Nancy Point', 'Mesa', 'AZ');
insert into CUSTOMER values (36, 'Gabrielle', 'Gutridge', date '1994-02-18', 2488269653, '67274 Laurel Parkway', 'Fayetteville', 'NC');
insert into CUSTOMER values (37, 'Waylon', 'Cassel', date '1990-12-04', 3961132020, '41 Schmedeman Circle', 'Baltimore', 'MD');
insert into CUSTOMER values (38, 'Oralee', 'Tregenza', date '1995-09-27', 2348645972, '439 Melrose Junction', 'Roanoke', 'VA');
insert into CUSTOMER values (39, 'Koo', 'Lively', date '2000-05-31', 1342296010, '9742 School Court', 'Fredericksburg', 'VA');
insert into CUSTOMER values (40, 'Bird', 'Castletine', date '1995-05-25', 5009426373, '8368 Northwestern Avenue', 'Modesto', 'CA');
insert into CUSTOMER values (41, 'Charmane', 'Waterfall', date '1995-06-22', 6445210856, '61092 Florence Center', 'Fort Wayne', 'IN');
insert into CUSTOMER values (42, 'Ardelis', 'Shenfisch', date '1997-09-26', 7199739391, '9 Vahlen Plaza', 'Washington', 'DC');
insert into CUSTOMER values (43, 'Katee', 'Deering', date '1994-12-07', 6817372328, '4576 Maple Wood Point', 'Flint', 'MI');
insert into CUSTOMER values (44, 'Tami', 'Wilsone', date '1995-08-01', 5197579965, '9 Oak Valley Crossing', 'Shreveport', 'LA');
insert into CUSTOMER values (45, 'Angy', 'Kinglesyd', date '1998-06-06', 5645638990, '798 Daystar Drive', 'Billings', 'MT');
insert into CUSTOMER values (46, 'Elwin', 'Presman', date '1992-12-26', 6117712316, '03 Summit Crossing', 'Glendale', 'AZ');
insert into CUSTOMER values (47, 'Bernard', 'Wynne', date '1993-10-13', 6612838503, '147 Jackson Terrace', 'Johnstown', 'PA');
insert into CUSTOMER values (48, 'Casandra', 'Vallery', date '1995-08-09', 6894816420, '7257 Maple Avenue', 'Kansas City', 'MO');
insert into CUSTOMER values (49, 'Abeu', 'Ranfield', date '1999-09-03', 2570650707, '95 Maple Drive', 'Jamaica', 'NY');
insert into CUSTOMER values (50, 'Claudelle', 'Gohier', date '1991-06-30', 9467482984, '96 1st Junction', 'Loretto', 'MN');




insert into INVOICE values (1, date '2017-09-25', 33, 352.27);
insert into INVOICE values (2, date '2018-03-17', 13, 52.79);
insert into INVOICE values (3, date '2017-09-17', 10, 368.8);
insert into INVOICE values (4, date '2018-01-03', 4, 816.68);
insert into INVOICE values (5, date '2018-04-05', 45, 965.04);
insert into INVOICE values (6, date '2017-09-09', 8, 129.82);
insert into INVOICE values (7, date '2018-05-22', 2, 217.03);
insert into INVOICE values (8, date '2018-05-20', 44, 664.03);
insert into INVOICE values (9, date '2018-06-04', 20, 66.28);
insert into INVOICE values (10, date '2018-01-31', 50, 76.28);
insert into INVOICE values (11, date '2017-12-21', 8, 458.13);
insert into INVOICE values (12, date '2018-02-20', 4, 466.72);
insert into INVOICE values (13, date '2017-10-07', 32, 352.42);
insert into INVOICE values (14, date '2018-07-17', 28, 443.35);
insert into INVOICE values (15, date '2018-07-16', 18, 597.28);
insert into INVOICE values (16, date '2017-10-08', 30, 25.55);
insert into INVOICE values (17, date '2018-03-31', 48, 733.91);
insert into INVOICE values (18, date '2018-06-22', 45, 213.38);
insert into INVOICE values (19, date '2018-04-13', 6, 439.66);
insert into INVOICE values (20, date '2017-10-11', 4, 138.62);
insert into INVOICE values (21, date '2017-11-02', 23, 21.83);
insert into INVOICE values (22, date '2018-01-05', 39, 321.29);
insert into INVOICE values (23, date '2017-10-24', 23, 717.43);
insert into INVOICE values (24, date '2018-07-01', 33, 613.65);
insert into INVOICE values (25, date '2017-12-07', 26, 690.98);
insert into INVOICE values (26, date '2017-10-04', 12, 766.97);
insert into INVOICE values (27, date '2018-06-26', 36, 817.52);
insert into INVOICE values (28, date '2017-11-14', 2, 944.25);
insert into INVOICE values (29, date '2017-12-06', 17, 945.55);
insert into INVOICE values (30, date '2018-04-28', 42, 24.96);
insert into INVOICE values (31, date '2018-01-17', 37, 859.99);
insert into INVOICE values (32, date '2017-09-29', 30, 288.12);
insert into INVOICE values (33, date '2017-12-29', 45, 646.01);
insert into INVOICE values (34, date '2018-05-13', 19, 497.73);
insert into INVOICE values (35, date '2017-11-18', 15, 533.26);
insert into INVOICE values (36, date '2017-12-17', 38, 577.42);
insert into INVOICE values (37, date '2018-05-07', 17, 265.42);
insert into INVOICE values (38, date '2018-06-20', 5, 839.09);
insert into INVOICE values (39, date '2018-07-02', 39, 127.29);
insert into INVOICE values (40, date '2018-01-22', 33, 989.0);
insert into INVOICE values (41, date '2018-02-02', 29, 692.51);
insert into INVOICE values (42, date '2018-02-14', 4, 418.24);
insert into INVOICE values (43, date '2017-08-24', 10, 685.19);
insert into INVOICE values (44, date '2017-12-26', 26, 961.49);
insert into INVOICE values (45, date '2018-03-21', 36, 906.88);
insert into INVOICE values (46, date '2018-06-08', 37, 59.84);
insert into INVOICE values (47, date '2018-01-29', 2, 125.1);
insert into INVOICE values (48, date '2018-07-09', 47, 618.05);
insert into INVOICE values (49, date '2018-05-21', 14, 51.6);
insert into INVOICE values (50, date '2018-08-21', 39, 147.82);
insert into INVOICE values (51, date '2018-05-20', 48, 277.43);
insert into INVOICE values (52, date '2018-08-02', 6, 114.49);
insert into INVOICE values (53, date '2018-03-11', 1, 431.44);
insert into INVOICE values (54, date '2018-07-25', 16, 475.09);
insert into INVOICE values (55, date '2018-03-02', 34, 39.08);
insert into INVOICE values (56, date '2017-11-11', 43, 248.71);
insert into INVOICE values (57, date '2018-05-31', 30, 113.84);
insert into INVOICE values (58, date '2018-02-04', 6, 457.16);
insert into INVOICE values (59, date '2018-07-20', 8, 345.16);
insert into INVOICE values (60, date '2018-08-06', 6, 595.25);
insert into INVOICE values (61, date '2017-09-28', 23, 795.43);
insert into INVOICE values (62, date '2018-06-29', 39, 647.34);
insert into INVOICE values (63, date '2018-06-28', 39, 175.77);
insert into INVOICE values (64, date '2017-09-01', 35, 47.95);
insert into INVOICE values (65, date '2017-12-03', 21, 116.24);
insert into INVOICE values (66, date '2018-02-23', 39, 885.63);
insert into INVOICE values (67, date '2017-12-26', 22, 16.4);
insert into INVOICE values (68, date '2017-08-29', 22, 784.89);
insert into INVOICE values (69, date '2018-01-24', 15, 769.58);
insert into INVOICE values (70, date '2018-06-03', 9, 902.9);
insert into INVOICE values (71, date '2018-08-15', 6, 821.65);
insert into INVOICE values (72, date '2018-03-20', 38, 974.14);
insert into INVOICE values (73, date '2018-06-04', 17, 437.9);
insert into INVOICE values (74, date '2018-04-29', 45, 426.18);
insert into INVOICE values (75, date '2018-02-28', 27, 6.19);
insert into INVOICE values (76, date '2018-03-30', 31, 157.17);
insert into INVOICE values (77, date '2018-02-04', 40, 383.21);
insert into INVOICE values (78, date '2018-07-31', 1, 438.51);
insert into INVOICE values (79, date '2018-07-18', 6, 84.43);
insert into INVOICE values (80, date '2018-07-24', 37, 968.18);
insert into INVOICE values (81, date '2018-02-20', 8, 822.44);
insert into INVOICE values (82, date '2018-03-07', 4, 335.32);
insert into INVOICE values (83, date '2017-10-06', 11, 953.04);
insert into INVOICE values (84, date '2018-02-20', 7, 428.15);
insert into INVOICE values (85, date '2018-06-20', 16, 639.98);
insert into INVOICE values (86, date '2018-02-17', 47, 580.34);
insert into INVOICE values (87, date '2017-11-19', 13, 13.34);
insert into INVOICE values (88, date '2018-03-02', 24, 281.33);
insert into INVOICE values (89, date '2018-04-21', 5, 59.58);
insert into INVOICE values (90, date '2018-07-10', 36, 847.88);
insert into INVOICE values (91, date '2018-04-05', 11, 730.68);
insert into INVOICE values (92, date '2018-05-16', 1, 341.15);
insert into INVOICE values (93, date '2018-06-26', 4, 808.19);
insert into INVOICE values (94, date '2018-03-31', 7, 809.46);
insert into INVOICE values (95, date '2018-06-24', 14, 517.17);
insert into INVOICE values (96, date '2018-05-09', 49, 360.83);
insert into INVOICE values (97, date '2017-09-04', 12, 918.41);
insert into INVOICE values (98, date '2018-06-21', 28, 792.96);
insert into INVOICE values (99, date '2018-01-25', 25, 339.88);
insert into INVOICE values (100, date '2018-08-04', 16, 310.3);


select * from INVOICE
where DATE_OF_INVOICE = date '2018-08-21';

select CUSTOMER.CUSTOMER_FNAME as NAME, count(INVOICE.CUSTOMER_ID) as PURCHASES
from CUSTOMER, INVOICE
where CUSTOMER.CUSTOMER_ID = INVOICE.CUSTOMER_ID
group by CUSTOMER_FNAME;

select CUSTOMER.CUSTOMER_FNAME as NAME, sum(INVOICE.AMOUNT) as TOTAL
from CUSTOMER, INVOICE
where CUSTOMER.CUSTOMER_ID = INVOICE.CUSTOMER_ID
group by CUSTOMER_FNAME;

select INVOICE_ID as ID, DATE_OF_INVOICE as DAY
from (select * 
    from INVOICE
    order by DATE_OF_INVOICE desc)
where DATE_OF_INVOICE >= date '2018-07-21';

select INVOICE_ID, AMOUNT 
from (
    select * from INVOICE
    order by AMOUNT desc)
where ROWNUM < 4;

commit;