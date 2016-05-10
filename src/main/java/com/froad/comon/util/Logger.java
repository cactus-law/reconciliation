package com.froad.comon.util;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class Logger {

    // Default logger
    protected static org.slf4j.Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    String className;
    Class aClass;


    public Logger(String className) {
        this.className = className;
    }

    public Logger(Class aclass) {
        this.className = aclass.getName();
    }

    public static Logger getLogger(String className) {
        return new Logger(className);
    }

    public static Logger getLogger(Class className) {
        return new Logger(className);
    }

    /**
     * get logger by  className
     *
     * @param className className
     * @return
     */
    private static org.slf4j.Logger getRootLogger(String className) {

        if (className != null && className.length() > 0) {
            return LoggerFactory.getLogger(className);
        } else {
            return rootLogger;
        }
    }

    /**
     * 自定义
     *
     * @param e
     */
    public void error(Exception e) {
        getRootLogger(className).error("", e);
    }

    public void error(String msg, Exception e) {
        getRootLogger(className).error(msg, e);
    }

    /* ===================
     *      以下为logback的方法
     * ===================
     */

    public void info(String msg) {
        getRootLogger(className).info(msg);
    }

    public void info(String msg, Throwable t) {
        getRootLogger(className).info(msg, t);
    }

    public void info(String msg, Object arg) {
        getRootLogger(className).info(msg, arg);
    }

    public void info(String msg, Object arg1, Object arg2) {
        getRootLogger(className).info(msg, arg1, arg2);
    }

    public void info(String msg, Object[] argArray) {
        getRootLogger(className).info(msg, argArray);
    }

    public void info(Marker marker, String msg) {
        getRootLogger(className).info(marker, msg);
    }

    public void info(Marker marker, String msg, Object arg) {
        getRootLogger(className).info(marker, msg, arg);
    }

    public void info(Marker marker, String msg, Object[] argArray) {
        getRootLogger(className).info(marker, msg, argArray);
    }

    public void info(Marker marker, String msg, Object arg1, Object arg2) {
        getRootLogger(className).info(marker, msg, arg1, arg2);
    }

    public void info(Marker marker, String msg, Throwable t) {
        getRootLogger(className).info(marker, msg, t);
    }

    public void error(String msg) {
        getRootLogger(className).error(msg);
    }

    public void error(String msg, Throwable t) {
        getRootLogger(className).error(msg, t);
    }

    public void error(String msg, Object arg) {
        getRootLogger(className).error(msg, arg);
    }

    public void error(String msg, Object arg1, Object arg2) {
        getRootLogger(className).error(msg, arg1, arg2);
    }

    public void error(String msg, Object[] argArray) {
        getRootLogger(className).error(msg, argArray);
    }

    public void error(Marker marker, String msg) {
        getRootLogger(className).error(marker, msg);
    }

    public void error(Marker marker, String msg, Object arg) {
        getRootLogger(className).error(marker, msg, arg);
    }

    public void error(Marker marker, String msg, Object[] argArray) {
        getRootLogger(className).error(marker, msg, argArray);
    }

    public void error(Marker marker, String msg, Object arg1, Object arg2) {
        getRootLogger(className).error(marker, msg, arg1, arg2);
    }

    public void error(Marker marker, String msg, Throwable t) {
        getRootLogger(className).error(marker, msg, t);
    }

    public void debug(String msg) {
        getRootLogger(className).debug(msg);
    }

    public void debug(String msg, Throwable t) {
        getRootLogger(className).debug(msg, t);
    }

    public void debug(String msg, Object arg) {
        getRootLogger(className).debug(msg, arg);
    }

    public void debug(String msg, Object arg1, Object arg2) {
        getRootLogger(className).debug(msg, arg1, arg2);
    }

    public void debug(String msg, Object[] argArray) {
        getRootLogger(className).debug(msg, argArray);
    }

    public void debug(Marker marker, String msg) {
        getRootLogger(className).debug(marker, msg);
    }

    public void debug(Marker marker, String msg, Object arg) {
        getRootLogger(className).debug(marker, msg, arg);
    }

    public void debug(Marker marker, String msg, Object[] argArray) {
        getRootLogger(className).debug(marker, msg, argArray);
    }

    public void debug(Marker marker, String msg, Object arg1, Object arg2) {
        getRootLogger(className).debug(marker, msg, arg1, arg2);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        getRootLogger(className).debug(marker, msg, t);
    }

    public void warn(String msg) {
        getRootLogger(className).warn(msg);
    }

    public void warn(String msg, Throwable t) {
        getRootLogger(className).warn(msg, t);
    }

    public void warn(String msg, Object arg) {
        getRootLogger(className).warn(msg, arg);
    }

    public void warn(String msg, Object arg1, Object arg2) {
        getRootLogger(className).warn(msg, arg1, arg2);
    }

    public void warn(String msg, Object[] argArray) {
        getRootLogger(className).warn(msg, argArray);
    }

    public void warn(Marker marker, String msg) {
        getRootLogger(className).warn(marker, msg);
    }

    public void warn(Marker marker, String msg, Object arg) {
        getRootLogger(className).warn(marker, msg, arg);
    }

    public void warn(Marker marker, String msg, Object[] argArray) {
        getRootLogger(className).warn(marker, msg, argArray);
    }

    public void warn(Marker marker, String msg, Object arg1, Object arg2) {
        getRootLogger(className).warn(marker, msg, arg1, arg2);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        getRootLogger(className).warn(marker, msg, t);
    }
}
