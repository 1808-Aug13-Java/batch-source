set serveroutput on;
--SQL LAB
--
--
--1. SETTING UP ORACLE CHINOOK
--IN THIS SECTION YOU WILL BEGIN THE PROCESS OF WORKING WITH THE ORACLE CHINOOK DATABASE
--
--

--TASK – EXECUTE THE SCRIPT FOUND HERE
--UPLOAD YOUR .SQL FILE TO YOUR BRANCH WITH EACH SOLUTION TO EACH PART MARKED WITH A DESCRIPTIVE COMMENT. 
--
--
--2.0 SQL QUERIES
--IN THIS SECTION YOU WILL BE PERFORMING VARIOUS QUERIES AGAINST THE ORACLE CHINOOK DATABASE.
--2.1 SELECT
--TASK – SELECT ALL RECORDS FROM THE EMPLOYEE TABLE.
alias task2.0.0=
  SELECT * FROM EMPLOYEE;

--TASK – SELECT ALL RECORDS FROM THE EMPLOYEE TABLE WHERE LAST NAME IS KING.
alias task2.0.1=
  select * from employee
  where lastname = 'King';

--TASK – SELECT ALL RECORDS FROM THE EMPLOYEE TABLE WHERE FIRST NAME IS ANDREW AND REPORTSTO IS NULL 
alias task2.0.2=
  select * from employee 
    where firstname = 'Andrew'
      and reportsto is null;

--2.2 ORDER BY
--TASK – SELECT ALL ALBUMS IN ALBUM TABLE AND SORT RESULT SET IN DESCENDING ORDER BY TITLE.
alias task2.2.0 =
  select * from album order by title desc;
--TASK – SELECT FIRST NAME FROM CUSTOMER AND SORT RESULT SET IN ASCENDING ORDER BY CITY
alias task2.2.1=
  select firstname from customer order by city;

--2.3 INSERT INTO
--TASK – INSERT TWO NEW RECORDS INTO GENRE TABLE 
alias 2.3.0=
  insert into genre values(26, 'french pop');
alias task2.3.1=
  insert into genre values(27, 'k-pop');

--TASK – INSERT TWO NEW RECORDS INTO EMPLOYEE TABLE
alias task2.3.2=
  insert into employee values(9, 'moliner', 'maria', 'lexicographer', 1, date '2000-2-2', date '2001-3-3', '123 fake street', 'some city', 'some state', 'some country', '666', '55555555', '23423532532', 'yaueohueonuheoa@utehan')  
alias task2.3.3=
  insert into employee (employeeid, lastname, firstname) values (10, 'gen', 'lin');

--TASK – INSERT TWO NEW RECORDS INTO CUSTOMER TABLE 
alias task2.3.3=
insert into customer(customerid, firstname, lastname, email) values(60, 'haydee', 'farias', 'haydee@farias');
alias task2.3.4=
  insert into customer(customerid, firstname, lastname, email) values(61, 'kim', 'jerry', 'jerry@kim.com');

--2.4 UPDATE
--TASK – UPDATE AARON MITCHELL IN CUSTOMER TABLE TO ROBERT WALTER
alias task2.4.0=
  update customer 
    set firstname = 'Robert', lastname = 'Walter'
    where firstname = 'Aaron'
      and lastname = 'Mitchell';

--TASK – UPDATE NAME OF ARTIST IN THE ARTIST TABLE “CREEDENCE CLEARWATER REVIVAL” TO “CCR”
alias task2.4.1=
  update artist 
    set name = 'CCR'
    where name = 'Creedence Clearwater Revival';

--2.5 LIKE
--TASK – SELECT ALL INVOICES WITH A BILLING ADDRESS LIKE “T%”
alias task2.5.0=
  select * from invoice
    where billingaddress like 'T%';

--2.6 BETWEEN
--TASK – SELECT ALL INVOICES THAT HAVE A TOTAL BETWEEN 15 AND 50
alias 2.6.0=
  select * from invoice
  where total between 15 and 50;

