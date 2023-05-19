-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 19, 2023 at 10:30 AM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Table structure for table `buildings`
--

DROP TABLE IF EXISTS `buildings`;
CREATE TABLE IF NOT EXISTS `buildings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `number_of_rooms` int NOT NULL,
  `occupied_rooms` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `buildings`
--

INSERT INTO `buildings` (`id`, `name`, `number_of_rooms`, `occupied_rooms`) VALUES
(1, 'Main Bloc', 30, 10),
(2, 'Urgence', 15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `chamber`
--

DROP TABLE IF EXISTS `chamber`;
CREATE TABLE IF NOT EXISTS `chamber` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  `isOccupied` tinyint(1) NOT NULL DEFAULT '0',
  `building` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chamber`
--

INSERT INTO `chamber` (`id`, `number`, `isOccupied`, `building`) VALUES
(1, 1, 1, 'Urgence'),
(2, 2, 1, 'Urgence'),
(3, 3, 1, 'Urgence'),
(4, 4, 1, 'Urgence'),
(5, 5, 0, 'Urgence'),
(6, 6, 0, 'Urgence'),
(7, 7, 0, 'Urgence'),
(8, 8, 0, 'Urgence'),
(9, 9, 0, 'Urgence'),
(10, 10, 0, 'Urgence'),
(11, 11, 0, 'Urgence'),
(12, 12, 1, 'Urgence'),
(13, 13, 0, 'Urgence'),
(14, 14, 0, 'Urgence'),
(15, 15, 0, 'Urgence'),
(16, 1, 1, 'Main Bloc'),
(17, 2, 1, 'Main Bloc'),
(18, 3, 1, 'Main Bloc'),
(19, 4, 1, 'Main Bloc'),
(20, 5, 1, 'Main Bloc'),
(21, 6, 1, 'Main Bloc'),
(22, 7, 0, 'Main Bloc'),
(23, 8, 0, 'Main Bloc'),
(24, 9, 1, 'Main Bloc'),
(25, 10, 0, 'Main Bloc'),
(26, 11, 0, 'Main Bloc'),
(27, 12, 0, 'Main Bloc'),
(28, 13, 0, 'Main Bloc'),
(29, 14, 0, 'Main Bloc'),
(30, 15, 1, 'Main Bloc'),
(31, 16, 0, 'Main Bloc'),
(32, 17, 0, 'Main Bloc'),
(33, 18, 0, 'Main Bloc'),
(34, 19, 0, 'Main Bloc'),
(35, 20, 0, 'Main Bloc'),
(36, 21, 0, 'Main Bloc'),
(37, 22, 0, 'Main Bloc'),
(38, 23, 1, 'Main Bloc'),
(39, 24, 0, 'Main Bloc'),
(40, 25, 0, 'Main Bloc'),
(41, 26, 0, 'Main Bloc'),
(42, 27, 0, 'Main Bloc'),
(43, 28, 0, 'Main Bloc'),
(44, 29, 0, 'Main Bloc'),
(45, 30, 0, 'Main Bloc');

-- --------------------------------------------------------

--
-- Table structure for table `maladies`
--

DROP TABLE IF EXISTS `maladies`;
CREATE TABLE IF NOT EXISTS `maladies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `maladies`
--

INSERT INTO `maladies` (`id`, `nom`) VALUES
(11, 'Cancer de l’estomac'),
(10, 'Tumeur hépatique'),
(15, 'Maladie de Crohn'),
(16, 'Appendicite'),
(18, 'Covid 24'),
(19, 'BING CHILLING');

-- --------------------------------------------------------

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
CREATE TABLE IF NOT EXISTS `managers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `managers`
--

INSERT INTO `managers` (`id`, `name`, `mdp`) VALUES
(1, 'AH', 'BH'),
(2, 'AH', 'BH'),
(3, 'AH', 'BH'),
(4, 'AHJ', 'K'),
(5, 'ah', 'bhh'),
(6, 'HJ', 'KK'),
(7, 'AH', 'BH'),
(8, 'AT', 'BT');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) NOT NULL,
  `sexe` char(1) NOT NULL,
  `nCin` int NOT NULL,
  `maladie` varchar(200) NOT NULL,
  `chamber` int NOT NULL,
  `phoneNumber` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`id`, `nom`, `sexe`, `nCin`, `maladie`, `chamber`, `phoneNumber`) VALUES
