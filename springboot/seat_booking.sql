/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : seat_booking

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/04/2023 18:22:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_board
-- ----------------------------
DROP TABLE IF EXISTS `t_board`;
CREATE TABLE `t_board`  (
  `board_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '公告唯一ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `msg` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '正文',
  `b_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配图url路径',
  `board_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  PRIMARY KEY (`board_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_board
-- ----------------------------
INSERT INTO `t_board` VALUES (1, '2121321', '21321312', NULL, '2023-03-19 18:34:09');
INSERT INTO `t_board` VALUES (2, '', '', NULL, '2023-03-19 18:37:11');
INSERT INTO `t_board` VALUES (3, '', '', NULL, '2023-03-19 18:37:25');
INSERT INTO `t_board` VALUES (4, '厕所', '12321321', NULL, '2023-03-19 18:39:08');
INSERT INTO `t_board` VALUES (5, '', '', NULL, '2023-03-19 19:07:11');
INSERT INTO `t_board` VALUES (6, '123', '123sweqwe', NULL, '2023-03-19 21:30:30');
INSERT INTO `t_board` VALUES (7, '厕所', '12312412312', NULL, '2023-03-19 22:38:51');
INSERT INTO `t_board` VALUES (8, '测试', '测试', NULL, '2023-03-19 22:47:11');
INSERT INTO `t_board` VALUES (9, '21321', '321321', NULL, '2023-03-19 22:48:59');
INSERT INTO `t_board` VALUES (10, 'sadas', 'dasdas', NULL, '2023-03-19 22:51:58');
INSERT INTO `t_board` VALUES (11, '', '', NULL, '2023-03-19 22:53:19');
INSERT INTO `t_board` VALUES (12, '111', '111', NULL, '2023-03-19 23:00:50');
INSERT INTO `t_board` VALUES (13, NULL, NULL, NULL, '2023-03-19 23:02:25');
INSERT INTO `t_board` VALUES (14, NULL, NULL, NULL, '2023-03-19 23:03:26');
INSERT INTO `t_board` VALUES (15, NULL, NULL, NULL, '2023-03-19 23:03:46');
INSERT INTO `t_board` VALUES (16, 'ceshi', NULL, NULL, '2023-03-19 23:04:23');
INSERT INTO `t_board` VALUES (17, 'ceshi', NULL, NULL, '2023-03-19 23:06:42');
INSERT INTO `t_board` VALUES (18, 'ceshi', NULL, NULL, '2023-03-19 23:07:36');
INSERT INTO `t_board` VALUES (19, 'ceshi', NULL, NULL, '2023-03-19 23:07:36');
INSERT INTO `t_board` VALUES (20, 'ceshi', NULL, NULL, '2023-03-19 23:09:34');
INSERT INTO `t_board` VALUES (21, 'ceshi', NULL, NULL, '2023-03-19 23:12:40');
INSERT INTO `t_board` VALUES (22, 'ceshi', NULL, '72330cf0336d49da8f20973478602585_素材.webp', '2023-03-19 23:12:58');
INSERT INTO `t_board` VALUES (23, 'ceshi', NULL, '72330cf0336d49da8f20973478602585_素材.webp', '2023-03-19 23:13:05');
INSERT INTO `t_board` VALUES (24, 'ceshi', NULL, '72330cf0336d49da8f20973478602585_sadsadas.webp', '2023-03-19 23:13:30');
INSERT INTO `t_board` VALUES (25, 'ceshi', NULL, '72330cf0336d49da8f20973478602585_sadsadas.webp', '2023-03-19 23:16:21');
INSERT INTO `t_board` VALUES (26, 'ceshi', NULL, '72330cf0336d49da8f20973478602585_.webp', '2023-03-19 23:16:29');
INSERT INTO `t_board` VALUES (27, '你没生气把', '没把', '32cfe3f80fa146dd88acb42be0e1cf0c_素材.webp', '2023-03-19 23:20:02');
INSERT INTO `t_board` VALUES (28, '???', '???', NULL, '2023-03-19 23:21:19');
INSERT INTO `t_board` VALUES (29, 'sad', 'asda', 'd32b6c7bcf894c0daac3f44ad47323b2_素材.webp', '2023-03-19 23:22:03');
INSERT INTO `t_board` VALUES (30, 'sadsa', 'dasdas', '4274baf1ca1847d4ab72fb94ad5e8508_logo.jpg', '2023-03-19 23:24:24');
INSERT INTO `t_board` VALUES (31, '公告1', '测试公告测试公告测试公告测试公告', 'e9b00d89f2fb47a38f675f830b5c1da4_素材.webp', '2023-04-06 00:59:09');
INSERT INTO `t_board` VALUES (32, '测试数据', '啊实打实的撒', '1f0a3208c8034345be3e081afa336748_素材.webp', '2023-04-17 21:24:46');
INSERT INTO `t_board` VALUES (33, '12321', '312321', 'b2158570146b495791e1ceca8d65a367_素材.webp', '2023-04-17 21:27:10');
INSERT INTO `t_board` VALUES (34, 'sadas', 'dasdsa', 'c64accb74c13476f940d1315b2b30449_素材.webp', '2023-04-17 21:27:24');
INSERT INTO `t_board` VALUES (35, '123213', '21312', 'b7abc195ffb948e09c38a249b984153f_素材.webp', '2023-04-17 21:30:16');
INSERT INTO `t_board` VALUES (36, '12321', '312', 'ab335953585d4736b7a1902e54dea1a8_素材.webp', '2023-04-17 21:30:28');
INSERT INTO `t_board` VALUES (37, '12321', '312312', '62c0d2322e824d3bad13bbc77baf94bb_素材.webp', '2023-04-17 21:31:18');
INSERT INTO `t_board` VALUES (38, '12312', '321321', 'b6199fe0ceed4c10b5d881f95e00b2f6_素材.webp', '2023-04-17 21:31:56');

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '预约号（时间戳+随机数生成唯一id）',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '预约人账号ID',
  `book_type` int(0) NULL DEFAULT NULL COMMENT '预约类型 1：会议室预约 2：自习室预约 3：教室预约',
  `room_id` int(0) NULL DEFAULT NULL COMMENT '会议室/自习室ID',
  `seat_id` int(0) NULL DEFAULT NULL COMMENT '座位ID',
  `book_date` date NULL DEFAULT NULL COMMENT '预约日期',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `create_book` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '记录时间',
  `book_status` int(0) NULL DEFAULT NULL COMMENT '预约状态 1：已预约 2：进行中 3：已完成 4：已违约 5：已取消 6：待审核 7：已驳回',
  `review_time` timestamp(0) NULL DEFAULT NULL COMMENT '审核时间',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预约原因',
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动主题',
  `advisor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指导老师',
  PRIMARY KEY (`book_id`) USING BTREE,
  INDEX `book_user`(`user_id`) USING BTREE,
  INDEX `book_room`(`room_id`) USING BTREE,
  INDEX `book_seat`(`seat_id`) USING BTREE,
  CONSTRAINT `book_room` FOREIGN KEY (`room_id`) REFERENCES `t_room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_seat` FOREIGN KEY (`seat_id`) REFERENCES `t_seat` (`seat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('162236492020612731', 3, 2, 10, 19, '2023-03-06', '2023-04-18 13:00:00', '2023-04-18 18:00:00', '2023-03-08 23:12:48', 3, '2023-04-18 16:54:54', NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('162236492020618732', 2, 2, 8, 11, '2023-03-05', '2023-03-05 08:00:00', '2023-03-05 12:00:00', '2023-03-08 23:10:41', 1, '2023-04-18 16:55:38', NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('168149471113261537066', 2, 2, 6, 19, NULL, NULL, NULL, '2023-04-15 01:51:51', 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681494774456597', 2, 2, 6, 19, NULL, NULL, NULL, '2023-04-15 01:52:55', 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681494831031807', 2, 2, 6, 19, '2023-03-06', NULL, NULL, '2023-04-15 01:53:51', 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681495338392818', 2, 2, 6, 19, '2023-03-06', NULL, NULL, '2023-04-15 02:02:18', 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681496055119281', 2, 2, 6, 19, '2023-03-06', NULL, NULL, '2023-04-15 02:14:15', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681496072137332', 2, 2, 6, 19, '2023-03-07', NULL, NULL, '2023-04-15 02:14:32', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681515426303011', 2, 2, 6, 19, '2023-03-07', '2023-03-07 20:00:00', NULL, '2023-04-15 07:37:06', 5, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681515581253294', 2, 2, 6, 19, '2023-03-07', '2023-03-07 22:00:00', '2023-03-08 01:00:00', '2023-04-15 07:39:41', 5, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681515605540725', 2, 2, 6, 19, '2023-03-06', '2023-03-06 22:00:00', '2023-03-07 01:00:00', '2023-04-15 07:40:05', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681515979014331', 2, 2, 6, 19, '2023-03-06', '2023-03-06 14:00:00', '2023-03-06 17:00:00', '2023-04-15 07:46:19', 4, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681565582807426', 3, 1, 7, NULL, '2023-03-06', '2023-03-06 14:00:00', '2023-03-06 17:00:00', '2023-04-15 21:33:02', 1, '2023-04-18 16:54:37', NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681787659527196', 3, 1, 7, NULL, '2023-03-08', '2023-03-08 14:00:00', '2023-03-08 17:00:00', '2023-04-18 11:14:19', 1, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('1681787696965075', 3, 1, 7, NULL, '2023-03-09', '2023-03-09 14:00:00', '2023-03-09 17:00:00', '2023-04-18 11:14:57', 1, NULL, '开会', '进度会', NULL);
INSERT INTO `t_book` VALUES ('1681788390294770', 3, 1, 7, NULL, '2023-03-09', '2023-03-09 07:00:00', '2023-03-09 08:00:00', '2023-04-18 11:26:30', 1, NULL, '开会', '进度会', '李四');
INSERT INTO `t_book` VALUES ('1681812921990969', 3, 1, 7, NULL, '2023-04-18', '2023-04-18 18:00:00', '2023-04-18 18:18:00', '2023-04-18 18:15:22', 3, NULL, '开会', '进度会', '李四');
INSERT INTO `t_book` VALUES ('1681813183349702', 3, 1, 7, NULL, '2023-04-18', '2023-04-18 18:19:00', '2023-04-18 18:20:00', '2023-04-18 18:19:43', 3, NULL, '开会', '进度会', '李四');
INSERT INTO `t_book` VALUES ('263182930271826489', 3, 1, 6, NULL, '2023-03-09', '2023-03-09 12:30:00', '2023-03-09 13:30:00', '2023-03-08 23:17:45', 5, NULL, NULL, NULL, NULL);
INSERT INTO `t_book` VALUES ('273821728392739193', 3, 2, 8, 11, '2023-02-22', '2023-02-22 13:00:00', '2023-02-22 18:00:00', '2023-03-08 23:41:12', 3, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_break
-- ----------------------------
DROP TABLE IF EXISTS `t_break`;
CREATE TABLE `t_break`  (
  `break_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '违约记录ID',
  `book_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预约号',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '预约人账号ID',
  `reason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '理由',
  `break_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '违约时间',
  `break_status` int(0) NULL DEFAULT NULL COMMENT '记录状态 0：启用 1：禁用',
  PRIMARY KEY (`break_id`) USING BTREE,
  INDEX `break_user`(`user_id`) USING BTREE,
  INDEX `break_book`(`book_id`) USING BTREE,
  CONSTRAINT `break_book` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `break_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_break
-- ----------------------------
INSERT INTO `t_break` VALUES (6, '162236492020612731', 3, '超时', '2023-03-11 12:41:22', 0);
INSERT INTO `t_break` VALUES (7, '162236492020618732', 2, '超时未签到', '2023-03-11 12:41:41', 0);
INSERT INTO `t_break` VALUES (8, '1681494831031807', 2, '12321312', '2023-04-16 19:27:26', 0);
INSERT INTO `t_break` VALUES (9, '1681515979014331', 2, 'wqewq', '2023-04-16 19:28:30', 0);
INSERT INTO `t_break` VALUES (10, '1681565582807426', NULL, NULL, '2023-04-18 16:53:28', 0);

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client`  (
  `client_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '账号ID',
  `school` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学院',
  `type` int(0) NULL DEFAULT NULL COMMENT '用户类别 1：学生 2：教职工',
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在系',
  PRIMARY KEY (`client_id`) USING BTREE,
  INDEX `c_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `c_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_client
-- ----------------------------
INSERT INTO `t_client` VALUES (1, 1, '商学院', 2, 'Finance');
INSERT INTO `t_client` VALUES (2, 2, '商学院', 1, 'Finance');
INSERT INTO `t_client` VALUES (3, 3, '商学院', 1, 'InformationManagement');

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room`  (
  `room_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '房间唯一ID',
  `r_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '房间号',
  `r_type` int(0) NULL DEFAULT NULL COMMENT '房间类型 1：会议室 2：自习室 3：教室',
  `num` int(0) NULL DEFAULT NULL COMMENT '座位数',
  `r_status` int(0) NULL DEFAULT NULL COMMENT '房间状态 0：启用 1：禁用',
  `r_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片url路径',
  `create_room` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `floor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '楼层',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '区域',
  `room_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '房间名称',
  `is_research` tinyint(1) NULL DEFAULT NULL COMMENT '是否为教研室',
  `room_depart` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '教研室所属系',
  PRIMARY KEY (`room_id`) USING BTREE,
  INDEX `r_code`(`r_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_room
-- ----------------------------
INSERT INTO `t_room` VALUES (6, 'A263', 1, NULL, 0, NULL, '2023-03-08 22:57:13', '2层', '?', '9999', NULL, NULL);
INSERT INTO `t_room` VALUES (7, 'B222', 1, NULL, 1, NULL, '2023-03-08 22:57:21', '2层', 'B区', '888', NULL, NULL);
INSERT INTO `t_room` VALUES (8, 'A315', 2, 120, 0, NULL, '2023-03-08 22:57:26', '3层', 'A区', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (9, 'A315', 2, 100, 0, NULL, '2023-03-08 22:57:40', '3层', 'A区', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (10, 'B303', 2, 80, 1, NULL, '2023-03-08 22:57:45', '3层', 'B区', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (11, 'A231', 1, NULL, 1, NULL, '2023-03-10 18:01:39', '2层', 'A区', '8899', NULL, NULL);
INSERT INTO `t_room` VALUES (12, 'C101', 1, NULL, 0, NULL, '2023-03-10 18:05:17', '1层', 'C区', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (28, 'C202', 1, NULL, 0, NULL, '2023-03-11 11:30:53', '2层', 'C区', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (32, 'A510', 2, 10, 1, NULL, '2023-03-11 12:15:28', '5层', 'A区', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (33, 'B420', 2, 3, 0, NULL, '2023-03-11 12:23:22', '4层', 'Bqu', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (34, '302', 2, 10, 0, NULL, '2023-04-15 01:39:34', NULL, NULL, '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (35, 'A11', 1, NULL, 0, NULL, '2023-04-16 15:21:39', '12', '测试', '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (36, '213213', 2, 5, 0, NULL, '2023-04-16 16:31:47', '12312', NULL, '2132132131', NULL, NULL);
INSERT INTO `t_room` VALUES (37, '', 2, NULL, 0, '28b593d7810b4e03a9fb1eba5c246dbc_素材.webp', '2023-04-16 17:02:12', '', NULL, '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (38, '12321', 2, 21, 0, '28b593d7810b4e03a9fb1eba5c246dbc_素材.webp', '2023-04-16 17:03:07', '12321', NULL, '123123', NULL, NULL);
INSERT INTO `t_room` VALUES (39, '321', 2, 4, 0, '84c1200f487549aba4eafb4e61eab302_素材.webp', '2023-04-16 17:04:06', '21312', NULL, '12321', NULL, NULL);
INSERT INTO `t_room` VALUES (42, '123', 3, 4, 1, NULL, '2023-04-16 17:29:19', '22', NULL, '123', NULL, NULL);
INSERT INTO `t_room` VALUES (43, 'AB303', 1, NULL, 0, NULL, '2023-04-16 17:56:22', '3', NULL, '测试', NULL, NULL);
INSERT INTO `t_room` VALUES (44, 'AB122', 3, 20, 0, NULL, '2023-04-16 18:15:24', '1层', NULL, '测试教室', NULL, NULL);
INSERT INTO `t_room` VALUES (45, 'AB211', 2, 20, 0, '0471c7b00093418a9ab031c2c4f174da_素材.webp', '2023-04-16 18:16:13', '2', NULL, '测试自习室', NULL, NULL);
INSERT INTO `t_room` VALUES (46, 'AB211', 2, 20, 1, '0471c7b00093418a9ab031c2c4f174da_素材.webp', '2023-04-16 18:16:20', '2', NULL, '测试自习室', NULL, NULL);

-- ----------------------------
-- Table structure for t_seat
-- ----------------------------
DROP TABLE IF EXISTS `t_seat`;
CREATE TABLE `t_seat`  (
  `seat_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '座位号唯一ID',
  `s_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '座位编号',
  `r_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '自习室编号',
  `room_id` int(0) NULL DEFAULT NULL COMMENT '自习室唯一ID',
  PRIMARY KEY (`seat_id`) USING BTREE,
  INDEX `seat_room`(`r_code`) USING BTREE,
  INDEX `s_code`(`s_code`) USING BTREE,
  INDEX `seat_room_id`(`room_id`) USING BTREE,
  CONSTRAINT `seat_room_id` FOREIGN KEY (`room_id`) REFERENCES `t_room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 22034 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_seat
-- ----------------------------
INSERT INTO `t_seat` VALUES (11, 'S001', 'A315', 8);
INSERT INTO `t_seat` VALUES (12, 'S002', 'A315', 8);
INSERT INTO `t_seat` VALUES (13, 'S003', 'A315', 8);
INSERT INTO `t_seat` VALUES (14, 'S004', 'A315', 8);
INSERT INTO `t_seat` VALUES (15, 'S005', 'A315', 8);
INSERT INTO `t_seat` VALUES (16, 'S001', 'B303', 10);
INSERT INTO `t_seat` VALUES (17, 'S002', 'B303', 10);
INSERT INTO `t_seat` VALUES (18, 'S003', 'B303', 10);
INSERT INTO `t_seat` VALUES (19, 'S004', 'B303', 10);
INSERT INTO `t_seat` VALUES (21, 'S001', 'A510', 32);
INSERT INTO `t_seat` VALUES (22, 'S002', 'A510', 32);
INSERT INTO `t_seat` VALUES (23, 'S003', 'A510', 32);
INSERT INTO `t_seat` VALUES (24, 'S004', 'A510', 32);
INSERT INTO `t_seat` VALUES (25, 'S005', 'A510', 32);
INSERT INTO `t_seat` VALUES (26, 'S006', 'A510', 32);
INSERT INTO `t_seat` VALUES (27, 'S007', 'A510', 32);
INSERT INTO `t_seat` VALUES (28, 'S008', 'A510', 32);
INSERT INTO `t_seat` VALUES (29, 'S009', 'A510', 32);
INSERT INTO `t_seat` VALUES (30, 'S010', 'A510', 32);
INSERT INTO `t_seat` VALUES (31, 'S001', 'B420', 33);
INSERT INTO `t_seat` VALUES (32, 'S002', 'B420', 33);
INSERT INTO `t_seat` VALUES (33, 'S003', 'B420', 33);
INSERT INTO `t_seat` VALUES (41, 'S001', '302', 34);
INSERT INTO `t_seat` VALUES (42, 'S002', '302', 34);
INSERT INTO `t_seat` VALUES (43, 'S003', '302', 34);
INSERT INTO `t_seat` VALUES (44, 'S004', '302', 34);
INSERT INTO `t_seat` VALUES (45, 'S005', '302', 34);
INSERT INTO `t_seat` VALUES (46, 'S006', '302', 34);
INSERT INTO `t_seat` VALUES (47, 'S007', '302', 34);
INSERT INTO `t_seat` VALUES (48, 'S008', '302', 34);
INSERT INTO `t_seat` VALUES (49, 'S009', '302', 34);
INSERT INTO `t_seat` VALUES (50, 'S010', '302', 34);
INSERT INTO `t_seat` VALUES (51, 'S001', '213213', 36);
INSERT INTO `t_seat` VALUES (52, 'S002', '213213', 36);
INSERT INTO `t_seat` VALUES (53, 'S003', '213213', 36);
INSERT INTO `t_seat` VALUES (54, 'S004', '213213', 36);
INSERT INTO `t_seat` VALUES (55, 'S005', '213213', 36);
INSERT INTO `t_seat` VALUES (56, 'S001', '12321', 38);
INSERT INTO `t_seat` VALUES (57, 'S002', '12321', 38);
INSERT INTO `t_seat` VALUES (58, 'S003', '12321', 38);
INSERT INTO `t_seat` VALUES (59, 'S004', '12321', 38);
INSERT INTO `t_seat` VALUES (60, 'S005', '12321', 38);
INSERT INTO `t_seat` VALUES (61, 'S006', '12321', 38);
INSERT INTO `t_seat` VALUES (62, 'S007', '12321', 38);
INSERT INTO `t_seat` VALUES (63, 'S008', '12321', 38);
INSERT INTO `t_seat` VALUES (64, 'S009', '12321', 38);
INSERT INTO `t_seat` VALUES (65, 'S010', '12321', 38);
INSERT INTO `t_seat` VALUES (66, 'S011', '12321', 38);
INSERT INTO `t_seat` VALUES (67, 'S012', '12321', 38);
INSERT INTO `t_seat` VALUES (68, 'S013', '12321', 38);
INSERT INTO `t_seat` VALUES (69, 'S014', '12321', 38);
INSERT INTO `t_seat` VALUES (70, 'S015', '12321', 38);
INSERT INTO `t_seat` VALUES (71, 'S016', '12321', 38);
INSERT INTO `t_seat` VALUES (72, 'S017', '12321', 38);
INSERT INTO `t_seat` VALUES (73, 'S018', '12321', 38);
INSERT INTO `t_seat` VALUES (74, 'S019', '12321', 38);
INSERT INTO `t_seat` VALUES (75, 'S020', '12321', 38);
INSERT INTO `t_seat` VALUES (76, 'S021', '12321', 38);
INSERT INTO `t_seat` VALUES (77, 'S001', '321', 39);
INSERT INTO `t_seat` VALUES (78, 'S002', '321', 39);
INSERT INTO `t_seat` VALUES (79, 'S003', '321', 39);
INSERT INTO `t_seat` VALUES (80, 'S004', '321', 39);
INSERT INTO `t_seat` VALUES (19417, 'S001', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19420, 'S002', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19422, 'S003', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19424, 'S004', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19426, 'S005', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19428, 'S006', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19430, 'S007', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19432, 'S008', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19434, 'S009', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19436, 'S010', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19438, 'S011', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19440, 'S012', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19442, 'S013', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19443, 'S014', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19445, 'S015', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19447, 'S016', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19449, 'S017', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19451, 'S018', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19453, 'S019', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19455, 'S020', 'AB211', 45);
INSERT INTO `t_seat` VALUES (19457, 'S001', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19459, 'S002', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19462, 'S003', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19464, 'S004', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19466, 'S005', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19468, 'S006', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19469, 'S007', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19471, 'S008', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19473, 'S009', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19475, 'S010', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19477, 'S011', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19479, 'S012', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19481, 'S013', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19483, 'S014', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19485, 'S015', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19487, 'S016', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19489, 'S017', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19491, 'S018', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19493, 'S019', 'AB211', 46);
INSERT INTO `t_seat` VALUES (19495, 'S020', 'AB211', 46);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '账号ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学工号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `role` int(0) NULL DEFAULT NULL COMMENT '账户角色 0：超级管理员 1：管理员 2：用户',
  `status` int(0) NULL DEFAULT NULL COMMENT '账户状态 0：正常 1：禁用',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin12321', '管理员', '123456', 1, 1, '2023-03-06 23:00:13');
INSERT INTO `t_user` VALUES (2, '8102190206', '黄婧仪', '123456', 2, 0, '2023-03-08 22:02:05');
INSERT INTO `t_user` VALUES (3, '8102180206', '张三', '123456', 2, 0, '2023-03-08 22:02:33');

-- ----------------------------
-- Procedure structure for update_status
-- ----------------------------
DROP PROCEDURE IF EXISTS `update_status`;
delimiter ;;
CREATE PROCEDURE `update_status`()
begin
update t_book set book_status = 2 where book_status = 1 and (now() between start_time and end_time);
update t_book set book_status = 3 where book_status = 2 and (now() <= end_time);
end
;;
delimiter ;

-- ----------------------------
-- Event structure for update_event
-- ----------------------------
DROP EVENT IF EXISTS `update_event`;
delimiter ;;
CREATE EVENT `update_event`
ON SCHEDULE
EVERY '20' SECOND STARTS '2023-04-18 18:13:57'
DO Begin
update t_book set book_status = 2 where book_status = 1 and (now() between start_time and end_time);
update t_book set book_status = 3 where book_status = 2 and (now() >= end_time);
End
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
