-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 24, 2019 lúc 03:15 AM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `appsellingflower`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` bigint(20) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `soluong` int(11) NOT NULL,
  `giasanpham` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `soluong`, `giasanpham`) VALUES
(1, 9, 7, 'Hoa hồng', 1, 0),
(2, 10, 2, 'giỏ hoa', 5, 0),
(3, 11, 7, 'Hoa hồng', 3, 0),
(4, 11, 3, 'Hoa Ly', 4, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `idloaisp` bigint(200) NOT NULL,
  `ten` varchar(500) CHARACTER SET utf8 NOT NULL,
  `img` varchar(500) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`idloaisp`, `ten`, `img`) VALUES
(1, 'Hoa chúc mừng', 'https://goo.gl/WyPfC2'),
(2, 'Hoa viếng', 'https://goo.gl/1KUJLX'),
(3, 'Hoa cưới', 'https://goo.gl/fCNj4u'),
(4, 'Hoa tết', 'https://goo.gl/sfG7EH');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `flowerid` bigint(200) NOT NULL,
  `flowername` varchar(500) CHARACTER SET utf8 NOT NULL,
  `flowerimage` varchar(500) CHARACTER SET utf8 NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `idloaisp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`flowerid`, `flowername`, `flowerimage`, `price`, `idloaisp`) VALUES
(1, 'Hoa Hồng', 'http://hatgionghoadepla.com/wp-content/uploads/2018/09/4770959597_c594dbcbe9_b.jpg', 250000, 3),
(2, 'giỏ hoa', 'https://goo.gl/7QijqC', 1500000, 1),
(3, 'Hoa Ly', 'https://goo.gl/ArBVs9', 250000, 4),
(4, 'giỏ hoa', 'https://goo.gl/Mhd98c', 190000, 1),
(5, 'Cây hoa', 'https://goo.gl/JSPrpX', 900000, 1),
(6, 'Hoa hồng', 'https://goo.gl/eE8CvY', 50000, 1),
(7, 'Hoa hồng', 'https://goo.gl/eE8CvY', 50000, 2),
(8, 'Hoa hồng', 'https://goo.gl/eE8CvY', 50000, 4),
(9, 'Hoa hồng', 'https://goo.gl/eE8CvY', 50000, 1),
(10, 'Hoa hồng', 'https://goo.gl/eE8CvY', 50000, 3),
(11, 'Hoa hồng', 'https://goo.gl/eE8CvY', 50000, 1),
(12, 'giỏ hoa', 'https://goo.gl/Mhd98c', 190000, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `userid` bigint(200) NOT NULL,
  `fullname` varchar(100) CHARACTER SET utf8 NOT NULL,
  `address` varchar(10000) CHARACTER SET utf8 NOT NULL,
  `email` varchar(250) CHARACTER SET utf8 NOT NULL,
  `phonenumber` char(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`userid`, `fullname`, `address`, `email`, `phonenumber`) VALUES
(1, 'Lê Văn Ninh', '48 cao thăng', 'levanninh2101@gmail.com', '0374802901'),
(2, 'Nguyễn Thị Minh Linh', '48 cao thăng', 'linhellby6789@gmail.com', '0762588660'),
(3, 'Bi.ute', 'abc', 'abc@gmail.com', '123456'),
(4, 'Bi.ute', 'abc', 'abc@gmail.com', '123456'),
(5, 'lê văn ninh', 'bsjậ', '123@&#-', '943484'),
(6, 'lê văn ninh', 'bsjậ', '123@&#-', '943484'),
(7, 'lê văn ninh', 'bsjậ', '123@&#-', '943484'),
(8, 'lê văn ninh', 'bsjậ', '123@&#-', '943484'),
(9, 'jsb', 'vzjan', 'âhba', '34845'),
(10, 'App Buy Flower', '48 cao thắng đà nẵng', 'appbuyflower@gmail.com', '0374802901'),
(11, 'ninh', 'hâvia sún', 'shsjbs', '843404');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`idloaisp`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`flowerid`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `idloaisp` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `flowerid` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `userid` bigint(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
