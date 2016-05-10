package com.froad.recon.importfile.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.comon.constant.BusinessConstant;
import com.froad.comon.util.SqlUtil;
import com.froad.recon.importfile.dao.QueryDao;
import com.froad.recon.importfile.service.QueryService;

/**
 * 查询数据Service实现类
 * @author Administrator
 *
 */
@Component("queryService")
public class QueryServiceImpl implements QueryService {
	
	@Autowired
	private QueryDao queryDao;

	@Override
	public List<Map<String, Object>> queryRefoud(Object... params) {
		if(params==null||params.length<1){
			return new ArrayList<Map<String,Object>>();
		}
		StringBuffer sql = new StringBuffer();
		//sql.append("select a.refund_order_ID,a.bill_no from openapi.refund a");
		sql.append(" select  a.refund_order_ID,b.refundInfoId from  openapi.refund a  ");
		sql.append(" INNER JOIN froadbill.refundinfo b on a.track_no=b.seqId ");
		sql.append(" INNER JOIN froadbill.pay c on b.refundInfoId=c.pay_seq_no ");
		if(params !=null){
			for(int i=0;i<params.length;i++){
				if(i==0){
					sql.append(" where a.refund_order_ID=?");
				}else{
					sql.append(" or a.refund_order_ID=?");
				}
			}
		}
		
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params);
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryRefoudInfo(Object... params) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.refundInfoId,a.seqId,a.billBankGroup,a.merchantId,a.billState,a.billchannelId,a.refundState,a.refundType from froadbill.refundinfo a");
		if(params !=null){
			for(int i=0;i<params.length;i++){
				if(i==0){
					sql.append(" where a.seqId=?");
				}else{
					sql.append(" or a.seqId=?");
				}
			}
		}
		
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params);
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryBill(Object... params) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.seq_no,concat(a.bankGroup,'') as bankGroup ,a.money,a.create_time from froadbill.bill a");
		if(params !=null){
			for(int i=0;i<params.length;i++){
				if(i==0){
					sql.append(" where a.seq_no=?");
				}else{
					sql.append(" or a.seq_no=?");
				}
			}
		}
		
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params);
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryPartnerTrade(List<String> paraList) {
		/*StringBuffer sql = new StringBuffer();
		sql.append("select a.order_no,a.supplier_order_no from i_phone_trade a");
		if(params !=null){
			for(int i=0;i<params.length;i++){
				if(i==0){
					sql.append(" where a.supplier_order_no=?");
				}else{
					sql.append(" or a.supplier_order_no=?");
				}
			}
		}
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params);*/
		
		List<Object> params=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select partner_no ,order_no from i_front_trade   a where 1=1  ");
		
		Map<String,Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("a.partner_no_in", paraList);
		SqlUtil.appendResearchConditionMap(paramsMap, sql,params);
		 
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryFrontMallCTrade(Object... params) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.front_order_no from froad_recon.i_front_trade a ");
		if(params !=null){
			for(int i=0;i<params.length;i++){
				if(i==0){
					sql.append(" where a.front_order_no=?");
				}else{
					sql.append(" or a.front_order_no=?");
				}
			}
		}
		
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params);
		
		return list;
	}

	@Override
	public int updateFrontMall(Object... params) {
		StringBuffer sql = new StringBuffer();
		sql.append("update i_front_trade set virtual_trade_type=?,supplier_no=?,result_code=?,supplier_name=?,chanel_type=? ");
		sql.append(" where front_order_no=?");
		
		int ret = this.queryDao.executeSql(sql.toString(), params);
		
		return ret;
	}
	
	@Override
	public int updateFrontMall(String refundSeq,String orderNo) {
		List<String> params=new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("update i_front_trade set order_no= ? ");
		sql.append(" where order_no=? ");
		params.add(orderNo);
		params.add(refundSeq);
		int ret = this.queryDao.executeSql(sql.toString(),params.toArray());
		return ret;
	}
	
	@Override
	public List<Map<String, Object>> queryFrontMall(List<String> frontOrderNos) {
		List<Object> params=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select  front_order_no,order_no from  i_front_trade a  ");
		sql.append(" where a.chanel_type =? ");
		params.add(BusinessConstant.CHANEL_TYPE.CASH);
		
		Map<String,Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("a.front_order_no_in", frontOrderNos);
		SqlUtil.appendResearchConditionMap(paramsMap, sql,params);
		 
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryFrontMallBypoint(Object... params) {
		StringBuffer sql = new StringBuffer();
		sql.append("select  point_order_no,order_no from  i_front_trade a ");
		if(params !=null){
			for(int i=0;i<params.length;i++){
				if(i==0){
					sql.append(" where a.point_order_no=?");
				}else{
					sql.append(" or a.point_order_no=?");
				}
			}
		}
		
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryPointsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append("select id as order_no,org_no as channel_no,total_points points,member_id user_id,create_time order_time ,transfer_type order_type,status status,parent_id org_order_no from froadpoints.points_transfer_points_info  t where 1=1");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		hql.append(" and status in ('001','009','008')");
		hql.append(" and transfer_type in ('consumPoints','refundPoints')");
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}
	
	public List<Map<String, Object>> queryAhExchPointsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append("select t.id as order_no,t.request_id as request_no,t.org_no as point_org,t.total_points trade_money,t.member_id,t.create_time order_time,case when t.status='001'  then '0000' when t.status='008'  then '0000' when t.status='009'  then '0000' else '0099' end as result_code,o.org_name bank_name,o.bankGroup point_bank_group from froadpoints.points_transfer_points_info t join froadpoints.points_org o  where t.org_no = o.org_no ");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		hql.append(" and t.status = '001'");
		hql.append(" and t.transfer_type ='consumPoints'");
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryPointsOrg(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append("SELECT  status, org_name, org_no, org_id, bankGroup, display_name, syncStatus FROM froadpoints.points_org where 1=1");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	/**
	 * 只查询成功的退款记录
	 * @param paramsMap
	 * @return
	 * @see com.froad.recon.importfile.service.QueryService#queryBossRefundPointsTrade(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> queryRefundPointsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append("select t.id as order_no,t.request_id as request_no,t.org_no as point_org,t.total_points trade_money,t.member_id,t.create_time order_time,case when t.status='001'  then '0000' when t.status='008'  then '0000' when t.status='009'  then '0000' else '0099' end as result_code,o.org_name bank_name,o.bankGroup point_bank_group from froadpoints.points_transfer_points_info t join froadpoints.points_org o  where t.org_no = o.org_no ");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		hql.append(" and t.transfer_type = 'refundPoints'");
		hql.append(" and t.status in ('001','004','009','008')");
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	@Override
	public List<Map<String, Object>> queryO2OBillPointsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
			hql.append("SELECT orders.sn AS front_order_no");
			hql.append(", payment.bank_sn AS order_no");
			hql.append(", payment.amount AS trade_money");
			hql.append(", date_format(orders.create_date, '%Y%m%d%H%m%s') AS order_time");
			hql.append(", CASE WHEN payment.method = 4 AND payment.type = 0 AND payment.bank = '000' THEN '1120' WHEN payment.method = 4 AND payment.type = 0 AND payment.bank != '000' THEN '1020' WHEN payment.method = 4 AND payment.type = 2 THEN '1180' WHEN payment.method = 4 AND payment.type = 1 THEN '1160' END AS transfer_type");
			hql.append(", '' AS supplier_no");
			hql.append(", CASE WHEN payment.status = 4 OR payment.status = 2 THEN '0000' ELSE '0099' END AS result_code");
			hql.append(", member.username AS member_id");
			hql.append(", payment.bank AS point_bank_group");
			hql.append(", channel.bank_name AS bank_name");
			hql.append(", CASE WHEN payment.bank = '612' THEN '100010009' WHEN payment.bank = '670' THEN '100010004' WHEN payment.bank = '632' THEN '100010015' WHEN payment.bank = '661' THEN '100010034' WHEN payment.bank = '679' THEN '100010052' WHEN payment.bank = '684' THEN '100010024' WHEN payment.bank = '629' THEN '100010054' WHEN payment.bank = '654' THEN '100010049' WHEN payment.bank = '671' THEN '100010035' WHEN payment.bank = '646' THEN '100010027' WHEN payment.bank = '638' THEN '100010031' WHEN payment.bank = '657' THEN '100010048' WHEN payment.bank = '603' THEN '100010001' WHEN payment.bank = '634' THEN '100010013' WHEN payment.bank = '652' THEN '100010023' WHEN payment.bank = '650' THEN '100010021' WHEN payment.bank = '643' THEN '100010068' WHEN payment.bank = '683' THEN '100010056' WHEN payment.bank = '641' THEN '100010055' WHEN payment.bank = '617' THEN '100010008' WHEN payment.bank = '642' THEN '100010017' WHEN payment.bank = '666' THEN '100010040' WHEN payment.bank = '677' THEN '100010051' WHEN payment.bank = '667' THEN '100010028' WHEN payment.bank = '656' THEN '100010046' WHEN payment.bank = '684' THEN '100010024' WHEN payment.bank = '665' THEN '100010038' WHEN payment.bank = '663' THEN '100010041' WHEN payment.bank = '633' THEN '100010016' WHEN payment.bank = '676' THEN '100010050' WHEN payment.bank = '645' THEN '100010018' WHEN payment.bank = '675' THEN '100010033' WHEN payment.bank = '635' THEN '100010030' WHEN payment.bank = '611' THEN '100010036' WHEN payment.bank = '662' THEN '100010037' WHEN payment.bank = '640' THEN '100010010' WHEN payment.bank = '674' THEN '100010042' WHEN payment.bank = '619' THEN '100010011' WHEN payment.bank = '620' THEN '100010014' WHEN payment.bank = '648' THEN '100010020' WHEN payment.bank = '623' THEN '100010029' WHEN payment.bank = '621' THEN '100010007' WHEN payment.bank = '649' THEN '100010022' WHEN payment.bank = '651' THEN '100010019' WHEN payment.bank = '636' THEN '100010026' WHEN payment.bank = '655' THEN '100010047' WHEN payment.bank = '653' THEN '100010025' WHEN payment.bank = '639' THEN '100010006' WHEN payment.bank = '637' THEN '100010053' WHEN payment.bank = '644' THEN '100010005' WHEN payment.bank = '659' THEN '100010039' WHEN payment.bank = '631' THEN '100010032' WHEN payment.bank = '664' THEN '100010043' WHEN payment.bank = '614' THEN '100010012' WHEN payment.bank = '660' THEN '100010045' WHEN payment.bank = '690' THEN '100010057' WHEN payment.bank = '693' THEN '100010058' WHEN payment.bank = '694' THEN '100010059' WHEN payment.bank = '695' THEN '100010060' WHEN payment.bank = '696' THEN '100010061' WHEN payment.bank = '697' THEN '100010062' WHEN payment.bank = '698' THEN '100010063' WHEN payment.bank = '699' THEN '100010064' WHEN payment.bank = '067' THEN '100010067' ELSE '100010002' END AS point_org");
			hql.append(", '10000005001' AS partner_no");
			hql.append(" FROM   o2obill_store.xx_payment payment, o2obill_store.xx_member member, o2obill_store.xx_order orders,");
			hql.append(" o2obill_store.xx_channel channel");
			hql.append(" WHERE  payment.member = member.id AND payment.bank = channel.bank_group AND payment.orders = orders.id AND payment.status IN (2, 4) AND payment.method = 4");
//				hql.append(" AND payment.create_date >= '2015-08-01 00:00:00' AND payment.create_date <= '2015-08-01 23:59:59'");
			SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
			List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	@Override
	public List<Map<String, Object>> queryO2OBillPointsRefundTrade(
			Map<String, Object> paramsMap) {
		StringBuffer hql=new StringBuffer();
		hql.append("select orders.sn as front_order_no");
	       hql.append(", refunds.sn order_no");
	       hql.append(", refunds.amount trade_money");
	       hql.append(", date_format(orders.create_date, '%Y%m%d%H%m%s') as order_time");
	       hql.append(", case when payment.type = 0 and payment.bank = '000' then '01140' when payment.type = 0 and payment.bank != '000' then '01040' end as transfer_type");
	       hql.append(", '' as supplier_no");
	       hql.append(", case when payment.status = 4 or payment.status = 2 then '0000' else '0099' end as result_code");
	       hql.append(", member.username member_id");
	       hql.append(", payment.bank point_bank_group");
	       hql.append(", channel.bank_name bank_name");
	       hql.append(", case when payment.bank = '612' then '100010009' when payment.bank = '670' then '100010004' when payment.bank = '632' then '100010015' when payment.bank = '661' then '100010034' when payment.bank = '679' then '100010052' when payment.bank = '684' then '100010024' when payment.bank = '629' then '100010054' when payment.bank = '654' then '100010049' when payment.bank = '671' then '100010035' when payment.bank = '646' then '100010027' when payment.bank = '638' then '100010031' when payment.bank = '657' then '100010048' when payment.bank = '603' then '100010001' when payment.bank = '634' then '100010013' when payment.bank = '652' then '100010023' when payment.bank = '650' then '100010021' when payment.bank = '643' then '100010068' when payment.bank = '683' then '100010056' when payment.bank = '641' then '100010055' when payment.bank = '617' then '100010008' when payment.bank = '642' then '100010017' when payment.bank = '666' then '100010040' when payment.bank = '677' then '100010051' when payment.bank = '667' then '100010028' when payment.bank = '656' then '100010046' when payment.bank = '684' then '100010024' when payment.bank = '665' then '100010038' when payment.bank = '663' then '100010041' when payment.bank = '633' then '100010016' when payment.bank = '676' then '100010050' when payment.bank = '645' then '100010018' when payment.bank = '675' then '100010033' when payment.bank = '635' then '100010030' when payment.bank = '611' then '100010036' when payment.bank = '662' then '100010037' when payment.bank = '640' then '100010010' when payment.bank = '674' then '100010042' when payment.bank = '619' then '100010011' when payment.bank = '620' then '100010014' when payment.bank = '648' then '100010020' when payment.bank = '623' then '100010029' when payment.bank = '621' then '100010007' when payment.bank = '649' then '100010022' when payment.bank = '651' then '100010019' when payment.bank = '636' then '100010026' when payment.bank = '655' then '100010047' when payment.bank = '653' then '100010025' when payment.bank = '639' then '100010006' when payment.bank = '637' then '100010053' when payment.bank = '644' then '100010005' when payment.bank = '659' then '100010039' when payment.bank = '631' then '100010032' when payment.bank = '664' then '100010043' when payment.bank = '614' then '100010012' when payment.bank = '660' then '100010045' when payment.bank = '690' then '100010057' when payment.bank = '693' then '100010058' when payment.bank = '694' then '100010059' when payment.bank = '695' then '100010060' when payment.bank = '696' then '100010061' when payment.bank = '697' then '100010062' when payment.bank = '698' then '100010063' when payment.bank = '699' then '100010064' when payment.bank = '067' then '100010067' else '100010002' end as point_org");
	       hql.append(", '10000005001' as partner_no");
		   hql.append(" from   o2obill_store.xx_payment payment, o2obill_store.xx_member member, o2obill_store.xx_order orders,o2obill_store.xx_channel channel, o2obill_store.xx_refunds refunds");
		   hql.append(" where  refunds.payment = payment.id and payment.member = member.id and payment.bank = channel.bank_group and payment.orders =orders.id and payment.status = 4 and payment.method = 4");
	       hql.append("  and payment.modify_date >= '2015-08-01 00:00:00' and payment.modify_date <= '2015-08-01 23:59:59'");
	       List<Object> params =new ArrayList<Object>();
			if(paramsMap==null){
				paramsMap=new HashMap<String, Object>();
			}
	       SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
			List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	 
	/**
	 * 只查询成功的退款记录
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> queryPayRefundsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();

		hql.append(" SELECT  ");
		hql.append(" b.refundInfoId as \"order_no\", ");
		hql.append(" b.billMoney as \"trade_money\", ");
		hql.append(" c.pay_time as \"order_time\", ");
		hql.append(" concat( b.billBankGroup,'') as \"bank_group\", ");
		hql.append(" (CASE c.state WHEN '已成功' THEN '0000' ");
		hql.append(" ELSE '9999' END) as \"result_code\" ");
		hql.append(" from froadbill.refundinfo b   inner join froadbill.pay c on b.refundInfoId=c.pay_seq_no  ");
		hql.append("    where b.applyType='FD' ");
		//hql.append(" c.pay_time >= STR_TO_DATE('20150910000000','%Y%m%d%H%i%s')  ");
		//hql.append(" and  c.pay_time <=STR_TO_DATE('20150910235959','%Y%m%d%H%i%s') ");

		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}
	
	
	/**
	 * 现金 冲正交易
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> queryPayCZsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		
		hql.append(" select  ");
		hql.append(" a.seq_no as \"order_no\", ");
		hql.append(" b.money as \"trade_money\", ");
		hql.append(" a.create_time as \"order_time\", ");
		hql.append(" concat(b.bankGroup,'') as \"bank_group\", ");
		hql.append(" (CASE a.state WHEN '1' THEN '0000' ");
		hql.append(" ELSE '9999' END) as \"result_code\" ");
		hql.append(" from froadbill.pay_back a  INNER JOIN froadbill.bill b  ");
		hql.append(" on a.seq_no=b.seq_no  WHERE 1=1  ");
//		hql.append(" a.create_time >= STR_TO_DATE('20150910000000','%Y%m%d%H%i%s')  ");
//		hql.append(" and  a.create_time <=STR_TO_DATE('20150910235959','%Y%m%d%H%i%s') ");
		
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}
	
	/**
	 * 现金 话费交易
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> queryPaySTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select  ");
		sql.append(" p.seq_no as \"order_no\", ");
		sql.append(" p.money  as \"trade_money\", ");
		sql.append(" p.pay_time as \"order_time\", ");
		sql.append(" concat(b.bankGroup,'') as \"bank_group\", ");
		sql.append(" b.create_seq_no as \"front_order_no\",   ");
		sql.append(" (CASE p.state WHEN '已成功' THEN '0000' ELSE '9999' END) as \"result_code\" ");
		sql.append(" from  froadbill.pay p ");
		sql.append(" INNER JOIN froadbill.pay p2 on p.seq_no =p2.seq_no and p.pay_step='1' and p2.pay_step='2' and p2.pay_type='话费充值' ");
		sql.append(" INNER JOIN froadbill.bill b on p.seq_no=b.seq_no ");
		sql.append(" where   b.bill_type!='行内账单' ");
		
		//and p.pay_time>='2015-11-02 16:44:00' and p.pay_time<'2015-11-03 16:39:00';
//		hql.append(" a.create_time >= STR_TO_DATE('20150910000000','%Y%m%d%H%i%s')  ");
//		hql.append(" and  a.create_time <=STR_TO_DATE('20150910235959','%Y%m%d%H%i%s') ");
		
		SqlUtil.appendResearchConditionMap(paramsMap, sql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryO2OBillPayTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();

		hql.append("SELECT  payment.bank_sn order_no,");
		hql.append(" orders.sn AS front_order_no,");
		hql.append(" payment.amount trade_money,");
		hql.append(" date_format(orders.modify_date, '%Y%m%d%H%m%s') AS order_time,");
		hql.append(" CASE");
		hql.append(" WHEN payment.method = 3 THEN '2020'");
		hql.append(" WHEN payment.method = 5 THEN '2051'");
		hql.append(" WHEN payment.method = 6 THEN '2053'");
		hql.append(" WHEN payment.method = 7 THEN '2050'");
		hql.append(" ELSE ''");
		hql.append(" END");
		hql.append(" AS transfer_type,");
		hql.append(" CASE");
		hql.append(" WHEN payment.status = 1 OR payment.status = 2 THEN '0000'");
		hql.append(" ELSE '0099'");
		hql.append(" END");
		hql.append(" AS result_code,");
		hql.append(" payment.bank bank_group,");
		hql.append(" channel.bank_name,");
		hql.append(" '10000005001' AS partner_no");
		 hql.append(" FROM o2obill_store.xx_payment payment,");
		hql.append(" o2obill_store.xx_channel channel,");
		hql.append(" o2obill_store.xx_order orders");
		hql.append(" WHERE  payment.bank = channel.bank_group");
		hql.append(" AND payment.orders = orders.id");
		hql.append(" AND payment.bank != '000'");
		hql.append(" AND payment.method IN (3, 5, 6, 7)");
		hql.append(" AND payment.status IN (2, 3)");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	@Override
	public List<Map<String, Object>> queryO2OBillPayRefundTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append(" SELECT refunds.sn order_no, orders.sn AS front_order_no,");
	       hql.append(" refunds.amount trade_money, refunds.modify_date  order_time,");
	       hql.append(" '2040' AS transfer_type, CASE WHEN refunds.status = 1 THEN '0000' ELSE '0099' END AS result_code,");
	       hql.append(" payment.bank bank_group, channel.bank_name,");
	       hql.append(" '10000005001' AS partner_no");
	       hql.append(" FROM   o2obill_store.xx_refunds refunds, o2obill_store.xx_channel channel, o2obill_store.xx_payment payment,");
	       hql.append(" o2obill_store.xx_order orders");
	       hql.append(" WHERE  refunds.payment = payment.id AND payment.bank = channel.bank_group AND payment.orders = orders.id AND payment.bank != '000'");
	       hql.append(" AND payment.method IN (3, 5, 6, 7) AND refunds.status IN (1, 2)"); 
//	       AND refunds.modify_date >= '2015-08-02 00:00:00' AND
//	    	       hql.append(" refunds.modify_date <= '2015-08-09 23:59:59';
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	
	/**
	 * 通过paySeqnos查询对应的账单号
	 */
	public List<Map<String, Object>> queryBillNoByPaySeqnos(
			List<String> paySeqnos) {
		if(paySeqnos.size()<1){
			return new ArrayList<Map<String,Object>>();
		}
		List<Object> params =new ArrayList<Object>();
		
		StringBuffer hql=new StringBuffer();
		hql.append(" select pay_seq_no,seq_no  as \"order_no\" from  froadbill.pay a where 1=1  ");
		Map<String,Object> paramsMap=new HashMap<String, Object>();
		paramsMap.put("pay_seq_no_in", paySeqnos);
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}
	
	
	/**
	 * 查询老商城信息
	 */
	public List<Map<String, Object>> queryMall(
			Map<String,Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select t.ID,t.SPID,t.billSeqNo,t.CPID,"
				+ "(CASE t.status WHEN '0' THEN '0000' ELSE '9999' END) as \"result_code\" ,"
				+ "t.buyer,t.buyerType,t.payTime, p.proname from froadmall.transaction t LEFT JOIN froadmall.trangoods tg on  t.ID= tg.TranID  ");
		sql.append(" LEFT JOIN  froadmall.provider p ON tg.providerID= p.ID  ");
		sql.append(" where  1=1   ");	
	//t.ID in (201509190000754);
//		Map<String,Object> paramsMap=new HashMap<String, Object>();
//		paramsMap.put("t.ID_in", paySeqnos);
		SqlUtil.appendResearchConditionMap(paramsMap, sql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		return list;
	}
	
	/**
	 * 查询老商城信息  桂林银行和 前端是银行发起的
	 */
	public List<Map<String, Object>> queryFrontMall(
			Map<String,Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select  pp.seq_no as \"order_no\", ");
		sql.append(" pp.create_seq_no as \"front_order_no\",   ");
		sql.append(" concat(pp.bankGroup ,'')  as \"bank_group\", ");
		sql.append(" pp.money as \"trade_money\", ");
		sql.append(" t.CPID as \"partner_no\", ");
		sql.append(" (CASE t.status WHEN '已成功' THEN '0000' ELSE '9999' END) as \"result_code\" , ");
		sql.append(" str_to_date(t.bargainTime, '%Y-%m-%d %H:%i:%s')  as \"order_time\",  ");
		sql.append(" p.proname as \"supplier_name\" ");
		sql.append(" from froadbill.bill pp  ");
		sql.append(" INNER JOIN  froadmall.transaction t  on pp.seq_no=t.billSeqNo  ");
		sql.append(" LEFT JOIN froadmall.trangoods tg on  t.ID= tg.TranID ");
		sql.append(" LEFT JOIN  froadmall.provider p ON tg.providerID= p.ID ");
		sql.append(" where 1=1 ");	
	//t.ID in (201509190000754);
//		Map<String,Object> paramsMap=new HashMap<String, Object>();
//		paramsMap.put("t.ID_in", paySeqnos);
		SqlUtil.appendResearchConditionMap(paramsMap, sql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		return list;
	}

	@Override
	public List<Map<String, Object>> checkrefundTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		//查询退款表支付定单号billNo
		hql.append(" select  bill_no as billNo from  openapi.refund  r where 1=1  ");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		if(null!=list&&list.size()>0){
			for (Map<String, Object> map : list) {
				String billNo=(String) map.get("billNo");
				HashMap<String, Object>	_paramsMap=new HashMap<String, Object>();
				_paramsMap.put("p.seq_no", billNo);
				//验证支付类别
				return	checkrefundPayTrade(_paramsMap);
			}
		}
		return null;
	}
	/**
	 * <pre>
	 * 查询退款定单的支付类别
	 * </pre>
	 *
	 * @param paramsMap
	 * @return
	 */
	public List<Map<String, Object>> checkrefundPayTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append(" select pay_seq_no, pay_type as payType from froadbill.pay  p where p.pay_step='2'");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}
	
	
	/**查询预售现金（正常交易）**/
	public List<Map<String, Object>> queryPresellPayTrade(
			Map<String, Object> paramsMap){
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer sql=new StringBuffer();

		sql.append(" SELECT ");
		sql.append(" 	p.sn as front_order_no, ");
		sql.append(" 	p.bill_no as order_no, ");
		sql.append(" 	p.pay_value as trade_money, ");
		sql.append(" 	p.create_time as  order_time, ");
		sql.append(" 	 p.pay_org as  bank_group, ");
		sql.append(" 	(CASE ");
		sql.append(" 		WHEN t.pay_state = '10' OR t.pay_state = '20' OR t.pay_state = '30' THEN ");
		sql.append(" 			(CASE t.pay_channel ");
		sql.append(" 				WHEN '20' THEN '2020' ");
		sql.append(" 				WHEN '53' THEN '2053' ");
		sql.append(" 				WHEN '50' THEN '2050' ");
		sql.append(" 				WHEN '51' THEN '2051'	END) ");
		sql.append(" 		ELSE ");
		sql.append(" 			(CASE t.pay_channel ");
		sql.append(" 				WHEN '20' THEN '2042' ");
		sql.append(" 				WHEN '53' THEN '2041' ");
		sql.append(" 				WHEN '50' THEN '2044' ");
		sql.append(" 				WHEN '51' THEN '2040' END) ");
		sql.append(" 		END ");
		sql.append(" 	) as  transfer_type , ");
		sql.append(" 	p.result_code as result_code, ");
		sql.append(" 	p.from_account_name as cname, ");
		sql.append(" 	p.from_account_no as caccount, ");
		sql.append(" 	p.to_account_name as dname, ");
		sql.append(" 	p.to_account_no as daccount, ");
		sql.append(" 	client.partner_id as partner_no ");
		sql.append(" FROM froadfft.fft_pay p ");
		sql.append(" LEFT JOIN froadfft.fft_trans t ON t.id = p.trans_id ");
		sql.append(" LEFT JOIN froadfft.fft_sys_client client ON client.id = t.client_id ");
		sql.append(" WHERE p.pay_type = '10' ");
		sql.append(" AND t.pay_channel IN ('20', '53', '50', '51') ");
		sql.append(" AND t.id IS NOT NULL ");
		sql.append(" AND client.id IS NOT NULL ");

		SqlUtil.appendResearchConditionMap(paramsMap, sql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		return list;
	}

	/**查询预售现金（退款）**/
	public List<Map<String, Object>> queryPresellPayRefundTrade(
			Map<String, Object> paramsMap){
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" 	p.sn as front_order_no, ");
		sql.append(" 	p.refund_order_id as order_no, ");
		sql.append(" 	p.pay_value as trade_money, ");
		sql.append(" 	p.update_time as  order_time, ");
		sql.append(" 	 p.pay_org as  bank_group, ");
		
		sql.append(" 	(CASE ");
		sql.append(" 		WHEN t.pay_state = '10' OR t.pay_state = '20' OR t.pay_state = '30' THEN ");
		sql.append(" 			(CASE t.pay_channel ");
		sql.append(" 				WHEN '20' THEN '2020' ");
		sql.append(" 				WHEN '53' THEN '2053' ");
		sql.append(" 				WHEN '50' THEN '2050' ");
		sql.append(" 				WHEN '51' THEN '2051'	END) ");
		sql.append(" 		ELSE ");
		sql.append(" 			(CASE t.pay_channel ");
		sql.append(" 				WHEN '20' THEN '2042' ");
		sql.append(" 				WHEN '53' THEN '2041' ");
		sql.append(" 				WHEN '50' THEN '2044' ");
		sql.append(" 				WHEN '51' THEN '2040' END) ");
		sql.append(" 		END ");
		sql.append(" 	) as  transfer_type , ");
		sql.append(" 	p.result_code as result_code, ");
		sql.append(" 	p.from_account_name as cname, ");
		sql.append(" 	p.from_account_no as caccount, ");
		sql.append(" 	p.to_account_name as dname, ");
		sql.append(" 	p.to_account_no as daccount, ");
		sql.append(" 	client.partner_id as partner_no ");
		sql.append(" FROM froadfft.fft_pay p ");
		sql.append(" LEFT JOIN froadfft.fft_trans t ON t.id = p.trans_id ");
		sql.append(" LEFT JOIN froadfft.fft_sys_client client ON client.id = t.client_id ");
		sql.append(" WHERE p.pay_type = '10' ");
		sql.append(" AND t.pay_channel IN ('20', '53', '50', '51') ");
		sql.append(" AND t.id IS NOT NULL ");
		sql.append(" AND client.id IS NOT NULL ");
		sql.append(" AND refund_order_id IS NOT NULL ");
		
		
		SqlUtil.appendResearchConditionMap(paramsMap, sql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(sql.toString(), params.toArray());
		return list;
	}


	@Override
	public List<Map<String, Object>> queryYushouPointsTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append("SELECT p.sn front_order_no,");
		hql.append("p.point_bill_no order_no,");
		hql.append("p.pay_value trade_money,");
		hql.append("date_format(p.create_time, '%Y%m%d%H%i%s') order_time,");
		     hql.append(" (CASE p.pay_type_details WHEN '002' THEN '1020' ELSE '1120' END)  transfer_type,");
		hql.append("p.result_code,");
		hql.append("p.from_user_name member_id,");
		hql.append("client.partner_id partner_no,");
		hql.append("client.order_display front_platform_name");
		  hql.append(" FROM froadfft.fft_pay p");
		hql.append(" LEFT JOIN froadfft.fft_trans t ON t.id = p.trans_id");
		hql.append(" LEFT JOIN froadfft.fft_sys_client client ON client.id = t.client_id");
		 hql.append(" WHERE    t.id IS NOT NULL");
		hql.append(" AND client.id IS NOT NULL");
		hql.append(" AND p.pay_type_details IN ('001', '002', '004')");
		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

	@Override
	public List<Map<String, Object>> queryYushouPointsRefundTrade(
			Map<String, Object> paramsMap) {
		List<Object> params =new ArrayList<Object>();
		if(paramsMap==null){
			paramsMap=new HashMap<String, Object>();
		}
		StringBuffer hql=new StringBuffer();
		hql.append("SELECT p.sn front_order_no,");
		hql.append("p.refund_point_no order_no,");
		hql.append("p.pay_value trade_money,");
		hql.append("date_format(p.create_time, '%Y%m%d%H%i%s') order_time,");
	    hql.append(" (CASE p.pay_type_details WHEN '002' THEN '1040' ELSE '1140' END)  transfer_type,");
		hql.append("p.result_code,");
		hql.append("p.from_user_name member_id,");
		hql.append("client.partner_id partner_no,");
		hql.append("client.order_display front_platform_name");
		hql.append(" FROM froadfft.fft_pay p");
		hql.append(" LEFT JOIN froadfft.fft_trans t ON t.id = p.trans_id");
		hql.append(" LEFT JOIN froadfft.fft_sys_client client ON client.id = t.client_id");
		hql.append(" WHERE t.id IS NOT NULL");
		hql.append(" AND client.id IS NOT NULL");
		hql.append(" AND t.pay_state in (40,50,60)");
		hql.append(" AND p.pay_type_details IN ('001', '002', '004')");

		SqlUtil.appendResearchConditionMap(paramsMap, hql, params);
		List<Map<String,Object>> list = this.queryDao.queryRefoud(hql.toString(), params.toArray());
		return list;
	}

}
