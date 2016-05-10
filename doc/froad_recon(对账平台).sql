

/*==============================================================*/
/* Table: i_bank_data_taizhou                                   */
/*==============================================================*/
create table i_bank_data_taizhou
(
order_no		varchar(50)	not null	comment '订单编号',
order_time		datetime				comment '订单时间',
order_type		varchar(2)				comment '订单类型',
org_order_no	varchar(50)				comment '原订单号',
user_id			varchar(50)				comment '用户标识',
points			decimal(16,4)			comment '积分',
bank_order_no	varchar(50)				comment '银行订单编号',
status			char(4)					comment '状态',
recon_date			char(8)					comment	'对账日期',
primary key (order_no)
);

alter table i_bank_data_taizhou comment '台州银行对账数据表';


/*==============================================================*/
/* Table: i_bank_data_anhui                                   */
/*==============================================================*/
create table i_bank_data_anhui
(
order_no		varchar(50)	not null 	comment '订单编号',
order_time		datetime				comment	'订单时间',
order_type		varchar(2)				comment	'订单类型',
org_order_no	varchar(50)				comment	'原订单号',
user_id			varchar(50)				comment	'用户标识',
points			decimal(16,4)			comment	'积分',
bank_order_no	varchar(50)				comment	'积分平台订单编号',
blance			decimal(4)				comment	'余额',
recon_date			char(8)					comment	'对账日期',
primary key (order_no)
);

alter table i_bank_data_anhui comment '安徽银行对账数据表';

/*==============================================================*/
/* Table: i_bank_data_dezhou                                   */
/*==============================================================*/
create table i_bank_data_dezhou
(
order_no		varchar(50)	not null 	comment '订单编号',
order_time		datetime				comment	'订单时间',
order_type		varchar(2)				comment	'订单类型',
org_order_no	varchar(50)				comment	'原订单号',
user_id			varchar(50)				comment	'用户标识',
points			decimal(16,4)			comment	'积分',
bank_order_no	varchar(50)				comment	'积分平台订单编号',
recon_date			char(8)					comment	'对账日期',
primary key (order_no)
);

alter table i_bank_data_dezhou comment '德州银行对账数据表';

/*==============================================================*/
/* Table: i_bank_data_xinhui                                   */
/*==============================================================*/
create table i_bank_data_xinhui
(
order_no		varchar(50)	not null 	comment '订单编号',
order_time		datetime				comment	'订单时间',
order_type		varchar(2)				comment	'订单类型',
org_order_no	varchar(50)				comment	'原订单号',
user_id			varchar(50)				comment	'用户标识',
points			decimal(16,4)			comment	'积分',
bank_order_no	varchar(50)				comment	'积分平台订单编号',
recon_date			char(8)					comment	'对账日期',
primary key (order_no)
);

alter table i_bank_data_xinhui comment '新会农商银行对账数据表';

/*==============================================================*/
/* Table: i_bank_data_wuhan                                   */
/*==============================================================*/
create table i_bank_data_wuhan
(
order_no		varchar(50)	not null	comment	'交易码',
org_no			varchar(50)				comment	'机构编号',
order_time		datetime				comment	'日期',
card_no			varchar(50)				comment	'卡号',
account_no		varchar(50)				comment	'账户编号',
points_type		char(1)					comment	'积分调整类型',
points			decimal(16,4)			comment	'积分',
org_order_no	varchar(50)				comment	'交易参考号',
recon_date			char(8)					comment	'对账日期',
primary key (order_no)
);

alter table i_bank_data_wuhang comment '武汉银行对账数据表';


