package com.froad.comon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String TIMEFORMAT = "yyyyMMddHHmmss";

    public static final String TIMEFORMATSSS = "yyyyMMddHHmmssSSS";

    public static final String DATEFORMAT = "yyyyMMdd";

    public static final String DATEFORMATyyy_MM_dd = "yyyy-MM-dd";

    public static final String TIME = "HH:mm:ss";

    public DateTime() {

    }

    /**
     * @return HHmmss
     */
    public static String GetTime() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(TIME);
        String mDateTime = formatter.format(cal.getTime());
        return (mDateTime);
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String GetDateTime() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATETIMEFORMAT);
        String mDateTime = formatter.format(cal.getTime());

        return (mDateTime);
    }

    /**
     * @return yyyy-MM-dd
     */
    public static String GetDate_yyyy_MM_dd() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATEFORMATyyy_MM_dd);
        String mDateTime = formatter.format(cal.getTime());

        return (mDateTime);
    }

    /**
     * @return yyyyMMddHHmmss
     */
    public static String GetCurrentDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                TIMEFORMAT);
        String mDateTime = formatter.format(cal.getTime());

        return (mDateTime);
    }

    /**
     * @return yyyyMMddHHmmssSSS
     */
    public static String GetDateWithMSEL() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                TIMEFORMATSSS);
        String mDateTime = formatter.format(cal.getTime());
        return (mDateTime);
    }

    /**
     * @return Date 类型的 yyyyMMdd
     */
    public static java.sql.Date GetDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATEFORMAT);
        String mDateTime = formatter.format(cal.getTime());
        return (java.sql.Date.valueOf(mDateTime));
    }

    /**
     * @return String yyyyMMdd
     */
    public static String GetData() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATEFORMAT);
        String mDateTime = formatter.format(cal.getTime());
        return mDateTime;
    }

    /**
     * 功能或作用：格式化日期时间
     *
     * @param DateValue 输入日期或时间
     * @param DateType  格式化 EEEE是星期, yyyy是年, MM是月, dd是日, HH是小时, mm是分钟, ss是秒
     * @return 输出字符串
     */
    public static String formatDate(String DateValue, String DateType) {
        String Result = "";
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DateType);
        try {
            java.util.Date mDateTime = formatter.parse(DateValue);
            Result = formatter.format(mDateTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (Result.equalsIgnoreCase("1900-01-01") ||
                Result.equalsIgnoreCase("0000-01-01")) {
            Result = "";
        }
        return Result;
    }

    /**
     * 功能或作用：格式化日期时间
     *
     * @param DateValue 输入日期或时间
     * @return 输出字符串
     */
    public static String formatDate(String DateValue) {
        String Result = "";
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATETIMEFORMAT);
        try {
            if (DateValue.length() == 10) {
                DateValue = DateValue + " 00:00:00";
            }
            java.util.Date mDateTime = formatter.parse(DateValue);
            Result = formatter.format(mDateTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (Result.equalsIgnoreCase("1900-01-01") ||
                Result.equalsIgnoreCase("0000-01-01")) {
            Result = "";
        }
        return Result;
    }

    public static String parseDate(String dateValue, String resourceformat, String toformat) throws ParseException {
        String refvalue = "";
        SimpleDateFormat sdf = new SimpleDateFormat(resourceformat);
        Date tempDate = sdf.parse(dateValue);

        SimpleDateFormat nsdf = new SimpleDateFormat(toformat);
        refvalue = nsdf.format(tempDate);

        return refvalue;
    }

    public static String parseDate(String dateValue, String format) throws ParseException {

        SimpleDateFormat nsdf = new SimpleDateFormat(format);
        Date tempDate = nsdf.parse(dateValue);

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(tempDate);
    }

    public static java.util.Date getDate(String DateValue) {
        java.util.Date Result = null;
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATETIMEFORMAT);
        try {
            Result = formatter.parse(DateValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Result;
    }

    public static Date getDate_yyyyMMdd(String DateValue) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(DATEFORMAT);
        try {
            date = format.parse(DateValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static java.util.Date getDate_yyyy_MM_dd(String DateValue) {
        java.util.Date Result = null;
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                DATEFORMATyyy_MM_dd);
        try {
            Result = formatter.parse(DateValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Result;
    }
    public static java.util.Date getDate_yyyy_MM_dd_HH_mm_ss(String DateValue) {
        java.util.Date Result = null;
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                TIMEFORMAT);
        try {
            Result = formatter.parse(DateValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Result;
    }

    /**
     * 取得系统当前时间前n天
     *
     * @param n int
     * @return String yyyymmdd
     */
    public static String getNDayBeforeCurrentDate(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -n);
        String math = null;
        String date = null;
        if (c.get(Calendar.MONTH) + 1 < 10) {
            math = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            math = String.valueOf(c.get(Calendar.MONTH) + 1);
        }
        if (c.get(Calendar.DATE) < 10) {
            date = "0" + c.get(Calendar.DATE);
        } else {
            date = String.valueOf(c.get(Calendar.DATE));
        }
        return "" + c.get(Calendar.YEAR) + math + date;
    }

    /**
     * 取得系统当前时间前n天
     *
     * @param n int
     * @return String yyyy-mm-dd
     */
    public static String getNDayBeforeCurrentDate10(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -n);
        String math = null;
        String date = null;
        if (c.get(Calendar.MONTH) + 1 < 10) {
            math = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            math = String.valueOf(c.get(Calendar.MONTH) + 1);
        }
        if (c.get(Calendar.DATE) < 10) {
            date = "0" + c.get(Calendar.DATE);
        } else {
            date = String.valueOf(c.get(Calendar.DATE));
        }
        return "" + c.get(Calendar.YEAR) + "-" + math + "-" + date;
    }

    /**
     * 取得系统当前时间前n个月的相对应的一天
     *
     * @param n int
     * @return String yyyymmdd
     */
    public static String getNMonthBeforeCurrentDay(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -n);
        String math = null;
        String date = null;
        if (c.get(Calendar.MONTH) + 1 < 10) {
            math = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            math = String.valueOf(c.get(Calendar.MONTH) + 1);
        }
        if (c.get(Calendar.DATE) < 10) {
            date = "0" + c.get(Calendar.DATE);
        } else {
            date = String.valueOf(c.get(Calendar.DATE));
        }
        return "" + c.get(Calendar.YEAR) + math + date;

    }

    /**
     * 取得传入时间前n个月的相对应的一天
     *
     * @param n int
     * @return String yyyymmdd
     */
    public static String getNMonthBeforeDay(Date date1, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.add(Calendar.MONTH, -n);
        String math = null;
        String date = null;
        if (c.get(Calendar.MONTH) + 1 < 10) {
            math = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            math = String.valueOf(c.get(Calendar.MONTH) + 1);
        }
        if (c.get(Calendar.DATE) < 10) {
            date = "0" + c.get(Calendar.DATE);
        } else {
            date = String.valueOf(c.get(Calendar.DATE));
        }
        return "" + c.get(Calendar.YEAR) + math + date;

    }

    /**
     * 取得传入时间前n天
     *
     * @param forDate 指定的时间
     * @param n       int
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String getNDayBeforeForDate(Date forDate, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(forDate);
        c.add(Calendar.DAY_OF_MONTH, -n);
        String math = null;
        String date = null;
        String hour_or_day = null;
        String minute = null;
        String second = null;
        StringBuffer sbdate = new StringBuffer("");
        if (c.get(Calendar.MONTH) + 1 < 10) {
            math = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            math = String.valueOf(c.get(Calendar.MONTH) + 1);
        }

        if (c.get(Calendar.DATE) < 10) {
            date = "0" + c.get(Calendar.DATE);
        } else {
            date = String.valueOf(c.get(Calendar.DATE));
        }

        hour_or_day = c.get(Calendar.HOUR_OF_DAY) + "";

        if (c.get(Calendar.MINUTE) < 10) {
            minute = "0" + c.get(Calendar.MINUTE);
        } else {
            minute = String.valueOf(c.get(Calendar.MINUTE));
        }

        if (c.get(Calendar.SECOND) < 10) {
            second = "0" + c.get(Calendar.SECOND);
        } else {
            second = String.valueOf(c.get(Calendar.SECOND));
        }
        sbdate.append("").append(c.get(Calendar.YEAR)).append("-").append(math).append("-").append(date).append(" ").append(hour_or_day).append(":").append(minute).append(":").append(second);
        return sbdate.toString();
    }

    /**
     * 获得指定日期前N天日期
     *
     * @param dateStr8 日期格式 YYYYMMDD
     * @param n        前N天
     * @return 日期格式 YYYYMMDD
     */
    public static String getNDayBeforeForDate(String dateStr8, int n) {
        Calendar c = Calendar.getInstance();
        Date forDate = getDate_yyyyMMdd(dateStr8);
        c.setTime(forDate);
        c.add(Calendar.DAY_OF_MONTH, -n);
        String math = null;
        String date = null;
        StringBuffer sbdate = new StringBuffer("");
        if (c.get(Calendar.MONTH) + 1 < 10) {
            math = "0" + (c.get(Calendar.MONTH) + 1);
        } else {
            math = String.valueOf(c.get(Calendar.MONTH) + 1);
        }
        if (c.get(Calendar.DATE) < 10) {
            date = "0" + c.get(Calendar.DATE);
        } else {
            date = String.valueOf(c.get(Calendar.DATE));
        }
        sbdate.append("").append(c.get(Calendar.YEAR)).append(math).append(date);
        return sbdate.toString();
    }
    /**
        * 获得指定日期前N天日期
        *
        * @param dateStr8 日期格式 yyyyMMdd
        * @param n        前N天
        * @return 日期格式 yyyyMMddHHmmss
        */
       public static String get14StrDayBeforeForDate(String dateStr8, int n) {
           Calendar c = Calendar.getInstance();
           Date forDate = getDate_yyyyMMdd(dateStr8);
           c.setTime(forDate);
           c.add(Calendar.DAY_OF_MONTH, -n);
           String math = null;
           String date = null;
           StringBuffer sbdate = new StringBuffer("");
           if (c.get(Calendar.MONTH) + 1 < 10) {
               math = "0" + (c.get(Calendar.MONTH) + 1);
           } else {
               math = String.valueOf(c.get(Calendar.MONTH) + 1);
           }
           if (c.get(Calendar.DATE) < 10) {
               date = "0" + c.get(Calendar.DATE);
           } else {
               date = String.valueOf(c.get(Calendar.DATE));
           }
           sbdate.append(c.get(Calendar.YEAR)).append(math).append(date).append("000000");
           return sbdate.toString();
       }

    public static void main(String[] args) {
        System.out.println(GetTime());
    }
}