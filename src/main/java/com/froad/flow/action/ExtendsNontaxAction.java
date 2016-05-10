package com.froad.flow.action;

import com.froad.comon.action.BaseAction;
import com.froad.beans.Rckflow;
import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.util.PropertyUtil;
import com.froad.flow.handler.RckFlowThread;
import com.froad.flow.service.RckflowService;
import com.froad.flow.service.RckflowdetailService;
import com.froad.comon.util.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("extendsNontaxAction")
@Scope("prototype")
public class ExtendsNontaxAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ExtendsNontaxAction.class);
    @Autowired
    private RckFlowThread rckFlowThread;
    @Autowired
    private RckflowdetailService rckflowdetailService;
    @Autowired
    private RckflowService rckflowService;


    /**
     * 查看历史或清算执行调度口
     *
     * @throws Exception
     */
    public String execute() throws Exception {
        String type = request.getParameter("rcktype");
        String clearDate = request.getParameter("cleardate");
        String tip = request.getParameter("tip");
        String reconType=request.getParameter("reconType");
        this.getRequest().setAttribute("rcktype", type);
        this.getRequest().setAttribute("cleardate", clearDate);
        this.getRequest().setAttribute("clearDate", clearDate);
        this.getRequest().setAttribute("tip", tip);
        this.getRequest().setAttribute("reconType", reconType);

        // 清算执行
        if (!"select".equals(tip)) {
            this.rck(clearDate, type,reconType);
            int sleepTime = 15;
            String sleepTimeStr = PropertyUtil.getProperties("sleepTime");
            try {
                sleepTime = Integer.parseInt(sleepTimeStr);
            } catch (NumberFormatException e) {
                logger.info("获取等待时间异常：" + sleepTimeStr + "  设置等待时间为默认值" + sleepTime);
            }
            Thread.sleep(sleepTime * 1000);

        }

        getDetail(type, clearDate);

        return "list";
    }

    /**
     * 对账 
     * @param reconType 
     *
     * @param cleardate：对账时间
     * @param rcktype：对账类别 
     * @param reconType 0：全部自动对账，1：部分末对账的对账
     */
    public String rck(String cleardate, String rcktype, String reconType) {
        try {
            RckflowdetailId id = new RckflowdetailId(cleardate, rcktype, 0);
            Rckflowdetail rckflowdetail = new Rckflowdetail(id);
            rckflowdetail.setReconType(reconType);
            rckFlowThread.setRckflowdetail(rckflowdetail);
            Thread thread = new Thread(rckFlowThread);
            thread.start();
            logger.info("手动执行RckFlowThread线程启动状态：" + thread.isAlive());
            logger.info("手动执行RckFlowThread线程是否中断：" + thread.isInterrupted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 清算状态列表详情，清算历史列表
     *
     * @param rcktype
     * @param cleardate
     * @return
     */
    public void getDetail(String rcktype, String cleardate) throws Exception {
        try {
            RckflowdetailId id = new RckflowdetailId();
            id.setRcktype(rcktype);
            id.setCleardate(cleardate);
            Rckflowdetail rckflowdetail = new Rckflowdetail();
            rckflowdetail.setId(id);
            List<Rckflowdetail> list = rckflowdetailService.initFlowDetail(rckflowdetail);
            this.getRequest().setAttribute("detailList", list);
            this.getRequest().setAttribute("rcktype", rcktype);
            this.getRequest().setAttribute("cleardate", cleardate);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 页面刷新获取最新状态用
     * <p/>
     * 清算状态列表详情，清算历史列表
     *
     * @return
     */
    public String getAJAXdetail() {
        try {
            String rcktype = this.request.getParameter("rcktype");
            String cleardate = this.request.getParameter("cleardate");
            RckflowdetailId id = new RckflowdetailId();
            id.setRcktype(rcktype);
            id.setCleardate(cleardate);
            Rckflowdetail rckflowdetail = new Rckflowdetail();
            rckflowdetail.setId(id);
            List<Rckflowdetail> list = rckflowdetailService.initFlowDetail(rckflowdetail);
            this.getRequest().setAttribute("detailList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 流程获取
     *
     * @return
     * @throws com.froad.flow.FlowException
     */
    public String selectFlows() throws Exception {
        String type = request.getParameter("rcktype");
        List<Rckflow> list = rckflowService.getRckflows(type);
        this.getRequest().setAttribute("flowlist", list);
        this.getRequest().setAttribute("rcktype", type);
        return "flowlist";
    }

    /**
     * 更改是否执行
     *
     * @return
     * @throws Exception
     */
    public String changeExecute() throws Exception {
        String cleardate = request.getParameter("cleardate");
        String rcktype = request.getParameter("rcktype");
        int rckorder = Integer.valueOf(request.getParameter("rckorder"));
        logger.info(cleardate + " " + rcktype + " " + rckorder);
        rckflowdetailService.changeExecute(cleardate, rcktype, rckorder);
        getDetail(rcktype, cleardate);
        return "list";
    }

    /**
     * 单独执行
     *
     * @return
     * @throws Exception
     */
    public String singleExt() throws Exception {
        String cleardate = request.getParameter("cleardate");
        String rcktype = request.getParameter("rcktype");
        int rckorder = Integer.valueOf(request.getParameter("rckorder"));
        logger.info("单独执行：" + cleardate + " " + rcktype + " " + rckorder);
        rckflowdetailService.singleExt(cleardate, rcktype, rckorder);
        getDetail(rcktype, cleardate);
        return "list";
    }


}
