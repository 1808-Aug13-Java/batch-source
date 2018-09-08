/*
CREATE TABLE CUSTOMER
 (
 CUST_ID NUMBER(5) CONSTRAINT PK_CUSTOMER PRIMARY KEY,
 FNAME VARCHAR(20),
 LNAME VARCHAR(20),
 EMAIL VARCHAR(45)
 );
 
 CREATE TABLE INVOICE 
 (
 CUST_ID NUMBER(5) CONSTRAINT FK_CUST_ID REFERENCES CUSTOMER,
 PURCHASE_DATE DATE,
 PUR_AMOUNT NUMBER(5)
 );
 
 insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (1, 'Claudie', 'Abele', 'cabele0@instagram.com');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (2, 'Kermit', 'Pearson', 'kpearson1@gnu.org');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (3, 'Witty', 'Wynch', 'wwynch2@cloudflare.com');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (4, 'Carmel', 'Stouther', 'cstouther3@senate.gov');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (5, 'Felice', 'Otson', 'fotson4@shinystat.com');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (6, 'Christiano', 'Muzzillo', 'cmuzzillo5@de.vu');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (7, 'Merrie', 'Sherbrooke', 'msherbrooke6@cdbaby.com');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (8, 'Richart', 'Rollinshaw', 'rrollinshaw7@eventbrite.com');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (9, 'Guthry', 'Daffey', 'gdaffey8@squidoo.com');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (10, 'Marius', 'Dysert', 'mdysert9@who.int');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (11, 'Fax', 'Blowin', 'fblowina@google.co.uk');
--customers with no invoices
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (12, 'Porco', 'Rosso', 'rporco@giblystudio.co.uk');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (13, 'Princess', 'Mononoki', 'mononoki@giblystudio.co.uk');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (14, 'Hauru', 'Kimura', 'hkimura@giblystudio.co.uk');
insert into CUSTOMER (cust_id, FNAME, LNAME, email) values (15, 'Lupin', 'III', 'lupin@fakemail.co.uk');

insert into INVOICE (cust_id, purchase_date, pur_amount) values (1, to_date('2018/03/01', 'yyyy/mm/dd'), 38);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (2, to_date('2018/06/27', 'yyyy/mm/dd'), 74);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (3, to_date('2018/01/16', 'yyyy/mm/dd'), 39);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (4, to_date('2018/08/17', 'yyyy/mm/dd'), 45);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (5, to_date('2018/04/20', 'yyyy/mm/dd'), 23);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (6, to_date('2018/04/26', 'yyyy/mm/dd'), 19);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (7, to_date('2017/11/27', 'yyyy/mm/dd'), 90);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (8, to_date('2018/05/23', 'yyyy/mm/dd'), 72);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (9, to_date('2018/06/06', 'yyyy/mm/dd'), 29);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (10, to_date('2018/06/16', 'yyyy/mm/dd'), 61);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (1, to_date('2017/12/29', 'yyyy/mm/dd'), 44);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (2, to_date('2017/11/06', 'yyyy/mm/dd'), 16);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (3, to_date('2018/06/16', 'yyyy/mm/dd'), 25);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (4, to_date('2018/07/30', 'yyyy/mm/dd'), 9);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (5, to_date('2017/08/25', 'yyyy/mm/dd'), 29);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (6, to_date('2018/05/01', 'yyyy/mm/dd'), 4);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (7, to_date('2018/01/11', 'yyyy/mm/dd'), 28);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (8, to_date('2017/12/29', 'yyyy/mm/dd'), 93);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (9, to_date('2018/03/22', 'yyyy/mm/dd'), 32);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (10, to_date('2018/07/30', 'yyyy/mm/dd'), 72);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (11, to_date('2018/02/16', 'yyyy/mm/dd'), 55);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (2, to_date('2018/05/08', 'yyyy/mm/dd'), 51);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (3, to_date('2017/12/03', 'yyyy/mm/dd'), 10);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (4, to_date('2017/10/15', 'yyyy/mm/dd'), 99);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (5, to_date('2017/08/22', 'yyyy/mm/dd'), 64);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (6, to_date('2017/11/20', 'yyyy/mm/dd'), 73);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (7, to_date('2018/05/26', 'yyyy/mm/dd'), 76);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (8, to_date('2018/04/27', 'yyyy/mm/dd'), 17);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (9, to_date('2018/03/08', 'yyyy/mm/dd'), 54);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (10, to_date('2018/03/07', 'yyyy/mm/dd'), 91);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (1, to_date('2017/12/27', 'yyyy/mm/dd'), 32);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (2, to_date('2018/05/23', 'yyyy/mm/dd'), 64);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (3, to_date('2018/06/22', 'yyyy/mm/dd'), 19);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (4, to_date('2017/10/04', 'yyyy/mm/dd'), 79);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (5, to_date('2017/10/03', 'yyyy/mm/dd'), 99);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (6, to_date('2018/06/21', 'yyyy/mm/dd'), 63);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (7, to_date('2018/05/29', 'yyyy/mm/dd'), 51);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (8, to_date('2018/05/02', 'yyyy/mm/dd'), 28);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (9, to_date('2018/04/02', 'yyyy/mm/dd'), 55);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (10, to_date('2017/09/08', 'yyyy/mm/dd'), 57);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (11, to_date('2017/10/30', 'yyyy/mm/dd'), 52);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (11, to_date('2018/05/14', 'yyyy/mm/dd'), 62);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (3, to_date('2017/11/06', 'yyyy/mm/dd'), 10);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (4, to_date('2018/06/17', 'yyyy/mm/dd'), 10);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (5, to_date('2018/03/21', 'yyyy/mm/dd'), 48);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (6, to_date('2017/11/11', 'yyyy/mm/dd'), 35);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (7, to_date('2018/08/07', 'yyyy/mm/dd'), 44);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (8, to_date('2017/12/30', 'yyyy/mm/dd'), 84);
insert into INVOICE (cust_id, purchase_date, pur_amount) values (10, to_date('2018/08/09', 'yyyy/mm/dd'), 69);

commit;
*/