/*==============================================================*/
/* Table: i_front_trade                                   */
/*==============================================================*/
create table i_front_trade
(
id					varchar(32)	not null	comment	'主键',
request_no			varchar(50)				comment	'请求流水号',
order_no			varchar(50)				comment	'账单号',
trade_money			decimal(16,4)			comment	'交易额',
order_time			datetime				comment	'订单时间',
chanel_type			char(1)					comment	'渠道类别',
transfer_type		varchar(50)				comment	'交易类型',
front_partner_no 	varchar(50)				comment	'前端平台号',
supplier_no			varchar(50)				comment	'供应商编号',
point_bank_group 	varchar(50)				comment	'积分银行组号',
back_group			varchar(50)				comment	'现金银行组号',
point_org			varchar(10)				comment	'积分机构号',
result_code			char(4)					comment	'结果代码',
create_time			datetime				comment	'创建时间',
member_id			varchar(50)				comment	'会员标识',
recon_date			char(8)					comment	'对账日期',
primary key (id)
);

alter table i_front_trade comment '前端对账数据表';


/*==============================================================*/
/* Table: i_points                                   */
/*==============================================================*/
create table i_points
(
transfer_id			varchar(50)	not null	comment	'交易id',
Transfer_type		varchar(50)				comment	'交易类别',
request_no			varchar(50)				comment	'请求流水号',
partner_no			varchar(50)				comment	'商户编号',
points				decimal(16,4)			comment	'积分额',
channel_type			char(1)					comment	'渠道类别	0 积分',
channel_no			varchar(20)				comment	'支付渠道编号,指银行组号或机构号',
order_time			datetime  				comment	'订单时间',
create_time			datetime  				comment	'创建时间',
member_id			varchar(50)				comment	'会员标识',
recon_date			char(8)					comment	'对账日期',
primary key (transfer_id)
);

alter table i_points comment '积分对账数据表';


/*==============================================================*/
/* Table: platform                                   */
/*==============================================================*/
create table platform
(
platform_no			varchar(50)not null	comment	'平台编号PK',
platform_name		varchar(50)			comment	'平台名称',
status				char(1)				comment	'是否对账状态	1=是0=否',
create_time	datetime	  				comment	'创建时间',
update_time	datetime	  				comment	'更新时间',
primary key (platform_no)
);

alter table platform comment '平台表';


/*==============================================================*/
/* Table: platform_detail                                   */
/*==============================================================*/
create table platform_detail
(
platform_detail_no		varchar(50)not null	comment	'平台明细编号PK',
platform_detail_name	varchar(50)			comment	'平台明细名称',
platform_no				varchar(50)			comment	'平台编号',
status					char(1)				comment	'是否参与对账	1.是0.否',
file_url				varchar(200)		comment	'对账文件地址',
table_name				varchar(256)		comment	'对账表名',
table_field				varchar(256)		comment	'对账字段		 ',
chanel_type				char(1)				comment	'渠道类别	0 积分 1 现金',
channel_no				varchar(20)			comment	'渠道编号	指银行组号或机构号',
flag					char(1)				comment	'是否必须对账	1.是0.否(如果是1，表示对账必须要有)',
create_time				datetime			comment	'创建时间',
update_time				datetime			comment	'更新时间',
bean_code				varchar(256)		comment	'文件下载器code	通过bena_code获取指定的文件下载器',
get_file_method		char(1) comment '获取文件方式',
get_file_url		varchar(200)		comment	'获取文件地址',
get_file_param		varchar(200)		comment '获取文件参数',
get_file_type		char(1) comment '获取文件类型	',
local_file_path		varchar(100) comment '本地文件路径	',
file_read_bean		varchar(100) comment '文件解析器',
check_data_bean		varchar(100) comment '数据校验器',
data_deal_bean		varchar(100) comment '数据整理器',
primary key (platform_detail_no)
);

alter table platform_detail comment '平台信息配置表';



