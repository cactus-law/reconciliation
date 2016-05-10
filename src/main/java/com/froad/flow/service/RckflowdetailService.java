package com.froad.flow.service;

import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.ServiceException;
import com.froad.flow.FlowException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程管理服务
 *
 * @author
 */
public interface RckflowdetailService {

    /**
     * 初始化清算列表
     *
     * @param rckflowdetail
     * @return
     * @throws FlowException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Rckflowdetail> initFlowDetail(Rckflowdetail rckflowdetail) throws FlowException;

    /**
     * 未使用流程的程序]调用初始化
     *
     * @param cleardate
     * @param rcktype
     * @return
     * @throws ServiceException
     */
    public List<Rckflowdetail> initFlowDetail(String cleardate, String rcktype) throws ServiceException;

    /**
     * 初始化清算列表[并账流程特殊处理]
     *
     * @param cleardate
     * @return
     * @throws ServiceException
     */
    public List<Rckflowdetail> initFlowDetailBz(String cleardate) throws ServiceException;

    /**
     * 第一步操作
     *
     * @param cleardate
     * @param rcktype
     * @throws ServiceException
     */
    public boolean flowFirst(String cleardate, String rcktype) throws ServiceException;

    /**
     * 最后步骤
     *
     * @param cleardate
     * @param rcktype
     * @param msg 
     * @throws ServiceException
     */
    public void flowLast(String cleardate, String rcktype, String flowstate, String msg) throws ServiceException;

    /**
     * 更新状态
     *
     * @param cleardate
     * @param rcktype
     * @param rckorder
     * @param flowstate
     * @throws ServiceException
     */
    public void updateFlowstate(String cleardate, String rcktype, int rckorder, String flowstate) throws ServiceException;

    /**
     * 根据ID获取对象
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    public Rckflowdetail getObjectById(RckflowdetailId id) throws ServiceException;

    /**
     * 更改可执行状态
     *
     * @param cleardate
     * @param rcktype
     * @param rckorder
     * @throws ServiceException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeExecute(String cleardate, String rcktype, int rckorder) throws ServiceException;

    /**
     * 单独执行
     *
     * @param cleardate
     * @param rcktype
     * @param rckorder
     * @throws ServiceException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void singleExt(String cleardate, String rcktype, int rckorder) throws ServiceException;
}
