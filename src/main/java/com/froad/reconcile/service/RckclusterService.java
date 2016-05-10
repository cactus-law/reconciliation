package com.froad.reconcile.service;

import com.froad.beans.Rckcluster;
import com.froad.comon.ServiceException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 集群处理
 *
 * @author silence
 */
@Transactional
public interface RckclusterService {
    /**
     * 初始化
     *
     * @param clustertype
     * @return
     * @throws ServiceException
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Rckcluster initCluster(String clustertype) throws ServiceException;

    /**
     * 初始化所有集群
     *
     * @return
     * @throws ServiceException
     */
    public List<Rckcluster> initClusterAll() throws ServiceException;

    /**
     * 集群列表
     *
     * @param clustertype
     * @return
     * @throws ServiceException
     */
    public List<Rckcluster> listRckcluster(String clustertype) throws ServiceException;

    /**
     * 更新执行结果
     *
     * @param clustertype
     * @param exestate
     * @param remark
     * @throws ServiceException
     */
    public void udpateExestate(String clustertype, String exestate, String remark) throws ServiceException;

    /**
     * 争夺执行
     *
     * @param clustertype
     * @return
     * @throws ServiceException
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean snatchExecute(String clustertype) throws ServiceException;

    /**
     * 更改集群是否执行
     *
     * @param clustertype
     * @param isexecute
     * @throws ServiceException
     */
    public void changeClusterExecute(String clustertype, String isexecute) throws ServiceException;

    /**
     * 更改集群运行状态
     * @param clustertype
     * @param isexecute
     * @throws ServiceException
     */
    public void changeExecuteState(String clustertype, String isexecute) throws ServiceException;

}
