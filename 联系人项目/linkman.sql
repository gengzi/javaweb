/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : linkman

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-11-22 14:11:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for linkman
-- ----------------------------
DROP TABLE IF EXISTS `linkman`;
CREATE TABLE `linkman` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `address` varchar(30) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId_id` (`userId`),
  CONSTRAINT `userId_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of linkman
-- ----------------------------
INSERT INTO `linkman` VALUES ('3', '发张学友67', '13903720002', '安阳市102', '455002', '1');
INSERT INTO `linkman` VALUES ('4', '发张学友', '13903720002', '发', '分', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三丰', '123456');
INSERT INTO `user` VALUES ('2', '耿帅佳', '123456');
INSERT INTO `user` VALUES ('3', '耿帅佳', '123456');
INSERT INTO `user` VALUES ('4', '王玉龙', '123456');
INSERT INTO `user` VALUES ('5', '贾谭克', '123456');
