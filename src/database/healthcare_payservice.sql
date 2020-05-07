-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: healthcare
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `payservice`
--

DROP TABLE IF EXISTS `payservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payservice` (
  `paymentId` int NOT NULL AUTO_INCREMENT,
  `patientName` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `paydate` date DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contactNo` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`paymentId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payservice`
--

LOCK TABLES `payservice` WRITE;
/*!40000 ALTER TABLE `payservice` DISABLE KEYS */;
INSERT INTO `payservice` VALUES (5,'Moshan',25000,'2020-10-10','Nugegoda','0715259859','moshan@gmail.om'),(9,'Rashitha',1500,'2020-04-03','Malabe','0712343223','rashbatugedara@gmail.com'),(21,'Moshan',1000,'2020-10-10','Rathnapura','0716545443','rash@gmail.com'),(22,'Ganushka',2400,'2020-01-07','Colombo','0776555432','Ganushka123@gmail.com'),(23,'Yehan Malith',2000,'2019-01-06','Kottawa','0713244565','yehanmalith123@gmal.com'),(24,'Sandaru',1500,'2020-01-11','Homagama','0717688980','sandaru123@gmail.com'),(25,'Anu',1600,'2019-01-10','Rathnapura','077876565','anusaci@gmail.com'),(26,'Rahal',1500,'2020-01-10','Maharagama','07765654','manurahal@gmail.com'),(28,'Dewpiya',12000,'2020-01-10','Pamunuwa','0715567665','dew@gmail.com');
/*!40000 ALTER TABLE `payservice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-06 15:19:37
