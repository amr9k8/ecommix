-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2023 at 08:17 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(50) NOT NULL,
  `cat_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `cat_name`) VALUES
(1, 'Food '),
(2, 'Books'),
(3, 'Health'),
(4, 'electronics'),
(5, 'coffee'),
(6, 'supplments');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `username` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birthdate` date NOT NULL,
  `job` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `email`, `username`, `password`, `gender`, `birthdate`, `job`) VALUES
(1, 'amrkhaled9k8@gmail.com', 'amr9k8', 'amr12345', 'male', '2000-09-11', 'developer'),
(3, 'amrkhaled9k8@gmail.com', 'ayman', 'ayman12345', 'male', '2000-03-12', 'developer'),
(4, 'amrkhaled9k8@gmail.com', 'doctor', 'doc12345', 'male', '2000-05-14', 'developer'),
(5, 'amrkhaled9k8@gmail.com', 'test12', 'test12345', 'male', '1999-08-14', 'tester'),
(7, 'test123@gmail.com', 'test', '12345', 'male', '2000-09-11', 'tester'),
(8, 'amrkhaled9k8@gmail.com', 'a7a', '123', 'male', '0000-00-00', 'dev'),
(9, 'asdd@d.com', 'asd', 'amr', 'dev', '0000-00-00', '1/1/1'),
(10, 'testo@ff.com', 'testo', '1234', 'teser', '0000-00-00', '12/22/25'),
(12, 'amrkhaled9k18@gmail.com', 'amr12', '12345', 'tester', '0000-00-00', '1/12/1222'),
(22, 'amrkhaled9k8@gmail.com', 'joku', '12346', 'dev', '0000-00-00', '2005-2-1'),
(23, 'amrkhaled9k8@gmail.com', 'newuser', 'amr', 'Male', '2000-09-11', 'developer'),
(24, '123', 'user', '123', 'Female', '0123-01-01', 'asd'),
(25, 'amr123@gmail.com', 'user123', '123', 'Male', '2000-01-01', 'dev'),
(26, 'sas', 'sasa', '123', 'Male', '0003-02-01', '23'),
(27, 'amrkhaled9k8@gmail.com', 'aly', '123', 'Male', '2000-12-01', 'develper'),
(28, 'amrkhaled9k8@gmail.com', 'tester1', 'Test1', 'Male', '2000-02-12', 'tester'),
(29, 'amrkhaled9k8@gmail.com', 'zezo', '123', 'Male', '2000-01-01', 'tester'),
(30, 'amrkhaled9k8@gmail.com', 'new1', '12345', 'Male', '2022-11-27', 'dev'),
(31, 'amrkhaled9k8@gmail.com', 'amr123', 'amr', 'Male', '2022-11-27', 'developer');

-- --------------------------------------------------------

--
-- Table structure for table `forgetpass`
--

CREATE TABLE `forgetpass` (
  `resetcode` int(11) NOT NULL,
  `cust_id` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `forgetpass`
--

INSERT INTO `forgetpass` (`resetcode`, `cust_id`) VALUES
(50922, 28),
(142436, 28),
(235581, 9),
(275203, 9),
(460153, 8),
(597308, 9),
(622930, 23),
(781320, 28),
(841218, 23),
(982907, 28);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `price`) VALUES
(1, 'Canon EOS', 36000),
(2, 'Nikon DSLR', 40000),
(3, 'Sony DSLR', 45000),
(4, 'Olympus DSLR', 50000),
(5, 'Titan Model #301', 13000),
(6, 'Titan Model #201', 3000),
(7, 'HMT Milan', 8000),
(8, 'Men Outfit', 800);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(50) NOT NULL,
  `orderdate` date NOT NULL DEFAULT current_timestamp(),
  `address` varchar(150) NOT NULL,
  `cust_id` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `orderdate`, `address`, `cust_id`) VALUES
(142, '2022-12-28', '38Q4+GFW, Mansheya El-Bakry, Heliopolis, Cairo Governorate 4460038, Egypt', 27),
(143, '2022-12-28', '38Q4+GFW, Mansheya El-Bakry, Heliopolis, Cairo Governorate 4460038, Egypt', 27),
(144, '2022-12-28', '38Q4+GFW, Mansheya El-Bakry, Heliopolis, Cairo Governorate 4460038, Egypt', 27);

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `order_id` int(50) NOT NULL,
  `prod_id` int(50) NOT NULL,
  `quanitiy` int(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`order_id`, `prod_id`, `quanitiy`) VALUES
(142, 1, 1),
(142, 2, 1),
(143, 3, 1),
(144, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `price` varchar(150) NOT NULL,
  `quantity` int(150) NOT NULL,
  `cat_id` int(50) NOT NULL,
  `barcode` varchar(50) NOT NULL,
  `img` varchar(120) NOT NULL DEFAULT 'placeholder.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `quantity`, `cat_id`, `barcode`, `img`) VALUES
(1, 'oats', '32', 50, 1, 'ABC-abc-1234', 'oats.jpg'),
(2, 'milk', '16', 50, 1, 'ABC-abc-1234', 'milk.jpg'),
(3, 'cornflex', '25', 50, 1, 'ABC-abc-1234', 'cornflex.jpg'),
(5, 'art of hacking', '365', 10, 2, 'ABC-abc-1234', 'artof.jpg'),
(6, 'redteaming the rightway', '365', 10, 2, 'ABC-abc-1234', 'redteam.jpg'),
(7, 'blackhat python', '365', 10, 2, 'ABC-abc-1235', 'blackhat.jpg'),
(10, 'mutlivitamins', '250', 50, 3, 'ABC-abc-1233', 'multivi.jpg'),
(11, 'Roids', '4500', 3, 6, '', 'roids.jpg'),
(12, 'Lavazza Coffee', '125', 11, 5, '', 'lavazza.jpg'),
(13, 'creatine', '900', 3, 6, '', 'creatine.jpg'),
(14, 'Illy Intenso Coffee', '225', 11, 5, '', 'illy_intenso.jpg'),
(15, 'whey', '1500', 3, 6, '', 'whey.jpg'),
(16, 'DeathWish Coffee', '325', 11, 5, '', 'death wish.webp'),
(19, 'bcaa', '1100', 3, 6, '', 'bcaa.jpg'),
(20, 'Purity Coffee', '225', 11, 5, '', 'purity-coffee-hearth-holiday-blend.webp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `forgetpass`
--
ALTER TABLE `forgetpass`
  ADD PRIMARY KEY (`resetcode`,`cust_id`),
  ADD KEY `custid` (`cust_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`cust_id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`order_id`,`prod_id`),
  ADD KEY `orderid` (`order_id`),
  ADD KEY `prod_id` (`prod_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cat_id` (`cat_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `forgetpass`
--
ALTER TABLE `forgetpass`
  ADD CONSTRAINT `forgetpass_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`prod_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
