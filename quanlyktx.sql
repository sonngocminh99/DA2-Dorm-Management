-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlyktx
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `IDAccount` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Permission` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('AC1','admin','admin','admin','yes'),('AC2','admin2','admin','admin','yes'),('AC3','admin1','admin','admin','yes');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment` (
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NoRoom` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDApartment`),
  KEY `fk_employee` (`IDEmployee`),
  CONSTRAINT `fk_employee` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`IDEmployee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES ('A','20','Nam','TN1','yes'),('B','20','Nữ','TN2','yes');
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electricityandwaterbill`
--

DROP TABLE IF EXISTS `electricityandwaterbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electricityandwaterbill` (
  `IDEWBill` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Createday` date DEFAULT NULL,
  `ChiSoDauDien` double DEFAULT NULL,
  `ChiSoCuoiDien` double DEFAULT NULL,
  `ChiSoDauNuoc` double DEFAULT NULL,
  `ChiSoCuoiNuoc` double DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDEWBill`),
  KEY `fk_employee_electricityandwaterbill` (`IDEmployee`),
  KEY `fk_room_electricityandwaterbill` (`IDRoom`),
  KEY `fk_apartment_electricityandwaterbill` (`IDApartment`),
  CONSTRAINT `fk_apartment_electricityandwaterbill` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`),
  CONSTRAINT `fk_employee_electricityandwaterbill` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`IDEmployee`),
  CONSTRAINT `fk_room_electricityandwaterbill` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electricityandwaterbill`
--

