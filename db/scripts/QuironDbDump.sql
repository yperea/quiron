-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quirondb
-- ------------------------------------------------------
-- Server version	5.7.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ADDRESSES`
--

DROP TABLE IF EXISTS `ADDRESSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESSES` (
  `AddressID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Address records.',
  `AddressLine1` varchar(60) NOT NULL COMMENT 'First street address line.',
  `AddressLine2` varchar(60) DEFAULT NULL COMMENT 'Second street address line.',
  `City` varchar(45) NOT NULL COMMENT 'Name of the city.',
  `StateProvinceID` int(11) NOT NULL COMMENT 'Unique identification number for the state or province. Foreign key to STATE_PROVINCES table.',
  `PostalCode` varchar(15) DEFAULT NULL COMMENT 'Postal code for the street address.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`AddressID`),
  KEY `fk_ADDRESSES_STATE_PROVINCES1_idx` (`StateProvinceID`),
  CONSTRAINT `fk_ADDRESSES_STATE_PROVINCES1` FOREIGN KEY (`StateProvinceID`) REFERENCES `STATE_PROVINCES` (`StateProvinceID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Street address information for patiens, providers, care sites and pharmacies.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDRESSES`
--

LOCK TABLES `ADDRESSES` WRITE;
/*!40000 ALTER TABLE `ADDRESSES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ADDRESSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ADDRESS_TYPES`
--

DROP TABLE IF EXISTS `ADDRESS_TYPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESS_TYPES` (
  `AddressTypeID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Address_Type records.',
  `Name` varchar(45) NOT NULL COMMENT 'Address type description. For example, Billing, Home, or Shipping.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`AddressTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Types of addresses stored in the Address table. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDRESS_TYPES`
--

LOCK TABLES `ADDRESS_TYPES` WRITE;
/*!40000 ALTER TABLE `ADDRESS_TYPES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ADDRESS_TYPES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BANKS`
--

DROP TABLE IF EXISTS `BANKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BANKS` (
  `BankID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for Banks records.',
  `Name` varchar(45) NOT NULL COMMENT 'Name of the Bank.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`BankID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Banks issuers of credit cards.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BANKS`
--

LOCK TABLES `BANKS` WRITE;
/*!40000 ALTER TABLE `BANKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `BANKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CARD_TYPES`
--

DROP TABLE IF EXISTS `CARD_TYPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CARD_TYPES` (
  `CardTypeID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for credit card type records.',
  `Name` varchar(45) NOT NULL COMMENT 'Name of credit card type.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  PRIMARY KEY (`CardTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Type or franchise of credit cards.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CARD_TYPES`
--

LOCK TABLES `CARD_TYPES` WRITE;
/*!40000 ALTER TABLE `CARD_TYPES` DISABLE KEYS */;
/*!40000 ALTER TABLE `CARD_TYPES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COUNTRIES`
--

DROP TABLE IF EXISTS `COUNTRIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COUNTRIES` (
  `CountryID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for countries.',
  `CountryCode` varchar(3) NOT NULL COMMENT 'ISO standard code for countries.',
  `Name` varchar(45) NOT NULL COMMENT 'Country name.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was Created.',
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CountryID`),
  UNIQUE KEY `CountryCode_UNIQUE` (`CountryCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Lookup table containing the ISO standard codes for countries.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COUNTRIES`
--

LOCK TABLES `COUNTRIES` WRITE;
/*!40000 ALTER TABLE `COUNTRIES` DISABLE KEYS */;
INSERT INTO `COUNTRIES` VALUES (1,'AD','Andorra','2018-10-01 18:04:41',NULL);
/*!40000 ALTER TABLE `COUNTRIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DIAGNOSIS`
--

DROP TABLE IF EXISTS `DIAGNOSIS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DIAGNOSIS` (
  `DiagnosisID` int(11) NOT NULL AUTO_INCREMENT,
  `VisitID` int(11) DEFAULT NULL,
  `PatientID` int(11) DEFAULT NULL,
  `ProviderID` int(11) DEFAULT NULL,
  `DiagnosisCauseID` int(11) DEFAULT NULL,
  `Comments` varchar(512) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`DiagnosisID`),
  KEY `fk_DIAGNOSIS_VISITS1_idx` (`VisitID`),
  KEY `fk_DIAGNOSIS_VISITS2_idx` (`PatientID`),
  KEY `fk_DIAGNOSIS_VISITS3_idx` (`ProviderID`),
  KEY `fk_DIAGNOSIS_DIAGNOSIS_CAUSES1_idx` (`DiagnosisCauseID`),
  CONSTRAINT `fk_DIAGNOSIS_DIAGNOSIS_CAUSES1` FOREIGN KEY (`DiagnosisCauseID`) REFERENCES `DIAGNOSIS_CAUSES` (`DiagnosisCauseID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIAGNOSIS_VISITS1` FOREIGN KEY (`VisitID`) REFERENCES `VISITS` (`VisitID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIAGNOSIS_VISITS2` FOREIGN KEY (`PatientID`) REFERENCES `VISITS` (`PatientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIAGNOSIS_VISITS3` FOREIGN KEY (`ProviderID`) REFERENCES `VISITS` (`ProviderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIAGNOSIS`
--

LOCK TABLES `DIAGNOSIS` WRITE;
/*!40000 ALTER TABLE `DIAGNOSIS` DISABLE KEYS */;
/*!40000 ALTER TABLE `DIAGNOSIS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DIAGNOSIS_CAUSES`
--

DROP TABLE IF EXISTS `DIAGNOSIS_CAUSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DIAGNOSIS_CAUSES` (
  `DiagnosisCauseID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `CommonName` varchar(45) DEFAULT NULL,
  `Description` varchar(512) DEFAULT NULL,
  `GeneralInstructions` varchar(512) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`DiagnosisCauseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIAGNOSIS_CAUSES`
--

LOCK TABLES `DIAGNOSIS_CAUSES` WRITE;
/*!40000 ALTER TABLE `DIAGNOSIS_CAUSES` DISABLE KEYS */;
/*!40000 ALTER TABLE `DIAGNOSIS_CAUSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMAIL_ADDRESSES`
--

DROP TABLE IF EXISTS `EMAIL_ADDRESSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMAIL_ADDRESSES` (
  `EmailAddressID` int(11) NOT NULL COMMENT 'Primary key. ID of this email address.',
  `PersonID` int(11) NOT NULL COMMENT 'Person associated with this email address.  Foreign key to PERSONS.PersonID',
  `EmailAddress` varchar(50) NOT NULL COMMENT 'E-mail address for the person.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`EmailAddressID`),
  KEY `fk_EMAIL_ADDRESSES_PERSONS1_idx` (`PersonID`),
  CONSTRAINT `fk_EMAIL_ADDRESSES_PERSONS1` FOREIGN KEY (`PersonID`) REFERENCES `PERSONS` (`PersonID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Where to send a person email.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMAIL_ADDRESSES`
--

LOCK TABLES `EMAIL_ADDRESSES` WRITE;
/*!40000 ALTER TABLE `EMAIL_ADDRESSES` DISABLE KEYS */;
/*!40000 ALTER TABLE `EMAIL_ADDRESSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENTITIES`
--

DROP TABLE IF EXISTS `ENTITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENTITIES` (
  `EntityID` int(11) NOT NULL AUTO_INCREMENT,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  PRIMARY KEY (`EntityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Source of the ID that connects providers and patients with address and contact information.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENTITIES`
--

LOCK TABLES `ENTITIES` WRITE;
/*!40000 ALTER TABLE `ENTITIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENTITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENTITY_ADDRESSES`
--

DROP TABLE IF EXISTS `ENTITY_ADDRESSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENTITY_ADDRESSES` (
  `EntityID` int(11) NOT NULL COMMENT 'Primary key. Foreign key to ENTITIES.EntityID.',
  `AddressID` int(11) NOT NULL COMMENT 'Primary key. Foreign key to ADDRESSES.AddressID.',
  `AddressTypeID` int(11) NOT NULL COMMENT 'Primary key. Foreign key to ADDRESS_TYPES.AddressTypeID.',
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  PRIMARY KEY (`EntityID`,`AddressID`,`AddressTypeID`),
  KEY `fk_ENTITIES_has_ADDRESSES_ADDRESSES1_idx` (`AddressID`),
  KEY `fk_ENTITIES_has_ADDRESSES_ENTITIES1_idx` (`EntityID`),
  KEY `fk_ENTITY_ADDRESSES_ADDRESS_TYPES1_idx` (`AddressTypeID`),
  CONSTRAINT `fk_ENTITIES_has_ADDRESSES_ADDRESSES1` FOREIGN KEY (`AddressID`) REFERENCES `ADDRESSES` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ENTITIES_has_ADDRESSES_ENTITIES1` FOREIGN KEY (`EntityID`) REFERENCES `ENTITIES` (`EntityID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ENTITY_ADDRESSES_ADDRESS_TYPES1` FOREIGN KEY (`AddressTypeID`) REFERENCES `ADDRESS_TYPES` (`AddressTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cross-reference table mapping patients, providers, and institutions to their addresses.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENTITY_ADDRESSES`
--

LOCK TABLES `ENTITY_ADDRESSES` WRITE;
/*!40000 ALTER TABLE `ENTITY_ADDRESSES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENTITY_ADDRESSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOCATIONS`
--

DROP TABLE IF EXISTS `LOCATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOCATIONS` (
  `LocationID` int(11) NOT NULL AUTO_INCREMENT,
  `EntityID` int(11) NOT NULL,
  `OrganizationID` int(11) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`LocationID`,`EntityID`),
  KEY `fk_CARE_SITES_ENTITIES1_idx` (`EntityID`),
  KEY `fk_CARE_SITES_ORGANIZATIONS1_idx` (`OrganizationID`),
  CONSTRAINT `fk_CARE_SITES_ENTITIES1` FOREIGN KEY (`EntityID`) REFERENCES `ENTITIES` (`EntityID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CARE_SITES_ORGANIZATIONS1` FOREIGN KEY (`OrganizationID`) REFERENCES `ORGANIZATIONS` (`OrganizationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOCATIONS`
--

LOCK TABLES `LOCATIONS` WRITE;
/*!40000 ALTER TABLE `LOCATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `LOCATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEDICATIONS`
--

DROP TABLE IF EXISTS `MEDICATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEDICATIONS` (
  `MedicationID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `CommonName` varchar(45) DEFAULT NULL,
  `Description` varchar(512) DEFAULT NULL,
  `GeneralInstructions` varchar(512) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`MedicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEDICATIONS`
--

LOCK TABLES `MEDICATIONS` WRITE;
/*!40000 ALTER TABLE `MEDICATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `MEDICATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORGANIZATIONS`
--

DROP TABLE IF EXISTS `ORGANIZATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORGANIZATIONS` (
  `OrganizationID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`OrganizationID`),
  CONSTRAINT `fk_ORGANIZATIONS_ENTITIES1` FOREIGN KEY (`OrganizationID`) REFERENCES `ENTITIES` (`EntityID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORGANIZATIONS`
--

LOCK TABLES `ORGANIZATIONS` WRITE;
/*!40000 ALTER TABLE `ORGANIZATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ORGANIZATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PATIENTS`
--

DROP TABLE IF EXISTS `PATIENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PATIENTS` (
  `PatientID` int(11) NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `Gender` varchar(1) DEFAULT NULL,
  `CompanyID` int(11) DEFAULT NULL,
  `SubscriberCode` varchar(25) DEFAULT NULL,
  `IsPrimarySubscriber` bit(1) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`PatientID`),
  CONSTRAINT `fk_PATIENTS_PERSONS1` FOREIGN KEY (`PatientID`) REFERENCES `PERSONS` (`PersonID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PATIENTS`
--

LOCK TABLES `PATIENTS` WRITE;
/*!40000 ALTER TABLE `PATIENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PATIENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSONS`
--

DROP TABLE IF EXISTS `PERSONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSONS` (
  `PersonID` int(11) NOT NULL,
  `PersonTypeID` int(11) NOT NULL,
  `Title` varchar(8) NOT NULL COMMENT 'A courtesy title. For example, Mr. or Ms.',
  `FirstName` varchar(45) NOT NULL COMMENT 'First name of the person.',
  `MiddleName` varchar(45) DEFAULT NULL COMMENT 'Middle name or middle initial of the person.',
  `LastName` varchar(45) NOT NULL COMMENT 'Last name of the person.',
  `LastName2` varchar(45) DEFAULT NULL COMMENT 'Second Last name of the person (If Any).',
  `Suffix` varchar(10) DEFAULT NULL COMMENT 'Surname suffix. For example, Sr. or Jr.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PersonID`),
  KEY `fk_PERSONS_ENTITIES_idx` (`PersonID`),
  KEY `fk_PERSONS_PERSON_TYPES1_idx` (`PersonTypeID`),
  CONSTRAINT `fk_PERSONS_ENTITIES` FOREIGN KEY (`PersonID`) REFERENCES `ENTITIES` (`EntityID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSONS_PERSON_TYPES1` FOREIGN KEY (`PersonTypeID`) REFERENCES `PERSON_TYPES` (`PersonTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Human beings involved with Quiron Project patients, patients contacts, and providers.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSONS`
--

LOCK TABLES `PERSONS` WRITE;
/*!40000 ALTER TABLE `PERSONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERSONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON_CREDITCARDS`
--

DROP TABLE IF EXISTS `PERSON_CREDITCARDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSON_CREDITCARDS` (
  `CreditCardID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for CreditCard records.',
  `PersonID` int(11) NOT NULL,
  `CardTypeID` int(11) DEFAULT NULL COMMENT 'Credit card brand.',
  `CardNumber` varchar(25) DEFAULT NULL COMMENT 'Credit card number',
  `BankID` int(11) DEFAULT NULL COMMENT 'Credit card issuer bank.',
  `ExpMonth` tinyint(4) DEFAULT NULL COMMENT 'Credit card expiration month.',
  `ExpYear` smallint(6) DEFAULT NULL COMMENT 'Credit card expiration year.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`CreditCardID`,`PersonID`),
  KEY `fk_PERSON_CREDITCARDS_PERSONS1_idx` (`PersonID`),
  KEY `fk_PERSON_CREDITCARDS_CARD_TYPES1_idx` (`CardTypeID`),
  KEY `fk_PERSON_CREDITCARDS_BANKS1_idx` (`BankID`),
  CONSTRAINT `fk_PERSON_CREDITCARDS_BANKS1` FOREIGN KEY (`BankID`) REFERENCES `BANKS` (`BankID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_CREDITCARDS_CARD_TYPES1` FOREIGN KEY (`CardTypeID`) REFERENCES `CARD_TYPES` (`CardTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_CREDITCARDS_PERSONS1` FOREIGN KEY (`PersonID`) REFERENCES `PERSONS` (`PersonID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Person credit card information.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON_CREDITCARDS`
--

LOCK TABLES `PERSON_CREDITCARDS` WRITE;
/*!40000 ALTER TABLE `PERSON_CREDITCARDS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERSON_CREDITCARDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON_PHONES`
--

DROP TABLE IF EXISTS `PERSON_PHONES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSON_PHONES` (
  `PersonID` int(11) NOT NULL COMMENT 'Entity identification number. Foreign key to PersonID.',
  `Number` varchar(25) NOT NULL COMMENT 'Telephone number identification number.',
  `PhoneTypeID` int(11) NOT NULL COMMENT 'Type of phone number. Foreign key to PhoneTypeID.',
  `Extension` varchar(10) DEFAULT NULL COMMENT 'Extension number.',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PersonID`,`Number`,`PhoneTypeID`),
  KEY `fk_PERSON_PHONES_PERSONS1_idx` (`PersonID`),
  KEY `fk_PERSON_PHONES_PHONE_TYPES1_idx` (`PhoneTypeID`),
  CONSTRAINT `fk_PERSON_PHONES_PERSONS1` FOREIGN KEY (`PersonID`) REFERENCES `PERSONS` (`PersonID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSON_PHONES_PHONE_TYPES1` FOREIGN KEY (`PhoneTypeID`) REFERENCES `PHONE_TYPES` (`PhoneTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Telephone number and type of a person.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON_PHONES`
--

LOCK TABLES `PERSON_PHONES` WRITE;
/*!40000 ALTER TABLE `PERSON_PHONES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERSON_PHONES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON_TYPES`
--

DROP TABLE IF EXISTS `PERSON_TYPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSON_TYPES` (
  `PersonTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `PersonTypeName` varchar(45) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PersonTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Type of a person.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON_TYPES`
--

LOCK TABLES `PERSON_TYPES` WRITE;
/*!40000 ALTER TABLE `PERSON_TYPES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERSON_TYPES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHONE_TYPES`
--

DROP TABLE IF EXISTS `PHONE_TYPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PHONE_TYPES` (
  `PhoneTypeID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for telephone number type records.',
  `Name` varchar(45) NOT NULL COMMENT 'Name of the telephone number type',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`PhoneTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Type of phone number of a person.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHONE_TYPES`
--

LOCK TABLES `PHONE_TYPES` WRITE;
/*!40000 ALTER TABLE `PHONE_TYPES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PHONE_TYPES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRESCRIPTIONS`
--

DROP TABLE IF EXISTS `PRESCRIPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRESCRIPTIONS` (
  `PrescriptionID` int(11) NOT NULL,
  `DiagnosisID` int(11) DEFAULT NULL,
  `MedicationID` int(11) DEFAULT NULL,
  `Instructions` varchar(512) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`PrescriptionID`),
  KEY `fk_PRESCRIPTIONS_DIAGNOSIS1_idx` (`DiagnosisID`),
  KEY `fk_PRESCRIPTIONS_MEDICATIONS1_idx` (`MedicationID`),
  CONSTRAINT `fk_PRESCRIPTIONS_DIAGNOSIS1` FOREIGN KEY (`DiagnosisID`) REFERENCES `DIAGNOSIS` (`DiagnosisID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRESCRIPTIONS_MEDICATIONS1` FOREIGN KEY (`MedicationID`) REFERENCES `MEDICATIONS` (`MedicationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRESCRIPTIONS`
--

LOCK TABLES `PRESCRIPTIONS` WRITE;
/*!40000 ALTER TABLE `PRESCRIPTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRESCRIPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROVIDERS`
--

DROP TABLE IF EXISTS `PROVIDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROVIDERS` (
  `ProviderID` int(11) NOT NULL,
  `NPI` varchar(45) NOT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ProviderID`),
  KEY `fk_PROVIDERS_PERSONS1_idx` (`ProviderID`),
  CONSTRAINT `fk_PROVIDERS_PERSONS1` FOREIGN KEY (`ProviderID`) REFERENCES `PERSONS` (`PersonID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROVIDERS`
--

LOCK TABLES `PROVIDERS` WRITE;
/*!40000 ALTER TABLE `PROVIDERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROVIDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROVIDERS_LOCATIONS`
--

DROP TABLE IF EXISTS `PROVIDERS_LOCATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROVIDERS_LOCATIONS` (
  `LocationID` int(11) NOT NULL,
  `ProviderID` int(11) NOT NULL,
  `WeekDay` varchar(1) DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`LocationID`,`ProviderID`),
  KEY `fk_CARE_SITES_has_PROVIDERS_PROVIDERS1_idx` (`ProviderID`),
  KEY `fk_CARE_SITES_has_PROVIDERS_CARE_SITES1_idx` (`LocationID`),
  CONSTRAINT `fk_CARE_SITES_has_PROVIDERS_CARE_SITES1` FOREIGN KEY (`LocationID`) REFERENCES `LOCATIONS` (`LocationID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CARE_SITES_has_PROVIDERS_PROVIDERS1` FOREIGN KEY (`ProviderID`) REFERENCES `PROVIDERS` (`ProviderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROVIDERS_LOCATIONS`
--

LOCK TABLES `PROVIDERS_LOCATIONS` WRITE;
/*!40000 ALTER TABLE `PROVIDERS_LOCATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROVIDERS_LOCATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATE_PROVINCES`
--

DROP TABLE IF EXISTS `STATE_PROVINCES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATE_PROVINCES` (
  `StateProvinceID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key for State_Provinces records.',
  `StateProvinceCode` varchar(3) NOT NULL COMMENT 'ISO standard state or province code.',
  `Name` varchar(45) NOT NULL COMMENT 'State or province description.',
  `CountryID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time the record was created.',
  `ModifiedDate` datetime DEFAULT NULL COMMENT 'Date and time the record was last updated.',
  PRIMARY KEY (`StateProvinceID`),
  KEY `fk_STATE_PROVINCES_COUNTRIES1_idx` (`CountryID`),
  CONSTRAINT `fk_STATE_PROVINCES_COUNTRIES1` FOREIGN KEY (`CountryID`) REFERENCES `COUNTRIES` (`CountryID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='State and province lookup table.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATE_PROVINCES`
--

LOCK TABLES `STATE_PROVINCES` WRITE;
/*!40000 ALTER TABLE `STATE_PROVINCES` DISABLE KEYS */;
/*!40000 ALTER TABLE `STATE_PROVINCES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TREATMENTS`
--

DROP TABLE IF EXISTS `TREATMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TREATMENTS` (
  `TreatmentID` int(11) NOT NULL AUTO_INCREMENT,
  `DiagnosisID` int(11) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `Enddate` datetime DEFAULT NULL,
  `PatientComments` varchar(512) DEFAULT NULL,
  `ProviderComments` varchar(512) DEFAULT NULL,
  `Status` varchar(2) DEFAULT NULL,
  `Evaluation` tinyint(4) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`TreatmentID`),
  KEY `fk_TREATMENTS_DIAGNOSIS1_idx` (`DiagnosisID`),
  CONSTRAINT `fk_TREATMENTS_DIAGNOSIS1` FOREIGN KEY (`DiagnosisID`) REFERENCES `DIAGNOSIS` (`DiagnosisID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TREATMENTS`
--

LOCK TABLES `TREATMENTS` WRITE;
/*!40000 ALTER TABLE `TREATMENTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TREATMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TREATMENTS_PRESCRIPTIONS`
--

DROP TABLE IF EXISTS `TREATMENTS_PRESCRIPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TREATMENTS_PRESCRIPTIONS` (
  `TreatmentID` int(11) NOT NULL,
  `PrescriptionID` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TreatmentID`,`PrescriptionID`),
  KEY `fk_TREATMENTS_has_PRESCRIPTIONS_PRESCRIPTIONS1_idx` (`PrescriptionID`),
  KEY `fk_TREATMENTS_has_PRESCRIPTIONS_TREATMENTS1_idx` (`TreatmentID`),
  CONSTRAINT `fk_TREATMENTS_has_PRESCRIPTIONS_PRESCRIPTIONS1` FOREIGN KEY (`PrescriptionID`) REFERENCES `PRESCRIPTIONS` (`PrescriptionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TREATMENTS_has_PRESCRIPTIONS_TREATMENTS1` FOREIGN KEY (`TreatmentID`) REFERENCES `TREATMENTS` (`TreatmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TREATMENTS_PRESCRIPTIONS`
--

LOCK TABLES `TREATMENTS_PRESCRIPTIONS` WRITE;
/*!40000 ALTER TABLE `TREATMENTS_PRESCRIPTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TREATMENTS_PRESCRIPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(16) NOT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(32) NOT NULL,
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VISITS`
--

DROP TABLE IF EXISTS `VISITS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VISITS` (
  `VisitID` int(11) NOT NULL AUTO_INCREMENT,
  `PatientID` int(11) NOT NULL,
  `ProviderID` int(11) NOT NULL,
  `SiteID` int(11) NOT NULL,
  `ServiceID` int(11) NOT NULL,
  `ScheduledStartDate` datetime DEFAULT NULL,
  `ScheduledEndDate` datetime DEFAULT NULL,
  `ActualStartDate` datetime DEFAULT NULL,
  `ActualEndDate` datetime DEFAULT NULL,
  `PatientBloodPressure` decimal(10,0) DEFAULT NULL,
  `PatientWeight` decimal(10,0) DEFAULT NULL,
  `PatientTemperature` decimal(10,0) DEFAULT NULL,
  `PatientRespiration` decimal(10,0) DEFAULT NULL,
  `PatientBMI` decimal(10,0) DEFAULT NULL,
  `PatientHeight` decimal(10,0) DEFAULT NULL,
  `PatientPulse` decimal(10,0) DEFAULT NULL,
  `PatientSymptoms` varchar(512) DEFAULT NULL,
  `ProviderComments` varchar(512) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`VisitID`),
  KEY `fk_VISITS_PATIENTS1_idx` (`PatientID`),
  KEY `fk_VISITS_PROVIDERS1_idx` (`ProviderID`),
  CONSTRAINT `fk_VISITS_PATIENTS1` FOREIGN KEY (`PatientID`) REFERENCES `PATIENTS` (`PatientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_VISITS_PROVIDERS1` FOREIGN KEY (`ProviderID`) REFERENCES `PROVIDERS` (`ProviderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VISITS`
--

LOCK TABLES `VISITS` WRITE;
/*!40000 ALTER TABLE `VISITS` DISABLE KEYS */;
/*!40000 ALTER TABLE `VISITS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-01 18:19:39
