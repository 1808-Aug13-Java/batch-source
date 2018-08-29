CREATE TABLE BANKING_ACCOUNT
(
    MONEY NUMBER(10,2),
    ACCOUNT_NAME VARCHAR2(20) CONSTRAINT PK_ACCOUNT_NAME PRIMARY KEY,
    IS_CHECKING NUMBER(1),
    MAIN_USER_ID NUMBER(10)
);

CREATE TABLE USER_ACCOUNT
(
    USER_NAME VARCHAR2(30) CONSTRAINT PK_UNIQUE_USER UNIQUE,
    PASSWORD_ VARCHAR2(30),
    FIRST_NAME VARCHAR2(15),
    LAST_NAME VARCHAR2(20),
    UA_ID NUMBER(10) CONSTRAINT PK_UA_ID PRIMARY KEY
);
/*
CREATE SEQUENCE SQ_UA_ID_PK
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_UA_ID
BEFORE INSERT ON USER_ACCOUNT FOR EACH ROW
BEGIN
    SELECT SQ_UA_ID_PK.NEXTVAL INTO :NEW.UA_ID FROM DUAL;
END;
*/

ALTER TABLE BANKING_ACCOUNT
    ADD CONSTRAINT FK_USER_ID FOREIGN KEY (MAIN_USER_ID) REFERENCES USER_ACCOUNT(UA_ID);

CREATE TABLE ACCOUNT_MATCHES
(
    ACCOUNT_NAME VARCHAR2(20) CONSTRAINT FK_BANKING_ACCOUNT REFERENCES BANKING_ACCOUNT,
    UA_ID NUMBER(10) CONSTRAINT FK_UA_ID REFERENCES USER_ACCOUNT
);


CREATE OR REPLACE PROCEDURE ADD_USER_TO_BANKING(USER_ IN VARCHAR2, BANK IN VARCHAR2, RES OUT NUMBER)
IS
THR SYS_REFCURSOR;
TEMP_NAME ACCOUNT_MATCHES.ACCOUNT_NAME%TYPE;
TEMP_ID ACCOUNT_MATCHES.UA_ID%TYPE;
THIS_ID USER_ACCOUNT.UA_ID%TYPE;
BEGIN
    RES := 0;

    OPEN THR FOR SELECT ACCOUNT_NAME FROM BANKING_ACCOUNT WHERE ACCOUNT_NAME = BANK;
    IF THR%NOTFOUND THEN
        RES := 1;
        RETURN;
    END IF;
    
    OPEN THR FOR SELECT UA_ID FROM USER_ACCOUNT WHERE USER_NAME = USER_;
    IF THR%NOTFOUND THEN
        RES := 2;
        RETURN;
    END IF;
    
    FETCH THR INTO THIS_ID;
    
    OPEN THR FOR SELECT * FROM ACCOUNT_MATCHES;
    
    LOOP
        EXIT WHEN THR%NOTFOUND;
        FETCH THR INTO TEMP_NAME, TEMP_ID;
        IF TEMP_NAME = BANK AND TEMP_ID = THIS_ID THEN
            RES := 3;
            RETURN;
        END IF;
    END LOOP;
    
    INSERT INTO ACCOUNT_MATCHES VALUES (BANK, THIS_ID);
    
END;
/

CREATE OR REPLACE PROCEDURE GET_AVAILABLE_USER_ID(RET OUT NUMBER)
IS
CUR SYS_REFCURSOR;
NUM_MAX NUMBER;
EARLY_POS NUMBER;
NUM_CUR NUMBER;
BEGIN
    NUM_MAX := 1;
    EARLY_POS := 1;

    OPEN CUR FOR SELECT UA_ID FROM USER_ACCOUNT;
    LOOP
        EXIT WHEN CUR%NOTFOUND;
        FETCH CUR INTO NUM_CUR;
        
        IF NUM_MAX <= NUM_CUR THEN
            NUM_MAX := NUM_MAX + 1;
        END IF;
        
        IF EARLY_POS = NUM_CUR THEN
            EARLY_POS := NUM_MAX;
        END IF;
        
    END LOOP;
    RET := EARLY_POS;
END;
/

--TRUNCATE TABLE USER_ACCOUNT;
--DROP TABLE ACCOUNT_MATCHES;
--DROP TABLE BANKING_ACCOUNT;
--DROP TABLE USER_ACCOUNT;
--DROP SEQUENCE SQ_UA_ID_PK;