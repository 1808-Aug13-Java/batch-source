set serveroutput on;
create or replace procedure testit
is
begin
  dbms_output.put_line('heyo');
end;
/
create or replace procedure testit
is
  a_name varchar2(50);
  b_name varchar2(50);
  c1 sys_refcursor; 
begin
  open c1 for 
  select table_name into a_name from user_tables;
  loop
    fetch c1 into b_name;
    if (b_name = 'GENRE') then
      dbms_output.put_line('EXISTS'); --raise table_found exception 
    end if;
    exit when c1%notfound;
  end loop;
end;
/
  drop table action_type;
  drop table user_actions;
  drop table actions;
  drop table users;
  create table users (
    user_id number,
    username varchar2(50) not null unique,
    password varchar2(50) not null,
    firstname varchar2(50) not null,
    lastname varchar2(50) not null,
    balance number not null,
    constraint pk_user primary key (user_id)
  );

  create table actions (
    action_id number not null,
    user_id number not null unique,
    amount number,
    timestamp date,
    constraint pk_action primary key (action_id)
  );

  create table user_actions (
    user_id number not null,
    action_id number not null,
    unique (user_id, action_id)
  );

  create table action_type (
    action_type_id number,
    description varchar2(50),
    constraint pk_action_type primary key(action_type_id)
  );

  alter table user_actions add constraint fk_user_id
  foreign key (user_id) references users (user_id);

  alter table user_actions add constraint fk_action_id
  foreign key(action_id) references actions (action_id);

create sequence sq_users_pk
  start with 1
  increment by 1;

create or replace trigger tr_insert_users 
before insert on users
for each row
begin
  select sq_users_pk.nextval into :new.user_id from dual;
end;
/

create or replace procedure add_balance(in_username in users.username%type, in_amt in users.balance%type) 
is
  cur_balance users.balance%type;
  new_balance users.balance%type;
begin
  select balance into cur_balance from users where username = in_username; 
  --dbms_output.put_line(cur_balance);
  new_balance := cur_balance + in_amt;
  update users
    set balance = new_balance
    where username = in_username;
end;
/
create or replace procedure add_balanceE(in_username in users.username%type, in_amt in users.balance%type) 
is
  negative_balance exception;
  cur_balance users.balance%type;
  new_balance users.balance%type;
begin
  select balance into cur_balance from users where username = in_username; 
  dbms_output.put_line(cur_balance);
  new_balance := cur_balance + in_amt;
  if new_balance < 0 then 
    raise negative_balance;
  end if;
  update users
    set balance = new_balance
    where username = in_username;
exception
  when negative_balance then
    raise_application_error(-20001, 'You cant withdraw that much');
end;
/


insert into users (username, password, firstname, lastname, balance) 
values('jbki', '123', 'jerry', 'kim', 499);

insert into users (username, password, firstname, lastname, balance) 
values('jbki5', '123', 'jerry', 'kim', 0);

insert into users (username, password, firstname, lastname, balance) 
values('username2', '123', 'first2', 'last2', 0);

insert into users (username, password, firstname, lastname, balance) 
values('username3', '123', 'first3', 'last3', 0);

insert into users (username, password, firstname, lastname, balance) 
values('username4', '123', 'first3', 'last3', 0);
commit;

begin
  add_balance('jbki5', 5000);
  add_balance('jbki', -300);
  add_balance('jbki', 10);
end;
/
