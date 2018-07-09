/*
Navicat MySQL Data Transfer

Source Server         : mypc
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ssmnetty

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-07 17:40:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for house_channel
-- ----------------------------
DROP TABLE IF EXISTS `house_channel`;
CREATE TABLE `house_channel` (
  `house_id` int(11) NOT NULL,
  `channel_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设备连接ID',
  `locks` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设备锁信息',
  `power` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设备电量',
  `state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 1-正常 0-异常',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of house_channel
-- ----------------------------
INSERT INTO `house_channel` VALUES ('1', 'F5690137563EE8', 'yyyyyyyyyyyyyyyyynnnnnnn', null, '1', '2018-07-07 16:31:53', '2018-07-07 17:12:37');
