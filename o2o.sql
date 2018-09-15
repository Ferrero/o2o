/*
Navicat MySQL Data Transfer

Source Server         : cy
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : o2o

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-09-15 23:37:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('1', '东苑', '1', null, null);
INSERT INTO `tb_area` VALUES ('2', '西苑', '2', null, null);

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line` (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------
INSERT INTO `tb_head_line` VALUES ('1', '地方', '地方', '/upload/item/headtitle/2017061320315746624.jpg', '34', '1', '2018-09-07 00:00:00', null);
INSERT INTO `tb_head_line` VALUES ('2', '高富帅', '让他玩', '/upload/item/headtitle/2017061320371786788.jpg', '43', '1', '2018-09-18 00:00:00', null);
INSERT INTO `tb_head_line` VALUES ('3', '苟富贵', '是梵蒂冈', '/upload/item/headtitle/2017061320393452772.jpg', '34', '1', '2018-09-18 00:00:00', null);

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`user_name`),
  KEY `fk_localauth_pofile` (`user_id`),
  CONSTRAINT `fk_localauth_pofile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
INSERT INTO `tb_local_auth` VALUES ('19', '1', 'TESTUSER', '123456', '2018-09-14 13:50:37', '2018-09-14 13:50:38');
INSERT INTO `tb_local_auth` VALUES ('20', '2', 'vogue', '111111', '2018-09-15 15:45:49', '2018-09-15 16:44:38');

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `profile_img` varchar(1024) DEFAULT NULL,
  `email` varchar(1024) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:禁止使用本商城,1:允许使用本商城',
  `user_type` int(2) NOT NULL DEFAULT '1' COMMENT '1:顾客，2：店家，3：超级管理员',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES ('1', '测试', 'test', 'test', '1', '1', '2', null, null);
INSERT INTO `tb_person_info` VALUES ('2', '周元', null, '996064025@qq.com', '1', '1', '1', null, null);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_procate` (`product_category_id`),
  KEY `fk_product_shop` (`shop_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '优酷YOU', '这世界很酷***', 'upload/item/shop/1/2018090519540335272.jpg', '198', '98', '100', null, '2018-09-05 19:54:03', '1', '5', '1');
INSERT INTO `tb_product` VALUES ('2', '优酷', '这世界很酷', 'upload/item/shop/1/2018090317020042457.jpg', '原价', '折扣价', '20', '2018-08-21 16:35:30', '2018-09-03 17:02:00', '1', '24', '1');
INSERT INTO `tb_product` VALUES ('3', 'test', 'description', 'tudou.com', 'normalPrice', 'promotionPrice', '10', '2018-08-21 16:35:30', '2018-08-21 16:35:30', '1', '31', '1');
INSERT INTO `tb_product` VALUES ('4', '好一二三', '测试', 'upload/item/shop/1/2018082318014014190.jpg', '33', '88', '89', '2018-08-23 18:01:40', '2018-08-23 18:01:40', '1', '32', '1');
INSERT INTO `tb_product` VALUES ('5', 'FS', '314', 'upload/item/shop/1/2018082917173934893.jpg', '342', '234', '2341', '2018-08-29 17:17:39', '2018-08-29 17:17:39', '1', '39', '1');
INSERT INTO `tb_product` VALUES ('6', 'FGDS', '234', 'upload/item/shop/1/2018082917182620903.jpg', '234', '234', '324', '2018-08-29 17:18:26', '2018-08-29 17:18:26', '1', '24', '1');
INSERT INTO `tb_product` VALUES ('7', '23', '232', 'upload/item/shop/1/2018082917290315543.jpg', '23', '232', '323', '2018-08-29 17:29:03', '2018-08-29 17:29:03', '1', '61', '1');
INSERT INTO `tb_product` VALUES ('8', '43', '23423', 'upload/item/shop/1/2018082917365075335.jpg', '324', '324', '324', '2018-08-29 17:36:50', '2018-08-29 17:36:50', '1', '5', '1');
INSERT INTO `tb_product` VALUES ('9', '靖江', '靖江style', 'upload/item/shop/1/2018082917480595563.jpg', '23', '23', '233', '2018-08-29 17:48:05', '2018-08-29 17:48:05', '1', '60', '1');
INSERT INTO `tb_product` VALUES ('10', '康师傅番茄鸡蛋面', '233', 'upload/item/shop/1/2018082917502248353.jpg', '23', '23', '23', '2018-08-29 17:50:22', '2018-08-29 17:50:22', '1', '59', '1');
INSERT INTO `tb_product` VALUES ('11', '23', '43', 'upload/item/shop/1/2018082918032143787.jpg', '32', '23', '23', '2018-08-29 18:03:21', '2018-08-29 18:03:21', '1', '31', '1');
INSERT INTO `tb_product` VALUES ('12', '21', '12321311', 'upload/item/shop/1/2018082918070136547.jpg', '121', '213', '211', '2018-08-29 18:07:01', '2018-08-29 18:07:01', '1', '39', '1');
INSERT INTO `tb_product` VALUES ('13', '12', '12312', 'upload/item/shop/1/2018082918112726979.jpg', '213', '123', '21', '2018-08-29 18:11:27', '2018-08-29 18:11:27', '1', '41', '1');
INSERT INTO `tb_product` VALUES ('14', '小米', '小米，为发烧而生', 'upload/item/shop/1/2018082920495831611.jpg', '1288', '999', '1000', '2018-08-29 20:49:58', '2018-08-29 20:49:58', '1', '38', '1');
INSERT INTO `tb_product` VALUES ('15', '1', '5', 'upload/item/shop/1/2018082920575077828.jpg', '3', '4', '2', '2018-08-29 20:57:50', '2018-08-29 20:57:50', '1', '37', '1');
INSERT INTO `tb_product` VALUES ('16', 'gujainiq', '32', 'upload/item/shop/1/2018082921055638185.jpg', '2323', '32', '32', '2018-08-29 21:05:56', '2018-08-29 21:05:56', '1', '24', '1');
INSERT INTO `tb_product` VALUES ('17', 'jdfkj', '345', 'upload/item/shop/1/2018082921071681180.jpg', '435', '453', '43', '2018-08-29 21:07:16', '2018-08-29 21:07:16', '1', '5', '1');
INSERT INTO `tb_product` VALUES ('18', '可乐', '34', '/upload/item/shop/1/2018082921482787442.jpg', '34', '45', '23', '2018-08-29 21:48:27', '2018-08-29 21:48:27', '1', '60', '1');
INSERT INTO `tb_product` VALUES ('19', '红枣核桃夹心派', '双重营养，一口尽享受', '/upload/item/shop/1/2018082922070720368.jpg', '33', '44', '97', '2018-08-29 22:07:07', '2018-08-29 22:07:07', '1', '24', '97');
INSERT INTO `tb_product` VALUES ('20', '22', '2332', 'upload/item/shop/1/2018082922090690657.jpg', '233', '233', '121', '2018-08-29 22:09:06', '2018-08-29 22:09:06', '1', '57', '1');
INSERT INTO `tb_product` VALUES ('21', '优酷', '这世界很酷', 'upload/item/shop/1/2018090317041783796.jpg', '333', '333', '0', null, '2018-09-03 17:04:17', '0', null, '1');
INSERT INTO `tb_product` VALUES ('22', '好一二三', '测试', 'upload/item/shop/1/2018090311523032435.jpg', '33', '88', '89', '2018-09-03 11:52:30', '2018-09-03 11:52:30', '1', '54', '100');
INSERT INTO `tb_product` VALUES ('23', '爵位咖啡', '爵位尊享，皇家之上', '/upload/item/shop/97/2018091121473844494.jpg', '34', '25', '100', '2018-09-11 21:47:38', '2018-09-11 21:47:38', '1', '61', '97');

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES ('5', '店铺商品类别3', '2', null, '1');
INSERT INTO `tb_product_category` VALUES ('24', '三国杀', '666', null, '1');
INSERT INTO `tb_product_category` VALUES ('31', 'A', '22', null, '1');
INSERT INTO `tb_product_category` VALUES ('32', 'B', '222', null, '1');
INSERT INTO `tb_product_category` VALUES ('34', 'D', '444', null, '1');
INSERT INTO `tb_product_category` VALUES ('37', 'we', '452', null, '1');
INSERT INTO `tb_product_category` VALUES ('38', 'twtw', '345', null, '1');
INSERT INTO `tb_product_category` VALUES ('39', '44', '43', null, '1');
INSERT INTO `tb_product_category` VALUES ('41', 'dfgg', '435', null, '1');
INSERT INTO `tb_product_category` VALUES ('42', 'rsrhd', '456', null, '1');
INSERT INTO `tb_product_category` VALUES ('43', 'sgdgs', '343', null, '1');
INSERT INTO `tb_product_category` VALUES ('54', '1', '1', '2018-08-18 15:19:18', '1');
INSERT INTO `tb_product_category` VALUES ('56', '2', '2', '2018-08-18 15:19:38', '1');
INSERT INTO `tb_product_category` VALUES ('57', 'TYFYF', '23', '2018-08-25 13:13:38', '96');
INSERT INTO `tb_product_category` VALUES ('59', 'RDTYT', '33', '2018-08-25 13:14:00', '96');
INSERT INTO `tb_product_category` VALUES ('60', 'TYRYD', '44', '2018-08-25 13:14:00', '96');
INSERT INTO `tb_product_category` VALUES ('61', '23', '32', '2018-08-28 10:11:41', '97');

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------
INSERT INTO `tb_product_img` VALUES ('5', 'upload/item/shop/1/2018082318014084570.jpg', null, null, '2018-08-23 18:01:40', '4');
INSERT INTO `tb_product_img` VALUES ('6', 'upload/item/shop/1/2018082318014018522.jpg', null, null, '2018-08-23 18:01:40', '4');
INSERT INTO `tb_product_img` VALUES ('7', 'upload/item/shop/1/2018082917173951095.jpg', null, null, '2018-08-29 17:17:39', '5');
INSERT INTO `tb_product_img` VALUES ('8', 'upload/item/shop/1/2018082917182673579.jpg', null, null, '2018-08-29 17:18:26', '6');
INSERT INTO `tb_product_img` VALUES ('9', 'upload/item/shop/1/2018082917290355512.jpg', null, null, '2018-08-29 17:29:03', '7');
INSERT INTO `tb_product_img` VALUES ('10', 'upload/item/shop/1/2018082917365438251.jpg', null, null, '2018-08-29 17:36:55', '8');
INSERT INTO `tb_product_img` VALUES ('11', 'upload/item/shop/1/2018082917480560394.jpg', null, null, '2018-08-29 17:48:05', '9');
INSERT INTO `tb_product_img` VALUES ('12', 'upload/item/shop/1/2018082917502319029.jpg', null, null, '2018-08-29 17:50:23', '10');
INSERT INTO `tb_product_img` VALUES ('13', 'upload/item/shop/1/2018082918032181897.jpg', null, null, '2018-08-29 18:03:22', '11');
INSERT INTO `tb_product_img` VALUES ('14', 'upload/item/shop/1/2018082918070131138.jpg', null, null, '2018-08-29 18:07:01', '12');
INSERT INTO `tb_product_img` VALUES ('15', 'upload/item/shop/1/2018082918112726930.jpg', null, null, '2018-08-29 18:11:27', '13');
INSERT INTO `tb_product_img` VALUES ('16', 'upload/item/shop/1/2018082920495915129.jpg', null, null, '2018-08-29 20:49:59', '14');
INSERT INTO `tb_product_img` VALUES ('17', 'upload/item/shop/1/2018082920575137438.jpg', null, null, '2018-08-29 20:57:51', '15');
INSERT INTO `tb_product_img` VALUES ('18', 'upload/item/shop/1/2018082921055667644.jpg', null, null, '2018-08-29 21:05:56', '16');
INSERT INTO `tb_product_img` VALUES ('19', 'upload/item/shop/1/2018082921071652782.jpg', null, null, '2018-08-29 21:07:16', '17');
INSERT INTO `tb_product_img` VALUES ('20', 'upload/item/shop/1/2018082921482815396.jpg', null, null, '2018-08-29 21:48:28', '18');
INSERT INTO `tb_product_img` VALUES ('21', 'upload/item/shop/1/2018082922070810600.jpg', null, null, '2018-08-29 22:07:08', '19');
INSERT INTO `tb_product_img` VALUES ('22', 'upload/item/shop/1/2018082922090783967.jpg', null, null, '2018-08-29 22:09:07', '20');
INSERT INTO `tb_product_img` VALUES ('33', 'upload/item/shop/1/2018090311523122475.jpg', null, null, '2018-09-03 11:52:31', '22');
INSERT INTO `tb_product_img` VALUES ('34', 'upload/item/shop/1/2018090311523140460.jpg', null, null, '2018-09-03 11:52:31', '22');
INSERT INTO `tb_product_img` VALUES ('73', 'upload/item/shop/1/2018090317020199691.jpg', null, null, '2018-09-03 17:02:01', '2');
INSERT INTO `tb_product_img` VALUES ('74', 'upload/item/shop/1/2018090317020123525.jpg', null, null, '2018-09-03 17:02:01', '2');
INSERT INTO `tb_product_img` VALUES ('75', 'upload/item/shop/1/2018090317041756462.jpg', null, null, '2018-09-03 17:04:17', '21');
INSERT INTO `tb_product_img` VALUES ('76', 'upload/item/shop/1/2018090317041758521.jpg', null, null, '2018-09-03 17:04:17', '21');
INSERT INTO `tb_product_img` VALUES ('77', 'upload/item/shop/1/2018090519540485242.jpg', null, null, '2018-09-05 19:54:04', '1');
INSERT INTO `tb_product_img` VALUES ('78', '/upload/item/shop/97/2018091121473842076.jpg', null, '12', '2018-09-11 21:47:38', '23');
INSERT INTO `tb_product_img` VALUES ('79', '/upload/item/shop/97/2018091121473830683.jpg', null, '34', '2018-09-11 21:47:38', '23');
INSERT INTO `tb_product_img` VALUES ('80', '/upload/item/shop/97/2018091121473841318.jpg', null, '45', '2018-09-11 21:47:38', '23');

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) NOT NULL,
  `shop_desc` varchar(1024) DEFAULT NULL,
  `shop_addr` varchar(200) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `shop_img` varchar(1024) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('1', '1', '1', '1', '浪人琵琶2.0', '甜蜜...&..暴击都发生过的', '情侣园路520号', '13852019451', 'upload/item/shop/1/2018082811512230915.jpg', null, '2018-07-18 11:05:48', '2018-08-28 11:51:22', '1', '审核中');
INSERT INTO `tb_shop` VALUES ('62', '1', '2', '1', '测试的店铺1', 'test1', 'test1', 'test1', 'upload/item/shop/62/2018072521135697440.jpg', null, '2018-07-25 21:13:56', '2018-07-25 21:13:56', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('63', '1', '2', '1', '测试的店铺2', 'test2', 'test2', 'test2', 'upload/item/shop/63/2018072609423322047.jpg', null, '2018-07-26 09:40:55', '2018-07-26 09:40:55', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('64', '1', '2', '1', '测试的店铺3', 'test3', 'test3', 'test3', 'upload/item/shop/64/2018072609544912663.jpg', null, '2018-07-26 09:53:25', '2018-07-26 09:53:25', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('68', '1', '2', '1', '测试的店铺5', 'test5', 'test5', 'test5', 'upload/item/shop/68/2018080215412388631.jpg', null, '2018-08-02 15:41:23', '2018-08-02 15:41:23', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('76', '1', '2', '3', '虾油', '爱人广点通合同认定', '安抚', '2354235621', 'upload/item/shop/76/2018080217135998407.jpg', null, '2018-08-02 17:13:59', '2018-08-02 17:13:59', '0', null);
INSERT INTO `tb_shop` VALUES ('78', '1', '2', '3', '加快解放', '发根深蒂固', '说的话了及时', '12345678912', 'upload/item/shop/78/2018080217340450902.jpg', null, '2018-08-02 17:33:46', '2018-08-02 17:33:46', '0', null);
INSERT INTO `tb_shop` VALUES ('79', '1', '2', '3', '小U', '富商大贾的时光', '爱人分团委', '123435434', 'upload/item/shop/79/2018080217363024626.jpg', null, '2018-08-02 17:36:19', '2018-08-02 17:36:19', '0', null);
INSERT INTO `tb_shop` VALUES ('80', '1', '2', '3', '小U潮鉴', '咖啡新贵小U，诚邀校友品鉴。', '南京市中央路紫晶环球大厦88号', '18868825888', 'upload/item/shop/80/2018080217390192113.jpg', null, '2018-08-02 17:39:01', '2018-08-02 17:39:01', '0', null);
INSERT INTO `tb_shop` VALUES ('81', '1', '1', '3', '丝芙兰', '咖啡帝国', '院上东路23号', '025-57689423', 'upload/item/shop/81/2018080416001915964.jpg', null, '2018-08-04 16:00:19', '2018-08-04 16:00:19', '0', null);
INSERT INTO `tb_shop` VALUES ('83', '1', '2', '3', '嘉年华', '嘉年华敬上', '嘉年华路23号', '1234345245', 'upload/item/shop/83/2018080416051798457.jpg', null, '2018-08-04 16:05:17', '2018-08-04 16:05:17', '0', null);
INSERT INTO `tb_shop` VALUES ('84', '1', '2', '3', '超大', '是特儿童观', '打发', '34352513454243', 'upload/item/shop/84/2018080416065534863.jpg', null, '2018-08-04 16:06:55', '2018-08-04 16:06:55', '0', null);
INSERT INTO `tb_shop` VALUES ('85', '1', '2', '3', 'RGDG', '司法改革合适的', '佛挡杀佛公司的', '4562545463', 'upload/item/shop/85/2018080416080942337.jpg', null, '2018-08-04 16:08:09', '2018-08-04 16:08:09', '0', null);
INSERT INTO `tb_shop` VALUES ('86', '1', '2', '3', 'DH', 'TRFDGDH', 'DHH', '1425235252', 'upload/item/shop/86/2018080416132612844.jpg', null, '2018-08-04 16:13:26', '2018-08-04 16:13:26', '0', null);
INSERT INTO `tb_shop` VALUES ('87', '1', '2', '3', '艾弗森', '都是发发发', '萨法', '324423423434', 'upload/item/shop/87/2018080416161617821.jpg', null, '2018-08-04 16:16:16', '2018-08-04 16:16:16', '0', null);
INSERT INTO `tb_shop` VALUES ('88', '1', '1', '3', 'GRS', 'SDGS', 'SG', '354325352', 'upload/item/shop/88/2018080416174392646.jpg', null, '2018-08-04 16:17:43', '2018-08-04 16:17:43', '0', null);
INSERT INTO `tb_shop` VALUES ('89', '1', '2', '3', 'AFSDF', 'FSGSDGS', 'ASFF', '124324213412', 'upload/item/shop/89/2018080416191695110.jpg', null, '2018-08-04 16:19:16', '2018-08-04 16:19:16', '0', null);
INSERT INTO `tb_shop` VALUES ('90', '1', '2', '3', '分公司', '水电费根深蒂固', '搜狗公司', '143323452352', 'upload/item/shop/90/2018080416205481558.jpg', null, '2018-08-04 16:20:54', '2018-08-04 16:20:54', '0', null);
INSERT INTO `tb_shop` VALUES ('91', '1', '2', '1', '测试的店铺5', 'test5', 'test5', 'test5', 'upload/item/shop/91/2018080418011648140.jpg', null, '2018-08-04 18:01:16', '2018-08-04 18:01:16', '0', '审核中');
INSERT INTO `tb_shop` VALUES ('92', '1', '2', '3', '话费单', '燃放的防守打法', '阿发非', '13415235352', 'upload/item/shop/92/2018080418025641789.jpg', null, '2018-08-04 18:02:56', '2018-08-04 18:02:56', '0', null);
INSERT INTO `tb_shop` VALUES ('93', '1', '2', '1', '测试的店铺5', 'test5', 'test5', 'test5', 'upload/item/shop/93/2018080516214193679.jpg', null, '2018-08-05 16:21:41', '2018-08-05 16:21:41', '1', '审核中');
INSERT INTO `tb_shop` VALUES ('94', '1', '2', '6', '标下', '黄金卡金风科技发', '龙王山路', '123455656756', 'upload/item/shop/94/2018080516280875462.jpg', null, '2018-08-05 16:28:08', '2018-08-05 16:28:08', '1', null);
INSERT INTO `tb_shop` VALUES ('95', '1', '2', '6', '孟老师养生馆', '金牌养生为你量身打造适合你的养生食疗', '孟家路孟客庄1号', '17823788238', '/upload/item/shop/95/2018081015083917115.jpg', null, '2018-08-10 15:08:39', '2018-08-10 15:08:39', '1', null);
INSERT INTO `tb_shop` VALUES ('96', '1', '1', '5', '小厨', '淮扬', '欢乐港', '02557642654', '/upload/item/shop/96/2018082513130547051.jpg', null, '2018-08-24 12:00:14', '2018-08-25 13:13:05', '1', null);
INSERT INTO `tb_shop` VALUES ('97', '1', '1', '5', '费列罗ferror', '354352', '三个人', '33526625', '/upload/item/shop/97/2018082412043044705.jpg', null, '2018-08-24 12:04:30', '2018-08-24 12:04:30', '1', null);
INSERT INTO `tb_shop` VALUES ('98', '1', '2', '5', '孟老板的小窝', '黄寺大街客户开始', '哈护肤课荆防颗粒建安费 ', '1737497592359', '/upload/item/shop/98/2018082412273845708.jpg', null, '2018-08-24 12:27:38', '2018-08-24 12:27:38', '1', null);
INSERT INTO `tb_shop` VALUES ('99', '1', '1', '5', 'A5', 'RDGDDH', 'DFH', '3455252', '/upload/item/shop/99/2018082415135218803.jpg', null, '2018-08-24 15:13:52', '2018-08-24 15:13:52', '1', null);
INSERT INTO `tb_shop` VALUES ('100', '1', '2', '5', 'y5ry', '6463', '1324', '53464636', '/upload/item/shop/100/2018082416332618956.jpg', null, '2018-08-24 16:33:26', '2018-08-24 16:33:26', '1', null);
INSERT INTO `tb_shop` VALUES ('101', '1', '2', '5', 'QWER', 'ERWQR', 'EWR', 'EWR', '/upload/item/shop/101/2018082811522222315.jpg', null, '2018-08-28 11:52:22', '2018-08-28 11:52:22', '1', null);
INSERT INTO `tb_shop` VALUES ('102', '1', '2', '5', 'QWER', 'ERWQR', 'EWR', 'EWR', '/upload/item/shop/102/2018082811553282235.jpg', null, '2018-08-28 11:55:32', '2018-08-28 11:55:32', '1', null);

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES ('1', '咖啡奶茶', '现磨咖啡', '/upload/item/shopcategory/2017060420464594520.png', '1', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('3', '咖啡', '测试类别', 'test3', '0', null, null, '1');
INSERT INTO `tb_shop_category` VALUES ('4', '运动潮牌', '大咖服饰', '/upload/item/shopcategory/2017061223273314635.png', '0', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('5', '李宁运动', '一切皆有可能呢', null, '0', null, null, '4');
INSERT INTO `tb_shop_category` VALUES ('6', '回力', '回天之力', null, '0', null, null, '4');

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(200) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  UNIQUE KEY `open_id` (`open_id`),
  KEY `fk_wechatauth_pofile` (`user_id`),
  CONSTRAINT `fk_wechatauth_pofile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
