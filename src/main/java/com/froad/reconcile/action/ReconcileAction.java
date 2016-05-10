package com.froad.reconcile.action;

import com.froad.beans.Rckcluster;
import com.froad.comon.action.BaseAction;
import com.froad.reconcile.service.RckclusterService;
import com.froad.comon.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhou on 2015/3/26.
 */
@Component("reconcileAction")
@Scope("prototype")
public class ReconcileAction  extends BaseAction {

    private static final Logger logger = Logger.getLogger(ReconcileAction.class);

    @Autowired
    private RckclusterService rckclusterService;

    /**
        * 集群管理
        *
        * @return
        * @throws com.froad.flow.FlowException
        */
       public String clusterList() throws Exception {
           String clustertype = request.getParameter("clustertype");
           rckclusterService.initClusterAll();
           List<Rckcluster> list = rckclusterService.listRckcluster(clustertype);
           this.getRequest().setAttribute("list", list);
           this.getRequest().setAttribute("clustertype", clustertype);
           return "clusterList";
       }

       /**
        * 更改集群是否执行
        *
        * @return
        * @throws Exception
        */
       public String changeClusterExecute() throws Exception {
           String ct = request.getParameter("ct");
           String isexecute = request.getParameter("isexecute");
           String clustertype = request.getParameter("clustertype");
           System.out.println(ct + ";" + isexecute + ";" + clustertype);
           rckclusterService.changeClusterExecute(ct, isexecute);
           List<Rckcluster> list = rckclusterService.listRckcluster(clustertype);
           this.getRequest().setAttribute("list", list);
           this.getRequest().setAttribute("clustertype", clustertype);
           return "clusterList";
       }
    /**
        * 更改集群运行状态
        *
        * @return
        * @throws Exception
        */
       public String changeExecuteState() throws Exception {
           String ct = request.getParameter("ct");
           String isexecute = request.getParameter("isexecute");
           String clustertype = request.getParameter("clustertype");
           System.out.println(ct + ";" + isexecute + ";" + clustertype);
           rckclusterService.changeExecuteState(ct, isexecute);
           List<Rckcluster> list = rckclusterService.listRckcluster(clustertype);
           this.getRequest().setAttribute("list", list);
           this.getRequest().setAttribute("clustertype", clustertype);
           return "clusterList";
       }
}
