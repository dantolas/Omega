-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: omega
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `passHash` varchar(500) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (_binary 'o\«\Z\Ù\œ\Ó´YåY\Ï\◊\—','ucitel','ucitel@predmetu.jecna','$2a$08$ebRVcbC6LmBdGTzOX8.pPu0wPLs7l2zsjUk2X6FNfyTdRmocpzBGm','ÔøΩ\npÔøΩÔøΩÔøΩcÔøΩÔøΩ!QÔøΩ)c'),(_binary '%∑w\ıo\Óã7|9Ωv','testicek','testicek@email.com','$2a$08$ISetVK1C41vSa5RnBVskyOW8dsDldT5G21srTOy6h3S005b87nbGG','ÔøΩRÔøΩÔøΩÔøΩW·óæÔøΩÔøΩZ\ZÔøΩ'),(_binary '-\ÏJˇ\\–\ÓÑaåY\Ï\◊\—','kuta','kuta@spsejecna.cz','$2a$08$t/CBJXwDA6Jo8lW6AtOXe.WEOzzTl8u7wprDyglX5LFUY/L.3t8Dy','wÔøΩq&ÔøΩÔøΩÔøΩÔøΩÔøΩ]ÔøΩZ ÔøΩÔøΩ'),(_binary 'jEæÆ˝n\Ó®HåY\Ï\◊\—','john_doe','john.doe@gmail.com','$2a$08$dlGF/0FhIbjHTXyNRlYQGuYO83taITbmUaTOxKyv9hXypvXCr5k6q','ÔøΩge$ÔøΩÔøΩdƒ∫ÔøΩÔøΩn-ÔøΩÔøΩ'),(_binary '≤É\\m\0\ËÔÅçåY\Ï\◊\—','patcunt','patcunt@retard.com','$2a$08$7NJR5LDwbam1geJMorJPGutrXcpBsti8wXTRGZTQABiO1r9yzAd2y','…πÔøΩÔøΩÔøΩÔøΩoÔøΩÔøΩ\"YoÔøΩÔøΩ0'),(_binary '¯;\·å\Ôq\Ó∑HåY\Ï\◊\—','test','test@email.com','$2a$08$VWNTfmMB1wshGhY5CpreQO340C8IidmAC9wWsESlWZMHvzm5OSBDe','ÔøΩÔøΩ*u$ÔøΩÔøΩ,LÔøΩJ»äÔøΩ');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23  3:21:49
