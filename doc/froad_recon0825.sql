



--alter table froad_recon.s_trade_result add virtual_trade_type varchar(50);
/***
INSERT INTO `froad_recon`.`platform_detail` (`platform_detail_no`, `platform_detail_name`, `platform_no`, `status`, `table_name`, `table_field`, `chanel_type`, `channel_no`, `flag`, `create_time`, `update_time`, `file_bean`, `file_url`, `file_param`, `file_type`, `local_file_path`, `file_read_bean`, `rule_file_name`, `check_data_bean`, `data_deal_bean`, `file_status`) VALUES 
('partnerOfcard', '供应商殴飞', 'partner', '1', 'i_partner_ofcard', 'order_no', null, 'ofcard', '1', '2015-07-22 15:06:45', '2015-07-22 15:06:45', 'fileGetProcessorHttpTxt', '', NULL, NULL, 'ofcard%s.csv', 'fileAnalysisProcessorSplit', 'ofcard.srl', 'fileCheckProcessorNo', 'dataDealProcessorOfcard', '1');

INSERT INTO `froad_recon`.`RCKFLOW` (`RCKTYPE`, `RCKORDER`, `BEANID`, `INITSTATE`, `ALOWCHANGE`, `EXPCONT`, `SINGLEEXT`, `REMARK`, `MSG`) VALUES
 ('RECON', '14', 'fileHandlerFlow', 'Y', 'Y', 'Y', 'Y', '殴飞话费供应商', 'partnerOfcard');

create table froad_recon.i_partner_ofcard
(
order_no		varchar(50)	not null	comment '订单编号',
order_time		varchar(50)				comment '订单时间',
business_type varchar(50)    comment '业务类型', 
income_money  decimal(16,4)  comment '收入金额',
expend_money  decimal(16,4)  comment '支出金额', 
recon_date			char(8)					comment	'对账日期',
remark      varchar(50)  comment	'备注',
create_time  datetime				comment '创建时间',
primary key (order_no)
);
alter table froad_recon.i_partner_ofcard comment '供应商殴飞表';
CREATE UNIQUE INDEX  idx_ipo_order_no_date ON froad_recon.i_partner_ofcard(order_no,recon_date);
CREATE  INDEX  idx_ip	o_order_no ON froad_recon.i_partner_ofcard(order_no);
CREATE  INDEX  idx_ipo_recon_date ON froad_recon.i_partner_ofcard(recon_date);




drop INDEX idx_snr_order_no_date on froad_recon.s_no_recon ;
drop INDEX idx_sd_order_no_date  on froad_recon.s_delay;
CREATE UNIQUE INDEX  idx_snr_order_no_date ON froad_recon.s_no_recon(order_no, recon_date, platform_no);
CREATE UNIQUE INDEX  idx_sd_order_no_date ON froad_recon.s_delay(order_no, recon_date, platform_no);


alter table froad_recon.s_delay add business_type varchar(50)  COMMENT '业务类型refund=退款，trade=交易' ;
alter table froad_recon.s_delay add seq_no varchar(50)  COMMENT '退款流水号' ;
alter table froad_recon.s_delay add recon_status varchar(50)  COMMENT 'noBiil=未获取账单号,order_no存的退款流水号,noRecon=未对账（已有账单号）,reconed=已对账' ;
alter table froad_recon.s_delay add channel_no varchar(50)  COMMENT '前端渠道标示' ;


alter table froad_recon.s_trade_result add import_date varchar(50)  COMMENT '导入日期，用于退款延迟对账';





alter table froad_recon.platform_detail add is_common_table varchar(50) COMMENT '是否公用表,1=公用表， 0=不共用';
update  froad_recon.platform_detail  set is_common_table='1';


CREATE TABLE `i_bank_points` (
`order_no`  varchar(50)   NOT NULL COMMENT '订单编号' ,
`order_time`  datetime  COMMENT '订单时间' ,
`order_type`  varchar(20)    COMMENT '订单类型' ,
`org_order_no`  varchar(50)    COMMENT '原订单号' ,
`user_id`  varchar(50)    COMMENT '用户标识' ,
`points`  decimal(16,4)  COMMENT '积分' ,
`bank_order_no`  varchar(50)    COMMENT '积分平台订单编号' ,
`blance`  decimal(16,4)  COMMENT '余额' ,
`recon_date`  char(8)    COMMENT '对账日期' ,
`channel_no`  char(20)    COMMENT '渠道' ,
`status`  varchar(10)    COMMENT '状态码' ,
PRIMARY KEY (`order_no`)
)
COMMENT='积分表'
;

CREATE UNIQUE INDEX  idx_ibp_order_no_date ON froad_recon.i_bank_points(order_no, recon_date, channel_no);
CREATE  INDEX  idx_ibp_order_no ON froad_recon.i_bank_points(order_no);
CREATE  INDEX  idx_ibp_date ON froad_recon.i_bank_points(recon_date);

*
****/


