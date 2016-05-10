package com.froad.comon.util;

import com.froad.comon.util.Logger;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


/**
 * 公用工具类
 */
public class CommonUtil {
    private static final Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     * 获取系统变量 如：user.home
     */
    public static String getEnvironment(String envirKey) {
        return System.getProperties().getProperty(envirKey);
    }

    /**
     * 交易串转换成double,除以100，并设置精度为2
     * C开头为正，D开头为负
     *
     * @param tranStr
     * @return
     */
    public static double tranStr2Double(String tranStr) {
        double value = 0.00;
        if (tranStr != null && !"".equals(tranStr.trim())) {
            int i = 0;
            for (; i < tranStr.length() - 1; ) {
                if (tranStr.charAt(i) == '0') {
                    i++;
                } else {
                    break;
                }
            }
            if (i != tranStr.length()) {
                if (tranStr.toUpperCase().startsWith("D")) {
                    tranStr = tranStr.substring(1, tranStr.length());
                    BigDecimal tran = new BigDecimal("-" + tranStr.trim());
                    value = tran.divide(new BigDecimal(100)).setScale(2).doubleValue();
                } else if (tranStr.toUpperCase().startsWith("C")) {
                    tranStr = tranStr.substring(1, tranStr.length());
                    BigDecimal tran = new BigDecimal(tranStr.trim());
                    value = tran.divide(new BigDecimal(100)).setScale(2).doubleValue();
                } else {
                    //System.out.println(tranStr+"*********************");
                    BigDecimal tran = new BigDecimal(tranStr.trim());
                    value = tran.divide(new BigDecimal(100)).setScale(2).doubleValue();
                }
            }
        }
        return value;
    }

