-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:8111
-- Generation Time: Nov 16, 2022 at 07:41 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `20game`
--

-- --------------------------------------------------------

--
-- Table structure for table `games`
--

CREATE TABLE `games` (
  `id` int(11) NOT NULL,
  `player1` int(11) NOT NULL,
  `player2` int(11) DEFAULT NULL,
  `winner` int(11) DEFAULT NULL,
  `invite_code` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `games`
--

INSERT INTO `games` (`id`, `player1`, `player2`, `winner`, `invite_code`) VALUES
(162, 17, NULL, NULL, NULL),
(163, 17, 3, 3, NULL),
(164, 3, 17, NULL, NULL),
(165, 17, 3, 3, NULL),
(166, 3, NULL, NULL, NULL),
(167, 3, NULL, NULL, NULL),
(168, 17, 3, 3, NULL),
(169, 3, 17, 17, NULL),
(170, 3, 17, 17, NULL),
(171, 3, 17, 17, NULL),
(172, 3, 17, NULL, NULL),
(173, 3, 17, 17, NULL),
(174, 3, 17, 17, NULL),
(175, 3, 17, 17, NULL),
(176, 3, 17, 17, NULL),
(177, 3, 17, 17, NULL),
(178, 3, 17, 17, NULL),
(179, 3, 17, 17, NULL),
(180, 3, 17, 17, NULL),
(181, 17, 3, 3, NULL),
(182, 17, 3, 3, NULL),
(183, 3, 17, 17, NULL),
(184, 17, 3, 3, NULL),
(185, 3, 17, 17, NULL),
(186, 17, 3, 3, NULL),
(187, 3, 17, 3, NULL),
(188, 3, 17, 17, NULL),
(189, 3, 17, 3, NULL),
(190, 3, NULL, NULL, NULL),
(191, 17, 3, 3, NULL),
(192, 3, 0, 0, NULL),
(193, 17, 0, 0, NULL),
(194, 3, 17, 3, NULL),
(195, 17, 3, 17, NULL),
(196, 3, 0, NULL, NULL),
(197, 3, 0, 0, NULL),
(198, 21, 0, 0, NULL),
(199, 3, 0, 0, NULL),
(200, 21, 0, 0, NULL),
(201, 3, 17, 17, NULL),
(202, 21, 3, 3, NULL),
(203, 17, 0, 0, NULL),
(204, 3, 0, NULL, NULL),
(205, 21, 0, 0, NULL),
(206, 21, 0, 0, NULL),
(207, 21, 0, 0, NULL),
(208, 21, 0, 0, NULL),
(209, 21, 0, NULL, NULL),
(210, 21, 0, 0, NULL),
(211, 21, 0, 0, NULL),
(212, 21, 0, 0, NULL),
(213, 21, NULL, NULL, NULL),
(214, 3, 17, 3, NULL),
(215, 3, 0, 3, NULL),
(216, 3, 0, 0, NULL),
(217, 3, 17, 3, NULL),
(218, 3, 0, 0, NULL),
(219, 21, 0, 21, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(0, 'computer', '555'),
(3, 'testAcc', '555'),
(17, 'testAcc5', '555'),
(19, 'testtt', '12'),
(20, 'test', '23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `games`
--
ALTER TABLE `games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=220;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
