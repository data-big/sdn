-- ----------------------------
-- VPN卡信息表
-- ----------------------------
DROP TABLE IF EXISTS `vpn_card`;
CREATE TABLE `vpn_card` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `realNumber` bigint(12) NOT NULL COMMENT '真实号',
  `bizIP` varchar(16) NOT NULL COMMENT '业务IP',
  `stopIP` varchar(16) NOT NULL COMMENT '停用IP',
  `specialIP` varchar(16) NOT NULL COMMENT '特殊IP',
  `offsetBizIP` varchar(16) NOT NULL COMMENT '偏移后业务IP',
  `offsetStopIP` varchar(16) NOT NULL COMMENT '偏移后停机IP',
  `offsetSpecialIP` varchar(16) NOT NULL COMMENT '偏移后特殊IP',
  `invalid` bit(1) NOT NULL DEFAULT b'0' COMMENT '状态：0（有效）1（过期）',
  `insertDate` datetime NOT NULL COMMENT '入库时间：2016-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- VPN用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `vpn_user`;
CREATE TABLE `vpn_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `realNumber` bigint(12) NOT NULL COMMENT '真实号',
  `sponsorNumber` varchar(16) NOT NULL COMMENT '担保人手机号',
  `sponsorName` varchar(64) NOT NULL COMMENT '担保人姓名',
  `sponsorIDType` tinyint(2) NOT NULL COMMENT '担保人证件类型：参考数据1，2，3...',
  `sponsorIDNumber` varchar(32) NOT NULL COMMENT '担保人证件号码：参考数据342622XXXXXXXX8888或其它',
  `userNumber` varchar(16) DEFAULT NULL COMMENT '使用人手机号',
  `userName` varchar(64) NOT NULL COMMENT '使用人姓名',
  `userIDType` tinyint(2) NOT NULL COMMENT '使用人证件类型：参考数据1，2，3...',
  `userIDNumber` varchar(32) NOT NULL COMMENT '使用人证件号码：参考数据342622XXXXXXXX8888或其它',
  `registerDate` datetime NOT NULL COMMENT '开户时间：2016-01-01 00:00:00',
  `cancelDate` datetime NOT NULL COMMENT '销户时间：2016-01-01 00:00:00',
  `registerAgent` varchar(64) DEFAULT NULL COMMENT '开户代理商',
  `modifyType` tinyint(2) NOT NULL COMMENT '变更类型：1.实名信息变更 2.IP地址变更 3.销户',
  `modifyDate` datetime NOT NULL COMMENT '变更时间：2016-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
-- ----------------------------
-- VPN用户地理位置信息表（该表仅做字段参考，存储采用Hbase和OpenTSDB）
-- ----------------------------
DROP TABLE IF EXISTS `vpn_position`;
CREATE TABLE `vpn_position` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `realNumber` bigint(12) NOT NULL COMMENT '真实号',
  `sac` varchar(16) NOT NULL COMMENT '基站SAC信息或CELLID信息：参考数据34162',
  `lac` varchar(16) NOT NULL COMMENT '基站LAC信息：参考数据25840',
  `time` datetime NOT NULL COMMENT '时间：2016-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


