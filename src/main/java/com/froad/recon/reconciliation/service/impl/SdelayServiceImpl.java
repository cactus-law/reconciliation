package com.froad.recon.reconciliation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.froad.comon.dao.impl.DBException;
import com.froad.comon.exception.ServiceException;
import com.froad.comon.util.Logger;
import com.froad.recon.importfile.service.QueryService;
import com.froad.recon.reconciliation.dao.SdelayDAO;
import com.froad.recon.reconciliation.dao.impl.SdelayDAOImpl;
import com.froad.recon.reconciliation.model.Sdelay;
import com.froad.recon.reconciliation.service.SdelayService;
import com.froad.recon.sys.model.PlatformDetail;


/**
 * 延时对账表Service实现类
 *
 * @author zhangjwei
 * @created 2015-06-16 11:25:48
 * @version 1.0
 */

public class SdelayServiceImpl implements SdelayService{
	private final static Logger logger = Logger.getLogger(SdelayDAOImpl.class);
	private SdelayDAO sdelayDAO;
	private QueryService queryService;
	
	public SdelayDAO getSdelayDAO() {
		return sdelayDAO;
	}
	public void setSdelayDAO(SdelayDAO sdelayDAO) {
		this.sdelayDAO = sdelayDAO;
	}
	public QueryService getQueryService() {
		return queryService;
	}
	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}
	/**
	 * @Title: inser 
	 * @Description: 插入单条数据
	 * @param sdelay  sdelay 对象
	 * @return Sdelay   返回插入完成的 sdelay对象
	 */
	public Sdelay insert(Sdelay sdelay) throws ServiceException{
		try {
			return sdelayDAO.insert(sdelay);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 插入多条数据 
	 * @param listSdelay 插入数据的list    
	 * @throws
	 */
	public void batchInser(List<Sdelay> lists) throws ServiceException{
		try {
			sdelayDAO.batchInser(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}

	/**
	 * @Title: delete
	 * @Description: 根据对象删除
	 * @param objId void    
	 */
	
	public void delete(Sdelay sdelay) throws ServiceException {
		try {
			sdelayDAO.delete(sdelay);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/**
	 * @Title: batchDelete 
	 * @Description: 根据 根据对象集合删除数据
	 * @param lists id集合   
	 */
	public void batchDelete(List<Sdelay> lists) throws ServiceException {
		try {
			sdelayDAO.batchDelete(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	
	
	/***
	 * 批量删除指定对账日的数据
	 * @param reconDate
	 * @param paramsMap
	 * @throws DBException
	 */
	public void batchDeleteByReconDate(String reconDate,Map<String, Object> paramsMap)throws ServiceException{
		try {
			sdelayDAO.batchDeleteByReconDate(reconDate,paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/**
	 * @Title: update 
	 * @Description: 跟新一条数据 sdelay
	 * @param sdelay  sdelay对象
	 * @throws
	 */
	public void update(Sdelay sdelay) throws ServiceException{
		try {
			sdelayDAO.update(sdelay);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: batchUpdate
	 * @Description: 跟新多条数据 sdelay集合
	 * @param listSdelay  sdelay集合  
	 * @throws
	 */
	
	public void batchUpdate(List<Sdelay> lists) throws ServiceException {
		try {
			sdelayDAO.batchUpdate(lists);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	/**
	 * @Title: getById
	 * @Description: 根据编号查询一条数据
	 * @param objID 编号
	 * @return Sdelay    sdelay 对象
	 * @throws
	 */
	public Sdelay getById(String objID) throws ServiceException {
		try {
			return sdelayDAO.getById(objID);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getAll
	 * @Description: 查询所有的sdelay数据
	 * @return List    返回一个sdelay的集合
	 * @throws
	 */
	public List getAll() throws ServiceException {
		try {
			return sdelayDAO.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: getList
	 * @Description: 根据 sdelay对象 查询符合的数据
	 * @param sdelay sdelay对象
	 * @return List    返回一个结合。 
	 * @throws
	 */
	public List getList(Sdelay sdelay) throws ServiceException{
		try {
			return sdelayDAO.getList(sdelay);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 查询符合条件的记录
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public List<Sdelay> selectForPagin(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			return sdelayDAO.selectForPagin(paramsMap, pageNum, pageSize);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Description: 根据 以paramsMap中key为字段名value为值， 总条数
	 * @param paramsMap 
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @throws ServiceException
	 */
	public Integer selectForPaginCount(Map<String, Object> paramsMap) throws ServiceException{
		try {
			return sdelayDAO.selectForPaginCount(paramsMap);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/***
	 * 根据对象删除
	 * @throws DBException
	 */
	public Integer deleteByObj(Sdelay obj)throws ServiceException{
		try {
			return sdelayDAO.deleteByObj(obj);
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/**
	 * @Title: batchInser 
	 * @Description: 退款延迟数据处理
	 * @throws
	 */
	public void refundSdelay(List<Map<String,Object>> lists,PlatformDetail platformDetail,String reconDate) throws ServiceException{
		try {
			/**添加延迟数据**/
			for (Map<String, Object> map : lists) {
				Sdelay insert=new Sdelay();
				insert.setOrderNo(MapUtils.getString(map, "order_no"));
				insert.setOrderTime((Date)map.get("order_time"));
				insert.setPlatformNo(platformDetail.getPlatformNo());
				insert.setChanelType(MapUtils.getString(map, "chanel_type"));
				insert.setCreateTime(new Date());
				insert.setReconDate(reconDate);
				
				insert.setBusinessType(Sdelay.BUSINESSTYPE_REFUND);
				insert.setSeqNo(insert.getOrderNo());
				insert.setReconStatus(Sdelay.RECON_STATUS_NOBIIL);
				insert.setChannelNo(platformDetail.getChannelNo());
				sdelayDAO.insert(insert);
			}
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		}catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/***
	 * 通过退款流水号 ，查询账单系统相关表，获取账户号，修改延迟对账表
	 * @param paramsMap
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public List<Sdelay> refundSdelayUpdate(Map<String, Object> paramsMap, Integer pageNum, Integer pageSize) 
			throws ServiceException{
		try {
			List<Sdelay> result=new ArrayList<Sdelay>();
			List<Sdelay> sdelays=sdelayDAO.selectForPagin(paramsMap, pageNum, pageSize);
			
			
			List<String> refundSeqs=new ArrayList<String>();
			List<Sdelay> refundsSdelays=new ArrayList<Sdelay>();
			for (Sdelay item : sdelays) {
				if(Sdelay.RECON_STATUS_NOBIIL.equals(item.getReconStatus())){
					refundSeqs.add(item.getOrderNo());
					refundsSdelays.add(item);
				}else{
					result.add(item);
				}
			}
			
			//根据请求流水查询退款交易账单号
			List<Map<String,Object>> refoudList = queryService.queryRefoud(refundSeqs.toArray());
			Map<String,Object> refundBillmap = new HashMap<String, Object>();
			//请求流水和账单号关系映射
			for(Map<String,Object> refoud : refoudList){
				refundBillmap.put((String)refoud.get("refund_order_ID"), refoud.get("refundInfoId"));
			}
			for (Sdelay item : refundsSdelays) {
				String  orderNo=item.getOrderNo();
				String  billNo=MapUtils.getString(refundBillmap, orderNo);
				if(StringUtils.isNotEmpty(billNo)){
					item.setOrderNo(billNo);
					item.setReconStatus(Sdelay.RECON_STATUS_NORECON);//以获取账单号
					sdelayDAO.update(item);
					queryService.updateFrontMall(orderNo, billNo);
					result.add(item);
				}
			}
			return result;
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
	
	/***
	 * 更新延迟对账的状态
	 * @throws DBException
	 */
	public void updateSdelays(List<Sdelay> list)throws ServiceException{
		try{
			for (Sdelay item : list) {
				sdelayDAO.updateSdelay(item);
			}
		} catch (DBException e) {
			logger.error(e);
			throw new ServiceException(e.getErrorCode(),e.getErrorMsg(),e);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(null,e.getMessage(),e);
		}
	}
}
