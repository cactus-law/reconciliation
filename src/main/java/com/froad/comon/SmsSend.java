package com.froad.comon;

import com.froad.comon.util.CommonUtil;
import com.froad.comon.util.DateTime;
import com.froad.comon.util.PropertyUtil;
import com.froad.comon.util.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author choky
 *         单条短信发信接口
 */
public class SmsSend implements Runnable {
    private static final Logger logger = Logger.getLogger(SmsSend.class);
    /**
     * 电话号码
     */
    private String telnumber;
    /**
     * 发送信息
     */
    private String message;

    /**
     * @param telnumber
     * @param message
     */
    public SmsSend(String telnumber, String message) {
        super();
        this.telnumber = telnumber;
        this.message = message;
    }

    /**
     * 发送单条短信
     *
     * @param telnumber
     * @param message   6.1.1	短信网关TCP报文
     *                  通信双方采用TCP/IP短连接连接方式，发送短信时候，客服端主动向服务器建立连接，发送报文，接收响应然后断开连接。报文格式
     *                  如下
     *                  字段	长度（字节）	类型	说明
     *                  PackageLength	4	text	消息的总长度（字节）网银不送，由网关送
     *                  ServiceCode	10	text	服务号 区分各种渠道 目前规定前4为为字母，后6位为数字
     *                  Date	8	text	日期 如20090218
     *                  Time	6	text	时间 如 092010 代表09：20：10
     *                  ID	36	text	渠道流水号 必须34位 此号码用来在对应的渠道上是唯一的，用以保证后端短信平台能够和前端对应渠道
     *                  关联
     *                  Telnumber	11	text	手机号
     *                  Content	MessageLength	text	短信内容
     *                  开发地址 tcp://sms.pub.froad.com 端口：8789
     *                  返回  "0000" 表示成功
     */
    public void run() {
        send(telnumber, message);
    }

    public boolean send(String telnumber, String message) {
        boolean bool = false;
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        logger.info("手机号:" + telnumber + "  短信内容:" + message);
        if ("".equals(CommonUtil.null2Blank(telnumber)) || "".equals(CommonUtil.null2Blank(telnumber))) {
            logger.error("手机号或内容为空不发送......");
            return bool;
        }
        int packagele = 0;
        String smsServer = PropertyUtil.getProperties("smsServer");
        int smsPort = Integer.valueOf(PropertyUtil.getProperties("smsPort"));
        int smsTimeout = Integer.valueOf(PropertyUtil.getProperties("smsTimeout"));
        String serviceCode = PropertyUtil.getProperties("serviceCode");
        String smsCharset = PropertyUtil.getProperties("smsCharset");
        try {
            String ID = serviceCode + packN(DateTime.GetCurrentDate(), 26);
            String nowDate = DateTime.GetCurrentDate();
            packagele = 75 + message.getBytes(smsCharset).length;
            String ms = packN(packagele + "", 4) + serviceCode + nowDate + ID + telnumber + message;
            //logger.info("SMS报文内容：" +  new String(ms.getBytes(),"UTF-8"));
            byte[] bytes = ms.getBytes(smsCharset);
            byte[] output = new byte[4];

            // 创建Socket类,打开通讯连接
            socket = new Socket(smsServer, smsPort);
            // 设置通讯接收超时时间
            socket.setSoTimeout(smsTimeout);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            // 发送报文
            out.write(bytes);
            out.flush();
            // 根据配置读取报文长度域，获取后续数据的读取长度
            in.read(output);
            if ("0000".equals(new String(output))) {
                bool = true;
                logger.info("返回信息为: " + new String(output) + " 给手机 " + telnumber + " 发送成功......");
            } else {
                logger.error("返回信息为: " + new String(output) + " 给手机 " + telnumber + " 发送失败......");
            }
        } catch (Exception e) {
            logger.error("短信发送出现异常" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }
        return bool;
    }

    private String packN(String point, int length) {
        if (null == point || "".equals(point.trim())) {
            point = "0";
        }
        while (point.length() < length) {
            point = "0" + point;
        }
        return point;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void main(String[] args) throws Exception {
        /*
		String smsTels=PropertUtil.getProperties("smsTels");
		String[] tels=smsTels.split("-");
		String message="测试......";
		for (String tel : tels) {
			if(tel!=null&&!"".equals(tel)){
				SmsSend sms=new SmsSend(tel, message);
				Thread thread=new Thread(sms);
				thread.start();
			}
		}
		*/
    }
}