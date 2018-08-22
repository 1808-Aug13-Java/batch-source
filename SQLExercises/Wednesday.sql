-- Create a query which returns all of the invoices which have a listed customer, 
--	but not invoices who have no customer listed and not customers who have no invoices listed
SELECT *
FROM INVOICE I, CUSTOMER C
WHERE I.CUST_ID = C.CUST_ID;

--Create a query which returns all of the invoices and their customer, 
--	not invoices who have no customer listed but include customers which have no invoices listed
SELECT *
FROM INVOICE I
RIGHT JOIN CUSTOMER C
ON I.CUST_ID = C.CUST_ID;
	
-- Create a query which shows each record in the invoice table, along with the name of the customer
SELECT I.*, C.CUST_NAME
FROM INVOICE I
JOIN CUSTOMER C
ON I.CUST_ID = C.CUST_ID
ORDER BY I.INV_ID;
	
-- Create a query which shows the name of each customer and the total amount they have spent
SELECT C.CUST_NAME CUSTOMER, CONCAT('$', 8*SUM(I.AMOUNT))TOTAL_COST
FROM INVOICE I
JOIN CUSTOMER C
ON C.CUST_ID = I.CUST_ID
GROUP BY CUST_NAME
ORDER BY 8*SUM(I.AMOUNT) DESC;

-- Create a query which returns the record of the customer who made the most recent purchase
SELECT *
FROM (
    SELECT *
    FROM INVOICE
    ORDER BY INV_DATE DESC)
WHERE ROWNUM <=1

-- Create a query which shows the purchaser of each invoice and the subtotal of each invoice if 6% sales tax 
--	was applied to the subtotal to get the price of each invoice
SELECT C.CUST_NAME CUSTOMER, I.INV_ID, CONCAT('$', 8*I.AMOUNT*1.06)SUBTOTAL
FROM INVOICE I
JOIN CUSTOMER C
ON C.CUST_ID = I.CUST_ID
ORDER BY INV_ID ASC;