--TASK – SELECT ALL EMPLOYEES HIRED BETWEEN 1ST OF JUNE 2003 AND 1ST OF MARCH 2004
alias 2.6.1=
  select * from employee
  where hiredate between date '2001-06-01' and date '2004-03-01';
--2.7 DELETE
--TASK – DELETE A RECORD IN CUSTOMER TABLE WHERE THE NAME IS ROBERT WALTER (THERE MAY BE CONSTRAINTS THAT RELY ON THIS, FIND OUT HOW TO RESOLVE THEM).        
alias 2.7.0=
  delete from customer
  where firstname = 'Robert' and lastname='Walter'; 

--1. SQL FUNCTIONS
--IN THIS SECTION YOU WILL BE USING THE ORACLE SYSTEM FUNCTIONS, AS WELL AS YOUR OWN FUNCTIONS, TO PERFORM VARIOUS ACTIONS AGAINST THE DATABASE
--3.1 SYSTEM DEFINED FUNCTIONS
--TASK – CREATE A FUNCTION THAT RETURNS THE CURRENT TIME.
create or replace function returns_the_current_time
return varchar2
is
cur_time varchar2(50);
h varchar2(5);
begin
  cur_time := to_char(current_timestamp, 'hh:mi:ss');
  return cur_time; 
end;
/

declare
  str varchar(50) := 'heyo';
begin
  dbms_output.put_line(returns_the_current_time);
end;
/

--TASK – CREATE A FUNCTION THAT RETURNS THE LENGTH OF NAME IN MEDIATYPE TABLE                
create or replace function length_of_name(name in varchar2)
return varchar2
is
begin
  return length(name);
end;
/

--3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS
--TASK – CREATE A FUNCTION THAT RETURNS THE AVERAGE TOTAL OF ALL INVOICES 
create or replace function average_total_of
return invoice.total%type
is
s sys_refcursor;
n number := 0; -- records loop count 
this_total invoice.total%type; -- holds total for current row of cursor
new_total invoice.total%type := 0; -- add cumulative total added from this_total
avg_total invoice.total%type; -- calculates average from new_total and n
begin
  open s for select total from invoice;
  loop
    fetch s into this_total; 
    new_total := new_total + this_total;
    n := n + 1;
    exit when s%notfound;
  end loop;
  avg_total := new_total / n;
  close s;
  return avg_total;
end;
/
alias task3.2.0=
  select average_total_of() as avg_total from dual;

--TASK – CREATE A FUNCTION THAT RETURNS THE MOST EXPENSIVE TRACK
--3.3 USER DEFINED SCALAR FUNCTIONS
create or replace function most_expensive_track
return invoiceline.unitprice%type
is
s sys_refcursor;
most_expensive invoiceline.unitprice%type;
begin
 open s for 
  select unitprice from invoiceline order by unitprice desc 
    fetch first 1 rows only;

  fetch s into most_expensive;
  return most_expensive; 
end;
/

alias task3.2.1=
select most_expensive_track() from dual;
--TASK – CREATE A FUNCTION THAT RETURNS THE AVERAGE PRICE OF INVOICELINE ITEMS IN THE INVOICELINE TABLE

create or replace function avg_price_invoiceline_items(in_invoiceid in invoiceline.invoiceid%type)
return invoiceline.unitprice%type
is
out_avg_unitprice invoiceline.unitprice%type;
begin
  select avg(unitprice) into out_avg_unitprice from invoiceline 
    where invoiceid = in_invoiceid;
  return out_avg_unitprice;
end;
/
--3.4 USER DEFINED TABLE VALUED FUNCTIONS

--TASK – CREATE A FUNCTION THAT RETURNS ALL EMPLOYEES WHO ARE BORN AFTER 1968.
create or replace function all_employees_after_1968
return sys_refcursor
is
  out_employeeid employee.employeeid%type;
  s sys_refcursor;
begin
  open s for
    select employeeid into out_employeeid from employee
      where birthdate > date '1968-12-31';
  return s;
