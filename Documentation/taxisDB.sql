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

CREATE TABLE `E1` (
  `TaxpayerID` int(11) NOT NULL,
  `Year` int(4) NOT NULL,
  `TaxpayerAddress` varchar(500) NOT NULL,
  `ATID` varchar(10) NOT NULL,
  `isComplete` int(1) DEFAULT NULL,
  `E1InfoDataID` int(11) DEFAULT NULL,
  `E1TaxableIncomeID` int(11) DEFAULT NULL,
  `idE1ReduceTax` int(11) DEFAULT NULL,
  `idE1ObjectiveSpending` int(11) DEFAULT NULL,
  `idE1IncomesReduceTaxes` int(11) DEFAULT NULL,
  `idE1ExpensesRemovedFromTotalIncome` int(11) DEFAULT NULL,
  `idE1PrepaidTaxes` int(11) DEFAULT NULL,
  `idE1PersonDataBorneTaxpayer` int(11) DEFAULT NULL,
  `idE1DataFromTaxPayerFolder` int(11) DEFAULT NULL,
  `idE1TaxPayerBankAccount` int(11) DEFAULT NULL,
  `DateInserted` datetime DEFAULT NULL,
  `DOY` varchar(45) NOT NULL,
  `Marriage` int(1) DEFAULT '0',
  `idE1NauticalIncomes` int(11) DEFAULT NULL,
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
  UNIQUE KEY `idE1NauticalIncomes_UNIQUE` (`idE1NauticalIncomes`),
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
  CONSTRAINT `fk_E1_InfoData` FOREIGN KEY (`E1InfoDataID`) REFERENCES `E1InfoData` (`E1InfoDataID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_DataFromTaxPayerFolder` FOREIGN KEY (`idE1DataFromTaxPayerFolder`) REFERENCES `E1DataFromTaxPayerFolder` (`idE1DataFromTaxPayerFolder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_ExpensesRemovedFromTotalIncome` FOREIGN KEY (`idE1ExpensesRemovedFromTotalIncome`) REFERENCES `E1ExpensesRemovedFromTotalIncome` (`idE1ExpensesRemovedFromTotalIncome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_IncomesReduceTaxes` FOREIGN KEY (`idE1IncomesReduceTaxes`) REFERENCES `E1IncomesReduceTaxes` (`idE1IncomesReduceTaxes`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_NauticalIncomes` FOREIGN KEY (`idE1NauticalIncomes`) REFERENCES `E1NAUTICALINCOMES` (`idE1NAUTICALINCOMES`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_ObjectiveSpending` FOREIGN KEY (`idE1ObjectiveSpending`) REFERENCES `E1ObjectiveSpending` (`idE1ObjectiveSpending`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_PersonDataBorneTaxPayer` FOREIGN KEY (`idE1PersonDataBorneTaxpayer`) REFERENCES `E1PersonDataBorneTaxpayer` (`idE1PersonDataBorneTaxpayer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_PrepaidTaxes` FOREIGN KEY (`idE1PrepaidTaxes`) REFERENCES `E1PrepaidTaxes` (`idE1PrepaidTaxes`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_ReduceTax` FOREIGN KEY (`idE1ReduceTax`) REFERENCES `E1ReduceTax` (`idE1ReduceTax`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_TaxableIncomes` FOREIGN KEY (`E1TaxableIncomeID`) REFERENCES `E1TaxableIncomes` (`E1TaxableIncome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_E1_Taxpayer` FOREIGN KEY (`TaxpayerID`) REFERENCES `Taxpayer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_E1_TaxPayerBankAccount` FOREIGN KEY (`idE1TaxPayerBankAccount`) REFERENCES `E1TaxPayerBankAccount` (`idE1TaxPayerBankAccount`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1DataFromTaxPayerFolder` (
  `idE1DataFromTaxPayerFolder` int(11) NOT NULL AUTO_INCREMENT,
  `_901` float DEFAULT NULL,
  `_902` float DEFAULT NULL,
  `_903` float DEFAULT NULL,
  `_904` float DEFAULT NULL,
  `_341` float DEFAULT NULL,
  `_995` float DEFAULT NULL,
  `_996` float DEFAULT NULL,
  PRIMARY KEY (`idE1DataFromTaxPayerFolder`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1ExpensesRemovedFromTotalIncome` (
  `idE1ExpensesRemovedFromTotalIncome` int(11) NOT NULL AUTO_INCREMENT,
  `_049` float DEFAULT NULL,
  `_051` float DEFAULT NULL,
  `_052` float DEFAULT NULL,
  `_053` float DEFAULT NULL,
  `_054` float DEFAULT NULL,
  `_057` float DEFAULT NULL,
  `_058` float DEFAULT NULL,
  `_031` float DEFAULT NULL,
  `_032` float DEFAULT NULL,
  `_059` float DEFAULT NULL,
  `_060` float DEFAULT NULL,
  `_075` float DEFAULT NULL,
  `_076` float DEFAULT NULL,
  `_061` float DEFAULT NULL,
  `_062` float DEFAULT NULL,
  `_071` float DEFAULT NULL,
  `_063` float DEFAULT NULL,
  `_064` float DEFAULT NULL,
  `_069` float DEFAULT NULL,
  `_070` float DEFAULT NULL,
  `RentalForFamilyOwnerName1` varchar(45) DEFAULT NULL,
  `_801` varchar(10) DEFAULT NULL,
  `_092` int(1) DEFAULT NULL,
  `_091` float DEFAULT NULL,
  `_097` int(2) DEFAULT NULL,
  `_811` float DEFAULT NULL,
  `_812` float DEFAULT NULL,
  `RentalForFamilyOwnerName2` varchar(45) DEFAULT NULL,
  `_802` varchar(10) DEFAULT NULL,
  `_094` int(1) DEFAULT NULL,
  `_093` float DEFAULT NULL,
  `_098` int(2) DEFAULT NULL,
  `_813` float DEFAULT NULL,
  `_814` float DEFAULT NULL,
  `RentalForFamilyOwnerName3` varchar(45) DEFAULT NULL,
  `_803` varchar(10) DEFAULT NULL,
  `_096` int(1) DEFAULT NULL,
  `_095` float DEFAULT NULL,
  `_099` float DEFAULT NULL,
  `_815` float DEFAULT NULL,
  `_816` float DEFAULT NULL,
  `RentalForStudyOwnerName1` varchar(45) DEFAULT NULL,
  `_804` varchar(10) DEFAULT NULL,
  `_817` float DEFAULT NULL,
  `RentalForStudyOwnerName2` varchar(45) DEFAULT NULL,
  `_805` varchar(10) DEFAULT NULL,
  `_819` float DEFAULT NULL,
  `RentalForStudyOwnerName3` varchar(45) DEFAULT NULL,
  `_806` varchar(10) DEFAULT NULL,
  `_821` float DEFAULT NULL,
  `RentalForStudyOwnerName4` varchar(45) DEFAULT NULL,
  `_807` varchar(10) DEFAULT NULL,
  `_823` float DEFAULT NULL,
  `_073` float DEFAULT NULL,
  `_074` float DEFAULT NULL,
  `_089` float DEFAULT NULL,
  `_090` float DEFAULT NULL,
  `_087` float DEFAULT NULL,
  `_088` float DEFAULT NULL,
  `_079` float DEFAULT NULL,
  `_080` float DEFAULT NULL,
  `_081` float DEFAULT NULL,
  `_082` float DEFAULT NULL,
  `_083` float DEFAULT NULL,
  `_084` float DEFAULT NULL,
  `_085` float DEFAULT NULL,
  `_077` float DEFAULT NULL,
  `_078` float DEFAULT NULL,
  `_663` float DEFAULT NULL,
  `_664` float DEFAULT NULL,
  `_033` int(1) DEFAULT '0',
  `_034` int(1) DEFAULT '0',
  `_035` int(1) DEFAULT '0',
  `_036` int(1) DEFAULT '0',
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
  `_655` float DEFAULT NULL,
  `_656` float DEFAULT NULL,
  `_693` float DEFAULT NULL,
  `_694` float DEFAULT NULL,
  `_659` float DEFAULT NULL,
  `_660` float DEFAULT NULL,
  `_657` float DEFAULT NULL,
  `_658` float DEFAULT NULL,
  `_661` float DEFAULT NULL,
  `_662` float DEFAULT NULL,
  `_431` float DEFAULT NULL,
  `_432` float DEFAULT NULL,
  `_433` float DEFAULT NULL,
  `_434` float DEFAULT NULL,
  `_305` float DEFAULT NULL,
  `_306` float DEFAULT NULL,
  `_477` float DEFAULT NULL,
  `_478` float DEFAULT NULL,
  `LessorName1` varchar(45) DEFAULT NULL,
  `_790` varchar(10) DEFAULT NULL,
  `_795` float DEFAULT NULL,
  `LassorName2` varchar(45) DEFAULT NULL,
  `_791` varchar(10) DEFAULT NULL,
  `_796` float DEFAULT NULL,
  `_793` float DEFAULT NULL,
  `_794` float DEFAULT NULL,
  `_615` float DEFAULT NULL,
  `_616` varchar(45) DEFAULT NULL,
  `LassorNameForStudent` varchar(45) DEFAULT NULL,
  `_417` varchar(10) DEFAULT NULL,
  `AreaStudentHouse` float DEFAULT NULL,
  `_419` float DEFAULT NULL,
  `_420` float DEFAULT NULL,
  `_735` float DEFAULT NULL,
  `_736` float DEFAULT NULL,
  `_781` float DEFAULT NULL,
  `_782` float DEFAULT NULL,
  `_783` float DEFAULT NULL,
  `_784` float DEFAULT NULL,
  `_787` float DEFAULT NULL,
  `_788` float DEFAULT NULL,
  PRIMARY KEY (`idE1IncomesReduceTaxes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1InfoData` (
  `E1InfoDataID` int(11) NOT NULL AUTO_INCREMENT,
  `_327` int(1) DEFAULT NULL,
  `_328` int(1) DEFAULT NULL,
  `_319` int(1) DEFAULT NULL,
  `_320` int(1) DEFAULT NULL,
  `_023` int(1) DEFAULT NULL,
  `_024` int(1) DEFAULT NULL,
  `_329` int(1) DEFAULT NULL,
  `_330` int(1) DEFAULT NULL,
  `_331` int(1) DEFAULT NULL,
  `_011` int(1) DEFAULT NULL,
  `_012` int(1) DEFAULT NULL,
  `_013` int(1) DEFAULT NULL,
  `_014` int(1) DEFAULT NULL,
  `_015` int(1) DEFAULT NULL,
  `_016` int(1) DEFAULT NULL,
  `_017` int(1) DEFAULT NULL,
  `_018` int(1) DEFAULT NULL,
  `_021` int(1) DEFAULT NULL,
  `_022` int(1) DEFAULT NULL,
  `_025` int(1) DEFAULT NULL,
  `_026` int(1) DEFAULT NULL,
  `_007` int(1) DEFAULT NULL,
  `_008` int(1) DEFAULT NULL,
  `_617` int(1) DEFAULT NULL,
  `_385` int(1) DEFAULT NULL,
  `_386` int(1) DEFAULT NULL,
  `_029` int(1) DEFAULT NULL,
  `_030` int(1) DEFAULT NULL,
  `_905` int(1) DEFAULT NULL,
  `_906` int(1) DEFAULT NULL,
  `_911` int(1) DEFAULT NULL,
  `_912` int(1) DEFAULT NULL,
  `_010` int(1) DEFAULT NULL,
  PRIMARY KEY (`E1InfoDataID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1NAUTICALINCOMES` (
  `idE1NAUTICALINCOMES` int(11) NOT NULL AUTO_INCREMENT,
  `_255` float DEFAULT NULL,
  `_256` float DEFAULT NULL,
  `_257` float DEFAULT NULL,
  `_258` float DEFAULT NULL,
  `_263` float DEFAULT NULL,
  `_264` float DEFAULT NULL,
  `_265` float DEFAULT NULL,
  `_266` float DEFAULT NULL,
  `_253` float DEFAULT NULL,
  `_254` float DEFAULT NULL,
  `_201` float DEFAULT NULL,
  `_202` float DEFAULT NULL,
  PRIMARY KEY (`idE1NAUTICALINCOMES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

ALTER TABLE E1ObjectiveSpending CONVERT TO CHARACTER SET utf8;delimiter $$

delimiter $$

CREATE TABLE `E1ObjectiveSpending` (
  `idE1ObjectiveSpending` int(11) NOT NULL AUTO_INCREMENT,
  `_205` varchar(45) DEFAULT NULL,
  `HouseAddr1` varchar(200) DEFAULT NULL,
  `Floor1` varchar(45) DEFAULT NULL,
  `_203` int(1) DEFAULT NULL,
  `_240` int(1) DEFAULT NULL,
  `_211` float DEFAULT NULL,
  `_212` float DEFAULT NULL,
  `_213` float DEFAULT NULL,
  `_214` float DEFAULT NULL,
  `_215` int(2) DEFAULT NULL,
  `_216` float DEFAULT NULL,
  `HouseAddr2` varchar(200) DEFAULT NULL,
  `Floor2` varchar(45) DEFAULT NULL,
  `_207` int(1) DEFAULT NULL,
  `_241` int(1) DEFAULT NULL,
  `_218` float DEFAULT NULL,
  `_219` float DEFAULT NULL,
  `_220` float DEFAULT NULL,
  `_221` float DEFAULT NULL,
  `_222` int(2) DEFAULT NULL,
  `_223` float DEFAULT NULL,
  `HouseAddr3` varchar(200) DEFAULT NULL,
  `Floor3` varchar(45) DEFAULT NULL,
  `_209` int(1) DEFAULT NULL,
  `_242` int(1) DEFAULT NULL,
  `_225` float DEFAULT NULL,
  `_226` float DEFAULT NULL,
  `_227` float DEFAULT NULL,
  `_228` float DEFAULT NULL,
  `_229` int(2) DEFAULT NULL,
  `_230` float DEFAULT NULL,
  `_707` float DEFAULT NULL,
  `_708` float DEFAULT NULL,
  `AFM1` varchar(10) DEFAULT NULL,
  `_750` varchar(7) DEFAULT NULL,
  `_703` int(4) DEFAULT NULL,
  `_761` int(2) DEFAULT NULL,
  `_771` float DEFAULT NULL,
  `_775` int(4) DEFAULT NULL,
  `AFM2` varchar(10) DEFAULT NULL,
  `_751` varchar(7) DEFAULT NULL,
  `_704` int(4) DEFAULT NULL,
  `_762` int(2) DEFAULT NULL,
  `_772` float DEFAULT NULL,
  `_776` int(4) DEFAULT NULL,
  `AFM3` varchar(10) DEFAULT NULL,
  `_752` varchar(7) DEFAULT NULL,
  `_705` int(4) DEFAULT NULL,
  `_763` int(2) DEFAULT NULL,
  `_773` float DEFAULT NULL,
  `_777` int(4) DEFAULT NULL,
  `AFM4` varchar(10) DEFAULT NULL,
  `_753` varchar(7) DEFAULT NULL,
  `_706` int(4) DEFAULT NULL,
  `_764` int(2) DEFAULT NULL,
  `_774` float DEFAULT NULL,
  `_778` int(4) DEFAULT NULL,
  `_851` float DEFAULT NULL,
  `_852` float DEFAULT NULL,
  `_853` float DEFAULT NULL,
  `_854` float DEFAULT NULL,
  `_855` float DEFAULT NULL,
  `_856` float DEFAULT NULL,
  `_857` float DEFAULT NULL,
  `_858` float DEFAULT NULL,
  `ShippingName1` varchar(45) DEFAULT NULL,
  `ShippingRegister1` varchar(100) DEFAULT NULL,
  `ShippingCountry1` varchar(45) DEFAULT NULL,
  `SailShip1` int(1) DEFAULT NULL,
  `AccommodationSpace1` int(1) DEFAULT NULL,
  `PercentPrincipalCoOwner1` float DEFAULT NULL,
  `PercentWifeCoOwner1` float DEFAULT NULL,
  `FirstRegister1` int(4) DEFAULT NULL,
  `OwnershipMonths1` int(2) DEFAULT NULL,
  `_747` float DEFAULT NULL,
  `ShippingName2` varchar(45) DEFAULT NULL,
  `ShippingRegister2` varchar(100) DEFAULT NULL,
  `ShippingCountry2` varchar(45) DEFAULT NULL,
  `SailShip2` int(1) DEFAULT NULL,
  `AccommodationSpace2` int(1) DEFAULT NULL,
  `PercentPrincipalCoOwner2` float DEFAULT NULL,
  `PercentWifeCoOwner2` float DEFAULT NULL,
  `FirstRegister2` int(4) DEFAULT NULL,
  `OwnershipMonths2` int(2) DEFAULT NULL,
  `_748` float DEFAULT NULL,
  `_711` float DEFAULT NULL,
  `_712` float DEFAULT NULL,
  `_713` float DEFAULT NULL,
  `_714` float DEFAULT NULL,
  `_731` float DEFAULT NULL,
  `_732` float DEFAULT NULL,
  `AircraftRegisterData` varchar(100) DEFAULT NULL,
  `AircraftType` varchar(45) DEFAULT NULL,
  `AircraftSN` varchar(45) DEFAULT NULL,
  `AirportBase` varchar(45) DEFAULT NULL,
  `AircraftOwnerShip` int(2) DEFAULT NULL,
  `AircraftPowerLibres` int(11) DEFAULT NULL,
  `AircraftFirstRegister` int(4) DEFAULT NULL,
  `_715` float DEFAULT NULL,
  `_716` float DEFAULT NULL,
  `_767` float DEFAULT NULL,
  `PoolPrincipalCoOwnerOutdoor` float DEFAULT NULL,
  `PoolWifeCoOwnerOutdoor` float DEFAULT NULL,
  `_768` float DEFAULT NULL,
  `PoolPrincipalCoOwnerIndoor` float DEFAULT NULL,
  `PoolWifeCoOwnerIndoor` float DEFAULT NULL,
  `_765` float DEFAULT NULL,
  `_766` float DEFAULT NULL,
  `_769` float DEFAULT NULL,
  `_770` float DEFAULT NULL,
  `_719` float DEFAULT NULL,
  `_720` float DEFAULT NULL,
  `_721` float DEFAULT NULL,
  `_722` float DEFAULT NULL,
  `_723` float DEFAULT NULL,
  `_724` float DEFAULT NULL,
  `_725` float DEFAULT NULL,
  `_726` float DEFAULT NULL,
  `_727` float DEFAULT NULL,
  `_728` float DEFAULT NULL,
  PRIMARY KEY (`idE1ObjectiveSpending`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1PersonDataBorneTaxpayer` (
  `idE1PersonDataBorneTaxpayer` int(11) NOT NULL AUTO_INCREMENT,
  `Name1` varchar(45) DEFAULT NULL,
  `BirthYear1` int(4) DEFAULT NULL,
  `SchoolName1` varchar(100) DEFAULT NULL,
  `_831` varchar(10) DEFAULT NULL,
  `Name2` varchar(45) DEFAULT NULL,
  `BirthYear2` int(4) DEFAULT NULL,
  `SchoolName2` varchar(100) DEFAULT NULL,
  `_832` varchar(10) DEFAULT NULL,
  `Name3` varchar(45) DEFAULT NULL,
  `BirthYear3` int(4) DEFAULT NULL,
  `SchoolName3` varchar(100) DEFAULT NULL,
  `_833` varchar(10) DEFAULT NULL,
  `Name4` varchar(45) DEFAULT NULL,
  `BirthYear4` int(4) DEFAULT NULL,
  `SchoolName4` varchar(100) DEFAULT NULL,
  `_834` varchar(10) DEFAULT NULL,
  `FullName1` varchar(45) DEFAULT NULL,
  `_835` varchar(10) DEFAULT NULL,
  `RelationshipWithPrincipal1` varchar(45) DEFAULT NULL,
  `RelationShipWithWife1` varchar(45) DEFAULT NULL,
  `FullName2` varchar(45) DEFAULT NULL,
  `_836` varchar(10) DEFAULT NULL,
  `RelationshipWithPrincipal2` varchar(45) DEFAULT NULL,
  `RelationShipWithWife2` varchar(45) DEFAULT NULL,
  `FullName3` varchar(45) DEFAULT NULL,
  `_837` varchar(45) DEFAULT NULL,
  `RelationshipWithPrincipal3` varchar(45) DEFAULT NULL,
  `RelationShipWithWife3` varchar(45) DEFAULT NULL,
  `FullName4` varchar(45) DEFAULT NULL,
  `_838` varchar(10) DEFAULT NULL,
  `RelationshipWithPrincipal4` varchar(45) DEFAULT NULL,
  `RelationShipWithWife4` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idE1PersonDataBorneTaxpayer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1PrepaidTaxes` (
  `idE1PrepaidTaxes` int(11) NOT NULL AUTO_INCREMENT,
  `_601` float DEFAULT NULL,
  `_602` float DEFAULT NULL,
  `_603` float DEFAULT NULL,
  `_604` float DEFAULT NULL,
  `_605` float DEFAULT NULL,
  `_606` float DEFAULT NULL,
  `_607` float DEFAULT NULL,
  `_608` float DEFAULT NULL,
  `_609` float DEFAULT NULL,
  `_610` float DEFAULT NULL,
  `_651` float DEFAULT NULL,
  `_652` float DEFAULT NULL,
  `_293` float DEFAULT NULL,
  `_294` float DEFAULT NULL,
  `_313` float DEFAULT NULL,
  `_314` float DEFAULT NULL,
  `_315` float DEFAULT NULL,
  `_316` float DEFAULT NULL,
  `_297` float DEFAULT NULL,
  `_298` float DEFAULT NULL,
  `_127` float DEFAULT NULL,
  `_128` float DEFAULT NULL,
  PRIMARY KEY (`idE1PrepaidTaxes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1ReduceTax` (
  `idE1ReduceTax` int(11) NOT NULL AUTO_INCREMENT,
  `_001` int(1) DEFAULT NULL,
  `_002` int(1) DEFAULT NULL,
  `_003` int(11) DEFAULT NULL,
  `_004` int(11) DEFAULT NULL,
  `_005` int(11) DEFAULT NULL,
  `_006` int(11) DEFAULT NULL,
  PRIMARY KEY (`idE1ReduceTax`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1RelatePersons` (
  `TaxpayerID` int(11) NOT NULL,
  `Year` int(4) NOT NULL,
  `idRelatePerson` int(11) NOT NULL,
  PRIMARY KEY (`TaxpayerID`,`Year`,`idRelatePerson`),
  KEY `fk_E1RelatePersons_1_idx` (`idRelatePerson`),
  KEY `fk_E1RelatePersons_E1_idx` (`TaxpayerID`,`Year`),
  CONSTRAINT `fk_E1RelatePersons_E1` FOREIGN KEY (`TaxpayerID`, `Year`) REFERENCES `E1` (`TaxpayerID`, `Year`),
  CONSTRAINT `fk_E1RelatePersons_RelatePerson` FOREIGN KEY (`idRelatePerson`) REFERENCES `RelatePerson` (`idRelatePerson`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `E1TaxableIncomes` (
  `E1TaxableIncome` int(11) NOT NULL AUTO_INCREMENT,
  `_301` float DEFAULT NULL,
  `_302` float DEFAULT NULL,
  `_303` float DEFAULT NULL,
  `_304` float DEFAULT NULL,
  `_321` float DEFAULT NULL,
  `_322` float DEFAULT NULL,
  `_317` float DEFAULT NULL,
  `_318` float DEFAULT NULL,
  `_461` float DEFAULT NULL,
  `_462` float DEFAULT NULL,
  `_921` float DEFAULT NULL,
  `_922` float DEFAULT NULL,
  `_919` float DEFAULT NULL,
  `_920` float DEFAULT NULL,
  `_915` float DEFAULT NULL,
  `_916` float DEFAULT NULL,
  `_923` float DEFAULT NULL,
  `_924` float DEFAULT NULL,
  `_335` float DEFAULT NULL,
  `_336` float DEFAULT NULL,
  `_326` float DEFAULT NULL,
  `_337` float DEFAULT NULL,
  `_338` float DEFAULT NULL,
  `_339` float DEFAULT NULL,
  `_340` float DEFAULT NULL,
  `_465` float DEFAULT NULL,
  `_466` float DEFAULT NULL,
  `_467` float DEFAULT NULL,
  `_468` float DEFAULT NULL,
  `_475` float DEFAULT NULL,
  `_476` float DEFAULT NULL,
  `_479` float DEFAULT NULL,
  `_480` float DEFAULT NULL,
  `_481` float DEFAULT NULL,
  `_482` float DEFAULT NULL,
  `_401` float DEFAULT NULL,
  `_402` float DEFAULT NULL,
  `_403` float DEFAULT NULL,
  `_404` float DEFAULT NULL,
  `_405` float DEFAULT NULL,
  `_406` float DEFAULT NULL,
  `_407` float DEFAULT NULL,
  `_408` float DEFAULT NULL,
  `_413` float DEFAULT NULL,
  `_414` float DEFAULT NULL,
  `_415` float DEFAULT NULL,
  `_416` float DEFAULT NULL,
  `_425` float DEFAULT NULL,
  `_426` float DEFAULT NULL,
  `_501` float DEFAULT NULL,
  `_502` float DEFAULT NULL,
  `_503` float DEFAULT NULL,
  `_504` float DEFAULT NULL,
  `_505` float DEFAULT NULL,
  `_506` float DEFAULT NULL,
  `_507` float DEFAULT NULL,
  `_508` float DEFAULT NULL,
  `_511` float DEFAULT NULL,
  `_512` float DEFAULT NULL,
  `_515` float DEFAULT NULL,
  `_516` float DEFAULT NULL,
  `_517` float DEFAULT NULL,
  `_518` float DEFAULT NULL,
  `_103` float DEFAULT NULL,
  `_104` float DEFAULT NULL,
  `_121` float DEFAULT NULL,
  `_122` float DEFAULT NULL,
  `_105` float DEFAULT NULL,
  `_106` float DEFAULT NULL,
  `_107` float DEFAULT NULL,
  `_108` float DEFAULT NULL,
  `_109` float DEFAULT NULL,
  `_110` float DEFAULT NULL,
  `_101` float DEFAULT NULL,
  `_102` float DEFAULT NULL,
  `_909` float DEFAULT NULL,
  `_910` float DEFAULT NULL,
  `_111` float DEFAULT NULL,
  `_112` float DEFAULT NULL,
  `_113` float DEFAULT NULL,
  `_114` float DEFAULT NULL,
  `_129` float DEFAULT NULL,
  `_130` float DEFAULT NULL,
  `_143` float DEFAULT NULL,
  `_144` float DEFAULT NULL,
  `_145` float DEFAULT NULL,
  `_146` float DEFAULT NULL,
  `_147` float DEFAULT NULL,
  `_148` float DEFAULT NULL,
  `_141` float DEFAULT NULL,
  `_142` float DEFAULT NULL,
  `_701` float DEFAULT NULL,
  `_702` float DEFAULT NULL,
  `_123` float DEFAULT NULL,
  `_124` float DEFAULT NULL,
  `_125` float DEFAULT NULL,
  `_126` float DEFAULT NULL,
  `_151` float DEFAULT NULL,
  `_152` float DEFAULT NULL,
  `_163` float DEFAULT NULL,
  `_164` float DEFAULT NULL,
  `_165` float DEFAULT NULL,
  `_166` float DEFAULT NULL,
  `_159` float DEFAULT NULL,
  `_160` float DEFAULT NULL,
  `_175` float DEFAULT NULL,
  `_176` float DEFAULT NULL,
  `_131` float DEFAULT NULL,
  `_132` float DEFAULT NULL,
  `_133` float DEFAULT NULL,
  `_134` float DEFAULT NULL,
  `_741` float DEFAULT NULL,
  `_742` float DEFAULT NULL,
  `_291` float DEFAULT NULL,
  `_292` float DEFAULT NULL,
  `_389` float DEFAULT NULL,
  `_390` float DEFAULT NULL,
  `_391` float DEFAULT NULL,
  `_392` float DEFAULT NULL,
  `_463` float DEFAULT NULL,
  `_464` float DEFAULT NULL,
  `_471` float DEFAULT NULL,
  `_472` float DEFAULT NULL,
  `_411` float DEFAULT NULL,
  `_412` float DEFAULT NULL,
  `_421` float DEFAULT NULL,
  `_422` float DEFAULT NULL,
  `_509` float DEFAULT NULL,
  `_510` float DEFAULT NULL,
  `_513` float DEFAULT NULL,
  `_514` float DEFAULT NULL,
  `_295` float DEFAULT NULL,
  `_296` float DEFAULT NULL,
  `_171` float DEFAULT NULL,
  `_172` float DEFAULT NULL,
  `_173` float DEFAULT NULL,
  `_174` float DEFAULT NULL,
  `_395` float DEFAULT NULL,
  `_396` float DEFAULT NULL,
  `AgrLocation1` varchar(150) DEFAULT NULL,
  `AgrProdKind1` varchar(45) DEFAULT NULL,
  `AgrPopulation1` float DEFAULT NULL,
  `AgrLocationType1` int(1) DEFAULT NULL,
  `AgrHasWater1` int(1) DEFAULT NULL,
  `AgrNetIncome1` float DEFAULT NULL,
  `AgrTotalNetincome1` float DEFAULT NULL,
  `AgrTotalWifeNetincome1` float DEFAULT NULL,
  `AgrLocation2` varchar(150) DEFAULT NULL,
  `AgrProdKind2` varchar(45) DEFAULT NULL,
  `AgrPopulation2` float DEFAULT NULL,
  `AgrLocationType2` int(1) DEFAULT NULL,
  `AgrHasWater2` int(1) DEFAULT NULL,
  `AgrNetIncome2` float DEFAULT NULL,
  `AgrTotalNetincome2` float DEFAULT NULL,
  `AgrTotalWifeNetincome2` float DEFAULT NULL,
  `AgrLocation3` varchar(150) DEFAULT NULL,
  `AgrProdKind3` varchar(45) DEFAULT NULL,
  `AgrPopulation3` float DEFAULT NULL,
  `AgrLocationType3` int(1) DEFAULT NULL,
  `AgrHasWater3` int(1) DEFAULT NULL,
  `AgrNetIncome3` float DEFAULT NULL,
  `AgrTotalNetincome3` float DEFAULT NULL,
  `AgrTotalWifeNetincome3` float DEFAULT NULL,
  `AgrLocation4` varchar(150) DEFAULT NULL,
  `AgrProdKind4` varchar(45) DEFAULT NULL,
  `AgrPopulation4` float DEFAULT NULL,
  `AgrLocationType4` int(1) DEFAULT NULL,
  `AgrHasWater4` int(1) DEFAULT NULL,
  `AgrNetIncome4` float DEFAULT NULL,
  `AgrTotalNetincome4` float DEFAULT NULL,
  `AgrTotalWifeNetincome4` float DEFAULT NULL,
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
