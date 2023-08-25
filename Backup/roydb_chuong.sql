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
-- Table structure for table `chuong`
--

DROP TABLE IF EXISTS `chuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuong` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ten_chuong` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `noi_dung_chuong` text COLLATE utf8mb4_0900_as_ci,
  `truyen_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4it8i93xrlnmgjv7s137vdht9` (`truyen_id`),
  CONSTRAINT `FK4it8i93xrlnmgjv7s137vdht9` FOREIGN KEY (`truyen_id`) REFERENCES `truyen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuong`
--

LOCK TABLES `chuong` WRITE;
/*!40000 ALTER TABLE `chuong` DISABLE KEYS */;
INSERT INTO `chuong` VALUES (1,'Con cáo và chùm nho','Truyện kể về chú ếch sống lâu đời ở trong một cái giếng, tiếng kêu của nó khiến các loài vật khác như ốc, nhái sợ hãi. Chính vì ở dưới đáy của giếng nên chú ếch chỉ nhìn thấy một khoảng trời nhỏ và nghĩ rằng bầu trời chỉ bằng chiếc vung.\nMột ngày nọ, mưa rất to đã làm nước trong giếng đầy lên và đẩy ếch ra khỏi miệng giếng. Ở bên ngoài, ếch ta nhận ra rằng thế giới thật sự rộng lớn nhưng vẫn không tin vào mắt mình và tiếp tục kêu lên để ra oai. Tuy nhiên, không một ai quan tâm đến tiếng kêu của chú và ếch đã bị một chú trâu đi ngang qua dẫm chết.\nCâu truyện cũng muốn nhắc nhở rằng, chúng ta cần lắng nghe và học từ thế giới xung quanh, mở rộng kiến thức và tư duy của mình để trở thành một người có tầm nhìn rộng và hiểu biết sâu sắc hơn.',1),(2,'Ếch ngồi đáy giếng','Truyện kể về chú ếch sống lâu đời ở trong một cái giếng, tiếng kêu của nó khiến các loài vật khác như ốc, nhái sợ hãi. Chính vì ở dưới đáy của giếng nên chú ếch chỉ nhìn thấy một khoảng trời nhỏ và nghĩ rằng bầu trời chỉ bằng chiếc vung.\nMột ngày nọ, mưa rất to đã làm nước trong giếng đầy lên và đẩy ếch ra khỏi miệng giếng. Ở bên ngoài, ếch ta nhận ra rằng thế giới thật sự rộng lớn nhưng vẫn không tin vào mắt mình và tiếp tục kêu lên để ra oai. Tuy nhiên, không một ai quan tâm đến tiếng kêu của chú và ếch đã bị một chú trâu đi ngang qua dẫm chết.\nCâu truyện cũng muốn nhắc nhở rằng, chúng ta cần lắng nghe và học từ thế giới xung quanh, mở rộng kiến thức và tư duy của mình để trở thành một người có tầm nhìn rộng và hiểu biết sâu sắc hơn.',1),(3,'Genshin Impact','Truyện kể về chú ếch sống lâu đời ở trong một cái giếng, tiếng kêu của nó khiến các loài vật khác như ốc, nhái sợ hãi. Chính vì ở dưới đáy của giếng nên chú ếch chỉ nhìn thấy một khoảng trời nhỏ và nghĩ rằng bầu trời chỉ bằng chiếc vung.\nMột ngày nọ, mưa rất to đã làm nước trong giếng đầy lên và đẩy ếch ra khỏi miệng giếng. Ở bên ngoài, ếch ta nhận ra rằng thế giới thật sự rộng lớn nhưng vẫn không tin vào mắt mình và tiếp tục kêu lên để ra oai. Tuy nhiên, không một ai quan tâm đến tiếng kêu của chú và ếch đã bị một chú trâu đi ngang qua dẫm chết.\nCâu truyện cũng muốn nhắc nhở rằng, chúng ta cần lắng nghe và học từ thế giới xung quanh, mở rộng kiến thức và tư duy của mình để trở thành một người có tầm nhìn rộng và hiểu biết sâu sắc hơn.',1),(4,'Genshin Impact 1','Truyện kể về chú ếch sống lâu đời ở trong một cái giếng, tiếng kêu của nó khiến các loài vật khác như ốc, nhái sợ hãi. Chính vì ở dưới đáy của giếng nên chú ếch chỉ nhìn thấy một khoảng trời nhỏ và nghĩ rằng bầu trời chỉ bằng chiếc vung.\nMột ngày nọ, mưa rất to đã làm nước trong giếng đầy lên và đẩy ếch ra khỏi miệng giếng. Ở bên ngoài, ếch ta nhận ra rằng thế giới thật sự rộng lớn nhưng vẫn không tin vào mắt mình và tiếp tục kêu lên để ra oai. Tuy nhiên, không một ai quan tâm đến tiếng kêu của chú và ếch đã bị một chú trâu đi ngang qua dẫm chết.\nCâu truyện cũng muốn nhắc nhở rằng, chúng ta cần lắng nghe và học từ thế giới xung quanh, mở rộng kiến thức và tư duy của mình để trở thành một người có tầm nhìn rộng và hiểu biết sâu sắc hơn.',2),(5,'Genshin Impact 2','Truyện kể về chú ếch sống lâu đời ở trong một cái giếng, tiếng kêu của nó khiến các loài vật khác như ốc, nhái sợ hãi. Chính vì ở dưới đáy của giếng nên chú ếch chỉ nhìn thấy một khoảng trời nhỏ và nghĩ rằng bầu trời chỉ bằng chiếc vung.\nMột ngày nọ, mưa rất to đã làm nước trong giếng đầy lên và đẩy ếch ra khỏi miệng giếng. Ở bên ngoài, ếch ta nhận ra rằng thế giới thật sự rộng lớn nhưng vẫn không tin vào mắt mình và tiếp tục kêu lên để ra oai. Tuy nhiên, không một ai quan tâm đến tiếng kêu của chú và ếch đã bị một chú trâu đi ngang qua dẫm chết.\nCâu truyện cũng muốn nhắc nhở rằng, chúng ta cần lắng nghe và học từ thế giới xung quanh, mở rộng kiến thức và tư duy của mình để trở thành một người có tầm nhìn rộng và hiểu biết sâu sắc hơn.',2),(6,'Genshin Impact 3','Truyện kể về chú ếch sống lâu đời ở trong một cái giếng, tiếng kêu của nó khiến các loài vật khác như ốc, nhái sợ hãi. Chính vì ở dưới đáy của giếng nên chú ếch chỉ nhìn thấy một khoảng trời nhỏ và nghĩ rằng bầu trời chỉ bằng chiếc vung.\nMột ngày nọ, mưa rất to đã làm nước trong giếng đầy lên và đẩy ếch ra khỏi miệng giếng. Ở bên ngoài, ếch ta nhận ra rằng thế giới thật sự rộng lớn nhưng vẫn không tin vào mắt mình và tiếp tục kêu lên để ra oai. Tuy nhiên, không một ai quan tâm đến tiếng kêu của chú và ếch đã bị một chú trâu đi ngang qua dẫm chết.\nCâu truyện cũng muốn nhắc nhở rằng, chúng ta cần lắng nghe và học từ thế giới xung quanh, mở rộng kiến thức và tư duy của mình để trở thành một người có tầm nhìn rộng và hiểu biết sâu sắc hơn.',2);
/*!40000 ALTER TABLE `chuong` ENABLE KEYS */;
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
