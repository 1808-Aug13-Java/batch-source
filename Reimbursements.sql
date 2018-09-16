
CREATE TABLE USERS(
    U_ID NUMBER PRIMARY KEY,
    U_USERNAME VARCHAR2(100),
    U_PASSWORD VARCHAR2(100),
    U_FIRSTNAME VARCHAR2(60),
    U_LASTNAME VARCHAR2(60),
    U_EMAIL VARCHAR2(100),
    UR_ID NUMBER
);

SELECT * FROM REIMBURSEMENTS;

CREATE TABLE REIMBURSEMENTS(
    R_ID NUMBER PRIMARY KEY,
    R_AMOUNT NUMBER,
    R_DESCRIPTION VARCHAR2(100),
    R_RECEIPT BLOB,
    R_SUBMITTED TIMESTAMP,
    R_RESOLVED TIMESTAMP,
    U_ID_AUTHOR NUMBER CONSTRAINT FK_U_ID REFERENCES USERS,
    U_ID_RESOLVER NUMBER,
    RT_TYPE NUMBER,
    RT_STATUS NUMBER
);
INSERT INTO USERS VALUES(50, 'AJ', 'AJ', 'Awkward', 'Jogger', 'aj@cnn.com', 50);
INSERT INTO REIMBURSEMENTS VALUES(50, 1000, 'Bribe to leave Revature', '54455354', '21-OCT-13', '21-OCT-13', 50, 50, 50, 50);

CREATE OR REPLACE PROCEDURE PR_CREATE_REIMB(REIMB_AMOUNT IN REIMBURSEMENTS.R_AMOUNT%TYPE,
REIMB_DESCRIPTION IN REIMBURSEMENTS.R_DESCRIPTION%TYPE, REIMB_AUTHOR IN REIMBURSEMENTS.U_ID_AUTHOR%TYPE)
IS 
BEGIN
    INSERT INTO REIMBURSEMENTS ("R_AMOUNT", "R_DESCRIPTION", "U_ID_AUTHOR") VALUES(REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_AUTHOR);
   COMMIT;
END;
/

CREATE SEQUENCE SQ_PK_R_ID
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_REIMBURSEMENTS
BEFORE INSERT ON REIMBURSEMENTS
FOR EACH ROW 
BEGIN
    SELECT SQ_PK_R_ID.NEXTVAL INTO :NEW.R_ID FROM DUAL;
END;
/


CREATE OR REPLACE PROCEDURE PR_APPROVE_REIMB(RID IN REIMBURSEMENTS.R_ID%TYPE,
UID IN REIMBURSEMENTS.U_ID_AUTHOR%TYPE, STATUS IN REIMBURSEMENTS.RT_STATUS%TYPE)
IS 
BEGIN
   UPDATE REIMBURSEMENTS SET RT_STATUS = 2 WHERE RID = R_ID AND UID = U_ID_AUTHOR;
   COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE PR_DENY_REIMB(RID IN REIMBURSEMENTS.R_ID%TYPE,
UID IN REIMBURSEMENTS.U_ID_AUTHOR%TYPE, STATUS IN REIMBURSEMENTS.RT_STATUS%TYPE)
IS 
BEGIN
   UPDATE REIMBURSEMENTS SET RT_STATUS = 3 WHERE RID = R_ID AND UID = U_ID_AUTHOR;
   COMMIT;
END;
/