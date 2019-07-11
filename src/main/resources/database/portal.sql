-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: asgard-mysql
-- Generation Time: Jul 11, 2019 at 07:06 PM
-- Server version: 8.0.16
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bgames`
--

-- --------------------------------------------------------

--
-- Table structure for table `board_game`
--

CREATE TABLE `board_game` (
  `board_game_id` int(11) NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `designer` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `min_age` int(11) DEFAULT NULL,
  `players` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `playing_time` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `release_year` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `board_game_category`
--

CREATE TABLE `board_game_category` (
  `board_game_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `active`, `email`, `first_name`, `last_name`, `login`, `password`) VALUES
(1, 1, 'admin@gmail.com', 'Admin', 'Admin', 'admin', '$2a$10$Pl.mEJvC/1zsg7FFgC5ihe0ddvPNeY3ag8S8Gm2E50sgH6sE8rh22'),
(2, 1, 'test@gmail.com', 'Tester', 'Test', 'test', '$2a$10$TM92qxuC.39DpmnqX3hIxuQchUJIzUgh9h45JrU.3Alb.jzqgM5d2'),
(3, 1, 'test2@gmail.com', 'Tester', 'Test', 'test2', '$2a$10$TM92qxuC.39DpmnqX3hIxuQchUJIzUgh9h45JrU.3Alb.jzqgM5d2'),
(4, 1, 'test3@gmail.com', 'Tester', 'Test', 'test3', '$2a$10$TM92qxuC.39DpmnqX3hIxuQchUJIzUgh9h45JrU.3Alb.jzqgM5d2'),
(5, 0, 'test4@gmail.com', 'Tester', 'Test', 'test4', '$2a$10$TM92qxuC.39DpmnqX3hIxuQchUJIzUgh9h45JrU.3Alb.jzqgM5d2'),
(6, 1, 'test5@gmail.com', 'Tester', 'Test', 'test5', '$2a$10$rMQ3bRZF81x7glE4RWegauMihvnrnQw6T/Q./t.jEDMOQwF1mgB82'),
(7, 1, 'test6@gmail.com', 'Tester', 'Test', 'test6', '$2a$10$rMQ3bRZF81x7glE4RWegauMihvnrnQw6T/Q./t.jEDMOQwF1mgB82');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `board_game`
--
ALTER TABLE `board_game`
  ADD PRIMARY KEY (`board_game_id`);

--
-- Indexes for table `board_game_category`
--
ALTER TABLE `board_game_category`
  ADD KEY `FKkrnqpjg7uys41j5rpuj50aynd` (`category_id`),
  ADD KEY `FKset0ikuyeupb30084xlx3cf7n` (`board_game_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `board_game`
--
ALTER TABLE `board_game`
  MODIFY `board_game_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `board_game_category`
--
ALTER TABLE `board_game_category`
  ADD CONSTRAINT `FKkrnqpjg7uys41j5rpuj50aynd` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `FKset0ikuyeupb30084xlx3cf7n` FOREIGN KEY (`board_game_id`) REFERENCES `board_game` (`board_game_id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
