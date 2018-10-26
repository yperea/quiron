-- MySQL Script generated by MySQL Workbench
-- Fri 26 Oct 2018 11:08:07 AM CDT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema QuironDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS quirondb;

-- -----------------------------------------------------
-- Schema QuironDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS quirondb DEFAULT CHARACTER SET utf8 ;
USE quirondb;

-- -----------------------------------------------------
-- Table `ENTITIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ENTITIES` ;

CREATE TABLE IF NOT EXISTS `ENTITIES` (
  `EntityID` INT NOT NULL AUTO_INCREMENT,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  PRIMARY KEY (`EntityID`))
ENGINE = InnoDB
COMMENT = 'Source of the ID that connects providers and patients with address and contact information.';


-- -----------------------------------------------------
-- Table `PERSON_TYPES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PERSON_TYPES` ;

CREATE TABLE IF NOT EXISTS `PERSON_TYPES` (
  `PersonTypeID` INT NOT NULL AUTO_INCREMENT,
  `PersonTypeName` NVARCHAR(45) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PersonTypeID`))
ENGINE = InnoDB
COMMENT = 'Type of a person.';


-- -----------------------------------------------------
-- Table `PERSONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PERSONS` ;

CREATE TABLE IF NOT EXISTS `PERSONS` (
  `PersonID` INT NOT NULL,
  `PersonTypeID` INT NOT NULL,
  `Title` NVARCHAR(8) NULL COMMENT 'A courtesy title. For example, Mr. or Ms.',
  `FirstName` NVARCHAR(45) NOT NULL COMMENT 'First name of the person.',
  `MiddleName` NVARCHAR(45) NULL COMMENT 'Middle name or middle initial of the person.',
  `LastName` NVARCHAR(45) NOT NULL COMMENT 'Last name of the person.',
  `LastName2` NVARCHAR(45) NULL COMMENT 'Second Last name of the person (If Any).',
  `Suffix` NVARCHAR(10) NULL COMMENT 'Surname suffix. For example, Sr. or Jr.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PersonID`),
  UNIQUE KEY `PERSONS_pk` (`PersonID`,`PersonTypeID`),
  CONSTRAINT `fk_PERSONS_ENTITIES`
  FOREIGN KEY (`PersonID`)
  REFERENCES `ENTITIES` (`EntityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSONS_PERSON_TYPES1`
  FOREIGN KEY (`PersonTypeID`)
  REFERENCES `PERSON_TYPES` (`PersonTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Human beings involved with Quiron Project patients, patients contacts, and providers.';

CREATE INDEX `fk_PERSONS_ENTITIES_idx` ON `PERSONS` (`PersonID` ASC);

CREATE INDEX `fk_PERSONS_PERSON_TYPES1_idx` ON `PERSONS` (`PersonTypeID` ASC);

-- -----------------------------------------------------
-- Table `PROVIDERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PROVIDERS` ;

CREATE TABLE IF NOT EXISTS `PROVIDERS` (
  `ProviderID` INT NOT NULL,
  `NPI` NVARCHAR(45) NOT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`ProviderID`),
  CONSTRAINT `fk_PROVIDERS_PERSONS1`
    FOREIGN KEY (`ProviderID`)
    REFERENCES `PERSONS` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_PROVIDERS_PERSONS1_idx` ON `PROVIDERS` (`ProviderID` ASC);


-- -----------------------------------------------------
-- Table `PATIENTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PATIENTS` ;

CREATE TABLE IF NOT EXISTS `PATIENTS` (
  `PatientID` INT NOT NULL,
  `BirthDate` DATE NULL,
  `Gender` NVARCHAR(1) NULL,
  `CompanyID` INT NULL,
  `SubscriberCode` NVARCHAR(25) NULL,
  `IsPrimarySubscriber` BIT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`PatientID`),
  CONSTRAINT `fk_PATIENTS_PERSONS1`
    FOREIGN KEY (`PatientID`)
    REFERENCES `PERSONS` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ORGANIZATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ORGANIZATIONS` ;

CREATE TABLE IF NOT EXISTS `ORGANIZATIONS` (
  `OrganizationID` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(45) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`OrganizationID`),
  CONSTRAINT `fk_ORGANIZATIONS_ENTITIES1`
    FOREIGN KEY (`OrganizationID`)
    REFERENCES `ENTITIES` (`EntityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LOCATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LOCATIONS` ;

CREATE TABLE IF NOT EXISTS `LOCATIONS` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `EntityID` INT NOT NULL,
  `OrganizationID` INT NULL,
  `Name` NVARCHAR(45) NULL,
  `Description` NVARCHAR(100) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`LocationID`, `EntityID`),
  CONSTRAINT `fk_CARE_SITES_ENTITIES1`
    FOREIGN KEY (`EntityID`)
    REFERENCES `ENTITIES` (`EntityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CARE_SITES_ORGANIZATIONS1`
    FOREIGN KEY (`OrganizationID`)
    REFERENCES `ORGANIZATIONS` (`OrganizationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CARE_SITES_ENTITIES1_idx` ON `LOCATIONS` (`EntityID` ASC);

CREATE INDEX `fk_CARE_SITES_ORGANIZATIONS1_idx` ON `LOCATIONS` (`OrganizationID` ASC);


-- -----------------------------------------------------
-- Table `PROVIDERS_LOCATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PROVIDERS_LOCATIONS` ;

CREATE TABLE IF NOT EXISTS `PROVIDERS_LOCATIONS` (
  `LocationID` INT NOT NULL,
  `ProviderID` INT NOT NULL,
  `WeekDay` NVARCHAR(1) NULL,
  `StartTime` TIME NULL,
  `EndTime` TIME NULL,
  `CreatedDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`LocationID`, `ProviderID`),
  CONSTRAINT `fk_CARE_SITES_has_PROVIDERS_CARE_SITES1`
    FOREIGN KEY (`LocationID`)
    REFERENCES `LOCATIONS` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CARE_SITES_has_PROVIDERS_PROVIDERS1`
    FOREIGN KEY (`ProviderID`)
    REFERENCES `PROVIDERS` (`ProviderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_CARE_SITES_has_PROVIDERS_PROVIDERS1_idx` ON `PROVIDERS_LOCATIONS` (`ProviderID` ASC);

CREATE INDEX `fk_CARE_SITES_has_PROVIDERS_CARE_SITES1_idx` ON `PROVIDERS_LOCATIONS` (`LocationID` ASC);


-- -----------------------------------------------------
-- Table `VISITS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VISITS` ;

CREATE TABLE IF NOT EXISTS `VISITS` (
  `VisitID` INT NOT NULL AUTO_INCREMENT,
  `PatientID` INT NOT NULL,
  `ProviderID` INT NOT NULL,
  `LocationID` INT NOT NULL,
  `ServiceID` INT NOT NULL,
  `ScheduledStartDate` DATETIME NULL,
  `ScheduledEndDate` DATETIME NULL,
  `ActualStartDate` DATETIME NULL,
  `ActualEndDate` DATETIME NULL,
  `PatientBloodPressure` DECIMAL NULL,
  `PatientWeight` DECIMAL NULL,
  `PatientTemperature` DECIMAL NULL,
  `PatientRespiration` DECIMAL NULL,
  `PatientBMI` DECIMAL NULL,
  `PatientHeight` DECIMAL NULL,
  `PatientPulse` DECIMAL NULL,
  `PatientSymptoms` NVARCHAR(512) NULL,
  `ProviderComments` NVARCHAR(512) NULL,
  `CreatedDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`VisitID`),
  CONSTRAINT `fk_VISITS_PATIENTS1`
    FOREIGN KEY (`PatientID`)
    REFERENCES `PATIENTS` (`PatientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VISITS_PROVIDERS_LOCATIONS1`
    FOREIGN KEY (`ProviderID` , `LocationID`)
    REFERENCES `PROVIDERS_LOCATIONS` (`ProviderID` , `LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_VISITS_PATIENTS1_idx` ON `VISITS` (`PatientID` ASC);

CREATE INDEX `fk_VISITS_PROVIDERS_LOCATIONS1_idx` ON `VISITS` (`ProviderID` ASC, `LocationID` ASC);


-- -----------------------------------------------------
-- Table `DIAGNOSIS_CAUSES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DIAGNOSIS_CAUSES` ;

CREATE TABLE IF NOT EXISTS `DIAGNOSIS_CAUSES` (
  `DiagnosisCauseID` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(45) NULL,
  `CommonName` NVARCHAR(45) NULL,
  `Description` NVARCHAR(512) NULL,
  `GeneralInstructions` NVARCHAR(512) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`DiagnosisCauseID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DIAGNOSIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DIAGNOSIS` ;

CREATE TABLE IF NOT EXISTS `DIAGNOSIS` (
  `DiagnosisID` INT NOT NULL AUTO_INCREMENT,
  `VisitID` INT NULL,
  `PatientID` INT NULL,
  `ProviderID` INT NULL,
  `DiagnosisCauseID` INT NULL,
  `Comments` VARCHAR(512) NULL,
  `CreatedDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`DiagnosisID`),
  CONSTRAINT `fk_DIAGNOSIS_VISITS1`
    FOREIGN KEY (`VisitID`)
    REFERENCES `VISITS` (`VisitID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIAGNOSIS_VISITS2`
    FOREIGN KEY (`PatientID`)
    REFERENCES `VISITS` (`PatientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIAGNOSIS_VISITS3`
    FOREIGN KEY (`ProviderID`)
    REFERENCES `VISITS` (`ProviderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIAGNOSIS_DIAGNOSIS_CAUSES1`
    FOREIGN KEY (`DiagnosisCauseID`)
    REFERENCES `DIAGNOSIS_CAUSES` (`DiagnosisCauseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_DIAGNOSIS_VISITS1_idx` ON `DIAGNOSIS` (`VisitID` ASC);

CREATE INDEX `fk_DIAGNOSIS_VISITS2_idx` ON `DIAGNOSIS` (`PatientID` ASC);

CREATE INDEX `fk_DIAGNOSIS_VISITS3_idx` ON `DIAGNOSIS` (`ProviderID` ASC);

CREATE INDEX `fk_DIAGNOSIS_DIAGNOSIS_CAUSES1_idx` ON `DIAGNOSIS` (`DiagnosisCauseID` ASC);


-- -----------------------------------------------------
-- Table `TREATMENTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TREATMENTS` ;

CREATE TABLE IF NOT EXISTS `TREATMENTS` (
  `TreatmentID` INT NOT NULL AUTO_INCREMENT,
  `DiagnosisID` INT NULL,
  `StartDate` DATETIME NULL,
  `Enddate` DATETIME NULL,
  `PatientComments` NVARCHAR(512) NULL,
  `ProviderComments` NVARCHAR(512) NULL,
  `Status` NVARCHAR(2) NULL,
  `Evaluation` TINYINT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`TreatmentID`),
  CONSTRAINT `fk_TREATMENTS_DIAGNOSIS1`
    FOREIGN KEY (`DiagnosisID`)
    REFERENCES `DIAGNOSIS` (`DiagnosisID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_TREATMENTS_DIAGNOSIS1_idx` ON `TREATMENTS` (`DiagnosisID` ASC);


-- -----------------------------------------------------
-- Table `USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USERS` ;

CREATE TABLE IF NOT EXISTS `USERS` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `Username` NVARCHAR(16) NOT NULL,
  `Email` NVARCHAR(255) NULL,
  `Password` NVARCHAR(32) NOT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`UserID`));

CREATE UNIQUE INDEX `Username_UNIQUE` ON `USERS` (`Username` ASC);

CREATE UNIQUE INDEX `Email_UNIQUE` ON `USERS` (`Email` ASC);


-- -----------------------------------------------------
-- Table `PHONE_TYPES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PHONE_TYPES` ;

CREATE TABLE IF NOT EXISTS `PHONE_TYPES` (
  `PhoneTypeID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for telephone number type records.',
  `Name` NVARCHAR(45) NOT NULL COMMENT 'Name of the telephone number type',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PhoneTypeID`))
ENGINE = InnoDB
COMMENT = 'Type of phone number of a person.';


-- -----------------------------------------------------
-- Table `PERSON_PHONES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PERSON_PHONES` ;

CREATE TABLE IF NOT EXISTS `PERSON_PHONES` (
  `PersonID` INT NOT NULL COMMENT 'Entity identification number. Foreign key to PersonID.',
  `Number` NVARCHAR(25) NOT NULL COMMENT 'Telephone number identification number.',
  `PhoneTypeID` INT NOT NULL COMMENT 'Type of phone number. Foreign key to PhoneTypeID.',
  `Extension` NVARCHAR(10) NULL COMMENT 'Extension number.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PersonID`, `Number`, `PhoneTypeID`),
  CONSTRAINT `fk_PERSON_PHONES_PERSONS1`
    FOREIGN KEY (`PersonID`)
    REFERENCES `PERSONS` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_PHONES_PHONE_TYPES1`
    FOREIGN KEY (`PhoneTypeID`)
    REFERENCES `PHONE_TYPES` (`PhoneTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Telephone number and type of a person.';

CREATE INDEX `fk_PERSON_PHONES_PERSONS1_idx` ON `PERSON_PHONES` (`PersonID` ASC);

CREATE INDEX `fk_PERSON_PHONES_PHONE_TYPES1_idx` ON `PERSON_PHONES` (`PhoneTypeID` ASC);


-- -----------------------------------------------------
-- Table `ADDRESS_TYPES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ADDRESS_TYPES` ;

CREATE TABLE IF NOT EXISTS `ADDRESS_TYPES` (
  `AddressTypeID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Address_Type records.',
  `Name` NVARCHAR(45) NOT NULL COMMENT 'Address type description. For example, Billing, Home, or Shipping.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`AddressTypeID`))
ENGINE = InnoDB
COMMENT = 'Types of addresses stored in the Address table. ';


-- -----------------------------------------------------
-- Table `COUNTRIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `COUNTRIES` ;

CREATE TABLE IF NOT EXISTS `COUNTRIES` (
  `CountryID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for countries.',
  `CountryCode` NVARCHAR(3) NOT NULL COMMENT 'ISO standard code for countries.',
  `Name` NVARCHAR(45) NOT NULL COMMENT 'Country name.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was Created.',
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`CountryID`))
ENGINE = InnoDB
COMMENT = 'Lookup table containing the ISO standard codes for countries.';

CREATE UNIQUE INDEX `CountryCode_UNIQUE` ON `COUNTRIES` (`CountryCode` ASC);


-- -----------------------------------------------------
-- Table `STATE_PROVINCES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `STATE_PROVINCES` ;

CREATE TABLE IF NOT EXISTS `STATE_PROVINCES` (
  `StateProvinceID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for State_Provinces records.',
  `StateProvinceCode` NVARCHAR(3) NOT NULL COMMENT 'ISO standard state or province code.',
  `Name` NVARCHAR(45) NOT NULL COMMENT 'State or province description.',
  `CountryID` INT NOT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`StateProvinceID`),
  CONSTRAINT `fk_STATE_PROVINCES_COUNTRIES1`
    FOREIGN KEY (`CountryID`)
    REFERENCES `COUNTRIES` (`CountryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'State and province lookup table.';

CREATE INDEX `fk_STATE_PROVINCES_COUNTRIES1_idx` ON `STATE_PROVINCES` (`CountryID` ASC);


-- -----------------------------------------------------
-- Table `ADDRESSES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ADDRESSES` ;

CREATE TABLE IF NOT EXISTS `ADDRESSES` (
  `AddressID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Address records.',
  `AddressLine1` NVARCHAR(60) NOT NULL COMMENT 'First street address line.',
  `AddressLine2` NVARCHAR(60) NULL COMMENT 'Second street address line.',
  `City` NVARCHAR(45) NOT NULL COMMENT 'Name of the city.',
  `StateProvinceID` INT NOT NULL COMMENT 'Unique identification number for the state or province. Foreign key to STATE_PROVINCES table.',
  `PostalCode` NVARCHAR(15) NULL COMMENT 'Postal code for the street address.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`AddressID`),
  CONSTRAINT `fk_ADDRESSES_STATE_PROVINCES1`
    FOREIGN KEY (`StateProvinceID`)
    REFERENCES `STATE_PROVINCES` (`StateProvinceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Street address information for patiens, providers, care sites and pharmacies.';

CREATE INDEX `fk_ADDRESSES_STATE_PROVINCES1_idx` ON `ADDRESSES` (`StateProvinceID` ASC);


-- -----------------------------------------------------
-- Table `ENTITY_ADDRESSES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ENTITY_ADDRESSES` ;

CREATE TABLE IF NOT EXISTS `ENTITY_ADDRESSES` (
  `EntityID` INT NOT NULL COMMENT 'Primary key. Foreign key to ENTITIES.EntityID.',
  `AddressID` INT NOT NULL COMMENT 'Primary key. Foreign key to ADDRESSES.AddressID.',
  `AddressTypeID` INT NOT NULL COMMENT 'Primary key. Foreign key to ADDRESS_TYPES.AddressTypeID.',
  `CreatedDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  PRIMARY KEY (`EntityID`, `AddressID`, `AddressTypeID`),
  CONSTRAINT `fk_ENTITIES_has_ADDRESSES_ENTITIES1`
    FOREIGN KEY (`EntityID`)
    REFERENCES `ENTITIES` (`EntityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ENTITIES_has_ADDRESSES_ADDRESSES1`
    FOREIGN KEY (`AddressID`)
    REFERENCES `ADDRESSES` (`AddressID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ENTITY_ADDRESSES_ADDRESS_TYPES1`
    FOREIGN KEY (`AddressTypeID`)
    REFERENCES `ADDRESS_TYPES` (`AddressTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Cross-reference table mapping patients, providers, and institutions to their addresses.';

CREATE INDEX `fk_ENTITIES_has_ADDRESSES_ADDRESSES1_idx` ON `ENTITY_ADDRESSES` (`AddressID` ASC);

CREATE INDEX `fk_ENTITIES_has_ADDRESSES_ENTITIES1_idx` ON `ENTITY_ADDRESSES` (`EntityID` ASC);

CREATE INDEX `fk_ENTITY_ADDRESSES_ADDRESS_TYPES1_idx` ON `ENTITY_ADDRESSES` (`AddressTypeID` ASC);


-- -----------------------------------------------------
-- Table `EMAIL_ADDRESSES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EMAIL_ADDRESSES` ;

CREATE TABLE IF NOT EXISTS `EMAIL_ADDRESSES` (
  `EmailAddressID` INT NOT NULL COMMENT 'Primary key. ID of this email address.',
  `PersonID` INT NOT NULL COMMENT 'Person associated with this email address.  Foreign key to PERSONS.PersonID',
  `EmailAddress` NVARCHAR(50) NOT NULL COMMENT 'E-mail address for the person.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`EmailAddressID`),
  CONSTRAINT `fk_EMAIL_ADDRESSES_PERSONS1`
    FOREIGN KEY (`PersonID`)
    REFERENCES `PERSONS` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Where to send a person email.';

CREATE INDEX `fk_EMAIL_ADDRESSES_PERSONS1_idx` ON `EMAIL_ADDRESSES` (`PersonID` ASC);


-- -----------------------------------------------------
-- Table `CARD_TYPES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CARD_TYPES` ;

CREATE TABLE IF NOT EXISTS `CARD_TYPES` (
  `CardTypeID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for credit card type records.',
  `Name` NVARCHAR(45) NOT NULL COMMENT 'Name of credit card type.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  PRIMARY KEY (`CardTypeID`))
ENGINE = InnoDB
COMMENT = 'Type or franchise of credit cards.';


-- -----------------------------------------------------
-- Table `BANKS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BANKS` ;

CREATE TABLE IF NOT EXISTS `BANKS` (
  `BankID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Banks records.',
  `Name` NVARCHAR(45) NOT NULL COMMENT 'Name of the Bank.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`BankID`))
ENGINE = InnoDB
COMMENT = 'Banks issuers of credit cards.';


-- -----------------------------------------------------
-- Table `PERSON_CREDITCARDS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PERSON_CREDITCARDS` ;

CREATE TABLE IF NOT EXISTS `PERSON_CREDITCARDS` (
  `CreditCardID` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key for CreditCard records.',
  `PersonID` INT NOT NULL,
  `CardTypeID` INT NULL COMMENT 'Credit card brand.',
  `CardNumber` NVARCHAR(25) NULL COMMENT 'Credit card number',
  `BankID` INT NULL COMMENT 'Credit card issuer bank.',
  `ExpMonth` TINYINT NULL COMMENT 'Credit card expiration month.',
  `ExpYear` SMALLINT NULL COMMENT 'Credit card expiration year.',
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` DATETIME NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`CreditCardID`, `PersonID`),
  CONSTRAINT `fk_PERSON_CREDITCARDS_PERSONS1`
    FOREIGN KEY (`PersonID`)
    REFERENCES `PERSONS` (`PersonID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_CREDITCARDS_CARD_TYPES1`
    FOREIGN KEY (`CardTypeID`)
    REFERENCES `CARD_TYPES` (`CardTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_CREDITCARDS_BANKS1`
    FOREIGN KEY (`BankID`)
    REFERENCES `BANKS` (`BankID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Person credit card information.';

CREATE INDEX `fk_PERSON_CREDITCARDS_PERSONS1_idx` ON `PERSON_CREDITCARDS` (`PersonID` ASC);

CREATE INDEX `fk_PERSON_CREDITCARDS_CARD_TYPES1_idx` ON `PERSON_CREDITCARDS` (`CardTypeID` ASC);

CREATE INDEX `fk_PERSON_CREDITCARDS_BANKS1_idx` ON `PERSON_CREDITCARDS` (`BankID` ASC);


-- -----------------------------------------------------
-- Table `MEDICATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEDICATIONS` ;

CREATE TABLE IF NOT EXISTS `MEDICATIONS` (
  `MedicationID` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(45) NULL,
  `CommonName` NVARCHAR(45) NULL,
  `Description` NVARCHAR(512) NULL,
  `GeneralInstructions` NVARCHAR(512) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`MedicationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PRESCRIPTIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PRESCRIPTIONS` ;

CREATE TABLE IF NOT EXISTS `PRESCRIPTIONS` (
  `PrescriptionID` INT NOT NULL,
  `DiagnosisID` INT NULL,
  `MedicationID` INT NULL,
  `Instructions` NVARCHAR(512) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`PrescriptionID`),
  CONSTRAINT `fk_PRESCRIPTIONS_DIAGNOSIS1`
    FOREIGN KEY (`DiagnosisID`)
    REFERENCES `DIAGNOSIS` (`DiagnosisID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRESCRIPTIONS_MEDICATIONS1`
    FOREIGN KEY (`MedicationID`)
    REFERENCES `MEDICATIONS` (`MedicationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_PRESCRIPTIONS_DIAGNOSIS1_idx` ON `PRESCRIPTIONS` (`DiagnosisID` ASC);

CREATE INDEX `fk_PRESCRIPTIONS_MEDICATIONS1_idx` ON `PRESCRIPTIONS` (`MedicationID` ASC);


-- -----------------------------------------------------
-- Table `TREATMENTS_PRESCRIPTIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TREATMENTS_PRESCRIPTIONS` ;

CREATE TABLE IF NOT EXISTS `TREATMENTS_PRESCRIPTIONS` (
  `TreatmentID` INT NOT NULL,
  `PrescriptionID` INT NOT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TreatmentID`, `PrescriptionID`),
  CONSTRAINT `fk_TREATMENTS_has_PRESCRIPTIONS_TREATMENTS1`
    FOREIGN KEY (`TreatmentID`)
    REFERENCES `TREATMENTS` (`TreatmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TREATMENTS_has_PRESCRIPTIONS_PRESCRIPTIONS1`
    FOREIGN KEY (`PrescriptionID`)
    REFERENCES `PRESCRIPTIONS` (`PrescriptionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_TREATMENTS_has_PRESCRIPTIONS_PRESCRIPTIONS1_idx` ON `TREATMENTS_PRESCRIPTIONS` (`PrescriptionID` ASC);

CREATE INDEX `fk_TREATMENTS_has_PRESCRIPTIONS_TREATMENTS1_idx` ON `TREATMENTS_PRESCRIPTIONS` (`TreatmentID` ASC);


-- -----------------------------------------------------
-- Table `ROLES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ROLES` ;

CREATE TABLE IF NOT EXISTS `ROLES` (
  `RoleID` INT NOT NULL AUTO_INCREMENT,
  `RoleName` VARCHAR(15) NOT NULL,
  `Description` VARCHAR(512) NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` DATETIME NULL,
  PRIMARY KEY (`RoleID`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `RoleName_UNIQUE` ON `ROLES` (`RoleName` ASC);


-- -----------------------------------------------------
-- Table `USERS_ROLES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `USERS_ROLES` ;

CREATE TABLE IF NOT EXISTS `USERS_ROLES` (
  `UserID` INT NOT NULL,
  `RoleID` INT NOT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`, `RoleID`),
  CONSTRAINT `fk_ROLES_has_USERS_ROLES1`
    FOREIGN KEY (`RoleID`)
    REFERENCES `ROLES` (`RoleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ROLES_has_USERS_USERS1`
    FOREIGN KEY (`UserID`)
    REFERENCES `USERS` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_ROLES_has_USERS_USERS1_idx` ON `USERS_ROLES` (`UserID` ASC);

CREATE INDEX `fk_ROLES_has_USERS_ROLES1_idx` ON `USERS_ROLES` (`RoleID` ASC);


-- -----------------------------------------------------
-- Table `PERSON_USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PERSON_USERS` ;

CREATE TABLE IF NOT EXISTS `PERSON_USERS` (
  `PersonID` INT NOT NULL,
  `PersonTypeID` INT NOT NULL,
  `UserID` INT NOT NULL,
  `CreatedDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PersonID`, `PersonTypeID`, `UserID`),
  CONSTRAINT `fk_USERS_has_PERSONS_USERS1`
    FOREIGN KEY (`UserID`)
    REFERENCES `USERS` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_USERS_PERSONS1`
    FOREIGN KEY (`PersonID` , `PersonTypeID`)
    REFERENCES `PERSONS` (`PersonID` , `PersonTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_USERS_has_PERSONS_USERS1_idx` ON `PERSON_USERS` (`UserID` ASC);

CREATE INDEX `fk_PERSON_USERS_PERSONS1_idx` ON `PERSON_USERS` (`PersonID` ASC, `PersonTypeID` ASC);

-- -----------------------------------------------------
-- View `V_USERS_ROLES`
-- -----------------------------------------------------
USE quirondb;
CREATE  OR REPLACE VIEW `V_USERS_ROLES` AS
    SELECT 
        U.Username AS Username, R.RoleName AS RoleName
    FROM
        USERS_ROLES UR
            INNER JOIN
        USERS U ON UR.UserID = U.UserID
            INNER JOIN
        ROLES R ON UR.RoleID = R.RoleID;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

GRANT SELECT ON USERS to 'tomcat'@'localhost';
GRANT SELECT ON ROLES to 'tomcat'@'localhost';
GRANT SELECT ON USERS_ROLES to 'tomcat'@'localhost';
GRANT SELECT ON V_USERS_ROLES to 'tomcat'@'localhost';


DELETE FROM PERSON_USERS;
DELETE FROM USERS_ROLES;
DELETE FROM PERSONS;
ALTER TABLE PERSONS AUTO_INCREMENT = 1;
DELETE FROM ENTITIES;
ALTER TABLE ENTITIES AUTO_INCREMENT = 1;
DELETE FROM PERSON_TYPES;
ALTER TABLE PERSON_TYPES AUTO_INCREMENT = 1;
DELETE FROM STATE_PROVINCES;
ALTER TABLE STATE_PROVINCES AUTO_INCREMENT = 1;
DELETE FROM COUNTRIES;
ALTER TABLE COUNTRIES AUTO_INCREMENT = 1;
DELETE FROM ADDRESS_TYPES;
ALTER TABLE ADDRESS_TYPES AUTO_INCREMENT = 1;
DELETE FROM USERS;
ALTER TABLE USERS AUTO_INCREMENT = 1;
DELETE FROM ROLES;
ALTER TABLE ROLES AUTO_INCREMENT = 1;
INSERT INTO ROLES (RoleName, Description) VALUES('Administrator', 'System administrator');
INSERT INTO ROLES (RoleName, Description) VALUES('User', 'System user');
INSERT INTO USERS(Username, Email, Password ) VALUES ('yesper', 'yesper@aol.com', '1234');
INSERT INTO USERS(Username, Email, Password ) VALUES ('clacar', 'clacar@aol.com', '1234');
INSERT INTO USERS_ROLES(UserID, RoleID) VALUES (1,1);
INSERT INTO USERS_ROLES(UserID, RoleID) VALUES (2,2);
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO PERSON_TYPES(PersonTypeName) VALUES("Employee");
INSERT INTO PERSON_TYPES(PersonTypeName) VALUES("Provider");
INSERT INTO PERSON_TYPES(PersonTypeName) VALUES("Patient");
INSERT INTO PERSONS (PersonID, PersonTypeID, Title, FirstName, MiddleName, LastName, LastName2, Suffix) VALUES(1, 1, null, 'Yesid', null, 'Perea', 'Martinez', null);
INSERT INTO PERSONS (PersonID, PersonTypeID, Title, FirstName, MiddleName, LastName, LastName2, Suffix) VALUES(2, 3, null, 'Claudia', 'Patricia', 'Carabali', 'Chacon', null);
INSERT INTO PERSON_USERS (PersonID, PersonTypeID, UserID) VALUES (1, 1, 1);
INSERT INTO PERSON_USERS (PersonID, PersonTypeID, UserID) VALUES (2, 3, 2);
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('US', 'United States');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('CO', 'Colombia');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('CA', 'Canada');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('ES', 'Spain');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('FR', 'France');
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('AK', 'Alaska', 1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('AL', 'Alabama', 1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('AR', 'Arkansas', 1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('AZ', 'Arizona', 1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('CA', 'California', 1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('WI', 'Wisconsin', 1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('AB', 'Alberta', 3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('BC', 'British Columbia', 3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('LB', 'Labrador', 3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('MB', 'Manitoba', 3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES ('NB', 'Brunswick', 3);
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Home');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Billing');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Office');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Primary');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Shipping');
