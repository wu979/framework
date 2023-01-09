/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : framework-file

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/12/2022 11:53:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_file_2022_01
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_01`;
CREATE TABLE `t_file_2022_01`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_01
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_02
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_02`;
CREATE TABLE `t_file_2022_02`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_02
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_03
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_03`;
CREATE TABLE `t_file_2022_03`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_03
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_04
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_04`;
CREATE TABLE `t_file_2022_04`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_04
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_05
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_05`;
CREATE TABLE `t_file_2022_05`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_05
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_06
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_06`;
CREATE TABLE `t_file_2022_06`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_06
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_07
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_07`;
CREATE TABLE `t_file_2022_07`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_07
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_08
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_08`;
CREATE TABLE `t_file_2022_08`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_08
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_09
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_09`;
CREATE TABLE `t_file_2022_09`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_09
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_10
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_10`;
CREATE TABLE `t_file_2022_10`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_10
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_11
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_11`;
CREATE TABLE `t_file_2022_11`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_11
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2022_12
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2022_12`;
CREATE TABLE `t_file_2022_12`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2022_12
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_01
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_01`;
CREATE TABLE `t_file_2023_01`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_01
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_02
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_02`;
CREATE TABLE `t_file_2023_02`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_02
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_03
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_03`;
CREATE TABLE `t_file_2023_03`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_03
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_04
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_04`;
CREATE TABLE `t_file_2023_04`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_04
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_05
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_05`;
CREATE TABLE `t_file_2023_05`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_05
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_06
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_06`;
CREATE TABLE `t_file_2023_06`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_06
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_07
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_07`;
CREATE TABLE `t_file_2023_07`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_07
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_08
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_08`;
CREATE TABLE `t_file_2023_08`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_08
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_09
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_09`;
CREATE TABLE `t_file_2023_09`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_09
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_10
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_10`;
CREATE TABLE `t_file_2023_10`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_10
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_11
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_11`;
CREATE TABLE `t_file_2023_11`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_11
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_2023_12
-- ----------------------------
DROP TABLE IF EXISTS `t_file_2023_12`;
CREATE TABLE `t_file_2023_12`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `new_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件新名称',
  `ext` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `size` bigint(32) NULL DEFAULT NULL COMMENT '文件大小(单位:kb)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径盐值（path md5）',
  `biz_id` bigint(64) NULL DEFAULT NULL COMMENT '业务id(业务未设置，则生成雪花返回给业务)',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_2023_12
-- ----------------------------

-- ----------------------------
-- Table structure for worker_node
-- ----------------------------
CREATE TABLE `worker_node` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
   `host_name` varchar(64) NOT NULL COMMENT 'host name',
   `port` varchar(64) NOT NULL COMMENT 'port',
   `type` int(11) NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
   `launch_date` date NOT NULL COMMENT 'launch date',
   `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modified time',
   `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'created time',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='DB WorkerID Assigner for UID Generator';
-- ----------------------------
-- Records of worker_node
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
