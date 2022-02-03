CREATE EVENT IF NOT EXISTS Txn7_Day_7_Customer_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Customer WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Wallet_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Wallet WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Transaction_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Transaction WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Customer_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Customer WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Wallet_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Wallet WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Transaction_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Transaction WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Customer_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Customer WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Wallet_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Wallet WHERE valid_end_time < NOW();

CREATE EVENT IF NOT EXISTS Txn7_Day_7_Transaction_Event
ON SCHEDULE EVERY 7 Day
DO 
DELETE FROM Transaction WHERE valid_end_time < NOW();

