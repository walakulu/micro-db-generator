CREATE DATABASE IF NOT EXISTS Txn14_Day_14;
USE Txn14_Day_14;

/*------------------DDL Script For Customer Table-----------------------*/
CREATE TABLE IF NOT EXISTS Customer
(cus_id VARCHAR(20) NOT NULL
, ssn VARCHAR(50) NOT NULL
, first_name VARCHAR(50) NOT NULL
, last_name VARCHAR(50) NOT NULL
, street_address VARCHAR(50) NOT NULL
, city VARCHAR(50) NOT NULL
, state VARCHAR(50) NOT NULL
, country VARCHAR(50) NOT NULL
, zip VARCHAR(10) NOT NULL
, gender VARCHAR(10) NOT NULL
, birth_date date NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX cus_id_idx ( cus_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Wallet Table-----------------------*/
CREATE TABLE IF NOT EXISTS Wallet
(wallet_id INT NOT NULL
, type VARCHAR(10) NOT NULL
, wallet_status VARCHAR(10) NOT NULL
, currency_type VARCHAR(10) NOT NULL
, prior_sar_acc TINYINT NOT NULL
, open_dt DATETIME NOT NULL
, close_dt DATETIME NOT NULL
, initial_deposit DOUBLE NOT NULL
, cus_id VARCHAR(20) NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX wallet_id_idx ( wallet_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Transaction Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction
(txn_id INT NOT NULL
, txn_amt DOUBLE NOT NULL
, orig_acc INT NOT NULL
, bene_acc INT NOT NULL
, txn_timestamp DATETIME NOT NULL
, txn_type_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,PRIMARY KEY txn_id_idx ( txn_id)
, INDEX wallet_id_a_idx ( orig_acc)
, INDEX wallet_id_b_idx ( bene_acc)
, INDEX txn_type_id_idx ( txn_type_id)
);
/*------------------DDL Script For Transaction_Type Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction_Type
(id INT NOT NULL
, type VARCHAR(20) NOT NULL
,PRIMARY KEY txn_type_id_idx ( id)
);
CREATE DATABASE IF NOT EXISTS Txn14_Day_14;
USE Txn14_Day_14;

/*------------------DDL Script For Customer Table-----------------------*/
CREATE TABLE IF NOT EXISTS Customer
(cus_id VARCHAR(20) NOT NULL
, ssn VARCHAR(50) NOT NULL
, first_name VARCHAR(50) NOT NULL
, last_name VARCHAR(50) NOT NULL
, street_address VARCHAR(50) NOT NULL
, city VARCHAR(50) NOT NULL
, state VARCHAR(50) NOT NULL
, country VARCHAR(50) NOT NULL
, zip VARCHAR(10) NOT NULL
, gender VARCHAR(10) NOT NULL
, birth_date date NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX cus_id_idx ( cus_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Wallet Table-----------------------*/
CREATE TABLE IF NOT EXISTS Wallet
(wallet_id INT NOT NULL
, type VARCHAR(10) NOT NULL
, wallet_status VARCHAR(10) NOT NULL
, currency_type VARCHAR(10) NOT NULL
, prior_sar_acc TINYINT NOT NULL
, open_dt DATETIME NOT NULL
, close_dt DATETIME NOT NULL
, initial_deposit DOUBLE NOT NULL
, cus_id VARCHAR(20) NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX wallet_id_idx ( wallet_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Transaction Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction
(txn_id INT NOT NULL
, txn_amt DOUBLE NOT NULL
, orig_acc INT NOT NULL
, bene_acc INT NOT NULL
, txn_timestamp DATETIME NOT NULL
, txn_type_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,PRIMARY KEY txn_id_idx ( txn_id)
, INDEX wallet_id_a_idx ( orig_acc)
, INDEX wallet_id_b_idx ( bene_acc)
, INDEX txn_type_id_idx ( txn_type_id)
);
/*------------------DDL Script For Transaction_Type Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction_Type
(id INT NOT NULL
, type VARCHAR(20) NOT NULL
,PRIMARY KEY txn_type_id_idx ( id)
);
CREATE DATABASE IF NOT EXISTS Txn14_Day_14;
USE Txn14_Day_14;

/*------------------DDL Script For Customer Table-----------------------*/
CREATE TABLE IF NOT EXISTS Customer
(cus_id VARCHAR(20) NOT NULL
, ssn VARCHAR(50) NOT NULL
, first_name VARCHAR(50) NOT NULL
, last_name VARCHAR(50) NOT NULL
, street_address VARCHAR(50) NOT NULL
, city VARCHAR(50) NOT NULL
, state VARCHAR(50) NOT NULL
, country VARCHAR(50) NOT NULL
, zip VARCHAR(10) NOT NULL
, gender VARCHAR(10) NOT NULL
, birth_date date NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX cus_id_idx ( cus_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Wallet Table-----------------------*/
CREATE TABLE IF NOT EXISTS Wallet
(wallet_id INT NOT NULL
, type VARCHAR(10) NOT NULL
, wallet_status VARCHAR(10) NOT NULL
, currency_type VARCHAR(10) NOT NULL
, prior_sar_acc TINYINT NOT NULL
, open_dt DATETIME NOT NULL
, close_dt DATETIME NOT NULL
, initial_deposit DOUBLE NOT NULL
, cus_id VARCHAR(20) NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX wallet_id_idx ( wallet_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Transaction Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction
(txn_id INT NOT NULL
, txn_amt DOUBLE NOT NULL
, orig_acc INT NOT NULL
, bene_acc INT NOT NULL
, txn_timestamp DATETIME NOT NULL
, txn_type_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,PRIMARY KEY txn_id_idx ( txn_id)
, INDEX wallet_id_a_idx ( orig_acc)
, INDEX wallet_id_b_idx ( bene_acc)
, INDEX txn_type_id_idx ( txn_type_id)
);
/*------------------DDL Script For Transaction_Type Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction_Type
(id INT NOT NULL
, type VARCHAR(20) NOT NULL
,PRIMARY KEY txn_type_id_idx ( id)
);
CREATE DATABASE IF NOT EXISTS Txn14_Day_14;
USE Txn14_Day_14;

/*------------------DDL Script For Customer Table-----------------------*/
CREATE TABLE IF NOT EXISTS Customer
(cus_id VARCHAR(20) NOT NULL
, ssn VARCHAR(50) NOT NULL
, first_name VARCHAR(50) NOT NULL
, last_name VARCHAR(50) NOT NULL
, street_address VARCHAR(50) NOT NULL
, city VARCHAR(50) NOT NULL
, state VARCHAR(50) NOT NULL
, country VARCHAR(50) NOT NULL
, zip VARCHAR(10) NOT NULL
, gender VARCHAR(10) NOT NULL
, birth_date date NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX cus_id_idx ( cus_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Wallet Table-----------------------*/
CREATE TABLE IF NOT EXISTS Wallet
(wallet_id INT NOT NULL
, type VARCHAR(10) NOT NULL
, wallet_status VARCHAR(10) NOT NULL
, currency_type VARCHAR(10) NOT NULL
, prior_sar_acc TINYINT NOT NULL
, open_dt DATETIME NOT NULL
, close_dt DATETIME NOT NULL
, initial_deposit DOUBLE NOT NULL
, cus_id VARCHAR(20) NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX wallet_id_idx ( wallet_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Transaction Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction
(txn_id INT NOT NULL
, txn_amt DOUBLE NOT NULL
, orig_acc INT NOT NULL
, bene_acc INT NOT NULL
, txn_timestamp DATETIME NOT NULL
, txn_type_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,PRIMARY KEY txn_id_idx ( txn_id)
, INDEX wallet_id_a_idx ( orig_acc)
, INDEX wallet_id_b_idx ( bene_acc)
, INDEX txn_type_id_idx ( txn_type_id)
);
/*------------------DDL Script For Transaction_Type Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction_Type
(id INT NOT NULL
, type VARCHAR(20) NOT NULL
,PRIMARY KEY txn_type_id_idx ( id)
);
CREATE DATABASE IF NOT EXISTS Txn14_Day_14;
USE Txn14_Day_14;

/*------------------DDL Script For Customer Table-----------------------*/
CREATE TABLE IF NOT EXISTS Customer
(cus_id VARCHAR(20) NOT NULL
, ssn VARCHAR(50) NOT NULL
, first_name VARCHAR(50) NOT NULL
, last_name VARCHAR(50) NOT NULL
, street_address VARCHAR(50) NOT NULL
, city VARCHAR(50) NOT NULL
, state VARCHAR(50) NOT NULL
, country VARCHAR(50) NOT NULL
, zip VARCHAR(10) NOT NULL
, gender VARCHAR(10) NOT NULL
, birth_date date NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX cus_id_idx ( cus_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Wallet Table-----------------------*/
CREATE TABLE IF NOT EXISTS Wallet
(wallet_id INT NOT NULL
, type VARCHAR(10) NOT NULL
, wallet_status VARCHAR(10) NOT NULL
, currency_type VARCHAR(10) NOT NULL
, prior_sar_acc TINYINT NOT NULL
, open_dt DATETIME NOT NULL
, close_dt DATETIME NOT NULL
, initial_deposit DOUBLE NOT NULL
, cus_id VARCHAR(20) NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX wallet_id_idx ( wallet_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Transaction Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction
(txn_id INT NOT NULL
, txn_amt DOUBLE NOT NULL
, orig_acc INT NOT NULL
, bene_acc INT NOT NULL
, txn_timestamp DATETIME NOT NULL
, txn_type_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,PRIMARY KEY txn_id_idx ( txn_id)
, INDEX wallet_id_a_idx ( orig_acc)
, INDEX wallet_id_b_idx ( bene_acc)
, INDEX txn_type_id_idx ( txn_type_id)
);
/*------------------DDL Script For Transaction_Type Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction_Type
(id INT NOT NULL
, type VARCHAR(20) NOT NULL
,PRIMARY KEY txn_type_id_idx ( id)
);
CREATE DATABASE IF NOT EXISTS Txn14_Day_14;
USE Txn14_Day_14;

/*------------------DDL Script For Customer Table-----------------------*/
CREATE TABLE IF NOT EXISTS Customer
(cus_id VARCHAR(20) NOT NULL
, ssn VARCHAR(50) NOT NULL
, first_name VARCHAR(50) NOT NULL
, last_name VARCHAR(50) NOT NULL
, street_address VARCHAR(50) NOT NULL
, city VARCHAR(50) NOT NULL
, state VARCHAR(50) NOT NULL
, country VARCHAR(50) NOT NULL
, zip VARCHAR(10) NOT NULL
, gender VARCHAR(10) NOT NULL
, birth_date date NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX cus_id_idx ( cus_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Wallet Table-----------------------*/
CREATE TABLE IF NOT EXISTS Wallet
(wallet_id INT NOT NULL
, type VARCHAR(10) NOT NULL
, wallet_status VARCHAR(10) NOT NULL
, currency_type VARCHAR(10) NOT NULL
, prior_sar_acc TINYINT NOT NULL
, open_dt DATETIME NOT NULL
, close_dt DATETIME NOT NULL
, initial_deposit DOUBLE NOT NULL
, cus_id VARCHAR(20) NOT NULL
, txn_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,INDEX wallet_id_idx ( wallet_id)
, INDEX txn_id_idx ( txn_id)
);
/*------------------DDL Script For Transaction Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction
(txn_id INT NOT NULL
, txn_amt DOUBLE NOT NULL
, orig_acc INT NOT NULL
, bene_acc INT NOT NULL
, txn_timestamp DATETIME NOT NULL
, txn_type_id INT NOT NULL
, valid_start_time DATETIME NOT NULL
, valid_end_time DATETIME NOT NULL
,PRIMARY KEY txn_id_idx ( txn_id)
, INDEX wallet_id_a_idx ( orig_acc)
, INDEX wallet_id_b_idx ( bene_acc)
, INDEX txn_type_id_idx ( txn_type_id)
);
/*------------------DDL Script For Transaction_Type Table-----------------------*/
CREATE TABLE IF NOT EXISTS Transaction_Type
(id INT NOT NULL
, type VARCHAR(20) NOT NULL
,PRIMARY KEY txn_type_id_idx ( id)
);
