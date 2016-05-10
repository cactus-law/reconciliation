package com.froad.comon.util;

import com.froad.comon.util.Logger;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    private static Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * <p>标准的日期格式yyyyMMdd</p>
     */
    public static final String standardFormat = "yyyyMMdd";

    /**
     * <p>8位日期格式yy-MM-dd</p>
     */
    public static final String byte8Format = "yy-MM-dd";

    /**
     * <p>8位日期格式yy/MM/dd</p>
     */
    public static final String anotherByte8Format = "yy/MM/dd";

    /**
     * <p>6位日期格式yyMMdd</p>
     */
    public static final String byte6Format = "yyMMdd";

    /**
     * <p>10位日期格式yyyy-MM-dd</p>
     */
    public static final String byte10Format = "yyyy-MM-dd";

    /**
     * <p>20位时间格式yyyy-MM-dd HH:mm:ss</p>
     */
    public static final String anotherByte20Format = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * <p>20位时间格式yyyy-MM-dd HH:mm:ss</p>
     */
    public static final String anotherByte14Format = "yyyyMMddHHmmss";
    
    /**
     * <p>10位日期格式yyyy/MM/dd</p>
     */
    public static final String anotherByte10Format = "yyyy/MM/dd";

    /**
     * <p>get Date from a formated string</p>
     * <p/>
     * <pre>
     * getDateFromString("031009")
     * getDateFromString("20031009")
     * </pre>
     *
     * @param strDate the formated data String
     * @return a date associate with the parameter strDate
     * @throws Exception
     */
    public static Date getDateFromString(String strDate) throws Exception {
        int length = strDate.length();
        if (length == 6) {
            return getDateFromString(strDate, "yyMMdd");
        } else if (length == 8) {
            //对于yy/MM/dd和yy-MM-dd格式
            if ("/".equals(strDate.substring(2, 3)) && "/".equals(strDate.substring(5, 6))) {
                //strDate = strDate.replace('/','-');
                return getDateFromString(strDate, "yy/MM/dd");
            } else if ("-".equals(strDate.substring(2, 3)) && "-".equals(strDate.substring(5, 6))) {
                return getDateFromString(strDate, "yy-MM-dd");
            } else {
                //yyMMdd类型的
                return getDateFromString(strDate, "yyyyMMdd");
            }

        } else if (length == 10) {
            if ("/".equals(strDate.substring(4, 5)) && "/".equals(strDate.substring(7, 8))) {
                return getDateFromString(strDate, "yyyy/MM/dd");
            } else if ("-".equals(strDate.substring(4, 5)) && "-".equals(strDate.substring(7, 8))) {
                return getDateFromString(strDate, "yyyy-MM-dd");
            }
        }
        return null;
    }

    /**
     * <p>get Date from a formated string</p>
     * 本方法实现在日期格式和日期类型相一致的情况下，返回该日期类型的date
     * 如果有异常，则为类型不匹配异常
     * <pre>
     * getDateFromString("031009", "yyMMdd")
     * getDateFromString("20031009", "yyyyMMdd")
     * </pre>
     *
     * @param strDate the formated data String
     * @param format  the format of the strDate
     * @return a date associate with the parameter strDate
     * @throws Exception
     * @throws Exception
     */
    public static Date getDateFromString(String strDate, String format) throws Exception {
        int length = strDate.length();
        boolean isByte6FormatVolidate = false;
        boolean isByte8FormatVolidate = false;
        boolean isByte10FormatVolidate = false;

        if (length == 6) {
            if ("yyMMdd".equals(format)) {
                //如果strDate是060417，format是yyMMdd
                isByte6FormatVolidate = true;
            }
        } else if (length == 8) {
            if ("/".equals(strDate.substring(2, 3)) && "/".equals(strDate.substring(5, 6))) {
                //当format为yy/MM/dd情况时
                if ("yy/MM/dd".equals(format)) {
                    isByte8FormatVolidate = true;
                }

            } else if ("-".equals(strDate.substring(2, 3)) && "-".equals(strDate.substring(5, 6))) {
                //当format为yy-MM-dd情况时
                if ("yy-MM-dd".equals(format)) {
                    isByte8FormatVolidate = true;
                }
            } else if ("yyyyMMdd".equals(format)) {
                isByte8FormatVolidate = true;
            }
        } else if (length == 10) {
            if ("/".equals(strDate.substring(4, 5)) && "/".equals(strDate.substring(7, 8))) {
                //当format为yyyy/MM/dd情况时
                if ("yyyy/MM/dd".equals(format)) {
                    isByte10FormatVolidate = true;
                }
            } else if ("-".equals(strDate.substring(4, 5)) && "-".equals(strDate.substring(7, 8))) {
                //当format为yyyy-MM-dd情况时
                if ("yyyy-MM-dd".equals(format)) {
                    isByte10FormatVolidate = true;
                }
            }
        }else {
            if (DateUtil.anotherByte20Format.equals( format )) {
                            isByte10FormatVolidate = true;
                        }
        }

        if (isByte6FormatVolidate || isByte8FormatVolidate || isByte10FormatVolidate) {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                ParsePosition pos = new ParsePosition(0);

                return formatter.parse(strDate, pos);
            } catch (Exception e) {

                logger.error("根据输入日期[" + strDate + "]以及格式 [" + format + "] 返回日期类型错误");

                throw new Exception("返回日期类型错误", e);
            }
        } else {
            throw new Exception("日期类型与格式不匹配");
        }

    }

    
    public static Date getDateFromStringN(String strDate, String format) throws Exception {
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat(format);
        	ParsePosition pos = new ParsePosition(0);
        	
        	return formatter.parse(strDate, pos);
        } catch (Exception e) {
        	
        	logger.error("根据输入日期[" + strDate + "]以及格式 [" + format + "] 返回日期类型错误");
        	
        	throw new Exception("返回日期类型错误", e);
        }
        
      
    }
    /**
     * <p>date String from format 1 to format 2</p>
     * 本方法实现从标准日期格式yyyyMMdd转换为format类型的格式
     * <pre>
     * getFormat2FromStd("20030908", "yyyy-MM-dd") = "2003-09-08"
     * </pre>
     *
     * @param dt the data to be convert
     * @return a String associate with the parameter dt
     * @throws Exception
     */
    public static String getFormat2FromStd(String date, String newFormat) throws Exception {
        //return getNewFormatFromStandardFormat(date, "yyyyMMdd", format);
        return getStringFromDate(getDateFromStringUsedForStdFrt(date, "yyyyMMdd"), newFormat);
    }

    /**
     * <p>date String from format 1 to format 2</p>
     * 本方法实现从一个日期格式date和其对应的日期类型format1，转换为另一种日期类型format2的日期表示
     * <pre>
     * getFormat2FromFormat1("03-09-08", "yy-MM-dd", "yyyyMMdd") = "20030908"
     * </pre>
     *
     * @param dt the data to be convert
     * @return a String associate with the parameter dt
     * @throws Exception
     */
    public static String getNewFormatFromOldFormat(String date, String format1, String format2) throws Exception {
        return getStringFromDate(getDateFromString(date, format1), format2);
        //注意：此处对于format2的格式无法进行校验

    }


    /**
     * 本方法实现在日期格式和日期类型相一致的情况下，返回该日期类型的date
     * 如果有异常，则为标准日期格式yyyyMMdd转换为其他格式(yyMMdd,yy-MM-dd,yy/MM/dd,yyyy-MM-dd,yyyy/MM/dd)异常
     *
     * @param strDate
     * @param format
     * @return
     * @throws Exception
     */
    private static Date getDateFromStringUsedForStdFrt(String strDate, String format) throws Exception {
        int length = strDate.length();
        boolean isByte8FormatVolidate = false;
        if ((strDate.indexOf("-") == -1) && (strDate.indexOf("/") == -1)) {
            isByte8FormatVolidate = true;
        }

        if (length == 8 && isByte8FormatVolidate) {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                ParsePosition pos = new ParsePosition(0);
                return formatter.parse(strDate, pos);
            } catch (Exception e) {

                logger.error("根据输入日期[" + strDate + "]以及格式 [" + format + "] 返回日期类型错误");

                throw new Exception("返回日期类型错误", e);
            }
        } else {
            throw new Exception("日期类型不是标准格式yyyyMMdd");
        }

    }

    /**
     * <p>get String from a Date</p>
     * <p/>
     * <pre>
     * getDateFromString(new Date(System.currentTimeMillis()))
     * </pre>
     *
     * @param dt the data to be convert
     * @return a String associate with the parameter dt
     * @throws Exception
     */
    public static String getStringFromDate(Date dt) throws Exception {
        return getStringFromDate(dt, "yyyyMMdd");
    }

    /**
     * <p>get String from a Date</p>
     * <p/>
     * <pre>
     * getDateFromString(new Date(System.currentTimeMillis()), "yyMMdd HH:mm:ss") = "030910 12:23:30"
     * </pre>
     * <p/>
     * 注意：由于format格式有多种(日期类型、日期类型 + 时间类型、时间类型或者可能有其他字符串)，因此本方法对format的格式并没有作出校验
     *
     * @param dt     the data to be convert
     * @param format the format to be returned
     * @return a String associate with the parameter dt
     */
    public static String getStringFromDate(Date dt, String format) throws Exception {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(dt);
        } catch (Exception e) {

            logger.error("根据输入日期[" + dt + "]以及格式 [" + format + "] 返回日期类型错误");

            throw new Exception("返回日期类型错误", e);
        }

    }


    /**
     * <p>get the interval days between two date</p>
     * 本方法实现获取两个不同的date类型日期之间的间隔
     * <pre>
     * diffDate(dt1, dt2) = 2 // suppose dt1="030902" & dt2="030831"
     * </pre>
     *
     * @param dt1 the first data
     * @param dt2 the second data
     * @return the interval days between dt1 and dt2
     */
    public static int diffDate(Date dt1, Date dt2) throws Exception {
        try {
            long date1 = dt1.getTime();
            long date2 = dt2.getTime();
            if (date1 > date2)
                return (int) ((date1 - date2) / (24 * 3600 * 1000.0) + 0.5);

            return (int) ((date2 - date1) / (24 * 3600 * 1000.0) + 0.5);
        } catch (Exception e) {

            logger.error("根据输入日期 [" + dt1 + "]以及日期 [" + dt2 + "] 返回日期间隔错误");
            throw new Exception("返回日期间隔错误,[ " + "可能是两种日期格式不匹配 ]", e);
        }
    }

    /**
     * <p>get the interval days between two date described by formater "yy-MM-dd"</p>
     * 本方法实现获取两个不同的String类型的日期间隔，只能实现对标准日期格式yyyyMMdd的间隔获取
     * <pre>
     * diffDate("20030902", "20030904") = 2
     * </pre>
     *
     * @param dt1 the first data
     * @param dt2 the second data
     * @return the interval days between dt1 and dt2
     * @throws Exception
     */
    public static int diffDate(String dt1, String dt2) throws Exception {
        return diffDate(dt1, dt2, "yyyyMMdd");
    }


    /**
     * <p>get the interval days between two date described by desired formater</p>
     * 本方法实现获取两个日期之间的间隔，日期格式按照format的类型而定
     * <pre>
     * diffDate("03-09-02", "03-09-04", "yy-MM-dd") = 2
     * </pre>
     *
     * @param dt1    the first date
     * @param dt2    the second date
     * @param format the formater of the dt1 and dt2
     * @return the interval days between dt1 and dt2
     * @throws Exception
     */
    public static int diffDate(String dt1, String dt2, String format) throws Exception {
        return diffDate(getDateFromString(dt1, format), getDateFromString(dt2, format));
    }
    
    /***比较两个日期*/
    public static  int compare(String dt1, String dt2, String format)  throws Exception{
    	 Date resource=getDateFromStringN(dt1, format);
    	 Date target=getDateFromStringN(dt2, format);
    	 try {
             long date1 = resource.getTime();
             long date2 = target.getTime();
             if (date1 > date2){
            	 return 1;	 
             }else if (date1 < date2){
            	 return -1;
             }else{
            	 return 0;
             }
         } catch (Exception e) {
             logger.error("根据输入日期 [" + dt1 + "]以及日期 [" + dt2 + "] 返回日期间隔错误");
             throw new Exception("返回日期间隔错误,[ " + "可能是两种日期格式不匹配 ]", e);
         }
    }
    /**
     * <p>Adds the specified (signed) amount of day to the given date, based on the calendar's rules</p>
     * 本方法实现对日期的增加，传入的参数为Date型的和int型的变量
     * <pre>
     * addDaysToDate(dt1, 2) = dt // if dt1="03-09-02" then dt="03-09-04"
     * </pre>
     *
     * @param dt1  the base data
     * @param days days to be added
     * @return the new date
     */
    public static Date addDaysToDate(Date dt1, int days) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(dt1);
        cale.add(Calendar.DATE, days);
        return cale.getTime();
    }

    /**
     * <p>Adds the specified (signed) amount of day to the given date, based on the calendar's rules</p>
     * 本方法实现日期的增加，传入的参数为一个标准日期类型(yyyyMMdd)的String参数和一个int型参数
     * <pre>
     * addDaysToDate("20030902", 2) = dt // dt="20030904"
     * </pre>
     *
     * @param dt1  the base data
     * @param days days to be added
     * @return the new date
     * @throws Exception
     */
    public static Date addDaysToDate(String dt1, int days) throws Exception {
        return addDaysToDate(dt1, "yyyyMMdd", days);
    }

    /**
     * <p>Adds the specified (signed) amount of day to the given date, based on the calendar's rules</p>
     * 本方法实现对日期的增加，其中日期格式要与format定义的类型一致
     * <pre>
     * addDaysToDate("03-09-02", "yy-MM-dd", 2) = dt // dt="03-09-04"
     * </pre>
     *
     * @param dt1    the base data
     * @param format the format of the dt1
     * @param days   days to be added
     * @return the new date
     * @throws Exception
     */
    public static Date addDaysToDate(String dt1, String format, int days) throws Exception {
        return addDaysToDate(getDateFromString(dt1, format), days);
    }

    /**
     * <p>get the system current date</p>
     * 本方法实现获取一个标准日期yyyyMMdd
     * <pre>
     * getCurrentDate() = "20030908"
     * </pre>
     *
     * @return the current date
     * @throws Exception
     */
    public static String getCurrentDate() throws Exception {
        return getCurrentDateTime("yyyyMMdd");
    }

    /**
     * <p>get the system current date</p>
     * 本方法实现获取一个标准日期yyyy-MM-dd
     * <pre>
     * getCurrentDate() = "20030908"
     * </pre>
     *
     * @return the current date
     * @throws Exception
     */
    public static String getCurrentDate1() throws Exception {
        return getCurrentDateTime("yyyy-MM-dd");
    }

    /**
     * <p>get the system current time</p>
     * 本方法实现获取一个标准格式的时间HHmmss
     * <pre>
     * getCurrentTime() = "121208"
     * </pre>
     *
     * @return the current time
     * @throws Exception
     */
    public static String getCurrentTime() throws Exception {
        return getCurrentDateTime("HHmmss");

    }

    /**
     * <p>get the system current date and time</p>
     * 本方法实现获取一个标准格式的日期和时间yyyyMMdd HHmmss(日期与时间中间有空格隔开)
     * <pre>
     * getCurrentTime() = "20030912 121208"
     * </pre>
     *
     * @return the current date and time
     * @throws Exception
     */
    public static String getCurrentDateTime() throws Exception {
        return getCurrentDateTime("yyyyMMdd HHmmss");
    }

    /**
     * <p>get the system current date and time</p>
     * <p/>
     * 说明: format格式可以有以下几种格式: yyyyMMdd,yyMMdd,yy-MM-dd,yy/MM/dd,yyyy-MM-dd,yyyy/MM/dd,
     * yyyyMMdd HHmmss日期格式,yyyyMMddHHmmss日期格式,HHmmss时间格式,HH:mm:ss时间格式
     * <p/>
     * <pre>
     * getCurrentTime("yy-MM-dd HH:mm:ss") = "03-09-12 12:12:08"
     * </pre>
     *
     * @return the current date and time
     * @throws Exception
     */
    public static String getCurrentDateTime(String format) throws Exception {
        return getStringFromDate(new Date(System.currentTimeMillis()), format);
    }

    public static Date getNow(){
        return  Calendar.getInstance().getTime();
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println(addDaysToDate("20150913",standardFormat ,1));
	}
}
