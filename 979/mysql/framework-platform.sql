/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : framework-platform

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/12/2022 11:53:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `t_gateway_route`;
CREATE TABLE `t_gateway_route`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由',
  `predicates` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '断言',
  `filters` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '过滤',
  `sort` tinyint(3) NULL DEFAULT NULL COMMENT '排序',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` bit(1) NULL DEFAULT b'0' COMMENT '是否启用',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NAME`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '动态路由' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_gateway_route
-- ----------------------------
INSERT INTO `t_gateway_route` VALUES (1274383603682214965, 'platform', 'lb://framework-cloud-platform-api', '[{\"args\":{\"_genkey_0\":\"/api/platform/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"2\"},\"name\":\"StripPrefix\"}]', 2, '平台', b'1', 1542053786268950529, 1542053786268950529, 1659492773302, 1659492773302, b'1', 0);
INSERT INTO `t_gateway_route` VALUES (1274383603682346122, 'oauth', 'lb://framework-cloud-oauth-api', '[{\"args\":{\"_genkey_0\":\"/api/oauth/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"}]', 3, '授权', b'1', 1542053786268950529, 1542053786268950529, 1659492773302, 1659492773302, b'1', 0);
INSERT INTO `t_gateway_route` VALUES (1274383603682705409, 'user', 'lb://framework-cloud-user-api', '[{\"args\":{\"_genkey_0\":\"/api/user/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"2\"},\"name\":\"StripPrefix\"}]', 1, '用户', b'1', 1542053786268950529, 1542053786268950529, 1659492773302, 1659492773302, b'1', 0);
INSERT INTO `t_gateway_route` VALUES (1554804122710949888, 'pay', 'lb://framework-cloud-pay-api', '[{\"args\":{\"_genkey_0\":\"/api/pay/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"2\"},\"name\":\"StripPrefix\"}]', 5, '支付', b'1', 1542053786268950529, 1, 1659529164379, 1659592944326, b'1', 40);
INSERT INTO `t_gateway_route` VALUES (1608010287009390592, 'file', 'lb://framework-cloud-file-api', '[{\"args\":{\"_genkey_0\":\"/api/file/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"2\"},\"name\":\"StripPrefix\"}]', 5, '文件', b'1', 1557624704405737472, 1557624704405737472, 1672214502466, 1672215008308, b'1', 2);

-- ----------------------------
-- Table structure for t_oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `t_oauth_code`;
CREATE TABLE `t_oauth_code`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权码',
  `authentication` blob NOT NULL COMMENT '授权用户二进制信息',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `expires_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE`(`code`) USING BTREE COMMENT '授权码索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '授权码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_pay_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_channel`;
CREATE TABLE `t_pay_channel`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标识',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `app_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `app_secret` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '秘钥',
  `private_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '私钥',
  `public_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公钥',
  `external_public_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方公钥',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` bit(1) NULL DEFAULT b'0' COMMENT '是否启用（0否1是）',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE`(`code`) USING BTREE,
  INDEX `TYPE`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付渠道' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_channel
-- ----------------------------
INSERT INTO `t_pay_channel` VALUES (1556552296967430144, 'WX-1000000', '微信支付-限额100万', 'WX', 'FG85O-230R4KJ', '3y3tg3ryh5uy5t3t3t3y', 't2390hy34tj309k0r23r0k20rek20tj39t,3tj3t93ht38th38thj39tj39th398ht', 't2390hy34tj309k0r23r0k20rek20tj39t,3tj3t93ht38th38thj39tj39th398ht', 't2390hy34tj309k0r23r0k20rek20tj39t,3tj3t93ht38th38thj39tj39th398ht', '微信支付', b'1', 1542053786268950529, 1542053786268950529, 1659945961598, 1659946294060, b'1', 5);

-- ----------------------------
-- Table structure for t_pay_mode
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_mode`;
CREATE TABLE `t_pay_mode`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `channel_id` bigint(64) NULL DEFAULT NULL COMMENT '支付渠道id',
  `mch_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '直连商户号（微信）',
  `mode_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付方式类型',
  `mode_version` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付方式版本号',
  `notify_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调请求路径',
  `return_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付后停留页面路径',
  `retry_count` tinyint(2) NULL DEFAULT NULL COMMENT '回调失败主动查询重试次数',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` bit(1) NULL DEFAULT b'0' COMMENT '是否启用（0否1是）',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CHANNEL_ID_MODE_TYPE`(`channel_id`, `mode_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付方式' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_mode
-- ----------------------------
INSERT INTO `t_pay_mode` VALUES (1556555361023762432, 1556552296967430144, '23t3t423e2', 'WX_APP', 'v3', 'https://www.baidu.com', NULL, 3, '微信APP支付', b'1', 1542053786268950529, 1542053786268950529, 1659946692125, 1659946937292, b'1', 5);

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `permission_delete` bit(1) NULL DEFAULT b'0' COMMENT '删除权限时是否删除后续节点\r\n（是：删除后续子节点，更新其余节点左右值）\r\n（否：删除当前节点，并更新当前节点左值+1节点的父级id，并且后续所有节点-2）',
  `tenant_id` bigint(64) NULL DEFAULT NULL COMMENT '租户id',
  `version` tinyint(11) NULL DEFAULT 1 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `TENANT_ID`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
INSERT INTO `t_setting` VALUES (1518137211888954023, b'1', 1539534613520924673, 1);

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `code` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秘钥',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `max_count` int(11) NULL DEFAULT NULL COMMENT '单用户每天最大认证次数',
  `redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向URI',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT '授权令牌的有效时间值(单位:秒)',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '刷新令牌的有效时间值(单位:秒)',
  `scope` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请的权限范围',
  `grant_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支持授权模式',
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值',
  `approve` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'false' COMMENT '设置用户是否自动Approval操作, 默认值为 \'false\', 可选值包括 \'true\',\'false\', \'read\',\'write\'.',
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问的资源id集合',
  `create_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人id',
  `update_id` bigint(64) NULL DEFAULT NULL COMMENT '修改人id',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否有效（0否1是）',
  `version` tinyint(11) NULL DEFAULT 0 COMMENT '并发版本控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `CODE`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '租户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
INSERT INTO `t_tenant` VALUES (1539534613520924673, 'tenxun', '疼迅', 'e10adc3949ba59abbe56e057f20f883e', 'REVIEWED', 10, 'https://www.baidu.com', -1, -1, 'all', 'authorization_code,password,client_credentials,refresh_token,implicit', NULL, 'false', NULL, 1542053786268950529, 1542053786268950529, 1655888629691, 1655888629692, b'1', 0);
INSERT INTO `t_tenant` VALUES (1539534613520962784, 'alibaba', '阿狸巴巴', 'e10adc3949ba59abbe56e057f20f883e', 'REVIEWED', 10, 'https://www.baidu.com', -1, -1, 'all', 'authorization_code,password,client_credentials,refresh_token,implicit', NULL, 'false', NULL, 1542053786268950529, 1542053786268950529, 1655888629691, 1655888629692, b'1', 0);

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
