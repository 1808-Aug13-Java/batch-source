--comment
/*
multi
line
comment
*/
drop table EMPLOYEE;
drop table LOCATIONS;
drop table DEPARTMENT;

create table DEPARTMENT (
    DEPT_ID number(5) constraint PK_DEPT primary key,
    DEPT_NAME varchar2(50),
    MONTHLY_BUDGET number(7,2)
);


create table EMPLOYEE(
    EMP_ID number(5) constraint PK_EMP primary key,
    EMP_NAME varchar2(50),
    BIRTHDAY date,
    MONTHLY_SALARY number(7,2),
    HIRE_DATE date,
    POSITION varchar2(20),
    MANAGER_ID number(5),
    DEPT_ID number(5) constraint FK_EMP_DPT references DEPARTMENT
);
--we can't first insert to our employee table witha  department without first defining the department
--insert into EMPLOYEE values (1, 'JOHN SMITH', date '1989-01-05', 2000, date '2015-03-08', 'MK_REP', null, 1);

insert into DEPARTMENT values (1, 'MARKETING', 5000);
insert into DEPARTMENT values (2, 'ACCOUNTING', 4000);
insert into DEPARTMENT values (3, 'INFORMATION TECHNOLOGY', 4500);
insert into DEPARTMENT values (4, 'HUMAN RESOURCES', 4500);
insert into DEPARTMENT values (5, 'LEGAL', 4500);
insert into DEPARTMENT values (6, 'CUSTOMER SERVICES', 4500);
insert into DEPARTMENT (DEPT_ID, DEPT_NAME) values (7, 'SALES');--could have set budget with default


alter table EMPLOYEE
drop constraint FK_EMP_DPT;

alter table EMPLOYEE
add constraint FK_EMP_DPT
foreign key (DEPT_ID) references DEPARTMENT (DEPT_ID) on delete cascade;

insert into EMPLOYEE values (1, 'JOHN SMITH', date '1989-01-05', 2000, date '2015-03-08', 'MK_REP', null, 1);
insert into EMPLOYEE values (2, 'JAMES BOSH', date '1990-01-05', 3200, date '2013-03-08', 'AC_ACCOUNT', null, 2);
insert into EMPLOYEE values (3, 'LISA JACKSON', date '1988-01-05', 3800, date '2012-03-08', 'IT_PROF', null, 3);
insert into EMPLOYEE values (4, 'ANGELA DEAN', date '1982-01-05', 2000, date '2017-03-08', 'IT_PROF', 3, 3);
insert into EMPLOYEE values (5, 'NIGEL OATS', date '1990-01-05', 2200, date '2018-03-08', 'MK_REP', 1, 1);
insert into EMPLOYEE values (6, 'JAMES BOND', date '1992-01-05', 2800, date '2020-03-08', 'MK_REP', 1, 1);

select EMP_NAME
from EMPLOYEE;

select * from EMPLOYEE;
/*
delete from EMPLOYEE
where MONTHLY_SALARY > 3500;
*/
delete from EMPLOYEE
where EMP_ID = 3;


--------------------------------------------------
--Querying the database
--------------------------------------------------

SELECT * from EMPLOYEE
where MANAGER_ID = 1;
/*some fuckery right here
select * 
from EMPLOYEE
where EMPLOYEE_NAME like 'JAMES%'
order by MONTHLY_SALARY desc;
*/
select count(*) as TOTAL_EMPLOYEES from EMPLOYEE;


select DEPT_ID DEPT, ROUND  (avg(MONTHLY_SALARY)) AVG_SALARY
from EMPLOYEE
group by DEPT_ID
having DEPT_ID < 3;


select * 
from EMPLOYEE
where DEPT_ID = 1 or DEPT_ID = 3 or DEPT_ID = 6;

select * 
from EMPLOYEE
where DEPT_ID in(1,3,6);

select * 
from EMPLOYEE
where MONTHLY_SALARY = (
    select max( MONTHLY_SALARY )
    from EMPLOYEE
);

