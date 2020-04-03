-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2020 at 05:45 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobilesales`
--

-- --------------------------------------------------------

--
-- Table structure for table `keys`
--

CREATE TABLE `keys` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `key` varchar(40) NOT NULL,
  `level` int(2) NOT NULL,
  `ignore_limits` tinyint(1) NOT NULL DEFAULT '0',
  `is_private_key` tinyint(1) NOT NULL DEFAULT '0',
  `ip_addresses` text,
  `date_created` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `keys`
--

INSERT INTO `keys` (`id`, `user_id`, `key`, `level`, `ignore_limits`, `is_private_key`, `ip_addresses`, `date_created`) VALUES
(1, 1, 'sidik123', 1, 0, 0, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `log_activity_ms`
--

CREATE TABLE `log_activity_ms` (
  `id` int(11) NOT NULL,
  `id_form` int(11) NOT NULL,
  `id_detail` int(11) NOT NULL,
  `action` varchar(100) NOT NULL,
  `distribusi_from` varchar(100) NOT NULL,
  `distribusi_to` varchar(100) NOT NULL,
  `ms_code` varchar(8) NOT NULL,
  `status` varchar(100) NOT NULL,
  `keterangan` text NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_activity_ms`
--

INSERT INTO `log_activity_ms` (`id`, `id_form`, `id_detail`, `action`, `distribusi_from`, `distribusi_to`, `ms_code`, `status`, `keterangan`, `created_date`, `created_by`) VALUES
(332511, 378, 117569, 'Worklist', '', '', 'F2400054', '1006', '', '2019-06-01 00:32:59', 'KETUT SUKRA YASA'),
(332512, 395, 123070, 'Worklist', '', '', 'F2700023', '1023', '', '2019-06-01 00:40:33', 'ROBI SUJARWADI'),
(332513, 388, 121438, 'Worklist', '', '', 'F2400120', '1023', '', '2019-06-01 00:50:47', 'TOMAS ALFA EDISON'),
(332514, 385, 120605, 'Worklist', '', '', 'F2400120', '1023', '', '2019-06-01 00:51:12', 'TOMAS ALFA EDISON'),
(332515, 385, 120613, 'Worklist', '', '', 'F2400120', '1023', '', '2019-06-01 00:51:35', 'TOMAS ALFA EDISON'),
(332516, 351, 106791, 'Worklist', '', '', 'F2400120', '1011', '', '2019-06-01 00:55:52', 'TOMAS ALFA EDISON'),
(332517, 353, 108233, 'Worklist', '', '', 'F2400120', '1011', '', '2019-06-01 00:56:27', 'TOMAS ALFA EDISON'),
(332518, 0, 0, 'distibusi', 'K1102197', 'F1100165', '', '', '', '2019-06-01 03:07:43', 'ANDRIE ALEXCANDRA KUSUMAH DINATA SE'),
(332519, 0, 0, 'distibusi', 'K1102197', 'F1100165', '', '', '', '2019-06-01 03:07:44', 'ANDRIE ALEXCANDRA KUSUMAH DINATA SE'),
(332520, 0, 0, 'distibusi', 'K1102197', 'F1100165', '', '', '', '2019-06-01 03:07:44', 'ANDRIE ALEXCANDRA KUSUMAH DINATA SE'),
(1158874, 43747, 48743, 'fjfi', '474758', '5576', '337', '9606', 'fifuu', '0000-00-00 00:00:00', 'fuii'),
(1158875, 43747, 48743, 'fjfi', '474758', '5576', '337', '9606', 'fifuu', '0000-00-00 00:00:00', 'fuii'),
(1158876, 665477, 0, 'worklist', 'worklist', 'worklist', 'worklist', 'worklist', 'worklist', '2020-03-26 12:05:19', 'created by'),
(1158877, 504, 0, 'worklist', 'worklist', 'worklist', 'K1002059', '1024', 'worklist', '2020-03-26 13:48:36', 'JOHAN WAHYUDI');

-- --------------------------------------------------------

--
-- Table structure for table `ref_pickup_reason`
--