end;
/
--4.0 STORED PROCEDURES
-- IN THIS SECTION YOU WILL BE CREATING AND EXECUTING STORED PROCEDURES. YOU WILL BE CREATING VARIOUS TYPES OF STORED PROCEDURES THAT TAKE INPUT AND OUTPUT PARAMETERS.
--4.1 BASIC STORED PROCEDURE
--TASK – CREATE A STORED PROCEDURE THAT SELECTS THE FIRST AND LAST NAMES OF ALL THE EMPLOYEES.
create or replace procedure selects_the_first(c1 out sys_refcursor)
is
begin
  open c1 for 
  select firstname, lastname from employee;
end;
/
  --declare
  --  svar sys_refcursor;
  --  firstname employee.firstname%type; 
  --  lastname employee.lastname%type; 
  --begin
  --  selects_the_first(svar);
  --  loop 
  --    fetch svar into firstname, lastname;
  --    exit when svar%notfound;
  --    dbms_output.put_line(firstname || ' ' || lastname);
  --  end loop;
  --end;
  --/

--4.2 STORED PROCEDURE INPUT PARAMETERS
--TASK – CREATE A STORED PROCEDURE THAT UPDATES THE PERSONAL INFORMATION OF AN EMPLOYEE.
create or replace procedure updates_the_personal(id in employee.employeeid%type, newName in employee.firstname%type)
is
begin
  update employee
  set firstname = newName
  where employeeid = id;
end;
/

--TASK – CREATE A STORED PROCEDURE THAT RETURNS THE MANAGERS OF AN EMPLOYEE.
create or replace procedure returns_the_managers(empid in employee.employeeid%type, name out employee.firstname%type) 
is
begin
   select m.firstname into name from employee e
    inner join employee m on m.employeeid = e.reportsto
      where e.employeeid = empid;

--same as
--select m.firstname into name from employee e, employee m
--  where e.reportsto = m.employeeid 
--    and employeeid = empid;
end;
/

--declare
--  name employee.firstname%type;
--begin
--  returns_the_managers(2, name);
--  dbms_output.put_line(name);
--end;
--/
--4.3 STORED PROCEDURE OUTPUT PARAMETERS

--TASK – CREATE A STORED PROCEDURE THAT RETURNS THE NAME AND COMPANY OF A CUSTOMER.
create or replace procedure returns_the_name_and_company(in_customerid in customer.customerid%type, out_firstname out customer.firstname%type, 
  out_company out customer.company%type  
) 
is
begin
  select firstname, 
    company into out_firstname, 
    out_company from customer 
  where customerid = in_customerid;
end;
/

--declare
--  out_firstname customer.firstname%type;
--  out_company customer.company%type;
--begin
--  returns_the_name_and_company(3, out_firstname, out_company);
--  dbms_output.put_line(out_firstname || out_company);
--end;
--/

--5.0 TRANSACTIONS
--IN THIS SECTION YOU WILL BE WORKING WITH TRANSACTIONS. TRANSACTIONS ARE USUALLY NESTED WITHIN A STORED PROCEDURE.

--TASK – CREATE A TRANSACTION THAT GIVEN A INVOICEID WILL DELETE THAT INVOICE (THERE MAY BE CONSTRAINTS THAT RELY ON THIS, FIND OUT HOW TO RESOLVE THEM).
create or replace procedure delete_invoice_by_invoiceid(in_invoiceid in invoice.invoiceid%type)
is
begin
  delete from invoice where invoiceid = in_invoiceid;
end;
/
--DROP CONSTRAINT FROM INVOICELINE AND ADD ON DELETE CASCADE
alter table invoiceline drop constraint fk_invoicelineinvoiceid;
alter table invoiceline add constraint fk_invoicelineinvoiceid
  foreign key (InvoiceId) references Invoice(InvoiceId)
  on delete cascade;

begin
  delete_invoice_by_invoiceid(7);
  commit;
end;
/

