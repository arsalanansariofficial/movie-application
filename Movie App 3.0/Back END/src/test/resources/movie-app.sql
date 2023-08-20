-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: movie-app
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `city_id` varchar(255) NOT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `UK_djnk44fptegbsu6drhc9xvlfj` (`city_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES ('delhi','delhi'),('mumbai','mumbai');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` varchar(255) NOT NULL,
  `artist_url` varchar(255) DEFAULT NULL,
  `artists` varchar(255) DEFAULT NULL,
  `critics_rating` int DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  `release_date` datetime(6) NOT NULL,
  `story_line` varchar(255) DEFAULT NULL,
  `trailer_url` varchar(255) DEFAULT NULL,
  `wikipedia_url` varchar(255) DEFAULT NULL,
  `released_status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  UNIQUE KEY `UK_ogko73h53na6cby69s2l8747a` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('movie-1','/posters/released/movie-1.png','Movie Artist',3,120,'action','Movie 1','/posters/released/movie-1.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary ''),('movie-2','/posters/released/movie-2.png','Movie Artist',3,120,'action','Movie 2','/posters/released/movie-2.png','2021-01-10 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary ''),('movie-3','/posters/released/movie-3.png','Movie Artist',3,120,'action','Movie 3','/posters/released/movie-3.png','2021-01-20 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary ''),('upcoming-movie-1','/posters/upcoming/movie-1.png','Movie Artist',3,120,'action','Upcoming Movie 1','/posters/upcoming/movie-1.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-10','/posters/upcoming/movie-10.png','Movie Artist',3,120,'action','Upcoming Movie 10','/posters/upcoming/movie-10.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-2','/posters/upcoming/movie-2.png','Movie Artist',3,120,'action','Upcoming Movie 2','/posters/upcoming/movie-2.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-3','/posters/upcoming/movie-3.png','Movie Artist',3,120,'action','Upcoming Movie 3','/posters/upcoming/movie-3.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-4','/posters/upcoming/movie-4.png','Movie Artist',3,120,'action','Upcoming Movie 4','/posters/upcoming/movie-4.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-5','/posters/upcoming/movie-5.png','Movie Artist',3,120,'action','Upcoming Movie 5','/posters/upcoming/movie-5.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-6','/posters/upcoming/movie-6.png','Movie Artist',3,120,'action','Upcoming Movie 6','/posters/upcoming/movie-6.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-7','/posters/upcoming/movie-7.png','Movie Artist',3,120,'action','Upcoming Movie 7','/posters/upcoming/movie-7.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-8','/posters/upcoming/movie-8.png','Movie Artist',3,120,'action','Upcoming Movie 8','/posters/upcoming/movie-8.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0'),('upcoming-movie-9','/posters/upcoming/movie-9.png','Movie Artist',3,120,'action','Upcoming Movie 9','/posters/upcoming/movie-9.png','2021-01-01 10:00:00.000000','Movie storyline','trailer_url','wikipedia_url',_binary '\0');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_show`
--

DROP TABLE IF EXISTS `movie_show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_show` (
  `movie_show_id` varchar(255) NOT NULL,
  `show_language` varchar(255) NOT NULL,
  `show_time` datetime(6) NOT NULL,
  `movie_theatre_id` varchar(255) DEFAULT NULL,
  `seats_available` int DEFAULT NULL,
  PRIMARY KEY (`movie_show_id`),
  KEY `FKh0fy3unt2g3e04j0q7h5w15yl` (`movie_theatre_id`),
  CONSTRAINT `FKh0fy3unt2g3e04j0q7h5w15yl` FOREIGN KEY (`movie_theatre_id`) REFERENCES `movie_theatre` (`movie_theatre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_show`
--

LOCK TABLES `movie_show` WRITE;
/*!40000 ALTER TABLE `movie_show` DISABLE KEYS */;
INSERT INTO `movie_show` VALUES ('show-english: [movie-1: pvr-delhi]','english','2021-01-01 11:00:00.000000','movie-1: pvr-delhi',50),('show-english: [movie-1: pvr-mumbai]','english','2021-01-01 11:00:00.000000','movie-1: pvr-mumbai',50),('show-english: [movie-1: waves-delhi]','english','2021-01-01 11:00:00.000000','movie-1: waves-delhi',50),('show-english: [movie-1: waves-mumbai]','english','2021-01-01 11:00:00.000000','movie-1: waves-mumbai',50),('show-english: [movie-2: pvr-delhi]','english','2021-01-10 11:00:00.000000','movie-2: pvr-delhi',50),('show-english: [movie-2: pvr-mumbai]','english','2021-01-10 11:00:00.000000','movie-2: pvr-mumbai',50),('show-english: [movie-2: waves-delhi]','english','2021-01-10 11:00:00.000000','movie-2: waves-delhi',50),('show-english: [movie-2: waves-mumbai]','english','2021-01-10 11:00:00.000000','movie-2: waves-mumbai',50),('show-english: [movie-3: pvr-delhi]','english','2021-01-20 11:00:00.000000','movie-3: pvr-delhi',50),('show-english: [movie-3: pvr-mumbai]','english','2021-01-20 11:00:00.000000','movie-3: pvr-mumbai',50),('show-english: [movie-3: waves-delhi]','english','2021-01-20 11:00:00.000000','movie-3: waves-delhi',50),('show-english: [movie-3: waves-mumbai]','english','2021-01-20 11:00:00.000000','movie-3: waves-mumbai',50),('show-hindi: [movie-1: pvr-delhi]','hindi','2021-01-01 10:00:00.000000','movie-1: pvr-delhi',50),('show-hindi: [movie-1: pvr-mumbai]','hindi','2021-01-01 10:00:00.000000','movie-1: pvr-mumbai',50),('show-hindi: [movie-1: waves-delhi]','hindi','2021-01-01 10:00:00.000000','movie-1: waves-delhi',50),('show-hindi: [movie-1: waves-mumbai]','hindi','2021-01-01 10:00:00.000000','movie-1: waves-mumbai',50),('show-hindi: [movie-2: pvr-delhi]','hindi','2021-01-10 10:00:00.000000','movie-2: pvr-delhi',50),('show-hindi: [movie-2: pvr-mumbai]','hindi','2021-01-10 10:00:00.000000','movie-2: pvr-mumbai',50),('show-hindi: [movie-2: waves-delhi]','hindi','2021-01-10 10:00:00.000000','movie-2: waves-delhi',50),('show-hindi: [movie-2: waves-mumbai]','hindi','2021-01-10 10:00:00.000000','movie-2: waves-mumbai',50),('show-hindi: [movie-3: pvr-delhi]','hindi','2021-01-20 10:00:00.000000','movie-3: pvr-delhi',50),('show-hindi: [movie-3: pvr-mumbai]','hindi','2021-01-20 10:00:00.000000','movie-3: pvr-mumbai',50),('show-hindi: [movie-3: waves-delhi]','hindi','2021-01-20 10:00:00.000000','movie-3: waves-delhi',50),('show-hindi: [movie-3: waves-mumbai]','hindi','2021-01-20 10:00:00.000000','movie-3: waves-mumbai',50);
/*!40000 ALTER TABLE `movie_show` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_theatre`
--

DROP TABLE IF EXISTS `movie_theatre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_theatre` (
  `movie_theatre_id` varchar(255) NOT NULL,
  `movie_id` varchar(255) DEFAULT NULL,
  `theatre_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_theatre_id`),
  KEY `FK1incw28ijb0lec9scnnu4jtvj` (`movie_id`),
  KEY `FKfuax2kbjiopy831d5r4x8ef41` (`theatre_id`),
  CONSTRAINT `FK1incw28ijb0lec9scnnu4jtvj` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  CONSTRAINT `FKfuax2kbjiopy831d5r4x8ef41` FOREIGN KEY (`theatre_id`) REFERENCES `theatre` (`theatre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_theatre`
--

LOCK TABLES `movie_theatre` WRITE;
/*!40000 ALTER TABLE `movie_theatre` DISABLE KEYS */;
INSERT INTO `movie_theatre` VALUES ('movie-1: pvr-delhi','movie-1','pvr-delhi'),('movie-1: pvr-mumbai','movie-1','pvr-mumbai'),('movie-1: waves-delhi','movie-1','waves-delhi'),('movie-1: waves-mumbai','movie-1','waves-mumbai'),('movie-2: pvr-delhi','movie-2','pvr-delhi'),('movie-2: pvr-mumbai','movie-2','pvr-mumbai'),('movie-2: waves-delhi','movie-2','waves-delhi'),('movie-2: waves-mumbai','movie-2','waves-mumbai'),('movie-3: pvr-delhi','movie-3','pvr-delhi'),('movie-3: pvr-mumbai','movie-3','pvr-mumbai'),('movie-3: waves-delhi','movie-3','waves-delhi'),('movie-3: waves-mumbai','movie-3','waves-mumbai');
/*!40000 ALTER TABLE `movie_theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre`
--

DROP TABLE IF EXISTS `theatre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatre` (
  `theatre_id` varchar(255) NOT NULL,
  `theatre_name` varchar(255) NOT NULL,
  `city_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`theatre_id`),
  KEY `FKqfrea71qlwgmpbyrmldr5iln3` (`city_id`),
  CONSTRAINT `FKqfrea71qlwgmpbyrmldr5iln3` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre`
--

LOCK TABLES `theatre` WRITE;
/*!40000 ALTER TABLE `theatre` DISABLE KEYS */;
INSERT INTO `theatre` VALUES ('pvr-delhi','pvr-delhi','delhi'),('pvr-mumbai','pvr-mumbai','mumbai'),('waves-delhi','waves-delhi','delhi'),('waves-mumbai','waves-mumbai','mumbai');
/*!40000 ALTER TABLE `theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` varchar(255) NOT NULL,
  `city_id` varchar(255) NOT NULL,
  `movie_id` varchar(255) NOT NULL,
  `movie_show_id` varchar(255) NOT NULL,
  `movie_show_language` varchar(255) NOT NULL,
  `number_of_tickets` varchar(255) NOT NULL,
  `theatre_id` varchar(255) NOT NULL,
  `ticket_price` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `access_token` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`access_token`),
  KEY `FKtq6tin4uayw3fwhspdukad20p` (`email`),
  CONSTRAINT `FKtq6tin4uayw3fwhspdukad20p` FOREIGN KEY (`email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('first-user@example.com','first user','$2a$10$4OpiZQqrXtY243ocO92LsuMYE7YafzZAeiNm9CmN0G4ty1m9g5ZLO','9876543210','ROLE_USER'),('user@example.com','user','$2a$10$mkDS74XMhzgpka8r6O84GuNY9Uo15cvFU81L9XaMSuiwWxFv8/IXi','1234567890','ROLE_USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-13  6:21:50