--Query return all invoices with listed customers exclude invoices without customers and exclude customers with no invoices
SELECT 
    *
FROM CUSTOMER
INNER JOIN INVOICE
ON INVOICE.CUST_ID = customer.CUST_ID
ORDER BY CUSTOMER.FNAME DESC;

--Query return all invoices with listed customers exclude invoices without customers and INCLUDE customers with no invoices
SELECT 
    *
FROM CUSTOMER
LEFT JOIN INVOICE
ON INVOICE.CUST_ID = customer.CUST_ID
ORDER BY CUSTOMER.FNAME DESC;

--Query to show each record in the invoice table along with the name of the customer
SELECT 
    FNAME, LNAME, INVOICE.CUST_ID, INVOICE.PURCHASE_DATE, INVOICE.PUR_AMOUNT
FROM CUSTOMER
JOIN INVOICE
ON INVOICE.CUST_ID = customer.CUST_ID
ORDER BY CUSTOMER.FNAME ASC;

--Query which shows the name of each customer and the total amount spent
SELECT 
    FNAME, LNAME, sum(invoice.pur_amount) AS AMOUNTSPENT
FROM CUSTOMER
JOIN INVOICE
ON INVOICE.CUST_ID = customer.CUST_ID
GROUP BY FNAME, LNAME
ORDER BY CUSTOMER.FNAME ASC;

--create a query which returns the customer who made the most recent purchase. the insert statement is for adding a purchase today to check statement
--insert into INVOICE (cust_id, purchase_date, pur_amount) values (10, to_date(SYSDATE, 'yyyy/mm/dd'), 69);
SELECT *
FROM ( SELECT *
FROM CUSTOMER
LEFT JOIN INVOICE
ON INVOICE.CUST_ID = customer.CUST_ID
ORDER BY INVOICE.PURCHASE_DATE ASC )WHERE ROWNUM = 1;

--create a query to display the purchaser of each invoice and the subtotal of each invoice if a 6% tax was applied to get toatal
SELECT 
    FNAME AS "FIRST NAME", LNAME AS "LAST NAME", ROUND(INVOICE.PUR_AMOUNT/1.06, 2) AS SUBTOTAL
FROM CUSTOMER
JOIN INVOICE
ON INVOICE.CUST_ID = customer.CUST_ID
ORDER BY CUSTOMER.FNAME ASC;

