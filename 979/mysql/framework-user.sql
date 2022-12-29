/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : framework-user

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/12/2022 11:53:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限图标',
  `sort` tinyint(11) NULL DEFAULT NULL COMMENT '权限排序',
  `level` tinyint(11) NULL DEFAULT NULL COMMENT '权限层级',
  `left` tinyint(11) NULL DEFAULT NULL COMMENT '权限左值',
  `right` tinyint(11) NULL DEFAULT NULL COMMENT '权限右值',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `parent_id` bigint(64) NULL DEFAULT NULL COMMENT '父级id',
  `trace_id` bigint(64) NULL DEFAULT NULL COMMENT '祖链id',
  `is_show` bit(1) NULL DEFAULT b'1' COMMENT '是否显示',
  `is_init` bit(1) NULL DEFAULT b'1' COMMENT '是否初始化（是否初始化给租户）',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE`(`code`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE,
  INDEX `NAME`(`name`) USING BTREE,
  INDEX `PARENT_ID`(`parent_id`) USING BTREE,
  INDEX `TRACE_ID`(`trace_id`) USING BTREE,
  INDEX `LEFT_RIGHT`(`left`, `right`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1539134448305491970, '系统', 'SYSTEM', 'MENU', 'icon:20202.png', 1, 1, 1, 2, '/platform', NULL, 1539534613520924673, b'1', b'1', 1, 1, 1655793222881, 1655794403402, b'1', 3);
INSERT INTO `t_permission` VALUES (1539136493238599681, '菜单', 'SYSTEM_MENU', 'MENU', 'icon:20202.png', 1, 2, 2, 5, '/api/user/permission/tree', 1539134448305491970, 1539534613520924673, b'1', b'1', 1, 1, 1655793716187, 1655793839235, b'1', 1);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE`(`code`, `tenant_id`) USING BTREE,
  INDEX `NAME`(`name`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1398204122871236980, '普通用户', 'ROLE_ORDINARY_USER', 1539534613520924673, 1, 1, 1655793222881, 1655793222881, b'1', 0);
