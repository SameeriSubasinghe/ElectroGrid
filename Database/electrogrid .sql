-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2022 at 07:00 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billID` int(20) NOT NULL,
  `billCode` varchar(20) NOT NULL,
  `electricityAccountNo` varchar(15) NOT NULL,
  `billMonth` varchar(10) NOT NULL,
  `units` varchar(10) NOT NULL,
  `paymentAmount` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billID`, `billCode`, `electricityAccountNo`, `billMonth`, `units`, `paymentAmount`) VALUES
(6, '', '54', 'sfhgh', '30.0', '1500.0'),
(7, '', '54', 'sfhgh', '30.0', '1500.0'),
(8, '', '5464', 'ssdfsdff', '25.0', '1250.0'),
(9, '1234', 'E2345', 'June', '77.0', '3850.0');

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE `complaints` (
  `complaintID` int(10) NOT NULL,
  `accountNum` varchar(7) NOT NULL,
  `complaintName` varchar(30) NOT NULL,
  `complaintAdd` varchar(30) NOT NULL,
  `complaintPhone` varchar(10) NOT NULL,
  `complaintEmail` varchar(30) NOT NULL,
  `complaintMessage` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaintID`, `accountNum`, `complaintName`, `complaintAdd`, `complaintPhone`, `complaintEmail`, `complaintMessage`) VALUES
(5, '82569', 'Mr.Kusal Perera', 'Gampaha', '[076987631', 'kusalperera@gmail.com', '[Power cut]'),
(6, '2568', 'Mr.Sandun Alvis', 'Malabe', '076987632', 'sandunalvis@gmail.com', 'Bill error'),
(7, '2569', 'Mr.Saman Perera', 'Colombo 07', '0769876325', 'samanperera@gmail.com', 'Power cut'),
(8, '54321', 'Ravindu Thenakoon', 'Matara', '077654328', 'ravindu@gmail.com', 'Meter board issue');

-- --------------------------------------------------------

--
-- Table structure for table `odpayments`
--

CREATE TABLE `odpayments` (
  `ODPaymentID` int(10) NOT NULL,
  `ODCode` varchar(10) NOT NULL,
  `dueAmount` decimal(10,2) NOT NULL,
  `dueMonthsNo` int(10) NOT NULL,
  `dueMonths` varchar(100) NOT NULL,
  `accountNo` varchar(10) NOT NULL,
  `IsSuspend` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `odpayments`
--

INSERT INTO `odpayments` (`ODPaymentID`, `ODCode`, `dueAmount`, `dueMonthsNo`, `dueMonths`, `accountNo`, `IsSuspend`) VALUES
(1, 'OD102', '2500.00', 3, 'March_2020 , April_2020, May_2020', 'U205', 0),
(2, 'OD103', '5000.00', 3, 'March_2021 , April_2021, May_2021', 'UI205', 0),
(3, 'OD104', '8500.00', 6, 'March_2021 , April_2021, May_2021, June_2021, July_2021, August_2021', 'U126', 1),
(21, 'OD108', '4500.00', 3, 'April_2021, May_2021, June_2021', 'UI209', 0),
(22, 'OD109', '4520.00', 3, 'April_2021, May_2021, June_2021', 'UI208', 0);

-- --------------------------------------------------------

--
-- Table structure for table `paymentapi`
--

CREATE TABLE `paymentapi` (
  `PaymentID` int(6) NOT NULL,
  `CustomerName` varchar(200) NOT NULL,
  `AccountNO` varchar(200) NOT NULL,
  `Date` varchar(200) NOT NULL,
  `PaymentAmount` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paymentapi`
--

INSERT INTO `paymentapi` (`PaymentID`, `CustomerName`, `AccountNO`, `Date`, `PaymentAmount`) VALUES
(1203, 'kavindu', '5432', '11/12/2022', '5000'),
(2310, 'Sasini', '5543', '11/12/2021', '3000'),
(4356, 'Kasuni', '8965', '03/06/2020', '2500');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `acc_no` int(11) NOT NULL,
  `regName` varchar(11) NOT NULL,
  `regAddress` varchar(11) NOT NULL,
  `regEmail` varchar(11) NOT NULL,
  `reDate` varchar(11) NOT NULL,
  `regPNo` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`acc_no`, `regName`, `regAddress`, `regEmail`, `reDate`, `regPNo`) VALUES
(2, 'Malshi', 'Ratnapura', 'mal@gmail.c', '2020.08.21', '070543214'),
(654, 'Jackson', 'Dehiwala', 'jackson@gma', '2020.04.23', '071234543'),
(4536, 'Perera', 'Panadura', 'pmperera@gm', '2022.04.30', '078543126');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`complaintID`);

--
-- Indexes for table `odpayments`
--
ALTER TABLE `odpayments`
  ADD PRIMARY KEY (`ODPaymentID`);

--
-- Indexes for table `paymentapi`
--
ALTER TABLE `paymentapi`
  ADD PRIMARY KEY (`PaymentID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`acc_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `billID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `complaints`
--
ALTER TABLE `complaints`
  MODIFY `complaintID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `odpayments`
--
ALTER TABLE `odpayments`
  MODIFY `ODPaymentID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `paymentapi`
--
ALTER TABLE `paymentapi`
  MODIFY `PaymentID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4357;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `acc_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4537;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