--Thursday Assignment
/*
-- CREATE A FUNCTION WHICH CALCULATES THE SUBTOTAL OF A GIVEN TOTAL, IF THE TOTAL IS EQUAL TO THE SUBTOTAL WITH 6% TAX - USE IT TO RECREATE THE QUERY FROM YESTERDAY
--0 CREATE A FUNCTION WHICH TAKES IN A STRING AND A NUMBER, AND CONCATINATES THAT STRING THAT NUMBER OF TIMES WITH A SPACE BETWEEN EACH AND RETURNS THE RESULT 
    (EX. MY_FUNC('CAT',5) RETURNS 'CAT CAT CAT CAT CAT')
-- CREATE A STORED PROCEDURE WHICH CHECK ALL OF THE INVOICES IN YOUR DATABASE AND DELETES ALL ORPHAN RECORDS IN YOUR INVOICE TABLE
-- CREATE A STORED PROCEDURE WHICH ENTERS A NEW INVOICE INTO THE INVOICE TABLE - IF THE DATE IS IN THE FUTURE, IT SHOULD NOT BE ENTERED AND A MESSAGE SHOULD BE PRINTED TO THE CONSOLE
    IN ADDITION IF THE VALUE OF THE INVOICE IS NEGATIVE, IT SHOULD NOT BE ENTERED INTO THE TABLE AND A MESSAGE SHOULD BE PRINTED TO THE CONSOLE
    
*/
--#1
CREATE OR REPLACE FUNCTION GET_SUBTOTAL(TOTAL INVOICE.PUR_AMOUNT%TOTAL)
RETURN INVOICE.PUR_AMOUNT%TYPE;
IS
BEGIN
	RETURN ROUND(TOTAL/1.06, 2);
END;
/

CREATE OR REPLACE FUNCTION CONCATENATIONX(STR VARCHAR2, X NUMBER)
RETURN VARCHAR2
IS 
    RTNSTRN VARCHAR2(50);
    INTERM VARCHAR2(50);
    BEGIN
        RTNSTRN := STR;
        INTERM := CONCAT(' ', STR);
        FOR I IN 1..X-1 LOOP
            RTNSTRN := CONCAT(RTNSTRN, INTERM);
        END LOOP;
    RETURN RTNSTRN;
END;
/
--#2
BEGIN
DBMS_OUTPUT.PUT_LINE(CONCATENATIONX('STAR', 8));
END;

--#3
CREATE OR REPLACE PROCEDURE DELETEORPHAN(INPUT_ID INVOICE.CUST_ID%TYPE)
IS 
CUST_ID CUSTOMER.CUST_ID%;
BEGIN
	select customer.cust_id, invoice.cust_id
	from customer 

END;







CREATE OR REPLACE PROCEDURE GIVERAISE(INPUT_ID EMPLOYEE.EMP_ID%TYPE, RAISE_AMOUNT EMPLOYEE.MONTHLY_SALARY%TYPE)
 IS 
 DEPT_BUDGET DEPARTMENT.MONTHLY_BUDGET%TYPE;
 BUDGET_USED DEPARTMENT.MONTHLY_BUDGET%TYPE;
 EMPLOYEE_NAME EPLOYEE.EMP_NAME%TYPE;
 CURRENT_SALARY EMPLOYEE.MONTHLY_SALARY%TYPE;
 BEGIN
 --SAVE THE DEPART BUDGET OF THE INPUT EMPLOYEE TO DEPT_BUDGET
    SELECT MONTHLY_BUDGET
    FROM DEPARTMENT
    WHERE DEPT_ID = 
    (SELECT DEPT_ID
    FROM EMPLOEE
    WHERE EMP_ID = INPUT_ID);
 -- BE SURE YOUR GETTING RIGHT INFO
 --DBMS_OUTPUT.PUT_LINE('DEPT_BUDGET: '||DEPT_BUDGET);
 SELECT SUM(MONTHLY_SALARY) INTO BUDGET_USED
    FROM EMPLOYEE
    WHERE DEPT_ID =
    (SELECT DEPT_ID
    FROM EMPLOYEE
    WHERE EMP_ID = INPUT_ID);
-- BE SURE YOUR GETTING RIGHT INFO
 --DBMS_OUTPUT.PUT_LINE('BUDGET_USED: '||BUDGET_USED);
 
 IF( (BUDGET_USED + RAISE_AMOUNT) > DEPT_BUDGET) THEN
    SELECT EMP_NAME, MONTHLY_SALARY INTO EMPLOYEE_NAME, CURRENT_SALARY
    FROM EMPLOYEE
    WHERE EMP_ID = INPUT_ID;
    DBMS_OUTPUT.PUT_LINE('INSUFFECIENT DEPARTMENT FUNDS. MONTHLY SALARY FOR '||EMPLOYEE_NAME||' REMAINS '||CURRENT_SALARY);
    ELSE
        UPDATE EMPLOYEE 
        SET MONTHLY_SALARY = MONTHLY_SALARY + RAISE_AMOUNT
        WHERE EMP_ID = INPUT_ID;
        DBMS_OUTPUT.PUT_LINE('RAISE SUCCESSFUL MONTHLY SALARY FOR '||EMPLOYEE_NAME||' RRASIED TO '||CURRENT_SALARY+RAISE_AMOUNT);
    END IF;
END;
\