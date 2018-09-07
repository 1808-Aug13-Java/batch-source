CREATE TABLE P1_STAFF (
    STAFF_ID NUMBER(3) CONSTRAINT PK_STAFF_ID PRIMARY KEY,
    STAFF_NAME VARCHAR2(100),
    USER_N VARCHAR2(30),
    PASS_W VARCHAR2(50),
    MAN_ID NUMBER (3),
    STAFF_ROLE VARCHAR2(50), --CHECK 'EMPLOYEE','BOTH', 'MANAGER' BUT IDK HOW TO DO :O
    PHONE VARCHAR2(20)
);

CREATE TABLE P1_REIMBURSEMENTS (
    REQUEST_ID NUMBER(10) CONSTRAINT PK_REQUEST_ID PRIMARY KEY,  --INTEGER ONLY
    STAFF_ID NUMBER(3), 
    STATUS VARCHAR2(20),  --CHECK 'PENDING', 'APPROVED', OR DENIED'
    MAN_ID NUMBER(3),
    AMOUNT NUMBER(10, 2),
    REQ_DATE DATE,
    MESSAGE VARCHAR2(500)
);

ALTER TABLE P1_REIMBURSEMENTS
ADD CONSTRAINT FK_REIM_STAF_STAFF_ID
FOREIGN KEY (STAFF_ID) REFERENCES P1_STAFF;

ALTER TABLE P1_REIMBURSEMENTS
ADD CONSTRAINT FK_REIM_STAF_MAN_ID
FOREIGN KEY (MAN_ID) REFERENCES P1_STAFF;


CREATE SEQUENCE SQ_REIMBURSEMENTS_PK  --SEQ/TRIGGER FOR GENERATING REIMBURSEMENTS PK
START WITH 1000
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_REIMBURSEMENTS
BEFORE INSERT ON P1_REIMBURSEMENTS
FOR EACH ROW
BEGIN
    SELECT SQ_REIMBURSEMENTS_PK.NEXTVAL INTO :NEW.REQUEST_ID FROM DUAL;
END;
/

CREATE SEQUENCE SQ_STAFF_PK --SEQ/TRIGGER FOR GENERATING STAFF PK
START WITH 1     --DIDNT KNOW THAT CANT START WITH 0. >= 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_STAFF
BEFORE INSERT ON P1_STAFF
FOR EACH ROW
BEGIN
    SELECT SQ_STAFF_PK.NEXTVAL INTO :NEW.STAFF_ID FROM DUAL;
END;
/

--INSERT INTO STAFF TABLE--
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, STAFF_ROLE, PHONE) values ('Cindy Peng', 'cindy', 'password', 'MANAGER', '775-882-3673');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, STAFF_ROLE, PHONE) values ('Morrie Rodane', 'morrie', 'password', 'MANAGER', '394-650-4842');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Amy Wang', 'amy', 'password', 1,  'BOTH', '680-123-8150');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Imogen Zotto', 'imogen', 'password', 1, 'BOTH', '206-703-7301');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Stanleigh Gaitone', 'stanleigh', 'password', 2, 'BOTH', '164-299-2164');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Katrinka Guitt', 'katrinka', 'password', 2, 'BOTH', '493-651-3906');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Myriam Buxcey', 'myriam', 'password', 3, 'EMPLOYEE', '411-423-0800');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Bax Leivers', 'bax', 'password', 3, 'EMPLOYEE', '405-492-4280');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Collie Domm', 'collie', 'password', 3, 'EMPLOYEE', '505-829-0930');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Michelle Cheng', 'michelle', 'password', 4, 'EMPLOYEE', '814-796-1248');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Charmane Leipnik', 'leipnik', 'password', 4, 'EMPLOYEE', '203-119-9418');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Wolfgang Gouldsmith', 'gouldsmith', 'password', 4, 'EMPLOYEE', '493-786-8608');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Livy Cropper', 'cropper', 'password', 5, 'EMPLOYEE', '906-347-4527');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Todd Rothwell', 'rothwell', 'password', 5, 'EMPLOYEE', '705-213-6626');
insert into P1_STAFF (STAFF_NAME, USER_N, PASS_W, MAN_ID, STAFF_ROLE, PHONE) values ('Barb Gniewosz', 'gniewosze', 'password', 6, 'EMPLOYEE', '722-196-2407');

