/*
 Navicat Premium Data Transfer

 Source Server         : ghost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : dentistry

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 06/06/2021 18:26:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `recentEditorId` bigint(20) NULL DEFAULT NULL COMMENT '最近编辑人',
  `eventTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `eventImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动图片',
  `numberOfEntries` int(11) NULL DEFAULT NULL COMMENT '报名人数',
  `published` bit(1) NULL DEFAULT NULL COMMENT '发布状态 0-未发布 1-已发布',
  `enrollOpened` bit(1) NULL DEFAULT NULL COMMENT '是否开启报名 0-关闭 1-开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `bannerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播图名称',
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `linkUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态 0-停用 1-启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '2021-06-03 18:00:56', '2021-06-06 18:15:03', 6, 'banner02', '/banner/69da176a-208c-457b-9c2c-cc53a89f83ed.png', 'aaa', b'1');
INSERT INTO `banner` VALUES (2, '2021-06-06 16:08:25', '2021-06-06 17:38:20', 23, 'banner03', NULL, 'http://www.baidu.com', b'1');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `locked` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '2021-06-01 14:00:00', NULL, 0, 'admin', '超级管理员', '123456', NULL, b'0');

-- ----------------------------
-- Table structure for employeerole
-- ----------------------------
DROP TABLE IF EXISTS `employeerole`;
CREATE TABLE `employeerole`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `employeeId` bigint(20) NULL DEFAULT NULL,
  `roleId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `employeeId_roleId_uindex`(`employeeId`, `roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employeerole
-- ----------------------------
INSERT INTO `employeerole` VALUES (1, NULL, NULL, 0, 1, 1);

-- ----------------------------
-- Table structure for enroll
-- ----------------------------
DROP TABLE IF EXISTS `enroll`;
CREATE TABLE `enroll`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `orderNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型 0-职场百分百 1-就业直通车 2-普通活动',
  `orderStatus` tinyint(1) NULL DEFAULT NULL COMMENT '订单状态 0-待支付 1-已支付',
  `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enroll
-- ----------------------------
INSERT INTO `enroll` VALUES (1, '2021-06-02 11:03:32', '2021-06-02 11:03:35', 00000000000, '0_1233523424', 2, 1, 1);
INSERT INTO `enroll` VALUES (2, '2021-06-03 11:49:04', NULL, 00000000000, '0_5555555555', 0, 0, 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_uindex`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '2021-06-01 14:00:00', NULL, 0, '*', '全部权限');
INSERT INTO `permission` VALUES (2, '2021-06-01 14:00:00', NULL, 0, 'message.sms.list', '短信消息-列表');
INSERT INTO `permission` VALUES (3, '2021-06-01 14:00:00', NULL, 0, 'message.smsVerification.list', '验证短信-列表');
INSERT INTO `permission` VALUES (4, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.add', '员工-添加');
INSERT INTO `permission` VALUES (5, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.list', '员工-列表');
INSERT INTO `permission` VALUES (6, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.get', '员工-获取');
INSERT INTO `permission` VALUES (7, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.assignRolesOptions', '员工-获取分配角色选项');
INSERT INTO `permission` VALUES (8, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.edit', '员工-编辑');
INSERT INTO `permission` VALUES (9, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.assignRoles', '员工-分配角色');
INSERT INTO `permission` VALUES (10, '2021-06-01 14:00:00', NULL, 0, 'platform.employee.resetPassword', '员工-重置密码');
INSERT INTO `permission` VALUES (11, '2021-06-01 14:00:00', NULL, 0, 'platform.permission.add', '权限-添加');
INSERT INTO `permission` VALUES (12, '2021-06-01 14:00:00', NULL, 0, 'platform.permission.get', '权限-获取');
INSERT INTO `permission` VALUES (13, '2021-06-01 14:00:00', NULL, 0, 'platform.permission.list', '权限-列表');
INSERT INTO `permission` VALUES (14, '2021-06-01 14:00:00', NULL, 0, 'platform.permission.edit', '权限-修改');
INSERT INTO `permission` VALUES (15, '2021-06-01 14:00:00', NULL, 0, 'platform.role.add', '角色-添加');
INSERT INTO `permission` VALUES (16, '2021-06-01 14:00:00', NULL, 0, 'platform.role.get', '角色-获取');
INSERT INTO `permission` VALUES (17, '2021-06-01 14:00:00', NULL, 0, 'platform.role.list', '角色-列表');
INSERT INTO `permission` VALUES (18, '2021-06-01 14:00:00', NULL, 0, 'platform.role.assignPermissionsOptions', '角色-获取分配权限选项');
INSERT INTO `permission` VALUES (19, '2021-06-01 14:00:00', NULL, 0, 'platform.role.assignPermissions', '角色-分配权限');
INSERT INTO `permission` VALUES (20, '2021-06-01 14:00:00', NULL, 0, 'platform.role.edit', '角色-修改');
INSERT INTO `permission` VALUES (21, '2021-06-01 14:00:00', NULL, 0, 'user.user.add', '用户-添加');
INSERT INTO `permission` VALUES (22, '2021-06-01 14:00:00', NULL, 0, 'user.user.get', '用户-获取');
INSERT INTO `permission` VALUES (23, '2021-06-01 14:00:00', NULL, 0, 'user.user.list', '用户-列表');
INSERT INTO `permission` VALUES (24, '2021-06-01 14:00:00', NULL, 0, 'user.user.edit', '用户-修改');
INSERT INTO `permission` VALUES (25, '2021-06-01 15:38:40', '2021-06-02 10:06:39', 3, 'enroll.enroll.list', '报名-列表');

-- ----------------------------
-- Table structure for registereduser
-- ----------------------------
DROP TABLE IF EXISTS `registereduser`;
CREATE TABLE `registereduser`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `createdDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `lastModifiedDate` datetime NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `realName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `locked` bit(1) NULL DEFAULT NULL COMMENT '是否锁定 0-未锁定 1-已锁定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of registereduser
-- ----------------------------
INSERT INTO `registereduser` VALUES (1, '2021-06-01 14:53:32', '2021-06-06 14:36:39', 5, '甘乐', 17, 1, '15228943505', b'0');
INSERT INTO `registereduser` VALUES (2, '2021-06-06 11:50:19', '2021-06-06 14:36:38', 5, 'aaa', 12, 1, '14252415263', b'0');
INSERT INTO `registereduser` VALUES (3, '2021-06-06 12:02:50', '2021-06-06 15:05:50', 73, 'bbb', 15, 0, '15674859685', b'0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '2021-06-01 14:00:00', NULL, 0, '超级管理员', '拥有全部权限');

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `roleId` bigint(20) NULL DEFAULT NULL,
  `permissionId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `roleId_permissionId_uindex`(`roleId`, `permissionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------
INSERT INTO `rolepermission` VALUES (1, NULL, NULL, 0, 1, 1);

-- ----------------------------
-- Table structure for smsmessage
-- ----------------------------
DROP TABLE IF EXISTS `smsmessage`;
CREATE TABLE `smsmessage`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `templateId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `closeDate` datetime NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phoneNumber_index`(`phoneNumber`) USING BTREE,
  INDEX `state_index`(`state`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of smsmessage
-- ----------------------------

-- ----------------------------
-- Table structure for smsverification
-- ----------------------------
DROP TABLE IF EXISTS `smsverification`;
CREATE TABLE `smsverification`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `smsMessageId` bigint(20) NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `retryCount` int(11) NULL DEFAULT NULL,
  `expirationDate` datetime NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL,
  `type` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `smsMessageId_index`(`smsMessageId`) USING BTREE,
  INDEX `phoneNumber_index`(`phoneNumber`) USING BTREE,
  INDEX `requestIp_index`(`requestIp`) USING BTREE,
  INDEX `state_index`(`state`) USING BTREE,
  INDEX `type_index`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of smsverification
-- ----------------------------

-- ----------------------------
-- Table structure for userprofessioninfo
-- ----------------------------
DROP TABLE IF EXISTS `userprofessioninfo`;
CREATE TABLE `userprofessioninfo`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `createdDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `lastModifiedDate` datetime NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `graduatedCollege` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业学院',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业',
  `education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历水平',
  `expectedOccupation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期望从事职业',
  `expectedAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期望就业地址',
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of userprofessioninfo
-- ----------------------------
INSERT INTO `userprofessioninfo` VALUES (1, '2021-06-06 10:45:05', NULL, 0, '家里蹲大学', '计算机', '本科', 'java', '成都', 1);
INSERT INTO `userprofessioninfo` VALUES (2, '2021-06-06 11:57:44', NULL, 0, 'aaa', 'aaa', 'aaa', 'aaa', 'aaa', 2);

SET FOREIGN_KEY_CHECKS = 1;