CREATE TABLE `ref_pickup_reason` (
  `Reason_ID` int(11) NOT NULL,
  `Reason_Code` varchar(3) NOT NULL,
  `Reason` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ref_pickup_reason`
--

INSERT INTO `ref_pickup_reason` (`Reason_ID`, `Reason_Code`, `Reason`) VALUES
(1, 'A1', 'Alamat Tidak Ditemukan'),
(2, 'A2', 'Rumah Kosong'),
(3, 'A3', 'Penerima Tidak Dikenal'),
(4, 'A4', 'Tidak Bertemu Nasabah'),
(5, 'A5', 'Dokumen Tidak Lengkap'),
(6, 'A6', 'Batal'),
(7, 'A7', 'Lain-Lain'),
(8, 'B1', 'CH Berubah pikiran'),
(9, 'B2', 'Kartu tidak dipakai'),
(10, 'B3', 'Dokumen tidak lengkap'),
(11, 'B4', 'Belum berminat'),
(12, 'B5', 'Tidak diizinkan suami/istri'),
(13, 'B6', 'Kurang/salah penjelasan dari TM'),
(14, 'B7', 'CH sering mengajukan tp tidak disetujui'),
(15, 'B8', 'Lain-lain');

-- --------------------------------------------------------

--
-- Table structure for table `ref_status`
--

CREATE TABLE `ref_status` (
  `Status_ID` int(11) NOT NULL,
  `kode_pick_up` int(11) NOT NULL,
  `Status` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ref_status`
--

INSERT INTO `ref_status` (`Status_ID`, `kode_pick_up`, `Status`) VALUES
(1, 1004, 'Sukses Pick Up'),
(2, 1005, 'Inprocess'),
(3, 1006, 'Gagal Pick Up'),
(4, 1008, 'Uncoverage'),
(5, 1011, 'Batal'),
(6, 1023, 'Jadwal Ulang'),
(7, 1024, 'RTS');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_upload_ms_detail`
--

CREATE TABLE `tbl_upload_ms_detail` (
  `id` int(11) NOT NULL,
  `id_form` int(11) NOT NULL,
  `unique_id` varchar(30) NOT NULL,
  `cabang` varchar(30) NOT NULL,
  `no_case` varchar(30) NOT NULL,
  `cis` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(20) NOT NULL,
  `address` text NOT NULL,
  `address_geotag` varchar(100) NOT NULL,
  `post_code` varchar(10) NOT NULL,
  `city` varchar(100) NOT NULL,
  `city2` varchar(100) NOT NULL,
  `phone1` varchar(15) NOT NULL,
  `phone2` varchar(15) NOT NULL,
  `phone3` varchar(15) NOT NULL,
  `phone4` varchar(15) NOT NULL,
  `phone5` varchar(15) NOT NULL,
  `phone6` varchar(15) NOT NULL,
  `phone7` varchar(15) NOT NULL,
  `phone8` varchar(15) NOT NULL,
  `phone9` varchar(15) NOT NULL,
  `phone10` varchar(15) NOT NULL,
  `phone11` varchar(15) DEFAULT NULL,
  `phone12` varchar(15) DEFAULT NULL,
  `phone13` varchar(15) DEFAULT NULL,
  `phone14` varchar(15) DEFAULT NULL,
  `phone15` varchar(15) DEFAULT NULL,
  `id_campaign` varchar(10) NOT NULL,
  `campaign` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `tm_name` varchar(30) NOT NULL,
  `tm_code` varchar(30) NOT NULL,
  `aggree_date` date NOT NULL,
  `agree_time` varchar(25) NOT NULL,
  `upload_date` date NOT NULL,
  `tgl_pu` date NOT NULL,
  `barcode` varchar(20) NOT NULL,
  `time_pu` varchar(20) NOT NULL,
  `remark` text NOT NULL,
  `kode_pick_up` varchar(20) NOT NULL,
  `kode_gagal_pu` varchar(20) NOT NULL,
  `kode_cancel_pu` varchar(50) NOT NULL,
  `pick_up_date` datetime NOT NULL,
  `ms_code` varchar(20) NOT NULL,
  `ms_name` varchar(50) NOT NULL,
  `spv_code` varchar(20) NOT NULL,
  `asm_code` varchar(10) NOT NULL,
  `image_type` varchar(10) NOT NULL,
  `image_name_pemilik` varchar(100) NOT NULL,
  `image_name_ktp` varchar(100) NOT NULL,
  `geo_info_pemilik` varchar(100) NOT NULL,
  `geo_info_ktp` varchar(100) NOT NULL,
  `image_name_bukti` varchar(100) NOT NULL,
  `geo_info_bukti` varchar(100) NOT NULL,
  `tanggal_distribusi_asm` date NOT NULL,
  `tanggal_distribusi` date NOT NULL,
  `note_Iinprocessed` text NOT NULL,
  `product` varchar(20) NOT NULL,
  `tanggal_reschedule` date NOT NULL,
  `source_code` varchar(20) NOT NULL,
  `created_date` date NOT NULL,
  `status_validasi` int(1) NOT NULL,
  `validasi_by` varchar(50) NOT NULL,
  `status_validasi_admin` int(1) NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `status_close` int(1) NOT NULL,
  `close_date` datetime NOT NULL,
  `closed_by` varchar(50) NOT NULL,
  `branch` varchar(30) NOT NULL,
  `tanggal_status_terakhir` datetime NOT NULL,
  `is_return` int(1) NOT NULL,
  `return_date` datetime NOT NULL,
  `status_call` varchar(100) NOT NULL,
  `reason_call` varchar(100) NOT NULL,
  `other_reason` text NOT NULL,
  `call_by` varchar(100) NOT NULL,
  `call_date` date NOT NULL,
  `call_time` datetime NOT NULL,
  `notes` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_upload_ms_detail`
--

INSERT INTO `tbl_upload_ms_detail` (`id`, `id_form`, `unique_id`, `cabang`, `no_case`, `cis`, `name`, `dob`, `gender`, `address`, `address_geotag`, `post_code`, `city`, `city2`, `phone1`, `phone2`, `phone3`, `phone4`, `phone5`, `phone6`, `phone7`, `phone8`, `phone9`, `phone10`, `phone11`, `phone12`, `phone13`, `phone14`, `phone15`, `id_campaign`, `campaign`, `type`, `username`, `tm_name`, `tm_code`, `aggree_date`, `agree_time`, `upload_date`, `tgl_pu`, `barcode`, `time_pu`, `remark`, `kode_pick_up`, `kode_gagal_pu`, `kode_cancel_pu`, `pick_up_date`, `ms_code`, `ms_name`, `spv_code`, `asm_code`, `image_type`, `image_name_pemilik`, `image_name_ktp`, `geo_info_pemilik`, `geo_info_ktp`, `image_name_bukti`, `geo_info_bukti`, `tanggal_distribusi_asm`, `tanggal_distribusi`, `note_Iinprocessed`, `product`, `tanggal_reschedule`, `source_code`, `created_date`, `status_validasi`, `validasi_by`, `status_validasi_admin`, `created_by`, `status_close`, `close_date`, `closed_by`, `branch`, `tanggal_status_terakhir`, `is_return`, `return_date`, `status_call`, `reason_call`, `other_reason`, `call_by`, `call_date`, `call_time`, `notes`) VALUES
(168900, 6654, 'jogoih', 'jogog', 'fiiig', 'fdifigfd', 'giud', '0000-00-00', 'm', 'shift12', 'tiide', '14440', 'fifd', 'dsu', '9706', '444', '666', '555', '111', '222', '333', '777', '888', '9876', '6789', '6685', '4553', '4374', '38759', '26536', 'PACTUR JAKARTA AUG 2019', 'frifi', 'eui', 'fiofri', 'diufrif', '0000-00-00', 'fdii', '0000-00-00', '0000-00-00', 'dirit', 'durig', 'froifif', '-', 'A1', 'difrifd', '0000-00-00 00:00:00', 'fifi', 'duri8tr', 'tifri', 'diudufd', 'digtog', 'IMG_1585494389530_png.jpg', 'IMG_1585494399067_png.jpg', 'fifif', 'rifoifgo', 'IMG_1585494407379_png.jpg', '-6.33688767,106.95530575', '0000-00-00', '0000-00-00', '', 'diufudu', '0000-00-00', 'fdigftifd', '2020-03-29', 0, 'diufig', 0, 'fifiuf', 0, '0000-00-00 00:00:00', 'fititoi', 'figfoi', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 'fioigt', 'toy0oydioig', 'gifi', 'fioig', '0000-00-00', '0000-00-00 00:00:00', 'foyyo'),
(168901, 504, '3134287', '', '3134287', '00021263895', 'ROBY YANA', '0000-00-00', 'M', 'JL GELORA 8 CIPONDOH MAKMUR BLOK G 5 NO 5 CIPONDOH TANGERANG 15148//087871275322/081288365678   CIPONDOH MAKMUR CIPONDOH', '', '15148', 'TANGERANG', '', '', '', '087871275322', '081288365678', '', '087871275322', '087871371115', '', '', '', '087871275322', '', '02548490550', '02548490550', '02548490550', '0002126389', 'PACTUR BOGOR JUL 2019', 'BCA Everyday', 'DINAR', 'INTAN DWI TAMARA', 'F1190024', '2019-07-11', '10:13:53', '2019-06-28', '2019-08-04', '*CC0313428719080111*', '10', '087871275322/081288365678 CH MINTA DIHUBUNGI DAHULU KARENA SEDANG DILUAR KOTA', '1023', '', '', '0000-00-00 00:00:00', 'F2100060', 'ANDRI RAHMAT DIRGANTARA', 'F2100004', 'K1103609', '', '', '', '', '', '', '', '2019-08-02', '2019-08-02', '', '1', '2019-08-03', '', '2019-08-01', 0, '', 0, '', 1, '2019-11-01 23:05:01', 'System', 'BGR', '2019-11-01 23:05:01', 0, '0000-00-00 00:00:00', '', '', '', '', '0000-00-00', '0000-00-00 00:00:00', ''),
(168902, 504, '3143341', '', '3143341', '00032831898', 'SISWADI A MD KOM', '0000-00-00', 'M', 'CILANDAK BARAT, GANG NYA SEBELAHNYA RUMAH MAKAN GANTO MINANG, JAKARTA SELATAN   CILANDAK BARAT CILANDAK', '', '12430', 'JAKARTA SELATAN', '', '', '', '081289605056', '', '', '081289605056', '', '', '', '', '082167412271', '', '081210350312', '0217257180', '081210350312', '0003283189', 'PACTUR JAKARTA JUL 2019', 'BCA Everyday', 'CANTIKA', 'FICKA SEPHORA', 'F1180017', '2019-07-16', '14:24:46', '2019-06-28', '2019-08-04', '*CC0314334119080111*', '10', 'PU JAM 10 PAGI/ MOHON KONFIRM ALAMAT CH DULU YA KRN CH LUPA ALAMAT LENGKAPNYA BARU PINDAH', '1004', '', '', '2019-08-19 08:35:48', 'F1100277', 'VIKRA PERMANA', 'F1100151', 'K1106887', '', '', '', '', '', '168902_bukti_4b1a8071484a5940b9d4bdb24091d58d.jpg', '-6.2387112777778,106.81730297222', '2019-08-01', '2019-08-19', '', '1', '0000-00-00', '', '2019-08-01', 0, '', 1, '', 1, '2019-08-20 07:36:14', 'Bima Satria Yudha', 'JKT1', '2019-08-19 08:35:48', 0, '0000-00-00 00:00:00', '', '', '', '', '0000-00-00', '0000-00-00 00:00:00', ''),
(168903, 504, '2946418', '', '2946418', '00039134880', 'FAHMI', '0000-00-00', 'M', 'JL.FAISAL 17 LORONG III KEC RAPOCINI KOTA MAKASSAR HP.085145165557    RAPPOCINI', '', '', 'MAKASSAR', '', '', '', '085145165557', '', '', '', '', '', '', '', '085145165557', '', '081242476808', '', '081242476808', '0003913488', 'PACTUR MAKASSAR JUN 2019', 'BCA Everyday', 'BERTHA', 'SITI KURNIASIH', 'F1170015', '2019-07-22', '12:59:01', '2019-05-31', '2019-08-04', '*CC0294641819080111*', '10', '\'TERIMA KASIH\'', '1011', '', '', '0000-00-00 00:00:00', 'F2500032', 'SULAIMAN M RA', 'F2500004', 'K1300160', '', '', '', '', '', '', '', '2019-08-02', '2019-08-02', '', '1', '0000-00-00', '', '2019-08-01', 0, '', 0, '', 1, '2019-11-01 23:05:01', 'System', 'MKS3', '2019-11-01 23:05:01', 0, '0000-00-00 00:00:00', '', '', '', '', '0000-00-00', '0000-00-00 00:00:00', ''),
(168904, 504, '3058266', '', '3058266', '00023358979', 'AGUNG SETIABUDI SAPUTRA', '0000-00-00', 'M', 'JL KEMANG II DALAM NO 25 //KEL PELA MAMPANG // RT 012/007 //KEC MAMPANG PRAPATAN //  JAKARTA SEL 12720 RT 012 RW 007 PELA MAMPANG MAMPANG PRAPATAN', '', '12720', 'JAKARTA SELATAN', '', '', '', '081218010327', '081218010327', '081218010327', '081218010327', '', '', '', '', '081218010327', '', '02194768420', '02183788388', '02194768420', '0002335897', 'PACTUR JAKARTA JUN 2019', 'BCA Everyday', 'CINTA', 'PUTRI SUSANTI', 'F1180007', '2019-07-23', '13:18:49', '2019-06-10', '2019-08-04', '*CC0305826619080111*', '9', 'TGL 4 AGUS 2019 JAM 09.00 // HUB DULU SEBELUM BERTEMU 081218010327 TERIMAKASIH ', '1006', 'A4', '', '0000-00-00 00:00:00', 'F1100277', 'VIKRA PERMANA', 'F1100151', 'K1106887', '', '', '', '', '', '', '', '2019-08-01', '2019-08-02', '', '1', '0000-00-00', '', '2019-08-01', 0, '', 0, '', 1, '2019-11-01 23:05:01', 'System', 'JKT1', '2019-11-01 23:05:01', 0, '0000-00-00 00:00:00', '', '', '', '', '0000-00-00', '0000-00-00 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `tes`
--

CREATE TABLE `tes` (
  `id` int(11) NOT NULL,
  `image_name_pemilik` varchar(100) NOT NULL,
  `image_name_ktp` varchar(100) NOT NULL,
  `image_name_bukti` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tes`
--

INSERT INTO `tes` (`id`, `image_name_pemilik`, `image_name_ktp`, `image_name_bukti`, `location`) VALUES
(1, 'IMG_1585404965116_png.jpg', 'IMG_1585404972912_png.jpg', 'IMG_1585404979137_png.jpg', ''),
(2, 'IMG_1585313143792_png.jpg', 'IMG_1585313152049_png.jpg', 'IMG_1585313159087_png.jpg', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `keys`
--
ALTER TABLE `keys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log_activity_ms`
--
ALTER TABLE `log_activity_ms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ref_pickup_reason`
--
ALTER TABLE `ref_pickup_reason`
  ADD PRIMARY KEY (`Reason_ID`);

--
-- Indexes for table `ref_status`
--
ALTER TABLE `ref_status`
  ADD PRIMARY KEY (`Status_ID`);

--
-- Indexes for table `tbl_upload_ms_detail`
--
ALTER TABLE `tbl_upload_ms_detail`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_id` (`unique_id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `tes`
--
ALTER TABLE `tes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `keys`
--
ALTER TABLE `keys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `log_activity_ms`
--
ALTER TABLE `log_activity_ms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1158878;

--
-- AUTO_INCREMENT for table `ref_pickup_reason`
--
ALTER TABLE `ref_pickup_reason`
  MODIFY `Reason_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `ref_status`
--
ALTER TABLE `ref_status`
  MODIFY `Status_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_upload_ms_detail`
--
ALTER TABLE `tbl_upload_ms_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=391161;

--
-- AUTO_INCREMENT for table `tes`
--
ALTER TABLE `tes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