INSERT INTO `t_role` VALUES (1398204122871765123, '管理员', 'ROLE_ADMIN', 1539534613520962784, 1, 1, 1655793222881, 1655793222881, b'1', 0);
INSERT INTO `t_role` VALUES (1398204122871860322, '管理员', 'ROLE_ADMIN', 1539534613520924673, 1, 1, 1655793222881, 1655793222881, b'1', 0);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `role_id` bigint(64) NOT NULL COMMENT '角色id',
  `permission_id` bigint(64) NOT NULL COMMENT '权限id',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户id',
  INDEX `ROLE_ID`(`role_id`) USING BTREE,
  INDEX `PERMISSION_ID`(`permission_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1398204122871860322, 1539134448305491970, 1539534613520924673);
INSERT INTO `t_role_permission` VALUES (1398204122871765123, 1539134448305491970, 1539534613520962784);
INSERT INTO `t_role_permission` VALUES (1398204122871236980, 1539136493238599681, 1539534613520924673);

-- ----------------------------
-- Table structure for t_user_0
-- ----------------------------
DROP TABLE IF EXISTS `t_user_0`;
CREATE TABLE `t_user_0`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户状态（ CANCELLATION=注销，NORMAL=正常，DISABLE=禁用 ）',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `EMAIL`(`email`, `tenant_id`) USING BTREE,
  INDEX `MOBILE`(`mobile`, `tenant_id`) USING BTREE,
  INDEX `USERNAME`(`username`, `tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`, `email`, `mobile`, `username`, `tenant_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_0
-- ----------------------------
INSERT INTO `t_user_0` VALUES (1557624704405737472, 'MAN', 'Ve+XSvp6Lja03fji5xMcH2arLB3ON4+NoAbkLiLSBM4=', 'LRgwryWz9XrKqsFKF7/IUw==', 'TaLyHpUeLJtAjAbxqwMr2g==', '979', '2022-08-11', 'wwwwwwwwww', 'fff', 'NORMAL', 1539534613520924673, 1557624704405737472, 1557624704405737472, 1660201643490, 1660201643490, b'1', 0);

-- ----------------------------
-- Table structure for t_user_1
-- ----------------------------
DROP TABLE IF EXISTS `t_user_1`;
CREATE TABLE `t_user_1`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户状态（ CANCELLATION=注销，NORMAL=正常，DISABLE=禁用 ）',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `EMAIL`(`email`, `tenant_id`) USING BTREE,
  INDEX `MOBILE`(`mobile`, `tenant_id`) USING BTREE,
  INDEX `USERNAME`(`username`, `tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`, `email`, `mobile`, `username`, `tenant_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_1
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_auth_0
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth_0`;
CREATE TABLE `t_user_auth_0`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_id` bigint(64) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份类型（MOBILE:用户名,USERNAME:手机号,EMAIL:邮箱,WX:微信,ZFB:支付宝）',
  `identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `is_verified` bit(1) NULL DEFAULT b'0' COMMENT '是否已经验证',
  `is_binding` bit(1) NULL DEFAULT b'0' COMMENT '是否绑定中',
  `verified_time` bigint(20) NULL DEFAULT NULL COMMENT '验证时间',
  `un_binding_time` bigint(20) NULL DEFAULT NULL COMMENT '解除绑定时间',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDENTIFIER`(`identifier`, `tenant_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth_0
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_auth_1
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth_1`;
CREATE TABLE `t_user_auth_1`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_id` bigint(64) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份类型（MOBILE:用户名,USERNAME:手机号,EMAIL:邮箱,WX:微信,ZFB:支付宝）',
  `identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `is_verified` bit(1) NULL DEFAULT b'0' COMMENT '是否已经验证',
  `is_binding` bit(1) NULL DEFAULT b'0' COMMENT '是否绑定中',
  `verified_time` bigint(20) NULL DEFAULT NULL COMMENT '验证时间',
  `un_binding_time` bigint(20) NULL DEFAULT NULL COMMENT '解除绑定时间',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDENTIFIER`(`identifier`, `tenant_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth_1
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_auth_2
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth_2`;
CREATE TABLE `t_user_auth_2`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_id` bigint(64) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份类型（MOBILE:用户名,USERNAME:手机号,EMAIL:邮箱,WX:微信,ZFB:支付宝）',
  `identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `is_verified` bit(1) NULL DEFAULT b'0' COMMENT '是否已经验证',
  `is_binding` bit(1) NULL DEFAULT b'0' COMMENT '是否绑定中',
  `verified_time` bigint(20) NULL DEFAULT NULL COMMENT '验证时间',
  `un_binding_time` bigint(20) NULL DEFAULT NULL COMMENT '解除绑定时间',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDENTIFIER`(`identifier`, `tenant_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth_2
-- ----------------------------
INSERT INTO `t_user_auth_2` VALUES (1557624704820973568, 1557624704405737472, 'MOBILE', 'LRgwryWz9XrKqsFKF7/IUw==', '8c62644d5041f635c5a5043a65353f51', b'1', b'1', 1660201643454, 1660201643454, 1539534613520924673, 1557624704405737472, 1557624704405737472, 1660201643553, 1660201643553, b'1', 0);

-- ----------------------------
-- Table structure for t_user_auth_3
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth_3`;
CREATE TABLE `t_user_auth_3`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_id` bigint(64) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份类型（MOBILE:用户名,USERNAME:手机号,EMAIL:邮箱,WX:微信,ZFB:支付宝）',
  `identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `is_verified` bit(1) NULL DEFAULT b'0' COMMENT '是否已经验证',
  `is_binding` bit(1) NULL DEFAULT b'0' COMMENT '是否绑定中',
  `verified_time` bigint(20) NULL DEFAULT NULL COMMENT '验证时间',
  `un_binding_time` bigint(20) NULL DEFAULT NULL COMMENT '解除绑定时间',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDENTIFIER`(`identifier`, `tenant_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth_3
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_auth_4
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth_4`;
CREATE TABLE `t_user_auth_4`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_id` bigint(64) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份类型（MOBILE:用户名,USERNAME:手机号,EMAIL:邮箱,WX:微信,ZFB:支付宝）',
  `identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `is_verified` bit(1) NULL DEFAULT b'0' COMMENT '是否已经验证',
  `is_binding` bit(1) NULL DEFAULT b'0' COMMENT '是否绑定中',
  `verified_time` bigint(20) NULL DEFAULT NULL COMMENT '验证时间',
  `un_binding_time` bigint(20) NULL DEFAULT NULL COMMENT '解除绑定时间',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDENTIFIER`(`identifier`, `tenant_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth_4
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_auth_5
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth_5`;
CREATE TABLE `t_user_auth_5`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_id` bigint(64) NULL DEFAULT NULL COMMENT '用户id',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份类型（MOBILE:用户名,USERNAME:手机号,EMAIL:邮箱,WX:微信,ZFB:支付宝）',
  `identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `credential` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权凭证',
  `is_verified` bit(1) NULL DEFAULT b'0' COMMENT '是否已经验证',
  `is_binding` bit(1) NULL DEFAULT b'0' COMMENT '是否绑定中',
  `verified_time` bigint(20) NULL DEFAULT NULL COMMENT '验证时间',
  `un_binding_time` bigint(20) NULL DEFAULT NULL COMMENT '解除绑定时间',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDENTIFIER`(`identifier`, `tenant_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户认证方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth_5
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `role_id` bigint(64) NOT NULL COMMENT '关联角色',
  `user_id` bigint(64) NOT NULL COMMENT '关联用户表',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户id',
  INDEX `ROLE_ID`(`role_id`) USING BTREE,
  INDEX `USER_ID`(`user_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1398204122871236980, 1557624704405737472, 1539534613520924673);
INSERT INTO `t_user_role` VALUES (1398204122871860322, 1557624704405737472, 1539534613520924673);

-- ----------------------------
-- Table structure for tcc_fence_log
-- ----------------------------
DROP TABLE IF EXISTS `tcc_fence_log`;
CREATE TABLE `tcc_fence_log`  (
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global id',
  `branch_id` bigint(20) NOT NULL COMMENT 'branch id',
  `action_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'action name',
  `status` tinyint(4) NOT NULL COMMENT 'status(tried:1;committed:2;rollbacked:3;suspended:4)',
  `gmt_create` datetime(3) NOT NULL COMMENT 'create time',
  `gmt_modified` datetime(3) NOT NULL COMMENT 'update time',
  PRIMARY KEY (`xid`, `branch_id`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tcc_fence_log
-- ----------------------------

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
