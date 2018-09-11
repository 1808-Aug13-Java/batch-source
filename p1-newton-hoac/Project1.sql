DROP SEQUENCE SQ_LOC_PK;
DROP SEQUENCE SQ_REIMB_PK;
DROP TABLE P1_LOGIN CASCADE CONSTRAINTS;
DROP TABLE P1_MANAGER CASCADE CONSTRAINTS;
DROP TABLE P1_LOCATION CASCADE CONSTRAINTS;
DROP TABLE P1_PROFILE CASCADE CONSTRAINTS;
DROP TABLE P1_REIMBURSEMENT CASCADE CONSTRAINTS;

CREATE TABLE P1_LOGIN (
    EMP_ID NUMBER(10) PRIMARY KEY,
    USERNAME VARCHAR2(30) NOT NULL,
    PASSWORD VARCHAR2(30) NOT NULL
);

CREATE TABLE P1_MANAGER (
    MAN_ID NUMBER(10) PRIMARY KEY,
    CONSTRAINT FK_MAN_EMP
        FOREIGN KEY (MAN_ID)
        REFERENCES P1_LOGIN(EMP_ID)
        ON DELETE CASCADE
);

CREATE TABLE P1_LOCATION (
    LOC_ID NUMBER(10) PRIMARY KEY,
    STREET VARCHAR2(30),
    CITY VARCHAR2(20),
    STATE VARCHAR2(2),
    ZIP NUMBER(5)
);

CREATE TABLE P1_PROFILE (
    EMP_ID NUMBER(10) NOT NULL,
    FIRSTNAME VARCHAR2(30) NOT NULL,
    LASTNAME VARCHAR2(30) NOT NULL,
    LOC_ID NUMBER(10) NOT NULL,
    PHONE NUMBER(15),
    EMAIL VARCHAR2(30),
    CONSTRAINT FK_PROF_LOG
        FOREIGN KEY (EMP_ID)
        REFERENCES P1_LOGIN(EMP_ID)
        ON DELETE CASCADE,
    CONSTRAINT FK_PROF_LOC
        FOREIGN KEY (LOC_ID)
        REFERENCES P1_LOCATION(LOC_ID)
        ON DELETE SET NULL
);

CREATE TABLE P1_REIMBURSEMENT (
    R_ID NUMBER(30) PRIMARY KEY,
    EMP_ID NUMBER(30),
    MAN_ID NUMBER(30) DEFAULT 0,
    STATUS VARCHAR2(10) NOT NULL,
    ACTION VARCHAR2(10),
    DESCRIPTION VARCHAR(50),
    CONSTRAINT FK_REIM_EMP
        FOREIGN KEY (EMP_ID)
        REFERENCES P1_LOGIN(EMP_ID)
        ON DELETE SET NULL,
    CONSTRAINT FK_REIM_MAN
        FOREIGN KEY (MAN_ID)
        REFERENCES P1_MANAGER(MAN_ID)
        ON DELETE SET NULL
);

CREATE SEQUENCE SQ_REIMB_PK
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SQ_LOC_PK
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_LOC
BEFORE INSERT ON P1_LOCATION
FOR EACH ROW
BEGIN
    SELECT SQ_LOC_PK.NEXTVAL INTO :NEW.LOC_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_REIMB
BEFORE INSERT ON P1_REIMBURSEMENT
FOR EACH ROW
BEGIN
    SELECT SQ_REIMB_PK.NEXTVAL INTO :NEW.R_ID FROM DUAL;
END;
/


INSERT INTO P1_LOGIN(EMP_ID, USERNAME, PASSWORD) VALUES (0, 'NULL', 'NULL');
INSERT INTO P1_LOGIN(EMP_ID, USERNAME, PASSWORD) VALUES (1, 'josh', 'josh');
INSERT INTO P1_LOGIN(EMP_ID, USERNAME, PASSWORD) VALUES (2, 'admin', 'pass');
INSERT INTO P1_LOGIN(EMP_ID, USERNAME, PASSWORD) VALUES (3, 'test', 'account');

INSERT INTO P1_MANAGER(MAN_ID) VALUES (0);
INSERT INTO P1_MANAGER(MAN_ID) VALUES (2);


INSERT INTO P1_LOCATION(STREET, CITY, STATE, ZIP) VALUES ('1234 Main St', 'City', 'VA', 20910);

INSERT INTO P1_REIMBURSEMENT(EMP_ID, MAN_ID, STATUS, ACTION, DESCRIPTION) VALUES (1, NULL, 'PENDING', NULL, 'Travel reimbursement');


SELECT * FROM P1_LOGIN;
SELECT * FROM P1_LOGIN WHERE EMP_ID = 1;

SELECT * FROM P1_LOGIN L
JOIN P1_MANAGER M
ON L.EMP_ID = M.MAN_ID;

SELECT * FROM P1_REIMBURSEMENT;

UPDATE P1_REIMBURSEMENT
SET EMP_ID = 2
WHERE R_ID = 1;

COMMIT;