--INSERT INTO REIMBURSEMENT TABLE--
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (6, 'APPROVED', 3, 22987.15, DATE '2018-07-29', 'pulvinar nulla pede ullamcorper augue a suscipit nulla elit ac nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit ligula in lacus curabitur at ipsum ac tellus');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (13, 'APPROVED', 6, 7346.59, DATE '2017-12-24', 'vulputate elementum nullam varius nulla facilisi cras non velit nec');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (5, 'DENIED', 5, 15056.94, DATE '2018-07-03', 'morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (7, 'DENIED', 3, 83463.08, DATE '2018-07-02', 'ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (7, 'PENDING', 3, 70942.03, DATE '2018-01-31', 'volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (15, 'DENIED', 6, 43960.27, DATE '2018-01-17', 'morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (9, 'PENDING', 1, 45337.01, DATE '2018-06-11', 'etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (14, 'APPROVED', 2, 74690.55, DATE '2018-04-24', 'erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (14, 'APPROVED', 3, 43379.91, DATE '2018-02-14', 'adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (8, 'APPROVED', 3, 21556.12, DATE '2017-11-10', 'etiam faucibus cursus urna ut tellus nulla ut erat id');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (3, 'PENDING', 1, 580.25, DATE '2018-03-22', 'quam suspendisse potenti nullam porttitor lacus at turpis donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (13, 'PENDING', 4, 29322.91, DATE '2018-08-29', 'eget congue eget semper rutrum nulla nunc purus phasellus in felis donec semper sapien a libero nam dui proin leo odio');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (3, 'APPROVED', 4, 82105.25, DATE '2018-06-10', 'molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (3, 'DENIED', 4, 46399.04, DATE '2018-08-10', 'id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (13, 'DENIED', 5, 35109.69, DATE '2018-06-16', 'erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (11, 'PENDING', 2, 95395.54, DATE '2017-10-11', 'nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (8, 'APPROVED', 1, 13082.9, DATE '2018-02-08', 'dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (12, 'APPROVED', 6, 34504.12, DATE '2018-03-17', 'leo pellentesque ultrices mattis odio donec vitae nisi nam ultrices libero');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (9, 'PENDING', 3, 75348.51, DATE '2018-01-05', 'odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (8, 'DENIED', 5, 64420.58, DATE '2018-03-04', 'nulla elit ac nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit ligula in lacus curabitur at ipsum ac');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (7, 'APPROVED', 6, 88295.62, DATE '2018-06-06', 'neque libero convallis eget eleifend luctus ultricies eu nibh quisque');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (6, 'DENIED', 4, 32997.51, DATE '2018-03-11', 'aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (9, 'APPROVED', 5, 18628.43, DATE '2017-12-11', 'sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (3, 'APPROVED', 2, 9812.05, DATE '2018-01-19', 'sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (12, 'PENDING', 5, 50621.57, DATE '2017-11-04', 'vestibulum vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (5, 'APPROVED', 4, 59311.52, DATE '2017-12-27', 'aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (14, 'DENIED', 5, 98937.21, DATE '2018-08-23', 'non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (10, 'DENIED', 6, 44634.67, DATE '2017-09-06', 'non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (10, 'APPROVED', 5, 99740.5, DATE '2018-02-03', 'sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (15, 'DENIED', 5, 22260.92, DATE '2018-01-11', 'in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus morbi sem mauris laoreet');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (3, 'PENDING', 5, 3385.25, DATE '2018-06-07', 'luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (10, 'APPROVED', 5, 41865.45, DATE '2018-08-25', 'morbi a ipsum integer a nibh in quis justo maecenas');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (9, 'PENDING', 1, 68495.82, DATE '2018-06-22', 'sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec dapibus duis at velit eu est congue elementum in');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (15, 'PENDING', 5, 14980.22, DATE '2018-03-08', 'habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet eros suspendisse');
insert into P1_REIMBURSEMENTS (STAFF_ID, STATUS, MAN_ID, AMOUNT, REQ_DATE, MESSAGE) values (3, 'PENDING', 2, 56754.11, DATE '2017-12-31', 'in porttitor pede justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante nulla justo aliquam');

COMMIT;
