INSERT INTO `platform` VALUES ('bank_points', '银行积分', '1', '2015-6-11 14:41:54', '2015-6-11 14:41:58');
INSERT INTO `platform` VALUES ('front', '前端', '1', '2015-6-11 14:43:18', '2015-6-11 14:43:21');
INSERT INTO `platform` VALUES ('points', '积分系统', '1', '2015-6-11 14:43:18', '2015-6-11 14:43:21');
INSERT INTO `platform` VALUES ('pay', '银行现金', '1', '2015-6-11 14:43:18', '2015-6-11 14:43:21');
INSERT INTO `platform` VALUES ('partner', '商户', '1', '2015-6-11 14:43:18', '2015-6-11 14:43:21');


INSERT INTO `RCKFLOW` VALUES ('RECON', 2001, 'loadDataRedisFlow', 'N', 'Y', 'N', 'Y', '前端数据放到redis', 'front');
INSERT INTO `RCKFLOW` VALUES ('RECON', 2002, 'loadDataRedisFlow', 'N', 'Y', 'N', 'Y', '积分系统数据放到redis', 'points');
INSERT INTO `RCKFLOW` VALUES ('RECON', 2003, 'loadDataRedisFlow', 'N', 'Y', 'N', 'Y', '银行积分数据放到redis', 'bank_points');
INSERT INTO `RCKFLOW` VALUES ('RECON', 2004, 'loadDataRedisFlow', 'N', 'Y', 'N', 'Y', '银行现金数据放到redis', 'pay');
INSERT INTO `RCKFLOW` VALUES ('RECON', 2005, 'loadDataRedisFlow', 'N', 'Y', 'N', 'Y', '供应商数据放到redis', 'partner');

INSERT INTO `RCKFLOW` VALUES ('RECON', 3001, 'tradeResultFlow', 'N', 'Y', 'N', 'Y', '求交集入结果集表', '');
INSERT INTO `RCKFLOW` VALUES ('RECON', 4001, 'tradeResultSeparateFlow', 'N', 'Y', 'Y', 'Y', '结果集分离', '');
INSERT INTO `RCKFLOW` VALUES ('RECON', 5001, 'dataAfterFlow', 'N', 'Y', 'N', 'Y', '结果集处理后的数据处理', '');


INSERT INTO `platform_detail` VALUES ('10000001', '安徽银行', 'bank_points', '1', 'i_bank_data_anhui', 'order_no', '0', '664', '1', '2015-6-11 14:54:46', '2015-6-11 14:54:49');
INSERT INTO `platform_detail` VALUES ('10000002', '台州银行', 'bank_points', '1', 'i_bank_data_taizhou', 'order_no', '0', '690', '1', '2015-6-16 10:33:23', '2015-6-16 10:33:28');
INSERT INTO `platform_detail` VALUES ('10000003', '德州银行', 'bank_points', '1', 'i_bank_data_dezhou', 'order_no', '0', '623', '1', '2015-6-18 10:47:17', '2015-6-18 10:47:21');
INSERT INTO `platform_detail` VALUES ('10000004', '新会农商银行', 'bank_points	', '1', 'i_bank_data_xinhui', 'order_no', '0', '612', '1', '2015-6-18 19:56:25', '2015-6-18 19:56:29');
INSERT INTO `platform_detail` VALUES ('30000001', '积分系统', 'points', '1', 'i_points', 'order_no', '0', '100010002', '1', '2015-6-18 10:38:21', '2015-6-18 10:38:25');




insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1020','tradeType','银行积分支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1080','tradeType','银行积分赠送','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1040','tradeType','银行积分退分','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1120','tradeType','联盟积分消费','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1140','tradeType','联盟积分退分','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1180','tradeType','联盟积分赠送','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1160','tradeType','联盟积分充值','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1142','tradeType','联盟积分转增','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2020','tradeType','贴膜卡支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2051','tradeType','快捷支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2050','tradeType','网银支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2053','tradeType','支付宝支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2040','tradeType','退款','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2055','tradeType','即时支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2030','tradeType','手机客户端支付','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('2010','tradeType','银行积分+现金','y');
insert into t_diractionary (diractionary_no,type_no,diractionary_name,status) VALUES('1121','tradeType','联盟积分现金','y');