/*==============================================================*/
/* Table: i_imp_status_detail                                   */
/*==============================================================*/
create table i_imp_status_detail
(
platform_detail_no		varchar(50)	not null	comment	'平台明细编号PK',
platform_detail_name	varchar(50)				comment	'平台明细名称',
imp_date				char(8)	  			comment	'导入日期PK',
success_count			integer	  				comment	'导入成功总数',
error_count				integer	  				comment	'导入失败总数',
total					integer	  				comment	'导入总记录数',
imp_type				char(1)					comment	'导入类型	0 自动1 手动',
status					char(1)					comment	'状态	 0.没有对账文件1.导入成功2.导入失败l',
create_time				datetime	  			comment	'创建时间',
download_url			varchar(512)			comment	'文件存放物理路径	文件下载路径',
primary key (platform_detail_no,imp_date)
);

alter table i_imp_status_detail comment '导入状态明细表';


/*==============================================================*/
/* Table: s_trade_result                                   */
/*==============================================================*/
create table s_trade_result
(
id					varchar(50)	  not null	comment	'编号Fk',
chanel_type			varchar(50)				comment	'渠道类别	0 积分 1 现金',
channel_no			varchar(20)				comment	'渠道编号	指银行组号或机构号',
trade_type			varchar(50)				comment	'交易类型',
order_no			varchar(50)				comment	'定单号',
order_time			datetime	  			comment	'订单时间',
trade_money			decimal(16,4)			comment	'交易额',
create_time			datetime	  			comment	'创建时间',
is_points			char(1)					comment	'平台编号1	1=是0=否',
is_bank_points		char(1)					comment	'平台编号2	1=是0=否',
is_pay				char(1)					comment	'平台编号3	1=是0=否',
is_partner			char(1)					comment	'平台编号4	1=是0=否',
is_front			char(1)					comment	'平台编号5	1=是0=否',
front_code			varchar(10)				comment	'前端应答码',
front_partner_no	varchar(50)				comment	'前端平台号',
supplier_no			varchar(50)				comment	'供应商编号',
point_bank_group 	varchar(50)				comment	'积分银行组号',
back_group			varchar(50)				comment	'现金银行组号',
point_org			varchar(10)				comment	'积分机构号',
recon_date			char(8)					comment	'对账日',

primary key (id)
);

alter table s_trade_result comment '对账结果表';



/*==============================================================*/
/* Table: s_no_recon                                   */
/*==============================================================*/
create table s_no_recon
(
id			varchar(50) not null	comment	'编号Fk',
chanel_type	varchar(50)			comment	'渠道类别	0 积分 1 现金',
platform_no	varchar(50)			comment	'平台编号',
trade_type	varchar(50)			comment	'交易类型	如退分、支付',
order_no	varchar(50)			comment	'定单号	对账唯一标识',
order_time	datetime	  		comment	'订单时间',
create_time	datetime	  		comment	'创建时间',
recon_date	char(8)				comment	'对账日',
primary key (id)
);

alter table s_no_recon comment '未对账表';



/*==============================================================*/
/* Table: s_exception                                   */
/*==============================================================*/
create table s_exception
(
order_no	varchar(50)not null	comment	'订单编号PK',
order_time	datetime	  		comment	'订单时间',
chanelType	varchar(50)			comment	'渠道类别	0 积分 1 现金',
trade_type	varchar(50)			comment	'交易类型	如退分、支付',
trade_type_name	varchar(256)		comment	'交易类型名称',
error_code	varchar(50)			comment	'错误代码',
error_desc	varchar(200)		comment	'错误描述',
trade_money	decimal(16,4)		comment	'交易额',
create_time	datetime	  		comment	'创建时间',
recon_date			char(8)		comment	'对账日',
process_status		char(2)		comment	'处理状态， 0未处理，1处理成功,2处理失败，3处理中',
process_info		varchar(1000)		comment	'处理信息',
front_code			varchar(10)				comment	'前端应答码',
front_no			varchar(50)				comment	'前端平台明细编号',
front_name 			varchar(256)			comment	'前端平台明细名称',
supplier_no			varchar(50)				comment	'供应商平台明细编号',
supplier_name		varchar(256)			comment	'供应商平台明细名称',
point_bank_no 		varchar(50)				comment	'积分银行平台明细编号',
point_bank_name 	varchar(256)			comment	'积分银行平台明细名称',
bank_no				varchar(50)				comment	'现金银行平台明细编号',
bank_name			varchar(256)			comment	'现金银行平台明细名称',
point_org_no		varchar(50)				comment	'积分机构平台明细编号',
point_org_name		varchar(256)			comment	'积分机构平台明细名称',
is_points			char(1)					comment	'平台编号1	1=是0=否',
is_bank_points		char(1)					comment	'平台编号2	1=是0=否',
is_pay				char(1)					comment	'平台编号3	1=是0=否',
is_partner			char(1)					comment	'平台编号4	1=是0=否',
is_front			char(1)					comment	'平台编号5	1=是0=否',
primary key (order_no)
);

