CREATE EVENT IF NOT EXISTS Txn14_Day_14_Customer_Event
ON SCHEDULE EVERY 14 Day
DO 
DELETE FROM Customer WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn14_Day_14_Wallet_Event
ON SCHEDULE EVERY 14 Day
DO 
DELETE FROM Wallet WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn14_Day_14_Transaction_Event
ON SCHEDULE EVERY 14 Day
DO 
DELETE FROM Transaction WHERE valid_end_time < NOW();