    /**
     * 对账时处理sourceflag 通过&关系处理 <br/>
     * 所有渠道拥有自己sourceflag，详见com.froad.common.Constant <br/>
     *
     * @param source
     * @param target
     * @return
     */
    public static String sourceflagDeal(String source, String target) {
        /*int sour=Integer.valueOf(source);
        int tar=Integer.valueOf(target);
		String result=String.valueOf(sour+tar);
		String temp="0000000000";
		result=temp.substring(0, 10-result.length())+result;
		return result;*/
        char[] s = new char[10];
        char[] t = new char[10];
        source.getChars(0, source.length(), s, 0);
        target.getChars(0, target.length(), t, 0);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            char temp = (char) ((s[i]) | (t[i]));
            sb.append(temp);
        }
        return sb.toString();
    }


    /**
     * 四舍五入,保留小数点后两位
     */
    public static double roundPrecision(double d) {
        String sd = Double.toString(d);
        BigDecimal bd = new BigDecimal(sd);
        BigDecimal bd1 = bd.setScale(2, RoundingMode.HALF_UP);
        double dd = bd1.doubleValue();
        return dd;

    }

    /**
     * 比较数字字符串大小
     *
     * @param source
     * @param target
     * @return 相同 返回"EQS" 大于 返回 "BIG"  小于返回 "SML"
     */
    public static String compareStr(String source, String target) {
        if (source.equals(target)) {
            return "EQS";
        } else if (Integer.valueOf(source) > Integer.valueOf(target)) {
            return "BIG";
        } else {
            return "SML";
        }
    }

    /**
     * 截取异常信息,截取n位
     *
     * @param message
     * @return
     */
    public static String cutExceptionStr(String message,int length) {
        if (message != null) {
            if (message.length() > length) {
                return message.substring(0, length);
            } else {
                return message;
            }
        } else {
            return "";
        }
    }

    /**
     * 截取长度
     *
     * @param message 信息
     * @param length  长度
     * @return
     */
    public static String cutStr(String message, int length) {
        if (message != null) {
            if (message.length() > length) {
                return message.substring(0, length);
            } else {
                return message;
            }
        } else {
            return "";
        }
    }

    /**
     * Description : 过滤空字符串
     *
     * @param inputStr
     * @return 如果为null或空，返回 "",其它情况返回inputStr.trim();
     */
    public static String null2Blank(Object inputStr) {
        if (inputStr == null || "null".equals(inputStr)
                || "".equals(inputStr.toString().trim())) {
            return "";
        } else {
            return inputStr.toString().trim();
        }
    }

    /**
     * 打印dir目录下fileType文件(也就是以什么结尾的文件!)
     *
     * @param dir      目录,如，src ,test
     * @param fileType 文件类型
     */
    public static void printFile(File dir, final String fileType) {
        File[] fs = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                return file.getName().endsWith(fileType);
            }
        });
        for (File f : fs) {
            if (f.isFile()) {
                String path = f.getPath().replace("\\", ".").substring(4);
                System.out.println(path);
            } else if (f.isDirectory())
                printFile(f, fileType);
        }
    }

    /**
     * 交易串转换成BigDecimal,精度为2
     *
     * @param tranStr    数据
     * @param needDivide true：则除以100   false:不除100
     * @return
     */
    public static BigDecimal tranStr2BigDecimal(String tranStr, boolean needDivide) {
        //double value=0.00;
        BigDecimal value = new BigDecimal(0).setScale(2);
        if (tranStr != null && !"".equals(tranStr.trim())) {
            int i = 0;
            for (; i < tranStr.length() - 1; ) {
                if (tranStr.charAt(i) == '0') {
                    i++;
                } else {
                    break;
                }
            }
            if (i != tranStr.length()) {
                if (tranStr.toUpperCase().startsWith("D")) {
                    tranStr = tranStr.substring(1, tranStr.length());
                    value = new BigDecimal("-" + tranStr.trim());
                } else if (tranStr.toUpperCase().startsWith("C")) {
                    tranStr = tranStr.substring(1, tranStr.length());
                    value = new BigDecimal(tranStr.trim());
                } else {
                    value = new BigDecimal(tranStr.trim());
                }
                if (needDivide) {
                    value = value.divide(new BigDecimal(100)).setScale(2);
                }
            }
        }
        return value;
    }

    /**
     * 获取IP地址
     *
     * @return
     */
    public static String getHostAddress() {
        String hostAddress = "localhost";
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ia = (InetAddress) addresses.nextElement();
                    if (ia != null && ia instanceof Inet4Address) {
                        String hostAddressTemp = ia.getHostAddress();
                        //String hostName=ia.getHostName();
                        //System.out.println("hostAddressTemp "+hostAddressTemp);
                        //System.out.println("hostName "+hostName);
                        if (!"127.0.0.1".equals(hostAddressTemp)) {
                            hostAddress = hostAddressTemp;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("本机IP地址：" + hostAddress);
        return hostAddress;
    }

    /**
     * 转换成INTEGER
     *
     * @param finalStr
     * @return
     */
    public static Integer tranStr2Integer(String finalStr) {
        if (finalStr != null && !"".equals(finalStr.trim())) {
            return Integer.valueOf(finalStr);
        } else {
            return 0;
        }
    }

    /**
     * 8位清算执行日期转4位清算日期
     *
     * @param cleardate
     * @return
     */
    public static String cleardateToSettledate(String cleardate) {
        String settledate = DateTime.getNDayBeforeForDate(cleardate, 1).substring(4);
        return settledate;
    }

    /**
     * 4位清算日期转8位清算执行日期
     *
     * @param settletedate
     * @param pospdate
     * @return
     */
    public static String settledateTocleardate(String settletedate, String pospdate) {
        String year = pospdate.substring(0, 4);
        String cleardate = DateTime.getNDayBeforeForDate(year + settletedate, -1);
        return cleardate;
    }

    /**
     * 时间 TRLTXTIME 6位转换成8位的时间
     *
     * @param time
     * @return
     */
    public static String tranTimeTrans(String time) {
        if (null != time && time.length() == 6) {
            StringBuffer sb = new StringBuffer();
            sb.append(time.substring(0, 2)).append(":");
            sb.append(time.substring(2, 4)).append(":");
            sb.append(time.substring(4));
            return sb.toString();
        } else {
            return time;
        }
    }

    /**
     * 后补空格
     *
     * @param sourceStr 原始字符串
     * @param length    整体长度
     * @return
     */
    public static String addBlankAfter(String sourceStr, int length) {
        StringBuffer targetStr = new StringBuffer(CommonUtil.null2Blank(sourceStr));
        if (sourceStr.length() < length) {
            for (int i = 0; i < (length - sourceStr.length()); i++) {
                targetStr.append(" ");
            }
        }
        return targetStr.toString();
    }

    public static void main(String[] args) throws Exception {
        //BigDecimal b=CommonUtil.tranStr2BigDecimal("1254885", true);
        //System.out.println(b);
        //System.out.println(CommonUtil.cleardateToSettledate("20140208"));;
        System.out.println(addBlankAfter("153", 8) + "||");

        String year = "20140708".substring(0, 4);
        String mmdd = "20140708".substring(4, 8);
        System.out.println(year + " " + mmdd);
    }

}