create table LOCATIONS (--alter table LOCATIONS add constraint PK_LOCATIONS primary key (LOCATIONS_ID
    LOCATION_ID number(1) constraint PK_LOC primary key, 
    STREET varchar2(30), 
    CITY varchar2(30), 
    STATE_ABR char(2), 
    ZIP_CODE number(5)
);

alter table EMPLOYEE
add (LOCATION_ID number(1) constraint FK_EMP_LOC references LOCATIONS);
--add (constraint FK_EMP_LOC foreign key (LOCATION_ID) references LOCATIONS)

insert into LOCATIONS values(1, '123 St', 'Chicago','IL','33809');
insert into LOCATIONS values(2, '123 St', 'Hell','MA','13255');
insert into LOCATIONS values(3, '123 St', 'League','GG','55555');
insert into LOCATIONS values(4, '1099 10th St', 'Moorhead','MN','56560');
insert into LOCATIONS values(5, '32913 Spruce Rd', 'Motley','MN','56466');


INSERT INTO EMPLOYEE VALUES (7, 'JILLIAN KYND', DATE '1980-10-15', 2840.00, DATE '2015-05-11', 'AC_ACCOUNT', 2, 2, 1);
INSERT INTO EMPLOYEE VALUES (8, 'TIM KIBBEL', DATE '1980-05-20', 2240.00, DATE '2014-07-28', 'MK_REP', 1, 1, 1);
INSERT INTO EMPLOYEE VALUES (9, 'ETHELIN COMINI', DATE '1982-06-16', 3380.00, DATE '2017-10-02', 'IT_DEV', 3, 3, 1);
INSERT INTO EMPLOYEE VALUES (10, 'JASE HANDLEY', DATE '1975-10-08', 1870.00, DATE '2017-08-17', 'LG_LAW',11, 5, 1);
INSERT INTO EMPLOYEE VALUES (11, 'ARIEL PAVIS', DATE '1981-09-23', 4500.00, DATE '2015-07-12', 'LG_LAW', NULL, 5, 2);
INSERT INTO EMPLOYEE VALUES (12, 'MELISSA ITZKOVSKY', DATE '1983-03-03', 3870.00, DATE '2014-09-15', 'LG_LAW',11, 5, 2);
INSERT INTO EMPLOYEE VALUES (13, 'MALIA FILISOV', DATE '1976-07-11', 4620.00, DATE '2014-11-09', 'CS_REP', NULL, 6, 2);
INSERT INTO EMPLOYEE VALUES (14, 'BRENDAN LOUISET', DATE '1979-01-21', 3760.00, DATE '2018-03-28', 'CS_REP',13, 6, 2);
INSERT INTO EMPLOYEE VALUES (15, 'MILT KLIEMANN', DATE '1983-02-25', 3820.00, DATE '2016-05-01', 'AC_ACCOUNT', 2, 2, 2);
INSERT INTO EMPLOYEE VALUES (16, 'LUCILLE HUNE', DATE '1994-01-04', 2300.00, DATE '2016-04-17', 'MK_REP',1, 1, 2);
INSERT INTO EMPLOYEE VALUES (17, 'PETA POLTZOLD', DATE '1990-09-24', 2500.00, DATE '2015-07-10', 'IT_DEV',3, 3, 3);
INSERT INTO EMPLOYEE VALUES (18, 'LYDIA POVER', DATE '1991-10-01', 2800.00, DATE '2016-08-03', 'IT_DEV', 17, 3, 3);
INSERT INTO EMPLOYEE VALUES (19, 'RON WINTERTON', DATE '1977-09-27', 2500.00, DATE '2018-02-23', 'LG_LAW', 11, 5, 3);
INSERT INTO EMPLOYEE VALUES (20, 'NITIN CHESTNUT', DATE '1995-01-18', 2800.00, DATE '2014-10-25', 'CS_REP', 13, 6, 3);
COMMIT;



select 
    E.EMP_NAME as NAME, 
    D.DEPT_NAME as DEPARTMENT
from EMPLOYEE E
--inner   --is implicit
join DEPARTMENT D
on E.DEPT_ID = D.DEPT_ID;
--comes out the same
select E.EMP_NAME as NAME, D.DEPT_NAME as DEPARTMENT
from EMPLOYEE E, DEPARTMENT D
where E.DEPT_ID = D.DEPT_ID;



select 
    E.EMP_NAME as NAME, 
    D.DEPT_NAME as DEPARTMENT
from EMPLOYEE E
full join DEPARTMENT D
on E.DEPT_ID = D.DEPT_ID;

select 
    E.EMP_NAME as NAME, 
    D.DEPT_NAME as DEPARTMENT
from EMPLOYEE E
left join DEPARTMENT D
on E.DEPT_ID = D.DEPT_ID;

select 
    E.EMP_NAME as NAME, 
    D.DEPT_NAME as DEPARTMENT
from EMPLOYEE E
right join DEPARTMENT D
on E.DEPT_ID = D.DEPT_ID;

select 
    E.EMP_NAME as NAME, 
    D.DEPT_NAME as DEPARTMENT
from EMPLOYEE E
cross join DEPARTMENT D;

/*
SELF JOIN STRUCTURE
select [columns]
from [tables]
where [predicate]
*/
select EMP1.EMP_NAME EMPLOYEE, EMP2.EMP_NAME MANAGER
from EMPLOYEE EMP1, EMPLOYEE EMP2
where EMP1.MANAGER_ID = EMP2.EMP_ID;

select * 
from EMPLOYEE
natural join DEPARTMENT;

select * 
from LOCATIONS
outer join EMPLOYEE
on EMPLOYEE.LOCATION_ID = LOCATIONS.LOCATION_ID;


select 
    E.EMP_NAME NAME,
    D.DEPT_NAME DEPARTMENT,
    concat(concat(L.CITY,', '),L.STATE) LOCATIONS
from EMPLOYEE E
inner join DEPARTMENT D
on E.DEPT+ID = E.DEPT_ID
inner join LOCATIONS L
on L.LOCATION_ID = E.LOCATION_ID;


select * from EMPLOYEE where DEPT_ID = 1
union
select * from EMPLOYEE where LOCATION_ID = 1;

select * from EMPLOYEE where DEPT_ID = 1
union all
select * from EMPLOYEE where LOCATION_ID = 1;

select * from EMPLOYEE where DEPT_ID = 1
intersect
select * from EMPLOYEE where LOCATION_ID = 1;

select * from EMPLOYEE where DEPT_ID = 1
minus
select * from EMPLOYEE where LOCATION_ID = 1;


select *
from DEPARTMENT
where DEPT_ID in (--CHECKS FOR JUST A LIST, RUNS EFFICIENTLY
    select DEPT_ID
    from EMPLOYEE
);


select *
from DEPARTMENT
where exists (--CAN LOOK THROUGH A QUERY, MORE THAN A SET, RUNS O(N2)
    select * from EMPLOYEE
    where EMPLOYEE.DEPT_ID = DEPTARTMENT.DEPT_ID
);

