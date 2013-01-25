delimiter $$

CREATE DATABASE `TaxisDB` /*!40100 DEFAULT CHARACTER SET utf8 */$$
delimiter $$

CREATE TABLE `Contact` (
  `ContactID` int(11) NOT NULL AUTO_INCREMENT,
  `Phone` varchar(15) NOT NULL,
  `Fax` varchar(15) DEFAULT NULL,
  `Cell` varchar(15) NOT NULL,
  `Email` varchar(100) NOT NULL,
  PRIMARY KEY (`ContactID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(2048) NOT NULL,
  `Active` int(11) NOT NULL,
  `OTP` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `Taxpayer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `AFM` varchar(10) NOT NULL,
  `FName` varchar(50) NOT NULL,
  `LName` varchar(50) NOT NULL,
  `FatherName` varchar(50) NOT NULL,
  `Contact` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `AFM_UNIQUE` (`AFM`),
  KEY `fk_taxPayer_Contact` (`Contact`),
  KEY `fk_taxPayer_User` (`UserID`),
  CONSTRAINT `fk_taxPayer_Contact` FOREIGN KEY (`Contact`) REFERENCES `Contact` (`ContactID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_taxPayer_User` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `E2` (
  `TaxierID` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `isComplete` int(1) DEFAULT '0',
  PRIMARY KEY (`TaxierID`,`Year`),
  CONSTRAINT `FK_Taxpayer_E2` FOREIGN KEY (`TaxierID`) REFERENCES `Taxpayer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$



CREATE TABLE `E2Estate` (
  `EstateID` int(11) NOT NULL AUTO_INCREMENT,
  `TaxierID` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `Location` varchar(200) NOT NULL,
  `Position` varchar(100) NOT NULL,
  `EstateUsage` varchar(50) NOT NULL,
  `Area` float NOT NULL,
  `TenantFullNAme` varchar(50) NOT NULL,
  `TenantAFM` varchar(10) NOT NULL,
  `FromMonth` int(2) NOT NULL,
  `ToMonth` int(2) NOT NULL,
  `MonthlyRental` float NOT NULL,
  `RersentCoOwner` float NOT NULL,
  `RevenueFreeHome` float NOT NULL,
  `RevenueFreeOffice` float NOT NULL,
  `RevenuePrivateHotel` float NOT NULL,
  `RevenuePrivateOffice` float NOT NULL,
  PRIMARY KEY (`EstateID`),
  KEY `FK_E2_Estate` (`TaxierID`,`Year`),
  CONSTRAINT `FK_E2_Estate` FOREIGN KEY (`TaxierID`, `Year`) REFERENCES `E2` (`TaxierID`, `Year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE  `TaxisDB`.`E2OtherEstate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TaxpayerID` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `Title` varchar(2048) NOT NULL,
  `Location` varchar(200) NOT NULL,
  `Position` varchar(100) NOT NULL,
  `EstateUsage` varchar(50) NOT NULL,
  `Area` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_E2_OtherEstate` (`TaxpayerID`,`Year`),
  CONSTRAINT `FK_E2_OtherEstate` FOREIGN KEY (`TaxpayerID`, `Year`) REFERENCES `E2` (`TaxierID`, `Year`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

CREATE  TABLE `TaxisDB`.`E2coOwner` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `EstateID` INT(11) NOT NULL ,
  `FullName` VARCHAR(50) NOT NULL ,
  `AFM` VARCHAR(10) NOT NULL ,
  `Address` VARCHAR(100) NOT NULL ,
  `Percent` FLOAT NOT NULL ,
  `Rent` FLOAT NULL DEFAULT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_E2Estate_E2EstateCoOwner` (`EstateID` ASC) ,
  CONSTRAINT `FK_E2Estate_E2EstateCoOwner`
    FOREIGN KEY (`EstateID` )
    REFERENCES `TaxisDB`.`E2Estate` (`EstateID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8