--RESTORE CONSTRAINTS FROM BEFORE
--alter table invoiceline drop constraint fk_invoicelineinvoiceid;
--alter table invoiceline add constraint fk_invoicelineinvoiceid
--  foreign key (InvoiceId) references Invoice(InvoiceId);
/
--TASK – CREATE A TRANSACTION NESTED WITHIN A STORED PROCEDURE THAT INSERTS A NEW RECORD IN THE CUSTOMER TABLE
create or replace procedure inserts_a_new_record(in_customerid in customer.customerid%type,
  in_firstname customer.firstname%type,
  in_lastname customer.lastname%type,
  in_email customer.email%type
)
is
begin
  insert into customer (customerid, firstname, lastname, email) 
    values(in_customerid, in_firstname, in_lastname, in_email);
  commit;
end;
/
--6.0 TRIGGERS
--IN THIS SECTION YOU WILL CREATE VARIOUS KINDS OF TRIGGERS THAT WORK WHEN CERTAIN DML STATEMENTS ARE EXECUTED ON A TABLE.
--6.1 AFTER/FOR
--TASK - CREATE AN AFTER INSERT TRIGGER ON THE EMPLOYEE TABLE FIRED AFTER A NEW RECORD IS INSERTED INTO THE TABLE.
create or replace trigger after_a_new_record_is_inserted
after insert on employee
for each row

begin
  dbms_output.put_line('You just inserted:');
  dbms_output.put_line('employeeid: ' || :new.employeeid);
  dbms_output.put_line('firstname: ' || :new.firstname);
  dbms_output.put_line('lastname: ' || :new.lastname);
  dbms_output.put_line('email: ' || :new.email);
end;
/

--TASK – CREATE AN AFTER UPDATE TRIGGER ON THE ALBUM TABLE THAT FIRES AFTER A ROW IS INSERTED IN THE TABLE
create or replace trigger after_update_album 
after update on album
for each row

begin
  --check if artistid was changed in album
  if :old.artistid != :new.artistid then
    dbms_output.put_line('artistid changed from ' || :old.artistid || ' to ' || :new.artistid); 
  end if;
end;
/

--TASK – CREATE AN AFTER DELETE TRIGGER ON THE CUSTOMER TABLE THAT FIRES AFTER A ROW IS DELETED FROM THE TABLE.
create or replace trigger after_delete_customer
  after delete on customer
  for each row

begin
  dbms_output.put_line('you just deleted customer = ' || :old.customerid);
end;
/
--7.0 JOINS
--IN THIS SECTION YOU WILL BE WORKING WITH COMBINING VARIOUS TABLES THROUGH THE USE OF JOINS. YOU WILL WORK WITH OUTER, INNER, RIGHT, LEFT, CROSS, AND SELF JOINS.
--7.1 INNER
--TASK – CREATE AN INNER JOIN THAT JOINS CUSTOMERS AND ORDERS AND SPECIFIES THE NAME OF THE CUSTOMER AND THE INVOICEID.
alias inner=
select firstname, invoiceid from customer 
inner join invoice using(customerid);
--7.2 OUTER
--TASK – CREATE AN OUTER JOIN THAT JOINS THE CUSTOMER AND INVOICE TABLE, SPECIFYING THE CUSTOMERID, FIRSTNAME, LASTNAME, INVOICEID, AND TOTAL.
alias outer=
select customerid, firstname, lastname, invoiceid, total from customer
full join invoice using(customerid); 

--7.3 RIGHT
--TASK – CREATE A RIGHT JOIN THAT JOINS ALBUM AND ARTIST SPECIFYING ARTIST NAME AND TITLE.
alias right=
select artist.name, album.title from artist
right join album using(artistid);
--7.4 CROSS
--TASK – CREATE A CROSS JOIN THAT JOINS ALBUM AND ARTIST AND SORTS BY ARTIST NAME IN ASCENDING ORDER.
alias cross=
select a1.name from artist a1 
cross join artist a2;
--7.5 SELF
--TASK – PERFORM A SELF-JOIN ON THE EMPLOYEE TABLE, JOINING ON THE REPORTSTO COLUMN.
alias self= 
select e1.firstname, e2.firstname from employee e1, employee e2
where e1.reportsto = e2.employeeid;
