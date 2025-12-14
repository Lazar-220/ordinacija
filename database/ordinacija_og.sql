-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2025 at 02:54 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `a_pomocna_ord`
--
CREATE DATABASE IF NOT EXISTS `a_pomocna_ord` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `a_pomocna_ord`;

-- --------------------------------------------------------

--
-- Table structure for table `fizijatar`
--

DROP TABLE IF EXISTS `fizijatar`;
CREATE TABLE IF NOT EXISTS `fizijatar` (
  `idFizijatra` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  PRIMARY KEY (`idFizijatra`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fizijatar`
--

INSERT INTO `fizijatar` (`idFizijatra`, `ime`, `prezime`, `korisnickoIme`, `sifra`) VALUES
(1, 'Vera', 'Veric', 'vera', 'v1admin'),
(2, 'Kika', 'Kikic', 'kika', 'k1'),
(3, 'Cika', 'Cikic', 'cika', 'c1'),
(9, 'Lazar', 'Milosavljevic', 'Laki', 'l1'),
(10, 'Marko', 'Markovic', 'Mare', 'm1'),
(11, 'Aleksa', 'Aleksic', 'aleksa', 'a2');

-- --------------------------------------------------------

--
-- Table structure for table `fizijatar_specijalista`
--

DROP TABLE IF EXISTS `fizijatar_specijalista`;
CREATE TABLE IF NOT EXISTS `fizijatar_specijalista` (
  `idSertifikat` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumIzdavanja` date NOT NULL,
  `fizijatar_id` bigint(20) NOT NULL,
  `specijalizacija_id` bigint(20) NOT NULL,
  PRIMARY KEY (`idSertifikat`),
  KEY `fizijatar_id` (`fizijatar_id`),
  KEY `specijalizacija_id` (`specijalizacija_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fizijatar_specijalista`
--

INSERT INTO `fizijatar_specijalista` (`idSertifikat`, `datumIzdavanja`, `fizijatar_id`, `specijalizacija_id`) VALUES
(1, '2024-11-12', 3, 1),
(2, '2025-01-24', 2, 2),
(3, '2023-07-01', 1, 3),
(4, '2023-01-10', 9, 1),
(5, '2021-05-25', 9, 3),
(6, '2020-09-09', 10, 2),
(7, '2019-08-08', 11, 3);

-- --------------------------------------------------------

--
-- Table structure for table `nalaz`
--

DROP TABLE IF EXISTS `nalaz`;
CREATE TABLE IF NOT EXISTS `nalaz` (
  `idNalaz` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumIzdavanja` date NOT NULL,
  `opisNalaza` varchar(250) NOT NULL,
  `ukupnaCena` double NOT NULL,
  `fizijatar_id` bigint(20) NOT NULL,
  `pacijent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`idNalaz`),
  KEY `fizijatar_id` (`fizijatar_id`),
  KEY `pacijent_id` (`pacijent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nalaz`
--

INSERT INTO `nalaz` (`idNalaz`, `datumIzdavanja`, `opisNalaza`, `ukupnaCena`, `fizijatar_id`, `pacijent_id`) VALUES
(1, '2025-07-05', 'Pacijent se žali na bol i ograničen pokret u desnom ramenu, posebno pri podizanju ruke iznad glave. Kliničkim pregledom i ultrazvukom utvrđena je parcijalna ruptura tetive.', 10000, 1, 1),
(4, '2023-07-10', 'Pregledom i ultrazvukom utvrđena je parcijalna ruptura levog pektoralisa.', 6000, 3, 1),
(5, '2024-10-10', 'Nagnjecenje levog lakta prilikom pada', 6000, 9, 1),
(9, '2025-08-08', 'Uganuce skocnog zgloba.', 6000, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pacijent`
--

DROP TABLE IF EXISTS `pacijent`;
CREATE TABLE IF NOT EXISTS `pacijent` (
  `idPacijent` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tipPacijenta_id` bigint(20) NOT NULL,
  PRIMARY KEY (`idPacijent`),
  KEY `tipPacijenta_id` (`tipPacijenta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pacijent`
--

INSERT INTO `pacijent` (`idPacijent`, `ime`, `prezime`, `email`, `tipPacijenta_id`) VALUES
(1, 'Zika', 'Zikic', 'zika@gmail.com', 3),
(2, 'Mika', 'Mikic', 'mika@gmail.com', 2),
(22, 'ime', 'prezime', 'mail', 7);

-- --------------------------------------------------------

--
-- Table structure for table `specijalizacija`
--

DROP TABLE IF EXISTS `specijalizacija`;
CREATE TABLE IF NOT EXISTS `specijalizacija` (
  `idSpecijalizacija` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `institucija` varchar(100) NOT NULL,
  PRIMARY KEY (`idSpecijalizacija`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `specijalizacija`
--

INSERT INTO `specijalizacija` (`idSpecijalizacija`, `naziv`, `institucija`) VALUES
(1, 'Fizioterapeut', 'Visoka zdravstvena škola strukovnih studija u Nišu'),
(2, 'Ergoterapeut', 'Visoka zdravstvena škola strukovnih studija Beograd'),
(3, 'Kineziterapeut', 'Fakultet sporta i fizičkog vaspitanja Niš');

-- --------------------------------------------------------

--
-- Table structure for table `stavka_nalaza`
--

DROP TABLE IF EXISTS `stavka_nalaza`;
CREATE TABLE IF NOT EXISTS `stavka_nalaza` (
  `rb` bigint(20) NOT NULL,
  `nalaz_id` bigint(20) NOT NULL,
  `cena` double NOT NULL,
  `terapija_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rb`,`nalaz_id`),
  KEY `terapija_id` (`terapija_id`),
  KEY `nalaz_id` (`nalaz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stavka_nalaza`
--

INSERT INTO `stavka_nalaza` (`rb`, `nalaz_id`, `cena`, `terapija_id`) VALUES
(1, 1, 4000, 1),
(1, 4, 4000, 1),
(1, 5, 2000, 3),
(1, 9, 4000, 2),
(2, 1, 2000, 3),
(2, 4, 2000, 3),
(2, 5, 4000, 2),
(2, 9, 2000, 3),
(3, 1, 4000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `terapija`
--

DROP TABLE IF EXISTS `terapija`;
CREATE TABLE IF NOT EXISTS `terapija` (
  `idTerapija` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `cena` double NOT NULL,
  `opis` varchar(250) NOT NULL,
  PRIMARY KEY (`idTerapija`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `terapija`
--

INSERT INTO `terapija` (`idTerapija`, `naziv`, `cena`, `opis`) VALUES
(1, 'Elektroterapija', 4000, 'Elektroterapija je terapijska metoda koja pomoću niskonaponske električne struje smanjuje bol, upalu i poboljšava cirkulaciju.'),
(2, 'Kineziterapija', 4000, 'Kineziterapija predstavlja primenu specifičnih pokreta i vežbi sa ciljem obnavljanja funkcije, povećanja snage i pokretljivosti povređenog dela tela.'),
(3, 'Redovni pregledi', 2000, 'Pregledi tokom oporavka od povrede.');

-- --------------------------------------------------------

--
-- Table structure for table `tip_pacijenta`
--

DROP TABLE IF EXISTS `tip_pacijenta`;
CREATE TABLE IF NOT EXISTS `tip_pacijenta` (
  `idTipPacijenta` bigint(20) NOT NULL AUTO_INCREMENT,
  `starosnaDob` varchar(50) NOT NULL,
  `pol` varchar(20) NOT NULL,
  PRIMARY KEY (`idTipPacijenta`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tip_pacijenta`
--

INSERT INTO `tip_pacijenta` (`idTipPacijenta`, `starosnaDob`, `pol`) VALUES
(2, 'odrasli', 'zensko'),
(3, 'penzioneri', 'musko'),
(5, 'odrasli', 'musko'),
(6, 'deca', 'zensko'),
(7, 'deca', 'musko'),
(9, 'penzioneri', 'zensko');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fizijatar_specijalista`
--
ALTER TABLE `fizijatar_specijalista`
  ADD CONSTRAINT `fizijatar_specijalista_ibfk_1` FOREIGN KEY (`fizijatar_id`) REFERENCES `fizijatar` (`idFizijatra`),
  ADD CONSTRAINT `fizijatar_specijalista_ibfk_2` FOREIGN KEY (`specijalizacija_id`) REFERENCES `specijalizacija` (`idSpecijalizacija`);

--
-- Constraints for table `nalaz`
--
ALTER TABLE `nalaz`
  ADD CONSTRAINT `nalaz_ibfk_1` FOREIGN KEY (`fizijatar_id`) REFERENCES `fizijatar` (`idFizijatra`),
  ADD CONSTRAINT `nalaz_ibfk_2` FOREIGN KEY (`pacijent_id`) REFERENCES `pacijent` (`idPacijent`);

--
-- Constraints for table `pacijent`
--
ALTER TABLE `pacijent`
  ADD CONSTRAINT `pacijent_ibfk_1` FOREIGN KEY (`tipPacijenta_id`) REFERENCES `tip_pacijenta` (`idTipPacijenta`);

--
-- Constraints for table `stavka_nalaza`
--
ALTER TABLE `stavka_nalaza`
  ADD CONSTRAINT `stavka_nalaza_ibfk_1` FOREIGN KEY (`terapija_id`) REFERENCES `terapija` (`idTerapija`),
  ADD CONSTRAINT `stavka_nalaza_ibfk_2` FOREIGN KEY (`nalaz_id`) REFERENCES `nalaz` (`idNalaz`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
