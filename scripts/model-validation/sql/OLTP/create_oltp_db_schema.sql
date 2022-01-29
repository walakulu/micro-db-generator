
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema online_bank
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `online_bank` ;
USE `online_bank` ;

-- -----------------------------------------------------
-- Table `Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Customer` (
  `cus_id` VARCHAR(10) NOT NULL,
  `ssn` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `street_address` VARCHAR(100) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  PRIMARY KEY (`cus_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Transaction_Type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transaction_Type` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Wallet` (
  `wallet_id` INT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `wallet_status` VARCHAR(10) NOT NULL,
  `currency_type` VARCHAR(10) NOT NULL,
  `prior_sar_acc` TINYINT NOT NULL,
  `open_dt` DATETIME NOT NULL,
  `close_dt` DATETIME NOT NULL,
  `initial_deposit` DOUBLE NOT NULL,
  `cus_id` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`wallet_id`),
  UNIQUE INDEX `acc_id_UNIQUE` (`wallet_id` ASC),
  INDEX `fk_Wallet_Profile_idx` (`cus_id` ASC),
  CONSTRAINT `fk_Wallet_Profile`
    FOREIGN KEY (`cus_id`)
    REFERENCES `Customer` (`cus_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Transaction` (
  `txn_id` INT NOT NULL,
  `txn_amt` DOUBLE NOT NULL,
  `orig_acc` INT NULL DEFAULT NULL,
  `bene_acc` INT NULL DEFAULT NULL,
  `txn_timestamp` DATETIME NULL DEFAULT NULL,
  `txn_type_id` INT NOT NULL,
  PRIMARY KEY (`txn_id`),
  INDEX `fk_Transaction_Transaction_Type1_idx` (`txn_type_id` ASC),
  INDEX `fk_Transaction_Wallet_a` (`orig_acc` ASC),
  INDEX `fk_Transaction_Wallet_b` (`bene_acc` ASC),
  CONSTRAINT `fk_Transaction_Transaction_Type1`
    FOREIGN KEY (`txn_type_id`)
    REFERENCES `Transaction_Type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Transaction_Wallet_b`
    FOREIGN KEY (`bene_acc`)
    REFERENCES `Wallet` (`wallet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Wallet-a`
    FOREIGN KEY (`orig_acc`)
    REFERENCES `Wallet` (`wallet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

