-- --------------------------------------------------------
-- MySQL version:                     8.0.18 - MySQL Community Server - GPL
-- HeidiSQL version:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP DATABASE IF EXISTS `moikiitos`;
CREATE DATABASE IF NOT EXISTS `moikiitos` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `moikiitos`;

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
  `authority` varchar(255) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`authority`,`username`),
  KEY `FK2B0F132176151D24` (`username`),
  CONSTRAINT `FK2B0F132176151D24` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `authorities` (`authority`, `username`) VALUES
	('user', 'dd'),
	('user', 'hello'),
	('user', 'hellopmh'),
	('user', 'pmh'),
	('user', 'pry'),
	('user', 'test');

DROP TABLE IF EXISTS `follower`;
CREATE TABLE IF NOT EXISTS `follower` (
  `target_username` varchar(20) NOT NULL,
  `follower_username` varchar(20) NOT NULL,
  PRIMARY KEY (`follower_username`,`target_username`),
  KEY `FK15D7843E24DC7025` (`follower_username`),
  KEY `FK15D7843E86441012` (`target_username`),
  CONSTRAINT `FK15D7843E24DC7025` FOREIGN KEY (`follower_username`) REFERENCES `users` (`username`),
  CONSTRAINT `FK15D7843E86441012` FOREIGN KEY (`target_username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `follower` (`target_username`, `follower_username`) VALUES
	('pmh', 'hello'),
	('test', 'pmh'),
	('pmh', 'test');

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NOT NULL,
  `message` longtext NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK260CC076151D24` (`username`),
  CONSTRAINT `FK260CC076151D24` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

INSERT INTO `post` (`id`, `createdDate`, `message`, `username`) VALUES
	(1, '2023-05-14 10:45:19', 'this is  a test', 'pmh'),
	(2, '2023-05-14 12:30:54', 'test2', 'pmh'),
	(3, '2023-05-14 12:36:59', 'test2', 'test'),
	(4, '2023-05-14 14:56:52', 'test 3', 'pmh'),
	(5, '2023-05-14 15:10:41', 'test 3', 'pmh'),
	(6, '2023-05-14 15:11:01', 'test 3', 'pmh'),
	(7, '2023-05-14 15:11:07', 'test 3', 'pmh'),
	(8, '2023-05-14 15:21:26', 'hello pmh', 'pmh'),
	(9, '2023-05-16 01:23:36', 'abc', 'hellopmh'),
	(10, '2023-05-16 01:23:43', 'cdefg', 'hellopmh'),
	(11, '2023-05-16 01:35:38', 'test', 'hellopmh'),
	(12, '2023-05-16 15:17:58', 'hello', 'pmh'),
	(13, '2023-05-16 15:46:59', 'Hi PMH!', 'test'),
	(14, '2023-05-16 16:00:26', 'HI PMH 2 !', 'test'),
	(15, '2023-05-16 16:00:31', 'HI PMH 3 !', 'test'),
	(16, '2023-05-16 16:00:38', 'HI PMH 4 !', 'test'),
	(17, '2023-05-16 16:00:44', 'HI PMH 5 !', 'test'),
	(18, '2023-05-16 16:01:10', 'HI PMH 6 ! Nice Day', 'test'),
	(19, '2023-05-16 16:01:16', 'HI PMH 7 ! Nice Day', 'test'),
	(20, '2023-05-16 16:58:31', 'HI test!', 'pmh'),
	(22, '2023-05-17 14:15:25', 'You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters remaining You have 500 characters ', 'pmh');

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(20) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `password` varchar(80) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`username`, `enabled`, `password`, `email`) VALUES
	('dd', 1, '20637408a5bb23b17ec8bfb4f9eb66af9e45a82c7825f775ca634ad38091894f37c7b956a234a27c', 'dd@test.com'),
	('hello', 1, '20637408a5bb23b17ec8bfb4f9eb66af9e45a82c7825f775ca634ad38091894f37c7b956a234a27c', 'hello@test.com'),
	('hellopmh', 1, '20637408a5bb23b17ec8bfb4f9eb66af9e45a82c7825f775ca634ad38091894f37c7b956a234a27c', 'hellopmh@test.com'),
	('pmh', 1, '20637408a5bb23b17ec8bfb4f9eb66af9e45a82c7825f775ca634ad38091894f37c7b956a234a27c', 'pmh@test.com'),
	('pry', 1, '20637408a5bb23b17ec8bfb4f9eb66af9e45a82c7825f775ca634ad38091894f37c7b956a234a27c', 'pry@test.com'),
	('test', 1, '20637408a5bb23b17ec8bfb4f9eb66af9e45a82c7825f775ca634ad38091894f37c7b956a234a27c', 'test@test.com');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
