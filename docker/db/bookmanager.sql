/*
Navicat MySQL Data Transfer

Source Server         : ccy004-root
Source Server Version : 50717
Source Host           : 39.107.126.75:3306
Source Database       : bookmanager

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-10 13:56:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `score` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `book_Id` varchar(200) NOT NULL,
  `user_Id` varchar(200) DEFAULT NULL,
  `book_Country` int(1) DEFAULT NULL,
  `book_No` varchar(255) DEFAULT NULL,
  `book_Img` longtext NOT NULL,
  `book_Name` varchar(50) DEFAULT NULL,
  `book_Author` varchar(30) DEFAULT NULL,
  `book_House` varchar(200) DEFAULT NULL,
  `book_Price` double(10,2) DEFAULT NULL,
  `book_Desc` varchar(255) DEFAULT NULL,
  `book_Count` int(11) DEFAULT NULL,
  `book_By_Time` date DEFAULT NULL,
  `book_Remain` int(11) DEFAULT NULL,
  `book_Upload_Time` datetime DEFAULT NULL,
  `book_Type` int(1) DEFAULT NULL,
  `book_Status` int(1) DEFAULT NULL,
  `book_Flag` int(1) DEFAULT '1',
  PRIMARY KEY (`book_Id`),
  KEY `book_user_Id` (`user_Id`) USING BTREE,
  KEY `book_name_` (`book_Name`) USING BTREE,
  CONSTRAINT `t_book_ibfk_1` FOREIGN KEY (`user_Id`) REFERENCES `t_user` (`user_Id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ebook
-- ----------------------------
DROP TABLE IF EXISTS `t_ebook`;
CREATE TABLE `t_ebook` (
  `ebook_Id` varchar(200) NOT NULL,
  `user_Id` varchar(200) DEFAULT NULL,
  `ebook_Name` varchar(50) DEFAULT NULL,
  `ebook_Country` int(1) DEFAULT NULL,
  `ebook_No` varchar(255) DEFAULT NULL,
  `ebook_House` varchar(100) DEFAULT NULL,
  `ebook_Img` varchar(255) DEFAULT NULL,
  `ebook_Desc` varchar(255) DEFAULT NULL,
  `ebook_Author` varchar(50) DEFAULT NULL,
  `ebook_Type` varchar(30) DEFAULT NULL,
  `ebook_Size` int(11) DEFAULT NULL,
  `ebook_Path` varchar(255) DEFAULT NULL,
  `ebook_Upload_Time` varchar(200) DEFAULT NULL,
  `ebook_Download_Count` int(11) DEFAULT NULL,
  `ebook_Flag` int(1) DEFAULT '1',
  PRIMARY KEY (`ebook_Id`),
  KEY `ebook_user_Id` (`user_Id`) USING BTREE,
  CONSTRAINT `t_ebook_ibfk_1` FOREIGN KEY (`user_Id`) REFERENCES `t_user` (`user_Id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_img
-- ----------------------------
DROP TABLE IF EXISTS `t_img`;
CREATE TABLE `t_img` (
  `img_Id` varchar(200) NOT NULL,
  `link_Id` varchar(200) NOT NULL,
  `link_Type` varchar(2) NOT NULL,
  `img_Path` varchar(200) NOT NULL,
  `create_User` varchar(30) NOT NULL,
  `create_Time` datetime NOT NULL,
  `last_Modify_User` varchar(30) DEFAULT NULL,
  `last_Modify_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`img_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `log_Id` varchar(200) NOT NULL,
  `user_Name` varchar(30) NOT NULL,
  `user_Nick_Name` varchar(30) DEFAULT NULL,
  `user_Address` varchar(100) NOT NULL,
  `user_JWD` varchar(100) NOT NULL,
  `module` varchar(30) NOT NULL,
  `action` varchar(50) NOT NULL,
  `action_Time` bigint(100) NOT NULL,
  `user_Ip` varchar(100) NOT NULL,
  `oper_Time` varchar(200) NOT NULL,
  `count` bigint(100) NOT NULL,
  PRIMARY KEY (`log_Id`,`user_Ip`),
  KEY `index_log_ip` (`user_Ip`) USING BTREE,
  KEY `x` (`oper_Time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `item_Id` varchar(200) NOT NULL,
  `user_Id` varchar(200) DEFAULT NULL,
  `book_Id` varchar(200) DEFAULT NULL,
  `lend_Count` int(11) DEFAULT NULL,
  `lend_Time` varchar(100) DEFAULT NULL,
  `return_Time` varchar(200) DEFAULT NULL,
  `item_Total` double(10,2) DEFAULT NULL,
  `item_Status` int(1) DEFAULT NULL,
  `item_Flag` int(1) DEFAULT '1',
  PRIMARY KEY (`item_Id`),
  KEY `book_item_id` (`book_Id`) USING BTREE,
  KEY `t_order_item_userId_fk` (`user_Id`) USING BTREE,
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`book_Id`) REFERENCES `t_book` (`book_Id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `t_order_item_ibfk_2` FOREIGN KEY (`user_Id`) REFERENCES `t_user` (`user_Id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_Id` varchar(200) NOT NULL,
  `login_Name` varchar(30) DEFAULT NULL,
  `real_Name` varchar(30) DEFAULT NULL,
  `user_Sex` int(1) DEFAULT NULL,
  `user_Pwd` varchar(255) DEFAULT NULL,
  `user_Email` varchar(100) DEFAULT NULL,
  `user_Tel` varchar(50) DEFAULT NULL,
  `user_BirthDay` datetime DEFAULT NULL,
  `user_Head` varchar(255) DEFAULT NULL,
  `user_Code` varchar(255) DEFAULT NULL,
  `user_Status` int(1) DEFAULT NULL,
  `user_RegistTime` varchar(200) DEFAULT NULL,
  `user_Role` int(1) DEFAULT NULL,
  `user_Flag` int(11) DEFAULT '1',
  PRIMARY KEY (`user_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
