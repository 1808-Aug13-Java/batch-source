CREATE TABLE BANK_USERS (
USER_ID INTEGER PRIMARY KEY,
USERNAME VARCHAR(50) UNIQUE NOT NULL,
PASSWORD VARCHAR(50) NOT NULL
);

DROP TABLE BANK_USERS;

CREATE TABLE BANK_ACCOUNT (
ACCOUNT_ID INTEGER PRIMARY KEY,
USER_ID INTEGER,
ACCOUNT_BALANCE NUMBER (15,2),
CONSTRAINT FK_ACCOUNT_USER FOREIGN KEY (USER_ID) REFERENCES BANK_USERS(USER_ID)
);

DROP TABLE BANK_ACCOUNT;


CREATE SEQUENCE SQ_BANK_USERS
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE SQ_BANK_USERS;

CREATE SEQUENCE SQ_BANK_ACCOUNT
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE SQ_BANK_ACCOUNT;

CREATE SEQUENCE SQ_BANK_ACCOUNT_FK
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE SQ_BANK_ACCOUNT_FK;

CREATE OR REPLACE TRIGGER INSERT_USER_INC
BEFORE INSERT ON BANK_USERS
FOR EACH ROW
    BEGIN
        :new.USER_ID := SQ_BANK_USERS.NEXTVAL;
    END;
/

CREATE OR REPLACE TRIGGER INSERT_ACCOUNT_INC
BEFORE INSERT ON BANK_ACCOUNT
FOR EACH ROW
    BEGIN
         :new.ACCOUNT_ID := SQ_BANK_ACCOUNT.NEXTVAL;  
    END;
/

CREATE OR REPLACE TRIGGER INSERT_ACCOUNTFK_INC
BEFORE INSERT ON BANK_ACCOUNT
FOR EACH ROW
    BEGIN
         :new.USER_ID := SQ_BANK_ACCOUNT_FK.NEXTVAL;  
    END;
/

INSERT INTO BANK_USERS (username, password) VALUES ('Henry','p@ssw0rd');
INSERT INTO BANK_ACCOUNT (ACCOUNT_BALANCE) VALUES (1.50);

CREATE OR REPLACE PROCEDURE INCREASE_AMOUNT(ID IN BANK_ACCOUNT.ACCOUNT_ID%TYPE, VAL IN BANK_ACCOUNT.ACCOUNT_BALANCE%TYPE)
IS 
BEGIN
    UPDATE BANK_ACCOUNT
    SET ACCOUNT_BALANCE = ACCOUNT_BALANCE +  VAL
    WHERE ACCOUNT_ID = ID;
END;
/

CREATE OR REPLACE PROCEDURE DECREASE_AMOUNT(ID IN BANK_ACCOUNT.ACCOUNT_ID%TYPE, VAL IN BANK_ACCOUNT.ACCOUNT_BALANCE%TYPE)
IS 
BEGIN
    UPDATE BANK_ACCOUNT
    SET ACCOUNT_BALANCE = ACCOUNT_BALANCE -  VAL
    WHERE ACCOUNT_ID = ID;
END;
/