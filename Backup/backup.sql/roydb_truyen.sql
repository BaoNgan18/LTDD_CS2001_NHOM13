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
-- Table structure for table `truyen`
--

DROP TABLE IF EXISTS `truyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `truyen` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nguoidung_id` bigint DEFAULT NULL,
  `mo_ta_truyen` varchar(5000) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `anh_bia` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `ten_truyen` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `noi_dung_chuong` text COLLATE utf8mb4_0900_as_ci,
  `the_loai` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo5ip68smhc6h1l32mwd6aqbk0` (`nguoidung_id`),
  CONSTRAINT `FKo5ip68smhc6h1l32mwd6aqbk0` FOREIGN KEY (`nguoidung_id`) REFERENCES `nguoi_dung` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truyen`
--

LOCK TABLES `truyen` WRITE;
/*!40000 ALTER TABLE `truyen` DISABLE KEYS */;
INSERT INTO `truyen` VALUES (1,1,'Mo ta 2',NULL,'truyen 2','Content 2','Bí ẩn, Cổ điển, Hài hước'),(2,1,'mo ta 1',NULL,'Truyen 1','content 1','Bí ẩn, Cổ điển'),(3,1,'mo ta 1',NULL,'Truyen 1','content 1','Bí ẩn, Cổ điển'),(4,1,'mo ta 1',NULL,'Truyen 1','content 1','Bí ẩn, Cổ điển'),(5,1,'mo ta 1',NULL,'Truyen 1','content 1',NULL),(6,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep1',NULL),(8,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(9,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(10,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(11,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(12,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(14,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(15,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2',NULL),(16,2,'The Story of Hy',NULL,'Hy\'s Story','The journey to find brother ep2','Fanfiction, Hành động, Kinh dị'),(17,2,'The Story of Mai',NULL,'Mai\'s Story','The journey to find brother ep3','Viễn tưởng, Lãng mạn, Kinh dị'),(19,6,'The Story of Khanh',NULL,'Khanh\'s Story','The journey to find brother ep11','Ngẫu nhiên, Siêu nhiên, Hành động'),(20,2,'Tác giả: Vu Triết. Chương 1.',NULL,'Cách cách bất nhập','ABC...','Hài hước, Lãng mạn, Ngẫu nhiên, Trưởng thành'),(21,2,'Tác giả: Vu Triết. Chương 2.',NULL,'Cách cách bất nhập','XYZ...','Hài hước, Lãng mạn, Ngẫu nhiên, Trưởng thành');
/*!40000 ALTER TABLE `truyen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18  9:31:00