LOCK TABLES `electricityandwaterbill` WRITE;
/*!40000 ALTER TABLE `electricityandwaterbill` DISABLE KEYS */;
INSERT INTO `electricityandwaterbill` VALUES ('EW1','TN1','A101','A','2022-05-30',0,100,0,10,1000000,'Đã thu'),('EW2','TN2','B101','B','2022-05-30',0,200,0,10,1000000,'Chưa thu'),('EW3','TN1','A102','A','2022-05-30',0,200,0,10,1000000,'Chưa thu'),('EW4','TN1','A101','A','2022-06-01',100,467,10,90,1901,'Đã thu'),('EW5','TN1','A102','A','2022-06-01',200,956,10,145,3618,'Chưa thu'),('EW6','TN1','A103','A','2022-06-01',0,167,0,45,786338,'Chưa thu'),('EW7','TN1','B101','B','2022-06-01',200,367,10,45,686338,'Đã thu'),('EW8','TN1','A101','A','2022-06-09',467,500,90,100,166462,'Chưa thu'),('EW9','TN1','A101','A','2022-06-09',500,600,100,200,1201400,'Chưa thu');
/*!40000 ALTER TABLE `electricityandwaterbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Fullname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Birthday` date DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PhoneNumber` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDAccount` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDEmployee`),
  KEY `fk_account_employee` (`IDAccount`),
  CONSTRAINT `fk_account_employee` FOREIGN KEY (`IDAccount`) REFERENCES `account` (`IDAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('NV3','minh','2022-06-09','abcs','Người quản trị','1234567890','AC3','yes'),('TN1','Minh','2001-12-09','Vietnam','Trưởng nhà','0888734218','AC1','yes'),('TN2','Đại','2001-01-01','Vietnam','Trưởng nhà','0123456789','AC2','yes');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentbill`
--

DROP TABLE IF EXISTS `rentbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentbill` (
  `IDRBill` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDStudent` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Createday` date DEFAULT NULL,
  `Total` double NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDRBill`),
  KEY `fk_room_rentbill` (`IDRoom`),
  KEY `fk_employee_rentbill` (`IDEmployee`),
  KEY `fk_apartment_rentbill` (`IDApartment`),
  KEY `fk_student_rentbill` (`IDStudent`),
  CONSTRAINT `fk_apartment_rentbill` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`),
  CONSTRAINT `fk_employee_rentbill` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`IDEmployee`),
  CONSTRAINT `fk_room_rentbill` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`),
  CONSTRAINT `fk_student_rentbill` FOREIGN KEY (`IDStudent`) REFERENCES `student` (`IDStudent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentbill`
--

LOCK TABLES `rentbill` WRITE;
/*!40000 ALTER TABLE `rentbill` DISABLE KEYS */;
INSERT INTO `rentbill` VALUES ('RB1','TN2','B101','B','ST2','2021-05-03',6000000,'Đã thu'),('RB2','TN1','A101','A','ST1','2021-05-01',6000000,'Đã thu'),('RB3','TN1','A101','A','ST3','2021-05-03',6000000,'Chưa thu'),('RB4','TN1','A101','A','ST3','2022-06-01',500000,'Đã thu'),('RB5','TN1','A101','A','ST4','2022-06-01',500000,'Chưa thu');
/*!40000 ALTER TABLE `rentbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NoStudent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Status` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Type` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RentingPrice` int DEFAULT NULL,
  `active` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDRoom`),
  KEY `fk_apartment_room` (`IDApartment`),
  CONSTRAINT `fk_apartment_room` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('A101','A','4','Hết chỗ','4',500000,'yes'),('A102','A','2','Còn chỗ','4',500000,'yes'),('A103','A','1','Còn chỗ','6',500000,'yes'),('B101','B','4','Hết chỗ','4',500000,'yes'),('B102','B','2','Còn chỗ','6',500000,'yes'),('B103','B','0','Còn Chỗ','6',500000,'yes');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `IDStudent` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Fullname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Birthday` date DEFAULT NULL,
  `Gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDCard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PhoneNumber` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `University` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Syear` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Eyear` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Image` mediumblob,
  `active` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDStudent`),
  KEY `fk_student_room` (`IDRoom`),
  CONSTRAINT `fk_student_room` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('ST1','Nguyễn Văn B','2003-09-10','Nam','123783874','0168666687','ĐH CNTT','2','ĐX','2021','2024','A102',NULL,'yes'),('ST2','Nguyễn Thị A','2003-09-10','Nữ','123783874','0168666687','ĐH CNTT','2','ĐX','2021','2025','B101',NULL,'yes'),('ST3','Nguyễn Văn C','2003-02-10','Nam','123783874','0168666687','ĐH CNTT','2','ĐX','2021','2025','A101',NULL,'yes'),('ST4','Võ Thời Đại','2001-03-06','Nam','123783877','0949241357','ĐH CNTT','3','ĐX','2020','2024','A101',NULL,'yes'),('ST5','Sơn Ngọc Minh','2022-05-03','Nam','123786821','0949231347','ĐH CNTT','1','ĐX','2022','2025','A102',NULL,'yes'),('ST6','Vũ Thị Khánh Linh','2022-05-01','Nữ','1678123442','0949241333','USSH','2','ĐX','2021','2025','B102',NULL,'yes');
INSERT INTO `student` VALUES ('ST8','Sơn Ngọc Minh','2022-06-08','Nam','281256140','0888734218','ĐH CNTT','3','CX','2019','2023',NULL,_binary '�\��\�\0JFIF\0\0`\0`\0\0�\�\0C\0		\r\r\r\r\Z�\�\0C\n\n��\0\0\�\0\�\"\0�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0	�\�\0]\0			\0\0\0\0!1\"2ARb	#BQar��q����$3C�\�\�\�SUcs�����4Wt���\�\�\��%567Tdev���\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\06\0\0\0\0\0\0\0!1A\"Q2aq�B��\�#3R��\��b��\�\0\0\0?\0�Є B\0\�q�e\�\0:��K�A3\�~YG��\"\�\�1�̰P�\��5>��2R\�8\�����\�%H\�$����^B��\'�\"GUʾ�H,�\�r\"q\�-;\�\�\�4s���(\�,��:i\�\�/̑1Oh��\�7%\�)@��\�\�\��!\�;\�=\�+�e\�fq�\�\'^t���\"�[�3\�jX��gf\���=�\�K\�kz�Vտ7>��oL�2\�\�\�9\�_BF-�6�\�\�\�MH\�\�\��yU\"_�\�I�F�4ɕ����=��j��=\��\�]U�M\�\Z��S�}]P�B\�²?\�\�r�6Z�\����I�(�\�:i_�8�A��[�1�#\�\��\�\�\��Z\�bYuKU�\�^\��x\�\�\�:&=4sK�����ItNN\��z(\�¾�	j�\�\�\�k\�h-\�L\�U[N��\�rd��iO�0\�!\\Wg���{<9���\�Q\"ZY[\�&M�hR3zY��Z�sH~��\�bb5�y4�o\�2sn \�VQ\�/w�y\��k�>��9,�>æӍ�q�\�@P�ԭ�g2%��qF^u\\�w\�\r��C��\�g\�UUN>��8U�]�j�KԟZ�<y��\��a\�W\�Ա(0�\Z,���b�>��\r	�Fi97S\�ߤ�\�X}���~H�\�\�/\�y,^�#5g�,xB8BL\�Qc�^F�!\�,�!\0B�!\0C�s�q\�\�\0:�\"\�\�e\�[�[��\�5�\�d\�Xn�񠈢u��Q�V��X6˕��\�\05�C{��P���\"\�n)\\X�Z)���/Md��Ͷ�\�\�.����\�\�竉G\r�ĺ�i\Zu_\�\����Z�o\�\�Cr�N\�����\�^w��e\�^}\�\�t\�q\�\�DE����\"%fܶ�\�^��\rd; n�\�\�~��&PB�!4S\n�N��gG\Z��^\��`\�B�va\�Y|_a\�mƋP�������\�\�hj�5\�\�ɪ\0v��P�f�~#�\n~\�\Z�����\0\�\'\�֜\�j%��Z�\\��t�\�&\�&So2hBI\�EH\�L_X!�wV\�b�\�L\�^/©\�~M\���K�\��r\�j헡Zq/C;]b�\'Oe����oD\�	���_�<@��Q&�\�y��[��\�(�ru��\�1\�sw4���Xdtr7G\'�\�8n�c�\�\�9�\�b�\0!\�`�u\�<Zx�|\�lE�\�]\�@kL��)0\��[\�%]ȑZ�k4\n\�b�0JI�N�\�H\")��\�\�\�I�bM\�\�Ne\\f�/�)҄Yrm�\�\�E\�\'R{0\�~\�kF��ģ��r\\Ş�D\�\��b\��]\��\�\�i�ҫ������o�?�\�E�D6I#��OAҧ\rH[\rѨ!G1X�{��-Z\�5J�\�==8�f\r2:�=}\�\�D��=���E���\�\�\�\'8)̖�C\�.�.~��\�\�jК\�\�\�HGs<MC\�ev��N�i�\�[�\��;z�;PyW�\�7�\�.��љ,}�\�:��\rNF���x\�¾�n��b^Q)\�<�IR��\�L2�[i�\�\0S\�#�$TO�$aabj�e[�����V\�Dc~\�`[ke;G#�\�Tꋗ8]��H؊�qyҰ+\n\�ZAfϑ<�wy=J2:&\�L�8l82�v�j)�9ݫ\�w\�K�w�4��D�dA?\���\0aD\��;i˂/\�/�\�\�T����UQs�CJu�q��\n��[��φg}\���\�v\�Ӧ�t\�˘-\�!�\�\n��1�\�=�\�\�\'�\�ĥPQ3\�\�4�I3�\�&6�L��x\�\\���\�\�\'\�;\�\�\�g&ٕ~�\�h\�V�\�iO��\�E������\���K�^\�Q\�f��Z�9#V�\�OK:�8\�\�	���tGlh\�nFk��X3$��\�L�\�\�\n��P���2Y½�h��\�,*Gy�Wױ\���W�\�\�R\�t�dg\�2\�\�E\�h{\�O�W\�\�\�G�M{X�$�ڵD!\�\��.|%�\�}t�Z���Qr��\"�s\r�K��؞�K}Qo\�A�\�\�$4Au�\\����D��H��jXoz7S�\��\�(�Q�ϔo�=\��S��\��t.\�q�\n3!ڮ\�%o\�l;\�~�\�kV�+�9j�6d&%f�tq�&h�SߔK�QST(��\�z�ɢ�\�N��:�TuU\��\�#�\�}������p\�j~\\\�\'\��\0�\�t�L�\�\�K\�\�$���\�\�(�VKS�\�\�\�0\�X�u\��شi����h�l�\0�pm}C�����\�e\�^}\�\�t\�q\�\�DE����A�Yu�\\\�=)�\�Ì�\�\�:�\�\�B�\�֪ .F\ZOF�6�Nf��\�TE\'Vɴ\�<o؞�[	\�D�u\�+3\�y\�\��*/��\��wee2U\�U=1��\�\Z\�\�M\�iN򬒊M\�)\�\r�K�\�.��4X��U~Po\�]�\�\nal\�\'Y-\�2i\��ODL1�#�=�\�\�y�\0�x~\�n�\�c���\�ڙe\�	ÊG0\�B\�B�Ȅ!\�q\�\�ts\�\�$\�9�T\0Y8��\�\� Њ�\\�E0\�/2\�X.��\0R\�֋�p�\�\�j\�T�_�x�ħ�<��\�.\�wSr\�ò-\�\��գ\���\��*2��B��K=\�+\�q\�:�xm���v��%\\7\�v�2\�U\�%꟱�xE뎘oT\�{�铨o\�L)�ҏ5\��\�$=i��X��4.��z��.\�l�Tp�!ń�\�{��_�2�O�9�<��\0�\0[I\�,�/j&*�\0�cW��:\�\��æۍ�!-$%�f�o\�6��>b�	_�\�\�7*:=~�\�/�bS���g�\�R~\�8}+M\�\�7\�\�S&B�ҵ>Y\�9�\�%�3�}�WN*�L�}J�@R�kOD����ː��.��\�K7\��b�I\��%\�I�\�%�\�]S/;0���\�:�D\�H\�3Vv1\"or\��q��>\�ӓy\'\��\0�\��F�G�բ\�.+�N�Ki]��xZdrD$ު�\��ĭ\�/\r\�V���\0Te9y\�ۧ!A�8�R,\�;������\�bm����6i���~%\�R��\�M�\���9.��\":\�莗�gN�����\"g�y�\�tC�a\�\�\�\�R+\r)2Z�Rq&\�\�{�<7�\�\�\�	.\�TT\�%X��ZƢ_���:\�B�\�\�É\�0.�OLI.c#�?\'\'!Op\�\�\�\�s\�v\�=y\��\�\�$]�7�5\�:�ګ\�\\\�\Z5��#\�&\�{�^jn]\�\�����wS�j�jl�\�+8!�L7\��hx\noL\�c\�\�`�\�\�L\��\�\�\���#0�xlmoR\�ks韣�T\'�/i|:#3\�+\�My�E9I���d�ĀudI\�]Wk\�\�H�/��\\�2ԙ��\�OO\�\Z\���1�1j�;zДjfm�\ne�&\�\�a�󈽲��K\�\�D9��Д�Fje\��U��o\��c\��6$�\�>Ĉ�mˁR��/\�3�\�R����\�m��-����G�\0�K\�\�ӼwC���$\�X�\"�\�h�*�\�\�\�\�\�_\�ۙ�\�Sq\�\�]�\�\��Y�x\"zy)o��\�\��\\E\�m�Ѽ�2\��\�xx;�\�\�\��\0���\0�\'|�e�8�H�\�w?��Ϙ<�I�ۧf\�9qʯ����Wi�e\��6(�^�ʒ�G\��y�\�\�F��x\�3�\�	�\0��_ev�\��\0\�FT��uZsb�k�̲_O$Q�0yI�cc�\���\� Qf\�_-J�\�\"\�\�\�sIz�##/\�f5\�\�W�t�\\%\�U���~Y\�M�=���u�F϶:\�\�[\0\�\���T�oĪ���3-�j%�\�E\��d�\r\�B��ccE�J\�\"b�PDK�+0��R\�D\�z��\�\�5�\0v\�\�6\�\�;C��\�\�ȼ@c\�\�t�Q�bM闧|Fm��\�&i,_\�\�S\�$��QA̚\"\��ĲE\��rԒX�F�HO��]J׺L\�ῧ\�H�B\"e\�#/\�_|��\�S�]D�W�\�;���\�\�>U\�}\�AIW��}�\�t�y�h��%\�N\�fk\�k\�c�~��\�\�9}M�\n��i�a�	]Axa�&\�EsR®��A\�\��\�$��\�zH\�r`�/WF���k\�!q��F��t�j3D�ʝm���}\��b%\�cۦ��<rzDO6\�2�1��^P���0\�C2�o��\�z�(��\Z>\\\�\�\���!n%�_\�7U\�M��\�U\�س�<\�.�{���c�\�6Z�B��I4-K\�0-2\��\�)�DD�[AS\�\�\��n�+�j�E׹���\'�\�\�L���Q-\����\��(�h�%�\"���V\'\�a\�D\�wG0ꇒ�г�Zá\�\�ÔZ\�(��\�tr\�ɧDĺ�#U[O0TLT�\�\�Qf}�~d�Ja�\�\���}�(\�\�\�x�\�`uj\�#�m��4YyY�3\�.�\�t\n5˱F���JҤ��s�2��^�N&�U�\�V��8\����+��\�G\�\�9A��WVG�\�\�No~\�t��FY[�.�	����oO�c>薥\�Ā���*�!\0B�!\0��\�\"N�\��t��pZ\�s�\�@MN��\�1�xGRw�\"\'x/�S\�/VϩL\�^\n2ᨓKS���\�\�s��H;1�\�\�\��~\�\�+V��\�\�\�:5z!7\�\�\�p�iSn�\nz�5Sf�f�TcM�q*_�:�w	O�׋\�Z\�M�\�s\�B\\�{�1�\�(juL�U\�)w=&R�B��O4�18���k�ćI\'\�T\�u.Q�\�c�ǣ�y��\��ߘ��:��4��\�\�	�]d>i{ä���\�l�7��\r��X�Qx��\�\����0An\�\�\�\��\�̇�\�\"�\�\�D!F=�\��\�q,ݥY�\�s}:h^ew�n_h	}\�F-\�n�T�nnD\�*�n��?8W�O�1\�\�ֳO<�1!\�ɢrw?�c\�H���q\�jΨ\�z���&?�1jǺ\�}&\�j�\����qψ�<1��\�s�\�_ݪ\���B\�n]Rf�?\�%�S3ΩPpĺ�7������\�C�3\�wF9\�>L$0\�i�\��忤Us��#\"d�uD��v\�j_\�\�t�9޿Կ\�\�ۆ�M�Q\�*՚�����+�\�̺-�Ч�D[�*Q\0�/8�=1y\�0\�Nd��%(59�Ĳ�p\��u+\nF�1ׄ\�q�\�\�\�\�;E\�<H��\�4�A^�i掾�k(��aǚ\��X��m\�-Br�-6\��t��iK�:HzZ�4b��/}\�\rjb�f\�\�&$\�Rn�6\�\'-+����]\�(�q�\0e�R\�[|\�\Z\�4ڭ%�~v����!\��HE�iқ�\�\Z��go\�cs�V��\�\'��d�\0_Gv�\��:Z\��4\�?I،p�\�\�H��&;�d3v��4��\0J>��=�d�j0�\0��X���Ca\"?�s~�\�-\�!�\�>�\�Q\��\�\�Gh@I\�ܘ�r�^\�(S�\n=R\�wc�}6-w}�a����\�Jim��?@��c2��a�\�<ױ�\�D\�\�oQ)�du����n��<\�\�E\�\��\��3N\�\�-b�-\�\�pљ�\�)/$�\�Y�lfPW\"\�\�D��W�iһ�\�\0t+\����\�v�t�{W7\�<)�H><\�}G\�\r<\�\�\�\�\�\�\���2�Hb�F�\�\�$&�bfY\�q�E|\�!ܱ��~�{\��lS\�)ř�$��J;\�KMi\�i.\�t\�J$g���\�:\�\�L�S\�e\n� \�b\�혃�>ؖ�\�\�i���uG0��L�\�!\'����h��s\���\�B\�ڧ\�i�D2��\0���O\�\�\�\ny)d{�3C��ͳl\�\�\��k:�\Z�\��/\�B�R\�.���J\��vpU>\�4ڗu\�\�\��JC��IK��4=&\'s\�!�8Ի\�G�B\�x�m%E쇖o�)\�h��{.fRZ\�J�f\�m=\�(�\�{�\�MazHƹ!\�\��)�\r�I-m?�2�\0\�\�/�ⱍ6H���]\�YY��\�?F2P�zb}[�-�!圣vޕ?�_\�xՇ�\��l[��D\�.ēc\���g�Q��\��:�t��\'�\�9��L�\���\�~�:�Fޤ��QL\�\\\�u%\�\�ߏ1Y�Z��Z�\�ҢE��%�\�mǉ�,�\�o\�J\�҂�5㜯D��,\�cR�=�\�\�\�ܫ\�5\�\�\��\����>=�Z.i/؋�v�\�\\T��&츐��BK#$��\�ſ�Zz\\W�\�#�\Z�=��\Z��%C��s3\�)�\�\�Xʸ\�i��%���\0\�2v\��\�\�~�-��&\�nv���J-\"Nו\�$ƧPn_\�&\�\�#��!Ԃ<�wKLl	\�l�Ɠ\'1Z\�\�\�%��2l\�\Z\�\�Se�\�\�Yk�\�\�\�f�\rHR�N�Ӈ��\��|�G\�6WļS��-��Sf�q�\�np�q	�P.h�^��><GË\�gf)���n\�\'دKLx�>�\�\�h\�\�-\"Y�/�.?\��ePvW�ӫ���83�d�ӵ6YpD�=9���\0ܻH����\��\0���\��\0��5쭉\�Y�\�W�\�3Br�&\�`\�\�\��\�p�4�����\0Ҟ�kK��[�\��F\����r�E��)\�&\�e��l�\�\�|ud\"Y��\�\"fͻ/^�\�b\�]6\�v�#)-Qr@��:�:d-6z��7\�_\��?�l\�\�Va�S3#��\���FÛ;gi�m\�}[�y���\�ǋT*m0\�&Љi\"\�\���G3��6\�\��^\�_�\��O\�8��j\�\�Pn	\�Z\\姩�NK\�0_�q�\�C�n3�h�X/j�n�IJ:,��r]HrD�w�Jiv�c{w��rQ*�74�)9�.�\�ǋͷ�I�\"҄<�w�TX�#�*\�])\�M�qi�)�#:�̲\�f�\�R\�䛇\����~��3{/��E[�\�\�2\�K\\˕Z��֞�D\�lìm�l2(�/�:\�{>&\���cL`\�\�\��\�\�oj\�\�\�\�&A�`{-4<\�_�w�_G*�\�RS��\�5&nl�\�\�?_\0~g��#c�a\�,H8t�~���5\��k���\��\�p�s�\�\���Q\0\�/�w�\Z�\�\���\�s;\��!a,\�\�%�ގ\�\�=6�)5r\�\�G�\�m��\�0���\\�\�$�\�P�k��\�q�#�)\'~\�\�S�\�\�Zј�\�%+��\0Tn�(\���s�ɪ}\r�\�r;]�ð\��9g\�1P\�\�!BA\�N\�\��\�ҡ��R\�T\�J�8؏iiq\�%�����\�o\�����3\�㮈�Z_[\"�f\����?�&Fh���\�|d�%V�o\�\nN������~g6 W�F��]E\���\�\�\�:���\\�\�\�\�\�c�YsW>\�w$\ZW�\�A:舦�!&�D�\�%\�\�\�\�F\�\�֤!\�\�!@�\0!@�\0!@&�����]�\�b�\�\�r�M���#p�\�B\�\�o��̙�vn*\�\�D\��EE\�\�ć\"FE���t{�q&\�\�\�4N�\�\�`?[\�p��h*j�϶ҏ�	ϴ\�$�\������u�ͺL�:��ަ\�\��S\��\0ܴ�%�OC\�E\�a\�\�~.�\�!CZb\���aV1~ܑEΦѨ�e�\��F|l3E*�8�8A\�\�Rn���^L~�\�H1�éL�U\�\�xlwDO�e}�s6�\�hV�4L��a�\���)\�DL��\�U�\�\�5�x\�u\�K2�\�:<\�/��W\�tL�\�\�0�F\�\�		t���j\r������|��E\�W��S\�BK �Xͭy\�nIE^V�0.\�N$>p�è}\�\���U��۲UiE\�i\�\�M8�\��*F��Ul�h�:b��<������E\�=m��K��\�\�\�\�\��N\�V^\�p���oF��z�?\�(\�*\�>J�K��T%����h��a�\�l�I	\'�W(�\'1\�J�X\�=\�*\�>\�H�\ZY��}���$C�a<�q���Pn:�NA�u7ƙ:y�eʈ�	syщ��_\�\��\0\�\�ҔJ/\��4]�y?�\rG3���|\���}\\�\�6�~�y\��\�g�s1��,�K\'wZCw�c}�k�Hg�\�[W�cV?�\���\�>k=:X�[_\�MD\�\�\�Xn�,P7)��\�c\��\0����>,\���\�[7\�\�:\�3j&N\�B\�w6�nR�\�\�\�8�YOG\�\r�\�G\\]\�\�w.�hՉ��\ZU%��m���o�)\�\�♦�ڦ�\�3�\"\�\�;�[Qkl!\"\�i�\�\��\�pK��r\\\�=�\�\�\�\�]t\�[����og����ڝ\�\�f~�_�2\�\n�����:.���Oj$F0\�\�s��h\�v����VpC&\�\�{�<7�\��x\��\0oI�\0���Cׇ2�G\��	���LNzh��h\�5P��\�/��f�e\�\�\�\�h\�\�*U\"V�N�j^NI�fY�\�H�؎�N�AL�J��\�\�ܴ\��\rE+8ۚK\�\�BQ�\�Ě.#\�cR�:��9̩�r�\�o\�I=�\�I�:\�D�7vҪX&tK*7ʅ�wg�)�=VJ�A��O��KȰO<k�DGR�\�1DDD\�tF]�qeil\�4ב�T��\�]\�ȗ4\�E�\�O9#��	^\'=ExLl��\�bu^N\�k�\�����\\S��	�{OX��>\�\�v(��Ad�\�H�^禪\�d2������.\�>ܻ\r�:B-�̣V�r\�u{\�\�+�\�	a\�\��\nVԬ\\\�&��T��6;\�\�2Ov�pN\�n\�\�:E���rR��\��\�%%�\��u\�(Z\�\�Y۫{%4��y};i �\�T\�\�1�}�V�*�S�g*}{T\�J=w��E\�,Nl\�z\�1\�\�\�_�c7Oa���)�́/S��7\�!_QG�e�D\�/\�<\�\�2L�~䦿\��u�\�a�ɷ\Z-&&\ZHJ:\�)QZ�)\��ޏj9�EBӭ\�mێN�Ku��x]i}(�aw|ا\�ޱ�\�\�ib�\'�\�Ț�M�\��L\�\"Z�N$C1A~]I�u:@^�_�Qb�E\�\�\�\�x\�&%\��\��\n��\�>aD\'\��~<{C\�\�L\�\'��s\�n\�rZ�C�	�9�\�W=+\�$\�*<W~q3\�_m��U\�y㊸nlM�V��/4_�F����mP�\�\�]�n�ri�x�N[N�=K���\�Aُf�On��vݯV�ݩI���ϓj :ĵ��H\�9�3Hr\"��!\0B�!\0G&K�x@��\�-�\0�ݢV\�BML�;�e\�蘗Q\'�5\�n	\�m�\�i��\0\�	$�\�!\�\�[\�\��h|\��d�rUM\�kb��B��gh����S.�\�s\�0%\�H�\�j��珓\�6�]\���|\�=>hj͌;��\�3\�jBZ�\�ٌ����nƺ\Z�Цy7Z\�\�nm�Ӥ\�P�\�E\�^1a�w\�h����%\�Vm&\�\�{�<7�\�V\"�\�e��\�\���\�OI}و���&\�\�V��?3w�\��\��5]Dw�.�|x*\�\�\�E\\���\�\�\�F\�\'k\�W\��\�׈\�@{�ъt#6�/�\�j��<)W+\�b\��\�B��\�F_\�\�\�;��̲�#A\�0\�擿�O�s/�R1�n��l0\�:㥤QD�ن�n�\�9Z{\�?tf\�\�g\�:\�/7\�M\"���|5U�m\�\���iG\�[�};�(�!��\"^Pb8$EEL\�R9�T\0C��p�\�u¾\�\�ȴ����\�\�\�\�w\�,�/j#\�l\�\�\�\�\n\��\�?)8\�4�d��	nTX�x���,6���̩�N�\�\�:qG>Q�\�w�\�_G�l�%c�VtR\�\�.&l����y\�\�׺�Ƅ!E��{\��(W�޻\�T\�W\�?\�$�G�=�\�.\�\"\�	��4Gz�{�:	۪)�<%ěwh)P�͡y$ı�^`�ć\�\�ދԫ�\�\�v\\cY6�z�nVZ�\�\�/H\�3\�y�ҋ\�.\��bM\�\�Բs \�:��I7�+Q�\�\�e\�\�\n�:�zxD��^)Sl��R�A�v\�9\�T�\�\�\�	;��`����n\�)��xOR\�Ӓ\�&m�.\���\�*�\�/D<��� c\�\�k�E;B8Nc�ɮ��D!�\0\�d��Y�7��R\�\Z\�*�Kr~�Q���i3\'�p@G\�R\�\";\�\�r\�\�:���Ǒ4�њM,��\0\"_kJz5Bi\�Eu{�Lf�FM�ث�\�d-�&p�0\�a�\�\�NPId��\�|\�g��\�\�ݜA#Ӭ��b�u\\����V�Q������=�\�S\�!~\�Y~�\��\�L�z�\��\�\�\�>��!%B�\�0ڣ�7�t\�nQ�t��*b9ho�>�.\"�I\�\�H!t\�\�\���Q�\�;DC$l=��\\�}�V%�S\\ʞ��\�\�\��bb�\"&�L�(�\�Z}�A��R\�\�ZRQ�m��7�d��QL���7�U�\�F!\�\��b\\�\�N���\��B�c0�T!԰\�\�\�-\\�h��j?C���&��\�=��LW�Qb\�L�\�L�摣�׷k�\���+d�\�9\ro\�͇\\\�\�\�-i�ҋ��\���o�?�\�ű\�k�[nQ\�ҨbI�/\�>�⊑��.<6��I����_�T\02m\�\�vK��b\'�\�:og\�^|\'\�d#m{+�T�K�4�B\0ЫY�U\�kOxսZ��u2\�H�=��3؛V^4�n\ZL�e�s�^.�~�ө\��P��ݱ&8d�Ø\��\�\�\�~\�ζ���\'��)j�-r\�+���g\�䈋\�ƕ��S>\�+W��~�Й\�\�A�\\\�\�����\�q�]cz�\��\0!�\�,2A\��\�E\�����\�6��8S \n�]҇�@�\�a\n #��r^\�C�=�\��S/\�Lۋj�?�E\nd�N�YsU�~M>~T���\��շ�E\r�r�\'IL�\�O\�\�N�\'\�Q�a	$\�ٓ�\�<Q\�,<\�\�\���\0�+u\�q]S�;qV�*qu\��=�菻xB_$�y�������\�\n#[��#tN\��\�p�\�Ě\�K\�[Yzk%�T��m�t{E\�]\� �I���+�\�ҁ\�Xv��7	l:\� \�-\�h�.�\\\�&T<��}���:\'�X�[\�f�Ge�x�5�k\�p���w�\�	�*\r�l�,��\�y\�\�\\|�\�ˊ��V.�$A]�ӊ�\"aB�`f��b�\�(�/6\�r�:\'�\�O� �\�8C�AB\"�\02L�\�8\�a\0Szp�e\�B�\\���Y6f\�fC��Bi\�E��Yn�z�\n����c\�\�#��*w!\�=\�\�U���b\�*��S�����\0tR󽨏�,�\�\�\�\�N4ZLHt��mF�\�r�8\�8\���/�\'\�\�<V���?,���\�e��BE�;xf�\�%,���=f��f�~\��� #/\�Η\�NL\�e��Ow)*XG�\�o_sR��L�\�\�0�D\�\��m�\�BQ��лG���ٺ7پ���#\�B\�:�B�� B\0�}%Yra�a��\�-\"\�c���-k�\�\��1���D>q\�U�^|Xa�u\�KH��2���[:ߗQ\�J](2%\�ٱ�=ָ������=��U�\�?# �U\r)�zo\�:�\'P����qM\����\�\�E6\�\�\��N���\0�i�W\r�\��.S�ۈi\�f&3\�s���\�{1,\�52ޤ5K�I�)),(-�\� ���\"�(��&\��2�<�2OVq%�N*\�шSy��ܬ�\�w.\�\��2OD�!X\�!@�\0!@�\0!@P�\0|\��\"\�S�8�o\�7�/�\�9�ӥ&��	\�1\�\'̰�k\�D\�\�u�ya]�Q~F��I�M��\�7\�=W?#2\�\��\"\��\�Q�n]��2�FRR�P�|Zi[$�\\ҟ\\!\r�q�W�\�_fb\�]\��\�X�\�4�\��N\�5dD\�+2���C�(�TJ̡i��O��\�J�\��Z8\�6�k\�v��x�}�\�\��w�i\��c\�-E�L�R���W�.EB8\�j��Y�\�ĮoR�C\�B��!%g\�WD��V\�-1|[{2\�}ID\�edim�I&f��\�\�O����`ąQ�\�L�*��Z���2���MQ�~\�f�\�2�2���z�.q̣�\�Ë*\�gM�@��=:I\�m	\�OAs�\�XB$\�\�\ZyP�^\�\�n�����}���L�Qԑʤ!\n��Ֆ\�\��!\0B�!\0�\�','yes'),('ST9','minh','2022-06-13','Nam','1234567890','0999099892','USSH','2','CX','2019','2023',NULL,_binary '�\��\�\0JFIF\0,,\0\0�\�\0oExif\0\0II*\0\0\0\0\0\0M\0\0\0\Z\0\0\0\0\0\0\0User Icon Flat Isolated on White Background. User Symbol. Vector Illustration�\�dhttp://ns.adobe.com/xap/1.0/\0<?xpacket begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>\n<x:xmpmeta xmlns:x=\"adobe:ns:meta/\">\n	<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n		<rdf:Description rdf:about=\"\" xmlns:photoshop=\"http://ns.adobe.com/photoshop/1.0/\" xmlns:Iptc4xmpCore=\"http://iptc.org/std/Iptc4xmpCore/1.0/xmlns/\"   xmlns:GettyImagesGIFT=\"http://xmp.gettyimages.com/gift/1.0/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:plus=\"http://ns.useplus.org/ldf/xmp/1.0/\"  xmlns:iptcExt=\"http://iptc.org/std/Iptc4xmpExt/2008-02-29/\" xmlns:xmpRights=\"http://ns.adobe.com/xap/1.0/rights/\" photoshop:Credit=\"Getty Images\" GettyImagesGIFT:AssetID=\"1300845620\" xmpRights:WebStatement=\"https://www.istockphoto.com/legal/license-agreement?utm_medium=organic&amp;utm_source=google&amp;utm_campaign=iptcurl\" >\n<dc:creator><rdf:Seq><rdf:li>PeterPencil</rdf:li></rdf:Seq></dc:creator><dc:description><rdf:Alt><rdf:li xml:lang=\"x-default\">User Icon Flat Isolated on White Background. User Symbol. Vector Illustration</rdf:li></rdf:Alt></dc:description>\n<plus:Licensor><rdf:Seq><rdf:li rdf:parseType=\'Resource\'><plus:LicensorURL>https://www.istockphoto.com/photo/license-gm1300845620-?utm_medium=organic&amp;utm_source=google&amp;utm_campaign=iptcurl</plus:LicensorURL></rdf:li></rdf:Seq></plus:Licensor>\n		</rdf:Description>\n	</rdf:RDF>\n</x:xmpmeta>\n<?xpacket end=\"w\"?>\n�\�\0�Photoshop 3.0\08BIM\0\0\0\0\0sP\0PeterPencilx\0MUser Icon Flat Isolated on White Background. User Symbol. Vector Illustrationn\0Getty Images\0�\�\0C\0\n\n\n\n\r\r#%$\"\"!&+7/&)4)!\"0A149;>>>%.DIC<H7=>;�\�\0C\n\r;(\"(;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;�\�\0dd\0�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\0\0\0\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0<>�zz\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�1#\�#T\�^\0}��oD�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�Y��\Z4m@|�MsR�\0��\�us�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�@�hҡ��\"�\�@\0\�\�#$���蝋2\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�S5+!=\�=\0\0\0\0\0d\"WH\����Z\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\05�jDW\�e�\"\�\0\0\0\0\0\0\0eU#+)m���\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0)����r\�\0\0\0\0\0\0\0\0��J����\�@\0\0\0\0\0\0\0\0\0\0\0\0\0\0xVR�YK�N(\0\0\0\0\0\0\0\0\0MHj݋\�\�\0\0\0\0\0\0\0\0\0\0\0\0\0<*)\\�ؼ�\�\0\0\0\0\0\0\0\0\0\0°�Z\�\�\�\0\0\0\0\0\0\0\0\0\0\0\0\0�T�^�0\0\0\0\0\0\0\0\0\0\0\0��\�\�\�\0\0\0\0\0\0\0\0\0\0\0\0\0�%R����\0\0\0\0\0\0\0\0\0\0\0\0\0!�[\�W`\0\0\0\0\0\0\0\0\0\0\0\0�J5n\��s\0\0\0\0\0\0\0\0\0\0\0\0\0^JuIE�~�\0\0\0\0\0\0\0\0\0\0X\�\�tw\0\0\0\0\0\0f��f6O@\0\0\0\0\0\nzWj\�\�\0\0\0\0\0\0\0\0\0\0\�EH��D\�\0\0\0\0\��A�\0\�\�d�\0\0\0\0\�\�\�W�d�\0\0\0\0\0\0\0\0\0\0�JEM\�\�@\0\0\0\0d�\Z�\0\09\�\0\0\0\0\0\�9\�\�\�W\�\0\0\0\0\0\0\0\0\0\�\�I�]\Z]�\0\0\0\0i*T\0\0\0\0݋\�\�\0\0\0\0*)[��X\0\0\0\0\0\0\0\0\0\n�Sj\��\0\0\0\0��\0\0\0\0��\�\0\0\0\0�Y�:2�\0\0\0\0\0\0\0\0\0\�5��K�\0\0\0\0\rc�Y�\0\0\0\0\0,�mP\0\0\0\0T�\�\�\'T\0\0\0\0\0\0\0\0(�Yb���\0\0\0\0�%r�\0\0\0\0�tyv@\0\0\0\0\�9��q~P\0\0\0\0\0\0\0\0\njW\�K \0\0\0\0\�\�,\�\0\0\0\0\0\0[\"̠\0\0\0\0(\�]\"]�\0\0\0\0\0\0\0\0�托\�\�\0\0\0\0�?�\0\0\0\0\0\0KE�@\0\0\0\0A�&�\�dP\0\0\0\0\0\0\08\�\�Y\"ܠ\0\0\0\0 �@\0\0\0\0\0j:B�\0\0\0\0\�sK%\���\0\0\0\0\0\0\0m*5y�d\0\0\0\0\\J�\0\0\0\0\0\0\�L�\0\0\0\0\0穩]2_@\0\0\0\0\0\0\0����.�\0\0\0\0)�\0\0\0\0\0\06��(\0\0\0\0OJ\�tiw\0\0\0\0\0\0\0\0�5���\0\0\0\0#�}`\0\0\0\0\0\0��ڀ\0\0\0\0ĨU\�Y�\0\0\0\0\0\0\04M��(\0\0\0\0�sT\�@\0\0\0\0\0ز�\0\0\0\0Ql�E�@\0\0\0\0\0\0×\�/�\0\0\0\0\0I+T\0\0\0\0\0��˰\0\0\0\0\0\�>�\��\0\0\0\0\0\0\0g1�r.\�\0\0\0\0\09�\0\0\0\0\0Y\�֠\0\0\0\0\0\�9ՖX��\0\0\0\0\0\0\0c9���vP\0\0\0\0\0D�P\0\0\0\0	�/\�\0\0\0\0\0\r3�\�d�r�\0\0\0\0\0\0\�\�욋\0\0\0\0\0\n\�T+�\0\0\0\r��.p\0\0\0\0\0@\�Y\�֠\0\0\0\0\0\0\�\�l��\�\0\0\0\0\0SSV�\0\0,n\\�\0\0\0\0\0\0�(6[\"̠\0\0\0\0\0\0\09\�zte\0\0\0\0\0\0\0|)F\�\�\�L%\�\0\0\0\0\0\0\0IJ��O(\0\0\0\0\0\0\0\n\"EWN�\�\0\0\0\0\0\0\0�k�&c)\�\0\0\0\0\0\0\0��j�\�&\0\0\0\0\0\0\0\0�%j��\0\0\0\0\0\0\0\�X\�0��E\�7�\0\0\0\0\0\0\0Q\��l�@\0\0\0\0\0\0\0�H��X�\0\0\0\0\0\��HR(\�@\0=7\�\\�]\�\0\0\0\0\0Ú�c��\0\0\0\0\0\0\0�\�L\�\�@\0\0\0\0em+f\Z\0\0\0\0	h���\0\0\0\0\0h�\�\\\0\0\0\0\0\0\0\0�4+�ː\0\0\0\0r��\�\0\0\0\0\0?\�\�\0\0\0\0Y*\�z�`\0\0\0\0\0\0\0\0i*Us��\0\0\0¨��\0\0\0\0\0\0\0m\�\�w�\0\0\0<9\�b��/\�\0\0\0\0\0\0\0\0\rs�\�!@\0\0\�LH*\0\0\0\0\0\0\0��K&\0\0\0��e�-\�\0\0\0\0\0\0\0\0\0)iW\�e\0\0\0xS��@\0\0\0\0\0\0\0\0\�_�\�\0\0\0(I]]\�\0\0\0\0\0\0\0\0\0h�\�\�8�/�\0\\J�\0\0\0\0\0\0\0\0\0\r�\�K�\0\0\"��qwP\0\0\0\0\0\0\0\0\0- j\��\0H\�|�\0\0\0\0\0\0\0\0\0O\�\�@\0|��4k�K�\0\0\0\0\0\0\0\0\0\05�ufH苘\0\n\"DP\0\0\0\0\0\0\0\0\0\0t	d�\0�%R��mP\0\0\0\0\0\0\0\0\0\0\�T\�]\�\�`\0\0\0\0\0\0\0\0\0\0	h�(8�Y�\rr\0\0\0\0\0\0\0\0\0\0\0<(\�\rVȳ(���\0\0\0\0\0\0\0\0\0\0�K�>Mj�\�$\0\0\0\0\0\0\0\0\0\0\0	\�\�N�\�8���\�\0\0\0\0\0\0\0\0\0\0\�K(c(iV���\0\0\0\0\0\0\0\0\0\0\0\0\�(	���4�%=+�\0\0\0\0\0\0\0\0\0\0؋�ɘ\�2EU�-\�\0\0\0\0\0\0\0\0\0\0\0\0\0�(i��1`Q�\n\�\0\0\0\0\0\0\0\0\0K\�\�v#j��\0\0\0\0\0\0\0\0\0\0\0\0\0\r��\�d�b�\Z\�I \�\0\0\0\0\0\0\0\�[	\��)	�VH���\0\0\0\0\0\0\0\0\0\0\0\0\0�Q\�:�\"\���*\�\r@\0\0\0\0\0b,��r%i*\�\�n�\Z�\0\0\0\0\0\0\0\0\0\0\0\0\0\0�����,qg\\�\� RР\0\0\0Bb\'	��QQM\nۋ�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0S\�F��8�.��\Z���\�x}���t�Y\�%e\"�\�\��\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0$U\�j�&\"p�\\�\0\0\0\�D�HR�\�Z�t\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�J�C\�B$M\�is�O���f�`����\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�!� ��@\0\0\0�*L,��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�����\��e6\r�\�2�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\0,\0\0\0\0\0\0@0P 1!234\"#�`�\�\0\0�\0\�~�\�]3�?�O\����\�\�2�s\�Y���r\�=\��\�*\�\�\�\Z�Ssl\�ke2�\�+��-��)\n��\�A�djڜ�!��!��%��kq�Q�\�\�k\���lqI��f����\�a\�\�\���6My\�j֦P�b-�Eba4\�.��\�jNV֤�lZ`�L\�\�;Ebr��,\rE\�Ip�n:\r�\r�\��3�ԃ�g�\�\�^�lHǥ)bY}FTx�k�|e/\�2\�VSiB�k�\n-�-\�|WXC`@0W�ְf\���\'��0d�i�/l��;�\�khiCCQ\�u\��Y�+u\�#�w�=~�*F��\�Z��u���5�EZ�C�]��~��v�1P4���|��\�MpVhо�d���I٫ҕ>��!����\�u�P\����x�\�h�B?�أ݌I�T�1h\�\�\\%-�A�\�\"\�\�`�mzS%գ>r�VAn\�.�\�[~\�\�ص�\r�\�;�\��O��p��\\��)�\�\Z��\�c�jŪ\�ҩ�R�_$ݛ=�\�^�|\�lh�\�=�*\�\�(Zy�Z\�\�Sc\�\'\�9s1Xh��\�I%�\Z�>m�ǣʳDT�=�l�M�T\�p\\��\�B�}���y�jʖ��\�\�Q�DQȈ��s\�ţ�\�C#���^S�\0\�\'�R׵�\�pzj�\�_��7eL\���m�������`\��P`\�Mm�7e�NܽGă\�W\�3\�R�J^����m\�k/y~?\�N\�y�6\��	p��\�wͶZ��/���i�\�O6\�}�\�+=-y�N\�=�VN�8���5t\�K͹�\0?\��Nۢ�l�}��\��S�6\�=\�\�+My��>)~�x\�۩\�׬�w\�\�K���[�}\�\�\�]�\�\�z��u=\���Dn�^\��\�#\�\�O�i����w�p{_�J\�\�@C\�~=\�\�,�����\0s�aT\�(\�y�*�\0^�\�\�����\0j?\�\�\�S��&��-k���3M�N-��5?\��\��rc\�|j�v�\�\��\�/�q�\�\�ϳ\�6\�8u���*\�\�L�*:�3\�i��f\�\�ږ\�o�j\��}@��\�2<�ڿ�\�1p:�[\0�W\�\�\�\�j+\�i^��=6�\�\Zb\'%`N|E�y�zk\�ҏsO�\�7\�\'ų �N\�H\�\�\�\�\�\�+9W��V��k~�0u\�l>�3NOp�mj\�\r�\0�V/�1I�\�\�`\�dx-\���{�N����\�>\�Y�/C~{ޣ�|)�i�dp\�\���\�)�q����i\�Ziz^	O+{��`�[��\�\������p_M`�i�7�jMֿ�ݟ�vH�\�lmS\�ѻ\�rR\�\"�\�p`ڳ[ ~\�^)�ha\'�0\�q]7*\�<[#�U\�@}\�\�\��|ױ\�[ñ{�<��\�\"�U�xv,w\��ʡ�\��\�_�:�~;\r����b\�d�G�\��\�\�~\�ͪݳf���&���kM\�\�յ\�\'\�31�O6�A/\�8jp�v\0k\0\�-L?\�d\�}�j,|��\0�~њ�}\�\�\�)ܦk��\��?\�W��?e��ơP\�\�k,��5)�?b�\�&k��]����\�7}oC�Y�\�M�%aP�R����Y�{�_M�w	9խ�d��\�ޣ��٢f�.\�}+ё7\"f���\�d\�Z\�\���u��\�ޣ��٫f���mJ42�\�$Lģ����������\�S^F0!)�5FܻV\�~���\�+F�prb[9�Dţ�Vue !쮬b�i��V#7\�5�Rp\r����*\�U�\�1\�\�!c�8��\�\�<���e)Q\�&b!��FZ\�i��JZ\�\�ڱhoW5\�\�E���\�cJ��X\'\�\��/-rH�\'�V��\"+�\��0\�Y�]b�eR\Z��� &q�ʴ\�\�p\�|�h��\\��ke�\�mC��n3���\�5�\�79\Z��+�&SN(\� ��\"\"=O�\0q��\�\�6�u39JTu����cSK\�(\'\"\�Y\�\��mW�P�$yJ\�\�\�\�0\����_Vb\�\n\��sh>�$\�k\��L\�\��G�ܖ2��dm��՜��g\�_>jѓ�R2\�u�-�\�\�Y�\\\�/�\�B\�u�\n�_\�J�M�\�\�p��i�\�\Zl�$ോ\"�X�먽�ڵg\'L,�.\rl�\Z��,\�i��\�/]z�ʎ��\0\��\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\�\0?9O�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\�\0?9O�\�\09\0	\0\0\0\0\0!\"@Q012Aaq#3BPR��� br��\�`����\�\0\0?�\0�}��\�?\�\���\�\"yخ��]��V\�y�Z��G��3�\�\�w0�\��W�3�W\�r>9�X\�\�մ3\�u�.\��]�\�\�.\����=Ws\�kg�\���\�\�[�\�\�/���2u�QYW�S��\��2\nP�s�\�}�d5Sr�k��%h/\�U\�m�Dհ�\�b\�\�9�\�V\0��I\�-<h�I��Q\��q\�\�u��V_\�\�麊�iq\�\�x\rj\�9�\�Ohp\���2ٷ6\�0�ƾ\���\�\�?�\�U[q�gEV4��U��\�*�\�\Z8a�*�\�)�Y�aEhn-*�k�\�q�ڭ�,�x����K��*�6\�Z\��*�#EH����{��\n�{��\�̪�/�\�cj\�l®\�\��\0TM���5Y��\�\�(&\��gD��y\�U`\�s��\�(�\�j�lv�\�\�#w�s�&\re0H\�!\���D�A\�2!Vn�\�/0!{ƌ�5�� <��\��#��\�f�Fj>U���(�5o9 \�	\�\�41|k\����`��\�\�\�~��&Jo�\�y�s+�g�횮\�a����\�Ӵ2��\�6�\0�\�q��\�\�<_w\��\�\Z8�Amn%[��lV�\��+� R�\�\0�����N\�S\ngm�;Q���\0Tt��pz\'\�V�\�\�žϐ��\�\Zr\��uu�\�69Lc	:�tOd\�e6wi\�&��e��\�[\�h=��i�v�\���W,`�5�_*�ɺ~����4���h 晃�t7jrt7kiM���1�\�\��ۓX\�n)�۩�L\�܋ݬ\�?�\�a\�Ӷ8\�\�:3���S�u��F=\��A\Z�i��\ra6 \�4Άw�Zu�ܝa\�~AC�Y\�w\'<��	�i\�\rO��D\�b_\�S�v\�\�q�ߺ\�=}\�4>�\�\�%C\�d%�h\�\�8G\�\Zw0����\�%��\�\�\�\�g���`\��A��k��x�\�+hc2n����>\�c�m\��\�\�s�csp�D	\�\�|�x;\'PD�<C���\�#�:\�\���\0\�P�\r\��\��5�\�l1\����9a�*?\�\�n\�Q�\�c��\��4E\�0\�\�A�pU\�\�7\�O.\�\�(5�@`b�&���Ə�83��f�t�7{�c��E�h�\�pȦz\�+�\�J#}tU�]n[\�k��Ģ)\�0�Gܡ�\'\0G8N�rܶ+�^s�.Bq�]k�B\�q�\�p�\�\���\���(�9\�\�V�g�Wb\�evG!��\�f�|m\�\�~bTC<%���\�v�\�{\�w��\��\�\��W\\#���J��!,=o)��\�8)��8�2qvL��w3�ج�O;W[ժ\�\��87�2���!\��\n*\�x��\�\0\��l�\�\�\�i]� j�\�p�R>\'�K�\�x\�\�^5=5V\�~Y)\�t�`�ƾ\���]��\�2��\0\�}��\�\�\r\�\�֔\��9\��-fc56\�w�J��\�x�Ѩb]��-:\�i;.�\�\�WG\�\�\�\"O\Zƌ����{�_J5?�@�\�l:.��5��!\�2!V\Z���\�,\nI��\�Ź���$\'�\����o�����\�{�\�Y�\�!\�g\�ҷe��\�Ѹ\�g\�\�/:�\\\�2q��\�j\�~��AwE�@\re5��\�]\r\�\�c��\"7r��UF\�,\�Nб\�W�\�*?�\�`\�\��\������\�V��\�<��\�>�\��\�d��\�rA��+�un��@�m�\Z�\�~����6^\�A�\0	��\�;G�K&\n�\�[�ttqX>i16�\�\Z\�2UW�o*\�dɃP���/���\�w�\�<Z��\n�\���DLbA��e��Mf�\�@)!�B�(�\��,��\Z�,x�\n`ȡ5�\�s��+��*�\�#��\�\�5Rd(�\�,�56�(��g\��u�q\�Tx�\0tq\���\�b6j�>��8z�\�5Z-�|Sm�\�\�Y\�ʁ8����MH��+mf\�Qe��p\�\�4\�͓��M�cx+pR�\�T\�hUX\�\�� �߹Vq�*@L�\"G�ۛ��\�p�*��\�Z&\�\"��\0 O\�\n�)\�81=�C2\�[��]\�+�`\�\��RB�N��\n�|��0s9+/?\�\�\�\��+\�\�0��7�(\��\0&�\�xw\�~]\�o\"�F�\n\�0��\�~��+�+��Z�=U\����<�VB��@K\�5ݓT�Q�\n$ʭ���U`��	0�A5[��0�4M���I҈8��a\�Ǉr:k\�D��A�\�\�~!�TX��oʸ\�s\�\�\"aNV~\�a�i�2VE\'���6����7Kh�av�v\��]�=\�l\�u\��+Ϣ�ܫ*��W\�9޴\��)\�u^ul�=�3\�C�\��X\��E_c�\�i.\�w���xo+U��~\�&���\�Ɩ�*ȏ\n\��\0��\�{.پ˷�V\�>\�\��\� y��ky�q�\�\0,\0\0\0\0\0\0\0\0!1AQ@aq0����P��� \�\�`�\�\0\0?!�\0I�e\�`Y��! ��b\�\�VI\'\r���v\0�\�\Z3�y\�\��{�z�l4^iAH��\�S�\��zy\�?�71\"��?�\�R\��6\�\n΋\�_�C {-���g\�F�\�\�N�\�Q\�\��ՎF\�)Ϧ\�\�1�\�6w cMS\��\�N\� \�)V7�J*B\�SYc\�i|�H�&�c$��H=M\�9�9H��0\�Lfw��s9`:|�SG6v���\" ;G\�6H\�\�\�ې(*vONY\��|\�@R��ܭ�C,\�\��	\�ñ�\�\Z��\�zhB\�I�{\���3\�\�\�杘�RtN\���\��\�\0r\�zV́�ȒK�䡢�@��Ρ0�윜�]\�>79�Np\�� \�\Z�^\�0l\�3d��3g�N\n3\� �\�1y�LP�r9~�\��{6�\���\"~�����\�98� \�{�����r�\�[�<!\�\Z\0z!�\0�\0��/`Pz6�(\�\Z.�$�.J�1��v\"}�*xF.a��e�pE}\\��#x\�\�\�\�(�\�d��\�3\�9+\�B�8/�{� U|7\�M\r�E��ϣ \\�\'G��\�vI\'\"N���X3�)��3� �Ts��� #\"�\"P>s\�yj��� 9�j����\�z��=G�Q+2،B>�\Z �ps�-�\�2F`�띂��D\�ߎ\�\�`D�s\�x.s\�Vb�n;e3��>�\�gO�<\"x�D\�\�0.�\�f\��@\�A\�(.*�L\�psR��s�\���T0iY\�0\�w9*�A���\0�2;|3PQn�>\�40�ߎ��\�\�|�b�+\\kC^5�-�,F!EE���4ߘD\�\��!\�<a�@��\�3?\�㽊UP,\\+�z\�	&A\�R�N\�#\�w	�~M�@\'\�.<&��A�\�1�,y\�LR��\�\�\�rz!\�q\�+y��Q:\�uG._x_#|>8��?|��c\�(���.W/k�\�\r�M�c��J9\r��}\�s�>]\�n�e�s�A��Q\�\�C�\�A\�\�0�\�\�_UGtس��1B�r��2\�\��;�;e�`J7M\�x\\vȶu�ȇg;\0�*\�����w\'�X����?����&�\� \�A|\�D8Yc�a\�}\�J@\� �\�1i�>�\0L\0\��\�a\���\�Fh��d�x��0x�ՙ\�\�,2D\�VO�_Z@��;�ӱ\�qP(<	,����\���=�ø\�z�/����)5L��\"�\�D��[~HdL\�tw|r�vc\rc�2�\"��:\�ð�P�\���\�\�\�=\�퇨\�ڿ:>�\�\�P\�t�j��(���f\�\�RU\�\�8�̳�#�|���n�\�\�4\���\�1tl~�E#��\'`�\�C6\�\�\�\�\"\rտr4��\n\�#��\�|�k�;\�V�D \�&21\�@\�!�톦\\Y&�\�7�k\��\�0a�� \�s�b^��\�(�gV���:e����\���\�z�4�\����\0�)\�\�Xn\�\0rXxJ\�ȗ.r�b3d��\�BX9W�͔Sa̳\�#�\�w>@\0�%	�2˝�r���8n#\�87�0�\�6\��\"s��\0	�(�_\�3O\"@��\�	�o��� \0\�\���6?\�\�A!\"aD\��\�\r01\n-����icq�ye�\�\�ތ\�\n�X(m�\04i��S������f`9(�hh�[��C��\�\�H ln�;���D\\Y\��N;\"\�>�|�\�X&-l��F��\���_�t�\��\�\�\��\�&�&�U�;DԦ�zF0\�l$̝]?\�\r�\�\�\�#n�C����G,C\0*�1~Aocb�\�m$����\nv\�\�\�֒o�;s�\0��\�)�@�rJu\�\��6�Q(���A3�L6\�j�\�!�B\�\�r4���Ƽ\�Mʦ�t��C<J�c7588�\"^\�E�5\�H\�DD�&����u�\n�h�r\�{X.p5bwQߐ�6a�\�\0FfL˚244�\�L��t21$Epn���O.\�ȥ<���Maj\��c\��&�\'�Hc{�\0�@\�P\�bp\�>p`���O�k�1s\�$��w=�\"�%�A\�2��z�@\r�\"%:DT\�\��0I�:\0T؄\��\'`\Z�\�\Z�����!�	SkO;�A劃�ȧ!\�S5�\"\Z|Gr�Yݎz&P��\0b\�\�\�m쑄�\�\��\�\�\�Ӂ�Dn�F�\r�AR�\�<\Z�íA�y�n�X�t�TF�D�/�VF৛\�\'�	^\�d�\�\�A`1%��\�ቧAs\�N\'@\�m,\�\�4Q\�P\"�=\�ߥ�\�A瀸�D�1wC�e1\�T\�V\�Jnw.\����K0\0��@�9\�ܓc\\q/T=�\'B\"=	؞lD1c���R಻�\n\�\���I�\�\"OFk��\�ؗaȹ\�>]:�š7�;\��.\�\�Ss\�Ǫ�h\�#��r�%�uGWwQ����I/\�jP�\�C�1:���?(\��\�C�F��\0xB�#�Uf�d\�R�\�=����\0�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0m�\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��?o�\��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\06�@\0\0	\��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\0\0\0\0\0\0\0@��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0\0\0\0\0\0\0\0>8\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\0\0��\0\0\0\0\0\0\0\0\0\0\0\0\0�x\0\0\0\0\0\0\0\0\0\0\0\0p\0\0\0\0\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\0\0\0\08\0\0\0\0\0\0\0\0\0\0\0\08\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0�\0.\0\0\0\0\0<�\0\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0,\0\0\0x\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\09\0\0\0\0\0\0\0\0\0\0\0\0\0\00\0\0\0\0\0\0\0\0\0\0\0\0\0\0`\0\0\0\0�\0\0\0\0 p\0\0\0\0\0\0\0\0\0F \0\0\0\0	\0\0\0\0\0\0�\0\0\0\08\0\0\0\0\0\0\0\0\0��\0\0\0\0\0\0\0\0\0\0@\0\0\0\00\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0 \0\0\0\0\0\0`\0\0\0\0�\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\08\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\0�\0\0\0\00\0\0\0\0\0\0\0\01\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0b\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0\0p\0\0\0\0\0D\0\0\0\0\0\08\0\0\0\0\0\0`\0\0\0\0\0\0\0\0`\0\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0@�\0\0\0\0\0\0\0@�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\08\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0F\0\0\0\0\0\0\0\0\0p\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0\0\0\'`\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0@\0\0\0\0\0\0\0�\0\0\0\0\0\0\0OĀ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0�\0\0\0\0\0~\0\0\0\0\0\0\0\08\0\0\0\0\0\'\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0p\0\0\0\0\0\0�\0\0\0\0 \0\0\0\0\0\0\0\0\0\0\0\08\0\0\0\0\0\0\08\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\08\0\0\0\0\0\0\0\0\0�\0\0\�\0\0\0\0\0\0\0\0\0\0�\0\0�\0\0\0\0\0\0\0\0\0�\0\0\0\�\0\0\0\0\0\0\0\0\0\�\0\0$\0\0\0\0\0\0\0\0\0\00\09\0\0\0\0\0\0\0\0\0\0F\0\0p\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0c�\0\0\0\0\0\0\0\0\0\0\08\0A\�\0\0\0\0\0\0\0\0\0\08`\0\0\0\0\0\0\0\0\0\0\0\0p\0\0\0\0\0\0\0\0\0\0\0\0O\0\0\0\0\0\0\0\0\0\0\0�8\0\0\0\0\0\0\0\0\0\0\0\0\�\0\0\0\0\0\0\0\0\0\0\0a�\0\0\0\0\0\0\0\0\0\0\0\0a��\0\0\0\0\0\0\0\0\0`\0\0\0\0\0\0\0\0\0\0\0\0\0\0`\0\0\0\0\0\0\0\07a�\0\0\0\0\0\0\0\0\0\0\0\0\0\0N8\0x\0\0\0\0\0\0?�q8\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\��\0\0\0\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�O\0��\0m�\0���\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0#\�v\0\0\0\0\��\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0?\06Ă\�\0x\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0N\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0%\��\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\�\0\0\0\0\0\0\0\0\0\0\0P @0`��p�\�\0?�oq�8\�B�.�p�Qv(�v���v�m�����y�#\�\�p�=`\�<\r�\�o\r󇁴;\�\�\�w�âhύ<��x&�l\n!�6�\��\�k\n\�\�<vNӎ;�\�q�:�臋<\r���\�l�\0<px\�\���X:�\�\�w���J(�QEԬ�=��Y8\�\�ǒ�]\�/��(��/\�?�\�\0\0\0\0\0\0\0\0\0\0\0 0@P`�p��\�\0?�ޢĢ\�+\�qǁv�<�\�\�zn\�\�{k\�\�\�墇pP�ÜP\�\�8g8�h\�C�(s�\�\�1\�El\�\n��8�`���\�=S�z����9\����#iE�\�:j,K�tW�qC�(}�\��\�!�|yA���[�s\�U�;���q\�k�<O�\�Qh+Yܢ�\�V�\�\�\��.8\�8\��\��\�\0,\0\0\0\0\0!1Aa@Qq����0�P\�� �\�`�\�\0\0?�\0\�6\��1Sa\�W�\0\�\��\0�za��*d7��Z����5&e$}\�9\��!Y�q^\�kT\���\��h����5e�j(\��f�P4�\�\\�\�S�?�P*\0\�jU�\�\�\�\\)ﹷ��߂�\���\0pn`x*2�\���c-&��<�\�O\�3�WH�o��B�\�Mh	�|\�:ศJ�#�\�\�\�\\T\�x�<\�u�s1:�Xq\��Ի��g�?\�\�[GC��=q�\�bj\0vn}\�>\�)1�Gh!a\�\��SvD�jRQ�/�Ld��\r3�\��4�s�;1C#�P\�\�u�\�ގ.��!�I��i\�\�d�뛑⢔�؃\�}�pBԛ;���48W�\�:Ea�\"Pɏ5*OI�ަϔ�s:�J!t\��hؾ���\�qd\����w�߀Ξz��5z-\�f!�ӤN\�\�ޏ�e�q^{�\�*����\�Z����h\�\�\�Ơ\�fh\�3\��S\�\��~2\�g\�^��0\�`#a�\�\�,hdj��\�\�5WyA$q\Z2r���s;R\�\�\�pM�;K\'�3J=(��t�Nh\�m\�6�z��nD�ʵ�\�C\r^�arI]\�]��Iu\�ri\\\�IoG\r~�]�^X�\�@�b�	\��\�\"\0�T\�p���ϖ5r{mcۥ��\�W���#�-\�\��\�!�Ӻ�L	�R��+w\�\����Ic\�?ǖ5���\��Qo\��d\��xj\�I B���*�d31�\�f�\0���G7Q�ï\�1)�1�>*7\�~\�\�׃ӓ�TDɬ2�GP5�u�r\���\�}\�\��\0)ۑ*��H[�93\��\�x�\�\�\�\�vZ 2ѩ揱�8?�u�c\�(s��A\�2\r\nf�\r]\n �|ח\r\���}����-KA\�S\�>	8ݦ�\08#;��\�&M�{#\�	W3�-y\��]�I\��\�3\�Ӟò\'B_,\�\��7z\��\\\�\�|R���ZxՊ����;Rc\\T��W6u\�G�\���5��X�g�\�a�\0i�(A�x\��\�9r?s�a�!\�:\�u\�\�a-��^�w�K\�q\�\�����20�\�d�s4{\��L4L�>t�nN\\�\Zf�+,��\\�ϙ�C�\�𚰬\0�\�ҍ#\'\�5��\�\�ѱ�\�\��\�Ю\��qr\�\"*��!�`�c\���6�y�q�\�8��z�\�k��\'2n��ܓ|��L��ۣ�w�\�\��-+E�\�\�˽*����ό4�����\r��\�bkӂj7�i1\��䗩�,\�\��\�w���J2l\\\r����\0!j�z@�^k�2�\'(\�\�?m=\Zn	��3�|�~\Zh�}��N˅�U~˝\r\�k\�W\�e\�\�/Y\�|䳬Fl�\�\�M�\�d��\0�H9�?4o\�\�F)\�&쑄����oW^�:\�Q\�g�\�\�>f�Q�-2w��\�\�nM) \Z6��\0=�#�e�z\�\"2&Tr���xp�\\�PYr&\�h��A\�V36���|.\�\�1\�\�O#焜�OM���O\'�\�\�b�f���|�\0:�\�B~�5��H��i$Q\�	S�s�<�w���\r4\�(���\�\�Ξ��ۜ�\�\�n\�\0=��\�L\�H$wx%�&�~�aAD�0\'\�\�\�\��\�\�&Y\��\�#�\�\�W\�l�\�\rj�7{\� ���X�\�pAS\�Qx\� \'���7w���l��|\�\�_\��E[��}n��q4j7/l�!ܒE���\�\�d�\0J=\�x=\���i ��⏚�\�\�q{K\�1u-\�q@��5b��	\�\�\�˸h׀>�Xݹ�I\�d�\��\�B\�sY\'&�\�8&I�_p|F	1p�w�9ټ��\��C�\�VC\�\�H��D�~bpXx\'�Aa�n.��\�w��v\�\��B��0\�O�\�\��cQ��L-\0�x?!e6\�\���A�c�\�\\\�˖�{l\�\���=~�J�\�\�\�C�+i�Ƨ�9\�[\��[�\0���?�\�~��ns9�>�(\�zA}\���!NK>\�c`\�\�u\"\��H�/�{<ҫe��\�\�1)̯;q�\0�\";�R���\0�wh p\��\�.��\�{��_F\� \�t�\�\�L\0x}���c4\�\�G$�\�MINʜ𤤪^X3�x�\\Qv{&�z�\�7xP\�^i>\�.4���\0�\�J�fӈ\�N\�4\�\\�+S\�v�n�\�5���\�\0�\��Ek��xݢz���e*��\��\r\�L\'��͘(\"\�����3V :��1\�z\Z}�a���H�,�;a���`���	\�P(\�8\�w�\�zN\�U��\�\�I\�oV�a\��\�!����\�(\�D�rj\�J�\�d�T\n^i�v�8��\���\��Q0Jfϔ4\�y�eR6�\r�{C�\�M�4\��}\�\�s\�N�\�G4i��H$�\�\�6�\�X���r\�\�PDK��;�\�2�\�z\��\�\�\��[\�\�aedr�x\�y\�`gC���\�C\�<\�\�\�F`J�H��1\�\�kۍ\"\"�*\��3�\�8\�.�5�\0�r�=��\����+0g�\�\�KP�22\r\�5\�͓ц�\�\�\�k�(ч\�\n\�\0�\�\n\��a�\�JA��Jȃ��\0C\�\�n	�8�[��>\��g���s�r�&�Ę�[��?`|FF�;\�\�|�=#P#U�\0S�ŋ�#\�)� �\�d.7w�\�{\'	\\�\�u�ĤLD��>�\�Љ%ρ�-\����{ڤ\�f���ǧS��c��ىX̖\�tǡ�\�El�{\�\�vcx7\��=8�\0�\Zę�\�\n{Ȼ5\�ֺ��C�8�\0f��0�Gu/�|\�i��,U�Ta$���둾�=Q�95#�f�4�t\�r\�U�`\�9��o���06���o��\"2&TrU��è���\�C�x�sً��s:0\�\�{�\0.�>�l�u\�\�rd�\0\��28\'�b�%\���_�����\�d\�#>\'B����qjg���\�և�f\�7�G*|@\�\�\��\0�\�Ьz�v�\02%�\�\�鿊\"0�5}\�\�|z\�\�\�5�j\�U`\�$(�iS�C\�r\Z���HC����\0ۉωNY�\�bV�3^\']�\�Wdǣ�\�\0O*ҋ��q\�\�\��1P��{��6Z�\�cf\��~N�Ŀ�T�5��A\�h\�hQ�\�\0q5\��A�)\\;���M����Yf��<p{ˬWQ�\�٦[萸��8�\���!�w�\�<)q3�\�\�u%\n\�5��~��\�\�{\�\�ֱ\�\�\�\�\�z1�1X\�\�t`�,W�\�\�\'�n+�f\�vN��\�~�!I!|_\�\� \0�\�\�\�W=�J\���JW\�h�\Z\�Ώ�\0\�\�\Z;�^#�3\��L����\�\�hS\�\�Cr7�{v�\0\0تJ\�\�\�kKeŬ��{؂6��\�4\�\�\0@�\�\nG�Y8��\\�Y[��D�.�S���\�\�fjm>A\�u2z\��\�Zk��\���!	�c�\�H\�+�oȝx��X%�\�mW\0j\�1�Eg.|\�s�s\�,�Zs�*ХA\\F�\��~,�A�Y\�g\�4ǝ9r!�;򰎍IF6}�\�\�q\��=�\��\0���\�F9;?U&�\��0\�H�\"b?&���$Տѽ�D�\�?�\�\�T��\�J�v�)\�͙\�\�\�V\�\�yϬh�A�!��\�\0[<���\'ظ\�d\�\�\nQ��D�9ս�ӵqg�p1?����{��T\�*\�y>���8-\�#�|\�~O\��\�5����_�����T\"�\0\�E��_\�X\�\��W*��� \��YX\�\�N\�\�\�b\�ؖWB�s^�.E�Q)\�\�\r��\�MJ���#i�\�J�b	\�l�\0GR�\�xc�\�h\��{\n׃;|�PV�\��k\�/\���Q�w�m�X\�U�n{��\�\'Z���匹���\��>��?N�N�i����1��Bu��vH<ޢ³P�P2Z�쨏\�YMa\'�\�+\0\��\����|Ϫ\����BgL<�_��\�K�\��g5\�\�2�T9�k=z�7\��!�u}~d�Q�\0z�m�;�\'��L䗳\rH����)8-C\�?��,\�\�}\�SN 샼\�<&\�\"b$�I�8���\������AAR͎h�Fw�kYY\�ho}��\���T�*\�E���?�\��\�','yes');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10  9:25:42