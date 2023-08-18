-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: roydb
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `truyen_the_loai`
--

DROP TABLE IF EXISTS `truyen_the_loai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `truyen_the_loai` (
  `theloai_id` bigint NOT NULL,
  `truyen_id` bigint NOT NULL,
  KEY `FK778ei5wrsfr858wjicjhfwkvh` (`theloai_id`),
  KEY `FK6xluvnsg3k6i0w62st3t31myo` (`truyen_id`),
  CONSTRAINT `FK6xluvnsg3k6i0w62st3t31myo` FOREIGN KEY (`truyen_id`) REFERENCES `truyen` (`id`),
  CONSTRAINT `FK778ei5wrsfr858wjicjhfwkvh` FOREIGN KEY (`theloai_id`) REFERENCES `the_loai` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truyen_the_loai`
--

LOCK TABLES `truyen_the_loai` WRITE;
/*!40000 ALTER TABLE `truyen_the_loai` DISABLE KEYS */;
INSERT INTO `truyen_the_loai` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(12,16),(13,16),(14,16),(11,17),(5,17),(14,17),(15,19),(6,19),(13,19),(3,21),(5,21),(15,21),(17,21),(3,20),(5,20),(15,20),(17,20);
/*!40000 ALTER TABLE `truyen_the_loai` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 14:55:11
