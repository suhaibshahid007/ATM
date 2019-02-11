-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Feb 11, 2019 at 03:36 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `atm`
--

-- --------------------------------------------------------

--
-- Table structure for table `balance_detail`
--

CREATE TABLE `balance_detail` (
  `id` int(11) NOT NULL,
  `card_num` int(11) NOT NULL,
  `total_amount` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `balance_detail`
--

INSERT INTO `balance_detail` (`id`, `card_num`, `total_amount`) VALUES
(2, 128344, 500),
(3, 144076, 4500),
(4, 1572, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `bill_details`
--

CREATE TABLE `bill_details` (
  `bill_id` int(11) NOT NULL,
  `company_account` int(11) NOT NULL,
  `card_Num` int(11) NOT NULL,
  `company_name` varchar(31) NOT NULL,
  `bill_number` varchar(50) NOT NULL,
  `bill_amount` int(11) NOT NULL,
  `time` varchar(31) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_details`
--

INSERT INTO `bill_details` (`bill_id`, `company_account`, `card_Num`, `company_name`, `bill_number`, `bill_amount`, `time`) VALUES
(4, 16819, 128344, 'Phone', '1875', 1000, '25-07-2017'),
(5, 19658, 128344, 'Phone', '1900', 1000, '25-07-2017'),
(6, 14378, 128344, 'Sui Gas', '18998', 1000, '25-07-2017'),
(7, 15322, 144076, 'Electricity', '1454', 1200, '25-07-2017'),
(8, 18319, 144076, 'Electricity', '1655', 1200, '25-07-2017'),
(9, 23678964, 128344, 'Sui Gas', '0000', 3000, '2019-02-11 11:19:55'),
(9000, 33476868, 128344, 'Ptcl', '9000', 1000, '2019-02-11 11:22:43');

-- --------------------------------------------------------

--
-- Table structure for table `deposit`
--

CREATE TABLE `deposit` (
  `depo_id` int(11) NOT NULL,
  `depo_amount` int(11) NOT NULL,
  `depo_time` varchar(31) NOT NULL,
  `card_num` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`depo_id`, `depo_amount`, `depo_time`, `card_num`) VALUES
(1, 1000, 'abcd', 128344),
(2, 1000, 'now()', 128344),
(4, 1000, 'acb', 1572),
(5, 10000, 'acb', 1572),
(6, 2000, '2019-02-11 09:25:04', 128344),
(7, 500, '2019-02-11 09:44:54', 128344),
(8, 1000, '2019-02-11 09:48:23', 128344),
(9, 2000, '2019-02-11 10:01:58', 128344),
(10, 1000, '2019-02-11 10:19:40', 128344),
(11, 500, '2019-02-11 10:42:48', 128344);

-- --------------------------------------------------------

--
-- Table structure for table `fund_transfer_detail`
--

CREATE TABLE `fund_transfer_detail` (
  `fund_id` int(100) NOT NULL,
  `sender_card_num` varchar(100) NOT NULL,
  `receiver_card_num` varchar(100) NOT NULL,
  `total_amount_transfer` varchar(100) NOT NULL,
  `transfer_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fund_transfer_detail`
--

INSERT INTO `fund_transfer_detail` (`fund_id`, `sender_card_num`, `receiver_card_num`, `total_amount_transfer`, `transfer_time`) VALUES
(1, '128344', '144076 ', '500', '2019-02-11 12:50:14');

-- --------------------------------------------------------

--
-- Table structure for table `pin_change`
--

CREATE TABLE `pin_change` (
  `pin_id` int(5) NOT NULL,
  `old_pin` int(5) NOT NULL,
  `new_pin` int(5) NOT NULL,
  `verify_pin` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pin_change`
--

INSERT INTO `pin_change` (`pin_id`, `old_pin`, `new_pin`, `verify_pin`) VALUES
(1, 9754, 1234, 1234),
(2, 1234, 9754, 9754),
(3, 1234, 7042, 7042),
(4, 1234, 7037, 7037),
(5, 5678, 1234, 1234),
(6, 1234, 9754, 9754),
(7, 9754, 7037, 7037),
(8, 7037, 4231, 4231),
(9, 4231, 7890, 7891),
(10, 7890, 9754, 9753),
(11, 9754, 7042, 7042);

-- --------------------------------------------------------

--
-- Table structure for table `registeration`
--

CREATE TABLE `registeration` (
  `ID` int(5) NOT NULL,
  `cnic` varchar(15) NOT NULL,
  `name` varchar(40) NOT NULL,
  `father_name` varchar(40) NOT NULL,
  `dob` varchar(50) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `martial_status` varchar(20) NOT NULL,
  `address` text NOT NULL,
  `city` varchar(20) NOT NULL,
  `pin` int(10) NOT NULL,
  `state` varchar(20) NOT NULL,
  `card_num` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registeration`
--

INSERT INTO `registeration` (`ID`, `cnic`, `name`, `father_name`, `dob`, `gender`, `email`, `martial_status`, `address`, `city`, `pin`, `state`, `card_num`) VALUES
(8, '32303-2086272-1', 'DANIAL ASGHAR', 'AMAN ULLAH ASGHAR', '29-July-1996', 'male', 'danialasghar5@gmail.com', 'single', 'room no.87 lahore', 'kot addu', 1234, 'PAK', 128344),
(10, '12345-6778890-0', 'SHAHZAD AKBAR', 'HAJI GHULAM AKBAR', '1-January-1984', 'male', 'zbcd@gmail.com', 'single', 'madina bakers , city kala', 'KALA', 123, 'PAKISTAN', 1572),
(9, '12345-6789009-8', 'SUHAIB SHAHID', 'SHAHID', '22-February-1996', 'male', 'bitf15m014@pucit.edu.pk', 'single', 'room no.87 lahore', 'Gojra', 1234, 'PAK', 144076);

-- --------------------------------------------------------

--
-- Table structure for table `withdraw`
--

CREATE TABLE `withdraw` (
  `with_id` int(11) NOT NULL,
  `with_amount` int(11) NOT NULL,
  `with_time` varchar(31) NOT NULL,
  `card_num` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `withdraw`
--

INSERT INTO `withdraw` (`with_id`, `with_amount`, `with_time`, `card_num`) VALUES
(2, 1000, 'acb', 128344),
(3, 10000, 'abcd', 1572),
(4, 500, '2019-02-11 10:48:44', 128344),
(5, 1000, '2019-02-11 10:52:24', 128344),
(6, 500, '2019-02-11 10:54:37', 128344),
(7, 500, '2019-02-11 10:56:36', 128344),
(8, 1500, '2019-02-11 10:59:54', 128344),
(9, 500, '2019-02-11 11:30:16', 128344),
(10, 500, '2019-02-11 12:51:05', 128344);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `balance_detail`
--
ALTER TABLE `balance_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bill_details`
--
ALTER TABLE `bill_details`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `deposit`
--
ALTER TABLE `deposit`
  ADD PRIMARY KEY (`depo_id`);

--
-- Indexes for table `fund_transfer_detail`
--
ALTER TABLE `fund_transfer_detail`
  ADD PRIMARY KEY (`fund_id`);

--
-- Indexes for table `pin_change`
--
ALTER TABLE `pin_change`
  ADD PRIMARY KEY (`pin_id`);

--
-- Indexes for table `registeration`
--
ALTER TABLE `registeration`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `withdraw`
--
ALTER TABLE `withdraw`
  ADD PRIMARY KEY (`with_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `balance_detail`
--
ALTER TABLE `balance_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bill_details`
--
ALTER TABLE `bill_details`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9001;

--
-- AUTO_INCREMENT for table `deposit`
--
ALTER TABLE `deposit`
  MODIFY `depo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `fund_transfer_detail`
--
ALTER TABLE `fund_transfer_detail`
  MODIFY `fund_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pin_change`
--
ALTER TABLE `pin_change`
  MODIFY `pin_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `registeration`
--
ALTER TABLE `registeration`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `withdraw`
--
ALTER TABLE `withdraw`
  MODIFY `with_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
