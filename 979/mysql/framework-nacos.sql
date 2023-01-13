/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : framework-nacos

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 13/01/2023 16:11:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (24, 'application.yml', 'COMMON', 'server:\n  max-http-header-size: 102400\nspring:\n  mvc:\n    servlet:\n      load-on-startup: 1\n    pathmatch:\n      matching-strategy: ANT_PATH_MATCHER\n  servlet:\n    multipart:\n      max-file-size: 500MB\n      max-request-size: 500MB\n#端口\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: always\n  security:\n    health:\n      elasticsearch:\n        enabled: false\n\n#日志\nlogging:\n  config: classpath:logback-spring.xml\n\n#trace\nskywalking:\n  traceId:\n    header: ${framework.skywalking.traceId:true}', 'dfcd93291359ced126a5f67ae32ac75c', '2022-12-13 05:31:18', '2023-01-10 08:25:18', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (25, 'cache.yml', 'COMMON', 'spring:\r\n  redis:\r\n    #单机\r\n    host: ${framework.redis.host:127.0.0.1}\r\n    port: ${framework.redis.port:6379}\r\n    password: ${framework.redis.password:123456}\r\n    #哨兵\r\n    sentinel:\r\n      master: ${framework.redis.sentinel.master}\r\n      nodes: ${framework.redis.sentinel.nodes}\r\n    #集群配置\r\n    cluster:\r\n      nodes: ${framework.redis.cluster.nodes}\r\n      max-redirects: ${framework.redis.cluster.max-redirects:3}\r\n    #超时时间\r\n    timeout: ${framework.redis.timeout:2000}\r\n    #连接超时\r\n    connectTimeout: ${framework.redis.connect-timeout:3000}\r\n    #默认缓存过期时间\r\n    cache-timeout: ${framework.redis.cache-timeout:7200}\r\n    #默认缓存过期时间单位 毫秒\r\n    cache-timeout-unit: ${framework.redis.cache-timeout-unit:SECONDS}\r\n    #库\r\n    database: ${framework.redis.database:0}\r\n    #前缀\r\n    prefix: ${framework.redis.prefix}\r\n    #redis初始化类型 单机standalone  哨兵sentinel  集群cluster\r\n    type: ${framework.redis.type:standalone}\r\n    #客户端类型 lettuce jedis\r\n    client-type: ${framework.redis.client-type:lettuce}\r\n    #分布式锁类型\r\n    lock-type: \r\n      redis: \r\n        enable: ${framework.redis.lock-type.redis.enable:true}\r\n      zk:\r\n        enable: ${framework.redis.lock-type.redis.enable:false}\r\n    #lettuce配置\r\n    lettuce:\r\n      pool:\r\n        enabled: ${framework.redis.lettuce.enabled:true}\r\n        max-active: ${framework.redis.lettuce.max-active:8}\r\n        max-idle: ${framework.redis.lettuce.max-idle:8}\r\n        min-idle: ${framework.redis.lettuce.min-idle:0}\r\n        max-wait: ${framework.redis.lettuce.max-wait:-1}\r\n    #布隆过滤器\r\n    bloom-filter:\r\n      #名称\r\n      name: ${framework.redis.bloom.name:cache_bloom_filter}\r\n      #每个元素的预期插入量\r\n      expectedInsertions: ${framework.redis.bloom.expectedInsertions:64000}\r\n      #预期错误概率\r\n      falseProbability: ${framework.redis.bloom.falseProbability:0.03}\r\n    #本地缓存配置\r\n    caffeine-cache:\r\n        name: ${framework.redis.caffeine.name}\r\n        initial-capacity: ${framework.redis.caffeine.initial-capacity:100}\r\n        maximum-size: ${framework.redis.caffeine.maximum-size:1024}', 'bbe792a7f318e257140bc37c03fa629d', '2022-12-13 05:31:32', '2022-12-13 05:31:32', NULL, '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (26, 'druid.yml', 'COMMON', 'spring:\r\n  datasource:\r\n    druid:\r\n      enable: true\r\n      reset_enable: true\r\n      db-type: mysql\r\n      initialSize: 10\r\n      minIdle: 10\r\n      maxActive: 500\r\n      max-wait: 60000\r\n      pool-prepared-statements: true\r\n      max-pool-prepared-statement-per-connection-size: 20\r\n      validation-query: SELECT 1 FROM DUAL\r\n      test-on-borrow: false\r\n      test-on-return: false\r\n      test-while-idle: true\r\n      #配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      time-between-eviction-runs-millis: 60000\r\n      #配置一个连接在池中最小生存的时间，单位是毫秒\r\n      min-evictable-idle-time-millis: 300000\r\n      filters: stat,wall\r\n      filter:\r\n        wall:\r\n          enabled: true\r\n          config:\r\n            commentAllow: true\r\n            multiStatementAllow: true\r\n            noneBaseStatementAllow: true\r\n      # StatViewServlet配置\r\n      web-stat-filter:\r\n        enabled: true\r\n        url-pattern: /*\r\n        exclusions: \"*.js , *.gif ,*.jpg ,*.png ,*.css ,*.ico , /druid/*\"\r\n        session-stat-max-count: 1000\r\n        profile-enable: true\r\n        session-stat-enable: false\r\n      # 配置DruidStatViewServlet\r\n      stat-view-servlet:\r\n        # 新版需要配置这个属性才能访问监控页面\r\n        enabled: true\r\n        url-pattern: /druid/*\r\n        #  禁用HTML页面上的“Reset All”功能\r\n        reset-enable: true\r\n        # 登录名\r\n        login-username: 979\r\n        # 登录密码\r\n        login-password: wsw979\r\n        # IP白名单(没有配置或者为空，则允许所有访问)\r\n        allow: 127.0.0.1\r\n        # IP黑名单 (存在共同时，deny优先于allow)\r\n        deny:', 'f06f47c765ce8feb670b924a7d1bd158', '2022-12-13 05:31:49', '2022-12-13 05:31:49', NULL, '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (27, 'elasticsearch.yml', 'COMMON', 'elasticsearch:\n    enabled: ${framework.elasticsearch.enabled:true}\n    uris: ${framework.elasticsearch.uris}\n    username: ${framework.elasticsearch.username}\n    password: ${framework.elasticsearch.password}\n    schema: ${framework.elasticsearch.schema:http}\n    connect-time-out: ${framework.elasticsearch.connect-time-out:3000}\n    socket-time-out: ${framework.elasticsearch.socket-time-out:5000}\n    connection-request-time-out: ${framework.elasticsearch.connection-request-time-out:500}\n    max-connect-num: ${framework.elasticsearch.max-connect-num:100}\n    max-connect-perRoute: ${framework.elasticsearch.max-connect-perRoute:100}', '15fccf0aba853aeab49dd0d839848bdb', '2022-12-13 05:32:07', '2022-12-13 05:32:40', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (28, 'executors.yml', 'COMMON', 'framework:\n    executors:\n        #核心线程数量\n        core-pool-size: ${framework.executors.core:5}\n        #线程池维护线程的最大数量\n        max-pool-size: ${framework.executors.max:10}\n        #队列最大长度\n        queue-capacity: ${framework.executors.queue:20}\n        #线程存活时间(单位：秒)\n        keep-alive-seconds: ${framework.executors.keep-alive:60}\n        #等待终止(单位：毫秒)\n        await-termination-millis: ${framework.executors.await-termination:5000}\n        #是否等待任务完成关机\n        wait-for-tasks-to-complete-on-shutdown: ${framework.executors.wait-on-shutdown:true}', '78b452017d066b11222d21a274645984', '2022-12-13 05:32:17', '2022-12-13 05:32:34', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (29, 'feign.yml', 'COMMON', 'feign:\n  enabled: ${framework.feign.enabled:true}\n  sentinel:\n    enabled: ${framework.feign.sentinel.enabled:false}\n  circuitbreaker:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      #默认的超时时间 可以在指定服务配置文件 指定单个Feign的超时时间 default改为@FeignClient(contextId = \"***\")\n      default:\n        connectTimeout: ${framework.feign.client.connect-timeout:8000}\n        readTimeout: ${framework.feign.client.read-timeout:12000}\n  compression:\n    request:\n      #开启请求压缩\n      enabled: true\n      #设置压缩的数据类型\n      mime-types: text/html,application/xml,application/json\n      #设置触发压缩的大小下限\n      min-request-size: 2048\n    response:\n      enabled: true\n\n#微服务客户端\nclient:\n  user: framework-cloud-user-api\n  platform: framework-cloud-platform-api\n  oauth: framework-cloud-oauth-api\n  pay: framework-cloud-pay-api', '9ade73d1564b8de31b3ff24344168139', '2022-12-13 05:32:29', '2022-12-26 11:04:36', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (32, 'mybatis.yml', 'COMMON', 'mybatisPlus:\n  #雪花ID \n  snowflake:\n    type: ${framework.mybatis.snowflake.type:cached}\n    time-bits: ${framework.mybatis.snowflake.time:32}\n    worker-bits: ${framework.mybatis.snowflake.worker:22}\n    seq-bits: ${framework.mybatis.snowflake.seq:9}\n    boost-power: ${framework.mybatis.snowflake.boost:3}\n  #Mapper 扫描路径\n  mapperScanner: ${framework.mybatis.mapperScanner:com.framework.cloud.**.infrastructure.mapper}\n  #Mapper Xml 扫描路径\n  mapperLocations: ${framework.mybatis.mapperXmlLocations:classpath*:mapper/*Mapper.xml}\n  #实体 扫描路径\n  typeAliasesPackage: ${framework.mybatis.typeAliasesPackage:com.framework.cloud.**.infrastructure.entity}\n  #转换器 扫描路径\n  typeHandlersPackage: ${framework.mybatis.typeHandlersPackage:com.framework.cloud.mybatis.hander}\n  #枚举 扫描路径\n  typeEnumsPackage: ${framework.mybatis.typeEnumsPackage:com.framework.cloud.**.common.enums}\n  #配置\n  configuration:\n    #二级缓存\n    cache-enabled: ${framework.mybatis.configuration.cache-enabled:true}\n    #延迟加载全局开关\n    lazyLoadingEnabled: ${framework.mybatis.configuration.lazyLoadingEnabled:false}\n    #任一方法的调用都会加载该对象的所有延迟加载属性\n    aggressiveLazyLoading: ${framework.mybatis.configuration.aggressiveLazyLoading:false}\n    #驼峰命名\n    map-underscore-to-camel-case: ${framework.mybatis.configuration.map-underscore-to-camel-case:true}\n    #结果集中值为 null 的时候是否调用映射对象的setter\n    call-setters-on-nulls: ${framework.mybatis.configuration.call-setters-on-nulls:true}\n    #允许单个语句返回多结果集\n    multipleResultSetsEnabled: ${framework.mybatis.configuration.multipleResultSetsEnabled:true}\n    #使用列标签代替列名\n    useColumnLabel: ${framework.mybatis.configuration.useColumnLabel:true}\n    #允许JDBC支持自动生成主键\n    useGeneratedKeys: ${framework.mybatis.configuration.multipleResultSetsEnabled:false}\n    #自动映射列到字段或属性。 NONE：关闭映射 PARTIAL：映射没有定义嵌套结果映射的字段 FULL：映射任何复杂的结果集\n    autoMappingBehavior: ${framework.mybatis.configuration.autoMappingBehavior:PARTIAL}\n    #自动映射列到字段或属性异常行为 NONE:不做任何反应 WARNING:输出警告日志 FAILING: 映射失败抛出异常\n    autoMappingUnknownColumnBehavior: ${framework.mybatis.configuration.autoMappingUnknownColumnBehavior:NONE}\n    #执行器 SIMPLE：默认 REUSE：重用预处理语句 BATCH：重用语句并且执行批量更新。\n    defaultExecutorType: ${framework.mybatis.configuration.defaultExecutorType:SIMPLE}\n    #超时时间（秒）\n    defaultStatementTimeout: ${framework.mybatis.configuration.defaultStatementTimeout:30}\n  global-config:\n    #banner\n    banner: ${framework.mybatis.global.banner:false}\n    #初始化SqlRunner\n    enable-sql-runner: ${framework.mybatis.global.enable-sql-runner:true}\n    db-config:\n      #主键类型\n      id-type: ${framework.mybatis.global.db.id-type:ASSIGN_ID}\n      #表名前缀\n      tablePrefix: ${framework.mybatis.global.db.tablePrefix}\n      #表名使用驼峰转下划线命名\n      table-underline: ${framework.mybatis.global.db.table-underline:true}\n      #字段验证策略\n      insert-strategy: NOT_EMPTY\n      #字段验证策略\n      update-strategy: NOT_EMPTY\n      #字段验证策略\n      select-strategy: NOT_EMPTY\n      #逻辑删除字段属性名\n      logicDeleteField: deleted\n      #逻辑已删除值\n      logic-delete-value: 0\n      #逻辑未删除值\n      logic-not-delete-value: 1', '2bbb42ad6d7c4e856a780eec6cabc4ab', '2022-12-13 05:32:56', '2023-01-09 07:08:46', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (33, 'rabbit.yml', 'COMMON', 'spring:\r\n  rabbitmq:\r\n    enable: ${framework.rabbitmq.enable:true}\r\n    host: ${framework.rabbitmq.host}\r\n    port: ${framework.rabbitmq.port}\r\n    username: ${framework.rabbitmq.username}\r\n    password: ${framework.rabbitmq.password}\r\n    virtual-host: ${framework.rabbitmq.virtual-host:/}\r\n    #是否启用【发布确认】\r\n    publisher-confirm-type: ${framework.rabbitmq.publisher-confirm-type:simple}\r\n    #是否启用 发布返回\r\n    publisher-returns: ${framework.rabbitmq.publisher-returns:true}\r\n    template:\r\n      retry:\r\n        max-attempts: ${framework.rabbitmq.retry.max-attempts:3}\r\n    listener:\r\n      type: ${framework.rabbitmq.listener.type:simple}\r\n      simple:\r\n        #手动ACK\r\n        acknowledge-mode: ${framework.rabbitmq.listener.simple.acknowledge-mode:MANUAL}\r\n        #最小的消费者数量\r\n        concurrency: ${framework.rabbitmq.listener.simple.concurrency:5}\r\n        #最大的消费者数量\r\n        max-concurrency: ${framework.rabbitmq.listener.simple.max-concurrency:20}\r\n      direct:\r\n        acknowledge-mode: ${framework.rabbitmq.listener.direct.acknowledge-mode:MANUAL}', 'e1c08dcf35601a4a745309f51f543d89', '2022-12-13 05:33:06', '2022-12-13 05:33:06', NULL, '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (34, 'seata.yml', 'COMMON', 'seata:\n  enabled: true\n  use-jdk-proxy: false\n  enable-auto-data-source-proxy: false\n  application-id: ${spring.application.name}\n  service:\n    enable-degrade: false\n    disable-global-transaction: false\n  config:\n    type: nacos\n    nacos:\n      namespace: 1146878a-9fec-4f49-947f-987f44bf5c78\n      server-addr: 127.0.0.1:8848\n      group: SEATA\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      group : SEATA\n      namespace: 1146878a-9fec-4f49-947f-987f44bf5c78\n      cluster: seata-cluster\n  client:\n    rm:\n      async-commit-buffer-limit: 10000\n      report-retry-count: 5\n      table-meta-check-enable: false\n      report-success-enable: false\n      saga-branch-register-enable: false\n      saga-json-parser: fastjson\n      saga-retry-persist-mode-update: false\n      saga-compensate-persist-mode-update: false\n      tcc-action-interceptor-order: -2147482648 #Ordered.HIGHEST_PRECEDENCE + 1000\n      sql-parser-type: druid\n      lock:\n        retry-interval: 10\n        retry-times: 30\n        retry-policy-branch-rollback-on-conflict: true\n    tm:\n      commit-retry-count: 5\n      rollback-retry-count: 5\n      default-global-transaction-timeout: 60000\n      degrade-check: false\n      degrade-check-period: 2000\n      degrade-check-allow-times: 10\n      interceptor-order: -2147482648 #Ordered.HIGHEST_PRECEDENCE + 1000\n    undo:\n      data-validation: true\n      log-serialization: jackson\n      log-table: undo_log\n      only-care-update-columns: true\n      compress:\n        enable: true\n        type: zip\n        threshold: 64k\n    load-balance:\n      type: RandomLoadBalance\n      virtual-nodes: 10\n  tcc:\n    fence:\n      log-table-name: tcc_fence_log\n      clean-period: 1h\n  transport:\n    shutdown:\n      wait: 3\n    thread-factory:\n      boss-thread-prefix: NettyBoss\n      worker-thread-prefix: NettyServerNIOWorker\n      server-executor-thread-prefix: NettyServerBizHandler\n      share-boss-worker: false\n      client-selector-thread-prefix: NettyClientSelector\n      client-selector-thread-size: 1\n      client-worker-thread-prefix: NettyClientWorkerThread\n      worker-thread-size: default\n      boss-thread-size: 1\n    type: TCP\n    server: NIO\n    heartbeat: true\n    serialization: seata\n    compressor: none\n    enable-tm-client-batch-send-request: false\n    enable-rm-client-batch-send-request: true\n    rpc-rm-request-timeout: 30000\n    rpc-tm-request-timeout: 30000', 'c6c94f3cf271da3218ed0a40d29498ba', '2022-12-13 05:33:18', '2022-12-13 07:14:04', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (35, 'swagger.yml', 'COMMON', '#增强模式\r\nknife4j:\r\n  enable: ${framework.knife4j.enabled:true}\r\n  basic:\r\n    enable: ${framework.knife4j.basic.enabled:false}\r\n    username: ${framework.knife4j.basic.username:979}\r\n    password: ${framework.knife4j.basic.password:wusiwei979}\r\n#文档\r\nframework:\r\n    swagger3:\r\n        #是否开启\r\n        enabled: ${framework.swagger.enabled:true}\r\n        #扫描路径\r\n        base-package: ${framework.swagger.base-package}\r\n        #页面标题\r\n        title: ${framework.swagger.title}\r\n        #描述\r\n        description: ${framework.swagger.description:文档}\r\n        #版本号\r\n        version: ${framework.swagger.version:v3}\r\n        #许可\r\n        license: ${framework.swagger.license:暂无}\r\n        #许可链接\r\n        license-url: ${framework.swagger.license-url:暂无}\r\n        #分组\r\n        group-name: ${framework.swagger.group-name}\r\n        #作者信息\r\n        contact:\r\n            #名称\r\n            name: ${framework.swagger.contact.name:979}\r\n            #链接\r\n            url: ${framework.swagger.contact.url:无}\r\n            #邮箱\r\n            email: ${framework.swagger.contact.email:18683789594@163.com}\r\n        #认证\r\n        authorization:\r\n            name: ${framework.swagger.authorization.name:Authorization}\r\n            auth-regex: ${framework.swagger.authorization.auth-regex:^.*$}\r\n', 'a125affa8ece0509c3801805b36fe06c', '2022-12-13 05:33:33', '2022-12-13 05:33:33', NULL, '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (36, 'xxl-job.yml', 'COMMON', 'xxl:\r\n  job:\r\n    #是否开启\r\n    enabled: ${framework.xxl-job.enabled:true}\r\n    #令牌\r\n    access-token: ${framework.xxl-job.access-token}\r\n    #调度中心地址 集群部署存在多个地址则用逗号分隔\r\n    admin-addresses: ${framework.xxl-job.admin-addresses}\r\n    #执行器\r\n    executor:\r\n      #执行器名称\r\n      app-name: ${framework.xxl-job.executor.app-name}\r\n      #自动注册不需要 地址 ip:port\r\n      address: ${framework.xxl-job.executor.address}\r\n      #自动注册不需要\r\n      ip: ${framework.xxl-job.executor.ip}\r\n      #端口\r\n      port: ${framework.xxl-job.executor.port}\r\n      #执行器运行日志文件存储的磁盘位置\r\n      log-path: ${framework.xxl-job.executor.job.log-path:/data/xxl-job/logs}\r\n      #执行器Log文件定期清理功能，指定日志保存天数\r\n      log-retention-days: ${framework.xxl-job.executor.job.log-retention-days:30}', '0b9e42d04c15e7dca26f87cdf639851a', '2022-12-13 05:33:44', '2022-12-13 05:33:44', NULL, '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (42, 'seata-server.properties', 'SEATA', 'transport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableTmClientBatchSendRequest=false\ntransport.enableRmClientBatchSendRequest=true\ntransport.enableTcServerBatchSendResponse=false\ntransport.rpcRmRequestTimeout=30000\ntransport.rpcTmRequestTimeout=30000\ntransport.rpcTcRequestTimeout=30000\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\n\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\n\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=true\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.rm.sagaJsonParser=fastjson\nclient.rm.tccActionInterceptorOrder=-2147482648\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.tm.interceptorOrder=-2147482648\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nserver.undo.logSaveDays=7\nserver.undo.logDeletePeriod=86400000\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\n\ntcc.fence.logTableName=tcc_fence_log\ntcc.fence.cleanPeriod=1h\n\nlog.exceptionRate=100\n\nstore.mode=db\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.jdbc.Driver\nstore.db.url=jdbc:mysql://127.0.0.1:3306/framework-seata?characterEncoding=utf8&useSSL=false&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=Asia/Shanghai\nstore.db.user=dev\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=30\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.lockTable=lock_table\nstore.db.maxWait=5000\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\nserver.distributedLockExpireTime=10000\nserver.xaerNotaRetryTimeout=60000\nserver.session.branchAsyncQueueSize=5000\nserver.session.enableBranchAsyncRemove=false\nserver.enableParallelRequestHandle=false\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898', '6ad4cf5154be9b1fad5b8f608e76bdf1', '2022-12-13 06:16:08', '2022-12-29 03:50:34', 'nacos', '0:0:0:0:0:0:0:1', '', '1146878a-9fec-4f49-947f-987f44bf5c78', '', '', '', 'properties', '');
INSERT INTO `config_info` VALUES (44, 'service.vgroupMapping.platform-group', 'SEATA', 'seata-cluster', '36795b6bcb77cf82c08904b91caa4c41', '2022-12-13 06:16:34', '2022-12-13 06:16:34', NULL, '0:0:0:0:0:0:0:1', '', '1146878a-9fec-4f49-947f-987f44bf5c78', NULL, NULL, NULL, 'text', NULL);
INSERT INTO `config_info` VALUES (45, 'service.vgroupMapping.user-group', 'SEATA', 'seata-cluster', '36795b6bcb77cf82c08904b91caa4c41', '2022-12-13 06:16:44', '2022-12-13 06:16:44', NULL, '0:0:0:0:0:0:0:1', '', '1146878a-9fec-4f49-947f-987f44bf5c78', NULL, NULL, NULL, 'text', NULL);
INSERT INTO `config_info` VALUES (46, 'service.vgroupMapping.pay-group', 'SEATA', 'seata-cluster', '36795b6bcb77cf82c08904b91caa4c41', '2022-12-26 09:17:22', '2022-12-26 09:17:31', 'nacos', '0:0:0:0:0:0:0:1', '', '1146878a-9fec-4f49-947f-987f44bf5c78', '', '', '', 'text', '');
INSERT INTO `config_info` VALUES (76, 'framework-cloud-user.yml', 'BASIC', 'framework:\n  swagger:\n    title: 用户\n    base-package: com.framework.cloud.user.api.controller\n    group-name: user\n  mybatis:\n    mapperScanner: com.framework.cloud.user.infrastructure.mapper\n    tenant:\n      is-open: true\n      ignore-table-names: \n        - t_permission\n  redis:\n    prefix: user\n    host: 127.0.0.1\n    password: 123456\n  elasticsearch:\n    uris: 127.0.0.1:9200,127.0.0.1:9201\n    username: elastic\n    password: 123456\nseata:\n  tx-service-group: user-group\n  service:\n    vgroup-mapping:\n      user-group: seata-cluster\n\nspring:\n  shardingsphere:\n    props:\n      sql-show: true\n    datasource:\n      names: user\n      user:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.jdbc.Driver\n        url: jdbc:mysql://127.0.0.1:3306/framework-user?characterEncoding=utf8&useSSL=false&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=Asia/Shanghai\n        username: dev\n        password: 123456\n    #分片规则 https://blog.csdn.net/qq_34279995/article/details/122421989\n    rules:\n      sharding:\n        tables:\n          t_user:\n            actualDataNodes: user.t_user_$->{0..1}\n            tableStrategy:\n              standard:\n                #分片策略\n                shardingColumn: id\n                #分片策略名称\n                shardingAlgorithmName: user_id\n          t_user_auth:\n            actualDataNodes: user.t_user_auth_$->{0..5}\n            tableStrategy:\n              standard:\n                shardingColumn: user_id\n                shardingAlgorithmName: user_auth_user_id\n        #分片策略\n        sharding-algorithms:\n          user_id:\n            #hash取模\n            type: HASH_MOD\n            props:\n              #分片数量\n              sharding-count: 2\n          #策略名称\n          user_auth_user_id:\n            #采用哪种类型\n            type: HASH_MOD\n            props:\n              sharding-count: 6\n      encrypt:\n        query-with-cipher-column: true\n        encryptors:\n          encryptor_md5:\n            type: md5\n            props:\n              md5-key-value: 979\n          encryptor_aes:\n            type: aes\n            props:\n              aes-key-value: 979\n        tables:\n          t_user:\n            columns:\n              email:\n                cipherColumn: email\n                encryptorName: encryptor_aes\n              username:\n                cipherColumn: username\n                encryptorName: encryptor_aes\n              mobile:\n                cipherColumn: mobile\n                encryptorName: encryptor_aes\n          t_user_auth:\n            columns:\n              identifier:\n                cipherColumn: identifier\n                encryptorName: encryptor_aes\n              credential:\n                cipherColumn: credential\n                encryptorName: encryptor_md5', '3a36d93d1388f3e5cf561bb2570fd084', '2022-12-13 05:35:24', '2022-12-13 06:11:12', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (77, 'framework-cloud-platform.yml', 'BASIC', 'framework:\n  swagger:\n    title: 平台\n    base-package: com.framework.cloud.platform.api.controller\n    group-name: platform\n  mybatis:\n    mapperScanner: com.framework.cloud.platform.infrastructure.mapper\n    tenant:\n      is-open: true\n      ignore-table-names: \n        - t_tenant\n        - t_gateway_route\n        - t_pay_channel\n        - t_pay_mode\n  redis:\n    prefix: platform\n    host: 127.0.0.1\n    password: 123456\n  rabbitmq:\n    host: 127.0.0.1\n    port: 5672\n    username: dev\n    password: 123456\n    virtual-host: /\nseata:\n  tx-service-group: platform-group\n  service:\n    vgroup-mapping:\n      platform-group: seata-cluster\nspring:\n  shardingsphere:\n    props:\n      sql-show: true\n    datasource:\n      names: platform\n      platform:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.jdbc.Driver\n        url: jdbc:mysql://127.0.0.1:3306/framework-platform?characterEncoding=utf8&useSSL=false&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=Asia/Shanghai\n        username: dev\n        password: 123456\n    rules:\n      sharding:\n        #不分库分表 none\n        default-table-strategy:\n          none: none\n      encrypt:\n        query-with-cipher-column: true\n        encryptors:\n          encryptor_md5:\n            type: md5\n            props:\n              md5-key-value: 979\n        tables:\n          t_tenant:\n            columns:\n              secret:\n                cipherColumn: secret\n                encryptorName: encryptor_md5\n  cloud:\n    stream:\n      bindings:\n        gateway-route-channel:\n          destination: gateway-route\n          content-type: application/json\n          group: ${spring.application.name}\n          consumer:\n            concurrency: 4\n        gateway-route-delete-channel:\n          destination: gateway-route-delete\n          content-type: application/json\n          group: ${spring.application.name}\n          consumer:\n            max-attempts: 1\n            concurrency: 4\n      binders:\n        rabbit:\n          type: rabbit', '10b55c721785614dd1093dc869a73ab0', '2022-12-13 07:56:03', '2022-12-13 10:45:08', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (78, 'framework-cloud-oauth.yml', 'BASIC', 'framework:\n  swagger:\n    title: 授权\n    base-package: com.framework.cloud.oauth.api.controller\n    group-name: oauth\n  redis:\n    prefix: authentication\n    host: 127.0.0.1\n    password: 123456\n  oauth2:\n    #租户最大认证次数\n    max-count: 1000\n    url:\n      cors-pattern: /**\n      logout-url: /oauth/logout\n      ignoring-url:\n        - /oauth/**\n\n\n    jwt:\n      #签名文件\n      key-path: classpath:oauth-servce.jks\n      #签名别名\n      alias: 979\n      #签名密码\n      password: wsw979', 'e1f11f8605f8e60e840d6f33de9f2eab', '2022-12-13 07:58:06', '2022-12-27 05:45:06', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (79, 'framework-cloud-gateway.yml', 'BASIC', 'framework:\n  gateway:\n    version: v1\n    public-key: public-key.txt\n    ignored-url:\n      - /webjars/**\n      - /swagger-resources/**\n      - /*/*/v3/api-docs\n      - /doc.html\n      - /api/oauth/**\n      - /api/*/open/**\n\n  redis:\n    prefix: authentication\n    host: 127.0.0.1\n    password: 123456\n  rabbitmq:\n    host: 127.0.0.1\n    port: 5672\n    username: dev\n    password: 123456\n    virtual-host: /\n  swagger:\n    docs-path: /v3/api-docs\n    version: 3.0\n    enable-group: true\n    ignore-routes:\n      - oauth\nspring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          # 根据注册中心自动注册路由，默认false\n          enabled: false\n          # 将注册表中的服务名转为小写创建路由，默认false\n          lower-case-service-id: true\n      #http连接设置\n      httpclient:\n        pool:\n          #最大连接数\n          max-connections: 5000\n          #返回时间\n          acquire-timeout: 15000\n          #最大空闲时间\n          max-idle-time: 10000\n    stream:\n      bindings:\n        gateway-route-channel:\n          destination: gateway-route\n          content-type: application/json\n          group: ${spring.application.name}\n        gateway-route-delete-channel:\n          destination: gateway-route-delete\n          content-type: application/json\n          group: ${spring.application.name}\n      binders:\n        rabbit:\n          type: rabbit', 'a98cea00e89bff97d6af139f96cbae2b', '2022-12-13 07:58:20', '2022-12-13 09:45:04', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (80, 'framework-cloud-pay.yml', 'BASIC', 'framework:\n  swagger:\n    title: 支付\n    basePackage: com.framework.cloud.pay.api.controller\n  mybatis:\n    tenant:\n      is-open: true\n  redis:\n    prefix: pay\n    host: 127.0.0.1\n    password: 123456\n  rabbitmq:\n    host: 127.0.0.1\n    port: 5672\n    username: dev\n    password: 123456\n    virtual-host: /\n  stream:\n    delay:\n      #1800000\n      pay-order-output-channel: 2000\nseata:\n  tx-service-group: pay-group\n  service:\n    vgroup-mapping:\n      pay-group: seata-cluster\nspring:\n  shardingsphere:\n    props:\n      sql-show: true\n    datasource:\n      names: pay\n      pay:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.jdbc.Driver\n        url: jdbc:mysql://127.0.0.1:3306/framework-pay?characterEncoding=utf8&useSSL=false&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=Asia/Shanghai\n        username: dev\n        password: 123456\n    rules:\n      sharding:\n        tables:\n          t_pay_order:\n            #定义表表名区间\n            actualDataNodes: pay.t_pay_order_$->{2022..2023}_0$->{1..9},pay.t_pay_order_$->{2022..2023}_$->{10..12}\n            #分片算法\n            tableStrategy:\n              standard:\n                #分片键\n                shardingColumn: create_time\n                #分片策略\n                shardingAlgorithmName: order_create_time\n          t_pay_notify:\n            actualDataNodes: pay.t_pay_notify_$->{0..9}\n            #分片算法\n            tableStrategy:\n              standard:\n                #分片键\n                shardingColumn: pay_order_id\n                #分片策略\n                shardingAlgorithmName: notify_pay_order_id\n        #分片策略\n        sharding-algorithms:\n          #策略名称\n          order_create_time:\n            #采用哪种类型\n            type: CLASS_BASED\n            props:\n              #STANDARD、COMPLEX、HINT （org.apache.shardingsphere.sharding.algorithm.sharding.classbased.ClassBasedShardingAlgorithmStrategyType）\n              strategy: STANDARD\n              algorithmClassName: com.framework.cloud.pay.api.application.shading.OrderTimePreciseShardingAlgorithm\n          #支付回调id分片名称\n          notify_pay_order_id:\n            #内嵌\n            type: INLINE\n            props:\n              algorithm-expression: t_pay_notify_$->{pay_order_id % 10}\n  cloud:\n    stream:\n      bindings:\n        pay-order-output-channel:\n          destination: pay-order-delay-exchange\n          content-type: application/json\n          group: ${spring.application.name}\n          producer:\n            required-groups: pay-order\n        pay-order-input-channel:\n          destination: pay-order-delay-exchange\n          content-type: application/json\n          group: ${spring.application.name}\n      binders:\n        rabbit:\n          type: rabbit\n      rabbit:\n        bindings:\n          pay-order-output-channel:\n            producer:\n              delayed-exchange: true\n              #消息ms之后还没被消费，就会成为死信，这个参数生效的前提是spring.cloud.stream.bindings里边声明了producer.requiredGroups\n              ttl: 120000\n              #开启死信\n              auto-bind-dlq: true\n              #dead-letter-exchange: pay-order-dead-exchange\n              #dead-letter-queueName: pay-order-dead-queue.${spring.application.name}\n          pay-order-input-channel:\n            consumer:\n              delayed-exchange: true', '47d0cffa9097bec571c6a86c10d2fb8e', '2022-12-26 09:19:51', '2023-01-13 03:36:34', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (96, 'framework-cloud-file.yml', 'BASIC', 'framework:\n  swagger:\n    title: 文件\n    basePackage: com.framework.cloud.file.api.controller\n  mybatis:\n    tenant:\n      is-open: true\n  redis:\n    prefix: file\n    host: 127.0.0.1\n    password: 123456\n  rabbitmq:\n    host: 127.0.0.1\n    port: 5672\n    username: dev\n    password: 123456\n    virtual-host: /\n  file:\n    env: OFFLINE\n    prefix: D:/upload/\n    max-size: 524288000\n    oss:\n      endpoint: 111111\n      access-key: 2222222222222222\n      access-secret: 222222222222222222222222\n      bucket-name: 222222222222\n      maxKeys: 3\n    not-allow-extension:\n      - .exe\n      - .msi\nspring:\n  shardingsphere:\n    props:\n      sql-show: true\n    datasource:\n      names: file\n      file:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.jdbc.Driver\n        url: jdbc:mysql://127.0.0.1:3306/framework-file?characterEncoding=utf8&useSSL=false&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=Asia/Shanghai\n        username: dev\n        password: 123456\n    rules:\n      sharding:\n        tables:\n          t_file:\n            #定义表表名区间\n            actualDataNodes: file.t_file_$->{2022..2023}_0$->{1..9},file.t_file_$->{2022..2023}_$->{10..12}\n            #分片算法\n            tableStrategy:\n              standard:\n                #分片键\n                shardingColumn: create_time\n                #分片策略\n                shardingAlgorithmName: file_create_time\n        #分片策略\n        sharding-algorithms:\n          #策略名称\n          file_create_time:\n            #采用哪种类型\n            type: CLASS_BASED\n            props:\n              #STANDARD、COMPLEX、HINT （org.apache.shardingsphere.sharding.algorithm.sharding.classbased.ClassBasedShardingAlgorithmStrategyType）\n              strategy: STANDARD\n              algorithmClassName: com.framework.cloud.file.api.application.shading.FileTimePreciseShardingAlgorithm', '88977086c5aab5de3c82c6f0364085b0', '2022-12-27 08:51:18', '2022-12-28 05:09:15', 'nacos', '0:0:0:0:0:0:0:1', '', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', '', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('ROLE_USER', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816:*:*', 'rw');
INSERT INTO `permissions` VALUES ('ROLE_USER', '1146878a-9fec-4f49-947f-987f44bf5c78:*:*', 'rw');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('979', 'ROLE_USER');
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (3, '1', '0e5ea9ed-691f-4059-b8cc-bac9f1ddc816', 'framework', 'work', 'nacos', 1670909435040, 1670909435040);
INSERT INTO `tenant_info` VALUES (4, '1', '1146878a-9fec-4f49-947f-987f44bf5c78', 'seata', 'work', 'nacos', 1670909443352, 1670909443352);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('979', '$2a$10$sRTG7dFFvnuxYLXEoijanOkrrabyyDXpzjJliEbySJBK.5J7qgQR2', 1);
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
