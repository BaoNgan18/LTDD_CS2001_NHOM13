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
-- Table structure for table `truyen_yeu_thich_nguoi_dung`
--

DROP TABLE IF EXISTS `truyen_yeu_thich_nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `truyen_yeu_thich_nguoi_dung` (
  `truyen_id` bigint NOT NULL,
  `nguoidung_id` bigint NOT NULL,
  KEY `FKg1wg26fgyneohspi39g2ssrhf` (`nguoidung_id`),
  KEY `FKkyme8l1gno0hjxg5oi408o8mj` (`truyen_id`),
  CONSTRAINT `FKg1wg26fgyneohspi39g2ssrhf` FOREIGN KEY (`nguoidung_id`) REFERENCES `nguoi_dung` (`id`),
  CONSTRAINT `FKkyme8l1gno0hjxg5oi408o8mj` FOREIGN KEY (`truyen_id`) REFERENCES `truyen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truyen_yeu_thich_nguoi_dung`
--

LOCK TABLES `truyen_yeu_thich_nguoi_dung` WRITE;
/*!40000 ALTER TABLE `truyen_yeu_thich_nguoi_dung` DISABLE KEYS */;
INSERT INTO `truyen_yeu_thich_nguoi_dung` VALUES (2,1),(2,6),(1,6),(1,1),(1,5);
/*!40000 ALTER TABLE `truyen_yeu_thich_nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-25 12:30:01
