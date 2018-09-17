CREATE TABLE BANK_ACCOUNT (
    USERNAME VARCHAR(50) CONSTRAINT FK_ACCOUNT REFERENCES USER_INFO,
    BALANCE NUMBER (9,2)
    );
    
CREATE TABLE USER_INFO(
    USERNAME VARCHAR2(50) CONSTRAINT PK_USER_INFO PRIMARY KEY,
    PW VARCHAR(20),
    FNAME VARCHAR2(50),
    LNAME VARCHAR2(50)
    );

CREATE OR REPLACE PROCEDURE INSERT_USER_INFO(UN IN VARCHAR2, PASWORD IN VARCHAR2, F_NAME IN VARCHAR2, L_NAME IN VARCHAR2)
IS
BEGIN
    INSERT INTO USER_INFO(USERNAME, PW, FNAME, LNAME) VALUES (UN, PASWORD, F_NAME, L_NAME);
END;
/

BEGIN
    INSERT_USER_INFO('username5', 'password5', 'Kitty', 'Boomboom');
END;

select * from user_info;

select * from bank_account;