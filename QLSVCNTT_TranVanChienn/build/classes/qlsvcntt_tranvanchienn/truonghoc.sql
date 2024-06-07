-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 07, 2024 lúc 04:17 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `svuneti`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `truonghoc`
--

CREATE TABLE `truonghoc` (
  `ma` varchar(10) NOT NULL,
  `tentruong` varchar(30) DEFAULT NULL,
  `khoa` varchar(30) DEFAULT NULL,
  `nam` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `truonghoc`
--

INSERT INTO `truonghoc` (`ma`, `tentruong`, `khoa`, `nam`) VALUES
('1', '1', '1', 1),
('2', 'aaaaa', '2', 2),
('3', 'Thinh Nguyen an cut', '2', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `truonghoc`
--
ALTER TABLE `truonghoc`
  ADD PRIMARY KEY (`ma`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
