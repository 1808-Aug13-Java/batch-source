-- 1. CREATE A FUNCTION WHICH CALCULATES THE SUBTOTAL OF A GIVEN TOTAL, IF THE TOTAL IS EQUAL TO THE 
-- SUBTOTAL WITH 6% TAX - USE IT TO RECREATE THE QUERY FROM YESTERDAY
CREATE OR REPLACE FUNCTION calcSubtotal(Total NUMBER)
RETURN NUMBER
IS
BEGIN
    RETURN (.94) * Total;
END;

SELECT IN_ID, CUST_NAME, calcSubtotal(AMOUNT) SubTotal, AMOUNT Total FROM INVOICE I
JOIN CUSTOMER C ON I.Customer_ID = C.Cust_ID;


-- 2.
CREATE OR REPLACE FUNCTION repeatString(str STRING, repeats NUMBER)
RETURN STRING
IS 
    -- Declare a string to return. It's size can be up to the maximum size for a string. 
    currentString varchar2(32767);
    -- Keeps track of how many copies of str we have
    counter NUMBER;
BEGIN
    -- Start by setting currentStr to str
    currentString := str;
    counter := 1;
    
    -- Loop until we have the specified number of str in currentString
    LOOP 
        EXIT WHEN counter = repeats;
        -- Concatenate the two strings together with a space between them
        currentString := CONCAT(currentString, CONCAT(' ', str));
        counter := counter + 1;
    END LOOP;
    
    RETURN currentString;
END;


SET SERVEROUTPUT ON;
BEGIN
    DBMS_OUTPUT.PUT_LINE(repeatString('123', 4));
END;


INSERT INTO INVOICE VALUES(100, DATE '2018-09-09', 25, 10);
-- 3. 
DELETE FROM INVOICE 
WHERE Customer_ID IS NULL 
    OR
    Customer_ID NOT IN 
    (SELECT CUST_ID FROM CUSTOMER);


-- 4. 
CREATE OR REPLACE PROCEDURE addInvoice(
                                IN_ID INVOICE.IN_ID % TYPE,
                                IN_DATE INVOICE.IN_DATE % TYPE,
                                Customer_ID INVOICE.Customer_ID % TYPE,
                                Amount INVOICE.Amount % TYPE
                                )
IS
BEGIN
    -- Make sure that the invoice date isn't in the future
    IF (IN_DATE > CURRENT_DATE) THEN
        DBMS_OUTPUT.PUT_LINE('Date: ' || IN_DATE || ' Is Beyond Current Date: ' || CURRENT_DATE);
    -- Make sure that the amount of the invoice is non-negative
    ELSIF (Amount < 0) THEN
        DBMS_OUTPUT.PUT_LINE('Amount cannot be negative: ' || Amount);
    ELSE
        INSERT INTO INVOICE VALUES (IN_ID, IN_DATE, Customer_ID, Amount);
    END IF;
END;

-- Test the following function
BEGIN
    addInvoice(54, CURRENT_DATE, 1, 1);
END;



