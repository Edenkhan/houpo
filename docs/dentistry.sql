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

 Date: 17/06/2021 18:48:42
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
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动标题',
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动图片',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动内容',
  `numberOfEntries` int(11) NULL DEFAULT NULL COMMENT '报名人数',
  `releaseStatus` tinyint(1) NULL DEFAULT NULL COMMENT '发布状态 0-未发布 1-已发布',
  `enrollStatus` tinyint(1) NULL DEFAULT NULL COMMENT '报名状态 0-关闭 1-开启',
  `recentId` bigint(20) NULL DEFAULT NULL COMMENT '最近编辑人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '2021-06-07 14:30:30', '2021-06-15 18:26:06', 9, '911活動', '/api/img/activity/f13527b6-da77-4d7f-b7cd-b77f10f38724.png', '<p>秒殺全場</p>', 1, 1, 1, 1);
INSERT INTO `activity` VALUES (3, '2021-06-15 14:30:12', '2021-06-15 18:31:54', 13, '免费送', '/api/img/activity/e62cda6b-c1b6-4153-897c-6bf1dea61c14.png', '<p>免费送了，大米小米</p>', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime NULL DEFAULT NULL,
  `lastModifiedDate` datetime NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播图名称',
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `linkUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态 0-停用 1-启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, '2021-06-03 18:00:56', '2021-06-07 17:25:46', 9, 'banner02', '/api/img/banner/6920e19e-8788-47ec-b5ad-04afd4b1fd3d.png', 'https://github.com', b'1');
INSERT INTO `banner` VALUES (2, '2021-06-06 16:08:25', '2021-06-15 15:02:45', 41, 'banner03', '/api/img/banner/ce60d7ad-c5a1-42b9-90ab-064d2502597c.png', 'http://www.baidu.com', b'0');
INSERT INTO `banner` VALUES (3, '2021-06-07 11:17:35', '2021-06-07 17:25:28', 15, 'banner01', '/api/img/banner/a70aae84-a287-4d04-8c4b-5c596dc2b34a.png', 'xxx', b'1');

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
  `activityId` bigint(20) NULL DEFAULT NULL COMMENT '活动id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of enroll
-- ----------------------------
INSERT INTO `enroll` VALUES (3, '2021-06-15 18:26:06', NULL, 00000000000, '854426558817894400', 2, 0, 8, 1);
INSERT INTO `enroll` VALUES (4, '2021-06-15 18:31:55', NULL, 00000000000, '854428022072147968', 2, 0, 8, 3);
INSERT INTO `enroll` VALUES (5, '2021-06-17 09:59:41', NULL, 00000000000, '855023889518952451', 0, 0, 8, NULL);

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
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信用户唯一id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of registereduser
-- ----------------------------
INSERT INTO `registereduser` VALUES (1, '2021-06-01 14:53:32', '2021-06-10 18:51:05', 6, '甘乐', 17, 1, '15228943505', b'0', NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (2, '2021-06-06 11:50:19', '2021-06-10 17:46:42', 6, 'aaa', 12, 1, '14252415263', b'1', NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (3, '2021-06-06 12:02:50', '2021-06-06 15:05:50', 73, 'bbb', 15, 0, '15674859685', b'0', NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (8, '2021-06-09 16:28:11', '2021-06-15 10:02:33', 2, '阿汤', 16, 1, NULL, NULL, 'o94oc5gI4p4hcPLDBNtflB_w1jKQ', '甘乐', 'https://thirdwx.qlogo.cn/mmopen/vi_32/TMAQqcP5mIFIniagwQ4gxECWiapxvEbibwEQiazIicXpCDiaSGib85NJORIX5vH1we8SufjPOTu9DGicphibrLVhBY8CnibQ/132');
INSERT INTO `registereduser` VALUES (9, '2021-06-09 16:40:48', '2021-06-15 17:52:38', 4, NULL, NULL, NULL, NULL, b'0', 'o94oc5gq7jxxe8ksCl8YYZ1m7BpM', 'Tomas', 'https://thirdwx.qlogo.cn/mmopen/vi_32/vXOazibrfHXk0YR6IAP7MXPMRUscZr0Iicrv9sV8VK9yCX6W7Z1tTZzNZqbRxnegwSdick8ClPE6KfOkc469dgiaicw/132');
INSERT INTO `registereduser` VALUES (12, '2021-06-17 14:49:42', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (13, '2021-06-17 14:53:38', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (14, '2021-06-17 14:54:03', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (15, '2021-06-17 15:01:07', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (16, '2021-06-17 15:01:10', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (17, '2021-06-17 15:03:58', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (18, '2021-06-17 15:04:35', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (19, '2021-06-17 15:04:52', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (20, '2021-06-17 15:04:56', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (21, '2021-06-17 15:05:59', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (22, '2021-06-17 15:05:59', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (23, '2021-06-17 15:07:12', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (24, '2021-06-17 15:07:12', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (25, '2021-06-17 15:09:37', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (26, '2021-06-17 15:13:46', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `registereduser` VALUES (27, '2021-06-17 15:14:40', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
-- Table structure for userotherinfo
-- ----------------------------
DROP TABLE IF EXISTS `userotherinfo`;
CREATE TABLE `userotherinfo`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `createdDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `lastModifiedDate` datetime NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业学院',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业',
  `education` tinyint(4) NULL DEFAULT NULL COMMENT '学历水平 0: \"高中及以下\", 1: \"大专\", 2: \"本科\", 3: \"硕士\", 4: \"博士及以上\"',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期望从事职业',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期望就业地址 行政区划代码',
  `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of userotherinfo
-- ----------------------------
INSERT INTO `userotherinfo` VALUES (1, '2021-06-06 10:45:05', '2021-06-15 10:02:33', 2, '家里蹲', '挖掘机', 0, 'java', '510100', 8);
INSERT INTO `userotherinfo` VALUES (2, '2021-06-06 11:57:44', NULL, 0, 'aaa', 'aaa', 0, 'aaa', '500100', 2);

SET FOREIGN_KEY_CHECKS = 1;
