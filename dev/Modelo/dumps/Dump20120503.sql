-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: lel
-- ------------------------------------------------------
-- Server version	5.5.17

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
-- Current Database: `lel`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lel` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `lel`;

--
-- Table structure for table `concept`
--

DROP TABLE IF EXISTS `concept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) DEFAULT NULL,
  `name` longtext COLLATE utf8_bin,
  `DocumentDef_id` int(11) NOT NULL,
  `ConceptDetails_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Concept_DocumentDef1` (`DocumentDef_id`),
  KEY `fk_Concept_ConceptDetails1` (`ConceptDetails_id`),
  CONSTRAINT `fk_Concept_DocumentDef1` FOREIGN KEY (`DocumentDef_id`) REFERENCES `documentdef` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Concept_ConceptDetails1` FOREIGN KEY (`ConceptDetails_id`) REFERENCES `conceptdetails` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept`
--

LOCK TABLES `concept` WRITE;
/*!40000 ALTER TABLE `concept` DISABLE KEYS */;
INSERT INTO `concept` VALUES (2,1,'Universidad de California en San Diego ',2,2),(3,1,'hidrogel',2,3),(4,1,'velcro',2,3),(6,1,'polímeros',2,3),(7,1,'La autocuración ',2,4);
/*!40000 ALTER TABLE `concept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptactiondef`
--

DROP TABLE IF EXISTS `conceptactiondef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptactiondef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptactiondef`
--

LOCK TABLES `conceptactiondef` WRITE;
/*!40000 ALTER TABLE `conceptactiondef` DISABLE KEYS */;
/*!40000 ALTER TABLE `conceptactiondef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptcategory`
--

DROP TABLE IF EXISTS `conceptcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conceptId` int(11) DEFAULT NULL,
  `ConceptCategoryDef_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ConceptCategory_ConceptCategoryDef1` (`ConceptCategoryDef_id`),
  CONSTRAINT `fk_ConceptCategory_ConceptCategoryDef1` FOREIGN KEY (`ConceptCategoryDef_id`) REFERENCES `conceptcategorydef` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptcategory`
--

LOCK TABLES `conceptcategory` WRITE;
/*!40000 ALTER TABLE `conceptcategory` DISABLE KEYS */;
INSERT INTO `conceptcategory` VALUES (1,2,1),(2,3,2),(3,7,3);
/*!40000 ALTER TABLE `conceptcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptcategorydef`
--

DROP TABLE IF EXISTS `conceptcategorydef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptcategorydef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptcategorydef`
--

LOCK TABLES `conceptcategorydef` WRITE;
/*!40000 ALTER TABLE `conceptcategorydef` DISABLE KEYS */;
INSERT INTO `conceptcategorydef` VALUES (1,'Sujeto'),(2,'Objeto'),(3,'Verbo'),(4,'Estado');
/*!40000 ALTER TABLE `conceptcategorydef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptclass`
--

DROP TABLE IF EXISTS `conceptclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conceptId` int(11) DEFAULT NULL,
  `ConceptClassDef_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ConceptClass_ConceptClassDef1` (`ConceptClassDef_id`),
  CONSTRAINT `fk_ConceptClass_ConceptClassDef1` FOREIGN KEY (`ConceptClassDef_id`) REFERENCES `conceptclassdef` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptclass`
--

LOCK TABLES `conceptclass` WRITE;
/*!40000 ALTER TABLE `conceptclass` DISABLE KEYS */;
INSERT INTO `conceptclass` VALUES (1,2,1),(2,3,3),(3,7,2);
/*!40000 ALTER TABLE `conceptclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptclassdef`
--

DROP TABLE IF EXISTS `conceptclassdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptclassdef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptclassdef`
--

LOCK TABLES `conceptclassdef` WRITE;
/*!40000 ALTER TABLE `conceptclassdef` DISABLE KEYS */;
INSERT INTO `conceptclassdef` VALUES (1,'Concepto dell Sistema'),(2,'Concepto de Requisito'),(3,'Concepto General');
/*!40000 ALTER TABLE `conceptclassdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptdetails`
--

DROP TABLE IF EXISTS `conceptdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` longtext COLLATE utf8_bin,
  `notion` longtext COLLATE utf8_bin,
  `actualIntention` longtext COLLATE utf8_bin,
  `futureIntention` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptdetails`
--

LOCK TABLES `conceptdetails` WRITE;
/*!40000 ALTER TABLE `conceptdetails` DISABLE KEYS */;
INSERT INTO `conceptdetails` VALUES (2,'Universidad de alto prestigio','encargada de hacer estudios sobre materiales de gran impacto','Preparar estudiantes capacitados para la investigacion','Participar en concursos y realizar publicaciones'),(3,'gel a base de agua','gel que se adhiere facilmente a varias superficies','Ser utilizado para investigaciones academicas','Ser utilizado en la industria con diversos fines'),(4,'es necesario hacer preguntas al expertos','Curarse mediante los propios medios del individuo',NULL,NULL);
/*!40000 ALTER TABLE `conceptdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conceptlog`
--

DROP TABLE IF EXISTS `conceptlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceptlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  `ConceptActionDef_id` int(11) NOT NULL,
  `Concept_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ConceptLog_ConceptActionDef1` (`ConceptActionDef_id`),
  KEY `fk_ConceptLog_Concept1` (`Concept_id`),
  CONSTRAINT `fk_ConceptLog_ConceptActionDef1` FOREIGN KEY (`ConceptActionDef_id`) REFERENCES `conceptactiondef` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ConceptLog_Concept1` FOREIGN KEY (`Concept_id`) REFERENCES `concept` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptlog`
--

LOCK TABLES `conceptlog` WRITE;
/*!40000 ALTER TABLE `conceptlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `conceptlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentdef`
--

DROP TABLE IF EXISTS `documentdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentdef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentdef`
--

LOCK TABLES `documentdef` WRITE;
/*!40000 ALTER TABLE `documentdef` DISABLE KEYS */;
INSERT INTO `documentdef` VALUES (2,'Un equipo de la Escuela Jacobs de Ingeniería de la Universidad de California en San Diego ');
/*!40000 ALTER TABLE `documentdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yanet','yanet'),(2,'luis ','luis');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useractiondef`
--

DROP TABLE IF EXISTS `useractiondef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useractiondef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useractiondef`
--

LOCK TABLES `useractiondef` WRITE;
/*!40000 ALTER TABLE `useractiondef` DISABLE KEYS */;
INSERT INTO `useractiondef` VALUES (1,'Insertar'),(2,'Editar'),(3,'Eliminar');
/*!40000 ALTER TABLE `useractiondef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UserActionDef_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_UserLog_UserActionDef1` (`UserActionDef_id`),
  KEY `fk_UserLog_User1` (`User_id`),
  CONSTRAINT `fk_UserLog_UserActionDef1` FOREIGN KEY (`UserActionDef_id`) REFERENCES `useractiondef` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserLog_User1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlog`
--

LOCK TABLES `userlog` WRITE;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
INSERT INTO `userlog` VALUES (1,'2012-12-12 07:00:00',1,1),(2,'2010-03-12 07:00:00',2,2);
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-03 14:46:31
