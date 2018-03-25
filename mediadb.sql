CREATE DATABASE  IF NOT EXISTS `mediadb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mediadb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mediadb
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `idDoc` int(11) NOT NULL AUTO_INCREMENT,
  `nomDoc` varchar(25) NOT NULL,
  `typeDoc` int(11) NOT NULL,
  `numUtil` int(11) DEFAULT NULL,
  `auteurDoc` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idDoc`),
  KEY `FK_Document_numUtil` (`numUtil`),
  CONSTRAINT `FK_Document_numUtil` FOREIGN KEY (`numUtil`) REFERENCES `utilisateur` (`numUtil`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`idDoc`, `nomDoc`, `typeDoc`, `numUtil`, `auteurDoc`) VALUES (1,'Lord of the rings',2,3,'Tolkien'),(2,'Robots',2,NULL,'Isaac Asimov'),(3,'Fondation',2,NULL,'Isaac Asimov'),(4,'ACDC',0,NULL,'Highway to Hell'),(5,'Totoro',0,NULL,'Miyazaki');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `numUtil` int(11) NOT NULL AUTO_INCREMENT,
  `typeUtil` int(11) NOT NULL,
  `pwdUtil` varchar(25) NOT NULL,
  `nomUtil` varchar(25) NOT NULL,
  PRIMARY KEY (`numUtil`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` (`numUtil`, `typeUtil`, `pwdUtil`, `nomUtil`) VALUES (1,2,'abcd','Pierre'),(2,2,'abcd','Jacques'),(3,1,'abcd','Virginie');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mediadb'
--

--
-- Dumping routines for database 'mediadb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-25 23:39:29