(1, 'EFZ', 'M', 12345678, 'GTYTH', 0, 1234567),
(2, 'AHL', 'M', 123456, '1', 12, 123),
(3, 'AHH', 'M', 12, 'hefizg', 133, 1234),
(4, 'AHJJ', 'M', 12349, 'hefizg', 5, 234),
(5, 'AGG', 'M', 12324, 'hefizg', 32, 21345),
(6, 'hj—', 'M', 1, 'hefizg', 0, 23),
(7, 'anhj', 'M', 111, 'hefizg', 4, 1234567),
(8, 'AHFLJ', 'M', 11112, 'hefizg', 14, 234321),
(9, 'hj—', 'M', 1, 'hefizg', 0, 23),
(10, 'FNION', 'M', 6, 'hefizg', 14, 23),
(11, 'FEZ', 'F', 3, 'hefizg', 0, 222),
(12, 'DFTJ', 'M', 177, 'JK', 0, 999),
(14, 'AHJF', 'M', 19, 'YESSINE', 0, 657),
(13, 'BJR', 'F', 112, 'FOJN%', 0, 1212),
(15, 'AIFUFBZ', 'M', 88, 'YESSINE', 0, 9740),
(16, 'EFLKHGY', 'M', 234, 'JKLF', 0, 43546758),
(17, 'JGFHJGEZB', 'M', 376, 'YESSINE', 0, 398647),
(18, 'KEFZGI', 'M', 213, 'el 3OT3OT', 0, 2134),
(19, 'KDHGJV', 'M', 76, 'el 3OT3OT', 0, 45346),
(20, 'VFJEZHV', 'M', 11, 'el 3OT3OT', 0, 3453),
(21, 'JHRFBKZ', 'M', 73, 'YESSINE', 0, 37865),
(22, 'BEFZIK', 'M', 332, 'YESSINE', 0, 4356),
(23, 'EKFZBJKZEF', 'M', 52, 'el 3OT3OT', 0, 3425),
(24, 'HZIEFG', 'M', 63, 'el 3OT3OT', 0, 38970),
(25, 'EEZFJHGB', 'M', 34, 'hefizg', 0, 2785),
(26, 'RHFGBK', 'M', 124, 'el 3OT3OT', 0, 2131),
(27, 'EKJZFGGU', 'M', 34, 'el 3OT3OT', 0, 325),
(28, 'FEJBJEZFK', 'M', 37, 'YESSINE', 0, 4536),
(29, 'FBZKBJ', 'M', 51, 'el 3OT3OT', 0, 3254364),
(30, 'EHGI', 'M', 19, 'el 3OT3OT', 0, 1234),
(31, 'FEVFZJH', 'M', 56, 'el 3OT3OT', 0, 2378),
(32, 'ZFJVJ', 'M', 87, 'el 3OT3OT', 0, 233),
(33, 'AHKZJRG', 'M', 7, 'el 3OT3OT', 0, 243),
(34, 'VEZHJ', 'M', 73, 'el 3OT3OT', 0, 214),
(35, 'EZKFJG', 'M', 54, 'el 3OT3OT', 0, 324),
(36, 'EZLHKFJIU', 'M', 328, 'el 3OT3OT', 0, 23),
(37, 'KFZJBEK', 'M', 30, 'el 3OT3OT', 0, 1234),
(55, 'DYFJ', 'M', 98765432, 'Appendicite', 12, 12345678),
(54, 'AH', 'M', 12345687, 'Cancer de l’estomac', 30, 22445561),
(53, 'AH', 'M', 12221, 'Covid 24', 19, 2345),
(52, 'AFZJHVB', 'M', 1241, 'covid', 18, 234),
(51, 'AHFL', 'M', 123, 'el 3OT3OT', 3, 132),
(50, 'Fruh', 'M', 123004, 'hefizg', 4, 213),
(56, 'ACHOUR', 'F', 12872006, 'Appendicite', 0, 12345678);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
