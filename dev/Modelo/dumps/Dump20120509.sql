CREATE DATABASE  IF NOT EXISTS `lel` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `lel`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: lel
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `concept`
--

DROP TABLE IF EXISTS `concept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) NOT NULL,
  `name` longtext COLLATE utf8_bin NOT NULL,
  `document_def_id` int(11) NOT NULL,
  `concept_details_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_concept_document_def` (`document_def_id`),
  KEY `fk_concept_concept_details` (`concept_details_id`),
  CONSTRAINT `fk_concept_document_def` FOREIGN KEY (`document_def_id`) REFERENCES `document_def` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_concept_details` FOREIGN KEY (`concept_details_id`) REFERENCES `concept_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept`
--

LOCK TABLES `concept` WRITE;
/*!40000 ALTER TABLE `concept` DISABLE KEYS */;
INSERT INTO `concept` VALUES (1,1,'Universidad de California en San Diego',1,1),(2,1,'hidrogel',1,2),(3,1,'velcro',1,2),(4,1,'polimeros',1,2),(5,1,'La autocuración',1,3);
/*!40000 ALTER TABLE `concept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_class_def`
--

DROP TABLE IF EXISTS `concept_class_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_class_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_class_def`
--

LOCK TABLES `concept_class_def` WRITE;
/*!40000 ALTER TABLE `concept_class_def` DISABLE KEYS */;
INSERT INTO `concept_class_def` VALUES (2,'concepto de requisito'),(1,'concepto del sistema'),(3,'concepto general');
/*!40000 ALTER TABLE `concept_class_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_log`
--

DROP TABLE IF EXISTS `user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_action_def_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_log_user_action_def` (`user_action_def_id`),
  KEY `fk_user_log_user` (`user_id`),
  CONSTRAINT `fk_user_log_user_action_def` FOREIGN KEY (`user_action_def_id`) REFERENCES `user_action_def` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_log_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_log`
--

LOCK TABLES `user_log` WRITE;
/*!40000 ALTER TABLE `user_log` DISABLE KEYS */;
INSERT INTO `user_log` VALUES (1,'2012-12-12 14:00:00',1,1),(2,'2012-12-12 14:30:00',2,1);
/*!40000 ALTER TABLE `user_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_class`
--

DROP TABLE IF EXISTS `concept_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concept_id` int(11) NOT NULL,
  `concept_class_def_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_concept_class_concept_class_def` (`concept_class_def_id`),
  CONSTRAINT `fk_concept_class_concept_class_def` FOREIGN KEY (`concept_class_def_id`) REFERENCES `concept_class_def` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_class`
--

LOCK TABLES `concept_class` WRITE;
/*!40000 ALTER TABLE `concept_class` DISABLE KEYS */;
INSERT INTO `concept_class` VALUES (6,1,3),(7,2,2),(8,3,1),(9,4,1),(10,5,2);
/*!40000 ALTER TABLE `concept_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_action_def`
--

DROP TABLE IF EXISTS `user_action_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_action_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_action_def`
--

LOCK TABLES `user_action_def` WRITE;
/*!40000 ALTER TABLE `user_action_def` DISABLE KEYS */;
INSERT INTO `user_action_def` VALUES (3,'cambiar password'),(2,'cerrar sesion'),(1,'iniciar sesion');
/*!40000 ALTER TABLE `user_action_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_action_def`
--

DROP TABLE IF EXISTS `concept_action_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_action_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_action_def`
--

LOCK TABLES `concept_action_def` WRITE;
/*!40000 ALTER TABLE `concept_action_def` DISABLE KEYS */;
INSERT INTO `concept_action_def` VALUES (2,'editar'),(3,'eliminar'),(1,'insertar');
/*!40000 ALTER TABLE `concept_action_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_details`
--

DROP TABLE IF EXISTS `concept_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` longtext COLLATE utf8_bin,
  `notion` longtext COLLATE utf8_bin,
  `actual_intention` longtext COLLATE utf8_bin,
  `future_intention` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_details`
--

LOCK TABLES `concept_details` WRITE;
/*!40000 ALTER TABLE `concept_details` DISABLE KEYS */;
INSERT INTO `concept_details` VALUES (1,'Universidad de alto prestigio','encargada de hacer estudios sobre materiales de gran impacto','Preparar estudiantes capacitados para la investigacion','Participar en concursos y realizar publicaciones'),(2,'gel a base de agua','gel que se adhiere facilmente a varias superficies','Ser utilizado para investigaciones academicas','Ser utilizado en la industria con diversos fines'),(3,'es necesario hacer preguntas al expertos','Curarse mediante los propios medios del individuo',NULL,NULL);
/*!40000 ALTER TABLE `concept_details` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yanet','yanet'),(2,'luis','luis');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_category`
--

DROP TABLE IF EXISTS `concept_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concept_id` int(11) NOT NULL,
  `concept_category_def_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_concept_category_concept_category_def` (`concept_category_def_id`),
  CONSTRAINT `fk_concept_category_concept_category_def` FOREIGN KEY (`concept_category_def_id`) REFERENCES `concept_category_def` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_category`
--

LOCK TABLES `concept_category` WRITE;
/*!40000 ALTER TABLE `concept_category` DISABLE KEYS */;
INSERT INTO `concept_category` VALUES (1,1,4),(2,2,3),(3,3,2),(4,4,1),(5,5,4);
/*!40000 ALTER TABLE `concept_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_category_def`
--

DROP TABLE IF EXISTS `concept_category_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_category_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_category_def`
--

LOCK TABLES `concept_category_def` WRITE;
/*!40000 ALTER TABLE `concept_category_def` DISABLE KEYS */;
INSERT INTO `concept_category_def` VALUES (4,'estado'),(2,'objeto'),(1,'sujeto'),(3,'verbo');
/*!40000 ALTER TABLE `concept_category_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concept_log`
--

DROP TABLE IF EXISTS `concept_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concept_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `concept_action_def_id` int(11) NOT NULL,
  `concept_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_concept_log_concept_action_def` (`concept_action_def_id`),
  KEY `fk_concept_log_concept` (`concept_id`),
  CONSTRAINT `fk_concept_log_concept_action_def` FOREIGN KEY (`concept_action_def_id`) REFERENCES `concept_action_def` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_log_concept` FOREIGN KEY (`concept_id`) REFERENCES `concept` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concept_log`
--

LOCK TABLES `concept_log` WRITE;
/*!40000 ALTER TABLE `concept_log` DISABLE KEYS */;
INSERT INTO `concept_log` VALUES (1,'2012-12-12 14:00:00',1,1,1),(2,'2012-12-12 14:30:00',1,2,1);
/*!40000 ALTER TABLE `concept_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_def`
--

DROP TABLE IF EXISTS `document_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_def` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_def`
--

LOCK TABLES `document_def` WRITE;
/*!40000 ALTER TABLE `document_def` DISABLE KEYS */;
INSERT INTO `document_def` VALUES (1,'Un equipo de la Escuela Jacobs de Ingeniería de la Universidad de California en San Diego');
/*!40000 ALTER TABLE `document_def` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-09 21:20:50
