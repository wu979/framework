/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : framework-pay

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/12/2022 11:53:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_pay_notify_0
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_0`;
CREATE TABLE `t_pay_notify_0`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_0
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_1
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_1`;
CREATE TABLE `t_pay_notify_1`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_1
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_2
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_2`;
CREATE TABLE `t_pay_notify_2`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_2
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_3
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_3`;
CREATE TABLE `t_pay_notify_3`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_3
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_4
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_4`;
CREATE TABLE `t_pay_notify_4`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_4
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_5
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_5`;
CREATE TABLE `t_pay_notify_5`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_5
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_6
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_6`;
CREATE TABLE `t_pay_notify_6`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_6
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_7
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_7`;
CREATE TABLE `t_pay_notify_7`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_7
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_8
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_8`;
CREATE TABLE `t_pay_notify_8`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_8
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_notify_9
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_notify_9`;
CREATE TABLE `t_pay_notify_9`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `pay_order_id` bigint(64) NULL DEFAULT NULL COMMENT '支付订单id',
  `resource` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调数据',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT NULL COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `PAY_ORDER_ID`(`pay_order_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_notify_9
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_01
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_01`;
CREATE TABLE `t_pay_order_2022_01`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_01
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_02
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_02`;
CREATE TABLE `t_pay_order_2022_02`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_02
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_03
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_03`;
CREATE TABLE `t_pay_order_2022_03`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_03
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_04
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_04`;
CREATE TABLE `t_pay_order_2022_04`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_04
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_05
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_05`;
CREATE TABLE `t_pay_order_2022_05`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_05
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_06
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_06`;
CREATE TABLE `t_pay_order_2022_06`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_06
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_07
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_07`;
CREATE TABLE `t_pay_order_2022_07`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_07
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_08
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_08`;
CREATE TABLE `t_pay_order_2022_08`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_08
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_09
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_09`;
CREATE TABLE `t_pay_order_2022_09`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_09
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_10
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_10`;
CREATE TABLE `t_pay_order_2022_10`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_10
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_11
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_11`;
CREATE TABLE `t_pay_order_2022_11`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_11
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2022_12
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2022_12`;
CREATE TABLE `t_pay_order_2022_12`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2022_12
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_01
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_01`;
CREATE TABLE `t_pay_order_2023_01`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_01
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_02
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_02`;
CREATE TABLE `t_pay_order_2023_02`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_02
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_03
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_03`;
CREATE TABLE `t_pay_order_2023_03`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_03
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_04
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_04`;
CREATE TABLE `t_pay_order_2023_04`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_04
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_05
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_05`;
CREATE TABLE `t_pay_order_2023_05`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_05
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_06
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_06`;
CREATE TABLE `t_pay_order_2023_06`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_06
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_07
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_07`;
CREATE TABLE `t_pay_order_2023_07`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_07
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_08
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_08`;
CREATE TABLE `t_pay_order_2023_08`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_08
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_09
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_09`;
CREATE TABLE `t_pay_order_2023_09`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_09
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_10
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_10`;
CREATE TABLE `t_pay_order_2023_10`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_10
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_11
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_11`;
CREATE TABLE `t_pay_order_2023_11`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_11
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_order_2023_12
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_order_2023_12`;
CREATE TABLE `t_pay_order_2023_12`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付订单号',
  `order_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单号',
  `order_amount` bigint(20) NULL DEFAULT 0 COMMENT '订单金额',
  `pay_amount` bigint(20) NULL DEFAULT NULL COMMENT '支付金额',
  `success_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付类型',
  `mode_id` bigint(64) NULL DEFAULT NULL COMMENT '支付方式id',
  `trade_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
  `trade_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信支付交易状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NUM`(`num`) USING BTREE,
  UNIQUE INDEX `ORDER_NUM`(`order_num`) USING BTREE,
  UNIQUE INDEX `TRADE_ID`(`trade_id`) USING BTREE,
  INDEX `TENANT_ID`(`tenant_id`) USING BTREE,
  INDEX `STATUS`(`status`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_order_2023_12
-- ----------------------------

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
