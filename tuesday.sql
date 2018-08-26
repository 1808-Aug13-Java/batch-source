--CREATE TABLE EMPLOYEE (
--    EMP_ID NUMBER(5) CONSTRAINT PK_EMP PRIMARY KEY,
--    EMP_NAME VARCHAR2(50),
--    BIRTHDAY DATE,
--    MONTHLY_SALARY NUMBER(7,2),
--    HIRE_DATE DATE,
--    POSITION VARCHAR2(20),
--    MANAGER_ID NUMBER(5),
--    DEPT_ID NUMBER(5) CONSTRAINT FK_EMP_DPT REFERENCES DEPARTMENT
--);

alias first= create table customer (
  cust_id number(5) constraint pk_cust primary key,
  cust_first_name varchar2(25)
);
alias second= create table invoice (
  id number(5) constraint pk_id primary key,
  date_c date,
  amnt number(7,2),
  cust_id number(5) constraint fk_cust_id references customer
);



--Tuesday
--Create a table INVOICE which includes an id, date, customer id, and amount

--Create a table CUSTOMER which includes relevant customer information 
--Customer id in the invoice table should make a reference to the customer id in the customer table
--Insert at least 50 records into your invoice table and at least 10 records into your customer table
--Create a query which shows purchases that occured today

--Create a query which shows each customer and the number of purchases made by each
select count(*) from invoice, customer;
--Create a query which shows each customer and the total cost of all their purchases

--Create a query which returns all purchases which took place in the last month, display them in descending order
--Create a query which show the top three most expensive purchases

alias top3=
select * from invoice 
order by total desc
fetch first 3 rows only;
--Wednesday
--
--
--Create a query which returns all of the invoices which have a listed customer, but not invoices who have no customer listed and not customers who have no invoices listed
alias invoice1=
select * from invoice 
inner join customer using (customerid);

--Create a query which returns all of the invoices and their customer, not invoices who have no customer listed but include customers which have no invoices listed
alias invoice2=
select invoiceid, firstname from invoice 
left join customer using (customerid) 
order by firstname;
--Create a query which shows each record in the invoice table, along with the name of the customer
alias invoice3=
select invoice.*, firstname from invoice 
inner join customer on invoice.customerid = customer.customerid; 
--Create a query which shows the name of each customer and the total amount they have spent
alias invoice4=
select  sum(i1.total), i1.customerid from invoice i1
where customerid = 
    (select customerid
        from customer 
        where i1.customerid 
            = customerid)
        group by customerid
        order by customerid;

alias test=
select total from invoice i1
where total > 
    (select avg(total) from invoice)
    order by total;
--Create a query which returns the record of the customer who made the most recent purchase
alias mostrecent=
select invoice.customerid, firstname, invoicedate from invoice
inner join customer on invoice.customerid = customer.customerid
order by invoicedate desc 
fetch first 1 row only; 

--Create a query which shows the purchaser of each invoice and the subtotal of each invoice if 6% sales tax was applied to the subtotal to get the price of each invoice
select firstname, total - total*0.06 from invoice 
inner join customer using(customerid);
--
--When you complete these exercises, put all of your DDL, DML, and DQL in a .sql file and upload it to your branch in the batch-source repository. Have these up by 5pm today.