alter table s_exception comment '差错表';


/*==============================================================*/
/* Table: s_success                                   */
/*==============================================================*/
create table s_success
(
order_no		varchar(50)not null	comment	'订单编号PK',
order_time		datetime	  		comment	'订单时间',
chanel_type		varchar(50)			comment	'渠道类别	0 积分 1 现金',
trade_type		varchar(50)			comment	'交易类型	如退分、支付',
trade_money		decimal(16,4)		comment	'交易额',
create_time		datetime	  		comment	'创建时间',
recon_date			char(8)					comment	'对账日',
primary key (order_no)
);
alter table s_success comment '对账成功表';


/*==============================================================*/
/* Table: s_delay                                   */
/*==============================================================*/
create table s_delay
(
id			    varchar(50)not null	comment	'编号Fk',
order_no		varchar(50)not null	comment	'订单编号',
order_time		datetime	  		comment	'订单时间',
platform_no		varchar(50)			comment	'平台编号',
chanel_type		varchar(50)			comment	'渠道类别	0 积分 1 现金',
trade_type		varchar(50)			comment	'交易类型	如退分、支付',
create_time	datetime	  			comment	'创建时间',
recon_date			char(8)					comment	'对账日',
primary key (id)
);

alter table s_delay comment '延时对账表';


/*==============================================================*/
/* Table: t_diractionary                                   */
/*==============================================================*/
create table t_diractionary
(
diractionary_no		varchar(50)not null	comment	'编号PK',
type_no				char(4)				comment	'类型FK',
diractionary_name	varchar(50)			comment	'名称',
status				char(1)				comment	'状态',
create_time			datetime	  		comment	'时间',
primary key (diractionary_no)
);

alter table t_diractionary comment '字典表';


/*==============================================================*/
/* Table: t_diractionary_type                                   */
/*==============================================================*/
create table t_diractionary_type
(
type_no		char(4)not null	comment	'编号PK',
type_name	varchar(50)		comment	'名称',
create_time	datetime	  	comment	'时间',
primary key (type_no)
);

alter table t_diractionary_type comment '字典类型表';







create table r_recon_result
(
id			    varchar(50)not null	comment	'编号Pk',
platform_detail_no		varchar(50)not null	comment	'平台明细编号PK',
platform_detail_name	varchar(50)			comment	'平台明细名称',
platform_no				varchar(50)			comment	'平台编号',
platform_name				varchar(50)			comment	'平台名称',
import_total 	   int comment	'导入总笔数',
success_total int comment	'对账成功总笔数',
success_total_money decimal(20,4) comment	'成功交易总金额',
refund_total_money decimal(20,4) comment	'成功退款总金额',
exception_total int comment	'对账差错总笔数',
delay_total int comment	'对账延迟总笔数',
no_total int comment	'未对账总笔数',
recon_date			char(8)					comment	'对账日',
create_time	datetime	  			comment	'创建时间',
primary key (id)
);
alter table s_delay comment '对账结果汇总报表';





