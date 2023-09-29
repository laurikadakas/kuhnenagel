CREATE DATABASE `quizapplication` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `questions` (
  `questionid` int NOT NULL AUTO_INCREMENT,
  `topic` varchar(45) DEFAULT NULL,
  `difficulty` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`questionid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `responses` (
  `responseid` int NOT NULL AUTO_INCREMENT,
  `questionid` int DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `iscorrect` tinyint DEFAULT NULL,
  PRIMARY KEY (`responseid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
