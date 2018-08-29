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

