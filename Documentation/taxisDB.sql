SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `TaxisDB` DEFAULT CHARACTER SET utf8 ;
USE `TaxisDB` ;
delimiter $$

CREATE TABLE `Contact` (
  `ContactID` int(11) NOT NULL AUTO_INCREMENT,
  `Phone` varchar(15) NOT NULL,
  `Fax` varchar(15) DEFAULT NULL,
  `Cell` varchar(15) NOT NULL,
  `Email` varchar(100) NOT NULL,
  PRIMARY KEY (`ContactID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8$$


delimiter $$

delimiter $$

CREATE TABLE `E1` (
  `TaxpayerID` int(11) NOT NULL,
  `Year` int(4) NOT NULL,
  `TaxpayerAddress` varchar(500) NOT NULL,
  `ATID` varchar(10) NOT NULL,
  `isComplete` int(1) DEFAULT NULL,
  `E1InfoDataID` int(11) DEFAULT '0',
  `E1TaxableIncomeID` int(11) DEFAULT '0',
  `idE1ReduceTax` int(11) DEFAULT '0',
  `idE1ObjectiveSpending` int(11) DEFAULT '0',
  `idE1IncomesReduceTaxes` int(11) DEFAULT '0',
  `idE1ExpensesRemovedFromTotalIncome` int(11) DEFAULT '0',
  `idE1PrepaidTaxes` int(11) DEFAULT '0',
  `idE1PersonDataBorneTaxpayer` int(11) DEFAULT '0',
  `idE1DataFromTaxPayerFolder` int(11) DEFAULT '0',
  `idE1TaxPayerBankAccount` int(11) DEFAULT '0',
  `DateInserted` datetime DEFAULT NULL,
  PRIMARY KEY (`TaxpayerID`,`Year`),
  UNIQUE KEY `idE1TaxPayerBankAccount_UNIQUE` (`idE1TaxPayerBankAccount`),
  UNIQUE KEY `idE1DataFromTaxPayerFolder_UNIQUE` (`idE1DataFromTaxPayerFolder`),
  UNIQUE KEY `idE1PersonDataBorneTaxpayer_UNIQUE` (`idE1PersonDataBorneTaxpayer`),
  UNIQUE KEY `idE1PrepaidTaxes_UNIQUE` (`idE1PrepaidTaxes`),
  UNIQUE KEY `idE1ExpensesRemovedFromTotalIncome_UNIQUE` (`idE1ExpensesRemovedFromTotalIncome`),
  UNIQUE KEY `idE1IncomesReduceTaxes_UNIQUE` (`idE1IncomesReduceTaxes`),
  UNIQUE KEY `idE1ObjectiveSpending_UNIQUE` (`idE1ObjectiveSpending`),
  UNIQUE KEY `idE1ReduceTax_UNIQUE` (`idE1ReduceTax`),
  UNIQUE KEY `E1InfoDataID_UNIQUE` (`E1InfoDataID`),
  UNIQUE KEY `E1TaxableIncomeID_UNIQUE` (`E1TaxableIncomeID`),
  KEY `fk_E1_Taxpayer_idx` (`TaxpayerID`),
  KEY `fk_E1_ExpensesRemovedFromTotalIncome_idx` (`idE1ExpensesRemovedFromTotalIncome`),
  KEY `fk_E1_IncomesreduceTaxes_idx` (`idE1IncomesReduceTaxes`),
  KEY `fk_E1_DataFromTaxPayerFolder_idx` (`idE1DataFromTaxPayerFolder`),
  KEY `fk_E1_InfoData_idx` (`E1InfoDataID`),
  KEY `fk_E1_ObjectiveSpending_idx` (`idE1ObjectiveSpending`),
  KEY `fk_E1_PersonDataBorneTaxPayer_idx` (`idE1PersonDataBorneTaxpayer`),
  KEY `fk_E1_PrepaidTaxes_idx` (`idE1PrepaidTaxes`),
  KEY `fk_E1_ReduceTax_idx` (`idE1ReduceTax`),
  KEY `fk_E1_TaxPayerBankAccount_idx` (`idE1TaxPayerBankAccount`),
  KEY `fk_E1_TaxableIncomes_idx` (`E1TaxableIncomeID`),
  CONSTRAINT `fk_E1_DataFromTaxPayerFolder` FOREIGN KEY (`idE1DataFromTaxPayerFolder`) REFERENCES `E1DataFromTaxPayerFolder` (`idE1DataFromTaxPayerFolder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_ExpensesRemovedFromTotalIncome` FOREIGN KEY (`idE1ExpensesRemovedFromTotalIncome`) REFERENCES `E1ExpensesRemovedFromTotalIncome` (`idE1ExpensesRemovedFromTotalIncome`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_IncomesReduceTaxes` FOREIGN KEY (`idE1IncomesReduceTaxes`) REFERENCES `E1IncomesReduceTaxes` (`idE1IncomesReduceTaxes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_InfoData` FOREIGN KEY (`E1InfoDataID`) REFERENCES `E1InfoData` (`E1InfoDataID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_ObjectiveSpending` FOREIGN KEY (`idE1ObjectiveSpending`) REFERENCES `E1ObjectiveSpending` (`idE1ObjectiveSpending`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_PersonDataBorneTaxPayer` FOREIGN KEY (`idE1PersonDataBorneTaxpayer`) REFERENCES `E1PersonDataBorneTaxpayer` (`idE1PersonDataBorneTaxpayer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_PrepaidTaxes` FOREIGN KEY (`idE1PrepaidTaxes`) REFERENCES `E1PrepaidTaxes` (`idE1PrepaidTaxes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_ReduceTax` FOREIGN KEY (`idE1ReduceTax`) REFERENCES `E1ReduceTax` (`idE1ReduceTax`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_TaxableIncomes` FOREIGN KEY (`E1TaxableIncomeID`) REFERENCES `E1TaxableIncomes` (`E1TaxableIncome`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_TaxPayerBankAccount` FOREIGN KEY (`idE1TaxPayerBankAccount`) REFERENCES `E1TaxPayerBankAccount` (`idE1TaxPayerBankAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_Taxpayer` FOREIGN KEY (`TaxpayerID`) REFERENCES `Taxpayer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$




delimiter $$

CREATE TABLE `E1DataFromTaxPayerFolder` (
  `idE1DataFromTaxPayerFolder` int(11) NOT NULL AUTO_INCREMENT,
  `901` float DEFAULT NULL,
  `902` float DEFAULT NULL,
  `903` float DEFAULT NULL,
  `904` float DEFAULT NULL,
  `341` float DEFAULT NULL,
  `342` float DEFAULT NULL,
  `745` float DEFAULT NULL,
  `746` float DEFAULT NULL,
  `995` float DEFAULT NULL,
  `996` float DEFAULT NULL,
  `997` float DEFAULT NULL,
  `998` float DEFAULT NULL,
  `959` float DEFAULT NULL,
  `960` float DEFAULT NULL,
  `743` float DEFAULT NULL,
  `744` float DEFAULT NULL,
  `323` float DEFAULT NULL,
  `324` float DEFAULT NULL,
  `399` float DEFAULT NULL,
  `400` float DEFAULT NULL,
  `907` float DEFAULT NULL,
  `908` float DEFAULT NULL,
  `985` float DEFAULT NULL,
  `986` float DEFAULT NULL,
  `987` float DEFAULT NULL,
  `988` float DEFAULT NULL,
  `989` float DEFAULT NULL,
  `990` float DEFAULT NULL,
  PRIMARY KEY (`idE1DataFromTaxPayerFolder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1ExpensesRemovedFromTotalIncome` (
  `idE1ExpensesRemovedFromTotalIncome` int(11) NOT NULL AUTO_INCREMENT,
  `049` float DEFAULT NULL,
  `051` float DEFAULT NULL,
  `052` float DEFAULT NULL,
  `053` float DEFAULT NULL,
  `054` float DEFAULT NULL,
  `057` float DEFAULT NULL,
  `058` float DEFAULT NULL,
  `031` float DEFAULT NULL,
  `032` float DEFAULT NULL,
  `059` float DEFAULT NULL,
  `060` float DEFAULT NULL,
  `075` float DEFAULT NULL,
  `076` float DEFAULT NULL,
  `061` float DEFAULT NULL,
  `062` float DEFAULT NULL,
  `071` float DEFAULT NULL,
  `063` float DEFAULT NULL,
  `064` float DEFAULT NULL,
  `069` float DEFAULT NULL,
  `070` float DEFAULT NULL,
  `RentalForFamilyOwnerName1` varchar(45) DEFAULT NULL,
  `801` varchar(10) DEFAULT NULL,
  `092` int(1) DEFAULT NULL,
  `091` float DEFAULT NULL,
  `097` int(2) DEFAULT NULL,
  `811` float DEFAULT NULL,
  `812` float DEFAULT NULL,
  `RentalForFamilyOwnerName2` varchar(45) DEFAULT NULL,
  `802` varchar(10) DEFAULT NULL,
  `094` int(1) DEFAULT NULL,
  `093` float DEFAULT NULL,
  `098` int(2) DEFAULT NULL,
  `813` float DEFAULT NULL,
  `814` float DEFAULT NULL,
  `RentalForFamilyOwnerName3` varchar(45) DEFAULT NULL,
  `803` varchar(10) DEFAULT NULL,
  `096` int(1) DEFAULT NULL,
  `095` float DEFAULT NULL,
  `099` float DEFAULT NULL,
  `815` float DEFAULT NULL,
  `816` float DEFAULT NULL,
  `RentalForStudyOwnerName1` varchar(45) DEFAULT NULL,
  `804` varchar(10) DEFAULT NULL,
  `817` float DEFAULT NULL,
  `RentalForStudyOwnerName2` varchar(45) DEFAULT NULL,
  `805` varchar(10) DEFAULT NULL,
  `819` float DEFAULT NULL,
  `RentalForStudyOwnerName3` varchar(45) DEFAULT NULL,
  `806` varchar(10) DEFAULT NULL,
  `821` float DEFAULT NULL,
  `RentalForStudyOwnerName4` varchar(45) DEFAULT NULL,
  `807` varchar(10) DEFAULT NULL,
  `823` float DEFAULT NULL,
  `073` float DEFAULT NULL,
  `074` float DEFAULT NULL,
  `089` float DEFAULT NULL,
  `090` float DEFAULT NULL,
  `087` float DEFAULT NULL,
  `088` float DEFAULT NULL,
  `079` float DEFAULT NULL,
  `080` float DEFAULT NULL,
  `084` float DEFAULT NULL,
  `085` float DEFAULT NULL,
  `077` float DEFAULT NULL,
  `078` float DEFAULT NULL,
  `663` float DEFAULT NULL,
  `664` float DEFAULT NULL,
  `033` int(1) DEFAULT '0',
  `034` int(1) DEFAULT '0',
  `035` int(1) DEFAULT '0',
  `036` int(1) DEFAULT '0',
  PRIMARY KEY (`idE1ExpensesRemovedFromTotalIncome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1IncomeFromAgricularCompanyData` (
  `idE1IncomeFromAgricularCompanyID` int(11) NOT NULL AUTO_INCREMENT,
  `E1TaxableIncomeID` int(11) DEFAULT NULL,
  `Location` varchar(150) DEFAULT NULL,
  `ProdKind` int(1) DEFAULT NULL COMMENT '1: Tillage\n2: Stockraising\n3: Other',
  `Population` float DEFAULT NULL COMMENT 'Στρέμματα,ζώα ...',
  `LocationType` int(1) DEFAULT NULL COMMENT '1: Ορεινό\n2: Ημιορεινό\n3: Πεδινό',
  `HasWater` int(1) DEFAULT NULL,
  `NetIncome` float DEFAULT NULL,
  PRIMARY KEY (`idE1IncomeFromAgricularCompanyID`),
  KEY `fk_E1IncomeFromAgricularCompanyData_1_idx` (`E1TaxableIncomeID`),
  CONSTRAINT `fk_E1IncomeFromAgricularCompanyData_1` FOREIGN KEY (`E1TaxableIncomeID`) REFERENCES `E1TaxableIncomes` (`E1TaxableIncome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1IncomesReduceTaxes` (
  `idE1IncomesReduceTaxes` int(11) NOT NULL AUTO_INCREMENT,
  `655` float DEFAULT NULL,
  `656` float DEFAULT NULL,
  `693` float DEFAULT NULL,
  `694` float DEFAULT NULL,
  `659` float DEFAULT NULL,
  `660` float DEFAULT NULL,
  `657` float DEFAULT NULL,
  `658` float DEFAULT NULL,
  `661` float DEFAULT NULL,
  `662` float DEFAULT NULL,
  `431` float DEFAULT NULL,
  `432` float DEFAULT NULL,
  `433` float DEFAULT NULL,
  `434` float DEFAULT NULL,
  `305` float DEFAULT NULL,
  `306` float DEFAULT NULL,
  `477` float DEFAULT NULL,
  `478` float DEFAULT NULL,
  `LessorName1` varchar(45) DEFAULT NULL,
  `790` varchar(10) DEFAULT NULL,
  `795` float DEFAULT NULL,
  `LassorName2` varchar(45) DEFAULT NULL,
  `791` varchar(10) DEFAULT NULL,
  `796` float DEFAULT NULL,
  `793` float DEFAULT NULL,
  `794` float DEFAULT NULL,
  `615` float DEFAULT NULL,
  `616` varchar(45) DEFAULT NULL,
  `LassorNameForStudent` varchar(45) DEFAULT NULL,
  `417` varchar(10) DEFAULT NULL,
  `AreaStudentHouse` float DEFAULT NULL,
  `419` float DEFAULT NULL,
  `420` float DEFAULT NULL,
  `735` float DEFAULT NULL,
  `736` float DEFAULT NULL,
  `781` float DEFAULT NULL,
  `782` float DEFAULT NULL,
  `783` float DEFAULT NULL,
  `784` float DEFAULT NULL,
  `787` float DEFAULT NULL,
  `788` float DEFAULT NULL,
  PRIMARY KEY (`idE1IncomesReduceTaxes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1InfoData` (
  `E1InfoDataID` int(11) NOT NULL AUTO_INCREMENT,
  `327` int(1) DEFAULT NULL,
  `328` int(1) DEFAULT NULL,
  `319` int(1) DEFAULT NULL,
  `320` int(1) DEFAULT NULL,
  `023` int(1) DEFAULT NULL,
  `024` int(1) DEFAULT NULL,
  `329` int(1) DEFAULT NULL,
  `330` int(1) DEFAULT NULL,
  `331` int(1) DEFAULT NULL,
  `011` int(1) DEFAULT NULL,
  `012` int(1) DEFAULT NULL,
  `013` int(1) DEFAULT NULL,
  `014` int(1) DEFAULT NULL,
  `015` int(1) DEFAULT NULL,
  `016` int(1) DEFAULT NULL,
  `017` int(1) DEFAULT NULL,
  `018` int(1) DEFAULT NULL,
  `021` int(1) DEFAULT NULL,
  `022` int(1) DEFAULT NULL,
  `025` int(1) DEFAULT NULL,
  `026` int(1) DEFAULT NULL,
  `007` int(1) DEFAULT NULL,
  `008` int(1) DEFAULT NULL,
  `617` int(1) DEFAULT NULL,
  `385` int(1) DEFAULT NULL,
  `386` int(1) DEFAULT NULL,
  `029` int(1) DEFAULT NULL,
  `030` int(1) DEFAULT NULL,
  `905` int(1) DEFAULT NULL,
  `906` int(1) DEFAULT NULL,
  `911` int(1) DEFAULT NULL,
  `912` int(1) DEFAULT NULL,
  `010` int(1) DEFAULT NULL,
  PRIMARY KEY (`E1InfoDataID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1ObjectiveSpending` (
  `idE1ObjectiveSpending` int(11) NOT NULL AUTO_INCREMENT,
  `205` varchar(45) DEFAULT NULL,
  `HouseAddr1` varchar(200) DEFAULT NULL,
  `203` int(1) DEFAULT NULL,
  `240` int(1) DEFAULT NULL,
  `211` float DEFAULT NULL,
  `212` float DEFAULT NULL,
  `213` float DEFAULT NULL,
  `214` float DEFAULT NULL,
  `215` int(2) DEFAULT NULL,
  `216` float DEFAULT NULL,
  `HouseAddr2` varchar(200) DEFAULT NULL,
  `207` int(1) DEFAULT NULL,
  `241` int(1) DEFAULT NULL,
  `218` float DEFAULT NULL,
  `219` float DEFAULT NULL,
  `220` float DEFAULT NULL,
  `221` float DEFAULT NULL,
  `222` int(2) DEFAULT NULL,
  `223` float DEFAULT NULL,
  `HouseAddr3` varchar(200) DEFAULT NULL,
  `209` int(1) DEFAULT NULL,
  `242` int(1) DEFAULT NULL,
  `225` float DEFAULT NULL,
  `226` float DEFAULT NULL,
  `227` float DEFAULT NULL,
  `228` float DEFAULT NULL,
  `229` int(2) DEFAULT NULL,
  `230` float DEFAULT NULL,
  `707` float DEFAULT NULL,
  `708` float DEFAULT NULL,
  `AFM1` varchar(10) DEFAULT NULL,
  `750` varchar(7) DEFAULT NULL,
  `703` int(4) DEFAULT NULL,
  `761` int(2) DEFAULT NULL,
  `771` float DEFAULT NULL,
  `775` int(4) DEFAULT NULL,
  `AFM2` varchar(10) DEFAULT NULL,
  `751` varchar(7) DEFAULT NULL,
  `704` int(4) DEFAULT NULL,
  `762` int(2) DEFAULT NULL,
  `772` float DEFAULT NULL,
  `776` int(4) DEFAULT NULL,
  `AFM3` varchar(10) DEFAULT NULL,
  `752` varchar(7) DEFAULT NULL,
  `705` int(4) DEFAULT NULL,
  `763` int(2) DEFAULT NULL,
  `773` float DEFAULT NULL,
  `777` int(4) DEFAULT NULL,
  `AFM4` varchar(10) DEFAULT NULL,
  `753` varchar(7) DEFAULT NULL,
  `706` int(4) DEFAULT NULL,
  `764` int(2) DEFAULT NULL,
  `774` float DEFAULT NULL,
  `778` int(4) DEFAULT NULL,
  `851` float DEFAULT NULL,
  `852` float DEFAULT NULL,
  `853` float DEFAULT NULL,
  `854` float DEFAULT NULL,
  `855` float DEFAULT NULL,
  `856` float DEFAULT NULL,
  `857` float DEFAULT NULL,
  `858` float DEFAULT NULL,
  `ShippingName1` varchar(45) DEFAULT NULL,
  `ShippingRegister1` varchar(100) DEFAULT NULL,
  `ShippingCountry1` varchar(45) DEFAULT NULL,
  `SailShip1` int(1) DEFAULT NULL,
  `AccommodationSpace1` int(1) DEFAULT NULL,
  `PercentPrincipalCoOwner1` float DEFAULT NULL,
  `PercentWifeCoOwner1` float DEFAULT NULL,
  `FirstRegister1` int(4) DEFAULT NULL,
  `OwnershipMonths1` int(2) DEFAULT NULL,
  `747` float DEFAULT NULL,
  `ShippingName2` varchar(45) DEFAULT NULL,
  `ShippingRegister2` varchar(100) DEFAULT NULL,
  `ShippingCountry2` varchar(45) DEFAULT NULL,
  `SailShip2` int(1) DEFAULT NULL,
  `AccommodationSpace2` int(1) DEFAULT NULL,
  `PercentPrincipalCoOwner2` float DEFAULT NULL,
  `PercentWifeCoOwner2` float DEFAULT NULL,
  `FirstRegister2` int(4) DEFAULT NULL,
  `OwnershipMonths2` int(2) DEFAULT NULL,
  `748` float DEFAULT NULL,
  `711` float DEFAULT NULL,
  `712` float DEFAULT NULL,
  `713` float DEFAULT NULL,
  `714` float DEFAULT NULL,
  `731` float DEFAULT NULL,
  `732` float DEFAULT NULL,
  `AircraftRegisterData` varchar(100) DEFAULT NULL,
  `AircraftType` varchar(45) DEFAULT NULL,
  `AircraftSN` varchar(45) DEFAULT NULL,
  `AirportBase` varchar(45) DEFAULT NULL,
  `AircraftOwnerShip` int(2) DEFAULT NULL,
  `AircraftPowerLibres` int(11) DEFAULT NULL,
  `AircraftFirstRegister` int(4) DEFAULT NULL,
  `715` float DEFAULT NULL,
  `716` float DEFAULT NULL,
  `767` float DEFAULT NULL,
  `PoolPrincipalCoOwnerOutdoor` float DEFAULT NULL,
  `PoolWifeCoOwnerOutdoor` float DEFAULT NULL,
  `768` float DEFAULT NULL,
  `PoolPrincipalCoOwnerIndoor` float DEFAULT NULL,
  `PoolWifeCoOwnerIndoor` float DEFAULT NULL,
  `765` float DEFAULT NULL,
  `766` float DEFAULT NULL,
  `769` float DEFAULT NULL,
  `770` float DEFAULT NULL,
  `719` float DEFAULT NULL,
  `720` float DEFAULT NULL,
  `721` float DEFAULT NULL,
  `722` float DEFAULT NULL,
  `723` float DEFAULT NULL,
  `724` float DEFAULT NULL,
  `725` float DEFAULT NULL,
  `726` float DEFAULT NULL,
  `727` float DEFAULT NULL,
  `728` float DEFAULT NULL,
  PRIMARY KEY (`idE1ObjectiveSpending`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1PersonDataBorneTaxpayer` (
  `idE1PersonDataBorneTaxpayer` int(11) NOT NULL AUTO_INCREMENT,
  `Name1` varchar(45) DEFAULT NULL,
  `BirthYear1` int(4) DEFAULT NULL,
  `SchoolName1` varchar(100) DEFAULT NULL,
  `831` varchar(10) DEFAULT NULL,
  `Name2` varchar(45) DEFAULT NULL,
  `BirthYear2` int(4) DEFAULT NULL,
  `SchoolName2` varchar(100) DEFAULT NULL,
  `832` varchar(10) DEFAULT NULL,
  `Name3` varchar(45) DEFAULT NULL,
  `BirthYear3` int(4) DEFAULT NULL,
  `SchoolName3` varchar(100) DEFAULT NULL,
  `833` varchar(10) DEFAULT NULL,
  `Name4` varchar(45) DEFAULT NULL,
  `BirthYear4` int(4) DEFAULT NULL,
  `SchoolName4` varchar(100) DEFAULT NULL,
  `834` varchar(10) DEFAULT NULL,
  `FullName1` varchar(45) DEFAULT NULL,
  `835` varchar(10) DEFAULT NULL,
  `RelationshipWithPrincipal1` varchar(45) DEFAULT NULL,
  `RelationShipWithWife1` varchar(45) DEFAULT NULL,
  `FullName2` varchar(45) DEFAULT NULL,
  `836` varchar(10) DEFAULT NULL,
  `RelationshipWithPrincipal2` varchar(45) DEFAULT NULL,
  `RelationShipWithWife2` varchar(45) DEFAULT NULL,
  `FullName3` varchar(45) DEFAULT NULL,
  `837` varchar(45) DEFAULT NULL,
  `RelationshipWithPrincipal3` varchar(45) DEFAULT NULL,
  `RelationShipWithWife3` varchar(45) DEFAULT NULL,
  `FullName4` varchar(45) DEFAULT NULL,
  `838` varchar(10) DEFAULT NULL,
  `RelationshipWithPrincipal4` varchar(45) DEFAULT NULL,
  `RelationShipWithWife4` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idE1PersonDataBorneTaxpayer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1PrepaidTaxes` (
  `idE1PrepaidTaxes` int(11) NOT NULL AUTO_INCREMENT,
  `601` float DEFAULT NULL,
  `602` float DEFAULT NULL,
  `603` float DEFAULT NULL,
  `604` float DEFAULT NULL,
  `605` float DEFAULT NULL,
  `606` float DEFAULT NULL,
  `607` float DEFAULT NULL,
  `608` float DEFAULT NULL,
  `609` float DEFAULT NULL,
  `610` float DEFAULT NULL,
  `651` float DEFAULT NULL,
  `652` float DEFAULT NULL,
  `293` float DEFAULT NULL,
  `294` float DEFAULT NULL,
  `313` float DEFAULT NULL,
  `314` float DEFAULT NULL,
  `315` float DEFAULT NULL,
  `316` float DEFAULT NULL,
  `297` float DEFAULT NULL,
  `298` float DEFAULT NULL,
  `127` float DEFAULT NULL,
  `128` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idE1PrepaidTaxes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1ReduceTax` (
  `idE1ReduceTax` int(11) NOT NULL AUTO_INCREMENT,
  `001` int(1) DEFAULT NULL,
  `002` int(1) DEFAULT NULL,
  `003` int(11) DEFAULT NULL,
  `004` int(11) DEFAULT NULL,
  `005` int(11) DEFAULT NULL,
  `006` int(11) DEFAULT NULL,
  PRIMARY KEY (`idE1ReduceTax`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE  `TaxisDB`.`E1RelatePersons` (
  `TaxpayerID` int(11) NOT NULL,
  `Year` int(4) NOT NULL,
  `idRelatePerson` int(11) NOT NULL,
  KEY `fk_E1RelatePersons_1_idx` (`idRelatePerson`),
  KEY `fk_E1RelatePersons_E1_idx` (`TaxpayerID`,`Year`),
  CONSTRAINT `fk_E1RelatePersons_RelatePerson` FOREIGN KEY (`idRelatePerson`) REFERENCES `RelatePerson` (`idRelatePerson`),
  CONSTRAINT `fk_E1RelatePersons_E1` FOREIGN KEY (`TaxpayerID`, `Year`) REFERENCES `E1` (`TaxpayerID`, `Year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

delimiter $$

CREATE TABLE `E1TaxableIncomes` (
  `E1TaxableIncome` int(11) NOT NULL AUTO_INCREMENT,
  `301` float DEFAULT NULL,
  `302` float DEFAULT NULL,
  `303` float DEFAULT NULL,
  `304` float DEFAULT NULL,
  `321` float DEFAULT NULL,
  `322` float DEFAULT NULL,
  `317` float DEFAULT NULL,
  `318` float DEFAULT NULL,
  `461` float DEFAULT NULL,
  `462` float DEFAULT NULL,
  `921` float DEFAULT NULL,
  `922` float DEFAULT NULL,
  `919` float DEFAULT NULL,
  `920` float DEFAULT NULL,
  `915` float DEFAULT NULL,
  `916` float DEFAULT NULL,
  `923` float DEFAULT NULL,
  `924` float DEFAULT NULL,
  `335` float DEFAULT NULL,
  `336` float DEFAULT NULL,
  `326` float DEFAULT NULL,
  `337` float DEFAULT NULL,
  `338` float DEFAULT NULL,
  `339` float DEFAULT NULL,
  `340` float DEFAULT NULL,
  `465` float DEFAULT NULL,
  `466` float DEFAULT NULL,
  `467` float DEFAULT NULL,
  `468` float DEFAULT NULL,
  `475` float DEFAULT NULL,
  `476` float DEFAULT NULL,
  `479` float DEFAULT NULL,
  `480` float DEFAULT NULL,
  `481` float DEFAULT NULL,
  `482` float DEFAULT NULL,
  `401` float DEFAULT NULL,
  `402` float DEFAULT NULL,
  `403` float DEFAULT NULL,
  `404` float DEFAULT NULL,
  `405` float DEFAULT NULL,
  `406` float DEFAULT NULL,
  `407` float DEFAULT NULL,
  `408` float DEFAULT NULL,
  `413` float DEFAULT NULL,
  `414` float DEFAULT NULL,
  `415` float DEFAULT NULL,
  `416` float DEFAULT NULL,
  `425` float DEFAULT NULL,
  `426` float DEFAULT NULL,
  `501` float DEFAULT NULL,
  `502` float DEFAULT NULL,
  `503` float DEFAULT NULL,
  `504` float DEFAULT NULL,
  `505` float DEFAULT NULL,
  `506` float DEFAULT NULL,
  `507` float DEFAULT NULL,
  `508` float DEFAULT NULL,
  `511` float DEFAULT NULL,
  `512` float DEFAULT NULL,
  `515` float DEFAULT NULL,
  `516` float DEFAULT NULL,
  `517` float DEFAULT NULL,
  `518` float DEFAULT NULL,
  `103` float DEFAULT NULL,
  `104` float DEFAULT NULL,
  `121` float DEFAULT NULL,
  `122` float DEFAULT NULL,
  `105` float DEFAULT NULL,
  `106` float DEFAULT NULL,
  `107` float DEFAULT NULL,
  `108` float DEFAULT NULL,
  `109` float DEFAULT NULL,
  `110` float DEFAULT NULL,
  `101` float DEFAULT NULL,
  `102` float DEFAULT NULL,
  `909` float DEFAULT NULL,
  `910` float DEFAULT NULL,
  `111` float DEFAULT NULL,
  `112` float DEFAULT NULL,
  `113` float DEFAULT NULL,
  `114` float DEFAULT NULL,
  `129` float DEFAULT NULL,
  `130` float DEFAULT NULL,
  `143` float DEFAULT NULL,
  `144` float DEFAULT NULL,
  `145` float DEFAULT NULL,
  `146` float DEFAULT NULL,
  `147` float DEFAULT NULL,
  `148` float DEFAULT NULL,
  `141` float DEFAULT NULL,
  `142` float DEFAULT NULL,
  `701` float DEFAULT NULL,
  `702` float DEFAULT NULL,
  `123` float DEFAULT NULL,
  `124` float DEFAULT NULL,
  `125` float DEFAULT NULL,
  `126` float DEFAULT NULL,
  `151` float DEFAULT NULL,
  `152` float DEFAULT NULL,
  `163` float DEFAULT NULL,
  `164` float DEFAULT NULL,
  `165` float DEFAULT NULL,
  `166` float DEFAULT NULL,
  `159` float DEFAULT NULL,
  `160` float DEFAULT NULL,
  `175` float DEFAULT NULL,
  `176` float DEFAULT NULL,
  `131` float DEFAULT NULL,
  `132` float DEFAULT NULL,
  `133` float DEFAULT NULL,
  `134` float DEFAULT NULL,
  `741` float DEFAULT NULL,
  `742` float DEFAULT NULL,
  `291` float DEFAULT NULL,
  `292` float DEFAULT NULL,
  `389` float DEFAULT NULL,
  `390` float DEFAULT NULL,
  `391` float DEFAULT NULL,
  `392` float DEFAULT NULL,
  `463` float DEFAULT NULL,
  `464` float DEFAULT NULL,
  `471` float DEFAULT NULL,
  `472` float DEFAULT NULL,
  `411` float DEFAULT NULL,
  `412` float DEFAULT NULL,
  `421` float DEFAULT NULL,
  `422` float DEFAULT NULL,
  `509` float DEFAULT NULL,
  `510` float DEFAULT NULL,
  `513` float DEFAULT NULL,
  `514` float DEFAULT NULL,
  `295` float DEFAULT NULL,
  `296` float DEFAULT NULL,
  `171` float DEFAULT NULL,
  `172` float DEFAULT NULL,
  `173` float DEFAULT NULL,
  `174` float DEFAULT NULL,
  `395` float DEFAULT NULL,
  `396` float DEFAULT NULL,
  PRIMARY KEY (`E1TaxableIncome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E1TaxPayerBankAccount` (
  `idE1TaxPayerBankAccount` int(11) NOT NULL AUTO_INCREMENT,
  `BIC` varchar(45) DEFAULT NULL,
  `IBAN` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idE1TaxPayerBankAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E2` (
  `TaxierID` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `isComplete` int(1) DEFAULT '0',
  `DateInserted` datetime DEFAULT NULL,
  PRIMARY KEY (`TaxierID`,`Year`),
  CONSTRAINT `FK_Taxpayer_E2` FOREIGN KEY (`TaxierID`) REFERENCES `Taxpayer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E2coOwner` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EstateID` int(11) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `AFM` varchar(10) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Percent` float NOT NULL,
  `Rent` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_E2Estate_E2EstateCoOwner_idx` (`EstateID`),
  CONSTRAINT `FK_E2Estate_E2EstateCoOwner` FOREIGN KEY (`EstateID`) REFERENCES `E2Estate` (`EstateID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8$$


delimiter $$

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
  KEY `FK_E2_Estate_idx` (`TaxierID`,`Year`),
  CONSTRAINT `FK_E2_Estate` FOREIGN KEY (`TaxierID`, `Year`) REFERENCES `E2` (`TaxierID`, `Year`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8$$


delimiter $$

CREATE TABLE `E2OtherEstate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TaxpayerID` int(11) NOT NULL,
  `Year` int(11) NOT NULL,
  `Title` varchar(2048) NOT NULL,
  `Location` varchar(200) NOT NULL,
  `Position` varchar(100) NOT NULL,
  `EstateUsage` varchar(50) NOT NULL,
  `Area` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_E2_OtherEstate_idx` (`TaxpayerID`,`Year`),
  CONSTRAINT `FK_E2_OtherEstate` FOREIGN KEY (`TaxpayerID`, `Year`) REFERENCES `E2` (`TaxierID`, `Year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


delimiter $$

delimiter $$

CREATE TABLE `RelatePerson` (
  `idRelatePerson` int(11) NOT NULL,
  `Type` int(1) DEFAULT NULL COMMENT '1: WIFE\n2: DELEGATE',
  `Address` varchar(500) DEFAULT NULL,
  `AFM` varchar(45) DEFAULT NULL,
  `ContactID` int(11) DEFAULT NULL,
  `FName` varchar(45) DEFAULT NULL,
  `LName` varchar(45) DEFAULT NULL,
  `FatherName` varchar(10) DEFAULT NULL,
  `ATID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idRelatePerson`),
  KEY `fk_RelatePerson_Address_idx` (`Address`),
  KEY `fk_RelatePerson_1_idx` (`ContactID`),
  CONSTRAINT `fk_RelatePerson_1` FOREIGN KEY (`ContactID`) REFERENCES `Contact` (`ContactID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$




delimiter $$

delimiter $$

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
  KEY `fk_taxPayer_Contact_idx` (`Contact`),
  KEY `fk_taxPayer_User_idx` (`UserID`),
  CONSTRAINT `fk_taxPayer_Contact` FOREIGN KEY (`Contact`) REFERENCES `Contact` (`ContactID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_taxPayer_User` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4$$






delimiter $$

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(21844) NOT NULL,
  `Active` int(11) NOT NULL DEFAULT '0',
  `OTP` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8$$




USE `TaxisDB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
