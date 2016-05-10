package com.froad.flow.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.froad.beans.Rckflowdetail;
import com.froad.beans.RckflowdetailId;
import com.froad.comon.util.Logger;
import com.froad.comon.util.PropertyUtil;
import com.froad.flow.service.RckflowdetailService;

/**
 *
 *   调度SOCKET
 */
@Component("taskQuartzSocket")
public class TaskQuartzSocket implements Runnable, DisposableBean {
    private final static Logger logger = Logger.getLogger(TaskQuartzSocket.class);
    private boolean flag = true;
    private ServerSocket serverSocket;
    @Autowired
    private RckflowdetailService rckflowdetailService;
    
    @Autowired
    private RckFlowThread rckFlowThread;
    /**
     * @throws Exception 接收报文    0000<id>编号</id><trancode>交易码：rck 清算，rerck 交易重发,lcretry 理财重上账</trancode><cleardate>清算日期</cleardate><list>交易重发序列号列表</list>
     *                   返回报文    0000<id>编号</id><trancode>交易码：rck 清算，rerck 交易重发,lcretry 理财重上账</trancode><cleardate>清算日期</cleardate><list>交易重发序列号列表</list><returncode></returncode>
     */
    public void run() {
        try {
            String socketPort = PropertyUtil.getProperties("socketPort");
            serverSocket = new ServerSocket(Integer.valueOf(socketPort));
            logger.info("调度服务开启......服务端口:" + socketPort);
            while (flag) {
                final Socket socket = serverSocket.accept();
                logger.info("客户端:" + socket.getInetAddress() + "进入......");
                new Thread() {
                    public void run() {
                        InputStream is = null;
                        OutputStream os = null;
                        Map<String, String> map = new HashMap<String, String>();
                        try {
                            is = socket.getInputStream();
                            os = socket.getOutputStream();
                            // 获取报文长度
                            byte[] bytesLen = new byte[4];
                            is.read(bytesLen);
                            String messageLen = new String(bytesLen, "UTF-8");
                            int len = Integer.valueOf(messageLen);

                            // 获取报文内容
                            byte[] bytesMessage = new byte[len];
                            is.read(bytesMessage);
                            String message = new String(bytesMessage, "UTF-8");
                            logger.info("报文长度：" + len + " 报文内容：" + message);

                            // 解析报文......
                            map = analysisMsg(message);
                            String trancode = map.get("trancode");
                            String cleardate = map.get("cleardate");
                            String reconType=map.get("reconType");
                            if (Rckflowdetail.TYPE_RECON.equals(trancode)) {
                            	//流程流水初始化成功
                            	logger.info("手动执行流程开始,对账日期：" + cleardate + " 清算类型：" + trancode);
                            	try {
                            		RckflowdetailId id = new RckflowdetailId(cleardate, trancode, 0);
                            		Rckflowdetail rckflowdetail = new Rckflowdetail(id);
                            		rckflowdetail.setReconType(reconType);
                            		rckFlowThread.setRckflowdetail(rckflowdetail);
                            		Thread thread = new Thread(rckFlowThread);
                            		thread.start();
                            		logger.info("手动执行RckFlowThread线程启动状态：" + thread.isAlive());
                            		logger.info("手动执行RckFlowThread线程是否中断：" + thread.isInterrupted());
                            		map.put("returncode", "调用对账成功，清算日期为："+ cleardate);
                            	} catch (Exception e) {
                            		logger.error("调用对账异常，清算日期为",e);
                            		map.put("returncode", "调用对账异常，清算日期为"+ cleardate);
                            	}
                            }else if (Rckflowdetail.TYPE_SYNC_RECON.equals(trancode)) {
                            	//流程流水初始化成功
                            	logger.info("手动执行流程开始,同步日期：" + cleardate + " 同步类型：" + trancode);
                            	try {
                            		RckflowdetailId id = new RckflowdetailId(cleardate, trancode, 0);
                            		Rckflowdetail rckflowdetail = new Rckflowdetail(id);
                            		rckFlowThread.setRckflowdetail(rckflowdetail);
                            		Thread thread = new Thread(rckFlowThread);
                            		thread.start();
                            		logger.info("手动执行RckFlowThread线程启动状态：" + thread.isAlive());
                            		logger.info("手动执行RckFlowThread线程是否中断：" + thread.isInterrupted());
                            		map.put("returncode", "调用同步成功，同步日期为："+ cleardate);
                            	} catch (Exception e) {
                            		logger.error("调用同步异常，同步日期为",e);
                            		map.put("returncode", "调用同步异常，同步日期为"+ cleardate);
                            	}
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            map.put("returncode", e.getMessage());
                        } finally {
                            //无论是否遇到异常，都将把返回报文发回
                            try {
                                os.write(encapsulationMsg(map));
                                os.flush();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (os != null) {
                                try {
                                    os.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 报文解析
     *
     * @param string 0000<id>编号</id><trancode>交易码：rck 清算，rerck 交易重发</trancode><cleardate>清算日期</cleardate><list>交易重发序列号列表</list>
     * @return
     */
    private Map<String, String> analysisMsg(String string) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", getTagValue(string, "id"));
        map.put("trancode", getTagValue(string, "trancode"));
        map.put("cleardate", getTagValue(string, "cleardate"));
        map.put("reconType", getTagValue(string, "reconType"));
        logger.info("报文解析完成：" + map);
        return map;
    }

    /**
     * 标签内容获取
     *
     * @param sourceStr
     * @param tagName
     * @return
     */
    private String getTagValue(String sourceStr, String tagName) {
        String returnStr = "";
        if (sourceStr != null && !"".equals(sourceStr.trim())) {
            int index = sourceStr.indexOf("<" + tagName + "/>");
            if (index == -1) {
                int beginIndex = sourceStr.indexOf("<" + tagName + ">") + tagName.length() + 2;
                int endIndex = sourceStr.indexOf("</" + tagName + ">");
                if(endIndex>0){
                	returnStr = sourceStr.substring(beginIndex, endIndex);
                }
            }
        }
        return returnStr;
    }

    /**
     * 封装报文
     *
     * @param map
     * @return
     */
    private byte[] encapsulationMsg(Map<String, String> map) throws Exception {
        final String lenStr = "0000";
        StringBuffer sb = new StringBuffer();
        sb.append(tagAdd("id", map.get("id")))
                .append(tagAdd("trancode", map.get("trancode")))
                .append(tagAdd("cleardate", map.get("cleardate")))
                .append(tagAdd("reconType", map.get("reconType")))
                .append(tagAdd("returncode", map.get("returncode")));
        String len = String.valueOf(sb.toString().getBytes("UTF-8").length);
        String msgHead = lenStr.substring(len.length()) + len;
        byte[] message = (msgHead + sb.toString()).getBytes("UTF-8");
        logger.info("返回报文：" + new String(message, "UTF-8"));
        return message;
    }

    /**
     * 增加标签
     *
     * @param tagName
     * @param tagValue
     * @return
     */
    private String tagAdd(String tagName, String tagValue) {
        if (tagValue == null) {
            tagValue = "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<").append(tagName).append(">").append(tagValue).append("</").append(tagName).append(">");
        return sb.toString();
    }

    public void startServer() {
        flag = true;
        logger.info("调度侦听服务开启......");
    }

    /**
     * 停止服务
     */
    public void stopServer() {
        flag = false;
        logger.info("调度侦听服务停止......");
    }

    /**
     * 对象销毁时调用此方法
     */
    public void destroy() throws Exception {
        stopServer();
        serverSocket.close();
        logger.info("销毁调度侦听服务......");
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void main(String[] args) throws Exception {
        TaskQuartzSocket quartzSocket = new TaskQuartzSocket();
//        String str = "<id>编号</id><trancode>交易码：rck 清算，rerck 交易重发</trancode><cleardate>清算日期</cleardate><list>交易重发序列号列表</list>";
        String str = "<id>编号</id><trancode>RECON</trancode><cleardate>20150708</cleardate><reconType>0</reconType>";
        quartzSocket.analysisMsg(str);
        quartzSocket.encapsulationMsg(quartzSocket.analysisMsg(str));
    }
}
