-- -----------------------------------------------------
-- Table `PERSONS`
-- -----------------------------------------------------
ALTER TABLE PERSONS ADD CONSTRAINT PERSONS_pk UNIQUE (PersonID, PersonTypeID);

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
